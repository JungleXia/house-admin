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

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.util.LayerData;
import com.zfh.app.mongo.entity.esf.LjEsfCommunity;
import com.zfh.app.mongo.service.esf.LjEsfCommunityService;

@Controller
@RequestMapping("admin/ljEsfCommunity")
public class LjEsfCommunityController {


	private static final Logger logger = LoggerFactory.getLogger(LjEsfCommunityController.class);

	@Autowired
	LjEsfCommunityService ljEsfCommunityService;


	@GetMapping("list")
	@SysLog("跳转链家二手房页面")
	public String list(Model model) {
		return "admin/fang/ljEsfCommunity/list";
	}

	
//	@RequiresPermissions("ljEsfHouse:data:list")
	@PostMapping("list")
	@ResponseBody
	public LayerData<LjEsfCommunity> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String cNo = (String)map.get("cNo");
    	String community = (String)map.get("community");
    	String status = (String)map.get("status");
    	String city = (String)map.get("city");
    	String district = (String)map.get("district");
    	String block = (String)map.get("block");
    	List<Object> paramsList = new ArrayList<>();
    	List<Object> valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(cNo)) {
    		paramsList.add("cNo");
    		valuesList.add(cNo);
    	}
    	if (!StringUtils.isEmpty(community)) {
    		paramsList.add("community");
    		valuesList.add(community);
    	}
    	if (!StringUtils.isEmpty(city)) {
    		paramsList.add("city");
    		valuesList.add(city);
    	}
    	if (!StringUtils.isEmpty(district)) {
    		paramsList.add("district");
    		valuesList.add(district);
    	}
    	if (!StringUtils.isEmpty(block)) {
    		paramsList.add("block");
    		valuesList.add(block);
    	}
    	if (!StringUtils.isEmpty(status)) {
    		paramsList.add("status");
    		valuesList.add(Integer.valueOf((String)map.get("status")));
    	}
    	// boolean 类型使用toArray 会报空指针
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
		LayerData<LjEsfCommunity> userLayerData = new LayerData<>();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "dealNum"));
		if (StringUtils.equals(status, "2")) {
			orders.add(new Order(Direction.DESC, "createTime"));
		} else {
			orders.add(new Order(Direction.DESC, "updateTime"));
		}
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
		Page<LjEsfCommunity> pageData = ljEsfCommunityService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), obj, null);
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}
	


}
