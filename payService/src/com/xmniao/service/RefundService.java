package com.xmniao.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xmniao.entity.PayRefund;

/**
 * 定义退款操作的常用接口
 * @author ChenBo
 *
 */
public interface RefundService {
	
	/**
	 * 添加寻蜜鸟订单退款记录    
	 * @param payOrder 订单信息
	 * @return
	 */
	PayRefund addPayRefundRecord(Map<String, Object> payOrder);
	
	/**
	 * 添加生鲜订单退款记录    
	 * @param payOrder 订单信息
	 * @return
	 */
	PayRefund addFreshRefundRecord(Map<String, Object> payOrder);
	/**
	 * 退款 ; 第三方支付，则构建第三方支付请求数据,纯钱包支付，则直接退款
	 * @param payRefund 退款信息
	 * @param orderNumber 原始订单编号
	 * @return
	 */
	Map<String,String> refundSubmit(PayRefund payRefund,String orderNumber);
	/**
	 * 退款 ; 第三方支付，则构建第三方支付请求数据,纯钱包支付，则直接退款
	 * @param payRefund 退款信息
	 * @param orderNumber 原始订单编号
	 * @param serviceType 0-寻密鸟订单，1-生鲜订单
	 * @return
	 */
	Map<String,String> refundSubmit(PayRefund payRefund,String orderNumber,int serviceType);
	/**
	 * 计算手续费
	 * @param totalMoney 总金额
	 * @return 计算得出的手续费
	 */
	BigDecimal generateFee(BigDecimal totalMoney);
	
	
	/**
	 * 扣取手续费
	 * @param totelMoney 消息总金额
	 * @param uId  用户/商家/合作商id
	 * @param type 1-用户，2-商家，3，合作商
	 * @param reamrks 备注
	 * @return
	 */
	Map<String,String> subtractRefundFee(BigDecimal refundFee,long uId,int type,String remarks);
	
	
	/**
	 * 扣取手续费
	 * @param feeMap
	 *  totelMoney 消息总金额
	 *  uId  用户/商家/合作商id
	 *  type 1-用户，2-商家，3，合作商
	 *  reamrks 备注
	 * @return
	 */
	Map<String,String> subtractRefundFee(Map<String,Object> feeMap);
	
	/**
	 * 查询退款记录
	 * @param map 可查条件：sdate(yyyy-MM-dd),status(状态 )
	 * @return
	 */
	List<Map<String,String>> payRefundList(Map<String, Object> map);
	
	/**
	 * 返回一个Map数据
	 * @param code 状态代码
	 * @param msg 错误信息
	 * @return
	 */
	Map<String,String> returnMap(String code,String msg);
	
	/**
	 * 返回一个Map数据
	 * @param code 状态代码
	 * @param msg 错误信息
	 * @param response 正确时的附带返回信息
	 * @return
	 */
	Map<String,String> returnMap(String code,String msg,String response);
	
	/**
	 * 是否退款失败
	 * @param map
	 * @return
	 */
	//boolean isRefundFail(Map<String, String> map);
	
	/**
	 * 是否还在退款中
	 * @param map
	 * @return
	 */
	//boolean isRefunding(Map<String, String> map);
	
	/**
	 * 是否退款成功
	 * @param map
	 * @return
	 */
	//boolean isRefundSuccess(Map<String, String> map);
	
	/**
	 * 更新钱包
	 * @param payRefund 退款信息
	 * @return
	 */
	Map<String,String> updateWallet(PayRefund payRefund) throws Exception;
	
	/**
	 * 退 钱包支付部分的钱到 钱包
	 * @param payRefund
	 * @return
	 */
	Map<String,String> refundWalletMoney(PayRefund payRefund);
	
	
	/**
	 * 更新退款订单状态
	 * @param refundId 退款流水号
	 * @param status   退款状态
	 * @return
	 */
	void updateRefundStatus(String refundId,int status);
	
	void updateRefundStatus(String refundId,int status,int serivceType);
	
	/**
	 * 根据pay_id查找退款记录
	 * @param payId 支付Id
	 * @return
	 */
	PayRefund getPayRefundRecordBypayId(long payId);
	
	PayRefund getFreshRefundRecordBypayId(long payId);

	PayRefund getPayRefundByRefundId(String refundId,int serviceType);
	
	/**
	 * 修改退款记录表中的refund_id字段
	 * @param updateMap
	 */
	void updateRefundInfo(Map<String,Object> updateMap);
	
	/**
	 * 运用第三方支付的查询接口，查询该订单的退款状态
	 * @param refundId
	 * @return
	 */
	Map<String,String> queryRefund(String refundId,String payType);
	
	/**
	 * 订单是否允许退钱包金额/即是否walletRecord中存在rtype=5和14的记录
	 * @param orderNumber
	 * @return
	 */
	int getAccordRefundWallet(String orderNumber);
	
	/**
	 * 更新业务库的订单状态
	 */
	Map<String,String> updateFreshBusineStatus(String bId,String code,String msg);
	
	Map<String,Object> getPayOrderBypayId(String payId,int serviceType);
	
	/**
	 * 收回订单赠送的金额及积分
	 */
	boolean recoverHandsel(String orderNumber);
}
