package com.xmniao.quartz.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilString;
import com.xmniao.dao.FreshOrderMapper;
import com.xmniao.dao.FreshRefundMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.quartz.AutoRefreshRefundService;
import com.xmniao.service.KuaiqianPayService;
import com.xmniao.service.LlPayService;
import com.xmniao.service.RefundService;
import com.xmniao.service.TongPayService;
import com.xmniao.service.UPayService;
import com.xmniao.service.WPayService;
import com.xmniao.service.pay.PayRefundServiceImpl;

/**
 * 查询更新正在退款中的退款生鲜单
 * @author ChenBo
 *
 */
@Service("autoRefreshFreshRefund")
public class AutoRefreshFreshRefundServiceImpl implements AutoRefreshRefundService{

	 //初始日志类
	private final Logger log = Logger.getLogger(AutoRefreshFreshRefundServiceImpl.class);
	
	@Autowired
	private FreshRefundMapper freshRefundMapper;
	
	@Autowired
	private FreshOrderMapper freshOrderMapper;
	
	@Resource(name="wxPayRefund")
	private WPayService wPayService;
	
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
	private RefundService refundService;
	
	//查询条件，每次查询的id均需>cursorId
	private int cursorId = 0;
	
	//本次查询任务，统计本次查询的总订单数
	private int countNum = 0;
	
	//本次查询后，统计更新为退款成功的个数
	private int successNum = 0;
	
	//本次查询后，统计更新为退款失败的个数
	private int failNum = 0;
	
	//private int refundStatus = PayRefundStatus.PROCESS_SUCCESS;
	
	//每次sql查询个数
	private final int PAGE_NUM =1000;
	//每次sql查询的起始条件
	private final int BEGIN_ID =0;
	
