package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.ErshoufangAnalysis;

public interface ErshoufangAnalysisService extends BaseMongoService<ErshoufangAnalysis>{

	public ErshoufangAnalysis getByHouseId(String houseId);
}
