package com.xmniao.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilString {
	/**
	 * 四位随机数
	 * @return
	 */
	public static String getFourDigit(){
		Random ran=new Random();
		int r=0;
		m1:while(true){
			int n=ran.nextInt(10000);
			if(n<100){
				continue m1;
			}
			r=n;
			int[] bs=new int[4];
			for(int i=0;i<bs.length;i++){
				bs[i]=n%10;
				n/=10;
			}
			Arrays.sort(bs);
			for(int i=1;i<bs.length;i++){
				if(bs[i-1]==bs[i]){
					continue m1;
				}
			}
			break;
		}
		return r<1000?"0"+r:""+r;
	}
	/**
	 * 八位随机数
	 * @return
	 */
	public static String getFourDigitZFB(){
		Random ran=new Random();
		int r=0;
		m1:while(true){
			int n=ran.nextInt(100000000);
			if(n<10000000){
				continue m1;
			}
			r=n;
			int[] bs=new int[4];
			for(int i=0;i<bs.length;i++){
				bs[i]=n%10;
				n/=10;
			}
			Arrays.sort(bs);
			for(int i=1;i<bs.length;i++){
				if(bs[i-1]==bs[i]){
					continue m1;
				}
			}
			break;
		}
		return String.format("%08d", r);
	}
	
	/**
	 * 日期转换：
	 * 格式 yyyy-MM-dd HH:mm:ss---->yyyyMMddHHmmss
	 * @return
	 * @throws ParseException
	 */
	public static String dateFormatToNumber(String srcDate) throws ParseException{
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	
	
	/**
	 * 日期转换：
	 * 格式 yyyy-MM-dd HH:mm:ss---->yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static String dateFormatTodate(String srcDate) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	/**
	 * 获取后一天的日期
	 * @param srcDate
	 * @return
	 * @throws ParseException
	 */
	public static String theDateOfTomorrow(String srcDate) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}

		 Calendar c = Calendar.getInstance();  
         c.setTime(date);  
         c.add(c.DATE, 1); 
         
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 获取前一天的日期
	 * @param srcDate
	 * @return
	 * @throws ParseException
	 */
	public static String theDateOfYesterday(String srcDate) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}

		 Calendar c = Calendar.getInstance();  
         c.setTime(date);  
         c.add(c.DATE, -1); 
         
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 获取上一个月的日期
	 * @param srcDate
	 * @return
	 * @throws ParseException
	 */
	public static String theDateOfPreMonth(String srcDate) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}

		 Calendar c = Calendar.getInstance();  
         c.setTime(date);  
         c.add(c.MONTH, -1); 
         
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 获取下一个月的日期
	 * @param srcDate
	 * @return
	 * @throws ParseException
	 */
	public static String theDateOfNextMonth(String srcDate) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}

		 Calendar c = Calendar.getInstance();  
         c.setTime(date);  
         c.add(c.MONTH, 1); 
         
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 获取上index月的日期
	 * @param srcDate
	 * @return
	 * @throws ParseException
	 */
	public static String theDateOfPreMonth(String srcDate,int index) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}

		 Calendar c = Calendar.getInstance();  
         c.setTime(date);  
         c.add(c.MONTH, Integer.parseInt("-"+index)); 
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 获取下index月的日期
	 * @param srcDate
	 * @return
	 * @throws ParseException
	 */
	public static String theDateOfNextMonth(String srcDate,int index) throws ParseException{
		Date date = null;
		srcDate = srcDate.trim();
		if(srcDate.length() == 10){
			date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
		}

		 Calendar c = Calendar.getInstance();  
         c.setTime(date);  
         c.add(c.MONTH, index); 
         
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String dateFormatNow(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	
	
	/**
	 * 字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean stringIsNotBlank(String str) {
		if (str != null) {
			if (!str.trim().equals("") && !str.trim().equalsIgnoreCase("null")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串是否是空
	 * @param str
	 * @return
	 */
	public static boolean stringIsBlank(String str) {
		return !stringIsNotBlank(str);
	}
	
	/**
	 * 字符串首字母大写
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
       return  name;
    }
	
	/**
	 * 驼峰命名改下划线形式 sellerAmount ---> seller_amount
	 * @param str
	 * @return
	 */
	public static String stringTurnUnderline(String str){
		StringBuilder sb = new StringBuilder("");  
		for(char code:str.toCharArray()){
			if(code>='A' && code<='Z'){
				sb.append("_"+(char)(code+32));
			}else{
				sb.append(code);
			}
		}
		return sb.toString();
	}

}
