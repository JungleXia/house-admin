package com.zfh.app.mongo.service.esf.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.esf.CommunityBaseRef;
import com.zfh.app.mongo.service.esf.CommunityBaseRefService;
import com.zfh.app.mongo.service.esf.CommunityService;

@Service
public class CommunityBaseRefServiceImpl extends BaseMongoServiceImpl<CommunityBaseRef> implements CommunityBaseRefService{

	private static final Log logger = LogFactory.getLog(CommunityBaseRefServiceImpl.class.getName());

	@Autowired
	private CommunityService communityService;
	
	@Override
	protected Class<CommunityBaseRef> getEntityClass() {
		// TODO Auto-generated method stub
		return CommunityBaseRef.class;
	}

	@Override
	public CommunityBaseRef autoBind(String community, String dataFrom, String baseId, String baseName) {
		Community entity = communityService.findByName(community, dataFrom);
		if (entity != null) {
			CommunityBaseRef baseRef = new CommunityBaseRef();
			baseRef.setBaseId(baseId);
			baseRef.setBaseName(baseName);
			baseRef.setCommunity(community);
			baseRef.setCommunityId(entity.getId());
			baseRef.setDataFrom(dataFrom);
			baseRef.setRef(entity);
			return super.save(baseRef);
		}
		return null;
	}
}
