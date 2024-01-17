package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.AccessLog;

public interface AccessLogMongoService extends BaseMongoService<AccessLog>{

	public int countPrivateNumber(String clientKey);
}
