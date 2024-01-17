package com.zfh.app.mongo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class HouseModel implements Serializable{

	private String id;
	// 标题
	private String title;
	// 总价
	private BigDecimal totalPrice;
	// 单价
	private BigDecimal unitPrice;
	// 房屋户型，几室几厅
	private String houseType;
	// 完整户型
	private String huxing;
	// 几室
	private int room;
	// 几厅
	private int hall;
	// 建筑面积
	private BigDecimal area;
	// 所在楼层
	private String floor;
	// 建筑年份，如：2017年
	private String buildYear;
	// 朝向
	private String orientation;
	// 装修情况，精装、毛坯
	private String decorationType;
	// 封面图
	private String picUrl;
	// 用途，交易权属：住宅、公寓、别墅、其他
	private String useType;
	// 上架状态
	private boolean online;
	// 综合分析均价
	private BigDecimal averagePrice;
	// 折扣率
	private BigDecimal discountRate;
	// 提示语
	private String tips;
	// 是否是新房，true:是，false:否
	private boolean xin;
	// 房源浏览次数
	private int view;
	// 小区名
	private String community;
	// 小区id
	private String communityId;
	// 大区域
	private String district;
	// 子区域
	private String blockName;
	// 是否已收藏 ，true-收藏，false-未收藏
	private boolean collect; 


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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHuxing() {
		return huxing;
	}

	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getHall() {
		return hall;
	}

	public void setHall(int hall) {
		this.hall = hall;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public BigDecimal getAveragePrice() {
		if (averagePrice == null) {
			return BigDecimal.valueOf(0);
		}
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getDiscountRate() {
		if (discountRate == null) {
			return BigDecimal.valueOf(1);
		}
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public String getTips() {
		if (tips == null || tips == null) {
			return "";
		}
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public boolean isXin() {
		return xin;
	}

	public void setXin(boolean xin) {
		this.xin = xin;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

}
