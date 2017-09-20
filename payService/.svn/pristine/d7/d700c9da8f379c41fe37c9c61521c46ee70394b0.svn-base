package com.xmniao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.PayConstants;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UpdateLedgerSystem;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.CommonService;
import com.xmniao.service.RefundService;
import com.xmniao.service.ShengPayService;
import com.xmniao.service.pay.PayRefundServiceImpl;
import com.xmniao.service.pay.WithdrawMoneyServiceImpl;

/**
 * 盛付通回调
 * @author Cheno
 *
 */
@Controller
public class ShengPayNotifyController {
	 //初始日志类
	private final Logger log = Logger.getLogger(ShengPayNotifyController.class);
	
	@Autowired
	private ShengPayService shengPayService;
	
	@Autowired
	private RefundService refundService;
	
	@Resource(name = "PayRefundServiceImpl")
	private PayRefundServiceImpl payRefundService;
	
	@Autowired
	private PayOrderMapper payOrderMapper;
	
	@Autowired
	private WithdrawMoneyServiceImpl withdrawMoneyServiceImpl;
	
	@Autowired
	private UpdateLedgerSystem updateLedgerSystem;
	
	@Autowired
	private CommonService commonService;
	
	private final String REFUNDING = "00";	//处理中
	private final String SUCCESS = "01";	//成功
	private final String FAILED = "02";	//失败
	private final String REFUNDOK ="OK";
	
	@RequestMapping(value={"/xmn/shengRefundNotify","/shengRefundNotify"},method={RequestMethod.GET,RequestMethod.POST})
	public void xmnRefundNotyfy(HttpServletRequest request,HttpServletResponse response) throws Exception{
		shengRefundNotyfy(request,response,0);
	}
	@RequestMapping(value="/fresh/shengRefundNotify",method={RequestMethod.GET,RequestMethod.POST})
	public void freshRefundNotyfy(HttpServletRequest request,HttpServletResponse response) throws Exception{
		shengRefundNotyfy(request,response,1);
	}
	
	public void shengRefundNotyfy(HttpServletRequest request,HttpServletResponse response,int serviceType) throws Exception{
		log.info("盛付通退款回调");
		
		Map<String,String> resultMap = new HashMap<String,String>();
		PrintWriter out;
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
			return ;
		}
		
		request.setCharacterEncoding("UTF-8");
		
		Map<String,String> params=new LinkedHashMap<String,String>();
		params.put("Name", request.getParameter("Name").trim());
		params.put("Version", request.getParameter("Version").trim());
		params.put("Charset", request.getParameter("Charset").trim());
		params.put("SenderId", request.getParameter("SenderId").trim());
		params.put("SendTime", request.getParameter("SendTime").trim());
		params.put("RefundOrderNo", request.getParameter("RefundOrderNo").trim());
		params.put("OriginalOrderNo", request.getParameter("OriginalOrderNo").trim());
		params.put("Status", request.getParameter("Status").trim());
		params.put("RefundAmount", request.getParameter("RefnudAmount").trim());
		params.put("RefundTransNo", request.getParameter("RefundTransNo").trim());
		params.put("Ext1", request.getParameter("Ext1").trim());
		params.put("Ext2", request.getParameter("Ext2").trim());
		params.put("SignType", request.getParameter("SignType").trim());
		params.put("SignMsg", request.getParameter("SignMsg").trim());
		log.info("回调结果："+params);

		String refundId = request.getParameter("RefundOrderNo").trim();
		String status = request.getParameter("Status").trim();
		String amount = request.getParameter("RefundAmount").trim();
		String signMsg = request.getParameter("SignMsg").trim();
		
