package com.zfh.app.mongo.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zfh.app.core.util.PrivateNumberUtil;
import com.zfh.app.mongo.model.AXBResponse;
import com.zfh.app.mongo.service.system.IAXBInterfaceDemo;

/**
 * AXB模式接口测试
 */
public class AXBInterfaceDemoImpl implements IAXBInterfaceDemo {
    private Logger logger = Logger.getLogger(AXBInterfaceDemoImpl.class);

    private static final String appKey = "fNTMmrdvT3WnbhnaalN12rpe8a1h"; // APP_Key
    private static final String appSecret = "1kO0FyRi4fGZ69SGoBK1aDSKbC1l"; // APP_Secret
    private static final String ompDomainName = "https://rtcapi.cn-north-1.myhuaweicloud.com:12543"; // APP接入地址

//    private static final String relationNum_1 = "+8617169662275";
//    private static final String relationNum_2 = "+8617169661605";
//    
//    private static final String relationNum_3 = "+8617169668753";
//    private static final String relationNum_4 = "+8617169668756";
    private static final String relationNum_5 = "+8617169668757";
    private static final String relationNum_6 = "+8617169668758";
    private static final String relationNum_7 = "+8617169668760";
    private static final String relationNum_8 = "+8617169668761";
    private static final String relationNum_9 = "+8617169668762";
    private static final String relationNum_10 = "+8617169668763";
    private static final String relationNum_11 = "+8617169668767";
    private static final String relationNum_12 = "+8617169668768";
    
    public static final String[] relationNums = {relationNum_5, relationNum_6,
    		relationNum_7, relationNum_8, relationNum_9, relationNum_10, relationNum_11, relationNum_12};
    
    private static AXBInterfaceDemoImpl instance = new AXBInterfaceDemoImpl();
    
	public static AXBInterfaceDemoImpl getInstance() {
		return instance;
	}
    /**
     * Build the real url of https request | 构建隐私保护通话平台请求路径
     * 
     * @param path 接口访问URI
     * @return
     */
    private String buildOmpUrl(String path) {
        return ompDomainName + path;
    }

    @Override
    public String axbBindNumber(String relationNum, String callerNum, String calleeNum) {
        if (StringUtils.isBlank(relationNum) || StringUtils.isBlank(callerNum) || StringUtils.isBlank(calleeNum)) {
            logger.info("axbBindNumber set params error");
            return null;
        }

        // 必填,AXB模式绑定接口访问URI
        String url = "/rest/caas/relationnumber/partners/v1.0";
        String realUrl = buildOmpUrl(url);

        // 封装JOSN请求
        JSONObject json = new JSONObject();
        json.put("relationNum", relationNum); // X号码(关系号码)
        json.put("callerNum", callerNum); // A方真实号码(手机或固话)
        json.put("calleeNum", calleeNum); // B方真实号码(手机或固话)

        /**
         * 选填,各参数要求请参考"AXB模式绑定接口"
         */
//         json.put("areaCode", "0755"); //城市码
         json.put("callDirection", 0); //允许呼叫的方向, 允许双向呼叫
         json.put("duration", 2592000); //绑定关系保持时间,单位为秒。 如果不携带该参数或携带为0，系统默认永不过期。
         json.put("recordFlag", "true"); //是否通话录音
//         json.put("recordHintTone", "recordHintTone.wav"); //录音提示音
         json.put("maxDuration", 120); //单次通话最长时间，单位为分钟
//         json.put("lastMinVoice", "lastMinVoice.wav"); //通话最后一分钟提示音
//         json.put("privateSms", "true"); //是否支持短信功能
         JSONObject preVoice = new JSONObject();
         preVoice.put("callerHintTone", "preVoice.wav"); //设置A拨打X号码时的通话前等待音
         preVoice.put("calleeHintTone", "preVoice.wav"); //设置B拨打X号码时的通话前等待音
         json.put("preVoice", preVoice); //个性化通话前等待音

        String result = PrivateNumberUtil.sendPost(appKey, appSecret, realUrl, json.toString());
        logger.info("Response is :" + result);
        return result;
    }

