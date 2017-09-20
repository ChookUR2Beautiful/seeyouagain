package com.xmniao.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sft.refund.Extension;
import com.sft.refund.Header;
import com.sft.refund.RefundAPI;
import com.sft.refund.RefundAPIExporterService;
import com.sft.refund.RefundRequest;
import com.sft.refund.Sender;
import com.sft.refund.Signature;
import com.shengpay.mcl.bp.api.ApplyInfoDetail;
import com.shengpay.mcl.bp.api.BatchPayment;
import com.shengpay.mcl.bp.api.BatchPaymentService;
import com.shengpay.mcl.bp.api.DirectApplyRequest;
import com.shengpay.mcl.bp.api.QueryRequest;
import com.shengpay.mcl.btc.response.DirectApplyResponse;
import com.shengpay.mcl.btc.response.QueryResponse;
import com.shengpay.mcl.btc.response.ResultInfoDetail;
import com.xmniao.common.MD5;
import com.xmniao.common.PayConstants;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.ShengPayService;

/**
 * 盛付通 退款服务
 * @author ChenBo
 *
 */
public class ShengPayServiceImpl implements ShengPayService {
	 //初始日志类
	private final Logger log = Logger.getLogger(ShengPayServiceImpl.class);
	
	@Autowired
	private PayRefundMapper payRefundMapper;

	private String signType;
	private String charset;
	private String merchantId;
	private String payUrl;
	private String refundVersion;
	private String refundName;
	private String md5Key;
	
	@Resource(name = "notifyServiceUrl")
	private String notifyUrl;
	
    private static final String XMN_REFUND_URL = "/xmn/shengRefundNotify";
    
    private static final String FRESH_REFUND_URL = "/fresh/shengRefundNotify";
    
