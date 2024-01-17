package com.zfh.app.mongo.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.AccessLog;
import com.zfh.app.mongo.entity.system.AdConveRecord;
import com.zfh.app.mongo.entity.system.Device;
import com.zfh.app.mongo.entity.system.DeviceAccess;
import com.zfh.app.mongo.entity.system.DeviceActivate;
import com.zfh.app.mongo.entity.system.UserAccount;
import com.zfh.app.mongo.entity.system.UserBuylimit;
import com.zfh.app.mongo.entity.system.UserPosition;
import com.zfh.app.mongo.entity.system.UserRequirement;
import com.zfh.app.mongo.service.system.AccessLogMongoService;
import com.zfh.app.mongo.service.system.AdConveRecordService;
import com.zfh.app.mongo.service.system.DeviceAccessService;
import com.zfh.app.mongo.service.system.DeviceActivateService;
import com.zfh.app.mongo.service.system.DeviceService;
import com.zfh.app.mongo.service.system.HouseViewHistoryService;
import com.zfh.app.mongo.service.system.UserAccountService;
import com.zfh.app.mongo.service.system.UserBuylimitService;
import com.zfh.app.mongo.service.system.UserPositionService;
import com.zfh.app.mongo.service.system.UserRequirementService;

@Service
public class DeviceActivateServiceImpl extends BaseMongoServiceImpl<DeviceActivate> implements DeviceActivateService{

	private static final Log logger = LogFactory.getLog(DeviceActivateServiceImpl.class.getName());
	
	@Resource(name = "redis2Template")
	private RedisTemplate<String, Object> redisTemplate2;
	
	@Autowired
	private AccessLogMongoService accessLogMongoService;
	
	@Autowired
	private AdConveRecordService adConveRecordService;
	
	@Autowired
	private UserPositionService userPositionService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private HouseViewHistoryService houseViewHistoryService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserBuylimitService userBuylimitService;
	
	@Autowired
	private UserRequirementService userRequirementService;
	
	@Autowired
	private DeviceAccessService deviceAccessService;
	
	@Override
	protected Class<DeviceActivate> getEntityClass() {
		// TODO Auto-generated method stub
		return DeviceActivate.class;
	}

	@Override
	public int distinctCount(String createDate) {
		Set<String> dateList = new HashSet<String>();
		Query query = new Query(Criteria.where("createDate").is(createDate));
		String reduce = "function(doc, aggr){" +
		"            aggr.count += 1;" +
		"        }";

		DBObject result = getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).group(
		new BasicDBObject("clientKey",1),
		query.getQueryObject(),
		new BasicDBObject("count", 1),
		reduce);

