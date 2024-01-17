package com.zfh.app.mongo.model;

import java.io.Serializable;

public class CommunitySearchModel implements Serializable{

	private String standId;		// 标准id，用于查询
	private String community;	// 小区名
	private String district;	// 大区域
	private String blockName;	// 小区域
	private String address;		// 详细地址
	private int total;			// 套数
	private String useType;		// 用途
	
	public String getStandId() {
		if (standId == null) {
			return "";
		}
		return standId;
	}

	public void setStandId(String standId) {
		this.standId = standId;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

}