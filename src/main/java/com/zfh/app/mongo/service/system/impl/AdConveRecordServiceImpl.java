package com.zfh.app.mongo.service.system.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.AdConveRecord;
import com.zfh.app.mongo.service.system.AdConveRecordService;
import com.zfh.app.mongo.service.system.DeviceActivateService;
import com.zfh.app.mongo.service.system.DeviceService;

@Service
public class AdConveRecordServiceImpl extends BaseMongoServiceImpl<AdConveRecord> implements AdConveRecordService{

	private static final Log logger = LogFactory.getLog(AdConveRecordServiceImpl.class);
	
	@Override
	protected Class<AdConveRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return AdConveRecord.class;
	}

	@Override
	public String getByMac1(String md5mac) {
		AdConveRecord record = uniqueByProps(new String[]{"mac1", "status"}, new Object[]{md5mac, 1});
		if (record != null) {
			return getSite(record.getCsite());
		}
		return null;
	}
	
	private String getSite(String code) {
		if (code == null) {
			return null;
		}
		if (code.equals("1")) {
			return "今日头条";
		} else if (code.equals("10001")) {
			return "西瓜视频";
		} else if (code.equals("30001")) {
			return "火山小视频";
		} else if (code.equals("40001")) {
			return "抖音";
		} else {
			return null;
		}
	}

	@Override
	public int distinctCount(String createDate, int status) {
		Set<String> dateList = new HashSet<String>();
		Query query = new Query(Criteria.where("updateDate").is(createDate).and("status").is(status));
		String reduce = "function(doc, aggr){" +
		"            aggr.count += 1;" +
		"        }";

		DBObject result = getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).group(
		new BasicDBObject("mac1",1),
		query.getQueryObject(),
		new BasicDBObject("count", 1),
		reduce);

		Map map = result.toMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			BasicDBObject obj = (BasicDBObject) map.get(key);
			dateList.add(obj.getString("mac1"));
		}
		return dateList.size();
	}

	@Override
	public int distinctCount(String createDate) {
		Set<String> dateList = new HashSet<String>();
		Query query = new Query(Criteria.where("updateDate").is(createDate));
		String reduce = "function(doc, aggr){" +
		"            aggr.count += 1;" +
		"        }";

		DBObject result = getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).group(
		new BasicDBObject("mac1",1),
		query.getQueryObject(),
		new BasicDBObject("count", 1),
		reduce);

		Map map = result.toMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			BasicDBObject obj = (BasicDBObject) map.get(key);
			dateList.add(obj.getString("mac1"));
		}
		return dateList.size();
	}
}
