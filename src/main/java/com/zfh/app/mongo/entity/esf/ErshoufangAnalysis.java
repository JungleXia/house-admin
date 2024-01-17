package com.zfh.app.mongo.entity.esf;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_ershoufang_analysis")
public class ErshoufangAnalysis {

	// 这个id 可以存houseId， 这样更新保存的时候就不需要判断
	@Id
	private String id;
	// 二手房id
	private String houseId;
	// 推荐原因一（文字）
	private String reason1;
	// 推荐原因二（文字）
	private String reason2;
	// 价格分析-单价（文字）
	private String unitP;
	// 价格分析-总价（文字）
	private String totalP;
	// 价格分析-均价（文字）
	private String avgP;
	// 省钱攻略（文字）
	private String strategy;
	// 房源详情（文字）
	private String houseDetails;
	// 单价 (数值)
	private BigDecimal unitPrice;
	// 小区同户型均价 (数值)
	private BigDecimal sUnitPrice;
	// 低于同户型均价 (数值)
	private BigDecimal belowUnit;
	// 折扣（单位：折） (数值)
	private BigDecimal disRate;
	// 本房源总价 (数值)
	private BigDecimal totalPrice;
	// 本小区同户型总价 (数值)
	private BigDecimal sTotalPrice;
	// 总价比均价低 (数值)
	private BigDecimal belowTotal;

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
	
	public String getReason1() {
		return reason1;
	}

	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}

	public String getReason2() {
		return reason2;
	}

	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

	public String getUnitP() {
		return unitP;
	}

	public void setUnitP(String unitP) {
		this.unitP = unitP;
	}

	public String getTotalP() {
		return totalP;
	}

	public void setTotalP(String totalP) {
		this.totalP = totalP;
	}

	public String getAvgP() {
		return avgP;
	}

	public void setAvgP(String avgP) {
		this.avgP = avgP;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getHouseDetails() {
		return houseDetails;
	}

	public void setHouseDetails(String houseDetails) {
		this.houseDetails = houseDetails;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getsUnitPrice() {
		return sUnitPrice;
	}

	public void setsUnitPrice(BigDecimal sUnitPrice) {
		this.sUnitPrice = sUnitPrice;
	}

	public BigDecimal getBelowUnit() {
		return belowUnit;
	}

	public void setBelowUnit(BigDecimal belowUnit) {
		this.belowUnit = belowUnit;
	}

	public BigDecimal getDisRate() {
		return disRate;
	}

	public void setDisRate(BigDecimal disRate) {
		this.disRate = disRate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getsTotalPrice() {
		return sTotalPrice;
	}

	public void setsTotalPrice(BigDecimal sTotalPrice) {
		this.sTotalPrice = sTotalPrice;
	}

	public BigDecimal getBelowTotal() {
		return belowTotal;
	}

	public void setBelowTotal(BigDecimal belowTotal) {
		this.belowTotal = belowTotal;
	}

}
