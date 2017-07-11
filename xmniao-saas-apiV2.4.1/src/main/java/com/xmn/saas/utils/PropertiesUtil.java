package com.xmn.saas.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

/**
 * 
*
* 项目名称：saasService   
* 类名称：PropertiesUtil   
* 类描述：   传入key和资源路径返回value
* 创建人：xiaoxiong   
* 创建时间：2016年4月21日 下午4:23:58   
* @version 
*
 */
@Service
public class PropertiesUtil {		
		public String getValue(String key ,String filePath) throws IOException{				
			  String path=this.getClass().getResource("/").getPath()+"properties/"+filePath;
			  Properties props = new Properties();  
		        InputStream in = null;  
		        try {  
		        	path=java.net.URLDecoder.decode(path,"UTF-8");
		            in = new FileInputStream(path);  
		            // 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中  
		            //in = propertiesTools.class.getResourceAsStream(fileNamePath);  
		            props.load(in);  
		            String value = props.getProperty(key);  
		            // 有乱码时要进行重新编码  
		            // new String(props.getProperty("name").getBytes("ISO-8859-1"), "GBK");  
		            return value;  
		  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		            return null;  
		  
		        } finally {  
		            if (null != in)  
		                in.close();  
		        }  
		    }  
		}

