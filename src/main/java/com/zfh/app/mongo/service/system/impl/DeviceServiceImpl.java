package com.zfh.app.mongo.service.system.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.Device;
import com.zfh.app.mongo.service.system.DeviceService;

@Service
public class DeviceServiceImpl extends BaseMongoServiceImpl<Device> implements DeviceService{

	@Override
	protected Class<Device> getEntityClass() {
		// TODO Auto-generated method stub
		return Device.class;
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
}
