package com.zfh.app.mongo.entity.system;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 华为话单通知
 * @author CB
 * 
 * @dateTime 2019年11月22日下午2:50:51
 */
@Document(collection = "user_telbill_notify")
public class BillNotifyRecord {
	
	@Id
	private String id;
	
	private int direction;

	private String spId;

	private String appKey;

	private String icid;

	private String bindNum;

	private String sessionId;

	private String callerNum;

	private String calleeNum;

	private String fwdDisplayNum;

	private String fwdDstNum;

	private String callInTime;

	private String fwdStartTime;

	private String fwdAlertingTime;

	private String fwdAnswerTime;

	private String callEndTime;

	private int fwdUnaswRsn;

	private String failTime;

	private int ulFailReason;

	private int sipStatusCode;

	/**
	 * 该字段用于录音标识，参数值范围如：0：表示未录音;1：表示有录音
	 */
	private int recordFlag;

	private String recordStartTime;

	/**
	 * 录音文件名
	 */
	private String recordObjectName;
	
	/**
	 * 录音文件名所在的目录名
	 */
	private String recordBucketName;

	/**
	 * 	存放录音文件的域名
	 */
	private String recordDomain;

	private String serviceType;

	private String hostName;

	/**
	 * 绑定ID
	 */
	private String subscriptionId;

	private int callOutUnaswRsn;

	private int ttsPlayTimes;

	private int ttsTransDuration;

	private String mptyId;
	
	private String createTime;
	
	/**
	 * 是否已经下载录音文件 （录音文件在华为云只保留7天）
	 */
	private boolean isDownloaded;
	
	/**
	 * 华为云录音文件地址
	 */
	private String downloadUrl;
	
	/**
	 * 上传录音文件到oss
	 */
	private String ossUrl;
	
	private String downTime;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getIcid() {
		return icid;
	}

	public void setIcid(String icid) {
		this.icid = icid;
	}

	public String getBindNum() {
		return bindNum;
	}

	public void setBindNum(String bindNum) {
		this.bindNum = bindNum;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public String getFwdDisplayNum() {
		return fwdDisplayNum;
	}

	public void setFwdDisplayNum(String fwdDisplayNum) {
		this.fwdDisplayNum = fwdDisplayNum;
	}

	public String getFwdDstNum() {
		return fwdDstNum;
	}

	public void setFwdDstNum(String fwdDstNum) {
		this.fwdDstNum = fwdDstNum;
	}

	public String getCallInTime() {
		return callInTime;
	}

	public void setCallInTime(String callInTime) {
		this.callInTime = callInTime;
	}

	public String getFwdStartTime() {
		return fwdStartTime;
	}

	public void setFwdStartTime(String fwdStartTime) {
		this.fwdStartTime = fwdStartTime;
	}

	public String getFwdAlertingTime() {
		return fwdAlertingTime;
	}

	public void setFwdAlertingTime(String fwdAlertingTime) {
		this.fwdAlertingTime = fwdAlertingTime;
	}

	public String getFwdAnswerTime() {
		return fwdAnswerTime;
	}

	public void setFwdAnswerTime(String fwdAnswerTime) {
		this.fwdAnswerTime = fwdAnswerTime;
	}

	public String getCallEndTime() {
		return callEndTime;
	}

	public void setCallEndTime(String callEndTime) {
		this.callEndTime = callEndTime;
	}

	public int getFwdUnaswRsn() {
		return fwdUnaswRsn;
	}

	public void setFwdUnaswRsn(int fwdUnaswRsn) {
		this.fwdUnaswRsn = fwdUnaswRsn;
	}

	public String getFailTime() {
		return failTime;
	}

	public void setFailTime(String failTime) {
		this.failTime = failTime;
	}

	public int getUlFailReason() {
		return ulFailReason;
	}

	public void setUlFailReason(int ulFailReason) {
		this.ulFailReason = ulFailReason;
	}

	public int getSipStatusCode() {
		return sipStatusCode;
	}

	public void setSipStatusCode(int sipStatusCode) {
		this.sipStatusCode = sipStatusCode;
	}

	public int getRecordFlag() {
		return recordFlag;
	}

	public void setRecordFlag(int recordFlag) {
		this.recordFlag = recordFlag;
	}

	public String getRecordStartTime() {
		return recordStartTime;
	}

	public void setRecordStartTime(String recordStartTime) {
		this.recordStartTime = recordStartTime;
	}

	public String getRecordObjectName() {
		return recordObjectName;
	}

	public void setRecordObjectName(String recordObjectName) {
		this.recordObjectName = recordObjectName;
	}

	public String getRecordBucketName() {
		return recordBucketName;
	}

	public void setRecordBucketName(String recordBucketName) {
		this.recordBucketName = recordBucketName;
	}

	public String getRecordDomain() {
		return recordDomain;
	}

	public void setRecordDomain(String recordDomain) {
		this.recordDomain = recordDomain;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getCallOutUnaswRsn() {
		return callOutUnaswRsn;
	}

	public void setCallOutUnaswRsn(int callOutUnaswRsn) {
		this.callOutUnaswRsn = callOutUnaswRsn;
	}

	public int getTtsPlayTimes() {
		return ttsPlayTimes;
	}

	public void setTtsPlayTimes(int ttsPlayTimes) {
		this.ttsPlayTimes = ttsPlayTimes;
	}

	public int getTtsTransDuration() {
		return ttsTransDuration;
	}

	public void setTtsTransDuration(int ttsTransDuration) {
		this.ttsTransDuration = ttsTransDuration;
	}

	public String getMptyId() {
		return mptyId;
	}

	public void setMptyId(String mptyId) {
		this.mptyId = mptyId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDownloaded() {
		return isDownloaded;
	}

	public void setDownloaded(boolean isDownloaded) {
		this.isDownloaded = isDownloaded;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getOssUrl() {
		return ossUrl;
	}

	public void setOssUrl(String ossUrl) {
		this.ossUrl = ossUrl;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}
	
	
}
