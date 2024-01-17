package com.zfh.app.mongo.service.esf.impl;

import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.XinDongtai;
import com.zfh.app.mongo.service.esf.XinDongtaiService;

@Service
public class XinDongtaiServiceImpl extends BaseMongoServiceImpl<XinDongtai> implements XinDongtaiService{

	@Override
	protected Class<XinDongtai> getEntityClass() {
		// TODO Auto-generated method stub
		return XinDongtai.class;
	}

}
