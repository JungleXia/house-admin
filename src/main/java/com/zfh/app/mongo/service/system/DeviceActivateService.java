package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.DeviceActivate;

public interface DeviceActivateService extends BaseMongoService<DeviceActivate>{

	public int distinctCount(String createDate);
	
	public void refreshUserAccount();
	/**
	 * 从接口调用记录中补充设备信息，原因：有部分用户进入app停留时间过短，没有提交设备信息；或用户禁止获取手机信息，app没有主动提交设备信息
	 * @param startTime
	 * @param endTime
	 * 2019年11月14日上午10:22:27
	 */
	public void replenishFromAccessLog(String startDate, String endDate);
//	
	/**
	 * 统计用户数据，开始日期和结束日期都为null时可以
	 * 
	 * @param startDate 开始日期	
	 * @param endDate	结束日期
	 * 2019年11月14日下午6:05:39
	 */
	public void statistics(String startDate, String endDate);
	
	
	public int countViewByCondition(String[] params, Object[] values, int startCount, int endCount);
	
	public int countNextfpByCondition(String[] params, Object[] values, int startCount, int endCount);
	
	public int countShoufuByCondition( int startShoufu, int endShoufu);
	
}
