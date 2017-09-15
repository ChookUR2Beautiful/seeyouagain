package com.xmniao.xmn.core.xmer.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.ViewWechatRequest;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.xmer.dao.ViewWechatMapper;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.ViewWechat;
import com.xmniao.xmn.core.xmer.entity.Xmer;

@Service
public class ViewWechatService {
	
	@Resource
	private ViewWechatMapper viewWechatMapper;
	
	@Resource
	private XmerDao xmerDao;
	
	@Autowired
	private String paySecret;	//签名
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	

	
	/**
	 * 
	* @Title: payWeChat
	* @Description: 通过付费积分获取微信号
	* @return Object    返回类型
	* @throws
	 */
	public Object createViewWechat(ViewWechatRequest viewWechatRequest) {
		try {
			// 获取寻蜜客的uid
			String sessionToken = viewWechatRequest.getSessiontoken();
			String uid = sessionTokenService.getStringForValue(sessionToken) + "";
			if (uid.equals("") || uid.equals("null")) {
				return new BaseResponse(ResponseCode.FAILURE, "支付积分失败");
			}
			//生成订单号
			String orderId = "03" + SaasBidType.getBid(); 
			//积分支付请求参数
			Map<String,Object> paramMap = new HashMap<>();
			//组装参数
			String nonceStr = new Random().nextInt(999999) + "";
			paramMap.put("userid",uid);
			paramMap.put("integral",viewWechatRequest.getIntegral().intValue()+"");
			paramMap.put("orderAmount",viewWechatRequest.getIntegral().intValue()+"");
			paramMap.put("orderId",orderId);
			paramMap.put("nonceStr",nonceStr);
			String sign = getSign(paramMap);
			paramMap.put("sign",sign);
			//积分支付的url
			PropertiesUtil pu = new PropertiesUtil();
			String payurl = pu.getValue("xmnpay.integralPayDirect.url", "conf_redis.properties");
			String jsonIntegralPay=HttpConnectionUtil.requestPost(payurl, paramMap);
			JSONObject sry = JSONObject.parseObject(jsonIntegralPay);
			String value = sry.getString("state");
			if (value.equals("500")) {
				return new BaseResponse(ResponseCode.INEGRAL_NOT_ENOUGH,"支付积分不足");
			} else if (value.equals("201")) {
				ViewWechat viewWechat=new  ViewWechat();
				viewWechat.setOrdersn(orderId);
				viewWechat.setBuyUid(Integer.valueOf(uid));
				viewWechat.setSoldUid(viewWechatRequest.getSolduid());
				viewWechat.setIntegral(new BigDecimal(viewWechatRequest.getIntegral()));
				viewWechat.setViewDate(new Date());
				viewWechatMapper.insertSelective(viewWechat);
				Xmer xmer = xmerDao.selectByUid(viewWechatRequest.getSolduid());
				if(xmer == null){
					return new BaseResponse(ResponseCode.FAILURE,"查无寻蜜客资料");
				}else if (StringUtils.isEmpty(xmer.getWeixinid())) {
					return new BaseResponse(ResponseCode.FAILURE,"该寻蜜客没有微信号,索要微信号失败");
				}{
					
				}
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
				Map<Object, Object> result = new HashMap<>();
				result.put("wechatno", xmer.getWeixinid());
				response.setResponse(result);
				return response;
			} else {
				return new BaseResponse(ResponseCode.FAILURE, "支付积分失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "支付积分失败");
		}
	}

	/**
	 * 
	* @Title: getSign
	* @Description: 获取签名
	* @return String    返回类型
	* @throws
	 */
	public String getSign(Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		sb.append(params.get("integral")).append(params.get("nonceStr")).append(params.get("orderAmount")).append(params.get("orderId")).append(params.get("userid"));
		sb.append(paySecret);
		return MD5.Encode(sb.toString());  
	}
}
