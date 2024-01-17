package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 小区评价
 * @author CB
 * 
 * @dateTime 2019年3月25日下午5:18:10
 */
@Document(collection = "esf_community_comments")
public class CommunityComment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	/**
	 * 小区名
	 */
	private String community;
	
	/**
	 * 小区链接
	 */
	private String md5CommunityUrl;
	
	/**
	 * 经纪人
	 */
	private String agentName;
	
	/**
	 * 经纪人公司
	 */
	private String agentCompany;
	
	/**
	 * 经纪人头像
	 */
	private String agentAvatar;
	
	/**
	 * 发布时间
	 */
	private String publishTime;
	
	/**
	 * 反馈和评论
	 */
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getMd5CommunityUrl() {
		return md5CommunityUrl;
	}

	public void setMd5CommunityUrl(String md5CommunityUrl) {
		this.md5CommunityUrl = md5CommunityUrl;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCompany() {
		return agentCompany;
	}

	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
	}

	public String getAgentAvatar() {
		return agentAvatar;
	}

	public void setAgentAvatar(String agentAvatar) {
		this.agentAvatar = agentAvatar;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
