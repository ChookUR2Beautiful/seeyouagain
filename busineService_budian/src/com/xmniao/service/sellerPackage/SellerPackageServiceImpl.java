package com.xmniao.service.sellerPackage;

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
import com.xmniao.dao.sellerPackage.SellerPackageDao;
import com.xmniao.service.order.SellerActivityServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.sellerPackage.SellerPackageService;


@Service("SellerPackageServiceImpl")
public class SellerPackageServiceImpl implements SellerPackageService.Iface{
	
	/**
	 * 初始化日志类
	 */
	private Logger log = Logger.getLogger(SellerPackageServiceImpl.class);
	
	@Autowired
	private SellerPackageDao sPackageDao;
	
	@Autowired
	private CouponOrderDao couponOrderDao;
	
	@Autowired
	private SellerActivityServiceImpl sellerActivityServiceImpl;
	/**
	 * 更新套餐订单
	 */
	@Override
	@Transactional(rollbackFor={Exception.class})
	public ResponseData updateSellerPackageOrder(Map<String, String> paraMap)
			throws FailureException, TException {
		log.info("更新套餐订单:updateSellerPackageOrder"+paraMap);
		
		ResponseData responseData = new ResponseData();
		
		//验证订单号
		if(StringUtils.isBlank(paraMap.get("orderNo"))){
			log.error("订单号为空");
			responseData.setState(2);
			responseData.setMsg("订单号为空");
			return responseData;
		}
		
		//获取订单信息
		Map<String, Object> pOrder = sPackageDao.getPackageOrder(paraMap);
		if(pOrder == null){
			log.error("未查询到该订单orderNo:"+paraMap.get("orderNo"));
			responseData.setState(1);
			responseData.setMsg("未查询到该订单");
			return responseData;
		}
		
		//验证订单状态
		if((Integer)pOrder.get("status")!=0){
			log.error("订单已经更新成功，请勿重复提交：订单状态："+pOrder.get("status"));
			responseData.setState(2);
			responseData.setMsg("订单已经更新成功，请勿重复提交");
			return responseData;
		}
		
		//验单
		if(!verifyOrder(paraMap, pOrder)){
			log.error("订单验证失败");
			responseData.setState(1);
			responseData.setMsg("订单验证失败");
			return responseData;
		}
		
		Integer status = Integer.valueOf(paraMap.get("status"));
		
		Map<String, Object> packageIssue = sPackageDao.getPackageIssue(pOrder.get("pid").toString());
		
		pOrder.put("status",status+"");
		pOrder.put("payid",paraMap.get("payid"));
		pOrder.put("payType", paraMap.get("payType"));
		pOrder.put("commision", paraMap.get("commision"));
		pOrder.put("zbalance", paraMap.get("zbalance"));
		pOrder.put("balance", paraMap.get("balance"));
		pOrder.put("sellerCoin", paraMap.get("sellerCoin"));
		pOrder.put("cash", paraMap.get("cash"));
		pOrder.put("beans", paraMap.get("beans"));
		
		if(status == 1){//支付成功
			
			successUpdate(pOrder,packageIssue);
			
			/* 更改套餐状态为售罄 */
			Map<String, Object> paramsState = new HashMap<String, Object>();
			paramsState.put("pid", pOrder.get("pid").toString());  //套餐ID
			paramsState.put("status", 3);  //状态 1.上架 2.下架 3.售罄
			this.updateSellerPackageState(paramsState);
		}else if(status ==2 || status == 3){// 2 支付失败 3 取消支付
			
			failUpdate(pOrder,packageIssue);
			
		}else{
			log.error("传入订单状态有误");
			responseData.setState(2);
			responseData.setMsg("传入订单状态有误");
			return responseData;
		}
		
		log.error("更新套餐订单成功");
		responseData.setState(0);
		responseData.setMsg("更新成功");
		return responseData;
	}
	
