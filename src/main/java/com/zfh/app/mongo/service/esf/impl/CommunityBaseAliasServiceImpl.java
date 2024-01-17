package com.zfh.app.mongo.service.esf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.zfh.app.core.service.impl.BaseMongoServiceImpl;
import com.zfh.app.mongo.entity.esf.CommunityBaseAlias;
import com.zfh.app.mongo.service.esf.CommunityBaseAliasService;

@Service
public class CommunityBaseAliasServiceImpl extends BaseMongoServiceImpl<CommunityBaseAlias> implements CommunityBaseAliasService{

	@Override
	protected Class<CommunityBaseAlias> getEntityClass() {
		// TODO Auto-generated method stub
		return CommunityBaseAlias.class;
	}

	@Override
	public List<CommunityBaseAlias> search(String keyword, int pageSize) {
		PageRequest pageReq = new PageRequest(0, pageSize);
		List<CommunityBaseAlias> list = new ArrayList<CommunityBaseAlias>();
		// 根据文字匹配
		Query query = new Query();
		query.addCriteria(Criteria.where("baseName").regex(keyword));
		query.with(pageReq);
		list = mongoTemplate.find(query, getEntityClass());
		if (!(list != null && list.size() > 0)) {
			query = new Query();
			query.addCriteria(Criteria.where("aliasName").regex(keyword));
			query.with(pageReq);
			list = mongoTemplate.find(query, getEntityClass());
		}
		if (!(list != null && list.size() > 0)) {
			// 以拼音开头
			String pinyin = PinyinHelper.convertToPinyinString(keyword, "", PinyinFormat.WITHOUT_TONE);
			query = new Query(Criteria.where("pinyin").regex("^" + pinyin));
			query.with(pageReq);
			list.addAll(mongoTemplate.find(query, getEntityClass()));
		}
		if (!(list != null && list.size() > 0)) {
			// 以首字母开头
			String shortPinyin = PinyinHelper.getShortPinyin(keyword).toUpperCase();
			query = new Query(Criteria.where("letter").regex(shortPinyin));
			query.with(pageReq);
			list.addAll(mongoTemplate.find(query, getEntityClass()));
		}
		if (!(list != null && list.size() > 0)) {
			// 以上都没有，模糊匹配
			String pinyin = PinyinHelper.convertToPinyinString(keyword, "", PinyinFormat.WITHOUT_TONE);
			String shortPinyin = PinyinHelper.getShortPinyin(keyword).toUpperCase();
			Criteria criteria = new Criteria();
			criteria.orOperator(Criteria.where("pinyin").regex(pinyin), Criteria.where("letter").regex(shortPinyin));
			query = new Query(criteria);
			query.with(pageReq);
			list.addAll(mongoTemplate.find(query, getEntityClass()));
		}

		return list;
	}

}
