package com.zfh.app.mongo.service.esf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.MD5Util;
import com.zfh.app.core.BathUpdateOptions;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.fang.service.OSSService;
import com.zfh.app.mongo.entity.esf.Agent;
import com.zfh.app.mongo.service.esf.AgentService;

@Service
public class AgentServiceImpl extends BaseMongoServiceImpl<Agent> implements AgentService{

	private static final Log logger = LogFactory.getLog(AgentServiceImpl.class.getName());
	
	@Autowired
	private OSSService ossService;
	
	@Override
	protected Class<Agent> getEntityClass() {
		// TODO Auto-generated method stub
		return Agent.class;
	}

	@Override
	public void download() {
		// 线程池维护线程的最少数量
		int corePoolSize = 5;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 10;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 4;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int pageNum = 0;
		int pageSize = 1000;
		Page<Agent> page = findUndownload(pageNum, pageSize);
		int totalPage = page.getTotalPages();
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<Agent> photoList = findUndownload(pageIndex, pageSize).getContent();
					upload(photoList);
				}

			}));
			pageNum++;
		}
		threadPool.shutdown();
		
		 // wait for thread all shutdown, after callback 
		 try {
			 while (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
			 // threalPool has not shutdown all
			 }
			 
			 // after threadPool task
			 // 未关联标准小区的，寻找同同房源小区下的房源，看是否有关联了标准小区的
			 ossService.getOSSClient().shutdown();
		 
		 } catch (InterruptedException e) {
			 logger.error(e.getMessage(), e);
		 }
		
	
	}

	@Override
	public void upload(List<Agent> photoList) {
		long start = new Date().getTime();
		List<BathUpdateOptions> options = new ArrayList<>();
		for (int index = 0; index < photoList.size(); index++) {
			Agent agent = photoList.get(index);
			if (StringUtils.isNotEmpty(agent.getDownload())) {
				continue;
			}
			
			String source = agent.getAvatar();
			String md5Source = MD5Util.MD5(source);
			
			String ossDir = "agent/" + DateUtil.format(DateUtil.now(), "yyyyMMdd"), fileName = md5Source + ".jpg";
			String returnUrl =  ossService.uploadByNetUrl(source, ossDir, fileName);
			if (returnUrl != null) {
				options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(agent.getId()))),
						Update.update("status", 1).set("download", returnUrl), false, false));
			}
		}
		// 上传完成后批量更新状态
		super.bathUpdate(options);
		long end = new Date().getTime();
		System.err.println("download agent avatar and upload once spand time: " + (end - start));
		
	}

	@Override
	public Page<Agent> findUndownload(int page, int size) {
		PageRequest pageReq = new PageRequest(page, size);
		return findPage(pageReq, "download", null);
	}

}
