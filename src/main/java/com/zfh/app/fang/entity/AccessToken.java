package com.zfh.app.fang.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 允许访问的token
 * @author CB
 * 
 * @dateTime 2019年2月26日上午11:27:03
 */
@Entity(name="T_ACCESS_TOKEN")
public class AccessToken implements Serializable{

	private static final long serialVersionUID = -5341127082882391229L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", length = 40, unique = true)
	private String id;
	
	/**
	 * 开发者apiKey
	 */
	@Column(name = "API_KEY", length = 40, columnDefinition="VARCHAR(40) COMMENT '开发者apiKey'")
	private String apiKey;
	
	/**
	 * 手机号，用户登录后和mac绑定
	 */
	@Column(name = "PHONE", length = 40, columnDefinition="VARCHAR(40) COMMENT '手机号，用户登录后和mac绑定'")
	private String phone;
	
	/**
	 * 客户端ip
	 */
	@Column(name = "CLIENT_IP", length = 100, columnDefinition="VARCHAR(100) COMMENT '客户端ip'")
	private String clientIp;
	
	/**
	 * 端口来源
	 */
	@Column(name = "CLIENT", length = 10, columnDefinition="VARCHAR(10) COMMENT '端口来源: app_android|app_ios'")
	private String client;
	
	/**
	 * 唯一识别码
	 */
	@Column(name = "CLIENT_KEY", length = 10, columnDefinition="VARCHAR(10) COMMENT '唯一识别码'")
	private String clientKey;
	
	/**
	 * token字符串
	 */
	@Column(name = "TOKEN", length = 100, columnDefinition="VARCHAR(100) COMMENT 'token字符串'")
	private String token;
	
	/**
	 * 刷新时间
	 */
	@Column(name = "REFRESH_TIME", length = 30, columnDefinition="VARCHAR(100) COMMENT '刷新时间字符串'")
	private String refreshTime;
	
	/**
	 * 到期时间
	 */
	@Column(name = "EXPIRE_TIME", length = 30, columnDefinition="VARCHAR(100) COMMENT '到期时间字符串'")
	private String expireTime;
	
	/**
	 * 有效期(分钟)
	 */
	@Column(name = "VALID_MINUTES", length = 10, columnDefinition="INT(10) COMMENT '有效期(分钟)'")
	private Integer validMinutes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getValidMinutes() {
		return validMinutes;
	}

	public void setValidMinutes(Integer validMinutes) {
		this.validMinutes = validMinutes;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
	
	
}
