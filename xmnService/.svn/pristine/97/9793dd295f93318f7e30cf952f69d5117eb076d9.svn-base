package com.xmniao.xmn.core.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;

public class CryptDecryptUtil {

//	private static String base64edKey = "aBRAsLPySqNxXRyrEkNfSQ==";
//	private static String base64edIv = "QHkdSEw5rohbvYT6Nckn6A==";
	
	/**
	 * 编码字符
	 * @param cryptStr
	 * @return
	 * @throws Exception
	 */
	public static String crypt(String cryptStr,String base64edKey,String base64edIv) throws Exception {
		return new String(Base64.encodeBase64(AESUtil.encrypt(cryptStr, base64edKey, base64edIv)),"UTF-8");
	}
	
	/**
	 * 解码字符
	 * @param decryptStr
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static  String decrypt(String decryptStr,String base64edKey,String base64edIv) throws UnsupportedEncodingException{
		byte[] decrypt = null;
		try {
			decrypt = AESUtil.decrypt(Base64.decodeBase64(decryptStr.getBytes("UTF-8")), base64edKey, base64edIv);
		} catch (Exception e) {
			throw new SecurityException(e);
		}
		return new String(decrypt,"UTF-8");
	}
	
	 public static void main(String[] args) {
		 try {
			Map<Object, Object> map = new HashMap<>();
			map.put("liveRecordId", 1347);
			map.put("text", "你好a123");
			map.put("phone", "19800000003");
			String crypt = crypt(JSONObject.toJSONString(map), com.xmniao.xmn.core.util.Base64.getBase64("xunminiaozhibo11"), com.xmniao.xmn.core.util.Base64.getBase64("xmnlive1xmnlive1"));
			System.out.println(crypt);
			String decrypt = decrypt(crypt, com.xmniao.xmn.core.util.Base64.getBase64("xunminiaozhibo11"), com.xmniao.xmn.core.util.Base64.getBase64("xmnlive1xmnlive1"));
			System.out.println(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
