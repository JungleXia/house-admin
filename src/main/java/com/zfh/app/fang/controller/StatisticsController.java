package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.fang.vo.PieDataModel;
import com.zfh.app.mongo.service.system.DeviceActivateService;
import com.zfh.app.mongo.service.system.UserRequirementService;

/**
 * 用户分析
 * @author CB
 * 
 * @dateTime 2019年11月15日上午9:57:25
 */
@Controller
@RequestMapping("admin/statistics")
public class StatisticsController extends BaseController{

	@Resource(name = "redis2Template")
	private RedisTemplate<String, Object> redisTemplate2;
	
	@Autowired
	private DeviceActivateService deviceActivateService;
	
	@Autowired
	private UserRequirementService userRequirementService;
	
    @GetMapping("list")
    @SysLog("跳转客户统计")
    public String analyzeList(){
        return "admin/user/statistics";
    }
    
    @GetMapping("register")
    @ResponseBody
    public RestResponse getPV(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        
        String key_register = "statis:register_list";
        if(redisTemplate2.hasKey(key_register)){
        	statisList = redisTemplate2.opsForList().range(key_register, 0, -1);
        } else {
            int registerCount = deviceActivateService.countByCondition(new String[]{"register"}, new Object[]{true});
            PieDataModel register = new PieDataModel();
            register.setName("已注册");
            register.setValue(registerCount);
            statisList.add(register);
            int unregisterCount = deviceActivateService.countByCondition(new String[]{"register"}, new Object[]{false});
            PieDataModel unregister = new PieDataModel();
            unregister.setName("未注册");
            unregister.setValue(unregisterCount);
            statisList.add(unregister);
            redisTemplate2.opsForList().rightPushAll(key_register, statisList);
            redisTemplate2.expire(key_register, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", new String[]{"已注册", "未注册"});
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 未注册用户获取设备和定位情况
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("warrant/unregister")
    @ResponseBody
    public RestResponse getWarrant() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        
        String key_warrant = "statis:warrant_unregister_list";
        if(redisTemplate2.hasKey(key_warrant)){
        	statisList = redisTemplate2.opsForList().range(key_warrant, 0, -1);
        } else {
            int locate_device = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{false, true, true});
            PieDataModel pie1 = new PieDataModel();
            pie1.setName("获取设备和定位");
            pie1.setValue(locate_device);
            statisList.add(pie1);
            int unlocate_device = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{false, false, true});
            PieDataModel pie2 = new PieDataModel();
            pie2.setName("只获取设备");
            pie2.setValue(unlocate_device);
            statisList.add(pie2);
            int locate_undevice = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{false, true, false});
            PieDataModel pie3 = new PieDataModel();
            pie3.setName("只获取定位");
            pie3.setValue(locate_undevice);
            statisList.add(pie3);
            int unlocate_undevice = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{false, false, false});
            PieDataModel pie4 = new PieDataModel();
            pie4.setName("未获取设备和定位");
            pie4.setValue(unlocate_undevice);
            statisList.add(pie4);
            redisTemplate2.opsForList().rightPushAll(key_warrant, statisList);
            redisTemplate2.expire(key_warrant, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", new String[]{"获取设备和定位", "只获取设备", "只获取定位", "未获取设备和定位"});
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户获取设备和定位情况
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("warrant/register")
    @ResponseBody
    public RestResponse getWarrantReg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        
        String key_warrant = "statis:warrant_register_list";
        if(redisTemplate2.hasKey(key_warrant)){
        	statisList = redisTemplate2.opsForList().range(key_warrant, 0, -1);
        } else {
            int locate_device = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{true, true, true});
            PieDataModel pie1 = new PieDataModel();
            pie1.setName("获取设备和定位");
            pie1.setValue(locate_device);
            statisList.add(pie1);
            int unlocate_device = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{true, false, true});
            PieDataModel pie2 = new PieDataModel();
            pie2.setName("只获取设备");
            pie2.setValue(unlocate_device);
            statisList.add(pie2);
            int locate_undevice = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{true, true, false});
            PieDataModel pie3 = new PieDataModel();
            pie3.setName("只获取定位");
            pie3.setValue(locate_undevice);
            statisList.add(pie3);
            int unlocate_undevice = deviceActivateService.countByCondition(new String[]{"register", "allowLocate", "allowDevice"}, new Object[]{true, false, false});
            PieDataModel pie4 = new PieDataModel();
            pie4.setName("未获取设备和定位");
            pie4.setValue(unlocate_undevice);
            statisList.add(pie4);
            redisTemplate2.opsForList().rightPushAll(key_warrant, statisList);
            redisTemplate2.expire(key_warrant, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", new String[]{"获取设备和定位", "只获取设备", "只获取定位", "未获取设备和定位"});
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 所有用户点击首页换一批次数统计
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("nxfp/all")
    @ResponseBody
    public RestResponse getNxfpAll() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_nxfp_all = "statis:nxfp_all_list";
        String key_nxfp_all_count = "statis:nxfp_all_count";
        if(redisTemplate2.hasKey(key_nxfp_all)){
        	statisList = redisTemplate2.opsForList().range(key_nxfp_all, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_nxfp_all_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"nextfp"}, new Object[]{0});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("0次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("0次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 1, 5);
			pie1 = new PieDataModel();
            pie1.setName("1~5次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("1~5次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 6, 10);
			pie1 = new PieDataModel();
            pie1.setName("6~10次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("6~10次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 11, 15);
			pie1 = new PieDataModel();
            pie1.setName("11~15次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("11~15次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 16, 20);
			pie1 = new PieDataModel();
            pie1.setName("16~20次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("16~20次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 20, 1000);
			pie1 = new PieDataModel();
            pie1.setName("20次以上");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("20次以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_nxfp_all, statisList);
            redisTemplate2.expire(key_nxfp_all, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_nxfp_all_count, legendList);
            redisTemplate2.expire(key_nxfp_all_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 未注册用户点击首页换一批次数统计
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("nxfp/unregister")
    @ResponseBody
    public RestResponse getNxfpAllUnReg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_nxfp_all = "statis:nxfp_unreg_list";
        String key_nxfp_all_count = "statis:nxfp_unreg_count";
        if(redisTemplate2.hasKey(key_nxfp_all)){
        	statisList = redisTemplate2.opsForList().range(key_nxfp_all, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_nxfp_all_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"nextfp", "register"}, new Object[]{0, false});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("0次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("0次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 1, 5);
			pie1 = new PieDataModel();
            pie1.setName("1~5次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("1~5次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 6, 10);
			pie1 = new PieDataModel();
            pie1.setName("6~10次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("6~10次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 11, 15);
			pie1 = new PieDataModel();
            pie1.setName("11~15次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("11~15次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 16, 20);
			pie1 = new PieDataModel();
            pie1.setName("16~20次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("16~20次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 20, 1000);
			pie1 = new PieDataModel();
            pie1.setName("20次以上");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("20次以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_nxfp_all, statisList);
            redisTemplate2.expire(key_nxfp_all, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_nxfp_all_count, legendList);
            redisTemplate2.expire(key_nxfp_all_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户点击首页换一批次数统计
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("nxfp/register")
    @ResponseBody
    public RestResponse getNxfpAllReg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_nxfp_all = "statis:nxfp_reg_list";
        String key_nxfp_all_count = "statis:nxfp_reg_count";
        if(redisTemplate2.hasKey(key_nxfp_all)){
        	statisList = redisTemplate2.opsForList().range(key_nxfp_all, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_nxfp_all_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"nextfp", "register"}, new Object[]{0, true});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("0次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("0次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 1, 5);
			pie1 = new PieDataModel();
            pie1.setName("1~5次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("1~5次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 6, 10);
			pie1 = new PieDataModel();
            pie1.setName("6~10次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("6~10次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 11, 15);
			pie1 = new PieDataModel();
            pie1.setName("11~15次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("11~15次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 16, 20);
			pie1 = new PieDataModel();
            pie1.setName("16~20次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("16~20次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 20, 1000);
			pie1 = new PieDataModel();
            pie1.setName("20次以上");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("20次以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_nxfp_all, statisList);
            redisTemplate2.expire(key_nxfp_all, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_nxfp_all_count, legendList);
            redisTemplate2.expire(key_nxfp_all_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 所有用户点击首页换一批次数统计
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("view/all")
    @ResponseBody
    public RestResponse getViewAll() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_view_all = "statis:view_all_list";
        String key_view_all_count = "statis:view_all_count";
        if(redisTemplate2.hasKey(key_view_all)){
        	statisList = redisTemplate2.opsForList().range(key_view_all, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_view_all_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"viewDetail"}, new Object[]{0});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("0次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("0次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 1, 5);
			pie1 = new PieDataModel();
            pie1.setName("1~5次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("1~5次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 6, 10);
			pie1 = new PieDataModel();
            pie1.setName("6~10次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("6~10次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 11, 15);
			pie1 = new PieDataModel();
            pie1.setName("11~15次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("11~15次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 16, 20);
			pie1 = new PieDataModel();
            pie1.setName("16~20次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("16~20次");
            
    		count = deviceActivateService.countViewByCondition(null, null, 20, 1000);
			pie1 = new PieDataModel();
            pie1.setName("20次以上");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("20次以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_view_all, statisList);
            redisTemplate2.expire(key_view_all, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_view_all_count, legendList);
            redisTemplate2.expire(key_view_all_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    
    /**
     * 未注册用户点击首页换一批次数统计
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("view/unregister")
    @ResponseBody
    public RestResponse getViewUnReg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_view_unregister = "statis:view_unregister_list";
        String key_view_unregister_count = "statis:view_unregister_count";
        if(redisTemplate2.hasKey(key_view_unregister)){
        	statisList = redisTemplate2.opsForList().range(key_view_unregister, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_view_unregister_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"viewDetail", "register"}, new Object[]{0, false});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("0次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("0次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 1, 5);
			pie1 = new PieDataModel();
            pie1.setName("1~5次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("1~5次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 6, 10);
			pie1 = new PieDataModel();
            pie1.setName("6~10次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("6~10次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 11, 15);
			pie1 = new PieDataModel();
            pie1.setName("11~15次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("11~15次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 16, 20);
			pie1 = new PieDataModel();
            pie1.setName("16~20次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("16~20次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{false}, 20, 1000);
			pie1 = new PieDataModel();
            pie1.setName("20次以上");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("20次以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_view_unregister, statisList);
            redisTemplate2.expire(key_view_unregister, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_view_unregister_count, legendList);
            redisTemplate2.expire(key_view_unregister_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户产看房源详情
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("view/register")
    @ResponseBody
    public RestResponse getViewReg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_view_register = "statis:view_register_list";
        String key_view_register_count = "statis:view_register_count";
        if(redisTemplate2.hasKey(key_view_register)){
        	statisList = redisTemplate2.opsForList().range(key_view_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_view_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"viewDetail", "register"}, new Object[]{0, true});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("0次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("0次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 1, 5);
			pie1 = new PieDataModel();
            pie1.setName("1~5次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("1~5次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 6, 10);
			pie1 = new PieDataModel();
            pie1.setName("6~10次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("6~10次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 11, 15);
			pie1 = new PieDataModel();
            pie1.setName("11~15次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("11~15次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 16, 20);
			pie1 = new PieDataModel();
            pie1.setName("16~20次");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("16~20次");
            
    		count = deviceActivateService.countViewByCondition(new String[]{"register"}, new Object[]{true}, 20, 1000);
			pie1 = new PieDataModel();
            pie1.setName("20次以上");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("20次以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_view_register, statisList);
            redisTemplate2.expire(key_view_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_view_register_count, legendList);
            redisTemplate2.expire(key_view_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    
    /**
     * 已注册用户修改首付及条件比例
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("modify/register")
    @ResponseBody
    public RestResponse getModifyReg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        
        String key_modify_regiter = "statis:modify_reg_list";
        String key_modify_register_count = "statis:modify_reg_count";
        if(redisTemplate2.hasKey(key_modify_regiter)){
        	statisList = redisTemplate2.opsForList().range(key_modify_regiter, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_modify_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"modifysf", "modifyother", "register"}, new Object[]{true, true, true});
			PieDataModel pie1 = new PieDataModel();
            pie1.setName("修改首付预算和其他");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("修改首付预算和其他");
            
    		count = deviceActivateService.countByCondition(new String[]{"modifysf", "modifyother", "register"}, new Object[]{true, false, true});
			pie1 = new PieDataModel();
            pie1.setName("只修改首付预算");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("只修改首付预算");
            
    		count = deviceActivateService.countByCondition(new String[]{"modifysf", "modifyother", "register"}, new Object[]{false, true, true});
			pie1 = new PieDataModel();
            pie1.setName("只修改其他条件");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("只修改其他条件");
            
    		count = deviceActivateService.countByCondition(new String[]{"modifysf", "modifyother", "register"}, new Object[]{false, false, true});
			pie1 = new PieDataModel();
            pie1.setName("均未修改");
            pie1.setValue(count);
            statisList.add(pie1);
            legendList.add("均未修改");
        	
            redisTemplate2.opsForList().rightPushAll(key_modify_regiter, statisList);
            redisTemplate2.expire(key_modify_regiter, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_modify_register_count, legendList);
            redisTemplate2.expire(key_modify_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户--首付分布
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("shoufu/register")
    @ResponseBody
    public RestResponse getShoudfuDistribute() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        List<Object> averageList = new ArrayList<Object>();
        String key_shoufu_register = "statis:shoufu_register_list";
        String key_shoufu_register_count = "statis:shoufu_register_count";
        String key_shoufau_average_median = "statis:shoufau_average_median";
        if(redisTemplate2.hasKey(key_shoufu_register)){
        	statisList = redisTemplate2.opsForList().range(key_shoufu_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_shoufu_register_count, 0, -1);
        	averageList = redisTemplate2.opsForList().range(key_shoufau_average_median, 0, -1);
        } else {
    		int count = deviceActivateService.countShoufuByCondition(0, 30);
    		PieDataModel pie1 = new PieDataModel();
    		if (count > 0) {
				pie1.setName("0~30万");
				pie1.setValue(count);
				statisList.add(pie1);
				legendList.add("0~30万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(31, 60);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("31~60万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("31~60万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(61, 90);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("61~90万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("61~90万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(91, 120);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("91~120万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("91~120万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(121, 150);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("121~150万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("121~150万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(151, 180);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("151~180万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("151~180万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(181, 210);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("181~210万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("181~210万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(211, 240);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("211~240万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("211~240万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(241, 270);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("241~270万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("241~270万");
    		}
            
    		count = deviceActivateService.countShoufuByCondition(271, 300);
    		if (count > 0) {
				pie1 = new PieDataModel();
	            pie1.setName("271~300万");
	            pie1.setValue(count);
	            statisList.add(pie1);
	            legendList.add("271~300万");
    		}
        	
    		averageList = userRequirementService.getAverageAndMdian();
            redisTemplate2.opsForList().rightPushAll(key_shoufu_register, statisList);
            redisTemplate2.expire(key_shoufu_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_shoufu_register_count, legendList);
            redisTemplate2.expire(key_shoufu_register_count, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_shoufau_average_median, averageList);
            redisTemplate2.expire(key_shoufau_average_median, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        pvs.put("average_media", averageList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户--户籍和限购
     * @return
     * 2019年11月15日上午11:08:21
     */
    @GetMapping("hujixg/register")
    @ResponseBody
    public RestResponse getHujiXg() {
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList<Object>();
        List<Object> legendList = new ArrayList<Object>();
        String key_huji_register = "statis:huji_register_list";
        String key_huji_register_count = "statis:huji_register_count";
        if(redisTemplate2.hasKey(key_huji_register)){
        	statisList = redisTemplate2.opsForList().range(key_huji_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_huji_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"register", "huji", "buylimit"}, new Object[]{true, 0, false});
    		PieDataModel pie1 = new PieDataModel();
			pie1.setName("深户-不限购");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("深户-不限购");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huji", "buylimit"}, new Object[]{true, 0, true});
    		pie1 = new PieDataModel();
			pie1.setName("深户-限购");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("深户-限购");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huji", "buylimit"}, new Object[]{true, 1, false});
    		pie1 = new PieDataModel();
			pie1.setName("非深户-不限购");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("非深户-不限购");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huji", "buylimit"}, new Object[]{true, 1, true});
    		pie1 = new PieDataModel();
			pie1.setName("非深户-限购");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("非深户-限购");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huji", "buylimit"}, new Object[]{true, 2, false});
    		pie1 = new PieDataModel();
			pie1.setName("未设置-不限购");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("未设置-不限购");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huji", "buylimit"}, new Object[]{true, 2, true});
    		pie1 = new PieDataModel();
			pie1.setName("未设置-限购");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("未设置-限购");
        	
            redisTemplate2.opsForList().rightPushAll(key_huji_register, statisList);
            redisTemplate2.expire(key_huji_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_huji_register_count, legendList);
            redisTemplate2.expire(key_huji_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户-设置办公地点
     * 
     * @return
     * 2019年11月15日下午5:18:55
     */
    @GetMapping("office/register")
    @ResponseBody
    public RestResponse getOffice(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_office_register = "statis:office_register_list";
        String key_office_register_count = "statis:office_register_count";
        if(redisTemplate2.hasKey(key_office_register)){
        	statisList = redisTemplate2.opsForList().range(key_office_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_office_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"register", "officeSel"}, new Object[]{true, true});
    		PieDataModel pie1 = new PieDataModel();
			pie1.setName("设置工作地点");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("设置工作地点");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "officeSel"}, new Object[]{true, false});
    		pie1 = new PieDataModel();
			pie1.setName("未设置工作地点");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("未设置工作地点");
        	
            redisTemplate2.opsForList().rightPushAll(key_office_register, statisList);
            redisTemplate2.expire(key_office_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_office_register_count, legendList);
            redisTemplate2.expire(key_office_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户-设置学位
     * 
     * @return
     * 2019年11月15日下午5:19:17
     */
    @GetMapping("school/register")
    @ResponseBody
    public RestResponse getSchool(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_school_register = "statis:school_register_list";
        String key_school_register_count = "statis:school_register_count";
        if(redisTemplate2.hasKey(key_school_register)){
        	statisList = redisTemplate2.opsForList().range(key_school_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_school_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"register", "schoolSel"}, new Object[]{true, 0});
    		PieDataModel pie1 = new PieDataModel();
			pie1.setName("无学位需求");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("无学位需求");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "schoolSel"}, new Object[]{true, 1});
    		pie1 = new PieDataModel();
			pie1.setName("有学位需求未指定学校");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("有学位需求未指定学校");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "schoolSel"}, new Object[]{true, 2});
			pie1 = new PieDataModel();
			pie1.setName("指定学校");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("指定学校");
        	
            redisTemplate2.opsForList().rightPushAll(key_school_register, statisList);
            redisTemplate2.expire(key_school_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_school_register_count, legendList);
            redisTemplate2.expire(key_school_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户-新房二手房选择
     * 
     * @return
     * 2019年11月15日下午5:19:39
     */
    @GetMapping("xinoresf/register")
    @ResponseBody
    public RestResponse getXinOresf(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_xinoresf_register = "statis:xinoresf_register_list";
        String key_xinoresf_register_count = "statis:xinoresf_register_count";
        if(redisTemplate2.hasKey(key_xinoresf_register)){
        	statisList = redisTemplate2.opsForList().range(key_xinoresf_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_xinoresf_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"register", "xinoresf"}, new Object[]{true, 0});
    		PieDataModel pie1 = new PieDataModel();
			pie1.setName("不限");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("不限");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "xinoresf"}, new Object[]{true, 1});
    		pie1 = new PieDataModel();
			pie1.setName("新房");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("新房");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "xinoresf"}, new Object[]{true, 2});
			pie1 = new PieDataModel();
			pie1.setName("二手房");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("二手房");
        	
            redisTemplate2.opsForList().rightPushAll(key_xinoresf_register, statisList);
            redisTemplate2.expire(key_xinoresf_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_xinoresf_register_count, legendList);
            redisTemplate2.expire(key_xinoresf_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户-户型选择
     * 
     * @return
     * 2019年11月15日下午5:20:01
     */
    @GetMapping("huxing/register")
    @ResponseBody
    public RestResponse getHuxing(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_huxing_register = "statis:huxing_register_list";
        String key_huxing_register_count = "statis:huxing_register_count";
        if(redisTemplate2.hasKey(key_huxing_register)){
        	statisList = redisTemplate2.opsForList().range(key_huxing_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_huxing_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 0});
    		PieDataModel pie1 = new PieDataModel();
			pie1.setName("不限");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("不限");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 1});
    		pie1 = new PieDataModel();
			pie1.setName("一室");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("一室");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 2});
			pie1 = new PieDataModel();
			pie1.setName("二室");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("二室");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 3});
			pie1 = new PieDataModel();
			pie1.setName("三室");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("三室");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 4});
			pie1 = new PieDataModel();
			pie1.setName("四室");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("四室");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 5});
			pie1 = new PieDataModel();
			pie1.setName("五室及以上");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("五室及以上");
        	
            redisTemplate2.opsForList().rightPushAll(key_huxing_register, statisList);
            redisTemplate2.expire(key_huxing_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_huxing_register_count, legendList);
            redisTemplate2.expire(key_huxing_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    /**
     * 已注册用户-房源用途选择
     * 
     * @return
     * 2019年11月15日下午5:20:19
     */
    @GetMapping("useType/register")
    @ResponseBody
    public RestResponse getUseType(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_useType_register = "statis:useType_register_list";
        String key_useType_register_count = "statis:useType_register_count";
        if(redisTemplate2.hasKey(key_useType_register)){
        	statisList = redisTemplate2.opsForList().range(key_useType_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_useType_register_count, 0, -1);
        } else {
    		int count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 0});
    		PieDataModel pie1 = new PieDataModel();
			pie1.setName("不限");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("不限");
			
    		count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 1});
    		pie1 = new PieDataModel();
			pie1.setName("住宅");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("住宅");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 2});
			pie1 = new PieDataModel();
			pie1.setName("公寓");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("公寓");
			
			count = deviceActivateService.countByCondition(new String[]{"register", "huxingSel"}, new Object[]{true, 3});
			pie1 = new PieDataModel();
			pie1.setName("其他");
			pie1.setValue(count);
			statisList.add(pie1);
			legendList.add("其他");
        	
            redisTemplate2.opsForList().rightPushAll(key_useType_register, statisList);
            redisTemplate2.expire(key_useType_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_useType_register_count, legendList);
            redisTemplate2.expire(key_useType_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    
    /**
     * 已注册用户-加密电话点击
     * 
     * @return
     * 2019年11月15日下午5:20:19
     */
    @GetMapping("useAxb/register")
    @ResponseBody
    public RestResponse getUseAxb(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_useAxb_register = "statis:useAxb_register_list";
        String key_useAxb_register_count = "statis:useAxb_register_count";
        if(redisTemplate2.hasKey(key_useAxb_register)){
        	statisList = redisTemplate2.opsForList().range(key_useAxb_register, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_useAxb_register_count, 0, -1);
        } else {
        	for (int i = 0; i < 20; i++) {
        		int count = deviceActivateService.countByCondition(new String[]{"register", "useAxb"}, new Object[]{true, i});
        		if (count > 0) {
            		PieDataModel pie1 = new PieDataModel();
        			pie1.setName(i + "次");
        			pie1.setValue(count);
        			statisList.add(pie1);
        			legendList.add(i + "次");
        		}
        	}
			
            redisTemplate2.opsForList().rightPushAll(key_useAxb_register, statisList);
            redisTemplate2.expire(key_useAxb_register, 24, TimeUnit.HOURS);
            redisTemplate2.opsForList().rightPushAll(key_useAxb_register_count, legendList);
            redisTemplate2.expire(key_useAxb_register_count, 24, TimeUnit.HOURS);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    @GetMapping("device/client")
    @ResponseBody
    public RestResponse getDeviceClient(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_device_client = "statis:device_client_list";
        String key_device_client_count = "statis:device_client_count";
        if(redisTemplate2.hasKey(key_device_client)){
        	statisList = redisTemplate2.opsForList().range(key_device_client, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_device_client_count, 0, -1);
        } else {
        	String[] clientList = new String[]{"Android", "ios", "baidu_mp", "pc", "mobile", "weixin", "toutiao_mp"};
        	for (int i = 0; i < clientList.length; i++) {
        		String client = clientList[i];
        		int count = deviceActivateService.countByCondition(new String[]{"client", "createDate"}, new Object[]{client, DateUtil.format(DateUtil.now())});
        		if (count > 0) {
            		PieDataModel pie1 = new PieDataModel();
        			pie1.setName(client);
        			pie1.setValue(count);
        			statisList.add(pie1);
        			legendList.add(client);
        		}
        	}
			
            redisTemplate2.opsForList().rightPushAll(key_device_client, statisList);
            redisTemplate2.expire(key_device_client, 15, TimeUnit.MINUTES);
            redisTemplate2.opsForList().rightPushAll(key_device_client_count, legendList);
            redisTemplate2.expire(key_device_client, 15, TimeUnit.MINUTES);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
    @GetMapping("device/clientlast")
    @ResponseBody
    public RestResponse getDeviceClientLast(){
        JSONObject pvs = new JSONObject();
        List<Object> statisList = new ArrayList();
        List<Object> legendList = new ArrayList<Object>();
        String key_device_client = "statis:device_client_last_list";
        String key_device_client_count = "statis:device_client_last_count";
        if(redisTemplate2.hasKey(key_device_client)){
        	statisList = redisTemplate2.opsForList().range(key_device_client, 0, -1);
        	legendList = redisTemplate2.opsForList().range(key_device_client_count, 0, -1);
        } else {
        	String[] clientList = new String[]{"Android", "ios", "baidu_mp", "pc", "mobile", "weixin", "toutiao_mp"};
        	for (int i = 0; i < clientList.length; i++) {
        		String client = clientList[i];
        		int count = deviceActivateService.countByCondition(new String[]{"client", "createDate"}, new Object[]{client, DateUtil.format(DateUtil.addDate(DateUtil.now(), -1))});
        		if (count > 0) {
            		PieDataModel pie1 = new PieDataModel();
        			pie1.setName(client);
        			pie1.setValue(count);
        			statisList.add(pie1);
        			legendList.add(client);
        		}
        	}
			
            redisTemplate2.opsForList().rightPushAll(key_device_client, statisList);
            redisTemplate2.expire(key_device_client, 15, TimeUnit.MINUTES);
            redisTemplate2.opsForList().rightPushAll(key_device_client_count, legendList);
            redisTemplate2.expire(key_device_client, 15, TimeUnit.MINUTES);
        }

        pvs.put("legend_data", (String[])legendList.toArray(new String[legendList.size()]));
        pvs.put("series_data", statisList);
        return RestResponse.success().setData(pvs);
    }
    
}
