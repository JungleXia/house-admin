package com.zfh.app;

import java.math.BigDecimal;

public class TestMathE {

	public static void main(String[] args) {
		BigDecimal a = BigDecimal.valueOf(45).divide(BigDecimal.valueOf(1000000));
		System.out.println("k=" +  a);
		for (int x = 0; x <= 10; x++) {
	        double e = Math.E;//自然常数e的近似值
	        double d = Math.pow(e, x);//e^x 

	        BigDecimal f = BigDecimal.valueOf(d).multiply(BigDecimal.valueOf(0.000045)).setScale(6, BigDecimal.ROUND_HALF_DOWN);//e^x 
	        System.out.println("ke^"+x+"="+f);//输出结果
		}
	}
}
