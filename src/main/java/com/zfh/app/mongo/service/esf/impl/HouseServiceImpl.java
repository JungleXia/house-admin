package com.zfh.app.mongo.service.esf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mysiteforme.admin.util.Constants;
import com.mysiteforme.admin.util.MD5Util;
import com.zfh.app.core.BathUpdateOptions;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.esf.CommunityBase;
import com.zfh.app.mongo.entity.esf.CommunityBaseRef;
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.entity.esf.ErshoufangAnalysis;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.esf.HouseHistory;
import com.zfh.app.mongo.entity.esf.StandCommunity;
import com.zfh.app.mongo.entity.esf.StandUnitType;
import com.zfh.app.mongo.model.DistrictModel;
import com.zfh.app.mongo.model.RegionModel;
import com.zfh.app.mongo.service.esf.CommunityBaseRefService;
import com.zfh.app.mongo.service.esf.CommunityBaseService;
import com.zfh.app.mongo.service.esf.CommunityService;
import com.zfh.app.mongo.service.esf.ErshoufangAnalysisService;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseHistoryService;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.esf.StandCommunityService;
import com.zfh.app.mongo.service.esf.StandUnitTypeService;

@Service("houseMongoService")
public class HouseServiceImpl extends BaseMongoServiceImpl<House> implements HouseService {

	private static final Log logger = LogFactory.getLog(HouseServiceImpl.class.getName());

	@Autowired
	private ErshoufangService ershoufangService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private StandUnitTypeService standUnitTypeService;

	@Autowired
	private ErshoufangAnalysisService ershoufangAnalysisService;
	
	@Autowired
	private CommunityBaseRefService communityBaseRefService;
	
	@Autowired
	private CommunityBaseService communityBaseService;
	
	@Autowired
	private StandCommunityService standCommunityService;

	@Autowired
	private HouseHistoryService houseHistoryService;
	
	@Resource(name = "redis2Template")
	private RedisTemplate<String, Object> redisTemplate2;

	private static final BigDecimal NEG_ONE = BigDecimal.valueOf(-1);

	private static final BigDecimal MIN_RATE = BigDecimal.valueOf(0.8);
	
	private static final BigDecimal MAX_RATE = BigDecimal.valueOf(1.2);
	
	private static final String SPECIAL_PATTERN = "(复式)|(双拼)|(别墅)|(商铺)|(复试)|(打通)|(门面)|(叠墅)|(洋房)";
	
