package com.zfh.app.mongo.model;

import org.springframework.data.annotation.Transient;

import com.mysiteforme.admin.util.MD5Util;


public class AgentModel {

	private String url;
	private String rate;
	private String tel;
	private String avatar;
	@Transient
	private String md5Url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getMd5Url() {
		if (org.apache.commons.lang.StringUtils.isNotEmpty(md5Url)) {
			return MD5Util.MD5(md5Url);
		}
		return null;
	}
	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}

}
