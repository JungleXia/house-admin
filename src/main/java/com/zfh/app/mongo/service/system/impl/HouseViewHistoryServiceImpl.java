package com.zfh.app.mongo.service.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.system.HouseViewHistory;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.system.HouseViewHistoryService;

@Service
public class HouseViewHistoryServiceImpl extends BaseMongoServiceImpl<HouseViewHistory>
		implements HouseViewHistoryService {

	@Autowired
	private HouseService houseService;

	@Override
	protected Class<HouseViewHistory> getEntityClass() {
		// TODO Auto-generated method stub
		return HouseViewHistory.class;
	}

	@Override
	public List<String> distinctDate(String userId) {
		List<String> dateList = new ArrayList<String>();
		Query query = new Query(Criteria.where("userId").is(userId));
		String reduce = "function(doc, aggr){" +
		"            aggr.count += 1;" +
		"        }";

		DBObject result = getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).group(
		new BasicDBObject("createDate",1),
		query.getQueryObject(),
		new BasicDBObject("count", 1),
		reduce);

		Map map = result.toMap();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			BasicDBObject obj = (BasicDBObject) map.get(key);
			dateList.add(obj.getString("createDate"));
		}
		Collections.sort(dateList, new Comparator<String>() {
            @Override
            public int compare(String b1, String b2) {
            	return -b1.compareTo(b2);
            }
        });

		return dateList;
	}

	@Override
	public HouseViewHistory findByClientKeyAndHouseId(String clientKey, String houseId) {
		return uniqueByProps(new String[] { "clientKey", "houseId" }, new String[] {clientKey, houseId });
	}

	@Override
	public void updateCollect(String userId, String houseId, boolean collect) {
		HouseViewHistory houseViewHistory = uniqueByProps(new String[]{"userId", "houseId"}, new String[]{userId, houseId});
		if (houseViewHistory != null) {
			houseViewHistory.setCollect(collect);
			super.save(houseViewHistory);
		}
		
	}

	private String convertDate(String createDate) {
		String today = DateUtil.format(DateUtil.now(), DateUtil.DEFAULT_FORMATE);
		String yesterday =  DateUtil.format(DateUtil.addDate(DateUtil.now(), -1), DateUtil.DEFAULT_FORMATE);
		if (createDate.equals(today)) {
			return "今天";
		}
		else if (createDate.equals(yesterday)) {
			return "昨天";
		}
		return createDate.replace("-", ".");
		
	}
}
