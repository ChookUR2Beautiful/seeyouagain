package com.xmniao.common;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * 向指定URL发送GET,POST方法的请求
 * 
 * @author Dongjietao
 * 
 */
public class HttpRequest {
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
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			/*
			 * for (String key : map.keySet()) { System.out.println(key + "--->"
			 * + map.get(key)); }
			 */
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
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
	 * @throws Exception
	 */
	public static Map<String, Object> sendPost(String url, String param)
			throws Exception {// 汇付天下提现

		BufferedOutputStream out = null;
		BufferedReader in = null;
		Map<String, Object> map = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			out = new BufferedOutputStream(conn.getOutputStream());

			out.write(param.getBytes("GBK"));

			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			ChinaPnRReturnData cpr = new ChinaPnRReturnData();
			map = cpr.resolveXML(conn.getInputStream()); // 解析xml数据信息，返回map
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
				// System.out.println(line);
			}
			 System.out.println("======:"+result);
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
			throw e;
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
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return map;
		// return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		HttpRequest hr = new HttpRequest();
		
		hr.sendPost("http://192.168.50.110:8080/xmnpay/searchPay", "orderNumber=123456");
		
		
	}

	public static Map<String, String> sendPostRT(String url, String param)
			throws Exception {// 融宝提现

		BufferedOutputStream out = null;
		BufferedReader in = null;
		Map<String, Object> map = null;
		Map<String, String> resutlMap = new HashMap<String, String>();
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			out = new BufferedOutputStream(conn.getOutputStream());

			out.write(param.getBytes("gbk"));

			// flush输出流的缓冲
			out.flush();
			ChinaPnRReturnData cpr = new ChinaPnRReturnData();
			map = cpr.resolveXML(conn.getInputStream()); // 解析xml数据信息，返回map
			/*
			 * in = new BufferedReader( new
			 * InputStreamReader(conn.getInputStream(),"GBK")); String line;
			 * while ((line = in.readLine()) != null) { result += line;
			 * System.out.println(line); }
			 */

			for (String key : map.keySet()) {
				resutlMap.put(key, String.valueOf(map.get(key)));
			}
			System.out.println("======:" + map);
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			throw e;
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
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return resutlMap;
		// return result;
	}

	public static InputStream sendPostRTQ(String url, String param) {// 融宝提现查询返回字节流

		BufferedOutputStream out = null;
		InputStream input = null;
		String result = "";

		URL realUrl = null;
		try {
			realUrl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 打开和URL之间的连接
		URLConnection conn = null;
		try {
			conn = realUrl.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置通用的请求属性
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		// conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);

		try {
			out = new BufferedOutputStream(conn.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out.write(param.getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// flush输出流的缓冲
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			input = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return input; // 解析xml数据信息，返回map

		// return result;
	}

	public static String inputStreamToString(InputStream input) {// 字节流转换为字符串

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String str = null;
		int i = -1;
		try {
			while ((i = input.read()) != -1) {
				baos.write(i);// 读入内存
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = baos.toString();
		try {
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;//
	}
}
