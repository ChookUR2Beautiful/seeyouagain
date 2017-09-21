package com.xmniao.common;

import java.util.regex.Pattern;

public class MathUtil {

    
    /**
     * 是否为数字
     */
    public static boolean isNumeric(String str){  
        Pattern pattern = Pattern.compile("[0-9]*");  
        return pattern.matcher(str).matches();     
    }  
    
    
    public static void main(String[] args) {
		System.out.println(MathUtil.isNumeric("123456"));
		System.out.println(MathUtil.isNumeric("1232sasd56"));
		System.out.println(MathUtil.isNumeric("12345643A#4"));
		System.out.println(MathUtil.isNumeric("123456{}asd3"));
		
	}
	
}
