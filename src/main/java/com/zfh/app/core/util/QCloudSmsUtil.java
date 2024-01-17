package com.zfh.app.core.util;

import java.io.IOException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

/**
 * 腾讯云短信接口
 * ClassName: QCloudSmsUtil
 * @author Jungle
 * @dateTime 2020年3月3日下午12:23:32
 * @version v1.0
 */
public class QCloudSmsUtil {

	private static final Logger logger = LoggerFactory.getLogger(QCloudSmsUtil.class);

	private static QCloudSmsUtil instance = null;
	
	private static Object lock = new Object();

	public static QCloudSmsUtil getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new QCloudSmsUtil();
				}
			}
		}
		return instance;
	}
	
	// 短信应用 SDK AppID
	private static int appid = 1400324716; // SDK AppID 以1400开头
	// 短信应用 SDK AppKey
	private static String appkey = "6eaf5a0ce2cafe871032e7ee9d989deb";
	// 需要发送短信的手机号码
//	private static String[] phoneNumbers = {"18018740607", "18194032692", "13434454737"};
	
	/** 免费搬家模板*/
	public static int templateId_freeSer = 546105;
//	public static int templateId_freeSer = 548078;
	/** 搬家券通知 模板*/
	public static int templateId_ticket = 547136;
	
	// 签名
	public static String smsSign = "众房汇"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
	
	public SmsSingleSenderResult singleSend(String phoneNumber, int templateId) {
		try {
			String[] params = {};
			SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", "");
			System.out.println(result);
			return result;
		} catch (HTTPException e) {
		  // HTTP 响应码错误
			logger.error(e.getMessage(), e);
		} catch (JSONException e) {
		  // JSON 解析错误
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
		  // 网络 IO 错误
			logger.error(e.getMessage(), e);
		}
		return new SmsSingleSenderResult();
	}
	
	public SmsMultiSenderResult multiSend(String[] phoneNumbers, int templateId) {
		try {
			  String[] params = {};
			  SmsMultiSender msender = new SmsMultiSender(appid, appkey);
			  SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers, templateId, params, smsSign, "", "");
			  System.out.println(result);
			  return result;
			} catch (HTTPException e) {
			  // HTTP 响应码错误
				logger.error(e.getMessage(), e);
			} catch (JSONException e) {
			  // JSON 解析错误
				logger.error(e.getMessage(), e);
			} catch (IOException e) {
			  // 网络 IO 错误
				logger.error(e.getMessage(), e);
			}
		return new SmsMultiSenderResult();
	}

}
