package com.xmniao.xmn.core.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;

import com.xmniao.xmn.core.util.DateUtil;


public class TestUserSendGiftClass1 implements Runnable{
	
	
	
	private static String userInfo ="605982,605983,605990,605991,606006,606007,606481,606482,606483,606484,606614,606615,606770,606774,606773,606775";	
	private static String giftInfo ="1,2,3,4,10,11,12,13,14";
	
	//初始化一批用户进房间
	public static void userEntryRoom(){
		String [] userInfoId = userInfo.split(",");
		for (int i = 0; i < userInfoId.length; i++) {
			String param = "uid="+userInfoId[i]+"&zhiboRecordId=3954&systemversion=ios";   //3954  3963  3960
			//循环登录直播间
//			sendPost("http://192.168.50.56/xmnService/live/anchor/entryRoom", param);
			sendPost("http://192.168.50.56/xmnService/live/anchor/entryRoom", param);
		}
		
	}
	
	//初始化一批用户进房间
	public void run(){
		String [] userInfoId = userInfo.split(",");
		String [] giftInfoId = giftInfo.split(",");
		System.out.println(DateUtil.format(new Date(), DateUtil.defaultSimpleFormater) );
		for (int i = 0; i < 50; i++) {
			Random random = new Random();
			int userId = (random.nextInt(16)%(16-1+1) + 0);
			int giftId = (random.nextInt(9)%(9-1+1) + 0);
			
			String param = "liveRecordId=3954&giftType=0&isFree=1&anchorId=1835&roomNo=2000"+"&id="+giftInfoId[giftId]+"&uid="+userInfoId[userId];
			//循环登录直播间
//			sendPost("http://192.168.50.56/xmnService/live/gifts/giveaway", param);
//			sendPost("http://192.168.50.56/xmnService/live/gifts/giveaway", param);
		}
		
		System.out.println(DateUtil.format(new Date(), DateUtil.defaultSimpleFormater) );
	}
	
	public static void main(String[] args) {
		//进房间
//	  userEntryRoom();
	  
	  TestUserSendGiftClass1 runnable = new TestUserSendGiftClass1();
	  for (int i = 0; i <100; i++) {
		  new Thread(runnable).start(); 
	  }
      
	}
	
	
	
	
	
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String sendPost(String url,String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			//打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//发送POST请求必须设置如下两行
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			//发送请求参数
			out.print(param);
			//flush输出流的缓冲
			out.flush();
			//定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine())!= null) {
				result += "\n" + line;
			}
		}
		catch(Exception e)
		{
		System.out.println("发送POST请求出现异常！" + e);
		e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	
	

}
