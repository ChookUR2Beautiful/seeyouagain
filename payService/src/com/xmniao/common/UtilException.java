package com.xmniao.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;

public class UtilException {
	
	/**
	 * 获取该异常的StackTrace
	 * @param ex
	 * @return
	 */
	 public static String getExceptionStackTrace(Exception ex){
	        String sOut = "";
	        StackTraceElement[] trace = ex.getStackTrace();
	        for (StackTraceElement s : trace) {
	            sOut += "\tat " + s + "\r\n";
	        }
	        return sOut;
		 }
	
	 /**
	  * 获取该异常的所有信息
	  * @param ex
	  * @return
	  */
	public static String getExceptionInformation1(Exception ex) {
	         ByteArrayOutputStream out = new ByteArrayOutputStream();
	         PrintStream pout = new PrintStream(out);
	         ex.printStackTrace(pout);
	         String ret = new String(out.toByteArray());
	         pout.close();
	         try {
	              out.close();
	         } catch (Exception e) {
	         }
	         return ret;
	 }
	 /**
	  * 获取该异常的所有信息
	  * @param ex
	  * @return
	  */
	 public static String getExceptionInformation2(Throwable e){   
          StringWriter sw = new StringWriter();   
          PrintWriter pw = new PrintWriter(sw, true);   
          e.printStackTrace(pw);   
          pw.flush();   
          sw.flush();   
          return sw.toString();   
  } 
	  
	public static void main(String[] args) {
		test();
	}
	public static void test(){
		try {
			throw	new  ParseException("日期错啦",1);
		} catch (ParseException e) {
			System.out.println(getExceptionStackTrace(e));
			System.out.println("-----------1---------------");
			System.out.println(getExceptionInformation1(e));
			System.out.println("-----------2---------------");
			System.out.println(getExceptionInformation2(e));
			System.out.println("-----------3---------------");
			e.printStackTrace();
			System.out.println("-----------4---------------");
			System.out.println(e.toString());
		}
		
	}
	 
}