	private final static Map<Integer, String> ROOM_TYPE = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(1, "一室");
			put(2, "二室");
			put(3, "三室");
			put(4, "四室");
			put(5, "五室");
			put(6, "六室");
			put(7, "七室");
			put(8, "八室");
			put(9, "九室");
		}
	};
	
	@Override
	protected Class<House> getEntityClass() {
		// TODO Auto-generated method stub
		return House.class;
	}

	@Override
	public void copyErshoufang() {
		// 复制前先将全部House状态设为下架
		logger.info("->开始下架上一批数据");
		updateAllOffline();
		logger.info("->结束下架上一批数据");
		
		// 线程池维护线程的最少数量
		int corePoolSize = 5;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 10;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 10;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int pageNum = 0;
		int pageSize = 1000;
		Page<Ershoufang> page = ershoufangService.findReal(pageNum, pageSize);
		int totalPage = page.getTotalPages();
		logger.info("-> totalSize: " + (totalPage * pageSize));
		final int tota = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<Ershoufang> houseList = ershoufangService.findReal(pageIndex, pageSize).getContent();
					System.out.println("当前页，倒数：" + pageIndex + " / " + tota);
					// 执行同步
					doCopy(houseList);
				}

			}));
		}
		threadPool.shutdown();

		try {
			while (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
				// threalPool has not shutdown all
			}
			logger.info("开始检查推荐房源小区");
			redisCacheCommunityEmpty();
			logger.info("结束同步推荐房源");
		} catch (InterruptedException e) {
			logger.error(e);
		}

	}

	/**
	 * 新增或更新
	 * 
	 * @param list
	 *            2019年5月22日下午2:02:59
	 */
	@Override
	public void doCopy(List<Ershoufang> list) {
		House house = null;
		for (Ershoufang ershoufang : list) {
			house = transform(ershoufang);
//			if (house != null) {
//				backupHouse(house, ershoufang.getVersionDate());
//			}
		}
	}

	/**
	 * 过滤 房源
	 * @param ershoufang	
	 * @return true: 过滤掉， false: 往下进行
	 * 2019年8月21日下午6:00:43
	 */
	public boolean filterHouse(Ershoufang ershoufang) {
		if (ershoufang.getTotalPrice() == null || ershoufang.getUnitPrice() == null || ershoufang.getArea() == null) {
			return true;
		}
		if (StringUtils.isEmpty(ershoufang.getStandId())) {
			return true;
		}
		String houseType = ershoufang.getHouseType();
		if (StringUtils.isEmpty(houseType)) {
			return true;
		}
		if (ershoufang.getTotalPrice() == null
				|| (ershoufang.getTotalPrice() != null 
				&& ershoufang.getTotalPrice().compareTo(BigDecimal.valueOf(10)) < 0) ) {
			return true;
		}

		// 修正room 为 0
		if (ershoufang.getRoom() == 0) {
			if (houseType.matches("(\\d)室") ){
				Pattern r = Pattern.compile("(\\d)室");
				Matcher m = r.matcher(houseType);
				if (m.find()) {
					ershoufang.setRoom(Integer.valueOf(m.group(1)));
					ershoufangService.save(ershoufang);
				}
			}
			// 写字楼、别墅、商铺、等等都为0
		}
		if (ershoufang.getRoom() == 0) {
			return true;
		}

		return false;
	}
	
	
	/**
	 * 数据转换
	 * 
	 * @param ershoufang
	 * @return House 2019年5月22日下午2:03:03
	 */
	public House transform(Ershoufang ershoufang) {
		if (filterHouse(ershoufang)) {
			return null;
		}
		StandCommunity standCommunity = standCommunityService.getByStandId(ershoufang.getStandId());
		if (standCommunity == null) {
			logger.error("标准小区未找到：houseId: " + ershoufang.getId() + ", standId: " + ershoufang.getStandId());
			return null;
		}
		String baseId = standCommunity.getBaseId();
		if (StringUtils.isEmpty(baseId)) {
			logger.error("标准小区未绑定baseId：standId: " + standCommunity.getStandId() + ", standName: " + standCommunity.getStandName());
			return null;
		}
		CommunityBaseRef baseRef = communityBaseRefService.uniqueByProp("baseId", baseId);
		if (baseRef == null) {
			logger.error("基础小区无关联的中介小区：baseId: " +  baseId);
			baseRef = communityBaseRefService.autoBind(ershoufang.getCommunity().getCommunity(), ershoufang.getDataFrom(), baseId, standCommunity.getBaseName());
			if (baseRef == null)
				return null;
		}
		House house = getById(ershoufang.getId());
		
		if (house == null) {
			house = new House();
			// copyProperties
			BeanUtils.copyProperties(ershoufang, house);
			String communtiyUrl = ershoufang.getCommunity().getUrl();
			String md5Url = ershoufang.getCommunity().getMd5Url();
			if (communtiyUrl.split("\\?").length > 1) {
				md5Url = MD5Util.MD5(communtiyUrl.split("\\?")[0]);
			}
			// set community
			Community community = communityService.uniqueByProp("md5Url", md5Url);
			if (community != null) {
				house.setCommunity(community.getCommunity());
				house.setCommunityId(community.getId());
				if (StringUtils.isNotEmpty(community.getLonglat())) {
					try {
						house.setLonglat(community.getLonglat());
						String[] point = community.getLonglat().split(",");
						if (point != null && point.length > 1) {
							GeoJsonPoint geopoint = new GeoJsonPoint(Double.valueOf(point[0]),
									Double.valueOf(point[1]));
							house.setGeometry(geopoint);
						}
					} catch (Exception e) {
						logger.error("community.longlat is invalid", e);
					}
				} else {
					CommunityBase communityBase = communityBaseService.uniqueByProp("baseId", baseId);
					if (communityBase == null) {
						return null;
					}
					String[] point = communityBase.getLonglat().split(",");
					if (point != null && point.length > 1) {
						GeoJsonPoint geopoint = new GeoJsonPoint(Double.valueOf(point[0]),
								Double.valueOf(point[1]));
						house.setGeometry(geopoint);
					}
				}
			}
			if (house.getGeometry() == null) {
				return null;
			}
			// 特殊数据标记
			house = specialMark(house);
			
			// set regions
			RegionModel regions = ershoufang.getRegions();
			house.setCity(regions.getCity());
			house.setDistrict(regions.getDistrict());
			house.setBlockName(regions.getBlockName());
			house.setXin(false);
			super.save(house);
		} else {
			// 特殊数据标记
			house = specialMark(house);
			// 更新数据
			house.setUrl(ershoufang.getUrl());
			house.setTitle(ershoufang.getTitle());
			if (ershoufang.isExpired()) {	// 如果是过期的房源，直接让它下架
				house.setOnline(false);
			} else {
				house.setOnline(ershoufang.isOnline());
			}
			house.setTotalPrice(ershoufang.getTotalPrice());
			house.setUnitPrice(ershoufang.getUnitPrice());
			house.setArea(ershoufang.getArea());
			house.setHouseType(ershoufang.getHouseType());
			house.setHuxing(ershoufang.getHuxing());
			house.setRoom(ershoufang.getRoom());
			super.save(house);
		}
		return house;
	}

	/**
	 * 备份
	 * @param hosue
	 * @return
	 * 2019年10月29日下午4:03:24
	 */
	public void backupHouse(House house, String versionDate) {
		HouseHistory houseHistory = houseHistoryService.findByHouseIdAndVersionDate(house.getId(), versionDate);
		if (houseHistory == null) {
			houseHistory = new HouseHistory();
			BeanUtils.copyProperties(house, houseHistory);
			houseHistory.setId(null);
			houseHistory.setHouseId(house.getId());
			houseHistory.setVersionDate(versionDate);
			houseHistoryService.save(houseHistory);
		}
	}
	
	// 特殊数据标记
	public House specialMark(House house) {
		String title = house.getTitle();
		Pattern p = Pattern.compile(SPECIAL_PATTERN); 
		Matcher m = p.matcher(title); 
		if (m.find()) { 
			house.setSpecial(true);
			house.setSpecialText(m.group());
			if (house.getSpecialText().equals("复试")) {
				house.setSpecialText("复式");
			}
		} else {
			house.setSpecial(false);
		}
		return house;
	}
	@Override
	public void redisCacheCommunityEmpty() {
		// 线程池维护线程的最少数量
		int corePoolSize = 5;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 20;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 10;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(300);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int pageNum = 0;
		final int pageSize = 1000;
		Page<House> page = findCommunityEnpty(pageNum, pageSize);
		int totalPage = page.getTotalPages();
		logger.info("-> totalSize: " + (totalPage * pageSize));
		final int total = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<House> houseList = findCommunityEnpty(pageIndex, pageSize).getContent();
					System.out.println("当前页，倒数：" + pageIndex + " / " + total);
					// 执行存储
					redisCommunity(houseList);
				}

			}));
		}
		threadPool.shutdown();
	}

	@Override
	public Page<House> findCommunityEnpty(int pageNum, int pageSize) {
		return findPage(new PageRequest(pageNum, pageSize), "communityId", null, new String[] { "houseCode" }, null);
	}

	/**
	 * 找到小区链接并存储到redis数据库中
	 * 
	 * @param list
	 *            2019年5月22日下午6:23:16
	 */
	public void redisCommunity(List<House> list) {
		String[] ids = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i).getId();
		}
		List<Ershoufang> esfList = ershoufangService.findFieldsByIds(ids, new String[] { "community", "dataFrom" });
		for (Ershoufang ershoufang : esfList) {
			cacheRedisCommunity(ershoufang);
		}
	}

	public void cacheRedisCommunity(Ershoufang esf) {
		String dataFrom = esf.getDataFrom();
		String communityUrl = esf.getCommunity().getUrl();
		if (StringUtils.isEmpty(dataFrom) || StringUtils.isEmpty(communityUrl)) {
			return;
		}
		String redisKey = null;
		if (StringUtils.equals(dataFrom, Constants.DATA_FROM_QF)) {
			if (communityUrl.split("\\?").length > 1) {
				communityUrl = communityUrl.split("\\?")[0];
			}
			redisKey = "qfang:community_url";
		} else if (StringUtils.equals(dataFrom, Constants.DATA_FROM_LIANJIA)) {
			System.err.println("id : " + esf.getId() + " , url:" + communityUrl);
			redisKey = "lianjia:community_url";
		} else if (StringUtils.equals(dataFrom, Constants.DATA_FROM_ZY)) {
			redisKey = "zhongyuan:community_url";
		} else if (StringUtils.equals(dataFrom, Constants.DATA_FROM_LYJ)) {
			redisKey = "leyoujia:community_url";
		}
		if (redisKey != null)
			redisTemplate2.opsForList().leftPush(redisKey, communityUrl);
	}

	@Override
	public List<Object> getDistinctRoom(String standId) {
		DBObject query = new BasicDBObject();
		query.put("standId", standId);
		return getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).distinct("room",
				query);
	}

	@Override
	public BigDecimal getAveragePrice(String standId, int room, int status) {	
		/**
		 * 使用javascript
		 */
		List<DistrictModel> list = new ArrayList<DistrictModel>();
		Query query = new Query();
		query.addCriteria(Criteria.where("standId").is(standId).and("room").is(room).and("online").is(true).and("special").is(false).and("status").is(status));
		String reduce = "function(doc, aggr){" +
		"            aggr.count += 1;" +
		"			 var unitP = parseInt(doc.unitPrice);" +
		"			 aggr.totalUnit += unitP;" +
		"        }";

		DBObject result = getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).group(
		new BasicDBObject("standId",1).append("room", 1),
		query.getQueryObject(),
		new BasicDBObject("count", 0).append("totalUnit", 0),
		reduce);

		Map map = result.toMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			BasicDBObject obj = (BasicDBObject) map.get(key);
			int count = obj.getInt("count");
			int totalUnit = obj.getInt("totalUnit");
			if (count == 0) {
				return BigDecimal.ZERO;
			}
			return BigDecimal.valueOf(Long.valueOf(String.valueOf(totalUnit / count)));
		}

		return BigDecimal.ZERO;
	}

	@Override
	public void taskFullAnalysis() {
		// 线程池维护线程的最少数量
		int corePoolSize = 4;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 8;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 10;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int pageNum = 0;
		int pageSize = 200;
		Page<StandUnitType> page = standUnitTypeService.findPage(new PageRequest(pageNum, pageSize));
		int totalPage = page.getTotalPages();
		logger.info("-> totalSize: " + page.getTotalElements());
		final int total = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<StandUnitType> houseList = standUnitTypeService.findPage(new PageRequest(pageIndex, pageSize, new Sort(Direction.DESC, "_id")))
							.getContent();
					System.out.println("当前页，倒数：" + pageIndex + " / " + total);
					// 执行分析
					doTaskFullAnalysis(houseList);
				}

			}));
		}
		threadPool.shutdown();
		
		try {
			while (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
				// threalPool has not shutdown all
			}
			logger.info("开始计算特殊关键字房源均价和折扣");
			taskSpecialAnalysis();
			logger.info("结束计算特殊关键字房源均价和折扣");
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	public void doTaskFullAnalysis(List<StandUnitType> list) {
		for (StandUnitType sut : list) {
			// 填充 House 数据
			fullHouse(sut, sut.getStandId(), sut.getRoom());
		}
	}

	public void fullHouse(StandUnitType sut, String standId, int room) {
		// 计算总量, 一次更新500个
		int pageSize = 500;
		Page<House> housePage = findPage(new PageRequest(0, pageSize),
				new String[] { "standId", "room", "online" },
				new Object[] { standId, room, true },
				new String[] { "unitPrice", "totalPrice" }, null);
		
		int totalPages = housePage.getTotalPages();
		List<House> houseList = new ArrayList<>();
		while (totalPages > 0) {
			totalPages = totalPages - 1;
			int pageIndex = totalPages;
			housePage = findPage(new PageRequest(pageIndex, pageSize),
					new String[] { "standId", "room", "online" },
					new Object[] { standId, room, true }, null);
			
			houseList = housePage.getContent();
			List<BathUpdateOptions> options = new ArrayList<>();
			if (houseList != null && houseList.size() > 0) {
				for (House house : houseList) {
					try {
						house.setAveragePrice(sut.getAvgUnit());
						house.setDiscountRate(
								house.getUnitPrice().divide(sut.getAvgUnit(), 2, BigDecimal.ROUND_HALF_DOWN));
						house.setTips(getPriceTips(house.getDiscountRate(), house.getTotalPrice()));

						// 只能暂时这样处理
						// 折扣低于0.8的设为疑问房源
						if (house.getDiscountRate().compareTo(MIN_RATE) < 0) {
							house.setStatus(10 + house.getStatus()); // 疑问房源
							house.setOnline(false);
						} else if (house.getDiscountRate().compareTo(MAX_RATE) >=0 ) {
							house.setStatus(20 + house.getStatus());
							house.setOnline(false);
						} else {
							house.setStatus(0);
							house.setOnline(true);
						}
						Update update = Update.update("averagePrice", house.getAveragePrice().doubleValue())
								.set("discountRate", house.getDiscountRate().doubleValue())
								.set("tips", house.getTips())
								.set("online", house.isOnline())
								.set("status", house.getStatus());
						options.add(new BathUpdateOptions(
								Query.query(Criteria.where("_id").is(new ObjectId(house.getId()))), update, false,
								false));
						
					} catch (Exception e) {
						logger.error("house:" + JSON.toJSONString(house));
						logger.error("sut:" + JSON.toJSONString(sut));
						logger.error(e.getMessage(), e);
					}
					
					createEsfAnalysis(house, sut, standId, room);
				}
			}
			// 批量更新
			if (options != null && options.size() > 0) {
				super.bathUpdate(options);
				ershoufangService.bathUpdate(options);
			}
		}
	}
	
	public void createEsfAnalysis(House entity, StandUnitType sut, String standId, int room) {
		Ershoufang house = ershoufangService.getById(entity.getId());
		List<BathUpdateOptions> options = new ArrayList<>();
		if (house != null && house.getUnitPrice() != null) {
			house.setAveragePrice(sut.getAvgUnit());
			house.setDiscountRate(
					house.getUnitPrice().divide(sut.getAvgUnit(), 2, BigDecimal.ROUND_HALF_DOWN));
			house.setTips(getPriceTips(house.getDiscountRate(), house.getTotalPrice()));

			options.add(new BathUpdateOptions(
					Query.query(Criteria.where("_id").is(new ObjectId(house.getId()))),
					Update.update("averagePrice", house.getAveragePrice().doubleValue()).set("discountRate",
							house.getDiscountRate().doubleValue()),
					false, false));

			ErshoufangAnalysis esfAnalysis = ershoufangAnalysisService.getById(house.getId());
			if (esfAnalysis == null) {
				esfAnalysis = new ErshoufangAnalysis();
				esfAnalysis.setId(house.getId());
			}
			if (entity.isLowestTotal()) {
				esfAnalysis.setReason1("本房源为本小区"+sut.getName()+"户型中总价最低，与你预算匹配");
			} else if (entity.isLowestUnit()) {
				esfAnalysis.setReason1("本房源为本小区"+sut.getName()+"户型中均价最低，与你预算匹配");
			} else {
				esfAnalysis.setReason1("本房源已验证为真实房源");
			}
			
			esfAnalysis.setReason2("");
			esfAnalysis.setDisRate(house.getDiscountRate());
			esfAnalysis.setHouseDetails("");
			esfAnalysis.setHouseId(house.getId());
			esfAnalysis.setsTotalPrice(house.getTotalPrice().divide(house.getDiscountRate(), 0, BigDecimal.ROUND_HALF_DOWN));
			esfAnalysis.setsUnitPrice(sut.getAvgUnit());
			esfAnalysis.setTotalPrice(house.getTotalPrice());
			esfAnalysis.setUnitPrice(house.getUnitPrice());
			esfAnalysis.setBelowTotal(esfAnalysis.getsTotalPrice().subtract(house.getTotalPrice()));
			esfAnalysis.setBelowUnit((BigDecimal.ONE.subtract(house.getDiscountRate()))
					.multiply(esfAnalysis.getsUnitPrice()).setScale(0, BigDecimal.ROUND_HALF_DOWN));

			esfAnalysis.setAvgP("本小区均价：" + sut.getAvgUnit().intValue() + "元/㎡");
			// 总价差值 大于0，低于总价平均值
			if (esfAnalysis.getBelowTotal().intValue() > 0) {
				esfAnalysis.setTotalP("低于本小区同户型总价平均值约" + esfAnalysis.getBelowTotal().intValue() + "万");
			} else if (esfAnalysis.getBelowTotal().intValue()== 0) {
				esfAnalysis.setTotalP("与本小区同户型总价平均值持平");
			} else {
				esfAnalysis.setTotalP("高于同户型总价平均值约" + NEG_ONE.multiply(esfAnalysis.getBelowTotal()).intValue() + "万");
			}

			if (esfAnalysis.getBelowUnit().intValue() > 0) {
				esfAnalysis.setUnitP("低于本小区同户型均价"
						+ esfAnalysis.getBelowUnit().intValue() + "元/㎡，相当于" + BigDecimal.TEN
								.multiply(esfAnalysis.getDisRate()).setScale(1, BigDecimal.ROUND_HALF_DOWN)
						+ "折");
			} else if (esfAnalysis.getBelowUnit().intValue() == 0) {
				esfAnalysis.setUnitP("与本小区同户型均价持平");
			} else {
				esfAnalysis.setUnitP("高于本小区同户型均价" + NEG_ONE.multiply(esfAnalysis.getBelowUnit()).intValue() + "元/㎡");
			}
			ershoufangAnalysisService.save(esfAnalysis);
		}
	
	}

	
	public String getPriceTips(BigDecimal discountRate, BigDecimal totalPrice) {
		String tips = "";
		BigDecimal discount = BigDecimal.ZERO;
		totalPrice = totalPrice.divide(discountRate, 1, BigDecimal.ROUND_HALF_DOWN);
		/**
		 * compareTo -1 小于, 0 等于, 1 大于
		 */
		if (discountRate.compareTo(BigDecimal.ONE) == 1) {
			discount = (discountRate.subtract(BigDecimal.ONE)).multiply(totalPrice).setScale(1,
					BigDecimal.ROUND_HALF_UP);
			tips = "高于同户型均价 " + discount.intValue() + "万";
		} else if (discountRate.compareTo(BigDecimal.ONE) == 0) {
			discount = BigDecimal.ZERO;
			tips = "和市场价持平";
		} else {
			discount = (BigDecimal.ONE.subtract(discountRate)).multiply(totalPrice).setScale(1,
					BigDecimal.ROUND_HALF_UP);
			tips = "低于同户型均价 " + discount.intValue() + "万";
		}
		return tips;
	}
	
	// 谨慎使用，注意检查
	/**
	 * 上所有上架的House 设置为下架，分析状态初始化为0
	 * 
	 * 2019年11月7日下午12:38:35
	 */
	public void updateAllOffline() {
		List<BathUpdateOptions> options = new ArrayList<>();
		Update update = Update.update("online", false)
				.set("status", 0)
				.set("lowestUnit", false)
				.set("lowestTotal", false)
				.set("reco", false);
		options.add(new BathUpdateOptions(
				Query.query(Criteria.where("online").is(true)), update, false,
				true));
		super.bathUpdate(options);
	}

	@Override
	public BigDecimal getSpecialAveragePrice(String standId, int room, int status, String specialText) {
		/**
		 * 使用javascript
		 */
		List<DistrictModel> list = new ArrayList<DistrictModel>();
		Query query = new Query();
		query.addCriteria(Criteria.where("standId").is(standId).and("room").is(room).and("online").is(true).and("special").is(true).and("status").is(status).and("specialText").is(specialText));
		String reduce = "function(doc, aggr){" +
		"            aggr.count += 1;" +
		"			 var unitP = parseInt(doc.unitPrice);" +
		"			 aggr.totalUnit += unitP;" +
		"        }";

		DBObject result = getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).group(
		new BasicDBObject("standId",1).append("room", 1),
		query.getQueryObject(),
		new BasicDBObject("count", 0).append("totalUnit", 0),
		reduce);

		Map map = result.toMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			BasicDBObject obj = (BasicDBObject) map.get(key);
			int count = obj.getInt("count");
			int totalUnit = obj.getInt("totalUnit");
			if (count == 0) {
				return BigDecimal.ZERO;
			}
			return BigDecimal.valueOf(Long.valueOf(String.valueOf(totalUnit / count)));
		}

		return BigDecimal.ZERO;
	}
	
	public void taskSpecialAnalysis() {
		// 线程池维护线程的最少数量
		int corePoolSize = 4;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 8;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 10;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int pageNum = 0;
		int pageSize = 200;
		String[] propsName = new String[]{"online", "status", "special"};
		Object[] values = new Object[]{true, 0, true};
		Page<House> page = findPage(new PageRequest(pageNum, pageSize), propsName, values, null);
		int totalPage = page.getTotalPages();
		logger.info("-> totalSize: " + page.getTotalElements());
		final int total = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<House> houseList = findPage(new PageRequest(pageNum, pageSize), propsName, values, null)
							.getContent();
					// 执行分析
					doTaskSpecialAnalysis(houseList);
					System.out.println("当前页，倒数：" + pageIndex + " / " + total);
				}

			}));
		}
		threadPool.shutdown();
	}

	public void doTaskSpecialAnalysis(List<House> houseList) {
		List<BathUpdateOptions> options = new ArrayList<>();
		StandUnitType sut = new StandUnitType();
		for (House house : houseList) {
			try {
				BigDecimal averageUnit = getSpecialAveragePrice(house.getStandId(), house.getRoom(), 0, house.getSpecialText());
				if (averageUnit.compareTo(BigDecimal.ZERO) == 0) {
					logger.error("error zero standId="+ house.getStandId() + ", room=" + house.getRoom());
					house.setOnline(false);
					super.save(house);
					continue;
				}
				house.setAveragePrice(averageUnit);
				house.setDiscountRate(
						house.getUnitPrice().divide(averageUnit, 2, BigDecimal.ROUND_HALF_DOWN));
				house.setTips(getPriceTips(house.getDiscountRate(), house.getTotalPrice()));

				// 只能暂时这样处理
				// 折扣低于0.8的设为疑问房源
				if (house.getDiscountRate().compareTo(MIN_RATE) < 0) {
					house.setStatus(10 + house.getStatus()); // 疑问房源
					house.setOnline(false);
				} else if (house.getDiscountRate().compareTo(MAX_RATE) >=0 ) {
					house.setStatus(20 + house.getStatus());
					house.setOnline(false);
				} else {
					house.setStatus(0);
					house.setOnline(true);
				}
				Update update = Update.update("averagePrice", house.getAveragePrice().doubleValue())
						.set("discountRate", house.getDiscountRate().doubleValue())
						.set("tips", house.getTips())
						.set("online", house.isOnline())
						.set("status", house.getStatus());
				options.add(new BathUpdateOptions(
						Query.query(Criteria.where("_id").is(new ObjectId(house.getId()))), update, false,
						false));
				
				sut.setAvgUnit(averageUnit);
				sut.setName(ROOM_TYPE.get(house.getRoom()));
				sut.setRoom(house.getRoom());
				createEsfAnalysis(house, sut, house.getStandId(), house.getRoom());
				
			} catch (Exception e) {
				logger.error("special house:" + JSON.toJSONString(house));
				logger.error(e.getMessage(), e);
			}
		}
		
		// 批量更新
		if (options != null && options.size() > 0) {
			super.bathUpdate(options);
			ershoufangService.bathUpdate(options);
		}
	}
}
