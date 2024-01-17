package com.zfh.app.mongo.entity.system;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sys_ad_convert")
public class AdConveRecord {

	@Id
	private String id;
	private String aid; 		//	 广告计划id	 原值
	private String aid_name; 	//	 广告计划名称	 url编码
	private String campaign_id; // 广告组id	 原值
	private String cid; 		//  广告创意id 原值
	private String csite; 		//	 广告投放位置	 1:今日头条	 10001:西瓜视频	 30001:火山小视频	 40001:抖音
	private String ctype; 		//	 创意样式	 2=小图模式  3=大图模式	 4=组图模式	 5=视频  
	private String mac; 		//	MAC地址	 去除分隔符 ":",(采用获取原始值)取 md5sum 摘要(备注:入网硬件地址)例：38978B891A08
	private String mac1; 		//	 用户终端的eth0接口的MAC地址	 保留分隔符 ":",(采用获取原始值)取 md5sum 摘要(备注:入网硬件地址) 例：38:97:8B:89:1A:08
	private String ua; 			//	 客户端上报数据时http的header中的user_agent	 urlencode编码
	private String idfa; 		//	iOS IDFA适用ios6及以上系统	 原值
	private String imei; 		//	 用户终端的15位数字IMEI	 取md5sum摘要（双卡手机可能会有多个IMEI，因此可能存在获取IMEI不唯一的情况）如果头条未能成功获取设备imei，此处可能为空
	private String oaid; 		//	Android Q 及更高版本的设备号	原值
	private String uuid; 		//	 用户终端的UUID（用户终端的15位数字IMEI）	 原值（安卓手机系统生成的设备ID）如果头条未能成功获取设备imei，此处可能为空
	private String android_id;  //	 手机android_id。该设备识别号可能为空，与设 备可能存在多对一或一对多的关系	 安卓手机为androidid md5加密  iOS设备为openudid md5加密
	private String openud_id; 	//	 OPENUDID（安卓和IOS手机均有）	 原值（通过第三方的openudid SDK生成）
	private String os; 			//	 客户端操作系统	 0=android	 1=IOS	 3=OTHERS或为空
	private String ip; 			//	 媒体投放系统获取的用户终端的公共IP地址	 A.B.C.D（4段分点）
	private String ts; 			//	 客户端发生广告点击事件的时间	 UNIX时间戳
	private String convertId; 	// 	 转化id	 原值
	private String callbackUrl; // 	 回调地址（方案一）	 见后文
	private String callbackParam; // 	 回调参数（方案二）	 见后文
	
	private String createTime;
	private String createDate;
	private String req_ip;		// 请求来源ip
	private String req_ua;		// 请求来源 ua
	
	private int status;			// 激活状态， 0 未激活；1-已激活
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAid_name() {
		return aid_name;
	}
	public void setAid_name(String aid_name) {
		this.aid_name = aid_name;
	}
	public String getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCsite() {
		return csite;
	}
	public void setCsite(String csite) {
		this.csite = csite;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMac1() {
		return mac1;
	}
	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getOaid() {
		return oaid;
	}
	public void setOaid(String oaid) {
		this.oaid = oaid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getOpenud_id() {
		return openud_id;
	}
	public void setOpenud_id(String openud_id) {
		this.openud_id = openud_id;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getConvertId() {
		return convertId;
	}
	public void setConvertId(String convertId) {
		this.convertId = convertId;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getCallbackParam() {
		return callbackParam;
	}
	public void setCallbackParam(String callbackParam) {
		this.callbackParam = callbackParam;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReq_ip() {
		return req_ip;
	}
	public void setReq_ip(String req_ip) {
		this.req_ip = req_ip;
	}
	public String getReq_ua() {
		return req_ua;
	}
	public void setReq_ua(String req_ua) {
		this.req_ua = req_ua;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	 
}
