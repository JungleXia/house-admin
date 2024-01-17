package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.HouseOffline;
import com.zfh.app.mongo.service.esf.HouseOfflineService;

@Service
public class HouseOfflineServiceImpl extends BaseMongoServiceImpl<HouseOffline> implements HouseOfflineService{

	@Override
	protected Class<HouseOffline> getEntityClass() {
		// TODO Auto-generated method stub
		return HouseOffline.class;
	}

}
