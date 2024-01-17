package com.zfh.app.fang.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.entity.VO.ZtreeVO;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.mongo.entity.esf.LjEsfStatistic;
import com.zfh.app.mongo.service.esf.LjEsfStatisticService;

@Controller
@RequestMapping("admin/ljEsfStatistic")
public class LjEsfStatisticController {

	private static final Logger logger = LoggerFactory.getLogger(LjEsfStatisticController.class);

	@Autowired
	LjEsfStatisticService ljEsfStatisticService;
	
	@GetMapping("list")
	@SysLog("跳转链家房源统计页面")
	public String list(Model model) {
		return "admin/fang/ljEsfStatistic/list";
	}
	
    @PostMapping("ztreeData")
    @ResponseBody
    public RestResponse getZtreeData(){
        List<ZtreeVO> list = ljEsfStatisticService.selectZtreeData();
        return RestResponse.success().setData(list);
    }
    
//	@RequiresPermissions("ljEsfStatistic:data:list")
	@PostMapping("list")
	@ResponseBody
	public LayerData<LjEsfStatistic> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String province = (String)map.get("province");
    	String city = (String)map.get("city");
    	String district = (String)map.get("district");
    	String block = (String)map.get("block");
    	String type = (String)map.get("type");
    	List<Object> paramsList = new ArrayList<>();
    	List<Object> valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(province)) {
    		paramsList.add("province");
    		valuesList.add(province);
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
    	if (!StringUtils.isEmpty(type)) {
    		paramsList.add("type");
    		valuesList.add(Integer.valueOf((String)map.get("type")));
    	}

    	// boolean 类型使用toArray 会报空指针
    	Object[] obj = new Object[valuesList.size()];
    	for (int i = 0; i < valuesList.size(); i++) {
    		obj[i] = valuesList.get(i);
    	}
		LayerData<LjEsfStatistic> userLayerData = new LayerData<>();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "createDay"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
		Page<LjEsfStatistic> pageData = ljEsfStatisticService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), obj, null);
		userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
		userLayerData.setData(pageData.getContent());
		return userLayerData;
	}
	
    @GetMapping("add")
    public String add(Model model){
        return "admin/fang/ljEsfStatistic/add";
    }
    
    @PostMapping("add")
    @SysLog("新增链接统计数据")
    @ResponseBody
    public RestResponse add(@RequestBody LjEsfStatistic ljEsfStatistic){
        if (StringUtils.isNotEmpty(ljEsfStatistic.getProvince()) && StringUtils.isNotEmpty(ljEsfStatistic.getCity())) {
        	ljEsfStatistic.setType(1);
        	if (StringUtils.isNotEmpty(ljEsfStatistic.getDistrict())) {
        		ljEsfStatistic.setType(2);
        	}
        	if (StringUtils.isNotEmpty(ljEsfStatistic.getBlock())) {
        		ljEsfStatistic.setType(3);
        	}
        } else {
        	return RestResponse.failure("省、市字段不能为空");
        }
		try {
			URL url = new URL(ljEsfStatistic.getUrl());
			ljEsfStatistic.setCityKey(url.getHost().replace(".lianjia.com", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
       
        ljEsfStatistic.setCreateDay(DateUtil.format(DateUtil.now()));
        ljEsfStatistic.setDiff(ljEsfStatistic.getNumbers() - ljEsfStatistic.getPrenums());
        ljEsfStatisticService.update(ljEsfStatistic);
        ljEsfStatisticService.save(ljEsfStatistic);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(String id,Model model){
	   	 LjEsfStatistic ljEsfStatistic = ljEsfStatisticService.getById(id);
	   	 model.addAttribute("ljEsfStatistic", ljEsfStatistic);
         return "admin/fang/ljEsfStatistic/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("编辑链家统计数据")
    public RestResponse edit(@RequestBody LjEsfStatistic ljEsfStatistic){
        if(null == ljEsfStatistic.getId() || "0".equals(ljEsfStatistic.getId())){
            return RestResponse.failure("ID不能为空");
        }
        if (StringUtils.isNotEmpty(ljEsfStatistic.getProvince()) && StringUtils.isNotEmpty(ljEsfStatistic.getCity())) {
        	ljEsfStatistic.setType(1);
        	if (StringUtils.isNotEmpty(ljEsfStatistic.getDistrict())) {
        		ljEsfStatistic.setType(2);
        	}
        	if (StringUtils.isNotEmpty(ljEsfStatistic.getBlock())) {
        		ljEsfStatistic.setType(3);
        	}
        } else {
        	return RestResponse.failure("省、市字段不能为空");
        }
		try {
			URL url = new URL(ljEsfStatistic.getUrl());
			ljEsfStatistic.setCityKey(url.getHost().replace(".lianjia.com", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
       
        ljEsfStatistic.setCreateDay(DateUtil.format(DateUtil.now()));
        ljEsfStatistic.setDiff(ljEsfStatistic.getNumbers() - ljEsfStatistic.getPrenums());
        ljEsfStatisticService.update(ljEsfStatistic);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除链家统计数据")
    public RestResponse delete(@RequestParam(value = "id",required = true) String id){
        if(StringUtils.isEmpty(id)){
            return RestResponse.failure("ID不能为空");
        }
        ljEsfStatisticService.delete(id);
        return RestResponse.success();
    }
}
