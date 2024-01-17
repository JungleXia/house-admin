package com.zfh.app.mongo.model;

import java.io.Serializable;

// 费用
public class CostModel implements Serializable{
	// 首付
	private String downpay;
	// 契税
	private String deedt;
	// 增值税
	private String vat;
	// 个税
	private String tax;
	// 赎楼税
	private String redt;
	// 中介费
	private String agcfee;
	// 总计
	private String total;

	public String getDownpay() {
		return downpay;
	}

	public void setDownpay(String downpay) {
		this.downpay = downpay;
	}

	public String getDeedt() {
		return deedt;
	}

	public void setDeedt(String deedt) {
		this.deedt = deedt;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getRedt() {
		return redt;
	}

	public void setRedt(String redt) {
		this.redt = redt;
	}

	public String getAgcfee() {
		return agcfee;
	}

	public void setAgcfee(String agcfee) {
		this.agcfee = agcfee;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
