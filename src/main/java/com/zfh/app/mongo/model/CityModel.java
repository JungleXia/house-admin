package com.zfh.app.mongo.model;

import java.util.List;

public class CityModel implements Comparable<CityModel>{
	
	/**
	 * 首字母
	 */
	private String initials;
	
	/**
	 * 名称
	 */
	private String name;
	
	private String province;
	
	private Integer numbers;
	
	/**
	 * 子级
	 */
	private List<CityModel> subList;
	
	/**
	 * 是否初始展开
	 */
	private boolean expaned;
	
    /**
     * 创建时间
     */
    private String createDay;
    /**
     * 上期数量
     */
    private Integer prenums;
    /**
     * 数量涨跌
     */
    private Integer diff;

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public List<CityModel> getSubList() {
		return subList;
	}

	public void setSubList(List<CityModel> subList) {
		this.subList = subList;
	}

	public boolean isExpaned() {
		return expaned;
	}

	public void setExpaned(boolean expaned) {
		this.expaned = expaned;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

	public Integer getPrenums() {
		return prenums;
	}

	public void setPrenums(Integer prenums) {
		this.prenums = prenums;
	}

	public Integer getDiff() {
		return diff;
	}

	public void setDiff(Integer diff) {
		this.diff = diff;
	}

	@Override
	public int compareTo(CityModel o) {
		char a = this.getInitials().toCharArray()[0];
		char b = o.getInitials().toCharArray()[0];
		return Integer.valueOf(a + 0).compareTo(b + 0);
	}
	
}
