package com.zfh.app.mongo.service.esf;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.Community;

public interface CommunityService extends BaseMongoService<Community>{

	List<Community> findByName(PageRequest pageReq, String name);
	
	public Community findByMd5Url(String md5Url);
	
	public Community findByName(String community, String dataFrom);
	
	/**
	 * 任务：小区挂牌房价分析
	 * 
	 * 2019年5月23日下午4:15:13
	 */
	public void analysis();
	
}
