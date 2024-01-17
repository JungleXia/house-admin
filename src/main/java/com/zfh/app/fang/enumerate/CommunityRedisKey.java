package com.zfh.app.fang.enumerate;

public enum CommunityRedisKey {

	/**
	 * 小区只考虑新增的情况
	 */
	LIANJIA_COMMUNITY_INSERT("lianjia_community:community_collect_insert", "链家-新增小区"),
//	LIANJIA_COMMUNITY_UPDATE("lianjia_community:community_collect_update", "链家-更新小区"),
	
	LEYOUJIA_COMMUNITY_INSERT("leyoujia_community:community_collect_insert", "乐有家-新增小区"),
//	LEYOUJIA_COMMUNITY_UPDATE("leyoujia_community:community_collect_update", "乐有家-更新小区"),
	
	ZHONGYUAN_COMMUNITY_INSERT("zhongyuan_community:community_collect_insert", "中原-新增小区"),
//	ZHONGYUAN_COMMUNITY_UPDATE("zhongyuan_community:community_collect_update", "中原-更新小区"),
	
	QFANG_COMMUNITY_INSERT("qfang_community:community_collect_insert", "Q房网-新增小区"),
//	QFANG_COMMUNITY_UPDATE("qfang_community:community_collect_update", "Q房网-更新小区"),
	
	CENTURY21_COMMUNITY_INSERT("century21_community:community_collect_insert", "21世纪-新增小区"),
//	CENTURY21_COMMUNITY_UPDATE("century21_community:community_collect_update", "21世纪-更新小区"),
	
	XHJ_COMMUNITY_INSERT("xhj_community:community_collect_insert", "象盒-新增小区"),
//	XHJ_COMMUNITY_UPDATE("xhj_community:community_collect_update", "象盒-更新小区"),
	
	YFDC_COMMUNITY_INSERT("yfdc_community:community_collect_insert", "裕丰地产-新增小区"),
//	YFDC_COMMUNITY_UPDATE("yfdc_community:community_collect_update", "裕丰地产-更新小区"),
	
	OKHOME_COMMUNITY_INSERT("okhome_community:community_collect_insert", "正顺好房-新增小区"),
//	OKHOME_COMMUNITY_UPDATE("okhome_community:community_collect_update", "正顺好房-更新小区"),
	
	FANGWANG_COMMUNITY_INSERT("ihk_community:community_collect_insert", "房王-新增小区"),
//	FANGWANG_COMMUNITY_UPDATE("ihk_community:community_collect_update", "房王-更新小区"),
	;
	
	private String redisKey;
	
	private String title;
	
	
	public String getRedisKey() {
		return redisKey;
	}

	public String getTitle() {
		return title;
	}

	CommunityRedisKey(String redisKey, String title) {
		this.redisKey = redisKey;
		this.title = title;
	}
}
