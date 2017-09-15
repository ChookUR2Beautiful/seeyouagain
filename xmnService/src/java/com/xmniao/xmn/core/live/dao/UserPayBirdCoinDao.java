/**
 * 2016年8月17日 下午2:20:06
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LivePayOrderInfo;
import com.xmniao.xmn.core.live.entity.LiveRechargeComboInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：UserPayBirdCoinDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月17日 下午2:20:06
 * @version
 */
@Repository
public interface UserPayBirdCoinDao {

	/**
	 * 
	* @Title: addUserPayBirdCoin
	* @Description: 用户购买鸟币记录
	* @return Integer
	 */
	@DataSource("joint")
	public Integer addUserPayBirdCoin(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: getRechargeComboById
	* @Description: 查询直播鸟币套餐
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> getRechargeComboById(Long id);
	
	/**
	 * 
	* @Title: queryUserPayBirdList
	* @Description: 查询用户充值记录
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryUserPayBirdList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: updatUserPayBirdCoin
	* @Description: 支付成功修改
	* @return Integer
	 */
	@DataSource("joint")
	public Integer updatUserPayBirdCoin(Map<Object,Object> map);
	
	/**
	* 描述：获取购买鸟币充值 套餐信息  区分 IOS  Android
	* @Title: updatUserPayBirdCoin
	* @param String systemType 操作系统类型
	* @return List<LiveRechargeComboInfo>
	 */
	@DataSource("joint")
	public List<LiveRechargeComboInfo> queryBirdCoinComboList(Map<Object,Object> map);
	
	/**
	* 描述：获取购买鸟币充值 IOS 内购对应的product_id  
	* @Title: queryLiveRechargeComboInfo
	* @param String product_id内购Id
	* @return Map<Object, Object>
	 */
	@DataSource("joint")
	public LiveRechargeComboInfo queryLiveRechargeComboInfo(Map<Object,Object> map);
	
	/**
	* 描述：根据订单编号获取订单信息
	* @Title: queryLivePayOrderInfoByOrderNo
	* @param String product_id内购Id
	* @return Map<Object, Object>
	 */
	@DataSource("joint")
	public LivePayOrderInfo queryLivePayOrderInfoByOrderNo(String orderNo);
	
	
	/**
	* 描述：根据订单编号修改订单状态
	* @Title: modifyLivePayOrderInfoByOrderNo
	* @param String product_id内购Id
	* @return Map<Object, Object>
	 */
	@DataSource("joint")
	public int modifyLivePayOrderInfoByOrderNo(Map<Object, Object> map);
	
	/**
	 * 描述 ：根据IOS传过来的字符流查询是否有订单被验证过
	 * @param Map<Object, Object> map
	 * @return int
	 * */
	@DataSource("joint")
	public int queryLivePayOrderInfoByReciptStr(Map<Object, Object> map);
	
	/**
	 * 描述 ：根据UID 查询用户最大的购买鸟币记录
	 * @param Map<Object, Object> map
	 * @return int
	 * */
	@DataSource("joint")
	public Map<Object, Object> queryBirdCoinBuyRecords(Map<Object, Object> comboMap);

	/**
	 * 根据套餐id数组，查询商家是否有 鸟粉专享卡套餐
	 * @param rechargeComboIds
	 * @return 
	 */
	@DataSource("joint")
	public Integer queryDebitcardRechargeComboListByIds(String[] rechargeComboIds);
	
	
}
