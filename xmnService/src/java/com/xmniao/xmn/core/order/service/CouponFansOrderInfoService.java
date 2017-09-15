package com.xmniao.xmn.core.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.BillOrderQueryRequest;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.order.dao.CouponFansOrderDao;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 项目描述：XmnService
 * API描述： 订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */
@Service
public class CouponFansOrderInfoService {

	//日志
	private final Logger log = Logger.getLogger(CouponFansOrderInfoService.class);
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private CouponFansOrderDao couponFansOrderDao;
	
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	
	/**
	 * API描述 获取订单详细信息 
	 * @author yhl
	 * @return object
	 * */
	public Object queryCouponOrderView(BillOrderQueryRequest orderQueryRequest){
		
		Map<Object, Object> order_view = new HashMap<Object, Object>();
		MapResponse response = null;
		try {
//			String uid = orderQueryRequest.getUid().toString();
			String uid = sessionTokenService.getStringForValue(orderQueryRequest.getSessiontoken())+"";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
			}
			
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put("order_sn", orderQueryRequest.getOrderNo());
			map.put(Constant.UID, uid);
			
			Map<Object, Object> fansOrderMap = couponFansOrderDao.queryCouponFansOrderView(map);
			if (fansOrderMap!=null) {
				order_view.put("bid",fansOrderMap.get("order_sn"));//订单编号
				order_view.put("createTime",DateUtil.format(DateUtil.parse(fansOrderMap.get("create_time").toString()),DateUtil.defaultSimpleFormater));//购买时间
				order_view.put("totalAmount",fansOrderMap.get("total_amount"));//订单总额
				
//				BigDecimal totalAmount = new BigDecimal(fansOrderMap.get("total_amount")==null?"0":fansOrderMap.get("total_amount").toString());
				BigDecimal cash = new BigDecimal(fansOrderMap.get("cash")==null?"0":fansOrderMap.get("cash").toString());
				BigDecimal commision = new BigDecimal(fansOrderMap.get("commision")==null?"0":fansOrderMap.get("commision").toString());
				BigDecimal zbalance = new BigDecimal(fansOrderMap.get("zbalance")==null?"0":fansOrderMap.get("zbalance").toString());
				BigDecimal beans = new BigDecimal(fansOrderMap.get("beans")==null?"0":fansOrderMap.get("beans").toString());
				BigDecimal payAmount = cash.add(commision).add(zbalance).add(beans);
				order_view.put("payAmount",payAmount);//第三方支付订单总额
				order_view.put("integral",fansOrderMap.get("return_integral"));
				order_view.put("status",fansOrderMap.get("status"));
				map.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));//订单是否有赠送积分
				
				order_view.put("sellerid",fansOrderMap.get("sellerid"));//订单总额
				order_view.put("zhiboThumbnail",fileUrl+fansOrderMap.get("zhibo_thumbnail")==null?fansOrderMap.get("zhibo_cover"):fansOrderMap.get("zhibo_thumbnail"));//预售缩略图
				order_view.put("paytype", this.conponFansOrderPayType(fansOrderMap));//支付方式
				order_view.put("cname", fansOrderMap.get("cname"));//优惠券名称
				order_view.put("fansCouponDesc", "直播粉丝券");//支付方式
				order_view.put("sdate", DateUtil.format(DateUtil.parse(fansOrderMap.get("create_time").toString()),DateUtil.defaultSimpleFormater)  );//下单时间
				if (fansOrderMap.get("modify_time")!=null) {
					order_view.put("modifyTime", DateUtil.format(DateUtil.parse(fansOrderMap.get("modify_time").toString()),DateUtil.defaultSimpleFormater)  );//支付方式
				}else {
					order_view.put("modifyTime", "");//支付方式
				}
				
				Seller seller = sellerService.querySellerBySellerid(Long.valueOf(fansOrderMap.get("sellerid").toString()));
				if (seller!=null) {
					order_view.put("sellername", seller.getSellername());//商家名称
					order_view.put("sellerPhone", seller.getTel());//商家电话
				}else {
					order_view.put("sellername", "");//商家名称
					order_view.put("sellerPhone", "");//商家电话
				}
				
