package com.zfh.app.fang.vo;

import java.io.Serializable;

public class PieDataModel implements Serializable{
	
	private static final long serialVersionUID = -1406152875649592872L;
	
	int value;
	String name;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
