package com.xmniao.xmn.core.integral.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.integral.response.IntegralBillListResponse;
import com.xmniao.xmn.core.market.entity.pay.ProductBill;
import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface BillFreshDao {
	
	/**
	 * 查询订单信息
	 * @author xiaoxiong
	 * @date 2016年11月25日
	 */
	@DataSource("joint")
	List<IntegralBillListResponse> queryBillFreshByUid(
			Map<String, Object> params);
	
	/**
	 * 查询订单商品
	 * @author xiaoxiong
	 * @date 2016年11月25日
	 */
	@DataSource("joint")
	List<ProductBill> queryBillProductByBid(@Param("bid")String bid,@Param("type")int type,@Param("fileUrl")String fileUrl);

}
