package com.zfh.app.mongo.entity.system;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 用户收藏房源，在前端“心仪房源”列表展示故有冗余
 * 
 * @author CB
 * 
 * @dateTime 2019年3月5日下午5:21:53
 */
@Document(collection = "user_collect_house")
public class CollectHouse implements Serializable{

	@Id
	private String id;
	// 标题
	private String title;
	// 总价
	private BigDecimal totalPrice;
	// 单价
	private BigDecimal unitPrice;
	// 建筑面积
	private BigDecimal area;
	// 房屋户型，几室几厅
	private String houseType;
	// 完整户型
	private String huxing;
	// 几室
	private int room;
	// 几厅
	private int hall;
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
	// 使用距离搜索，必须建立索引
//	db.getCollection('user_collect_house').createIndex({'geometry': "2dsphere"}, {background: true})
	private GeoJsonPoint geometry;
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
	
	private boolean collect;
	
	private String houseId;
	
	private String userId;
	
	private String client;
	
	private String clientKey;
	
	private String createDate;
	
	private String createTime;
	
	private String updateTime;
	// 税费
	private BigDecimal tax;
	
	@Transient
	private String url;

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
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
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
		return averagePrice;
	}
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	public String getTips() {
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
	public GeoJsonPoint getGeometry() {
		return geometry;
	}
	public void setGeometry(GeoJsonPoint geometry) {
		this.geometry = geometry;
	}
	public String getLonglat() {
		if (longlat == null)
			return "";
		return longlat;
	}
	public void setLonglat(String longlat) {
		this.longlat = longlat;
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
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		if (updateTime == null)
			return "";
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public BigDecimal getTax() {
		if (tax == null){
			return BigDecimal.ZERO;
		}
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
