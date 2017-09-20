package com.xmniao.kuaiqian;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.xmniao.common.Base64;



/**
 * @project mgwCore
 * @author cen
 * @create_time:Jun 22, 2009
 * @modify_time:Jun 22, 2009
 */
public class PostTr1ProcessorImpl {
	
	
	/**
	 * @param merchantInfo
	 * @return
	 * @throws Exception
	 */
	public InputStream post(MerchantInfo merchantInfo) throws Exception {
		File certFile = new File(merchantInfo.getCertPath());
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(new FileInputStream(certFile), merchantInfo.getCertPass()
				.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, merchantInfo.getCertPass().toCharArray());

		TrustManager[] tm = { new MyX509TrustManager() }; 
		
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(kmf.getKeyManagers(),tm, null);

		SSLSocketFactory factory = sslContext.getSocketFactory();
		
		URL url = new URL(merchantInfo.getUrl());
		HttpsURLConnection urlc = (HttpsURLConnection) url.openConnection();
		urlc.setSSLSocketFactory(factory);
		urlc.setDoOutput(true); 
		urlc.setDoInput(true); 
		urlc.setReadTimeout(merchantInfo.getTimeOut()*1000);
		
		String authString = merchantInfo.getMerchantId() + ":" + merchantInfo.getPassword();
		String auth = "Basic " + Base64.encode(authString.getBytes());
		System.out.println("authString:"+authString+"\r\nauth："+auth);
		urlc.setRequestProperty("Authorization", auth);
	
		OutputStream out = urlc.getOutputStream();
		out.write(merchantInfo.getXml().getBytes());
		out.flush(); 
		out.close();
		
		return urlc.getInputStream();
	}
}
