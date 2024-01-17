package com.zfh.app.fang.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.mysiteforme.admin.entity.UploadInfo;
import com.mysiteforme.admin.service.UploadInfoService;
import com.zfh.app.fang.service.OSSService;

@Service("OOSService")
public class OSSServiceImpl implements OSSService{

	private static final Log logger = LogFactory.getLog(OSSServiceImpl.class);
	@Autowired
    private UploadInfoService uploadInfoService;
    
    private OSSClient ossClient = null;

    private UploadInfo getUploadInfo(){
        return uploadInfoService.getOneInfo();
    }

    public OSSClient getOSSClient(){
    	if (ossClient == null) {
    		return new OSSClient(getUploadInfo().getOssEndpoint(),getUploadInfo().getOssKeyId(), getUploadInfo().getOssKeySecret());
    	}
    	return ossClient;
    }

	@Override
	public String uploadByNetUrl(String url, String ossDir, String fileName) {
		String basePath = getUploadInfo().getOssBasePath();
		if (!(basePath.startsWith("http") && basePath.startsWith("//"))) {
			basePath = "https://" + basePath;
		}
		
		if (!basePath.endsWith("/")) {
			basePath = basePath + "/";
		}
		
		StringBuffer returnUrl = new StringBuffer(basePath);
		StringBuffer key = new StringBuffer();
		if (ossDir != null && !"".equals(ossDir)) {
			key.append(ossDir);
			if (!ossDir.endsWith("/")) {
				key.append("/");
			}
		}
		key.append(fileName);
		InputStream inputStream;
		returnUrl.append(key);
		try {
			URL urlOpen = new URL(url);
			HttpURLConnection httpconn = (HttpURLConnection) urlOpen.openConnection();
			httpconn.setConnectTimeout(5000);
			httpconn.setReadTimeout(5000);
			inputStream = urlOpen.openStream();
			getOSSClient().putObject(getUploadInfo().getOssBucketName(),
					key.toString(), inputStream);
			inputStream.close();
			return returnUrl.toString();
		} catch (IOException e) {
			// 网络资源下载失败
			logger.error("资源下载失败：" + e);
			return null;
		}
		
	}

}
