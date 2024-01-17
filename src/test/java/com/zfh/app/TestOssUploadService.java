package com.zfh.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysiteforme.admin.entity.Rescource;
import com.mysiteforme.admin.entity.UploadInfo;
import com.mysiteforme.admin.service.RescourceService;
import com.mysiteforme.admin.service.UploadInfoService;
import com.xiaoleilu.hutool.util.RandomUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestOssUploadService {

	@Autowired
	private RescourceService rescourceService;

	@Autowired
	private UploadInfoService uploadInfoService;

	public void testUpload() {

	}

	// @Test
	public void testnetUrl() {
		UploadInfo uplocaInfo = uploadInfoService.getOneInfo();
		String url = "https://image1.ljcdn.com/hdic-resblock/610335cf-f62c-41f8-afaa-93c62d7dc0f0.jpg.710x400.jpg";

		Rescource rescource = new Rescource();

		String ossDir = uploadInfoService.getOneInfo().getOssDir(), fileName = RandomUtil.randomUUID();
		String basePath = uplocaInfo.getOssBasePath();
		if (!(basePath.startsWith("http") && basePath.startsWith("//"))) {
			basePath = "https://" + basePath;
		}
		if (!basePath.endsWith("/")) {
			basePath = basePath + "/";
		}
		StringBuffer returnUrl = new StringBuffer(basePath);
		StringBuffer key = new StringBuffer();
		if (ossDir != null && !"".equals(ossDir)) {
			key.append(ossDir).append("/");
		}
		key.append(com.mysiteforme.admin.util.DateUtil.format(com.mysiteforme.admin.util.DateUtil.now(), "yyyyMMdd"))
				.append("/");
		key.append(fileName).append(".jpg");
		StringBuffer sb = new StringBuffer(fileName);
		InputStream inputStream;
		try {
			inputStream = new URL(url).openStream();
			OSSClient ossClient = new OSSClient(uplocaInfo.getOssEndpoint(), uplocaInfo.getOssKeyId(),
					uplocaInfo.getOssKeySecret());
			PutObjectResult putObjectResult = ossClient.putObject(uplocaInfo.getOssBucketName(), key.toString(),
					inputStream);
			ResponseMessage responseMessage = putObjectResult.getResponse();

			inputStream.close();

			ossClient.shutdown();

			System.out.println(returnUrl.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
