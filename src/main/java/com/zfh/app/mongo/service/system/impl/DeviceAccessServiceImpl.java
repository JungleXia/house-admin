package com.zfh.app.mongo.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.AccessLog;
import com.zfh.app.mongo.entity.system.DeviceAccess;
import com.zfh.app.mongo.service.system.AccessLogMongoService;
import com.zfh.app.mongo.service.system.DeviceAccessService;

@Service
public class DeviceAccessServiceImpl extends BaseMongoServiceImpl<DeviceAccess> implements DeviceAccessService{

	@Autowired
	private AccessLogMongoService accessLogMongoService;
	
	@Override
	protected Class<DeviceAccess> getEntityClass() {
		// TODO Auto-generated method stub
		return DeviceAccess.class;
	}

	@Override
	public DeviceAccess findOne(String clientKey, String createDate) {
		// TODO Auto-generated method stub
		return uniqueByProps(new String[]{"clientKey", "createDate"},  new String[]{clientKey, createDate});
	}

	@Override
	public int countBy(String createDate) {
		Query query = new Query(Criteria.where("createDate").is(createDate));
		Long count = getMongoTemplate().count(query, getEntityClass());
		return count.intValue();
	}

	@Override
	public int countBy(String startDate, String endDate) {
		Query query = new Query(Criteria.where("createDate").gte(startDate).lte(endDate));
		Long count = getMongoTemplate().count(query, getEntityClass());
		return count.intValue();
	}
	
	@Override
	public List<DeviceAccess> findListBy(String startDate, String endDate) {
		Query query = new Query(Criteria.where("createDate").gte(startDate).lte(endDate));
		return getMongoTemplate().find(query, getEntityClass());
	}
	
	public void saveEntity(String clientIp, String client, String clientKey, String createDate) {
		DeviceAccess access = findOne(clientKey, createDate);
		if (access == null) {
			access = new DeviceAccess();
			access.setClient(client);
			access.setClientIp(clientIp);
			access.setClientKey(clientKey);
			access.setCreateDate(createDate);
			super.save(access);
		}
	}
	
	@Override
	public void createDeviceAccess(String createDate) {
		// list 太长了 会报错  "java.lang.OutOfMemoryError: Java heap space", 所以要分页做
		int pageSize = 1000;
		PageRequest pageReq = new PageRequest(0, pageSize);
		Page<AccessLog> singlepage = accessLogMongoService.findPage(pageReq, new String[]{"createDate", "checkStatus"}, new Object[]{createDate, null}, "_id desc");
		int totalPage = singlepage.getTotalPages();
		if (totalPage > 50) {
			totalPage = 50;
		}
		int index = 0;
		Map<String, Object> map = new HashMap<String, Object>(pageSize);
		while (index < totalPage) {
			pageReq = new PageRequest(index, pageSize);
			singlepage = accessLogMongoService.findPage(pageReq, new String[]{"createDate", "checkStatus"}, new Object[]{createDate, null}, "_id desc");
			
			List<AccessLog> accessLogList = singlepage.getContent();
			for (AccessLog accessLog : accessLogList) {
				map.put(accessLog.getClientKey(), accessLog);
				accessLog.setCheckStatus(true);
				accessLogMongoService.save(accessLog);
			}
			for (String key : map.keySet()) {
				AccessLog accessLog = (AccessLog) map.get(key);
				saveEntity(accessLog.getClientIp(), accessLog.getClient(), accessLog.getClientKey(), createDate);
			}
			index++;
		}

	}

}
