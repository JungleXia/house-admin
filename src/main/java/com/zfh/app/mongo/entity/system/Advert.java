package com.zfh.app.mongo.entity.system;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sys_advert")
public class Advert implements Serializable {
	@Id
	private String id;
	// 描述
	private String description; 
	// 源链接
	private String imageUrl; 
	// 跳转类型， native： 本地；html：外部html
	private String targetType; 
	// targetType为native 时为空值；当targetType 为 html 时跳转链接
	private String targetUrl; 
	 // 当targetType 为 native
	// 时，跳转页面类型：0：啥都不跳，1：二手房详情，2：新房详情；当targetType 为 html时为空值
	private int target;
	// 参数，当targetType 为 native 时使用 格式
	// 如"id=xxxxxx&name=xxxxx&agent=xxxxx"
	private String params; 
	// 展示频次，按小时计算
	private String frequeHour; 
	// 是否有效
	private boolean expired; 
	// 广告类型， 1 ：app开机广告; 2: app首页轮播图广告; 3: app房源列表页广告； 4: app二手房详情广告; 5:app新房详情页广告， 11：网站首页，21：移动端首页；
	private int type; 
	// 排序号
	private int no; 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getFrequeHour() {
		return frequeHour;
	}

	public void setFrequeHour(String frequeHour) {
		this.frequeHour = frequeHour;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
