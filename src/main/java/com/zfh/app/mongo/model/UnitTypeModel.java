package com.zfh.app.mongo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 挂牌分析
 * @author CB
 * 
 * @dateTime 2019年5月23日下午4:07:07
 */
public class UnitTypeModel implements Serializable{
	// 三室
	private String name;
	// 3
	private int room;
	// 挂牌套数
	private int guapai;
	// 总价最低
	private BigDecimal minTotal;
	// 单价最低
	private BigDecimal minUnit;
	// 平均单价
	private BigDecimal avgUnit;
	// 同户型房源数据
	private SameRoom sameList;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getGuapai() {
		return guapai;
	}

	public void setGuapai(int guapai) {
		this.guapai = guapai;
	}

	public BigDecimal getMinTotal() {
		return minTotal;
	}

	public void setMinTotal(BigDecimal minTotal) {
		this.minTotal = minTotal;
	}

	public BigDecimal getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(BigDecimal minUnit) {
		this.minUnit = minUnit;
	}

	public BigDecimal getAvgUnit() {
		return avgUnit;
	}

	public void setAvgUnit(BigDecimal avgUnit) {
		this.avgUnit = avgUnit;
	}

	public SameRoom getSameList() {
		return sameList;
	}

	public void setSameList(SameRoom sameList) {
		this.sameList = sameList;
	}

}
