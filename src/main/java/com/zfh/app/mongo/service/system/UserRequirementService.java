package com.zfh.app.mongo.service.system;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.UserRequirement;

public interface UserRequirementService extends BaseMongoService<UserRequirement> {

	public UserRequirement getLast(String userId);
	
	/**
	 * 首付分布 计算
	 * @param startShoufu
	 * @param endShoufu
	 * @return
	 * 2019年11月15日下午4:09:18
	 */
	public int countShoufuByCondition( int startShoufu, int endShoufu);
	
	public List<Object> getAverageAndMdian();
}
