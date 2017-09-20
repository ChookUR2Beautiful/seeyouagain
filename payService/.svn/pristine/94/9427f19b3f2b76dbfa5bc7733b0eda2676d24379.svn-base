package com.xmniao.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.alibaba.fastjson.JSON;
import com.bill99.schema.fo.settlement.ApplyResponseType;
import com.bill99.schema.fo.settlement.BatchSettlementApplyResponse;
import com.bill99.schema.fo.settlement.BatchidQueryResponse;
import com.bill99.schema.fo.settlement.ComplexQueryResponse;
import com.bill99.schema.fo.settlement.Pay2bankResultType;
import com.bill99.schema.fo.settlement.SettlementPkiApiResponse;
import com.xmniao.common.MD5;
import com.xmniao.common.PayConstants;
import com.bill99.schema.fo.settlement.SettlementPkiResponseType;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.kuaiqian.CustomerTool;
import com.xmniao.kuaiqian.DealInfoEntity;
import com.xmniao.kuaiqian.OrderInfoEntity;
import com.xmniao.kuaiqian.GatewayRefundQueryRequest;
import com.xmniao.kuaiqian.GatewayRefundQueryResponse;
import com.xmniao.kuaiqian.GatewayRefundQueryResultDto;
import com.xmniao.kuaiqian.GatewayRefundQueryServiceLocator;
import com.xmniao.kuaiqian.GatewayRefundQuerySoapBindingStub;
import com.xmniao.kuaiqian.ParseUtil;
import com.xmniao.service.KuaiqianPayService;


/**
 * 快钱支付退款服务实现
 * @author ChenBo
 *
 */

public class KuaiqianPayServiceImpl implements KuaiqianPayService{
	 //初始日志类
	private final Logger log = Logger.getLogger(KuaiqianPayServiceImpl.class);
	
	//版本信息
	private String version;
	/** 支付提交类型 */
	private String serviceType;
	/** 批次号查询提交类型 */
	private String queryBatch;
	/** 订单号查询提交类型 */
	private String queryOrder;
	/** 加密类型 */
	private String featureCode;
	/** 代付付款商户名称 */
	private String payMerchantName;
	/** 代付商户号 */
	private String payMerchantId;
	/** 代付付款方帐号 */
	private String payPayerAcctCode;
	// 商户编号，线上的话改成你们自己的商户编号的，发到商户的注册快钱账户邮箱的
	private String refundMerchantId;
	// 退款接口版本号 目前固定为此值
	private String refundVersion;
	// 操作类型
	private String refundCommandType;
	// 原商户订单号
	private String orderId;
	// 退款金额，整数或小数，小数位为2位 以人民币元为单位
	private String amount;
	// 退款提交时间 数字串，一共14位 格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2位]
	private String postDate;
	// 退款流水号 字符串
	private String txOrder;
	// 加密所需的key值
	private String refundMerchantKey;

	private String charSet="utf-8";

	private String refundUrl;
	

	private String queryMerchantId;
	
	private String queryVersion;
	
	private String queryKey;
	
	private String queryUrl;

	private String payUrl;



