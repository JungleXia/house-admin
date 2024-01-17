package com.zfh.app.mongo.service.esf;

import java.util.List;

import org.springframework.data.domain.Page;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.Agent;

public interface AgentService extends BaseMongoService<Agent>{

	public void download();
	
	public void upload(List<Agent> photoList);
	
	public Page<Agent> findUndownload(int page, int size);
}
