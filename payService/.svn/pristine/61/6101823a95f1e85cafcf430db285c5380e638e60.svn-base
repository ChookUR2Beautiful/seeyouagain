package com.xmniao.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 融宝数据签名
 * 
 * @author DongJieTao
 * 
 */
public class RTSign {
	
	

	private static String batchVersion; // 版本号

	private static String signType; // 加密方式

	private static String batchBizid; // 合作伙伴在融宝的用户ID

	// //安全校验码 测试
	private static String key; // 安全校验码

	private static String inputCharset; // 编码gbk, utf8

	private static String batchBiztype; // 5位，默认00000
	
	private static String payUrl ;
	
	private static String queryUrl;
	
	
	
    
    public static String getPayUrl() {
		return payUrl;
	}

	public static void setPayUrl(String payUrl) {
		RTSign.payUrl = payUrl;
	}

	public static String getQueryUrl() {
		return queryUrl;
	}

	public static void setQueryUrl(String queryUrl) {
		RTSign.queryUrl = queryUrl;
	}

	public static String getSign(Map<String, Object> map) {

	return BuildMysign(ParaFilter(map), key);

    }

    public static String getPostData(Map<String, String> map) {

	StringBuffer sb = new StringBuffer();
	sb.append("_input_charset=" + inputCharset)
		.append("&batchBizid=" + batchBizid)
		.append("&batchVersion=" + batchVersion)
		.append("&batchBiztype=" + batchBiztype)
		.append("&batchDate=" + map.get("batchDate"))
		.append("&batchCurrnum=" + map.get("batchCurrnum"))
		.append("&batchCount=" + map.get("batchCount"))
		.append("&batchAmount=" + map.get("batchAmount"))
		.append("&batchContent=" + map.get("batchContent"))
		.append("&sign=" + map.get("sign"))
		.append("&signType=" + signType);
	return sb.toString();

    }

    /**
     * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String CreateLinkString(Map<String, Object> params) {
	List<String> keys = new ArrayList<String>(params.keySet());
	Collections.sort(keys);

	String prestr = "";

	for (int i = 0; i < keys.size(); i++) {
	    String key = keys.get(i);
	    String value = String.valueOf(params.get(key));

	    if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符o
		prestr = prestr + key + "=" + value;
	    } else {
		prestr = prestr + key + "=" + value + "&";
	    }
	}

	return prestr;
    }

    /**
     * 功能：除去数组中的空值和签名参数
     * 
     * @param map
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, Object> ParaFilter(Map<String, Object> map) {
	List<String> keys = new ArrayList<String>(map.keySet());
	Map<String, Object> sArrayNew = new HashMap<String, Object>();
	for (int i = 0; i < keys.size(); i++) {
	    String key = keys.get(i);
	    String value = String.valueOf(map.get(key));
	    if (value.equals("") || value == null||value.equals("null")
		    || key.equalsIgnoreCase("sign")
		    || key.equalsIgnoreCase("signType")) {// 新增notifyid不参加签名,只做标识用
		continue;
	    }

	    sArrayNew.put(key, value);
	}

	return sArrayNew;
    }

    // 生成签名结果
    public static String BuildMysign(Map<String, Object> sArray, String key) {
	String prestr = CreateLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	prestr = prestr + key; // 把拼接后的字符串再与安全校验码直接连接起来
	// System.out.println("签名数据体---：" + prestr);
	String mysign = MD5.Encode(prestr,"gbk");
	// System.out.println("+++"+mysign+"============");
	return mysign;
    }

    public static void httpClient(String url, String sign, String signType,
	    HttpServletRequest request, HttpServletResponse res) {

	HttpClient client = new HttpClient();
	// BASE64Decoder decoder = new BASE64Decoder();
	String ht = url + "&signType=" + signType + "&sign=" + sign;
	HttpMethod method = new GetMethod(ht);
	String response = "";
	try {
	    client.executeMethod(method);
	    response = method.getResponseBodyAsString();
	    Document doc = DocumentHelper.parseText(response);
	    Element root = doc.getRootElement();
	    String restatus = root.elementText("status");
	    String error = root.elementText("reason");
	    res.setContentType("text/html; charset=gbk"); // 转码
	    PrintWriter out = res.getWriter();
	    out.flush();
	    out.println("<script>");
	    out.println("alert('" + error + "！');");
	    out.println("history.back();");
	    out.println("</script>");
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    method.releaseConnection();
	}
    }

    public static void paddingData(Map<String, Object> map) {// 提现基础数据填充
	map.put("batchVersion",batchVersion);
	map.put("signType", signType);
	map.put("batchBizid", batchBizid);
	map.put("key", key);
	map.put("_input_charset",inputCharset);
	map.put("batchBiztype", batchBiztype);
    }

    public static void qPaddingData(Map<String, Object> map) {// 提现查询基础数据填充
	map.put("signType",signType);
	map.put("batchBizid", batchBizid);
	map.put("_input_charset", inputCharset);
	map.put("batchVersion", batchVersion);
    }

    /**
     * @Title: jm
     * @author Eric bjwdong@cn.ibm.com
     * @Description: 加密
     * @param @param content
     * @param @param pa
     * @param @return
     * @param @throws UnsupportedEncodingException
     * @param @throws CertificateException
     * @param @throws FileNotFoundException
     * @param @throws NoSuchAlgorithmException
     * @param @throws NoSuchPaddingException
     * @param @throws InvalidKeyException
     * @param @throws IllegalBlockSizeException
     * @param @throws BadPaddingException
     * @return String
     * @throws
     */
    public static String jm(String content)
	    throws UnsupportedEncodingException, CertificateException,
	    FileNotFoundException, NoSuchAlgorithmException,
	    NoSuchPaddingException, InvalidKeyException,
	    IllegalBlockSizeException, BadPaddingException {
	byte[] msg = content.getBytes("GBK"); // 待加解密的消息

	// 用证书的公钥加密
	CertificateFactory cff = CertificateFactory.getInstance("X.509");
	String path = LoadProperties.getKeyPath("tomcat.cer", "classes");
	FileInputStream fis1 = new FileInputStream(path); // 证书文件
	Certificate cf = cff.generateCertificate(fis1);
	PublicKey pk1 = cf.getPublicKey(); // 得到证书文件携带的公钥
	Cipher c1 = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // 定义算法：RSA
	byte[] dataReturn = null;
	c1.init(Cipher.PUBLIC_KEY, pk1);
	// StringBuilder sb = new StringBuilder();
	for (int i = 0; i < msg.length; i += 100) {
	    byte[] doFinal = c1.doFinal(ArrayUtils.subarray(msg, i, i + 100));

	    // sb.append(new String(doFinal,"gbk"));
	    dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
	}

	BASE64Encoder encoder = new BASE64Encoder();

	String afjmText = encoder.encode(dataReturn);

	return afjmText;
    }

