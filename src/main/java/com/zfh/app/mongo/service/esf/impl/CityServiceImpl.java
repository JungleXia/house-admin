package com.zfh.app.mongo.service.esf.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.City;
import com.zfh.app.mongo.entity.esf.District;
import com.zfh.app.mongo.entity.esf.LjEsfStatistic;
import com.zfh.app.mongo.entity.esf.StandardRegion;
import com.zfh.app.mongo.service.esf.CityService;
import com.zfh.app.mongo.service.esf.DistrictService;
import com.zfh.app.mongo.service.esf.LjEsfStatisticService;
import com.zfh.app.mongo.service.esf.StandardRegionService;

@Service
public class CityServiceImpl extends BaseMongoServiceImpl<City> implements CityService{

	@Autowired
	DistrictService districtService;
	
	@Autowired
	StandardRegionService standardRegionService;
	
	@Autowired
	LjEsfStatisticService ljEsfStatisticService;
	
	@Override
	protected Class<City> getEntityClass() {
		// TODO Auto-generated method stub
		return City.class;
	}

	@Override
	public City findByDomain(String domain) {
		if (StringUtils.isNotEmpty(domain)) {
			List<City> cityList = findByProp("domain", domain);
			if (cityList != null && cityList.size() > 0) {
				return cityList.get(0);
			}
		}
		return null;
	}

	@Override
	public void refreshData(City city) {
		// 更新区域
		List<District> districtList = districtService.findByProp("city", city.getCity());
		for (District district : districtList) {
			district.setCityCode(city.getCityCode());
			district.setProvince(city.getProvince());
			district.setDomain(city.getDomain());
			districtService.save(district);
		}
		
		// 更新子区域
		List<StandardRegion> standardRegionList = standardRegionService.findByProp("city", city.getCity());
		for (StandardRegion standardRegion : standardRegionList) {
			standardRegion.setCityCode(city.getCityCode());
			standardRegion.setCityInitials(city.getCityInitials());
			standardRegion.setCityPinyin(city.getCityPinyin());
			standardRegion.setPrivinceInitials(city.getProvinceInitials());
			standardRegion.setPrivincePinyin(city.getProvincePinyin());
			standardRegion.setProvince(city.getProvince());
			standardRegion.setProvinceCode(city.getProvinceCode());
			standardRegionService.save(standardRegion);
		}
	}

    @Cacheable(value = "cityData",key = "'lj_cityList'",unless = "#result == null or #result.size() == 0")
	@Override
	public List<String> findCityList() {
    	List<LjEsfStatistic> list = ljEsfStatisticService.findByProp("type", 1, "city_key 1");
    	List<String> result = new ArrayList<>();
    	for (LjEsfStatistic ljEsfStatistic : list) {
    		result.add(ljEsfStatistic.getCity());
		}
    	return result;
	}

}
