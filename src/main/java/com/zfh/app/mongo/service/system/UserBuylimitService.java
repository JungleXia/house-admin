package com.zfh.app.mongo.service.system;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.UserBuylimit;

public interface UserBuylimitService extends BaseMongoService<UserBuylimit> {
	
	public UserBuylimit save(UserBuylimit entity, boolean isNew);
	
	public UserBuylimit findByUser(String userId, String sysBuylimitId);
	
}
