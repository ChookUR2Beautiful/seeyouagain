package com.xmniao.wpay;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xmniao.wpay.HttpClientUtil;

/**
 * 财付通http或者https网络通信客户端<br/>
 * ========================================================================<br/>
 * api说明：<br/>
 * setReqContent($reqContent),设置请求内容，无论post和get，都用get方式提供<br/>
 * getResContent(), 获取应答内容<br/>
 * setMethod(method),设置请求方法,post或者get<br/>
 * getErrInfo(),获取错误信息<br/>
 * setCertInfo(certFile, certPasswd),设置证书，双向https时需要使用<br/>
 * setCaInfo(caFile), 设置CA，格式未pem，不设置则不检查<br/>
 * setTimeOut(timeOut)， 设置超时时间，单位秒<br/>
 * getResponseCode(), 取返回的http状态码<br/>
 * call(),真正调用接口<br/>
 * getCharset()/setCharset(),字符集编码<br/>
 * 
 * ========================================================================<br/>
 *
 */
public class TenpayHttpClientV3 {
	
	private static final String USER_AGENT_VALUE = 
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";
	
//	private static final String JKS_CA_FILENAME = 
//		"tenpay_cacert.jks";
//	
//	private static final String JKS_CA_ALIAS = "tenpay";
//	
//	private static final String JKS_CA_PASSWORD = "";
	
	/** ca证书文件 */
	private File caFile;
	
	/** 证书文件 */
	private File certFile;
	
	/** 证书密码 */
	private String certPasswd;
	
	//请求URL
	private String reqUrl;
	/** 请求内容，无论post和get，都用get方式提供 */
	private String reqContent;
	
	/** 应答内容 */
	private String resContent;
	
	/** 请求方法 */
	private String method;
	
	/** 错误信息 */
	private String errInfo;
	
	/** 超时时间,以秒为单位 */
	private int timeOut;
	
	/** http应答编码 */
	private int responseCode;
	
	/** 字符编码 */
	private String charset;
	
	private InputStream inputStream;
	
	private Map resXml;
	
	public TenpayHttpClientV3() {
		this.caFile = null;
		this.certFile = null;
		this.certPasswd = "";
		
		this.reqUrl="";
		this.reqContent = "";
		this.resContent = "";
		this.method = "POST";
		this.errInfo = "";
		this.timeOut = 30;//30秒
		
		this.responseCode = 0;
		this.charset = "GBK";
		
		this.inputStream = null;
		
		resXml = new HashMap();
	}

	/**
	 * 设置证书信息
	 * @param certFile 证书文件
	 * @param certPasswd 证书密码
	 */
	public void setCertInfo(File certFile, String certPasswd) {
		this.certFile = certFile;
		this.certPasswd = certPasswd;
	}
	
	/**
	 * 设置ca
	 * @param caFile
	 */
	public void setCaInfo(File caFile) {
		this.caFile = caFile;
	}
	
