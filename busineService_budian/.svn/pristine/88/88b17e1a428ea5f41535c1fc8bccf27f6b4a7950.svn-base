/**    
 * 文件名：FansCouponServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2017年3月14日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.dao.live.CouponOrderDao;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.order.SellerActivityServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：FansCouponServiceImpl
 * 
 * 类描述： 将粉丝券购买相关服务腾移至当前class处理
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月14日 上午10:49:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FansCouponServiceImpl {
	// 初始化日志类
	private final Logger log = Logger.getLogger(LiveOrderServiceImpl.class);
	
	@Autowired
	private CommonServiceImpl commonServiceImpl;

	@Autowired
	private CouponOrderDao couponOrderDao;
	
	@Autowired
	private SellerActivityServiceImpl sellerActivityServiceImpl;
	
	
	private static BigDecimal ZERO = new BigDecimal(0.00);// 0


	/**
	 * 更新粉丝券订单 一、成功  1 支付鸟粪卡  1.修改订单状态 t_coupon_order 2.如有使用，消费预售抵用券 t_coupon_detail
	 * 3.发放粉丝券 t_coupon_detail 4.赠送预售抵用券t_coupon_detail 5.赠送积分
	 * 
	 * 二、失败/取消 1.修改订单状态 2.恢复占用的预售抵用券 3.更新粉丝券库存数量
	 */
	@Transactional(rollbackFor = { FailureException.class, Exception.class,
			RuntimeException.class })
	public Map<String, String> updateCouponOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新粉丝券订单updateCouponOrder"+paraMap);
		Map<String, String> resultMap = new HashMap<>();
		try {
			if (StringUtils.isBlank(paraMap.get("orderNo"))|| StringUtils.isBlank(paraMap.get("status"))) {
				log.error("订单号或订单状态为空");
				throw new FailureException(1, "订单号或订单状态为空");
			}
			String orderNo = paraMap.get("orderNo");// 订单号

			resultMap.put("orderNo", orderNo.toString());

			Map<String, Object> couponOrder = couponOrderDao
					.getCouponOrder(orderNo);

			if (couponOrder != null) {
				
				int status = Integer.valueOf(paraMap.get("status"));// 要修改的状态
				int orderStatus = Integer.valueOf(couponOrder.get("status")
						+ "");// 订单状态

				if (orderStatus == status && (status == 1 || status == 2 || status == 3)) {
					log.info("订单状态已修改成功，请勿重复提交");
					return resultMap;
				}
				
				paraMap.put("modify_time",formatDate());//更新时间
				paraMap.put("version",couponOrder.get("version").toString());//版本号，用于乐观锁控制
				
				Map<String, Object> couponAnchorRef = couponOrderDao
						.couponAnchorRef(couponOrder.get("anchor_cid").toString());// 查询粉丝券id
				if (couponAnchorRef == null) {
					log.error("未查询的订单："+orderNo+"和粉丝券的关联关系");
					throw new FailureException(1, "未查询的订单："+orderNo+"和粉丝券的关联关系");
				}
				
				couponOrder.put("cid",couponAnchorRef.get("cid"));//粉丝券id
				
				if (orderStatus == 0 && status == 1) {// 0-1 在支付成功
					
					// 验证传入参数
					if (!verifyParam(paraMap)) {
						log.error("传入参数部分为空" + paraMap);
						throw new FailureException(1, "传入参数部分为空");
					}
					
					if (!verifyDbParam(paraMap, couponOrder)) {
						log.error("传入参数与后台订单参数不匹配");
						throw new FailureException(1, "传入订单参数与后台订单参数不匹配");
					}
					
//					BigDecimal beans=(BigDecimal)couponOrder.get("beans");
					
					/**
					 * 如果使用鸟币，则支付鸟币（该功能移植支付操作）
					 */
					/*if(beans.compareTo(ZERO)==1){
						Map<String,String> map = new HashMap<>();
						map.put("uid",couponOrder.get("uid").toString());
						map.put("rtype","8");
						map.put("zbalance",beans.toString());
						map.put("remarks",paraMap.get("orderNo"));
						boolean result = commonServiceImpl.updateLiveWallet(map);
						if (!result) {
							log.error("业务服务调用更新鸟币失败");
							throw new FailureException(1, "业务服务调用更新鸟币失败");
						}
					}*/
					
					/**
					 * 卖出粉丝券，给主播分账
					 */
//					不给主播分账 了，2017年4月17日。					
//					try {
//						BigDecimal balance = (((BigDecimal)couponOrder.get("total_amount")).subtract((BigDecimal)couponOrder.get("cuser")));
//						if (balance.compareTo(ZERO)==1) {
//							Map<String,String> walletMap = new HashMap<>();
//							walletMap.put("uid",couponAnchorRef.get("uid").toString());
//							walletMap.put("balance",balance.toString());
//							walletMap.put("rtype","9");
//							walletMap.put("percent",couponAnchorRef.get("sale_coupon_ration").toString());
//							walletMap.put("remarks",paraMap.get("orderNo"));
//							walletMap.put("description",couponAnchorRef.get("sale_coupon_ration").toString());
//							boolean result = commonServiceImpl.updateLiveWallet(walletMap);
//							if (!result) {
//								log.error("调用支付服务,购买粉丝券给主播分账失败，订单号："+paraMap.get("orderNo"));
//							}
//						}
//					} catch (Exception e) {
//						log.error("购买粉丝券给主播分账失败，订单号："+paraMap.get("orderNo"),e);
//					}
					
					/**
					 * 更新订单状态
					 */
					successUpdate(paraMap, couponOrder,couponAnchorRef);
				} else if (orderStatus == 0 && (status == 2 ||status == 3)) {// 0-2支付失败，解除占用的预售抵用券3增加库存
					
					
					failUpdate(paraMap, couponOrder);
					
				} else {
					log.error("订单状态异常orderStatus" + orderStatus + ",status:"
							+ status);
					throw new FailureException(1, "订单状态异常orderStatus"
							+ orderStatus + ",status:" + status);
				}

			} else {
				log.error("未查询到订单号为：" + orderNo + "的订单");
				throw new FailureException(1, "为查询到该订单");
			}

			log.info("更新粉丝券订单成功");

			return resultMap;
		} catch (Exception e) {
			log.error("更新粉丝券订单失败", e);
			throw new FailureException(1, "更新粉丝券订单失败");
		}

	}

	/**
	 * 验证传入参数
	 * 
	 * @param paraMap
	 */
	private boolean verifyParam(Map<String, String> paraMap) {
		if (StringUtils.isBlank(paraMap.get("orderNo"))
				|| StringUtils.isBlank(paraMap.get("uid"))
				|| StringUtils.isBlank(paraMap.get("status"))
				|| StringUtils.isBlank(paraMap.get("payType"))
				|| StringUtils.isBlank(paraMap.get("totalAmount"))
				|| StringUtils.isBlank(paraMap.get("cash"))
				|| StringUtils.isBlank(paraMap.get("balance"))
				|| StringUtils.isBlank(paraMap.get("commision"))
				|| StringUtils.isBlank(paraMap.get("zbalance"))
				|| StringUtils.isBlank(paraMap.get("integral"))
				|| StringUtils.isBlank(paraMap.get("beans"))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 验证订单参数与数据库订单信息书否匹配
	 * 
	 * @param paraMap
	 * @param orderMap
	 * @return
	 */
	private boolean verifyDbParam(Map<String, String> paraMap,
			Map<String, Object> orderMap) {
		// 验证订单参数与数据库订单信息书否匹配
		if (paraMap.get("uid").equals(orderMap.get("uid") + "")
				&& paraMap.get("totalAmount").equals(
						orderMap.get("total_amount").toString())
				&& paraMap.get("balance").equals(
						orderMap.get("balance").toString())
				&& paraMap.get("commision").equals(
						orderMap.get("commision").toString())
				&& paraMap.get("zbalance").equals(
						orderMap.get("zbalance").toString())
				&& paraMap.get("integral").equals(
						orderMap.get("integral").toString())
				&& paraMap.get("cash").equals(orderMap.get("cash").toString())
				&& paraMap.get("beans")
						.equals(orderMap.get("beans").toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新订单状态为成功
	 * 
	 * @param paraMap
	 * @param orderMap
	 * @throws FailureException
	 *             ,Exception
	 */
	private void successUpdate(Map<String, String> paraMap,
			Map<String, Object> orderMap,Map<String, Object> couponAnchorRef) throws FailureException, Exception {

//		Map<String, Object> couponAnchorRef = couponOrderDao
//				.couponAnchorRef(orderMap.get("anchor_cid").toString());// 查询粉丝券id
		/**
		 * 1.消费预售抵用券
		 */
		// 判断是否使用预售抵用券
		if (orderMap.get("cdid") != null && orderMap.get("cdid") != "") {// 使用预售抵用券,不在赠送新的抵用券

			Map<String, Object> couponDetail = couponOrderDao
					.getCouponDetail(orderMap);

			if (couponDetail == null) {
				log.error("未查询到用户：" + orderMap.get("uid") + "预售抵用券："
						+ orderMap.get("cdid"));
				throw new FailureException(1, "未查询到用户：" + orderMap.get("uid")
						+ "预售抵用券：" + orderMap.get("cdid"));
			}

			if ((Integer) couponDetail.get("status") != 1) {
				log.error("预售抵用券状态异常，抵用券id" + couponDetail.get("cdid"));
				throw new FailureException(1, "预售抵用券状态异常，抵用券id"
						+ couponDetail.get("cdid"));
			}
			// 更新预售抵用券为已使用状态
			Integer result2 = couponOrderDao.updateCouponDetailStatus(
					couponDetail.get("cdid").toString(), 2, formatDate());
			if (result2 != 1) {
				log.error("更新预售抵用券状态失败cdid:" + couponDetail.get("cdid"));
				throw new FailureException(1, "更新预售抵用券状态失败cdid:"
						+ couponDetail.get("cdid"));
			}

		} else {// 未使用预售抵用券，赠送抵用券
			BigDecimal total = new BigDecimal(0.00);
			// 判断是否需要赠送抵用券
			List<Map<String, Object>> couponIssueRef = couponOrderDao.couponIssueRef(couponAnchorRef.get("cid").toString());
			if (couponIssueRef != null && couponIssueRef.size()>0) {

				for (Map<String, Object> map : couponIssueRef) {
					/**
					 * 计算 赠送优惠券总面额
					 */
					Map<String, Object> couponMsg = couponOrderDao
							.getCouponMsg(map.get("gift_cid").toString());// 获取优惠券信息
					
					if (couponMsg == null) {
						log.error("优惠券不存在");
						throw new FailureException(1,"优惠券不存在");
					}
					
					BigDecimal denomination = new BigDecimal(couponMsg.get(
							"denomination").toString());// 优惠券面额
					BigDecimal num = new BigDecimal(map.get("num")
							.toString());// 优惠券数量
					total = total.add(denomination.multiply(num));// 赠送优惠券总面额

					/**
					 * 赠送抵用券
					 */
					// 批量插入抵用券
					List<Map<String, Object>> list = getParam(orderMap, couponMsg,null,
							num.intValue(),1);
					couponOrderDao.insertCoupon(list);
				}
				
				paraMap.put("retrun_coupon_amount", total.toString());
			}
		}
		BigDecimal integral = new BigDecimal(orderMap.get("total_amount")
				.toString()).subtract(
				new BigDecimal(orderMap.get("cuser").toString())).subtract(
				new BigDecimal(orderMap.get("integral").toString()));// 计算积分
																		// ：订单总金额-预售券抵用金额-积分
		if (integral.compareTo(ZERO) == 1) {
			paraMap.put("return_integral", integral.toString());
		}

		/**
		 * 2.更新订单状态
		 */
//		paraMap.put("version",orderMap.get("version").toString());
		
		Integer result1 = couponOrderDao.updateOrderStatus(paraMap);
		if (result1 != 1) {
			log.error("更新订单状态失败orderNo:" + paraMap.get("orderNo"));
			throw new FailureException(1, "更新订单状态失败orderNo:"
					+ paraMap.get("orderNo"));
		}

		/**
		 * 3.发放粉丝券
		 */

		Map<String, Object> couponMsg = couponOrderDao
				.getCouponMsg(couponAnchorRef.get("cid").toString());
		List<Map<String, Object>> list = getParam(orderMap, couponMsg,couponAnchorRef,
				Integer.valueOf(orderMap.get("cid_num").toString()),2);
		couponOrderDao.insertCoupon(list);
		/**
		 * 5.赠送积分
		 */

//		if (1 == integral.compareTo(ZERO)) {// 判断赠送积分是否为零
//			XmnWalletBean xmnWalletBean = new XmnWalletBean();
//			xmnWalletBean.setuId(orderMap.get("uid").toString());
//			xmnWalletBean.setUserType("1");
//			xmnWalletBean.setOption("0");
//			xmnWalletBean.setrType("4");
//			xmnWalletBean.setReturnMode("0");
//			xmnWalletBean.setIntegral(integral.toString());
//			xmnWalletBean.setOrderId(orderMap.get("order_sn").toString());
//			orderServiceImpl.insertXmnWalletRedis(xmnWalletBean);
//			log.info("用户："+orderMap.get("uid")+"赠送积分加入消息队列");
//			/*int flag = commonServiceImpl.modifyWalletBalance(wMap);
//			if (flag == 1) {
//				log.error("调用支付服务赠送积分失败，订单号："
//						+ orderMap.get("order_sn").toString());
//				throw new FailureException(1, "调用支付服务赠送积分失败，订单号："
//						+ orderMap.get("order_sn").toString());
//			}*/
//		}
	}

	/**
	 * 更新订单状态为失败(1更新订单状态 2.解除被锁定的预售抵用券)
	 * 
	 * @param paraMap
	 * @param orderMap
	 * @throws FailureException
	 */
	private void failUpdate(Map<String, String> paraMap,
			Map<String, Object> orderMap) throws FailureException {
		// 1更新订单状态
//		paraMap.put("version",orderMap.get("version").toString());
		Integer result = couponOrderDao.updateOrderStatus(paraMap);
		if (result != 1) {
			log.error("更新订单状态为失败");
			throw new FailureException(1, "更新订单状态为失败");
		}
		// 2.解除被锁定的预售抵用券
		if (orderMap.get("cdid") != null && orderMap.get("cdid") != "") {
			Integer result2 = couponOrderDao.updateCouponDetailStatus(orderMap
					.get("cdid").toString(), 0, formatDate());
			if (result2 != 1) {
				log.error("解除被锁定的预售抵用券失败");
				throw new FailureException(1, "解除被锁定的预售抵用券失败");
			}
		}
		//更细粉丝券库存
		Integer updateStock = couponOrderDao.updateStock(orderMap);
		if (updateStock != 1) {
			log.error("更新粉丝券库存失败");
			throw new FailureException(1, "更新粉丝券库存失败");
		}
	}

	/**
	 * 组装参数
	 * 
	 * @param orderMap
	 * @param couponMsg
	 * @param num
	 * @param type 1 赠送抵用券 2 发放粉丝券
	 * @return
	 */
	private List<Map<String, Object>> getParam(Map<String, Object> orderMap,
			Map<String, Object> couponMsg,Map<String,Object> couponAnchorRef, Integer num,Integer type) {
		List<Map<String, Object>> list = new ArrayList<>();
		if(type == 1){
			for (int i = 0; i < num; i++) {
				Map<String, Object> coupMap = new HashMap<>();
				coupMap.put("cid", couponMsg.get("cid"));
				//coupMap.put("serial", UUID.randomUUID().toString());
				coupMap.put("denomination", couponMsg.get("denomination"));
				//coupMap.put("cid", couponMsg.get("cid"));
				coupMap.put("sellerid",0);
				if((int)couponMsg.get("invalid_today")==0){//当天可用
					coupMap.put("start_date",formatDate());
					coupMap.put("end_date",getDate((int)couponMsg.get("day_num")));
				}else {//当天不可用
					coupMap.put("start_date",getDate(1));
					coupMap.put("end_date",getDate((int)couponMsg.get("day_num")+1));
				}
				coupMap.put("ctype",3);

				coupMap.put("anchor_cid",orderMap.get("anchor_cid"));
				coupMap.put("get_status", 1);
				coupMap.put("get_time", formatDate());
				coupMap.put("uid", orderMap.get("uid"));
				coupMap.put("phone", orderMap.get("phoneid"));
				coupMap.put("user_status", 0);
				coupMap.put("date_issue", formatDate());
				coupMap.put("order_no", orderMap.get("order_sn"));
				coupMap.put("seats",null);
				coupMap.put("serial", null);
				list.add(coupMap);
			}
		}else{
//			for (int i = 0; i < num; i++) {
				Map<String, Object> coupMap = new HashMap<>();
				coupMap.put("cid", couponMsg.get("cid"));
				
				long sellerid=(Long)orderMap.get("sellerid");
				String serialNo = sellerActivityServiceImpl.getRedisSerial((int)sellerid);
				if(StringUtils.isBlank(serialNo)){
					throw new RuntimeException("获取序列号出错");
				}
				serialNo = "4"+serialNo;
				coupMap.put("serial", serialNo);
				//coupMap.put("serial", UUID.randomUUID().toString());
				coupMap.put("denomination", (new BigDecimal(couponMsg.get("denomination").toString())).multiply(new BigDecimal(num+"")));
				{//发放粉丝券
					//coupMap.put("cid", couponAnchorRef.get("anchor_cid"));
					coupMap.put("start_date", couponMsg.get("start_date"));
					coupMap.put("end_date", couponMsg.get("end_date"));
					coupMap.put("sellerid",orderMap.get("sellerid"));
					coupMap.put("ctype",2);
					coupMap.put("seats",num);
				}
				coupMap.put("anchor_cid",orderMap.get("anchor_cid"));
				coupMap.put("get_status", 1);
				coupMap.put("get_time", formatDate());
				coupMap.put("uid", orderMap.get("uid"));
				coupMap.put("phone", orderMap.get("phoneid"));
				coupMap.put("user_status", 0);
				coupMap.put("date_issue", formatDate());
				coupMap.put("order_no", orderMap.get("order_sn"));
				list.add(coupMap);
//			}
		}

		return list;
	}

	private String formatDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 获取间隔天数
	 * @param num
	 */
	private String getDate(int num){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,num);
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(cal.getTime()); 
	}

}
