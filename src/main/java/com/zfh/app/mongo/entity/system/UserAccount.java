package com.zfh.app.mongo.entity.system;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 账号表
 * 
 * @author CB
 */
@Document(collection = "user_account")
public class UserAccount {

	@Id
	private String id;
	// 用户编码
	private String userNo;
	// 用户真实姓名
	private String realName;
	// 昵称
	private String nickName;
	// 头像
	private String avatar;
	// 手机号码
	private String phone;
	// 登录密码
	private String password;
	// 用户角色
	private String userRole;
	// 用户所属机构，对应系统，或者我们的客户
	private String belongs;
	// 最后登录时间
	private String lastLogin;
	// 上次登录时间
	private String lastTime;
	// 创建账号时间
	private String createTime;
	private String createDate;
	// 来源客户端
	private String client;
	// 客户端key
	private String clientKey;
	// 邮箱
	private String email;
	
	private String idfa;
	
	private String uuid;
	
	private String imei;
	
	private String android_id;
	
	// 城市群 1 ： （深圳-东莞-惠州）
	private int cityGroup;
	
	private String city;
	
	private String province;

	private String telCity;
	
	private String telProvince;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getBelongs() {
		return belongs;
	}

	public void setBelongs(String belongs) {
		this.belongs = belongs;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getAndroid_id() {
		return android_id;
	}

	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getCityGroup() {
		return cityGroup;
	}

	public void setCityGroup(int cityGroup) {
		this.cityGroup = cityGroup;
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
	
	
}
