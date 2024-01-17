package com.zfh.app.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysiteforme.admin.util.RestResponse;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.zfh.app.mongo.service.esf.CityService;
import com.zfh.app.mongo.service.esf.LjEsfStatisticService;

@RequestMapping("api")
@Controller
public class ApiHouseController {

	private static Log logger = LogFactory.get();
	
    @Autowired
    private LjEsfStatisticService ljEsfStatisticService;
	
    @PostMapping("click")
    @ResponseBody
    public RestResponse changeClicks(@RequestParam(value = "articleId",required = false) Long articleId){
        if(articleId == null){
            return RestResponse.failure("文章ID不能为空");
        }
        return RestResponse.success().setData(null);
    }

    @GetMapping("test")
    @ResponseBody
    public RestResponse test(Long channelId){
    	logger.info("test success ");
        return RestResponse.success().setData(ljEsfStatisticService.findProvinceList());
    }
}
