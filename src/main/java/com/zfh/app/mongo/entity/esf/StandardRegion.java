package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 平台标准区域
 * @author CB
 * 
 * @dateTime 2019年4月2日上午10:31:06
 */
@Document(collection = "esf_standard_region")
public class StandardRegion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
    private String province;
    private String provinceCode;
    private String privincePinyin;
    private String privinceInitials;
    private String city;
    private String cityCode;
    private String cityPinyin;
    private String cityInitials;
    private String district;
    private String districtNo;
    private String districtCode;
    private String districtPinyin;
    private String districtInitials;
    private String blockName;
    private String blockPinyin;
    private String blockInitials;
    private String md5Region; //md5(city-district-blockName)
    private String status;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getPrivincePinyin() {
		return privincePinyin;
	}
	public void setPrivincePinyin(String privincePinyin) {
		this.privincePinyin = privincePinyin;
	}
	public String getPrivinceInitials() {
		return privinceInitials;
	}
	public void setPrivinceInitials(String privinceInitials) {
		this.privinceInitials = privinceInitials;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityPinyin() {
		return cityPinyin;
	}
	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}
	public String getCityInitials() {
		return cityInitials;
	}
	public void setCityInitials(String cityInitials) {
		this.cityInitials = cityInitials;
	}
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
	public String getBlockInitials() {
		return blockInitials;
	}
	public void setBlockInitials(String blockInitials) {
		this.blockInitials = blockInitials;
	}
	public String getMd5Region() {
		return md5Region;
	}
	public void setMd5Region(String md5Region) {
		this.md5Region = md5Region;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
