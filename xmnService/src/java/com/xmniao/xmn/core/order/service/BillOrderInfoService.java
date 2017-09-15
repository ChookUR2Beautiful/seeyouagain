package com.xmniao.xmn.core.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.BillOrderQueryRequest;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.order.dao.CouponFansOrderDao;
import com.xmniao.xmn.core.payment.service.BillPaymentService;
import com.xmniao.xmn.core.seller.entity.Redpacket;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageGrantDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 项目描述：XmnService
 * API描述： 订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */
@Service
public class BillOrderInfoService {

	//日志
	private final Logger log = Logger.getLogger(BillOrderInfoService.class);
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private BillPaymentService billPaymentService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private CouponFansOrderDao couponFansOrderDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired 
	private MongoBaseService mongoBaseService;
	
	@Autowired
	private SellerPackageGrantDao sellerPackageGrantDao;
	
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	
	/**
	 * 根据订单编号去查 支付服务
	 * @param orderQueryRequest
	 * @return object
	 * */
	public Object queryBillForPaymentService(BillOrderQueryRequest orderQueryRequest){
		
		MapResponse response = null;
		
//		String uid = orderQueryRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(orderQueryRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		
		try {
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.BID, orderQueryRequest.getOrderNo());
			map.put(Constant.UID, uid);
			
			if (orderQueryRequest.getType() == 0) {//调统一订单支付接口  
				//请求支付接口
				String url = propertiesUtil.getValue("foodOrderQuery", "conf_common.properties");
				//拼装
				url = url+"?orderNumber="+orderQueryRequest.getOrderNo();
				String result = HttpConnectionUtil.doPost(url, "");
				if (StringUtils.isNotEmpty(result)) {
					if (result.indexOf("state") != -1) {
						JSONObject json = JSONObject.parseObject(result);
						return json;
					}
				}else {
					log.info("调用支付接口失败"+orderQueryRequest.getOrderNo());
					return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
				}
			}else { //自己查询咯
				Map<Object, Object> billInfoMap = billDao.queryBillInfoByOrderNo(map);
				JSONObject jsonObject = new JSONObject();
				if (billInfoMap!=null && billInfoMap.size()>0) {
					jsonObject.put("state", 200);
					return jsonObject;
				}else {
					jsonObject.put("state", 500);
					return jsonObject;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.DATA_NULL, "未知错误，请稍后或联系管理员");
		}
		return response;
	}
	
	
	/**
	 * API描述 获取订单详细信息 
	 * @author yhl
	 * @param object
	 * @return object
	 * */
	public Object queryBillOrderView(BillOrderQueryRequest orderQueryRequest){
		
		Map<Object, Object> order_view = new HashMap<Object, Object>();
		MapResponse response = null;
		try {
//			String uid = orderQueryRequest.getUid().toString();
			String uid = sessionTokenService.getStringForValue(orderQueryRequest.getSessiontoken())+"";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
			}
			
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.BID, orderQueryRequest.getOrderNo());
			map.put(Constant.UID, uid);
			