				List<SellerPic> sellerPics = sellerService.querySellerPicBySelleridAndType(Integer.parseInt(fansOrderMap.get("sellerid").toString()),1);
				if (sellerPics.size()>0) {
					order_view.put("logo", sellerPics.get(0).getUrl());//商家logo
				}else {
					order_view.put("logo", "");//商家logo
				}
				
				Map<Object, Object> couponFansParam = new HashMap<Object, Object>();
				couponFansParam.put("uid", uid);
				couponFansParam.put("orderNo", orderQueryRequest.getOrderNo());//粉丝券订单 传值orderNo  
				CouponInfo fansCouponInfo = couponFansOrderDao.queryCouponDetailInfoByBid(couponFansParam);
				
				if (fansCouponInfo!=null) {
					order_view.put("userTime",fansCouponInfo.getStartDateStr()+"-"+fansCouponInfo.getEndDateStr());
					order_view.put("serial",fansCouponInfo.getSerial());//优惠券序列码-生成二维码
					order_view.put("seats",fansCouponInfo.getSeats());//消费人数
					order_view.put("denomination",fansCouponInfo.getDenomination());//优惠券面额
					order_view.put("isTimeout",fansCouponInfo.getIsTimeout());//是否过期
					order_view.put("userStatus",fansCouponInfo.getUserStatus());//使用状态;
					order_view.put("currOrderDesc",this.currentOrderStatus(fansOrderMap.get("status").toString(),fansCouponInfo.getUserStatus(),fansCouponInfo.getIsTimeout()));
					
				}else {
					log.info("订单详情获取粉丝券信息失败："+orderQueryRequest.getOrderNo());
					return new BaseResponse(ResponseCode.FAILURE, "获取粉丝券失败");
				}
				
