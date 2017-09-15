package com.xmniao.xmn.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

/**
 * 工具类
 * @author douk
 *
 */
public class utilClass {
	
	/**
	 * 获取4位随机数
	 * @return
	 */
	public static int RandomNum(){
		int[] array = {1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 4; i++){
		    result = result * 10 + array[i];
		}
		return result;
	}
	
	
	
	/**
	 * 获取6位随机数
	 * @return
	 */
	public static int Random6Num(){
		int[] array = {1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++){
		    result = result * 10 + array[i];
		}
		return result;
	}
	
	/**
	 * @Description 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
    	//开始请求时间
    	long startTime = System.currentTimeMillis();
        String result = "";
        BufferedReader in = null;
        HttpURLConnection connection = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestMethod("GET");
			connection.setDoOutput(true);  // post请求  需设置
			connection.setDoInput(true);  // post请求  需设置
			connection.setRequestProperty("Content-Type","application/json,charset=UTF-8"); //发送格式
			//设置连接主机超时（单位：毫秒）
			connection.setConnectTimeout(6000);
			//设置从主机读取数据超时（单位：毫秒）
			connection.setReadTimeout(6000);
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！请求耗时:"+(System.currentTimeMillis()-startTime));
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                //即设置 http.keepAlive = false;
                if (connection != null){
                	connection.disconnect(); 
                }                
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            
        }
        return result;
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
    public static String sendPost(String url, String param) {
    	//开始请求时间
    	long startTime = System.currentTimeMillis();
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        HttpURLConnection connection = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestMethod("POST");
			connection.setDoOutput(true);  // post请求  需设置
			connection.setDoInput(true);  // post请求  需设置
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"); //发送格式
			//设置连接主机超时（单位：毫秒）
			connection.setConnectTimeout(6000);
			//设置从主机读取数据超时（单位：毫秒）
			connection.setReadTimeout(6000);
			// 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！耗时：" + (System.currentTimeMillis()-startTime));
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                //释放资源 即设置 http.keepAlive = false;
                if (connection != null){
                	connection.disconnect(); 
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }  	
	
//	public static void main(String[] args){
//		String dat = "2014-05-08";
//		Date date = DateUtil.parse(dat, "yyyy-MM-dd");
//		
//		String sdate = DateUtil.format(date, "yyyy-MM-dd");
//		System.out.println(sdate);
//		
//		String ss="1212";
//		if(isNumeric(ss)){
//			System.out.println("is number");
//		}else{
//			System.out.println("not number");
//		}
//	}
    
    /*public static void main(String[] args) {
    	String httpurl = "http://192.168.1.11:7900/user/getUserMikeCount.html";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("genussellerid", "25974");
		jsonObject.put("keywords", "");
		jsonObject.put("mike_type", 2);
		jsonObject.put("attach_time", "2015-07-20");
		String param = "p=" + jsonObject.toJSONString();
		System.out.println(httpurl+"?"+param);
		System.out.println("开始时间："+DateUtil.getCurrentTimeStr());
		int errorNum = 0;
		for(int i=0;i<1000;i++){
			try {
				long startTime = System.currentTimeMillis();
				String json = utilClass.sendGet(httpurl, param);
				System.err.println("第"+i+"次调用  耗时"+(System.currentTimeMillis()-startTime));
			} catch (Exception e) {					
				e.printStackTrace();
				errorNum = errorNum+1;
			}			
		}
		System.out.println("1000次出现失败有"+errorNum);
		System.out.println("结束时间："+DateUtil.getCurrentTimeStr());
	}*/
}
