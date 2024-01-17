package com.zfh.app.mongo.service.system;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.PrivateNumber;

public interface PrivateNumberService extends BaseMongoService<PrivateNumber>{

	/**
	 * 
	 * @param callerNum 主叫号码  
	 * @param calleeNum	被叫号码
	 * @return
	 * 2019年8月12日下午4:16:58
	 */
	public String bindAxB(String callerNum, String calleeNum, String houseId);
	
	/**
	 * 查询绑定的隐私号
	 * 
	 * @param callerNum	主叫
	 * @param calleeNum 被叫
	 * @param relationNum 隐私号码
	 * @return
	 * 2020年8月14日上午10:22:31
	 */
	public PrivateNumber findLastByPhoneNumber(String callerNum,String calleeNum,String relationNum);
}
