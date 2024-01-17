package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.HouseCode;

public interface HouseCodeMongoService extends BaseMongoService<HouseCode> {
	
	public HouseCode getByRefId(String refId);
	
	public HouseCode getByHouseCode(String houseCode);
}
