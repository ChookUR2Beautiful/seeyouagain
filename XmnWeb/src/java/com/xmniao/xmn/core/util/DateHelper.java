
package com.xmniao.xmn.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 日期工具类
 * 
 * @author YangJing
 *
 */
public class DateHelper {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort = "yyyyMMdd";
    
    public static Calendar calendar = Calendar.getInstance();
    
    /** 标准日期*/
    public static final String  STANDARD_DATE = "yyyy-MM-dd";
    
    /** 中文日期*/
    public static final String  CHINESE_DATE = "yyyy年MM月dd日";
    
    /** 一天中的毫秒数 */
    public static final long MS = 86400000;
    /** 上周 */
    
    public static final String lastWeek= "上周";
    /** 上个月 */
    
    public static final String lastMonth = "上个月";
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 依传入的格式 获取系统当期年月日(精确到天)，
	 * @return
	 */
	public static String getDate(String dtShort){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	public static String getCurrentDate(){
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 0);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
	}
	
	public static String getCurrenMonth(){
		return null;
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	

	/**
	 * 获取昨天的日期 2014-03-18
	 * @param str 输入 2014-03-18  输出 2014-03-17
	 * @return
	 */
	public static String getYesterday(String str){
		
		if(str == null || "".equals(str)){
			return "";
		}
		
		String reStr = "";
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        Date dt = sdf.parse(str);
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(dt);
	        rightNow.add(Calendar.DAY_OF_YEAR,-1);
	        Date dt1=rightNow.getTime();
	        reStr = sdf.format(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reStr;
	}
	
	/**
	 * 获取明天的日期 2014-03-18
	 * @param str 输入 2014-03-18  输出 2014-03-19
	 * @return
	 */
	public static String getTomorrow(String str){
		
		if(str == null || "".equals(str)){
			return "";
		}
		
		String reStr = "";
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dt = sdf.parse(str);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.DAY_OF_YEAR,+1);
			Date dt1=rightNow.getTime();
			reStr = sdf.format(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reStr;
	}
	
	/**
	 * 获取本周日期
	 * @param format 返回日期格式
	 * @param index  获取日期天数,如 6 则星期一到星期 六
	 * @return
	 */
	public static List<String> getDateToWeek(String format,int index) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		Long msec = c.getTimeInMillis();
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < index; i++) {
			msec -= MS;
			c.setTimeInMillis(msec);
			l.add(sdf.format(c.getTime()));
		}
		return l;
	}
	
	
	
	
	/**
	 * 得到完整日期
	 * @param date 月份日期   格式  xx月xx日
	 * @return 完整日期  格式 xxxx-xx-xx
	 */
	public static String getDoneDate(String date) {

		try {
			String doneDate = Calendar.getInstance().get(Calendar.YEAR) + "年" + date;
			SimpleDateFormat sdf = new SimpleDateFormat(CHINESE_DATE);
			Date time = sdf.parse(doneDate);
			sdf.applyPattern(STANDARD_DATE);
			return sdf.format(time);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到当月有效时间的开始时间与结束时间
	 * @param time 传入 时间参数    Calendar.DATE即可获取月份有效的开始与结束时间
	 * @return
	 */
	public static String[] getMonth(int time){
		SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE);
		Calendar c=Calendar.getInstance();
		c.roll(Calendar.DAY_OF_YEAR, -1);
		
		String endDate = sdf.format(c.getTime());
		
		c.roll(Calendar.DAY_OF_YEAR, -c.get(time)+1);
		
		String startDate = sdf.format(c.getTime());
		
		return new String[]{startDate,endDate};
	}
	
	
	
	/**
	 * 获取上一个月或者上一周的开始跟结束时间
	 * @param time 时间参数    Calendar.DATE为月份的开始与结束时间，Calendar.DAY_OF_WEEK 为获取一周的开始与结束时间
	 * @return
	 */
	public static String[] getLastTime(int time){
		SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE);
		Calendar c=Calendar.getInstance();
		
		c.roll(Calendar.DAY_OF_YEAR, -c.get(time));
		//得到上一月或者上一周的结束时间
		String endDate = sdf.format(c.getTime());
		c.roll(Calendar.DAY_OF_YEAR, -(c.get(time)));
		//得到上一月或者上一周的开始时间
		String startDate = sdf.format(c.getTime());
		return new String[]{startDate,endDate};
	}
	
