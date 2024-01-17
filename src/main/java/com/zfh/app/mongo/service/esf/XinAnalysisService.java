package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.XinAnalysis;

public interface XinAnalysisService extends BaseMongoService<XinAnalysis>{

	public XinAnalysis findByHouseId(String houseId);
}
