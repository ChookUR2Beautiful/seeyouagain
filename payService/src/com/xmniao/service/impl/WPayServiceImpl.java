package com.xmniao.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.MD5;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.WPayService;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.wpay.ClientResponseHandler;
import com.xmniao.wpay.RequestHandler;
import com.xmniao.wpay.TenpayHttpClient;

/**
 * 微信退款实现 主要方法：1.退款 2.退款状态查询
 * 
 * 关于退款请求字段的说明： 1.out_trade_no 商户平台内部的订单号 2.transaction_id 财付通平台的交易号 3.out_refund_no 商户平台内部的退款单号 4.refund_id
 * 财付通平台的退款单号
 * 
 * @author ChenBo
 * 
 */
public class WPayServiceImpl implements WPayService {

    // 初始日志类
    private final Logger log = Logger.getLogger(WPayServiceImpl.class);

    @Autowired
    private PayRefundMapper payRefundMapper;

    // 签名方式
    private String signType;
    // 签名
    private String sign;
    // 商户号
    private String partner;
    // 接口版本
    private String refundVersion;
    // 操作员
    private String opUserId;
    // 登录密码
    private String passwrod;
    // 请求url
    private String refundUrl;
    // 密钥
    private String partnerKey;
    // 字符集
    private String inputCharset;
    // 密钥序号
    private String signKeyIndex;
    // 请求方式
    private String requestMethod;
    // 证书密码
    private String certPassword;
    // 查询URL
    private String queryRefundUrl;

    // 返回处理代码 0---成功
    private final String RET_SUCCESS = "0";
    // 0.00
    private final BigDecimal ZERO = new BigDecimal("0.00");

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
	RequestHandler reqHandler = new RequestHandler(null, null);
	// 通信对象
	TenpayHttpClient httpClient = new TenpayHttpClient();

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
	    reqHandler.setParameter("service_version", this.getRefundVersion());
	    reqHandler.setParameter("partner", this.getPartner());
	    reqHandler.setParameter("transaction_id", orderId);
	    reqHandler.setParameter("out_refund_no", refundId);
	    reqHandler.setParameter("total_fee", String.valueOf(oAmount));
	    reqHandler.setParameter("refund_fee", String.valueOf(rAmount));
	    reqHandler.setParameter("op_user_id", this.getPartner());
	    // 操作员密码,MD5处理
	    reqHandler.setParameter("op_user_passwd", MD5.Encode(this.getPasswrod(), this.getInputCharset()));

	    reqHandler.setParameter("recv_user_id", "");
	    reqHandler.setParameter("reccv_user_name", "");
	    // -----------------------------
	    // 设置通信参数
	    // -----------------------------
	    // 设置请求返回的等待时间
	    httpClient.setTimeOut(5);
	    // 设置ca证书
	    String caLoc = this.getClass().getResource("/").getPath() + "cacert1.pem";
	    caLoc = URLDecoder.decode(caLoc, "UTF-8");
	    httpClient.setCaInfo(new File(caLoc));

	    // 设置个人(商户)证书
	    String pfxLoc = this.getClass().getResource("/").getPath() + "1224582201_20160226135836.pfx";
	    pfxLoc = URLDecoder.decode(pfxLoc, "UTF-8");
	    httpClient.setCertInfo(new File(pfxLoc), certPassword);

	    // 设置发送类型POST
	    httpClient.setMethod(this.getRequestMethod());

	    // 设置请求内容
	    String requestUrl = reqHandler.getRequestURL();

	    httpClient.setReqContent(requestUrl);
	    String rescontent = "null";

	    log.info("requestUrl：" + requestUrl);

