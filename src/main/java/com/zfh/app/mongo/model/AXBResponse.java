package com.zfh.app.mongo.model;

public class AXBResponse {

	private String resultcode;
	
	private String resultdesc;
	
	private String subscriptionId;
	
	private String relationNum;
	
	private String callDirection;
	
	private String duration; //绑定关系保持时间，单位为秒，0表示永不过期。
	
	private String maxDuration;

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

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getRelationNum() {
		return relationNum;
	}

	public void setRelationNum(String relationNum) {
		this.relationNum = relationNum;
	}

	public String getCallDirection() {
		return callDirection;
	}

	public void setCallDirection(String callDirection) {
		this.callDirection = callDirection;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(String maxDuration) {
		this.maxDuration = maxDuration;
	}
	
}
