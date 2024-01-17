package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 二手房历史记录
 * @author CB
 * 
 * @dateTime 2019年4月4日下午1:59:44
 */
@Document(collection = "esf_ershoufang_history")
public class ErshoufangHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String title; 			// 标题
	private String url; 			// 原网站链接
	private String md5Url;			// MD5(url)
	private String totalPrice; 		// 总价
	private String unitPrice; 		// 单价
	private String houseType; 		// 房屋户型，几室几厅
	private String area; 			// 建筑面积
	private String innerArea; 		// 套内面积
	private String floor; 			// 所在楼层
	private String buildYear; 		// 建筑年份，如：2017年
	private String orientation; 	// 朝向
	private String decorationType;  // 装修情况，精装、毛坯
	private String houseCode; 		// 房协编码，房源编码
	private String houseSiteCode; 	// 本站房源编码
	private String dataFrom;		// 来源
	private String createDate;		// 入库日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMd5Url() {
		return md5Url;
	}
	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
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
	public String getInnerArea() {
		return innerArea;
	}
	public void setInnerArea(String innerArea) {
		this.innerArea = innerArea;
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
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getHouseSiteCode() {
		return houseSiteCode;
	}
	public void setHouseSiteCode(String houseSiteCode) {
		this.houseSiteCode = houseSiteCode;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
}
