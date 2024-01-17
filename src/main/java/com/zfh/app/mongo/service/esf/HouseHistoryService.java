package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.HouseHistory;

public interface HouseHistoryService extends BaseMongoService<HouseHistory> {

	public HouseHistory findByHouseIdAndVersionDate(String houseId, String versionDate);
}
