package com.zfh.app.mongo.service.esf.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.BathUpdateOptions;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.entity.esf.HouseCode;
import com.zfh.app.mongo.entity.esf.HouseOffline;
import com.zfh.app.mongo.entity.esf.HousePhoto;
import com.zfh.app.mongo.service.esf.ErshoufangService;
import com.zfh.app.mongo.service.esf.HouseCodeMongoService;
import com.zfh.app.mongo.service.esf.HouseCodeUnFindService;
import com.zfh.app.mongo.service.esf.HouseOfflineService;
import com.zfh.app.mongo.service.esf.HousePhotoService;
import com.zfh.app.mongo.service.esf.HouseService;

@Service("ershoufangService")
public class ErshoufangServiceImpl extends BaseMongoServiceImpl<Ershoufang> implements ErshoufangService {

	private static final Log logger = LogFactory.getLog(ErshoufangServiceImpl.class.getName());
	
	@Resource(name = "redis3Template")
	private RedisTemplate<String, Object> redisTemplate3;
	
	@Autowired
	HouseCodeUnFindService houseCodeUnFindService;
	
	@Autowired
	HousePhotoService housePhotoService;
	
	@Autowired
	HouseCodeMongoService houseCodeService;
	
	@Autowired
	HouseService houseService;
	
	@Autowired
	HouseOfflineService houseOfflineService;

	@Override
	protected Class<Ershoufang> getEntityClass() {
		// TODO Auto-generated method stub
		return Ershoufang.class;
	}

	/**
	 * <p>
	 * 查询无效的
	 * </p>
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return 2019年5月22日上午10:17:31
	 */
	public Page<Ershoufang> findUnVerify(int pageNum, int pageSize) {
		return findPage(new PageRequest(pageNum, pageSize), new String[] { "houseCode", "useType"}, null);
	}

	@Override
	public void mulitVerify() {

		// 线程池维护线程的最少数量
		int corePoolSize = 10;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 20;
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
		Page<Ershoufang> page = findUnVerify(pageNum, pageSize);
		int totalPage = page.getTotalPages();
		logger.info("-> totalSize: " + (totalPage * pageSize));
		final int tota = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("当前页，倒数：" + pageIndex + " / " + tota);
					// 只获取部分字段 houseCode、useType
					List<Ershoufang> houseList = findUnVerify(pageIndex, pageSize).getContent();
					// 执行同步
					doVerify(houseList);
				}

			}));
		}
		threadPool.shutdown();
		
		 // wait for thread all shutdown, after callback 