	@Override
	public Map<String, String> shengPayRefund(String refundId,String payId,double orderAmount,String remarks,int serviceType) {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String amount = String.format("%.2f",orderAmount);
		
		try{
	    	RefundRequest rr = new RefundRequest();
	    	Extension ext = new Extension();
	    	ext.setExt1("XMN");//-
	    	
	    	com.sft.refund.Service service = new com.sft.refund.Service();
	    	service.setServiceCode(refundName);//-
	    	service.setVersion(refundVersion);//-
	
	    	Sender sender = new Sender();
	    	sender.setSenderId(merchantId);//-
	    	
	    	 Header header = new Header();
	    	 header.setCharset(charset);//-
	    	 header.setSender(sender);
	    	 header.setSendTime(time);//-退款日期
	    	 header.setService(service);
	
	    	 String signCode = getSignCode(refundId,payId,amount,time,serviceType);
	    	 
	    	 Signature sign = new Signature();
	    	 sign.setSignMsg(signCode);//-验证
	    	 sign.setSignType(signType);//-
	
	
	    	rr.setExtension(ext);
	    	rr.setHeader(header);
	    	rr.setMerchantNo(merchantId);//-
	    	rr.setNotifyURL(generateActionUrl(serviceType));//-
	    	rr.setOriginalOrderNo(payId);//-
	    	rr.setRefundAmount(amount);//-
	    	rr.setRefundOrderNo(refundId);//-
	    	rr.setRefundRoute("0");
	    	rr.setSignature(sign);
	
	    	RefundAPI  iApupProductService = new RefundAPIExporterService().getRefundAPIExporterPort();
	    	com.sft.refund.RefundResponse apa=iApupProductService.processRefund(rr);
	        log.info("返回结果："+JSON.toJSONString(apa));
	        
	        
	        if(StringUtils.isBlank(apa.getStatus())){
	        	log.info("盛付通退款出错，出错信息："+JSON.toJSONString(apa.getReturnInfo()));
	        	return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,JSON.toJSONString(apa.getReturnInfo()));
	        }
	        else{
	        	//1.数据校验
	        	Map<String,String> paramsMap=new LinkedHashMap<String,String>();
	        	paramsMap.put("Name", apa.getHeader().getService().getServiceCode());
	        	paramsMap.put("Version", apa.getHeader().getService().getVersion());
	        	paramsMap.put("Charset", apa.getHeader().getCharset());
	        	paramsMap.put("SenderId", apa.getHeader().getSender().getSenderId());
	        	paramsMap.put("SendTime", apa.getHeader().getSendTime());
	        	paramsMap.put("RefundOrderNo", apa.getRefundOrderNo());
	        	paramsMap.put("OriginalOrderNo", apa.getOriginalOrderNo());
	        	paramsMap.put("Status", apa.getStatus());
	        	paramsMap.put("RefundAmount", apa.getRefundAmount());
	        	paramsMap.put("RefundTransNo", apa.getRefundTransNo());
	        	paramsMap.put("Ext1", apa.getExtension().getExt1());
	        	paramsMap.put("SignType", apa.getSignature().getSignType());
	        	paramsMap.put("SignMsg", apa.getSignature().getSignMsg());
	        	
	        	if(verifyRefundSign(paramsMap)){
	        		return dealRefundData(paramsMap);
	        	}
	        	return returnMap(StateCodeUtil.PR_REFUND_FAIL,"验证不通过");

	        }
		}catch(Exception e){
			e.printStackTrace();
			log.error("系统执行异常",e);
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"系统执行异常");
		}
	}

	@Override
	public Map<String, String> queryPayRefund() {
		
		return null;
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
	
	@Override
	public String shengSign(Map<String,String> map,String key){
		if(map!=null && map.size()>0){
			StringBuilder prestr = getSignString(map);  //把MAP中所有元素的值拼在一起
			String temp = prestr.append(key).toString();
			log.info("要编码的字符串："+temp);
			return MD5.Encode(temp,"UTF-8");//把拼接后的字符串再与安全校验码直接连接起来,并且生成加密串
		}
		return null;
	}
	
	//获取要进行签名的字符串
	private StringBuilder getSignString(Map<String,String> params){
		
		List<String> keys = new ArrayList<String>(params.keySet());
		log.info("Keys"+keys);
		StringBuilder prestr = new StringBuilder();
		String key="";
		String value="";
		for (int i = 0; i <keys.size(); i++) {
			key=(String) keys.get(i);
			value = (String) params.get(key);
			if(StringUtils.isBlank(value)){
				continue;
			}
			if(key.equalsIgnoreCase("SignMsg")){
				continue;
			}
			prestr.append(value);
//			System.out.println(key+"="+value);
		}
		return prestr;
}

	public static void main(String[] args){	

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 333334298);
		map.put("province", "广东省");
		map.put("cityname", "深圳市");
		map.put("bank", "中国工商银行");
		map.put("bankname", "梅林支行");
		map.put("ispublic", 0);
		map.put("account", "6212264000022804666");
		map.put("amount", 0.01);
		map.put("fullname", "曾浩明");
		map.put("userType", 1);
		
		//System.out.println(String.valueOf(map.get("ispublic")));
		 FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[] {"WebRoot/WEB-INF/pay-context.xml","WebRoot/WEB-INF/pay-service.xml","WebRoot/WEB-INF/pay-rocketmq.xml","WebRoot/WEB-INF/pay-quartz.xml","WebRoot/WEB-INF/pay-thrift.xml","WebRoot/WEB-INF/pay-servlet.xml"}, true);

	    context.start();
	    ShengPayService shengPayService =  context.getBean(ShengPayServiceImpl.class);  
	   System.out.println( shengPayService.shengPayQuery("800030231"));

	   //054  出款失败
	   //066  收款方卡号与户名不符
	   //023
