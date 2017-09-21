package com.xmniao.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.xmniao.common.MD5Util;

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
		
		return MD5Util.md5(sign.toString());
	}
	
	public static void main(String[] args){
//		freshKey=5RDkevmhd9vVyXmBnvpE5o/u60otfdXxCxxOKY2Uxf7vMLGy9F7d6JgAsoaKQCMc5224/nHMlPnoBbMkiG2q0A\=\=
		System.out.println(MD5Util.md5("123456"));
	}

}
