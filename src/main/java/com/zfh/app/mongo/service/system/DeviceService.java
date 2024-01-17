package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.Device;

public interface DeviceService extends BaseMongoService<Device>{

	public int distinctCount(String createDate);

}
