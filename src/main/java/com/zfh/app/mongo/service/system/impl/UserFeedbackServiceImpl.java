package com.zfh.app.mongo.service.system.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.UserFeedback;
import com.zfh.app.mongo.service.system.UserFeedbackService;

@Service
public class UserFeedbackServiceImpl extends BaseMongoServiceImpl<UserFeedback> implements UserFeedbackService{

	@Override
	protected Class<UserFeedback> getEntityClass() {
		// TODO Auto-generated method stub
		return UserFeedback.class;
	}

}
