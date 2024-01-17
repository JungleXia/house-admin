package com.zfh.app.mongo.service.esf.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.service.esf.CommunityService;

@Service("communityMongoService")
public class CommunityServiceImpl extends BaseMongoServiceImpl<Community> implements CommunityService{

	@Override
	protected Class<Community> getEntityClass() {
		// TODO Auto-generated method stub
		return Community.class;
	}

	@Override
	public List<Community> findByName(PageRequest pageReq, String name) {
		return super.findPage(pageReq, "community", name).getContent();
	}

	@Override
	public Community findByMd5Url(String md5Url) {
		return super.uniqueByProp("md5Url", md5Url);
	}
	
	@Override
	public Community findByName(String community, String dataFrom) {
		return super.uniqueByProps(new String []{"community", "dataFrom"}, new String[]{"community", "dataFrom"});
	}
	
	@Override
	public void analysis() {
		
		
	}
}
