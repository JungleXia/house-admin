package com.zfh.app.mongo.entity.system;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 记录每日访问的设备
 * ClassName: DeviceAccess
 * @author Jungle
 * @dateTime 2020年6月11日下午3:19:05
 * @version v1.0
 */
@Document(collection = "user_device_access")
@CompoundIndexes({
	// name：索引名称 def：字段(1正序 -1倒序) unique：是否唯一索引
	// 直接加到字段上面没用
	@CompoundIndex(def = "{clientKey:1, createDate:1}", background = true)
})
public class DeviceAccess implements Serializable{

	@Id
	private String id;

	/**
	 * 客户端ip
	 */
	private String clientIp;

	/**
	 * 端口来源
	 */
	private String client;
	
	/**
	 * 唯一识别码
	 */
	private String clientKey;
	
	
	private String createDate;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getClientIp() {
		return clientIp;
	}


	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getClientKey() {
		return clientKey;
	}


	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
