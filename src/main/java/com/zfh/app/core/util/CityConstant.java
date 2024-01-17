package com.zfh.app.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 拓展城市初始化，当前支持的城市列表
 * ClassName: CityEnum
 * @author Jungle
 * @dateTime 2020年6月2日下午12:03:02
 * @version v1.0
 */
public class CityConstant {

	public static final Map<String, Object> CITY_MAP = new HashMap<String, Object>();
	
	static {
			CITY_MAP.put("深圳", "4403");
			CITY_MAP.put("广州", "4401");
			CITY_MAP.put("东莞", "4419");
			CITY_MAP.put("惠州", "4413");
			CITY_MAP.put("北京", "1100");
			CITY_MAP.put("上海", "3100");
			CITY_MAP.put("杭州", "3301");
			CITY_MAP.put("重庆", "5000");
			CITY_MAP.put("武汉", "4201");
			CITY_MAP.put("成都", "5101");
			CITY_MAP.put("西安", "6101");
			CITY_MAP.put("南京", "3201");
			CITY_MAP.put("苏州", "3200");
			CITY_MAP.put("合肥", "3401");
			CITY_MAP.put("长沙", "4301");
			CITY_MAP.put("宁波", "3302");
	}

}


