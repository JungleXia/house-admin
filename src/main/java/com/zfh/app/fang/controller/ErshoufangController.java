package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseService;

@Controller
@RequestMapping("admin/ershoufang")
public class ErshoufangController extends BaseController{
	
    private static final Logger logger = LoggerFactory.getLogger(ErshoufangController.class);
    
    @Autowired
    ErshoufangService ershoufangService;
    
    @Autowired
    HouseService houseService;

    @GetMapping("list")
    @SysLog("跳转小区列表页面")
    public String list(){
        return "admin/fang/ershoufang/list";
    }

//    @RequiresPermissions("esfang:data:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<Ershoufang> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String houseId = (String)map.get("houseId");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(houseId)) {
    		paramsList.add("_id");
    		valuesList.add(new ObjectId(houseId));
    	}
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
        LayerData<Ershoufang> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		Sort sort = new Sort(orders);
		PageRequest pageRequset = new PageRequest(page - 1, limit, sort);
        Page<Ershoufang> pageData = ershoufangService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), obj, null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
	// @RequiresPermissions("esfang:house:synchred")
	@PostMapping("synchred")
	@SysLog("房源验真-绑定标准小区")
	@ResponseBody
	public RestResponse synchred() {
		logger.info("开始房源验真-绑定标准小区");
		ershoufangService.mulitVerify();
		logger.info("结束房源验真-绑定标准小区");
		return RestResponse.success();
	}
	
	// @RequiresPermissions("esfang:house:coverPic")
	@PostMapping("coverPic")
	@SysLog("房源-替换封面图片")
	@ResponseBody
	public RestResponse coverPic() {
		logger.info("开始房源-替换封面图片");
		ershoufangService.replacePicTask();
		logger.info("结束房源-替换封面图片");
		return RestResponse.success();
	}
	
}
