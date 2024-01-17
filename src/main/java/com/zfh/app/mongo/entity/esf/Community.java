package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.RegionModel;

/**
 * 中介小区
 * @author CB
 * 
 * @dateTime 2019年8月1日上午10:11:07
 */
@Document(collection = "esf_community")
public class Community implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	// 小区封面图片
	private String picUrl;

	// 小区名
	private String community;
	
    // 小区拼音
    private String pinyin;
    
    // 小区拼音缩写
    private String letter;
    
    // 小区首字母索引, 即第一个汉字首字母
    private String firstLetter;

	// 原网站小区地址
	private String url;
	
	// MD5(url)
	private String md5Url;	

	// 小区均价
	private String unitPrice;

	// 出售数量
	private String sellingCount;

	// 出租数量
	private String sentCount;

	// 建成时间
	private String buildYear;

	// 数据来源
	private String dataFrom;

	// 创建时间
	private String createTime;

	// 修改时间
	private String updateTime;

	// 停车位
	private String numParking;

	// 单元数量
	private String unitCount;

	// 房子数量
	private String houseCount;

	// 开发商
	private String developers;

	// 物业公司
	private String propertyCompany;

	// 物业费用
	private String propertyFee;

	// 中介门店
	private String nStore;

	/**
	 * 自定义
	 */
	// 标准小区id
	private String standId;

	// 标准小区名
	private String standName;

	// 经纬度
	private String longlat;


	// 综合分析均价（系统计算）
	private BigDecimal averagePrice;

	// 差价比 = （综合分析均价 - 小区均价）/ 小区均价（系统计算）
	private BigDecimal diffRate;
	
	private Integer status;
		
	// 所在区域
	private RegionModel regions;
	
	@DBRef
	private List<HousePhoto> photos;
	
	@DBRef
    private List<School> schools;
	
    // 成交记录
	@org.springframework.data.annotation.Transient
    private List<DealRecord> dealRecords;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
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

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSellingCount() {
		return sellingCount;
	}

	public void setSellingCount(String sellingCount) {
		this.sellingCount = sellingCount;
	}

	public String getSentCount() {
		return sentCount;
	}

	public void setSentCount(String sentCount) {
		this.sentCount = sentCount;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
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

	public String getNumParking() {
		return numParking;
	}

	public void setNumParking(String numParking) {
		this.numParking = numParking;
	}

	public String getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}

	public String getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}

	public String getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(String propertyFee) {
		this.propertyFee = propertyFee;
	}

	public String getnStore() {
		return nStore;
	}

	public void setnStore(String nStore) {
		this.nStore = nStore;
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

	public String getLonglat() {
		return longlat;
	}

	public void setLonglat(String longlat) {
		this.longlat = longlat;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getDiffRate() {
		return diffRate;
	}

	public void setDiffRate(BigDecimal diffRate) {
		this.diffRate = diffRate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public RegionModel getRegions() {
		return regions;
	}

	public void setRegions(RegionModel regions) {
		this.regions = regions;
	}

	public List<HousePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<HousePhoto> photos) {
		this.photos = photos;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public List<DealRecord> getDealRecords() {
		return dealRecords;
	}

	public void setDealRecords(List<DealRecord> dealRecords) {
		this.dealRecords = dealRecords;
	}
}
