package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.BillNotifyRecord;

public interface BillNotifyRecordService extends BaseMongoService<BillNotifyRecord>{

	/**
	 * 批量下载录音文件
	 * 
	 * 2020年3月24日下午12:14:45
	 */
	public void downloadLinks();
}
