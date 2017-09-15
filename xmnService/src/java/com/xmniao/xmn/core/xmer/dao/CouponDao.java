package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;
import com.xmniao.xmn.core.xmer.entity.Coupon;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponDao    
* @Description:优惠券dao   
* @author: liuzhihao   
* @date: 2016年11月26日 下午4:06:44
 */
@Repository
public interface CouponDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer cid);

	@DataSource("joint")
    int insert(Coupon record);

	@DataSource("joint")
    int insertSelective(Coupon record);

	@DataSource("joint")
    Coupon selectByPrimaryKey(@Param("cid") Integer cid);
	
	@DataSource("joint")
	Coupon queryCouponByCid(Integer cid);

	@DataSource("joint")
    int updateByPrimaryKeySelective(Coupon record);

	@DataSource("joint")
    int updateByPrimaryKeyWithBLOBs(Coupon record);

	@DataSource("joint")
    int updateByPrimaryKey(Coupon record);
	
	/**
	 * 通过用户id查询用户未使用并且未过期的平台礼券 
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryPlatCouponUsed(Map<Object,Object> map);
	
	/**
	 * 通过用户id查询用户已使用或者已过期平台礼券
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> queryPlatCouponUnused(Map<Object,Object> map);
	
	/**
	 * 通过用户id查询用户未使用并且未过期的餐饮商铺优惠券列表
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> querySellerCouponUsed(Map<Object,Object> map);
	
	/**
	 * 通过用户id查询用户已使用或者已过期的优惠卷列表
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object,Object>> querySellerCouponUnused(Map<Object,Object> map);
	
	
	/**
	 * 删除商铺优惠卷
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int removeSellerCouponByUid(Map<Object,Object> map);
	
	/**
	 * 删除平台优惠卷
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	int removePlatCouponByUid(Map<Object,Object> map);

	/**
	 * 
	* @Title: queryRecommendFansCoupon
	* @Description: 查询推荐的粉丝券 1、根据用户定位，按距离显示附近店铺粉丝券   2、根据可使用时间，由近至远排序
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryRecommendFansCoupon(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryPlatformRecommendCouponSum
	* @Description: 查询平台推荐的粉丝券数量
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer queryPlatformRecommendCouponSum(Map<Object, Object> couponMap);

	/**
	 * 
	* @Title: queryAnchorCouponSumByUid
	* @Description: 查询主播的其他粉丝券数量
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer queryAnchorCouponSumByUid(Map<Object, Object> couponMap);
	
	/**
	 * 根据用户ID 查询用户持有XMN平台发布的有券
	 * @param uid 			为true 用户ID
	 * @param type 			为true 0 为过期或者已使用 1 为 未使用且未过期
	 * @param page 			为true 分页页码
	 * @param pageSize 		为true 分页条数
	 * @return
	 * [{"sellerid":"","sellername":"","uid":"","cname":"","denomination":"",
	 * "conditions":"serial":"","startDate":"","endDate":"","useStatus":""}]
	 */
	@DataSource("joint")
	List<Map<Object,Object>> selectXMNCouponsByUid(@Param("uid")String uid,@Param("type")Integer type,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
	
	/**
	 * 根据用户ID 查询用户持有的SAAS发布的优惠卷
	 * @param uid			为true 用户ID
	 * @param type			为true 0 为过期或者已使用 1 为 未使用且未过期
	 * @param page			为true 分页页码
	 * @param pageSize		为true 分页条数
	 * @return
	 *  [{"sellerid":"","sellername":"","uid":"","cname":"","denomination":"",
	 * "conditions":"serial":"","startDate":"","endDate":"","useStatus":""}]
	 */
	@DataSource("joint")
	List<Map<Object,Object>> selectSAASCouponsByUid(@Param("uid")String uid,@Param("type")Integer type,@Param("page")Integer page,@Param("pageSize")Integer pageSize);

	@DataSource("joint")
	int sumXMNXMNCouponCount(@Param("uid")String uid);
	
	@DataSource("joint")
	int sumSAASCouponCount(@Param("uid")String uid);
}