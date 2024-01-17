package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.CostCompareModel;

/**
 *  新房分析数据
 * @author CB
 * 
 * @dateTime 2019年7月11日下午2:58:47
 */
@Document(collection = "xin_house_analysis")
public class XinAnalysis implements Serializable{
	@Id
	private String id;
	// 新房id
	private String houseId;
	// 推荐原因
	private String reason; 
	// 价格对比分析
	private CostCompareModel cost;
	// 价值分析
	private String worth;
	// 距离买房中心位置文案
	private String centerTip;
	// 买房中心区域
	private String centerDistirct;
	// 买房中心，如果为空则未设置买房中心
	private String centerAddr;
	// 乘车时间
	private String drivingTime;
	// 买房中心经纬度
	private String centerPoint;
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public CostCompareModel getCost() {
		return cost;
	}
	public void setCost(CostCompareModel cost) {
		this.cost = cost;
	}
	public String getWorth() {
		return worth;
	}
	public void setWorth(String worth) {
		this.worth = worth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	public String getCenterDistirct() {
		if (centerDistirct == null)
			return "";
		return centerDistirct;
	}
	public void setCenterDistirct(String centerDistirct) {
		this.centerDistirct = centerDistirct;
	}
	public String getCenterAddr() {
		if (centerAddr == null)
			return "";
		return centerAddr;
	}
	public void setCenterAddr(String centerAddr) {
		this.centerAddr = centerAddr;
	}
	public String getCenterPoint() {
		if (centerPoint == null)
			return "";
		return centerPoint;
	}
	public void setCenterPoint(String centerPoint) {
		this.centerPoint = centerPoint;
	}
	public String getDrivingTime() {
		if (drivingTime == null)
			return "";
		return drivingTime;
	}
	public void setDrivingTime(String drivingTime) {
		this.drivingTime = drivingTime;
	}
	public String getCenterTip() {
		if (centerTip == null)
			return "";
		return centerTip;
	}
	public void setCenterTip(String centerTip) {
		this.centerTip = centerTip;
	}
	
}
