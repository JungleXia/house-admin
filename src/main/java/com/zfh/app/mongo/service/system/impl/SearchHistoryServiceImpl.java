package com.zfh.app.mongo.service.system.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.SearchHistory;
import com.zfh.app.mongo.service.system.SearchHistoryService;

@Service
public class SearchHistoryServiceImpl extends BaseMongoServiceImpl<SearchHistory> implements SearchHistoryService {

	@Override
	protected Class<SearchHistory> getEntityClass() {
		// TODO Auto-generated method stub
		return SearchHistory.class;
	}

}
