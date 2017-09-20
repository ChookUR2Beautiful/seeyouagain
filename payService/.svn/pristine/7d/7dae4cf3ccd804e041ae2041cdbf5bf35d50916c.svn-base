package com.xmniao.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xmniao.common.MD5;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.service.ReaPalService;

public class ReaPalServiceImpl implements ReaPalService {
	
	 //初始日志类
	private final Logger log = Logger.getLogger(ReaPalServiceImpl.class);
	
	// 合作身份者ID，由纯数字组成的字符串
	private String merchantId;
	
	//交易安全检验码，由数字和字母组成的32位字符串
	private String merchantKey;

	//返回验证订单地址
	private String verifyUrl;

	//退款请求地址
	private String refundUrl;
	
	// 字符编码格式 目前支持 gbk 或 utf-8
	private String charset;
	
	// 签名方式 不需修改
	private String signType;
	
	private String REFUND_SUCCESS = "T";
	
	private String REFUND_FAIL ="F";
	
	//0.00
	private final BigDecimal ZERO = new BigDecimal("0.00");  
	
	@Override
	public Map<String, String> reaPalRefund(String refundId, String payId,
			double orderAmount, String refundNote){
		log.info("[reaPalRefund]refundId:"+refundId+",orderId:"+payId+",orderAmount:"+orderAmount+",refundNote:"+refundNote);
		/*---------------------------------------------------------------------------------------------*/
		Map<String,String> resultMap = new HashMap<String,String>();
		if(StringUtils.isBlank(refundId) || StringUtils.isBlank(payId)){
			log.error("提交订单号与退款流水号不能为空!");
			return returnMap(StateCodeUtil.PR_REFUND_FAIL,"提交订单号与退款流水号不能为空!");
		}

		String sign_type=this.signType;

		Map<String,Object> params=new HashMap<String,Object>();
		params.put("merchant_ID",this.merchantId);
		params.put("charset",this.charset);
		params.put("orig_order_no",payId);
		params.put("order_no",refundId);
		params.put("amount",String.format("%.2f",orderAmount));
		params.put("note",/*refundNote.trim()*/"");

		try{
			String sign=BuildMysign(params,this.merchantKey);
			log.info("签名数据：\r\n"+sign);
			String url=this.refundUrl;
			String paramstr=CreateLinkString(params).append("&sign=").append(sign).append("&sign_type=").append(sign_type).toString();
			
			log.info("生成的融宝退款请求url:\r\n"+paramstr);
			
			HashMap returnxml=GetMessage(url+"?"+paramstr);
			log.info("解析后的结果：\r\n"+returnxml);
			String isSuccess = (String) returnxml.get("is_success");
			if(isSuccess.equalsIgnoreCase(REFUND_SUCCESS)){
				resultMap = returnMap(StateCodeUtil.PR_SUCCESS,"");
			}
			else{
				String errorMsg = (String) returnxml.get("result_code");
				if(errorMsg.equals("OUT_TRADE_REPEAT")){
					//反馈重复退款，已经退过款了，也算退款成功
					resultMap = returnMap(StateCodeUtil.PR_SUCCESS,"OUT_TRADE_REPEAT");
				}else{
					resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,errorMsg==null?"退款失败":"失败代码为:"+errorMsg);				
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("融宝退款过程中，系统执行异常",e);
			resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"系统执行异常");
		}
		/*---------------------------------------------------------------------------------------------*/
		
		return resultMap;
	}

	
	/** 
	 * 功能：生成签名结果
	 * @param sArray 要签名的数组
	 * @param key 安全校验码
	 * @return 签名结果字符串
	 */
	private  String BuildMysign(Map sArray, String key) {
		if(sArray!=null && sArray.size()>0){
			StringBuilder prestr = CreateLinkString(sArray);  //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
			String temp = prestr.append(key).toString();
			return MD5.Encode(temp);//把拼接后的字符串再与安全校验码直接连接起来,并且生成加密串
		}
		return null;
	}
	
	/** 
	 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private StringBuilder CreateLinkString(Map params){
			List keys = new ArrayList(params.keySet());
			Collections.sort(keys);
	
			StringBuilder prestr = new StringBuilder();
			String key="";
			String value="";
			for (int i = 0; i < keys.size(); i++) {
				key=(String) keys.get(i);


				value = (String) params.get(key);
				if("".equals(value) || value == null || 
						key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
					continue;
				}
				
				prestr.append(key).append("=").append(value).append("&");
			}
			return prestr.deleteCharAt(prestr.length()-1);
	}

	/**
	 * 功能：XML报文解析函数
	 * */
	private  HashMap GetMessage(String url){
		SAXReader reader = new SAXReader();
		Document doc=null;
		HashMap hm=null;
		try {
			InputStream in=new URL(url).openStream();
			if (in != null) {
				doc = reader.read(in);
				hm = new HashMap();
				Element root = doc.getRootElement();
				for (Iterator it = root.elementIterator(); it.hasNext();) {
					Element e = (Element) it.next();
					if (e.nodeCount() > 1) {
						HashMap hm1 = new HashMap();
						for (Iterator it1 = e.elementIterator(); it1.hasNext();) {
							Element e1 = (Element) it1.next();
							hm1.put(e1.getName(), e1.getText());
						}
						hm.put(e.getName(), hm1);
					} else {
						hm.put(e.getName(), e.getText());
					}
				}
			}
			doc.clearContent();
			in.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hm;
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

	public String getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	public String getMerchantKey() {
		return merchantKey;
	}


	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}


	public String getVerifyUrl() {
		return verifyUrl;
	}


	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}


	public String getCharset() {
		return charset;
	}


	public void setCharset(String charset) {
		this.charset = charset;
	}


	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getRefundUrl() {
		return refundUrl;
	}


	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}
	
}
