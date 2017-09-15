package com.xmniao.xmn.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
/**
 * 
* 项目名称：saasService   
* 类名称：DateUtils 
* 类描述：日期的一些操作，如：格式，追加等   
* 创建人：zhangchangyuan   
* 创建时间：2016年3月29日 下午5:37:46   
* @version    
*
 */
public class DateUtil {
	//默认格式,精确到秒
	public static String defaultSimpleFormater = "yyyy-MM-dd HH:mm:ss";
	//精确到日
	public static String daySimpleFormater ="yyyy-MM-dd";
	
	//默认格式,精确到分
	public static String minuteSimpleFormater = "yyyy-MM-dd HH:mm";
	
	//默认格式,精确到分
	public static String defaultSimpleFormater2 = "yyyy.MM.dd HH.mm.ss";
	
	/**
	* 默认简单日期字符串
	* 
	* @return
	*/
	public static String getDefaultSimpleFormater() {
	   return defaultSimpleFormater;
	}

	/**
	* 设置默认简单日期格式字符串
	* 
	* @param defaultFormatString
	*/
	public static void setDefaultSimpleFormater(String defaultFormatString) {
	   DateUtil.defaultSimpleFormater = defaultFormatString;
	}

	/**
	* 格式化日期
	* 
	* @param date
	* @param formatString
	* @return
	*/
	public static String format(Date date, String formatString) {
	   SimpleDateFormat df = new SimpleDateFormat(formatString);
	   return df.format(date);
	}

	/**
	 * 格式化指定日期
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Integer year,Integer month,Integer day) {
		Calendar calendar = Calendar.getInstance();
		if (year != null && month == null && day == null) {
			calendar.set(Calendar.YEAR,year);
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			
		}else if (year != null && month != null && day == null) {
			calendar.set(Calendar.YEAR,year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			
		}else if (year != null && month != null && day != null) {
			calendar.set(Calendar.YEAR,year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			
		}
		
		if (year != null || month != null || day != null) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}
	
	/**
	* 格式化日期(使用默认格式)
	* 
	* @param date
	* @return
	*/
	public static String format(Date date) {
	   return format(date, defaultSimpleFormater);
	}

	/**
	* 转换成日期
	* 
	* @param dateString
	* @param formatString
	* @return
	*/
	public static Date parse(String dateString, String formatString) {
	   SimpleDateFormat df = new SimpleDateFormat(formatString);
	   try {
	    return df.parse(dateString);
	   } catch (ParseException e) {
	    return null;
	   }
	}

	/**
	* 转换成日期(使用默认格式)
	* 
	* @param dateString
	* @return
	*/
	public static Date parse(String dateString) {
	   return parse(dateString, defaultSimpleFormater);
	}
	
	/**
	 * 
	* @Title: beforeday
	* @Description: (前天)
	* @return Date    返回类型
	* @throws
	 */
	public static Date beforeday(){
		return addDay(-2);
	}
	/**
	* 昨天
	* 
	* @return
	*/
	public static Date yesterday() {
	   return addDay(-1);
	}

	/**
	* 明天
	* 
	* @return
	*/
	public static Date tomorrow() {
	   return addDay(1);
	}

	/**
	* 现在
	* 
	* @return
	*/
	public static Date now() {
	   return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 
	* @Title: firstDayOfMonth
	* @Description:某一个月第一天 
	* @return Date
	 */
	public static Date firstDayOfMonth(Date date){
		 Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
	}
	
	/**
	 * 
	* @Title: firstDayOfMonth
	* @Description:某一个月最后一天
	* @return Date
	 */
	public static Date lastDayOfMonth(Date date){
		 Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return calendar.getTime();
	}
	
	/**
	* 按日加
	* 
	* @param value
	* @return
	*/
	public static Date addDay(int value) {
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.DAY_OF_YEAR, value);
	   return now.getTime();
	}

