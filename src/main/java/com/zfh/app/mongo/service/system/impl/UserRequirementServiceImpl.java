package com.zfh.app.mongo.service.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.UserRequirement;
import com.zfh.app.mongo.service.system.UserRequirementService;

@Service
public class UserRequirementServiceImpl extends BaseMongoServiceImpl<UserRequirement>
		implements UserRequirementService {

	private static final Log logger = LogFactory.getLog(UserRequirementServiceImpl.class.getName());


	@Override
	protected Class<UserRequirement> getEntityClass() {
		// TODO Auto-generated method stub
		return UserRequirement.class;
	}

	@Override
	public UserRequirement getLast(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId).and("expired").is(false));
		List<UserRequirement> list = getMongoTemplate().find(query, getEntityClass());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int countShoufuByCondition(int startShoufu, int endShoufu) {
		Query query = new Query();
		query.addCriteria(Criteria.where("expired").is(false));
		query.addCriteria(Criteria.where("downPayment").gte(startShoufu).lte(endShoufu));
		Long count = mongoTemplate.count(query, getEntityClass());
		return count.intValue();
	}

	@Override
	public List<Object> getAverageAndMdian() {
		List<Object> result = new ArrayList<>();
		List<UserRequirement> list = findByProp("expired", false, new String[]{"downPayment"}, null);
		int total = 0;
		List<Integer> sortList = new ArrayList<>();
		for (UserRequirement userRequirement : list) {
			total += userRequirement.getDownPayment();
			sortList.add(userRequirement.getDownPayment());
		}
		Collections.sort(sortList);
		result.add(total / list.size());
		if (sortList.size() % 2 == 0) {
			result.add((sortList.get((sortList.size() / 2)) + sortList.get((sortList.size() / 2) + 1)) / 2);
		} else {
			result.add(sortList.get((sortList.size() / 2) + 1));
		}
		return result;
	}
}

