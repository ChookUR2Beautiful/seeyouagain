package com.xmn.saas.dao.coupon;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.coupon.UserCoupon;

@Repository
public interface UserCouponDao {
	
    int deleteByPrimaryKey(Integer cuid) throws SQLException;

    int insert(UserCoupon record) throws SQLException;

    int insertSelective(UserCoupon record) throws SQLException;

	/**
	 * 查询某一用户领取的优惠券详细信息
	 * @param recordId 领取记录id
	 * @return UserCoupon 领取信息
	 * @throws
	 */
    UserCoupon selectByPrimaryKey(int recordId) throws SQLException;

    int updateByPrimaryKeySelective(UserCoupon record) throws SQLException;

    int updateByPrimaryKey(UserCoupon record) throws SQLException;
    
    /**
	 * 查询用户领取优惠券明细列表
	 * @param couponId 优惠券id
	 * @return List<Coupon>
	 * @throws SQLException
	 */
	List<Map<String,Object>> queryUserCouponListByCid(int couponId) throws SQLException;
	
	/**
	 * 统计优惠券活动新绑定用户数
	 * @param 优惠券活动id
	 * @return int    返回类型
	 * @throws
	 */
	int countNewuer(int couponId);
	
	/**
	 * 
	 * 方法描述：统计活动优惠券使用数量
	 * 创建人：jianming  
	 * 创建时间：2016年11月24日 下午6:35:08   
	 * @param activityId  活动领取记录id
	 * @param activityType	活动类型
	 * @return
	 */
	Integer countActivityUse(@Param("activityId")Integer activityId, @Param("activityType")int activityType);
	
}