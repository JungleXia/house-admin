package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.City;

public interface CityService  extends BaseMongoService<City>{

	public City findByDomain(String domain);
	
	/**
	 * 刷新城市绑定相关数据
	 * @param city
	 * 2020年9月23日下午6:32:01
	 */
	public void refreshData(City city);
	
	public List<String> findCityList();
}
