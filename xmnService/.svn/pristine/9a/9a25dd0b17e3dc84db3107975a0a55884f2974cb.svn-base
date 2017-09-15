package com.xmniao.xmn.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：StringUtil
 * 
 * 类描述： 字符串工具类
 * 
 * 创建时间：2014年8月27日 上午11:54:33
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class StringUtils {

	private static final String CONTRACT = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private StringUtils() {

	}


	/**
	 * 
	 * getRandomString(生成指定长度的随机字符)
	 * 
	 * @param length
	 *            生成随机字符的长度
	 * @return
	 * 
	 *         author：zhou'sheng
	 */
	public String getRandomString(Integer length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(CONTRACT.charAt(random.nextInt(CONTRACT.length())));
			System.out.println(sb.toString());
		}
		return sb.toString();
	}
	/**
	 * 
	 * getRaString(生成一个长度为20的随机字符)
	 * 
	 * @return
	 * 
	 *         author：zhou'sheng
	 */
	public String getRandomString() {
		return getRandomString(20);
	}
	
	/**
	 * 
	 * getUUIDString(生成UUID)  
	 *  
	 * @return 
	 *  
	 * author：zhou'sheng
	 */
	public synchronized static String getUUIDString() {
		String id = UUID.randomUUID().toString();
		id = id.replace("-", "");
		return id;
	}
	
	/**
	 * 
	 * getIpAddr(获取IP)  
	 *  
	 * @param request
	 * @return 
	 *  
	 * author：zhou'sheng
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!org.apache.commons.lang.StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!org.apache.commons.lang.StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	
	/**
	 * 创建list
	 * @param parameter
	 * @param Collection
	 */
	public static void paresToList(String parameter,Collection<String> container,String delim){
		if(null==parameter){
			return;
		}
		 StringTokenizer st = new StringTokenizer(parameter, delim);
		 while (st.hasMoreTokens()) {
	         String token = st.nextToken().trim();
	         if (token.length() > 0) {
	        	 container.add(token);
	         }
	     }
	}
	
	/**
	 * 指定字符 分割   返回数组
	 * @param parameter
	 * @param Collection
	 */
	public static String[] paresToArray(String parameter,String delim){
		if(!hasLength(parameter)){
			return null;
		}
		List<String> container = new ArrayList<String>();
		paresToList(parameter,container,delim);
		return (container.toArray(new String[container.size()]));
	}
	
	
	/**
	 * 指定字符 分割并去掉重复的数据   返回数组
	 * @param parameter
	 * @param Collection
	 */
	public static String[] paresDuplicateRemovalToArray(String parameter,String delim){
		if(!hasLength(parameter)){
			return null;
		}
		Set<String> container = new HashSet<String>();
		paresToList(parameter,container,delim);
		return (container.toArray(new String[container.size()]));
	}
	
	/**
	 * 判断字符是否为空<br>
	 * StringUtils.hasLength(null) = false<br>
	 * StringUtils.hasLength("") = false<br>
	 * StringUtils.hasLength(" ") = true<br>
	 * StringUtils.hasLength("Hello") = true<br>
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence)str);
	}
	
	
	/**
	 * 判断字符数组是否为空<br>
	 * StringUtils.hasLength(null) = false<br>
	 * StringUtils.hasLength({}) = false<br>
	 * StringUtils.hasLength({"Hello"}) = true 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String[] str){
		return hasLength((CharSequence[])str);
	}
	
	/**
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength({}) = false
	 * StringUtils.hasLength({"Hello"}) = true 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence[] str) {
		return (str != null && str.length > 0);
	}
	
	/**
	 * 合并数组
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}
	
	/**
	 * 转换集合成数组
	 * @param collection
	 * @return
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}
	
	/**
	 * 数组转成字符串
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 指定数组 中是否有元素是以 指定 字符串 开始
	 * @param sArray
	 * @param value
	 * @return
	 */
	public static boolean startsWithStringArray(String[] sArray, String value) {
		for (int i = 0; i < sArray.length; i++) {
			String v = sArray[i];
			if(value.startsWith(v)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkPackge(int basePakgeStart, String basePakge,int pakgeStart, String pakges) {
		if (basePakge.length() <= pakges.length()) {
			int start = basePakge.indexOf("*", basePakgeStart);
			boolean b = start == - 1;
			String context = basePakge.substring(basePakgeStart,!b?start:basePakge.length());
			if(!b){
				if (pakges.startsWith(context, pakgeStart)) {
					int len = context.length();
					basePakgeStart = basePakgeStart + len + 1;
					pakgeStart = pakges.indexOf(".",pakgeStart + len);
					return checkPackge(basePakgeStart, basePakge, pakgeStart,pakges);
				}
			}else{
				return pakges.startsWith(context, pakgeStart);
			}
		}
		return false;
	}
	
	
	/**
	 * 字符串 编码成 Unicode
	 * @param string
	 * @return
	 */
	public static String encodeUnicode(final String string) {
		char[] utfBytes = string.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			sb.append("\\u" + hexB);
		}
		return sb.toString();
	}
	
	/**
	 * 解码 Unicode成string
	 * @param string
	 * @return
	 */
	public static String decodeUnicode(String string) {
		int n = string.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = string.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}
	
	/**
	 * 日志描述
	 * @param request
	 * @param strings
	 */
	public static String requestActiongLogging(String[] strings,Integer state)throws Exception{
			StringBuilder sb = new StringBuilder();
			boolean isSuccess = state==null|| state==1;
			String r = isSuccess ?"成功":"失败"; 
			int len = isSuccess ? strings.length : strings.length-1;
			if(len==3){
				sb.append(String.format("新增%s【%s】的记录，结果是【%s %s】",strings[0],strings[1],strings[2],r));
			}else if(len==4){
				sb.append(String.format("对  %s【%s】的记录，进行【%s】，结果是【%s %s】",strings[0],strings[1],strings[2],strings[3],r));
			}
			
			
			return sb.toString();
			
	}
	
	public static String[] addStrToStrArray(String[] array,String str){
		String[] newArray = new String[array.length+1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[array.length] = str;
		return newArray;
	}
	/**
	 * 字符串转集合
	 * @param parameter 字符串
	 * @param delim 分隔符号
	 * @return (默认返回set 集合)
	 */
	public static Collection<String> strToCollection(String parameter,String delim){
		Set<String> collection = new HashSet<>();
		if(hasLength(parameter)){
			String[] provines = paresToArray(parameter, delim);
			for(String p : provines){
				collection.add(p);
			}
		}
		return collection;
	}
	
	
	/**
	 * 描述 ： 返回用户名  如果用户名过长  返回截取的字符串  默认12位英文  6个汉字
	 * @param String content
	 * @return String
	 * */
	public static String getUserNameStr(String content){
		try {
			if (org.apache.commons.lang.StringUtils.isEmpty(content)) {
				return "";
			}
		   StringBuffer buffer = new StringBuffer();
		   int sublength = 12;
           int length = content.getBytes("GBK").length;
           if(sublength>=length){
        	  return content;
           }else {
              int i = 0;
              int j = 0;
              for(;i<length;i++){
                  if(content.substring(0,i+1).getBytes("GBK").length>=sublength) {
                      j = content.substring(0,i+1).getBytes("GBK").length;
                      break;
                  }
              }
              String substring = j>sublength?content.substring(0,i):content.substring(0, i+1);
              return buffer.append(substring).append("..").toString();
           }
		} catch (Exception e) {
			e.printStackTrace();
			return content;
		}
		
	}
	
	/**
	 * 去掉一个字段串中的空格键
	 * @param key
	 * @return
	 */
	public static String cutSpaceKey(String key){
		
		StringBuilder bud = new StringBuilder();
		
		for(int i=0;i<key.length();i++){
			char c = key.charAt(i);
			
			boolean flag = c != ' ';
			
			if(flag){
				bud.append(c);
			}
		}
		return bud.toString();
	}
	
	/**
	 * 生成模糊查询词
	 * @param key
	 * @return
	 */
	public static String generateSearchKey(String key){
		
		StringBuilder bud = new StringBuilder("%");
		
		for(int i=0; i<key.length(); i++){
			
			char c = key.charAt(i);
			
			if(c != ' '){
				bud.append(c);
				bud.append("%");
			}
		}
		
		return bud.toString();
	}
}
