package com.zfh.app.mongo.entity.esf;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 基础小区别名，用于搜索
 * @author CB
 * 
 * @dateTime 2019年8月1日上午10:10:15
 */
@Document(collection = "esf_community_base_alias")
public class CommunityBaseAlias {

	@Id
	private String id;
	
	@Indexed
	private String baseId;
	
	private String baseName;
	
	private String aliasName;
	
    // 别名按拼音检索
    private String pinyin;
    
    // 别名拼音缩写
    private String letter;
    
    // 别名首字母索引, 即第一个汉字首字母
    private String firstLetter;
    
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

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
}
