package com.zfh.app.mongo.entity.esf;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @type 引用, 需要对图片进行处理，所以用引用
 * 图片
 * @author CB
 * 
 * @dateTime 2019年3月25日下午3:29:28
 */
@Document(collection = "esf_photos")
public class HousePhoto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description; // 描述
	private String source; 		// 源链接
	private String size; 		// 大小 710x400
	@Indexed(background = true)
	private String md5Url;		// MD5(source);
	private String target;		// oos链接
	private String large; 		// 最大
	private String middle; 		// 中图
	private String small; 		// 小图
	private Integer type; 		// 类型，房源图 1*，小区图2*， 新房图2*
	private Integer status; 	// 状态， 0-未下载， 1-已下载
	private String dataFrom;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMd5Url() {
		return md5Url;
	}

	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
}
