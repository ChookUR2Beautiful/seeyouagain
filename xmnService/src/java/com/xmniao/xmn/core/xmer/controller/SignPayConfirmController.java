package com.xmniao.xmn.core.xmer.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.request.SignPayConfirmRequest;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.xmer.service.BuySaasPackageService;
import com.xmniao.xmn.core.xmer.service.XmerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SignPayConfirmController   
* 类描述：   购买saas套餐,微信支付(H5)
* 创建人：yezhiyong   
* 创建时间：2016年6月12日 下午2:04:30   
* @version    
*
 */
@Controller
public class SignPayConfirmController{
	//日志报错s
	private static final Logger logger = LoggerFactory.getLogger(SignPayConfirmController.class);
	//注入验证
	@Autowired
	private Validator validator;
	
	//注入购买saas套餐解耦
	@Autowired
	private BuySaasPackageService buySaasPackageService;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private String payDomain;
	
	@Autowired
	private String paySecret;
	
	@Autowired
	private XmerService xmerService;
	
	@RequestMapping(value="/pay/paySaasPackage")
	@ResponseBody
	public Object signPayConfirm(Model model,SignPayConfirmRequest signPayConfirmRequest,HttpServletRequest request) throws Exception{
		List<ConstraintViolation> result = validator.validate(signPayConfirmRequest);
		if(result.size() >0 && result != null){
			logger.info("提交的数据有问题"+result.get(0).getMessage());
			Map<String,Object> returnResult = new HashMap<String,Object>();
			returnResult.put("result", result.get(0).getMessage()+",请确认邀请链接是否正确");
			return returnResult;
		}
		
		String uid = sessionTokenService.getStringForValue(signPayConfirmRequest.getSessiontoken()).toString();
		
		MapResponse  response =  (MapResponse)buySaasPackageService.addSoldOrder(signPayConfirmRequest);
		
		Map<Object, Object> resultMap = response.getResponse();
		Map<String, String> map = new HashMap<String,String>();
		//用户UID
		map.put("uid", uid);
		//订单编号
		map.put("orderSn", String.valueOf(resultMap.get("orderid")));
		//订单金额
		BigDecimal b = new  BigDecimal(String.valueOf(resultMap.get("amount")));  
		map.put("amount", b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
//		map.put("amount", "0.01");		//测试金额
		//支付方式：1000001支付宝SDK支付，1000003 微信SDK支付,1000013 微信公众号支付
		map.put("paymentType", "1000013");
		//订单类型，目前传固定值2
		map.put("orderType", "2");
		//订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单
		map.put("source", "001");
		//订单标题
		map.put("subject","SAAS_PACKAGE");
		//微信openid
		map.put("wxuid",CookieUtils.getVal(AuthzController.WEIXIN_OPENID_KEY, request));
		
		String sign = Signature.sign(map,paySecret);
		map.put("sign", sign);
		
		//判断是否是V客寻蜜客   
		String consumerType ="1";
		List<Integer> xmerTypeList= xmerService.identityList(Integer.parseInt(uid));
		if (xmerTypeList.contains(4)) {
			consumerType = "2";
		}
		map.put("consumerType", consumerType); // 客户类型（1：个人寻蜜客2:V客寻蜜客3:中脉寻蜜客）
		
		String url = "";
		for(Entry<String, String> entry : map.entrySet()){
			url  += "&" + entry.getKey() + "=" + entry.getValue();
		}
		url = url.substring(1, url.length());
		
		url = payDomain + "/unified_order/prepare?" + url;
		
		logger.info("Payment request URL : {}",url);
		String text = ClientCustomSSL.doGet(url);
		JSONObject o =  JSON.parseObject(text);
		Map<String,Object> returnResult = new HashMap<String,Object>();
		returnResult.put("result", "success");
		returnResult.put("o", o);
		
		return returnResult;
	}
	
	/**
	 * 
	* @Title: toSignSuccess
	* @Description: 跳转支付成功页面
	* @return String    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value="/toSignSuccess",method = RequestMethod.GET)
	public String toSignSuccess() {
		return "signprogress/signsuccess";
	}
	
	/**
	 * 
	 * @Title: toSignPayFail
	 * @Description: 跳转支付失败页面
	 * @return String    返回类型
	 * @author
	 * @throws
	 */
	@RequestMapping(value="/toSignPayFail",method = RequestMethod.GET)
	public String toSignPayFail() {
		return "signprogress/signpayfail";
	}
}
