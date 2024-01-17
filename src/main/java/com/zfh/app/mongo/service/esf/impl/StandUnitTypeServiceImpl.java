package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.StandUnitType;
import com.zfh.app.mongo.service.esf.StandUnitTypeService;

@Service
public class StandUnitTypeServiceImpl extends BaseMongoServiceImpl<StandUnitType> implements StandUnitTypeService {

	@Override
	protected Class<StandUnitType> getEntityClass() {
		// TODO Auto-generated method stub
		return StandUnitType.class;
	}

	@Override
	public StandUnitType findByStandIdAndRoom(String standId, int room) {
		return uniqueByProps(new String[]{"standId", "room"}, new Object[]{standId, room});
	}

}