				//处理订单基本
				order_view.put("statusDesc",this.couponFansOrderStatus(fansOrderMap.get("status").toString(),fansCouponInfo.getUserStatus(),fansCouponInfo.getIsTimeout()));
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				order_view.put("platformPhone", propertiesUtil.getValue("platform_telphone", "conf_common.properties"));
				order_view.put("isAgio", propertiesUtil.getValue("isAgio", "conf_common.properties")) ;//订单是否有下单立减
				order_view.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));//订单是否有赠送积分
				
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

	public Object queryCouponOrderList(BillOrderQueryRequest orderQueryRequest) {
		return queryCouponOrderList(orderQueryRequest, null, null);
	}

	
	/**
	 * API描述 获取订单列表 
	 * @author yhl
	 * @return object
	 * */
	public Object queryCouponOrderList(BillOrderQueryRequest orderQueryRequest, String maxTime, Integer status){
		
		MapResponse response = null;
		try {
//			String uid = orderQueryRequest.getUid().toString();
			String uid = sessionTokenService.getStringForValue(orderQueryRequest.getSessiontoken())+"";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
			}
			
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.UID, uid);
			map.put("page", orderQueryRequest.getPage());
			map.put("limit", Constant.PAGE_LIMIT);
			map.put("maxTime", maxTime);
			map.put("status", status);
			List<Map<Object, Object>> fansList = couponFansOrderDao.queryCouponFansOrderList(map);
			List<Map<Object, Object>> billOrderList = new ArrayList<Map<Object, Object>>();
			if (fansList!=null && fansList.size()>0) {
				for (int i = 0; i < fansList.size(); i++) {
					Map<Object, Object> resultInfo = new HashMap<Object, Object>();
					Map<Object, Object> info = fansList.get(i);
					
					resultInfo.put("bid", info.get("order_sn"));
					resultInfo.put("cname", info.get("cname"));//粉丝券名称
					
					resultInfo.put("sellername", info.get("sellername")==null?"":info.get("sellername"));
					resultInfo.put("sellerid", info.get("sellerid"));
					
					resultInfo.put("zhiboThumbnail", fileUrl+info.get("zhibo_thumbnail"));//封面缩略图
					resultInfo.put("money", info.get("total_amount"));//总金额
					
					BigDecimal cash = new BigDecimal(info.get("cash")==null?"0":info.get("cash").toString());
					BigDecimal balance = new BigDecimal(info.get("balance")==null?"0":info.get("balance").toString());
					BigDecimal commision = new BigDecimal(info.get("commision")==null?"0":info.get("commision").toString());
					BigDecimal beans = new BigDecimal(info.get("beans")==null?"0":info.get("beans").toString());
					
					resultInfo.put("payment", cash.add(balance).add(commision).add(beans));//实际支付
					resultInfo.put("beans", info.get("beans"));//鸟币支付
					resultInfo.put("seats", info.get("seats"));//使用人数
					StringBuffer useTime = new StringBuffer("使用期限：");
					if (info.get("start_date")!=null) {
						useTime = useTime.append(DateUtil.format(DateUtil.parse(info.get("start_date").toString()),DateUtil.daySimpleFormater));
					}
					if (info.get("end_date")!=null) {
						useTime = useTime.append(" - ").append(DateUtil.format(DateUtil.parse(info.get("end_date").toString()),DateUtil.daySimpleFormater));
					}
					resultInfo.put("useTime", useTime.toString());//使用期限
					resultInfo.put("status", info.get("status"));
					resultInfo.put("userStatus", info.get("user_status"));
					resultInfo.put("isTimeOut", info.get("isTimeOut"));
					resultInfo.put("create_time", info.get("create_time"));
//					resultInfo.put("paymentType", this.conponFansOrderPayType(info));
//					resultInfo.put("statusDesc", this.couponFansOrderListStatus(info.get("status").toString()));
					
					Integer userStatuts = info.get("user_status")==null?0:Integer.parseInt(info.get("user_status").toString());
					Integer isTimeOut = info.get("isTimeOut")==null?0:Integer.parseInt(info.get("isTimeOut").toString());
					
					resultInfo.put("statusDesc", this.couponFansOrderStatus(info.get("status").toString(),userStatuts,isTimeOut));
					billOrderList.add(resultInfo);
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
	 * 描述：订单相关状态匹配
	 * 订单有4个状态
		1、未支付（未支付）
		2、已支付（粉丝券状态：已支付  未使用）
		3、已完成（粉丝券状态：已支付 已使用、已过期）
		4、已关闭（支付超时或取消订单）
	 * */
	public String couponFansOrderStatus(String status,Integer useStauts, Integer isTimeout){
		String state="";
		if (status.equals("0")){
			state = "待支付";
		}
		if (status.equals("2") || status.equals("3") ){
			state = "已关闭";
		}
		if (status.equals("1")) {
			if (useStauts == 0 && isTimeout == 0) {//未使用 未过期
				state = "已支付";
			}
			//已使用 未过期     ,已使用 已过期       , 未使用 已过期
			if ((useStauts == 2 && isTimeout == 0) || (useStauts == 2 && isTimeout == 1) || (useStauts == 0 && isTimeout == 1)) {
				state = "已完成";
			}
		}
		return state;
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
	
	/**
	 * 描述：订单相关状态匹配
	 * */
	public String currentOrderStatus(String orderStatus,Integer useStatus,Integer isTimeOut){
		String state = "";
		if (orderStatus!=null) {
			if (orderStatus.equals("0"))
				state = "已经为您预售直播粉丝券,请尽快支付";
				
			if (orderStatus.equals("1")){
				if (useStatus==2) {
					state = "直播粉丝券已使用";
				}
				if (useStatus==0 && isTimeOut == 0) {
					state = "请在粉丝券有效期内到店使用";
				}
				if (useStatus==0 && isTimeOut == 1) {
					state = "直播粉丝券已过期";
				}
			}
				
			if (orderStatus.equals("2")){
				state = "订单已关闭！";
			}
				
		}
		return state;
	}
	
	/**
	 * 描述：订单相关支付
	 * */
//	public String conponFansOrderPayType(Map<Object, Object> orderMap){
//		StringBuffer payType = new StringBuffer();
//		
//		//余额
//		BigDecimal give_money = new BigDecimal(orderMap.get("zbalance")==null?"0":orderMap.get("zbalance").toString());
//		BigDecimal commision = new BigDecimal(orderMap.get("commision")==null?"0":orderMap.get("commision").toString());
//		BigDecimal balanceAmount = give_money.add(commision);
//		//
//		BigDecimal beansAmount = new BigDecimal(orderMap.get("beans")==null?"0":orderMap.get("beans").toString());
//		
//		BigDecimal cash = new BigDecimal(orderMap.get("cash")==null?"0":orderMap.get("cash").toString());
//		if (orderMap.get("payment_type")!=null) {
//			if (orderMap.get("payment_type").toString().equals("1000015")) {
//				payType = payType.append("粉丝卡支付："+beansAmount);
//			}
//			if (orderMap.get("payment_type").toString().equals("1000000")) {
//				payType = payType.append( "余额额支付："+balanceAmount);
//			}
//			if (orderMap.get("payment_type").toString().equals("1000000") && beansAmount.compareTo(new BigDecimal(0))>0) {
//				payType = payType.append("粉丝卡支付："+beansAmount +"+"+ "余额额支付："+balanceAmount);
//			}
//			if (orderMap.get("payment_type").toString().equals("1000001")) {
//				if (balanceAmount.compareTo(new BigDecimal(0))>0 && beansAmount.compareTo(new BigDecimal(0))>0) {
//					payType = payType.append("粉丝卡支付："+beansAmount +"+"+ "余额额支付："+balanceAmount+"+"+"支付宝："+cash);
//				}
//				if (balanceAmount.compareTo(new BigDecimal(0))>0 && beansAmount.compareTo(new BigDecimal(0))==0) {
//					payType = payType.append("余额额支付："+balanceAmount+"+"+"支付宝："+cash);
//				}
//				if (balanceAmount.compareTo(new BigDecimal(0))==0 && beansAmount.compareTo(new BigDecimal(0))>0) {
//					payType = payType.append("粉丝卡支付："+beansAmount +"+"+"支付宝："+cash);
//				}
//			}
//			if (orderMap.get("payment_type").toString().equals("1000003")) {
//				if (balanceAmount.compareTo(new BigDecimal(0))>0 && beansAmount.compareTo(new BigDecimal(0))>0) {
//					payType = payType.append("粉丝卡支付："+beansAmount +"+"+ "余额支付："+balanceAmount+"+"+"微信："+cash);
//				}
//				if (balanceAmount.compareTo(new BigDecimal(0))>0 && beansAmount.compareTo(new BigDecimal(0))==0) {
//					payType = payType.append("余额额支付："+balanceAmount+"+"+"微信："+cash);
//				}
//				if (balanceAmount.compareTo(new BigDecimal(0))==0 && beansAmount.compareTo(new BigDecimal(0))>0) {
//					payType = payType.append("粉丝卡支付："+beansAmount +"+"+"微信："+cash);
//				}
//			}
//		}
//		
//		return payType.toString();
//	}
	
	
	public String conponFansOrderPayType(Map<Object, Object> orderMap){
		String payType = "";
		if (orderMap.get("payment_type")!=null) {
			if (orderMap.get("payment_type").toString().equals("1000015") || orderMap.get("payment_type").toString().equals("1000020") ) {
				payType = "鸟币支付";
			}
			if (orderMap.get("payment_type").toString().equals("1000000")) {
				payType = "余额支付";
			}
			if (orderMap.get("payment_type").toString().equals("1000001") || orderMap.get("payment_type").toString().equals("1000014") 
					|| orderMap.get("payment_type").toString().equals("1000022") || orderMap.get("payment_type").toString().equals("1000023")) {
				payType = "支付宝";
			}
			if (orderMap.get("payment_type").toString().equals("1000003") || orderMap.get("payment_type").toString().equals("1000013")
					|| orderMap.get("payment_type").toString().equals("1000024") || orderMap.get("payment_type").toString().equals("1000025")) {
				payType = "微信";
			}
		}
		return payType;
	}

}
