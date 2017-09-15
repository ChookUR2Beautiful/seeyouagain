package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.BillFresh;
import com.xmniao.xmn.core.market.entity.pay.OrderInfoResponse;
import com.xmniao.xmn.core.market.entity.pay.OrderResponse;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: BillFreshDao    
* @Description:积分商品订单   
* @author: liuzhihao   
* @date: 2016年12月22日 上午11:00:11
 */
@Repository
public interface MarketBillFreshDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Long bid);

	@DataSource("joint")
    int insert(BillFresh record);

	@DataSource("joint")
	int insertSelective(BillFresh record);

	@DataSource("joint")
    BillFresh selectByPrimaryKey(Long bid);

	@DataSource("joint")
    int updateByPrimaryKeySelective(BillFresh record);

	@DataSource("joint")
    int updateByPrimaryKey(BillFresh record);
	
	//定时删除未支付和取消订单的订单
	@DataSource("joint")
	List<BillFresh> findAllByQuertz();
	
	/**
	 * 通过用户ID 查询用户订单 
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<BillFresh> findAllByUid(@Param("uid")String uid);
	
	/**
	 * 通过用户ID查询用户订单列表
	 * @param uid
	 * @param type
	 * @return
	 */
	@DataSource("joint")
	List<OrderResponse> queryOrderList(@Param("uid")String uid,@Param("type")Integer type,@Param("page")Integer page,@Param("limit")Integer limit);

	@DataSource("joint")
	OrderInfoResponse findOneBySubBid(@Param("uid")String uid,@Param("bid")String bid);
	
	@DataSource("joint")
	OrderInfoResponse findOneByBid(@Param("uid")String uid,@Param("bid")String bid);
	
	//查询供应商联系电话
	@DataSource("joint")
	String querySupplierPhone(@Param("supplierId")String supplierId);

}