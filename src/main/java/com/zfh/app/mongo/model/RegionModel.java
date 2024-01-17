package com.zfh.app.mongo.model;

import org.apache.commons.lang.StringUtils;

public class RegionModel {
		
	private String cityCode;

	private String city;
	
	// 由于历史原因，数据库中存储的城市字段不一致
	private String cityName;
	
	private String district;

	private String districtPinyin;
	
	private String blockName;

	private String blockPinyin;
	
	private String md5Region;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		if (StringUtils.isNotEmpty(cityName)) {
			return cityName;
		}
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

	public String getDistrictPinyin() {
		return districtPinyin;
	}

	public void setDistrictPinyin(String districtPinyin) {
		this.districtPinyin = districtPinyin;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getBlockPinyin() {
		return blockPinyin;
	}

	public void setBlockPinyin(String blockPinyin) {
		this.blockPinyin = blockPinyin;
	}

	public String getMd5Region() {
		return md5Region;
	}

	public void setMd5Region(String md5Region) {
		this.md5Region = md5Region;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
