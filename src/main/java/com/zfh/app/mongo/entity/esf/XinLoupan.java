package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zfh.app.mongo.model.HouseFeatureModel;
import com.zfh.app.mongo.model.HouseTagModel;
import com.zfh.app.mongo.model.LoupanPicModel;
import com.zfh.app.mongo.model.RegionModel;
import com.zfh.app.mongo.model.RoomModel;

/**
 * 新房-新楼盘
 * @author CB
 * 
 * @dateTime 2019年5月7日下午2:06:58
 */
@Document(collection = "xin_loupan")
public class XinLoupan implements Serializable{

	@Id
	private String id;
	private String projectName; // 楼盘名称
	private String aliasName;	// 别名 
	private String description;	
	private String saleStatus;  // 销售状态
	private String online;		// 在售状态
	private String introduce;	// 介绍
	private String address;		// 楼盘地址
	private String picUrl;		// 封面图片
	private String phone;		// 转联系电话
	private String resPhone;	// 售楼处电话
	private String longlat;		// 经纬度
	private String createDate;	// 入库日期
	private String updateDate;  // 更新时间
	private RegionModel regions;// 区域
	private String useType;		// 用途
	private String developers; // 开发商
	private String propertyCompany;// 物业公司
	private String propertyFee; // 物业费用
	private String numParking; 	// 停车位	
	private String unitCount; 	// 单元数量
	private String houseCount; 	// 户数
	private String openDate;	// 开盘时间
	private BigDecimal unitPrice;	// 参考均价 元/平
	private String greenRate;	// 绿化率 
	private String plotRate;	// 容积率
	private String recentDate;	// 最近交房日期
	private String license;		// 销售许可证号
	private String preferential; // 优惠
	private String hydro;  			// 供水
	private String gas;				// 供气
	private String heating;			// 供暖
	private String power;			// 供电
	private String propertyYear; 	// 产权年限，70年
	private String buildType; 		// 建筑类型, 板塔结合
	private String buildStructure;  // 建筑结构，如：钢混结构
	private String porpertyRights; 	// 产权所属，非共有
	private String userCode;	// 绑定用户
	private List<RoomModel> houseType;	// 户型(数量)
	
	private List<HouseFeatureModel> features;// 房源特色
	private List<HouseTagModel> tags; 	// 房源标签
	private List<LoupanPicModel> photos; 	// 房源相册
	@Transient
	private List<XinDongtai> dongtais;	// 楼盘动态
	