//		 try {
//			 while (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
//			 // threalPool has not shutdown all
//			 }
//			 
//			 // after threadPool task
//			 // 未关联标准小区的，寻找同房源小区下的房源，看是否有关联了标准小区的
////			 afterExecutor();
//		 
//		 } catch (InterruptedException e) {
//			 logger.error(e);
//		 }
	}

	public void doVerify(List<Ershoufang> list) {
		List<BathUpdateOptions> options = new ArrayList<>();
		for (Ershoufang ershoufang : list) {
			String houseCode = ershoufang.getHouseCode();
			if (StringUtils.isNotEmpty(houseCode)) {
				houseCode = houseCode.trim();
				HouseCode hcode = houseCodeService.getByHouseCode(houseCode);
				
				if (hcode != null) {
					ershoufang.setVerified(true);
					ershoufang.setVerifyStatus(hcode.isVerifyStatus());
					ershoufang.setCodeStatus(hcode.isCodeStatus());
					ershoufang.setOldUseType(ershoufang.getUseType());
					ershoufang.setUseType(hcode.getTargetType());
					ershoufang.setStandId(hcode.getStandId());
					ershoufang.setStandName(hcode.getStandName());
					ershoufang.setBaseId(hcode.getBaseId());
					ershoufang.setBaseName(hcode.getBaseName());
					
					options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ershoufang.getId()))),
							Update.update("verifyStatus", ershoufang.isVerifyStatus())
							.set("verified", ershoufang.isVerified())
							.set("codeStatus", ershoufang.isCodeStatus())
							.set("useType", ershoufang.getUseType())
							.set("standId", ershoufang.getStandId())
							.set("standName", ershoufang.getStandName())
							.set("baseId", ershoufang.getBaseId())
							.set("baseName", ershoufang.getBaseName())
							.set("useType", ershoufang.getUseType())
							.set("oldUseType", ershoufang.getOldUseType())
							.set("status", 0),
							false,
							false));
				} else {
					// 房源编码未找到
					houseCodeUnFindService.saveUnFind(houseCode, ershoufang.getId());
				}
			} else {
				// 无效的房源
				options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ershoufang.getId()))),
						Update.update("verifyStatus", ershoufang.isVerifyStatus())
						.set("verified", false)
						.set("codeStatus", false),
						false,
						false));
			}
		}
		if (options != null && options.size() > 0)
			super.bathUpdate(options);
	}

	public void updateStatus(Ershoufang ershoufang) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(new ObjectId(ershoufang.getId())));
		Update update = Update.update("verifyStatus", ershoufang.isVerifyStatus())
				.set("verified", ershoufang.isVerified())
				.set("codeStatus", ershoufang.isCodeStatus())
				.set("useType", ershoufang.getUseType())
				.set("standId", ershoufang.getStandId())
				.set("standName", ershoufang.getStandName());
		getMongoTemplate().updateFirst(query, update, getEntityClass());
	}
	
	private void afterExecutor() {
		// after verify code and bind standard community, reset the houses that standId is null by the same house 
		logger.info("set the same house that standId is null");
		int pageSize = 1000;
		Page<Ershoufang> page = findStandIdIsNull(0, pageSize);
		int totalPage = page.getTotalPages();
		while(totalPage > 0) {
			totalPage = totalPage -1;
			logger.info("--> " + totalPage);
			List<Ershoufang> houseList = findStandIdIsNull(totalPage, pageSize).getContent();
			for (Ershoufang esf : houseList) {
				Ershoufang refHouse = uniqueByProps(new String[]{"oldUseType", "community.md5Url"}, new Object[]{esf.getOldUseType(), esf.getCommunity().getMd5Url()});
				if (refHouse != null) {
					esf.setStandId(refHouse.getStandId());
					esf.setStandName(refHouse.getStandName());
					esf.setUseType(refHouse.getUseType());
					esf.setOldUseType(refHouse.getOldUseType());
					super.save(esf);
				}
			}

		}
		logger.info("End set the same house that standId is null");
	}
	
	private Page<Ershoufang> findStandIdIsNull(int pageNum, int pageSize) {
		return findPage(new PageRequest(pageNum, pageSize), "standId", null);
	}

	@Override
	public Page<Ershoufang> findReal(int pageNum, int pageSize) {
		return findPage(new PageRequest(pageNum, pageSize), new String[]{"verifyStatus"}, new Object[]{true}, null, new String[]{"extension", "photos", "loadInfo"});
	}

	@Override
	public List<Ershoufang> findFieldsByIds(Object[] ids, String[] fields) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").in(ids));
		if (fields != null && fields.length > 0) {
			for (String field : fields) {
				query.fields().include(field);
			}
		}
		return getMongoTemplate().find(query, getEntityClass());
	}

	@Override
	public List<Object> getDistinctRoom(String standId) {
		DBObject query = new BasicDBObject();
		query.put("standId", standId);
		return getMongoTemplate().getCollection(getMongoTemplate().getCollectionName(getEntityClass())).distinct("room", query);
	}

	@Override
	public BigDecimal getAveragePrice(String standId, int room) {
		Aggregation agg2 = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("standId").is(standId).and("room").is(room)),
				Aggregation.group("_id").avg("unitPrice").as("averagePrice"));

		AggregationResults<Ershoufang> results = mongoTemplate.aggregate(agg2,
				mongoTemplate.getCollectionName(getEntityClass()), Ershoufang.class);
		List<Ershoufang> esfList = results.getMappedResults();
		if (esfList != null && esfList.size() > 0) {
			return esfList.get(0).getAveragePrice();
		}
		return BigDecimal.ZERO;
	}

	@Override
	public void replacePicTask() {
		// 线程池维护线程的最少数量
		int corePoolSize = 10;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 20;
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
		Page<Ershoufang> page = findPage(new PageRequest(0, pageSize));
		int totalPage = page.getTotalPages();
		logger.info("-> totalSize: " + (totalPage * pageSize));
		final int tota = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("替换封面图片：" + pageIndex + " / " + tota);
					// 只获取部分字段 picUrl、dataFrom
					List<Ershoufang> houseList = findPage(new PageRequest(pageIndex, pageSize), new String[]{"picUrl", "dataFrom"}, null).getContent();
					// 执行同步
					covertPicList(houseList);
				}

			}));
		}
		threadPool.shutdown();
		logger.info("替换封面图片结束");
	 // wait for thread all shutdown, after callback 
//		 try {
//			 while (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
//			 // threalPool has not shutdown all
//			 }
//			 
//			 // after threadPool task
//		 
//		 } catch (InterruptedException e) {
//			 logger.error(e);
//		 }
	}
	
	public void covertPicList(List<Ershoufang> esfList) {
		List<BathUpdateOptions> options = new ArrayList<>();
//		File f1 = new File("F://bad_img.txt");//传入文件/目录的路径
//        
//        PrintWriter printWriter = null;
//		try {
//			printWriter = new PrintWriter(new FileWriter(f1,true),true);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//第二个参数为true，从文件末尾写入 为false则从开头写入

		for (Ershoufang ershoufang : esfList) {
			String url = ershoufang.getPicUrl().replace("?x-oss-process=style/resize_50", "");
			String image = getImage(url);
			if (image == null) {
				// 图片已损坏
				HousePhoto housePhoto = housePhotoService.uniqueByProp("target", url);
				if (housePhoto != null) {
					System.err.println("图片已损坏：" + housePhoto.getSource());
//			        printWriter.println(housePhoto.getSource());
				} 
		       
				String ossPic = housePhotoService.findOSSUrl(url, ershoufang.getDataFrom());
				if (StringUtils.isNotEmpty(ossPic)) {
					options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ershoufang.getId()))),
							Update.update("picUrl", housePhoto.getSource())
							.set("coverPic", false),
							false,
							false));
				} 
			} 
