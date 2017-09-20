package com.xmn.designer.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
*    
* 类描述：   MD5加密
* 创建人：fubin  
* 创建时间：2014年12月11日 下午6:45:03   
* 修改人：  
* 修改时间：  
* 修改备注：   
* @version    v1.X
*
 */
public class MD5 {
	public static final String MD5CODE = "";

	private static MessageDigest messageDigest = null;

	public static String Encode(final String Source) {
		return Encode(Source + MD5CODE, "UTF-8");
	}

	public static String Encode(final String Source, final String charset) {
		if (Source == null)
			return null;
		if (messageDigest == null) {
			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
		}
		if (charset == null || charset.trim().length() <= 0)
			messageDigest.update(Source.getBytes());
		else {
			try {
				messageDigest.update(Source.getBytes(charset));
			} catch (Exception e) {
				messageDigest.update(Source.getBytes());
			}
		}
		byte[] digesta = messageDigest.digest();
		return Bytes2Hex(digesta);
	}

	public static byte[] EncodeBytes(byte[] Source) {
		if (messageDigest == null) {
			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
		}
		messageDigest.update(Source);
		byte[] ret = messageDigest.digest();
		return ret;
	}

	public static String Bytes2Hex(final byte[] Source) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < Source.length; n++) {
			stmp = (java.lang.Integer.toHexString(Source[n] & 0xFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs;
	}

	public static void main(String args[]) {
	
		String str = "123456";
		String encode = MD5.Encode(str);
		String encode1 = MD5.Encode(str);
		String encode2 = MD5.Encode(str);
		System.out.println("MD5.encode 123456="+encode+"---"+encode1+"---"+encode2);
		
	}
}
