package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.StandCommunity;

public interface StandCommunityService extends BaseMongoService<StandCommunity>{

	public StandCommunity getByStandId(String standId);
	
	public List<StandCommunity> searchByBaseIds(String[] baseIds);

	public void analyzePriceTask();
}