			Map<Object, Object> billInfoMap = billDao.queryBillInfoByOrderNo(map);
			if (billInfoMap!=null && billInfoMap.size()>0) {
				order_view.put("bid", billInfoMap.get("bid"));//订单编号
				order_view.put("codeid", billInfoMap.get("codeid"));//订单消费码
				order_view.put("sellerid", billInfoMap.get("sellerid"));//商户Id
				order_view.put("sellername", billInfoMap.get("sellername"));//商户名称
				order_view.put("money", billInfoMap.get("money"));//订单总额
				order_view.put("ydate", billInfoMap.get("ydate")==null?"":DateUtil.format(DateUtil.parse(billInfoMap.get("ydate").toString()), DateUtil.defaultSimpleFormater));//消费时间
				order_view.put("sdate", billInfoMap.get("sdate")==null?"":DateUtil.format(DateUtil.parse(billInfoMap.get("sdate").toString()), DateUtil.defaultSimpleFormater));//下单时间
				order_view.put("zdate", billInfoMap.get("zdate")==null?"":DateUtil.format(DateUtil.parse(billInfoMap.get("zdate").toString()), DateUtil.defaultSimpleFormater));//支付时间
				order_view.put("status", billInfoMap.get("status"));//订单状态
				order_view.put("statusDesc", this.billOrderStatus(billInfoMap.get("status").toString()));
				//实际支付金额  总金额 - 满减 - 立减 - 优惠券
				BigDecimal fullReduction = new BigDecimal(billInfoMap.get("full_reduction")==null?"0":billInfoMap.get("full_reduction").toString());
				BigDecimal reduction = new BigDecimal(billInfoMap.get("reduction")==null?"0":billInfoMap.get("reduction").toString());
				BigDecimal cuser = new BigDecimal(billInfoMap.get("cuser")==null?"0":billInfoMap.get("cuser").toString());
				BigDecimal money = new BigDecimal(billInfoMap.get("money")==null?"0":billInfoMap.get("money").toString());
				
				order_view.put("payment", money.subtract(fullReduction).subtract(reduction).subtract(cuser));//支付金额
				order_view.put("cuser", billInfoMap.get("cuser"));//优惠券支付
				order_view.put("reduction", billInfoMap.get("reduction"));//下单立减
				order_view.put("fullReduction", billInfoMap.get("full_reduction"));//订单满减
				order_view.put("integral", billInfoMap.get("integral"));//订单满减
				order_view.put("paytype", this.billOrderPayType(billInfoMap));//支付方式
				order_view.put("rPhone", billInfoMap.get("r_phone")==null?"":billInfoMap.get("r_phone"));//订单满减
				
				order_view.put("isAgio", propertiesUtil.getValue("isAgio", "conf_common.properties")) ;//订单是否有下单立减
				order_view.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));//订单是否有赠送积分
				
				
				//查看商家有无设置推荐红包  
				Map<Object, Object> redpacketMap = new HashMap<Object, Object>();
				redpacketMap.put("sellerid", billInfoMap.get("sellerid"));
				redpacketMap.put("type", 3);
				//查询好友的在该商家领取记录
				List<Map<Object, Object>> recordListMap= sellerDao.queryUserRedPacketRecord(billInfoMap.get("bid").toString());
				if (recordListMap.size()>0) {
					for (int i = 0; i < recordListMap.size(); i++) {
						Map<Object, Object> recordMap = recordListMap.get(i);
						String redpacketType = recordMap.get("redpacketType")==null?"":recordMap.get("redpacketType").toString();
						if (redpacketType.equals("3")) {
							order_view.put("isRedPacket", 1);
							order_view.put("singleAmount", recordMap.get("denomination"));//推荐好友获得红包
							break;
						}
					}
				}else {
					order_view.put("isRedPacket", 0);
				}
				
				//获取商家logo
				List<SellerPic> sellerPics = sellerService.querySellerPicBySelleridAndType(Integer.parseInt(billInfoMap.get("sellerid").toString()),1);
				if (sellerPics.size()>0) {
					order_view.put("logo", sellerPics.get(0).getUrl());//商家logo
				}else {
					order_view.put("logo", "");//商家logo
				}
				
				//查询使用粉丝券的信息
				if (null!=billInfoMap.get("coupon_type")) {
					order_view.put("couponType", billInfoMap.get("coupon_type").toString());//优惠券类型
					order_view.put("couponDesc", this.billOrderCouponTypeStatus(billInfoMap.get("coupon_type").toString()));//优惠券类型
					//获取订单使用的粉丝券   消费订单查询传值 bid
					CouponInfo couponInfo = couponFansOrderDao.queryCouponDetailInfoByBid(map);
					if (couponInfo!=null) {
						order_view.put("cdid", couponInfo.getCdid());
						order_view.put("cid", couponInfo.getCid());
						order_view.put("denomination", couponInfo.getDenomination());
						order_view.put("seats", couponInfo.getSeats());
					}else {
						order_view.put("denomination", cuser);//优惠券使用金额
						order_view.put("seats", "");
					}
				}else {
					order_view.put("couponType", 0);//没有使用优惠券返回类型
				}
				
				//查询 套餐券使用信息
				Map<Object, Object> grant = sellerPackageGrantDao.querySellerPackageGrantByBid(billInfoMap.get("bid").toString());
				if (grant!=null) {
					order_view.put("packageStatus", grant.get("packageStatus"));
					order_view.put("useNum", grant.get("useNum"));
					order_view.put("grantMoney", grant.get("grantMoney"));
				}else {
					order_view.put("packageStatus", 0);
				}
				 
				//通过mongoDB获取商户 基本信息
