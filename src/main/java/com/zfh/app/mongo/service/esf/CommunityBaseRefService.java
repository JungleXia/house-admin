package com.zfh.app.mongo.service.esf;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.CommunityBaseRef;

public interface CommunityBaseRefService extends BaseMongoService<CommunityBaseRef>{
	
	public CommunityBaseRef autoBind(String community, String dataFrom, String baseId, String baseName);
}
