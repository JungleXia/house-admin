package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.XinLoupan;
import com.zfh.app.mongo.service.esf.XinLoupanService;

@Service
public class XinLoupanServiceImpl extends BaseMongoServiceImpl<XinLoupan> implements XinLoupanService{

	@Override
	protected Class<XinLoupan> getEntityClass() {
		// TODO Auto-generated method stub
		return XinLoupan.class;
	}

}
