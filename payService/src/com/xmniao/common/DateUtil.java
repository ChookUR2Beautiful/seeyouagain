package com.xmniao.common;

import com.xmniao.exception.CustomException;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称：TravelingWeb
 *
 * 类名称：DateUtil
 *
 * 类描述： 日期转换工具类
 *
 * 创建人： zhou'sheng
 *
 * 创建时间：2014年8月25日 下午4:23:32
 *
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class DateUtil {

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD = "yyyyMMdd";
    public static final String YMDHM = "yyyyMMddHHmm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String ymd = "yyyy/MM/dd";
    public static final String ymd_HM = "yyyy/MM/dd HH:mm";
    public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";
    public static final String china_ymd_HM = "yyyy年MM月dd日 HH点mm分ss秒";


    public static final int YESTERDAY = 1;//昨天
    public static final int LAST_WEEK = 2;//最近一周
    public static final int LAST_MONTY = 3;//最近一个月
    public static final int TOMORROW = -1; // 明天

    /**
     * 智能转换日期
     */
    public static String smartFormat(Date date) {
        String dateStr = null;
        if (date == null) {
            dateStr = "";
        } else {
            try {
                dateStr = formatDate(date, Y_M_D_HMS);
                // 时分秒
                if (dateStr.endsWith(" 00:00:00")) {
                    dateStr = dateStr.substring(0, 10);
                }
                // 时分
                else if (dateStr.endsWith("00:00")) {
                    dateStr = dateStr.substring(0, 16);
                }
                // 秒
                else if (dateStr.endsWith(":00")) {
                    dateStr = dateStr.substring(0, 16);
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
            }
        }
        return dateStr;
    }


    /**
     * 转换中国年日期
     */
    public static String chinaSmartFormat(Date date) {
        String dateStr = null;
        if (date == null) {
            dateStr = "";
        } else {
            try {
                dateStr = formatDate(date, china_ymd_HM);
                // 时分
                if (dateStr.endsWith("00点00分00秒")) {
                    dateStr = dateStr.substring(0, dateStr.indexOf(" "));
                }// 分
                else if (dateStr.endsWith("00分00秒")) {
                    dateStr = dateStr.substring(0, dateStr.indexOf("点") + 1);
                }
                // 秒
                else if (dateStr.endsWith("00秒")) {
                    dateStr = dateStr.substring(0, dateStr.indexOf("分") + 1);
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
            }
        }
        return dateStr;
    }

    /**
     * 智能转换日期
     */
    public static Date smartFormat(String text) {
        Date date = null;
        try {
            if (text == null || text.length() == 0) {
                date = null;
            } else if (text.length() == 10) {
                date = formatStringToDate(text, Y_M_D);
            } else if (text.length() == 13) {
                date = new Date(Long.parseLong(text));
            } else if (text.length() == 16) {
                date = formatStringToDate(text, Y_M_D_HM);
            } else if (text.length() == 19) {
                date = formatStringToDate(text, Y_M_D_HMS);
            } else {
                throw new IllegalArgumentException("日期长度不符合要求!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("日期转换失败!");
        }
        return date;
    }

    /**
     * 获取当前日期
     */
    public static String getNow(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * 格式化日期格式
     *
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date argDate, String argFormat) {
        if (StringUtils.isEmpty(argFormat)) {
            argFormat = Y_M_D;
        }
        SimpleDateFormat sdfFrom = new SimpleDateFormat(argFormat);
        return sdfFrom.format(argDate).toString();
    }

    /**
     * 格式化日期格式
     *
     * @return 格式化后的日期字符串
     */
    public static String formatDate(String argFormat, Date argDate) {
        try {
            if (argDate == null) {
                return null;
            }
            if (StringUtils.isEmpty(argFormat)) {
                argFormat = Y_M_D;
            }
            SimpleDateFormat sdfFrom = new SimpleDateFormat(argFormat);
            return sdfFrom.format(argDate).toString();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 把字符串格式化成日期
     */
    public static Date formatStringToDate(String argDateStr, String argFormat) throws Exception {
        if (argDateStr == null || argDateStr.trim().length() < 1) {
            throw new Exception("参数[日期]不能为空!");
        }
        String strFormat = argFormat;
        if (StringUtils.isEmpty(strFormat)) {
            strFormat = Y_M_D;
            if (argDateStr.length() > 16) {
                strFormat = Y_M_D_HMS;
            } else if (argDateStr.length() > 10) {
                strFormat = Y_M_D_HM;
            }
        }
        SimpleDateFormat sdfFormat = new SimpleDateFormat(strFormat);
        // 严格模式
        sdfFormat.setLenient(false);
        try {
            return sdfFormat.parse(argDateStr);
        } catch (ParseException e) {
            throw new Exception(e);
        }
    }

    /**
     * 方法描述：获取指定起始日期 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-29上午10:42:11 <br/>
     */
    public static String getSpecifiedDate(int DateType) {
        String specifiedDate = "";
        Calendar instance = Calendar.getInstance();

        switch (DateType) {
            case DateUtil.YESTERDAY:
                instance.add(Calendar.DATE, -1);
                break;
            case DateUtil.LAST_WEEK:
                instance.add(Calendar.DATE, -6);
                break;
            case DateUtil.LAST_MONTY:
                instance.add(Calendar.DATE, -30);
                break;
            case DateUtil.TOMORROW:
                instance.add(Calendar.DATE, 1);
                break;
            default:
                instance.add(Calendar.DATE, -1);
                break;
        }

        specifiedDate = new SimpleDateFormat("yyyy-MM-dd ").format(instance.getTime());
        return specifiedDate;
    }

    /**
     * 方法描述：获取天数之间的日期
     * 创建人： jianming <br/>
     * 创建时间：2017年2月15日下午3:40:12 <br/>
     */
    public static Date getNearbyDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }

    public static String getHoursFromSecond(Long second) {
        String hoursFormat = "";
        BigDecimal zhiboDurationSecond = new BigDecimal(second == null ? 0d : second.doubleValue());
        long hours = 0;
        Integer minute = 0;
        hours = zhiboDurationSecond.divideToIntegralValue(new BigDecimal(3600)).longValue();
        minute = zhiboDurationSecond.subtract(new BigDecimal(hours * 3600)).divideToIntegralValue(new BigDecimal(60)).intValue();
        hoursFormat = (hours == 0 ? "" : hours + "小时") + minute + "分钟";
        return hoursFormat;
    }

    /**
     * 方法描述：判断是否同一天
     * 创建人： jianming <br/>
     * 创建时间：2017年2月15日下午3:25:09 <br/>
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }


    /**
     * 方法描述：获取上周开始时间   上周周一开始,非7天前
     * 创建人：jianming
     * 创建时间：2017年5月11日 上午11:45:34
     */
    public static Date getLastWeekSdate() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, -1 * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(cal.getTime());
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 获取增加小时候后的时间
     */
    public static String getAddHoursafterDate(String date, int hours, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        try {
            Date d = new Date(f.parse(date).getTime() + hours * 3600 * 1000);
            return f.format(d);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("时间转换异常");
        }

    }

    /**
     * 判断2个日期的大小
     */
    public static int compare_date(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
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
     * 获取一月中的头一天
     */
    public static String firstDayByMonth() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DATE, now.getActualMinimum(Calendar.DATE));
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        System.out.println(now.getTime());

        SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");
        return sdfFrom.format(now.getTime());
    }

    /**
     * 获取一月中的最后一天
     */
    public static String lastDayByMonth() {
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");
        return sdfFrom.format(cale.getTime());
    }

    /**
     * 减去小时数
     */
    public static String miunsHours(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }


}
