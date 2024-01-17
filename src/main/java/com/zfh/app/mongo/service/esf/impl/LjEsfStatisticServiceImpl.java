package com.zfh.app.mongo.service.esf.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mysiteforme.admin.entity.VO.ZtreeVO;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.LjEsfStatistic;
import com.zfh.app.mongo.model.CityModel;
import com.zfh.app.mongo.service.esf.LjEsfStatisticService;

@Service("ljEsfStatisticService")
public class LjEsfStatisticServiceImpl extends BaseMongoServiceImpl<LjEsfStatistic> implements LjEsfStatisticService{

	@Override
	protected Class<LjEsfStatistic> getEntityClass() {
		return LjEsfStatistic.class;
	}

    @Cacheable(value = "statistic",key = "'city_list'",unless = "#result == null or #result.size() == 0")
	@Override
	public List<CityModel> findCityList() {
    	//city
    	List<LjEsfStatistic> list = findByProp("type", 1, "city_key 1");
    	List<CityModel> result = new ArrayList<>();
    	for (LjEsfStatistic city : list) {
    		CityModel model = new CityModel();
    		model.setInitials(String.valueOf(PinyinHelper.getShortPinyin(city.getCity()).toUpperCase().charAt(0)));
    		model.setExpaned(false);
    		model.setName(city.getCity());
    		model.setNumbers(city.getNumbers());
    		model.setProvince(city.getProvince());
    		model.setCreateDay(city.getCreateDay());
    		model.setDiff(city.getDiff());
    		model.setPrenums(city.getPrenums());
    		
    		// district
    		List<CityModel> subList = new ArrayList<>();
    		List<LjEsfStatistic> districtlist = findByProps(new String[]{"city", "type" }, new Object[]{city.getCity(), 2});
    		for (LjEsfStatistic district : districtlist) {
        		CityModel districtModel = new CityModel();
        		districtModel.setInitials(String.valueOf(PinyinHelper.getShortPinyin(district.getDistrict()).toUpperCase().charAt(0)));
        		districtModel.setExpaned(false);
        		districtModel.setName(district.getDistrict());
        		districtModel.setNumbers(district.getNumbers());
        		districtModel.setCreateDay(district.getCreateDay());
        		districtModel.setDiff(district.getDiff());
        		districtModel.setPrenums(district.getPrenums());
        		
        		// block
        		List<CityModel> blockList = new ArrayList<>();
        		List<LjEsfStatistic> blockStatisticList = findByProps(new String[]{"city", "district", "type" }, new Object[]{district.getCity(), district.getDistrict(), 3});
        		for (LjEsfStatistic block : blockStatisticList) {
        			CityModel blockModel = new CityModel();
        			blockModel.setInitials(String.valueOf(PinyinHelper.getShortPinyin(block.getBlock()).toUpperCase().charAt(0)));
        			blockModel.setExpaned(false);
        			blockModel.setName(block.getBlock());
        			blockModel.setNumbers(block.getNumbers());
        			blockModel.setCreateDay(block.getCreateDay());
        			blockModel.setDiff(block.getDiff());
        			blockModel.setPrenums(block.getPrenums());
        			
        			blockList.add(blockModel);
				}

               	// 按首字母排序
        		Collections.sort(blockList);
        		districtModel.setSubList(blockList);
        		
        		subList.add(districtModel);
			}
    		
    		model.setSubList(subList);
    		
        	// 按首字母排序
    		Collections.sort(subList);
    		
    		result.add(model);
		}
    	
    	// 按首字母排序
    	Collections.sort(result);
    	return result;
	}

    @Cacheable(value = "statistic",key = "'province_list'",unless = "#result == null or #result.size() == 0")
	@Override
	public List<CityModel> findProvinceList() {
    	List<CityModel> result = new ArrayList<>();
    	List<CityModel> cityList = findCityList();
    	Set<String> provinceSet = new HashSet<>();
    	for (CityModel cityModel : cityList) {
    		provinceSet.add(cityModel.getProvince());
		}
    	for (String province : provinceSet) {
    		CityModel model = new CityModel();
    		model.setInitials(String.valueOf(PinyinHelper.getShortPinyin(province).toUpperCase()));
    		model.setExpaned(false);
    		model.setName(province);
    		model.setProvince(province);
    		List<CityModel> subList = new ArrayList<>();
			for (CityModel cityModel : cityList) {
				if (StringUtils.equals(province, cityModel.getProvince())) {
					subList.add(cityModel);
				}
			}
			model.setSubList(subList);
			
			result.add(model);
		}
    	// 按首字母排序
    	result.sort(new Comparator<CityModel>() {
    		@Override
    		public int compare(CityModel model1, CityModel model2) {
    			char a = model1.getInitials().toCharArray()[0];
    			char b = model2.getInitials().toCharArray()[0];
    			return Integer.valueOf(a + 0).compareTo(b + 0);
    		}
    		
		});
    	return result;
	}

    @Cacheable(value = "statistic",key = "'province_ztree'",unless = "#result == null or #result.size() == 0")
	@Override
	public List<ZtreeVO> selectZtreeData() {
    	List<ZtreeVO> result = new ArrayList<>();
    	List<CityModel> provinceList = findProvinceList();
    	result = changeZtree(1L, provinceList);
    	return result;
	}
    
    
    public List<ZtreeVO> changeZtree(Long parentId, List<CityModel> modelList) {
    	List<ZtreeVO> result = new ArrayList<>();
    	if (modelList != null && modelList.size() > 0) {
    		int idStart = Integer.valueOf(String.valueOf(parentId) + "01");
    		for (int i = 0; i < modelList.size(); i++) {
        		List<CityModel> cityList = modelList.get(i).getSubList();
        		ZtreeVO province = new ZtreeVO();
        		province.setId(Long.valueOf(idStart + i));

        		province.setName(modelList.get(i).getName());
        		province.setOpen(false);
        		province.setPid(parentId);
        		if (cityList != null && cityList.size() > 0) {
            		province.setIsParent(true);
              		province.setChildren(changeZtree(province.getId(), cityList));
        		} else {
            		province.setIsParent(false);
        		}
          		result.add(province);
			}
    	}
    	return result;
    }
}
