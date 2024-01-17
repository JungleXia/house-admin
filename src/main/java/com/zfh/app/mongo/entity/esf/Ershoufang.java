package com.zfh.app.mongo.entity.esf;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfh.app.mongo.model.LoadInfoModel;
import com.zfh.app.mongo.model.RegionModel;

/**
 * 二手房源，用于数据分析
 * 
 * @author CB
 * 
 * @dateTime 2019年4月4日下午1:59:44
 */
@Document(collection = "esf_ershoufang")
@CompoundIndexes({
	// name：索引名称 def：字段(1正序 -1倒序) unique：是否唯一索引
	// 直接加到字段上面没用
	@CompoundIndex(def = "{standId:1, verifyStatus:1, online:1}", background = true),
})
public class Ershoufang {

	@Id
	private String id;
	private String title; // 标题
	private String url; // 原网站链接
	@Indexed
	private String md5Url; // MD5(url)
	private BigDecimal totalPrice; // 总价
	private BigDecimal unitPrice; // 单价
	private String houseType; // 房屋户型，几室几厅
	private String huxing; // 完整户型
	private int room; // 几室
	private int hall;
	private BigDecimal area; // 建筑面积
	private String innerArea; // 套内面积
	private String floor; // 所在楼层
	private String buildYear; // 建筑年份，如：2017年
	private String orientation; // 朝向
	private String decorationType; // 装修情况，精装、毛坯
	private String houseCode; // 房协编码，房源编码
	private String houseSiteCode; // 本站房源编码
	private String picUrl; // 封面图
	private String picHuxing; // 户型图
	private String useType; // 用途，交易权属：住宅、公寓、别墅、其他
	private String dataFrom; // 来源
	private String createDate; // 入库日期
	private String updateDate; // 分析更新日期

	private GeoJsonPoint geometry;
	private boolean verified; // 是否已验证
	private boolean verifyStatus; // 是否是真实房源，false-非真实；true-真实
	private boolean codeStatus; // 房源编码是否有效，0-无效； 1-有效
	private boolean expired; // 是否过期
	private boolean online; // 是否显示
	private BigDecimal averagePrice; // 综合分析均价
	private BigDecimal discountRate; // 折扣率
	private String tips; // 提示语

	private RegionModel regions;
	private LoadInfoModel loadInfo; // 贷款信息

	// @DBRef
	// @DBRef 只能通过id查询，不能通过其他属性查询
	private Community community;

	@DBRef
	private ErshoufangExtend extension; // 拆分拓展信息

	@DBRef
	private List<HousePhoto> photos; // 房源相册

	@Transient
	private boolean xin; // 是否是新房，true:是，false:否

	// 标准小区id
	private String standId;
	// 标准小区
	private String standName;
	// 旧的用途
	private String oldUseType;
	
	// 人工处理状态, -1 错误数据
	private int status;
	private String baseId;
	private String baseName;
	private String versionDate;
	
	@Field(value="agent_name")
	private String agentName;
	
	@Field(value="agent_tel")
	private String agentTel;
	
	private boolean synched; // 是否需要同步
	
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPicHuxing() {
		return picHuxing;
	}

	public void setPicHuxing(String picHuxing) {
		this.picHuxing = picHuxing;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public GeoJsonPoint getGeometry() {
		return geometry;
	}

	public void setGeometry(GeoJsonPoint geometry) {
		this.geometry = geometry;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(boolean verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public boolean isCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(boolean codeStatus) {
		this.codeStatus = codeStatus;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
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

	public RegionModel getRegions() {
		return regions;
	}

	public void setRegions(RegionModel regions) {
		this.regions = regions;
	}

	public LoadInfoModel getLoadInfo() {
		return loadInfo;
	}

	public void setLoadInfo(LoadInfoModel loadInfo) {
		this.loadInfo = loadInfo;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public ErshoufangExtend getExtension() {
		return extension;
	}

	public void setExtension(ErshoufangExtend extension) {
		this.extension = extension;
	}

	public List<HousePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<HousePhoto> photos) {
		this.photos = photos;
	}

	public boolean isXin() {
		return xin;
	}

	public void setXin(boolean xin) {
		this.xin = xin;
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

	public String getOldUseType() {
		return oldUseType;
	}

	public void setOldUseType(String oldUseType) {
		this.oldUseType = oldUseType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(String versionDate) {
		this.versionDate = versionDate;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentTel() {
		return agentTel;
	}

	public void setAgentTel(String agentTel) {
		this.agentTel = agentTel;
	}

	public boolean isSynched() {
		return synched;
	}

	public void setSynched(boolean synched) {
		this.synched = synched;
	}
	
}
