package com.zfh.app.mongo.service.system;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 限购条件-结果
 * 
 * @author CB
 * 
 * @dateTime 2019年5月10日上午11:29:38
 */
@Document(collection = "sys_buylimit_answer")
public class SysBuylimitAnswer implements Serializable{

	@Id
	private String id;
	// 组id
	private String groupId;
	// 限购城市
	private String city;
	// 城市编码
	private String cityCode;
	// 名称
	private String name;
	// 户籍
	private String number1;
	// 身份
	private String number2;
	// 住房
	private String number3;
	// 房贷情况
	private String number4;
	// 是否限购 ：不限购、禁止购买
	private String buylimit;
	// 首付比：禁贷、3成、5成、7成
	private String shoufu;
	
	private int buylimitNumber;
	
	private int shoufuNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber2() {
		return number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	public String getNumber3() {
		return number3;
	}

	public void setNumber3(String number3) {
		this.number3 = number3;
	}

	public String getNumber4() {
		return number4;
	}

	public void setNumber4(String number4) {
		this.number4 = number4;
	}

	public String getBuylimit() {
		return buylimit;
	}

	public void setBuylimit(String buylimit) {
		this.buylimit = buylimit;
	}

	public String getShoufu() {
		return shoufu;
	}

	public void setShoufu(String shoufu) {
		this.shoufu = shoufu;
	}


	public int getBuylimitNumber() {
		return buylimitNumber;
	}

	public void setBuylimitNumber(int buylimitNumber) {
		this.buylimitNumber = buylimitNumber;
	}

	public int getShoufuNumber() {
		return shoufuNumber;
	}

	public void setShoufuNumber(int shoufuNumber) {
		this.shoufuNumber = shoufuNumber;
	}

}
