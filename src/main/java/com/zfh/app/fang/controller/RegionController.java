package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.mongo.entity.esf.City;
import com.zfh.app.mongo.entity.esf.District;
import com.zfh.app.mongo.entity.esf.HouseRegion;
import com.zfh.app.mongo.entity.esf.StandardRegion;
import com.zfh.app.mongo.service.esf.CityService;
import com.zfh.app.mongo.service.esf.DistrictService;
import com.zfh.app.mongo.service.esf.HouseRegionService;
import com.zfh.app.mongo.service.esf.StandardRegionService;

@Controller
@RequestMapping("admin/fang/region")
public class RegionController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    HouseRegionService houseRegionService;
    
    @Autowired
    StandardRegionService standardRegionService;
    
    @Autowired
    CityService cityService;
    
    @Autowired
    DistrictService districtService;
    
    @GetMapping("city/list")
    @SysLog("跳转城市列表")
    public String cityList(){
        return "admin/fang/region/cityList";
    }

    @PostMapping("city/list")
    @ResponseBody
    public LayerData<City> cityList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        LayerData<City> userLayerData = new LayerData<>();
		PageRequest pageRequset = new PageRequest(page - 1, limit);
        Page<City> pageData = cityService.findPage(pageRequset, null, null, "sort asc");
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    @GetMapping("city/add")
    @SysLog("添加城市")
    public String cityAdd(Model model){
        return "admin/fang/region/cityAdd";
    }
    
    @GetMapping("city/edit")
    @SysLog("编辑城市")
    public String cityEdit(@RequestParam(value = "id",required = true)String id, Model model){
    	City city = cityService.getById(id);
    	model.addAttribute("city", city);
        return "admin/fang/region/cityEdit";
    }
    
    
    @PostMapping("city/deleteById")
    @ResponseBody
    public RestResponse deleteById(@RequestParam(value = "id",required = true) String id){
        if(id == null || id == ""){
            return RestResponse.failure("城市ID错误");
        }
       	cityService.delete(id);
        return RestResponse.success();
    }
    
    @PostMapping("city/add")
    @SysLog("保存城市")
    @ResponseBody
    public RestResponse addCity(City model){
    	String province = model.getProvince();
    	String cityName = model.getCity();
        if(StringUtils.isEmpty(province)){
            return RestResponse.failure("省份不能为空");
        }
        if(StringUtils.isEmpty(cityName)){
            return RestResponse.failure("城市不能为空");
        }
        if(StringUtils.isEmpty(model.getCityImg())){
            return RestResponse.failure("城市图片链接不能为空");
        }
        if(StringUtils.isEmpty(model.getLongitude()) || StringUtils.isEmpty(model.getLatitude())){
            return RestResponse.failure("城市经纬度不能为空");
        }
        if (province.endsWith("省")) {
        	province = province.substring(0, province.length() - 1);
        }
        if (cityName.endsWith("市")) {
        	cityName = cityName.substring(0, cityName.length() - 1);
        }
        City city = new City();
        city.setCity(cityName);
        city.setCityCode(model.getCityCode());
        city.setCityImg(model.getCityImg());
        city.setCityInitials(PinyinHelper.getShortPinyin(cityName).substring(0, 1).toUpperCase());
        city.setCityPinyin(PinyinHelper.convertToPinyinString(cityName, "", PinyinFormat.WITHOUT_TONE));
        city.setDisplay(model.isDisplay());
        city.setDomain(model.getDomain());
        city.setLatitude(model.getLatitude());
        city.setLongitude(model.getLongitude());
        city.setProvince(province);
        city.setProvinceInitials(PinyinHelper.getShortPinyin(province).substring(0, 1).toUpperCase());
        city.setProvincePinyin(PinyinHelper.convertToPinyinString(province, "", PinyinFormat.WITHOUT_TONE));
        city.setProvinceCode(model.getProvinceCode());
        city.setSort(model.getSort());
        city.setHot(model.isHot());
        cityService.save(city);
        return RestResponse.success();
    }
    
    @PostMapping("city/edit")
    @SysLog("保存城市")
    @ResponseBody
    public RestResponse editCity(City model){
    	String province = model.getProvince();
    	String cityName = model.getCity();
        if(StringUtils.isEmpty(province)){
            return RestResponse.failure("省份不能为空");
        }
        if(StringUtils.isEmpty(cityName)){
            return RestResponse.failure("城市不能为空");
        }
        if(StringUtils.isEmpty(model.getCityImg())){
            return RestResponse.failure("城市图片链接不能为空");
        }
        if(StringUtils.isEmpty(model.getLongitude()) || StringUtils.isEmpty(model.getLatitude())){
            return RestResponse.failure("城市经纬度不能为空");
        }
        if (province.endsWith("省")) {
        	province = province.substring(0, province.length() - 1);
        }
        if (cityName.endsWith("市")) {
        	cityName = cityName.substring(0, cityName.length() - 1);
        }
        City city = new City();
        if (StringUtils.isNotEmpty(model.getId())) {
        	city = cityService.getById(model.getId());
        }
     
        city.setCity(cityName);
        city.setCityCode(model.getCityCode());
        city.setCityImg(model.getCityImg());
        city.setCityInitials(PinyinHelper.getShortPinyin(cityName).substring(0, 1).toUpperCase());
        city.setCityPinyin(PinyinHelper.convertToPinyinString(cityName, "", PinyinFormat.WITHOUT_TONE));
        city.setDisplay(model.isDisplay());
        city.setDomain(model.getDomain());
        city.setLatitude(model.getLatitude());
        city.setLongitude(model.getLongitude());
        city.setProvince(province);
        city.setProvinceInitials(PinyinHelper.getShortPinyin(province).substring(0, 1).toUpperCase());
        city.setProvincePinyin(PinyinHelper.convertToPinyinString(province, "", PinyinFormat.WITHOUT_TONE));
        city.setProvinceCode(model.getProvinceCode());
        city.setSort(model.getSort());
        city.setHot(model.isHot());
        cityService.save(city);
        cityService.refreshData(city);
        return RestResponse.success();
    }

    
    @PostMapping("city/refresh")
    @SysLog("刷新城市相关数据")
    @ResponseBody
    public RestResponse refreshCity(City model){
    	List<City> cityList = cityService.findAll();
    	for (City city : cityList) {
    		cityService.refreshData(city);
		}
        return RestResponse.success();
    }

    
    
    @GetMapping("district/list")
    @SysLog("跳转城市区域列表")
    public String districtList(@RequestParam(value = "cityId",required = true)String cityId,Model model){
        City city = cityService.getById(cityId);
        model.addAttribute("city", city);
        return "admin/fang/region/districtList";
    }
    
    @GetMapping("district/add")
    @SysLog("添加区域")
    public String districtAdd(@RequestParam(value = "cityId",required = true)String cityId,Model model){
        City city = cityService.getById(cityId);
        model.addAttribute("city", city);
        return "admin/fang/region/districtAdd";
    }
    
    @GetMapping("district/edit")
    @SysLog("编辑区域")
    public String districtEdit(@RequestParam(value = "id",required = true)String id, Model model){
    	District district = districtService.getById(id);
    	model.addAttribute("district", district);
        return "admin/fang/region/districtEdit";
    }
    
    @PostMapping("district/deleteById")
    @ResponseBody
    public RestResponse deleteDistrictById(@RequestParam(value = "id",required = true) String id){
        if(id == null || id == "" || id == "undefined"){
            return RestResponse.failure("区域ID错误");
        }
        districtService.delete(id);
        return RestResponse.success();
    }
    
    
    @PostMapping("district/list")
    @ResponseBody
    public LayerData<District> districtList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
	    Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
       	String city = (String)map.get("city");
       	String district = (String)map.get("district"); 
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
       	if (StringUtils.isNotEmpty(city)) {
    		paramsList.add("city");
    		valuesList.add(city);
    	}
    	if (StringUtils.isNotEmpty(district)) {
    		paramsList.add("district");
    		valuesList.add(district);
    	}
    	// boolean 类型使用toArray 会报空指针
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
        LayerData<District> userLayerData = new LayerData<>();
		PageRequest pageRequset = new PageRequest(page - 1, limit);
        Page<District> pageData = districtService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), obj, "sort asc");
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }

    @PostMapping("district/add")
    @SysLog("新增区域")
    @ResponseBody
    public RestResponse addDistrict(District model){
    	String cityName = model.getCity();
    	String districtName = model.getDistrict();
        if(StringUtils.isEmpty(cityName)){
            return RestResponse.failure("城市不能为空");
        }
        if(StringUtils.isEmpty(districtName)){
            return RestResponse.failure("区域不能为空");
        }
        if (cityName.endsWith("市")) {
        	cityName = cityName.substring(0, cityName.length() - 1);
        }
        City city = cityService.uniqueByProp("city", cityName);
        if (city == null) {
        	return RestResponse.failure("所属城市不存在");
        }
        District district = new District();
        district.setCity(city.getCity());
        district.setCityCode(city.getCityCode());
        district.setDisplay(model.isDisplay());
        district.setDistrict(districtName);
        district.setDistrictCode(model.getDistrictCode());
        district.setDistrictInitials(PinyinHelper.getShortPinyin(districtName).substring(0, 1).toUpperCase());
        district.setDistrictPinyin(PinyinHelper.convertToPinyinString(districtName, "", PinyinFormat.WITHOUT_TONE));
        district.setDomain(city.getDomain());
        district.setLatitude(model.getLatitude());
        district.setLongitude(model.getLongitude());
        district.setProvince(city.getProvince());
        district.setSort(model.getSort());
        districtService.save(district);
        return RestResponse.success();
    }
    
    @PostMapping("district/edit")
    @SysLog("修改区域")
    @ResponseBody
    public RestResponse editDistrict(District model){
    	String cityName = model.getCity();
    	String districtName = model.getDistrict();
        if(StringUtils.isEmpty(cityName)){
            return RestResponse.failure("城市不能为空");
        }
        if(StringUtils.isEmpty(districtName)){
            return RestResponse.failure("区域不能为空");
        }
        if (cityName.endsWith("市")) {
        	cityName = cityName.substring(0, cityName.length() - 1);
        }
        City city = cityService.uniqueByProp("city", cityName);
        if (city == null) {
        	return RestResponse.failure("所属城市不存在");
        }
        District district = districtService.getById(model.getId());
        district.setCity(city.getCity());
        district.setCityCode(city.getCityCode());
        district.setDisplay(model.isDisplay());
        district.setDistrict(districtName);
        district.setDistrictCode(model.getDistrictCode());
        district.setDistrictInitials(PinyinHelper.getShortPinyin(districtName).substring(0, 1).toUpperCase());
        district.setDistrictPinyin(PinyinHelper.convertToPinyinString(districtName, "", PinyinFormat.WITHOUT_TONE));
        district.setDomain(city.getDomain());
        district.setLatitude(model.getLatitude());
        district.setLongitude(model.getLongitude());
        district.setProvince(city.getProvince());
        district.setSort(model.getSort());
        districtService.save(district);
        return RestResponse.success();
    }
    
    @GetMapping("houseRegion/list")
    @SysLog("跳转房源区域列表")
    public String houseRegion(){
        return "admin/fang/region/houseRegion";
    }

    @PostMapping("houseRegion/list")
    @ResponseBody
    public LayerData<HouseRegion> houseRegion(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        LayerData<HouseRegion> userLayerData = new LayerData<>();
		PageRequest pageRequset = new PageRequest(page - 1, limit);
        Page<HouseRegion> pageData = houseRegionService.findPage(pageRequset, null, null, null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    
    @GetMapping("standard/list")
    @SysLog("跳转房源区域列表")
    public String standardRegion(){
        return "admin/fang/region/standardRegion";
    }

    @PostMapping("standard/list")
    @ResponseBody
    public LayerData<StandardRegion> standardRegion(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        LayerData<StandardRegion> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, "cityCode"));
		orders.add(new Order(Direction.ASC, "districtNo"));
		orders.add(new Order(Direction.ASC, "blockInitials"));
		Sort sort = new Sort(orders);
		PageRequest pageRequset = new PageRequest(page - 1, limit, sort);
        Page<StandardRegion> pageData = standardRegionService.findPage(pageRequset, null, null, null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
}
