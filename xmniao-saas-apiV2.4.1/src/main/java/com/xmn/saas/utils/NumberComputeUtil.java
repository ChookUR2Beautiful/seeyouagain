package com.xmn.saas.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 数字算法工具类
 * 
 * @ClassName: NumberComputeUtil
 * @Description: TODO
 * @author dengqiang
 * @date 2016年9月23日 下午2:02:13
 *
 */
@SuppressWarnings("all")
public class NumberComputeUtil {
	
	/**
	 * 将百分比转换成小数
	 * @Title: percentConvertNumber 
	 * @Description: TODO 
	 * @param @param percent
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Double    返回类型 
	 * @throws
	 */
	public Double percentConvertNumber(String percent) throws Exception{
		if (StringUtils.isBlank(percent)) {
			throw new Exception("percent is null");
		}
		NumberFormat numberFormat=NumberFormat.getPercentInstance();
		Number number=numberFormat.parse(percent);
		return number.doubleValue();
	}
	
	/**
	 * 生成在[min,max]之间的随机(保留两小数)
	 * @Title: randomNumberBetween 
	 * @Description: TODO 偏大数=[min,max]的区间数至 max(最大数)随机数值（包含中间数值）
	 * @Description: TODO 偏小数=min(最小数)至[min,max]的区间数随机数值（不包含中间数值）
	 * @param @param min(最小数)
	 * @param @param max(最大数)
	 * @param @param flag(是否产生[min,max]之间的中间值随机偏大数标识)
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public BigDecimal randomNumberBetween(BigDecimal min, BigDecimal max,boolean flag) throws Exception {
		BigDecimal result=null;
		if (min == null || max == null) {
			throw new Exception("min or max is null");
		}
		if (min.compareTo(max)>0) {
			throw new Exception("min > max");
		}
		if (min.compareTo(max) == 0) {
			return min;
		}
		Random random = new Random();
		//计算公式：(min & max) + ((min ^ max) >> 1)
		Integer middleNumber=(min.setScale(0,BigDecimal.ROUND_HALF_UP).intValue() & max.setScale(0,BigDecimal.ROUND_HALF_UP).intValue()) + ((min.setScale(0,BigDecimal.ROUND_HALF_UP).intValue() ^ max.setScale(0,BigDecimal.ROUND_HALF_UP).intValue()) >> 1);// 计算[min,max]之间的中间数值(四舍五入取整数)
		BigDecimal middle =new BigDecimal(middleNumber).setScale(2,BigDecimal.ROUND_HALF_UP);  //[min,max]之间的中间数值保留两位小数
		min=min.setScale(2,BigDecimal.ROUND_HALF_UP);//最小数四舍五入并保留两位小数
		max=max.setScale(2,BigDecimal.ROUND_HALF_UP);//最大数四舍五入并保留两位小数
		if (flag) {
			while (true) {
				result=middle.add((max.subtract(middle)).multiply(new BigDecimal(random.nextDouble())));// [min,max]之间的中间数值随机偏大数
				result=result.setScale(2,BigDecimal.ROUND_HALF_UP);
				if(result.compareTo(middle)==0 || result.compareTo(middle)==1){
					break;
				}
			}
		} else {
			 while (true) {
				result=min.add((middle.subtract(min)).multiply(new BigDecimal(random.nextDouble())));// [min,max]之间的中间数值随机偏小数
				result.setScale(2,BigDecimal.ROUND_HALF_UP);
				if(result.compareTo(middle)==-1){
					break;
				}
			}
		}
		return result;
	}


	/**
	 * 生成在[min,max]之间的随机浮点数(保留两小数)
	 * @Title: randomNumberBetween 
	 * @Description: TODO 
	 * @param @param min(最小数)
	 * @param @param max(最大数)
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String randomNumberBetween(Double min,Double max) throws Exception {
		if (min == null || max == null) {
			throw new Exception("min or max is null");
		}
		if (min > max) {
			throw new Exception("min > max");
		}
		if (min == max) {
			return String.valueOf(min);
		}
		
		BigDecimal minBigDecimal = new BigDecimal(min);  
        BigDecimal maxBigDecimal = new BigDecimal(max); 
		Random random = new Random();
		Double randomNumber=minBigDecimal.add(new BigDecimal((maxBigDecimal.subtract(minBigDecimal).doubleValue()) * random.nextDouble())).doubleValue();
		DecimalFormat  decimalFormat =new DecimalFormat("######0.00"); 
		return decimalFormat.format(randomNumber);
	}

	/**
	 * 生成在[min,max]之间的随机整数
	 * @Title: randomNumberBetween 
	 * @Description: TODO 偏大数=[min,max]的区间数至 max(最大数)随机数值（包含中间数值）
	 * @Description: TODO 偏小数=min(最小数)至[min,max]的区间数随机数值（不包含中间数值）
	 * @param @param min(最小数)
	 * @param @param max(最大数)
	 * @param @param flag(是否产生[min,max]之间的中间值随机偏大数标识)
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	public Integer randomNumberBetween(Integer min, Integer max, boolean flag) throws Exception {
		Integer result=null;
		if (min == null || max == null) {
			throw new Exception("min or max is null");
		}
		if (max < min) {
			throw new Exception("min > max");
		}
		if (min == max) {
			return min;
		}
		Random random = new Random();
		//计算公式：(min & max) + ((min ^ max) >> 1)
		Integer middle = (min & max) + ((min ^ max) >> 1); // 计算[min,max]之间的中间数值
		if (flag) {
			while (true) {
				result=random.nextInt(max - middle + 1) + middle;// [min,max]之间的中间数值随机偏大数
				if(result>=middle){
					break;
				}
			}
		} else {
			while (true) {
				result=random.nextInt(middle - min + 1) + min; // [min,max]之间的中间数值随机偏小数
				if(result<middle){
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 生成在[min,max]之间的随机整数
	 * @Title: randomNumberBetween 
	 * @Description: TODO 
	 * @param @param min(最小数)
	 * @param @param max(最大数)
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	public Integer randomNumberBetween(Integer min, Integer max) throws Exception {
		if (min == null || max == null) {
			throw new Exception("min or max is null");
		}
		if (max < min) {
			throw new Exception("min > max");
		}
		if (min == max) {
			return min;
		}
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * 创建订单号
	 */
	public static String buildOrderNo() throws Exception{
		String dateStr = CalendarUtil.getCurrentDate(CalendarUtil.FORMAT1);
    	String[] da = dateStr.replace(" ","-").replace(":","-").substring(2, dateStr.length()).split("-");
    	StringBuilder db = new StringBuilder();
    	for (int i = 0; i < da.length; i++) {
    		String dr = da[i];
    		db.append(dr);
		}
		return db.append(((int)(Math.random()*9000+1000))+"").toString();
	}