//				MSeller mseller = mongoBaseService.findOne("sellerid", billInfoMap.get("sellerid")+"",MSeller.class);
//				if (mseller!=null) {
//					order_view.put("agio", mseller.getAgio());//商家立减
//					order_view.put("sellerPhone", mseller.getTel());//商家电话
//				}else {
					Seller seller = sellerService.querySellerBySellerid(Long.valueOf(billInfoMap.get("sellerid").toString()));
					if (seller!=null && null!=seller.getSellerid()) {
						order_view.put("agio", seller.getAgio());
						order_view.put("agio", billPaymentService.calculationSellerDiscount(order_view));
						order_view.put("sellerPhone", seller.getTel());//商家电话
					}else {
						order_view.put("sellerPhone", "");//商家电话
						order_view.put("agio", "0");
					}
//				}
				
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				order_view.put("platformPhone", propertiesUtil.getValue("platform_telphone", "conf_common.properties"));
				response.setResponse(order_view);
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "该订单不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误，请稍后或联系管理员");
		}
		return response;
	}
	
	/**
	 * API描述 获取订单列表 
	 * @author yhl
	 * @param object
	 * @return object
	 * */
	public Object queryBillOrderList(BillOrderQueryRequest orderQueryRequest){
		
		MapResponse response = null;
		try {
//			String uid = orderQueryRequest.getUid().toString();
			String uid = sessionTokenService.getStringForValue(orderQueryRequest.getSessiontoken())+"";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
			}
			
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.BID, orderQueryRequest.getOrderNo());
			map.put(Constant.UID, uid);
			map.put("page", orderQueryRequest.getPage());
			map.put("limit", Constant.PAGE_LIMIT);
			
			List<Map<Object, Object>> billList = billDao.queryBillListByUid(map);
			List<Map<Object, Object>> billOrderList = new ArrayList<Map<Object, Object>>();
			if (billList!=null && billList.size()>0) {
				
				if (billList.size()>0) {
					for (int i = 0; i < billList.size(); i++) {
						Map<Object, Object> resultInfo = new HashMap<Object, Object>();
						Map<Object, Object> info = billList.get(i);
						resultInfo.put("bid", info.get("bid"));
						resultInfo.put("sellername", info.get("sellername"));
						resultInfo.put("sellerid", info.get("sellerid"));
						resultInfo.put("logo", fileUrl+info.get("picurl"));
						resultInfo.put("sdate", info.get("sdate")==null?"":DateUtil.format(DateUtil.parse(info.get("sdate").toString())));
						resultInfo.put("zdate", info.get("zdate")==null?"":DateUtil.format(DateUtil.parse(info.get("zdate").toString())));
						resultInfo.put("ydate", info.get("ydate")==null?"":DateUtil.format(DateUtil.parse(info.get("ydate").toString())));
						resultInfo.put("money", info.get("money"));
						resultInfo.put("payment", info.get("payment"));
						resultInfo.put("status", info.get("status"));
						resultInfo.put("statusDesc", this.billOrderStatus(info.get("status").toString()));
						
						//套餐券使用信息
						resultInfo.put("packageStatus", info.get("packageStatus"));
						resultInfo.put("useNum", info.get("useNum"));
						resultInfo.put("grantMoney", info.get("grantMoney"));
						
						//实际支付金额  总金额 - 满减 - 立减 - 优惠券
						BigDecimal fullReduction = new BigDecimal(info.get("full_reduction")==null?"0":info.get("full_reduction").toString());
						BigDecimal reduction = new BigDecimal(info.get("reduction")==null?"0":info.get("reduction").toString());
						BigDecimal cuser = new BigDecimal(info.get("cuser")==null?"0":info.get("cuser").toString());
						BigDecimal money = new BigDecimal(info.get("money")==null?"0":info.get("money").toString());
						
						resultInfo.put("payment", money.subtract(fullReduction).subtract(reduction).subtract(cuser));//支付金额
						
						//查询使用粉丝券的信息
						if (null!=info.get("coupon_type")) {
							resultInfo.put("couponType", info.get("coupon_type").toString());//优惠券类型
							resultInfo.put("couponDesc", this.billOrderCouponTypeStatus(info.get("coupon_type").toString()));//优惠券类型
							//获取订单使用的粉丝券
//							CouponInfo couponInfo = couponFansOrderDao.queryCouponDetailInfoByBid(map);
//							if (couponInfo!=null) {
								resultInfo.put("cdid", info.get("cdid"));
								resultInfo.put("denomination", info.get("denomination"));
								resultInfo.put("seats", info.get("seats"));
//							}
						}else {
							resultInfo.put("couponType", 0);//没有使用优惠券返回类型
						}
						billOrderList.add(resultInfo);
					}
				}
			}
			response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			Map<Object, Object> resMap = new HashMap<Object,Object>();
			resMap.put("billList", billOrderList);
			resMap.put("platformPhone", propertiesUtil.getValue("platform_telphone", "conf_common.properties"));
			response.setResponse(resMap);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误，请稍后或联系管理员");
		}
		return response;
	}
	
	
	/**
	 * 描述：查询用户买单后是发有机会获取到点红包
	 * @param 
	 * @return object
	 * */
	public Object queryBillOrderRedPacketView(BillOrderQueryRequest billOrderQueryRequest){
		
		String uid = sessionTokenService.getStringForValue(billOrderQueryRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		//查看商家有无设置推荐红包  
		try {
			
			//首先查询有无订单
			Map<Object, Object> billParamMap = new HashMap<Object, Object>();
			billParamMap.put("bid", billOrderQueryRequest.getOrderNo());
			Map<Object, Object> billInfoMap = billDao.queryBillInfoByOrderNo(billParamMap);
			if (billInfoMap!=null) {
				//查询商家的红包设置
				Map<Object, Object> redpacketMap = new HashMap<Object, Object>();
				redpacketMap.put("sellerid", billInfoMap.get("sellerid"));
				redpacketMap.put("type", 2);
				BigDecimal profit = new BigDecimal(billInfoMap.get("profit")==null?"0":billInfoMap.get("profit").toString());
				BigDecimal commision = new BigDecimal(billInfoMap.get("commision")==null?"0":billInfoMap.get("commision").toString());
				BigDecimal payment = new BigDecimal(billInfoMap.get("payment")==null?"0":billInfoMap.get("payment").toString());
				redpacketMap.put("amount",profit.add(commision).add(payment));
				//查询有无资格获取红包
				Redpacket redpacketRec = new Redpacket();
				List<Map<Object, Object>> recordListMap= sellerDao.queryUserRedPacketRecord(billInfoMap.get("bid").toString());
				if (recordListMap.size()>0) {
					for (int i = 0; i < recordListMap.size(); i++) {
						Map<Object, Object> recordMap = recordListMap.get(i);
						String redpacketType = recordMap.get("redpacketType")==null?"":recordMap.get("redpacketType").toString();
						if (redpacketType.equals("2")) {
							resultMap.put("isFullSend", 1);//是否有满赠
							redpacketRec.setSingleAmount(new BigDecimal(recordMap.get("denomination")==null?"0":recordMap.get("denomination").toString()));
							redpacketRec.setSellerid(Integer.parseInt(billInfoMap.get("sellerid").toString()));
							redpacketRec.setRedpacketName(recordMap.get("redpacketName")==null?"":recordMap.get("redpacketName").toString());
							redpacketRec.setRedpacketType(Integer.parseInt(recordMap.get("redpacketType")==null?"":recordMap.get("redpacketType").toString())) ;
							String redPacketDesc = propertiesUtil.getValue("platformBuySucessDesc", "conf_common.properties");
							redpacketRec.setBuyRedPacketDesc(redPacketDesc);
							resultMap.put("redPacket", redpacketRec);
							response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
							response.setResponse(resultMap);
						}
					}
				}else {
					response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
					response.setResponse(resultMap);
					resultMap.put("isFullSend", 0);
				}
			}else {
				response = new MapResponse(ResponseCode.FAILURE, "未获取到订单信息");
				return response;
			}
			if (resultMap!=null && resultMap.size()<=0) {
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				response.setResponse(resultMap);
				resultMap.put("isFullSend", 0);
			}
			
		} catch (Exception e) {
			log.info("获取商家满赠红包异常");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "操作异常");
		}
		return response;
		
	}
	
	
	
	/**
	 * API描述  取消订单  -调起支付服务接口
	 * @param object
	 * @return object
	 * */
	public Object billOrderCancle(BillOrderQueryRequest orderQueryRequest){
		
//		String uid = orderQueryRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(orderQueryRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		MapResponse response = null;
		try {
			//查询订单信息
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.BID, orderQueryRequest.getOrderNo());//（唯一订单号）
			map.put(Constant.UID, uid);
			Map<Object, Object> billOrderMap = billDao.queryBillInfoByOrderNo(map);
			
			if (billOrderMap!=null && billOrderMap.size()>0) {
				//组装生成签名
				Map<String, String> tranMap = new HashMap<String, String>();
				tranMap.put("amount", billOrderMap.get("money").toString());//（支付总金额）
				tranMap.put("orderNumber", orderQueryRequest.getOrderNo());//（支付总金额）
				tranMap.put("randomNumber", (int)((Math.random()*9+1)*100000)+"");//（随机数）
				//生成签名
				String url = billPaymentService.transMap(tranMap, "foodOrderCancle");
				
				String result = HttpConnectionUtil.doPost(url, "");
				System.out.println("===================:"+result);
				if (StringUtils.isNotEmpty(result)) {
					if (result.indexOf("state") != -1) {
						JSONObject json = JSONObject.parseObject(result);
						return json;
					}
				}else {
					log.info("调用支付接口失败"+orderQueryRequest.getOrderNo());
					return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
				}
				
			}else {
				return new MapResponse(ResponseCode.FAILURE, "未获取到订单信息");
			}
			
			return response;
		} catch (Exception e) {
			log.info("请求支付服务接口异常："+orderQueryRequest.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作异常,请重试");
		}
	}
	
	/**
	 * 描述：订单相关状态匹配
	 * */
