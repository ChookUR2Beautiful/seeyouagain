package com.xmniao.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.LlPayService;
import com.xmniao.service.RefundService;
import com.xmniao.service.impl.WithdrawMoneyServiceTools;
import com.xmniao.service.pay.PayRefundServiceImpl;

@Controller
public class LlPayNotifyController {
	private final Logger log = Logger.getLogger(LlPayNotifyController.class);
	
	
	@Resource(name = "llPayService")
	private LlPayService llPayService;
	
	@Resource(name = "PayRefundServiceImpl")
	private PayRefundServiceImpl payRefundService;
	
	@Autowired
	private RefundService refundService;
	
	
	@Autowired
	private WithdrawMoneyServiceTools withdrawMoneyServiceTools;
	
	@Autowired
	private LlPayService llPay;
	   
	@RequestMapping(value = "/2", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object test3(@RequestBody String json){
		System.out.println(json);
		return retResponse();
	}
	
	@RequestMapping(value = {"/llRefundNotify","/xmn/llRefundNotify"}, method = RequestMethod.POST)
	@ResponseBody
	public Object xmnRefundNotify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("连连退款寻蜜鸟订单异步通知回调入口");
		return RequestHandle(request,response,0);
	}
	
	@RequestMapping(value = "/fresh/llRefundNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object freshRefundNotify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("连连退款生鲜订单异步通知回调入口");
		return RequestHandle(request,response,1);
	}
	
	public Object RequestHandle(HttpServletRequest request,HttpServletResponse response,int serviceType) throws IOException{
		
//		StringBuffer sb = new StringBuffer() ; 
//		InputStream is = request.getInputStream(); 
//		InputStreamReader isr = new InputStreamReader(is);   
//		BufferedReader br = new BufferedReader(isr); 
//		String s = "" ; 
//		while((s=br.readLine())!=null){ 
//		sb.append(s) ; 
//		} 
//		String json = new String(sb.toString().getBytes("gbk"),"utf-8");
//		System.out.println(json);
	    StringBuffer json = new StringBuffer();
	    String line = null;
	    try {
	        BufferedReader reader = request.getReader();
	        while((line = reader.readLine()) != null) {
	            json.append(line);
	        }
	    }
	    catch(Exception e) {
	        log.error("",e);
	    }
	    log.info( json.toString());		
	
		@SuppressWarnings("unchecked")
		Map<String, String> reqMap = (Map<String, String>) JSON.parse(json.toString());
		if(llPayService.verifySign(reqMap)){
			String refundId = reqMap.get("no_refund");

			//是否 该在退款记录表中，有该订单
			PayRefund payRefund = refundService.getPayRefundByRefundId(refundId,serviceType);
			if(payRefund != null){
				return refundOrder(payRefund,reqMap,serviceType);
			}
			else{
				sleepForWait(5);
				payRefund = refundService.getPayRefundByRefundId(refundId,serviceType);
				if(payRefund != null){
					return refundOrder(payRefund,reqMap,serviceType);
				}else{
					log.error("退款流水号:"+refundId+"无对应的记流水订单表或退款记录表");
				}
			}
		}	
		return null;
	}
		
    //给连连支付反馈表明已接收到通知信息
	private String retResponse(){
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("ret_code", "0000");
		retMap.put("ret_msg", "Ok");
		
		return JSON.toJSONString(retMap);
	}
	
	public Map<String,String> returnMap(String code,String msg){
		return this.returnMap(code,msg,"");
	}
	//返回的Map数据
	public Map<String,String> returnMap(String code,String msg,String response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("code", code);
		resultMap.put("Msg",msg);
		resultMap.put("response",response);
		return resultMap;
	}

