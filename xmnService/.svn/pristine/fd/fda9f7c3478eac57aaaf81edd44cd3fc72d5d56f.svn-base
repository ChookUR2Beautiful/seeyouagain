package com.xmniao.xmn.core.order.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.order.entity.FreshOrder;
import com.xmniao.xmn.core.order.entity.IntegralOrder;
import com.xmniao.xmn.core.order.entity.OrderExpress;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 项目描述：XmnService
 * API描述：订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */
public interface FreshOrderProcessDao {
	/**
	 * 订单操作VIEW  主订单详情
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public FreshOrder queryOrderView(Map<Object, Object>  params);
	
	
	/**
	 * 订单操作VIEW  子订单详情 用于拆分后订单详情查询
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public FreshOrder queryOrderSubView(Map<Object, Object>  params);
	
	/**
	 * 订单操作  取消订单操作
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public int editOrderForClose(Map<Object, Object>  params);
	
	/**
	 * 订单操作  查询订单快递 公司 及 单号
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public OrderExpress queryOrderExpressview(Map<Object, Object>  params);
	
	/**
	 * 订单操作 积分兑换订单VIEW
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public IntegralOrder queryIntegralOrderList(Map<Object, Object> map);
	
	/**
	 * 订单操作 积分兑换订单 查找商户logo
	 * @author yhl
	 * @param 订单bid 
	 * @return Object
	 * */
	@DataSource("joint")
	public Map<Object, Object> querySellerLogoUrl(Long sellerid);
	
	/**
	 * 订单操作   有无使用优惠券记录
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public Map<Object, Object> queryOrderCouponView(Map<Object, Object>  params);
	
	/**
	 * 订单操作VIEW  取消订单 回退优惠券
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public int editOrderCouponForClose(Map<Object, Object> map);
	
	
	/**
	 * 订单操作VIEW  取消订单 回退优惠券
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public int queryBillOrderCountByOrderNo(String bid);
	
	/**
	 * 订单操作 积分兑换订单VIEW
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryFreshProductListByIntegral(Map<Object, Object> map);
	
	
	

}
