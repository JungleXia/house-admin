package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.HouseCodeUnFind;
import com.zfh.app.mongo.service.esf.HouseCodeUnFindService;

@Service
public class HouseCodeUnFindServiceImpl extends BaseMongoServiceImpl<HouseCodeUnFind> implements HouseCodeUnFindService{

	@Override
	protected Class<HouseCodeUnFind> getEntityClass() {
		// TODO Auto-generated method stub
		return HouseCodeUnFind.class;
	}

	@Override
	public void saveUnFind(String houseCode, String houseId) {
		HouseCodeUnFind entity = uniqueByProps(new String[]{"houseCode", "houseId"}, new String[]{houseCode, houseId});
		if(entity == null) {
			entity = new HouseCodeUnFind();
			entity.setHouseCode(houseCode);
			entity.setHouseId(houseId);
			entity.setCreateTime(DateUtil.currentDateTime());
			super.save(entity);
		}
	}

}
