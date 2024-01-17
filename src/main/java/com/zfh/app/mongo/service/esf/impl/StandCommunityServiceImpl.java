package com.zfh.app.mongo.service.esf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.fang.enumerate.RedisKeyConst;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.esf.StandCommunity;
import com.zfh.app.mongo.entity.esf.StandCommunityPrice;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.esf.StandCommunityPriceService;
import com.zfh.app.mongo.service.esf.StandCommunityService;

@Service
@Transactional
public class StandCommunityServiceImpl extends BaseMongoServiceImpl<StandCommunity> implements StandCommunityService {

	private static final Log logger = LogFactory.getLog(StandCommunityServiceImpl.class.getName());

	@Resource(name = "redis3Template")
	private RedisTemplate<String, Object> redisTemplate3;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private StandCommunityPriceService standCommunityPriceService;
	
	@Override
	protected Class<StandCommunity> getEntityClass() {
		// TODO Auto-generated method stub
		return StandCommunity.class;
	}

	
	@Override
	public StandCommunity getByStandId(String standId) {
		// TODO Auto-generated method stub
		return uniqueByProp("standId", standId);
	}

	@Override
	public List<StandCommunity> searchByBaseIds(String[] baseIds) {
		if (baseIds == null) {
			return null;
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("baseId").in(baseIds));
		return mongoTemplate.find(query, getEntityClass());
	}


	@Override
	public void analyzePriceTask() {
		if (!redisTemplate3.hasKey(RedisKeyConst.WAITING_PRICE_STAND_ID_SET)) {
			// redis 中是空的，添加数据到redis
			int pageSize = 1000;
			Page<StandCommunity> page = findPage(new PageRequest(0, pageSize));
			int totalPage = page.getTotalPages();
			int pageNum = totalPage;
			logger.info("将standId 写入redis 中, totalPage=" + totalPage);
			List<StandCommunity> list = new ArrayList<StandCommunity>();
			while (pageNum > 0) {
				logger.info("进度：" + pageNum + " / " + totalPage);
				page = findPage(new PageRequest(pageNum -1, pageSize));
				list = page.getContent();
				for (StandCommunity standCommunity : list) {
					redisTemplate3.opsForSet().add(RedisKeyConst.WAITING_PRICE_STAND_ID_SET, standCommunity.getStandId());
					
				}
				pageNum--;
			}
		}
		
		BigDecimal averagePrice = null;
		int index = 0;
		while (redisTemplate3.hasKey(RedisKeyConst.WAITING_PRICE_STAND_ID_SET)) {
			String standId = (String) redisTemplate3.opsForSet().pop(RedisKeyConst.WAITING_PRICE_STAND_ID_SET);
			averagePrice = getAveragePriceFromHouse(standId);
			if (averagePrice.compareTo(BigDecimal.ZERO) > 0) {
				StandCommunity standCommunity = getByStandId(standId);
				standCommunity.setAveragePrice(averagePrice);
				
				StandCommunityPrice standCommunityPrice = new StandCommunityPrice();
				BeanUtils.copyProperties(standCommunity, standCommunityPrice);
				
				standCommunityPrice.setId(null);
				standCommunityPrice.setUpdateTime(DateUtil.currentDateTime());
				super.save(standCommunity);
				standCommunityPriceService.save(standCommunityPrice);
			}
			index++;
			if (index % 1000 == 0) {
				logger.info("计算均价进度：" + (index / 1000));
			}
 		}
		
	}
	
	private BigDecimal getAveragePriceFromHouse(String standId) {
		int pageSize = 1000;
		String[] params = new String[]{"standId", "online"};
		Object[] values = new Object[]{standId, true};
		String[] includeFields = new String[] {"unitPrice"};
		Page<House> page = houseService.findPage(new PageRequest(0, pageSize), params, values, includeFields, null);
		int totalPage = page.getTotalPages();
		int pageNum = totalPage;
		List<House> list = new ArrayList<House>();
		BigDecimal totalPrice = BigDecimal.ZERO;
		int totalCount = 0;
		while (pageNum > 0) {
			page = houseService.findPage(new PageRequest(pageNum -1, pageSize), params, values, includeFields, null);
			list = page.getContent();
			for (House house : list) {
				totalPrice = totalPrice.add(house.getUnitPrice());
				totalCount++;
			}
			pageNum--;
		}
		if (totalCount > 0) {
			return totalPrice.divide(BigDecimal.valueOf(totalCount), 0, BigDecimal.ROUND_HALF_DOWN);
		}
		return BigDecimal.ZERO;
	}
}
