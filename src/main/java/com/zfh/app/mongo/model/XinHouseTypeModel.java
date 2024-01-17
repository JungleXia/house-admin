package com.zfh.app.mongo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于楼盘存储精简的数据
 * @author CB
 * 
 * @dateTime 2019年7月11日下午3:23:05
 */
public class XinHouseTypeModel implements Serializable{

	private String houseId;
	private String picUrl;			// 图片地址
	private String huxing; 			// 完整户型
	private BigDecimal totalPrice;	// 总价 起(万)
	private BigDecimal unitPrice;	// 单价
	private BigDecimal area; 		// 建筑面积
	private String orientation; 	// 朝向
	private List<HouseTagModel> tags; 	// 户型标签
	private String saleStatus;		// 销售状态
	
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getHuxing() {
		if (huxing == null)
			return "";
		return huxing;
	}
	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice.setScale(0, BigDecimal.ROUND_HALF_DOWN);
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice.setScale(0, BigDecimal.ROUND_HALF_DOWN);
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getArea() {
		if (area != null && new BigDecimal(area.intValue()).compareTo(area) == 0) {
			return area.setScale(0, BigDecimal.ROUND_HALF_DOWN);
		}
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public List<HouseTagModel> getTags() {
		if (tags == null)
			return new ArrayList<>();
		return tags;
	}
	public void setTags(List<HouseTagModel> tags) {
		this.tags = tags;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	
	
	
}
