package com.xmniao.xmn.core.sellerPackage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.xmer.dao.SellerDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.service.NewXmnHomeService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.sellerPackage.SellerPackageQueryRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.service.LiveBuyFansCouponService;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageGrantDao;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageOrderDao;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackagePicDao;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackage;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackageGrant;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackageOrder;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.service.SellerService;


/**
 * 描述： 用户或主播进入房间
 * @author yhl
 * 2016年8月15日11:34:14
 * */
@Service
public class SellerPackageOrderService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerPackageOrderService.class);
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private SellerPackageOrderDao sellerPackageOrderDao;
	
	@Autowired
	private SellerPackageGrantDao sellerPackageGrantDao;
	
	@Autowired
	private SellerPackageDao sellerPackageDao;
	
	@Autowired
	private SellerPackagePicDao sellerPackagePicDao;
	
	@Autowired
	private LiveBuyFansCouponService liveBuyFansCouponService;
	
	@Autowired 
	private SellerService sellerService;
	
	@Autowired
	private NewXmnHomeService newXmnHomeService;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private SellerDao sellerDao;

	public Object querySellerPackageOrderInfo(SellerPackageQueryRequest sellerPackageQueryRequest) {
		return querySellerPackageOrderInfo(sellerPackageQueryRequest, false);
	}
	
	/**
	 * 描述：购买套餐成功 根据订单标号查询订单基本信息  sellerPackageGrant
	 * */
	public Object querySellerPackageOrderInfo(SellerPackageQueryRequest sellerPackageQueryRequest, boolean isPreDetail){
		
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("order_no", sellerPackageQueryRequest.getOrderNo());
			//获取订单基本信息
			SellerPackageOrder orderInfo = sellerPackageOrderDao.querySellerPackageOrderByOrderNo(paramMap);
			if (orderInfo!=null) {
				Seller seller = null;
				if (isPreDetail) {  //从预售订单详情，进入调用
					seller = sellerDao.querySellerInfoBySellerid(orderInfo.getSellerid().longValue());
				} else {
					seller = sellerService.querySellerBySellerid(orderInfo.getSellerid().longValue());
				}
				Map<Object, Object> orderMap = new HashMap<Object, Object>();
				orderMap.put("orderNo", orderInfo.getOrderNo());
				orderMap.put("buyTime", DateUtil.format(orderInfo.getCreateTime(), DateUtil.defaultSimpleFormater));
				orderMap.put("sellername", seller.getSellername());
				orderMap.put("nums", orderInfo.getNums());
				orderMap.put("paymentAmount", orderInfo.getTotalAmount());
				
				//组装支付方式 余额和第三方放在现金支付里面   鸟币支付 放在鸟币支付 组合 成 现金 + 鸟币
				StringBuffer sbBuffer  = new StringBuffer();
				BigDecimal cash = orderInfo.getCash()==null?new BigDecimal(0):orderInfo.getCash();
				BigDecimal zbalance = orderInfo.getZbalance()==null?new BigDecimal(0):orderInfo.getZbalance();
				BigDecimal commision = orderInfo.getCommision()==null?new BigDecimal(0):orderInfo.getCommision();
				BigDecimal sellerCoin = orderInfo.getSellerCoin()==null?new BigDecimal(0):orderInfo.getSellerCoin();
				BigDecimal balance = zbalance.add(commision);
				BigDecimal beans = orderInfo.getBeans()==null?new BigDecimal(0):orderInfo.getBeans();
				BigDecimal zeroNum = new BigDecimal(0);
				if (cash.compareTo(new BigDecimal(0))>0 || balance.compareTo(new BigDecimal(0))>0) {
					sbBuffer = sbBuffer.append("¥:").append(cash.add(balance).toString()) ;
				}
				if (beans.compareTo(new BigDecimal(0)) >0 || sellerCoin.compareTo(new BigDecimal(0)) >0) {
					sbBuffer = sbBuffer.append(" 鸟币:").append(beans.add(sellerCoin));
				}
				orderMap.put("reallyPayAmount", StringUtils.isEmpty(sbBuffer.toString())?"¥:0":sbBuffer.toString());
				
				if (null!=orderInfo.getPaymentType() && !orderInfo.getPaymentType().equals("")) {
					if ((sellerCoin.compareTo(zeroNum)>0 || beans.compareTo(zeroNum)>0)
							&& (cash.compareTo(zeroNum)>0 || balance.compareTo(zeroNum)>0 ) ) {
						orderMap.put("paymentDesc", "组合支付");
					}else {
						orderMap.put("paymentDesc", this.sellerPackageOrderPayType(orderInfo.getPaymentType()) );
					}
				}else {
					orderMap.put("paymentDesc", "未知");
				}
				
				//如果订单信息不为空 查询订单的套餐券的列表信息
				String sellerPackegerCode = propertiesUtil.getValue("sellerPackageCodeUrl", "conf_common.properties");
				List<SellerPackageGrant> packagerCouponList =  sellerPackageGrantDao.querySellerPackageOrderCouponList(orderInfo.getOrderNo());
				
				//套餐信息
				SellerPackage sellerPackage = sellerPackageDao.selectByPrimaryKey(orderInfo.getPid());
				if (sellerPackage!=null) {
					//获取套餐封面图
					List<String> pic = sellerPackagePicDao.getCoverImage(orderInfo.getPid(),1);
					if(pic != null && pic.size() > 0){
						orderMap.put("packagePic", fileUrl+pic.get(0));
					}else{
						orderMap.put("packagePic", "");
					}
					orderMap.put("title", sellerPackage.getTitle());
					orderMap.put("useTime", DateUtil.format(sellerPackage.getUseStartTime(), DateUtil.daySimpleFormater)  +
							" - "+DateUtil.format(sellerPackage.getUseEndTime(), DateUtil.daySimpleFormater));
				}
				
				//用于返回参数
				List<Map<Object, Object>> couponsList  = new ArrayList<Map<Object, Object>>();
				
				List<Integer> useStauts = new ArrayList<Integer>();
				List<Integer> isTimeout = new ArrayList<Integer>();
				
				if (packagerCouponList!=null && packagerCouponList.size()>0) {
					for (int i = 0; i < packagerCouponList.size(); i++) {
						Map<Object, Object> map = new HashMap<Object, Object>();
						SellerPackageGrant grant = packagerCouponList.get(i);
						useStauts.add(grant.getUserStatus());
						isTimeout.add(grant.getIsTimeOut());
						map.put("serial", grant.getSerial());
						map.put("serialCode", sellerPackegerCode.replace("{p_id}", grant.getPid().toString()).replace("{p_code}", grant.getSerial()));
						map.put("titleDesc", "套餐券");
						map.put("useStatus", grant.getUserStatus());
						if (grant.getIsTimeOut() == 0) {
							if(grant.getUserStatus() == 2) {
								map.put("useDesc", "已使用");//使用状态
							}else if(grant.getUserStatus() == 3) {
								map.put("useDesc","已取消");//使用状态
								map.put("useStatus","4");//使用状态
							}
						}else {
							map.put("useDesc","已过期");//使用状态
							map.put("useStatus", 3);
						}
						couponsList.add(map);
					}
				}
				//如果是送礼套餐 则返回送礼人名称
				if (null!=orderInfo.getSendUid() && orderInfo.getSendUid()>0) {
					Map<Object, Object> liverInfoMap = liveUserDao.queryLiverInfoByUid(orderInfo.getSendUid());
					if (liverInfoMap!=null && liverInfoMap.size()>0) {
						orderMap.put("sendName", liverInfoMap.get("nname"));
						orderMap.put("sendUid", orderInfo.getSendUid());
					}
				}else {
					orderMap.put("sendName", "");
					orderMap.put("sendUid", "");
				}
				
				orderMap.put("orderStatusDesc", packageOrderStatus(orderInfo.getStatus().toString(),useStauts,isTimeout).get("stateDesc"));
				orderMap.put("orderStatus", packageOrderStatus(orderInfo.getStatus().toString(),useStauts,isTimeout).get("state"));
				orderMap.put("orderStatusRemark", currentOrderStatus(orderInfo.getStatus().toString(), useStauts, isTimeout));
				
				resultMap.put("orderInfo", orderMap);
				resultMap.put("couponInfo", couponsList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
				response.setResponse(resultMap);
			}else {
				log.info("没有找到订单相关信息："+sellerPackageQueryRequest.getOrderNo());
				response = new MapResponse(ResponseCode.FAILURE, "未找到订单信息!");
			}
		} catch (Exception e) {
			log.info("查询订单详情异常:"+sellerPackageQueryRequest.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取订单信息异常");
		}
		return response;
	}
	
	
	/**
	 * 描述：  取消订单操作,调用支付接口
	 * */
	public Object cancelSellerPackageOrderInfo(SellerPackageQueryRequest sellerPackageQueryRequest){
		
		MapResponse response = null;
		
		//验证token
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("order_no", sellerPackageQueryRequest.getOrderNo());
			//获取订单基本信息
			SellerPackageOrder orderInfo = sellerPackageOrderDao.querySellerPackageOrderByOrderNo(paramMap);
			if (orderInfo!=null) {
				//取消订单组装
				Map<String, String> cancelMap = new HashMap<String, String>();
				cancelMap.put("orderNumber",orderInfo.getOrderNo());
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
				
			}else {
				log.info("没有找到订单相关信息："+sellerPackageQueryRequest.getOrderNo());
				response = new MapResponse(ResponseCode.FAILURE, "未找到订单信息!");
			}
		} catch (Exception e) {
			log.info("查询订单详情异常:"+sellerPackageQueryRequest.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取订单信息异常");
		}
		return response;
	}
	
	
	
	/**
	 * 描述： 订单支付完成 获取推荐套餐  - 其他套餐
	 * */
	public Object recommendSellerPackage(SellerPackageQueryRequest sellerPackageQueryRequest){
		
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		//验证token
		try {
			
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("order_no", sellerPackageQueryRequest.getOrderNo());
			//获取订单基本信息
			SellerPackageOrder orderInfo = sellerPackageOrderDao.querySellerPackageOrderByOrderNo(paramMap);
			
			List<Integer> packageIdList =  new ArrayList<Integer>();
			
			if (orderInfo!=null) {
				
				Map<Object, Object> nearMap = new HashMap<Object, Object>();
				nearMap.put("lon", sellerPackageQueryRequest.getLon());
				nearMap.put("lat", sellerPackageQueryRequest.getLat());
				nearMap.put("page", 1 );
				nearMap.put("cityId", sellerPackageQueryRequest.getCityId());
				
				
				SellerPackage sellerPackage = sellerPackageDao.selectByPrimaryKey(orderInfo.getPid());
				if (sellerPackage!=null) {
					//获取商家的其他套餐 排除当前订单的套餐
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("id", sellerPackage.getId());
					map.put("sellerid", sellerPackage.getSellerid());
					map.put("limit", Constant.PAGE_LIMIT);
					map.put("page", 1);
					
					packageIdList.add(sellerPackage.getId());
					nearMap.put("list", packageIdList);
					
					List<Map<Object, Object>> packageList = sellerPackageDao.querySellerPackageList(map);
					if (packageList.size()>0) {//如果有其他套餐 判断是否够5个套餐
						
						if (packageList.size()>5) {
							resultMap.put("recommendPackage", packageList);
						}else {
							
							for (int i = 0; i < packageList.size(); i++) {
								packageIdList.add(Integer.parseInt(packageList.get(i).get("id").toString()) );
							}
							nearMap.put("list", packageIdList);
							
							nearMap.put("limit", 5-packageList.size());
//							if(packageIdList.size()>0){
//								nearMap.put("list", packageIdList);
//							}
							//查询附近的其他商家的套餐
							//查询推荐套餐
							List<Map<Object,Object>> recommendCombos = sellerPackageDao.queryMynearbySellerPackage(nearMap);
							
							if (recommendCombos.size()>0) {
								for (int i = 0; i < recommendCombos.size(); i++) {
									if (i>5) {
										break;
									}
									Map<Object, Object> comboMap = new HashMap<Object, Object>();
									Map<Object,Object> combo = recommendCombos.get(i);
									comboMap.put("id", combo.get("id"));
									comboMap.put("title", combo.get("comboname"));
									comboMap.put("sellerid", combo.get("sellerid"));
									comboMap.put("sellingPrice", combo.get("comboprice"));
									comboMap.put("sellingCoinPrice", combo.get("coinprice"));
									comboMap.put("picUrl", fileUrl+combo.get("comboimage"));
									packageList.add(comboMap);
								}
								
							}
							resultMap.put("recommendPackage", packageList);
						}
					}else {
						
						//查询附近的其他商家的套餐
						//查询推荐套餐
						nearMap.put("limit", 5);
						List<Map<Object,Object>> recommendCombos = sellerPackageDao.queryMynearbySellerPackage(nearMap);
						
						if (recommendCombos.size()>0) {
							for (int i = 0; i < recommendCombos.size(); i++) {
								if (i>5) {
									break;
								}
								Map<Object, Object> comboMap = new HashMap<Object, Object>();
								Map<Object,Object> combo = recommendCombos.get(i);
								comboMap.put("id", combo.get("id"));
								comboMap.put("title", combo.get("comboname"));
								comboMap.put("sellerid", combo.get("sellerid"));
								comboMap.put("sellingPrice", combo.get("comboprice"));
								comboMap.put("sellingCoinPrice", combo.get("coinprice"));
								comboMap.put("picUrl", fileUrl+combo.get("comboimage"));
								packageList.add(comboMap);
							}
							
						}
						resultMap.put("recommendPackage", packageList);
					}
				}
				
				response = new MapResponse(ResponseCode.SUCCESS, "查询成功!");
				response.setResponse(resultMap);
				
			}else {
				log.info("没有找到订单相关信息："+sellerPackageQueryRequest.getOrderNo());
				response = new MapResponse(ResponseCode.FAILURE, "未找到订单信息!");
			}
		} catch (Exception e) {
			log.info("查询订单详情异常:"+sellerPackageQueryRequest.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取订单信息异常");
		}
		return response;
	}


	
	/**
	 * 套餐订单列表
	 * */
	public Object querySellerPackageOrderList(SellerPackageQueryRequest sellerPackageQueryRequest){
		return querySellerPackageOrderList(sellerPackageQueryRequest, null, null);
	}

	public Object querySellerPackageOrderList(SellerPackageQueryRequest sellerPackageQueryRequest, String maxTime, Integer status) {
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
//		String uid = sellerPackageQueryRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(sellerPackageQueryRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		try {
			Map<Object, Object> packageMap = new HashMap<Object, Object>();
			packageMap.put("page", sellerPackageQueryRequest.getPage());
			packageMap.put("limit", Constant.PAGE_LIMIT);
			packageMap.put("uid", uid);
			packageMap.put("maxTime", maxTime);
			packageMap.put("status", status);
			List<SellerPackageOrder> sellerPackageOrders = sellerPackageOrderDao.querySellerPackageOrderList(packageMap);

			if (sellerPackageOrders.size()>0) {

				List<SellerPackageOrder> resultList = getSellerPackageOrderList(sellerPackageOrders);

				resultMap.put("orderList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
				response.setResponse(resultMap);

			}else {
				resultMap.put("orderList", sellerPackageOrders);
				response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
				response.setResponse(resultMap);
			}
		} catch (Exception e) {
			log.info("查询订单列表失败："+uid);
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取订单列表失败");
		}
		return response;
	}


	// 组装数据
	public List<SellerPackageOrder> getSellerPackageOrderList(List<SellerPackageOrder> sellerPackageOrders) {
		List<SellerPackageOrder> resultList = new ArrayList<SellerPackageOrder>();
		for (int i = 0; i < sellerPackageOrders.size(); i++) {
			SellerPackageOrder order = sellerPackageOrders.get(i);
			//组装支付方式
			StringBuffer sbBuffer  = new StringBuffer();
			//组装支付方式 余额和第三方放在现金支付里面   鸟币支付 放在鸟币支付 组合 成 现金 + 鸟币
			BigDecimal cash = order.getCash()==null?new BigDecimal(0):order.getCash();
			BigDecimal zbalance = order.getZbalance()==null?new BigDecimal(0):order.getZbalance();
			BigDecimal commision = order.getCommision()==null?new BigDecimal(0):order.getCommision();
			BigDecimal sellerCoin = order.getSellerCoin()==null?new BigDecimal(0):order.getSellerCoin();
			BigDecimal balance = zbalance.add(commision);
			BigDecimal beans = order.getBeans()==null?new BigDecimal(0):order.getBeans();

			if (cash.compareTo(new BigDecimal(0))>0 || balance.compareTo(new BigDecimal(0))>0) {
				sbBuffer = sbBuffer.append("¥:").append(cash.add(balance).toString()) ;
			}
			if (beans.compareTo(new BigDecimal(0)) >0 || sellerCoin.compareTo(new BigDecimal(0)) >0) {
				sbBuffer = sbBuffer.append("鸟币:").append(beans.add(sellerCoin));
			}

			order.setReallyPayAmount(sbBuffer.toString());

			order.setPicUrl(fileUrl+order.getPicUrl());

			BigDecimal zeroNum = new BigDecimal(0);
			order.setTotalAmount(order.getTotalAmount().subtract(order.getCuser()).compareTo(zeroNum)<0?zeroNum:order.getTotalAmount().subtract(order.getCuser()));
			order.setPayTimeStr(DateUtil.format(order.getPayTime(), DateUtil.defaultSimpleFormater));
			order.setCname("套餐");
			if (order!=null) {
				if (null == order.getIsTimeOut() && null == order.getUseStatus()) {
					if (order.getStatus() == 1) {
						order.setOrderStautsDesc("套餐·已支付");
					}
					if (order.getStatus() == 0) {
						order.setOrderStautsDesc("套餐·待支付");
					}
					if (order.getStatus() == 2 ||order.getStatus() == 3||order.getStatus() == 4 ) {
						order.setOrderStautsDesc("套餐·已关闭");
					}

				}else {
					if (order.getIsTimeOut()>0) {//如果已过期 就是已完成
						order.setOrderStautsDesc("套餐·已完成");
					}else if(order.getUseStatus()>0){//如果未过期，并且未使用数量大于0
						order.setOrderStautsDesc("套餐·已支付");
					}else if(order.getUseStatus()==0){//如果未过期，并且未使用数量等于0
						order.setOrderStautsDesc("套餐·已完成");
					}
				}
				resultList.add(order);
			}
		}
		return resultList;
	}
	
	/**
	 * 描述：订单相关状态匹配
	 * 订单有4个状态
		1、未支付（未支付）
		2、已支付（粉丝券状态：已支付  未使用）
		3、已完成（粉丝券状态：已支付 已使用、已过期）
		4、已关闭（支付超时或取消订单）
	 * */
	public Map<Object, Object> packageOrderStatus(String status,List<Integer> useStauts, List<Integer> isTimeout){
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		//判断是否有套餐相关状态  
		if (status!=null && useStauts.size()>0 && isTimeout.size()>0) {
			if (status.equals("0")){
				map.put("state", 0);
				map.put("stateDesc", "待支付");
			}
			if (status.equals("2") || status.equals("3") ){
				map.put("state", 2);
				map.put("stateDesc", "已关闭");
			}
			if (status.equals("1")) {
				if (useStauts.contains(0) && isTimeout.contains(0)) {//未使用 未过期 
					map.put("state", 1);
					map.put("stateDesc", "已支付");
				}
				//已使用 未过期     ,已使用 已过期       , 未使用 已过期
				if ((useStauts.contains(2) && isTimeout.contains(0)) || ((useStauts.contains(2) && isTimeout.contains(1)) || (useStauts.contains(2) && isTimeout.contains(0) ))) {
					map.put("state", 3);
					map.put("stateDesc", "已完成");
				}
			}
		}else {
			if (status.equals("0")){
				map.put("state", 0);
				map.put("stateDesc", "待支付");
			}
			if (status.equals("2") || status.equals("3") ){
				map.put("state", 2);
				map.put("stateDesc", "已关闭");
			}
			if (status.equals("1")) {
				map.put("state", 1);
				map.put("stateDesc", "已支付");
			}
		}
		return map;
	}
	
	/**
	 * 描述：订单相关状态匹配
	 * */
//	public String couponFansOrderListStatus(String status){
//		String state = "";
//		if (status!=null) {
//			if (status.equals("0"))
//				state = "待支付";
//			if (status.equals("1"))
//				state = "已完成";
//			if (status.equals("2"))
//				state = "已取消";
//		}
//		return state;
//	}
	
	public String sellerPackageOrderPayType(String paymentType){
		String payType = "";
		if (paymentType.equals("1000015") || paymentType.equals("1000020") || paymentType.equals("1000027") ) {
			payType = "鸟币支付";
		}
		if (paymentType.toString().equals("1000000")) {
			payType = "余额支付";
		}
		if (paymentType.toString().equals("1000001") ||paymentType.equals("1000014")  || paymentType.equals("1000022") || paymentType.equals("1000023")) {
			payType = "支付宝";
		}
		if (paymentType.equals("1000003") || paymentType.equals("1000013") || paymentType.equals("1000024") || paymentType.equals("1000025")) {
			payType = "微信支付";
		}
		if (paymentType.equals("1000011")) {
			payType = "优惠券支付";
		}
		return payType;
	}
	
	
	/**
	 * 描述：订单相关状态匹配
	 * */
	public String currentOrderStatus(String orderStatus,List<Integer> useStauts, List<Integer> isTimeout){
		String state = "";
		if (orderStatus!=null && useStauts.size()>0 && isTimeout.size()>0) {
			if (orderStatus.equals("0"))
				state = "已经为您预留套餐,请尽快支付";
				
			if (orderStatus.equals("1")){
				if (!useStauts.contains(1)) {
					state = "套餐已使用";
				}
				if (useStauts.contains(0) && isTimeout.contains(0)) {
					state = "请在套餐有效期内到店使用";
				}
				if (useStauts.contains(0) && isTimeout.contains(1)) {
					state = "套餐已过期";
				}
			}
			if (orderStatus.equals("2")){
				state = "订单已关闭！";
			}
		}else {
			if (orderStatus.equals("0"))
				state = "已经为您预留套餐,请尽快支付";
				
			if (orderStatus.equals("1")){
				if (useStauts.contains(0) && isTimeout.contains(0)) {
					state = "请在套餐有效期内到店使用";
				}
			}
			if (orderStatus.equals("2") || orderStatus.equals("3")){
				state = "订单已关闭！";
			}
		}
		return state;
	}
	
	
	
	/**
	 * 描述：支付类型描述
	 * */
//	public String packageOrderPayType(String type){
//		String payType = "";
//		if (type!=null && ""!=type) {
//			if (type.equals("1000015") || type.equals("1000015")) {
//				payType = "鸟币支付";
//			}
//			if (type.equals("1000000")) {
//				payType = "余额支付";
//			}
//			if (type.equals("1000001") || type.equals("1000022")) {
//				payType = "支付宝";
//			}
//			if (type.equals("1000003") || type.equals("1000024")) {
//				payType = "微信";
//			}
//		}
//			
//		return payType;
//	}
}
