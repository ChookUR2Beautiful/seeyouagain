package com.sms.common;

import java.security.MessageDigest;
/**
 * MD5转换类
 * @author xuqingxiong
 *
 */
public class Md5Convert {
	
	/**
	 * MD5加密方法
	 * @param str传入需要转的字符串
	 * @return返回加密串
	 */
	public static String md5(String str)
	{
	   try
	   {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes());
	    byte[] b = md.digest();					//使用JDK的MD5转换成byte字符
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < b.length; i++)		//循环MD5密文，转换成16进制表示
	    {
	     int v = (int) b[i];
	     v = v < 0 ? 0x100 + v : v;
	     String cc = Integer.toHexString(v);	//byte转16进制
	     if (cc.length() == 1)
	      sb.append('0');						//长度1位就补0
	     sb.append(cc);
	    }
	    return sb.toString();			//返回字符串
	   } catch (Exception e)
	   {
		   e.printStackTrace();
	   }
	   return "";
	}
	
	public static void main(String args[]){
		
		System.out.println(Md5Convert.md5("123456"));
		
		System.out.println(Md5Convert.md5("DXX-WSS-100-06298318099").toUpperCase());
		
	}
}
