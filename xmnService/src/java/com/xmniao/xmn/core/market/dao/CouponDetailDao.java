package com.xmniao.xmn.core.market.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.CouponDetail;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponDetailDao    
* @Description:平台优惠卷发放dao   
* @author: liuzhihao   
* @date: 2016年12月22日 下午3:13:47
 */
@Repository
public interface CouponDetailDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer key);

	@DataSource("joint")
    int insert(CouponDetail record);
	
	@DataSource("joint")
    int insertBatch(Map<Object, Object> map);

	@DataSource("joint")
    int insertSelective(CouponDetail record);

	@DataSource("joint")
    CouponDetail selectByPrimaryKey(Integer key);

	@DataSource("joint")
    int updateByPrimaryKeySelective(CouponDetail record);

	@DataSource("joint")
    int updateByPrimaryKey(CouponDetail record);
	
	/**
	 * 查询为过期的用户积分超市优惠卷
	 * @param map
	 * @return
	 */
	@DataSource("joint")
    List<CouponDetail> queryCouponDetailsByUid(Map<Object,Object> map);
	
	/**
	 * 查询未过期的用户积分超市优惠卷
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllCouponsByUid(Map<Object,Object> map);
	
	/**
	 * 查询用户可以使用的优惠卷
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> findAllIsUserCoupons(Map<Object,Object> map);
	
	@DataSource("joint")
	Map<Object,Object> findOneCoupon(@Param("uid")Integer uid,@Param("cdid")Integer cdid);
	
	@DataSource("joint")
	List<CouponDetail> findAllByCdids(@Param("cdids")List<Integer> cdids);
	
	@DataSource("joint")
	List<Map<Object,Object>> findAllCouponsByBid(@Param("bid")String bid);
	
	/**
	 * 获取用户在积分超市购物时可用的优惠卷列表
	 */
	@DataSource("joint")
	List<Map<Object,Object>> getMarketUsableCouponsByUid(Map<Object,Object> map);
}