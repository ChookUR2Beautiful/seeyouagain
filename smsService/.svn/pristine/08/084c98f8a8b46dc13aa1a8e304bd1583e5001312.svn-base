package com.sms.common;

import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.InputStream; 
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件类
 * @author douk
 *
 */
public class ConfigFileReader {
	
	public static Properties p = new Properties();
	
	public final Logger log =  Logger.getLogger(ConfigFileReader.class);
	
	public ConfigFileReader()
	{		
		try {
			InputStream ins = ConfigFileReader.class.getClassLoader().getResourceAsStream("appid.properties"); 

			p.load(ins);
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
