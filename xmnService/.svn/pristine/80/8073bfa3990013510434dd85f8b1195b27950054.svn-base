package com.xmniao.xmn.core.sellerPackage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.sellerPackage.entity.SellerPackageGrant;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerPackageGrantDao    
* @Description:用户套餐信息dao   
* @author: liuzhihao   
* @date: 2017年2月20日 上午9:51:11
 */
@Repository
public interface SellerPackageGrantDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    SellerPackageGrant selectByPrimaryKey(Integer id);
	
	/**
	 * 查询 套餐订单的使用券  
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<SellerPackageGrant> querySellerPackageOrderCouponList(@Param("bid") String bid);
	
	/**
	 * 查询 套餐订单的使用券  批量
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<SellerPackageGrant> queryBatchSellerPackageOrderCouponList(Map<Object, Object> map);
	
	/**
	 * 查询 美食买单订单 有无使用 套餐券结账
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public Map<Object, Object> querySellerPackageGrantByBid(@Param("bid") String bid);
	

}