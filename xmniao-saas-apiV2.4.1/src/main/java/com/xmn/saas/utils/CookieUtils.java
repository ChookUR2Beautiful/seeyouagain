package com.xmn.saas.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static void setVal(String key ,String val,HttpServletResponse response){
		Cookie cookie = new Cookie(key,val);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static void delVal(String key ,HttpServletResponse response){
		Cookie cookie = new Cookie(key,null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String getVal(String key ,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = 0;i < cookies.length; i++){
				Cookie cookie = cookies[i];
				if(cookie.getName().equals(key)){
					return cookie.getValue();
				}
			}
		}else{
			return null;
		}
		
		return null;
	}
	
}
