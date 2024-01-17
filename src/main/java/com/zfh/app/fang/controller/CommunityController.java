package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.esf.CommunityBase;
import com.zfh.app.mongo.entity.esf.CommunityBaseAlias;
import com.zfh.app.mongo.entity.esf.StandCommunity;
import com.zfh.app.mongo.service.esf.CommunityBaseAliasService;
import com.zfh.app.mongo.service.esf.CommunityBaseRefService;
import com.zfh.app.mongo.service.esf.CommunityBaseService;
import com.zfh.app.mongo.service.esf.CommunityService;
import com.zfh.app.mongo.service.esf.StandAnalysisService;
import com.zfh.app.mongo.service.esf.StandCommunityService;

@Controller
@RequestMapping("admin/community")
public class CommunityController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	CommunityService communityService;

	@Autowired
	StandCommunityService standCommunityService;
	
	@Autowired
	StandAnalysisService standAnalysisService;
	
	@Autowired
	CommunityBaseService communityBaseService;
	
	@Autowired
	CommunityBaseAliasService communityBaseAliasService;

	@GetMapping("list")
	@SysLog("跳转小区列表页面")
	public String list() {
		return "admin/community/list";
	}

//	@RequiresPermissions("comm:data:list")
	@PostMapping("list")
	@ResponseBody
	public LayerData<Community> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
		Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
		String community = (String) map.get("community");
    	List<String> paramsList = new ArrayList<String>();
    	List<Object> valuesList = new ArrayList<Object>();
    	if (!StringUtils.isEmpty(community)) {
    		paramsList.add("community");
    		valuesList.add(community);
    	}
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
    	
		LayerData<Community> userLayerData = new LayerData<>();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageReqt = new PageRequest(page - 1, limit, new Sort(orders));
		Page<Community> pageData = communityService.findPage(pageReqt, (String[])paramsList.toArray(new String[paramsList.size()]), obj, null);
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}

	@GetMapping("stand/list")
	@SysLog("跳转标准小区列表页面")
	public String standlist() {
		return "admin/community/standlist";
	}

//	@RequiresPermissions("comm:data:standlist")
	@PostMapping("stand/list")
	@ResponseBody
	public LayerData<StandCommunity> standlist(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
		Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
		String city = (String) map.get("city");
		String standName = (String) map.get("standName");
    	List<String> paramsList = new ArrayList<String>();
    	List<Object> valuesList = new ArrayList<Object>();
    	if (!StringUtils.isEmpty(city)) {
    		paramsList.add("city");
    		valuesList.add(city);
    	}
    	if (!StringUtils.isEmpty(standName)) {
    		paramsList.add("standName");
    		valuesList.add(standName);
    	}
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
    	
		LayerData<StandCommunity> userLayerData = new LayerData<>();
		PageRequest pageReqt = new PageRequest(page - 1, limit);
		Page<StandCommunity> pageData = standCommunityService.findPage(pageReqt, (String[])paramsList.toArray(new String[paramsList.size()]), obj, "_id desc");
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}
	
	@GetMapping("base/list")
	@SysLog("跳转标准小区列表页面")
	public String baselist() {
		return "admin/community/baselist";
	}

//	@RequiresPermissions("comm:data:baselist")
	@PostMapping("base/list")
	@ResponseBody
	public LayerData<CommunityBase> baselist(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
		Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
		String city = (String) map.get("city");
		String baseName = (String) map.get("baseName");
    	List<String> paramsList = new ArrayList<String>();
    	List<Object> valuesList = new ArrayList<Object>();
    	if (!StringUtils.isEmpty(city)) {
    		paramsList.add("city");
    		valuesList.add(city);
    	}
    	if (!StringUtils.isEmpty(baseName)) {
    		paramsList.add("baseName");
    		valuesList.add(baseName);
    	}
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
    	
		LayerData<CommunityBase> userLayerData = new LayerData<>();
		PageRequest pageReqt = new PageRequest(page - 1, limit);
		Page<CommunityBase> pageData = communityBaseService.findPage(pageReqt, (String[])paramsList.toArray(new String[paramsList.size()]), obj,  "_id desc");
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		
		for (CommunityBase communityBase : pageData.getContent()) {
			List<CommunityBaseAlias> aliasList = communityBaseAliasService.findByProp("baseId", communityBase.getBaseId());
			if (aliasList != null && aliasList.size() > 0) {
				StringBuffer aliasName = new StringBuffer();
				for (CommunityBaseAlias communityBaseAlias : aliasList) {
					aliasName.append(communityBaseAlias.getAliasName()).append(",");
				}
				String aliasNames = aliasName.toString();
				communityBase.setAliasName(aliasNames.substring(0, aliasNames.length() - 1));
			}
		}
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}
	
//	@RequiresPermissions("comm:stand:detection")
	@PostMapping("stand/detection")
	@SysLog("执行标准小区挂牌分析前，进行单价异常值检测")
	@ResponseBody
	public RestResponse detection() {
		LOGGER.info("开始任务-单价异常值检测");
		standAnalysisService.mulitDetection();
		LOGGER.info("结束任务-单价异常值检测");
		return RestResponse.success();
	}
	
//	@RequiresPermissions("comm:stand:analysis")
	@PostMapping("stand/analysis")
	@SysLog("执行标准小区挂牌分析")
	@ResponseBody
	public RestResponse analysis() {
		LOGGER.info("开始任务-执行标准小区挂牌分析");
		standAnalysisService.mulitAnalysis();
		LOGGER.info("结束任务-执行标准小区挂牌分析");
		
		return RestResponse.success();
	}
	
}
