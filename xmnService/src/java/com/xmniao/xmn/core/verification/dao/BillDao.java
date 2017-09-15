package com.xmniao.xmn.core.verification.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.RefundRequest;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.verification.entity.VerifyResponseBean;

/**
 * bill DAO
 * @author Administrator
 *
 */
@Repository
public interface BillDao {

	/**
	 * 获取订单列表
	 * @return
	 * 
	 */
	@DataSource("joint")  	//业务数据库订单表
    public List<VerifyResponseBean> list(); 
	
	
	/**
	 * 更加订单号获取订单数据
	 * @return
	 */
	@DataSource("joint")  	//业务数据库订单表
	public VerifyResponseBean getBillById(Long bid);

	/**
	 * 
	* @Title: queryTotalflow
	* @Description:查询总流水 
	* @return double    总流水
	* @throws
	 */
	@DataSource("joint")
	public double queryTotalflow(Integer sellerid);

	/**
	 * 
	* @Title: queryMonthflow
	* @Description: 查询本月
	* @return double    本月流水
	* @throws
	 */
	@DataSource("joint")
	public double queryMonthflow(Integer sellerid);
	
	/**
	 * 
	* @Title: sumSellerOrder
	* @Description: 统计商户所有订单消费额
	* @return Double    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Double sumSellerOrder(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryAllSellersFlow
	* @Description: 查询所有签约商家的流水
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryAllSellersFlow(Map<Object,Object> map);


	/**
	 * 查询所有签约商家的流水分成
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryAllSellersFlowPay(Map<Object,Object> map);

	
	
	/**
	* @Title: queryMonthflowBySellerids
	* @Description: 根据用户ID查询本月
	* @param List<Integer> sellerids 商铺ID列表
	* @return double 返回类型
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("joint")
	public double queryMonthflowBySellerids(List<Integer> sellerids);

	/**
	 * 
	* @Title: queryFlowAmount
	* @Description: 查询总交易流水金额
	* @return Double    返回类型
	* @throws
	 */
	
	public Double queryFlowAmount(Integer xmer_uid);

	/**
	 * 
	* @Title: updateStatusByBid
	* @Description: 修改积分订单状态
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	public void updateStatusByBid(Long bid);

	@DataSource("joint")
	public Integer getBillBargainById(RefundRequest request);

	/**
	 * 
	* @Title: modifyCommentStatus
	* @Description: 修改订单评价状态为已评价
	* @return int    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public int modifyCommentStatus(Long bid);

	
	/**
	 * 
	* @Title: queryAllMoneyByUid
	* @Description: 查询月消费金额
	* @return Object    返回类型
	* @throws
	 */
	@DataSource("joint")
	public double queryBillAllMoney(Map<String, Object> paramMap);

	@DataSource("joint")
	public double queryBillBargainAllMoney(Map<String, Object> paramMap);
	
	/**
	 * 根据用户id查询所有用户消费过的商铺id
	 * @param uid
	 * @return sellerid
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryBillByUid(Integer uid);
	
	/**
	 * 统计商铺的所有订单数量
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	int sumAllOrdersBySellerId(Integer sellerid);
	
	@DataSource("joint")
	public List<Map<String, Object>> queryUidBySellerid(
			SelleridPageRequest request);
	
	
	/**
	 * 描述：插入订单信息
	 * @param map
	 * @return integer
	 * */
	@DataSource("joint")
	public int insertBillOrderInfo(Map<Object, Object> map);
	
	
	/**
	 * 
	 * @Description: 查询商家消费人数
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	@DataSource("joint")
	public int consumeCount(int sellerid);
	/**
	 * 
	* @Title: queryCustomerSellers
	* @Description: 查询消费过的店铺信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCustomerSellers(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: querySameConsumerSellerCount
	* @Description: 查询去过相同消费的店铺数量
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer querySameConsumerSellerCount(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryConsumerSellerCount
	* @Description: 查询去过的店铺总数
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryConsumerSellerCount(Integer uid);
	
	/**
	 * 
	* @Title: queryCouponOrderCount
	* @Description: 参加过的直播活动总数
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Integer queryCouponOrderCount(Integer uid);

	/**
	 * 
	 * @Description: 查询用户在商家消费的数量
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	@DataSource("joint")
	public int billCountBySelleridAndUid(@Param("sellerid")int sellerid, @Param("uid")int uid);

	/**
	 * 
	* @Title: querySameConsumerSellerListCount
	* @Description: 批量查询去过相同店铺消费的数量
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> querySameConsumerSellerListCount(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryFavoriteSellerByUid
	* @Description: 查询最喜欢消费的店铺信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryFavoriteSellerByUid(Integer uid);

	/**
	 * 
	* @Title: queryCustomerTimeByUid
	* @Description: 查询最喜欢外出消费时间段
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCustomerTimeByUid(Integer uid);

	/**
	 * 
	* @Title: queryCustomerWeekByUid
	* @Description: 查询最喜欢外出的消费时间周
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryCustomerWeekByUid(Integer uid);
	
	/**
	 * 
	* 描述: 获取美食订单基本信息
	* @param  Map<Object,Object>
	* @return Map<Object,Object>    返回类型
	* 
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryBillInfoByOrderNo(Map<Object, Object> paramMap);
	
	/**
	* 描述: 获取个人的美食订单列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryBillListByUid(Map<Object, Object> paramMap);
	
	/**
	* 描述: 修改各种粉丝券的使用状态
	* @return int    返回类型
	* @throws
	 */
	@DataSource("joint")
	public int updateOrderCouponStatus(Map<Object, Object> map);
	
	/**
	* 描述: 将使用的优惠券信息插入到 使用记录表
	* @return int    返回类型
	* @throws
	 */
	@DataSource("joint")
	public int insertBillCouponRecord(Map<Object, Object> map);
	
	/**
	 * 通过商铺id查询所有去过这家商铺消费过的用户id
	 * @param list
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryConsumerbySelleridList(List<Integer> list);
	
	@DataSource("joint")
	List<Integer> querySellersByUid(Map<Object,Object> map);
	
	
	/**
	 * 通过用户id和商铺id查询用户是否在该商铺消费过
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	Map<Object,Object> queryBillBySellerIdAndUid(Map<Object,Object> map);
	
	@DataSource("joint")
	List<Map<Object,Object>> findAll();
	
	@DataSource("joint")
	List<Map<Object,Object>> findAllSellers(String uid);
	
	@DataSource("joint")
	String queryZdateByUidAndSellerId(@Param("uid")String uid,@Param("sellerid")String sellerid);
	
	/**
	 * 查询商家每日限额
	 * */
	@DataSource("joint")
	public Map<Object,Object> queryDayOrderAmountBySellerId(Long sellerId);
	
	
	/**
	 * 查询商家总限额
	 * */
	@DataSource("joint")
	public Map<Object,Object> queryTotalOrderAmountBySellerId(Long sellerId);

	/**
	 * 
	* @Title: queryBillCountBySellerIdsAndUid
	* @Description: 批量查询用户是否在这些商家消费过
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryBillCountBySellerIdsAndUid(Map<Object, Object> paramMap);


	/**
	 * 通过店铺id，批量统计店铺流水
	 * @param paramMap
	 * @return
	 */
	@DataSource("joint")
	Double sumSellerOrderBySellerIds(Map<Object, Object> paramMap);

	/**
	 * 批量查询订单信息
	 * @param paramMap
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryBillByBids(Map<Object, Object> paramMap);
}
