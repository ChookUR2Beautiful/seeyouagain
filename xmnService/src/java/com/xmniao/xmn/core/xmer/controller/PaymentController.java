package com.xmniao.xmn.core.xmer.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.xmniao.xmn.core.xmer.service.XmerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.xmer.service.PayTypeService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PayTypeService payTypeService;
  
	private String payDomain;
	
	private String paySecret;

	@Autowired
	private XmerService xmerService;
	
	//签约商户软件销售订单支付
	@RequestMapping("/do_saas_sold")
	@ResponseBody
	public Map<String,Object> doSaasSold(String ordersn,HttpServletRequest request) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(StringUtils.isEmpty(ordersn)){
			result.put("result", "ordersn is null");
			return result;
		}
		
		Map<Object,Object> order = payTypeService.getSaasSoldOrder(ordersn);
		
		if(order == null){
			result.put("result", "order is null");
			return result;
		}
		
		Map<String, String> map = new HashMap<String,String>();

		//用户UID
		map.put("uid", String.valueOf(order.get("uid")));
		//订单编号
		map.put("orderSn", String.valueOf(order.get("ordersn")));
		//订单金额
		BigDecimal b = new  BigDecimal(String.valueOf(order.get("amount")));  
		map.put("amount", b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		//支付方式：1000001支付宝SDK支付，1000003 微信SDK支付,1000013 微信公众号支付
		map.put("paymentType", "1000013");
		//订单类型，目前传固定值2
		map.put("orderType", "2");
		//订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单
		map.put("source", "002");
		//订单标题
		map.put("subject","SAAS_SALE");
		//微信openid
		map.put("wxuid",CookieUtils.getVal(AuthzController.WEIXIN_OPENID_KEY, request));
		
		String sign = Signature.sign(map,paySecret);
		map.put("consumerType", "1"); // 客户类型（1：个人寻蜜客2:V客寻蜜客3:中脉寻蜜客）
		map.put("sign", sign);
		
		String url = "";
		
		for(Entry<String, String> entry : map.entrySet()){
			url  += "&" + entry.getKey() + "=" + entry.getValue();
		}
		
		url = url.substring(1, url.length());
		
		url = payDomain+"/unified_order/prepare" + "?" + url;
		
		logger.info("Payment request URL : {}",url);
		String text = ClientCustomSSL.doGet(url);
		logger.info("pay callback data: " + text);
		JSONObject o =  JSON.parseObject(text);
		
		result.put("result", "success");
		result.put("o", o);
		
		return result;
	}

	@Autowired
	public void setPayDomain(String payDomain) {
		this.payDomain = payDomain;
	}
	
	@Autowired
	public void setPaySecret(String paySecret) {
		this.paySecret = paySecret;
	}
	
	
}