	/**
	 * 验单
	 * @param paraMap
	 * @param orderMap
	 * @return
	 */
	private boolean verifyOrder(Map<String,String> paraMap,Map<String,Object> orderMap){
		Boolean bool = true;
		if(!paraMap.get("uid").equals(orderMap.get("uid")+"")){
			log.error("订单用户身份未验证通过");
			bool=false;
		}
		//鸟币支付 订单应付 =（鸟币支付 + 现金支付/基数*商家折扣）
		BigDecimal totalAmount;//传入参数计算得出订单总金额
		BigDecimal baseAmount;//数据库订单金额
		//现金支付总金额
		BigDecimal cashAmount = new BigDecimal(paraMap.get("cash")).add(new BigDecimal(paraMap.get("balance")))
				.add(new BigDecimal(paraMap.get("commision"))).add(new BigDecimal(paraMap.get("zbalance")));//
		
		if(new BigDecimal(paraMap.get("beans")).compareTo(BigDecimal.ZERO)>0 || new BigDecimal(paraMap.get("sellerCoin")).compareTo(BigDecimal.ZERO)>0){//鸟币支付
			totalAmount = cashAmount.add(new BigDecimal(paraMap.get("beans"))).add(new BigDecimal(paraMap.get("sellerCoin")));
					
//			totalAmount = cashAmount.divide(new BigDecimal(paraMap.get("base")),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(paraMap.get("discounts")))//现金支付/基数*商家折扣
//					.add(new BigDecimal(paraMap.get("beans"))).add(new BigDecimal(paraMap.get("sellerCoin"))).setScale(2,BigDecimal.ROUND_HALF_UP);
			baseAmount = new BigDecimal(orderMap.get("total_coin_amount").toString()).subtract( new BigDecimal(orderMap.get("cuser").toString()));
			
			/**
			 * 优惠券分账面额（用于优惠券分账）
			 * ledgerAmount = ((鸟币支付金额/商家折扣)*鸟币人民币兑换比 +现金支付总额)/购买数量
			 */
//			BigDecimal ledgerAmount = (
//					new BigDecimal(paraMap.get("beans")).add(new BigDecimal(paraMap.get("sellerCoin")))
//					).divide(new BigDecimal(paraMap.get("discounts")),4,BigDecimal.ROUND_HALF_UP)
//					.multiply(new BigDecimal(paraMap.get("base")))
//					.add(cashAmount);
//			orderMap.put("ledger_amount",ledgerAmount.divide(new BigDecimal(orderMap.get("nums").toString()),2,BigDecimal.ROUND_HALF_UP).toString());
		}else{
			totalAmount = cashAmount;
//			baseAmount = new BigDecimal(orderMap.get("total_amount").toString()).subtract( new BigDecimal(orderMap.get("cuser").toString()));
					
			String tempTotalAmout = "";
			if (paraMap.get("deduction") == null || "1".equals(paraMap.get("deduction"))){
				tempTotalAmout = orderMap.get("total_amount").toString();  //现金总额
			}else {
				tempTotalAmout = orderMap.get("total_coin_amount").toString();//鸟币总额
			}
			
			baseAmount = new BigDecimal(tempTotalAmout).subtract( new BigDecimal(orderMap.get("cuser").toString()));
//			orderMap.put("ledger_amount",cashAmount.divide(new BigDecimal(orderMap.get("nums").toString()),2,BigDecimal.ROUND_HALF_UP).toString());
		}
		
		if(totalAmount.compareTo(baseAmount)<0){
			bool = false;
		}
		log.info("验单 ：传入订单总金额与数据库匹配："+totalAmount+":"+baseAmount+" :"+bool);
		return bool;
	}
	
