package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 链家二手房
 * 
 * @author CB
 * 
 * @dateTime 2019年5月6日下午4:35:02
 */
@Document(collection = "lj_esf_house")
//@CompoundIndexes({
//	// name：索引名称 def：字段(1正序 -1倒序) unique：是否唯一索引
//	// 直接加到字段上面没用
////	@CompoundIndex(name="houseNo_1", def = "{houseNo_1:1}", background = true),
//})
public class LjEsfHouse implements Serializable{

	/** serialVersionUID*/  
	private static final long serialVersionUID = -1528641677974815742L;
	
	@Id
	private String id;
	/**
	 * 房源链接
	 */
	private String url;
	/**
	 * md5Url
	 */
	private String md5Url;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区
	 */
	private String district;
	/**
	 * 街道|商圈
	 */
	private String block;
	
	/**
	 *  标题
	 */
	private String title;
	/**
	 * 房源编号
	 */
	private String houseNo;
	/**
	 *  总价
	 */
	private BigDecimal totalPrice;
	/**
	 *  单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 小区名
	 */
	private String community;
	/**
	 * 小区链接
	 */
	private String communityUrl;
	/**
	 *  房屋户型，几室几厅
	 */
	private String houseType;
	/**
	 *  完整户型
	 */
	private String huxing;
	/**
	 *  几室
	 */
	private int room;
	/**
	 *  几厅
	 */
	private int hall;
	/**
	 *  建筑面积
	 */
	private BigDecimal area;
	/**
	 *  所在楼层
	 */
	private String floor;
	/**
	 *  建筑年份，如：2017年
	 */
	private String buildYear;
	/**
	 *  朝向
	 */
	private String orientation;
	/**
	 *  装修情况，精装、毛坯
	 */
	private String decorationType;
	/**
	 *  封面图
	 */
	private String picUrl;
	/**
	 *  用途，交易权属：住宅、公寓、别墅、其他
	 */
	private String useType;
	/**
	 * 来源网站
	 */
	private String dataFrom;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 过期状态
	 */
	private boolean expired;
	/**
	 * 涨跌额
	 */
	private float diff;
	/**
	 * 涨跌比例
	 */
	private float diffRate;
	/**
	 * 关注人数
	 */
	private Integer followers;
	/**
	 * 发布时间
	 */
	private String publish;
	/**
	 * 来源页面
	 */
	private String pageUrl;
	/**
	 * 状态 -1：跌价，1：涨价，2：上新
	 */
	private Integer status;
	/**
	 * 标签
	 */
	private List<String> tags;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
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
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getCommunityUrl() {
		return communityUrl;
	}
	public void setCommunityUrl(String communityUrl) {
		this.communityUrl = communityUrl;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public float getDiff() {
		return diff;
	}
	public void setDiff(float diff) {
		this.diff = diff;
	}
	public Integer getFollowers() {
		return followers;
	}
	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public float getDiffRate() {
		return diffRate;
	}
	public void setDiffRate(float diffRate) {
		this.diffRate = diffRate;
	}
	
}
