package com.xmniao.common;

import java.math.BigDecimal;

public class NullU {
	public static boolean isNull(String o) {
        if (o == null || o.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(String o) {
        if (o == null || o.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 保证调用端代码风格统一，添加isNotNull之overload方法
     * 
     * @param o
     * @return
     */
    public static boolean isNotNull(Object o) {
        return o != null;
    }
    public static Boolean nvl(Boolean oldValue) {
        return oldValue == null ? false : oldValue;
    }

    public static Short nvl(Short oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Integer nvl(Integer oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Long nvl(Long oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Float nvl(Float oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Double nvl(Double oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    private static BigDecimal BD_ZERO = new BigDecimal("0");

    public static BigDecimal nvl(BigDecimal oldValue) {
        return oldValue == null ? BD_ZERO : oldValue;
    }

    public static String nvl(String oldValue) {
        return oldValue == null ? "" : oldValue;
    }

    public static <T> T nvl(T oldValue, T defaultValue) {
        return oldValue == null ? defaultValue : oldValue;
    }
    public static String getErrandCode(String code) {
        if (code == null && "".equals(code)) {
            return "0";
        } else {
            return code;
        }

    }
    public static String getErrandLimitCode(String code) {
        if (code == null && "".equals(code)) {
            return " 未设置 ";
        } else {
            return code;
        }

    }
    
    public static Object nullPro(Object obj){
		return obj == null ? "" : obj;
	}
}
