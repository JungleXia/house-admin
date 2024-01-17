package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "esf_house_region")
public class HouseRegion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
    private String ocity;
    private String oregionFir;
    private String oregionSub;
    private String md5oregion;
    private String dataFrom;
    private String bind;//绑定状态
    
    @DBRef
    private StandardRegion standRegion;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOcity() {
		return ocity;
	}
	public void setOcity(String ocity) {
		this.ocity = ocity;
	}
	public String getOregionFir() {
		return oregionFir;
	}
	public void setOregionFir(String oregionFir) {
		this.oregionFir = oregionFir;
	}
	public String getOregionSub() {
		return oregionSub;
	}
	public void setOregionSub(String oregionSub) {
		this.oregionSub = oregionSub;
	}
	public String getMd5oregion() {
		return md5oregion;
	}
	public void setMd5oregion(String md5oregion) {
		this.md5oregion = md5oregion;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getBind() {
		return bind;
	}
	public void setBind(String bind) {
		this.bind = bind;
	}
	public StandardRegion getStandRegion() {
		return standRegion;
	}
	public void setStandRegion(StandardRegion standRegion) {
		this.standRegion = standRegion;
	}

}
