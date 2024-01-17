package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.ErshoufangAnalysis;
import com.zfh.app.mongo.service.esf.ErshoufangAnalysisService;

@Service
public class ErshoufangAnalysisServiceImpl extends BaseMongoServiceImpl<ErshoufangAnalysis> implements ErshoufangAnalysisService{

	@Override
	protected Class<ErshoufangAnalysis> getEntityClass() {
		// TODO Auto-generated method stub
		return ErshoufangAnalysis.class;
	}

	@Override
	public ErshoufangAnalysis getByHouseId(String houseId) {
		// TODO Auto-generated method stub
		return uniqueByProp("houseId", houseId);
	}

}
