package com.zfh.app.fang.enumerate;

public enum DataFrom {

	ZHONG_YUAN("中原"),

	LIAN_JIA("链家"),
	
	QFANG("Q房网"),

	LE_YOU_JIA("乐有家"),
	;

	private String dataFrom;

	public String getDataFrom() {
		return dataFrom;
	}

	DataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

}