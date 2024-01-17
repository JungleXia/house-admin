package com.zfh.app.mongo.service.esf;

import java.util.List;

import com.zfh.app.core.service.BaseMongoService;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.esf.CommunityBase;
import com.zfh.app.mongo.entity.esf.DealRecord;
import com.zfh.app.mongo.entity.esf.School;
import com.zfh.app.mongo.model.CommunitySearchModel;

public interface CommunityBaseService extends BaseMongoService<CommunityBase>{

	public List<CommunitySearchModel> search(String keyword, int pageSize);
	
	public Community findCommuntiyByStandId(String standId);
	
	public List<School> findSchoolsByStandId(String standId);
	
	public List<DealRecord> findDealRecordByStandId(String standId);
	
}
