package com.xmniao.xmn.core.api.controller.xmer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.xmer.controller.SignPayConfirmController;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;


/**
 * 
*      
* 类名称：PlacePayApi   
* 类描述：   代付支付接口
* 创建人：xiaoxiong   
* 创建时间：2016年9月3日 下午4:05:15   
* 修改人：xiaoxiong   
* 修改时间：2016年9月3日 下午4:05:15   
* 修改备注：   
* @version    
*
 */
@Controller
public class PlacePayApi {
	
		//日志
		private static final Logger logger = LoggerFactory.getLogger(PlacePayApi.class);
	
		@Autowired
		private SaasOrderDao saasOrderDao;
	
		@Autowired
		private String payDomain;
		
		@Autowired
		private String paySecret;
		
		@RequestMapping(value="/pay/placePay")
		@ResponseBody
		public Object placePay(String orderid,HttpServletRequest request,String phone) throws Exception{
			//参数验证
			if(orderid==null||orderid.length()==0){
				Map<String,Object> returnResult = new HashMap<String,Object>();
				returnResult.put("result", "订单id不能为空");
				return returnResult;
			}
			//手机号码验证
			if(phone==null||phone.length()==0){
				Map<String,Object> returnResult = new HashMap<String,Object>();
				returnResult.put("result", "手机号码不能为空");
				return returnResult;
			}
				//获取saas订单信息
				Map<Object,Object>  orderMap=saasOrderDao.querySaasOrderInfoByOrdersn(orderid);
				String uid=orderMap.get("uid")+"";
				Double amount=ArithUtil.mul(Double.valueOf(orderMap.get("amount")+""), Double.valueOf(orderMap.get("agio")+""));
				String otherTel=orderMap.get("otherTel")+"";
				//判断代付号码是否一致
				if(!otherTel.equals(phone)){
					Map<String,Object> returnResult = new HashMap<String,Object>();
					returnResult.put("result", "输入的代付手机号码错误！");
					return returnResult;
				}
				Map<String, String> map = new HashMap<String,String>();
				//用户UID
				map.put("uid", uid);
				//订单编号
				map.put("orderSn",orderid);
				//订单金额
				BigDecimal b = new BigDecimal(amount); 
				map.put("amount", b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
//				map.put("amount", "0.01");		//测试金额
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
				String url = "";
				
				map.put("consumerType", "1"); // 客户类型（1：个人寻蜜客2:V客寻蜜客3:中脉寻蜜客）
				
				for(Entry<String, String> entry : map.entrySet()){
					url  += "&" + entry.getKey() + "=" + entry.getValue();
				}
				url = url.substring(1, url.length());
				
				url = payDomain + "/unified_order/prepare?" + url;

				String text = ClientCustomSSL.doGet(url);
				JSONObject o =  JSON.parseObject(text);
				Map<String,Object> returnResult = new HashMap<String,Object>();
				returnResult.put("result", "success");
				returnResult.put("o", o);
				
				logger.info("请求支付返回参数："+o.toJSONString());
				
				return returnResult;
		
		
			
		}

}