	/**
	* 按日加,指定日期
	* 
	* @param date
	* @param value
	* @return
	*/
	public static Date addDay(Date date, int value) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   now.add(Calendar.DAY_OF_YEAR, value);
	   return now.getTime();
	}

	/**
	* 按月加
	* 
	* @param value
	* @return
	*/
	public static Date addMonth(int value) {
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.MONTH, value);
	   return now.getTime();
	}

	/**
	* 按月加,指定日期
	* 
	* @param date
	* @param value
	* @return
	*/
	public static Date addMonth(Date date, int value) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   now.add(Calendar.MONTH, value);
	   return now.getTime();
	}

	/**
	* 按年加
	* 
	* @param value
	* @return
	*/
	public static Date addYear(int value) {
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.YEAR, value);
	   return now.getTime();
	}

	/**
	* 按年加,指定日期
	* 
	* @param date
	* @param value
	* @return
	*/
	public static Date addYear(Date date, int value) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   now.add(Calendar.YEAR, value);
	   return now.getTime();
	}

	/**
	* 按小时加
	* 
	* @param value
	* @return
	*/
	public static Date addHour(int value) {
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.HOUR_OF_DAY, value);
	   return now.getTime();
	}

	/**
	* 按小时加,指定日期
	* 
	* @param date
	* @param value
	* @return
	*/
	public static Date addHour(Date date, int value) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   now.add(Calendar.HOUR_OF_DAY, value);
	   return now.getTime();
	}

	/**
	* 按分钟加
	* 
	* @param value
	* @return
	*/
	public static Date addMinute(int value) {
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.MINUTE, value);
	   return now.getTime();
	}

	/**
	* 按分钟加,指定日期
	* 
	* @param date
	* @param value
	* @return
	*/
	public static Date addMinute(Date date, int value) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   now.add(Calendar.MINUTE, value);
	   return now.getTime();
	}
	
	/**
	* 按秒数加
	* 
	* @param value
	* @return
	*/
	public static Date addSecond(int value) {
	   Calendar now = Calendar.getInstance();
	   now.add(Calendar.SECOND, value);
	   return now.getTime();
	}

	/**
	* 按秒数加,指定日期
	* 
	* @param date
	* @param value
	* @return
	*/
	public static Date addSecond(Date date, int value) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   now.add(Calendar.SECOND, value);
	   return now.getTime();
	}


	/**
	* 年份
	* 
	* @return
	*/
	public static int year() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.YEAR);
	}

	/**
	* 年份 指定日期
	* 
	* @return
	*/
	public static int year(Date date){
		 Calendar now = Calendar.getInstance();
		 now.setTime(date);
		 return now.get(Calendar.YEAR);
	}
	/**
	* 月份
	* 
	* @return
	*/
	public static int month() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.MONTH);
	}
	
	/**
	* 月份 指定日期
	* 
	* @return
	*/
	public static int month(Date date) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   return now.get(Calendar.MONTH);
	}

	/**
	* 日(号)
	* 
	* @return
	*/
	public static int day() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	* 日(号) 指定日期
	* 
	* @return
	*/
	public static int day(Date date) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	* 小时(点)
	* 
	* @return
	*/
	public static int hour() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	* 小时(点) 指定日期
	* 
	* @return
	*/
	public static int hour(Date date) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   return now.get(Calendar.HOUR);
	}
	/**
	* 分钟
	* 
	* @return
	*/
	public static int minute() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.MINUTE);
	}

	/**
	* 分钟 指定日期
	* 
	* @return
	*/
	public static int minute(Date date) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   return now.get(Calendar.MINUTE);
	}
	/**
	* 秒
	* 
	* @return
	*/
	public static int second() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.SECOND);
	}

	/**
	* 秒 指定日期
	* 
	* @return
	*/
	public static int second(Date date) {
	   Calendar now = Calendar.getInstance();
	   now.setTime(date);
	   return now.get(Calendar.SECOND);
	}
	/**
	* 星期几(礼拜几)
	* 
	* @return
	*/
	public static int weekday() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	* 是上午吗?
	* 
	* @return
	*/
	public static boolean isAm() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.AM_PM) == 0;
	}

	/**
	* 是下午吗?
	* 
	* @return
	*/
	public static boolean isPm() {
	   Calendar now = Calendar.getInstance();
	   return now.get(Calendar.AM_PM) == 1;
	}
	
	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 在map里压入当前时间，返回原有对象
	 * 
	 */
	public static Map<String,Object> getCurrentTime(Map<String,Object> map){
		map.put("datetime", format(new Date(),defaultSimpleFormater));
		return map;
	}
	
	/**
	 * string形式返回当前时间
	 */
	public static String  getCurrentTimeStr(){
		return format(new Date(),defaultSimpleFormater);
	}
	
	/**
	 * 历史到账日期
	 * 
	 * @return
	 */
	public static boolean setAccountdateDate(String pTime) throws ParseException {
		// String pTime ="2014-12-10";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		c.add(5, +3);

		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}

		return dayForWeek > 5 ? true : false;
	}

	/**
	 * 历史到账日期
	 * 
	 * @return
	 */
	public static String setAccountdateDateTwo(String pTime) throws ParseException {
		// String pTime ="2014-12-10";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		c.add(5, +3);

		return format.format(new Date(c.getTime().toString()));
	}
	/**
	 * 获取当周的星期一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonday(Date date) {
		date = DateUtils.truncate(date, Calendar.DATE);
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek -= 1;
		}
		return DateUtils.addDays(date, 1 - dayOfWeek);
	}

	/**
	 * 获取当周的星期天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSunday(Date date) {
		date = DateUtils.truncate(date, Calendar.DATE);
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek -= 1;
		}
		return DateUtils.addDays(date, 7 - dayOfWeek);
	}

	/**
	 * 得到当前月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		date = DateUtils.truncate(date, Calendar.MONTH);
		return date;
	}
	
	/**
	 * 获取指定时间的当天凌晨0点的时间点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayFirstTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到当前月的最后天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		date = DateUtils.truncate(date, Calendar.MONDAY);
		date = DateUtils.addMonths(date, 1);
		date = DateUtils.addDays(date, -1);
		return date;
	}

	/**
	 * 得到当前时间月的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthDayNum(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		return dateOfMonth;
	}
	/**
	 * 
	* @Title: setDateCondition
	* @Description: (设置查询时时间作为判断条件用,参数为Map型)
	* @return void    返回类型
	* @throws
	 */
	public static void setDateCondition(int type,Map<Object,Object> map){
		if(map.size() < 1){
			map = new HashMap<Object,Object>();
		}
		switch(type){
		//全部
		case 0:
			return ;
		//今天
		case 1:
			String today = format(now(),defaultSimpleFormater);
			String tomorrow = format(tomorrow(),daySimpleFormater)+" 00:00:00";
			map.put("startDate", today);
			map.put("endDate", tomorrow);
			break;
		//昨天
		case 2:
			String yesterday = format(yesterday(),daySimpleFormater)+" 00:00:00";
			String nowday = format(now(),defaultSimpleFormater);
			map.put("startDate", yesterday);
			map.put("endDate", nowday);
			break;
		//本月
		case 3:
			//本月第一天
			String thisMouth = DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.now()),DateUtil.defaultSimpleFormater);
			//下个月第一天
			String nextMouth = DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), 1)),DateUtil.defaultSimpleFormater);
			map.put("startDate", thisMouth);
			map.put("endDate", nextMouth);
			break;
		//上月
		case 4:
			//上个月第一天
			String reviewMouth = DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), -1)),DateUtil.defaultSimpleFormater);
			//本月第一天
			String nowMouth = DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.now()),DateUtil.defaultSimpleFormater);
			map.put("startDate", reviewMouth);
			map.put("endDate", nowMouth);
			break;
		}
			
	}
	
	
	/**
	 * 
	* @方法名称: secToTime
	* @描述: 根据描述转换为时分秒：00:00:00
	* @返回类型 String
	* @创建时间 2016年9月17日
	* @param time
	* @return
	 */
	public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(hour)+":"+unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    }  
  
    public static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
	
}
