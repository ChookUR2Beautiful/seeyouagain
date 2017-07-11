/**
 * 
 */
package com.xmn.saas.service.coupon.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.controller.h5.coupon.vo.QueryCondition;
import com.xmn.saas.dao.coupon.SellerCouponDetailDao;
import com.xmn.saas.dao.coupon.UserCouponDao;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.coupon.UserCoupon;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.service.redpacket.RedpacketService;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   商户优惠券服务实现
 * 创建人：huangk   
 * 创建时间：2016年9月26日 下午3:20:13      
 */
@Service
public class CouponServiceImpl implements CouponService {
	
	private Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);
	
	/**
	 * 注入商户优惠券mapper
	 */
	@Autowired
	private SellerCouponDetailDao sellerCouponDetailDao;
	
	/**
	 * 注入用户优惠券mapper
	 */
	@Autowired
	private UserCouponDao userCouponDao;
	
	@Autowired
	private GlobalConfig globalConfig;
	
	@Autowired
	private RedpacketService redpacketService;

	/** 
	 * 查新商户优惠券列表
	 * @param int sellerId商户id
	 * @param String status活动状态
	 * @param String 活动类型
	 */
	@Override
	public List<SellerCouponDetail> queryCouponList(QueryCondition query) {
		List<SellerCouponDetail> couponList = null;
		try {
			couponList = sellerCouponDetailDao.queryCouponList(query);
		} catch (SQLException e) {
			log.error("queryCouponListBySidAndStatus occured SQLException:{}", e);
		}
		return couponList;
	}

	/** 
	 * 主键查询商户优惠券信息
	 * @param couponId
	 * @return SellerCouponDetail
	 */
	@Override
	public SellerCouponDetail querySellerCouponById(int couponId) {
		SellerCouponDetail coupon= null;
		try {
			coupon = sellerCouponDetailDao.selectByPrimaryKey(couponId);
		} catch (SQLException e) {
			log.error("querySellerCouponById occured SQLException:{}", e);
		}
		return coupon;
	}

	/** 
	 * 添加保存商户优惠券信息
	 * @param SellerCouponDetail
	 * @return 主键
	 */
	@Transactional
	@Override
	public int saveSellerCoupon(SellerCouponDetail sellerCouponDetail) {
		sellerCouponDetailDao.insertSelective(sellerCouponDetail);
		return sellerCouponDetail.getCid();
	}

	/** 
	 * 更新商户优惠券信息
	 * @param SellerCouponDetail
	 * @return int
	 */
	@Transactional
	@Override
	public int updateSellerCoupon(SellerCouponDetail sellerCouponDetail) {
		int record = sellerCouponDetailDao.updateByPrimaryKeySelective(sellerCouponDetail);
		return record;
	}

	/** 
	 * 查询用户领取优惠券列表
	 * @param couponId 优惠券id
	 * @return List<UserCoupon>
	 */
	@Override
	public List<Map<String,Object>> queryUserCouponListByCid(int couponId,Integer sellerId) {
		List<Map<String,Object>> couponList = null;
		try {
			couponList = userCouponDao.queryUserCouponListByCid(couponId);
			for(Map<String,Object> coupon :couponList){
			    Map<String,String> params = new HashMap<>();
                params.put("uid", coupon.get("uid")+"");
                ResponseData responseData;
                try {
                    responseData = redpacketService.getUserMsg(params);
                    if(responseData!=null && responseData.getState()==0){
                        String nname=  responseData.getResultMap().get("nname");
                        String avatar=  responseData.getResultMap().get("avatar");
                        coupon.put("avatar", StringUtils.isNotBlank(avatar)?globalConfig.getImageHost()+avatar:"");
                        String phone=  responseData.getResultMap().get("phone");
                        String genussellerid=  responseData.getResultMap().get("genussellerid");
                        coupon.put("isBind", genussellerid.equals(sellerId+"")?0:1); //是否绑定会员
                        coupon.put("name", StringUtils.isNotBlank(nname)?nname:phone);
                     }else{
                         coupon.put("name", StringUtils.isNotBlank(coupon.get("phone").toString())?coupon.get("phone").toString():"匿名"); 
                         coupon.put("avatar", ""); 
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
			}
		} catch (SQLException e) {
			log.error("queryUserCouponListByCid occured SQLException:{}", e);
		}
		return couponList;
	}

	/** 
	 * 查询用户优惠券详情
	 * @param recordId 领取记录id
	 * @return UserCoupon
	 */
	@Override
	public UserCoupon queryUserCouponById(int recordId) {
		UserCoupon coupon= null;
		try {
			coupon = userCouponDao.selectByPrimaryKey(recordId);
		} catch (SQLException e) {
			log.error("queryUserCouponById occured SQLException:{}", e);
		}
		return coupon;
	}

	/**
	 * 
	 * 方法描述：根据礼物Id和类型获得优惠券名字
	 * 创建人：jianming  
	 * 创建时间：2016年9月27日 下午8:35:35   
	 * @param awardId
	 * @param awardType
	 * @return
	 */
	@Override
	public String getNameBy(Integer awardId, Integer awardType) {
		return sellerCouponDetailDao.getNameBy(awardId,awardType);
	}
	
	/**
	 * 
	 * 方法描述：获取免尝活动可选赠品券奖品
	 * 创建人：jianming  
	 * 创建时间：2016年9月28日 上午11:12:01   
	 * @param sellerId
	 * @return
	 */
	@Override
	public List<SellerCouponDetail> listFreetryAward(Integer sellerId) {
		//只查询赠品券类型
		return sellerCouponDetailDao.listFreetryAward(sellerId);
	}

	@Override
	public List<SellerCouponDetail> selectByFreetry(AwardRelation[] awardRelations) {
		if(awardRelations==null||awardRelations.length==0){
			return null;
		}
		String ids="";
		for (AwardRelation awardRelation : awardRelations) {
			ids+=awardRelation.getId()+",";
		}
		ids=ids.substring(0,ids.lastIndexOf(","));
		return sellerCouponDetailDao.selectByFreetry(ids);
	}

	@Override
	public void setAward(Integer awardId) {
		sellerCouponDetailDao.setAward(awardId);
	}

	/**
	 * 
	 * 方法描述：获取大转盘可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午10:02:36   
	 * @param sellerId
	 * @return
	 */
	@Override
	public List<SellerCouponDetail> listAllAward(Integer sellerId) {
		//查询所有没锁定抵用券和赠品券奖品信息
		return sellerCouponDetailDao.listRoulleteAward(sellerId);
	}

	@Override
	public List<SellerCouponDetail> getZhengpinCoupons(Integer sellerId) {
		return sellerCouponDetailDao.getZhengpinCoupons(sellerId);
	}

	@Override
	public List<SellerCouponDetail> getDiyongCoupons(Integer sellerId) {
		return sellerCouponDetailDao.getDiyongCoupons(sellerId);
	}

	@Override
	public List<SellerCouponDetail> getActivityAward(Integer id, Integer activityType) {
		return sellerCouponDetailDao.getActivityAward(id,activityType);
	}
	/** 
	 * 统计优惠券活动期间新绑定用户数
	 */
	@Override
	public int countNewuer(int couponId) {
		return userCouponDao.countNewuer(couponId);
	}
	

	@Override
	public SellerCouponDetail selectByPrimaryKey(Integer awardId) {
		try {
			return sellerCouponDetailDao.selectByPrimaryKey(awardId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updateAwardNumber(Integer cid, int i) {
		sellerCouponDetailDao.updateAwardNumber(cid,i);
	}

	@Override
	public SellerCouponDetail selectOneByAward(Integer awardId, Integer awardType) {
		return sellerCouponDetailDao.selectOneByAward(awardId,awardType);
	}

	@Override
	public Integer selectAwardCount(Integer awardId) {
		try {
			SellerCouponDetail sellerCouponDetail = sellerCouponDetailDao.selectByPrimaryKey(awardId);
			return sellerCouponDetail.getMaximum()-sellerCouponDetail.getUseNumber();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
