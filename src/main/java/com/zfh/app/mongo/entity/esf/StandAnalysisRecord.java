package com.zfh.app.mongo.entity.esf;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.UnitTypeModel;

/**
 * 标准小区分析 记录
 * 
 * @author CB
 * 
 * @dateTime 2019年5月9日下午3:02:36
 */
@Document(collection = "esf_stand_analysis_rec")
public class StandAnalysisRecord {

	@Id
	private String objId;
	// id
	private String id;
	// 标准小区id
	private String standId;
	// 标准小区名称
	private String standName;
	// 总数量
	private int total;
	// 真房源数量
	private int real;
	// 假房源数量
	private int fake;
	// 笋盘数量
	private int discount;
	// 户型分析
	private List<UnitTypeModel> unitType;
	// 日期
	private String date;
	// 记录时间
	private String time;
	// 用途
	private String useType;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStandId() {
		return standId;
	}

	public void setStandId(String standId) {
		this.standId = standId;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getReal() {
		return real;
	}

	public void setReal(int real) {
		this.real = real;
	}

	public int getFake() {
		return fake;
	}

	public void setFake(int fake) {
		this.fake = fake;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<UnitTypeModel> getUnitType() {
		return unitType;
	}

	public void setUnitType(List<UnitTypeModel> unitType) {
		this.unitType = unitType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}
	

}
