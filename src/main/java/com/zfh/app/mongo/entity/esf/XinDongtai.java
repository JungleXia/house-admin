package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 新房-动态
 * @author CB
 * 
 * @dateTime 2019年5月7日下午2:08:02
 */
@Document(collection = "xin_dongtai")
public class XinDongtai implements Serializable{

	@Id
	private String id;
	private String projectId;	// 楼盘id 
	
	private String projectName;	// 楼盘名称
	
	private String title;		// 标题
	
	private String summary;		// 简介
	
	private String content;		// 内容
	
	private String createDate;	// 创建日期
		
	private String userCode;	// 绑定用户

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUserCode() {
		if (userCode == null)
			return "";
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}
