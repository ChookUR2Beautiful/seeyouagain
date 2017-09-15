package com.xmniao.xmn.core.market.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.BillFreshSub;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: BillFreshSubDao    
* @Description:子订单dao   
* @author: liuzhihao   
* @date: 2016年12月22日 上午10:59:23
 */
@Repository
public interface MarketBillFreshSubDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Long id);

	@DataSource("joint")
    int insert(BillFreshSub record);

	@DataSource("joint")
    int insertSelective(BillFreshSub record);

	@DataSource("joint")
    BillFreshSub selectByPrimaryKey(Long id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(BillFreshSub record);

	@DataSource("joint")
    int updateByPrimaryKey(BillFreshSub record);
	
	@DataSource("joint")
	BillFreshSub findOneByGroupBidAanUid(@Param("bid")Long bid,@Param("uid")String uid);
	
	@DataSource("joint")
	BillFreshSub findOneSubOrderSnAanUid(@Param("bid")Long bid,@Param("uid")String uid);
	
	/**
	 * 合计子单消费积分
	 * @param bid
	 * @return
	 */
	@DataSource("joint")
	Double sumOrderIntegral(@Param("bid")Long bid);
}