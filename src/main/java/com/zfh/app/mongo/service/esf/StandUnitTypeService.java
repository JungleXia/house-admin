package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.StandUnitType;

public interface StandUnitTypeService extends BaseMongoService<StandUnitType>{
	
	public StandUnitType findByStandIdAndRoom(String standId, int room);

}
