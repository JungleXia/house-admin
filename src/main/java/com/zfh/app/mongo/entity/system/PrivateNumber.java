package com.zfh.app.mongo.entity.system;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 隐私号绑定关系
 * @author CB
 * 
 * @dateTime 2019年8月12日下午2:45:56
 */
@Document(collection = "user_private_number")
public class PrivateNumber implements Serializable{

	@Id
	private String id;
	
	private String callerNum;	// 主叫号码
	
	private String calleeNum;	// 被叫号码
	
	private String relationNum;  // 隐私号码
	
	private String subscriptionId; // 绑定关系id
	
	private String resultcode;
	
	private String resultdesc; 
	
	private String createTime;	// 绑定时间
	
	private String expiredTime;	// 过期时间
	
	private int duration;	// 绑定关系维持时间
	
	private boolean expired;

	private String houseId;
	
	/**
	 * 房源的链接
	 */
	@Transient
	private String url;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCallerNum() {
		return callerNum;
	}

	public void setCallerNum(String callerNum) {
		this.callerNum = callerNum;
	}

	public String getCalleeNum() {
		return calleeNum;
	}

	public void setCalleeNum(String calleeNum) {
		this.calleeNum = calleeNum;
	}

	public String getRelationNum() {
		return relationNum;
	}

	public void setRelationNum(String relationNum) {
		this.relationNum = relationNum;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultdesc() {
		return resultdesc;
	}

	public void setResultdesc(String resultdesc) {
		this.resultdesc = resultdesc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
