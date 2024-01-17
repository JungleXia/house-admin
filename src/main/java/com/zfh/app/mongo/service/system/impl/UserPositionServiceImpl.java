package com.zfh.app.mongo.service.system.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.UserPosition;
import com.zfh.app.mongo.service.system.UserPositionService;

@Service
public class UserPositionServiceImpl extends BaseMongoServiceImpl<UserPosition> implements UserPositionService{

	@Override
	protected Class<UserPosition> getEntityClass() {
		// TODO Auto-generated method stub
		return UserPosition.class;
	}

	@Override
	public UserPosition getLastByClientKey(String clientKey) {
		// TODO Auto-generated method stub
		Page<UserPosition> page = findPage(new PageRequest(0, 10), new String[]{"clientKey"}, new String[]{clientKey}, "_id desc");
		if (page.getContent() != null && page.getContent().size() > 0) {
			return page.getContent().get(0);
		}
		return null;
	}

	@Override
	public UserPosition getLastByUserId(String userId) {
		Page<UserPosition> page = findPage(new PageRequest(0, 10), new String[]{"userId"}, new String[]{userId}, "_id desc");
		if (page.getContent() != null && page.getContent().size() > 0) {
			return page.getContent().get(0);
		}
		return null;
	}

}
