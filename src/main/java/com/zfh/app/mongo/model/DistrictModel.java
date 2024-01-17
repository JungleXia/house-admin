package com.zfh.app.mongo.model;

import java.util.List;

public class DistrictModel {
	private String district;
	private String districtNo;
	private String districtCode;
	private String districtPinyin;
	private String districtInitials;
	private List<BlockModel> blocks;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrictNo() {
		return districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictPinyin() {
		return districtPinyin;
	}

	public void setDistrictPinyin(String districtPinyin) {
		this.districtPinyin = districtPinyin;
	}

	public String getDistrictInitials() {
		return districtInitials;
	}

	public void setDistrictInitials(String districtInitials) {
		this.districtInitials = districtInitials;
	}

	public List<BlockModel> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<BlockModel> blocks) {
		this.blocks = blocks;
	}

}
