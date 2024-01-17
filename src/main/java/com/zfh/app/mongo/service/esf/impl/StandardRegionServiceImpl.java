package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.StandardRegion;
import com.zfh.app.mongo.service.esf.StandardRegionService;

@Service("standardRegionService")
public class StandardRegionServiceImpl extends BaseMongoServiceImpl<StandardRegion> implements StandardRegionService{

	@Override
	protected Class<StandardRegion> getEntityClass() {
		// TODO Auto-generated method stub
		return StandardRegion.class;
	}

	
}
