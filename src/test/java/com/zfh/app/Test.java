package com.zfh.app;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Test {
	
	public static void main(String[] args) {
//		List<String> dates = new ArrayList<String>();
//	       
//		dates.add("02/31/2011");  //Invalid date
//		dates.add("02/27/2011");  //Valid date
//		 
//		String regex = "^(?<month>[0-3]?[0-9])/(?<day>[0-3]?[0-9])/(?<year>(?:[0-9]{2})?[0-9]{2})$";
//		 
//		Pattern pattern = Pattern.compile(regex);
//		 
//		for (String date : dates)
//		{
//		    Matcher matcher = pattern.matcher(date);
//		 
//		    //This is the root cause of error. Don't forget to do this !!
//		    matcher.matches();
//		 
//		    //Get date parts here
//		    String day = matcher.group(2);
//		    String month = matcher.group(1);
//		    String year = matcher.group(3);
//		 
//		    String formattedDate = month + "/" + day + "/" + year;
//		 
//		    System.out.println("Date to check : " + formattedDate);
//		}
//		Pattern p=Pattern.compile("(复式)|(双拼)|(别墅)"); 
//		Matcher m=p.matcher("东南朝向！双拼！复式实用4房！一手业主满2年！别墅好房"); 
//		while(m.find()) { 
//		     System.out.println(m.group()); 
//		} 
//		String str = null;
//		if (str !=null || !StringUtils.isEmpty(str) || !str.equals("")) {
//			System.out.println("结果：" + str);
//		}
		
		String host = "";
		try {
			URL Url = new URL("https://sh.lianjia.com/ershoufang/shanghaizhoubian/");
			host = Url.getHost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(host); 


	        
		System.out.println(PinyinHelper.getShortPinyin("深圳").toUpperCase().charAt(0));
	}
}