    @Override
    public String axbModifyNumber(String subscriptionId, String callerNum, String calleeNum) {
        if (StringUtils.isBlank(subscriptionId)) {
            logger.info("axbModifyNumber set params error");
            return null;
        }

        // 必填,AXB模式绑定信息修改接口访问URI
        String url = "/rest/caas/relationnumber/partners/v1.0";
        String realUrl = buildOmpUrl(url);

        // 封装JOSN请求
        JSONObject json = new JSONObject();
        json.put("subscriptionId", subscriptionId); // 绑定关系ID
        if (StringUtils.isNotBlank(callerNum)) {
            json.put("callerNum", callerNum); // 将A方修改为新的号码(手机或固话)
        }
        if (StringUtils.isNotBlank(calleeNum)) {
            json.put("calleeNum", calleeNum); // 将B方修改为新的号码(手机或固话)
        }

        /**
         * 选填,各参数要求请参考"AXB模式绑定信息修改接口"
         */
         json.put("callDirection", 0); //允许呼叫的方向
         json.put("duration", 2592000); //绑定关系保持时间
         json.put("maxDuration", 120); //单次通话最长时间，单位为分钟
         json.put("recordFlag", "true"); //是否通话录音
//         json.put("lastMinVoice", "lastMinVoice.wav"); //通话最后一分钟提示音
//         json.put("privateSms", "true"); //是否支持短信功能
//         JSONObject preVoice = new JSONObject();
//         preVoice.put("callerHintTone", "callerHintTone.wav"); //设置A拨打X号码时的通话前等待音
//         preVoice.put("calleeHintTone", "calleeHintTone.wav"); //设置B拨打X号码时的通话前等待音
//         json.put("preVoice", preVoice); //个性化通话前等待音

        String result = PrivateNumberUtil.sendPut(appKey, appSecret, realUrl, json.toString());
        logger.info("Response is :" + result);
        return result;
    }

    @Override
    public String axbUnbindNumber(String subscriptionId, String relationNum) {
        if (StringUtils.isBlank(subscriptionId) && StringUtils.isBlank(relationNum)) {
            logger.info("axbUnbindNumber set params error");
            return null;
        }

        // 必填,AXB模式解绑接口访问URI
        String url = "/rest/caas/relationnumber/partners/v1.0";
        String realUrl = buildOmpUrl(url);

        // 申明对象
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(subscriptionId)) {
            map.put("subscriptionId", subscriptionId); // 绑定关系ID
        }
        if (StringUtils.isNotBlank(relationNum)) {
            map.put("relationNum", relationNum); // X号码(关系号码)
        }

        String result = PrivateNumberUtil.sendDelete(appKey, appSecret, realUrl, PrivateNumberUtil.map2UrlEncodeString(map));
        logger.info("Response is :" + result);
        return result;
    }

    @Override
    public String axbQueryBindRelation(String subscriptionId, String relationNum) {
        if (StringUtils.isBlank(subscriptionId) && StringUtils.isBlank(relationNum)) {
            logger.info("axbQueryBindRelation set params error");
            return null;
        }

        // 必填,AXB模式绑定信息查询接口访问URI
        String url = "/rest/caas/relationnumber/partners/v1.0";
        String realUrl = buildOmpUrl(url);

        // 申明对象
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(subscriptionId)) {
            map.put("subscriptionId", subscriptionId); // 绑定关系ID
        }
        if (StringUtils.isNotBlank(relationNum)) {
            map.put("relationNum", relationNum); // X号码(关系号码)
        }
        /**
         * 选填,当携带了subscriptionId时无需关注如下参数
         */
        // map.put("pageIndex", 1); //查询的分页索引,从1开始编号
        // map.put("pageSize", 20); //查询的分页大小,即每次查询返回多少条数据

        String result = PrivateNumberUtil.sendGet(appKey, appSecret, realUrl, PrivateNumberUtil.map2UrlEncodeString(map));
        logger.info("Response is :" + result);
        return result;
    }

    @Override
    public String axbGetRecordDownloadLink(String recordDomain, String fileName) {
        if (StringUtils.isBlank(recordDomain) || StringUtils.isBlank(fileName)) {
            logger.info("axbGetRecordDownloadLink set params error");
            return null;
        }
        // 必填,AXB模式获取录音文件下载地址接口访问URI
        String url = "/rest/provision/voice/record/v1.0";
        String realUrl = buildOmpUrl(url);

        // 申明对象
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("recordDomain", recordDomain); // 录音文件存储的服务器域名
        map.put("fileName", fileName); // 录音文件名

        String result = PrivateNumberUtil.sendGet(appKey, appSecret, realUrl, PrivateNumberUtil.map2UrlEncodeString(map));
        logger.info("Response is :" + result);
        return result;
    }

    @Override
    public String axbStopCall(String sessionid) {
        if (StringUtils.isBlank(sessionid)) {
            logger.info("axbStopCall set params error");
            return null;
        }

        // 必填,AXB模式终止呼叫接口访问URI
        String url = "/rest/httpsessions/callStop/v2.0";
        String realUrl = buildOmpUrl(url);

        // 封装JOSN请求
        JSONObject json = new JSONObject();
        json.put("sessionid", sessionid); // 呼叫会话ID
        json.put("signal", "call_stop"); // 取值固定为"call_stop"

        String result = PrivateNumberUtil.sendPost(appKey, appSecret, realUrl, json.toString());
        logger.info("Response is :" + result);
        return result;
    }
    
    public static void main(String[] args) {
    	AXBInterfaceDemoImpl demo = new AXBInterfaceDemoImpl();
    	String response = demo.axbBindNumber(relationNum_5, "+8613128802961", "+8613510084877");
    	AXBResponse resp = new AXBResponse();
    	resp = JSON.parseObject(response, AXBResponse.class);
    	System.out.println(JSON.toJSONString(resp, true));

	}

}