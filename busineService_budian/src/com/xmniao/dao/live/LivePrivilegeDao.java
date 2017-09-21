package com.xmniao.dao.live;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.live.LivePrivilege;

public interface LivePrivilegeDao {

	/**
	 * 
	 * 方法描述：获取会员充值/兑换金额最高的订单
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param map
	 * @return LivePrivilege
	 */
	LivePrivilege getHighestLedgerOrder(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：初始化充值/兑换订单的打赏分红信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param priviledge
	 * @return int
	 */
	int initPrivilegeInfo(LivePrivilege priviledge);
	
	/**
	 * 
	 * 方法描述：查询用户所有未返满打赏部分商家专享鸟币订单
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param uid
	 * @return List<LivePrivilege>
	 */
	List<LivePrivilege> getSellerCoinOrderList(Integer uid);
	
	/**
	 * 
	 * 方法描述：更新订单的打赏分红信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param uPrivilege
	 * @return int
	 */
	int updateLiveOrderLedger(LivePrivilege uPrivilege);
	
	/**
	 * 获取最新可奖励分账返利中的订单(含已经返利完毕的订单)
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param reqMap
	 * @return LivePrivilege
	 */
	LivePrivilege getLiveOrderByLedger(Map<String,Object> reqMap);
	
	/**
	 * 
	 * 方法描述：获取用户所有可能在享受分账的订单
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param reqMap
	 * @return List<LivePrivilege>
	 */
	List<LivePrivilege> getLiveOrderListByLedgering(Map<String,Object> reqMap);
	
	/**
	 * 获取有充值订单及还有红包的用户列表
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param paraMap
	 * @return List<Integer>
	 */
	List<Integer> getLiveOrderHasDividendsUserList(Map<String,Object> paraMap);
	
	/**
	 * 
	 * 方法描述：获取打赏分红信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param id
	 * @return LivePrivilege
	 */
	LivePrivilege getLivePrivilege(LivePrivilege livePrivilege);
		
	/**
	 * 
	 * 方法描述：初始化用户可用于兑换储值卡的鸟币面额
	 * 创建人： ChenBo
	 * 创建时间：2017年4月6日
	 * @param reqMap
	 * @return List<Map<String,String>>
	 */
	List<Map<String,String>> getAvailableExchangeCoinList(Map<String,Object> reqMap);
	
	/**
	 * 
	 * 方法描述：获取会员兑换额最大的正在分账的订单
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param map
	 * @return LivePrivilege
	 */
	LivePrivilege getHighestDebitcardLedgerOrder(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：获取会员所有还没打赏鸟豆完毕的充值订单
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月23日下午8:25:21 <br/>
	 * @param map
	 * @return
	 */
	List<LivePrivilege> getConsumeOrderList(Map<String,Object> map);

	/**
	 * 查询满足发放奖励的方案A充值记录
	 * @return
	 */
	List<LivePrivilege> selectRechargeRecordListForPlanA();

	/**
	 * 更新已领取期数
	 * @param livePrivilege
	 */
	void updateCurPeriodExcitation(LivePrivilege livePrivilege);
}