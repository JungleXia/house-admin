package com.zfh.app.mongo.model;

/**
 * @type 嵌入
 * 贷款信息
 * 
 * @author CB
 * 
 * @dateTime 2019年3月25日下午2:25:17
 */
public class LoadInfoModel {

	private double downPaymentPercent; 	// 首付比例
	private int downPaymentAmount; 		// 参考首付，可能需要计算（不能直接爬取）（万元）
	private int monthlyPayment; 		// 参考月供，可能需要计算（元）
	private int loanAmount; 			// 贷款金额（万元）
	private int loanDeadline; 			// 贷款月数（月）
	private int loanLixi; 				// 合计利息（万元）
	private int huankuan; 				// 本息合计，还款总额（万元）
	private double loanRate; 			// 贷款利率
	private int loanType; 				// 贷款类型

	public double getDownPaymentPercent() {
		return downPaymentPercent;
	}

	public void setDownPaymentPercent(double downPaymentPercent) {
		this.downPaymentPercent = downPaymentPercent;
	}

	public int getDownPaymentAmount() {
		return downPaymentAmount;
	}

	public void setDownPaymentAmount(int downPaymentAmount) {
		this.downPaymentAmount = downPaymentAmount;
	}

	public int getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(int monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getLoanDeadline() {
		return loanDeadline;
	}

	public void setLoanDeadline(int loanDeadline) {
		this.loanDeadline = loanDeadline;
	}

	public int getLoanLixi() {
		return loanLixi;
	}

	public void setLoanLixi(int loanLixi) {
		this.loanLixi = loanLixi;
	}

	public int getHuankuan() {
		return huankuan;
	}

	public void setHuankuan(int huankuan) {
		this.huankuan = huankuan;
	}

	public double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(double loanRate) {
		this.loanRate = loanRate;
	}

	public int getLoanType() {
		return loanType;
	}

	public void setLoanType(int loanType) {
		this.loanType = loanType;
	}

}
