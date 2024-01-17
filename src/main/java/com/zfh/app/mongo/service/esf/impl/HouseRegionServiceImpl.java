package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.HouseRegion;
import com.zfh.app.mongo.service.esf.HouseRegionService;

@Service("houseRegionService")
public class HouseRegionServiceImpl extends BaseMongoServiceImpl<HouseRegion> implements HouseRegionService{

	@Override
	protected Class<HouseRegion> getEntityClass() {
		// TODO Auto-generated method stub
		return HouseRegion.class;
	}

	
}