	/**
	 * 传入格式yyyy-MM
	 * 获取传入月份的天数
	 * @return
	 */
	public static int getMonthDays(String str){
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM"); 
		try {
			rightNow.setTime(simpleDate.parse(str)); 
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	

	/**
	 * 传入格式yyyy-MM
	 * 获取传入月份的天数
	 * @return
	 */
	public static int getMonthMinDays(String str){
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM"); 
		try {
			rightNow.setTime(simpleDate.parse(str)); 
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return rightNow.getActualMinimum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 判读传入的时间是否是当月的第一天
	 * @param str
	 * @return
	 */
	public static boolean isTodayFirst(String str){			
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			rightNow.setTime(simpleDate.parse(str)); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.get(rightNow.DAY_OF_MONTH)==1?true:false;
				
	}
	
	/**
	 * 格式"yyyy-MM-dd 输入 2014-03-01
	 * 取得昨天的年月 输出2014-02
	 * @return
	 */
	public static String getBeforeYearMonth(String str){
		String yearStr =DateHelper.getYesterday(str);
		return yearStr.substring(0, 7);
	}
	
	
	
	/**
	 * 获取自定义的日期格式
	 * @param date   日期
	 * @param currentFormat  传入日期的格式
	 * @param format  返回日期的格式
	 * @return
	 */
	public static String getCustomDate(String date,String currentFormat,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(currentFormat);
		try {
			Date d = sdf.parse(date);
			sdf.applyPattern(format);
			return sdf.format(d);
		} catch (Exception e) {
		}
		 return null;
	}
	
	public static String getMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			return sdf.format(new Date());
		} catch (Exception e) {
		}
		 return null;
	}
	
	/**
	 * 日志中时间转换
	 * 传入格式  01/Mar/2014:09:38:14
	 * 输出格式
	 * @throws ParseException 
	 */
	public static String getDateFormat(String str){
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy:hh:mm:ss", Locale.ENGLISH);  // Locale.ENGLISH
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d;
		try {
			if(null!=str&&!str.equals("")){
				String strdate = sdf.parse(str).toString();
				d = new Date(Date.parse(strdate));
				d.setHours(d.getHours()-14); 
				return dataFormat.format(d);
			}else{
				return "";
			}
						
		} catch (ParseException e) {
			return "";
		}         			
	}
	
	/**
	 * 根据传入天数 当前日期获取之前日期    例如当前日期  2015-01-15  传入 <code>3</code>  返回  2015-01-12
	 * @param day
	 * @return
	 */
	public static String getOtherDayByDay(int day){
		SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE);
		Calendar c=Calendar.getInstance();
		Long msec = c.getTimeInMillis();
		msec = msec -(MS*day);
		c.setTimeInMillis(msec);
		return sdf.format(c.getTime());
	}
	/**
	 * 根据传入天数 当前日期获取之前日期    例如当前日期  2015-01-15  传入 <code>3</code>  返回  2015-01-12
	 * @param day
	 * @return
	 * @throws ParseException 
	 */
	public static String getOtherDayByDay(String str,int day) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE);
		Calendar c=Calendar.getInstance();
		c.setTime(sdf.parse(str));
		Long msec = c.getTimeInMillis();
		msec = msec +(MS*day);
		c.setTimeInMillis(msec);
		return sdf.format(c.getTime());
	}
	
	public static void main(String[] args) throws ParseException {
		
		String sdate="2015-09-01",enddate="2015-09-20",newsdate = null, newEndDate = null;
		int days = Integer.parseInt(enddate.split("-")[2]);
		int section = days/5;
		 section = days%5>0 ? section+1:section;
		List<String> s = new ArrayList<>();
		for(int i=0;i<section;i++){
			s.add(sdate);
			newEndDate = getOtherDayByDay(sdate,5);
			if(newEndDate.compareTo(enddate)>=0){
				s.add(enddate);
				break;
			}
			newsdate = getTomorrow(newEndDate);
			if(newsdate.compareTo(enddate)>=0){
				s.add(enddate);
				break;
			}
			s.add(newEndDate);
			sdate = newsdate;	
			
		}
		System.out.println(s);
	}
}
