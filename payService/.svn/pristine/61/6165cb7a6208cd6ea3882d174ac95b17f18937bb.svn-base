package com.xmniao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chinapnr.SecureLink;

import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.PnrRefundService;
import com.xmniao.service.RefundService;
import com.xmniao.service.pay.PayRefundServiceImpl;

/**
 * 汇付退款 异步回调
 * @author ChenBo
 *
 */
@Controller
public class PnrRefundNotifyController {
	 //初始日志类
	private final Logger log = Logger.getLogger(PnrRefundNotifyController.class);
	
	@Resource(name = "pnrPayRefund")
	private PnrRefundService pnrPayService;
	
	@Autowired
	
	private PayOrderMapper payOrderMapper;
	
	@Autowired
	private PayRefundServiceImpl payRefundService;
	
	@Autowired
	private RefundService refundService;
	
	private final String PNR_SUCCESS = "000000";
	
	@RequestMapping({"/PnrRefundNotify","/xmn/PnrRefundNotify"})
	public void xmnRefundNotify(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		noticeHandle(request,response,0);
	}
	@RequestMapping("/fresh/PnrRefundNotify")
	public void freshRefundNotify(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		noticeHandle(request, response, 1);
	}

	public void noticeHandle(HttpServletRequest request,HttpServletResponse response,int serviceType) throws UnsupportedEncodingException{
		log.info("[NoticeHandle]---汇付天下，退款异步通知---");
		Map<String,String> resultMap = new HashMap<String,String>();
		PrintWriter out;
		
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
			return ;
		}
		
	
			request.setCharacterEncoding("GBK");
			String CmdId = request.getParameter("CmdId").trim() ;				//消息类型
			String OrdId = request.getParameter("OrdId").trim();				//退款订单号
			String OldOrdId = request.getParameter("OldOrdId").trim();			//原交易订单号
			String RespCode = request.getParameter("RespCode").trim();	 		//商户号
			String ErrMsg = request.getParameter("ErrMsg").trim();				//应答错误描述
			String ChkValue = request.getParameter("ChkValue").trim();			//签名
			
//          模拟测试用			
//			if(CmdId == null) CmdId ="Refund";
//			if(OrdId == null) OrdId ="20141209095342658";
//			if(OldOrdId == null) OldOrdId ="1412031958433574";
//			if(RespCode == null) RespCode ="000001";
//			if(ErrMsg == null) ErrMsg ="";
//			if(ChkValue == null) ChkValue ="";
			
			log.info("cmdId:"+CmdId+",ordId:"+OrdId+",oldOrdId:"+OldOrdId+",respCode:"+RespCode+",errMsg:"+ErrMsg+",chkValue:"+ChkValue);
			
			//验签
			String 	MerKeyFile	= request.getSession().getServletContext().getRealPath("/") + "/PgPubk.key";
			String	MerData = CmdId + OrdId + OldOrdId + RespCode + ErrMsg;  	//参数顺序不能错
			SecureLink sl = new SecureLink() ;
			int ret = sl.VeriSignMsg(MerKeyFile , MerData, ChkValue) ;
			if (ret != 0) 
			{
				out.println("签名验证失败["+MerData+"]");
				log.error("签名验证失败:"+MerData);
				return;
			}

				
			//根据退款流水号获取退款流水记录
			PayRefund payRefund = refundService.getPayRefundByRefundId(OrdId,serviceType);
			if(payRefund == null){
				log.error("找不到退款流水号"+OrdId+"对应的流水订单");
				return;
			}
			//验证是否已经修改了支付状态
			if(payRefund.getStatus().equals(PayRefundStatus.PROCESS_SUCCESS)){
				log.info("该订单已成功退款");
				out.println("退款成功");
				out.println("RECV_ORD_ID_"+OldOrdId);
				return;
			}
			
			try
			{	
				if(RespCode.equals(PNR_SUCCESS))
				{
					out.println("退款成功");
					log.info("汇付退款成功，退 钱包支付部分到 钱包中");
					int result= refundService.getAccordRefundWallet(payRefund.getBid().toString());
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
				else
				{
					//退款失败
					resultMap = payRefundService.returnMap(StateCodeUtil.PR_THIRD_SERVER_FAIL, "退款失败:RespCode="+RespCode+",ErrMsg="+ErrMsg);
					out.println("退款失败");
					log.error("退款失败:RespCode="+RespCode+",ErrMsg="+ErrMsg);
				}
				out.println("RECV_ORD_ID_"+OldOrdId);
				
			}
			catch(Exception e)
			{
				out.println("签名验证异常");
				log.error("系统执行异常，汇付退款-钱包退款部分异常");
				resultMap = payRefundService.returnMap(StateCodeUtil.PR_SYSTEM_ERROR, "系统执行异常");
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
					if(0 == serviceType){
						payRefundService.updateBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}else if(1 == serviceType){
						refundService.updateFreshBusineStatus(orderNumber,resultMap.get("code"),resultMap.get("Msg"));
					}
							if(busineMap == null){
						log.error("汇付异步退款，调用业务服务接口，失败");
					}
				}
			}
	}
}
