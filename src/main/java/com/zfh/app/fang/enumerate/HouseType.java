package com.zfh.app.fang.enumerate;

public enum HouseType {
	/**
	 * 1室
	 */
	ONE(1, "一室"),
	/**
	 * 2室
	 */
	TWO(2, "二室"),
	
	/**
	 * 3室
	 */
	THREE(3, "三室"),
	
	/**
	 * 4室
	 */
	FOUR(4, "四室"),
	
	/**
	 * 5室
	 */
	FIVE(5,"五室"),
	/**
	 * 6室
	 */
	SIX(6,"六室"),
	/**
	 * 7室
	 */
	SEVEN(7,"七室"),
	/**
	 * 8室
	 */
	EIGHT(8,"八室"),
	/**
	 * 9室
	 */
	NIGHT(9,"九室"),
	;

	private int code;
	private String msg;
		
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	HouseType(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static HouseType getEnum(int code) {
		HouseType[] returnCode = HouseType.values();
		for (HouseType target : returnCode) {
			if (target.getCode() == code) {
				return target;
			}
		}

		return ONE;
	}
	
	public static HouseType turnZH(HouseType houseType) {
		String msg = houseType.getMsg();
		
		return houseType;
	}

	@Override
	public String toString() {
		return "{code:" + this.getCode() + ", msg: " + this.getMsg() + "}";
	}
}