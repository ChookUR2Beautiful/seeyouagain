package com.xmniao.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.MallOrderBean;
import com.xmniao.domain.order.MallSubOrderBean;
import com.xmniao.domain.order.MallOrderProductBean;

/***
 * 积分商城订单DAO
 * @author Jian
 */

public interface MallOrderDao {
	public MallOrderBean getByBid(@Param("bid")Long bid);
	
	public int updateForPayComplete(Map<String,Object> map);
	
	public int addSubOrder(MallSubOrderBean subOrder);
	
	public List<MallOrderProductBean> getOrderProductByBid(@Param("bid")Long bid);
	
	public int setSubBidForOrderProduct(@Param("subBid")String subBid,@Param("id")Long id);
	
	public Map<String,Object> getCouponDetail(String cdid);
	
	public Long updateCouponStatus(Map<String,String> paraMap);
	
	public List<MallOrderBean> scanNoPaymentOrder(Map<String,Object> paraMap);
	
	Long updateFreshRepertory(Map<String,Object> paraMap);//非活动商品
	
	Long updateFreshProductInfo(Map<String,Object> paraMap);
	
	Long updateRepertory(Map<String,Object> paraMap);//活动商品
	
	Long updateFreshActivityProduct(Map<String,Object> paraMap);
	
	
	/**
	 * 根据订单编获取券信息
	 * @param cdid
	 * @return
	 */
	public List<Map<String,Object>> getCouponDetailByBid(Long bid);  //@Param("bid")
	
	public Long updateCouponStatusByBid(Map<String, Object> paraMap);
}
