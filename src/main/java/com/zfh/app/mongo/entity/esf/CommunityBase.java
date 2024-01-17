package com.zfh.app.mongo.entity.esf;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 基础小区
 * @author CB
 * 
 * @dateTime 2019年8月1日上午10:10:28
 */
@Document(collection = "esf_community_base")
public class CommunityBase {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private String baseId;
	
	private String baseName;
	
	private String createTime;

	private String longlat;
	
	private String city;
	
	private String versionDate;
	
	private boolean synched; // 是否需要同步
	
	@Transient
	private String aliasName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLonglat() {
		return longlat;
	}

	public void setLonglat(String longlat) {
		this.longlat = longlat;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(String versionDate) {
		this.versionDate = versionDate;
	}

	public boolean isSynched() {
		return synched;
	}

	public void setSynched(boolean synched) {
		this.synched = synched;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
}