//	public String billOrderStatus(String status){
//		String state = "";
//		if (status!=null) {
//			if (status.equals("0"))
//				state = "待支付";
//			if (status.equals("1") || status.equals("2") || status.equals("3") || status.equals("6")  || status.equals("8")|| status.equals("9"))
//				state = "已支付";
//			if (status.equals("4") || status.equals("7")|| status.equals("11")|| status.equals("13")|| status.equals("14"))
//				state = "退款中";
//			if (status.equals("15"))
//				state = "刷单退款";
//			if (status.equals("10"))
//				state = "申诉失败";
//			if (status.equals("12"))
//				state = "退款失败";
//		}
//		return state;
//	}
	public String billOrderStatus(String status){
		String state = "";
		if (status!=null) {
			if (!status.equals("0"))
				state = "已完成";
			if (status.equals("0"))
				state = "待支付";
			
		}
		return state;
	}
	
	/**
	 * 描述：订单相关状态匹配
	 * */
	public String billOrderCouponTypeStatus(String status){
		String state = "";
		if (status!=null) {
			if (status.equals("1"))
				state = "平台优惠券";
			if (status.equals("2"))
				state = "商户优惠券";
			if (status.equals("3"))
				state = "直播粉丝券";
		}
		return state;
	}
	
	/**
	 * 描述：订单相关支付
	 * */
