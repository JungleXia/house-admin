package com.zfh.app.mongo.service.system;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 限购条件设置
 * 
 * @author CB
 * 
 * @dateTime 2019年5月10日上午11:29:38
 */
@Document(collection = "sys_buylimit")
public class SysBuylimit implements Serializable{

	@Id
	private String id;
	// 组id
	private String groupId;
	// 限购城市
	private String city;
	// 城市编码
	private String cityCode;
	// 排列序号
	private int number;
	// 问题
	private String question;
	// 提示语
	private String tips;
	// 显示优先级
	private int priority;
	// 选项
	@DBRef
	private List<SysBuylimitOption> options;
	// 用户选中的值，用于设置选中状态，如为空，则为选项中的默认选中
	@Transient
	private String choose;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTips() {
		if (tips == null || tips == null) {
			return "";
		}
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<SysBuylimitOption> getOptions() {
		return options;
	}

	public void setOptions(List<SysBuylimitOption> options) {
		this.options = options;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getChoose() {
		if (choose == null)
			return "";
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}
	
}
