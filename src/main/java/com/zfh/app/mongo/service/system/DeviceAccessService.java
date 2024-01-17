package com.zfh.app.mongo.service.system;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.DeviceAccess;

public interface DeviceAccessService extends BaseMongoService<DeviceAccess>{

	public DeviceAccess findOne(String clientKey, String createDate);
	
	public int countBy(String createDate);
	
	public int countBy(String startDate, String endDate);
	
	public List<DeviceAccess> findListBy(String startDate, String endDate);
	
	public void createDeviceAccess(String createDate);
}
