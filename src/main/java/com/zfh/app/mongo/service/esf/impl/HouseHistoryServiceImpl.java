package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.HouseHistory;
import com.zfh.app.mongo.service.esf.HouseHistoryService;

@Service
public class HouseHistoryServiceImpl extends BaseMongoServiceImpl<HouseHistory> implements HouseHistoryService{

	@Override
	protected Class<HouseHistory> getEntityClass() {
		// TODO Auto-generated method stub
		return HouseHistory.class;
	}

	@Override
	public HouseHistory findByHouseIdAndVersionDate(String houseId, String versionDate) {
		return uniqueByProps(new String[]{"houseId", "versionDate"}, new String[]{houseId, versionDate});
	}

}