	private String takeLand;		// 拿地时间
	private String url;
	private String md5Url;
	private String lastTime;		// 最近更新户型时间
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAliasName() {
		if (aliasName == null)
			return "";
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getDescription() {
		if (description == null)
			return "";
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOnline() {
		if (StringUtils.equals(saleStatus, "在售"))
			online = "true";
		else 
			online = "false";
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getIntroduce() {
		if (introduce == null)
			return "";
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAddress() {
		if (address == null)
			return "";
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicUrl() {
		if (picUrl == null)
			return "";
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPhone() {
		if (phone == null)
			return "";
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResPhone() {
		if (resPhone == null)
			return "";
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public String getLonglat() {
		if (longlat == null)
			return "";
		return longlat;
	}

	public void setLonglat(String longlat) {
		this.longlat = longlat;
	}

	public String getCreateDate() {
		if (createDate == null)
			return "";
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public RegionModel getRegions() {
		if (regions == null)
			return new RegionModel();
		return regions;
	}

	public void setRegions(RegionModel regions) {
		this.regions = regions;
	}

	public String getUseType() {
		if (useType == null)
			return "";
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getDevelopers() {
		if (developers == null)
			return "";
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String getPropertyCompany() {
		if (propertyCompany == null)
			return "";
		return propertyCompany;
	}

	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}

	public String getPropertyFee() {
		if (propertyFee == null)
			return "";
		return propertyFee;
	}

	public void setPropertyFee(String propertyFee) {
		this.propertyFee = propertyFee;
	}

	public String getNumParking() {
		if (numParking == null)
			return "";
		return numParking;
	}

	public void setNumParking(String numParking) {
		this.numParking = numParking;
	}

	public String getUnitCount() {
		if (unitCount == null)
			return "";
		return unitCount;
	}

	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}

	public String getHouseCount() {
		if (houseCount == null)
			return "";
		return houseCount;
	}

	public void setHouseCount(String houseCount) {
		this.houseCount = houseCount;
	}

	public String getOpenDate() {
		if (openDate == null)
			return "";
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public BigDecimal getUnitPrice() {
		if (unitPrice == null)
			return BigDecimal.ZERO;
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getGreenRate() {
		if (greenRate == null)
			return "";
		return greenRate;
	}

	public void setGreenRate(String greenRate) {
		this.greenRate = greenRate;
	}

	public String getPlotRate() {
		if (plotRate == null)
			return "";
		return plotRate;
	}

	public void setPlotRate(String plotRate) {
		this.plotRate = plotRate;
	}

	public String getPropertyYear() {
		if (propertyYear == null) 
			return "";
		return propertyYear;
	}

	public void setPropertyYear(String propertyYear) {
		this.propertyYear = propertyYear;
	}

	public String getRecentDate() {
		if (recentDate == null) 
			return "";
		return recentDate;
	}

	public void setRecentDate(String recentDate) {
		this.recentDate = recentDate;
	}

	public String getLicense() {
		if (license == null) 
			return "";
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getPreferential() {
		if (preferential == null)
			return "";
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public String getHydro() {
		if (hydro == null) 
			return "";
		return hydro;
	}

	public void setHydro(String hydro) {
		this.hydro = hydro;
	}

	public String getGas() {
		if (gas == null) 
			return "";
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getHeating() {
		if (heating == null) 
			return "";
		return heating;
	}

	public void setHeating(String heating) {
		this.heating = heating;
	}

	public String getPower() {
		if (power == null) 
			return "";
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public List<XinDongtai> getDongtais() {
		if (dongtais == null)
			return new ArrayList<>();
		return dongtais;
	}

	public void setDongtais(List<XinDongtai> dongtais) {
		this.dongtais = dongtais;
	}

	public List<LoupanPicModel> getPhotos() {
		if (photos == null) {
			return new ArrayList<LoupanPicModel>();
		}
		return photos;
	}

	public void setPhotos(List<LoupanPicModel> photos) {
		this.photos = photos;
	}

	public String getUserCode() {
		if (userCode == null) 
			return "";
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBuildType() {
		if (buildType == null) 
			return "";
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getBuildStructure() {
		if (buildStructure == null) 
			return "";
		return buildStructure;
	}

	public void setBuildStructure(String buildStructure) {
		this.buildStructure = buildStructure;
	}

	public String getPorpertyRights() {
		if (porpertyRights == null) 
			return "";
		return porpertyRights;
	}

	public void setPorpertyRights(String porpertyRights) {
		this.porpertyRights = porpertyRights;
	}

	public List<HouseFeatureModel> getFeatures() {
		if (features == null)
			return new ArrayList<>();
		return features;
	}

	public void setFeatures(List<HouseFeatureModel> features) {
		this.features = features;
	}

	public List<HouseTagModel> getTags() {
		if (tags == null)
			return new ArrayList<>();
		return tags;
	}

	public void setTags(List<HouseTagModel> tags) {
		this.tags = tags;
	}

	public List<RoomModel> getHouseType() {
		if (houseType == null)
			return new ArrayList<>();
		return houseType;
	}

	public void setHouseType(List<RoomModel> houseType) {
		this.houseType = houseType;
	}

	public String getSaleStatus() {
		if (saleStatus == null)
			return "";
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getTakeLand() {
		if (takeLand == null) {
			return "";
		}
		return takeLand;
	}

	public void setTakeLand(String takeLand) {
		this.takeLand = takeLand;
	}

	public String getUpdateDate() {
		if (updateDate == null)
			return "";
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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

	public String getLastTime() {
		if (lastTime == null)
			return "";
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

}
