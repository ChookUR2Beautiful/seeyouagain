package com.xmniao.xmn.core.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;



/**
 * 
 * AES加解密工具类
 */
public class AESUtil {
    protected static final String KEY_ALGORITHM = "AES";  
    protected static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 
     * @param rawData 原始数据
     * @param base64edKey base64编码过的key
     * @param base64edIv base64编码过的iv
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String rawData, String base64edKey, String base64edIv) throws Exception {  
		return encrypt(rawData.getBytes(), Base64.decodeBase64(base64edKey.getBytes("UTF-8")), Base64.decodeBase64(base64edIv.getBytes("UTF-8")));
    }  

    /**
     * 加密
     * @param rawDataByte 原始数据
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] rawDataByte, byte[] key, byte[] iv) throws Exception {  
    	Key skey = new SecretKeySpec(key, KEY_ALGORITHM);
    	Cipher in = Cipher.getInstance(ALGORITHM);
    	in.init(Cipher.ENCRYPT_MODE, skey, new IvParameterSpec(iv));
    	return in.doFinal(rawDataByte);
    }  

    /**
     * 解密
     * @param decryptData 要解密的数据
     * @param base64edKey base64编码过的key
     * @param base64edIv base64编码过的iv
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] decryptData, String base64edKey, String base64edIv) throws Exception {  
		return decrypt(decryptData, Base64.decodeBase64(base64edKey.getBytes("UTF-8")), Base64.decodeBase64(base64edIv.getBytes("UTF-8")));
	}  
    
    /**
     * 解密
     * @param encryptData
     * @param key
     * @param iv
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] encryptData, byte[] key, byte[] iv) throws Exception {  
    	Key skey = new SecretKeySpec(key, KEY_ALGORITHM);
    	Cipher out = Cipher.getInstance(ALGORITHM);
    	out.init(Cipher.DECRYPT_MODE, skey, new IvParameterSpec(iv));
    	return out.doFinal(encryptData);
    }
    
}
