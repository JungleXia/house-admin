package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_city")
public class City implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String province;
    private String provinceCode;
    private String provincePinyin;	// 拼音
    private String provinceInitials;// 首字母
	private String domain;   		// 城市域名
	private String city; 	 		// 城市名
	private String cityCode; 		// 城市编码
	private String cityImg;  		// 城市封面图片url
    private String cityPinyin;		// 拼音
    private String cityInitials;	// 首字母
	private String longitude;
	private String latitude;
	private boolean display; 		// 是否展示，boolean类型的属性值不建议设置为is开头，否则会引起rpc框架的序列化异常。
	private int sort;		 		// 排序号，从小到大
	private boolean hot;			// 是否是热门城市
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
	public String getProvincePinyin() {
		return provincePinyin;
	}
	public void setProvincePinyin(String provincePinyin) {
		this.provincePinyin = provincePinyin;
	}
	public String getProvinceInitials() {
		return provinceInitials;
	}
	public void setProvinceInitials(String provinceInitials) {
		this.provinceInitials = provinceInitials;
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
	public String getCityImg() {
		return cityImg;
	}
	public void setCityImg(String cityImg) {
		this.cityImg = cityImg;
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
	public boolean isHot() {
		return hot;
	}
	public void setHot(boolean hot) {
		this.hot = hot;
	}
	
}
