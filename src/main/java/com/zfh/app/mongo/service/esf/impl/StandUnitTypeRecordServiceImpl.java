package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.StandUnitTypeRecord;
import com.zfh.app.mongo.service.esf.StandUnitTypeRecordService;

@Service
public class StandUnitTypeRecordServiceImpl extends BaseMongoServiceImpl<StandUnitTypeRecord> implements StandUnitTypeRecordService{

	@Override
	protected Class<StandUnitTypeRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return StandUnitTypeRecord.class;
	}

}
