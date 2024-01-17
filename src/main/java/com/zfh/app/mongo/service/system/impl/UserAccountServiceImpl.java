package com.zfh.app.mongo.service.system.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mysiteforme.admin.util.AmapAPI;
import com.mysiteforme.admin.util.AmapIpLocation;
import com.mysiteforme.admin.util.ToolUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.DeviceAccess;
import com.zfh.app.mongo.entity.system.UserAccount;
import com.zfh.app.mongo.service.system.DeviceAccessService;
import com.zfh.app.mongo.service.system.UserAccountService;

@Service("userAccountService")
public class UserAccountServiceImpl extends BaseMongoServiceImpl<UserAccount> implements UserAccountService {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	
	@Autowired
	private DeviceAccessService deviceAccessService;
	
	private static final String CITY_GROUP_1 = "深圳东莞惠州";
	
	@Override
	protected Class<UserAccount> getEntityClass() {
		// TODO Auto-generated method stub
		return UserAccount.class;
	}

	/**
	 * 用户数据已经是唯一的，不需要用distinct
	 */
	@Override
	public int distinctCount(String startDate, String endDate) {
		Query query = new Query(Criteria.where("createDate").gte(startDate).lte(endDate));
		Long count = getMongoTemplate().count(query, getEntityClass());
		return count.intValue();
	}

	@Override
	public int distinctCount(String createDate) {
		Query query = new Query(Criteria.where("createDate").is(createDate));
		Long count = getMongoTemplate().count(query, getEntityClass());
		return count.intValue();
	}
	
	public Page findPageByCityList(PageRequest pageReq, String[] citys, String phone, String date) {
		Query query = new Query();
		if (StringUtils.isNotEmpty(phone)) {
			query.addCriteria(Criteria.where("phone").is(phone));
		}
		if (StringUtils.isNotEmpty(date)) {
			query.addCriteria(Criteria.where("createDate").lte(date));
		}
		if (citys != null && citys.length > 0) {
			query.addCriteria(Criteria.where("telCity").in(citys));
		}
		query.with(pageReq);
		Long total = mongoTemplate.count(query, getEntityClass());
		List<UserAccount> list = mongoTemplate.find(query, getEntityClass());

		return new PageImpl<UserAccount>(list, pageReq, total);
	}

	@Override
	public void searchUserIp() {
		// 限制每天只能查100个
		PageRequest pageReq = new PageRequest(0, 100);

		Page<UserAccount> userNoCityPage = findPage(pageReq, "city", null);
		for (UserAccount ua : userNoCityPage.getContent()) {
			// IP 归属地查询
			if (StringUtils.isEmpty(ua.getProvince())) {
				try {
					Thread.sleep(1000); // 限制频率，太快了接口可能无返回值
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<DeviceAccess> deviceAccessList = deviceAccessService.findByProp("clientKey", ua.getClientKey());
				if (deviceAccessList != null && deviceAccessList.size() > 0) {
					String clientIp = deviceAccessList.get(0).getClientIp();
					AmapIpLocation ipLocation = AmapAPI.ipLocation(clientIp);
					
					if (StringUtils.isEmpty(ipLocation.getProvince()) && StringUtils.isEmpty(ipLocation.getCity())) {
						continue;
					}
					String city = ipLocation.getCity();
					String province = ipLocation.getProvince();
					city = city.replace("市", "");
					province = province.replace("市", "");	
					// 有直辖市的情况
					if ((StringUtils.isNotEmpty(city) && CITY_GROUP_1.contains(city)) || (StringUtils.isNotEmpty(province) && CITY_GROUP_1.contains(province))) {
						ua.setCityGroup(1);
						ua.setCity(city);
						ua.setProvince(province);
						super.save(ua);
					} else {
						if (StringUtils.isNotEmpty(city)) {
							ua.setCity(city);
							ua.setProvince(province);
							super.save(ua);
						}
					}
				} else {
					deviceAccessService.createDeviceAccess(ua.getCreateDate());
				}
			}
		}
		
		Page<UserAccount> singlepage = findPage(pageReq, "telCity", null);
		List<UserAccount> uaList = singlepage.getContent();
		for (UserAccount ua : uaList) {
			// 手机归属地查询
			if (StringUtils.isEmpty(ua.getTelProvince())) {
				try {
					Thread.sleep(1000); // 限制频率，太快了接口可能无返回值
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Map<String, String> resultMap = ToolUtil.getAddressByTelPhone(ua.getPhone());
				String city = resultMap.get("city");
				String province = resultMap.get("province");
				city = city.replace("市", "");
				province = province.replace("市", "");
				if ((StringUtils.isNotEmpty(city) && CITY_GROUP_1.contains(city)) || (StringUtils.isNotEmpty(province) && CITY_GROUP_1.contains(province))) {
					ua.setCityGroup(1);
					ua.setTelCity(city);
					ua.setTelProvince(province);
					super.save(ua);
				} else {
					if (StringUtils.isNotEmpty(city) && !StringUtils.equals(city, "未知")) {
						ua.setTelCity(city);
						ua.setTelProvince(province);
						super.save(ua);
					} else if (StringUtils.isNotEmpty(province) && StringUtils.isEmpty(city)) {
						// 直辖市只有province字段，没有city字段
						ua.setTelCity(province);
						ua.setTelProvince(province);
						super.save(ua);
					}
				}
			}
		}
		
	}

}