//			String ossPic = housePhotoService.findOSSUrl(ershoufang.getPicUrl(), ershoufang.getDataFrom());
//			if (StringUtils.isNotEmpty(ossPic)) {
//				options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(ershoufang.getId()))),
//						Update.update("picUrl", ossPic)
//						.set("coverPic", true),
//						false,
//						false));
//			} 
		}
//		printWriter.close();//记得关闭输入流
		if (options != null && options.size() > 0)
			super.bathUpdate(options);
	}
	
	public static String getImage(String url) {
		InputStream inputStream = null;
		BufferedImage sourceImg;
		int picWidth = 0;
		try {
			try {
				URL urlOpen = new URL(url);
				HttpURLConnection httpconn = (HttpURLConnection) urlOpen.openConnection();
				httpconn.setConnectTimeout(5000);
				httpconn.setReadTimeout(5000);
				inputStream = urlOpen.openStream();
				if (inputStream == null) {
					return null;
				}
				sourceImg = ImageIO.read(inputStream);
				if (sourceImg == null) {
					return null;
				}
				picWidth = sourceImg.getWidth(); // 确保图片是正确的（正确的图片可以取得宽度）
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 最后一定要关闭IO流
		}
		return String.valueOf(picWidth);

	}
	
	@Override
	public Page<Ershoufang> findlteVersionDate(PageRequest pageReq, String versionDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("online").is(false).and("versionDate").lt(versionDate));
		query.with(pageReq);
		query.fields().include("versionDate");
		Long total = mongoTemplate.count(query, getEntityClass());
		List<Ershoufang> list = mongoTemplate.find(query, getEntityClass());

		return new PageImpl<Ershoufang>(list, pageReq, total);
	}

	@Override
	public void expiredFromRedis() {
		String redis_key = "md5Url_has_expired";
		if (redisTemplate3.hasKey(redis_key)) {
			String md5Url =  (String) redisTemplate3.opsForSet().pop(redis_key);
			while (!StringUtils.isEmpty(md5Url)) {
				Ershoufang esf = uniqueByProp("md5Url", md5Url);
				if (esf != null) {
					esf.setOnline(false);
					super.save(esf);
					House house = houseService.getById(esf.getId());
					if (house != null) {
						house.setOnline(false);
						houseService.save(house);
						
						// 保存下架房源记录
						HouseOffline offline = new HouseOffline();
						BeanUtils.copyProperties(house, offline);
						offline.setExpiredTime(DateUtil.currentDateTime());
						if (org.springframework.util.StringUtils.isEmpty(offline.getUrl())) {
							offline.setUrl(esf.getUrl());
						}
						if (org.springframework.util.StringUtils.isEmpty(offline.getDataFrom())) {
							offline.setDataFrom(esf.getDataFrom());
						}
						offline.setCommunity(house.getCommunity());
						houseOfflineService.save(offline);
					} else {
						HouseOffline offline = new HouseOffline();
						offline.setArea(esf.getArea());
						offline.setAveragePrice(esf.getAveragePrice());
						offline.setBaseId(esf.getBaseId());
						offline.setBaseName(esf.getBaseName());
						offline.setBlockName(esf.getRegions().getBlockName());
						offline.setBuildYear(esf.getBuildYear());
						offline.setCommunity(esf.getCommunity().getCommunity());
						offline.setDataFrom(esf.getDataFrom());
						offline.setDecorationType(esf.getDecorationType());
						offline.setDistrict(esf.getRegions().getDistrict());
						offline.setFloor(esf.getFloor());
						offline.setHouseCode(esf.getHouseCode());
						offline.setHouseType(esf.getHouseType());
						offline.setHuxing(esf.getHuxing());
						offline.setId(esf.getId());
						offline.setRoom(esf.getRoom());
						offline.setTitle(esf.getTitle());
						offline.setUrl(esf.getUrl());
						offline.setPicUrl(esf.getPicUrl());
						offline.setTotalPrice(esf.getTotalPrice());
						offline.setUnitPrice(esf.getUnitPrice());
						offline.setUseType(esf.getUseType());
						offline.setStandId(esf.getStandId());
						offline.setStandName(esf.getStandName());
						houseOfflineService.save(offline);
					}
					
				}
				md5Url =  (String) redisTemplate3.opsForSet().pop(redis_key);
			}
			
		}
		
	}
}
