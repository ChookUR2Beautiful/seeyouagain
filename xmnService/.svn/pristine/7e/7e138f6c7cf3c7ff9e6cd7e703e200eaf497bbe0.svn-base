package com.xmniao.xmn.core.market.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.ProductDetails;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: ProductDetailsDao    
* @Description:积分商品   
* @author: liuzhihao   
* @date: 2016年12月22日 上午11:01:56
 */
@Repository
public interface ProductDetailsDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer fid);

	@DataSource("joint")
    int insert(ProductDetails record);

	@DataSource("joint")
    int insertSelective(ProductDetails record);

	@DataSource("joint")
    ProductDetails selectByPrimaryKey(Integer fid);
	
	@DataSource("joint")
	ProductDetails selectByCodeId(@Param("codeid")Long codeid);

	@DataSource("joint")
    int updateByPrimaryKeySelective(ProductDetails record);

	@DataSource("joint")
    int updateByPrimaryKeyWithBLOBs(ProductDetails record);

	@DataSource("joint")
    int updateByPrimaryKey(ProductDetails record);
}