package com.xmniao.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.HttpRequestSimple;
import com.xmniao.common.LianLianBankCodeEnum;
import com.xmniao.common.MD5;
import com.xmniao.common.PayConstants;
import com.xmniao.common.RSAUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.service.LlPayService;

public class LlPayServiceImpl implements LlPayService {
	 //初始日志类
	private final Logger log = Logger.getLogger(LlPayServiceImpl.class);
	
	private String partner;//"201502121000214502";
	private String signType;//MD5
	private String signKey;//"Q2Pk614lsxuoBJMcntSQCL7u0dtl3AVDKWQ4";
	
	@Resource(name = "notifyServiceUrl")
	private String notifyUrl;//http://szdev.xmniao.com:8242/payService/llRefundNotify
	private String refundUrl;//https://yintong.com.cn/traderapi/refund.htm
	private String refundQueryUrl;
	private String payUrl;
	private String payQueryUrl;
	
	private String rsaPriKey;
	private SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final String XMN_PAY_URL ="/llNofity";
	
    private static final String XMN_REFUND_URL = "/xmn/llRefundNotify";
    
    private static final String FRESH_REFUND_URL = "/fresh/llRefundNotify";
	@Override
	public Map<String, String> llPayRefund(String refundId, String orderId,	double amount, String payDate, String refundDate,int serviceType) {

		log.info("[llPayRefund]refundId:"+refundId+",orderId:"+orderId+",amount:"+amount+",payDate"+payDate);
		String refundAmount = String.format("%.2f",amount);
		
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("oid_partner", partner.trim());
		paramMap.put("no_refund", refundId.trim());
		paramMap.put("dt_refund", refundDate);
		paramMap.put("money_refund", refundAmount);
		paramMap.put("no_order", orderId);	//原商户订单号
		paramMap.put("dt_order", payDate);	//原商户订单时间
		paramMap.put("oid_paybill", orderId); //原连连支付号
		paramMap.put("notify_url", generateActionUrl(serviceType));
		paramMap.put("sign_type", signType);
		
		paramMap.put("sign", genSign(paramMap,signType));
		
		try{
	        HttpRequestSimple httpclent = new HttpRequestSimple();
	        String resJson = httpclent.postSendHttp(refundUrl,JSON.toJSONString(paramMap));
	        log.info("请求结果："+resJson);
	        
	        @SuppressWarnings("unchecked")
			Map<String,String> resMap =(Map<String,String>) JSON.parse(resJson);
	        log.info("同步返回数据为:"+resMap);
	        if(resMap.get("ret_code").equalsIgnoreCase("0000")){
		         String	resState = resMap.get("sta_refund");
		         if(resState.equals("0") || resState.equals("1")){
		        	 log.info("请求退款处理中");
		        	 return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
		         }
		         else if(resState.equals("2")){
		        	 log.info("退款成功");
		        	 return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
		         }
		         else if(resState.equals("3")){
		        	 log.error("退款失败");
		        	 return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,resMap.get("ret_msg"));
		         }
	        }
	        else{
	        	log.error("连连退款，请求请求失败");
	        	return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,resMap.get("ret_msg"));
	        }
		}catch(Exception e){
			log.error("程序运行异常",e);
		}
		return returnMap(StateCodeUtil.PR_REFUND_FAIL,"连连退款失败");
	
	}
	
	
	/**
	 * 退款查询接口
	 * @param refundId	//商户退款流水号
	 * @param llRefundNo //连连退款流水号
	 * @param payDate	//商户退款时间
	 * @return
	 */
	public Map<String, String> llPayRefundQuery(String refundId,String llRefundNo, String payDate){
		log.info("[llPayRefundQuery]refundId:"+refundId+",payDate"+payDate+",llRefundNo:"+llRefundNo);
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("oid_partner", partner.trim());
		paramMap.put("no_refund", refundId.trim());
		paramMap.put("dt_refund", payDate.trim());
		paramMap.put("oid_refundno", llRefundNo.trim());
		paramMap.put("sign_type", signType);
		
		paramMap.put("sign", genSign(paramMap,signType));
		
		try{
	        HttpRequestSimple httpclent = new HttpRequestSimple();
	        String resJson = httpclent.postSendHttp(refundQueryUrl,JSON.toJSONString(paramMap));
	        log.info("请求结果："+resJson);
	        
	        @SuppressWarnings("unchecked")
			Map<String,String> resMap =(Map<String,String>) JSON.parse(resJson);
	        log.info("同步返回数据为:"+resMap);
	        if(resMap.get("ret_code").equalsIgnoreCase("0000")){
		         String	resState = resMap.get("sta_refund");
		         if(resState.equals("0") || resState.equals("1")){
		        	 log.info("请求退款处理中");
		        	 return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
		         }
		         else if(resState.equals("2")){
		        	 log.info("退款成功");
		        	 return returnMap(StateCodeUtil.PR_SUCCESS,"退款成功");
		         }
		         else if(resState.equals("3")){
		        	 log.error("退款失败");
		        	 return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,resMap.get("ret_msg"));
		         }
	        }
	        else{
	        	log.error("连连退款，查询请求失败");
	        	return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,resMap.get("ret_msg"));
	        }
		}catch(Exception e){
			log.error("程序运行异常",e);
		}
		return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"连连退款失败");
	}
	
	public boolean verifySign(Map<String,String> map){
		String genSignMsg = genSign(map,map.get("sign_type"));
		return genSignMsg.equalsIgnoreCase(map.get("sign"));
	}
	
	public String genSign(Map<String,String> map,String type){
		if(type.equalsIgnoreCase("MD5")){
			return MD5.Encode(getSignData(map)+"&key="+signKey);
		}
		else if(type.equalsIgnoreCase("RSA")){
			System.out.println("RSA加密:"+rsaPriKey+"\r\n待签名串："+getSignData(map));
			return RSAUtil.sign(getSignData(map), rsaPriKey,"utf-8");
		}
		return "";
	}
	
    
	/*
	 * 组合待签名字符串
	 */
    public static String getSignData(Map<String,String> map)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = map.get(key);
            // 空串不参与签名
            if (isnull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        System.out.println("signSrc:"+signSrc);
        return signSrc;
    }
    
    public static boolean isnull(String str)
    {
        if (null == str || str.equalsIgnoreCase("null") || str.trim().equals(""))
        {
            return true;
        } else
            return false;
    }
    
	public Map<String,String> returnMap(String code,String msg){
		return this.returnMap(code,msg,"");
	}
	//返回的Map数据
	public Map<String,String> returnMap(String code,String msg,String response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("code", code);
		resultMap.put("Msg",msg);
		resultMap.put("response",response);
		return resultMap;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}
	
	
	public String getRefundQueryUrl() {
		return refundQueryUrl;
	}

	public void setRefundQueryUrl(String refundQueryUrl) {
		this.refundQueryUrl = refundQueryUrl;
	}
	
	private String generateActionUrl(int serviceType){
		String nUrl ="";
		if(0==serviceType){
			nUrl = notifyUrl+XMN_REFUND_URL;
		}else if(1 == serviceType){
			nUrl = notifyUrl+FRESH_REFUND_URL;
		}
		return nUrl;
	}
	public static void main(String[] args) {
//		LlPayServiceImpl ll = new LlPayServiceImpl();
//		ll.setPartner("201502121000214502");
//		ll.setSignType("MD5");
//		ll.setSignKey("Q2Pk614lsxuoBJMcntSQCL7u0dtl3AVDKWQ4");
//		ll.setRefundQueryUrl("https://yintong.com.cn/traderapi/refundquery.htm");
//		//Map<String,String> ret = ll.llPayRefundQuery("", "9015052569290881","");
//		//Map<String,String> ret = ll.llPayRefundQuery("1505251134451307", "","20150525113445");
//		Map<String,String> ret = ll.llPayRefundQuery("1505251134451307", "","20150525113445");//20150525113445
//		System.out.println(ret);
//		
//		long s=System.currentTimeMillis();
//		String xxx= ResourceBundle.getBundle("LLPayBankCode").getString(String.valueOf("中国工商银行"));
//    	long t = System.currentTimeMillis()-s;
//    	System.out.println("共耗时："+t+",code:"+xxx);
		String sign_src = "no_order=10000000&oid_partner=201502121000214502&sign_type=RSA&type_dc=1";
		String rsaPriKey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMlGNh/WsyZSYnQcHd9t5qUkhcOhuQmozrAY9DM4+7fhpbJenmYee4chREW4RB3m95+vsz9DqCq61/dIOoLK940/XmhKkuVjfPqHJpoyHJsHcMYy2bXCd2fI++rERdXtYm0Yj2lFbq1aEAckciutyVZcAIHQoZsFwF8l6oS6DmZRAgMBAAECgYAApq1+JN+nfBS9c2nVUzGvzxJvs5I5qcYhY7NGhySpT52NmijBA9A6e60Q3Ku7vQeICLV3uuxMVxZjwmQOEEIEvXqauyYUYTPgqGGcwYXQFVI7raHa0fNMfVWLMHgtTScoKVXRoU3re6HaXB2z5nUR//NE2OLdGCv0ApaJWEJMwQJBAPWoD/Cm/2LpZdfh7oXkCH+JQ9LoSWGpBDEKkTTzIqU9USNHOKjth9vWagsR55aAn2ImG+EPS+wa9xFTVDk/+WUCQQDRv8B/lYZD43KPi8AJuQxUzibDhpzqUrAcu5Xr3KMvcM4Us7QVzXqP7sFc7FJjZSTWgn3mQqJg1X0pqpdkQSB9AkBFs2jKbGe8BeM6rMVDwh7TKPxQhE4F4rHoxEnND0t+PPafnt6pt7O7oYu3Fl5yao5Oh+eTJQbyt/fwN4eHMuqtAkBx/ob+UCNyjhDbFxa9sgaTqJ7EsUpix6HTW9f1IirGQ8ac1bXQC6bKxvXsLLvyLSxCMRV/qUNa4Wxu0roI0KR5AkAZqsY48Uf/XsacJqRgIvwODstC03fgbml890R0LIdhnwAvE4sGnC9LKySRKmEMo8PuDhI0dTzaV0AbvXnsfDfp" ;
		System.out.println(RSAUtil.sign(sign_src, rsaPriKey,"utf-8"));
	}

	/**
	 * 代付申请
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> withdrawMoney(Map<String, Object> paramMap) throws Exception{
		log.info("[lianlian:withdrawMoney]"+paramMap);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL);
		
		String info_order=String.valueOf(paramMap.get("purpose"));
		if(info_order==null ||info_order.trim().length()==0){
			info_order="连连提现";
		}

    	Map<String,String> reqMap = new HashMap<String,String>();
    	reqMap.put("api_version","1.2");
    	reqMap.put("oid_partner",partner);
    	reqMap.put("sign_type","RSA");
    	reqMap.put("acct_name",String.valueOf(paramMap.get("fullname")));
    	reqMap.put("card_no",String.valueOf(paramMap.get("account")));
    	reqMap.put("bank_code",LianLianBankCodeEnum.getCodeByName(String.valueOf(paramMap.get("bank"))));
    	reqMap.put("no_order",String.valueOf(paramMap.get("no_order")));
    	reqMap.put("dt_order",sdf.format(new Date()));
    	reqMap.put("money_order",String.valueOf(paramMap.get("amount")));
    	reqMap.put("flag_card",String.valueOf(paramMap.get("ispublic")));
    	reqMap.put("info_order",info_order);
    	reqMap.put("notify_url",notifyUrl+XMN_PAY_URL);
    	reqMap.put("sign",genSign(reqMap,"RSA"));
        String reqJson = JSON.toJSONString(reqMap);
        log.info("请求json:"+reqJson);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.postSendHttp(payUrl,reqJson);

        log.info("结果报文为:" + resJson);
        Map<String, String> resMap = (Map<String, String>) JSON.parse(resJson.toString());
        if(resMap!=null){
        	if(resMap.get("ret_code").equals("0000")){
        		System.out.println("代付申请已提交,正在处理中...");
        		resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
        		resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);
         	}else{
        		System.out.println("代付申请提交失败,失败原因:"+resMap.get("ret_msg"));
           		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
        		resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL + "," + resMap.get("ret_msg"));
        	}
        }else{
       		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
    		resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL);
	
        }
        return resultMap;
	}
	
	/**
	 * 代付查询
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> withdrawMoneyQuery(String orderId) throws Exception{

		log.info("[lianlian:withdrawMoneyQuery]"+orderId);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
		resultMap.put("Message", PayConstants.WITHDRAW_MSG_PROCESS);
		
    	Map<String,String> reqMap = new HashMap<String,String>();
    	reqMap.put("oid_partner",partner);
    	reqMap.put("sign_type","RSA");
    	reqMap.put("type_dc","1");
    	reqMap.put("no_order",String.valueOf(orderId));
    	reqMap.put("sign",genSign(reqMap,"RSA"));
        String reqJson = JSON.toJSONString(reqMap);
        log.info("请求json:"+reqJson);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.postSendHttp(payQueryUrl,reqJson);

        log.info("结果报文为:" + resJson);
        Map<String, String> resMap = (Map<String, String>) JSON.parse(resJson.toString());
        if(resMap!=null){
        	if(resMap.get("ret_code").equals("0000")){
        		log.info("代付查询操作成功，");
        		String status = resMap.get("result_pay");
        		if(status != null && status.equals("SUCCESS")){
        			log.info("订单代付成功");
        			resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
            		resultMap.put("Message", PayConstants.WITHDRAW_MSG_SUCCESS);
        		}else if(status != null && status.equals("FAILURE")){
        			log.info("订单代付失败，失败原因："+resMap.get("info_order"));
        			resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
            		resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL+resMap.get("info_order"));
        		}else{
        			log.info("订单代付正在处理中。。。");
        		}
        		
         	}else{
         		log.info("代付查询操作失败,失败原因:"+resMap.get("ret_msg"));
        	}
        }else{
        	log.info("代付查询操作失败,失败原因:");
        }
        return resultMap;
	}


	public String getPayUrl() {
		return payUrl;
	}


	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}


	public String getRsaPriKey() {
		return rsaPriKey;
	}


	public void setRsaPriKey(String rsaPriKey) {
		this.rsaPriKey = rsaPriKey;
	}


	public String getPayQueryUrl() {
		return payQueryUrl;
	}


	public void setPayQueryUrl(String payQueryUrl) {
		this.payQueryUrl = payQueryUrl;
	}
	
}
