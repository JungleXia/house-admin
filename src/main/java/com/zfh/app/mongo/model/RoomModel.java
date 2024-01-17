package com.zfh.app.mongo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

public class RoomModel implements Serializable{

	private String kind; // 类型：一居室、二居室、三居室..
	private int room; 	 // 居室数
	private int total;   // 剩余数量
	private List<XinHouseTypeModel> dataList;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<XinHouseTypeModel> getDataList() {
		return dataList;
	}

	public void setDataList(List<XinHouseTypeModel> dataList) {
		this.dataList = dataList;
	}
	

}
