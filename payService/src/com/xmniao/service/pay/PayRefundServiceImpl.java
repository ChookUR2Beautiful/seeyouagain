package com.xmniao.service.pay;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.PayRefundStatus;
import com.xmniao.common.PayTypeUtil;
import com.xmniao.common.RefundUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilString;
import com.xmniao.dao.PayOrderMapper;
import com.xmniao.entity.PayRefund;
import com.xmniao.service.RefundService;
import com.xmniao.thrift.busine.OrderService;
import com.xmniao.thrift.busine.OrderService.Client;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.PayRefundService;
import com.xmniao.thrift.ledger.RefundRequest;


/**
 * 商户提交的确认订单退款操作
 * @author ChenBo
 *
 */
@Service("PayRefundServiceImpl")
public class PayRefundServiceImpl implements PayRefundService.Iface{
	
	 //初始日志类
	private final Logger log = Logger.getLogger(PayRefundServiceImpl.class);
	
	@Autowired
	private RefundService refundService;
	
	@Autowired
	private PayOrderMapper payOrderMapper;
	
	@Resource(name = "BUSINESS_IP_NUMBER")
	private String ipNumbertBusine;
	
	@Resource(name = "IP_PORT_BUSINE")
	private int ipPortBusine;

	//充值到钱包订单
	private final int RECHARGE_ORDER = 1;
	//消费订单
//	private final int PAY_ORDER = 2;
	//向蜜客保证金订单
//	private final int MIKE_ORDER = 3;
	
	
	/**
	 * 退款入口
	 */
	@Override
	public Map<String, String> PayRefund(RefundRequest refundRequest)
			throws FailureException, TException {
		Map<String,String> resultMap = new HashMap<String,String>();
				
		log.info("PayRefund----->refundRequest.orderNumer:"+refundRequest.getOrderNumber()+"----->refundRequest.money:"
					+refundRequest.getMoney()+"----->refundRequest.remark:"+refundRequest.getRemark()+"refundRequest.bId:"+refundRequest.getBid());
		try{	
			//1.查询该支付订单信息
			String orderNumber = refundRequest.getOrderNumber()==null?"":refundRequest.getOrderNumber().trim();
			BigInteger bId=null;
			try{
				bId = new BigInteger(orderNumber);
			}catch(NumberFormatException e){
				return returnMap(StateCodeUtil.PR_PARAM_ERROR,"订单号格式错误");
			}
			
			Map<String,Object> payOrder = payOrderMapper.getPayOrderByorderNumber(orderNumber);
			if(payOrder == null){
				log.error("订单号:"+orderNumber+",未找到该订单的支付记录");
				return returnMap(StateCodeUtil.PR_ORDER_NOT_EXIST,"未找到该订单的支付记录");
			}
			log.info("查询到的订单信息：\r\n"+JSON.toJSONString(payOrder));
			
			//2.验证该支付订单是否符合退款需求
			resultMap = checkOrderStatus(payOrder,refundRequest);
			if(StateCodeUtil.PR_SUCCESS.compareToIgnoreCase(resultMap.get("code")) != 0){
				log.error("订单号:"+orderNumber+",该订单不符合退款条件");
				return resultMap;
			}
			resultMap.clear();
			
			//3.查询退款记录表,看该退款记录是否已经存在
			PayRefund payRefund = refundService.getPayRefundRecordBypayId((long)payOrder.get("payId"));
			if(payRefund != null){
				
				//验证退款记录表中，退款记录状态
				if(payRefund.getStatus() == PayRefundStatus.PROCESS_SUCCESS ){	//已退款
					Map<String, String> busineMap =updateBusineStatus(refundRequest.getOrderNumber(),StateCodeUtil.PR_SUCCESS,"退款成功!");
					if(busineMap == null){
						log.error("调用业务服务接口，失败,接口地址与端口:"+ipNumbertBusine+","+ipPortBusine);
					}
					
					log.info("订单号:"+orderNumber+",该订单已退款");
					return returnMap(StateCodeUtil.PR_REFUNDED,"退款成功!");
				}
				
				String thirdName = payRefund.getThirdName();
				if(payRefund.getStatus() == PayRefundStatus.PROCESSING){		//正在处理中，
					if(thirdName.compareToIgnoreCase(PayTypeUtil.alipay) == 0){
						//...
						log.info("退款订单使用支付宝支付，在退款处理中状态下，允许再次退款");
					}else{
						log.error("订单号:"+orderNumber+",该订单退款正在处理中，请勿重复退款");
						return returnMap(StateCodeUtil.PR_REQUEST_REPEAT,"已成功申请退款");
					}
				}

	
				if((thirdName.compareToIgnoreCase(PayTypeUtil.umpay) == 0)
						||(thirdName.compareToIgnoreCase(PayTypeUtil.kqpay) == 0)){
					//...
					log.info("U付退款，其退款流水号只1次有效，重复提交会提示反馈失败");
					String newRefundid = new SimpleDateFormat("yyMMddHHmmss").format(new Date()).toString()+UtilString.getFourDigit();
					log.info("之前退款失败的refundId:"+payRefund.getRefundId()+",重新生成的refundId："+newRefundid);
					//新refundId
					payRefund.setRefundId(newRefundid);
					
					Map<String,Object> refundInfoMap = new HashMap<String,Object>();
					refundInfoMap.put("payId", payRefund.getPayId());
					refundInfoMap.put("refundId", payRefund.getRefundId());
					refundService.updateRefundInfo(refundInfoMap);
				}
				if(thirdName.compareToIgnoreCase(PayTypeUtil.tlpay) == 0){
					//log
				}
				
				//将退款记录状态更新为"正在处理中"
				refundService.updateRefundStatus(payRefund.getRefundId(), PayRefundStatus.PROCESSING);
				
				payRefund.setPayDate(payOrder.get("payDate")==null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payOrder.get("payDate")));
				payRefund.setPayDescription(payOrder.get("payDescription")==null?"":(String)payOrder.get("payDescription"));
				log.info("该退款记录已存在，现对其再次退款");
			}else{
				
				//4.插入请求退款记录，并返回退款信息
				payOrder.put("description", refundRequest.getRemark());
				try{
				payOrder.put("bid", bId);
				}catch(Exception e){
					log.error("退款请求bid:"+refundRequest.getBid().trim()+"格式转换失败！");
				}
				payRefund = refundService.addPayRefundRecord(payOrder);
				if(payRefund == null){
					log.error("订单号:"+orderNumber+",添加该订单的退款记录异常");
					return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"添加退款记录异常");
				}
				/*---------------每次确认一条退款订单时，即从商家中扣除退款手续费-----------------*/
				/*---------------------从营收金额中扣除退款手续费-------------------------*/
				Integer sellerId = (Integer)payOrder.get("merchId");
				BigDecimal fee = refundService.generateFee((BigDecimal)payOrder.get("money"));
				Map<String,Object> feeMap = new HashMap<String,Object>();
				feeMap.put("uId", sellerId);
				feeMap.put("userType", 2);
				feeMap.put("fee", fee);
				feeMap.put("remarks",orderNumber);
				feeMap.put("description", "用户"+payOrder.get("userId")+"退款"+payRefund.getMoney()+"元，从商户扣除"+fee+"元");

				if(!PayTypeUtil.yhqpay.equals(payRefund.getThirdName())){
					resultMap = refundService.subtractRefundFee(feeMap);
					if(isRefundFail(resultMap)){
						log.error("订单号:"+orderNumber+",扣除退款手续费失败，退款失败。。。");
						throw new FailureException(1,"扣除手续费失败");
//						return resultMap;
					}
				}else{
					log.info("优惠券退款不收手续费");
				}
				resultMap.clear();

			}
			log.info("payRefund退款记录信息：\r\n"+JSON.toJSONString(payRefund));
			