	/**
	 * @param refundId  本次退款的退款流水号
	 * @param payId 支付订单时，提交的支付流水号
	 * @param orderAmount 退款金额
	 */
	@Override
	public Map<String,String> kuaiqianPayRefund(String refundId,String payId,double orderAmount){

		log.info("[kuaiqianPayRefund]payId:"+payId+",refundId:"+refundId
				+",orderAmount:"+orderAmount);
		//原商户订单号
		orderId = payId;
		//退款金额，整数或小数，小数位为2位   以人民币元为单位
		amount = String.format("%.2f",orderAmount);;
		//退款提交时间 数字串，一共14位 格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2位]
		postDate = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		//退款流水号  字符串
		txOrder = refundId;		

		//生成加密签名串
		String macVal = "";
		macVal = appendParam(macVal, "merchant_id", refundMerchantId);
		macVal = appendParam(macVal, "version", refundVersion);
		macVal = appendParam(macVal, "command_type", refundCommandType);                                                  
		macVal = appendParam(macVal, "orderid", orderId);
		macVal = appendParam(macVal, "amount", amount);
		macVal = appendParam(macVal, "postdate",postDate);
		macVal = appendParam(macVal, "txOrder", txOrder);
		macVal = appendParam(macVal,"merchant_key",refundMerchantKey);   
		String mac = MD5.Encode(macVal,"UTF-8").toUpperCase();
		String param = "merchant_id="+refundMerchantId
					+"&version="+refundVersion
					+"&command_type="+refundCommandType
					+"&txOrder="+txOrder
					+"&amount="+amount
					+"&postdate="+postDate
					+"&orderid="+orderId
					+"&mac="+mac;
		String url = refundUrl+"?"+param;
		log.info("url:"+url);
		
		try{
			GetMethod method = new GetMethod(url);
			HttpClient client = new HttpClient();
			int result;
			
			result = client.executeMethod(method);
			if(result != HttpStatus.SC_OK){
				log.error("http请求错误 ");
				
				return returnMap(StateCodeUtil.PR_THIRD_NOT_CONNECT,"http请求错误!");
			}
	
			InputStream is = method.getResponseBodyAsStream();
//			Map<String,String> resultMap =GetMessage(is);

			String ret= GetMessage2(is);
			System.out.println("ret:"+ret);
	    	Map<String,String> resultMap = ParseUtil.parseXML(ret);
	    	log.info("resultMap:"+resultMap);
	    	
	    	if(resultMap.get("RESULT").equalsIgnoreCase("Y")){
	    		log.info("退款成功");
	    		return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
	    	}else{
	    		log.error("退款失败,错误信息:"+resultMap.get("CODE"));
	    		String errMsg;
	    		if(resultMap.get("CODE").indexOf("CNP退款失败") != -1){
	    			errMsg = "快钱支付，不允许当天支付订单退款";//"CNP退款失败,请明天再试";
	    		}else{
	    			errMsg = resultMap.get("CODE");
	    		}
	    		return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,errMsg);
	    	}
	    	
		}catch(Exception e){
			log.info(e);
			e.printStackTrace();
		}
		return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"快钱退款异常");
	}

	public String GetMessage2(InputStream is){
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
	}
	/**
	 * 功能：XML报文解析函数
	 * */
	private  HashMap GetMessage(InputStream in){
		SAXReader reader = new SAXReader();
		Document doc=null;
		HashMap hm=null;
		try {
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
	
	/*public static void main(String[] args){

		KuaiqianPayServiceImpl kPayService = new KuaiqianPayServiceImpl(); 
	//	Map<String,String> refundMap = kPayService.kuaiqianPayRefund("1504091541042069", "1503271531017584", 1.0);
	//	System.out.println("refundMap:"+refundMap);
		Map<String,String> map = kPayService.KuaiqianQueryRefund(null"1504151754220143","1504151759126709");
		System.out.println("result:"+map);
	}*/
	/*
	 * 退款订单查询
	 */
	public Map<String,String> KuaiqianQueryRefund(String payId,String refundId){
		String returnMsg = "";
		
		String signType = "1";
		String refundDate = "20"+refundId.substring(0, 6);
		String startDate= refundDate;//起始日期
		String endDate = refundDate;//结束日期
		String requestPage = "1";	//页码
		
//		String customerBatchId;	//客户方的批次号
//		String orderId ;	//退款订单号
		String rOrderId = payId;
//		String seqId;		//退款交易号
		String extraOutputColumn = "payType";
		String signMsg = "";	//签名字符串

		if(signType.equals("1")){
			StringBuffer signSrc = new StringBuffer();
			
			appendSignSrc(queryVersion,"version",signSrc);
			appendSignSrc(signType,"signType",signSrc);
			appendSignSrc( queryMerchantId,"merchantAcctId",signSrc);
			appendSignSrc(startDate,"startDate",signSrc);
			appendSignSrc(endDate,"endDate",signSrc);
			appendSignSrc(requestPage,"requestPage",signSrc);
			appendSignSrc(rOrderId,"rOrderId",signSrc);
			appendSignSrc(extraOutputColumn,"extra_output_column",signSrc);
			appendSignSrc(queryKey,"key",signSrc);
			
			signSrc.deleteCharAt(signSrc.length()-1);
			System.out.println(signSrc.toString());
			signMsg = MD5.Encode(signSrc.toString(), "utf-8").toUpperCase();
			
		}else{
			log.error("当前只支付MD5签名");
		}
		
		try{
		GatewayRefundQueryRequest  request = new GatewayRefundQueryRequest();
		 
		request.setVersion(queryVersion);
		request.setSignType(signType);
		request.setMerchantAcctId(queryMerchantId);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		request.setROrderId(rOrderId);
		request.setRequestPage(requestPage);
		request.setExtra_output_column(extraOutputColumn);
		request.setSignMsg(signMsg);

		log.info("request:"+JSON.toJSONString(request));
		GatewayRefundQueryResponse result = queryService(request);
		log.info(JSON.toJSONString(result));
		
		String errCode = result.getErrCode();
		if(errCode == null ||errCode.trim().equals("")){
			log.info("查询成功");
			//对整体数据进行验证
			boolean isVerify = true;
			if(isVerify){
				GatewayRefundQueryResultDto [] refundResult = result.getResults();
				//分解出各个退款订单的数据
				for(GatewayRefundQueryResultDto refundOrder:refundResult){
					//对各个退款订单进行处理，是否退款成功
					log.info(JSON.toJSONString(refundOrder));
					String retId = refundOrder.getROrderId();
					
					//这个订单就是当前需要查询的退款订单
					if(retId.equals(payId)){
						String refundState = refundOrder.getStatus();
						if(refundState.equals("1")){
							log.info("退款成功");
							return returnMap(StateCodeUtil.PR_SUCCESS,"");
						}
						else if(refundState.equals("2")){
							log.info("退款失败");
							return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"快钱退款反馈退款失败");
						}
						else{
							log.error("退款处理中");
							returnMsg = "退款处理中";
						}
					}
				}		
			}
			else{
				log.error("数据验证失败");
				returnMsg = "数据验证失败";
			}
		}
		else{
			log.error("查询失败："+errCode);
			returnMsg = "查询失败："+errCode;
		}
		}catch(Exception e){
			log.error("快钱退款查询失败"+e);
		}
		return returnMap(StateCodeUtil.PR_THIRD_REFUNDING,returnMsg);	
	}
	
	private void appendSignSrc(String src,String name,StringBuffer signSrc){
		if(signSrc == null)
			return;
		if (StringUtils.isNotBlank(src)) {
			signSrc.append(name+"="+src).append("&");
		}
	}
	
	private GatewayRefundQueryResponse queryService(GatewayRefundQueryRequest request){
		 try{
		 // 取得Web 服务
		 URL wsdlUrl = new URL(queryUrl);
		 GatewayRefundQuerySoapBindingStub service = (GatewayRefundQuerySoapBindingStub) new GatewayRefundQueryServiceLocator().getgatewayRefundQuery(wsdlUrl);
			
		 // 调用WS服务
		 GatewayRefundQueryResponse result = service.query(request);
		 return result;
		 }catch(Exception e){
			 log.error("查询失败",e);
		 }
		 return null;
	}
	
	public String appendParam(String returns, String paramId, String paramValue) {
		if (returns != "") {
			if (paramValue != "") {
				returns +=  paramId + "=" + paramValue;
			}

		} else {
			if (paramValue != "") {
				returns = paramId + "=" + paramValue;
			}
		}
		return returns;
	}
	
	
	public Map<String,String> returnMap(String code,String msg){
		return this.returnMap(code,msg,"");
	}
	
	//返回的Map数据
	public Map<String,String> returnMap(String code,String msg,String response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("code", code==null?"":code);
		resultMap.put("Msg",msg==null?"":msg);
		resultMap.put("response",response);
		return resultMap;
	}
	
	public static void main(String[] args){
	/*	System.out.println("传入数组大小为："+args.length);
		for(String arg:args){
			System.out.println(arg);
		}
		System.out.println("----以上是测试Main方法传入参数---");*/
		
		//new KuaiqianPayServiceImpl().kuaiqianPay();
		
	}

	
	@Override
	public Map<String, String> kuaiqianPay(Map<String, Object> paramMap) throws Exception{		
		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("RespDesc", "");
		//包装参数
		DealInfoEntity dealInfo = getkuaiqianPayParam(paramMap);
        
		//try {
			//提交请求
			CustomerTool ct = new CustomerTool();
			//log.info("快钱支付发送请求-------------------------");
			SettlementPkiApiResponse response;
			response = ct.apply_ws(dealInfo,payUrl);
			String errorCode = response.getResponseBody().getErrorCode();
			String errorMsg = response.getResponseBody().getErrorMsg();
			if(!"0000".equals(errorCode) && !"1313".equals(errorCode)){
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_ERROR);
				resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + errorMsg);
				return resultMap;
			}
			// 解析返回参数
			//log.info("快钱支付返回信息：-------------------------");
			BatchSettlementApplyResponse bsar = (BatchSettlementApplyResponse) ct.unseal(response, dealInfo);// 得到解密数据
			log.info("提交的批次号为:" + bsar.getResponseBody().getBatchNo());
			//log.info("申请成功笔数为:" + bsar.getResponseBody().getTotalApplySuccCnt());
			log.info("状态：" + bsar.getResponseBody().getStatus());
			Pay2bankResultType chen = (Pay2bankResultType) bsar.getResponseBody().getPay2bankLists().get(0);
			log.info("ErrorCode:" + chen.getErrorCode() + ",ErrorMsg:"
					+ chen.getErrorMsg() + ",BankErrorCode:"
					+ chen.getBankErrorCode() + ",BankErrorMsg:"
					+ chen.getBankErrorMsg());
			chen.getPay2bank().getMerchantId();

			if ("100".equals(bsar.getResponseBody().getStatus())) {
				resultMap.put("status",PayConstants.WITHDRAW_STATUS_PROCESS);
				resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);
			} else {
				resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + chen.getErrorMsg());
			}
		/*} catch (MalformedURLException e) {
			log.error("快钱支付发送请求异常", e);
			resultMap.put("RespDesc", "快钱提交--MalformedURLException异常");
		} catch (UnsupportedEncodingException e) {
			log.error("快钱支付发送请求异常", e);
			resultMap.put("RespDesc", "快钱提交--UnsupportedEncodingException异常");
		} catch (IOException e) {
			log.error("快钱支付发送请求异常", e);
			resultMap.put("RespDesc", "快钱提交--IOException异常");
		}*/

		return resultMap;

	}
	
	
	
	private DealInfoEntity getkuaiqianPayParam(Map<String, Object> map){
		
	
		//批次号
		String batchNo = "XMN"+payMerchantId+"_"+map.get("id");
		//金额
		String amt = new BigDecimal(String.valueOf(map.get("amount"))).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_DOWN).toString();
		List<OrderInfoEntity> ordersInfos = new ArrayList<OrderInfoEntity>();
		for (int i = 0; i < 1; i++) {
			OrderInfoEntity orderInfo = new OrderInfoEntity();
			orderInfo.setMerchantId(payMerchantId+"_"+map.get("id"));
			orderInfo.setAmt(amt);//金额
			orderInfo.setBank(String.valueOf(map.get("bank")));//银行
			orderInfo.setName(String.valueOf(map.get("fullname")));//户名
			orderInfo.setBankCardNo(String.valueOf(map.get("account")));//卡号
			orderInfo.setBranchBank(String.valueOf(map.get("bankname")));//开户行
			orderInfo.setPayeeType(String.valueOf(map.get("ispublic")) == "0" ? "1": "0");//对公、私
			orderInfo.setProvince(String.valueOf(map.get("province")));//省
			orderInfo.setCity(String.valueOf(map.get("cityname")));//市
			orderInfo.setMemo("代返还");//备注
			orderInfo.setBankPurpose("代返还");// 银行交易用备注 
			orderInfo.setBankMemo("代返还");// 银行交易备注 
			orderInfo.setPayeeNote("代返还");// 收款方通知知内容 
			orderInfo.setPayeeMobile("");// 收款方手机号 
			orderInfo.setPayeeEmail("");// 收款方邮件 
			orderInfo.setPeriod("");// 到账时效 
			orderInfo.setMerchantMemo1(""+map.get("userType"));// 商户预留字段1
			orderInfo.setMerchantMemo2("");
			orderInfo.setMerchantMemo3("");
			ordersInfos.add(orderInfo);
		}

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setPayerAcctCode(payPayerAcctCode);// 付款方帐号
		dealInfo.setBatchNo(batchNo);//批次号 
		dealInfo.setApplyDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 发起日期 
		dealInfo.setName(payMerchantName);
		dealInfo.setTotalAmt(amt);// 总金额 
		dealInfo.setTotalCnt("1");//总笔额 
		dealInfo.setFeeType("1");// 付费方式 
		dealInfo.setCur("RMB");// 币种 
		dealInfo.setCheckAmtCnt("0");// 是否验证金额 
		dealInfo.setBatchFail("1");// 是否整批失败 
		dealInfo.setRechargeType("1");// 充值方式 0:代扣，1:充值，2:垫资
		dealInfo.setAutoRefund("0");// 是否自动退款  0:自动退款; 1:不自动退款
		dealInfo.setPhoneNoteFlag("1");//是否短信通知  0:通知; 1:不通知
		dealInfo.setMerchantMemo1("Memo1");
		dealInfo.setMerchantMemo2("Memo2");
		dealInfo.setMerchantMemo3("Memo3");
		dealInfo.setOrdersInfo(ordersInfos);//支付明细
		dealInfo.setServiceType(serviceType);//提交类型 
		dealInfo.setVersion(version);// 版本号 
		dealInfo.setFeatureCode(featureCode);//加密类型
		dealInfo.setMemberCode(payMerchantId);//商户号 
		return dealInfo;
		
	}
	
	
	public  Map<String,Object> queryByBatch(String batchId) {
		
		
		String batchNo =batchId;
		String listFlag = "0";// 显示详细明细
		String page = "1";
		String pageSize = "10";

		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBatchNo(batchNo);
		dealInfo.setListFlag(listFlag);
		dealInfo.setPage(page);
		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(queryBatch);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(payMerchantId);

		CustomerTool ct = new CustomerTool();
		
		SettlementPkiApiResponse response = null;
		try {
			response = ct.apply_ws(dealInfo,payUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		SettlementPkiResponseType responsebody = response.getResponseBody();
		String errorCode="";
		errorCode=responsebody.getErrorCode();
		String msg =responsebody.getErrorMsg();
		//String sta = responsebody.getStatus();
		String status = "";
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(!"0000".equals(errorCode) && !"1313".equals(errorCode)){
			map.put("status", 0);
			map.put("Message", msg);
			return map;
		}
		BatchidQueryResponse bidqr = (BatchidQueryResponse) ct.unseal(response,
				dealInfo);// 得到解密数据
		ApplyResponseType app = bidqr.getResponseBody().getBatchList();
		String platformCode = app.getOrderSeqId();
		status = app.getStatus();
	
		map.put("platform_code", platformCode);
		if(status.equals("111")){
			map.put("status", 1);
			map.put("Message",PayConstants.WITHDRAW_MSG_SUCCESS);
		}else if(status.equals("112")){
			map.put("status", 2);
			map.put("Message", PayConstants.WITHDRAW_MSG_FAIL);
		}
		
		return map;
	
	}
	
public  Map<String,String> queryByOrder(String id) {

		//String listFlag = "0";// 显示详细明细
		//String page = "1";
	    Map<String,String> resultMap = new HashMap<String,String>();
		String pageSize = "10";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String beginApplyDate = format.format(DateUtils.addDays(now, -7));
		String endApplyDate = format.format(now);


		DealInfoEntity dealInfo = new DealInfoEntity();
		dealInfo.setBeginApplyDate(beginApplyDate);
		dealInfo.setEndApplyDate(endApplyDate);
		dealInfo.setPageSize(pageSize);
		dealInfo.setServiceType(queryOrder);
		dealInfo.setVersion(version);
		dealInfo.setFeatureCode(featureCode);
		dealInfo.setMemberCode(payMerchantId);
		dealInfo.setMerchantId(payMerchantId+"_"+id);

		CustomerTool ct = new CustomerTool();
		
		SettlementPkiApiResponse response = null;
		try {
			response = ct.apply_ws(dealInfo,payUrl);
		} catch (Exception e) {
			log.error("快钱查询异常");
			resultMap.put("status", "0");
			resultMap.put("Message", "查询异常");
			return resultMap;
		} 
		
		SettlementPkiResponseType responsebody = response.getResponseBody();
		String errorCode="";
		errorCode=responsebody.getErrorCode();
		String msg =responsebody.getErrorMsg();

		
		log.info("快钱查询返回参数------errorCode："+errorCode+",msg:"+msg+"");
		if(!"0000".equals(errorCode) && !"1313".equals(errorCode)){
			resultMap.put("status", "0");
			resultMap.put("Message", msg);
			return resultMap;
		}
		
		ComplexQueryResponse bidqr = (ComplexQueryResponse) ct.unseal(response,
				dealInfo);// 得到解密数据
		
		List<Pay2bankResultType> orders =  bidqr.getResponseBody().getPay2bankLists();
		//String bankErrorCode1 = "";
		String bankErrorMsg1 = "";
		//String errorCode1 = "";
		String errorMsg1 = "";
		String orderSeqId1 = "";
		String status1 = "";
		String userType1 = "";
		
		if(orders!=null&&orders.size()>0){
			Pay2bankResultType order = orders.get(0);
			//bankErrorCode1 = order.getBankErrorCode();
			bankErrorMsg1 = order.getBankErrorMsg();
			//errorCode1 = order.getErrorCode();
			errorMsg1 = order.getErrorMsg();
			orderSeqId1 = order.getOrderSeqId();
			status1 = order.getStatus();
			userType1 = order.getPay2bank().getMerchantMemo1();
			
		}
		resultMap.put("platform_code", orderSeqId1);
		resultMap.put("usertype", userType1);
		if(status1.equals("111")){
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
			resultMap.put("Message",PayConstants.WITHDRAW_MSG_SUCCESS);
		}else if(status1.equals("112")){
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
			resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL+","+errorMsg1+","+bankErrorMsg1);
		}else if(status1.equals("101")) {
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
			resultMap.put("Message", PayConstants.WITHDRAW_MSG_PROCESS);
		}
		
		return resultMap;
	
	}
	

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getRefundMerchantId() {
		return refundMerchantId;
	}

	public void setRefundMerchantId(String refundMerchantId) {
		this.refundMerchantId = refundMerchantId;
	}

	public String getRefundVersion() {
		return refundVersion;
	}

	public void setRefundVersion(String refundVersion) {
		this.refundVersion = refundVersion;
	}

	public String getRefundCommandType() {
		return refundCommandType;
	}

	public void setRefundCommandType(String refundCommandType) {
		this.refundCommandType = refundCommandType;
	}

	public String getRefundMerchantKey() {
		return refundMerchantKey;
	}

	public void setRefundMerchantKey(String refundMerchantKey) {
		this.refundMerchantKey = refundMerchantKey;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}

	
	public String getQueryBatch() {
		return queryBatch;
	}

	public void setQueryBatch(String queryBatch) {
		this.queryBatch = queryBatch;
	}

	public String getQueryOrder() {
		return queryOrder;
	}

	public void setQueryOrder(String queryOrder) {
		this.queryOrder = queryOrder;
	}

	public String getPayMerchantName() {
		return payMerchantName;
	}

	public void setPayMerchantName(String payMerchantName) {
		this.payMerchantName = payMerchantName;
	}

	public String getPayMerchantId() {
		return payMerchantId;
	}

	public void setPayMerchantId(String payMerchantId) {
		this.payMerchantId = payMerchantId;
	}

	public String getPayPayerAcctCode() {
		return payPayerAcctCode;
	}

	public void setPayPayerAcctCode(String payPayerAcctCode) {
		this.payPayerAcctCode = payPayerAcctCode;
	}
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
public String getFeatureCode() {
	return featureCode;
}

	public String getQueryMerchantId() {
		return queryMerchantId;
	}

	public void setQueryMerchantId(String queryMerchantId) {
		this.queryMerchantId = queryMerchantId;
	}

	public String getQueryVersion() {
		return queryVersion;
	}

	public void setQueryVersion(String queryVersion) {
		this.queryVersion = queryVersion;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	
	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	
	
//	public String getServiceType() {
//	return serviceType;
//}
//
//public void setServiceType(String serviceType) {
//	this.serviceType = serviceType;
//}
//
//public String getFeatureCode() {
//	return featureCode;
//}
//
//public void setFeatureCode(String featureCode) {
//	this.featureCode = featureCode;
//}
//
//public String getMerchantName() {
//	return merchantName;
//}
//
//public void setMerchantName(String merchantName) {
//	this.merchantName = merchantName;
//}
//
//public String getTxnType() {
//	return txnType;
//}
//
//public void setTxnType(String txnType) {
//	this.txnType = txnType;
//}
//
//public String getInteractiveStatus() {
//	return interactiveStatus;
//}
//
//public void setInteractiveStatus(String interactiveStatus) {
//	this.interactiveStatus = interactiveStatus;
//}
//
//public String getPayerAcctCode() {
//	return payerAcctCode;
//}
//
//public void setPayerAcctCode(String payerAcctCode) {
//	this.payerAcctCode = payerAcctCode;
//}
//
//public String getTerminalId() {
//	return terminalId;
//}
//
//public void setTerminalId(String terminalId) {
//	this.terminalId = terminalId;
//}
//
//public String getExt() {
//	return ext;
//}
//
//public void setExt(String ext) {
//	this.ext = ext;
//}
//
//public String getExt1() {
//	return ext1;
//}
//
//public void setExt1(String ext1) {
//	this.ext1 = ext1;
//}



public void setFeatureCode(String featureCode) {
	this.featureCode = featureCode;
}

}