	public Object refundOrder(PayRefund payRefund,Map<String,String> reqMap,int serviceType){
		String amount = reqMap.get("money_refund");
		String refundState = reqMap.get("sta_refund");
		
		Map<String,String> resultMap = returnMap(StateCodeUtil.PR_REFUND_FAIL,"");
		if(payRefund.getStatus() == PayRefundStatus.PROCESS_SUCCESS){
			resultMap = returnMap(StateCodeUtil.PR_SUCCESS,"");
			log.info("该订单已退款成功");
		}
		else {
			try{
				BigDecimal refundAmount = new BigDecimal(amount);
				//是否 实际退款金额与申请退款金额一致
				if (refundAmount.compareTo(payRefund.getSamount()) == 0) {
					if(refundState.equals("2")){
						int result =refundService.getAccordRefundWallet(payRefund.getBid().toString());
						if(result==0){
							resultMap = refundService.updateWallet(payRefund);
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
							resultMap = returnMap(StateCodeUtil.PR_SUCCESS,"");
						}
					}
					else if(refundState.equals("3")){
						log.error("退款流水号:"+payRefund.getRefundId()+"连连退款反馈失败了！");
						resultMap = returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL,"");
					}
				} 
			
			} catch (Exception e) {
				resultMap = payRefundService.returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "钱包退款异常");
				log.error("",e);
			}finally{
				//更新退款状态
				if(payRefundService.isRefundSuccess(resultMap)){
					refundService.updateRefundStatus(payRefund.getRefundId(), PayRefundStatus.PROCESS_SUCCESS,serviceType);
				}
				else if(payRefundService.isRefundFail(resultMap)){
					refundService.updateRefundStatus(payRefund.getRefundId(), PayRefundStatus.PROCESS_Failure,serviceType);
				}
				
				//调用业务服务接口，更新相关状态
				if(!payRefundService.isRefunding(resultMap)){
					Map<String,Object> payOrderMap = refundService.getPayOrderBypayId(payRefund.getPayId()+"",serviceType);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap =null;
					if(0==serviceType){
					payRefundService.updateBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}else if(1 == serviceType){
						refundService.updateFreshBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}
					
					if(busineMap == null){
						log.error("连连异步退款，调用业务服务接口，失败");
					}
				}
			}
		}
		return retResponse();
	
	}
	public void sleepForWait(int s){
		log.info("线程Sleep...");
        try {  
            Thread.sleep(s*1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } 
	}
	
	/*
	 * 代付异步通知
	 */
	@RequestMapping(value = "/llNofity", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object payNotify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("----------连连支付,代付回调通知---------------");
//	    StringBuffer json = new StringBuffer();
//	    String line = null;
//	    try {
//	        BufferedReader reader = request.getReader();
//	        while((line = reader.readLine()) != null) {
//	            json.append(line);
//	        }
//	    }
//	    catch(Exception e) {
//	        log.error("",e);
//	    }
//	    log.info( json.toString());		
//	
//		Map<String, String> reqMap = (Map<String, String>) JSON.parse(json.toString());
//		if(llPayService.verifySign(reqMap)){
//			String orderIdStr = reqMap.get("no_order"); // 订单号
//			//String merDate = result.get("mer_date");
//
//			String[] orderIdArray = orderIdStr.split("_");
//
//			String orderId = orderIdArray[0];
//
//			String userType = orderIdArray[1];
//
//			String tradeState = reqMap.get("result_pay"); // 退费状态
//			
//			String tradeNo = reqMap.get("oid_paybill");//联动优势交易流水号
//			
//			String retMsg = "";//
//			
//			String sta = commonService.getWithdrawStatus(userType,orderId);
//
//			// 验证是否已经修改了支付状态
//			if (!PayConstants.WITHDRAW_STATUS_PROCESS.equals(sta)) {
//			    return retResponse();
//			}else if ("-1".equals(sta)) {
//				log.error("连连代付回调参数异常----userType:"+userType+",orderId:"+orderId);
//				return retResponse();
//			}
//			
//			String status = tradeState != null && tradeState.equalsIgnoreCase("SUCCESS")?PayConstants.WITHDRAW_STATUS_SUCCESS:PayConstants.WITHDRAW_STATUS_FAIL;
//			if(status.equals(PayConstants.WITHDRAW_STATUS_SUCCESS)){
//				retMsg = PayConstants.WITHDRAW_MSG_SUCCESS;
//			}else{
//				String[] reason = String.valueOf(reqMap.get("info_order")).split("_");
//				retMsg = PayConstants.WITHDRAW_MSG_FAIL+","+reason[reason.length-1];
//			}
//
//			//修改分账服务
//			Map<String, String> uwsMap = new HashMap<String, String>();
//			uwsMap.put("orderNumber", orderId);
//			uwsMap.put("status", status);
//			uwsMap.put("Message", retMsg);
//			uwsMap.put("platform_code", tradeNo);
//
//			try {
//				//修改提现状态
//				commonService.updateWithdrawState(orderId, userType, Integer.valueOf(status),PayConstants.WITHDRAW_TYPE_LL, uwsMap);
//			} catch (Exception e) {
//				log.error("连连付代发回调异常！订单号为："+orderId,e);
//			}
//		}	
		return retResponse();
	}
	
	/*
	 * 代付测试
	 */
	@RequestMapping(value = "/llPay", method = {RequestMethod.POST,RequestMethod.GET})
	public void pay(HttpServletRequest request,HttpServletResponse response){
		try{
		int userType=2;
		String orderId = request.getParameter("orderId"); 
		Map<String, Object> mapEntity = withdrawMoneyServiceTools.getOrderData(orderId, userType);
		mapEntity.put("no_order", String.valueOf(mapEntity.get("id"))+"_"+userType);
		Map<String, String> resultMap = llPay.withdrawMoney(mapEntity);// 连连代发执行
		log.info("代付申请结果："+resultMap);

		}catch(Exception e){
			log.error("请求异常",e);
		}
	}
	/*
	 * 代付查询测试
	 */
	@RequestMapping(value = "/llQuery", method = {RequestMethod.POST,RequestMethod.GET})
	public void query(HttpServletRequest request,HttpServletResponse response){
		System.out.println("代付查询");
		try{
		int userType=2;
		String orderId = request.getParameter("orderId"); 
		
		String  folwId=orderId+"_"+userType;
		Map<String, String> resultMap = llPay.withdrawMoneyQuery(folwId);// 连连代发执行
		log.info("代付查询结果："+resultMap);

		}catch(Exception e){
			log.error("请求异常",e);
		}
	}
}
