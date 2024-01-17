package com.zfh.app.mongo.service.esf.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.HouseCode;
import com.zfh.app.mongo.service.esf.HouseCodeMongoService;

@Service("houseCodeMongoService")
@Transactional(propagation = Propagation.REQUIRED)
public class HouseCodeServiceImpl extends BaseMongoServiceImpl<HouseCode> implements HouseCodeMongoService {

	private static final Log logger = LogFactory.getLog(HouseCodeServiceImpl.class.getName());

	@Override
	protected Class<HouseCode> getEntityClass() {
		// TODO Auto-generated method stub
		return HouseCode.class;
	}

	@Override
	public HouseCode getByRefId(String refId) {
		// TODO Auto-generated method stub
		return uniqueByProp("refId", Long.valueOf(refId).longValue());
	}
	
	@Override
	public HouseCode getByHouseCode(String houseCode) {
		if (StringUtils.isEmpty(houseCode)) {
			return null;
		}
		return uniqueByProp("houseCode", houseCode);
	}
}
