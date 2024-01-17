package com.zfh.app.fang.enumerate;

public enum ErshoufangStatus {
	
	MISS_COVER_PIC(-10, "缺少数据-封面图片"),
	/**
	 * -9, "缺失数据-room"
	 */
	MISS_ROOM(-9, "缺失数据-房间数"),
	
	/**
	 * -8, "缺失数据-户型"
	 */
	MISS_HOUSE_TYPE(-8, "缺失数据-户型"),
	
	/**
	 * -7, "缺失数据-房源面积"
	 */
	MISS_AREA(-7, "缺失数据-房源面积"),
	
	/**
	 * -6, "缺失数据-房源单价"
	 */
	MISS_UNIT_PRICE(-6, "缺失数据-房源单价"),
	
	/**
	 * -5, "缺失数据-房源总价"
	 */
	MISS_TOTAL_PRICE(-5, "缺失数据-房源总价"),
	
	/**
	 * -4, "缺失数据-小区或标准小区无法关联"
	 */
	MISS_COMMUNITY(-4, "缺失数据-小区或标准小区无法关联"),
	
	/**
	 * -3, "缺失数据-房源用途未找到"
	 */
	MISS_USETYPE(-3, "缺失数据-房源用途未找到"),
	
	/**
	 * -2, "缺失数据-联系方式格式错误"
	 */
	MISS_TEL_FORMAT(-2, "缺失数据-联系方式格式错误"),
	
	/**
	 * -1, "缺失数据-联系方式"
	 */	
	MISS_TEL(-1, "缺失数据-联系方式"),
	
	/**
	 * 0, "正常"
	 */	
	NORMAL(0, "正常"),
	
	/**
	 * 20, "超出2个标准差"
	 */	
	OVER_STEP_TWO(20, "超出2个标准差"),
	
	/**
	 * 30, "超出3个标准差"
	 */	
	OVER_STEP_THREE(30, "超出3个标准差"),
	
	/**
	 * 40, "折扣率低于0.8"
	 */
	OVER_RATE_DOWN(40, "折扣率低于0.8"),
	
	/**
	 * 50, "折扣率高于1.2"
	 */
	OVER_RATE_UP(50, "折扣率高于1.2"),
	
	/**
	 * 100, "数据待上传"
	 */	
	WAIT_UPLOAD(100, "数据待上传"),
	
	/**
	 * 200, "数据上传成功"
	 */	
	UPLOADED(200, "数据上传成功"),
	;
	
	private int code;
	
	private String msg;
	
	ErshoufangStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public static ErshoufangStatus getEnum(int code) {
		ErshoufangStatus[] returnCode = ErshoufangStatus.values();
		for (ErshoufangStatus target : returnCode) {
			if (target.getCode() == code) {
				return target;
			}
		}
		return NORMAL;
	}
}
