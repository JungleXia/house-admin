package com.zfh.app.fang.enumerate;

public enum ErshoufangRedisKey {
	
	LIANJIA_ESF_INSERT("lianjia_house:ershoufang_collect_insert", "链家-新增房源"),
	LIANJIA_ESF_UPDATE("lianjia_house:ershoufang_collect_update", "链家-更新房源"),
	
	LEYOUJIA_ESF_INSERT("leyoujia_house:ershoufang_collect_insert", "乐有家-新增房源"),
	LEYOUJIA_ESF_UPDATE("leyoujia_house:ershoufang_collect_update", "乐有家-更新房源"),
	
	ZHONGYUAN_ESF_INSERT("zhongyuan_house:ershoufang_collect_insert", "中原-新增房源"),
	ZHONGYUAN_ESF_UPDATE("zhongyuan_house:ershoufang_collect_update", "中原-更新房源"),
	
	QFANG_ESF_INSERT("qfang_house:ershoufang_collect_insert", "Q房网-新增房源"),
	QFANG_ESF_UPDATE("qfang_house:ershoufang_collect_update", "Q房网-更新房源"),
	
	CENTURY21_ESF_INSERT("century21_house:ershoufang_collect_insert", "21世纪-新增房源"),
	CENTURY21_ESF_UPDATE("century21_house:ershoufang_collect_update", "21世纪-更新房源"),
	
	XHJ_ESF_INSERT("xhj_house:ershoufang_collect_insert", "象盒-新增房源"),
	XHJ_ESF_UPDATE("xhj_house:ershoufang_collect_update", "象盒-更新房源"),
	
	YFDC_ESF_INSERT("yfdc_house:ershoufang_collect_insert", "裕丰地产-新增房源"),
	YFDC_ESF_UPDATE("yfdc_house:ershoufang_collect_update", "裕丰地产-更新房源"),
	
	OKHOME_ESF_INSERT("okhome_house:ershoufang_collect_insert", "正顺好房-新增房源"),
	OKHOME_ESF_UPDATE("okhome_house:ershoufang_collect_update", "正顺好房-更新房源"),
	
	FANGWANG_ESF_INSERT("fangwang_house:ershoufang_collect_insert", "房王-新增房源"),
	FANGWANG_ESF_UPDATE("fangwang_house:ershoufang_collect_update", "房王-更新房源"),
	
	REPEAT_ESF_INSERT("repeat_house:ershoufang_collect_insert", "遗漏的房源"),
	;
	
	private String redisKey;
	
	private String title;
	
	public String getRedisKey() {
		return redisKey;
	}

	public String getTitle() {
		return title;
	}

	ErshoufangRedisKey(String redisKey, String title) {
		this.redisKey = redisKey;
		this.title = title;
	}
}
