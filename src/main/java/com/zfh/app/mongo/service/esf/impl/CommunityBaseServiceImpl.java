package com.zfh.app.mongo.service.esf.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.fang.enumerate.DataFrom;
import com.zfh.app.mongo.entity.esf.Community;
import com.zfh.app.mongo.entity.esf.CommunityBase;
import com.zfh.app.mongo.entity.esf.CommunityBaseAlias;
import com.zfh.app.mongo.entity.esf.CommunityBaseRef;
import com.zfh.app.mongo.entity.esf.DealRecord;
import com.zfh.app.mongo.entity.esf.School;
import com.zfh.app.mongo.entity.esf.StandCommunity;
import com.zfh.app.mongo.model.CommunitySearchModel;
import com.zfh.app.mongo.service.esf.CommunityBaseAliasService;
import com.zfh.app.mongo.service.esf.CommunityBaseRefService;
import com.zfh.app.mongo.service.esf.CommunityBaseService;
import com.zfh.app.mongo.service.esf.StandAnalysisService;
import com.zfh.app.mongo.service.esf.StandCommunityService;

@Service
public class CommunityBaseServiceImpl extends BaseMongoServiceImpl<CommunityBase> implements CommunityBaseService{

	private static final Log logger = LogFactory.getLog(CommunityBaseServiceImpl.class.getName());
	
	@Autowired
	private CommunityBaseAliasService communityBaseAliasService;
	
	@Autowired
	private CommunityBaseRefService communityBaseRefService;
	
	@Autowired
	private StandCommunityService standCommunityService;
	
	@Autowired
	private StandAnalysisService standAnalysisService;
	
	@Override
	protected Class<CommunityBase> getEntityClass() {
		// TODO Auto-generated method stub
		return CommunityBase.class;
	}

	@Override
	public List<CommunitySearchModel> search(String keyword, int pageSize) {
		List<CommunityBaseAlias> aliasList = communityBaseAliasService.search(keyword, pageSize);
		Set<String> baseIdset = new LinkedHashSet<>();
		if (aliasList != null && aliasList.size() > 0) {
			for (CommunityBaseAlias alias : aliasList) {
				baseIdset.add(alias.getBaseId());
			}
			String[] baseIds = Arrays.copyOf(baseIdset.toArray(), baseIdset.size(), String[].class); 
			List<StandCommunity> standList = standCommunityService.searchByBaseIds(baseIds);
			if (standList != null && standList.size() > 0) {
				return covertModel(standList);
			}
		} 
		// 没有搜索结果
		return null;
	}
	
	public List<CommunitySearchModel> covertModel(List<StandCommunity> standList) {
		List<CommunitySearchModel> modelList = new ArrayList<>();
		for (StandCommunity stand : standList) {
			CommunitySearchModel model = new CommunitySearchModel();
			model.setAddress("");
			model.setBlockName(stand.getRegionSub());
			model.setCommunity(stand.getBaseName());
			model.setDistrict(stand.getRegion());
			model.setStandId(stand.getStandId());
			model.setTotal(standAnalysisService.getHouseCount(stand.getStandId()));
			if (StringUtils.isEmpty(stand.getBaseMark())) {
				model.setUseType(stand.getUseType());
			} else {
				model.setUseType(stand.getBaseMark());
			}
			modelList.add(model);
		}
		return modelList;
	}

	@Override
	public Community findCommuntiyByStandId(String standId) {
		try {
			StandCommunity stand = standCommunityService.getByStandId(standId);
			CommunityBaseRef communityRef = communityBaseRefService.uniqueByProps(new String[]{"baseId", "dataFrom"}, new String[]{stand.getBaseId(), DataFrom.LIAN_JIA.getDataFrom()});
			if (communityRef == null) {
				communityRef = communityBaseRefService.uniqueByProps(new String[]{"baseId", "dataFrom"}, new String[]{stand.getBaseId(), DataFrom.QFANG.getDataFrom()});
			}
			if (communityRef == null) {
				communityRef = communityBaseRefService.uniqueByProps(new String[]{"baseId", "dataFrom"}, new String[]{stand.getBaseId(), DataFrom.ZHONG_YUAN.getDataFrom()});
			}
			if (communityRef == null) {
				communityRef = communityBaseRefService.uniqueByProps(new String[]{"baseId", "dataFrom"}, new String[]{stand.getBaseId(), DataFrom.LE_YOU_JIA.getDataFrom()});
			}
			return communityRef.getRef();
		} catch (Exception e) {
			logger.error("findCommuntiyByStandId standId=" + standId);
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<School> findSchoolsByStandId(String standId) {
		return null;
	}

	@Override
	public List<DealRecord> findDealRecordByStandId(String standId) {
		// TODO Auto-generated method stub
		return null;
	}

}
