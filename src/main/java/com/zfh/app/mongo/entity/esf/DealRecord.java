package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 小区房源成交记录
 * @author CB
 * 
 * @dateTime 2019年3月25日下午5:55:17
 */
@Document(collection = "esf_deal_records")
public class DealRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/**
	 * 小区名
	 */
	private String community;

	/**
	 * MD5(小区url)
	 */
	private String md5CommunityUrl;

	/**
	 * 成交价
	 */
	private String totalPrice;

	/**
	 * 单价
	 */
	private String unitPrice;

	/**
	 * 户型
	 */
	private String houseType;

	/**
	 * 面积
	 */
	private String area;
	private String floor; 			// 所在楼层
	private String buildYear; 		// 建筑年份，如：2017年
	private String orientation; 	// 朝向
	private String decorationType;  // 装修情况，精装、毛坯

	/**
	 * 房源详情
	 */
	private String houseDetails;

	/**
	 * 成交日期
	 */
	private String dealTime;

	/**
	 * 数据来源
	 */
	private String dataFrom;

	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getMd5CommunityUrl() {
		return md5CommunityUrl;
	}

	public void setMd5CommunityUrl(String md5CommunityUrl) {
		this.md5CommunityUrl = md5CommunityUrl;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHouseDetails() {
		return houseDetails;
	}

	public void setHouseDetails(String houseDetails) {
		this.houseDetails = houseDetails;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(String decorationType) {
		this.decorationType = decorationType;
	}
	
}
