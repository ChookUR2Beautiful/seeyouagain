package com.xmniao.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.RandomUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilString;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.WPayService;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.wpay.RequestHandlerV3;
import com.xmniao.wpay.TenpayHttpClientV3;

/**
 * V3版本API 微信退款实现 主要方法：1.退款 2.退款状态查询
 * 
 * 关于退款请求字段的说明： 1.out_trade_no 商户平台内部的订单号 2.transaction_id 财付通平台的交易号 3.out_refund_no 商户平台内部的退款单号 4.refund_id
 * 财付通平台的退款单号
 * 
 * @author ChenBo
 * 
 */
public class WPayV3ServiceImpl implements WPayService {

    // 初始日志类
    private final Logger log = Logger.getLogger(WPayV3ServiceImpl.class);

    @Autowired
    private PayRefundMapper payRefundMapper;

    // 签名方式
    private String signType = "MD5";
    // 签名
    private String sign = "";
    // 商户号
    private String partner = "1266874401";
    // 操作员
    private String opUserId = "1266874401";
    // 请求url
    private String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 密钥
    private String partnerKey = "2c8623ee30a6c6254538036faa0e17d9";
    // 请求方式
    private String requestMethod = "POST";

    // 证书密码
    private String certPassword = "1266874401";
    // 查询URL
    private String queryRefundUrl = "https://api.mch.weixin.qq.com/pay/refundquery";

    private String appid = "wxf22563034b1ca356";

    //微信证书
    private String cretFile="wx_cert_1266874401.p12";
    
    // 0.00
    private final BigDecimal ZERO = new BigDecimal("0.00");

    private final String WX_SUCCESS = "SUCCESS";// —退款成功
    private final String WX_FAIL = "FAIL";// —退款失败
    private final String WX_PROCESSING = "PROCESSING";// —退款处理中
    private final String WX_NOTSURE = "NOTSURE";// —未确定，需要商户原退款单号重新发起

