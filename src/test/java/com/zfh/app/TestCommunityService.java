package com.zfh.app;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.system.UserAccount;
import com.zfh.app.mongo.service.esf.CommunityService;
import com.zfh.app.mongo.service.system.UserAccountService;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestCommunityService {

	private static final Logger logger = LoggerFactory.getLogger(TestCommunityService.class);

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private UserAccountService userAccountService;


	@Test
	public void test() {
		/**
		 * 测试mongodb数据源切换
		 * zfh_app
		 */
		List<Community> list = communityService.findByName(new PageRequest(0, 10), "阅景花园");
		System.err.println("1 zfh_app");
		for (Community entity : list) {
			System.err.println(JSON.toJSONString(entity));
		}
				
		/**
		 * zfh_system
		 */
//		UserAccount ua = userAccountService.findByPhone("13128802961");
//		System.err.println("2 zfh_system");
//		System.err.println(JSON.toJSONString(ua));
		
		/**
		 * zfh_app
		 */
		list = communityService.findByName(new PageRequest(0, 10), "朗景园");
		System.err.println("3 zfh_app");
		for (Community entity : list) {
			System.err.println(JSON.toJSONString(entity));
		}
		
	}
}
