/**
 * 
 */
package com.xmniao.xmn.test.publish;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 
 * 项目名称：projectD
 * 
 * 类名称：TxtFileReadUtil
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-20 下午4:44:36 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class TxtFileReadUtil {

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-20下午4:44:37
	 * @param args
	 */
	public static void main(String[] args) {
		readTxtFile("D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\发布清单.txt");
	}
	
	
	 public static void readTxtFile(String filePath){
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    String fileName= null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                    	int beginIndex = lineTxt.lastIndexOf("/")+1;
	                    	fileName=lineTxt.substring(beginIndex, lineTxt.length()).replace("java", "class");
	                        System.out.println(fileName);
	                    }
	                    read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	     
	    }

}
