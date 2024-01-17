package com.zfh.app.fang.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.zfh.app.core.util.CityConstant;
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.system.AccessLog;
import com.zfh.app.mongo.entity.system.BillNotifyRecord;
import com.zfh.app.mongo.entity.system.CollectHouse;
import com.zfh.app.mongo.entity.system.Device;
import com.zfh.app.mongo.entity.system.DeviceActivate;
import com.zfh.app.mongo.entity.system.HouseViewHistory;
import com.zfh.app.mongo.entity.system.PrivateNumber;
import com.zfh.app.mongo.entity.system.SearchHistory;
import com.zfh.app.mongo.entity.system.UserAccount;
import com.zfh.app.mongo.entity.system.UserFeedback;
import com.zfh.app.mongo.entity.system.UserPosition;
import com.zfh.app.mongo.entity.system.UserRequirement;
import com.zfh.app.mongo.model.UserAccountModel;
import com.zfh.app.mongo.service.esf.CityService;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseService;
import com.zfh.app.mongo.service.system.AccessLogMongoService;
import com.zfh.app.mongo.service.system.AdConveRecordService;
import com.zfh.app.mongo.service.system.BillNotifyRecordService;
import com.zfh.app.mongo.service.system.CollectHouseService;
import com.zfh.app.mongo.service.system.DeviceAccessService;
import com.zfh.app.mongo.service.system.DeviceActivateService;
import com.zfh.app.mongo.service.system.DeviceService;
import com.zfh.app.mongo.service.system.HouseViewHistoryService;
import com.zfh.app.mongo.service.system.PrivateNumberService;
import com.zfh.app.mongo.service.system.SearchHistoryService;
import com.zfh.app.mongo.service.system.UserAccountService;
import com.zfh.app.mongo.service.system.UserFeedbackService;
import com.zfh.app.mongo.service.system.UserPositionService;
import com.zfh.app.mongo.service.system.UserRequirementService;

import freemarker.template.utility.StringUtil;

/**
 * 用户管理
 * @author CB
 * 
 * @dateTime 2019年10月22日下午2:43:54
 */
@Controller
@RequestMapping("admin/user")
public class UserAccountController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HousePhotoController.class);

	@Resource(name = "redis2Template")
	private RedisTemplate<String, Object> redisTemplate2;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private UserRequirementService userRequirementService;
	
	@Autowired
	private HouseViewHistoryService houseViewHistoryService;
	
	@Autowired
	private AccessLogMongoService accessLogMongoService;
	
	@Autowired
	private ErshoufangService ershoufangService;
	
	@Autowired
	private UserPositionService userPositionService;
	
	@Autowired
	private AdConveRecordService adConveRecordService;
	
	@Autowired
	private DeviceActivateService deviceActivateService;
	
	@Autowired
	private UserFeedbackService userFeedbackService;
	
	@Autowired
	private PrivateNumberService privateNumberService;
	
    @Autowired
    private CollectHouseService collectHouseService;
    
    @Autowired
    private BillNotifyRecordService billNotifyRecordService;
    
    @Autowired
    private SearchHistoryService searchHistoryService;
    
    @Autowired
    private DeviceAccessService deviceAccessService;
    
    @Autowired
    private CityService cityService;
	
    @GetMapping("analyze/list")
    @SysLog("跳转客户统计")
    public String analyzeList(){
        return "admin/user/analyzelist";
    }
    
    @GetMapping("account/list")
    @SysLog("跳转客户信息")
    public String loupanList(Model model){
		model.addAttribute("cityList", cityService.findAll());
        return "admin/user/list";
    }
    
    @GetMapping("feedback/list")
    @SysLog("跳转客户反馈")
    public String feedback(){
        return "admin/user/feedback";
    }
    
    @GetMapping("position/list")
    @SysLog("跳转客户定位")
    public String position(){
        return "admin/user/position";
    }
    
    @GetMapping("devices/list")
    @SysLog("跳转活跃设备")
    public String devicesList(){
        return "admin/user/deviceslist";
    }
    
    @GetMapping("accessLog/list")
    @SysLog("查询接口调用")
    public String accessLog(){
        return "admin/user/accessLog";
    }
    
    @GetMapping("privateNumber/list")
    @SysLog("查询加密电话")
    public String privateNumberList(){
        return "admin/user/privateNumberList";
    }

    @GetMapping("telbill/list")
    @SysLog("查询电话回单")
    public String telbillList(){
        return "admin/user/telbillList";
    }
    
    @GetMapping("searchHistory/list")
    @SysLog("查询电话回单")
    public String searchHistorylList(){
        return "admin/user/searchHistory";
    }
    
