package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.mysiteforme.admin.entity.VO.ZtreeVO;
import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.LjEsfStatistic;
import com.zfh.app.mongo.model.CityModel;

public interface LjEsfStatisticService extends BaseMongoService<LjEsfStatistic>{

	public List<CityModel> findCityList();
	
	public List<CityModel> findProvinceList();
	
	public List<ZtreeVO> selectZtreeData();
}