			/*
			 * 收回该订单的系统赠送金额及积分 
			 */
			boolean bSuccess = refundService.recoverHandsel(refundRequest.getOrderNumber());
			if(!bSuccess){
				log.error("收回订单"+refundRequest.getOrderNumber()+"赠送的金额及积分失败");
				resultMap = returnMap(StateCodeUtil.PR_REFUND_FAIL,"收回订单赠送的金额及积分失败");
			}else{
				//5.退款 ; 第三方支付，则构建第三方退款请求、并退款,纯钱包支付，则直接退款
				resultMap = refundService.refundSubmit(payRefund,refundRequest.getOrderNumber());
				if(resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS) 
						&& resultMap.get("Msg").trim().equals("")){
					resultMap.put("Msg","退款成功!");
				}else if(resultMap.get("code").equals(StateCodeUtil.PR_THIRD_REFUNDING) 
						&& resultMap.get("Msg").trim().equals("")){
					resultMap.put("Msg","已成功申请第三方平台退款");
				} 
			}
			//6.更新退款状态
			if(RefundUtil.isRefundSuccess(resultMap)){
				refundService.updateRefundStatus(payRefund.getRefundId(),PayRefundStatus.PROCESS_SUCCESS);
			}
			else if(RefundUtil.isRefundFail(resultMap)){
				refundService.updateRefundStatus(payRefund.getRefundId(),PayRefundStatus.PROCESS_Failure);
			}
		
			//5.调用业务退款接口 退款处理中,状态不需更新
			if(!RefundUtil.isRefunding(resultMap)){
				Map<String, String> busineMap =updateBusineStatus(refundRequest.getOrderNumber(),resultMap.get("code"),resultMap.get("Msg"));
				if(busineMap == null){
					log.error("调用业务服务接口，失败,接口地址与端口:"+ipNumbertBusine+","+ipPortBusine);
				}
			}
			log.info("成功后返回的结果："+resultMap);
			return resultMap;
		}catch (FailureException e){
			throw new FailureException(1,"扣除手续费失败");
		
		}catch (Exception e){
			log.error("退款失败",e);
			
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"系统异常");
		}
	}
	

	/**
	 * 验证该订单是否处于可退款状态
	 * @param payOrder 
	 * @param refundRequest 
	 * @return
	 */
	public Map<String,String> checkOrderStatus(Map<String,Object> payOrder,RefundRequest refundRequest){
		//1.查询到对应的支付订单
		if(payOrder == null || payOrder.get("payId")==null){
			log.error("未找到该订单的支付记录或在支付记录中支付ID为空");
			return returnMap(StateCodeUtil.PR_ORDER_NOT_EXIST,"未找到该订单的支付记录或在支付记录中支付ID为空");
		}

		//2.指定类型支付订单不允许退款
		if(payOrder.get("payType")==null || (int)payOrder.get("payType")==RECHARGE_ORDER){
			log.error("该支付订单类型不允许退款");
			return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"该支付订单类型不允许退款");
		}
		
		//3.未支付完成的订单不允许退款
