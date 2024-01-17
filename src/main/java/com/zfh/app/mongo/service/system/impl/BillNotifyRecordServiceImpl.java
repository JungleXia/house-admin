package com.zfh.app.mongo.service.system.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mysiteforme.admin.util.DateUtil;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.fang.service.OSSService;
import com.zfh.app.mongo.entity.system.BillNotifyRecord;
import com.zfh.app.mongo.service.system.BillNotifyRecordService;

@Service
public class BillNotifyRecordServiceImpl extends BaseMongoServiceImpl<BillNotifyRecord> implements BillNotifyRecordService{

	private static final Log logger = LogFactory.getLog(BillNotifyRecordServiceImpl.class.getName());
	
	@Autowired
	private OSSService ossService;
	
	@Override
	protected Class<BillNotifyRecord> getEntityClass() {
		// TODO Auto-generated method stub
		return BillNotifyRecord.class;
	}

	@Override
	public void downloadLinks() {
		int pageSize = 10;
		String[] propName = new String[]{"recordFlag", "downloadUrl"};
		Object[] values = new Object[]{1, null};
		// 查找所有未下载的录音的话单
		int total = countByCondition(propName, values);
		int pageNum = (total + pageSize - 1) / pageSize;
		logger.info("======待下载的录音文件总数：" + total);
		while (pageNum > 0) {
			pageNum = pageNum - 1;
			Page<BillNotifyRecord> page =  findPage(new PageRequest(pageNum, pageSize), propName, values, "_id desc");
			for (BillNotifyRecord record : page) {
				String downloadUrl = axbGetDownloaadLink(record.getRecordDomain(), record.getRecordObjectName());
				if (downloadUrl == null) {
					continue;
				}
				record.setDownloadUrl(downloadUrl);
				// 资源下载失败, No subject alternative names matching IP address 1.189.75.77 found
//				String ossUrl = uploadSoundRecord(downloadUrl, record.getRecordObjectName());
//				if (ossUrl != null) {
//					record.setDownTime(DateUtil.currentDateTime());
//					record.setDownloaded(true);
//					record.setOssUrl(ossUrl);
//				}
				super.save(record);
			}
		}
		
	}
	
	/**
	 * 获取华为云录音文件链接
	 * @param recordDomain
	 * @param fileName
	 * @return
	 * 2020年3月24日下午12:49:45
	 */
	private String axbGetDownloaadLink(String recordDomain, String fileName) {
		try {
			return AXBInterfaceDemoImpl.getInstance().axbGetRecordDownloadLink(recordDomain, fileName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 上传录音文件
	 * @return
	 * 2020年3月24日下午12:44:07
	 */
	private String uploadSoundRecord(String downloadUrl, String fileName) {
		String ossDir = "sound";
		String returnUrl =  ossService.uploadByNetUrl(downloadUrl, ossDir, fileName);
		logger.info("上传录音成功：url=" + returnUrl);
		return returnUrl;
	}

}
