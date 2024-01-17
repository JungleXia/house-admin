package com.zfh.app.fang.service;

import com.aliyun.oss.OSSClient;

public interface OSSService {
	
	public OSSClient getOSSClient();
	
	/**
	 * 下载网络文件，并上传至阿里云oss，返回oss链接
	 * @param url
	 * @param ossDir
	 * @param fileName
	 * @return
	 * 2019年6月5日下午3:36:06
	 */
	public String uploadByNetUrl(String url, String ossDir, String fileName);
}
