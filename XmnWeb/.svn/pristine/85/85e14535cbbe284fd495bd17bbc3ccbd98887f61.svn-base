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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：StringUtil
 * 
 * 类描述： 字符串工具类
 * 
 * 创建人： zhou'sheng
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
	 * 		author：zhou'sheng
	 */
	public String getRandomString(Integer length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(CONTRACT.charAt(random.nextInt(CONTRACT.length())));
		}
		return sb.toString();
	}

	/**
	 * 
	 * getRaString(生成一个长度为20的随机字符)
	 * 
	 * @return
	 * 
	 * 		author：zhou'sheng
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
	 * 		author：zhou'sheng
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
	 * 		author：zhou'sheng
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
	 * 
	 * @param parameter
	 * @param Collection
	 */
	public static void paresToList(String parameter, Collection<String> container, String delim) {
		if (null == parameter) {
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
	 * 指定字符 分割 返回数组
	 * 
	 * @param parameter
	 * @param Collection
	 */
	public static String[] paresToArray(String parameter, String delim) {
		if (!hasLength(parameter)) {
			return null;
		}
		List<String> container = new ArrayList<String>();
		paresToList(parameter, container, delim);
		return (container.toArray(new String[container.size()]));
	}

	/**
	 * 指定字符 分割并去掉重复的数据 返回数组
	 * 
	 * @param parameter
	 * @param Collection
	 */
	public static String[] paresDuplicateRemovalToArray(String parameter, String delim) {
		if (!hasLength(parameter)) {
			return null;
		}
		Set<String> container = new HashSet<String>();
		paresToList(parameter, container, delim);
		return (container.toArray(new String[container.size()]));
	}

	/**
	 * 判断字符是否为空<br>
	 * StringUtils.hasLength(null) = false<br>
	 * StringUtils.hasLength("") = false<br>
	 * StringUtils.hasLength(" ") = true<br>
	 * StringUtils.hasLength("Hello") = true<br>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	/**
	 * 判断字符数组是否为空<br>
	 * StringUtils.hasLength(null) = false<br>
	 * StringUtils.hasLength({}) = false<br>
	 * StringUtils.hasLength({"Hello"}) = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String[] str) {
		return hasLength((CharSequence[]) str);
	}

	/**
	 * StringUtils.hasLength(null) = false StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true StringUtils.hasLength("Hello") = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * StringUtils.hasLength(null) = false StringUtils.hasLength({}) = false
	 * StringUtils.hasLength({"Hello"}) = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence[] str) {
		return (str != null && str.length > 0);
	}

	/**
	 * 合并数组
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @param sArray
	 * @param value
	 * @return
	 */
	public static boolean startsWithStringArray(String[] sArray, String value) {
		for (int i = 0; i < sArray.length; i++) {
			String v = sArray[i];
			if (value.startsWith(v)) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkPackge(int basePakgeStart, String basePakge, int pakgeStart, String pakges) {
		if (basePakge.length() <= pakges.length()) {
			int start = basePakge.indexOf("*", basePakgeStart);
			boolean b = start == -1;
			String context = basePakge.substring(basePakgeStart, !b ? start : basePakge.length());
			if (!b) {
				if (pakges.startsWith(context, pakgeStart)) {
					int len = context.length();
					basePakgeStart = basePakgeStart + len + 1;
					pakgeStart = pakges.indexOf(".", pakgeStart + len);
					return checkPackge(basePakgeStart, basePakge, pakgeStart, pakges);
				}
			} else {
				return pakges.startsWith(context, pakgeStart);
			}
		}
		return false;
	}

	/**
	 * 字符串 编码成 Unicode
	 * 
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
	 * 
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
	 * 
	 * @param request
	 * @param strings
	 */
	public static String requestActiongLogging(String[] strings, Integer state) throws Exception {
		StringBuilder sb = new StringBuilder();
		boolean isSuccess = state == null || state == 1;
		String r = isSuccess ? "成功" : "失败";
		int len = isSuccess ? strings.length : strings.length - 1;
		if (len == 3) {
			sb.append(String.format("新增%s【%s】的记录，结果是【%s %s】", strings[0], strings[1], strings[2], r));
			/*
			 * sb.append("新增 "); sb.append(strings[0]); sb.append("【");
			 * sb.append(strings[1]); sb.append("】的记录，结果是【");
			 * sb.append(strings[2]); sb.append(r); sb.append("】");
			 */
		} else if (len == 4) {
			sb.append(
					String.format("对  %s【%s】的记录，进行【%s】，结果是【%s %s】", strings[0], strings[1], strings[2], strings[3], r));
			/*
			 * sb.append("对"); sb.append(strings[0]); sb.append("【");
			 * sb.append(strings[1]); sb.append("】的记录，进行【");
			 * sb.append(strings[2]); sb.append("】，结果是【");
			 * sb.append(strings[3]); sb.append(r); sb.append("】");
			 */
		}

		return sb.toString();

	}

	public static String[] addStrToStrArray(String[] array, String str) {
		String[] newArray = new String[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[array.length] = str;
		return newArray;
	}

	/**
	 * 字符串转集合
	 * 
	 * @param parameter
	 *            字符串
	 * @param delim
	 *            分隔符号
	 * @return (默认返回set 集合)
	 */
	public static Collection<String> strToCollection(String parameter, String delim) {
		Set<String> collection = new HashSet<>();
		if (hasLength(parameter)) {
			String[] provines = paresToArray(parameter, delim);
			for (String p : provines) {
				collection.add(p);
			}
		}
		return collection;
	}
	
	/**
	 * 生成订单格式 : 160501 100655 1254。格式6位年月日+小时 分 秒+4位随机数
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-4上午11:30:59 <br/>
	 * @return
	 */
	public static String generateOrderNo(){
		String dateStr = DateUtil.getNow(DateUtil.Y_M_D_HMS);
    	String[] da = dateStr.replace(" ","-").replace(":","-").substring(2, dateStr.length()).split("-");
    	StringBuilder db = new StringBuilder();
    	for (int i = 0; i < da.length; i++) {
    		String dr = da[i];
    		db.append(dr);
		}
		return db.append(((int)(Math.random()*9000+1000))+"").toString();
	}
	
	/**
	 * 生成11位Uid字符串，不足11位，前面补0
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-4上午11:30:59 <br/>
	 * @return
	 */
	public static String generateUidStr(Integer uid){
		StringBuffer sb=new StringBuffer();
		String uidStr="";
		String zeroStr="00000000000";
		if(uid==null){
			return null;
		}else{
			uidStr = uid.toString();
			int length = uidStr.length();
			if(length<11){
				String substring = zeroStr.substring(0, 11-length);
				sb.append(substring).append(uidStr);
			}else{
				sb.append(uid);
			}
		}
		
		return sb.toString();
	}
	

	
	/**
	 * 
	 * 方法描述：list集合按符号隔开输出
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日上午10:06:28 <br/>
	 * @param list
	 * @param separator
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String listToString(List list, char separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i));
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	
	/** 
     * 读取字符串第i次出现特定符号的位置 
     * @param string 
     * @param i 
     * @return 
     */  
    public static int getCharacterPosition(String string ,int i,String character){  
        //这里是获取"/"符号的位置  
        // Matcher slashMatcher = Pattern.compile("/").matcher(string);  
        Matcher slashMatcher = Pattern.compile(character).matcher(string);  
        int mIdx = 0;  
        while(slashMatcher.find()) {  
           mIdx++;  
           //当"/"符号第三次出现的位置  
           if(mIdx == i){  
              break;  
           }  
        }  
        return slashMatcher.start();  
     }  
    
    /**
     * 
     * 方法描述：将字符串转换为数组后，返回查找字符串的数组下标 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-8上午11:26:07 <br/>
     * @param goalStr :目标字符串
     * @param findStr :查找字符串
     * @param separator:分隔符
     * @return
     */
    public static int getArrayIndexFromStr(String goalStr,String findStr,String separator ){
    	int index=0;
    	if(goalStr==null){
    		index=-1;
    	}else{
    		String[] split = goalStr.split(separator);
    		int k=1;
    		for(String str:split){
    			if(findStr.equals(str)){
    				index=k;
    				break;
    			}
    			k++;
    		}
    	}
    	return index;
    }
    
    
    /**
     * 
     * 方法描述：字节数自动转换
     * 创建人： jianming <br/>
     * 创建时间：2017年7月24日下午3:12:22 <br/>
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {  
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义  
        if (size < 1024) {  
            return String.valueOf(size) + "B";  
        } else {  
            size = size / 1024;  
        }  
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位  
        //因为还没有到达要使用另一个单位的时候  
        //接下去以此类推  
        if (size < 1024) {  
            return String.valueOf(size) + "KB";  
        } else {  
            size = size / 1024;  
        }  
        if (size < 1024) {  
            //因为如果以MB为单位的话，要保留最后1位小数，  
            //因此，把此数乘以100之后再取余  
            size = size * 100;  
            return String.valueOf((size / 100)) + "."  
                    + String.valueOf((size % 100)) + "MB";  
        } else {  
            //否则如果要以GB为单位的，先除于1024再作同样的处理  
            size = size * 100 / 1024;  
            return String.valueOf((size / 100)) + "."  
                    + String.valueOf((size % 100)) + "GB";  
        }  
    }  
}