	    // 后台调用
	    if (httpClient.call()) {
		// 设置结果参数
		rescontent = httpClient.getResContent();
		resultMap = dealResponse(rescontent);

	    } else {
		// 有可能因为网络原因，请求已经处理，但未收到应答。
		log.error("微信退款网络连接失败");

		resultMap = returnMap(StateCodeUtil.PR_THIRD_NOT_CONNECT, "微信退款，失败");
	    }

	} catch (Exception e) {
	    resultMap = returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "微信退款，执行异常");
	}
	return resultMap;
    }

    // 处理应答
    public Map<String, String> dealResponse(String rescontent) throws Exception {
	Map<String, String> resultMap = new HashMap<String, String>();
	log.info("rescontent:" + rescontent);

	// 应答对象
	ClientResponseHandler resHandler = new ClientResponseHandler();
	resHandler.setContent(rescontent);
	resHandler.setKey(this.getPartnerKey());

	// 获取返回参数
	String retcode = resHandler.getParameter("retcode");
	String refund_status = resHandler.getParameter("refund_status");
	String refundId = resHandler.getParameter("out_refund_no");
	String refund_fee = resHandler.getParameter("refund_fee");
	String returnMsg = "retcode:" + resHandler.getParameter("retcode") + " retmsg:"
		+ resHandler.getParameter("retmsg");
	// 判断签名及结果
	if (resHandler.isTenpaySign() && RET_SUCCESS.equals(retcode)) {

	    resultMap = analyseResponseOrder(refundId, refund_fee, refund_status);
	    return resultMap;

	} else {
	    // 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
	    this.doResponse("retcode:" + resHandler.getParameter("retcode") + " retmsg:"
		    + resHandler.getParameter("retmsg"));
	    // 更新退款状态
	    log.error("微信退款失败," + returnMsg);
	    return returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, returnMsg);
	}
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
	RequestHandler reqHandler = new RequestHandler(null, null);
	// 通信对象
	TenpayHttpClient httpClient = new TenpayHttpClient();

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
	    reqHandler.setParameter("partner", this.getPartner());
	    switch (type) {
	    case 1:
		reqHandler.setParameter("transaction_id", id);
		break;
	    case 2:
		reqHandler.setParameter("refund_id", id);
		break;
	    case 3:
		reqHandler.setParameter("out_trade_no", id);
		break;
	    case 4:
		reqHandler.setParameter("out_refund_no", id);
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

	    httpClient.setReqContent(requestUrl);
	    String rescontent = "null";

	    // 后台调用
	    if (httpClient.call()) {
		// 设置结果参数
		rescontent = httpClient.getResContent();
		resultMap = dealQueryResponse(rescontent);

	    } else {
		// 有可能因为网络原因，请求已经处理，但未收到应答。
		log.error("本次查询微信退款状态失败");

		resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "");
	    }

	    // 获取debug信息,建议把请求、应答内容、debug信息，通信返回码写入日志，方便定位问题
	} catch (Exception e) {
	    // 更新退款状态
	    log.error("查询微信退款执行异常！", e);
	    resultMap = returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "查询微信退款状态，执行异常");
	}
	return resultMap;

    }

    // 处理应答
    public Map<String, String> dealQueryResponse(String rescontent) throws Exception {
	Map<String, String> resultMap = new HashMap<String, String>();
	log.info("rescontent:" + rescontent);

	// 应答对象
	ClientResponseHandler resHandler = new ClientResponseHandler();
	resHandler.setContent(rescontent);
	resHandler.setKey(this.getPartnerKey());

	// 获取返回参数
	String retcode = resHandler.getParameter("retcode");

	String returnMsg = "retcode:" + resHandler.getParameter("retcode") + " retmsg:"
		+ resHandler.getParameter("retmsg");

	// 判断签名及结果
	if (resHandler.isTenpaySign() && RET_SUCCESS.equals(retcode)) {
	    String refund_count = resHandler.getParameter("refund_count");
	    int refundCount = Integer.parseInt(refund_count);
	    log.info("共返回" + refundCount + "笔退款状态");

	    for (int i = 0; i < refundCount; i++) {
		String wPayRefundNo = "out_refund_no_" + i;
		String wPayRefundFee = "refund_fee_" + i;
		String wPayRefundState = "refund_state_" + i;

		String refundId = resHandler.getParameter(wPayRefundNo);
		String refund_fee = resHandler.getParameter(wPayRefundFee);
		String refund_status = resHandler.getParameter(wPayRefundState);

		resultMap = analyseResponseOrderByQuery(refundId, refund_fee, refund_status);
		if (StringUtils.isBlank(resultMap.get("Msg"))) {
		    resultMap.put("Msg", returnMsg);
		}

		log.info(resultMap.toString());
	    }
	    return resultMap;

	} else {
	    // 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
	    this.doResponse("retcode:" + resHandler.getParameter("retcode") + " retmsg:"
		    + resHandler.getParameter("retmsg"));
	    // 更新退款状态
	    log.error("查询微信退款，退款还未成功--"+resHandler.getParameter("retmsg"));
	    return returnMap(StateCodeUtil.PR_THIRD_REFUNDING, returnMsg);
	}
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

    public String getRefundVersion() {
	return refundVersion;
    }

    public void setRefundVersion(String refundVersion) {
	this.refundVersion = refundVersion;
    }

    public String getOpUserId() {
	return opUserId;
    }

    public void setOpUserId(String opUserId) {
	this.opUserId = opUserId;
    }

    public String getPasswrod() {
	return passwrod;
    }

    public void setPasswrod(String passwrod) {
	this.passwrod = passwrod;
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

    public String getInputCharset() {
	return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
	this.inputCharset = inputCharset;
    }

    public String getSignKeyIndex() {
	return signKeyIndex;
    }

    public void setSignKeyIndex(String signKeyIndex) {
	this.signKeyIndex = signKeyIndex;
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
	
    public static void main(String[] args) throws Exception {
	WPayServiceImpl wRefund = new WPayServiceImpl();
	wRefund.setQueryRefundUrl("https://gw.tenpay.com/gateway/normalrefundquery.xml");
	wRefund.setSignType("MD5");
	wRefund.setPartnerKey("aa0837afd1bcf472b26e4843e09e7883");
	wRefund.setRefundUrl("https://api.mch.tenpay.com/refundapi/gateway/refund.xml");
	wRefund.setRefundVersion("1.1");
	wRefund.setPartner("1224582201");
	wRefund.setOpUserId("1224582201");
	wRefund.setPasswrod("cf710314");
	wRefund.setInputCharset("GBK");
	wRefund.setSignKeyIndex("1");
	wRefund.setRequestMethod("POST");
	wRefund.setCertPassword("430128");

	// wRefund.QueryWPayRefund("1505111701401235", 1);
	wRefund.wPayRefund("1602260946498262", "1224582201391602211139794983", 399.32, 399.32);
    }
}
