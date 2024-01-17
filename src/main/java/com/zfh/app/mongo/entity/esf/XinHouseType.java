package com.zfh.app.mongo.entity.esf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import com.zfh.app.mongo.model.HouseTagModel;

/**
 * 新房-户型
 * @author CB
 * 
 * @dateTime 2019年5月7日下午2:07:41
 */
@Document(collection = "xin_house_type")
public class XinHouseType {

	@Id
	private String id;
	private String title;			// 标题
	private String projectId;		// 楼盘id 
	private String projectName;		// 楼盘名称
	private String picUrl;			// 图片地址
	private String houseType;		// 户型名称
	private String huxing; 			// 完整户型
	private int room;				// 几室
	private int hall;				// 几厅
	private BigDecimal totalPrice;	// 总价 起(万)
	private BigDecimal unitPrice;	// 单价
	private BigDecimal area; 		// 建筑面积
	private String innerArea; 		// 套内面积
	private String orientation; 	// 朝向
	private String decorationType;  // 装修情况，精装、毛坯
	private List<HouseTagModel> tags; 	// 户型标签
	private boolean online;			// 是否在售
	private String saleStatus;		// 销售状态
	
	@Transient
	private XinLoupan loupan;

	private boolean xin;			// 是否是新房，true:是，false:否
	private String lastTime;		// 最近更新时间
	@Transient
	private XinAnalysis analysis;		// 房源分析
	
	@Transient
	private boolean collect; // 是否已收藏 ，true-收藏，false-未收藏
	
	private String hxfx; 	// 户型分析
	private String url;
	private String md5Url;
	private boolean reco;	// 是否推送到House
	private String useType; // 用途
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getHouseType() {
		if (houseType == null)
			return "";
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHuxing() {
		if (huxing == null)
			return "";
		return huxing;
	}

	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getHall() {
		return hall;
	}

	public void setHall(int hall) {
		this.hall = hall;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice.setScale(0, BigDecimal.ROUND_HALF_DOWN);
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice.setScale(0, BigDecimal.ROUND_HALF_DOWN);
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getArea() {
		if (area != null && new BigDecimal(area.intValue()).compareTo(area) == 0) {
			return area.setScale(0, BigDecimal.ROUND_HALF_DOWN);
		}
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public String getInnerArea() {
		return innerArea;
	}

	public void setInnerArea(String innerArea) {
		this.innerArea = innerArea;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(String decorationType) {
		this.decorationType = decorationType;
	}

	public List<HouseTagModel> getTags() {
		if (tags == null)
			return new ArrayList<>();
		return tags;
	}

	public void setTags(List<HouseTagModel> tags) {
		this.tags = tags;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getSaleStatus() {
		if (saleStatus == null)
			return "";
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public XinLoupan getLoupan() {
		return loupan;
	}

	public void setLoupan(XinLoupan loupan) {
		this.loupan = loupan;
	}

	public boolean isXin() {
		return true;
	}

	public void setXin(boolean xin) {
		this.xin = true;
	}

	public XinAnalysis getAnalysis() {
		if (analysis == null) {
			return new XinAnalysis();
		}
		return analysis;
	}

	public void setAnalysis(XinAnalysis analysis) {
		this.analysis = analysis;
	}

	public String getLastTime() {
		if (lastTime == null)
			return "";
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public String getHxfx() {
		return hxfx;
	}

	public void setHxfx(String hxfx) {
		this.hxfx = hxfx;
	}

	public String getTitle() {
		if (title == null)
			return "";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		if (url == null)
			return "";
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5Url() {
		if (md5Url == null)
			return "";
		return md5Url;
	}

	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}

	public boolean isReco() {
		return reco;
	}

	public void setReco(boolean reco) {
		this.reco = reco;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}
	
}
