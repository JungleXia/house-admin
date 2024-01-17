package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.LjEsfHouse;
import com.zfh.app.mongo.service.esf.LjEsfHouseService;

@Service("ljEsfHouseService")
public class LjEsfHouseServiceImpl extends BaseMongoServiceImpl<LjEsfHouse> implements LjEsfHouseService{

	@Override
	protected Class<LjEsfHouse> getEntityClass() {
		return LjEsfHouse.class;
	}

}
