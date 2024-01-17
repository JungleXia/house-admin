package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.util.LayerData;
import com.zfh.app.mongo.entity.esf.XinHouseType;
import com.zfh.app.mongo.entity.esf.XinLoupan;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.esf.XinHouseTypeService;
import com.zfh.app.mongo.service.esf.XinLoupanService;

@Controller
@RequestMapping("admin/xinfang")
public class XinFangController extends BaseController {

	
    private static final Logger logger = LoggerFactory.getLogger(HousePhotoController.class);

	@Autowired
	HouseService houseService;
	
	@Autowired
	XinHouseTypeService xinHouseTypeService;
	
	@Autowired
	XinLoupanService xinLoupanService;
	
    @GetMapping("loupan/list")
    @SysLog("跳转楼盘列表页面")
    public String loupanList(){
        return "admin/fang/xinfang/loupanList";
    }

//    @RequiresPermissions("xinfang:loupan:list")
    @PostMapping("loupan/list")
    @ResponseBody
    public LayerData<XinLoupan> loupanList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        LayerData<XinLoupan> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<XinLoupan> pageData = xinLoupanService.findPage(pageRequset, null, null, null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    

    @GetMapping("houseType/list")
    @SysLog("跳转户型列表页面")
    public String list(){
        return "admin/fang/xinfang/houseTypeList";
    }

//    @RequiresPermissions("xinfang:houseType:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<XinHouseType> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        LayerData<XinHouseType> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "reco"));
		orders.add(new Order(Direction.DESC, "projectId"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<XinHouseType> pageData = xinHouseTypeService.findPage(pageRequset, null, null, null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }

}
