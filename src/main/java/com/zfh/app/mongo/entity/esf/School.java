package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_school")
public class School implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
		
	/**
	 * MD5(小区url)
	 */
	private String md5CommunityUrl;
	
	/**
	 * 小区名
	 */
	private String community;
	
	/**
	 *  MD5(学校链接)
	 */
	private String md5Url;
	
	/**
	 * 学校链接
	 */
	private String url;
	
	/**
	 * 学校名称
	 */
	private String name;
	
	/**
	 * 办学性质
	 */
	private String nature;
	
	/**
	 * 类别
	 */
	private String category;
	
	/**
	 * 大区域
	 */
	private String district;
	
	/**
	 * 学校地址
	 */
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMd5CommunityUrl() {
		return md5CommunityUrl;
	}

	public void setMd5CommunityUrl(String md5CommunityUrl) {
		this.md5CommunityUrl = md5CommunityUrl;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getMd5Url() {
		return md5Url;
	}

	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
