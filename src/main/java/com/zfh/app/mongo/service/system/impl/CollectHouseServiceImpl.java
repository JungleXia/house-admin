package com.zfh.app.mongo.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.system.CollectHouse;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.system.CollectHouseService;

@Service
public class CollectHouseServiceImpl extends BaseMongoServiceImpl<CollectHouse> implements CollectHouseService{

	@Autowired
	private HouseService houseService;
	
	@Override
	protected Class<CollectHouse> getEntityClass() {
		// TODO Auto-generated method stub
		return CollectHouse.class;
	}

	@Override
	public void expiredAll() {
		int pageSize = 500;
		Page<CollectHouse> page = findPage(new PageRequest(0, pageSize), "online", true);
		int totalPage = page.getTotalPages();
		int pageNum = 0;
		while (pageNum < totalPage) {
			page = findPage(new PageRequest(pageNum, pageSize), "online", true);
			for (CollectHouse collectHouse : page.getContent()) {
				House house = houseService.getById(collectHouse.getHouseId());
				if (house != null && !house.isOnline()) {
					collectHouse.setOnline(house.isOnline());
					super.save(collectHouse);
				}
			}	
			pageNum++;
		}
	}

}
