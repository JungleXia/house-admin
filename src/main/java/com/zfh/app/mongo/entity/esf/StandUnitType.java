package com.zfh.app.mongo.entity.esf;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.SameRoom;

/**
 * 标准小区-同户型-分析数据 记录，每次按createTime 取最新的使用
 * 
 * @author CB
 * 
 * @dateTime 2019年7月30日上午9:47:01
 */
@Document(collection = "esf_stand_unit_type")
@CompoundIndexes({
		// name：索引名称 def：字段(1正序 -1倒序) unique：是否唯一索引
		// 直接加到字段上面没用
		@CompoundIndex(name = "index_stand_room", def = "{standId:1, room:1}") })
public class StandUnitType {

	@Id
	private String id;

	private String standId;
	// 三室
	private String name;
	// 3
	private int room;
	// 挂牌套数
	private int guapai;
	// 笋盘数量
	private int discount;
	// 总价最低
	private BigDecimal minTotal;
	// 单价最低
	private BigDecimal minUnit;
	// 平均单价
	private BigDecimal avgUnit;
	// 同户型房源数据
	private SameRoom sameList;
	
	private String createTime;;
	
	private Date dataTime;

	private String city;
	
	private boolean synched; // 是否需要同步
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getGuapai() {
		return guapai;
	}

	public void setGuapai(int guapai) {
		this.guapai = guapai;
	}

	public BigDecimal getMinTotal() {
		return minTotal;
	}

	public void setMinTotal(BigDecimal minTotal) {
		this.minTotal = minTotal;
	}

	public BigDecimal getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(BigDecimal minUnit) {
		this.minUnit = minUnit;
	}

	public BigDecimal getAvgUnit() {
		return avgUnit;
	}

	public void setAvgUnit(BigDecimal avgUnit) {
		this.avgUnit = avgUnit;
	}

	public SameRoom getSameList() {
		return sameList;
	}

	public void setSameList(SameRoom sameList) {
		this.sameList = sameList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isSynched() {
		return synched;
	}

	public void setSynched(boolean synched) {
		this.synched = synched;
	}
	
}
