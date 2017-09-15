package com.xmniao.xmn.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @projectName: xmnService 
* @ClassName: StrUtils    
* @Description:字符串相关操作工具类   
* @author: liuzhihao   
* @date: 2017年2月17日 下午4:38:30
 */
public class StrUtils {

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
	
	/**
	 * 用正则表达式替换电话号码的中间四位数
	 * @param phone
	 * @return
	 */
	public static String regexReplacePhone(String phone){
		
		String regex = "(\\d{3})\\d{4}(\\d{4})";
		
		String str = phone.replaceAll(regex, "$1****$2");
		
		return str;
		
	}

	public static String standardName(Object name, Object phone) {
		String nameStr = name == null ? "" : String.valueOf(name);
		String phoneStr = phone == null ? "" : String.valueOf(phone);
		return standardName(nameStr, phoneStr);
	}

	public static String standardName(String name, String phone) {

		if (name == null || name.trim().equals("")) {
			if (phone == null) {
				return "";
			}
			return regexReplacePhone(phone);
		}
		return name;
	}
	
	public static String replaceBlank(String str) {  
        String dest = "";  
        if (str!=null) {  
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
            Matcher m = p.matcher(str);  
            dest = m.replaceAll("");  
        }  
        return dest;  
    }  
	
	public static void main(String[] args) {
		System.out.println(replaceBlank("\t"));
	}
}
