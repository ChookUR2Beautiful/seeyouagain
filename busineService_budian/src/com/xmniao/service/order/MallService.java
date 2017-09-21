/**
 * 
 */
package com.xmniao.service.order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.BillBargainDao;
import com.xmniao.dao.order.MallOrderDao;
import com.xmniao.dao.order.MallPackageDao;
import com.xmniao.domain.order.MallOrderBean;
import com.xmniao.domain.order.MallOrderProductBean;
import com.xmniao.domain.order.MallPackage;
import com.xmniao.domain.order.MallSubOrderBean;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.live.VerMallService;
import com.xmniao.thrift.busine.common.FailureException;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：MallService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： Administrator
 * 
 * 创建时间：2017年8月11日 下午5:39:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class MallService {

	private final static Logger logger = LoggerFactory.getLogger(MallOrderServiceImpl.class);
	
	@Autowired
	private MallOrderDao mallOrderDao;
	
	@Resource(name="smsqueue")
	private String smsQueue;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Resource(name="waterCoupon")
	private Map<String,String> waterCoupon;

	@Autowired
	private MallPackageDao mallPackageDao;
	
	@Transactional
	public Map<String, String> notifyForPayComplete(Map<String, String> params) throws FailureException, TException {
		logger.info("积分商城订单支付成功通知，参数:{}",JSON.toJSONString(params));
		String bid = params.get("bid");
		
		if(StringUtils.isEmpty(bid)){
			throw new FailureException(1001,"参数bid不能为空。");
		}
		
		String payid =  params.get("payid");
		
		String status = params.get("status");
		
		if("1".equals(status)&&StringUtils.isEmpty(payid)){
			throw new FailureException(1002,"参数payid不能为空。");
		}
		
		if(StringUtils.isEmpty(status) || !("1".equals(status) || "8".equals(status)||"2".equals(status))){
			throw new FailureException(1003,"参数status不能为空,且值必须是1或2或8。");
		}
		
		MallOrderBean order = mallOrderDao.getByBid(Long.valueOf(bid));
		
		if(order == null){
			logger.error("订单不存在。");
			throw new FailureException(1004,"订单不存在。");
		}
		
		if(!verifyMallOrder(params,order)){
			logger.error("订单验证不通过。");
			throw new FailureException(1006,"订单验证不通过。");
		}
		
		if(order.getStatus().intValue() != 0){
			logger.error("重复通知，订单非待支付状态。");
			throw new FailureException(1005,"重复通知，订单非待支付状态。");
		}
		
		//使用优惠券，更新优惠券状态
		List<Map<String, Object>> couponDetailList = mallOrderDao.getCouponDetailByBid(order.getBid());  //根据订单编号获取多张优惠券
		if(order.getCdid() != null){
			/*Map<String, Object> couponDetail = mallOrderDao.getCouponDetail(order.getCdid().toString());
			if(couponDetail == null || (Integer)couponDetail.get("status")!= 1){
				logger.error("订单使用优惠券，但优惠券不存在或不是锁定状态"+order.getBid());
				throw new FailureException(1,"订单使用优惠券，但优惠券不存在或不是锁定状态"+order.getBid());
			}
			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("cdid",order.getCdid().toString());
			if("1".equals(status)){//支付成功 优惠券状态改为已使用
				paraMap.put("status","2");
				paraMap.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}else if("2".equals(status) || "8".equals(status)){//取消支付或支付失败   解 除优惠券占用,增加库存
				paraMap.put("status","0");
			}
			Long result = mallOrderDao.updateCouponStatus(paraMap);
			if(result != 1){
				logger.info("更新优惠券使用状态失败");
				throw new FailureException(1,""+order.getBid());
			}*/
			
			if (couponDetailList != null && couponDetailList.size() > 0){  
				boolean flag = true;
				for (Map<String, Object> map: couponDetailList){  //更改每一张优惠券信息的状态
					if(map == null || (Integer)map.get("status")!= 1){  //status使用状态，0未使用，1锁定，2已使用 3 已过期并退款
						flag = false;
						logger.error("订单使用优惠券，但优惠券不存在或不是锁定状态, 订单编号："+order.getBid() + "优惠券编号：" +(String)map.get("cdid"));
						throw new FailureException(1,"订单使用优惠券，但优惠券不存在或不是锁定状态, 订单编号："+order.getBid() + "优惠券编号：" +(String)map.get("cdid"));
					}				
				}
				
				if (flag ){  //如果状态都正常
					Map<String, Object> paraMap = new HashMap<>();
					//获取优惠券编号
					paraMap.put("bid", order.getBid());  
					if("1".equals(status)){//支付成功 优惠券状态改为已使用
						paraMap.put("status","2");
						paraMap.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					}else if("2".equals(status) || "8".equals(status)){//取消支付或支付失败   解 除优惠券占用,增加库存
						paraMap.put("status","0");
					}
					Long result = mallOrderDao.updateCouponStatusByBid(paraMap);
					if(result < 1){
						logger.info("更新优惠券使用状态失败");
						throw new FailureException(1,""+order.getBid());
					}
				}
				
			}
			
		}
		
		List<MallOrderProductBean>  products = mallOrderDao.getOrderProductByBid(Long.valueOf(bid));
		
		
			/**
			 * 支付成功 ：增加销售总数
			 * 其他状态：还原商品总库存和商品属性库存
			 */
			for (MallOrderProductBean mproduct : products) {
				Integer codeId = mproduct.getCodeId();//
				String attrIds = mproduct.getAttrids();//属性id
				Map<String,Object> map = new HashMap<>();
				long result = 0;
				long result2 = 0;
				map.put("codeId",codeId);
				map.put("attrIds",attrIds);
				map.put("num",mproduct.getWareNum());
				map.put("status",status);
				if(codeId != null && codeId !=0 && attrIds != null){
					//非活动商品
					if(mproduct.getActivityId() == null || mproduct.getActivityId()==0 || (mproduct.getActivityType()!=null && mproduct.getActivityType()==4)){
						if("2".equals(status) || "8".equals(status)){
							result = mallOrderDao.updateFreshRepertory(map);//还原属性库存
							result2 = mallOrderDao.updateFreshProductInfo(map);//还原商品库存
							
						}else if("1".equals(status)){
							result = mallOrderDao.updateFreshProductInfo(map);//增加商品销售总数
							result2 = 1;
						}
					}else{//活动商品
						
						map.put("activityId",mproduct.getActivityId());
						
						if("2".equals(status) || "8".equals(status)){
							
							result = mallOrderDao.updateRepertory(map);//还原属性库存
							result2 = mallOrderDao.updateFreshActivityProduct(map);//还原商品库存
							
						}else if("1".equals(status)){
							
							result = mallOrderDao.updateFreshActivityProduct(map);//增加商品销售总数
							result2 =1;
						}
					}
				}else {
					logger.error("订单："+bid+"产品编码或属性id为空");
					throw new FailureException(1,"还原库存失败");
				}
				
				if(result != 1 || result2 !=1){
					logger.error("更新库存失败"+mproduct);
					throw new FailureException(1,"还原库存失败");
				}
			}
		
		Map<String, String> result = new HashMap<String, String>();
		
		if(!"1".equals(status)){//非支付成功状态，直接更新订单状态
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bid", order.getBid());
			map.put("status", status);
			map.put("payid", payid);
			map.put("zdate", new Date());
			map.put("udate", map.get("zdate"));
			map.put("version", order.getVersion());
			map.put("number",params.get("number"));
			map.put("thirdUid",params.get("thirdUid"));
			
			map.put("payType", params.get("payType"));
			map.put("balance", params.get("balance"));
			map.put("commision", params.get("commision"));
			map.put("zbalance", params.get("zbalance"));
			map.put("birdCoin", params.get("birdCoin"));
			map.put("payment", params.get("payment"));
			
			mallOrderDao.updateForPayComplete(map);
			
			result.put("is_success", "T");
			logger.info("更新订单状态成功："+params);
			return result;
		}
		
		
		if(products.size() == 0){//无订单产品，将订单设置为“支付退款中”，触发系统退款
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bid", order.getBid());
			map.put("status",6);
			map.put("payid", payid);
			map.put("zdate", new Date());
			map.put("udate", map.get("zdate"));
			map.put("version", order.getVersion());
			map.put("number",params.get("number"));
			map.put("thirdUid",params.get("thirdUid"));
			
			map.put("payType", params.get("payType"));
			map.put("balance", params.get("balance"));
			map.put("commision", params.get("commision"));
			map.put("zbalance", params.get("zbalance"));
			map.put("birdCoin", params.get("birdCoin"));
			map.put("payment", params.get("payment"));
			
			mallOrderDao.updateForPayComplete(map);
			
			result.put("is_success", "T");
			
			logger.info("更新积分订单成功："+params);
			return result;
		}
		
		//根据供应商分组，准备拆单
		Map<Long,List<MallOrderProductBean>> groups = new HashMap<Long,List<MallOrderProductBean>>();
		
		Iterator<MallOrderProductBean> e = products.iterator();
		while(e.hasNext()){
			MallOrderProductBean product= e.next();
			
			if(!groups.containsKey(product.getSupplierId())){
				groups.put(product.getSupplierId(), new ArrayList<MallOrderProductBean>());
			}
			
			groups.get(product.getSupplierId()).add(product);
		}
		
		List<MallSubOrderBean> suborders = new ArrayList<MallSubOrderBean>();
		
		for(Entry<Long, List<MallOrderProductBean>> entry : groups.entrySet()){
			MallSubOrderBean sub = new MallSubOrderBean();
			sub.init(entry.getKey(),entry.getValue(),order);
			
			suborders.add(sub);
		}
		
		//订单现金支付部分
//		BigDecimal cash = order.getCash();
		BigDecimal cash = new BigDecimal(params.get("payment"));
		
		//订单余额支付部分
//		BigDecimal balance = order.getBalance();
		BigDecimal balance = new BigDecimal(params.get("balance")).add(new BigDecimal(params.get("zbalance"))).add(new BigDecimal(params.get("commision")));
		//订单积分支付部分
//		BigDecimal integral = order.getIntegral();
		BigDecimal integral = new BigDecimal(params.get("integral"));
		
		//鸟币支付部分
//		BigDecimal coin = order.getLiveCoin();
		BigDecimal coin = new BigDecimal(params.get("birdCoin"));
		
		//实际支付金额
		BigDecimal realPayAmount = cash.add(balance).add(coin);
		
		//判断是否是饮用水优惠券信息
		Boolean isflag = false;
		label:
		for (Map.Entry<String, String> maps : waterCoupon.entrySet()) {    
			logger.info("比较券信息："+"key:"+maps.getKey()+" value" + maps.getValue());
			Integer couponId = Integer.parseInt(maps.getValue()) ;
			if (couponDetailList != null && couponDetailList.size() > 0){  
				for (Map<String, Object> object: couponDetailList){  //更改每一张优惠券信息的状态
					if(couponId.equals((Integer)object.get("cid"))){  //优惠券信息
						isflag = true;
						break label;
					}	
				}
			}
		}  
		
		//优惠面额
		BigDecimal cuser = BigDecimal.ZERO;
		if(isflag){   // 100  20/ 20 ;10  20/ 10;
			cuser = order.getCuser().compareTo(order.getMoney()) < 0 ? order.getCuser() : order.getMoney();
		}else{  //(sb.total_amount+sb.freight-sb.preferential-sb.integral_amount) 待支付金额 App端计算公式 
			BigDecimal allMoney = order.getMoney().add(order.getFreight()).add(order.getIntegral()); //优惠面额 = 支付金额 + 运费 + 积分
			cuser = order.getCuser().compareTo(allMoney) < 0 ? order.getCuser() : allMoney;  //实际优惠的金额
		}

		/**
		 * 扣除运费
		 */
		if(cash.compareTo(order.getFreight())>=0){
			cash = cash.subtract(order.getFreight());
		}else if(cash.add(balance).compareTo(order.getFreight())>=0){
			cash = BigDecimal.ZERO;
			balance = cash.add(balance).subtract(order.getFreight());
		}else {
			cash = BigDecimal.ZERO;
			balance = BigDecimal.ZERO;
			coin = cash.add(balance).add(coin).subtract(order.getFreight());
		}
		
	
		for (MallSubOrderBean sub : suborders) { // 真实优惠的钱
			BigDecimal freight = new BigDecimal(params.get("freight"));
			BigDecimal totalAmount = sub.getTotalAmount().add(integral).add(freight);  //积分+运费
			
			int compared = cuser.compareTo(totalAmount);  //优惠券金额50 ， 总金额45
			switch (compared) {//优惠券金额足够支付
			case 1://现金大于子订单产品总金额，优先设为现金支付
				sub.setPreferential(cuser);  //优惠金额  sub.getTotalAmount()
				sub.setBalanceAmount(new BigDecimal("0"));  //余额支付金额
				  //用券+积分的情况  (sb.total_amount-sb.integral_amount) AS money App端计算公式 
			    sub.setIntegralAmount(new BigDecimal("0"));
			 
				cuser = cuser.subtract(sub.getTotalAmount());
				break;
				
			case 0://等同于 1
				sub.setPreferential(cuser);
				sub.setBalanceAmount(new BigDecimal("0"));
				sub.setIntegralAmount(new BigDecimal("0"));
				
				cuser = new BigDecimal("0");
				break;
			//优惠券金额不足以支付
			case -1:  //分配顺序 ：优惠面额,现金，余额，积分，鸟币 	
				sub.setPreferential(cuser);
				cuser = new BigDecimal("0");
				BigDecimal cash_3 = totalAmount.subtract(balance);
				
				sub.setBalanceAmount(balance);
				balance = new BigDecimal("0");
				
				if(cash_3.compareTo(new BigDecimal("0")) > 0){
					int compared_3 = integral.compareTo(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()));
					switch (compared_3) {
						case 1:  //积分足够支付
							sub.setIntegralAmount(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()));
							integral = integral.subtract(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()));
							break;

						case 0:
							sub.setIntegralAmount(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()));
							integral = new BigDecimal("0");
							break;
							
						case -1:
							 //积分不足以支付
							BigDecimal cash_4 = totalAmount.subtract(integral);
							
							sub.setIntegralAmount(integral);
							integral = new BigDecimal("0");
							
							if(cash_4.compareTo(new BigDecimal("0"))>0){
								int compare_4 = coin.compareTo(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()).subtract(sub.getIntegralAmount()));
								
								switch(compare_4){
								case 1: //鸟币足够支付
									sub.setCoinAmount(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()).subtract(sub.getIntegralAmount()));
									coin = coin.subtract(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()).subtract(sub.getIntegralAmount()));
									break;
								case 0:
									sub.setCoinAmount(totalAmount.subtract(sub.getPreferential()).subtract(sub.getBalanceAmount()).subtract(sub.getIntegralAmount()));
									coin = new BigDecimal("0");
									break;
								case -1:  //鸟币不足以支付
									break;
								}
							}else {
								sub.setCoinAmount(new BigDecimal("0"));
								sub.setCashAmount(new BigDecimal("0"));
							}
							break;
					}			
			    }
			}			
		}

		MallPackage mallPackage = new MallPackage();
		Integer activityId = products.get(0).getActivityId();
		Integer activityType = products.get(0).getActivityType();
		if(activityType!=null && activityType==4
				&& activityId!=null){
			mallPackage = mallPackageDao.selectById((long)activityId);
		}
		
		//更新大订单状态为已经支付
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bid", order.getBid());
		map.put("status", 1);//已经支付
		map.put("payid", payid);
		map.put("zdate", new Date());
		map.put("udate", map.get("zdate"));
		map.put("version", order.getVersion());
		map.put("number",params.get("number"));
		map.put("thirdUid",params.get("thirdUid"));
		
		map.put("payType", params.get("payType"));
		map.put("balance", params.get("balance"));
		map.put("commision", params.get("commision"));
		map.put("zbalance", params.get("zbalance"));
		map.put("birdCoin", params.get("birdCoin"));
		map.put("payment", params.get("payment"));
		
		map.put("rankType", mallPackage.getRankType());
		map.put("rankId", mallPackage.getRankId());
		int r =mallOrderDao.updateForPayComplete(map);
		
		if(r <= 0){
			throw new FailureException(1006,"并发更新，版本号冲突");
		}
		
		
		/**
		 * 调用支付服务扣除鸟币
		 */
		/*BigDecimal coin = new BigDecimal(params.get("birdCoin"));
		if(coin.compareTo(new BigDecimal("0"))==1){
			Map<String,String> liveMap = new HashMap<>();
			liveMap.put("uid",order.getUid().toString());
			liveMap.put("rtype","15");
			liveMap.put("zbalance",coin.toString());
			liveMap.put("remarks",order.getBid().toString());
			boolean result2 = cServiceImpl.updateLiveWallet(liveMap);
			if (!result2) {
				logger.error("业务服务调用支付服务更新鸟币失败");
				throw new FailureException(1, "业务服务调用支付服务更新鸟币失败");
			}
		}*/
		
		//插入子订单
		for(MallSubOrderBean sub : suborders){
			mallOrderDao.addSubOrder(sub);
			
			for(MallOrderProductBean product : sub.getProducts()){
				mallOrderDao.setSubBidForOrderProduct(sub.getSubOrderSn(), product.getId());
			}
		}
		
		/**
		 * 下发短信
		 */
		sendMsg(realPayAmount.toString(),order.getBid().toString(),order.getPhoneid());
		
		result.put("is_success", "T");
		return result;
	}

	/**
	 * 
	 * @Title: verifyMallOrder 
	 * @Description:验证订单数据一致性
	 */
	private boolean verifyMallOrder(Map<String, String> params,MallOrderBean order){
		StringBuffer paramsStr = new StringBuffer();
		paramsStr.append(params.get("bid"));
		paramsStr.append(params.get("orderAmount"));
//		paramsStr.append(params.get("balance"));
//		paramsStr.append(params.get("zbalance"));
//		paramsStr.append(params.get("commision"));
		paramsStr.append(params.get("integral"));
		paramsStr.append(params.get("freight"));
		paramsStr.append(params.get("cuser"));
//		paramsStr.append(params.get("birdCoin"));
//		paramsStr.append(String.format("%.2f",Double.valueOf(params.get("orderAmount"))));
//		paramsStr.append(String.format("%.2f",Double.valueOf(params.get("balance"))));
//		paramsStr.append(String.format("%.2f",Double.valueOf(params.get("zbalance"))));
//		paramsStr.append(String.format("%.2f",Double.valueOf(params.get("commision"))));
//		paramsStr.append(String.format("%.2f",Double.valueOf(params.get("integral"))));
		
		
		
		StringBuffer orderStr = new StringBuffer();
		orderStr.append(order.getBid());
		orderStr.append(order.getMoney());
//		orderStr.append(order.getProfit());
//		orderStr.append(order.getGiveMoney());
//		orderStr.append(order.getCommision());
		orderStr.append(order.getIntegral());
		orderStr.append(order.getFreight());
		orderStr.append(order.getCuser());
//		orderStr.append(order.getLiveCoin());
		
		logger.info("订单数据一致性对比"+paramsStr+" : "+orderStr);
		boolean flag1 = paramsStr.toString().equals(orderStr.toString());
		
		BigDecimal toatl = order.getMoney().add(order.getFreight());
		BigDecimal realAmount;
		boolean flag2;
		if(!"2".equals(params.get("status"))){
			if(order.getCuser().compareTo(new BigDecimal("0"))==1){//使用优惠券
				realAmount = new BigDecimal(params.get("balance")).add(new BigDecimal(params.get("zbalance"))).add(new BigDecimal(params.get("commision")))
				.add(new BigDecimal(params.get("birdCoin"))).add(new BigDecimal(params.get("cuser"))).add(new BigDecimal(params.get("payment")));
				flag2=realAmount.compareTo(toatl)>=0?true:false;
			}else{//不使用优惠券
				realAmount = new BigDecimal(params.get("balance")).add(new BigDecimal(params.get("zbalance"))).add(new BigDecimal(params.get("commision")))
						.add(new BigDecimal(params.get("birdCoin"))).add(new BigDecimal(params.get("payment")));
				flag2=realAmount.compareTo(toatl)==0?true:false;
			}
			logger.info("订单总额比对"+toatl+" : "+realAmount);
		}else {
			flag2 = true;
		}
		return flag1&&flag2;
	}
	
	/**
	 * 订单支付成功，下发短信
	 */
	public void sendMsg(String realPay,String bid,String phoneid){
		 //组装短信发送
        Map<String,String> sendSmsMap = new HashMap<String,String>();
        //手机号码
        sendSmsMap.put("phoneid",phoneid);
        //订单编号
        sendSmsMap.put("bid",bid);
        
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎使用寻蜜鸟，你在寻蜜鸟商城的商品订单已付款成功，消费"+realPay);
        sb.append("元。订单编号："+bid+"。");
        sendSmsMap.put("smscontent",sb.toString());
        String smsJson = JSONObject.toJSONString(sendSmsMap);
        logger.info("SendSms Redis Key:" + smsQueue + "==Send Sms JSON:" + smsJson);
        
        try {
			redisTemplate.opsForList().rightPush(smsQueue, smsJson);
		} catch (Exception e) {
			logger.error("订单"+bid+"发送短信失败",e);
		}
	}
}
