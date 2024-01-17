package com.zfh.app.mongo.entity.esf;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 基础小区和中介小区关联关系, 1对多
 * @author CB
 * 
 * @dateTime 2019年8月1日上午10:09:53
 */
@Document(collection = "esf_community_base_ref")
public class CommunityBaseRef {

	@Id
	private String id;
	
	@Indexed(background = true)
	private String baseId;
	
	private String baseName;
	
	private String communityId;
	
	private String community;
	
	private String dataFrom;
	
	private String city;
	
	private String versionDate;
	
	@DBRef
	private Community ref;
	
	private boolean synched; // 是否需要同步

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

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public Community getRef() {
		return ref;
	}

	public void setRef(Community ref) {
		this.ref = ref;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
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
	
}
