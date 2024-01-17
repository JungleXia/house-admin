package com.zfh.app.mongo.entity.system;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sys_access_log")
@CompoundIndexes({
	// name：索引名称 def：字段(1正序 -1倒序) unique：是否唯一索引
	// 直接加到字段上面没用
	@CompoundIndex(def = "{clientKey:1, title:1}", background = true)
})
public class AccessLog implements Serializable{

	@Id
	private String id;

	/**
	 * 客户端ip
	 */
	private String clientIp;

	/**
	 * access token
	 */
	private String token;

	/**
	 * 端口来源
	 */
	private String client;
	
	/**
	 * 唯一识别码
	 */
	private String clientKey;
	
	/**
	 * 描述
	 */
	private String title;
	
	/**
	 * 调用方法
	 */
	private String classMethod;

	/**
	 * http请求方式 POST GET
	 */
	private String httpMethod;

	/**
	 * 客户端浏览器信息
	 */
	private String browser;

	/**
	 * 请求路径
	 */
	private String reqUri;

	/**
	 * 请求参数
	 */
	private String reqParams;
	
	/**
	 * 请求头
	 */
	private String reqHeaders;

	/**
	 * 响应结果
	 */
	private String response;

	/**
	 * 耗时(毫秒)
	 */
	private Integer processTime;

	/**
	 * 记录创建时间
	 */
	private String createTime;
	
	@Indexed
	private String createDate;
	
	/**
	 * 请求状态， 1：成功 | 0：失败
	 */
	private String status;
	
	private long datetime;
	
	private boolean checkStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassMethod() {
		return classMethod;
	}

	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getReqUri() {
		return reqUri;
	}

	public void setReqUri(String reqUri) {
		this.reqUri = reqUri;
	}

	public String getReqParams() {
		return reqParams;
	}

	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Integer processTime) {
		this.processTime = processTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public String getReqHeaders() {
		return reqHeaders;
	}

	public void setReqHeaders(String reqHeaders) {
		this.reqHeaders = reqHeaders;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
