package com.zfh.app.mongo.entity.system;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.RegionModel;


/**
 * 客户主动提交购房需求(以最新的为准，每次更改都记录，一个客户多次的需求)
 * 
 * @author CB
 * 
 * @dateTime 2019年5月9日下午5:48:29
 */
@Document(collection = "user_requirment")
public class UserRequirement implements Serializable {

	@Id
	private String id;
	// 用户id
	private String userId;
	// 用户手机
	private String phone;
	// 客户端
	private String client;
	// 客户key
	private String clientKey;
	// 预算房源总价
	private int totalPrice;
	// 佣金比例
	private int brokerage;
	// 首付预算
	private int downPayment;
	// 首付比例
	private int downRatio;
	// 办公地点
	private String officeAddress;
	// 区域地址
	private RegionModel regions;
	// 经纬度坐标
	private String longlat;
	// 坐标
	private GeoJsonPoint geometry;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;
	// 用户邮箱
	private String email;
	// 是否订阅
	private boolean subscribe;
	// 是否限购, true 限购
	private boolean buylimit;
	// 是否过期
	private boolean expired;

	// 买房偏好结果
	private String needSchool;
	// 学校
	private String schoolName;
	// 新房or二手房
	private String xinOresf;
	// 户型
	private String huxing;
	// 用途
	private String useType;
	// 朝向
	private String chaoxing;
	// 电梯
	private String dianti;
	// 装修
	private String zhuangxiu;
	// 梯户比
	private String tihubi;
	// 房龄
	private String houseYear;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(int brokerage) {
		this.brokerage = brokerage;
	}

	public int getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(int downPayment) {
		this.downPayment = downPayment;
	}

	public int getDownRatio() {
		return downRatio;
	}

	public void setDownRatio(int downRatio) {
		this.downRatio = downRatio;
	}

	public int getTotalPrice() {
		if (downRatio == 0) {
			downRatio = 1;
		}
		totalPrice = BigDecimal.valueOf(getDownPayment())
				.multiply(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(downRatio), 0, BigDecimal.ROUND_HALF_DOWN))
				.intValue();
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public GeoJsonPoint getGeometry() {
		return geometry;
	}

	public void setGeometry(GeoJsonPoint geometry) {
		this.geometry = geometry;
	}

	public String getLonglat() {
		return longlat;
	}

	public void setLonglat(String longlat) {
		this.longlat = longlat;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public boolean isBuylimit() {
		return buylimit;
	}

	public void setBuylimit(boolean buylimit) {
		this.buylimit = buylimit;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public RegionModel getRegions() {
		return regions;
	}

	public void setRegions(RegionModel regions) {
		this.regions = regions;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getNeedSchool() {
		return needSchool;
	}

	public void setNeedSchool(String needSchool) {
		this.needSchool = needSchool;
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

	public String getChaoxing() {
		return chaoxing;
	}

	public void setChaoxing(String chaoxing) {
		this.chaoxing = chaoxing;
	}

	public String getDianti() {
		return dianti;
	}

	public void setDianti(String dianti) {
		this.dianti = dianti;
	}

	public String getZhuangxiu() {
		return zhuangxiu;
	}

	public void setZhuangxiu(String zhuangxiu) {
		this.zhuangxiu = zhuangxiu;
	}

	public String getTihubi() {
		return tihubi;
	}

	public void setTihubi(String tihubi) {
		this.tihubi = tihubi;
	}

	public String getHouseYear() {
		return houseYear;
	}

	public void setHouseYear(String houseYear) {
		this.houseYear = houseYear;
	}

}