    /**
     * @Title: jim
     * @author Eric bjwdong@cn.ibm.com
     * @Description: 解密
     * @param @param dataReturn_r
     * @param @param pa
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     */
    public static String jim(String dataReturn) throws Exception {
	BASE64Decoder decoder = new BASE64Decoder();

	byte[] dataReturn_r = decoder.decodeBuffer(dataReturn);

	final String KEYSTORE_FILE = LoadProperties.getKeyPath("clientok.p12",
		"classes");

	final String KEYSTORE_PASSWORD = "clientok";

	final String KEYSTORE_ALIAS = "clientok";

	KeyStore ks = KeyStore.getInstance("PKCS12");

	FileInputStream fis = new FileInputStream(KEYSTORE_FILE);

	char[] nPassword = null;

	if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")) {
	    nPassword = null;
	} else {
	    nPassword = KEYSTORE_PASSWORD.toCharArray();
	}

	ks.load(fis, nPassword);
	fis.close();

	PrivateKey prikey = (PrivateKey) ks.getKey(KEYSTORE_ALIAS, nPassword);

	Cipher rc2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	rc2.init(Cipher.DECRYPT_MODE, prikey);

	// byte[] rmsg2 = rc2.doFinal(dataReturn_r); // 解密后的数据，不能超过128个字节
	StringBuilder bf_r = new StringBuilder();

	byte[] bs = {};
	for (int i = 0; i < dataReturn_r.length; i += 128) {
	    try {
		byte[] subarray = ArrayUtils.subarray(dataReturn_r, i, i + 128);
		byte[] doFinal = rc2.doFinal(subarray);
		bs = ArrayUtils.addAll(bs, doFinal);
	    } catch (Exception e) {
		System.err.println("提交查询数据有误，请查证！");
	    }
	}

	bf_r.append(new String(bs, inputCharset));

