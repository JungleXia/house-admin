package com.zfh.app.mongo.entity.esf;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_house_code_unfind")
public class HouseCodeUnFind {

	@Id
	private String id;
	
	private String houseId;

	private String houseCode;
	
	private boolean isfind;
	
	private String createTime;

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

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public boolean isIsfind() {
		return isfind;
	}

	public void setIsfind(boolean isfind) {
		this.isfind = isfind;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
