package com.xmn.saas.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DurationFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 * 
 * @ClassName: CalendarUtil
 * @Description: TODO
 * @author dengqiang
 * @date 2016年9月28日 下午8:54:57
 *
 */
@SuppressWarnings( "all" )
public class CalendarUtil {

    public static final String FORMAT0 = "yyyy-MM-dd";
    public static final String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT2 = "yyyy.MM.dd";

    public static SimpleDateFormat sdf = new SimpleDateFormat(FORMAT0);

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT1);


    public static void main(String[] args) throws ParseException {
        DateFormat fmt =new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = fmt.parse("20161130212459");
        System.out.println();
    }


    /**
     * 
     * @Title: distanceDay
     * @Description: TODO
     * @param @param dateParam
     * @param @param days
     * @param @throws Exception 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String distanceDay(Date dateParam, Integer days) throws Exception {
        Calendar calendar = Calendar.getInstance(); // 得到日历
        calendar.setTime(dateParam);// 把时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        Date before = calendar.getTime();
        return sdf.format(before);
    }


    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws Exception
     */
    public static Integer daysBetween(Date smdate, Date bdate) throws Exception {
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算dateParam距离当前日期多少天数
     * 
     * @Title: distanceDay
     * @Description: TODO
     * @param @param date
     * @param @return 设定文件
     * @return Integer 返回类型
     * @throws
     */
    public static Integer distanceDay(String dateParam) throws Exception {
        if (StringUtils.isBlank(dateParam)) {
            throw new Exception("dateParam is null");
        }
        Date date = sdf.parse(dateParam.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Integer result = (int) ((today.getTimeInMillis() - calendar.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        if (today.getTime().compareTo(date) == -1) {
            if (result < 0) {
                String rs = String.valueOf(result);
                if (rs.indexOf("-") != -1) {
                    result = Integer.valueOf(rs.substring(rs.indexOf("-") + 1));
                }
            }
        }
        return result;
    }

    /**
     * 计算dateParam距离当前日期多少天数
     * 
     * @Title: distanceDay
     * @Description: TODO
     * @param @param date
     * @param @return 设定文件
     * @return Integer 返回类型
     * @throws
     */
    public static Integer distanceDay(Date dateParam) throws Exception {
        if (dateParam == null) {
            throw new Exception("dateParam is null");
        }
        String stringDate = CalendarUtil.dateFormat(dateParam, FORMAT0);
        Date date = sdf.parse(stringDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar today = Calendar.getInstance();
        Integer result =
                (int) ((today.getTimeInMillis() - calendar.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        if (today.getTime().compareTo(date) == -1) {
            if (result < 0) {
                String rs = String.valueOf(result);
                if (rs.indexOf("-") != -1) {
                    result = Integer.valueOf(rs.substring(rs.indexOf("-") + 1));
                }
            }
            result = result + 1;
        }
        return result;
    }

    /**
     * 
     * @Title: formatDate
     * @Description: TODO
     * @param @param date
     * @param @return
     * @param @throws Exception 设定文件
     * @return Date 返回类型
     * @throws
     */
    public static Date formatDate(String date) throws Exception {
        if (StringUtils.isBlank(date)) {
            throw new Exception("date is null");
        }

        if (date.indexOf("年") != -1) {
            date = date.replace("年", "-");
        }

        if (date.indexOf("月") != -1) {
            date = date.replace("月", "-");
        }

        if (date.indexOf("日") != -1) {
            date = date.replace("日", "-");
        }
        return sdf.parse(date);
    }
    /**
     * 
     * @Title: DateFormat
     * @Description: TODO
     * @param @param date
     * @param @param dateFormat
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static Date dateFormat(Date date) throws Exception {
        if (date == null) {
            return null;
        } else {
            return sdf.parse(sdf.format(date));
        }
    }

    /**
     * 
     * @Title: DateFormat
     * @Description: 精确到时分秒
     * @param @param date
     * @param @param dateFormat
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String dateToString(Date date){
        if (date == null) {
            return null;
        } else {
            return simpleDateFormat.format(date);
        }
    }
    /**
     * 
     * @Title: DateFormat
     * @Description: TODO
     * @param @param date
     * @param @param dateFormat
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static Date dateFormat(String date) throws Exception {
        if (date == null) {
            return null;
        } else {
            return simpleDateFormat.parse(date);
        }
    }

    /**
     * 
     * @Title: DateFormat
     * @Description: TODO
     * @param @param date
     * @param @param dateFormat
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String stringFormat(Date date) throws Exception {
        if (date == null) {
            return null;
        } else {
            return simpleDateFormat.format(date);
        }
    }
    
    public static Date stringToDate(String date) throws ParseException{
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        
        Date d = fmt.parse(date);
        return d;
    }
    /**
     * 
     * @Title: DateFormat
     * @Description: TODO
     * @param @param date
     * @param @param dateFormat
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String dateFormat(Date date, String dateFormat) throws Exception {
        String datetamp = null;
        if (date != null && StringUtils.isNotBlank(dateFormat)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            datetamp = simpleDateFormat.format(date);
        }
        return datetamp;
    }

    /**
     * 
     * @Title: getCurrentDate
     * @Description: TODO
     * @param @param dateFormat
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getCurrentDate(String dateFormat) throws Exception {
        String datetamp = null;
        Calendar calendar = Calendar.getInstance();
        if (StringUtils.isNotBlank(dateFormat)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            datetamp = simpleDateFormat.format(calendar.getTime());
        }
        return datetamp;
    }

    /**
     * 根据日期获取星期
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static String getWeek(Date date) {
        String[] weeks = { "星期日" , "星期一" , "星期二" , "星期三" , "星期四" , "星期五" , "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param sFormat
     * @return
     */
    public static String getDateString(Date date, String sFormat) {
        SimpleDateFormat format = new SimpleDateFormat(sFormat);
        if (date != null) {
            return format.format(date);
        }
        return "";
    }


    /**
     * 根据日期计算所在周的周一和周日
     * 
     * @param time 指定的日期
     */
    public static Map<Object,Object> convertWeekByDate(Date time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptimeBegin = sdf.format(cal.getTime());
        System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        System.out.println("所在周星期日的日期：" + imptimeEnd);
        Map<Object,Object> map = new HashMap<>();
        map.put("imptimeBegin", imptimeBegin);
        map.put("imptimeEnd", imptimeEnd);
        return map;
        
    }
    
    /**
	 * 
	 * 方法描述：判断选择的日期是否是本周  
	 * 创建人：jianming  
	 * 创建时间：2016年10月17日 上午11:05:50   
	 * @param time
	 * @return
	 */
    public static boolean isThisWeek(long time)  
    {  
        Calendar calendar = Calendar.getInstance();  
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);  
        calendar.setTime(new Date(time));  
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);  
        if(paramWeek==currentWeek){  
           return true;  
        }  
        return false;  
    }  
    /**
     * 
     * 方法描述：判断选择的日期是否是今天  
     * 创建人：jianming  
     * 创建时间：2016年10月17日 上午11:05:58   
     * @param time
     * @return
     */
    public static boolean isToday(long time)  
    {  
       return isThisTime(time,"yyyy-MM-dd");  
    }  
    /**
     * 
     * 方法描述：判断选择的日期是否是本月  
     * 创建人：jianming  
     * 创建时间：2016年10月17日 上午11:06:15   
     * @param time
     * @return
     */
    public static boolean isThisMonth(long time)  
    {  
         return isThisTime(time,"yyyy-MM");  
    }  
    private static boolean isThisTime(long time,String pattern) {  
        Date date = new Date(time);  
         SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
         String param = sdf.format(date);//参数时间  
         String now = sdf.format(new Date());//当前时间  
         if(param.equals(now)){  
           return true;  
         }  
         return false;  
    }


    /**
     * 获取开始时间 距离今天的天数
     * @param startDate
     * @return
     */
    public static int startDay(Date startDate){
        String startDay = DurationFormatUtils.formatPeriod(startDate.getTime(), new Date().getTime(),"d");
        return Integer.valueOf(startDay);
    }

    /**
     * 今天距离结束日期的天数(算上结束日期当天)
     * @param endDate
     * @return
     */
    public static int endDay(Date endDate){

        if(new Date().getTime() > endDate.getTime()){
            return 1;
        }

        String endDay = DurationFormatUtils.formatPeriod(new Date().getTime(),endDate.getTime(),"d");
//        String endDay = DurationFormatUtils.formatPeriod(new Date().getTime() , new Date().getTime(),"d");
        // 算上结束日期当天
        return (Integer.valueOf(endDay))+1;
    }
}