	return bf_r.toString();
    }

    public static Map<String, Object> parseString(String response)
	    throws Exception {// 解析出金查询返回参数
	// TODO Auto-generated method stub

	Document doc = DocumentHelper.parseText(response);
	Element root = doc.getRootElement();
	List attrList = root.elements();
	String file = "";
	Element bo = (Element) attrList.get(5);
	String merchartNo = root.elementText("batchBizid");
	String version = root.elementText("batchVersion");
	String date = root.elementText("batchDate");
	String pcno = root.elementText("batchCurrnum");
	String batchContent = "";
	String detailInfo = root.elementText("detailInfo");

	StringBuffer sb = new StringBuffer();
	if (detailInfo != null) {
	    sb.append(detailInfo);
	} else {
	    for (int i = 0; i < bo.elements().size(); i++) {
		Element item = (Element) bo.elements().get(i);
		sb.append(item.getData() + "|");
	    }
	    batchContent = sb.toString();
	}
	String inputCharset = root.elementText("_input_charset");
	//String key = RoogTWithdrawMConfig.key;
	String signR = root.elementText("sign");
	String signTypeR = root.elementText("signType");
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("batchBizid", merchartNo);
	map.put("batchVersion", version);
	map.put("batchDate", date);
	map.put("batchCurrnum", pcno);
	if (!"".equals(batchContent)) {
	    map.put("batchContent", batchContent);
	} else {
	    map.put("detailInfo", detailInfo);
	}
	map.put("_input_charset", inputCharset);
	Map<String, Object> mapNew = ParaFilter(map); // 除去数组中的空值和签名参数
	String mySign = BuildMysign(mapNew, key);// 生成签名结果
	if (!signR.equals(mySign)) {
        new Exception("查询验签错误，订单号为："+pcno);
	    return null;// 签名错误返回空值
	}
	return map;
    }

    public static List<Map<String,Object>> splistParams(Map<String, Object> map) {// 交易单笔明细字符串拆分为单个参数
							      // list-->map--->单笔交易参数
	Logger log = Logger.getLogger(RTSign.class);
	// 交易明细所有参数名称
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	try {
	    Map<String, Object> mapx = null;
	    String name[] = { "tradeNum", "tradeCardnum", "tradeCardname",
	    	"tradeBranchbank", "tradeSubbranchbank", "tradeAccountname",
	    	"tradeAccounttype", "tradeAmount", "tradeAmounttype",
	    	"tradeRemark", "contractUsercode", "tradeFeedbackcode"};// 交易明细字段名称 tradeReason

	    String batchContent = String.valueOf(map.get("batchContent"));// 交易明细字符串
	    if (batchContent == null || batchContent.length() == 0||batchContent.trim().equals("null")) {
	        batchContent = String.valueOf(map.get("detailInfo"));
	    }
	    String[] parmas = null;
	    try {
	        parmas = batchContent.split(",");// 交易明细字符串用“,"拆分为字符串数组
	    } catch (Exception e) {
		log.error("融宝查询返回参数拆分错误，请查证请求参数是不正确！,订单号为："+map.get("batchCurrnum"));
	        return null;
	    }
	    mapx = new HashMap<String, Object>();

	    for (int i = 0; i < name.length; i++) {// 单个参数装配到map中
	    	
	    		mapx.put(name[i], parmas[i]);
	    }
	    if(parmas.length>name.length){
	    	StringBuffer tradeReason = new StringBuffer("");
	    	for(int j=name.length;j<parmas.length;j++){
	    		tradeReason.append(parmas[j]).append("  ");
	    	}
	    	mapx.put("tradeReason", tradeReason.toString());
	    }
	    list.add(mapx);
	} catch (Exception e) {
	    log.error("融宝查询返回参数拆分错误,订单号为："+map.get("batchCurrnum"),e);
	    return null;
	}
	return list;
    }
    
    public static String getBatchVersion() {
		return batchVersion;
	}

	public static void setBatchVersion(String batchVersion) {
		RTSign.batchVersion = batchVersion;
	}

	public static String getSignType() {
		return signType;
	}

	public static void setSignType(String signType) {
		RTSign.signType = signType;
	}

	public static String getBatchBizid() {
		return batchBizid;
	}

	public static void setBatchBizid(String batchBizid) {
		RTSign.batchBizid = batchBizid;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		RTSign.key = key;
	}

	public static String getInputCharset() {
		return inputCharset;
	}

	public static void setInputCharset(String inputCharset) {
		RTSign.inputCharset = inputCharset;
	}

	public static String getBatchBiztype() {
		return batchBiztype;
	}

	public static void setBatchBiztype(String batchBiztype) {
		RTSign.batchBiztype = batchBiztype;
	}
}
