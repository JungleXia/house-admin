package com.zfh.app.mongo.entity.esf;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_house_code")
public class HouseCode {

	@Id
	private String id;
	
	private long refId;
	// 官方房源编号
	@Indexed(unique=true)
	private String houseCode;
	//  用途
	private String useType;
	// 前台显示用途
	private String targetType;
	// 项目名称
	private String projectName;
	// 面积
	private String area;
	// 意向价格
	private String intentPrice;
	// 所在地区
	private String region;
	//房源编码状态* 0-无效； 1-有效
	private boolean codeStatus;
	// 房源认证（是否是真实房源）
	private boolean verifyStatus;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;
	
	/**
	 * 标准小区
	 */
	private String standId;
	
	/**
	 * 标准小区
	 */
	private String standName;
	
	private String baseId;
	
	private String baseName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIntentPrice() {
		return intentPrice;
	}

	public void setIntentPrice(String intentPrice) {
		this.intentPrice = intentPrice;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(boolean codeStatus) {
		this.codeStatus = codeStatus;
	}

	public boolean isVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(boolean verifyStatus) {
		this.verifyStatus = verifyStatus;
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

}
