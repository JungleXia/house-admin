package com.zfh.app.mongo.entity.esf;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 标准小区-价格记录
 * 
 * @author CB
 * 
 * @dateTime 2019年5月24日下午4:33:53
 */
@Document(collection = "esf_stand_community_price")
public class StandCommunityPrice {

	@Id
	private String id;

	@Indexed(unique = true)
	private String standId;
	
	/**
	 * 标准小区名
	 */
	private String standName;

	/**
	 * 用途
	 */
	private String useType;

	/**
	 * 状态，是否参与分析 0-参与；1-不参与
	 */
	private String status;

	/**
	 * 更新时间
	 */
	private String updateTime;
	
	
	private String city;
	
	/**
	 * 一级区域
	 */
	private String region;

	/**
	 * 二级区域
	 */
	private String regionSub;
	
	/**
	 * 基础小区id
	 */
	private String baseId;
	
	/**
	 * 基础小区名称
	 */
	private String baseName;
	
	/**
	 * 基础小区标注
	 */
	private String baseMark;
	
	private boolean synched; // 是否需要同步
	
	private BigDecimal averagePrice;	// 当前均价

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionSub() {
		return regionSub;
	}

	public void setRegionSub(String regionSub) {
		this.regionSub = regionSub;
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

	public String getBaseMark() {
		return baseMark;
	}

	public void setBaseMark(String baseMark) {
		this.baseMark = baseMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isSynched() {
		return synched;
	}

	public void setSynched(boolean synched) {
		this.synched = synched;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	
}
