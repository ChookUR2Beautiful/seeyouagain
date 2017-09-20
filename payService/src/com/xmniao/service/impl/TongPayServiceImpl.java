package com.xmniao.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;




import com.thoughtworks.xstream.XStream;
import com.xmniao.common.MD5;
import com.xmniao.common.PayConstants;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.service.CommonService;
import com.xmniao.service.TongPayService;
import com.xmniao.tonglian.AipgReq;
import com.xmniao.tonglian.AipgRsp;
import com.xmniao.tonglian.CryptInf;
import com.xmniao.tonglian.CryptNoRestrict;
import com.xmniao.tonglian.InfoReq;
import com.xmniao.tonglian.QTDetail;
import com.xmniao.tonglian.QTransRsp;
import com.xmniao.tonglian.Trans;
import com.xmniao.tonglian.TransQueryReq;
import com.xmniao.tonglian.TransRet;



/**
 * 通联支付/退款服务
 * @author ChenBo
 * 
 */
public class TongPayServiceImpl implements TongPayService{
	
	 //初始日志类
	private final Logger log = Logger.getLogger(TongPayServiceImpl.class);
	
	private String refundUrl;//退款url
	
	private String queryUrl;//退款查询url
	
	//private String tranURL="https://113.108.182.3/aipg/ProcessServlet"; //通联测试环境，外网（商户测试使用）
	
	private String tranURL;//通联生产环境（商户上线时使用）
	private String refundVersion;//退款版本
	private String queryVersion;//退款查询版本
	private String payVersion;
	private String signType;	//签名类型
	private String merchantId;//支付-退款商户ID
	private String merchantIdPay;
	private String userName;
	private String userPass;
	private String md5Key;//密钥
	private String pfx;
	private static final SSLHandler simpleVerifier=new SSLHandler();
	private static SSLSocketFactory sslFactory;
	@Autowired
	private CommonService commonService;

	@Override
	public Map<String,String> tongPayRefund(String payId,String orderDate,double refundAmount){
		if(StringUtils.isBlank(payId) || StringUtils.isBlank(orderDate)){
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"通联退款请求参数不能不空");
		}
		
		log.info("[tongPayRefund]payId:"+payId+",orderDate:"+orderDate+",refundAmount:"+refundAmount);
		int amount = yuan2fen(refundAmount);
		
		try{
			Map<String,String> paramMap = new LinkedHashMap<String,String>();
			paramMap.put("version", refundVersion);
			paramMap.put("signType", signType);
			paramMap.put("merchantId", merchantId);
			paramMap.put("orderNo", payId);
			paramMap.put("refundAmount", amount+"");
			paramMap.put("orderDatetime", orderDate);
			String signMsg = getMd5Encode(paramMap);
			paramMap.put("signMsg", signMsg);
			
			String str  =  "";
        	str = tongRefund(paramMap);	
        	log.info("通联退款请求结果："+str);
        	if(str == null || str.trim().equals("")){
        		return returnMap(StateCodeUtil.PR_THIRD_NOT_CONNECT,"提交通联退款请求失败");
        	}
        	
        	Map<String,String> tongMap = splitResult(str);
        	if(StringUtils.isNotBlank(tongMap.get("ERRORCODE"))){
        		log.error("退款失败");
        		return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,StringUtils.isBlank(tongMap.get("ERRORMSG"))?"反馈退款失败":tongMap.get("ERRORMSG"));
        	}
        	