	/**
	 * 根据多位随机数字字符串
	 * @param size 生成数字字符串的位数
	 * @return
	 */
	public static String getRandomNumberStringOfSize(int size){
		String[] array = {"1","2","3","4","5","6","7","8","9","0"};
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int index = (int) (Math.random() * 10);
			sb.append(array[index]);
		}
		return sb.toString();
	}
	/**
	 * 判断输入字符串是否为数字
	 * @param str 数字字符串
	 * @return boolean    返回类型
	 * @throws
	 */
	public static boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	
	/**
	 * 去掉小数位多余的0，返回等值的数值
	 * @param number 字符串数字
	 * @return BigDecimal    返回类型
	 */
	public static BigDecimal trimZero(String number) throws Exception{
		BigDecimal decimal = new BigDecimal("0");
		number = number.replaceAll("0+?$", "");//去掉后面无用的零
		number = number.replaceAll("[.]$", "");//如小数点后面全是零则去掉
	    decimal = new BigDecimal(number);
		return decimal;
	}
	
	public static void main(String[] args) {
		try {
			NumberComputeUtil numberComputeUtil = new NumberComputeUtil();
			//System.out.println(numberUtil.randomNumberBetween(6,66,true));
			//System.out.println(numberUtil.randomNumberBetween(1.00,2.00));
			System.out.println(numberComputeUtil.randomNumberBetween(new BigDecimal(3.01),new BigDecimal(11.66),true));
			//System.out.println(numberUtil.percentConvertNumber("5%"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
