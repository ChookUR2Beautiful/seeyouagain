package com.xmn.saas.dao.coupon;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmn.saas.controller.h5.coupon.vo.QueryCondition;
import com.xmn.saas.entity.coupon.SellerCouponDetail;

/**
 * 项目名称：xmniao-saas-api    
 * 类描述：   商户优惠券 DAO
 * 创建人：huangk   
 * 创建时间：2016年9月26日 下午3:04:51
 */
@Repository
public interface SellerCouponDetailDao {
	
    int deleteByPrimaryKey(Integer cid);

    /**
     * 添加优惠券信息
     * @param SellerCouponDetail
     * @return 返回主键
     */
    int insert(SellerCouponDetail record);

    /**
     * 添加优惠券信息
     * @param SellerCouponDetail
     * @return 返回主键
     */
    int insertSelective(SellerCouponDetail record);

    SellerCouponDetail selectByPrimaryKey(Integer cid) throws SQLException;

    int updateByPrimaryKeySelective(SellerCouponDetail record);

    int updateByPrimaryKey(SellerCouponDetail record);
    
    /**
   	 * 查询商户当前优惠券列表信息
   	 * @param QueryCondition	查询参数
   	 * @return List<SellerCouponDetail>    返回类型
   	 * @throws SQLException
   	 */
   	List<SellerCouponDetail> queryCouponList(QueryCondition query) throws SQLException;
	
	/**
	 * 
	 * 方法描述：根据礼物Id和类型获得优惠券名字
	 * 创建人：jianming  
	 * 创建时间：2016年9月27日 下午8:37:27   
	 * @param awardId
	 * @param awardType
	 * @return
	 */
	String getNameBy(@Param("awardId")Integer awardId, @Param("awardType")Integer awardType);
	
	/**
	 * 
	 * 方法描述：获取免尝活动可选赠品券奖品
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 上午11:13:24   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listFreetryAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询ids范围的赠品券
	 * 创建人：jianming  
	 * 创建时间：2016年9月30日 下午6:04:50   
	 * @param ids
	 * @return
	 */
	List<SellerCouponDetail> selectByFreetry(String ids);
	
	/**
	 * 
	 * 方法描述：修改奖品状态为锁定
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 上午11:44:05   
	 * @param awardId
	 */
	void setAward(Integer awardId);
	
	/**
	 * 
	 * 方法描述：查询所有没锁定抵用券和赠品券奖品信息
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午10:04:39   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> listRoulleteAward(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得赠品券可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:58:45   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> getZhengpinCoupons(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获得抵用券可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午11:58:54   
	 * @param sellerId
	 * @return
	 */
	List<SellerCouponDetail> getDiyongCoupons(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动赠品券和抵用券奖品列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 上午10:10:04   
	 * @param id
	 * @param activityType
	 * @return
	 */
	List<SellerCouponDetail> getActivityAward(@Param("id")Integer id, @Param("activityType")Integer activityType);
	
	/**
	 * 
	 * 方法描述：修改领取数量
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 下午3:08:31   
	 * @param cid
	 * @param i
	 */
	void updateAwardNumber(@Param("cid")Integer cid,@Param("awardNumber") int awardNumber);
	
	/**
	 * 
	 * 方法描述：根据奖品表获取关联的优惠券
	 * 创建人：jianming  
	 * 创建时间：2016年10月25日 上午9:58:17   
	 * @param awardId
	 * @param awardType
	 * @return
	 */
	SellerCouponDetail selectOneByAward(@Param("awardId")Integer awardId, @Param("awardType")Integer awardType);

	
}