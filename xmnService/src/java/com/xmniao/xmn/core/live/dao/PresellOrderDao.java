/**
 * 2016年10月24日 上午10:08:56
 */
package com.xmniao.xmn.core.live.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService
 * @类名称：PresellOrderDao
 * @类描述：预售订单dao
 * @创建人： yeyu
 * @创建时间 2016年10月24日 上午10:08:56
 * @version
 */
@Repository
public interface PresellOrderDao {
	/**
	 * 
	* @方法名称: addPresellOrder
	* @描述: 增加预售订单接口
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int addPresellOrder(Map<Object,Object> map);
	
	/**
	 * 
	* @方法名称: modiflyPreSellOrder
	* @描述: 取消订单接口
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int modifyPreSellOrder(Map<Object,Object> map);
	/**
	 * 
	* @方法名称: lockAndUnlockFocusTicket
	* @描述: 锁定或解除或已使用锁定券
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int lockAndUnlockCouponTicket(Map<Object,Object> map);
	
	/**
	 * 
	* @方法名称: modifyFocusTicketNums
	* @描述: 修改粉丝券数量
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int modifyFocusTicketNums(Map<Object,Object> map);
	/**
	 * 
	* @方法名称: giveUseTicket
	* @描述: 赠送抵用券
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int giveUseTicket(Map<Object,Object> map);
	/**
	 * 
	* @方法名称: queryCouponByCid
	* @描述: 根据cid获取粉丝券基本信息
	* @返回类型 Map<Object,Object>
	* @创建时间 2016年10月24日
	* @param cid
	* @return
	 */
	@DataSource("joint")
	public Map<Object,Object> queryCouponByCid(Integer cid);
	
	/**
	 * 
	* @方法名称: queryCouponDetailByCdid
	* @描述: 获取抵用券信息
	* @返回类型 Map<Object,Object>
	* @创建时间 2016年10月24日
	* @param cdid
	* @return
	 */
	@DataSource("joint")
	public Map<Object,Object> queryCouponDetailByCdid(Integer cdid);
	
	/**
	 * 
	* @方法名称: modifyfansCoupon
	* @描述: 更新粉丝券数量
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int modifyfansCoupon(Map<Object,Object> map);
	/**
	 * 
	* @方法名称: queryFansCouponByOther
	* @描述: 获取粉丝券总数
	* @返回类型 Map<Object,Object>
	* @创建时间 2016年10月24日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public Map<Object,Object> queryFansCouponByOther(Map<Object,Object> map);
}