	/**
	 * 支付成功更新
	 * 1 更新抵用券状态，并发放新的抵用券
	 * @param pOrder
	 * @throws FailureException 
	 */
	@Transactional(rollbackFor={FailureException.class,Exception.class})
	private void successUpdate(Map<String, Object> orderMap,
			Map<String, Object> packageIssue) throws FailureException {

		/**
		 * 1.更新抵用券状态，并发放新的抵用券
		 */

		Map<String, Object> params = new HashMap<>();
		params.put("bid", orderMap.get("order_no"));
		params.put("uid", orderMap.get("uid"));
		List<Map<String, Object>> couponDetail = couponOrderDao.getCouponDetailByRelation(params);
		if (couponDetail != null && couponDetail.size() > 0) {//使用多张抵用券
//			List<String> ids = new ArrayList<>();
			String ids = "";
			for (Map<String, Object> object : couponDetail) {
				if ((Integer) object.get("status") != 1) {
					log.error("预售抵用券状态异常，抵用券id" + object.get("cdid"));
					throw new FailureException(1, "预售抵用券状态异常，抵用券id"
							+ object.get("cdid"));
				}
//				ids.add( object.get("cdid").toString());
				ids += "," + object.get("cdid").toString();
			}
			if (ids.length() > 1) {
				ids = ids.substring(1);
			}
			// 更新预售抵用券为已使用状态
			Integer result2 = couponOrderDao.updateCouponDetailStatusByRelation(ids.split(","), 2, formatDate());
			if (result2 < 1) {
				log.error("更新预售抵用券状态失败, 订单编号:" + orderMap.get("order_no"));
				throw new FailureException(1, "更新预售抵用券状态失败, 订单编号:"
						+ orderMap.get("order_no"));
			}

		} else {// 未使用预售抵用券，赠送抵用券

			BigDecimal total = new BigDecimal(0.00);

			// 获取套餐关联的抵用券
			List<Map<String, Object>> issueList = sPackageDao.getPackageCoupon(orderMap.get("pid").toString());

			if (issueList != null && issueList.size() > 0) {

				for (Map<String, Object> map : issueList) {
					/**
					 * 计算 赠送抵用券总面额
					 */
					Map<String, Object> couponMsg = couponOrderDao.getCouponMsg(map.get("gift_cid").toString());// 获取抵用券信息

					if (couponMsg == null) {
						log.error("抵用券不存在");
						throw new FailureException(1, "抵用券不存在");
					}

					BigDecimal denomination = new BigDecimal(couponMsg.get(
							"denomination").toString());// 抵用券面额
					BigDecimal num = new BigDecimal(map.get("num").toString());// 抵用券数量
					total = total.add(denomination.multiply(num));// 赠送抵用券总面额

					/**
					 * 赠送抵用券
					 */
					// 批量插入抵用券
					List<Map<String, Object>> list = getParam(orderMap,
							couponMsg, num.intValue(), 1);
					couponOrderDao.insertCoupon(list);
				}

				orderMap.put("retrun_coupon_amount", total.toString());
			}
		}

		/**
		 * 2.更新订单状态
		 */
		orderMap.put("updateTime", formatDate());
		Integer result = sPackageDao.updatePackageOrder(orderMap);
		if (result != 1) {
			log.error("更新订单状态失败orderNo:" + orderMap.get("order_no"));
			throw new FailureException(1, "更新订单状态失败orderNo:"
					+ orderMap.get("order_no"));
		}

		/**
		 * 3.发放优惠券
		 */
		List<Map<String, Object>> list = getParam(orderMap, packageIssue, (Integer) orderMap.get("nums"), 2);
		sPackageDao.insertCoupon(list);
	}

