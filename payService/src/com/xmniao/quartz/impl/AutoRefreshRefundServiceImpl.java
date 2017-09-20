package com.xmniao.quartz.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.PayTypeUtil;
import com.xmniao.common.RefundUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilString;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.dao.PayRefundMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.quartz.AutoRefreshRefundService;
import com.xmniao.service.KuaiqianPayService;
import com.xmniao.service.LlPayService;
import com.xmniao.service.RefundService;
import com.xmniao.service.TongPayService;
import com.xmniao.service.UPayService;
import com.xmniao.service.WPayService;
import com.xmniao.service.impl.RefundServiceImpl;
import com.xmniao.service.pay.PayRefundServiceImpl;

/**
 * 一个关于退款服务的定时服务，主要功能为查询更新正在退款中的退款单
 * @author ChenBo
 *
 */
@Service(value="autoRefreshRefund")
public class AutoRefreshRefundServiceImpl implements AutoRefreshRefundService{

	 //初始日志类
	private final Logger log = Logger.getLogger(AutoRefreshRefundServiceImpl.class);
	
	@Autowired
	private PayRefundMapper payRefundMapper;
	
	@Autowired
	private PayOrderMapper payOrderMapper;
	
	
	//2201
	@Resource(name="wxPayRefund")
	private WPayService wPayService;
	//4401
	@Resource(name="wxV3PayRefund")
	private WPayService wxscPayService;
	
	@Autowired
	private TongPayService tPayService;
	
	@Autowired
	private KuaiqianPayService kPayService;

	@Autowired
	private UPayService uPayService;
	
	@Autowired
	private LlPayService llPayService;
	
	@Autowired
	private PayRefundServiceImpl payRefundService;
	
	@Autowired
	private RefundServiceImpl refundServiceImpl;
	
	@Autowired
	private RefundService refundService;
	
	@Resource(name="autoRefreshFreshRefund")
	private AutoRefreshRefundService freshRefreshService;
	
	//查询条件，每次查询的id均需>cursorId
	private int cursorId = 0;
	
	//本次查询任务，统计本次查询的总订单数
	private int countNum = 0;
	
	//本次查询后，统计更新为退款成功的个数
	private int successNum = 0;
	
	//本次查询后，统计更新为退款失败的个数
	private int failNum = 0;
	
	private int refundStatus = PayRefundStatus.PROCESS_SUCCESS;
	
	//每次sql查询个数
	private final int PAGE_NUM =1;
	//每次sql查询的起始条件
	private final int BEGIN_ID =0;
	
	@Override
	public void autoRefreshRefund(){
		
		log.info("== 自动查询更新退款订单状态开始 ==");
		//微信查询
		autoRefreshWPayRefund();
		
		//微信4401账户查询
		autoRefreshWxscPayRefund();
		//通联查询
		autoRefreshTPayRefund();
		
		//快钱查询
		autoRefreshKPayRefund();
		
		//u付查询
		autoRefreshUPayRefund();
		
		//连连查询
		autoRefreshLPayRefund();
		
		log.info("== 自动查询更新退款订单状态结束 ==");
		
		freshRefreshService.autoRefreshRefund();
		
	}
	
