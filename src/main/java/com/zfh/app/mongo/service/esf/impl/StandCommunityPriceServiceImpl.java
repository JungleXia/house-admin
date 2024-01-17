package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.StandCommunityPrice;
import com.zfh.app.mongo.service.esf.StandCommunityPriceService;

@Service
public class StandCommunityPriceServiceImpl extends BaseMongoServiceImpl<StandCommunityPrice> implements StandCommunityPriceService{

	@Override
	protected Class<StandCommunityPrice> getEntityClass() {
		// TODO Auto-generated method stub
		return StandCommunityPrice.class;
	}

}