	/**
	 * 请求URL
	 * @param reqUrl
	 */
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	
	/**
	 * 设置请求内容
	 * @param reqContent 表求内容
	 */
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}
	

	/**
	 * 获取结果内容
	 * @return String
	 * @throws IOException 
	 */
	public String getResContent() {
		try {
			this.doResponse();
		} catch (IOException e) {
			this.errInfo = e.getMessage();
			//return "";
		}
		
		return this.resContent;
	}
	
	/**
	 * 获取结果内容
	 * @return String
	 * @throws IOException 
	 */
	public Map getResXmlContent() {
		try {
			this.doResponse2();
		} catch (IOException e) {
			this.errInfo = e.getMessage();
			//return "";
		}
		
		return this.resXml;
	}
	
	/**
	 * 设置请求方法post或者get
	 * @param method 请求方法post/get
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * 获取错误信息
	 * @return String
	 */
	public String getErrInfo() {
		return this.errInfo;
	}
	
	/**
	 * 设置超时时间,以秒为单位
	 * @param timeOut 超时时间,以秒为单位
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	
	/**
	 * 获取http状态码
	 * @return int
	 */
	public int getResponseCode() {
		return this.responseCode;
	}
	
	/**
	 * 执行http调用。true:成功 false:失败
	 * @return boolean
	 */
	public boolean call() {
		
		boolean isRet = false;
		
		//http
		if(null == this.caFile && null == this.certFile) {
			try {
				this.callHttp();
				isRet = true;
			} catch (IOException e) {
				this.errInfo = e.getMessage();
			}
			return isRet;
		}
		
		//https
		try {
			this.callHttps();
			isRet = true;
		} catch (UnrecoverableKeyException e) {
			this.errInfo = e.getMessage();
		} catch (KeyManagementException e) {
			this.errInfo = e.getMessage();
		} catch (CertificateException e) {
			this.errInfo = e.getMessage();
		} catch (KeyStoreException e) {
			this.errInfo = e.getMessage();
		} catch (NoSuchAlgorithmException e) {
			this.errInfo = e.getMessage();
		} catch (IOException e) {
			
			this.errInfo = e.getMessage();			
			e.printStackTrace();
		}
		System.out.println("errInfo"+this.errInfo);
		return isRet;
		
	}
	
	protected void callHttp() throws IOException {
		
		if("POST".equals(this.method.toUpperCase())) {
			String url = this.reqUrl;//HttpClientUtil.getURL(this.reqContent);
			String queryString = this.reqContent;//HttpClientUtil.getQueryString(this.reqContent);
			byte[] postData = queryString.getBytes(this.charset);
			this.httpPostMethod(url, postData);
			
			return ;
		}
		
		this.httpGetMethod(this.reqUrl,this.reqContent);
		
	} 
	
	protected void callHttps() throws IOException, CertificateException,
			KeyStoreException, NoSuchAlgorithmException,
			UnrecoverableKeyException, KeyManagementException {

//		// ca目录
//		String caPath = this.caFile.getParent();
//
//		File jksCAFile = new File(caPath + "/"
//				+ TenpayHttpClientV3.JKS_CA_FILENAME);
//		if (!jksCAFile.isFile()) {
//			X509Certificate cert = (X509Certificate) HttpClientUtil
//					.getCertificate(this.caFile);
//
//			FileOutputStream out = new FileOutputStream(jksCAFile);
//
//			// store jks file
//			HttpClientUtil.storeCACert(cert, TenpayHttpClientV3.JKS_CA_ALIAS,
//					TenpayHttpClientV3.JKS_CA_PASSWORD, out);
//
//			out.close();
//
//		}
//
//		FileInputStream trustStream = new FileInputStream(jksCAFile);
//		FileInputStream keyStream = new FileInputStream(this.certFile);
//
//		SSLContext sslContext = HttpClientUtil.getSSLContext(trustStream,
//				TenpayHttpClientV3.JKS_CA_PASSWORD, keyStream, this.certPasswd);
//		
//		//关闭流
//		keyStream.close();
//		trustStream.close();
		
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(certFile);
        try {
            keyStore.load(instream, certPasswd.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslContext = new SSLContextBuilderUtil()
                .loadKeyMaterial(keyStore, certPasswd.toCharArray())
                .build();
        
		if("POST".equals(this.method.toUpperCase())) {
			String url = this.reqUrl;//HttpClientUtil.getURL(this.reqContent);
			String queryString = this.reqContent;//HttpClientUtil.getQueryString(this.reqContent);
			
			System.out.println("url:"+url);
			System.out.println("queryString:"+queryString);
			byte[] postData = queryString.getBytes(this.charset);
			
			this.httpsPostMethod(url, postData, sslContext);
			
			return ;
		}
		
		this.httpsGetMethod(this.reqUrl,this.reqContent, sslContext);

	}
	
	/**
	 * 以http post方式通信
	 * @param url
	 * @param postData
	 * @throws IOException
	 */
	protected void httpPostMethod(String url, byte[] postData)
			throws IOException {

		HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);

		this.doPost(conn, postData);
	}
	
	/**
	 * 以http get方式通信
	 * 
	 * @param url
	 * @throws IOException
	 */
	protected void httpGetMethod(String url,String content) throws IOException {
		
		/**
		 * 备注：不可用 2015-9-22
		 */
		HttpURLConnection httpConnection =
			HttpClientUtil.getHttpURLConnection(url);
		
		this.setHttpRequest(httpConnection);
		
		httpConnection.setRequestMethod("GET");
		
		this.responseCode = httpConnection.getResponseCode();
		
		this.inputStream = httpConnection.getInputStream();
		
	}
	
	/**
	 * 以https get方式通信
	 * @param url
	 * @param sslContext
	 * @throws IOException
	 */
	protected void httpsGetMethod(String url,String content, SSLContext sslContext)
			throws IOException {

		/**
		 * 备注：不可用 2015-9-22
		 */
		SSLSocketFactory sf = sslContext.getSocketFactory();

		HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);

		conn.setSSLSocketFactory(sf);

		this.doGet(conn);

	}
	
	protected void httpsPostMethod(String url, byte[] postData,
			SSLContext sslContext) throws IOException {

		SSLSocketFactory sf = sslContext.getSocketFactory();

		HttpsURLConnection conn = HttpClientUtil.getHttpsURLConnection(url);

		conn.setSSLSocketFactory(sf);

		this.doPost(conn, postData);

	}
	
	/**
	 * 设置http请求默认属性
	 * @param httpConnection
	 */
	protected void setHttpRequest(HttpURLConnection httpConnection) {
		
		//设置连接超时时间
		httpConnection.setConnectTimeout(this.timeOut * 1000);
		
		//User-Agent
		httpConnection.setRequestProperty("User-Agent", 
				TenpayHttpClientV3.USER_AGENT_VALUE);
		
		//不使用缓存
		httpConnection.setUseCaches(false);
		
		//允许输入输出
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);
		
	}
	
	/**
	 * 处理应答
	 * @throws IOException
	 */
	protected void doResponse() throws IOException {
		
		if(null == this.inputStream) {
			return;
		}

		//获取应答内容
		this.resContent=HttpClientUtil.InputStreamTOString(this.inputStream,this.charset); 

		//关闭输入流
		this.inputStream.close();
		
	}
	
	/**
	 * 处理应答
	 * @throws IOException
	 */
	protected void doResponse2() throws IOException {
		
		if(null == this.inputStream) {
			return ;
		}

		//获取应答内容
		//this.resContent=HttpClientUtil.InputStreamTOString(this.inputStream,this.charset); 

		SAXReader reader = new SAXReader();
		Document doc=null;
		try {
			//InputStream in=new URL(url).openStream();
				doc = reader.read(inputStream);
				Element root = doc.getRootElement();
				for (Iterator it = root.elementIterator(); it.hasNext();) {
					Element e = (Element) it.next();
					if (e.nodeCount() > 1) {
						HashMap hm1 = new HashMap();
						for (Iterator it1 = e.elementIterator(); it1.hasNext();) {
							Element e1 = (Element) it1.next();
							hm1.put(e1.getName(), e1.getText());
						}
						resXml.put(e.getName(), hm1);
					} else {
						resXml.put(e.getName(), e.getText());
					}
				}
			doc.clearContent();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally{
			//关闭输入流
			this.inputStream.close();
		}
		
		return ;
	}
	
	/**
	 * post方式处理
	 * @param conn
	 * @param postData
	 * @throws IOException
	 */
	protected void doPost(HttpURLConnection conn, byte[] postData)
			throws IOException {

		// 以post方式通信
		conn.setRequestMethod("POST");

		// 设置请求默认属性
		this.setHttpRequest(conn);

		// Content-Type
		conn.setRequestProperty("Content-Type",
				"text/xml");

		BufferedOutputStream out = new BufferedOutputStream(conn
				.getOutputStream());

		final int len = 1024; // 1KB
		HttpClientUtil.doOutput(out, postData, len);

		// 关闭流
		out.close();

		// 获取响应返回状态码
		this.responseCode = conn.getResponseCode();

		// 获取应答输入流
		this.inputStream = conn.getInputStream();

	}
	
	/**
	 * get方式处理
	 * @param conn
	 * @throws IOException
	 */
	protected void doGet(HttpURLConnection conn) throws IOException {
		
		//以GET方式通信
		conn.setRequestMethod("GET");
		
		//设置请求默认属性
		this.setHttpRequest(conn);
		
		//获取响应返回状态码
		this.responseCode = conn.getResponseCode();
		
		//获取应答输入流
		this.inputStream = conn.getInputStream();
	}

	
}
