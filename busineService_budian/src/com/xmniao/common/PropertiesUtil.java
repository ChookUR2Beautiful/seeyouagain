package com.xmniao.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：PropertiesUtil
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年6月8日 上午10:39:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class PropertiesUtil {		
	public static String readValue(String key ,String filePath){				
	  Properties props = new Properties();  
        InputStream in = null;  
        try {  
            in = new FileInputStream("properties/"+filePath);    
            props.load(in);  
            return props.getProperty(key);   
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;  
        } finally {  
            if (null != in){ 
                try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
            }
        }  
    }  
}

