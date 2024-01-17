package com.zfh.app.mongo.service.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.system.UserAccount;

public interface UserAccountService extends BaseMongoService<UserAccount> {

	public int distinctCount(String startDate, String endDate);
	
	public int distinctCount(String createDate);
	
	public void searchUserIp();
	
	public Page<UserAccount> findPageByCityList(PageRequest pageReq, String[] citys, String phone, String date);

}
