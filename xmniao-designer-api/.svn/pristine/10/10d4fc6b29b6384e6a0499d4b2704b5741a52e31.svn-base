package com.xmn.designer.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signature {
	public static String sign(Map<String, String> map, String secret) {
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		
		StringBuffer sign = new StringBuffer();
		
		for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = map.get(key);
            
            sign.append(value != null ? value : "");
        }

		sign.append(secret);
		
		return MD5.Encode(sign.toString());
	}
	
	public static String string2Unicode(String string) {
		 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
	
	
	public static void main(String[] args) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("amount", "0.01");
		map.put("uid", "389805");
		map.put("source", "001");
		map.put("orderType", "2");
		map.put("subject", "SAAS套餐");
		map.put("paymentType", "1000013");
		map.put("wxuid", "op5ZZt1NEVBkmEGNevgsbcfWXRY8");
		map.put("orderSn", "011607201624031468");
		String s  = Signature.sign(map, "5RDkevmhd9vVyXmBnvpE5o/u60otfdXxCxxOKY2Uxf7vMLGy9F7d6JgAsoaKQCMc5224/nHMlPnoBbMkiG2q0A==");
		System.out.println(s);
	}
}
