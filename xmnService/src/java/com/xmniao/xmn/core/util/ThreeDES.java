package com.xmniao.xmn.core.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
* 项目名称：saasService   
* 类名称：ThreeDES   
* 类描述：3DES加密算法类
* 创建人：liuzhihao   
* 创建时间：2016年3月29日 下午5:49:17   
* @version    
*
 */
public class ThreeDES {
	//定义 加密算法,可用 DES,DESede,Blowfish
	private static final String Algorithm = "DESede";
	//24字节的密钥
	private static final byte[] keyBytes = {(byte)0x88, 0x44, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38
                           , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
                           , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};
                           
	/**
	 * 加密
	 * @param keybyte 加密密钥，长度为24字节
	 * @param src 被加密的数据缓冲区（源）
	 * @return
	 */
    public static String encryptMode(byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return parseByte2HexStr(c1.doFinal(src));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param keybyte 为加密密钥，长度为24字节
     * @param src 为加密后的缓冲区
     * @return
     */
    public static byte[] decryptMode(byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

	/**
	 * 将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}
	
	/**
	 * 将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	} 
    
    public static void main(String[] args)
    {
     
        String szSrc = "1002_1_2014-09-26-14:27:22";
       // String szSrc = "5D040A5E4A909168";
        
        
        System.out.println("加密前的字符串:" + szSrc);
        
        
        String encoded = encryptMode(szSrc.getBytes());
        
        //System.out.println("加密后的长度:" + encoded.length);
        
        System.out.println("加密后的字符串:" + encoded);
        
        System.out.println("加密后的字符串长度:" + encoded.length());
        
        byte[] srcBytes = decryptMode(parseHexStr2Byte("5D040A5E4A909168"));
        System.out.println("解密后的字符串:" + (new String(srcBytes)));
    }
}