package com.xmniao.xmn.core.sellerPackage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.sellerPackage.entity.SellerPackageOrder;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerPackageOrderDao    
* @Description:套餐订单dao   
* @author: liuzhihao   
* @date: 2017年2月20日 上午9:51:41
 */
@Repository
public interface SellerPackageOrderDao {
    
	@DataSource("joint")
	int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    SellerPackageOrder selectByPrimaryKey(Integer id);
	
	/**
	 * 描述：创建套餐订单
	 * @param Map
	 * @return Int
	 * */
	@DataSource("joint")
	public int insertSellerPackageOrder(Map<Object, Object> map);
	
	/**
	 * 描述：根据订单信息查询订单详情
	 * @param Map<Object, Object> map
	 * @return SellerPackageOrder
	 * */
	@DataSource("joint")
	public SellerPackageOrder querySellerPackageOrderByOrderNo(Map<Object, Object> map);
	
	/**
	 * 描述：根据用户查询订单列表
	 * @param Map<Object, Object> map
	 * @return List<SellerPackageOrder>
	 * */
	@DataSource("joint")
	public List<SellerPackageOrder> querySellerPackageOrderList(Map<Object, Object> map);
	
	/**
	 * 描述：根据用户查询订单列表
	 * @param Map<Object, Object> map
	 * @return List<SellerPackageOrder>
	 * */
	@DataSource("joint")
	public int updateSellerPackageOrder(Map<Object, Object> map);
	
	

}