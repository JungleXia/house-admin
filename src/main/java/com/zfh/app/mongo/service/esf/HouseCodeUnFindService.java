package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.HouseCodeUnFind;

public interface HouseCodeUnFindService extends BaseMongoService<HouseCodeUnFind>{

	public void saveUnFind(String houseCode, String houseId);
}
