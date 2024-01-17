package com.zfh.app.mongo.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.zfh.app.mongo.entity.esf.House;

public class SameRoom implements Serializable{

	// 标题， 小区名称·居室
	private String title;
	// 总价最低
	@DBRef
	private House minTotal;
	// 单价最低
	@DBRef
	private House minUnit;
	// 其他房源
	@DBRef
	private List<House> others;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public House getMinTotal() {
		return minTotal;
	}

	public void setMinTotal(House minTotal) {
		this.minTotal = minTotal;
	}

	public House getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(House minUnit) {
		this.minUnit = minUnit;
	}

	public List<House> getOthers() {
		return others;
	}

	public void setOthers(List<House> others) {
		this.others = others;
	}

	
}

