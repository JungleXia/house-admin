package com.zfh.app.mongo.entity.system;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 用户搜索记录
 * @author CB
 *
 */
@Document(collection = "user_search_history")
public class SearchHistory implements Serializable{
	
	@Id
	private String id;

	private String dateTime;
	
	private String dateString;
	
	private String clientIp;
	
	private String client;
	
	private String clientKey;
	
	private String searchType;
	
	private String searchTxt;
	
	private String result;
	
	// 搜索后点击的结果
	private String standId;
	
	private String communtiy;
	
	private String region;
	
	private String useType;
		
	private int count;
	
	private boolean delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
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

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStandId() {
		if (standId == null) {
			return "";
		}
		return standId;
	}

	public void setStandId(String standId) {
		this.standId = standId;
	}

	public String getCommuntiy() {
		return communtiy;
	}

	public void setCommuntiy(String communtiy) {
		this.communtiy = communtiy;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
