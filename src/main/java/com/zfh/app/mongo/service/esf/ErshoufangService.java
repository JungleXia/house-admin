package com.zfh.app.mongo.service.esf;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.Ershoufang;

public interface ErshoufangService extends BaseMongoService<Ershoufang>{

	/**
	 * 开启多线程，用房源编码认证真实房源,且绑定标准小区
	 * 
	 * 2019年5月23日下午1:50:09
	 */
	public void mulitVerify();

	public Page<Ershoufang> findUnVerify(int pageNum, int pageSize);
	
	public Page<Ershoufang> findReal(int pageNum, int pageSize);
	
	public void updateStatus(Ershoufang ershoufang);
	
	public List<Ershoufang> findFieldsByIds(Object[] ids, String[] fields);
	
	/**
	 * 根据标准小区，划分户型
	 * @param standId
	 * @return
	 * 2019年5月27日下午2:25:42
	 */
	public List<Object> getDistinctRoom(String standId);
	
	public BigDecimal getAveragePrice(String standId, int room);
	
	public void replacePicTask();
	
	Page<Ershoufang> findlteVersionDate(PageRequest pageReq, String versionDate);
	
	/**
	 * 从redis中获取每日下架的房源
	 * 
	 * 2019年11月21日上午10:00:21
	 */
	public void expiredFromRedis();

}
