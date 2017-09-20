package com.xmniao.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.umpay.api.common.ReqData;
import com.umpay.api.exception.ParameterCheckException;
import com.umpay.api.exception.ReqDataException;
import com.umpay.api.exception.RetDataException;
import com.umpay.api.exception.VerifyException;
import com.umpay.api.paygate.v40.Mer2Plat_v40;
import com.umpay.api.paygate.v40.Plat2Mer_v40;
import com.xmniao.common.HttpRequest;
import com.xmniao.common.PayConstants;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.RefundService;
import com.xmniao.service.UPayService;
import com.xmniao.service.WalletService;
import com.xmniao.thrift.ledger.FailureException;

public class UPayServiceImpl implements UPayService {

	// 日志
	private static final Logger log = LoggerFactory
			.getLogger(UPayServiceImpl.class);

	/** 商户号 */
	private String merId = "9495";

	/** 退款通知url */
	@Resource(name = "notifyServiceUrl")
	private String notifyUrl;

	private String signType;
	private String charset;
	private String version;
	private String resFormat;
	private String service;
	
	//--------------查询添加
	private String queryService = "transfer_query";

    private static final String XMN_REFUND_URL = "/xmn/uRefundNotify";
    
    private static final String FRESH_REFUND_URL = "/fresh/uRefundNotify";
	//-----------------
	private final BigDecimal hundred = new BigDecimal("100");

	@Autowired
	private PayRefundMapper payRefundMapper;

	@Autowired
	private WalletService walletService;

	@Autowired
	private RefundService refundService;

	@Autowired
	private PayOrderMapper payOrderMapper;

	@Autowired
	private UpdateWithdrawalsRecordStateMapper recordStateMapper;

	/**
	 * u付退款方法
	 */
	@Override
	public Map<String, String> uPayRefund(String refundId, String orderId,
			String merDate, double refundAmount, double orderAmount,int serviceType)
			throws FailureException {

		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("code", StateCodeUtil.PR_SYSTEM_ERROR);
		resultMap.put("Msg", "");
		resultMap.put("response", "");

		Map<String, String> map = new HashMap<String, String>();
		map.put("service", "mer_refund");
		map.put("sign_type", "RSA");
		map.put("charset", "UTF-8");
		map.put("mer_id", merId);
		map.put("notify_url",
				generateActionUrl(serviceType));
		map.put("version", "4.0");
		map.put("refund_no", refundId); // 退款流水号
		// 由商户生成的退款流水，规则是“YYMMDDHHMMSS+4位序列数”，序列数不足4位的前补0
		map.put("order_id", orderId); // 商户订单号
		map.put("mer_date", merDate); // 商户订单日期 yyyyMMdd
		map.put("refund_amount",
				hundred.multiply(BigDecimal.valueOf(refundAmount)).setScale(0)
						.toString()); // 退款金额 以分为单位
		map.put("org_amount", hundred.multiply(BigDecimal.valueOf(orderAmount))
				.setScale(0).toString()); // 原订单金额 以分为单位

		try {
			ReqData result = Mer2Plat_v40.ReqDataByGet(map);
			GetMethod method = new GetMethod(result.getUrl());
			HttpClient client = new HttpClient();

			if (client.executeMethod(method) == HttpStatus.SC_OK) {
				@SuppressWarnings("unchecked")
				Map<String, String> ret = Plat2Mer_v40.getResData(method
						.getResponseBodyAsString());
				if ("0000".equals(ret.get("ret_code"))
						|| "00060861".equalsIgnoreCase(ret.get("ret_code"))) {
					resultMap.put("code", StateCodeUtil.PR_THIRD_REFUNDING);
					resultMap.put("Msg", "");
					resultMap.put("response", "");
				} else {
					resultMap.put("code", StateCodeUtil.PR_THIRD_SERVER_FAIL);
					resultMap.put("Msg", "[U付提示]" + ret.get("ret_msg"));
				}
			} else {
				resultMap.put("Msg", "u付提交异常");
			}
		} catch (IOException e) {
			resultMap.put("Msg", "IOException");
		} catch (RetDataException | ReqDataException e) {
			resultMap.put("Msg", "RetDataException | ReqDataException");
		} catch (Exception e) {
			resultMap.put("Msg", "Exception");
		}
		return resultMap;

	}

