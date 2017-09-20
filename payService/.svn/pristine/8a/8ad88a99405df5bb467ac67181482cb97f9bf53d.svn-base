package com.xmniao.kuaiqian;


import com.bill99.schema.fo.settlement.SettlementPkiApiRequest;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * webservices 请求应答码
 * */
public class FoApiPkiWSClient {
	//private static final String URL = "https://www.99bill.com/fo-batch-settlement/services";
	
	/**
	 * 用于把请求信息发送给快钱的webservices服务，同时拿到对应的应答信息
	 * @throws IOException 
	 * */
	public static SettlementPkiApiResponse doit(SettlementPkiApiRequest request,String url) throws MalformedURLException, UnsupportedEncodingException ,IOException {
		SettlementPkiApiResponse response = null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		String responseXML="";//返回的xml
		try {
			//创建URL
			URL urlString = new URL(url);
			URLConnection urlConn = urlString.openConnection();
			urlConn.setRequestProperty("content-type","text/xml;charset=utf-8");
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(1200000);
			PrintWriter out = new PrintWriter(urlConn.getOutputStream());
			String postContent = StringUtils.ReqFormat(CustomerUtil.settlementPkiApiRequestToXml(request));
			if (postContent == null){
				return null;
			}
			out.print(postContent);
			out.close();
			urlConn.connect();
			
			/*获取服务器端返回信息*/
			isr=new InputStreamReader(urlConn.getInputStream(),"utf-8"); //解决乱码错配合61行
			StringBuffer sb=new StringBuffer();
			if(isr!=null){
				br = new BufferedReader(isr);
	            String inputLine="";
	            while ((inputLine = br.readLine())!= null){
	                sb.append(inputLine);
	            }
			}
            String sbr=new String(sb.toString().getBytes());

			if (sbr.length() > 0) {
				responseXML=StringUtils.ResFormat(sbr);
				response=CustomerUtil.xmlToSettlementPkiApiResponse(responseXML);
			}
		} catch (MalformedURLException e) {
			throw new MalformedURLException(e.toString());
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException(e.toString());
		} catch (IOException e) {
			throw new IOException(e.toString());
		}finally{
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				br=null;
				isr=null;
				e.printStackTrace();
			}
		}
		return response;
	}
/*	public static String getResponseXML() {
		return responseXML;
	}
	public static void setResponseXML(String responseXML) {
		FoApiPkiWSClient.responseXML = responseXML;
	}*/
}
