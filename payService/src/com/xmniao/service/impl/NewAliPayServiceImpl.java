package com.xmniao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.xmniao.common.RefundUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.service.NewAlipayService;
import com.xmniao.service.RefundService;

/**
 * 支付宝Service实现
 * @author chenjie
 *
 */

@Service("NewAliPayServiceImpl")
public class NewAliPayServiceImpl implements NewAlipayService{
	
	//初始化日志类
	private final Logger log = Logger.getLogger(NewAliPayServiceImpl.class);
	
	@Autowired
	private PayOrderMapper payOrderMapper;
	
	@Autowired
	private RefundService refundService;
	
	//请求地址
	private static final String REQUEST_URL="https://openapi.alipay.com/gateway.do";
	
	//支付宝分配给开发者的应用ID
	private static final String APP_ID = "";
	
	//私人秘钥
	private static final String PRIVATE_KEY = "";
	
	//支付宝秘钥
	private static final String ALIPAY_PUBLIC_KEY = "";
	
	
	
	/**
	 * 支付宝退款
	 * @param orderNumber 订单号
	 */
	@Override
	public Map<String, String> aliPayRefund(String orderNumber,double refundAmount,long payId) {
		log.info("  orderNumber:"+ orderNumber);
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("out_trade_no", orderNumber);//订单号
			paramMap.put("trade_no", payId);//支付id
			paramMap.put("refund_amount", refundAmount);//退款金额
			
			String content = JSON.toJSONString(paramMap);
			log.info("请求参数："+content);
			
			AlipayClient alipayClient = 
					new DefaultAlipayClient(REQUEST_URL, APP_ID, PRIVATE_KEY,"json","GBK",ALIPAY_PUBLIC_KEY);
			AlipayTradeRefundRequest request =new AlipayTradeRefundRequest();
			request.setBizContent(content);
			AlipayTradeRefundResponse response =alipayClient.execute(request);
			String responseCode = response.getCode();//响应码
			if ("1000".equals(responseCode)) {
				//响应结果
				Map<String, String> result = response.getParams();
				log.info("响应结果："+result.toString());
				resultMap = RefundUtil.returnMap(StateCodeUtil.PR_SUCCESS,"退款成功",JSON.toJSONString(result));
				return resultMap;
			}else {
				resultMap = RefundUtil.returnMap(response.getCode(),response.getSubMsg(),"");
				return resultMap;
			}
		} catch (AlipayApiException e) {
			log.error("退款失败："+e);
			resultMap = RefundUtil.returnMap(StateCodeUtil.PR_PARAM_ERROR,"退款失败","");
			return resultMap;
		}
	}
	
	
	
	
	/**
	 * 支付宝查询订单
	 * @param orderNumber 订单号
	 */
	@Override
	public Map<String, String> aliPayQuery(String orderNumber) {
		log.info("  orderNumber:"+ orderNumber);
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			//验证订单号是否为空
			if(orderNumber==null || orderNumber.equals("")){
				log.info("订单号为空");
				resultMap = RefundUtil.returnMap(StateCodeUtil.PR_ORDER_NOT_EXIST,"订单号为空","");
				return resultMap;
			}
			//订单信息
			Map<String, Object> orderMap = payOrderMapper.getPayOrderByorderNumber(orderNumber);
			if(orderMap==null || orderMap.size()==0){
				log.info("订单不存在");
				resultMap = RefundUtil.returnMap(StateCodeUtil.PR_ORDER_NOT_EXIST,"订单不存在","");
				return resultMap;
			}
			
			//支付Id
			String payId = orderMap.get("payId")+"";
			
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("out_trade_no", orderNumber);//订单号
			paramMap.put("trade_no", payId);//支付id
			
			String content = JSON.toJSONString(paramMap);
			log.info("请求参数："+content);
			
			AlipayClient alipayClient = 
					new DefaultAlipayClient(REQUEST_URL, APP_ID, PRIVATE_KEY,"json","GBK",ALIPAY_PUBLIC_KEY);
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			request.setBizContent(content);
			AlipayTradeQueryResponse response =alipayClient.execute(request);
			String responseCode = response.getCode();//响应码
			if ("1000".equals(responseCode)) {
				Map<String, String> result = response.getParams();
				log.info("响应结果："+result);
				resultMap = RefundUtil.returnMap("1000","查询成功",JSON.toJSONString(result));
				return resultMap;
			}else {
				resultMap = RefundUtil.returnMap(response.getCode(),response.getSubMsg(),"");
				return resultMap;
			}
		} catch (AlipayApiException e) {
			log.error("查询失败："+e);
			resultMap = RefundUtil.returnMap("2000","查询失败","");
			return resultMap;
		}
	}
}
