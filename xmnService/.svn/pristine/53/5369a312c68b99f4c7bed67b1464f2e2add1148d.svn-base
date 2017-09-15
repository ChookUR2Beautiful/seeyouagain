package com.xmniao.xmn.core.market.service.pay;

import java.util.List;

import com.xmniao.xmn.core.common.request.market.pay.ChangePayTypeRequest;
import com.xmniao.xmn.core.common.request.market.pay.CreateOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.DirectOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.GenerateOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderInfoRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderNoRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderSettlementRequest;
import com.xmniao.xmn.core.common.request.market.pay.PayOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.SettlementRequest;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;

public interface MarketOrderService {

	/**
	 * 创建订单
	 * @param baseRequest
	 * @return
	 */
	public Object createOrder(CreateOrderRequest createOrderRequest);
	
	/**
	 * 直接支付
	 * @param cartAddRequest
	 * @return
	 */
	public Object directOrder(DirectOrderRequest directOrderRequest);
	
	/**
	 * 结算
	 * @param baseRequest
	 * @return
	 */
	public Object settlement(SettlementRequest settlementRequest);
	
	/**
	 * 支付
	 * @param createOrderRequest
	 * @return
	 */
	public Object payOrder(PayOrderRequest payOrderRequest);
	
	/**
	 * 取消订单
	 * @return
	 */
	public Object cancelOrder(OrderNoRequest orderNoRequest);
	
	/**
	 * 提醒商家发货
	 * @param orderInfoRequest
	 * @return
	 */
	public Object remindExpress(OrderInfoRequest orderInfoRequest);
	
	/**
	 * 切换支付方式
	 * @param changePayTypeRequest
	 * @return
	 */
	public Object changePayType(ChangePayTypeRequest changePayTypeRequest);
	
	/**
	 * 查询订单支付状态
	 * @param changePayTypeRequest
	 * @return
	 */
	public Object queryOrderStatus(ChangePayTypeRequest changePayTypeRequest);
	
	/**
	 * 确认收货
	 * @return
	 */
	public Object confirmOrder(OrderNoRequest orderNoRequest);
	
	/**
	 * 获取用户购买的商品信息，并且初始化商品信息
	 * @param uid
	 * @param cartIds
	 * @param isDelete
	 * @param buyType
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(String uid,List<String> cartIds,boolean isDelete,Integer buyType);
	
	/**
	 * 判断是否包邮
	 * @param tid
	 * @param areaId
	 * @param totalWeight
	 * @param totalPrice
	 * @return
	 */
	public boolean isFree(Integer tid,Integer areaId,Double totalWeight,Double totalPrice);
	
	/**
	 * 计算运费
	 * @param tid
	 * @param totalWeight
	 * @return
	 */
	public Double getPostPrice(Integer tid,Double totalWeight,Integer areaid,String areaname);
	
	/**
	 * 
	* @Title: orderSettlement 
	* @Description:订单结算(新) 
	* @param:@param orderSettlementRequest
	* @param:@return
	* @return:Object返回类型 
	* @throws
	 */
	public Object orderSettlement(OrderSettlementRequest orderSettlementRequest);
	
	
	public List<ProductInfo> getProductInfoList(OrderSettlementRequest orderSettlementRequest,String uid);
	
	/**
	 * 
	* @Title: generateOrder 
	* @Description:创建订单(新)
	* @param:@return
	* @return:Object返回类型 
	* @throws
	 */
	public Object generateOrder(GenerateOrderRequest generateOrderRequest);
}
