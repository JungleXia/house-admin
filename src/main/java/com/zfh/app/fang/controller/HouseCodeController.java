package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.util.LayerData;
import com.zfh.app.mongo.entity.esf.HouseCode;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseCodeMongoService;

@Controller
@RequestMapping("admin/houseCode")
public class HouseCodeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(HouseCodeController.class);

	@Autowired
	private HouseCodeMongoService houseCodeMongoService;

	@Autowired
	private ErshoufangService ershoufangService;
	
	@GetMapping("list")
	@SysLog("跳转小区列表页面")
	public String list() {
		return "admin/fang/houseCode/list";
	}

	// @RequiresPermissions("esfang:housecode:list")
	@PostMapping("list")
	@ResponseBody
	public LayerData<HouseCode> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String houseCode = (String)map.get("houseCode");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(houseCode)) {
    		paramsList.add("houseCode");
    		valuesList.add(houseCode);
    	}
		LayerData<HouseCode> userLayerData = new LayerData<>();
//		List<Order> orders = new ArrayList<Order>();
//		Sort sort = new Sort(orders);
		PageRequest pageRequset = new PageRequest(page - 1, limit, null);
		Page<HouseCode> pageData = houseCodeMongoService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), (Object[])valuesList.toArray(new Object[valuesList.size()]), null);
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}

}
