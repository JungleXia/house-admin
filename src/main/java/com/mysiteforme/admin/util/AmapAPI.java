package com.mysiteforme.admin.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.http.HttpUtil;

public class AmapAPI {
	private static String WEB_SERVICE_KEY = "c4a7a95eef75debff0dbb98db47e1eed";

	private static final String And = "%7C";//  表示URL中的"|"
	
	private static final Log LOGGER = LogFactory.getLog(AmapAPI.class.getName());
	
	public static AmapIpLocation ipLocation(String ip) {
		StringBuffer url = new StringBuffer("https://restapi.amap.com/v3/ip?ip=").append(ip);
		url.append("&output=JSON&key=" + WEB_SERVICE_KEY);
		LOGGER.info(url.toString());
		String result = HttpUtil.get(url.toString(), 10000);
		AmapIpLocation ipLocation = JSONObject.parseObject(result, AmapIpLocation.class);
		LOGGER.info(ipLocation);
		if ("[]".equals(ipLocation.getProvince())) {
			ipLocation.setProvince(null);
		}
		if ("[]".equals(ipLocation.getCity())) {
			ipLocation.setCity(null);
		}
		return ipLocation;
	}
	
	public static void main(String[] args) {
		AmapIpLocation ipLocation = ipLocation("223.104.37.25");
		System.out.println(ipLocation.getProvince());
	}
}
