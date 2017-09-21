package com.xmniao.service.manor;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by yang.qiang on 2017/6/20.
 */
public class ManorDateUtils {

    /**
     * 返回 日期+指定时间
     *
     * @param calendarField
     * @param amount
     * @return
     */
    public static Date getDateDistance(Date date, Integer calendarField, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarField, amount);
        return calendar.getTime();
    }

    public static Date getFlowerPerishDate(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.add(Calendar.DAY_OF_MONTH,days);
        return calendar.getTime();
    }
}