	public void autoRefreshWPayRefund() {
		
		log.info("[autoRefreshWPayRefund]系统自动查询微信账号【2201】退款状态");
		initQueryCount();
		
		while(true){
			//1.获取表中处于退款中状态的微信支付退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", PayTypeUtil.wxpay);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> wPaylist = getRefundList(queryMap);
			if(wPaylist == null || wPaylist.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询微信的订单");
				break ;
			}
			
			int curSize = wPaylist.size();
			countNum += curSize;
			
			String maxId = String.valueOf(wPaylist.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
//			System.out.println("curSize="+curSize+"cursorId="+cursorId);
			
			
			//2.依次调用微信支付退款查询接口
			for(Map<String,Object> refund :wPaylist){
				String thirdId = (String)refund.get("third_id");
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				
				log.info("开始查询id为:"+id+"的退款订单");
				
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}
				if(thirdId == null || StringUtils.isBlank(thirdId)){
					log.error("该退款记录"+refundId+"中无微信支付交易号");
					continue;
				}
				refundStatus = PayRefundStatus.PROCESSING;
				Map<String,String> map =wPayService.QueryWPayRefund(thirdId, 1);
				if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
					log.info("退款id:"+id+"该退款记录退款成功");
					
					//退钱包的余额
					PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
					try {
						int result =refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result==0){
							map = refundService.updateWallet(payRefund);
						}else{
							if(result ==1){
								log.error("该订单已取消支付，不退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
							}else if(result==2){
								log.error("该订单尚未消费，不允许退钱包支付部分金额 ");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
							}else{
								log.error("该订单不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
							}
							//不退钱包支付部分，统一作退款钱包成功
							map.put("code", StateCodeUtil.PR_SUCCESS);
							map.put("Msg", "");
							map.put("response", "");
						}
						
//						if(payRefund.getLiveCoin().compareTo(BigDecimal.ZERO)>0||payRefund.getSellerCoin().compareTo(BigDecimal.ZERO)>0){
//							map = refundServiceImpl.liveCoinRefund(payRefund);
//						}
						refundStatus = PayRefundStatus.PROCESS_SUCCESS;
						successNum++;
					} catch (Exception e) {
						
						e.printStackTrace();
						log.error("退款流水"+refundId+",微信退款退钱包部分出错");
						failNum++;
						refundStatus = PayRefundStatus.PROCESS_Failure;
					}
				}
				else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
					log.info("退款id:"+id+"该退款记录还在退款中");
					refundStatus = PayRefundStatus.PROCESSING;
				}
				else{
					log.info("退款流水:"+id+"该退款记录退款失败");
					failNum++;
					refundStatus = PayRefundStatus.PROCESS_Failure;
				}
				
				//更新注册表中状态,保持为退款中状态的不更新
				if(refundStatus != PayRefundStatus.PROCESSING){
					refundService.updateRefundStatus(refundId, refundStatus);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = payOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =payRefundService.updateBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询微信退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询微信退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");
	}

	public void autoRefreshWxscPayRefund() {
		
		log.info("[autoRefreshWPayRefund]系统自动查询微信商城账号【4401】退款状态");
		initQueryCount();
		
		while(true){
			//1.获取表中处于退款中状态的微信支付退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", PayTypeUtil.wxscpay);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> wPaylist = getRefundList(queryMap);
			if(wPaylist == null || wPaylist.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询微信的订单");
				break ;
			}
			
			int curSize = wPaylist.size();
			countNum += curSize;
			
			String maxId = String.valueOf(wPaylist.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
//			System.out.println("curSize="+curSize+"cursorId="+cursorId);
			
			
			//2.依次调用微信支付退款查询接口
			for(Map<String,Object> refund :wPaylist){
				String thirdId = (String)refund.get("third_id");
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				
				log.info("开始查询id为:"+id+"的退款订单");
				
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}
				if(thirdId == null || StringUtils.isBlank(thirdId)){
					log.error("该退款记录"+refundId+"中无微信支付交易号");
					continue;
				}
				refundStatus = PayRefundStatus.PROCESSING;
				Map<String,String> map =wxscPayService.QueryWPayRefund(thirdId, 1);
				if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
					log.info("退款id:"+id+"该退款记录退款成功");
					
					//退钱包的余额
					PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
					try {
						int result =refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result==0){
							map = refundService.updateWallet(payRefund);
						}else{
							if(result ==1){
								log.error("该订单已取消支付，不退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
							}else if(result==2){
								log.error("该订单尚未消费，不允许退钱包支付部分金额 ");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
							}else{
								log.error("该订单不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
							}
							//不退钱包支付部分，统一作退款钱包成功
							map.put("code", StateCodeUtil.PR_SUCCESS);
							map.put("Msg", "");
							map.put("response", "");
						}
						refundStatus = PayRefundStatus.PROCESS_SUCCESS;
						successNum++;
					} catch (Exception e) {
						
						e.printStackTrace();
						log.error("退款流水"+refundId+",微信退款退钱包部分出错");
						failNum++;
						refundStatus = PayRefundStatus.PROCESS_Failure;
					}
				}
				else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
					log.info("退款id:"+id+"该退款记录还在退款中");
					refundStatus = PayRefundStatus.PROCESSING;
				}
				else{
					log.info("退款流水:"+id+"该退款记录退款失败");
					failNum++;
					refundStatus = PayRefundStatus.PROCESS_Failure;
				}
				
				//更新注册表中状态,保持为退款中状态的不更新
				if(refundStatus != PayRefundStatus.PROCESSING){
					refundService.updateRefundStatus(refundId, refundStatus);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = payOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =payRefundService.updateBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询微信退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询微信退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");
	}
	
	/*
	 * 通联
	 */
	public void autoRefreshTPayRefund() {
		log.info("通联定时查询任务");
		log.info("[autoRefreshTPayRefund]系统自动查询通联退款状态");
				
		initQueryCount();
		
		while(true){
			//1.获取表中处于退款中状态的通联支付退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", PayTypeUtil.tlpay);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> tPaylist = getRefundList(queryMap);
			if(tPaylist == null || tPaylist.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询的通联订单");
				break ;
			}
			
			int curSize = tPaylist.size();
			countNum += curSize;
			
			String maxId = String.valueOf(tPaylist.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
//			System.out.println("curSize="+curSize+"cursorId="+cursorId);
			
			
			//2.依次调用通联支付退款查询接口
			for(Map<String,Object> refund :tPaylist){
				String thirdId = (String)refund.get("third_id");
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				BigDecimal samount = (BigDecimal)refund.get("samount");

				String amount = samount.multiply(new BigDecimal(100)).intValue()+"";
				log.info("开始查询id为:"+id+"的退款订单");
				
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}
				refundStatus = PayRefundStatus.PROCESSING;
				Map<String,String> map =tPayService.tongPayRefundQuery(payId,refundId, amount);
				if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
					log.info("退款id:"+id+"该退款记录退款成功");
					
					//退钱包的余额
					PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
					try {
						int result = refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result ==0 ){
						map = refundService.updateWallet(payRefund);
						}else{
							if(result ==1){
								log.error("该订单已取消支付，不退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
							}else if(result==2){
								log.error("该订单尚未消费，不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
							}else{
								log.error("该订单不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
							}
							
							//不退钱包支付部分，统一作退款钱包成功
							map.put("code", StateCodeUtil.PR_SUCCESS);
							map.put("Msg", "");
							map.put("response", "");
						}
						refundStatus = PayRefundStatus.PROCESS_SUCCESS;
						successNum++;
					} catch (Exception e) {
						
						e.printStackTrace();
						log.error("退款流水"+refundId+",通联退款退钱包部分出错");
						failNum++;
						refundStatus = PayRefundStatus.PROCESS_Failure;
					}
				}
				else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
					log.info("退款id:"+id+"该退款记录还在退款中");
					refundStatus = PayRefundStatus.PROCESSING;
				}
				else{
					log.info("退款流水:"+id+"该退款记录退款失败");
					failNum++;
					refundStatus = PayRefundStatus.PROCESS_Failure;
				}
				
				//更新注册表中状态,保持为退款中状态的不更新
				if(refundStatus != PayRefundStatus.PROCESSING){
					refundService.updateRefundStatus(refundId, refundStatus);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = payOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =payRefundService.updateBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询通联退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询通联退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");

	}
	
	//快钱退款查询
	public void autoRefreshKPayRefund() {
		log.info("快钱定时查询任务");
		log.info("[autoRefreshWPayRefund]系统自动查询快钱退款状态");
				
		initQueryCount();
		
		while(true){
			//1.获取表中处于退款中状态的通联支付退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", PayTypeUtil.kqpay);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> tPaylist = getRefundList(queryMap);
			if(tPaylist == null || tPaylist.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询的快钱订单");
				break ;
			}
			
			int curSize = tPaylist.size();
			countNum += curSize;
			
			String maxId = String.valueOf(tPaylist.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
//			System.out.println("curSize="+curSize+"cursorId="+cursorId);
			
			
			//2.依次调用快钱支付退款查询接口
			for(Map<String,Object> refund :tPaylist){
				String thirdId = (String)refund.get("third_id");
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				BigDecimal samount = (BigDecimal)refund.get("samount");
				String amount = samount.multiply(new BigDecimal(100)).intValue()+"";
				log.info("开始查询id为:"+id+"的退款订单");
				
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}
				refundStatus = PayRefundStatus.PROCESSING;
				Map<String,String> map =kPayService.KuaiqianQueryRefund(payId,refundId);
				if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
					log.info("退款id:"+id+"该退款记录退款成功");
					
					//退钱包的余额
					PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
					try {
						int result = refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result==0){
						map = refundService.updateWallet(payRefund);
						}else{
							if(result ==1){
								log.error("该订单已取消支付，不退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
							}else if(result==2){
								log.error("该订单尚未消费，不允许退钱包支付部分金额 ");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
							}else{
								log.error("该订单不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
							}
							//不退钱包支付部分，统一作退款钱包成功
							map.put("code", StateCodeUtil.PR_SUCCESS);
							map.put("Msg", "");
							map.put("response", "");
						}
						refundStatus = PayRefundStatus.PROCESS_SUCCESS;
						successNum++;
					} catch (Exception e) {
						
						e.printStackTrace();
						log.error("退款流水"+refundId+",快钱退款退钱包部分出错");
						failNum++;
						refundStatus = PayRefundStatus.PROCESS_Failure;
					}
				}
				else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
					log.info("退款id:"+id+"该退款记录还在退款中");
					refundStatus = PayRefundStatus.PROCESSING;
				}
				else{
					log.info("退款流水:"+id+"该退款记录退款失败");
					failNum++;
					refundStatus = PayRefundStatus.PROCESS_Failure;
				}
				
				//更新注册表中状态,保持为退款中状态的不更新
				if(refundStatus != PayRefundStatus.PROCESSING){
					refundService.updateRefundStatus(refundId, refundStatus);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = payOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =payRefundService.updateBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询快钱退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询快钱退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");

	}
	
	public void autoRefreshUPayRefund() {
		
		log.info("[autoRefreshUPayRefund]系统自动查询U付退款状态");
		initQueryCount();
		
		while(true){
			//1.获取表中处于退款中状态的U付支付退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", PayTypeUtil.umpay);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> wPaylist = getRefundList(queryMap);
			if(wPaylist == null || wPaylist.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询的U付订单");
				break ;
			}
			
			int curSize = wPaylist.size();
			countNum += curSize;
			
			String maxId = String.valueOf(wPaylist.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
			//2.依次调用U付支付退款查询接口
			for(Map<String,Object> refund :wPaylist){
				String thirdId = (String)refund.get("third_id");
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				
				log.info("开始查询id为:"+id+"的退款订单");
				
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}
				if(thirdId == null || StringUtils.isBlank(thirdId)){
					log.error("该退款记录"+refundId+"中无U付支付交易号");
					continue;
				}
				refundStatus = PayRefundStatus.PROCESSING;
				Map<String,String> map =uPayService.queryUpayRefund(refundId);
				if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
					log.info("退款id:"+id+"该退款记录退款成功");
					
					//退钱包的余额
					PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
					try {
						int result = refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result==0){
						map = refundService.updateWallet(payRefund);
						}else{
							if(result ==1){
								log.error("该订单已取消支付，不退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
							}else if(result==2){
								log.error("该订单尚未消费，不允许退钱包支付部分金额 ");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
							}else{
								log.error("该订单不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
							}
							//不退钱包支付部分，统一作退款钱包成功
							map.put("code", StateCodeUtil.PR_SUCCESS);
							map.put("Msg", "");
							map.put("response", "");
						}
						refundStatus = PayRefundStatus.PROCESS_SUCCESS;
						successNum++;
					} catch (Exception e) {
						
						e.printStackTrace();
						log.error("退款流水"+refundId+",U付退款退钱包部分出错");
						failNum++;
						refundStatus = PayRefundStatus.PROCESS_Failure;
					}
				}
				else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
					log.info("退款id:"+id+"该退款记录还在退款中");
					refundStatus = PayRefundStatus.PROCESSING;
				}
				else{
					log.info("退款流水:"+id+"该退款记录退款失败");
					failNum++;
					refundStatus = PayRefundStatus.PROCESS_Failure;
				}
				
				//更新注册表中状态,保持为退款中状态的不更新
				if(refundStatus != PayRefundStatus.PROCESSING){
					refundService.updateRefundStatus(refundId, refundStatus);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = payOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =payRefundService.updateBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询U付退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询U付退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");
	}
	
	/*
	 * 连连退款查询
	 */
	public void autoRefreshLPayRefund() {
		
		log.info("[autoRefreshLPayRefund]系统自动查询连连退款状态");
		initQueryCount();
		
		while(true){
			//1.获取表中处于退款中状态的连连支付退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", PayTypeUtil.llpay);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> wPaylist = getRefundList(queryMap);
			if(wPaylist == null || wPaylist.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询的连连订单");
				break ;
			}
			
			int curSize = wPaylist.size();
			countNum += curSize;
			
			String maxId = String.valueOf(wPaylist.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
//			System.out.println("curSize="+curSize+"cursorId="+cursorId);
			
			
			//2.依次调用连连支付退款查询接口
			for(Map<String,Object> refund :wPaylist){
				String thirdId = (String)refund.get("third_id");
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				String sDate;
				try {
					sDate = UtilString.dateFormatToNumber(String.valueOf(refund.get("sdate")));
				} catch (ParseException e1) {
					e1.printStackTrace();
					log.error("退款记录表中，记录的时间格式化错误。");
					continue;
				}
				
				log.info("开始查询id为:"+id+"的退款订单");
				
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}

				refundStatus = PayRefundStatus.PROCESSING;
				Map<String,String> map =llPayService.llPayRefundQuery(refundId,"", sDate);
				if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
					log.info("退款id:"+id+"该退款记录退款成功");
					
					//退钱包的余额
					PayRefund payRefund = payRefundMapper.getPayRefundByRefundId(refundId);
					try {
						int result = refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result==0){
						map = refundService.updateWallet(payRefund);
						}else{
							if(result ==1){
								log.error("该订单已取消支付，不退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该订单已取消支付，不退钱包支付部分金额");
							}else if(result==2){
								log.error("该订单尚未消费，不允许退钱包支付部分金额 ");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单尚未消费，不允许退款!");
							}else{
								log.error("该订单不允许退钱包支付部分金额");
								//resultMap.put("Msg", /*resultMap.get("Msg")+*/"该订单尚未消费，不允许退款! ");
								//return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该订单不允许退款!");
							}
							//不退钱包支付部分，统一作退款钱包成功
							map.put("code", StateCodeUtil.PR_SUCCESS);
							map.put("Msg", "");
							map.put("response", "");
						}
						refundStatus = PayRefundStatus.PROCESS_SUCCESS;
						successNum++;
					} catch (Exception e) {
						
						e.printStackTrace();
						log.error("退款流水"+refundId+",连连退款退钱包部分出错");
						failNum++;
						refundStatus = PayRefundStatus.PROCESS_Failure;
					}
				}
				else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
					log.info("退款id:"+id+"该退款记录还在退款中");
					refundStatus = PayRefundStatus.PROCESSING;
				}else{
					log.info("退款id:"+id+"该退款记录退款失败");
					failNum++;
					refundStatus = PayRefundStatus.PROCESS_Failure;
				}

				
				//更新注册表中状态,保持为退款中状态的不更新
				if(refundStatus != PayRefundStatus.PROCESSING){
					refundService.updateRefundStatus(refundId, refundStatus);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = payOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =payRefundService.updateBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询连连退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询连连退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");
	}

	
	private void initQueryCount(){
		countNum = 0;
		failNum = 0;
		successNum = 0;
	}


	private List<Map<String,Object>> getRefundList(Map<String,Object> queryMap){
		return payRefundMapper.getPayRefundNoList(queryMap);
	}
//	@Override
//	public void resetRefundCursor() {
//		log.info("[resetRefundCursor]系统自动重置退款订单游标cursorId=0,之前的值为cursorId="+cursorId);
//		cursorId = 0;
//	}
}
