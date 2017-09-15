package com.xmniao.xmn.core.util;

import java.util.UUID;

/**
 * 框架UTIL
 * @author guwen
 *
 */
public class FrameUtil {
	
	/**
	 * 32位UUID
	 * @return
	 */
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return (s.replaceAll("-", "")+"0000000000000000000000000000000000").substring(0,32); 
    } 
	
	
	public static void main(String[]arg){
		for(int i=0;i<10;i++){
		 String s = UUID.randomUUID().toString(); 
		 System.out.println(s.replaceAll("-", ""));
		// FrameUtil.getUUID()
		 
		}
	}
}
