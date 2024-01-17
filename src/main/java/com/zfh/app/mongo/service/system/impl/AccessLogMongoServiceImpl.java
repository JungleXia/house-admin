package com.zfh.app.mongo.service.system.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.AccessLog;
import com.zfh.app.mongo.service.system.AccessLogMongoService;

@Service("accessLogMongoService")
public class AccessLogMongoServiceImpl extends BaseMongoServiceImpl<AccessLog> implements AccessLogMongoService{

	@Override
	protected Class<AccessLog> getEntityClass() {
		// TODO Auto-generated method stub
		return AccessLog.class;
	}


	@Override
	public int countPrivateNumber(String clientKey) {
		Query query = new Query(Criteria.where("clientKey").is(clientKey).and("title").is("1.5.4、 加密电话").and("response").regex("\"code\":0"));
		return getMongoTemplate().find(query, getEntityClass()).size();
	}

}
