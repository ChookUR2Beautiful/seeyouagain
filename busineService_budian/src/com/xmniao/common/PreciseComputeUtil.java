package com.xmniao.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数值计算工具类
 * @author huangxiaobin
 *
 */
public class PreciseComputeUtil {
	//默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param ds 减数，数组
	 * @return 两个参数的差
	 */

	public static double add(double v1, double...ds) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		for (double d : ds) {
			BigDecimal b2 = new BigDecimal(Double.toString(d));
			b1 = b1.add(b2);
		}
		return b1.doubleValue();
	}

	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2,int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.add(b2).doubleValue(), scale);
	}
	
	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		double dub = b1.subtract(b2).doubleValue();
		return dub;
	}

	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param ds 减数，数组
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double...ds) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		for (double d : ds) {
			BigDecimal b2 = new BigDecimal(Double.toString(d));
			b1 = b1.subtract(b2);
		}
		return b1.doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2,int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return keepTwoPoint(b1.multiply(b2).doubleValue(), scale);
	}
	
	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 小数点以后10位，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		
		if(b2.compareTo(BigDecimal.ZERO) == 0){
			return 0.00;
		}
		
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal ne = new BigDecimal("1");
		return b.divide(ne, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取小数 scale 位的比例值
	 * @param v 实际比例值
	 * @param scale 小数点后面的位数
	 * @return
	 */
	public static double getRation(double v,int scale){
		String str = v+"";
		if("0".equals(str)){
			return 0d;
		}
		int length = (scale + 2) > str.length()?str.length():(scale + 2);
		String str_index = str.substring(0,str.indexOf("."));
		String str_end = str.substring(str.indexOf("."),length);
		return Double.valueOf(str_index + str_end);
	}
	
	/**
	 * 获取金额（小余0.01 设为0，且0.01后面的数字抹去）
	 * @param moeny 实际金额
	 * @return
	 */
	public static double getMoney(double moeny){
		String str = moeny+"";
		
		int index = str.indexOf(".");
		if(index < 0 ){
			str = str+".00";
			index = str.indexOf(".");
		}
		String last = str.substring(index+1,str.length());
		if(last.length() == 1){
			last += "0";
		}else if(last.length() > 2){
			last = last.substring(0,2);
		}
		str = str.substring(0,index+1) + last;
		double retDouble = 0d;
		if("0".equals(str)){
			return 0d;
		}
		if(moeny >= 0.01 ){
			retDouble = Double.valueOf(str);
		}
		return retDouble;
	}
	
	/**
	 * double类型数据非四舍五入保留两位小数 非千分位
	 * 
	 * @param money
	 * @param length
	 * @return
	 */
	public static double keepTwoPoint(double money, int length) {
		String str = "#####0.";
		for (int i = 0; i < length; i++) {
			str = str + "0";
		}
		DecimalFormat format = new DecimalFormat(str);
		format.setRoundingMode(RoundingMode.FLOOR);
		return Double.parseDouble(format.format(money));
	}

	/**
	 * double类型数据非四舍五入保留两位小数 非千分位
	 *
	 * @param money
	 * @param length
	 * @return
	 */
	public static String keepTwoPointStr(double money, int length) {
		String str = "#####0.";
		for (int i = 0; i < length; i++) {
			str = str + "0";
		}
		DecimalFormat format = new DecimalFormat(str);
		format.setRoundingMode(RoundingMode.FLOOR);
		return format.format(money);
	}
}