		if(!shengPayService.verifyRefundSign(params)){
			log.error("盛付通回调数据验证不通过。"+JSON.toJSONString(params));
			return ;
			//退款失败
		}else{
			
			//根据退款流水号获取退款流水记录
			PayRefund payRefund = refundService.getPayRefundByRefundId(refundId,serviceType);
			if(payRefund == null){
				log.error("找不到退款流水号"+refundId+"对应的流水订单");
				return;
			}
			//验证是否已经修改了支付状态
			if(payRefund.getStatus().equals(PayRefundStatus.PROCESS_SUCCESS)){
				log.info("该订单已成功退款");
				out.println(REFUNDOK);
				return;
			}
			
			try{
				if(status.equals(SUCCESS)){
					log.info("退款提示成功");
					
					BigDecimal refundAmount = new BigDecimal(amount);
					//是否 实际退款金额与申请退款金额一致
					if(refundAmount.compareTo(payRefund.getSamount())==0){	//退款金额一致
						out.println(REFUNDOK);
						//退钱包
						int result=refundService.getAccordRefundWallet(payRefund.getBid().toString());
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
					}
					else{
						log.error("盛付通退款金额不一致！");
						resultMap = payRefundService.returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, "盛付通实际退款金额与请求退款金额不一致");
					}
				}
				else if(status.equals(FAILED)){
					log.info("退款流水:"+refundId+",盛付通退款提示失败");
					resultMap = payRefundService.returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, "盛付通反馈退款失败");
				}
				else if(status.equals(REFUNDING)){
					log.info("退款提示退款中。。。");
					resultMap = payRefundService.returnMap(StateCodeUtil.PR_THIRD_REFUNDING, "盛付通反馈退款失败");
				}
				else{
					log.error("退款流水:"+refundId+",盛付通反馈未知状态");
					resultMap = payRefundService.returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, "盛付通反馈未知状态");
				}
			
			}
			catch(Exception e){
				log.error("系统执行异常");
				resultMap = payRefundService.returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "系统执行异常");
				e.printStackTrace();
			}
			finally{
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
					Map<String,String> busineMap = null;
					if(0== serviceType){
						payRefundService.updateBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}else if(1 == serviceType){
						refundService.updateFreshBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}
					if(busineMap == null){
						log.error("盛付通异步退款，调用业务服务接口，失败");
					}
				}
			}
		}
			
	
	}
	
	
	/**
	 * 盛付通代付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/shengPayNotify",method={RequestMethod.GET,RequestMethod.POST})
	public void shengPayNotyfy(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		log.info("盛付通代付回调----shengPayNotyfy  start");
		long edate = System.currentTimeMillis();
		log.info("盛付通代付回调时间-----------："+edate+"，订单号："+ request.getParameter("batchNo"));
		log.info("回调信息---resultCode:"+request.getParameter("resultCode")+";resultName:"+request.getParameter("resultName")+
				 ";statusCode:"+request.getParameter("statusCode")+";statusName:"+request.getParameter("statusName")+";resultMemo:"+request.getParameter("resultMemo"));
		String tempresult1 = "";
		
		String orderIdStr = request.getParameter("batchNo");
		String orderId = orderIdStr.substring(0, orderIdStr.length()-1);
		String userType = orderIdStr.substring(orderIdStr.length()-1, orderIdStr.length());
		
		String sta = commonService.getWithdrawStatus(userType,orderId);

		// 验证是否已经修改了支付状态
		if (!PayConstants.WITHDRAW_STATUS_PROCESS.equals(sta)) {
			return;
		}
		if ("-1".equals(sta)) {
			log.error("汇付天下代付回调参数异常----userType:"+userType+",orderId:"+orderId);
			return;
		}
		
		String status = PayConstants.WITHDRAW_STATUS_AUDIT;// 财务处理中
		String msg = "";
		String resultCode = request.getParameter("resultCode");
		if (resultCode.equals("S001")||"S002".equals(resultCode)) { // 交易成功
			status = PayConstants.WITHDRAW_STATUS_SUCCESS;
		    msg = PayConstants.WITHDRAW_MSG_SUCCESS;
		} else {// 交易失败
			status = PayConstants.WITHDRAW_STATUS_FAIL;
		    msg = PayConstants.WITHDRAW_MSG_FAIL+","+request.getParameter("resultName");
		}
		
	
		PrintWriter out = null;
		try {
		    
		    Map<String, String> uwsMap = new HashMap<String, String>();
		    uwsMap.put("orderNumber", orderId);
		    uwsMap.put("status", status);
		    uwsMap.put("Message", msg);
		    uwsMap.put("platform_code", orderIdStr);
		    //修改提现状态
		    commonService.updateWithdrawState(orderId, userType, Integer.valueOf(status), PayConstants.WITHDRAW_TYPE_SFT, uwsMap);
			// 输出串，按此格式输出，<resultCode>ok</resultCode>表求接收到了通知，如无此串，会重复通知
			tempresult1 = "<result><sign></sign><signType>MD5</signType><resultCode>ok</resultCode><resultMessage>ok</resultMessage></result>";
			out = response.getWriter();
			out.write(tempresult1);
			out.flush();
		} catch (Exception e) {
			log.info("盛付通代付回调异常！订单号为："+orderId,e);
		} finally {
			if (out != null)
			    out.close();
		} 

		log.info("盛付通代付回调----shengPayNotyfy  end");	
	}
}