	//支付失败更新
	private void failUpdate(Map<String,Object> pOrder,Map<String,Object> packageIssue) throws FailureException{
		// 1更新订单状态
		Integer result = sPackageDao.updatePackageOrder(pOrder);
		if (result != 1) {
			log.error("更新订单状态失败");
			throw new FailureException(1, "更新订单状态为失败");
		}
		// 2.解除被锁定的预售抵用券
		if (pOrder.get("cdid") != null && pOrder.get("cdid") != "") {
			Integer result2 = couponOrderDao.updateCouponDetailStatus(pOrder
					.get("cdid").toString(), 0, formatDate());
			if (result2 != 1) {
				log.error("解除被锁定的预售抵用券失败");
				throw new FailureException(1, "解除被锁定的预售抵用券失败");
			}
		}
		//还原套餐优惠券库存
		Integer updateStock = sPackageDao.updateStock(pOrder);
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
	 * @throws FailureException 
	 */
	private List<Map<String, Object>> getParam(Map<String, Object> orderMap,
			Map<String, Object> couponMsg, Integer num,Integer type) throws FailureException {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
		if(type == 1){//发放抵用券
				Map<String, Object> coupMap = new HashMap<>();
				coupMap.put("cid", couponMsg.get("cid"));
				coupMap.put("denomination", couponMsg.get("denomination"));
				coupMap.put("sellerid",0);
				if((int)couponMsg.get("invalid_today")==0){//当天可用
					coupMap.put("start_date",formatDate());
					coupMap.put("end_date",getDate((int)couponMsg.get("day_num")));
				}else {//当天不可用
					coupMap.put("start_date",getDate(1));
					coupMap.put("end_date",getDate((int)couponMsg.get("day_num")+1));
				}
				coupMap.put("ctype",4);

				coupMap.put("get_status", 1);
				coupMap.put("get_time", formatDate());
				coupMap.put("uid", orderMap.get("uid"));
				coupMap.put("phone", orderMap.get("phone"));
				coupMap.put("anchor_cid",null);
				coupMap.put("user_status", 0);
				coupMap.put("date_issue", formatDate());
				coupMap.put("order_no", orderMap.get("order_no"));
				coupMap.put("seats",null);
				coupMap.put("serial", null);
				list.add(coupMap);
			
		}else{//发放优惠券
				Map<String, Object> coupMap = new HashMap<>();
				coupMap.put("pid", orderMap.get("pid"));
				
				long sellerid=Long.valueOf(orderMap.get("sellerid")+"");
				String serialNo = sellerActivityServiceImpl.getRedisSerial((int)sellerid);
				if(StringUtils.isBlank(serialNo)){
					throw new RuntimeException("获取序列号出错");
				}
				serialNo = "5"+serialNo;
				coupMap.put("serial", serialNo);
//				coupMap.put("denomination", (new BigDecimal(couponMsg.get("denomination").toString())).multiply(new BigDecimal(num+"")));
				
				if(Double.valueOf(orderMap.get("sellerCoin").toString())!= 0 || Double.valueOf(orderMap.get("beans").toString())!=0){//鸟币价
					coupMap.put("denomination",orderMap.get("selling_coin_price"));
				}else{//现金支付
					coupMap.put("denomination",orderMap.get("selling_price"));
				}
				coupMap.put("use_start_time", couponMsg.get("use_start_time"));
				coupMap.put("use_end_time", couponMsg.get("use_end_time"));
				coupMap.put("forbid_start_time", couponMsg.get("forbid_start_time"));
				coupMap.put("forbid_end_time", couponMsg.get("forbid_end_time"));
				coupMap.put("get_time", formatDate());
				coupMap.put("uid", orderMap.get("uid"));
				coupMap.put("phone", orderMap.get("phone"));
				coupMap.put("user_status", 0);
				coupMap.put("order_no", orderMap.get("order_no"));
				coupMap.put("sellerid",orderMap.get("sellerid"));
				coupMap.put("ledger_amount",getLedgerAmount(orderMap, couponMsg));//分账金额（用于优惠券分账）
				list.add(coupMap);
		}
		}
		return list;
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
	
	private String formatDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 计算优惠券分账金额
	 * @param orderMap
	 * @param couponMsg
	 * @throws FailureException 
	 */
	private Object getLedgerAmount(Map<String,Object> orderMap,Map<String,Object> couponMsg) throws FailureException{
		
		if((Integer)couponMsg.get("ledger_type")==1){//固定分账
			return couponMsg.get("ledger_ratio");
		}else if((Integer)couponMsg.get("ledger_type")==2){//按比例分账
			
			//现金支付总金额
			BigDecimal cashAmount = new BigDecimal(orderMap.get("cash").toString()).add(new BigDecimal(orderMap.get("balance").toString()))
					.add(new BigDecimal(orderMap.get("commision").toString())).add(new BigDecimal(orderMap.get("zbalance").toString()));
			
			if(Double.valueOf(orderMap.get("sellerCoin").toString())!= 0 || Double.valueOf(orderMap.get("beans").toString())!=0){//鸟币支付
				
				/**
				 * 鸟币支付
				 * 优惠券分账面额（用于优惠券分账）
				 * ledgerAmount = (((鸟币支付金额+优惠券抵用金额)/商家折扣)*鸟币人民币兑换比 +现金支付总额)/购买数量
				 */
				
				BigDecimal ledgerAmount = (
						new BigDecimal(orderMap.get("beans").toString()).add(new BigDecimal(orderMap.get("sellerCoin").toString())).add(new BigDecimal(orderMap.get("cuser").toString()))//鸟币支付金额+优惠券顶用金额
						).divide(new BigDecimal(orderMap.get("seller_agio").toString()),4,BigDecimal.ROUND_HALF_UP)// /商家折扣
						.multiply(new BigDecimal(orderMap.get("base_agio").toString()))// *鸟币人民币兑换比
						.add(cashAmount);// +现金支付总额
				return ledgerAmount.divide(new BigDecimal(orderMap.get("nums").toString()),2,BigDecimal.ROUND_HALF_UP);
				
			}else {//现金支付
				/**
				 * 现金支付
				 * 优惠券分账面额（用于优惠券分账）
				 * ledgerAmount = (现金支付总额+优惠券抵用金额)/购买数量
				 */
				BigDecimal ledgerAmount = cashAmount.add(new BigDecimal(orderMap.get("cuser").toString()));// 现金支付总额+优惠券抵用金额
				return ledgerAmount.divide(new BigDecimal(orderMap.get("nums").toString()),2,BigDecimal.ROUND_HALF_UP);// /购买数量
				
			}
			
		}else{//分账类型错误
			log.error("订单："+orderMap.get("order_no")+"分账类型错误");
			throw new FailureException(1,"分账类型错误");
		}
		
	}
	
	/**
	 * 更新套餐状态
	 * 
	 * @param params
	 */
	private void updateSellerPackageState(Map<String, Object> params) {
		sPackageDao.updateSellerPackageState(params);
	}
	
}
