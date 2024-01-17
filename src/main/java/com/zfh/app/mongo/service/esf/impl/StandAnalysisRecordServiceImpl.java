package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.StandAnalysisRecord;
import com.zfh.app.mongo.service.esf.StandAnalysisRecordService;

@Service
public class StandAnalysisRecordServiceImpl extends BaseMongoServiceImpl<StandAnalysisRecord> implements StandAnalysisRecordService{

	@Override
	protected Class<StandAnalysisRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return StandAnalysisRecord.class;
	}

}
