package com.zfh.app.mongo.entity.system;

import java.io.Serializable;
import java.math.BigDecimal;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.entity.esf.House;

/**
 * 房源浏览记录
 * @author CB
 *
 */
@Document(collection = "user_house_view")
public class HouseViewHistory implements Serializable{
		
	@Id
	private String id;
	private String houseId;			// 房源id
	private int viewCount;			// 个人独立浏览次数
	private String createTime;		// 创建时间
	private String createDate;		// 创建日期
	private String client;			// 客户端类型
	private String clientKey;		// 客户端key	
	private String userId;			// 已登录用户id
	private String title;			// 标题
	private String longlat;
	// 小区名
	private String community;
	// 小区id
	private String communityId;
	// 市
	private String city;
	// 大区域
	private String district;
	// 子区域
	private String blockName;
	// 标准小区id
	private String standId;
	// 标准小区
	private String standName;
	
	private BigDecimal totalPrice; 	// 总价
	private BigDecimal unitPrice; 	// 单价
	private String houseType; 		// 房屋户型，几室几厅
	private String huxing; 			// 完整户型
	private int room;				// 几室
	private int hall;				// 几厅
	private BigDecimal area; 		// 建筑面积
	private String picUrl; 			// 封面图
	private String useType; 		// 用途，交易权属：住宅、公寓、别墅、其他
	private boolean xin;			// 是否是新房，true:是，false:否
	private boolean collect;		// 是否被收藏
	// 朝向
	private String orientation;
	
	@Transient
	private String phone;
	
	private String url;
	
	private boolean online;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getUserId() {
		if (userId == null)
			return "";
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public boolean isXin() {
		return xin;
	}

	public void setXin(boolean xin) {
		this.xin = xin;
	}

	public String getLonglat() {
		return longlat;
	}

	public void setLonglat(String longlat) {
		this.longlat = longlat;
	}

	public String getCommunity() {
		if (community == null)
			return standName;
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCommunityId() {
		if (communityId == null)
			return standId;
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
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

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getStandId() {
		return standId;
	}

	public void setStandId(String standId) {
		this.standId = standId;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrientation() {
		if (orientation == null)
			return "";
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
}
