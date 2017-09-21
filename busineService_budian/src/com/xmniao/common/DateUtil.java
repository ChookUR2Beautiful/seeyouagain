package com.xmniao.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	private static final String TIME_PATTERN = "HH:mm";
	// 一天的毫秒数 60*60*1000*24
	private final static long DAY_MILLIS = 86400000;

	// 一小时的毫秒数 60*60*1000
	private final static long HOUR_MILLIS = 3600000;

	// 一分钟的毫秒数 60*1000
	private final static long MINUTE_MILLIS = 60000;
	
	
	public static final String Y_M_D = "yyyy-MM-dd";

	public static String defaultSimpleFormater = "yyyy-MM-dd HH:mm:ss";
	
	public static String YYMMDD = "yyMMdd";
	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}



	public static String getOrderNo(Date date){
		String str = format(date, YYMMDD);
		
		return str+String.valueOf(Math.random()).substring(2,8);
	}
	
	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 * @see java.text.SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
	    Date date =null;
		SimpleDateFormat df= new SimpleDateFormat(aMask);;
		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			 log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return date;
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 *
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}


	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.warn("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
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
	 * yyyy-mm-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat1(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy/mm/dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat2(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy年mm月dd日 HH时mm分ss秒
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat3(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒")
						.format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy-mm-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY1(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy-MM-dd").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy/mm/dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY2(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy/MM/dd").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy年mm月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY3(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy年MM月dd日").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * mm月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY4(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("MM月dd日").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY5(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * yyyy年MM月
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY6(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("yyyy年MM月").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * mm:dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatY7(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("MM:dd").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatH1(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("HH:mm:ss").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormatH2(Date date) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat("HH:mm").format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		String result = "";
		if (NullU.isNotNull(date)) {
			try {
				result = new SimpleDateFormat(format).format(date);
			} catch (Exception e) {
			}
		}
		return result;
	}

	public static Date parseDate(String str, String simple) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat(simple).parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * yyyy-mm-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDate1(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * yyyy/mm/dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDate2(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * yyyy年mm月dd日 HH时mm分ss秒
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDate3(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * yyyy-mm-dd
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDateY1(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * yyyy/mm/dd
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDateY2(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("yyyy/MM/dd").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * yyyy年mm月dd日
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDateY3(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("yyyy年MM月dd日").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDateH1(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("HH:mm:ss").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * HH:mm
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDateH2(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				date = new SimpleDateFormat("HH:mm").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	public static Date parseBeginDate(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				if (str.indexOf(":") < 0) {
					str += " 00:00:00";
				}
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	public static Date parseEndDate(String str) {
		Date date = null;
		if (NullU.isNotNull(str)) {
			try {
				if (str.indexOf(":") < 0) {
					str += " 23:59:59";
				}
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			} catch (Exception e) {
			}
		}
		return date;
	}

	public static int gadYear(Date startDate, Date endDate) {
		Integer result = 0;
		Calendar startC = Calendar.getInstance();
		Calendar endC = Calendar.getInstance();
		startC.setTime(startDate);
		endC.setTime(endDate);
		result = startC.get(Calendar.YEAR) - endC.get(Calendar.YEAR);
		return result == 0 ? 1 : Math.abs(result);
	}

	public static int gadMonday(Date startDate, Date endDate) {
		Integer result = 0;
		Calendar startC = Calendar.getInstance();
		Calendar endC = Calendar.getInstance();

		startC.setTime(startDate);
		int year = startC.get(Calendar.YEAR);
		int month = startC.get(Calendar.MONTH);

		endC.setTime(endDate);
		int year1 = endC.get(Calendar.YEAR);
		int month1 = endC.get(Calendar.MONTH);

		if (year == year1) {
			result = month1 - month;// 两个日期相差几个月，即月份差
		} else {
			result = 12 * (year1 - year) + month1 - month;// 两个日期相差几个月，即月份差
		}
		return result == 0 ? 1 : Math.abs(result);
	}

	/**
	 * 将时间减去天
	 * 
	 * @author 作者 :刘万里
	 * @version 创建时间：2014年7月28日 下午12:38:31
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date calendarDay(Date date, Integer amount) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		dateC.add(Calendar.DAY_OF_MONTH, amount);
		return dateC.getTime();
	}

	/**
	 * 将时间减去月
	 * 
	 * @author 作者 :刘万里
	 * @version 创建时间：2014年7月28日 下午12:42:34
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date calendarMonth(Date date, Integer amount) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		dateC.add(Calendar.MONTH, amount);
		return dateC.getTime();
	}

	/**
	 * 将时间减去年
	 * 
	 * @author 作者 :刘万里
	 * @version 创建时间：2014年7月28日 下午12:42:48
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date calendarYear(Date date, Integer amount) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		dateC.add(Calendar.YEAR, amount);
		return dateC.getTime();
	}

	/**
	 * 将时间减去时
	 * 
	 * @author 作者 :刘万里
	 * @version 创建时间：2014年7月29日 上午11:26:13
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date calendarHour(Date date, Integer amount) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		dateC.add(Calendar.HOUR, amount);
		return dateC.getTime();
	}

	/**
	 * 将时间减去分
	 * 
	 * @author 作者 :刘万里
	 * @version 创建时间：2014年7月29日 上午11:26:20
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date calendarMinute(Date date, Integer amount) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		dateC.add(Calendar.MINUTE, amount);
		return dateC.getTime();
	}

	/**
	 * 将时间减去秒
	 * 
	 * @author 作者 :刘万里
	 * @version 创建时间：2014年7月29日 上午11:26:27
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date calendarSecond(Date date, Integer amount) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		dateC.add(Calendar.SECOND, amount);
		return dateC.getTime();
	}

	/**
	 * 当前时间加毫秒数 例 ： 20141019180403477
	 * 
	 * @author wushuaihua
	 * @version 创建时间：2014年10月19日 下午6:06:27
	 * @return
	 */
	public static String getCurrentTimeMillise() {
		Calendar cal = new GregorianCalendar();
		// 当前年
		int year = cal.get(Calendar.YEAR);
		// 当前月
		int month = (cal.get(Calendar.MONTH)) + 1;
		// 当前月的第几天：即当前日
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
		// 当前时：HOUR_OF_DAY-24小时制；HOUR-12小时制
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		// 当前分
		int minute = cal.get(Calendar.MINUTE);
		// 当前秒
		int second = cal.get(Calendar.SECOND);
		// 当前毫秒
		int millise = cal.get(Calendar.MILLISECOND);

		StringBuffer sb = new StringBuffer(String.valueOf(year));
		sb.append(fullZero(month)).append(fullZero(day_of_month))
				.append(fullZero(hour)).append(fullZero(minute))
				.append(fullZero(second)).append(fullMillizeZero(millise));
		return sb.toString();
	}

	/**
	 * 月、日、时、分、秒填充
	 * 
	 * @author wushuaihua
	 * @version 创建时间：2014年10月19日 下午6:07:25
	 * @param num
	 * @return
	 */
	private static String fullZero(int num) {
		return num < 10 ? "0" + num : String.valueOf(num);
	}

	/**
	 * 毫秒填充
	 * 
	 * @author wushuaihua
	 * @version 创建时间：2014年10月19日 下午6:09:54
	 * @param num
	 * @return
	 */
	private static String fullMillizeZero(int num) {
		String str = String.valueOf(num);
		if (num < 10)
			str = "00" + str;
		else if (num < 100)
			str = "0" + str;
		return str;
	}

	/**
	 * @throws ParseException 
	 * 
	* @Title: pustTime
	* @Description: 设置推送时间
	* @return Date    返回类型
	* obj 需要推送的时间
	* timeStr 免推送时间段
	* @throws
	 */
	private static Date pustTime(Object obj,String timeStr) throws ParseException{
    	
		//当前日期
    	Date nowdate = null;
    	if(obj instanceof String && StringUtils.isNotBlank(obj+"")){
    		nowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(obj+""); 
    	}else if(obj instanceof Date){
    		nowdate = (Date) obj;
    	}else{
    		nowdate = new Date();
    	}
    	
    	//免打扰延迟时间处理
    	if(StringUtils.isNotBlank(timeStr)&&timeStr.contains("-")){
    		
    		//起始时间
        	String beginNewTime = timeStr.split("-")[0];    	
        	//结束时间
        	String endNewTime = timeStr.split("-")[1];
        	       	
        	//转成时间格式  yyyy-MM-dd   
        	SimpleDateFormat dfsym = new SimpleDateFormat("yyyy-MM-dd");
        	//转成时间格式  yyyy-MM-dd HH:mm      	
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");           
            //当前时间 字符串
            String nowDateStr = dfs.format(nowdate);
            
            //当前时间 date
            java.util.Date nowDate=dfs.parse(nowDateStr);
            //免打扰开始时间 date
            java.util.Date beginTime=dfs.parse(dfsym.format(nowdate)+" "+beginNewTime);
            //免打扰结束时间 date
            java.util.Date endTime = dfs.parse(dfsym.format(nowdate)+" "+endNewTime); 
            
            //推送时间是否要延迟判断处理
            if(!beginTime.equals(endTime)){          
	            //开始时间小于结束时间
	            if(beginTime.before(endTime)){
	            	//延迟推送时间设置
	            	if(nowDate.after(beginTime)&&nowDate.before(endTime)){
	            		//发推送消息时间 结束时间+1分钟
	                	endTime.setMinutes(endTime.getMinutes()+1);
	                	nowdate = endTime;               	
	            	}           	
	            }else{
	            	//开始时间大于结束时间
	            	if(!(nowDate.after(endTime)&&nowDate.before(beginTime))){
	            		//延迟推送时间设置
	            		//发推送时间 开始时间+1分钟
	                	endTime.setMinutes(endTime.getMinutes()+1);
	                	nowdate = endTime;
	            	}    	
	            }  
            }
    	}
    	return nowdate;
	}
	
	/**
	 * string形式返回当前时间
	 */
	public static String  getCurrentTimeStr(){
		return format(new Date(),defaultSimpleFormater);
	}
	
	/**
	 * 判断当前时间与传入时间是否一致
	 * false:不是同一天
	 * true:同一天
	 * 入参格式:yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
	 */
	public static boolean  isCurrentTime(String date){
		return date!=null?date.contains(format(new Date(),"yyyy-MM-dd")):false;
	}
	
	/**
	 * 
	* @Title: compare_date
	* @Description:比较时间大小 
	* @return int
	 */
	public static int compare_date(Date date1, Date Date2){
	    try {
	        if (date1.getTime() > Date2.getTime()) {
//	            System.out.println("dt1 在dt2前");
	            return 1;
	        } else if (date1.getTime() < Date2.getTime()) {
//	            System.out.println("dt1在dt2后");
	            return -1;
	        } else {
	            return 0;
	        }
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	    return 0;
	}
	
	/**
	 * 
	 * 方法描述：当前时间加上天数，获取指定日期 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-29上午10:42:11 <br/>
	 * @param days
	 * @return
	 */
	public static String getSpecifiedDate(int days){
		String specifiedDate="";
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DATE,days);
		specifiedDate = new SimpleDateFormat(defaultSimpleFormater).format(instance.getTime());
		return specifiedDate;
	}
		
	public static void main(String[] args) throws ParseException {

		/*//结束时间大于起始时间
    	String nonewtiem2 = "09:45-12:30";
    	//结束时间小于开始时间
    	String nonewtiem1 = "22:45-22:45";
    	
		System.out.println(DateUtil.pustTime(new Date(),nonewtiem1));*/
//		System.out.println(format(new Date(),"yyyy-MM-dd"));
//		System.out.println(isCurrentTime("444"));
//        Date zdate=DateUtil.convertStringToDate("yyyy-MM-dd", "2015-08-14 12:15:19");
//        String tempdate=DateUtil.dateFormatY1(zdate);
//        System.out.println(DateUtil.isCurrentTime(tempdate));
//        
//        Date cdate= DateUtil.calendarDay(new Date(), -1);
//        String tempdate1=DateUtil.dateFormatY1(cdate);
//        System.out.println(tempdate1);
        
        System.out.println(getSpecifiedDate(0));
	}



	public static Date getLastMonFirst() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(c.getTime());
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}



	public static Date getThisMonFirst() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(c.getTime());
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
