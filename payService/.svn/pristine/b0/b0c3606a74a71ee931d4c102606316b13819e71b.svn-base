package com.xmniao.common;

import java.net.URL;
/**
 * 
 * @author DongJieTao
 *
 */
public class LoadProperties {

	public static  String getKeyPath(String keyName,String directory){//加载属性文件，获取对应value;

		URL currentPath=LoadProperties.class.getResource("/");
		String path=currentPath+"";

		path=path.substring(6,path.lastIndexOf("classes"));
		String separt=path.substring(path.length()-1,path.length());
		path=separt+path+directory+separt+keyName;
		path=path.replaceAll("\\%20", " ");
		System.out.println(path);
	   return path;

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("MerPrK872881.key.path: "+LoadProperties.getKeyPath("MerPrK872881.key.path"));
		//System.out.println("PgPubk.key.path: "+LoadProperties.getKeyPath("PgPubk.key.path"));
		//System.out.println(LoadProperties.class.getResource(""));
		
	}

}
