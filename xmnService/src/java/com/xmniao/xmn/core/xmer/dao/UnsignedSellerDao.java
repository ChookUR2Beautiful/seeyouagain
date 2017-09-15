package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.seller.entity.UnsignedSeller;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 未签约商家信息dao
 * @ClassName:UnsignedSellerDao
 * @Description:未签约店铺dao
 * @Author:xw
 * @Date:2017年5月15日下午8:57:06
 */
@Repository
public interface UnsignedSellerDao {

	/**
	 * 查询店铺名称
	 * @Title:querySellerName
	 * @Description:
	 * @param sellerId 店铺id
	 * @return String	店铺名称
	 * 2017年5月15日下午9:07:10
	 */
	@DataSource("joint")
	String querySellerName(Integer sellerId);

	/**
	 * 根据关键字，商圈编号查询 店铺列表
	 * @Title:listSellerbyZoneid
	 * @Description:根据商圈id查询通过审核的未签约店铺的列表
	 * @param paraMap 商圈id 和 关键字
	 * @return List<Map<Object,Object>> 店铺列表
	 * 2017年5月17日下午4:28:01
	 */
	@DataSource("joint")
	List<Map<Object, Object>> listSellerbyKeyword(Map<Object,Object> paraMap);

	/**
	 * 
	 * @Title:addUnsignedSeller
	 * @Description:添加未签约店铺
	 * @param seller 未签约店铺实体类
	 * 2017年5月17日下午7:33:48
	 */
	@DataSource("joint")
	void addUnsignedSeller(UnsignedSeller seller);

	/**
	 * 
	 * @Title:querySellerById
	 * @Description:根据id查询店铺信息
	 * @param sellerid 店铺id
	 * @return UnsignedSeller 未签约店铺实体
	 * 2017年5月18日下午3:00:55
	 */
	@DataSource("joint")
	UnsignedSeller querySellerById(Integer sellerid);

	/**根据id集合查询商家
	 * @param unsignSellerids
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> findSellerbyIds(List<Integer> unsignSellerids);

	/**
	 * 根据id查询点评商家的信息
	 * @Title:queryCommentSellerInfoById
	 * @Description 修改点评时，回显点评店铺的信息
	 * @param sellerId
	 * @return Map<String,String>
	 * 2017年5月24日下午6:24:33
	 */
	@DataSource("joint")
	Map<String,String> queryCommentSellerInfoById(Integer sellerId);

	
}
