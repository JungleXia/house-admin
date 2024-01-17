package com.zfh.app.fang.enumerate;

public class RedisKeyConst {
	/**
	 * 小区关联信息 小区映射 md5Url - standId
	 */
	public static final String COMMUNITY_BASE_REF_KEY = "community_base_ref_key";
	
	/**
	 * 等待分析的standId
	 */
	public static final String WAITING_WORK_STAND_ID_SET = "waiting_work:stand_id_set";
	
	/**
	 * 等待填充数据的standId
	 */
	public static final String WAITING_FILL_STAND_ID_SET = "waiting_fill:stand_id_set";
	
	/**
	 * 等待均价分析的standId
	 */
	public static final String WAITING_PRICE_STAND_ID_SET = "waiting_price:stand_id_set";
	
	
	/**
	 * 等待上传同步的数据
	 */
	/**
	 *  等待上传同步的房源id
	 *  
	 */
	public static final String WAITING_UPLOAD_HOUSE_ID_SET = "waiting_upload:house_id_set";
	
	/**
	 * 等待上传的标准小区standId
	 */
	public static final String WAITING_UPLOAD_STAND_ID_SET = "waiting_upload:stand_id_set";
	
	/**
	 * 等待上传的小区 communityId
	 */
	public static final String WAITING_UPLOAD_COMMUNITY_ID = "waiting_upload:community_id_set";
	
	/**
	 * 等待上传的小区baseId
	 */
	public static final String WAITING_UPLOAD_COMMUNITY_BASE_ID = "waiting_upload:community_base_id_set";
	
	/**
	 * 等待上传的小区 baseRefId
	 */
	public static final String WAITING_UPLOAD_COMMUNITY_BASE_REF_ID = "waiting_upload:community_base_ref_id_set";
	
	/**
	 * 等待上传的communityAlias Id
	 */
	public static final String WAITING_UPLOAD_SALIAS_ID_SET = "waiting_upload:alias_id_set";
}
