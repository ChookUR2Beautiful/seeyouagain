package com.xmniao.xmn.core.order.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.springframework.stereotype.Repository;

/**
 * 项目描述：XmnService
 * API描述：订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */
@Repository
public interface CouponFansOrderDao {
	/**
	 * 查询订单详情
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public Map<Object, Object> queryCouponFansOrderView(Map<Object, Object>  params);
	
	/**
	 * 查询粉丝券订单列表
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCouponFansOrderList(Map<Object, Object>  params);
	
	/**
	 * 查询粉丝券订单对应粉丝券详情
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public CouponInfo queryCouponDetailInfoByBid(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryCheckCouponFansOrder
	* @Description: 批量查询这些用户是否购买过粉丝卷
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCheckCouponFansOrder(Map<Object, Object> map);
	
	/**
	 * 查询 我购买过的粉丝券列表 且主播正在直播的
	 * 
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCouponFansOrderAnchorUidList(Map<Object, Object>  params);
	
	
	/**
	 * 查询 正在直播的记录  且是有预售的列表 且主播正在直播的 根据人气排序
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryliveingFansRecordList(Map<Object, Object>  params);
	
	/**
	 * 根据订单编号批量查询粉丝券订单信息
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryFansCouponInfoByOrderNoList(List<String> list);
	
	/**
	 * 查询 主播共卖出了多少位粉丝券
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public int queryFansCouponSeatsCount(Map<String, String> map);
	
	/**
	 * 查询 主播共卖出了多少位粉丝券 或  
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public int queryAnchorFansSellSeatsCount(Map<String, String> map);
	
	
}
