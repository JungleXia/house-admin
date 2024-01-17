package com.zfh.app.mongo.service.system.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.UserBuylimit;
import com.zfh.app.mongo.service.system.UserBuylimitService;

@Service
public class UserBuylimitServiceImpl extends BaseMongoServiceImpl<UserBuylimit> implements UserBuylimitService {

	@Override
	protected Class<UserBuylimit> getEntityClass() {
		// TODO Auto-generated method stub
		return UserBuylimit.class;
	}

	@Override
	public UserBuylimit save(UserBuylimit entity, boolean isNew) {

		doExpired(entity.getUserId(), entity.getBuylimit().getId());

		entity.setExpired(false);
		entity.setCreateTime(DateUtil.currentDateTime());
		return super.save(entity);
	}

	private void doExpired(String userId, String sysbuylimitId) {
		List<UserBuylimit> list = findByProps(new String[] { "userId", "expired", "buylimit.$id" },
				new Object[] { userId, false, new ObjectId(sysbuylimitId) });
		for (UserBuylimit userBuylimit : list) {
			userBuylimit.setExpired(true);
			super.save(userBuylimit);
		}
	}

	@Override
	public UserBuylimit findByUser(String userId, String sysBuylimitId) {
		return uniqueByProps(new String[] { "userId", "expired", "buylimit.$id" },
				new Object[] { userId, false, new ObjectId(sysBuylimitId) });

	}


}