//    @RequiresPermissions("admin:user:accountlist")
    @PostMapping("account/list")
    @ResponseBody
    public LayerData<UserAccountModel> loupanList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String phone = (String)map.get("phone");
    	String clientKey = (String)map.get("clientKey");
    	String city = (String) map.get("city");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(phone)) {
    		paramsList.add("phone");
    		valuesList.add(phone);
    	}
    	if (!StringUtils.isEmpty(clientKey)) {
    		paramsList.add("clientKey");
    		valuesList.add(clientKey);
    	}
    	if (!StringUtils.isEmpty(city)) {
    		paramsList.add("city");
    		valuesList.add(city);
    	}
        LayerData<UserAccountModel> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<UserAccount> pageData = userAccountService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(transform(pageData.getContent()));
        return  userLayerData;
    }
    
    @PostMapping("feedback/list")
    @ResponseBody
    public LayerData<UserFeedback> feedback(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");

        LayerData<UserFeedback> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<UserFeedback> pageData = userFeedbackService.findPage(pageRequset);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    @GetMapping("pvs")
    @ResponseBody
    public RestResponse getPV(ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
        JSONObject pvs = new JSONObject();
        List<Object> registerList = new ArrayList();
        List<Object> deviceList = new ArrayList();
        
        String key_register = "chart_register_list";
        String key_device = "chart_device_list";
        if(redisTemplate2.hasKey(key_register)){
        	registerList = redisTemplate2.opsForList().range(key_register, 0, -1);
        } else {
            registerList.add("注册用户");
            int curDay = userAccountService.distinctCount(DateUtil.format(DateUtil.now()));
            registerList.add(curDay);
            int yestDay = userAccountService.distinctCount(DateUtil.format(DateUtil.addDate(DateUtil.now(), -1)));
            registerList.add(yestDay);
            int thisweek = userAccountService.distinctCount(DateUtil.getWeekStart(), DateUtil.getWeekEnd());
            registerList.add(thisweek);
            int lastweek = userAccountService.distinctCount(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekStart()), -7)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekEnd()), -7)));
            registerList.add(lastweek);
            int thismonth = userAccountService.distinctCount(DateUtil.getMonthStart(), DateUtil.getMonthEnd());
            registerList.add(thismonth);
            int lastmonth = userAccountService.distinctCount(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthStart()), -30)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthEnd()), -30)));
            registerList.add(lastmonth);
            redisTemplate2.opsForList().rightPushAll(key_register, registerList);
            redisTemplate2.expire(key_register, 10, TimeUnit.MINUTES);

        }
        if(redisTemplate2.hasKey(key_device)){
        	deviceList = redisTemplate2.opsForList().range(key_device, 0, -1);
        } else {
        	deviceList.add("活跃设备");
            int curDay = deviceAccessService.countBy(DateUtil.format(DateUtil.now()));
            deviceList.add(curDay);
            int yestDay = deviceAccessService.countBy(DateUtil.format(DateUtil.addDate(DateUtil.now(), -1)));
            deviceList.add(yestDay);
            int thisweek = deviceAccessService.countBy(DateUtil.getWeekStart(), DateUtil.getWeekEnd());
            deviceList.add(thisweek);
            int lastweek = deviceAccessService.countBy(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekStart()), -7)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getWeekEnd()), -7)));
            deviceList.add(lastweek);
            int thismonth = deviceAccessService.countBy(DateUtil.getMonthStart(), DateUtil.getMonthEnd());
            deviceList.add(thismonth);
            int lastmonth = deviceAccessService.countBy(DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthStart()), -30)), DateUtil.format(DateUtil.addDate(DateUtil.format(DateUtil.getMonthEnd()), -30)));
            deviceList.add(lastmonth);
            redisTemplate2.opsForList().rightPushAll(key_device, deviceList);
            redisTemplate2.expire(key_device, 10, TimeUnit.MINUTES);
        }
        pvs.put("product", new String[]{"product", "今日", "昨日", "本周", "上周", "本月", "上月"});
        pvs.put("register", registerList);
        pvs.put("device", deviceList);
        return RestResponse.success().setData(pvs);
    }
    
    @GetMapping("devices")
    @ResponseBody
    public RestResponse getDevice(ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	int days = 13;
    	String initDay = (String) map.get("initDay");
    	if (org.apache.commons.lang.StringUtils.isNotEmpty(initDay)) {
    		days = Integer.valueOf(initDay) - 1;
    	}
    	
        JSONObject pvs = new JSONObject();
        List<Object> xAxisList = new ArrayList<Object>();
        List<Object> registerList = new ArrayList<Object>(); // 注册人数
        List<Object> deviceList = new ArrayList<Object>();   // 活跃设备
        List<Object> activateList = new ArrayList<Object>(); // 新增设备
        List<Object> advertActiveList = new ArrayList<Object>();   // 广告转化
        
        if(redisTemplate2.hasKey("redis_key_xAxisList") && StringUtils.isEmpty(initDay)){
        	xAxisList = redisTemplate2.opsForList().range("redis_key_xAxisList", 0, -1);
        	registerList = redisTemplate2.opsForList().range("redis_key_registerList", 0, -1);
        	deviceList = redisTemplate2.opsForList().range("redis_key_deviceList", 0, -1);
        	activateList = redisTemplate2.opsForList().range("redis_key_activateList", 0, -1);
        	advertActiveList = redisTemplate2.opsForList().range("redis_key_advertActiveList", 0, -1);
        } else {
            int count_register = 0;
            int count_device = 0;
            int count_active = 0;
            int count_advert_active = 0;
            String createDate = "";
            Date today = DateUtil.now();
            for (int i = days; i >= 0; i--) {
            	createDate = DateUtil.format(DateUtil.addDate(today, i * (-1)));
            	xAxisList.add(createDate);
            	count_register = userAccountService.distinctCount(createDate);
            	registerList.add(count_register);
            	count_device = deviceAccessService.countBy(createDate);
            	deviceList.add(count_device);
            	count_active = deviceActivateService.distinctCount(createDate);
            	activateList.add(count_active);
            	count_advert_active = adConveRecordService.distinctCount(createDate, 1);
            	advertActiveList.add(count_advert_active);
            }
            if (days == 13) {
                redisTemplate2.opsForList().rightPushAll("redis_key_xAxisList", xAxisList);
                redisTemplate2.opsForList().rightPushAll("redis_key_registerList", registerList);
                redisTemplate2.opsForList().rightPushAll("redis_key_deviceList", deviceList);
                redisTemplate2.opsForList().rightPushAll("redis_key_activateList", activateList);
                redisTemplate2.opsForList().rightPushAll("redis_key_advertActiveList", advertActiveList);
                redisTemplate2.expire("redis_key_xAxisList", 10, TimeUnit.MINUTES);
                redisTemplate2.expire("redis_key_registerList", 10, TimeUnit.MINUTES);
                redisTemplate2.expire("redis_key_deviceList", 10, TimeUnit.MINUTES);
                redisTemplate2.expire("redis_key_activateList", 10, TimeUnit.MINUTES);
                redisTemplate2.expire("redis_key_advertActiveList", 10, TimeUnit.MINUTES);
            }
        }

        pvs.put("xAxis", xAxisList);
        pvs.put("register", registerList);
        pvs.put("device", deviceList);
        pvs.put("activate", activateList);
        pvs.put("advertActive", advertActiveList);
        return RestResponse.success().setData(pvs);
    } 
    
    public List<UserAccountModel> transform(List<UserAccount> pageData) {
    	List<UserAccountModel> newlist = new ArrayList<UserAccountModel>();
    	for (UserAccount userAccount : pageData) {
    		UserAccountModel userAccountModel = new UserAccountModel();
    		BeanUtils.copyProperties(userAccount, userAccountModel);
    		
    		if (org.apache.commons.lang3.StringUtils.isEmpty(userAccountModel.getCity()) &&
    				org.apache.commons.lang3.StringUtils.isNotEmpty(userAccountModel.getTelCity())) {
    			userAccountModel.setCity(userAccountModel.getTelCity());
    		}
    		Device device = deviceService.uniqueByProp("clientKey", userAccount.getClientKey());
    		if (device != null) {
    			userAccountModel.setIdfa(device.getIdfa());
    			userAccountModel.setImei(device.getImei());
    		}
    		
    		UserRequirement userReq = userRequirementService.getLast(userAccount.getId());
    		if (userReq != null) {
    			userAccountModel.setDownPayment(userReq.getDownPayment());
    			userAccountModel.setDownRatio(userReq.getDownRatio() + "成");
    			if (userReq.getRegions() != null) {
    				userAccountModel.setOfficeAddress(userReq.getRegions().getDistrict() + "-" + 
    						userReq.getRegions().getBlockName() + "-" + userReq.getOfficeAddress());
    			} else {
    				userAccountModel.setOfficeAddress(userReq.getOfficeAddress());
    			}
    			userAccountModel.setBuylimit(userReq.isBuylimit());
    			userAccountModel.setSchoolName(userReq.getSchoolName());
    			if ("0".equals(userReq.getXinOresf()) || StringUtils.isEmpty(userReq.getXinOresf())) {
    				userAccountModel.setXinOresf("不限");
    			} else if ("1".equals(userReq.getXinOresf())) {
    				userAccountModel.setXinOresf("新房");
    			} else if ("2".equals(userReq.getXinOresf())) {
    				userAccountModel.setXinOresf("二手房");
    			}
    			if ("0".equals(userReq.getHuxing()) || StringUtils.isEmpty(userReq.getHuxing())) {
    				userAccountModel.setHuxing("不限");
    			} else if ("1".equals(userReq.getHuxing())) {
    				userAccountModel.setHuxing("一室");
    			} else if ("2".equals(userReq.getHuxing())) {
    				userAccountModel.setHuxing("二室");
    			} else if ("3".equals(userReq.getHuxing())) {
    				userAccountModel.setHuxing("三室");
    			} else if ("4".equals(userReq.getHuxing())) {
    				userAccountModel.setHuxing("四室");
    			} else if ("5".equals(userReq.getHuxing())) {
    				userAccountModel.setHuxing("五室及以上");
    			} 
    			if ("0".equals(userReq.getUseType()) || StringUtils.isEmpty(userReq.getUseType())) {
    				userAccountModel.setUseType("不限");
    			} else if ("1".equals(userReq.getUseType())) {
    				userAccountModel.setUseType("住宅");
    			} else if ("2".equals(userReq.getUseType())) {
    				userAccountModel.setUseType("公寓");
    			} else {
    				userAccountModel.setUseType("其他");
    			}
    		}
    		
    		UserPosition userPosition = userPositionService.getLastByClientKey(userAccount.getClientKey());
    		if (userPosition != null) {
    			userAccountModel.setLocation(userPosition.getAddress());
    		}
    		
    		/**
    		 * 已经没用了
    		 */
//    		String site = adConveRecordService.getByMac1(DigestUtils.md5Hex(userAccount.getClientKey()));
//    		if (site != null) {
//    			userAccountModel.setCsite(site);
//    		}
//    		
//    		if (org.apache.commons.lang3.StringUtils.equals("抖音广告_租房", userAccountModel.getClient())) {
//    			userAccountModel.setCsite("抖音广告_租房");
//    			userAccountModel.setLastLogin(userAccount.getCreateTime());
//    		}
    		newlist.add(userAccountModel);
		}
    	return newlist;
    }
    
    @GetMapping("view/list")
    @SysLog("跳转客户浏览记录")
    public String houseview(){
        return "admin/user/viewlist";
    }

