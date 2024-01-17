package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lj_esf_community")
public class LjEsfCommunity  implements Serializable{

	/** serialVersionUID*/  
	private static final long serialVersionUID = -7008633486089835446L;
	
	@Id
	private String id;
	/**
	 * 小区链接
	 */
	private String url;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String district;
	/**
	 * 商圈|街道
	 */
	private String block;
	/**
	 * 编号
	 */
	private String cNo;
	/**
	 * 在售房源数量
	 */
	private Integer numbers;
	/**
	 * 小区均价
	 */
	private BigDecimal unitPrice;
	/**
	 * 小区名
	 */
	private String community;
	/**
	 * 成交周期
	 */
	private Integer dealDay;
	/**
	 * dealDay内成交数量
	 */
	private Integer dealNum;
	/**
	 * 在租房源数量
	 */
	private Integer rentNum;
	/**
	 * 建筑结构
	 */
	private String buildType;
	/**
	 * 建筑年代
	 */
	private String buildYear;
	
	/**
	 * 标签
	 */
	private List<String> tags;
	
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
	 * 房源数量变化
	 */
	private Integer diffNum;
	/**
	 * 均价涨跌
	 */
	private Integer diffPrice;
	
	/**
	 * 来源页面
	 */
	private String pageUrl;
	/**
	 * 状态 -1：跌价，1：涨价，2：上新
	 */
	private Integer status;
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
	public String getcNo() {
		return cNo;
	}
	public void setcNo(String cNo) {
		this.cNo = cNo;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
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
	public Integer getDealDay() {
		return dealDay;
	}
	public void setDealDay(Integer dealDay) {
		this.dealDay = dealDay;
	}
	public Integer getDealNum() {
		return dealNum;
	}
	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}
	public Integer getRentNum() {
		return rentNum;
	}
	public void setRentNum(Integer rentNum) {
		this.rentNum = rentNum;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
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
	public Integer getDiffNum() {
		return diffNum;
	}
	public void setDiffNum(Integer diffNum) {
		this.diffNum = diffNum;
	}
	public Integer getDiffPrice() {
		return diffPrice;
	}
	public void setDiffPrice(Integer diffPrice) {
		this.diffPrice = diffPrice;
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
}
