package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.CollectHouse;

public interface CollectHouseService extends BaseMongoService<CollectHouse>{

	public void expiredAll();
}
