package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.mongo.entity.esf.HousePhoto;
import com.zfh.app.mongo.service.esf.HousePhotoService;

@Controller
@RequestMapping("admin/fang/housePhoto")
public class HousePhotoController extends BaseController{
	
    private static final Logger logger = LoggerFactory.getLogger(HousePhotoController.class);

    @Autowired
    HousePhotoService housePhotoService;

    @GetMapping("list")
    @SysLog("跳转小区列表页面")
    public String list(){
        return "admin/fang/housePhoto/list";
    }

//    @RequiresPermissions("comm:photo:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<HousePhoto> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        LayerData<HousePhoto> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit);
        Page<HousePhoto> pageData = housePhotoService.findPage(pageRequset, null, null, null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
	// @RequiresPermissions("esfang:house:synchred")
	@PostMapping("download")
	@SysLog("批量下载图片")
	@ResponseBody
	public RestResponse download() {
		logger.info("开始-批量下载图片");
		housePhotoService.download();
		logger.info("结束-批量下载图片");
		return RestResponse.success();
	}
}
