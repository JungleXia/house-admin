package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.StandAnalysis;
import com.zfh.app.mongo.entity.esf.StandCommunity;

public interface StandAnalysisService extends BaseMongoService<StandAnalysis> {

	public void mulitDetection();
	
	public void mulitAnalysis();
	
	public void doAnalysis(List<StandCommunity> list);
	
	public int getHouseCount(String standId);
}
