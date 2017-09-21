package com.xmniao.dao.live;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface CouponOrderDao {
	
	/**
	 * 根据订单号获得粉丝券订单信息
	 * @param orderNo
	 * @return
	 */
	Map<String,Object> getCouponOrder(String orderNo);
	
	/**
	 * 更新订单状态
	 * @param paraMap
	 * @return
	 */
	Integer updateOrderStatus(Map<String,String> paraMap);
	
	/**
	 * 获取预售抵用券信息
	 * @param uid
	 * @param cdid
	 * @return
	 */
	Map<String,Object> getCouponDetail(Map<String,Object> paraMap);
	
	/**
	 * 更新预售抵用券使用状态
	 * @param paraMap
	 * @return
	 */
	Integer updateCouponDetailStatus(@Param("cdid")String cdid,@Param("status")Integer status,@Param("date")String date);
	
	Map<String,Object> couponAnchorRef(String id);
	
	List<Map<String,Object>> couponIssueRef(String cid);
	
	Map<String,Object> getCouponMsg(String cid);
	
	/**
	 * 批量插入优惠券
	 * @param list
	 * @return
	 */
	Integer insertCoupon(List<Map<String,Object>> list);
	
	/**
	 * 更新粉丝券库存
	 * @param num
	 * @return
	 */
	Integer updateStock(Map<String,Object> paraMap);
	
	/**
	 * 获取商家还在没过期的粉丝券配置数量
	 * @Title: getSellerFansCouponCount 
	 * @Description:
	 */
	long getSellerFansCouponCount(Map<String,Object> paraMap);
	
	/**
	 * 获取预售抵用券信息
	 * @param uid
	 * @param cdid
	 * @return
	 */
	List<Map<String,Object>> getCouponDetailByRelation(Map<String,Object> paraMap);
	
	/**
	 * 更新预售抵用券使用状态
	 * @param paraMap
	 * @return
	 */
	Integer updateCouponDetailStatusByRelation(@Param("ids") String[] ids, @Param("status")Integer status, @Param("date")String date);
	
}
