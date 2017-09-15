package com.xmniao.xmn.core.seller.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LiveRechargeComboInfo;
import com.xmniao.xmn.core.seller.entity.DebitcardSeller;
import com.xmniao.xmn.core.util.dataSource.DataSource;
/**
 * 
* @projectName: xmnService 
* @ClassName: DebitcardSellerDao    
* @Description:充值卡dao   
* @author: liuzhihao   
* @date: 2017年2月23日 上午11:33:21
 */
@Repository
public interface DebitcardSellerDao {

	@DataSource("joint")
    DebitcardSeller selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(DebitcardSeller record);

	@DataSource("joint")
    int updateByPrimaryKey(DebitcardSeller record);
	
	/**
	 * 根据店铺ID查询充值卡信息
	 * @param sellerid
	 * @return
	 */
	@DataSource("joint")
	DebitcardSeller findBySellerId(@Param("sellerid")String sellerid);

	/**查询面向商家的专享卡套餐
	 * @return
	 */
	@DataSource("joint")
	public List<LiveRechargeComboInfo> findSellerAllCharge();	

	/**
	 * 查询 专享卡充值记录
	 * @param sellerid 商户id
	 * @param uid 用户uid
	 * @return 充值记录数
	 */
	@DataSource("joint")
	Integer queryDebitcardPayOrder(@Param("sellerid")Long sellerid, @Param("uid")String uid);

	/**查询最低的鸟币余额
	 * @return
	 */
	@DataSource("joint")
	public Map<Object, Object> findMinRechargeAmount();

	/**插入专享卡订单表
	 * @param cardOrder
	 * @return
	 */
	@DataSource("joint")
	public void insertDebitcardOrder(Map<Object, Object> cardOrder);
	
	/**根据id查询有效的专享卡
	 * @param debitId
	 * @return
	 */
	@DataSource("joint")
	public DebitcardSeller selectDebitcardByid(Integer debitId);

	/**查询所有的粉丝等级详情
	 * @return
	 */
	@DataSource("burs")
	public List<Map<Object, Object>> findAllRanks();

	/**根据金额匹配对应的粉丝等级
	 * @param param
	 * @return
	 */
	@DataSource("burs")
	public Map<Object, Object> findFansRankByAmout(Map<Object, Object> param);
}