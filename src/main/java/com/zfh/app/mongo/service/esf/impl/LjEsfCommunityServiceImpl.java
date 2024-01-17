package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.LjEsfCommunity;
import com.zfh.app.mongo.service.esf.LjEsfCommunityService;
import com.zfh.app.mongo.service.esf.LjEsfHouseService;

@Service("ljEsfCommunityService")
public class LjEsfCommunityServiceImpl extends BaseMongoServiceImpl<LjEsfCommunity> implements LjEsfCommunityService{

	@Override
	protected Class<LjEsfCommunity> getEntityClass() {
		return LjEsfCommunity.class;
	}

}