	// U付退款查询方法
	@Override
	public Map<String, String> queryUpayRefund(String refundId) {
		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("code", StateCodeUtil.PR_THIRD_REFUNDING);
		resultMap.put("Msg", "");
		resultMap.put("response", "");

		Map<String, String> map = new HashMap<String, String>();
		map.put("service", "mer_refund_query");
		map.put("sign_type", "RSA");
		map.put("charset", "UTF-8");
		map.put("mer_id", merId);
		map.put("version", "4.0");
		map.put("refund_no", refundId); // 退款流水号
		// 由商户生成的退款流水，规则是“YYMMDDHHMMSS+4位序列数”，序列数不足4位的前补0
		try {
			ReqData result = Mer2Plat_v40.ReqDataByGet(map);
			GetMethod method = new GetMethod(result.getUrl());
			HttpClient client = new HttpClient();

			if (client.executeMethod(method) == HttpStatus.SC_OK) {
				@SuppressWarnings("unchecked")
				Map<String, String> ret = Plat2Mer_v40.getResData(method
						.getResponseBodyAsString());
				log.info("请求结果：" + ret);
				String refundState = ret.get("refund_state");
				String retMsg = ret.get("ret_msg");
				
				resultMap.put("Msg", retMsg);
				if ("0000".equals(ret.get("ret_code"))) {

					log.info("U付退款查询成功，退款处理结果：" + ret.get("refund_state"));
					if("REFUND_SUCCESS".equalsIgnoreCase(refundState)){
						log.info("查询结果：退款成功");
						resultMap.put("code", StateCodeUtil.PR_SUCCESS);
					}else if("REFUND_CLOSE".equalsIgnoreCase(refundState)){
						log.info("查询结果：退款失败");
						resultMap.put("code", StateCodeUtil.PR_THIRD_SERVER_FAIL);
					}else{
						
					}
					
				} else {
				}
			} else {
				resultMap.put("Msg", "u付查询失败");
			}
		} catch (IOException e) {
			log.error("查询退款状态异常",e);
			resultMap.put("Msg", "查询退款状态异常IOException");
		} catch (RetDataException | ReqDataException e) {
			log.error("查询退款状态异常",e);
			resultMap.put("Msg", "查询退款状态异常 RetDataException | ReqDataException");
		} catch (Exception e) {
			log.error("查询退款状态异常",e);
			resultMap.put("Msg", "查询退款状态异常Exception");
		}
		return resultMap;
	}