        	//认证签名字符串
        	if(!verifySignMsg(tongMap)){
        		log.error("签名验证不通过");
        		return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"返回的签名字符串验证不通过");
        	}
        	
        	String refundCode = tongMap.get("refundResult");
        	if(StringUtils.isNotBlank(refundCode)){
        		if(refundCode.equals("20")){
        			log.info("退款受理成功");
        			
        		}else{
        			log.error("退款受理失败");
        			return returnMap(StateCodeUtil.PR_REFUND_FAIL,tongMap.get("ERRORMSG"));
        		}
        		
        	}else{
        		log.error("返回的东西错了");
        		return returnMap(StateCodeUtil.PR_THIRD_SERVER_UNKOWN,"出错了");
        	}
        	log.info("本次通联退款的返回值："+str);
		}catch(Exception e){
			log.error("系统执行异常",e);
			e.printStackTrace();
			 return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"系统执行异常");
		}
        return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
        
	}
	/*
	 *  提交退款请求
	 */
	public String tongRefund(Map<String,String> paramMap) throws HttpException, IOException{
		PostMethod method = new PostMethod(this.refundUrl);
		HttpClient client = new HttpClient();
        method.setRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=UTF-8"); 
        List<NameValuePair> nameValuePairList = createNameValuePairList(paramMap);
        NameValuePair[] param = (NameValuePair[]) nameValuePairList.toArray(new NameValuePair[nameValuePairList.size()]);
        method.setRequestBody(param); 
        int result = client.executeMethod(method);

        if(result != HttpStatus.SC_OK){
        	log.error("本次http请求失败");
        	return null;
        }
    
    	InputStream is = method.getResponseBodyAsStream();

    	String all_content=null;  
        try {  
	        all_content =new String();  
	        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();  
	        byte[] str_b = new byte[1024];  
	        int i = -1;  
	        while ((i=is.read(str_b)) > 0) {  
	           outputstream.write(str_b,0,i);  
	        }  
	        all_content = new String(outputstream.toByteArray(),"utf-8");  
	    } catch (Exception e) {  
		   e.printStackTrace();  
	    }  
        
    	return all_content;
//    	BufferedReader reader=new BufferedReader(new InputStreamReader(is));
//    	String tmp=null;
//    	String htmlRet="";
//    	while((tmp=reader.readLine())!=null){
//    	 htmlRet+=tmp+"\r\n";
//    	}
//    	return new String(htmlRet.getBytes(),"utf-8");
	}
	
