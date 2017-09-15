package com.xmniao.xmn.core.order.service;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xmniao.xmn.core.order.dao.ExperienceActivityDao;
import com.xmniao.xmn.core.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.ExperienceCardRequest;
import com.xmniao.xmn.core.live.service.LiveBuyFansCouponService;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.order.dao.ExperienceConfigDao;
import com.xmniao.xmn.core.order.entity.ExperienceOfficerOrder;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

@Service
public class ExperienceOrderService {

	private final Logger log = Logger.getLogger(ExperienceOrderService.class);
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private ExperienceConfigDao experienceConfigDao;
	
	@Autowired 
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private LiveBuyFansCouponService liveBuyFansCouponService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private String fileUrl;

	@Autowired
	private ExperienceActivityDao experienceActivityDao;

	public Object queryExperienceOrderDetail (ExperienceCardRequest request) {
		return queryExperienceOrderDetail(request, false);
	}
	
	/**
	 * 获取体验卡配置信息
	 * @author yhl
	 * @return Object
	 * */
	public Object queryExperienceOrderDetail(ExperienceCardRequest request, boolean isFromOrderPreDetail){
		
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		if (null==request.getOrderNo() && StringUtils.isEmpty(request.getOrderNo())) {
			return new MapResponse(ResponseCode.FAILURE, "未获取到订单编号");
		}
		
		MapResponse response = null;
		Map<Object, Object>  resultMap = new HashMap<Object, Object>();
		
		ExperienceOfficerOrder order = null;
		
		if (request.getOrderSource()  == 1) {//调统一订单支付接口  
			//请求支付接口
			String url;
			try {
				url = propertiesUtil.getValue("unifiedOrderQuery", "conf_live.properties");
			} catch (IOException e1) {
				log.info("读取配置文件出错conf_common.properties - > foodOrderQuery");
				e1.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, "读取出错");
			}
			
			//拼装
			url = url+"?orderNumber="+request.getOrderNo();
			String result = HttpConnectionUtil.doPost(url, "");
			if (StringUtils.isNotEmpty(result)) {
				if (result.indexOf("state") != -1) {
					JSONObject json = JSONObject.parseObject(result);
					if (json.get("state").toString().equals("200")) {
						
						//获取美食体验卡 返回
						Map<Object, Object> paramMap = new HashMap<Object, Object>();
						paramMap.put("uid", uid);
						try {
							Map<String, String>  resultCardMap = qureyExperienceCard(paramMap);
							if (resultCardMap!=null && resultCardMap.size()>0) {
								resultCardMap.put("orderNo", request.getOrderNo());
								resultMap.put("experienceCard", resultCardMap);
								response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
								response.setResponse(resultMap);
								return response;
							}else {
								return new MapResponse(ResponseCode.FAILURE, "获取体验卡异常");
							}
						} catch (Exception e) {
							return new MapResponse(ResponseCode.FAILURE, "获取体验卡异常");
						}
						
					}else {
						return new MapResponse(ResponseCode.FAILURE, "获取订单信息异常");
					}
				}
			}else {
				log.info("调用支付接口失败"+request.getOrderNo());
				return new BaseResponse(ResponseCode.FAILURE, "获取订单信息异常");
			}
		}else { //普通查询
			
			//获取美食体验卡 返回
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("uid", uid);
			paramMap.put("orderNo", request.getOrderNo());
			try {
				//获取体验卡
				Map<String, String>  resultCardMap = qureyExperienceCard(paramMap);
				//获取订单信息
				order = experienceConfigDao.queryExperienceOrderDetail(paramMap);
				
				if (resultCardMap!=null && resultCardMap.size()>0 && order!=null) {
					Map<Object, Object> tmp = orderMap(order, isFromOrderPreDetail, request.getSessiontoken());
					
					order.setPayAmount(order.getLiveCoin().add(order.getWalletAmount()).add(order.getSamount()));
					
					resultMap.put("experienceCard", resultCardMap);
					if (tmp != null) {
						resultMap.put("orderInfo", tmp);
					} else {
						resultMap.put("orderInfo", order);
					}
					response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
					response.setResponse(resultMap);
					return response;
				}else {
					return new MapResponse(ResponseCode.FAILURE, "获取体验卡异常");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, "获取体验卡异常");
			}
		}
		return new MapResponse(ResponseCode.FAILURE, "获取订单异常");
	}

	/**
	 * 从预售订单列表进入，返回的数据
	 * @param order
	 * @param isFromOrderPreDetail
	 */
	Map<Object, Object> orderMap(ExperienceOfficerOrder order, boolean isFromOrderPreDetail, String sessionToken) {
		if (!isFromOrderPreDetail) {
			return null;
		}
		Map<Object, Object> orderMap = new HashMap<Object, Object>();
		orderMap.put("orderNo", order.getOrderNo());

		orderMap.put("createTimeStr", order.getCreateTimeStr());
		orderMap.put("days", order.getDays());
		orderMap.put("id", order.getId());
		orderMap.put("nname", order.getNname());
		orderMap.put("nums", order.getNums());
		orderMap.put("payState", order.getPayState());
		orderMap.put("payTypeStr", order.getPayTypeStr());
		StringBuilder sb = new StringBuilder();
		Integer day = null;
		Integer nums = null;
		try {
			Map<Object, Object>  params = new HashMap<Object, Object>();
			params.put("orderNo", order.getOrderNo());
			ExperienceOfficerOrder experienceOfficerOrder = experienceConfigDao.queryExperienceOrderDetail(params);
			day = experienceOfficerOrder.getDays();
			nums = experienceOfficerOrder.getNums();
			if (day != null && nums != null) {
				sb.append(day);
				sb.append("天有效,");
				sb.append(nums);
				sb.append("次机会");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("体验卡使用条件描述失败");
		}
		String h5DoMain = null;
		String h5Action = null;
		String picUrl = "";
		try {
			picUrl = propertiesUtil.getValue("experienceOfficerPic", "conf_common.properties");
			h5DoMain = propertiesUtil.getValue("h5DoMain", "conf_common.properties");
			h5Action = propertiesUtil.getValue("h5Action", "conf_common.properties");
		}catch (Exception e) {
			log.warn("读取配置信息失败：experienceOfficerPic");
		}
		String toUrl = "";
		// 拼接跳转
		if (h5DoMain != null && h5Action != null) {
			toUrl = h5DoMain + "action=" + h5Action + "&sessionToken=" + String.valueOf(sessionToken);
		}
		Integer actionStatus = 0;
		String totalAmount = "";
		String reallyPayAmount = "";
		try {
			totalAmount = "¥" + order.getAmount().toString();
			reallyPayAmount = "¥" + order.getAmount().toString();
		} catch (Exception e) {
			log.warn("解析美食体验官金额失败");
		}
		StringBuilder useTime = new StringBuilder();
		try {
			Date lastDay = DateUtils.addDays(order.getCreateTime(), day);
			useTime.append(DateUtil.format(order.getCreateTime(), DateUtil.daySimpleFormater));
			useTime.append(" - ");
			useTime.append(DateUtil.format(lastDay, DateUtil.daySimpleFormater));
		} catch (Exception e) {
			log.warn("获取使用期限失败" + order.toString());
		}

		orderMap.put("outDate", useTime);
		orderMap.put("amount", totalAmount);
		orderMap.put("payAmount", reallyPayAmount);

		//
		orderMap.put("orderStatusDesc", "预售·已支付");  //订单状态描述
		orderMap.put("orderDesc", "请在美食体验官资格有效期内使用");  //订单描述
		orderMap.put("picUrl", picUrl);  //图片路径
		orderMap.put("experienceTitle", "美食体验官");  //体验卡名称
		orderMap.put("cname", sb.toString());  //体验卡使用条件描述（如：3个月有效，5次机会）
		orderMap.put("toUrl", toUrl);
		orderMap.put("actionStatus", actionStatus);  // 0 未报名  1 已报名
		return orderMap;
	}

	
	/**
	 * 查询体验卡信息
	 * @return object
	 * */
	public Map<String, String>  qureyExperienceCard(Map<Object, Object> map) throws Exception {
		
		Map<String, String> cardMap = new HashMap<String, String>();
		cardMap.put("uid", map.get("uid").toString());
		ResponseData resData = liveGiftsInfoService.queryExperienceCard(cardMap);
		if (resData.getState() == 0) {
			return resData.getResultMap();
		}else {
			throw new Exception("查询美食体验卡失败");
		}
	}
	
	/**
	 * 描述：  取消订单操作,调用支付接口
	 * */
	public Object cancelExperienceOrderInfo(ExperienceCardRequest request){
		
		MapResponse response = null;
		
		//验证token
		try {
			//获取订单基本信息
			//取消订单组装
			Map<String, String> cancelMap = new HashMap<String, String>();
			cancelMap.put("orderNumber",request.getOrderNo());
			cancelMap.put("randomNumber", (int)((Math.random()*9+1)*100000)+"");
			String url = liveBuyFansCouponService.transMap(cancelMap);
			//请求接口
			String result = HttpConnectionUtil.doPost(url, "");
			if (StringUtils.isNotEmpty(result)) {
				if (result.indexOf("state") != -1) {
					JSONObject json = JSONObject.parseObject(result);
					int state = Integer.parseInt(json.get("state").toString());
					if (state == 201 || state == 200) {
						return new MapResponse(ResponseCode.SUCCESS, "操作成功");
					}else {
						return new MapResponse(ResponseCode.FAILURE, json.get("info")==null?"操作失败":json.get("info").toString());
					}
				}
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
			}
				
		} catch (Exception e) {
			log.info("查询订单详情异常:"+request.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取订单信息异常");
		}
		return response;
	}
	
		
	
}
