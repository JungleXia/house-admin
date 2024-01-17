package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.UserPosition;

public interface UserPositionService extends BaseMongoService<UserPosition>{

	public UserPosition getLastByClientKey(String clientKey);
	
	public UserPosition getLastByUserId(String userId);
}