//	1	TKSUCC0001	退款未受理
//	2	TKSUCC0002	待通联审核
//	3	TKSUCC0003	通联审核通过
//	4	TKSUCC0004	退款冲销
//	5	TKSUCC0005	处理中
//	6	TKSUCC0006	退款成功
//	7	TKSUCC0007	退款失败
//	8	TKSUCC0008	通联审核不通过
	@Override
	public Map<String, String> tongPayRefundQuery(String payId,String refundId, String amount) {
		if(StringUtils.isBlank(payId) || StringUtils.isBlank(amount)){
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"通联退款请求参数不能不空");
		}
		log.info("[tongPayRefundQuery]payId:"+payId+",refundId:"+refundId+",amount:"+amount);

		//支付单号
		String orderNo = payId;
		//金额.分
		String refundAmount = amount;
		//签名
		String signMsg = "";
		
		PostMethod postmethod=null;

		String signTypeMsg="";
		
		try{
			HttpClient httpclient=new HttpClient();

			signMsg="version="+queryVersion+"&signType="+signType+"&merchantId="+merchantId+"&orderNo="+orderNo
					+"&refundAmount="+refundAmount+"&key="+md5Key;
			log.info("待签名:"+signMsg);
			signMsg = MD5.Encode(signMsg,"utf-8").toUpperCase();
			log.info("签名后:"+signMsg);
			postmethod=new PostMethod(queryUrl);
			NameValuePair[] date = { 
					new NameValuePair("merchantId",merchantId),
					new NameValuePair("version",queryVersion),
					new NameValuePair("signType",signType),
					new NameValuePair("orderNo",orderNo),
					new NameValuePair("refundAmount",refundAmount),
					new NameValuePair("signMsg",signMsg)
					};
			postmethod.setRequestBody(date);
			postmethod.setRequestHeader("Content-Type",  
	                "application/x-www-form-urlencoded;charset=UTF-8"); 
			int responseCode=httpclient.executeMethod(postmethod);

			if(responseCode == HttpURLConnection.HTTP_OK){
		    	InputStream is = postmethod.getResponseBodyAsStream();
		    	
		    	BufferedReader reader=new BufferedReader(new InputStreamReader(is));
		    	String tmp=null;
		    	String htmlRet="";
		    	while((tmp=reader.readLine())!=null){
		    	 htmlRet+=tmp+"\r\n";
		    	}
		    	String ret =  new String(htmlRet.getBytes(),"UTF-8");
		    	log.info("查询结果:"+ret);
		    	/*
		    	 * 返回格式
		    	 正常：ret:v2.4|0|109020201503025|1503311505328903|1|20150410101526||TKSUCC0002|201504101024147E697A7223CA3BB64F0012DEBEC7FF43
		    	 不正常：ERRORCODE=011&ERRORMSG=退款订单不存在
		    	 */
		    	
		    	String resultMsg = ret;
				String fileAsString = ""; // 签名信息前的对账文件内容
				String fileSignMsg = ""; // 文件签名信息
				String lines;

				if(resultMsg.indexOf("ERRORCODE=")<0){
					  BufferedReader fileReader = new BufferedReader(new StringReader(resultMsg));
					  // 读取交易结果
					StringBuffer fileBuf = new StringBuffer(); // 签名信息前的字符串	
					List<String> refundOrderList = new ArrayList<String>();
					while ((lines = fileReader.readLine()) != null) {
						if (lines.length() > 0) {
							//将数据放入List中
							refundOrderList.add(lines);
						} 
					}
					fileReader.close();
					
					//取签名源串 
					for(int i=0;i<refundOrderList.size()-2;i++){
						fileBuf.append(refundOrderList.get(i)+"\r\n");
					}
					fileBuf.append(refundOrderList.get(refundOrderList.size()-2));
					fileAsString = fileBuf.toString();
					
					//取签名信息 
					fileSignMsg = refundOrderList.get(refundOrderList.size()-1);
					signTypeMsg = String.valueOf(resultMsg.charAt(5));
					
					if("0".equals(signTypeMsg)){
						//用md5验证签名
						// 验证签名：先对文件内容计算MD5摘要，再将MD5摘要与返回的签名进行对比
						String sourceString = fileAsString+"|"+md5Key;
						String fileMd5 = MD5.Encode(sourceString, "utf-8").toUpperCase();
						if(fileMd5.equals(fileSignMsg)){
							 // 验证签名通过，解析交易明细，开始对账
							String[] resultArray = StringUtils.split(fileAsString, "|");
							String result = resultArray[6];
							
							if(result.equalsIgnoreCase("TKSUCC0006")){
								log.info("refundId:"+refundId+"退款已成功");
								return returnMap(StateCodeUtil.PR_SUCCESS,"");
							}
							else if(result.equalsIgnoreCase("TKSUCC0008") || result.equalsIgnoreCase("TKSUCC0007")){
								log.error("refundId:"+refundId+"退款已失败");
								return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"退款失败");
							}
							else{
								log.info("refundId:"+refundId+"退款在进行中");
							}
						}else{
							// 验证签名不通过，丢弃对账文件
							log.error("验证签名不通过");
						}
					}else if("1".equals(signTypeMsg)){
						log.error("统一使用MD5签名验证！");
//					        String fileMd5String = SecurityUtil.MD5Encode(fileAsString);
//							String certPath="";
//							if(postUrl.indexOf("service.allinpay.com")>0){
//								certPath="/opt/conf/TLCert-prod.cer";//生产证书路径
//							}else{
//								certPath="/opt/conf/TLCert-test.cer"; //测试证书路径
//							}
//							isVerified = SecurityUtil.verifyByRSA(certPath, fileMd5String.getBytes(), Base64.decode(fileSignMsg));
//							if (isVerified) {
//								// 验证签名通过，解析交易明细，开始对账
//								System.out.println("验证签名通过");
//								viewMsg=fileAsString+"\r\n"+fileSignMsg;
//							} else {
//								// 验证签名不通过，丢弃对账文件
//								System.out.println("验证签名不通过");
//								viewMsg="验证签名不通过";
//				
//							}
					}else{
						log.error("未知签名方式:"+signTypeMsg);
					}
				}else{
					log.error("退款失败："+resultMsg);
				}
		   }else{
			   log.error("本次查询通联退款状态失败，");
			   return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"本次查询通联退款状态失败");
		   }
			
		}catch(Exception e){
			log.error("查询异常"+e);
			return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
		}finally{
			postmethod.releaseConnection();
		}		
        return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
	}
	
	
	/** 
	 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private List<NameValuePair> createNameValuePairList(Map<String,String> params){
			
		List<NameValuePair> pair = new ArrayList<NameValuePair>();
		
			List<String> keys = new ArrayList<String>(params.keySet());
			log.info("Keys"+keys);
			String key="";
			String value="";
			for (int i = 0; i <keys.size(); i++) {
				key=(String) keys.get(i);
				value = (String) params.get(key);
				
				pair.add(new NameValuePair(key,value));
				//System.out.println(key+"="+value);
			}
			return pair;
	}
	
	//将通联返回的字符串分解，将内容保存到Map当中
	public Map<String,String> splitResult(String str){
		Map<String,String> splitMap = new HashMap<String,String>();
		if(str.trim().endsWith("&")){
			str = str.substring(0, str.length()-1-"&".length());
		}
		String[] strArray = str.split("&");
		for(String subStr:strArray){
			if(subStr.endsWith("=")){
				subStr += " ";
			}
			String[] keyValue = subStr.split("=");
			splitMap.put(keyValue[0].trim(), keyValue.length<2?"":keyValue[1].trim());
		}
		log.info("分解后的Map:"+splitMap);
		return splitMap;
	}
	
	/**
	 * 生成MD5签名字符串
	 * @param paramMap
	 * @return
	 */
	public  String getMd5Encode(Map<String,String> paramMap){

		String source = getParams(paramMap);
		String md5Encode = MD5.Encode(source, "utf-8").toUpperCase();
//		System.out.println("签名后的数据："+md5Encode);
		return md5Encode;
	}
	
	public String getParams(Map<String,String> paramMap){
		List<String> keys = new ArrayList<String>(paramMap.keySet());
//		System.out.println("Keys"+keys);
		StringBuilder prestr = new StringBuilder();
		String key="";
		String value="";
		for (int i = 0; i <keys.size(); i++) {
			key=(String) keys.get(i);
			value = (String) paramMap.get(key);
			if(StringUtils.isBlank(value)){
				continue;
			}
			
			prestr.append(key+"="+value+"&");
//			System.out.println(key+"="+value);
		}
		String  source = prestr.append("key=").append(md5Key).toString();
		log.info("构建待签名的数据："+source);
		
		return source;
	}
	

	/**
	 * 验证签名字符串
	 * @param signMap
	 * @return
	 */
	public boolean verifySignMsg(Map<String,String> signMap){
		Map<String,String> paramMap = new LinkedHashMap<String,String>();
		paramMap.put("merchantId", signMap.get("merchantId").toString());
		paramMap.put("version", signMap.get("version").toString());
		paramMap.put("signType", signMap.get("signType").toString());
		paramMap.put("orderNo", signMap.get("orderNo").toString());
		paramMap.put("orderAmount", signMap.get("orderAmount").toString());
		paramMap.put("orderDatetime", signMap.get("orderDatetime").toString());
		paramMap.put("refundAmount", signMap.get("refundAmount").toString());
		paramMap.put("refundDatetime", signMap.get("refundDatetime").toString());
		paramMap.put("refundResult", signMap.get("refundResult").toString());
		if(StringUtils.isNotBlank(signMap.get("errorCode"))){
			paramMap.put("errorCode", signMap.get("errorCode"));
		}
		paramMap.put("returnDatetime", signMap.get("returnDatetime").toString());
		
		String verifyString = getMd5Encode(paramMap);
		if(verifyString.equals(signMap.get("signMsg"))){
			return true;
		}
		
		return false;
	}
	
	public Map<String,String> returnMap(String code,String msg){
		return this.returnMap(code,msg,"");
	}
	
	//返回Map结果
	private Map<String,String> returnMap(String code,String msg,String response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("code", code);
		resultMap.put("Msg",msg);
		resultMap.put("response",response);
		return resultMap;
	}
	public static void main(String[] args) throws Exception {
//		TongPayService tongPayService = new TongPayServiceImpl();
//		Map<String,String> resultMap = new HashMap<String,String>();
//			resultMap = tongPayService.tongPayRefund("340822201502060003", "20150206142909", 305.00);
//			System.out.println(resultMap);
		
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("id", "333334234");
//		map.put("province", "广东省");
//		map.put("cityname", "深圳市");
//		map.put("bank", "中国工商银行");
//		map.put("bankname", "梅林支行");
//		map.put("ispublic", "1");
//		map.put("account", "6212264000022804666");
//		map.put("amount", 0.01);
//		map.put("fullname", "曾浩明");
//		map.put("userType", 1);
//		
//		
//		
//		
//		 FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[] {"WebRoot/WEB-INF/pay-context.xml","WebRoot/WEB-INF/pay-service.xml"}, true);
//	     context.start();
//	     TongPayService tongPayService = context.getBean(TongPayServiceImpl.class);
//	     tongPayService.tongPay(map);
		new TongPayServiceImpl().tongPayRefundQuery("2", "2","2");
		//testQuery();
	}
	

	public String getParam(Map<String,String> map){
		
		return "";
				
	}
	
	
	public Map<String, String> tongPay(Map<String, Object> paramMap){

		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("RespDesc", "");

		// 整理参数，转化为xml字符串
		String xml = getPayReqXml(paramMap);
		String strRnt = "";
		String IDD_STR = "";
		IDD_STR = "<SIGNED_MSG></SIGNED_MSG>";
		String strMsg = xml.replaceAll(IDD_STR, "");
		// 得到签名
		strRnt = getSign(strRnt, strMsg);
		//log.info("签名:" + strRnt);
		strRnt = xml.replaceAll(IDD_STR, "<SIGNED_MSG>" + strRnt
				+ "</SIGNED_MSG>");
		log.info("请求XML:" + strRnt);
		// 发送请求
		log.info("通联支付发送请求-------------------------");
		long sdate = System.currentTimeMillis();
		String retXml = send(tranURL, strRnt);
		//log.info("响应XML:" + retXml);
		long edate = System.currentTimeMillis();
		long ydate = edate - sdate;
		log.info("通联代付发送完时间-----------："+edate);
		log.info("通联代付响应时间----------："+ydate);
		XStream xs1 = new XStream();
		xs1.alias("AIPG", AipgRsp.class);
		xs1.alias("INFO", InfoReq.class);
		xs1.alias("TRANSRET", TransRet.class);
		xs1.addImplicitCollection(AipgRsp.class, "trxData");
		AipgRsp aipgrsp = (AipgRsp) xs1.fromXML(retXml);

		// int status = 2;
		String message = "";
		if ("0000".equals(aipgrsp.getINFO().getRET_CODE())) {
			TransRet ret = (TransRet) aipgrsp.getTrxData().get(0);
			log.info("通联交易结果：" + ret.getRET_CODE() + ":" + ret.getERR_MSG());
			if ("0000".equals(ret.getRET_CODE())) {
				// status = 1;
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
				resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);
				message = "提现成功";
				log.info("通联提现成功！");
			} else {
				resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + ret.getERR_MSG());
				message = "通联交易失败原因：" + ret.getERR_MSG();
				log.info(message);
			}
		} else if ("2000".equals(aipgrsp.getINFO().getRET_CODE())
				|| "2001".equals(aipgrsp.getINFO().getRET_CODE())
				|| "2003".equals(aipgrsp.getINFO().getRET_CODE())
				|| "2005".equals(aipgrsp.getINFO().getRET_CODE())
				|| "2007".equals(aipgrsp.getINFO().getRET_CODE())
				|| "2008".equals(aipgrsp.getINFO().getRET_CODE())) {
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
			resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);
			log.info("通联交易处理中或者不确定状态，需要在稍后5分钟后进行交易结果查询（轮询）");
		} else if (aipgrsp.getINFO().getRET_CODE().startsWith("1")) {
			String errormsg = aipgrsp.getINFO().getERR_MSG() == null ? "连接异常，请重试"
					: aipgrsp.getINFO().getERR_MSG();
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_ERROR);
			resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + errormsg);
			message = "通联提交失败：" + errormsg;
			log.info(message);
			return resultMap;
		} else {
			TransRet ret = (TransRet) aipgrsp.getTrxData().get(0);
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
			resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + ret.getERR_MSG());
			message = "通联交易失败，失败原因：" + ret.getERR_MSG();
			log.info(message);
		}
		
		return resultMap;
	}
	
	private String getSign(String strRnt, String strMsg) {
		String pfxPath= TongPayServiceImpl.class.getClassLoader().getResource(pfx).getPath();
		BouncyCastleProvider provider = new BouncyCastleProvider();
		BouncyCastleProvider p=new BouncyCastleProvider();
		Security.addProvider(p);
		
		CryptInf crypt=new CryptNoRestrict("GBK",provider);
		
		try {
			if (crypt.SignMsg(strMsg, pfxPath, userPass)) {
				String signedMsg = crypt.getLastSignMsg();
				strRnt = signedMsg;
			} else {
				throw new Exception("签名失败");
			}

		} catch (Exception e) {
			log.error("通联签名异常！",e);
		}
		return strRnt;
	}

	private String getPayReqXml(Map<String, Object> map) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		//金额
		String amt = new BigDecimal(String.valueOf(map.get("amount"))).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_DOWN).toString();
		//批次号
		String batchNo = map.get("id")+"_"+ map.get("userType");
		String xml="";
		AipgReq aipg=new AipgReq();
		
		InfoReq info=new InfoReq();
		info.setTRX_CODE("100014");//交易代码
		info.setREQ_SN(batchNo);//交易批次号
		info.setUSER_NAME(userName);//用户名
		info.setUSER_PASS(userPass);//用户密码
		info.setLEVEL("5");//处理级别
		info.setDATA_TYPE("2");//数据格式
		info.setVERSION(payVersion );//版本号
		
		aipg.setINFO(info);
		Trans trans=new Trans();
		trans.setBUSINESS_CODE("09900");//业务代码
		trans.setMERCHANT_ID(merchantIdPay);//商户代码
		trans.setSUBMIT_TIME(df.format(new Date()));//提交时间
		
		if(map.get("bankCode")==null||map.get("bankCode").equals("null")){
			trans.setBANK_CODE("");
		}else{
			trans.setBANK_CODE(map.get("bankCode")+"");//银行代码   105建设银行 存折
		}
		
		
		trans.setACCOUNT_TYPE("00");//账号类型
		trans.setACCOUNT_NO(String.valueOf(map.get("account")));//账号
		trans.setACCOUNT_NAME(String.valueOf(map.get("fullname")));//账号名
		trans.setACCOUNT_PROP(String.valueOf(map.get("ispublic")));//账号属性  0私人，1公司
		trans.setAMOUNT(amt);//金额 单位分
		trans.setCURRENCY("CNY");//
		aipg.addTrx(trans);
		
		XStream xs=new XStream();
	    xs.alias("AIPG", AipgReq.class); 
		xs.alias("INFO", InfoReq.class);
		xs.alias("TRANS", Trans.class);
		xs.alias("TRANSRET", TransRet.class);
		xs.addImplicitCollection(AipgReq.class, "trxData");
		xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>"+xs.toXML(aipg);
		xml=xml.replaceAll("__", "_");
		return xml;
	}
	
	
	

	public AipgRsp queryTradeNew(String querySN) {

		String xml = "";
		AipgReq aipg = new AipgReq();
		InfoReq info = new InfoReq();
		info.setTRX_CODE("200004");// 交易代码
		info.setREQ_SN(merchantIdPay + "-"
				+ String.valueOf(System.currentTimeMillis()));// 交易批次号
		info.setUSER_NAME(userName);// 用户名
		info.setUSER_PASS(userPass);// 用户密码
		info.setLEVEL("5");// 处理级别
		info.setDATA_TYPE("2");// 数据格式
		info.setVERSION(payVersion);// 版本号
		aipg.setINFO(info);

		TransQueryReq dr = new TransQueryReq();
		aipg.addTrx(dr);
		dr.setMERCHANT_ID(merchantIdPay);
		dr.setQUERY_SN(querySN);
		dr.setSTATUS(2);
		dr.setTYPE(1);

		XStream xs = new XStream();
		xs.alias("AIPG", AipgReq.class);
		xs.alias("INFO", InfoReq.class);
		xs.alias("QTRANSREQ", TransQueryReq.class);
		xs.addImplicitCollection(AipgReq.class, "trxData");
		xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + xs.toXML(aipg);
		xml = xml.replaceAll("__", "_");

		String strRnt = "";
		String IDD_STR = "";
		IDD_STR = "<SIGNED_MSG></SIGNED_MSG>";
		String strMsg = xml.replaceAll(IDD_STR, "");
		// 得到签名
		strRnt = getSign(strRnt, strMsg);
		//log.info("签名:" + strRnt);
		strRnt = xml.replaceAll(IDD_STR, "<SIGNED_MSG>" + strRnt
				+ "</SIGNED_MSG>");
		//log.info("请求XML:" + strRnt);
		// 发送请求
		log.info("通联支付发送请求-------------------------");
		String retXml;

		retXml = send(tranURL, strRnt);

		//log.info("响应XML:" + retXml);

		XStream xs1 = new XStream();
		xs1.alias("AIPG", AipgRsp.class);
		xs1.alias("INFO", InfoReq.class);
		xs1.alias("QTRANSRSP", QTransRsp.class);
		xs1.alias("QTDETAIL", QTDetail.class);
		xs1.addImplicitCollection(QTransRsp.class, "details");
		xs1.addImplicitCollection(AipgRsp.class, "trxData");
		AipgRsp aipgrsp = (AipgRsp) xs1.fromXML(retXml);
		return aipgrsp;
	}
	
	
	
	
	public String send(String strUrl, String xml){
		

		OutputStream reqStream = null;
		InputStream resStream = null;
		URLConnection request = null;
		String respText = null;
		byte[] postData;
		try {
			postData = xml.getBytes("GBK");

			URL url = new URL(strUrl);
			request = url.openConnection();
			request.setDoInput(true);
			request.setDoOutput(true);
			if (request instanceof HttpsURLConnection)
			{
				HttpsURLConnection httpsConn = (HttpsURLConnection) request;
				httpsConn.setRequestMethod("POST");
				httpsConn.setSSLSocketFactory(getSSLSF());
				httpsConn.setHostnameVerifier(simpleVerifier);
			}
			else if (request instanceof HttpURLConnection)
			{
				HttpURLConnection httpConn = (HttpURLConnection) request;
				httpConn.setRequestMethod("POST");
			}
			

			request.setRequestProperty("Content-type", "application/tlt-notify");
			request.setRequestProperty("Content-length",
					String.valueOf(postData.length));
			request.setRequestProperty("Keep-alive", "false");

			reqStream = request.getOutputStream();
			reqStream.write(postData);
			reqStream.close();

			ByteArrayOutputStream ms = null;
			resStream = request.getInputStream();
			ms = new ByteArrayOutputStream();
			byte[] buf = new byte[4096];
			int count;
			while ((count = resStream.read(buf, 0, buf.length)) > 0) {
				ms.write(buf, 0, count);
			}
			resStream.close();
			respText = new String(ms.toByteArray(), "GBK");
		} catch (Exception ex) {
			log.error("通联请求异常！",ex);
			if(ex.getCause() instanceof ConnectException||ex instanceof ConnectException){
				log.info("请求链接中断，请做交易结果查询，以确认上一笔交易是否已被通联受理，避免重复交易");
			}
		} finally {
			try {
				if (reqStream != null)
					reqStream.close();
				if (resStream != null)
					resStream.close();
			} catch (Exception ex) {
			}
		}
		return respText;
	}
	
	
	
	public synchronized SSLSocketFactory getSSLSF() throws Exception
	{
		if(sslFactory!=null) return sslFactory; 
		SSLContext sc = SSLContext.getInstance("SSLv3");
		sc.init(null, new TrustManager[]{simpleVerifier}, null);
		sslFactory = sc.getSocketFactory();
		return sslFactory;
	}
	
	private static class SSLHandler implements X509TrustManager,HostnameVerifier
	{	
		private SSLHandler()
		{
		}
		
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean verify(String arg0, SSLSession arg1)
		{
			return true;
		}
	}
	
	 //元->分
	 public int yuan2fen(double orderAmount){
	        BigDecimal b1 = new BigDecimal(Double.toString(orderAmount));
	        BigDecimal b2 = new BigDecimal(Double.toString(100));
	       return b1.multiply(b2).intValue();
	 }
	 
	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}

	public String getTranURL() {
		return tranURL;
	}

	public void setTranURL(String tranURL) {
		this.tranURL = tranURL;
	}

	public String getQueryVersion() {
		return queryVersion;
	}

	public void setQueryVersion(String queryVersion) {
		this.queryVersion = queryVersion;
	}

	public String getPayVersion() {
		return payVersion;
	}

	public void setPayVersion(String payVersion) {
		this.payVersion = payVersion;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantIdPay() {
		return merchantIdPay;
	}

	public void setMerchantIdPay(String merchantIdPay) {
		this.merchantIdPay = merchantIdPay;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getMd5Key() {
		return md5Key;
	}

	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}

	public String getPfx() {
		return pfx;
	}

	public void setPfx(String pfx) {
		this.pfx = pfx;
	}
	public String getRefundVersion() {
		return refundVersion;
	}
	public void setRefundVersion(String refundVersion) {
		this.refundVersion = refundVersion;
	}


}
