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
import com.zfh.app.mongo.entity.esf.HousePhoto;
import com.zfh.app.mongo.service.esf.HousePhotoService;

@Service("housePhotoService")
public class HousePhotoServiceImpl extends BaseMongoServiceImpl<HousePhoto> implements HousePhotoService {

	private static final Log logger = LogFactory.getLog(HousePhotoServiceImpl.class.getName());
	
	@Autowired
	private OSSService ossService;
	
	private final static String DF_LIANJIA = "链家";
	private final static String DF_ZHONGYUAN = "中原";
	private final static String DF_QFANG = "Q房网";
	private final static String DF_LEYOUJIA = "乐有家";
    
	private final static String PIC_LJ = "https://www.zfh.vip/resources/images/lj_default.jpg";
	
	private final static String PIC_DEF = "https://image.zfh.vip/ershoufang/default.png";
	@Override
	protected Class<HousePhoto> getEntityClass() {
		// TODO Auto-generated method stub
		return HousePhoto.class;
	}

	public Page<HousePhoto> findUndownload(int page, int size) {
		PageRequest pageReq = new PageRequest(page, size);
		// 先爬链家的图片
		return findPage(pageReq, new String[] { "status", "dataFrom" }, new Object[] { 0 , "链家"}, null);
	}

	public void upload(List<HousePhoto> photoList, int pageIndex) {
		long start = new Date().getTime();
		List<BathUpdateOptions> options = new ArrayList<>();
		for (int index = 0; index < photoList.size(); index++) {
			logger.info(pageIndex + " - " + index);
			HousePhoto photo = photoList.get(index);
			if (StringUtils.isNotEmpty(photo.getTarget())) {
				continue;
			}
			
			String source = photo.getSource();
			if (StringUtils.isEmpty(photo.getMd5Url())) {
				photo.setMd5Url(MD5Util.MD5(source));
			}
			source = replaceSize(source, photo.getSize(), photo.getDataFrom());
			String ossDir = getOssDir(photo.getType()), fileName = photo.getMd5Url() + ".jpg";
			String returnUrl =  ossService.uploadByNetUrl(source, ossDir, fileName);
			int status = 0;
			if (returnUrl != null) {
				status = 1;
			} else {
				status = 2; // 下载失败
			}
			options.add(new BathUpdateOptions(Query.query(Criteria.where("_id").is(new ObjectId(photo.getId()))),
					Update.update("status", status).set("target", returnUrl), false, false));
		}
		// 上传完成后批量更新状态
		super.bathUpdate(options);
		long end = new Date().getTime();
		logger.info("spand time: " + (end - start));
	}
		
	public String getOssDir(int photoType) {
		String oosDir = "ershoufang/";
		if (photoType == 10) {
			oosDir = "ershoufang/";
		} else if (photoType == 20) {
			oosDir = "community/";
		} else if (photoType == 30) {
			oosDir = "xinfang/";
		}
		oosDir += DateUtil.format(DateUtil.now(), "yyyyMMdd");
		return oosDir;
	}
	
	public String replaceSize(String source, String size, String dataFrom) {
		String replaceSize = "1000x800";
		String replaceSource = "";
		if (StringUtils.isNotEmpty(size)) {
			if (!DF_LEYOUJIA.equals(dataFrom)) {
				// 乐有家的图片大小不做处理
				replaceSource = source.replace(size, replaceSize);
			}
		}
		if (DF_ZHONGYUAN.equals(dataFrom)) {
			// 旧 的域名有问题
			replaceSource = source.replaceAll("szstatic.centaline.com.cn", "img0sz.centainfo.com");
		}
		return replaceSource;
	}
	
	public String cropSize(String url, String oldSize, String newSize) {
		return url.replace(oldSize, newSize);
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
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(10000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		DiscardPolicy handler = new ThreadPoolExecutor.DiscardPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int pageNum = 0;
		int pageSize = 300;
		Page<HousePhoto> page = findUndownload(pageNum, pageSize);
		int totalPage = page.getTotalPages();
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					List<HousePhoto> photoList = findUndownload(pageIndex, pageSize).getContent();
					upload(photoList, pageIndex);
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
			 
			 ossService.getOSSClient().shutdown();
		 
		 } catch (InterruptedException e) {
			 logger.error(e.getMessage(), e);
		 }
		
	}

	@Override
	public String findOSSUrl(String url, String dataFrom) {
		if (StringUtils.equals(url, PIC_LJ) || url.equals(PIC_DEF) 
				|| url.startsWith("https://image.zfh.vip")) {
			return PIC_DEF;
		}

		String md5Url = MD5Util.MD5(url);
		HousePhoto housePhoto = uniqueByProp("md5Url", md5Url);
		if (housePhoto != null) {
			logger.info("find, url: " + url);
			if (StringUtils.isNotEmpty(housePhoto.getTarget()))
				return housePhoto.getTarget() + "?x-oss-process=style/resize_50";
		} else {
			logger.info("unfind, url: " + url);
			housePhoto = new HousePhoto();
			housePhoto.setDescription("");
			housePhoto.setSize("");
			housePhoto.setStatus(1);
			housePhoto.setType(10);
		}
		return null;
//		String md5Url = MD5Util.MD5(url);
//		HousePhoto housePhoto = uniqueByProp("md5Url", md5Url);
//		if (housePhoto == null) {
//			housePhoto = new HousePhoto();
//			housePhoto.setDescription("");
//			housePhoto.setSize("");
//			housePhoto.setStatus(1);
//			housePhoto.setType(10);
//			housePhoto.setSource(url);
//		}
//		
//		String ossDir = getOssDir(10), fileName = md5Url + ".jpg";
//		String targetUrl = ossService.uploadImgByNetUrl(housePhoto.getSource(), ossDir, fileName);
//		logger.info("新连接：" + targetUrl);
//		if (StringUtils.isNotBlank(targetUrl)) {
//			housePhoto.setMd5Url(md5Url);
//			
//			housePhoto.setDataFrom(dataFrom);
//			housePhoto.setTarget(targetUrl);
//			super.save(housePhoto);
//			targetUrl = targetUrl + "?x-oss-process=style/resize_50";
//			return targetUrl;
//		}
//		return null;
	}

}