	@Override
	public void autoRefreshRefund(){
		refreshFreshRefund();

	}
	
	
	public Map<String,String> autoRefreshWPayRefund(Map<String,Object> refund,WPayService wxSerivce,String payType) {
		
		log.info("查询微信退款状态,微信账号："+wxSerivce.getWxPartner());
		String thirdId = (String)refund.get("third_id");
		//String id = String.valueOf(refund.get("id"));
		String refundId = (String)refund.get("refund_id");
		//String payId = String.valueOf(refund.get("pay_id"));
		
		if(thirdId == null || StringUtils.isBlank(thirdId)){
			log.error("该退款记录"+refundId+"中无微信支付交易号");
			return refundService.returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "");
		}
		return wxSerivce.QueryWPayRefund(thirdId, 1);
	}
	
	
	
	public Map<String,String> autoRefreshWPayRefund(Map<String,Object> refund){
		return autoRefreshWPayRefund(refund,wPayService,PayTypeUtil.wxpay);
	}
	
	public Map<String,String> autoRefreshWxscPayRefund(Map<String,Object> refund){
		return autoRefreshWPayRefund(refund,wxscPayService,PayTypeUtil.wxscpay);
	}
	
	public void refreshFreshRefund() {
		log.info("开始自动更新生鲜业务退款订单状态。。。");
		
		/*
		 * 需查询的支付类型
		 */
		String[] refreshPayType ={PayTypeUtil.wxpay,
						PayTypeUtil.wxscpay,
						PayTypeUtil.llpay,
						PayTypeUtil.umpay,
						PayTypeUtil.tlpay,
						PayTypeUtil.kqpay};
		
		//更新结果List
		List<Map<String,Object>> rResultList =  new ArrayList<Map<String,Object>>();
		initQueryCount();
		
		StringBuffer payTypes = new StringBuffer();
		for(String type:refreshPayType){
			payTypes.append(type).append(",");
		}
		String types = payTypes.substring(0, payTypes.length()-1).toString();
		while(true){
			//1.获取表中处于退款中状态的退款记录
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("thirdName", types);
			queryMap.put("status", PayRefundStatus.PROCESSING);
			queryMap.put("cursorId", cursorId);
			queryMap.put("beginId", BEGIN_ID);
			queryMap.put("pageNum", PAGE_NUM);
			List<Map<String,Object>> refundList = getRefundList(queryMap);
			if(refundList == null || refundList.size() == 0){
				cursorId = 0;
				log.info("本次没有需要更新查询的订单");
				break ;
			}
			
			int curSize = refundList.size();
			countNum += curSize;
			
			String maxId = String.valueOf(refundList.get(curSize-1).get("id"));
			cursorId = Integer.parseInt(maxId);
			
			//2.依次调用各第三方支付退款查询接口
			for(Map<String,Object> refund :refundList){
				String id = String.valueOf(refund.get("id"));
				String refundId = (String)refund.get("refund_id");
				String payId = String.valueOf(refund.get("pay_id"));
				if(cursorId < Integer.parseInt(id)){
					cursorId = Integer.parseInt(id);
				}
				log.info("开始查询id为:"+id+"的退款订单");
				Map<String,String> map = thirdRefundRefresh(refund);
				map = dealResultAndWallet(map, id, refundId);
				
				
				//更新退款表中状态,保持为退款中状态的不更新
				//更新退款状态
				int refreshStatus = PayRefundStatus.PROCESSING;
				if(payRefundService.isRefundSuccess(map)){
					refreshStatus=PayRefundStatus.PROCESS_SUCCESS;
					successNum++;
				}
				else if(payRefundService.isRefundFail(map)){
					refreshStatus = PayRefundStatus.PROCESS_Failure;
					failNum++;
				}
				refundService.updateRefundStatus(refundId, refreshStatus,PayTypeUtil.ORDER_FRESH);
				if(PayRefundStatus.PROCESSING != refreshStatus){
					Map<String,Object> rMap= new HashMap<String,Object>();
					rMap.put("refundId", refundId);
					rMap.put("status", refreshStatus);
					rResultList.add(rMap);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(map)){
					Map<String,Object> payOrderMap = freshOrderMapper.getPayOrderBypayId(payId);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =refundService.updateFreshBusineStatus(orderNumber,(String)map.get("code"),map.get("Msg"));
					if(busineMap == null){
						log.error("定点查询订单退款状态，调用业务服务接口，失败");
					}
				}
			}
		}
		
		log.info("本次自动查询退款状态完毕，共查询了"+countNum+"个未完成退款的订单，更新后的情况如下："
				+ "本次更新为退款成功的订单"+successNum+"个，更新为失败的订单"+failNum+"个，仍保持为退款中的订单"+(countNum-successNum-failNum)+"个。");
	}
	
	/*
	 * 通联
	 */
	public Map<String,String> autoRefreshTPayRefund(Map<String,Object> refund) {
		//String thirdId = (String)refund.get("third_id");
		//String id = String.valueOf(refund.get("id"));
		String refundId = (String)refund.get("refund_id");
		String payId = String.valueOf(refund.get("pay_id"));
		BigDecimal samount = (BigDecimal)refund.get("samount");

		String amount = samount.multiply(new BigDecimal(100)).intValue()+"";
		
		return tPayService.tongPayRefundQuery(payId,refundId, amount);
	}
	
	//快钱退款查询
	public Map<String,String> autoRefreshKPayRefund(Map<String,Object> refund) {
		//String thirdId = (String)refund.get("third_id");
		//String id = String.valueOf(refund.get("id"));
		String refundId = (String)refund.get("refund_id");
		String payId = String.valueOf(refund.get("pay_id"));
		//BigDecimal samount = (BigDecimal)refund.get("samount");
		//String amount = samount.multiply(new BigDecimal(100)).intValue()+"";
		
		return kPayService.KuaiqianQueryRefund(payId,refundId);
	}
	
	public Map<String,String> autoRefreshUPayRefund(Map<String,Object> refund) {
		
		String thirdId = (String)refund.get("third_id");
		//String id = String.valueOf(refund.get("id"));
		String refundId = (String)refund.get("refund_id");
		//String payId = String.valueOf(refund.get("pay_id"));
		
		if(thirdId == null || StringUtils.isBlank(thirdId)){
			log.error("该退款记录"+refundId+"中无U付支付交易号");
			return refundService.returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "");
		}
		return uPayService.queryUpayRefund(refundId);
	}
	
	/*
	 * 连连退款查询
	 */
	public Map<String,String> autoRefreshLPayRefund(Map<String,Object> refund) {
		//String thirdId = (String)refund.get("third_id");
		//String id = String.valueOf(refund.get("id"));
		String refundId = (String)refund.get("refund_id");
		//String payId = String.valueOf(refund.get("pay_id"));
		String sDate;
		try {
			sDate = UtilString.dateFormatToNumber(String.valueOf(refund.get("sdate")));
		} catch (ParseException e1) {
			e1.printStackTrace();
			log.error("退款记录表中，记录的时间格式化错误。");
			return refundService.returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "");
		}
		
		return llPayService.llPayRefundQuery(refundId,"", sDate);
	}

	/*
	 * 处理第三方查询后的操作
	 */
	private Map<String,String> dealResultAndWallet(Map<String,String> map,String id,String refundId){
		if(map.get("code").equals(StateCodeUtil.PR_SUCCESS)){
			log.info("退款id:"+id+"该退款记录退款成功");
			map.clear();
			//退钱包的余额
			PayRefund payRefund = freshRefundMapper.getPayRefundByRefundId(refundId);
			try {
				int result =refundService.getAccordRefundWallet(payRefund.getBid().toString());
				if(result==0){
					map = refundService.updateWallet(payRefund);
				}else{
					if(result ==1){
						log.info("该订单已取消支付，不退钱包支付部分金额");
					}else if(result==2){
						log.info("该订单尚未消费，不允许退钱包支付部分金额 ");
					}else{
						log.info("该订单不允许退钱包支付部分金额");
					}
					//不退钱包支付部分，统一作退款钱包成功
					map.put("code", StateCodeUtil.PR_SUCCESS);
					map.put("Msg", "");
					map.put("response", "");
				}
			} catch (Exception e) {
				
				log.error("退款流水"+refundId+"的订单,退钱包部分出错",e);
				map.put("code", StateCodeUtil.PR_SYSTEM_ERROR);
				map.put("Msg", "退款流水"+refundId+"的订单,退钱包部分出错");
				map.put("response", "");
			}
		}
		else if(map.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING)){
			log.info("退款id:"+id+"该退款记录还在退款中");
		}
		else{
			log.info("退款流水:"+id+"该退款记录退款失败");
		}
		return map;
	}
	
	
	private void initQueryCount(){
		countNum = 0;
		failNum = 0;
		successNum = 0;
	}


	private List<Map<String,Object>> getRefundList(Map<String,Object> queryMap){
		return freshRefundMapper.getPayRefundNoList(queryMap);
	}
	
	private Map<String,String> thirdRefundRefresh(Map<String,Object> refund){
		Map<String,String> resultMap;
		String thirdName = refund.get("thirdName").toString();
		if(thirdName.equals(PayTypeUtil.umpay)){
			log.info("U付退款");
			resultMap = autoRefreshUPayRefund(refund);
		}else if(thirdName.equals(PayTypeUtil.wxpay)){
			log.info("微信/财付通退款");
			resultMap = autoRefreshWPayRefund(refund);
		}else if(thirdName.equals(PayTypeUtil.kqpay)){
			log.info("快钱退款");
			resultMap = autoRefreshKPayRefund(refund);
		}else if(thirdName.equals(PayTypeUtil.tlpay)){
			log.info("通联退款");
			resultMap = autoRefreshTPayRefund(refund);
		}else if(thirdName.equals(PayTypeUtil.llpay)){
			log.info("连连退款");
			resultMap = autoRefreshLPayRefund(refund);
		}else if(thirdName.equals(PayTypeUtil.wxscpay)){
			log.info("微信商城退款");
			resultMap = autoRefreshWxscPayRefund(refund);
		}else{
			log.error("未知方式 的支付退款 "+thirdName);
			resultMap = refundService.returnMap(StateCodeUtil.PR_THIRD_REFUNDING,"");
		}
		return resultMap;
	}

}
