package com.zfh.app.mongo.model;

import java.io.Serializable;
import java.util.List;

import com.zfh.app.mongo.entity.esf.HousePhoto;

public class LoupanPicModel implements Serializable{
	// 类型
	private String type;
	// 数量
	private int count;
	// 图片数据
	private List<HousePhoto> pics;
	public String getType() {
		if (type == null)
			return "";
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<HousePhoto> getPics() {
		return pics;
	}
	public void setPics(List<HousePhoto> pics) {
		this.pics = pics;
	}
	
}
