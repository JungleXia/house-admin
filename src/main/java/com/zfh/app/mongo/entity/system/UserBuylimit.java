package com.zfh.app.mongo.entity.system;


import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.service.system.SysBuylimit;

/**
 * 用户限购条件(一对多)
 * 
 * @author CB
 * 
 * @dateTime 2019年5月10日上午11:29:38
 */
@Document(collection = "user_buylimit")
public class UserBuylimit implements Serializable{

	@Id
	private String id;
	// 用户id
	private String userId;
	// 用户限购条件设置
	@DBRef
	private SysBuylimit buylimit;
	// 用户选择
	private String choose;
	
	private String createTime;
	
	private boolean expired;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public SysBuylimit getBuylimit() {
		return buylimit;
	}

	public void setBuylimit(SysBuylimit buylimit) {
		this.buylimit = buylimit;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
}