//		System.out.println("======================================================");
//		Map<String,String> result = new ShengPayServiceImpl().shengPayRefund("","",0,"");
//		System.out.println("执行结果："+result);
		/*String str1="REFUND_REPV4.4.2.1.1UTF-8SFT2015030417334515030417334867521502021606070195010.0120150304173344396563XMNMD57P9H58CAD1CE34FCF64CE8C3CCB87389";
		String str2="REFUND_REPV4.4.2.1.1UTF-8SFT2015030417334515030417334867521502021606070195010.0120150304173344396563XMNMD57P9H58CAD1CE34FCF64CE8C3CCB87389";
		System.out.println(str1.equals(str2));*/

		
	    /*	String Msg = MD5.Encode("utf-8MD54246603333342981http://szdev.xmniao.com:8184/payService/shengPayNotify0.01333334298001广东省深圳市梅林支行中国工商银行C曾浩明62122640000228046660.01remark7P9H58CAD1CE34FCF64CE8C3CCB87389","UTF-8").toUpperCase();
	    	System.out.println("Msg:"+Msg);*/
	    	/*System.out.println("Msg2:"+"3C20971CDAC26DF732B7C5D11A394A92");
	    	if(Msg.equalsIgnoreCase("3C20971CDAC26DF732B7C5D11A394A92")){
	    		//通过验证
	    		System.out.println("验证成功了");
	    	}
	    	else{
	    		//验证失败
	    		System.out.println("验证失败了");
	    	}*/
		//new ShengPayServiceImpl().shengPayRefund("1232145202021", "3654155411232145202021", 1.2, "");
		
		
		
		
		//new ShengPayServiceImpl().shengPayQuery();
	}

	@Override
	public PayRefund getPayRefundByRefundId(String refundId) {
		return payRefundMapper.getPayRefundByRefundId(refundId);
	}

	/**
     * 盛付通支付提现接口(盛付通代发接口)   
     * @param map
     * @return
     */
	@Override
	public Map<String, String> shengPay(Map<String, Object> map) {
		
		//响应对象
		DirectApplyResponse response = new DirectApplyResponse();
		//请求对象
		DirectApplyRequest request = new DirectApplyRequest();
		//包装请求参数
		warpShengPayParams(map, request);

		//构建代付sign
		String orgsign = getPaySign(request);
		request.setSign(MD5.Encode(orgsign,"UTF-8").toUpperCase());
		log.info("sign--------" + request.getSign());

		BatchPaymentService bps = new BatchPaymentService();
		BatchPayment batchPaymentWs = bps.getBatchPaymentPort();
		long sdate = System.currentTimeMillis();
		//请求webservice
		response = batchPaymentWs.directApply(request);
		long edate = System.currentTimeMillis();
		long ydate = edate - sdate;
		log.info("盛付通代付发送完时间-----------："+edate);
		log.info("盛付通代付响应时间----------："+ydate);
		log.info("返回-----------batchno:" + response.getBatchNo());
		log.info("返回-----------ResultCode:"+ response.getResultCode());
		log.info("返回-----------ResultMessage:"+ response.getResultMessage());

		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("RespDesc", "");

		if ("00".equals(response.getResultCode())) {
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
			resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);
		} else {
			resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + response.getResultMessage());
		}

		return resultMap;
	}

	/**
	 * 构建PaySign字符串
	 * @param request
	 * @return
	 */
	private String getPaySign(DirectApplyRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getCharset());
		sb.append(request.getSignType());
		sb.append(StringUtils.isEmpty(request.getCustomerNo()) ? "" : request.getCustomerNo());
		sb.append(request.getBatchNo());
		sb.append(request.getCallbackUrl());
		sb.append(String.format("%.2f", request.getTotalAmount()));
		List<ApplyInfoDetail> details = null;

		details = request.getDetails();
		if (details != null) {
			for (ApplyInfoDetail detail : details) {
				sb.append(detail.getId());
				sb.append(detail.getProvince());
				sb.append(detail.getCity());
				sb.append(detail.getBranchName());
				sb.append(detail.getBankName());
				sb.append(detail.getAccountType());
				sb.append(detail.getBankUserName());
				sb.append(detail.getBankAccount());
				sb.append(String.format("%.2f", detail.getAmount()));
				sb.append(detail.getRemark());
			}
		}
		sb.append(this.md5Key);
		String orgsign = sb.toString();
		log.info("orgsign--------" + orgsign);
		return orgsign;
	}

	/**
	 * 封装代付参数
	 * @param map
	 * @param request
	 */
	private void warpShengPayParams(Map<String, Object> map,
			DirectApplyRequest request) {
		//批次号
		request.setBatchNo(String.valueOf(map.get("id"))+map.get("userType"));
		//回调url
		request.setCallbackUrl(this.payUrl);
		//字符集
		request.setCharset(this.charset.toLowerCase());
		//商户号
		request.setCustomerNo(this.merchantId);
		//签字类型
		request.setSignType(this.signType);
		//总金额
		request.setTotalAmount(new BigDecimal(String.valueOf(map.get("amount"))).setScale(2,BigDecimal.ROUND_DOWN));
		//明细对象
		ApplyInfoDetail detail1 = new ApplyInfoDetail();
		//帐号类型
		detail1.setAccountType(String.valueOf(map.get("ispublic")).equals( "0" )? "C": "B");
		//金额
		detail1.setAmount(new BigDecimal(String.valueOf(map.get("amount"))).setScale(2,BigDecimal.ROUND_DOWN));
		//银行帐号
		detail1.setBankAccount(String.valueOf(map.get("account")));
		//银行名称
		//String 
		String bankName = "";
		String abbrev = String.valueOf(map.get("abbrev"));
		
		bankName = abbrev.equals("CMB")?"招商银行":abbrev.equals("BOCOM")?"交通银行":String.valueOf(map.get("bank"));
		
		detail1.setBankName(bankName);
		//银行支行名称
		detail1.setBranchName(String.valueOf(map.get("bankname")));
		//帐户
		detail1.setBankUserName(String.valueOf(map.get("fullname")));
		//城市
		detail1.setCity(String.valueOf(map.get("cityname")));
		//明细批次号
		detail1.setId(String.valueOf(map.get("id")) + "001");
		//省份
		detail1.setProvince(String.valueOf(map.get("province")));
		//备注
		detail1.setRemark("提现");
		request.getDetails().add(detail1);
	}
	

	public String getSignCode(String refundId,String payId,String amount,String time,int serviceType){
		Map<String,String> params=new LinkedHashMap<String,String>();
		params.put("Name",this.refundName);
		params.put("Version", this.refundVersion);
		params.put("Charset", this.charset);
		params.put("SenderId", this.merchantId);
		params.put("SendTime", time);//时间
		params.put("MerchantNo",this.merchantId);
		params.put("RefundOrderNo", refundId);
		params.put("OriginalOrderNo", payId);
		params.put("RefundAmount", amount);
		params.put("RefundRoute", "0");
		params.put("NotifyURL", generateActionUrl(serviceType));
		params.put("Ext1", "XMN");
		params.put("SignType", this.signType);
		
		return shengSign(params,this.md5Key).toUpperCase();
	}
	
	public boolean verifyRefundSign(Map<String,String> paramsMap){
		return true;
//    	String Msg = shengSign(paramsMap,this.md5Key).toUpperCase();
//    	if(Msg.equalsIgnoreCase(paramsMap.get("SignMsg"))){
//    		//通过验证
//    		log.info("验证成功了");
//    		return true;
//    	}
//    	else{
//    		//验证失败
//    		log.info("验证失败了");
//    		return false;
//    	}
	}
	
	public Map<String,String> dealRefundData(Map<String,String> paramsMap){
		if(paramsMap.get("Status").equals("00")){
			log.info("退款处理中。。。");
			return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"退款处理中");
		}
		else if(paramsMap.get("Status").equals("01")){
			log.info("退款成功");
			return returnMap(StateCodeUtil.PR_SUCCESS,"");
		}
		else if(paramsMap.get("Status").equals("02")){
			log.error("退款失败了");
			return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"盛付通退款失败");
		}
		else{
			return returnMap(StateCodeUtil.PR_REFUND_FAIL,"盛付通反馈退款状态异常");
		}
	}
	
	
	@Override
	public Map<String ,String> shengPayQuery(String batchNo){//orderId+userType
		
		log.info("batch:"+batchNo);
		
		Map<String,String> resultMap = new HashMap<String,String>();
		String userType = batchNo.substring(batchNo.length()-1, batchNo.length());
		resultMap.put("platform_code", batchNo);
		resultMap.put("usertype", userType);
	
		QueryResponse response=new QueryResponse();		
		QueryRequest request=new QueryRequest();		
        request.setCharset(this.charset);				
        request.setCustomerNo(this.merchantId);				
        request.setSignType(this.signType);  				
        request.setBatchNo(batchNo);				
       // request.setDetailId("800026131001");	//设置了 没有details 			
        StringBuilder sb = new StringBuilder();				
        sb.append(request.getCharset());				
        sb.append(request.getSignType());				
        sb.append(StringUtils.isEmpty(request.getCustomerNo())?"":request.getCustomerNo());				
        sb.append(StringUtils.isEmpty(request.getBatchNo())?"":request.getBatchNo());				
        sb.append(StringUtils.isEmpty(request.getDetailId())?"":request.getDetailId());  
        sb.append(this.md5Key);
        String orgsign= sb.toString();  				
    	request.setSign(MD5.Encode(orgsign,"UTF-8").toUpperCase());				
    	try {
			BatchPaymentService bps = new BatchPaymentService();
			BatchPayment batchPaymentWs = bps.getBatchPaymentPort();
			response=batchPaymentWs.query(request);
		} catch (Exception e) {
			log.error("盛付通查询异常",e);
			resultMap.put("status", "0");
			resultMap.put("Message", "查询异常");
			return resultMap;
		}	
   
   
    	if(!response.getResultCode().equals("00")){
    		log.info("盛付通返回结果code:"+response.getResultCode()+"------99：系统错误；01：参数错误；02：商户号不合法；03：验签失败；04：无权访问；05：原交易不存在；"
        			+ "06：重复请求；09：文件格式错误；11：校验请求失败；12：批次不存在；13：日期区间不合法；14：禁止查询；15：请求明细数量超限；16：请求的批次未进入支付；17：文件格式错误；18：请求的批次还未产生任务结果；");
    		log.error("盛付通查询失败");
			resultMap.put("status", "0");
			resultMap.put("Message", "查询失败");
			return resultMap;
    	}else{
    		
    		if (!response.getResultInfo().getPayStatus().equals("批次校验失败")) {
				ResultInfoDetail detail = response.getResultInfo().getDetails()
						.get(0);
				String payStatus = detail.getPayStatus().trim();
				log.info("orderNo:" + detail.getOrderNo());
				if (payStatus.equals("付款成功")) {
					resultMap.put("status",
							PayConstants.WITHDRAW_STATUS_SUCCESS);
					resultMap.put("Message", PayConstants.WITHDRAW_MSG_SUCCESS);
				} else if (payStatus.equals("付款失败") || payStatus.equals("已退票")
						|| payStatus.equals("批次校验失败")) {
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
					resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL
							+ "," + detail.getResultRemark());
				} else if (payStatus.equals("付款中")) {
					resultMap.put("status",
							PayConstants.WITHDRAW_STATUS_PROCESS);
					resultMap.put("Message", PayConstants.WITHDRAW_MSG_PROCESS);
				} else {
					resultMap.put("status", "0");
					resultMap.put("Message", payStatus);
				}
			}else{
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
				resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL
						+ "," + response.getResultInfo().getPayStatus());
			}

    	}
       		
		return resultMap;
		
		
	}

	
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}


	public void setRefundVersion(String refundVersion) {
		this.refundVersion = refundVersion;
	}

	public String getRefundName() {
		return refundName;
	}

	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	public String getMd5Key() {
		return md5Key;
	}

	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}
	
	public String getRefundVersion() {
		return refundVersion;
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


}
