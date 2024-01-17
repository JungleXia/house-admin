package com.mysiteforme.admin.util;

/**
 * 方差和标准差计算
 * 
 * @author CB
 * 
 * @dateTime 2019年10月4日上午9:57:23
 */
public class VarianceUtil {

	public static double variance(double[] x) {
		int m = x.length;
		double sum = 0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x[i];
		}
		double dAve = sum / m;// 求平均值
		double dVar = 0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		return dVar / m;
	}

	// 标准差σ=sqrt(s^2)
	public static double standardDiviation(double[] x) {
		int m = x.length;
		double sum = 0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x[i];
		}
		double dAve = sum / m;// 求平均值
		double dVar = 0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		// reture Math.sqrt(dVar/(m-1));// 样本标准差
		System.out.println("平均值：" + dAve);
		System.out.println("总体标准值：" + Math.sqrt(dVar / m));
		return Math.sqrt(dVar / m);
	}

	/**
	 * 	用平均值和标准差比较进行异常值检测，高斯定理
	 * @param x 计算的样本
	 * @param σ 几个标准差
	 * @return
	 * 2019年10月4日上午10:18:43
	 */
	public static Boolean[] outlierDetection(double[] x, int σ) {
		int m = x.length;
		double sum = 0;
		for (int i = 0; i < m; i++) {// 求和
			sum += x[i];
		}
		double dAve = sum / m;// 求平均值
		double dVar = 0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x[i] - dAve) * (x[i] - dAve);
		}
		// reture Math.sqrt(dVar/(m-1));// 样本标准差

		int ave = (int) dAve;
		int sqrt = (int) Math.sqrt(dVar / m);
//		System.out.println("平均值：" + ave);
//		System.out.println("总体标准值：" + sqrt);
		Boolean[] result = new Boolean[m];
		for (int i = 0; i < m; i++) {
			if (sqrt == 0) {
				result[i] = false;
			} else {
				result[i] = Math.abs(x[i] - ave) > (σ * sqrt) ? true : false;
			}
//			System.out.println(i + " = " + result[i]);
		}
		return result;
	}

	public static void main(String[] args) {
		double[] x = new double[] { 95884, 80295, 80295, 94446, 94446, 94872, 94446, 82368, 82368, 96154, 78106, 95715,
				95715, 84987, 88653, 85719, 78106 };
		Boolean[] result = outlierDetection(x, 2);
		for (int i = 0; i < result.length; i++) {
			if (result[i] == true) {
				System.out.println("第" + i + "个是异常值：" + x[i]);
			}
		}
	}
}
