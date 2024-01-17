package com.zfh.app.mongo.model;

import java.io.Serializable;

// 新房费用对比
public class CostCompareModel implements Serializable{
	// 新房费用
	private CostModel xinf;
	// 二手房费用
	private CostModel esf;

	public CostModel getXinf() {
		return xinf;
	}

	public void setXinf(CostModel xinf) {
		this.xinf = xinf;
	}

	public CostModel getEsf() {
		return esf;
	}

	public void setEsf(CostModel esf) {
		this.esf = esf;
	}

}
