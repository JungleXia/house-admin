package com.mysiteforme.admin.util.quartz.task;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.BlogArticle;
import com.mysiteforme.admin.service.BlogArticleService;
import com.mysiteforme.admin.util.DateUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.zfh.app.mongo.entity.system.PrivateNumber;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.StandCommunityService;
import com.zfh.app.mongo.service.system.BillNotifyRecordService;
import com.zfh.app.mongo.service.system.CollectHouseService;
import com.zfh.app.mongo.service.system.DeviceAccessService;
import com.zfh.app.mongo.service.system.DeviceActivateService;
import com.zfh.app.mongo.service.system.PrivateNumberService;
import com.zfh.app.mongo.service.system.UserAccountService;
import com.zfh.app.mongo.service.system.impl.AXBInterfaceDemoImpl;

/**
 * Created by wangl on 2018/1/26.
 * todo: 系统定时任务
 */
@Component("systemTask")
public class SystemTask {
    private static Log log = LogFactory.get();

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

	@Resource(name = "redis2Template")
	private RedisTemplate<String, Object> redisTemplate2;

    @Autowired
    private BlogArticleService blogArticleService;
    
    @Autowired
    private DeviceActivateService deviceActivateService;
    
    @Autowired
    private ErshoufangService ershoufangService;
    
    @Autowired
    private CollectHouseService collectHouseService;
    
    @Autowired
    private BillNotifyRecordService billNotifyRecordService;
    
    @Autowired
    private DeviceAccessService deviceAccessService;
    
    @Autowired
    private UserAccountService userAccountService;
    
    @Autowired
    private PrivateNumberService privateNumberService;
    
    @Autowired
    private StandCommunityService standCommunityService;

    /**
     * 同步文章点击量
     * @param params
     */
    public void  synchronizationArticleView(String params){
        ValueOperations<String, Object> operations=redisTemplate.opsForValue();
        EntityWrapper<BlogArticle> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        List<BlogArticle> list = blogArticleService.selectList(wrapper);
        for (BlogArticle article : list){
            String key = "article_click_id_"+article.getId();
            if(redisTemplate.hasKey(key)){
                Integer count = (Integer)operations.get(key);
                if(count > 0){
                    article.setClick(blogArticleService.getArticleClick(article.getId()));
                    if(StringUtils.isNotBlank(params)){
                        article.setUpdateId(Long.valueOf(params));
                    }
                    blogArticleService.updateById(article);
                }
            }
        }
    }

    /**
     * 生成文章搜索索引
     */
    public void createArticleIndex(String params) {
        blogArticleService.createArticlIndex();
    }

    /**
     * 从接口调用记录补充激活设备
     * 
     * 2019年11月14日上午11:13:22
     */
    public void refreshDeviceActive(String params) {
    	log.info("开始任务--从接口调用记录补充激活设备");
    	deviceActivateService.replenishFromAccessLog(DateUtil.format(DateUtils.addMinutes(DateUtil.now(), -60)), DateUtil.format(DateUtil.now()));

    	log.info("结束任务--从接口调用记录补充激活设备");
    }
    
    /**
     * 统计用户数据
     * @param params
     * 2019年11月14日下午6:04:34
     */
    public void statistics(String params) {
    	log.info("开始任务--统计用户数据");
    	deviceActivateService.statistics(DateUtil.format(DateUtils.addDays(DateUtil.now(), -1)), DateUtil.format(DateUtil.now()));
		Set<String> keys = redisTemplate2.keys("statis:"+"*");
		if (!CollectionUtils.isEmpty(keys)) {
		    redisTemplate2.delete(keys);
		    log.info("删除之前的keys--统计用户数据");
		}
    	log.info("结束任务--统计用户数据");
    }
    
    /**
     * 记录每日登录设备
     * @param params
     * 2020年6月11日下午3:35:02
     */
    public void createDeviceAccess(String params) {
    	log.info("开始任务--统记录每日登录设备");
		String createDate = DateUtil.format(DateUtil.now());
		deviceAccessService.createDeviceAccess(createDate);
    	log.info("结束任务--记录每日登录设备");
    }
    
    /**
     * 刷新用戶分析數據
     * @param params
     * 2019年11月18日下午2:54:58
     */
    public void refreshClient(String params) {
    	deviceActivateService.refreshUserAccount();
    }
    
    /**
     * 下架过期的房源
     * @param params
     * 2019年11月21日上午10:10:53
     */
    public void expiredErshoufang(String params) {
    	ershoufangService.expiredFromRedis();
    }
    
    /**
     * 下架心仪房源
     * @param params
     * 2020年3月24日下午12:51:02
     */
    public void expiredCollectHouse(String params) {
    	log.info("开始任务-收藏房源修改过期状态");
    	collectHouseService.expiredAll();
    	log.info("结束任务-收藏房源修改过期状态");
    }
    
    /**
     * 下载华为隐私号通话录音文件
     * @param params
     * 2020年3月24日下午12:52:46
     */
    public void downloadSoundRecord(String params) {
    	log.info("开始任务-下载华为隐私号通话录音文件");
    	billNotifyRecordService.downloadLinks();
    	log.info("结束任务-下载华为隐私号通话录音文件");
    }
    
    /**
     * 查询注册用户ip，获取所在市
     * @param params
     * 2020年7月9日下午4:47:23
     */
    public void searchUserId(String params) {
    	log.info("开始任务-查询注册用户ip");
    	userAccountService.searchUserIp();
    	log.info("开始任务-查询注册用户ip");
    }
    
	public void expiredPrivateNumber(String params) {
		log.info("开始定时任务，解除绑定过期的隐私号");
		try {
			int pageSize = 100;
			Page<PrivateNumber> page = privateNumberService.findPage(new PageRequest(0, pageSize), "expired", false);
			int totalPage = page.getTotalPages();
			while (totalPage > 0) {
				int pageIndex = totalPage - 1;
				page = privateNumberService.findPage(new PageRequest(pageIndex, pageSize), "expired", false);
				for (PrivateNumber privateNumber : page.getContent()) {
					Date expiredTime = DateUtil.format(privateNumber.getExpiredTime());
					if (expiredTime.before(DateUtil.now())) {
						privateNumber.setExpired(true);
						privateNumberService.save(privateNumber);
						
						// 解除绑定
						AXBInterfaceDemoImpl.getInstance().axbUnbindNumber(privateNumber.getSubscriptionId(), null);
					}
				}
				totalPage = totalPage - 1;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		log.info("结束定时任务，解除绑定过期的隐私号");
	}
	
	/**
	 * 计算标准小区均价
	 */
	public void countStandCommunityPrice(String params) {
		log.info("开始定时任务，计算标准小区均价");
		standCommunityService.analyzePriceTask();
		log.info("结束定时任务，计算标准小区均价");
		
	}
}
