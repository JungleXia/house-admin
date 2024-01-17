package com.zfh.app.mongo.model;

import javax.persistence.Column;

public class UserAccountModel {
	private String id;
	// 昵称
	private String nickName;
	// 头像
	private String avatar;
	// 手机号码
	private String phone;
	
	private String email;
	
	private String lastLogin;
	
	private String client;
	
	private String clientKey;
	
	private String idfa;
	
	private String uuid;
	
	private String imei;
	
	// 首付预算
	private int downPayment;
	// 首付比例
	private String downRatio;
	// 办公地点
	private String officeAddress;
	
	// 定位
	private String location;
	
	private boolean buylimit;
	// 学校
	private String schoolName;
	// 新房or二手房
	private String xinOresf;
	// 户型
	private String huxing;
	// 用途
	private String useType;
	
	private String csite;
	// 经纪人
	private String brokerName;
	// 经纪人手机
	private String brokerPhone;
	// 隐私号
	private String privatePhone;
	// 隐私号状态
	private boolean isPrivateExpired;
	// 客户专属id 六位
	private String customerId;
	
	private int documentaryCount;
	
	private String city;
	
	private String province;
	
	private String telCity;
	
	private String telProvince;
	
	private String createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	public String getClientKey() {
		return clientKey;
	}
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(int downPayment) {
		this.downPayment = downPayment;
	}
	public String getDownRatio() {
		return downRatio;
	}
	public void setDownRatio(String downRatio) {
		this.downRatio = downRatio;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public boolean isBuylimit() {
		return buylimit;
	}
	public void setBuylimit(boolean buylimit) {
		this.buylimit = buylimit;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getXinOresf() {
		return xinOresf;
	}
	public void setXinOresf(String xinOresf) {
		this.xinOresf = xinOresf;
	}
	public String getHuxing() {
		return huxing;
	}
	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCsite() {
		return csite;
	}
	public void setCsite(String csite) {
		this.csite = csite;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getBrokerPhone() {
		return brokerPhone;
	}
	public void setBrokerPhone(String brokerPhone) {
		this.brokerPhone = brokerPhone;
	}
	public String getPrivatePhone() {
		return privatePhone;
	}
	public void setPrivatePhone(String privatePhone) {
		this.privatePhone = privatePhone;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getDocumentaryCount() {
		return documentaryCount;
	}
	public void setDocumentaryCount(int documentaryCount) {
		this.documentaryCount = documentaryCount;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getTelCity() {
		return telCity;
	}
	public void setTelCity(String telCity) {
		this.telCity = telCity;
	}
	public String getTelProvince() {
		return telProvince;
	}
	public void setTelProvince(String telProvince) {
		this.telProvince = telProvince;
	}
	public boolean isPrivateExpired() {
		return isPrivateExpired;
	}
	public void setPrivateExpired(boolean isPrivateExpired) {
		this.isPrivateExpired = isPrivateExpired;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
