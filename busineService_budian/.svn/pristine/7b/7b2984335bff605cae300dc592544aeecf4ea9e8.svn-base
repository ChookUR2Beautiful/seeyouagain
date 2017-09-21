package com.xmniao.service.sellerOrder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.coupon.RedPacketRecordDao;
import com.xmniao.dao.sellerOrder.ActivityKillOrderDao;
import com.xmniao.dao.sellerOrder.SellerOrderDao;
import com.xmniao.domain.coupon.RedPacketRecord;
import com.xmniao.domain.order.XmnWalletBean;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.order.OrderServiceImpl;
import com.xmniao.service.order.SellerActivityServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.sellerOrder.SellerOrderService;

@Service("sellerOrderServiceImpl")
public class SellerOrderServiceImpl implements SellerOrderService.Iface {

	// 初始化日志类
	private final Logger log = Logger.getLogger(SellerOrderServiceImpl.class);

	@Autowired
	private SellerOrderDao sellerOrderDao;

	@Autowired
	private RedPacketRecordDao redPacketRecordDao;

	@Autowired
	private ActivityKillOrderDao activityKillOrderDao;

	@Autowired
	private SellerActivityServiceImpl sellerActivityService;

	@Autowired
	private CommonServiceImpl commonServiceImpl;

	@Autowired
	private OrderServiceImpl orderService;
	/**
	 * 更新红包支付订单
	 */
	@Override
	public Map<String, String> modifyRedPacketOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新红包支付订单modifyRedPacketOrder：" + paraMap);
		Map<String, String> resultMap = new HashMap<>();
		try {
			// 验证参数
			if (!verifyParam(paraMap)) {
				log.error("更新红包支付订单,验证参数失败");
				throw new FailureException(1, "传入参数有误");
			}

			String orderNo = paraMap.get("order_no");// 订单id
			resultMap.put("order_no", orderNo);
			// 获取订单信息
			Map<String, Object> redPacket = sellerOrderDao
					.getRedPacket(orderNo);
			if (redPacket == null) {
				log.error("未查询到订单：" + orderNo);
				throw new FailureException(1, "未查询到该订单");
			}

			Long version = (Long) redPacket.get("version");// 版本号
			// 支付状态 1：未支付 2：已支付 3:取消支付 4:支付失败
			int status = Integer.valueOf(paraMap.get("status"));
			int orderStatus = (int) redPacket.get("status");// 订单支付状态
			if (2 == orderStatus) {
				log.info("订单" + orderNo + "已完成支付，请勿重复提交");
				return resultMap;
			} else if (status != 1 && status != 2 && status != 3 && status != 4) {
				log.error("订单状态或支付状态有误" + status);
				throw new FailureException(1, "订单状态或支付状态有误");
			}

			// 验证订单参数与数据库订单信息书否匹配
			if (paraMap.get("sellerid").equals(redPacket.get("sellerid") + "")
					&& paraMap.get("pay_type").equals(
							redPacket.get("payType") + "")
					&& paraMap.get("total_amount").equals(
							redPacket.get("totalAmount").toString())
					&& paraMap.get("balance").equals(
							redPacket.get("balance").toString())
					&& paraMap.get("commision").equals(
							redPacket.get("commision").toString())
					&& paraMap.get("zbalance").equals(
							redPacket.get("zbalance").toString())
					&& paraMap.get("integral").equals(
							redPacket.get("integral").toString())
					&& paraMap.get("amount").equals(
							redPacket.get("amount").toString())
					&& paraMap.get("profit").equals(
							redPacket.get("profit").toString())) {
				// 更新订单状态和支付状态
				paraMap.put("version", version.toString());
				Integer result = sellerOrderDao.updateRedPacketState(paraMap);
				if (result != 1) {
					log.error("更新红包支付订单失败");
					throw new FailureException(1, "更新红包支付订单失败");
				}
				log.info("更新红包支付订单成功");
				return resultMap;
			} else {
				log.error("传入订单参数与后台订单参数不匹配");
				throw new FailureException(1, "传入订单参数与后台订单参数不匹配");
			}
		} catch (Exception e) {
			log.error("更新红包支付订单失败", e);
			throw new FailureException(1, "更新红包支付订单失败");
		}
	}

	/**
	 * 更新领取红包记录状态
	 */
	@Override
	public Map<String, String> modifyRedPacketRecord(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新领取红包记录状态modifyRedPacketRecord:" + paraMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", paraMap.get("id"));
		if (StringUtils.isBlank(paraMap.get("id"))
				|| StringUtils.isBlank(paraMap.get("status"))) {
			log.error("传入参数有误:" + paraMap);
			throw new FailureException(1, "传入参数有误");
		}
		try {
			long id = Long.parseLong(paraMap.get("id"));
			int status = Integer.parseInt(paraMap.get("status"));
			RedPacketRecord record = new RedPacketRecord();
			record.setId(id);
			RedPacketRecord redPacketRecord = redPacketRecordDao
					.getRedPacketRecord(record);
			if (redPacketRecord == null) {
				log.error("找不到对应红包【" + id + "】领取记录");
				throw new FailureException(2, "找不到对应记录");
			}
			if (redPacketRecord.getStatus() == status) {
				log.info("对应红包【" + id + "】领取记录已经到账");
				return resultMap;
			}
			record.setStatus(status);
			redPacketRecordDao.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			log.error("更新红包到帐状态异常", e);
			throw new FailureException(3, "更新红包到账状态异常");
		}
		return resultMap;
	}

	/**
	 * 验证传入参数
	 * 
	 * @param paraMap
	 */
	private boolean verifyParam(Map<String, String> paraMap) {
		if (StringUtils.isBlank(paraMap.get("order_no"))
				|| StringUtils.isBlank(paraMap.get("sellerid"))
				|| StringUtils.isBlank(paraMap.get("status"))
				|| StringUtils.isBlank(paraMap.get("pay_type"))
				|| StringUtils.isBlank(paraMap.get("pay_id"))
				|| StringUtils.isBlank(paraMap.get("total_amount"))
				|| StringUtils.isBlank(paraMap.get("balance"))
				|| StringUtils.isBlank(paraMap.get("commision"))
				|| StringUtils.isBlank(paraMap.get("zbalance"))
				|| StringUtils.isBlank(paraMap.get("integral"))
				|| StringUtils.isBlank(paraMap.get("profit"))
				|| StringUtils.isBlank(paraMap.get("amount"))
				|| "null".equals(paraMap.get("order_no"))
				|| "null".equals(paraMap.get("sellerid"))
				|| "null".equals(paraMap.get("pay_type"))
				|| "null".equals(paraMap.get("pay_id"))
				|| "null".equals(paraMap.get("total_amount"))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 更新秒杀订单
	 */
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class,
			RuntimeException.class })
	public Map<String, String> updateKillOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新秒杀订单updateKillOrder:" + paraMap);
		Map<String, String> resultMap = new HashMap<>();
		try {
			// 验证参数
			if (StringUtils.isBlank(paraMap.get("uid"))
					|| StringUtils.isBlank(paraMap.get("amount"))
					|| StringUtils.isBlank(paraMap.get("number"))
					|| StringUtils.isBlank(paraMap.get("status"))) {
				log.error("传入参数部分为空");
				throw new FailureException(1, "传入参数部分为空");
			}

			// 查询秒杀记录
			Map<String, Object> activityRecord = activityKillOrderDao
					.getActivityRecord(paraMap.get("number"));
			if (activityRecord == null) {
				log.error("未查询到秒杀记录：" + paraMap.get("number"));
				throw new FailureException(1, "未查询到秒杀记录");
			}
			// 查询活动信息
			/*
			 * Map<String, Object> activity = activityKillOrderDao
			 * .getActivity(activityRecord.get("activity_id") + ""); if
			 * (activity == null) { log.error("为查询到活动id为：" +
			 * activityRecord.get("activity_id") + "的秒杀活动"); throw new
			 * FailureException(1, "为查询到活动id为：" +
			 * activityRecord.get("activity_id") + "的秒杀活动"); }
			 */

			if ((int) activityRecord.get("pay_status") == 1) {
				log.info("订单已支付成功，请勿重复提交");
				return resultMap;
			}

			// 匹配传入参数与数据库参数
			if (paraMap.get("uid").equals(activityRecord.get("uid").toString())
					&& paraMap.get("amount").equals(
							activityRecord.get("pay_amount").toString())) {

				paraMap.put("version", activityRecord.get("version").toString());// 版本号，用于乐观锁控制

				// 发放秒杀奖品
				Object serialNo = sendCoupon(activityRecord);

				// 更新订单状态
				paraMap.put("payTime", formatDate());// 支付时间
				paraMap.put("codeid",
						serialNo == null ? null : serialNo.toString());// 赠品券序列码
				BigDecimal ledgeramount = (BigDecimal) activityRecord.get("pay_amount");
				BigDecimal selleramount = this.getKillOrderSellerLedgerAmount(
						ledgeramount, (double) activityRecord.get("baseagio"));
				
				paraMap.put("sellerAmount", selleramount.toString());
				paraMap.put("isaccount", "0");
				Integer result = activityKillOrderDao
						.updateActivityRecordStatus(paraMap);
				if (result != 1) {
					log.error("更新秒杀订单失败");
					throw new FailureException(1, "更新秒杀订单失败");
				}
				
				try{
					if(paraMap.get("status").equals("1")){
						//短信下发
		                orderService.sendSmsForSeller((int)activityRecord.get("sellerid"),paraMap.get("number"),selleramount.toString()); 
					
		                // 调用支付服务，给商户加钱
		                activityRecord.put("sellerAmount", selleramount.toString());
						addMoney(activityRecord);
					}
				}catch(Exception e){
					log.error("处理秒杀订单异常",e);
				}

			} else {
				log.error("传入参数与数据库参数不匹配");
				throw new FailureException(1, "传入参数与数据库参数不匹配");
			}
			log.info("更新秒杀订单状态成功");
			return resultMap;
		} catch (Exception e) {
			log.error("更新秒杀订单失败", e);
			throw new FailureException(1, "更新秒杀订单失败");
		}
	}

	/**
	 * 发放秒杀奖品
	 * 
	 * @param list
	 * @param activityRecord
	 * @throws FailureException
	 */
	private Object sendCoupon(Map<String, Object> activityRecord)
			throws FailureException {
		Map<String, Object> packageParam = null;
		Map<String, Object> activityRelation = activityKillOrderDao
				.getActivityRelaction(activityRecord.get("cid").toString());
		if (activityRelation == null) {
			log.error("为查询到奖品关联关系:" + activityRecord.get("cid"));
			throw new FailureException(1, "为查询到奖品信息");
		}
		Map<String, Object> sellerCoupon = activityKillOrderDao
				.getSellerCoupon(activityRelation.get("award_id").toString());
		if (sellerCoupon != null) {

			packageParam = packageParam(sellerCoupon, activityRecord);

			activityKillOrderDao.insertUserCoupon(packageParam);
		} else {
			log.error("为查询到奖品信息cid:" + activityRecord.get("cid"));
			throw new FailureException(1, "为查询到奖品信息");
		}

		return packageParam.get("serial_no");
			
	}

	/**
	 * 组装参数
	 * 
	 * @param sellerCoupon
	 * @return
	 */
	private Map<String, Object> packageParam(Map<String, Object> sellerCoupon,
			Map<String, Object> couponOrder) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("cid", sellerCoupon.get("cid"));
		if (sellerCoupon.get("coupon_type") != null
				&& (Integer) sellerCoupon.get("coupon_type") == 4) {
			String serialNo = sellerActivityService
					.getRedisSerial((int) sellerCoupon.get("sellerid"));
			if (StringUtils.isBlank(serialNo)) {
				throw new RuntimeException("获取序列号出错");
			}
			paraMap.put("serial_no", ("3" + serialNo));
		} else {
			paraMap.put("serial_no", null);
		}
		paraMap.put("denomination", sellerCoupon.get("denomination"));
		paraMap.put("get_way", 5);
		paraMap.put("get_time", formatDate());
		paraMap.put("uid", couponOrder.get("uid"));
		paraMap.put("sellerid", sellerCoupon.get("sellerid"));
		paraMap.put("use_status", 0);
		paraMap.put("start_date", sellerCoupon.get("start_date"));
		paraMap.put("end_date", sellerCoupon.get("end_date"));
		paraMap.put("bid", couponOrder.get("number"));
		paraMap.put("phone", couponOrder.get("phone"));
		paraMap.put("activity_id", couponOrder.get("id"));
		paraMap.put("activity_type", 3);
		return paraMap;
	}

	private String formatDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 给商家加钱
	 * 
	 * @param activity
	 * @param activityRecord
	 * @throws FailureException
	 *             ,Exception
	 */
	private void addMoney(Map<String, Object> activityRecord)
			throws FailureException, Exception {

//		Map<String, String> paraMap = new HashMap<>();
//		paraMap.put("uId", activityRecord.get("sellerid") + "");
//		paraMap.put("userType", "2");
//		paraMap.put("sellerAmount",activityRecord.get("sellerAmount").toString() + "");
//		paraMap.put("orderId", activityRecord.get("number") + "_2");
//		paraMap.put("remark", "秒杀活动");
//		paraMap.put("rType", "22");
//		int result = commonServiceImpl.modifyWalletBalance(paraMap);
//		if (result != 0) {
//			log.info("调用支付服务，给商家加钱失败");
//			throw new FailureException(1, "调用支付服务，给商家加钱失败");
//		}


		XmnWalletBean xmnWallet = new XmnWalletBean();
		xmnWallet.setuId(activityRecord.get("sellerid")+"");
		xmnWallet.setUserType("2");
		xmnWallet.setSellerAmount(activityRecord.get("sellerAmount").toString());
		xmnWallet.setrType("22");
		xmnWallet.setOrderId(activityRecord.get("number") + "_2");
		xmnWallet.setRemark("秒杀活动");
		xmnWallet.setOption("0");
		xmnWallet.setReturnMode("2");
		orderService.insertXmnWalletRedis(xmnWallet);
	}

	/**
	 * 更新面对面支付订单
	 */
	@Override
	@Transactional
	public Map<String, String> updateMicroOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("updateMicroOrder:" + paraMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		String orderNumber = paraMap.get("orderNumber");
		resultMap.put("orderNumber", paraMap.get("orderNumber"));
		try {
			if (StringUtils.isBlank(orderNumber)
					|| StringUtils.isBlank(paraMap.get("payType"))
					|| StringUtils.isBlank(paraMap.get("authCode"))
					|| StringUtils.isBlank(paraMap.get("totalAmount"))
					|| StringUtils.isBlank(paraMap.get("payStatus"))
					|| StringUtils.isBlank(paraMap.get("sellerid"))) {
				throw new Exception("传入参数不齐");
			}
			Map<String, Object> orderMap = sellerOrderDao
					.getMicroBill(orderNumber);
			if (orderMap == null) {
				throw new Exception("找不到该订单");
			}
			log.info("orderMap:" + orderMap);
			if (orderMap.get("payStatus") != null
					&& orderMap.get("payStatus").toString()
							.equals(paraMap.get("payStatus"))) {
				return resultMap;
			}
			if (orderMap.get("payStatus") != null
					&& (Integer) orderMap.get("payStatus") == 1) {
				throw new Exception("已支付订单不能被修改为其他状态");
			}
			if (!orderMap.get("totalAmount").toString()
					.equals(paraMap.get("totalAmount"))) {
				throw new Exception("订单金额错误");
			}
			// if(!orderMap.get("authCode").toString().equals(paraMap.get("authCode"))){
			// throw new Exception("验证错误");
			// }
			
			BigDecimal ledgeramount = (BigDecimal) orderMap.get("totalAmount");
			double ledgerratio = 1;
			BigDecimal selleramount = getMicroOrderSellerLedgerAmount(
					ledgeramount, ledgerratio);
			
			Map<String, Object> uMap = new HashMap<String, Object>();
			uMap.put("orderNumber", orderNumber);
			uMap.put("payStatus", paraMap.get("payStatus"));
			uMap.put("authCode", paraMap.get("authCode"));
			uMap.put("sellerAmount", selleramount);
			uMap.put("isaccount", 0);
			sellerOrderDao.updateMicroBill(uMap);


            
//			Map<String, String> walletMap = new HashMap<String, String>();
//			walletMap.put("uId", orderMap.get("sellerid") + "");
//			walletMap.put("userType", "2");
//			walletMap.put("sellerAmount", selleramount + "");
//			walletMap.put("orderId", orderNumber + "_2");
//			walletMap.put("remark", "面对面扫码支付");
//			walletMap.put("rType", "22");
//			if (paraMap.get("payStatus").equals("1")) {
//				commonServiceImpl.modifyWalletBalance(walletMap);
//			}
			try{
            if (paraMap.get("payStatus").equals("1")) {
    			//短信下发
                orderService.sendSmsForSeller((int)orderMap.get("sellerid"),paraMap.get("orderNumber"),selleramount.toString()); 
				XmnWalletBean xmnWallet = new XmnWalletBean();
				xmnWallet.setuId(orderMap.get("sellerid")+"");
				xmnWallet.setUserType("2");
				xmnWallet.setSellerAmount(selleramount + "");
				xmnWallet.setrType("22");
				xmnWallet.setOrderId(orderNumber + "_2");
				xmnWallet.setRemark("面对面扫码支付");
				xmnWallet.setOption("0");
				xmnWallet.setReturnMode("3");
				orderService.insertXmnWalletRedis(xmnWallet);
            }
			}catch(Exception sube){
				log.error("",sube);
			}
		} catch (Exception e) {
			log.error("更新异常", e);
			throw new FailureException(101, e.getMessage());
		}
		return resultMap;
	}

	public ResponseData getKillOrderLedgerInfo(String bid) {
		ResponseData responseData = new ResponseData();
		try {
			Map<String, Object> billBean = activityKillOrderDao
					.getActivityRecord(bid);
			if (billBean == null) {
				responseData.setMsg("订单不存在");
				responseData.setState(1);
				return responseData;
			}
			if (billBean.get("pay_status") == null
					|| (Integer) billBean.get("pay_status") != 1) {
				responseData.setMsg("订单尚未支付");
				responseData.setState(1);
				return responseData;
			}
			if (billBean.get("baseagio") == null) {
				responseData.setMsg("订单未写入分账比例");
				responseData.setState(1);
				return responseData;
			}

			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("bid", String.valueOf(billBean.get("number")));
			resultMap.put("paytype", String.valueOf(billBean.get("pay_type")));
			resultMap.put("money",
					String.valueOf(billBean.get("sec_kill_amount")));
			resultMap.put("payamount",
					String.valueOf(billBean.get("pay_amount")));
			resultMap.put("wamount", String.valueOf("0"));
			resultMap
					.put("samount", String.valueOf(billBean.get("pay_amount")));
			resultMap.put("preferential", String.valueOf(((BigDecimal) billBean
					.get("sec_kill_amount")).subtract((BigDecimal) billBean
					.get("pay_amount"))));
			resultMap.put("zinteger", String.valueOf("0"));
			resultMap.put("liveCoin", String.valueOf(0));
			resultMap.put("liveCoinRatio", String.valueOf(0));
			resultMap.put("codeid", String.valueOf(billBean.get("codeid")));
			
			BigDecimal ledgeramount = (BigDecimal) billBean.get("pay_amount");
			BigDecimal selleramount = this.getKillOrderSellerLedgerAmount(
					ledgeramount, (double) billBean.get("baseagio"));
			resultMap.put("ledgerratio",
					String.valueOf(billBean.get("baseagio")));
			resultMap.put("ledgeramount", String.valueOf(ledgeramount));
			resultMap.put("selleramount", String.valueOf(selleramount));
			resultMap.put("expectedselleramount", resultMap.get("selleramount"));
			resultMap.put("sellercommision", String.valueOf(0));
			resultMap.put("jointamount", String.valueOf(0));
			resultMap.put("xmeramount", String.valueOf(0));
			resultMap.put("txmeramount", String.valueOf(0));
			resultMap.put("pxmeramount", String.valueOf(0));
			resultMap.put("platformamount",
					String.valueOf(ledgeramount.subtract(selleramount)));
			responseData.setState(0);
			responseData.setMsg("查询成功");
			responseData.setResultMap(resultMap);

		} catch (Exception e) {
			log.error("查询异常", e);
			responseData.setState(2);
			responseData.setMsg("查询异常");
		}
		return responseData;

	}

	public ResponseData getMicroOrderLedgerInfo(String bid) {
		ResponseData responseData = new ResponseData();
		try {
			Map<String, Object> billBean = sellerOrderDao.getMicroBill(bid);
			if (billBean == null) {
				responseData.setMsg("订单不存在");
				responseData.setState(1);
				return responseData;
			}
			if (billBean.get("payStatus") == null
					|| (Integer) billBean.get("payStatus") != 1) {
				responseData.setMsg("订单尚未支付");
				responseData.setState(1);
				return responseData;
			}

			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("bid", String.valueOf(bid));
			resultMap.put("paytype", String.valueOf(billBean.get("payType")));
			resultMap.put("money", String.valueOf(billBean.get("totalAmount")));
			resultMap.put("payamount",
					String.valueOf(billBean.get("totalAmount")));
			resultMap.put("wamount", String.valueOf(0));
			resultMap.put("samount",
					String.valueOf(billBean.get("totalAmount")));
			resultMap.put("preferential", String.valueOf(0));
			resultMap.put("zinteger", String.valueOf(0));
			resultMap.put("liveCoin", String.valueOf(0));
			resultMap.put("liveCoinRatio", String.valueOf(0));
			resultMap.put("codeid", String.valueOf(""));
			
			double ledgerRatio = 1;
			BigDecimal ledgeramount = (BigDecimal) billBean.get("totalAmount");
			BigDecimal selleramount = ledgeramount.multiply(
					new BigDecimal(Double.toString(ledgerRatio))).setScale(2,
					RoundingMode.FLOOR);
			if (selleramount.compareTo(BigDecimal.ZERO) <= 0
					&& ledgeramount.compareTo(BigDecimal.ZERO) > 0) {
				selleramount = new BigDecimal("0.01");
			}
			resultMap.put("ledgerratio", String.valueOf(ledgerRatio));
			resultMap.put("ledgeramount", String.valueOf(ledgeramount));
			resultMap.put("selleramount", String.valueOf(selleramount));
			resultMap.put("expectedselleramount", resultMap.get("selleramount"));
			resultMap.put("sellercommision", String.valueOf(0));
			resultMap.put("jointamount", String.valueOf(0));
			resultMap.put("xmeramount", String.valueOf(0));
			resultMap.put("txmeramount", String.valueOf(0));
			resultMap.put("pxmeramount", String.valueOf(0));
			resultMap.put("platformamount",
					String.valueOf(ledgeramount.subtract(selleramount)));
			responseData.setState(0);
			responseData.setMsg("查询成功");
			responseData.setResultMap(resultMap);

		} catch (Exception e) {
			log.error("查询异常", e);
			responseData.setState(2);
			responseData.setMsg("查询异常");
		}
		return responseData;

	}

	public BigDecimal getKillOrderSellerLedgerAmount(BigDecimal ledgeramount,
			double ledgerRatio) {
		BigDecimal selleramount = ledgeramount.multiply(
				new BigDecimal(Double.toString(ledgerRatio))).setScale(2,
				RoundingMode.FLOOR);
		if (selleramount.compareTo(BigDecimal.ZERO) <= 0
				&& ledgeramount.compareTo(BigDecimal.ZERO) > 0) {
			selleramount = new BigDecimal("0.01");
		}
		return selleramount;
	}

	public BigDecimal getMicroOrderSellerLedgerAmount(BigDecimal ledgeramount,
			double ledgerRatio) {
		BigDecimal selleramount = ledgeramount.multiply(
				new BigDecimal(Double.toString(ledgerRatio))).setScale(2,
				RoundingMode.FLOOR);
		if (selleramount.compareTo(BigDecimal.ZERO) <= 0
				&& ledgeramount.compareTo(BigDecimal.ZERO) > 0) {
			selleramount = new BigDecimal("0.01");
		}
		return selleramount;
	}

	/**
	 * 更新网红订单
	 */
	@Override
	@Transactional(rollbackFor={FailureException.class,Exception.class,RuntimeException.class})
	public Map<String, String> updateCelebrityOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新网红订单updateCelebrityOrder：" + paraMap);
		Map<String,String> resultMap = new HashMap<>();
		try {
			/**
			 * 验证参数
			 */
			if (StringUtils.isBlank(paraMap.get("orderNo"))
					|| StringUtils.isBlank(paraMap.get("sellerid"))
					|| StringUtils.isBlank(paraMap.get("payType"))
					|| StringUtils.isBlank(paraMap.get("price"))
					|| StringUtils.isBlank(paraMap.get("payStatus"))){
				log.error("传入参数有误"+paraMap);
				throw new FailureException(1,"传入参数部分为空");
			}
			
			if("1".equals(paraMap.get("payStatus"))){
				log.error("订单状态不能为1"+paraMap);
				throw new FailureException(1,"订单状态不能为1");
			}
			
			resultMap.put("orderNo",paraMap.get("orderNo"));
			
			Map<String, Object> celebrityOrder = sellerOrderDao.getCelebrityOrder(paraMap);
			
			if(celebrityOrder==null){
				log.error("未查询到订单号为："+paraMap.get("orderNo")+"的订单");
				throw new FailureException(1,"为查询到订单号为："+paraMap.get("orderNo")+"的订单");
			}
			
			//匹配数据库参数
			if(!paraMap.get("sellerid").equals(celebrityOrder.get("seller_id").toString()) ||
					!paraMap.get("price").equals(celebrityOrder.get("price").toString())){
				log.error("匹配数据库参数失败，传参有误"+paraMap);
				throw new FailureException(1,"匹配数据库参数失败");
			}
			
			if(paraMap.get("payStatus").equals(celebrityOrder.get("pay_status").toString())){
				log.info("订单号为："+paraMap.get("orderNo")+"的订单已修改状态成功，请勿重复提交");
				return resultMap;
			}
			
			paraMap.put("payTime",formatDate());
			paraMap.put("lock",celebrityOrder.get("version_lock").toString());
			//更新订单状态
			Integer result = sellerOrderDao.updateCelebrityOrder(paraMap);
			if (result !=1) {
				log.error("更新网红订单失败");
				throw new FailureException(1,"更新网红订单失败");
			}
			
			log.info("更新网红订单成功");
			return resultMap;
		} catch (Exception e) {
			log.error("更新网红订单失败",e);
			throw new FailureException(1,"更新网红订单失败");
		}
	}

	/**
	 * 更新金额到账状态
	 */
	@Override
	public Map<String, String> modifyReceiptStatus(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("modifyReceiptStatus:"+paraMap);
		if(StringUtils.isNotBlank(paraMap.get("type"))
			&& StringUtils.isNotBlank(paraMap.get("id"))
			&& StringUtils.isNotBlank(paraMap.get("status"))){
			if(paraMap.get("type").equals("1")){
				return this.modifyRedPacketRecord(paraMap);
			}else if(paraMap.get("type").equals("2")){
				return this.modifyKillOrderRecord(paraMap);
			}else if(paraMap.get("type").equals("3")){
				return this.modifyMicroRecord(paraMap);
			}
		}			
		throw new FailureException(1, "传入参数错误！");
	}
	
	/**
	 * 更新商家面对面订单分账记录状态
	 */
	public Map<String, String> modifyMicroRecord(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新面对面订单分账状态modifyMicroRecord:" + paraMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", paraMap.get("id"));
		if (StringUtils.isBlank(paraMap.get("id"))
				|| StringUtils.isBlank(paraMap.get("status"))) {
			log.error("传入参数有误:" + paraMap);
			throw new FailureException(1, "传入参数有误");
		}
		try {
			String orderNumber = paraMap.get("id");
			int status = Integer.parseInt(paraMap.get("status"));

			Map<String,Object> microMap = sellerOrderDao
					.getMicroBill(orderNumber);
			if (microMap == null) {
				log.error("找不到对应面对面订单【" + orderNumber + "】记录");
				throw new FailureException(2, "找不到对应记录");
			}
			if (microMap.get("isaccount")!=null && microMap.get("isaccount").toString().equals("1")) {
				log.info("对应面对面订单【" + orderNumber + "】分账已经到账");
				return resultMap;
			}
			Map<String,Object> uMap = new HashMap<String,Object>();
			uMap.put("orderNumber", orderNumber);
			uMap.put("isaccount", status);
			sellerOrderDao.updateMicroBill(uMap);
		} catch (Exception e) {
			log.error("更新面对面订单分账状态异常", e);
			throw new FailureException(3, "更新面对面订单分账状态异常");
		}
		return resultMap;
	}
	
	/**
	 * 更新秒杀分账记录状态
	 */
	public Map<String, String> modifyKillOrderRecord(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新秒杀订单分账状态modifyKillOrderRecord:" + paraMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", paraMap.get("id"));
		if (StringUtils.isBlank(paraMap.get("id"))
				|| StringUtils.isBlank(paraMap.get("status"))) {
			log.error("传入参数有误:" + paraMap);
			throw new FailureException(1, "传入参数有误");
		}
		try {
			String number = paraMap.get("id");
			int status = Integer.parseInt(paraMap.get("status"));
			
			Map<String,Object> record = activityKillOrderDao.getActivityRecord(number);
			if (record == null) {
				log.error("找不到秒杀订单【" + number + "】记录");
				throw new FailureException(2, "找不到对应记录");
			}
			if (record.get("isaccount")!=null && record.get("isaccount").toString().equals("1")) {
				log.info("对应订单【" + number + "】记录分账已经到账");
				return resultMap;
			}
			Map<String,String> uMap = new HashMap<String,String>();
			uMap.put("number", number);
			uMap.put("isaccount", status+"");
			uMap.put("version", record.get("version").toString());
			activityKillOrderDao.updateActivityRecordStatus(uMap);
		} catch (Exception e) {
			log.error("更新秒杀订单分账到账状态异常", e);
			throw new FailureException(3, "更新秒杀订单分账到账状态异常");
		}
		return resultMap;
	}
}
