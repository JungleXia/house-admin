package com.zfh.app.mongo.service.system;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.HouseViewHistory;

public interface HouseViewHistoryService extends BaseMongoService<HouseViewHistory>{
	
	public List<String> distinctDate(String userId);
	
	public HouseViewHistory findByClientKeyAndHouseId(String clientKey, String houseId);
		
	public void updateCollect(String userId, String houseId, boolean collect);
}
