package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.ProductBill;
import com.xmniao.xmn.core.market.entity.pay.ProductResponse;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: ProductBillDao    
* @Description:商品订单实体   
* @author: liuzhihao   
* @date: 2016年12月22日 上午11:14:01
 */
@Repository
public interface ProductBillDao {
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(ProductBill record);

	@DataSource("joint")
    int insertSelective(ProductBill record);

	@DataSource("joint")
    ProductBill selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(ProductBill record);

	@DataSource("joint")
    int updateByPrimaryKey(ProductBill record);
	
	@DataSource("joint")
	int deleteByBid(Long id);
	
	//父订单ID查询商品详情
	@DataSource("joint")
    List<ProductBill> findAllByBid(@Param("bid") Long bid);
	
	@DataSource("joint")
	List<ProductResponse> queryProductBillByBid(@Param("bid")Long bid);
	
	@DataSource("joint")
	List<ProductResponse> queryProductBillBySubBid(@Param("bid")Long bid);
	
	
	//子订单查询商品详情
	@DataSource("joint")
	List<ProductBill> findAllBySubBid(@Param("bid")String bid);
	
	/**
	 * 统计活动商品的购买总数量
	 * @param activityId
	 * @param codeId
	 * @return
	 */
	@DataSource("joint")
	int sumProductCountsByActivityIdAndCodeId(@Param("activityId")String activityId,@Param("codeId")String codeId,@Param("uid")String uid);
}