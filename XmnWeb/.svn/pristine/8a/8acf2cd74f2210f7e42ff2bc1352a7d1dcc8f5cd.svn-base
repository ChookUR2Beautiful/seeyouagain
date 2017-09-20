package com.xmniao.xmn.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class NMD5 {
	public static final String MD5CODE = "";

	private static MessageDigest messageDigest = null;
	private static Logger log = Logger.getLogger(NMD5.class);

	public static String Encode(final String Source) {
		return Encode(Source + MD5CODE, null);
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
	
	/**
	 * 
	 * 方法描述：md5加密并且前六位和后六位互换 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-7下午5:03:30 <br/>
	 * @param source
	 * @return
	 */
	public static String EncodeSwap(final String source){
		String md = NMD5.Encode(source);
		String md5BeforeSix = md.substring(0, 6);
		String md5InSix = md.substring(6, 26);
		String md5AfterSix = md.substring(26, 32);
		String md5Nmber = md5AfterSix + md5InSix + md5BeforeSix;
		
		return md5Nmber;
	}

	public static void main(String args[]) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("0.09");
		buffer.append("10009");
		buffer.append("http://user.xunmike.com/member/triporderdisp.html?orderCode=ZC20141107360413_Y");
		buffer.append("PC");
		buffer.append("");
		buffer.append("");

		String str = "";
		String encode = NMD5.Encode(str);
		System.out.println("MD5.encode =" + encode);
		
		// md5加密并且前六位和后六位互换
		String md = NMD5.Encode(str);
		String md5BeforeSix = md.substring(0, 6);
		String md5InSix = md.substring(6, 26);
		String md5AfterSix = md.substring(26, 32);
		String md5Nmber = md5AfterSix + md5InSix + md5BeforeSix;

		System.out.println(md5Nmber);

	}
}
