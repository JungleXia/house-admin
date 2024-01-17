package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.CommunityBaseAlias;
import com.zfh.app.mongo.entity.esf.CommunityBaseRef;

public interface CommunityBaseAliasService extends BaseMongoService<CommunityBaseAlias>{

	public List<CommunityBaseAlias> search(String keyword, int pageSize);

}
