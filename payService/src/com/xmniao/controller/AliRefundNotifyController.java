package com.xmniao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.PayTypeUtil;
import com.xmniao.common.RefundUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.AliPayService;
import com.xmniao.service.RefundService;
import com.xmniao.service.impl.RefundServiceImpl;
import com.xmniao.service.pay.PayRefundServiceImpl;


/**
 * 支付宝异步通知数据 处理
 * 
 * @author ChenBo
 *
 */
@Controller
public class AliRefundNotifyController {
	
	@Resource(name = "AliPayServiceImpl")
	private AliPayService aliPayService;
	
	@Resource(name = "PayRefundServiceImpl")
	private PayRefundServiceImpl payRefundService;
	
	@Autowired
	private RefundService refundService;
	
	@Autowired
	private PayOrderMapper payOrderMapper;
	
	@Autowired
	private RefundServiceImpl refundServiceImpl;
 	 //初始日志类
	private final Logger log = Logger.getLogger(AliRefundNotifyController.class);
	
	@RequestMapping(value={"/aliRefundNotify","/xmn/aliRefundNotify"},method={RequestMethod.GET,RequestMethod.POST})
	public void xmnRefundNotify(HttpServletRequest request,HttpServletResponse response){
		requestHandle(request,response,PayTypeUtil.ORDER_XMN);
	}
	
	@RequestMapping(value="/fresh/aliRefundNotify",method={RequestMethod.GET,RequestMethod.POST})
	public void freshRefundNotify(HttpServletRequest request,HttpServletResponse response){
		requestHandle(request,response,PayTypeUtil.ORDER_FRESH);
	}
	
	public void requestHandle(HttpServletRequest request,HttpServletResponse response,int serviceType){

		// 获得通知参数
		Map<String, String> params = toMap(request.getParameterMap());
		Map<String,String> resultMap = new HashMap<String,String>();
		log.info("支付宝退款异步通知："+JSON.toJSONString(params));
		boolean istrue = aliPayService.verify(params);
		log.info("数据验证结果:"+istrue);
		if (istrue) {
			String aliBatchNo = params.get("batch_no") == null?"":params.get("batch_no");
			if(StringUtils.isBlank(aliBatchNo)){
				log.error("退款记录号错误");
				return;
			}

			String refundId = aliPayService.batchNoTorefundId(aliBatchNo);
			//是否 该在退款记录表中，有该订单
			PayRefund payRefund = refundService.getPayRefundByRefundId(refundId,serviceType);
			if(payRefund == null){
				log.error("退款流水号:"+refundId+"无对应的记流水订单表或退款记录表");
				return;
			}
			
			try {
				//验证支付宝退款状态
				int aliResult = aliPayService.RefoundNotity(params,payRefund);
				if(aliResult == 1){
					//回复支付宝退款成功
					doResponse(response);
					//重复退款，直接返回
					return;
				}
				else if(aliResult == 0){
					//回复支付宝退款成功
					doResponse(response);
					
					//退 钱包支付部分的钱到 钱包中
					log.info("支付宝退款完毕，退 钱包支付部分到 钱包中");
					
					int result = refundService.getAccordRefundWallet(payRefund.getBid().toString());
					if(result==0){
						resultMap = refundService.updateWallet(payRefund);
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
						resultMap.put("code", StateCodeUtil.PR_SUCCESS);
						resultMap.put("Msg", "");
						resultMap.put("response", "");
					}
					
//					if(RefundUtil.isRefundSuccess(resultMap)&&(payRefund.getLiveCoin().compareTo(BigDecimal.ZERO)>0||payRefund.getSellerCoin().compareTo(BigDecimal.ZERO)>0)){
//						resultMap = refundServiceImpl.liveCoinRefund(payRefund);
//					}
				}
				else{
					//更新退款状态
					log.error("支付宝服务器退款异常");
					resultMap = payRefundService.returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "支付宝服务器退款异常");
				}
			
			} catch (Exception e) {
				resultMap = payRefundService.returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "钱包退款异常");
				e.printStackTrace();
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
					Map<String,Object> payOrderMap = refundService.getPayOrderBypayId(payRefund.getPayId()+"", serviceType);
					String orderNumber = String.valueOf(payOrderMap.get("order_number"));
					Map<String,String> busineMap = null;
					if(serviceType==PayTypeUtil.ORDER_XMN){
						busineMap = payRefundService.updateBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}else if(serviceType==PayTypeUtil.ORDER_FRESH){
						busineMap = refundService.updateFreshBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}
					
					if(busineMap == null){
						log.error("支付宝异步退款，调用业务服务接口，失败");
					}
				}
			}
	
		}
	}
	  
    
    //给支付宝反馈"success"
	protected void doResponse(HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write("success");
			out.flush();
		} catch (IOException e) {
			log.debug("notify alipay is error : "+ e);
			
		} finally{
			if(out != null)out.close();
		}
	}
	
	
	/**
	 * 把map里的值转成String类型的
	 * @param map
	 * @return
	 */
	public Map<String,String> toMap(Map<String, String[]> map){
		Map<String,String> newMap = new HashMap<String,String>();
		Set<String> set=map.keySet();
		Iterator<String> it = set.iterator();
		 for(int i=0;i<set.size();i++){
			   String itStr = it.next();
			   String strw[] =map.get(itStr);
			   String strNew ="";
			   for (int index = 0; index < strw.length; index++)
			   {    
				   strNew += strw[index];
			   }
			   newMap.put(itStr, strNew);
		 }
		return newMap;
	}
	
}
