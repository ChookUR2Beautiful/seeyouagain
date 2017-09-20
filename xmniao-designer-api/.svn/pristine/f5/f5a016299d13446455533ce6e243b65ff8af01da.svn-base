/**   
 * 文件名：PropertiesUtil.java   
 *    
 * 日期：2013-10-25   
 * 
 * Copyright (c) 广州闪购软件服务有限公司-版权所有     
 */
package com.xmn.designer.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * 项目名称：shopsSystem
 * 
 * 类名称：PropertiesUtil
 * 
 * 类描述： 资源操作类
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2013-10-25 下午2:59:58
 * 
 * Copyright (c) 广州闪购软件服务有限公司-版权所有
 */
public class PropertiesUtil {
	
	/** 
     * 获取Properties对象(默认为utf-8字符编码) 
     *  
     * @param filePath 
     *            文件路径 
     * @return 
     */  
    public static Properties getProperties() {  
        return getProperties("utf-8");  
    }  
    
    /** 
     * 获取Properties对象 
     *  
     * @param filePath 
     *            文件路径 
     * @param encoding 
     *            字符编码 
     * @return 
     */  
    public static Properties getProperties(String encoding) {  
        // 定义properties对象  
        Properties props = new Properties();  
  
        try {  
            
            InputStream rd = PropertiesUtil.class.getClassLoader().getResourceAsStream ("properties/conf-thrift.properties");
            // 加载properties文件  
            props.load(rd);  
            rd.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return props;  
    }  
    
    /** 
     * 获取Properties对象 
     *  
     * @param filePath 
     *            文件路径 
     * @param encoding 
     *            字符编码 
     * @return 
     */  
    public static Properties getCustomProperties(String filePath) {  
        // 定义properties对象  
        Properties props = new Properties();  
  
        try {  
            
            InputStream rd = PropertiesUtil.class.getClassLoader().getResourceAsStream (filePath);
            // 加载properties文件  
            props.load(rd);  
            rd.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return props;  
    } 
    
    /** 
     * 获取key对应的value值 默认为utf-8 
     * @param filePath 
     * @param name 
     * @return 
     */  
    public static String readValue(String name){  
        return readValue("utf-8", name);  
    } 
    
    /** 
     * 获取key对应的value值 
     * @param filePath 
     * @param encoding 
     * @param name 
     * @return 
     */  
    public static String readValue(String encoding,String name){  
        Properties props=getProperties(encoding);  
        return props.getProperty(name);  
    }  

}
