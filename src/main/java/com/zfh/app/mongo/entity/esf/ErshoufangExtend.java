package com.zfh.app.mongo.entity.esf;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.AgentModel;
import com.zfh.app.mongo.model.HouseFeatureModel;
import com.zfh.app.mongo.model.HouseTagModel;

/**
 * 爬虫房源拓展信息（房源信息太多，拆分成两个文档 sp_ershoufang 和 sp_ershoufang_ext）
 * 
 * @author CB
 * 
 * @dateTime 2019年4月8日下午3:06:54
 */
@Document(collection = "esf_ershoufang_ext")
public class ErshoufangExtend {
	
	@Id
	private String id;
	private String md5Url; 			// MD5(url) 用作外键关联
	private String isEvevators; 	// 是否配有电梯
	private String tihubi; 			// 梯户比，三梯十六户，X梯X户
	private String propertyYear; 	// 产权年限，70年
	private String huxingStructure; // 户型结构，平层
	private String buildType; 		// 建筑类型, 板塔结合
	private String buildStructure;  // 建筑结构，如：钢混结构
	private String visitTime;		// 带看时间
	private String listingTime; 	// 挂牌日期
	private String lastTradeTime; 	// 上次交易日期
	private String mortgage; 		// 抵押状况，无抵押
	private String houseYear; 		// 房屋年限，满两年、满五年
	private String porpertyRights; 	// 产权所属，非共有
	private List<HouseFeatureModel> features;// 房源特色
	private List<HouseTagModel> tags; // 房源标签
	private List<AgentModel> agents;
	private String createTime;

    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMd5Url() {
		return md5Url;
	}

	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}

	public String getIsEvevators() {
		return isEvevators;
	}

	public void setIsEvevators(String isEvevators) {
		this.isEvevators = isEvevators;
	}

	public String getTihubi() {
		return tihubi;
	}

	public void setTihubi(String tihubi) {
		this.tihubi = tihubi;
	}

	public String getPropertyYear() {
		return propertyYear;
	}

	public void setPropertyYear(String propertyYear) {
		this.propertyYear = propertyYear;
	}

	public String getHuxingStructure() {
		return huxingStructure;
	}

	public void setHuxingStructure(String huxingStructure) {
		this.huxingStructure = huxingStructure;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getBuildStructure() {
		return buildStructure;
	}

	public void setBuildStructure(String buildStructure) {
		this.buildStructure = buildStructure;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getListingTime() {
		return listingTime;
	}

	public void setListingTime(String listingTime) {
		this.listingTime = listingTime;
	}

	public String getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public String getMortgage() {
		return mortgage;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	public String getHouseYear() {
		return houseYear;
	}

	public void setHouseYear(String houseYear) {
		this.houseYear = houseYear;
	}

	public String getPorpertyRights() {
		return porpertyRights;
	}

	public void setPorpertyRights(String porpertyRights) {
		this.porpertyRights = porpertyRights;
	}

	public List<HouseFeatureModel> getFeatures() {
		return features;
	}

	public void setFeatures(List<HouseFeatureModel> features) {
		this.features = features;
	}

	public List<HouseTagModel> getTags() {
		return tags;
	}

	public void setTags(List<HouseTagModel> tags) {
		this.tags = tags;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<AgentModel> getAgents() {
		return agents;
	}

	public void setAgents(List<AgentModel> agents) {
		this.agents = agents;
	}

}