		Map map = result.toMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			BasicDBObject obj = (BasicDBObject) map.get(key);
			dateList.add(obj.getString("clientKey"));
		}
		return dateList.size();
	
	}

	@Override
	public void replenishFromAccessLog(String startDate, String endDate) {
		List<DeviceAccess> deviceList = deviceAccessService.findListBy(startDate, endDate);
		for (DeviceAccess device : deviceList) {
			String clientKey = device.getClientKey();
			if (!StringUtils.isEmpty(clientKey)) {
				DeviceActivate deviceActivate = uniqueByProp("clientKey", clientKey);
				if (deviceActivate == null) {
					AccessLog accessLog = accessLogMongoService.uniqueByProp("clientKey", clientKey);
					if (accessLog != null) {
						try {
							deviceActivate = new DeviceActivate();
							deviceActivate.setClient(accessLog.getClient());
							deviceActivate.setClientKey(clientKey);
							deviceActivate.setCreateDate(accessLog.getCreateDate());
							deviceActivate.setCreateTime(accessLog.getCreateTime());
							String[] splitStr = accessLog.getBrowser().split("/");
							deviceActivate.setOs(splitStr[splitStr.length - 1]);
							// 排除微信爬虫
							if (org.apache.commons.lang3.StringUtils.equals(deviceActivate.getClient(), "weixin")
									&& deviceActivate.getOs().indexOf("iphone 11_0") > -1 ) {
								continue;
							}
							// 广告来源
							AdConveRecord record = adConveRecordService.uniqueByProps(new String[]{"mac1", "status"}, new Object[]{DigestUtils.md5Hex(clientKey), 1});
							if (record != null) {
								deviceActivate.setAdTime(record.getCreateTime());
								deviceActivate.setAndroid_id(record.getAndroid_id());
								deviceActivate.setCsite(record.getCsite());
							}
							super.save(deviceActivate);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
		}
		
	}
	
	public void statistics(String startDate, String endDate) {
		// 注册前定位绑定用户id
		refreshPositions();

		int pageSize = 100;
		logger.info("startDate: " + startDate + ", endDate:" + endDate);
		Page<DeviceActivate> page = findPageByCreateDate(new PageRequest(0, pageSize), startDate, endDate);
		int totalPage = page.getTotalPages();
		logger.info("statistics -> totalSize: " + page.getTotalElements());
		final int tota = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			int pageIndex = totalPage;
			
			List<DeviceActivate> deviceList = findPageByCreateDate(new PageRequest(pageIndex, pageSize), startDate, endDate).getContent();
			System.out.println("当前页，倒数：" + pageIndex + " / " + tota);
			// 执行同步
			for (DeviceActivate deviceActivate : deviceList) {
				try {
					updateDeviceActive(deviceActivate);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
	private void updateDeviceActive(DeviceActivate deviceActivate) {
		String clientKey = deviceActivate.getClientKey();
		// 是否允许定位
		if (!deviceActivate.isAllowLocate()) {
			UserPosition position = userPositionService.uniqueByProp("clientKey", clientKey);
			if (position != null) {
				deviceActivate.setAllowLocate(true);
			}
		}
		Device device = deviceService.uniqueByProp("clientKey", clientKey);
		// 是否允许获取设备信息
		if (!deviceActivate.isAllowDevice()) {
			if (device != null && (!StringUtils.isEmpty(device.getImei()) || !StringUtils.isEmpty(device.getIdfa()))) {
				deviceActivate.setAllowDevice(true);
				deviceActivate.setImei(device.getImei());
				deviceActivate.setIdfa(device.getIdfa());
			}
		}
		
		if (StringUtils.isEmpty(deviceActivate.getImei())) {
			if (device != null && !StringUtils.isEmpty(device.getImei())) {
				deviceActivate.setAllowDevice(true);
				deviceActivate.setImei(device.getImei());
			}	
		}
		
		if (StringUtils.isEmpty(deviceActivate.getIdfa())) {
			if (device != null && !StringUtils.isEmpty(device.getImei())) {
				deviceActivate.setAllowDevice(true);
				deviceActivate.setIdfa(device.getIdfa());
			}	
		}
		
		// 获取手机型号
		if (StringUtils.isEmpty(deviceActivate.getOs())) {
			AccessLog accessLog = accessLogMongoService.uniqueByProps(new String[]{"clientKey"}, new String[]{clientKey});
			if (accessLog != null && !StringUtils.isEmpty(accessLog.getBrowser())) {
				String[] splitStr = accessLog.getBrowser().split("/");
				deviceActivate.setOs(splitStr[splitStr.length - 1]);
			}
		}
		
		// 操作换一批次数
		String title = "1.1、 推荐房源列表（游客）";
		String reqParams = "";
		for (int i = 20; i > 0; i--) {
			if (deviceActivate.getClient().equals("ios")) {
				reqParams = "{\"pageSize\":\"6\",\"pageNum\":\""+ i +"\"}";
				
			} else if (deviceActivate.getClient().equals("Android")) {
				reqParams = "{\"pageSize\":\"10\",\"pageNum\":\""+ i +"\"}";
			}
			AccessLog accessLog = accessLogMongoService.uniqueByProps(new String[]{"clientKey", "reqParams", "title"}, new String[]{clientKey, reqParams, title});
			if (accessLog != null) {
				deviceActivate.setNextfp(i);
				break;
			}
		}
		
		// 查看详情页页数
		deviceActivate.setViewDetail(houseViewHistoryService.countByCondition(new String[]{"clientKey"}, new String[]{clientKey}));
		// 是否注册
		UserAccount ua = userAccountService.uniqueByProp("clientKey", clientKey);
		if (!deviceActivate.isRegister()) {
			if (ua != null) {
				deviceActivate.setRegister(true);
			}
		}
		
		// 是否修改首付及比例
		if (!deviceActivate.isModifysf()) {
			AccessLog accessLog = accessLogMongoService.uniqueByProps(new String[]{"title", "clientKey"}, new String[]{"4.6.1、 修改首付预算", clientKey});
			if (accessLog != null) {
				deviceActivate.setModifysf(true);
			}
		}
		
		// 是否修改其他条件
		if (!deviceActivate.isModifyother()) {
			AccessLog accessLog = accessLogMongoService.uniqueByProps(new String[]{"title", "clientKey"}, new String[]{"4.6.7、 保存用户选房偏好", clientKey});
			if (accessLog != null) {
				deviceActivate.setModifyother(true);
			}
		}
		
		int countPrivateNumber = accessLogMongoService.countPrivateNumber(clientKey);
		deviceActivate.setUseAxb(countPrivateNumber);
		
		if (ua != null) {
			// 户籍 0 深户 1 非深户
			UserBuylimit userBuyLimit = userBuylimitService.findByUser(ua.getId(), "5cd52685fc3914336e2f1c5d");
			UserRequirement userReq = userRequirementService.getLast(ua.getId());
			StringBuffer appendStr = new StringBuffer("00");
			deviceActivate.setDownPayment(userReq.getDownPayment());
			if (userBuyLimit != null) {
				if (userBuyLimit.getChoose().equals("0")) {
					appendStr.append("S");	// 深户 S
					deviceActivate.setHuji(0); // 未填写
				} else {
					appendStr.append("F");  // 非深户 F
					deviceActivate.setHuji(1); // 未填写
				}
			} else {
				deviceActivate.setHuji(2); // 未填写
			}
			
			if (userReq != null) {
				// 限购 X， 不限购Y
				if (userReq.isBuylimit()) {
					appendStr.append("X");
					deviceActivate.setBuylimit(true);
				} else {
					appendStr.append("Y");
					deviceActivate.setBuylimit(false);
				}
				
				deviceActivate.setHujixgSel(appendStr.toString());
				
				if (!StringUtils.isEmpty(userReq.getSchoolName())) {
					deviceActivate.setSchoolSel(2); // 指定学校
				} else if (!StringUtils.isEmpty(userReq.getNeedSchool()) && userReq.getNeedSchool().equals("1")){
					deviceActivate.setSchoolSel(1); // 有需求未指定学校
				} else {
					deviceActivate.setSchoolSel(0);	// 无学位需求
				}
				if (!StringUtils.isEmpty(userReq.getOfficeAddress())) {
					deviceActivate.setOfficeSel(true);
				}
				if (!StringUtils.isEmpty(userReq.getXinOresf())) {
					deviceActivate.setXinoresf(Integer.valueOf(userReq.getXinOresf().split(",")[0]));
				}
				if (!StringUtils.isEmpty(userReq.getHuxing())) {
					deviceActivate.setHuxingSel(Integer.valueOf(userReq.getHuxing().split(",")[0]));
				}
				if (!StringUtils.isEmpty(userReq.getUseType())) {
					deviceActivate.setUseTypeSel(Integer.valueOf(userReq.getUseType().split(",")[0]));
				}
			}
		}
		super.save(deviceActivate);
	}
	
	
	public Page<DeviceActivate> findPageByCreateDate(PageRequest pageReq, String startDate, String endDate) {
		Query query = new Query();
		if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			query.addCriteria(Criteria.where("createDate").gt(startDate).lte(endDate));
		}
		query.with(pageReq);
		Long total = mongoTemplate.count(query, getEntityClass());
		List<DeviceActivate> list = mongoTemplate.find(query, getEntityClass());

		return new PageImpl<DeviceActivate>(list, pageReq, total);
	}

	@Override
	public int countViewByCondition(String[] params, Object[] values, int startCount, int endCount) {
		Query query = new Query();
		// where
		if (params != null && values != null) {
			for (int i = 0; i < params.length; i++) {
				query.addCriteria(Criteria.where(params[i]).is(values[i]));
			}
		}
		query.addCriteria(Criteria.where("viewDetail").gte(startCount).lte(endCount));
		Long count = mongoTemplate.count(query, getEntityClass());
		return count.intValue();
	}
	
	public int countNextfpByCondition(String[] params, Object[] values, int startCount, int endCount) {
		Query query = new Query();
		// where
		if (params != null && values != null) {
			for (int i = 0; i < params.length; i++) {
				query.addCriteria(Criteria.where(params[i]).is(values[i]));
			}
		}
		query.addCriteria(Criteria.where("nextfp").gte(startCount).lte(endCount));
		Long count = mongoTemplate.count(query, getEntityClass());
		return count.intValue();
	}
	
	@Override
	public int countShoufuByCondition(int startShoufu, int endShoufu) {
		Query query = new Query();
		query.addCriteria(Criteria.where("register").is(true));
		query.addCriteria(Criteria.where("downPayment").gte(startShoufu).lte(endShoufu));
		Long count = mongoTemplate.count(query, getEntityClass());
		return count.intValue();
	}

	@Override
	public void refreshUserAccount() {
        List<Object> registerList = new ArrayList<Object>();
        List<Object> deviceList = new ArrayList<Object>();
        String key_register = "chart_register_list";
        String key_device = "chart_device_list";
        registerList.add("注册用户");
        int curDay = userAccountService.distinctCount(DateUtil.format(DateUtil.now()));
        registerList.add(curDay);
        int yestDay = userAccountService.distinctCount(DateUtil.format(DateUtil.addDate(DateUtil.now(), -1)));
        registerList.add(yestDay);
        int thisweek = userAccountService.distinctCount(DateUtil.getWeekStart(), DateUtil.getWeekEnd());
        registerList.add(thisweek);
        int lastweek = userAccountService.distinctCount(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekStart()), -7)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekEnd()), -7)));
        registerList.add(lastweek);
        int thismonth = userAccountService.distinctCount(DateUtil.getMonthStart(), DateUtil.getMonthEnd());
        registerList.add(thismonth);
        int lastmonth = userAccountService.distinctCount(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthStart()), -30)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthEnd()), -30)));
        registerList.add(lastmonth);
        redisTemplate2.opsForList().rightPushAll(key_register, registerList);
        redisTemplate2.expire(key_register, 10, TimeUnit.MINUTES);

    	deviceList.add("活跃设备");
        curDay = deviceAccessService.countBy(DateUtil.format(DateUtil.now()));
        deviceList.add(curDay);
        yestDay = deviceAccessService.countBy(DateUtil.format(DateUtil.addDate(DateUtil.now(), -1)));
        deviceList.add(yestDay);
        thisweek = deviceAccessService.countBy(DateUtil.getWeekStart(), DateUtil.getWeekEnd());
        deviceList.add(thisweek);
        lastweek = deviceAccessService.countBy(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekStart()), -7)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekEnd()), -7)));
        deviceList.add(lastweek);
        thismonth = deviceAccessService.countBy(DateUtil.getMonthStart(), DateUtil.getMonthEnd());
        deviceList.add(thismonth);
        lastmonth = deviceAccessService.countBy(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthStart()), -30)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthEnd()), -30)));
        deviceList.add(lastmonth);
	    redisTemplate2.delete("chart_register_list");
	    redisTemplate2.delete("chart_device_list");
        redisTemplate2.opsForList().rightPushAll(key_device, deviceList);
        redisTemplate2.expire(key_device, 10, TimeUnit.MINUTES);
        
        refreshDevices();
	}
	
	private void refreshDevices() {
		List<Object> xAxisList = new ArrayList<Object>();
        List<Object> registerList = new ArrayList<Object>(); // 注册人数
        List<Object> deviceList = new ArrayList<Object>();   // 活跃设备
        List<Object> activateList = new ArrayList<Object>(); // 新增设备
        List<Object> advertActiveList = new ArrayList<Object>();   // 广告转化

        int count_register = 0;
        int count_device = 0;
        int count_active = 0;
        int count_advert_active = 0;
        String createDate = "";
        Date today = DateUtil.now();
        for (int i = 13; i >= 0; i--) {
        	createDate = DateUtil.format(DateUtil.addDate(today, i * (-1)));
        	xAxisList.add(createDate);
        	count_register = userAccountService.distinctCount(createDate);
        	registerList.add(count_register);
        	count_device = deviceAccessService.countBy(createDate);
        	deviceList.add(count_device);
        	count_active = distinctCount(createDate);
        	activateList.add(count_active);
        	count_advert_active = adConveRecordService.distinctCount(createDate, 1);
        	advertActiveList.add(count_advert_active);
        }
	    redisTemplate2.delete("redis_key_xAxisList");
	    redisTemplate2.delete("redis_key_registerList");
	    redisTemplate2.delete("redis_key_deviceList");
	    redisTemplate2.delete("redis_key_activateList");
	    redisTemplate2.delete("redis_key_advertActiveList");
        redisTemplate2.opsForList().rightPushAll("redis_key_xAxisList", xAxisList);
        redisTemplate2.opsForList().rightPushAll("redis_key_registerList", registerList);
        redisTemplate2.opsForList().rightPushAll("redis_key_deviceList", deviceList);
        redisTemplate2.opsForList().rightPushAll("redis_key_activateList", activateList);
        redisTemplate2.opsForList().rightPushAll("redis_key_advertActiveList", advertActiveList);
        redisTemplate2.expire("redis_key_xAxisList", 10, TimeUnit.MINUTES);
        redisTemplate2.expire("redis_key_registerList", 10, TimeUnit.MINUTES);
        redisTemplate2.expire("redis_key_deviceList", 10, TimeUnit.MINUTES);
        redisTemplate2.expire("redis_key_activateList", 10, TimeUnit.MINUTES);
        redisTemplate2.expire("redis_key_advertActiveList", 10, TimeUnit.MINUTES);
	}
	
	public void refreshPositions() {
		int pageSize = 50;
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		Sort sort = new Sort(orders);
		Page<UserAccount> page = userAccountService.findPage(new PageRequest(0, pageSize, sort));
		List<UserAccount> list = page.getContent();
		for (UserAccount userAccount : list) {
			List<UserPosition> positionsList = userPositionService.findByProps(new String[]{"userId", "clientKey"},
					new Object[]{null, userAccount.getClientKey()});
			if (positionsList == null || (positionsList != null && positionsList.size() == 0)) {
				continue;
			}
			for (UserPosition userPosition : positionsList) {
				userPosition.setUserId(userAccount.getId());
				userPositionService.save(userPosition);
			}
		}
		
	}
	
}
