package com.xmniao.xmn.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Zhiwen Zhang
 * 
 */
public class NumberUtil {

	public static Double getDoubleDivide100Value(Double value) {
		if (null != value) {
			return new BigDecimal(Double.toString(value)).divide(
					new BigDecimal(100)).doubleValue();
		}
		return 0.0;
	}

	/**
	 * double乘100
	 * 
	 * @param value
	 * @return
	 */
	public static Double getDoubleMultiply100Value(Double value) {
		if (null != value) {
			return new BigDecimal(Double.toString(value)).multiply(
					new BigDecimal(100)).doubleValue();
		}
		return 0.0;
	}
	
	/**
	 * 保留小数点后4位的字符串
	 */
	public static String getDouble4Fixedpoint(Double value){
		return new DecimalFormat("0.0000").format(value);
	}
	
	/**
	 * 保留小数点后2位的字符串
	 */
	public static String getDouble2Fixedpoint(Double value){
		return new DecimalFormat("0.00").format(value);
	}
	
	/**
	 * 
	 * 方法描述：保留小数点后n位的字符串
	 * 创建人： huang'tao
	 * 创建时间：2016-8-11上午10:56:47
	 * @param value  待转换的值
	 * @param length 小数点后保留位数
	 * @return
	 */
	public static String getNumberFixedpoint(BigDecimal value,int length){
		if(value==null){
			return null;
		}
		String formatParam="0.00000";
		if(length==0){
			formatParam="0";
		}else{
			formatParam=formatParam.substring(0, length+2);
		}
		return new DecimalFormat(formatParam).format(value);
	}
	
	public static void main(String[] args) {
		System.out.println(getNumberFixedpoint(new BigDecimal(12.365), 0));
		System.out.println(getNumberFixedpoint(new BigDecimal(12.365), 1));
		System.out.println(getNumberFixedpoint(new BigDecimal(12.365), 2));
		System.out.println(getNumberFixedpoint(new BigDecimal(12.365), 3));
	}
	
	
}
