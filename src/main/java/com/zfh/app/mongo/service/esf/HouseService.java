package com.zfh.app.mongo.service.esf;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.Ershoufang;
import com.zfh.app.mongo.entity.esf.House;
import com.zfh.app.mongo.model.HouseModel;

public interface HouseService extends BaseMongoService<House>{

	/**
	 * 任务：从二手房复制真房源到本文档
	 * 
	 * 2019年5月22日下午6:17:46
	 */
	public void copyErshoufang();
	
	/**
	 * 任务完善房源价格分析
	 * 
	 * 2019年7月30日下午4:02:06
	 */
	public void taskFullAnalysis();
	
	/**
	 * 复制操作
	 * @param list
	 * 2019年5月22日下午6:18:07
	 */
	public void doCopy(List<Ershoufang> list);
	
	/**
	 * 任务：检查House中是否有小区是空的，如果有，查询到该小区链接并存储到对应的redis缓存中，小区爬虫再从redis读取链接
	 * 
	 * 2019年5月22日下午6:18:42
	 */
	public void redisCacheCommunityEmpty();
	
	/**
	 * 找到community 是null 的House
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * 2019年5月22日下午6:21:33
	 */
	public Page<House> findCommunityEnpty(int pageNum, int pageSize);
	
	
	public BigDecimal getAveragePrice(String standId, int room, int status);
	
	public BigDecimal getSpecialAveragePrice(String standId, int room, int status, String specialText);
	
	public List<Object> getDistinctRoom(String standId);
	
}
