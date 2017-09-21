package com.xmniao.dao.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.ActivitypPrizeBean;
import com.xmniao.domain.order.AdjustApplyBean;
import com.xmniao.domain.order.BillBean;
import com.xmniao.domain.order.CouponRelationBean;
import com.xmniao.domain.order.JointDayCensusBean;
import com.xmniao.domain.order.ModifyOrderInfoBean;
import com.xmniao.domain.order.OrdRecordBean;
import com.xmniao.domain.order.RefundOrdRecordBean;
import com.xmniao.domain.order.SellerDayCensusBean;
import com.xmniao.domain.push.PushSysMsgBean;
import com.xmniao.domain.push.PushtSysUserMsgBean;


/**
 * 订单服务模块DAO层接口类
 * @author  LiBingBing
 * @version  [版本号, 2014年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface OrderServiceDao
{
    
    /**
     * 更新订单信息接口
     * @param request [请求参数]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int modifyOrderInfo(ModifyOrderInfoBean request);
    
    /**
     * 订单处理记录接口
     * @param reqBean [请求参数实体Bean类]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertBillRecord(OrdRecordBean reqBean);
	
	/**
     * 分账系统订单查询
     * @param id
     * @return
     */
    public Map<String, Object> getOrderInfo(Long bid);
    
    /**
     * 更新订单流程
     * @param param [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifyOrderProcess(Map<String,String> param);
    
    /**
     * 根据订单编号查询订单的所有信息
     * @param bid [订单编号]
     * @return Map<String,String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> selectBillAll(String bid);
    
    /**
     * 根据订单的用户ID查询是否是首单的订单数
     * @param uid [订单中的用户ID]
     * @return int [返回是否为首单的订单记录数]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String queryOrderCountByUid(String uid);
    
    /**
     * 批量查询订单信息
     * @param bidList [订单号LIST集合]
     * @return List<Map<String,Object>> [返回订单信息集合]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, Object>> batchQueryBillAll(List<String> bidList);
    
    /**
     * 根据订单号验证订单是否存在
     * @param bid [订单号]
     * @return int [返回查询的总记录数]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int valideBillByBid(long bid);
    
    /**
     * 获取所属合作商和消费合作商的业务员ID
     * @param paramMap [请求参数]
     * @return Map<String,String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> selectSalesmanId(Map<String, Object> paramMap);
    
    /**
     * 更新订单佣金
     * @param paramMap [请求参数]
     * @return Map<String,String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifyCommission(Map<String, String> paramMap);
    
    /**
     * 获取是否是总部帮忙签约
     * @param paraMap [请求参数]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,Object> selectGiveInfo(Map<String, Object> paraMap);
    
    /**
     * 更新向蜜客类型接口
     * @param paraMap [请求参数]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifyMikeType(String bid);
    
    /**
     * 获取合作商员工的分账比例
     * @param paramMap [请求参数]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> selectPercentageInfo(Map<String, Object> paraMap);
    
    /**
     * 获取合作商员工的分账比例
     * @param jointId [合作商ID]
     * @return String [返回合作商员工分账比例]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String queryPercentageInfo(String jointId);
    
    /**
     * 增加合作商员工提成记录
     * @param paramMap [t_take_record表中的所有参数]
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void addPercentageRecord(Map<String, Object> paraMap);
    
    /**
     * 订单退款更新状态
     * @param paraMap [请求参数]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int refundOrderInfo(Map<String,String> paraMap);
    
    /**
     * 获取前一天商户订单日统计的数据
     * @return List<SellerDayCensusBean> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int querySellerStatistics(String censusTime);
    
    /**
     * 查询前一天所有已支付过的商户
     * @return List<Map<String,Integer>> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String,Integer>> querySellerAndGenuseller(Map<String,String> reqMap);
    
    /**
     * 查询商户信息
     * @param sellerid [商家ID]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,Object> querySellerInfos(String sellerid);
    
    /**
     * 查询合作商信息
     * @param jointid [合作商ID]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String queryJointInfos(String jointid);
    
    /**
     * 获取商户日流水总额和日返利总额
     * @param sellerid [商家ID]
     * @return [参数说明]
     * @return Map<String,BigDecimal> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,BigDecimal> querySellerDayCensus(Map<String,String> reqMap);
    
    /**
     * 获取已支付的订单总数
     * @param sellerId [商家ID]
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int queryOrderTotalInfo(Map<String,String> reqMap);
   
    /**
     * 获取商户的日分账总额和日分账订单数
     * @param sellerId [商家ID]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,Object> queryYLedgerInfo(Map<String,String> reqMap);
    
    /**
     * 获取商户的未分账订单数
     * @param sellerId [商家ID]
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int queryWLedgerInfo(Map<String,String> reqMap);
    
    /**
     * 获取商户会员店外消费总额
     * @param sellerId
     * @return BigDecimal [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal querySellerShopTotal(Map<String,String> reqMap);
    
    /**
     * 获取商户的日营业收入
     * @param sellerId
     * @return List<OrderCommissionResponse> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<String> selectOrderIncome(Map<String,String> reqMap);

    /**
     * 获取所属商户的订单佣金
     * @param genussellerId
     * @return List<OrderCommissionResponse> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<String> selectOrderCommission(Map<String,String> reqMap);
    
    /**
     * 插入商户日统计表
     * @param SellerDayCensusBean [商户日统计请求参数]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertSellerDayCensus(SellerDayCensusBean reqBean);
    
    /**
     * 查询插入的商户日订单统计表中全都为0的数据记录数
     * @param reqBean [请求参数]
     * @return int [返回为0的记录数]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int querySellerDayCensusZero(SellerDayCensusBean reqBean);
    
    /**
     * 删除全都为0的数据
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void delSellerDayCensusZero(SellerDayCensusBean reqBean);
    
    /**
     * 获取合作商日统计中的数据
     * @return List<JointDayCensusBean> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int queryJointDayCensus(String censusTime);
    
    /**
     * 查询订单表中的消费合作商和所属合作商
     * @param reqMap
     * @return [参数说明]
     * @return List<Map<String,Integer>> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String,Integer>> queryJointAndConsumJoint(Map<String,String> reqMap);
    
    /**
     * 查询合作商收益总额
     * @param reqMap
     * @return List<String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String,Object>> queryJointProfit(Map<String,String> reqMap);
    
    /**
     * 查询合作商收益到账总额
     * @param reqMap
     * @return List<String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String,Object>> queryJointAccountTotal(Map<String,String> reqMap);
    
    /**
     * 查询统计记录是否存在
     */
    public int queryOneJointDayCensusByIDAndDate(Map<String,String> map);
    
    /**
     * 更新统计记录
     */
    public int updateOneJointDayCensusByIDAndDate(JointDayCensusBean reqBean);
    
    
    /**
     * 插入合作商日统计表
     * @param request [合作商日统计请求参数]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertJointDayCensus(JointDayCensusBean reqBean);
    
    /**
     * 根据区域id回去区域名称
     * @param areaid
     * @return
     */
    public String findAreaByid(@Param("areaid")Long areaid);
    
    /**
     * 查询向密客所在的区域
     * @param uid
     * @return
     */
    public Map<String,Object> findAreaOrHc(@Param("uid")Long uid);
    
    /**
     * 查询商家所在行业
     * @param uid
     * @return
     */
    public Map<String,String> findSellerByid(@Param("sellerid")Long sellerid);
    
    /**
     * 查询商户活动信息
     * @param reqMap [请求参数MAP集合]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,Object> querySellerActivity(Map<String,String> reqMap);
    
    /**
     * 插入活动抽奖信息
     * @param reqBean [活动抽奖信息实体bean类]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertActivityPrize(ActivitypPrizeBean reqBean);
    
    /**
     * 查询当前订单在的用户在同一家商家d定时间内的订单总数 （风险识别）
     * @param map
     * @return
     */
    public Integer findBillBySelloruser(Map<String,Object> map);
    
    /**
     *  查询当前订单的同一商家前一笔订单折扣（风险识别）
     * @param map
     * @return
     */
    public Double findPreviousBillBySell(Map<String,Object> map);

    /**
     * 查询订单退款信息
     * @param bid
     * @return
     */
	public Integer getRefund(@Param("bid")Long bid);
	
	/**
	 * 查询商户的区编号
	 * @param sellerId [商户ID]
	 * @return String [返回区编号]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String querySellerArea(String sellerId);
	
	/**
	 * 更新订单为首单
	 * @param bid [订单号]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifyOrderBfirst(String bid);
	
	/**
	 * 查询商家的累积消费流水总额
	 * @param sellerId [消费商家ID]
	 * @return double [返回查询接口]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public double querySellerWaterTotal(String sellerId);
	
	/**
	 * 获取超过三天以上的的退款订单
	 * @return RefundOrdRecordBean [退款订单对象]
	 * @see [类、类#方法、类#成员]
	 */
	public List<RefundOrdRecordBean> queryRefundOrderList();
	
	/**
	 * 批量更新退款订单状态
	 * @param map [更新的id参数]
	 */
	public void modifyBatchOrderMapStatus(Map<String,Object> map);
	
	/**
	 * 插入系统信息发布表
	 * @param reqBean [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void addPushSysMsg(PushSysMsgBean reqBean);
	
	/**
	 * 插入系统用户信息发布表
	 * @param reqBean [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void addPushSysUserMsg(PushtSysUserMsgBean reqBean);
	
	/**
	 * 查询商户账户信息
	 * @param sellerid [商户ID]
	 * @return Map<String,Integer> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,String> querySellerAccountInfos(String sellerid);
	
	/**
	 * 查询商户发行的优惠券订单关系信息
	 * @param bid [订单号]
	 * @return List<CouponRelationBean> [返回优惠券实体Bean的LIST集合]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<CouponRelationBean> querCouponRelation(String bid);
	
	/**
	 * 查询订单先关优惠劵
	 * @param bid
	 * @return
	 */
	public List<Map<String, Object>> getOrderCoupon(@Param("bid")Long bid);
	
	/**
	 * 查询已使用的优惠券与订单关系
	 * @param bid [订单号]
	 * @return List<Map<String,Object>> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> getOrderCouponList(String bid);
	
	/**
	 * 根据优惠券序列码修改用户使用状态
	 * @param serial [优惠券序列码]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void mdyCouponUserStatus(String serial);
	
	/**
	 * 查询调单前的订单信息
	 * @param paraMap
	 * @return Map<String,String> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, Object> queryAdjBeforeOrderInfo(Map<String, String> paraMap);
	
	/**
	 * 查询调单后的订单信息
	 * @param paraMap [请求参数]
	 * @return Map<String,Object> [返回查询接口MAP]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, String> queryAdjAfterOrderInfo(String orderId);
	
	/**
	 * 查询调单信息
	 * @param bid [订单号请求参数]
	 * @return Map<String,Object> [返回结果MAP]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, Object> queryAdjustInfo(String bid);
	
	/**
	 * 更新调单后的订单信息
	 * @param paraMap [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifyAdjustOrderInfo(Map<String, String> paraMap);
	
	/**
	 * 更新调单记录信息
	 * @param paraMap [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifyAdjustApply(Map<String, String> paraMap);
	
	/**
	 * 根据商户ID和订单日期获取商户日订单统计信息
	 * @param paraMap [请求参数]
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]String sellerid,String orderdate
	 */
	public Map<String,Object> querySellerCensusBySellerAndDate(@Param("sellerid")String sellerid,@Param("orderdate") String orderdate);
	
	/**
	 * 根据合作商ID和订单日期查询合作商统计信息
	 * @param paraMap [参数说明]
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,Object> queryJointCensusByJointAndDate(Map<String, String> paraMap);
	
	/**
	 * 根据合作商ID和订单日期和商家ID查询合作商收益统计信息
	 * @param paraMap [请求参数]
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,Object> queryJointProfitCensus(Map<String, String> paraMap);
	
	/**
	 * 更新调单后的商户日统计
	 * @param paraMap [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifySellerDayCensus(SellerDayCensusBean reqBean);
	
	/**
	 * 更新调单后的合作商订单统计
	 * @param paraMap [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifyJointDayCensus(Map<String, String> paraMap);
	
	/**
	 * 更新调单后的合作商收益订单统计
	 * @param paraMap [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void modifyJointProfitDayCensus(Map<String, String> paraMap);
	
	/**
	 * 增加调单申请记录
	 * @param reqBean [参数说明]
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void addAdjustApplyInfo(AdjustApplyBean reqBean);
	
	/**
	 * 查询 北京  广州区域数据
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> findAreaList();
	
	/**
	 * 获取订单信息
	 */
	public BillBean getBillBean(String bid);
	
	/**
	 * 获取订单信息
	 */
	public List<BillBean> getBillBeanList(List<String> bids);
	/**
	 * 
	* @Title: selectBargainIncome
	* @Description: 查询订单总营收
	* @return BigDecimal    返回类型
	* @throws
	 */
	public BigDecimal selectBargainIncome(Map<String,String> resMap);
	
	/**
	 * 
	* @Title: queryBargainCount
	* @Description  查询积分订单数量
	* @return int    数量
	* @throws
	 */
	public int queryBargainCount(Map<String, String> reMap);

	/**
	 * 
	* @Title: queryBargainMount
	* @Description: 查询订单流水
	* @return BigDecimal    返回类型
	* @throws
	 */
	public BigDecimal queryBargainMount(Map<String, String> reMap);
	
	/**
	 * 
	* @Title: queryIncome
	* @Description: 查询日营收
	* @return double    返回类型
	* @throws
	 */
	public BigDecimal queryIncome(Map<String, String> reMap);
	
	/**
	 * 查询商家订单店外收益
	 * @param reqMap
	 * @return
	 */
	public List<String> querySellerMoney(Map<String,String> reqMap);
	
	/**
	 * 查询商家爆品订单店外收益
	 * @param reqMap
	 * @return
	 */
	public List<String> queryBSellerMoney(Map<String,String> reqMap);
	
	
	/**
	 * 查询积分订单店外收益
	* @Title: queryfreshSellerMoney
	* @Description: 
	* @return List<String>    返回类型
	* @throws
	 */
	public List<String> queryfreshSellerMoney(Map<String, String> reMap);
	
	/**
	 * 订单表中按商家统计用户下单消费信息
	 * @Title: getUserCountBySeller 
	 * @Description:
	 */
	public List<Map<String,Object>> getUserCountBySeller(Map<String,Object> reqMap);
	
	/**
	 * 订单表中按商圈统计用户下单消费信息
	 * @Title: getUserCountBySeller 
	 * @Description:
	 */
	public List<Map<String,Object>> getUserCountByZone(Map<String,Object> reqMap);
	
	/**
	 * 订单表中按分类统计用户下单消费信息
	 * @Title: getUserCountBySeller 
	 * @Description:
	 */
	public List<Map<String,Object>> getUserCountByTrade(Map<String,Object> reqMap);
	
	/**
	 * 
	 * 方法描述：验证订单
	 * 创建人： ChenBo
	 * 创建时间：2017年3月9日
	 * @param billBean
	 * @return int
	 */
	int modifyBillVerify(BillBean billBean);
	
}