//	public String billOrderPayType(Map<Object, Object> orderMap){
//		String payType = "";
//		
//		BigDecimal profit = new BigDecimal(orderMap.get("profit")==null?"0":orderMap.get("profit").toString());
//		BigDecimal give_money = new BigDecimal(orderMap.get("give_money")==null?"0":orderMap.get("give_money").toString());
//		BigDecimal commision = new BigDecimal(orderMap.get("commision")==null?"0":orderMap.get("commision").toString());
//		BigDecimal balanceAmount = profit.add(give_money).add(commision);
//		
//		//支付方式：1000001支付宝SDK支付，1000003 微信SDK支付,1000013 微信公众号支付
//		if (balanceAmount.compareTo(new BigDecimal(0))>0) {
//			if (orderMap.get("paytype")!=null) {
//				if (orderMap.get("paytype").toString().equals("1000001")) {
//					payType = "余额支付："+balanceAmount+"+"+"支付宝支付："+orderMap.get("payment");
//				}
//				if (orderMap.get("paytype").toString().equals("1000003")) {
//					payType = "余额支付："+balanceAmount+"+"+"微信支付："+orderMap.get("payment");
//				}
//				if (orderMap.get("paytype").toString().equals("1000000")) {
//					payType = "余额支付："+balanceAmount;
//				}
//			}
//		}else {
//			if (orderMap.get("paytype")!=null) {
//				if (orderMap.get("paytype").toString().equals("1000001")) {
//					payType ="支付宝支付："+orderMap.get("payment");
//				}
//				if (orderMap.get("paytype").toString().equals("1000003")) {
//					payType = "微信支付："+orderMap.get("payment");
//				}
//			}
//		}
//		return payType;
//	}
	public String billOrderPayType(Map<Object, Object> orderMap){
		String payType = "";
		//支付方式：1000001支付宝SDK支付，1000003 微信SDK支付,1000013 微信公众号支付
		if (orderMap.get("paytype")!=null) {
			if (orderMap.get("paytype").toString().equals("1000001") || orderMap.get("paytype").toString().equals("1000014")
					|| orderMap.get("paytype").toString().equals("1000022") || orderMap.get("paytype").toString().equals("1000023")) {
				payType = "支付宝";
			}
			if (orderMap.get("paytype").toString().equals("1000003") || orderMap.get("paytype").toString().equals("1000013")
					|| orderMap.get("paytype").toString().equals("1000024") || orderMap.get("paytype").toString().equals("1000025")) {
				payType = "微信支付";
			}
			if (orderMap.get("paytype").toString().equals("1000000")) {
				payType = "余额支付";
			}
			if (orderMap.get("paytype").toString().equals("1000015") || orderMap.get("paytype").toString().equals("1000020") || orderMap.get("paytype").toString().equals("1000027")) {
				payType = "鸟币支付";
			}
			if (orderMap.get("paytype").toString().equals("1000011")) {
				payType = "优惠券";
			}
		}
		return payType;
	}

}
