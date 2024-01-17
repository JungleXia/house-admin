package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Role;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.mongo.entity.system.Advert;
import com.zfh.app.mongo.service.system.AdvertService;

import freemarker.template.utility.StringUtil;

/**
 * app 广告设置
 * @author CB
 * 
 * @dateTime 2019年11月18日上午11:19:16
 */
@Controller
@RequestMapping("admin/advert")
public class AdvertController extends BaseController{

	@Autowired
	private AdvertService advertService;
	
    @GetMapping("list")
    @SysLog("跳转客户统计")
    public String analyzeList(){
        return "admin/fang/advert/list";
    }
    
//	@RequiresPermissions("admin:advert:list")
	@PostMapping("list")
	@ResponseBody
	public LayerData<Advert> advertList(@RequestParam(value = "page",defaultValue = "1")Integer page,
	              @RequestParam(value = "limit",defaultValue = "10")Integer limit,
	              ServletRequest request){
	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");

	List paramsList = new ArrayList<>();
	List valuesList = new ArrayList<>();

	LayerData<Advert> userLayerData = new LayerData<>();
	List<Order> orders = new ArrayList<Order>();
	orders.add(new Order(Direction.ASC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
		Page<Advert> pageData = advertService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return  userLayerData;
	}
	
	@GetMapping("add")
    public String add(Model model){
        return "admin/fang/advert/add";
    }
	
	@GetMapping("edit")
    public String edit(String id,Model model){
		Advert advert = advertService.getById(id);
        model.addAttribute("advert", advert);
        return "admin/fang/advert/edit";
    }
	
    @PostMapping("add")
    @ResponseBody
    @SysLog("保存广告编辑数据")
    public RestResponse add(Advert advert){
    	if (StringUtils.isEmpty(advert.getTargetType())) {
    		advert.setTargetType("native");
    	}
    	if (StringUtils.isEmpty(advert.getTarget())) {
    		advert.setTarget(0);
    	}
    	advertService.save(advert);
        return RestResponse.success();
    }
    

//    @RequiresPermissions("sys:user:edit")
    @PostMapping("save")
    @ResponseBody
    @SysLog("保存广告编辑数据")
    public RestResponse edit(Advert advert){
        if(advert.getId() == "" || advert.getId() == null){
            return RestResponse.failure("ID不能为空");
        }
        Advert entity = advertService.getById(advert.getId());
        if (entity != null) {
        	if (!StringUtils.isEmpty(advert.getImageUrl())) {
        		entity.setImageUrl(advert.getImageUrl());
        	}
        	if (!StringUtils.isEmpty(advert.isExpired())) {
        		entity.setExpired(advert.isExpired());
        	}
        	advertService.save(entity);
        }
        return RestResponse.success();
    }
}