	/**
	 * 退钱包支付部分的钱到 钱包
	 * 
	 * @param payRefund
	 * @return
	 */
	@Override
	public Map<String, String> RefoundWalletMoney(PayRefund payRefund) {
		try {
			int result = refundService.getAccordRefundWallet(payRefund.getBid().toString());
			if(result==0){
			return refundService.updateWallet(payRefund);
			}else{
				if(result ==1){
					log.error("该订单已取消支付，不退钱包支付部分金额");
					//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
					//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
				}else if(result==2){
					log.error("该订单尚未消费，不允许退钱包支付部分金额 ");
					//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
					//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
				}else{
					log.error("该订单不允许退钱包支付部分金额");
					//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
					//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
				}
				
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("code", StateCodeUtil.PR_SUCCESS);
				resultMap.put("Msg", "");
				resultMap.put("response", "");
				return resultMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("code", StateCodeUtil.PR_SYSTEM_ERROR);
			resultMap.put("Msg", "");
			resultMap.put("response", "");
			return resultMap;
		}
		// Map<String, String> resultMap = new HashMap<String, String>();
		// try {
		// Map<String, Object> map = new HashMap<String, Object>();
		//
		// if (ZERO.compareTo(payRefund.getAmount()) != 0) {
		// // 使用了钱包支付,将该部分金额退还给钱包
		// map.put("amount", payRefund.getAmount());
		// }
		// if (ZERO.compareTo(payRefund.getBalance()) != 0) {
		// // 使用了佣金支付,将该部分金额退还给佣金
		// map.put("balance", payRefund.getBalance());
		// }
		// if (ZERO.compareTo(payRefund.getCommision()) != 0) {
		// // 使用了返利支付,将该部分金额退还给返利
		// map.put("commision", payRefund.getCommision());
		// }
		//
		// if (map.size() != 0) {
		// map.put("accountid", payRefund.getAccountid());
		//
		// // 退返钱包支付金额
		// Map<String, Object> walletMap = new HashMap<String, Object>();
		// walletMap.put("accountid", payRefund.getAccountid());
		// walletMap.put("amount", payRefund.getAmount());
		// walletMap.put("balance", payRefund.getBalance());
		// walletMap.put("commision", payRefund.getCommision());
		// walletMap.put("refunddi",
		// payRefund.getRefundId());//退款流水号，添加修改钱包记录时，需做为备注信息记录下来
		// walletService.updateWalletAmount(walletMap);
		//
		// resultMap.put("code", StateCodeUtil.PR_SUCCESS);
		// resultMap.put("Msg", "");
		// resultMap.put("response", "");
		// }
		// } catch (Exception e) {
		// resultMap.put("code", StateCodeUtil.PR_SYSTEM_ERROR);
		// resultMap.put("Msg", "");
		// resultMap.put("response", "");
		// }
		// return resultMap;
	}

	/**
	 * 将链接拼成跟action(支付/充值/页面回调相关的)的链接
	 * 
	 * @param baseUrl
	 * @param action
	 * @return
	 */
	private String generateActionUrl(int serviceType) {
		String nUrl ="";
		if(0==serviceType){
			nUrl = notifyUrl+XMN_REFUND_URL;
		}else if(1 == serviceType){
			nUrl = notifyUrl+FRESH_REFUND_URL;
		}
		return nUrl;
	}

	/**
	 * u付验签方法
	 * 
	 * @param req
	 * @return
	 * @throws VerifyException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> virifySign(HttpServletRequest req)
			throws VerifyException {
		return Plat2Mer_v40.getPlatNotifyData(req);
	}

	/**
	 * 商户 回应 U付网关
	 * 
	 * @param ret_code
	 * @param ret_msg
	 * @return
	 */
	@Override
	public String merRespUmp(String ret_code, String ret_msg) {
		Map<String, String> map = new HashMap<>();
		map.put("mer_id", merId);
		map.put("sign_type", "RSA");
		map.put("version", "4.0");
		map.put("ret_code", ret_code);
		map.put("ret_msg", ret_msg);

		String content = null;
		try {
			content = Mer2Plat_v40.merNotifyResData(map);
		} catch (ParameterCheckException e) {
			log.error("", e);
		}
		return String.format(
				"<META NAME=\"MobilePayPlatform\" CONTENT=\"%s\">", content);
	}

	/**
	 * 代发回应 U付网关
	 * 
	 * @param ret_code
	 * @param ret_msg
	 * @return
	 */
	@Override
	public String payRespUmp(String ret_code, String ret_msg, String orderId,
			String merDate) {
		Map<String, String> map = new HashMap<>();
		map.put("mer_id", merId);
		map.put("sign_type", "RSA");
		map.put("version", "4.0");

		map.put("order_id", orderId);
		map.put("mer_date", merDate);
		map.put("ret_code", ret_code);
		map.put("ret_msg", ret_msg);

		String content = null;
		try {
			content = Mer2Plat_v40.merNotifyResData(map);
		} catch (ParameterCheckException e) {
			log.error("", e);
		}
		return String.format(
				"<META NAME=\"MobilePayPlatform\" CONTENT=\"%s\">", content);
	}

	/**
	 * 根据退款流水号 查询该 退款记录
	 */
	@Override
	public PayRefund getPayRefundByRefundId(String refundid) {

		return payRefundMapper.getPayRefundByRefundId(refundid);
	}

	/**
	 * 根据订单号，查询相关支付订单表数据
	 */
	@Override
	public Map<String, Object> getPayOrderByorderNumber(String order_number) {

		return payOrderMapper.getPayOrderByorderNumber(order_number);
	}

	/**
	 * 根据支付号 查询该 退款记录
	 * 
	 * @param payid
	 * @return
	 */
	@Override
	public PayRefund getPayRefundBypayId(long payid) {

		return payRefundMapper.getPayRefundBypayId(payid);
	}

	/**
	 * 更新退款记录状态
	 * 
	 * @param map
	 */
	@Override
	public void updatePayOrderByRefundId(Map<String, Object> map) {

		payRefundMapper.updatePayOrderByRefundId(map);
	}

	/**
	 * u付代发服务
	 * 
	 * @param paramMap
	 * @return
	 * @throws FailureException
	 */
	@Override
	public Map<String, String> uPay(Map<String, Object> paramMap)
			throws FailureException {
		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("RespDesc", "");
		try {

			Map<String, String> map = wrapParams(paramMap);
			ReqData result = Mer2Plat_v40.makeReqDataByGet(map);

			GetMethod method = new GetMethod(result.getUrl());
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 100); //链接超时100秒
			client.getHttpConnectionManager().getParams().setSoTimeout(1000 * 100); 
			long sdate = System.currentTimeMillis();
			
			if (client.executeMethod(method) == HttpStatus.SC_OK) {
				@SuppressWarnings("unchecked")
				Map<String, String> ret = Plat2Mer_v40.getResData(method
						.getResponseBodyAsString());
				if ("0000".equals(ret.get("ret_code"))) {
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
					resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);
				} else {
					resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+"," + ret.get("ret_msg"));
				}
			} else {
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_ERROR);
				resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+",提交异常");
			}
			long edate = System.currentTimeMillis();
			long ydate = edate - sdate;
			log.info("U代付发送完时间-----------："+edate);
			log.info("U代付响应时间----------："+ydate);
			
		}  catch (Exception e) {
			log.error("Exception", e);
			resultMap.put("status", PayConstants.WITHDRAW_STATUS_ERROR);
			resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL+",提交异常");
		}
		return resultMap;
	}

	private Map<String, String> wrapParams(Map<String, Object> paramMap) {

		Map<String, String> map = new HashMap<String, String>();
		// 协议参数
		map.put("service", service);
		map.put("sign_type", signType);
		map.put("charset", charset);
		map.put("mer_id", merId);
		map.put("notify_url", this.notifyUrl+"/pay");
		map.put("version", version);
		map.put("res_format", resFormat);

		// 业务参数
		map.put("order_id",
				paramMap.get("orderId") + "-" + paramMap.get("userType")); // 商户订单号
		map.put("mer_date",
				(new SimpleDateFormat("yyyyMMdd").format(new Date()))); // 商户订单日期
		// yyyyMMdd

		String orderAmount = String.valueOf(paramMap.get("orderAmount"));

		BigDecimal orderAmountD = hundred.multiply(new BigDecimal(orderAmount));

		map.put("amount", String.format("%.0f", orderAmountD)); // 以分为单位
		map.put("recv_account_type", "00");// 00---银行卡
		map.put("recv_bank_acc_pro", String.valueOf(paramMap.get("ispublic"))); // 银行账户属性
		map.put("recv_account", String.valueOf(paramMap.get("recvAccount")));// 收款方账号
		map.put("recv_user_name", String.valueOf(paramMap.get("recvUserName")));// 收款方户名
		map.put("identity_type", paramMap.get("identityType") == null ? ""
				: String.valueOf(paramMap.get("identityType")));// 收款方证件类型
		// 01-----身份证（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
		map.put("identity_code", paramMap.get("identityCode") == null ? ""
				: String.valueOf(paramMap.get("identityCode")));// 收款方平台预留证件号码
		// 使用平台公钥进行RSA加密（收款方身份证号码。长度为15-18位数字与字母的组合，中间不允许有空格。(对私付款时必填)
		map.put("identity_holder", String.valueOf(paramMap.get("recvUserName")));// 证件持有人真实姓名
		// 使用平台公钥进行RSA加密
		// 收款方身份证持有人真实姓名。长度小于等于30个汉字。(对私付款时必填)

		map.put("media_type",
				paramMap.get("mediaType") == null ? "" : String
						.valueOf(paramMap.get("mediaType")));// 媒介类型
		// 取值范围：MOBILE（手机号）（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
		map.put("media_id",
				paramMap.get("mediaId") == null ? "" : String.valueOf(paramMap
						.get("mediaId")));// 媒介值
		// 付款方平台预留手机号码（收款方账户属性值为0：对私时，该字段需传递，值为1：对公时，该字段不传递）
		map.put("recv_gate_id", String.valueOf(paramMap.get("recvGateId")));// 支持银行列表
		map.put("purpose",
				paramMap.get("purpose") == null
						|| "".equals(String.valueOf(paramMap.get("purpose"))
								.trim()) ? "无" : String.valueOf(paramMap
						.get("purpose")));// 摘要
		map.put("prov_name",
				paramMap.get("provName") == null ? "无" : String
						.valueOf(paramMap.get("provName")));// 省
		map.put("city_name",
				paramMap.get("cityName") == null ? "无" : String
						.valueOf(paramMap.get("cityName")));// 市
		map.put("bank_brhname", String.valueOf(paramMap.get("bankBrhname")));// 开户行支行全称
		map.put("checkFlag", "0");
		log.info("发送给u付的参数map:" + map);
		return map;
	}
	
	/**
	 * u付代发结果查询
	 * 
	 * @param paramMap
	 * @return  1打款成功  2 打款失败  0查询失败  3打款处理中
	 * @throws FailureException
	 */
	@Override
	public Map<String, String> queryUPayResult(String orderId, String merDate) {
		// 响应参数
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", "0");
		resultMap.put("Message", "");
		resultMap.put("platform_code", "");
        
		try {

			Map<String, String> map = new HashMap<String, String>();
			// 协议参数
			map.put("service", queryService);
			map.put("charset", charset);
			map.put("mer_id", merId);
			map.put("res_format", resFormat);
			map.put("version", version);
			map.put("sign_type", signType);
			// 业务参数
			map.put("order_id", orderId); // 商户订单号
			map.put("mer_date", merDate); // 商户订单日期 yyyyMMdd

			ReqData reqData = Mer2Plat_v40.makeReqDataByGet(map);

			GetMethod method = new GetMethod(reqData.getUrl());
			HttpClient client = new HttpClient();

			if (client.executeMethod(method) == HttpStatus.SC_OK) {
				@SuppressWarnings("unchecked")
				Map<String, String> ret = Plat2Mer_v40.getResData(method
						.getResponseBodyAsString());
				if ("0000".equals(ret.get("ret_code"))) {

					/*
					 * 1-支付中 3-失败 4-成功 11：待确认 12：已冻结,待财务审核 13: 待解冻,交易失败
					 * 14：财务已审核，待财务付款 15: 财务审核失败，交易失败 16：受理成功，交易处理中 17: 交易失败退单中
					 * 18：交易失败退单成功
					 */
					String tradeState = ret.get("trade_state");
					String tradeNo = ret.get("trade_no");

					if (tradeState.equals("3")) {// 失败
						resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
						resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL+","+ret.get("ret_msg"));
						resultMap.put("platform_code", tradeNo == null ? ""
								: tradeNo);

					} else if (tradeState.equals("4")) {// 成功
						resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
						resultMap.put("Message", PayConstants.WITHDRAW_MSG_SUCCESS);
						resultMap.put("platform_code", tradeNo == null ? ""
								: tradeNo);

					} else {// 其他
						resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
						resultMap
								.put("Message",
										"tradeState："
												+ tradeState
												+ " ,1:支付中3:失败 4:成功11:待确认12:已冻结待财务审核13:待解冻,交易失败14:财务已审核，待财务付款15:财务审核失败，交易失败16:受理成功，交易处理中17:交易失败退单中18:交易失败退单成功 , ret_msg"+ret.get("ret_msg"));
					}

				} else {
					resultMap.put("Message", "u付查询响应异常：" + ret.get("ret_msg"));
				}
			} else {
				resultMap.put("Message", "u付查询异常");
			}

		} catch (IOException e) {
			resultMap.put("Message", "IOException");
			log.error("IOException", e);
		} catch (RetDataException | ReqDataException e) {
			resultMap.put("Message", "RetDataException | ReqDataException");
			log.error("RetDataException | ReqDataException", e);
		} catch (Exception e) {
			log.error("Exception", e);
			resultMap.put("Message", "Exception");
		}
		return resultMap;
	}
	
	

	/**
	 * 获取用户商家提现记录
	 * 
	 * @param statusMap
	 * @return
	 */
	@Override
	public Map<String, Object> selectByflowid(Map<String, Object> statusMap) {
		return recordStateMapper.selectByflowid(statusMap);
	}

	/**
	 * 获取合作商提现记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Map<String, Object> selectByjointid(Map<String, Object> paramMap) {
		return recordStateMapper.selectByjointid(paramMap);
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getResFormat() {
		return resFormat;
	}

	public void setResFormat(String resFormat) {
		this.resFormat = resFormat;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public static void main(String[] args){
		String refundId = "1502041419539245";
		UPayServiceImpl uPayService = new UPayServiceImpl();
		Map<String,String> map = uPayService.queryUpayRefund(refundId);
		log.info(JSON.toJSONString(map));
	}
}
