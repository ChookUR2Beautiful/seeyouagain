package com.xmniao.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * MD5工具类
 * 
 * @author YangJing
 * 
 */
public class MD5 {
    public static final String MD5CODE = "";

    private static MessageDigest messageDigest = null;
    private static Logger log = Logger.getLogger(MD5.class);

    public static String Encode(final String Source) {
	return Encode(Source + MD5CODE, null);
    }

    /**
     * MD5字符串加密
     * 
     * @param Source
     * @param charset
     * @return
     */
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

    /**
     * byte数组加密
     * 
     * @param Source
     * @return
     */
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

    /**
     * 数组2hex
     * 
     * @param Source
     * @return
     */
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

    /**
     * 验证签名是否一致
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果 
     */
    public static boolean verify(String text, String sign,  String input_charset) {
    	String mysign = Encode(text,input_charset);
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
