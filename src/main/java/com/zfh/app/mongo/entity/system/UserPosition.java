package com.zfh.app.mongo.entity.system;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 使用百度定位
 * 用户定位，1对多，只有最新的一个有效
 * 
 * @author CB
 * 
 * @dateTime 2018年7月30日上午10:26:21
 */
@Document(collection = "user_position")
public class UserPosition implements Serializable{

	@Id
	private String id;
	private String phone; 		// 用户手机号
	private Date dateTime; 		// 当前定位时间
	private String dateString; 	// 定位日期
	private String clientIp;	// 用户IP
	private String client; 		// ios|android|mobile|pc
	private String clientKey; 	// 用户key
	private String userId;
	// 可选，设置返回经纬度坐标类型，默认GCJ02
	// GCJ02：国测局坐标；
	// BD09ll：百度经纬度坐标；
	// BD09：百度墨卡托坐标；
	// 海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
	private String coorType;

	// 可选，设置发起定位请求的间隔，int类型，单位ms
	// 如果设置为0，则代表单次定位，即仅定位一次，默认为0
	// 如果设置非0，需设置1000ms以上才有效
	private int scanSpan; // 发起定位请求的间隔

	// 可选，设置是否使用gps，默认false
	// 使用高精度和仅用设备两种定位模式的，参数必须设置为true
	private boolean openGps;		// 是否使用gps
	private String latitude; 		// 获取纬度信息
	private String longitude; 		// 获取经度信息
	private String longlat;
	private String radius; 			// 获取定位精度，默认值为0.0f

	// 获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
	int errorCode;

	private String address; // 获取详细地址信息
	private String country; // 获取国家
	private String province; // 获取省份
	private String city; // 获取城市
	private String district; // 获取区县
	private String street; // 获取街道信息

	private boolean expired;	//是否过期 
	private String createTime;  //创建时间
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
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

	public String getCoorType() {
		return coorType;
	}

	public void setCoorType(String coorType) {
		this.coorType = coorType;
	}

	public int getScanSpan() {
		return scanSpan;
	}

	public void setScanSpan(int scanSpan) {
		this.scanSpan = scanSpan;
	}

	public boolean isOpenGps() {
		return openGps;
	}

	public void setOpenGps(boolean openGps) {
		this.openGps = openGps;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLonglat() {
		return longlat;
	}

	public void setLonglat(String longlat) {
		this.longlat = longlat;
	}
	
}
