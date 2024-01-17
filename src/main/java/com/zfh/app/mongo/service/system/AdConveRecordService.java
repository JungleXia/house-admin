package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.AdConveRecord;

public interface AdConveRecordService extends BaseMongoService<AdConveRecord>{

	public String getByMac1(String md5mac);
	
	public int distinctCount(String createDate, int status);
	
	public int distinctCount(String createDate);
}