    @Override
    public Map<String, String> wPayRefund(String refundId, String orderId, double refundAmount,
	    double orderAmount) throws FailureException {
	log.info("wPayRefund----->refundId:" + refundId + "----->orderId:" + orderId + "----->refundAmount:"
		+ refundAmount + "----->orderAmount:" + orderAmount);

	Map<String, String> resultMap = new HashMap<String, String>();

	// 财付通的金额单位是分
	int rAmount = yuan2fen(refundAmount);
	int oAmount = yuan2fen(orderAmount);
	if (rAmount > oAmount) {
	    log.error("退款金额不应大于实际支付金额");
	    return returnMap(StateCodeUtil.PR_ORDER_EXCEPTION, "退款金额不应大于实际支付金额");
	}
	// 创建查询请求对象
	RequestHandlerV3 reqHandler = new RequestHandlerV3(null, null);
	// 通信对象
	TenpayHttpClientV3 httpClient = new TenpayHttpClientV3();

	try {
	    // -----------------------------
	    // 设置请求参数
	    // -----------------------------
	    reqHandler.init();
	    reqHandler.setKey(this.getPartnerKey());
	    reqHandler.setGateUrl(this.getRefundUrl().trim());

	    // -----------------------------
	    // 设置接口参数
	    // -----------------------------
	    reqHandler.setParameter("appid", this.appid);
	    reqHandler.setParameter("mch_id", this.getPartner());
	    reqHandler.setParameter("nonce_str", RandomUtil.generateString(32));

	    // 微信订单号
	    //reqHandler.setParameter("transaction_id", orderId);
	    // 支付单号
	    reqHandler.setParameter("out_trade_no", orderId);

	    reqHandler.setParameter("out_refund_no", refundId);
	    reqHandler.setParameter("total_fee", String.valueOf(oAmount));
	    reqHandler.setParameter("refund_fee", String.valueOf(rAmount));
	    reqHandler.setParameter("op_user_id", this.getPartner());
	    // -----------------------------
	    // 设置通信参数
	    // -----------------------------
	    // 设置请求返回的等待时间
	    httpClient.setTimeOut(5);

	    // 设置个人(商户)证书
	    String p12Loc = this.getClass().getResource("/").getPath() + cretFile;
	    p12Loc = URLDecoder.decode(p12Loc, "UTF-8");
	    httpClient.setCertInfo(new File(p12Loc), certPassword);

	    // 设置发送类型POST
	    httpClient.setMethod(this.getRequestMethod());

	    // 设置请求内容
	    String requestUrl = reqHandler.getRequestURL();

	    httpClient.setReqUrl(requestUrl);

	    String requestContent = reqHandler.getRequestXml();
	    httpClient.setReqContent(requestContent);
	    Map<String, Object> rescontent = null;

	    log.info("requestUrl：" + requestUrl);
	    log.info("requestContent：" + requestContent);
	    // boolean test = false;
	    // if(test){
	    // rescontent = call(requestUrl,requestContent);
	    // //System.out.println("rescontent:"+rescontent);
	    // resultMap = dealResponse(rescontent);
	    // }else{
	    // 后台调用
	    if (httpClient.call()) {
		// 设置结果参数
		rescontent = httpClient.getResXmlContent();
		System.out.println("rescontent:" + rescontent);
		resultMap = dealResponse(rescontent);

	    } else {
		// 有可能因为网络原因，请求已经处理，但未收到应答。
		log.error("微信退款网络连接失败");

		resultMap = returnMap(StateCodeUtil.PR_THIRD_NOT_CONNECT, "微信退款，失败");
	    }
	    // }

	} catch (Exception e) {
	    resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "微信退款，执行异常");
	}
	return resultMap;
    }

    // 处理应答
    public Map<String, String> dealResponse(Map<String, Object> resMap) throws Exception {
	log.info("resMap:" + resMap);

	String refundMsg = null;
	if (resMap.get("return_code") != null && resMap.get("return_code").equals(WX_SUCCESS)) {
	    // 本次查询反馈成功
	    if (resMap.get("result_code") != null && resMap.get("result_code").equals(WX_SUCCESS)) {
		boolean isVerify = true;
		if (isVerify) {
		    log.info("退款申请提交成功，返回微信退款单号:" + resMap.get("refund_id"));
		    return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "退款提交成功");
		} else {
		    refundMsg = "返回结果签名验证不通过";
		}

	    } else {
		// 本次查询反馈失败
		refundMsg = (UtilString.stringIsNotBlank(resMap.get("err_code").toString()) ? resMap.get(
			"err_code").toString() : "不详")
			+ " - "
			+ (UtilString.stringIsNotBlank(resMap.get("err_code_des").toString()) ? resMap.get(
				"err_code_des").toString() : "不详");
	    }
	} else {
	    // 本次查询反馈失败
	    refundMsg = UtilString.stringIsNotBlank(resMap.get("return_msg").toString()) ? resMap.get(
		    "return_msg").toString() : "不详";
	}
	log.error("退款申请失败,失败原因：" + refundMsg);
	return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, refundMsg == null ? "退款失败" : refundMsg);
    }

    public Map<String, String> returnMap(String code, String msg) {
	return this.returnMap(code, msg, "");
    }

    // 返回Map结果
    private Map<String, String> returnMap(String code, String msg, String response) {
	Map<String, String> resultMap = new HashMap<String, String>();
	resultMap.put("code", code);
	resultMap.put("Msg", msg);
	resultMap.put("response", response);
	return resultMap;
    }

    // 回应
    private void doResponse(String response) {
	// out.println(response);
    }

    /**
     * 通过财付通服务器查询退款状态 1.transaction_id 财付通平台的交易号 2.refund_id 财付通平台的退款单号 3.out_trade_no 商户平台内部的订单号
     * 4.out_refund_no 商户平台内部的退款单号
     */
    @Override
    public Map<String, String> QueryWPayRefund(String id, int type) {

	log.info("QueryWPayRefund----->id:" + id + "----->id:" + type);
	Map<String, String> resultMap = new HashMap<String, String>();

	if (StringUtils.isBlank(id)) {
	    return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "查询参数不能为空!");
	}
	if (type > 4 || type < 1) {
	    return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "参数类型错误!");
	}

	// 创建查询请求对象
	RequestHandlerV3 reqHandler = new RequestHandlerV3(null, null);
	// 通信对象
	TenpayHttpClientV3 httpClient = new TenpayHttpClientV3();

	try {
	    // -----------------------------
	    // 设置请求参数
	    // -----------------------------
	    reqHandler.init();
	    reqHandler.setKey(this.getPartnerKey());
	    reqHandler.setGateUrl(this.getQueryRefundUrl().trim());
	    // -----------------------------
	    // 设置接口参数
	    // -----------------------------
	    reqHandler.setParameter("appid", this.appid);
	    reqHandler.setParameter("mch_id", this.getPartner());
	    reqHandler.setParameter("nonce_str", RandomUtil.generateString(32));
	    switch (type) {
	    case 1:
		reqHandler.setParameter("out_refund_no", "");
		reqHandler.setParameter("out_trade_no", "");
		reqHandler.setParameter("refund_id", "");
		reqHandler.setParameter("transaction_id", id);
		break;
	    case 2:
		reqHandler.setParameter("out_refund_no", "");
		reqHandler.setParameter("out_trade_no", "");
		reqHandler.setParameter("refund_id", id);
		reqHandler.setParameter("transaction_id", "");
		break;
	    case 3:
		reqHandler.setParameter("out_refund_no", "");
		reqHandler.setParameter("out_trade_no", id);
		reqHandler.setParameter("refund_id", "");
		reqHandler.setParameter("transaction_id", "");
		break;
	    case 4:
		reqHandler.setParameter("out_refund_no", id);
		reqHandler.setParameter("out_trade_no", "");
		reqHandler.setParameter("refund_id", "");
		reqHandler.setParameter("transaction_id", "");
		break;
	    default:
		break;
	    }

	    // -----------------------------
	    // 设置通信参数
	    // -----------------------------
	    // 设置请求返回的等待时间
	    httpClient.setTimeOut(5);

	    // 设置发送类型POST
	    httpClient.setMethod(this.getRequestMethod());

	    // 设置请求内容
	    String requestUrl = reqHandler.getRequestURL();

	    httpClient.setReqUrl(requestUrl);

	    String requestContent = reqHandler.getRequestXml();
	    httpClient.setReqContent(requestContent);
	    Map<String, Object> rescontent = null;

	    // 后台调用
	    if (httpClient.call()) {
		// 设置结果参数
		rescontent = httpClient.getResXmlContent();
		System.out.println("rescontent:" + rescontent);
		resultMap = dealQueryResponse(rescontent);

	    } else {
		// 有可能因为网络原因，请求已经处理，但未收到应答。
		log.error("本次查询微信退款状态失败");
		resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "");
	    }
	} catch (Exception e) {
	    // 更新退款状态
	    log.error("查询微信退款执行异常！", e);
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "查询微信退款状态，执行异常");
	}
	return resultMap;

    }

    // 处理应答
    public Map<String, String> dealQueryResponse(Map<String, Object> resMap) throws Exception {
	log.info("resMap:" + resMap);
	String refundMsg = null;
	if (resMap.get("return_code") != null && resMap.get("return_code").equals(WX_SUCCESS)) {
	    // 本次查询反馈成功
	    if (resMap.get("result_code") != null && resMap.get("result_code").equals(WX_SUCCESS)) {
		boolean isVerify = true;
		if (isVerify) {
		    int refundCount = resMap.get("refund_count") == null ? 0 : Integer.parseInt(resMap.get(
			    "refund_count").toString());
		    if (refundCount == 1) {
			String refundStatus = resMap.get("refund_status_0").toString();
			if (refundStatus == null) {
			    refundMsg = "未知退款状态";
			} else if (refundStatus.equals(WX_SUCCESS)) {
			    // 成功
			    log.info("退款成功");
			    return returnMap(StateCodeUtil.PR_SUCCESS, "退款成功");
			} else if (refundStatus.equals(WX_FAIL)) {
			    // 失败
			    log.info("退款失败");
			    return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, "退款失败");
			} else if (refundStatus.equals(WX_PROCESSING)) {
			    // 退款中
			    refundMsg = "退款处理中";
			    log.info(refundMsg);
			    return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, refundMsg);
			} else if (refundStatus.equals(WX_NOTSURE)) {
			    // 不确定
			    refundMsg = "未知退款状态";
			} else {
			    // 其他
			    refundMsg = "未知退款状态";
			}
		    } else {
			refundMsg = "该支付订单的退款单总比次不为1";
		    }
		} else {
		    refundMsg = "查询结果签名验证不通过";
		}

	    } else {
		// 本次查询反馈失败
		refundMsg = (UtilString.stringIsNotBlank(resMap.get("err_code").toString()) ? resMap.get(
			"err_code").toString() : "不详")
			+ " - "
			+ (UtilString.stringIsNotBlank(resMap.get("err_code_des").toString()) ? resMap.get(
				"err_code_des").toString() : "不详");
	    }
	} else {
	    // 本次查询反馈失败
	    refundMsg = (UtilString.stringIsNotBlank(resMap.get("return_msg").toString()) ? resMap.get(
		    "return_msg").toString() : "不详");
	}
	log.error("本次查询反馈失败,失败原因：" + refundMsg);
	return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, refundMsg == null ? "" : refundMsg);
    }

    public Map<String, String> analyseResponseOrder(String refundId, String refund_fee, String refund_status)
	    throws Exception {
	Map<String, String> resultMap = new HashMap<String, String>();
	String returnMsg = "";
	/*
	 * 退款状态 refund_status 4，10：退款成功。 3，5，6：退款失败。 8，9，11:退款处理中。 1，2: 未确定，需要商户原退款单号重新发起。
	 * 7：转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
	 */
	// 查询相关记录
	PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
	if (payRefund == null) {
	    log.error("找不到对应的退款记录：" + refundId);
	    return returnMap(StateCodeUtil.PR_NOREFUND_RECORE, "找不到对应的退款记录：" + refundId);
	}
	if (payRefund.getStatus() == PayRefundStatus.PROCESS_SUCCESS) {
	    log.info("该退款记录已完成退款：" + refundId);
	    return returnMap(StateCodeUtil.PR_REFUNDED, "该退款记录已完成退款:" + refundId);
	}

	// int updateStatus = PayRefundStatus.PROCESS_Failure;
	this.doResponse("商户退款单号" + refundId + "的退款状态是：" + refund_status);
	int refundStatus = Integer.parseInt(refund_status);
	switch (refundStatus) {
	case 4:
	case 10:
	    log.info("微信支付退款成功");

	    BigDecimal refundAmount = payRefund.getSamount() == null ? ZERO : payRefund.getSamount();
	    String strAmount = refundAmount.multiply(new BigDecimal(100)).intValue() + "";

	    log.info("refund_fee:" + refund_fee + ",strAmount:" + strAmount);
	    if (refund_fee.equalsIgnoreCase(strAmount)) {
		resultMap = returnMap(StateCodeUtil.PR_SUCCESS, "");
	    } else {
		log.error("微信支付退款金额不一致 " + refund_fee + "," + strAmount);
		resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "微信支付退款金额不一致" + refund_fee + ","
			+ strAmount);
	    }
	    break;
	case 3:
	case 5:
	case 6:
	    log.info("微信支付退款失败");
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, returnMsg);
	    break;
	case 8:
	case 9:
	case 11:
	    log.info("微信支付退款处理中");
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, returnMsg);
	    break;
	case 1:
	case 2:
	    log.info("微信支付退款，未确定，请重新申请退款");
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_UNKOWN, returnMsg);
	    break;
	case 7:
	    log.info("微信支付退款，转入代发，请人工干预");
	    resultMap = returnMap(StateCodeUtil.PR_REFUND_TO_XMN, returnMsg);
	    break;
	default:
	    log.info("微信支付退款，未定义的结果状态：" + refundStatus);
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, returnMsg);
	    break;
	}

	return resultMap;
    }

    /*
     * 查询用
     */
    public Map<String, String> analyseResponseOrderByQuery(String refundId, String refund_fee,
	    String refund_status) throws Exception {
	Map<String, String> resultMap = new HashMap<String, String>();
	String returnMsg = "";
	/*
	 * 退款状态 refund_status 4，10：退款成功。 3，5，6：退款失败。 8，9，11:退款处理中。 1，2: 未确定，需要商户原退款单号重新发起。
	 * 7：转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
	 */
	// 查询相关记录
	PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
	if (payRefund == null) {
	    log.error("找不到对应的退款记录：" + refundId);
	    return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "找不到对应的退款记录：" + refundId);
	}
	if (payRefund.getStatus() == PayRefundStatus.PROCESS_SUCCESS) {
	    log.info("该退款记录已完成退款：" + refundId);
	    return returnMap(StateCodeUtil.PR_SUCCESS, "该退款记录已完成退款:" + refundId);
	}

	// int updateStatus = PayRefundStatus.PROCESS_Failure;
	this.doResponse("商户退款单号" + refundId + "的退款状态是：" + refund_status);
	int refundStatus = Integer.parseInt(refund_status);
	switch (refundStatus) {
	case 4:
	case 10:
	    log.info("微信支付退款成功");

	    BigDecimal refundAmount = payRefund.getSamount() == null ? ZERO : payRefund.getSamount();
	    String strAmount = refundAmount.multiply(new BigDecimal(100)).intValue() + "";

	    log.info("refund_fee:" + refund_fee + ",strAmount:" + strAmount);
	    if (refund_fee.equalsIgnoreCase(strAmount)) {
		resultMap = returnMap(StateCodeUtil.PR_SUCCESS, "");
	    } else {
		log.error("微信支付退款金额不一致 " + refund_fee + "," + strAmount);
		resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "微信支付退款金额不一致" + refund_fee + ","
			+ strAmount);
	    }
	    break;
	case 3:
	case 5:
	case 6:
	    log.info("微信支付退款失败");
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, returnMsg);
	    break;
	case 8:
	case 9:
	case 11:
	    log.info("微信支付退款处理中");
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, returnMsg);
	    break;
	case 1:
	case 2:
	    log.info("微信支付退款，未确定，请重新申请退款");
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, returnMsg);
	    break;
	case 7:
	    log.info("微信支付退款，转入代发，请人工干预");
	    resultMap = returnMap(StateCodeUtil.PR_REFUND_TO_XMN, returnMsg);
	    break;
	default:
	    log.info("微信支付退款，未定义的结果状态：" + refundStatus);
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, returnMsg);
	    break;
	}

	return resultMap;
    }

    public String getAppid() {
	return appid;
    }

    public void setAppid(String appid) {
	this.appid = appid;
    }

    public String getSignType() {
	return signType;
    }

    public void setSignType(String signType) {
	this.signType = signType;
    }

    public String getSign() {
	return sign;
    }

    public void setSign(String sign) {
	this.sign = sign;
    }

    public String getPartner() {
	return partner;
    }

    public void setPartner(String partner) {
	this.partner = partner;
    }

    public String getOpUserId() {
	return opUserId;
    }

    public void setOpUserId(String opUserId) {
	this.opUserId = opUserId;
    }

    public String getRefundUrl() {
	return refundUrl;
    }

    public void setRefundUrl(String refundUrl) {
	this.refundUrl = refundUrl;
    }

    public String getPartnerKey() {
	return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
	this.partnerKey = partnerKey;
    }

    public Logger getLog() {
	return log;
    }

    public String getRequestMethod() {
	return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
	this.requestMethod = requestMethod;
    }

    public String getCertPassword() {
	return certPassword;
    }

    public void setCertPassword(String certPassword) {
	this.certPassword = certPassword;
    }

    public String getQueryRefundUrl() {
	return queryRefundUrl;
    }

    public void setQueryRefundUrl(String queryRefundUrl) {
	this.queryRefundUrl = queryRefundUrl;
    }

    public String getCretFile() {
		return cretFile;
	}

	public void setCretFile(String cretFile) {
		this.cretFile = cretFile;
	}

	// 元->分
    public static int yuan2fen(double orderAmount) {
	BigDecimal b1 = new BigDecimal(Double.toString(orderAmount));
	BigDecimal b2 = new BigDecimal(Double.toString(100));
	return b1.multiply(b2).intValue();
    }

	@Override
	public String getWxPartner() {
		return partner;
	}
    // public Map call(String url, String arrayToXml) throws Exception{
    //
    // Map doXMLtoMap=new HashMap();
    // KeyStore keyStore = KeyStore.getInstance("PKCS12");
    // FileInputStream instream = new FileInputStream(new
    // File(this.getClass().getResource("/").getPath()+"apiclient_cert.p12"));
    // try {
    // keyStore.load(instream, certPassword.toCharArray());
    // } finally {
    // instream.close();
    // }
    // SSLContext sslcontext = SSLContexts.custom()
    // .loadKeyMaterial(keyStore, certPassword.toCharArray())
    // .build();
    // // Allow TLSv1 protocol only
    // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
    // sslcontext,
    // new String[] { "TLSv1" },
    // null,
    // SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    // CloseableHttpClient httpclient = HttpClients.custom()
    // .setSSLSocketFactory(sslsf)
    // .build();
    // try {
    // // HttpGet httpget = new HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");
    // HttpPost httpPost = new HttpPost(url);
    // httpPost.setEntity(new StringEntity(arrayToXml, "UTF-8"));
    //
    // System.out.println(httpPost.getURI());
    //
    // System.out.println("executing request" + httpPost.getRequestLine());
    //
    // CloseableHttpResponse response = httpclient.execute(httpPost);
    //
    // String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
    // doXMLtoMap = XMLUtil.doXMLParse(jsonStr);
    // System.out.println(jsonStr);
    //
    // response.close();
    //
    // } finally {
    //
    // httpclient.close();
    // }
    // return doXMLtoMap;
    //
    // }

    public static void main(String[] args) throws Exception {
	Map resultMap = null;
	WPayV3ServiceImpl wRefund = new WPayV3ServiceImpl();
	//resultMap = wRefund.wPayRefund("1602260946498261", "1602260946498261", 399.32, 399.32);
	resultMap = wRefund.QueryWPayRefund("1006260461201603073797026733", 1);
	System.out.println(resultMap);
	// wRefund.setQueryRefundUrl("https://gw.tenpay.com/gateway/normalrefundquery.xml");
	// wRefund.setSignType("MD5");
	// wRefund.setPartnerKey("aa0837afd1bcf472b26e4843e09e7883");
	//
	// wRefund.QueryWPayRefund("1505111701401235", 1);
    }

}
