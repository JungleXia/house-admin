package com.zfh.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zfh.app.mongo.entity.esf.Agent;
import com.zfh.app.mongo.service.esf.AgentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestAgentService {

	@Autowired
	AgentService agentService;
	
	@Test
	public void test() {
		Page<Agent> page = agentService.findUndownload(0, 1000);
		int totalPage = page.getTotalPages();
		
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			System.out.println("倒数第 " + totalPage + " 页");
			page = agentService.findUndownload(totalPage, 1000);
			agentService.upload(page.getContent());
		}
	}
}
