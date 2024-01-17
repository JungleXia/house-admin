package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_district")
public class District implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4366386996837208498L;
	
	@Id
	private String id;
	private String province;
	private String domain;   		// 城市域名
	private String city; 	 		// 城市名
	private String cityCode; 		// 城市编码
	private String longitude;
	private String latitude;
	private String district; 		// 行政区 
    private String districtCode; 	// 行政编码
    private String districtPinyin;	// 拼音
    private String districtInitials;// 首字母
	private boolean display; 		// 是否展示，boolean类型的属性值不建议设置为is开头，否则会引起rpc框架的序列化异常。
	private int sort;				// 排序号从小到大
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
