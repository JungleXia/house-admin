package com.zfh.app.mongo.service.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 限购条件-选项设置
 * 
 * @author CB
 * 
 * @dateTime 2019年5月10日上午11:29:38
 */
@Document(collection = "sys_buylimit_option")
public class SysBuylimitOption implements Serializable{

	@Id
	private String id;
	// 组
	private String groupId;
	// 选项名称
	private String name;
	// 选项值
	private String values;
	// 默认值
	private boolean defSelect;
	// 是否有子选项
	private boolean hasChild;
	// 如果有子选择
	@DBRef
	private List<SysBuylimit> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public boolean isDefSelect() {
		return defSelect;
	}

	public void setDefSelect(boolean defSelect) {
		this.defSelect = defSelect;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<SysBuylimit> getChildren() {
		if (children == null)
			return new ArrayList<>();
		return children;
	}

	public void setChildren(List<SysBuylimit> children) {
		this.children = children;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


}