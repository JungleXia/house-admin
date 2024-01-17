package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 链家房源数量统计
 * <p>Title: EsfStatistic.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2023</p>  
 * @author Jungle  
 * @date 2023年12月18日
 */
@Document(collection = "esf_statistic")
public class LjEsfStatistic implements Serializable{
	
	/** serialVersionUID*/  
	private static final long serialVersionUID = 1336221069864169430L;
	
	@Id
	private String id;
	/**
	 * 城市编码
	 */
	@Field(value = "city_key")
	private String cityKey;
	/**
	 * 城市
	 */
    private String city;
    /**
     * 区
     */
    private String district;	
    /**
     * 街道|商圈
     */
    private String block;
    /**
     * 链接
     */
    private String url;
    /**
     * 房源数量
     */
    private Integer numbers;
    /**
     * md5Url
     */
    private String md5Url;
    /**
     * 创建时间
     */
    private String createDay;
    /**
     * 上期数量
     */
    private Integer prenums;
    /**
     * 数量涨跌
     */
    private Integer diff;
    /**
     * 统计类型： 1-城市，2-区域，3-街道|商圈
     */
    private Integer type;
    /**
     * 省份
     */
    private String province;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityKey() {
		return cityKey;
	}
	public void setCityKey(String cityKey) {
		this.cityKey = cityKey;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public String getMd5Url() {
		return md5Url;
	}
	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}
	public String getCreateDay() {
		return createDay;
	}
	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
	public Integer getPrenums() {
		return prenums;
	}
	public void setPrenums(Integer prenums) {
		this.prenums = prenums;
	}
	public Integer getDiff() {
		return diff;
	}
	public void setDiff(Integer diff) {
		this.diff = diff;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}