//		if(payOrder.get("payStatus")==null || (int)payOrder.get("payStatus")!=2){
//			log.error("该支付订单未完成支付，不允许退款");
//			return returnMap(StateCodeUtil.PR_ORDER_NOTPAY,"该支付订单未完成支付，不允许退款");
//		}
		
		//4.退款总金额不大于订单总金额
		BigDecimal payMoney = new BigDecimal(Double.toString(refundRequest.getMoney()));
		if(payMoney.compareTo((BigDecimal)payOrder.get("money")) != 0){
			log.error("退款金额错误,请求金额："+refundRequest.getMoney()+"实际金额："+payOrder.get("money"));
			return returnMap(StateCodeUtil.PR_MONEY_ERROR,"退款金额错误");
		}
		
		//5.U付订单不支持T+0退款
		String thirdName = (String) payOrder.get("thirdName");
		if(thirdName.equals(PayTypeUtil.umpay)){
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String thirdPayDate = (String) payOrder.get("thirdId");
			thirdPayDate = thirdPayDate.substring(0, 6);
			if(today.equals(thirdPayDate)){
				log.error("U付订单：当天支付订单不可退款");
				return returnMap(StateCodeUtil.PR_ORDER_DISENABLE,"U付订单：当天支付订单不可退款");
			}
		}
		
		return returnMap(StateCodeUtil.PR_SUCCESS,"");
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
	
	/*
	 * 设置调用的服务地址及端口
	 */
	public Map<String, String> TSocket(Map<String, String> paramMap){
		log.info("调用业务系统接口OrderService.refundOrderInfo("+paramMap+")");
		
		Map<String, String> result = null;
		TTransport transport = null;
		try {
		    transport = new TSocket(ipNumbertBusine, ipPortBusine);
		    TFramedTransport frame = new TFramedTransport(transport);
		    // 设置传输协议为 TBinaryProtocol
		    TProtocol protocol = new TBinaryProtocol(frame);
		    TMultiplexedProtocol ManagerProtocol = new TMultiplexedProtocol(
			    protocol, "OrderService");
		    OrderService.Client client = new Client(ManagerProtocol);
		    // 打开端口,开始调用
		    transport.open();

		    try {
		    	result = client.refundOrderInfo(paramMap);

		    } catch (TException e) {
		    	log.error("设置调用的服务地址及端口异常，", e);
		    }
		} catch (TTransportException e) {
		    log.error("设置调用的服务地址及端口异常", e);
		} catch (Exception e) {
		    log.error("程序退款调用业务服务异常", e);
		}finally {
		    transport.close();
		}
		return result;
//		Map<String, String> result = null;
//		try {
//			OrderService.Client busineClient = (OrderService.Client)this.busineProxy.getClient();
//			result = busineClient.refundOrderInfo(paramMap);
//			System.out.println("运行结果:"+result);
//		} catch (Exception e){
//
//		} 
//		finally{
//			try{
//				if(busineProxy != null){
//					busineProxy.returnCon();
//				}
//			} catch (Exception e){
//			} 
//			finally{
//				
//			}
//		}
//		
//		return result;
		
	}
	
	/**
	 * 准备参数，并调用业务服务接口
	 * @param bId
	 * @param code
	 * @param msg
	 * @return
	 */
	public Map<String, String> updateBusineStatus(String bId,String code,String msg){
		log.info("调用业务服务更新退款状态接口");
//		boolean test = true;
//		if(test){
//		return null;
//		}
		/*
		 * bid     订单号
		 * refundStatus =9      平台退款成功
		 *              =10   平台退款失败
		 * remarks 失败原因            
		 * */
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("bid", bId);
		if(code.equals(StateCodeUtil.PR_SUCCESS)){	//退款成功
			paramMap.put("refundStatus", 9+"");
			paramMap.put("remarks", "");
		}
		else{	//退款失败
			paramMap.put("refundStatus", 10+"");
			paramMap.put("remarks", msg);
		}
		return TSocket(paramMap);
	}
	
	/**
	 * 是否退款失败
	 * @param map
	 * @return
	 */
	public boolean isRefundFail(Map<String,String> map){
		if(isRefundSuccess(map)){
			return false;
		}
		else if(isRefunding(map)){
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * 是否还在退款中
	 * @param map
	 * @return
	 */
	public boolean isRefunding(Map<String,String> map){
		String resultCode = map.get("code");
		if(resultCode.equals(StateCodeUtil.PR_REQUEST_THIRD_SUCCESS) 		
				|| resultCode.equals(StateCodeUtil.PR_THIRD_REFUNDING)
				|| resultCode.equals(StateCodeUtil.PR_REQUEST_REPEAT)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 是否退款成功
	 * @param map
	 * @return
	 */
	public boolean isRefundSuccess(Map<String,String> map){
		String resultCode = map.get("code");
		if(resultCode.equals(StateCodeUtil.PR_SUCCESS) || resultCode.equals(StateCodeUtil.PR_REFUNDED)){
			return true;
		}
		else{
			return false;
		}
	}

}