//    @RequiresPermissions("admin:user:viewlist")
    @PostMapping("view/list")
    @ResponseBody
    public LayerData<HouseViewHistory> viewList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String clientKey = (String)map.get("clientKey");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(clientKey)) {
    		paramsList.add("clientKey");
    		valuesList.add(clientKey);
    	}
        LayerData<HouseViewHistory> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "createTime"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<HouseViewHistory> pageData = houseViewHistoryService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        for (HouseViewHistory houseViewHistory : pageData.getContent()) {
        	Ershoufang esf = ershoufangService.getById(houseViewHistory.getHouseId());
        	if (esf != null) {
        		houseViewHistory.setOnline(esf.isOnline());
        	}
        	if (StringUtils.isEmpty(houseViewHistory.getUrl()) && esf != null) {
            	houseViewHistory.setUrl(esf.getUrl());
        	}
			if (!StringUtils.isEmpty(houseViewHistory.getUserId())) {
				UserAccount ua = userAccountService.getById(houseViewHistory.getUserId());
				if (ua != null) {
					houseViewHistory.setPhone(ua.getPhone());
				}
			} else {
				if (!StringUtils.isEmpty(houseViewHistory.getClientKey())) {
					UserAccount ua = userAccountService.uniqueByProp("clientKey", houseViewHistory.getClientKey());
					if (ua != null) {
						houseViewHistory.setPhone(ua.getPhone());
					}
				} 
			}
			houseViewHistoryService.save(houseViewHistory);
			// Android app 开发人员是个SB，用户浏览一次，调用了两遍接口
			if (houseViewHistory.getClient().equals("Android")) {
				houseViewHistory.setViewCount(houseViewHistory.getViewCount() / 2);
			}
			if (houseViewHistory.getViewCount() == 0) {
				houseViewHistory.setViewCount(1);
			}
			
		}
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    @GetMapping("collect/list")
    @SysLog("跳转客户浏览记录")
    public String collectList(){
        return "admin/user/collectlist";
    }
    
    @PostMapping("collect/list")
    @ResponseBody
    public LayerData<CollectHouse> collectList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String clientKey = (String)map.get("clientKey");
    	String online = (String)map.get("online");
    	
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(online)) {
    		paramsList.add("online");
    		if (online.equals("0")) {
        		valuesList.add(false);
    		} else {
    			valuesList.add(true);
    		}
    	}
    	if (!StringUtils.isEmpty(clientKey)) {
    		paramsList.add("clientKey");
    		valuesList.add(clientKey);
    	}
        LayerData<CollectHouse> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<CollectHouse> pageData = collectHouseService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        for (CollectHouse collectHouse : pageData.getContent()) {
        	Ershoufang ershoufang = ershoufangService.getById(collectHouse.getHouseId());
			if (ershoufang != null) {
				collectHouse.setUrl(ershoufang.getUrl());
			}
		}
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    @GetMapping("positions")
    @ResponseBody
    public RestResponse getPositions(ServletRequest request){
        JSONObject pvs = new JSONObject();
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String register = (String)map.get("register");
    	String clientKey = (String)map.get("clientKey");
    	String createDate = (String)map.get("createDate");
    	List<Object> positions = new ArrayList<Object>();
        List<Object> officeAddress = new ArrayList<Object>();

        if (!StringUtils.isEmpty(createDate)) {
			List<UserPosition> positionList = userPositionService.findByProp("dateString", createDate);
            for (UserPosition userPosition : positionList) {
    			if (!StringUtils.isEmpty(userPosition.getUserId()) && !StringUtils.isEmpty(userPosition.getLonglat())) {
    				positions.add(userPosition.getLonglat());
    			}
    		}
            
            pvs.put("positions", positions);
            pvs.put("offices", officeAddress);
            return RestResponse.success().setData(pvs);
        }
        // 查询某个用户的位置
        if (!StringUtils.isEmpty(clientKey)) {
			List<UserPosition> positionList = userPositionService.findByProp("clientKey", clientKey);
            for (UserPosition userPosition : positionList) {
    			if (!StringUtils.isEmpty(userPosition.getUserId()) && !StringUtils.isEmpty(userPosition.getLonglat())) {
    				positions.add(userPosition.getLonglat());
    			}
    		}
            
            List<UserRequirement> requirementsList = userRequirementService.findByProp("clientKey", clientKey);
            for (UserRequirement userPosition : requirementsList) {
    			if (!StringUtils.isEmpty(userPosition.getLonglat())) {
    				officeAddress.add(userPosition.getLonglat());
    			}
    		}
            pvs.put("positions", positions);
            pvs.put("offices", officeAddress);
            return RestResponse.success().setData(pvs);
        } // return 结束
        
    	if (StringUtils.isEmpty(register)) {
            String redis_keys_positions = "redis_key_positions";
            if(redisTemplate2.hasKey(redis_keys_positions)){
            	positions = redisTemplate2.opsForList().range(redis_keys_positions, 0, -1);
            } else {
                List<UserPosition> positionList = userPositionService.findAll();
                for (UserPosition userPosition : positionList) {
        			if (!StringUtils.isEmpty(userPosition.getLonglat())) {
        				positions.add(userPosition.getLonglat());
        			}
        		}
                redisTemplate2.opsForList().rightPushAll(redis_keys_positions, positions);
                redisTemplate2.expire(redis_keys_positions, 12, TimeUnit.HOURS);
            }
            
            String redis_key_office = "redis_key_officeAddress";
            if(redisTemplate2.hasKey(redis_key_office)){
            	officeAddress = redisTemplate2.opsForList().range(redis_key_office, 0, -1);
            } else {
                List<UserRequirement> positionList = userRequirementService.findAll();
                for (UserRequirement userPosition : positionList) {
        			if (!StringUtils.isEmpty(userPosition.getLonglat())) {
        				officeAddress.add(userPosition.getLonglat());
        			}
        		}
                redisTemplate2.opsForList().rightPushAll(redis_key_office, officeAddress);
                redisTemplate2.expire(redis_key_office, 12, TimeUnit.HOURS);
            }
            pvs.put("positions", positions);
            pvs.put("offices", officeAddress);
    	} else {
    		if ("1".equals(register)) {
    			// 注册
    			List<UserPosition> positionList = userPositionService.findAll();
                for (UserPosition userPosition : positionList) {
        			if (!StringUtils.isEmpty(userPosition.getUserId()) && !StringUtils.isEmpty(userPosition.getLonglat())) {
        				positions.add(userPosition.getLonglat());
        			}
        		}
                
                List<UserRequirement> requirementsList = userRequirementService.findAll();
                for (UserRequirement userPosition : requirementsList) {
        			if (!StringUtils.isEmpty(userPosition.getLonglat())) {
        				officeAddress.add(userPosition.getLonglat());
        			}
        		}
    		} else {
    			// 未注册
    			List<UserPosition> positionList = userPositionService.findAll();
                for (UserPosition userPosition : positionList) {
        			if (StringUtils.isEmpty(userPosition.getUserId()) && !StringUtils.isEmpty(userPosition.getLonglat())) {
        				positions.add(userPosition.getLonglat());
        			}
        		}
    		}
            pvs.put("positions", positions);
            pvs.put("offices", officeAddress);
    	}
        return RestResponse.success().setData(pvs);
    }
    
    @PostMapping("devicesList")
    @ResponseBody
    public LayerData<DeviceActivate> devicesList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String clientKey = (String)map.get("clientKey");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(clientKey)) {
    		paramsList.add("clientKey");
    		valuesList.add(clientKey);
    	}
        LayerData<DeviceActivate> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<DeviceActivate> pageData = deviceActivateService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    @PostMapping("accessLog/query")
    @ResponseBody
    public LayerData<AccessLog> queryAccessLog(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String title = (String)map.get("title");
    	String clientKey = (String)map.get("clientKey");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(title)) {
    		paramsList.add("title");
    		valuesList.add(title);
    	}
    	if (!StringUtils.isEmpty(clientKey)) {
    		paramsList.add("clientKey");
    		valuesList.add(clientKey);
    	}
        LayerData<AccessLog> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<AccessLog> pageData = accessLogMongoService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
    
    @PostMapping("privateNumber/query")
    @ResponseBody
    public LayerData<PrivateNumber> queryPrivateNumber(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String callerNum = (String)map.get("callerNum");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(callerNum)) {
    		paramsList.add("callerNum");
    		valuesList.add("+86" + callerNum);
    	}

        LayerData<PrivateNumber> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<PrivateNumber> pageData = privateNumberService.findPage(pageRequset, 
        		(String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        List<PrivateNumber> list = new ArrayList<>();
        for (PrivateNumber privateNumber : pageData.getContent()) {
			// 查询房源信息
        	Ershoufang ershoufang  = ershoufangService.getById(privateNumber.getHouseId());
        	if (ershoufang != null) {
        		privateNumber.setUrl(ershoufang.getUrl());
        	}
        	list.add(privateNumber);
		}
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(list);
        return  userLayerData;
    }
    
    @PostMapping("telbill/query")
    @ResponseBody
    public LayerData<BillNotifyRecord> queryTelBill(@RequestParam(value = "page",defaultValue = "1")Integer page,
    		@RequestParam(value = "limit",defaultValue = "10")Integer limit,
    		ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String callerNum = (String)map.get("callerNum");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();
    	if (!StringUtils.isEmpty(callerNum)) {
    		paramsList.add("callerNum");
    		valuesList.add("+86" + callerNum);
    	}
    	
    	LayerData<BillNotifyRecord> userLayerData = new LayerData<>();
    	List<Order> orders = new ArrayList<Order>();
    	orders.add(new Order(Direction.DESC, "_id"));
    	PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
    	Page<BillNotifyRecord> pageData = billNotifyRecordService.findPage(pageRequset, 
    			(String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
    	userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
    	userLayerData.setData(pageData.getContent());
    	return  userLayerData;
    }
    
    @PostMapping("searchHistory/query")
    @ResponseBody
    public LayerData<SearchHistory> searchHistory(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
    	Map<?, ?> map = WebUtils.getParametersStartingWith(request, "s_");
    	String clientKey = (String)map.get("clientKey");
    	List paramsList = new ArrayList<>();
    	List valuesList = new ArrayList<>();

    	if (!StringUtils.isEmpty(clientKey)) {
    		paramsList.add("clientKey");
    		valuesList.add(clientKey);
    	}
        LayerData<SearchHistory> userLayerData = new LayerData<>();
        List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Direction.DESC, "_id"));
		PageRequest pageRequset = new PageRequest(page - 1, limit, new Sort(orders));
        Page<SearchHistory> pageData = searchHistoryService.findPage(pageRequset, (String[])paramsList.toArray(new String[paramsList.size()]), valuesList.toArray(new String[valuesList.size()]), null);
        userLayerData.setCount(Math.toIntExact(pageData.getTotalElements()));
        userLayerData.setData(pageData.getContent());
        return  userLayerData;
    }
}
