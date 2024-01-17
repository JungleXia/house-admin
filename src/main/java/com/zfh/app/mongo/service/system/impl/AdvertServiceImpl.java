package com.zfh.app.mongo.service.system.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.Advert;
import com.zfh.app.mongo.service.system.AdvertService;

@Service
public class AdvertServiceImpl extends BaseMongoServiceImpl<Advert> implements AdvertService{

	@Override
	protected Class<Advert> getEntityClass() {
		// TODO Auto-generated method stub
		return Advert.class;
	}

}
