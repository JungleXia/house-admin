package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.District;
import com.zfh.app.mongo.service.esf.DistrictService;

@Service
public class DistrictServiceImpl extends BaseMongoServiceImpl<District> implements DistrictService{

	@Override
	protected Class<District> getEntityClass() {
		// TODO Auto-generated method stub
		return District.class;
	}

}
