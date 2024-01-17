package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.core.util.CityConstant;
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.esf.HouseOffline;
import com.zfh.app.mongo.service.esf.CityService;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseOfflineService;
import com.zfh.app.mongo.service.esf.HouseService;

@Controller
@RequestMapping("admin/house")
public class HouseController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	HouseService houseService;
	
	@Autowired
	ErshoufangService ershoufangService;
	
	@Autowired
	HouseOfflineService houseOfflineService;
	
    @Autowired
    private CityService cityService;

	@GetMapping("list")
	@SysLog("跳转推荐房源页面")
	public String list(Model model) {
		model.addAttribute("cityList", cityService.findAll());
		return "admin/fang/house/list";
	}

	@GetMapping("offline/list")
	@SysLog("跳转下架房源页面")
	public String offline() {
		return "admin/fang/house/offline";
	}
	
//	@RequiresPermissions("house:data:list")
	@PostMapping("list")
	@ResponseBody
	public LayerData<House> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String houseId = (String)map.get("houseId");
    	String standId = (String)map.get("standId");
    	String baseId = (String)map.get("baseId");
    	String online = (String)map.get("online");
    	String reco = (String)map.get("reco");
    	String city = (String)map.get("city");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(houseId)) {
    		paramsList.add("_id");
    		valuesList.add(new ObjectId(houseId));
    	}
    	if (!StringUtils.isEmpty(standId)) {
    		paramsList.add("standId");
    		valuesList.add(standId);
    	}
    	if (!StringUtils.isEmpty(baseId)) {
    		paramsList.add("baseId");
    		valuesList.add(baseId);
    	}
    	if (!StringUtils.isEmpty(city)) {
    		paramsList.add("city");
    		valuesList.add(city);
    	}
    	if (!StringUtils.isEmpty(online)) {
    		paramsList.add("online");
    		if (online.equals("0")) {
        		valuesList.add(false);
    		} else {
    			valuesList.add(true);
    		}
    	}
    	if (!StringUtils.isEmpty(reco)) {
    		paramsList.add("reco");
    		if (reco.equals("0")) {
        		valuesList.add(false);
    		} else {
    			valuesList.add(true);
    		}
    	}
    	
    	// boolean 类型使用toArray 会报空指针
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
		LayerData<House> userLayerData = new LayerData<>();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.ASC, "discountRate"));
		orders.add(new Order(Direction.ASC, "totalPrice"));
		orders.add(new Order(Direction.ASC, "area"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
		Page<House> pageData = houseService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), obj, null);
		for (House house: pageData.getContent()) {
			if (StringUtils.isEmpty(house.getUrl())) {
				Ershoufang ershoufang = ershoufangService.getById(house.getId());
				if (ershoufang != null) {
					house.setUrl(ershoufang.getUrl());
				}
			}
		}
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}
	
//	@RequiresPermissions("house:data:offlinelist")
	@PostMapping("offline/list")
	@ResponseBody
	public LayerData<HouseOffline> offlinelist(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String houseId = (String)map.get("houseId");
    	String standId = (String)map.get("standId");
    	String standName = (String)map.get("standName");
    	String baseId = (String)map.get("baseId");
    	String baseName = (String)map.get("baseName");
    	String online = (String)map.get("online");
    	String reco = (String)map.get("reco");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(houseId)) {
    		paramsList.add("_id");
    		valuesList.add(new ObjectId(houseId));
    	}
    	if (!StringUtils.isEmpty(standId)) {
    		paramsList.add("standId");
    		valuesList.add(standId);
    	}
    	if (!StringUtils.isEmpty(standName)) {
    		paramsList.add("standName");
    		valuesList.add(standName);
    	}
    	if (!StringUtils.isEmpty(baseId)) {
    		paramsList.add("baseId");
    		valuesList.add(baseId);
    	}
    	if (!StringUtils.isEmpty(baseName)) {
    		paramsList.add("baseName");
    		valuesList.add(baseName);
    	}
    	if (!StringUtils.isEmpty(reco)) {
    		paramsList.add("reco");
    		if (reco.equals("0")) {
        		valuesList.add(false);
    		} else {
    			valuesList.add(true);
    		}
    	}
    	
    	// boolean 类型使用toArray 会报空指针
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
		LayerData<HouseOffline> userLayerData = new LayerData<>();
		PageRequest pageRequset = new PageRequest(page - 1, limit);
		Page<HouseOffline> pageData = houseOfflineService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), obj, "_id desc");
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}

//	@RequiresPermissions("house:data:copy")
	@PostMapping("copy")
	@SysLog("同步真房源")
	@ResponseBody
	public RestResponse copy() {
		logger.info("同步真房源");
		houseService.copyErshoufang();
		logger.info("同步真房源");
		return RestResponse.success();
	}
	
//	@RequiresPermissions("house:data:analysis")
	@PostMapping("price/analysis")
	@SysLog("房源价格分析")
	@ResponseBody
	public RestResponse fullAnalysis() {
		logger.info("开始任务-房源价格分析");
		houseService.taskFullAnalysis();
		logger.info("结束任务-房源价格分析");
		return RestResponse.success();
	}
	
	@PostMapping("set/status")
	@SysLog("上架/下架房源")
	@ResponseBody
	public RestResponse setStatus(@RequestParam(value = "id",required = true) String id) {
		House house = houseService.getById(id);
		Ershoufang esf = ershoufangService.getById(id);
		if (house != null && house.isOnline()) {
			house.setOnline(false);
			houseService.save(house);
		} else if (house != null && !house.isOnline()) {
			house.setOnline(true);
			houseService.save(house);
		}
		if (esf != null && esf.isOnline()) {
			esf.setOnline(false);
			ershoufangService.save(esf);
			
			// 保存下架房源记录
			HouseOffline offline = new HouseOffline();
			BeanUtils.copyProperties(house, offline);
			offline.setExpiredTime(DateUtil.currentDateTime());
			if (org.springframework.util.StringUtils.isEmpty(offline.getUrl())) {
				offline.setUrl(esf.getUrl());
			}
			if (org.springframework.util.StringUtils.isEmpty(offline.getDataFrom())) {
				offline.setDataFrom(esf.getDataFrom());
			}
			houseOfflineService.save(offline);
		} else if (esf != null && !esf.isOnline()) {
			esf.setOnline(true);
			ershoufangService.save(esf);
			houseOfflineService.delete(house.getId());
		}
		return RestResponse.success();
	}
}
