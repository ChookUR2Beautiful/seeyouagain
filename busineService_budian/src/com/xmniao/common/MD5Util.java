package com.xmniao.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
	StringBuffer resultSb = new StringBuffer();
	for (int i = 0; i < b.length; i++)
	    resultSb.append(byteToHexString(b[i]));

	return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
	int n = b;
	if (n < 0)
	    n += 256;
	int d1 = n / 16;
	int d2 = n % 16;
	return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
	String resultString = null;
	try {
	    resultString = new String(origin);
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    if (charsetname == null || "".equals(charsetname))
		resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
	    else
		resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
	} catch (Exception exception) {
	}
	return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
	    "c", "d", "e", "f" };

    /**
     * 支付宝 生成签名字符串
     * 
     * @param text
     *            需要签名的字符串
     * @param key
     *            密钥
     * @param input_charset
     *            编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
	text = text + key;
	return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 支付宝 校验签名字符串
     * 
     * @param text
     *            需要签名的字符串
     * @param sign
     *            签名结果
     * @param key
     *            密钥
     * @param input_charset
     *            编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
	text = text + key;
	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
	if (mysign.equals(sign)) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
	if (charset == null || "".equals(charset)) {
	    return content.getBytes();
	}
	try {
	    return content.getBytes(charset);
	} catch (UnsupportedEncodingException e) {
	    throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
	}
    }

    // =========融宝支付=======//
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
	    'd', 'e', 'f' };

    /**
     * 对字符串进行MD5加密
     * 
     * @param text
     *            明文
     * 
     * @return 密文
     */
    public static String md5(String text) {
	MessageDigest msgDigest = null;
	try {
	    msgDigest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    throw new IllegalStateException("System doesn't support MD5 algorithm.");
	}
	try {
	    msgDigest.update(text.getBytes("UTF-8")); // 注意改接口是按照指定编码形式签名
	} catch (UnsupportedEncodingException e) {
	    throw new IllegalStateException("System doesn't support your  EncodingException.");
	}
	byte[] bytes = msgDigest.digest();
	String md5Str = new String(encodeHex(bytes));
	return md5Str;
    }

    /**
     * 十六进制转换
     * */
    private static char[] encodeHex(byte[] data) {
	int l = data.length;
	char[] out = new char[l << 1];
	// two characters form the hex value.
	for (int i = 0, j = 0; i < l; i++) {
	    out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
	    out[j++] = DIGITS[0x0F & data[i]];
	}
	return out;
    }

    // =========融宝支付=======//

    // =========通联支付md5开始=======//
    public static String md5Allin(String string) {
	byte[] hash;
	try {
	    hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException("Huh, MD5 should be supported?", e);
	} catch (UnsupportedEncodingException e) {
	    throw new RuntimeException("Huh, UTF-8 should be supported?", e);
	}

	return hexString(hash);
    }

    public static final String hexString(byte[] bytes) {
	StringBuffer buffer = new StringBuffer();
	for (int i = 0; i < bytes.length; i++) {
	    buffer.append(hexString(bytes[i]));
	}
	return buffer.toString();
    }

    public static final String hexString(byte byte0) {
	char ac[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	char ac1[] = new char[2];
	ac1[0] = ac[byte0 >>> 4 & 0xf];
	ac1[1] = ac[byte0 & 0xf];
	String s = new String(ac1);
	return s;
    }
    // =========通联支付md5结束=======//
}
