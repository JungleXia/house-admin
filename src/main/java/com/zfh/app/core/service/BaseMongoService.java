package com.zfh.app.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.zfh.app.core.BathUpdateOptions;

public interface BaseMongoService<T> {
	
	/**
	 * 对于id在数据库中存在的数据， 要先getById(id)再更新字段，否则部分不希望更新的字段会被覆盖掉；<br/>
	 * 如id不存在，则直接保存
	 * @param entity
	 * @return
	 * 2019年5月8日下午5:59:18
	 */
	T save(T entity);

	/**
	 * update 对非String 型的空数据会有影响，因为会有默认值
	 * @param entity
	 * @return
	 * 2019年5月9日下午4:25:16
	 */
	T update(T entity);

	void delete(Object[] ids);
	
	void delete(Object id);
	
	T getById(Object id);

	List<T> findAll();

	List<T> findAll(String order);

	List<T> findByProp(String propName, Object value);

	List<T> findByProp(String propName, Object value, String order);
	
	/**
	 * 注意 includeFields 和 excludeFields 不能同时使用 
	 * @param propName
	 * @param value
	 * @param includeFields	查询项
	 * @param excludeFields	排除项
	 * @return
	 * 2019年5月8日下午6:38:13
	 */
	List<T> findByProp(String propName, Object value, String[] includeFields, String[] excludeFields);

	List<T> findByProps(String[] propName, Object[] values);

	List<T> findByProps(String[] propName, Object[] values, String order);
	
	/**
	 * 注意 includeFields 和 excludeFields 不能同时使用 
	 * @param propName
	 * @param values
	 * @param includeFields	查询项
	 * @param excludeFields	排除项
	 * @return
	 * 2019年5月8日下午6:38:13
	 */
	List<T> findByProps(String[] propName, Object[] values, String[] includeFields, String[] excludeFields);

	T uniqueByProp(String propName, Object value);

	T uniqueByProps(String[] propName, Object[] values);

	int countByCondition(String[] params, Object[] values);

	Page<T> findPage(PageRequest pageReq);
	
	Page<T> findPage(PageRequest pageReq, String propName, Object value);
	
	Page<T> findPage(PageRequest pageReq, String[] propName, Object[] values, String order);
	
	Page<T> findPage(PageRequest pageReq, String[] includeFields, String[] excludeFields);
	
	Page<T> findPage(PageRequest pageReq, String propName, Object value, String[] includeFields, String[] excludeFields);
	
	Page<T> findPage(PageRequest pageReq, String[] propName, Object[] values, String[] includeFields, String[] excludeFields);
	
	/**
	 * 批量修改， 使用此方法是注意 options size 不能超过1000，
	 * 否则报错'exceeded maximum write batch size of 1000'
	 * @param options
	 * @return
	 * 2019年7月31日上午9:39:17
	 */
	public int bathUpdate(List<BathUpdateOptions> options);
}
