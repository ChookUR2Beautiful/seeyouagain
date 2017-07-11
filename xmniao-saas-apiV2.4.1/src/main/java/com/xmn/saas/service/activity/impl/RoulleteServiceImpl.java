package com.xmn.saas.service.activity.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.dao.activity.RoulleteDao;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.activity.Roullete;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.service.activity.AwardRelationService;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.activity.RoulleteService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.service.redpacket.RedpacketService;
@Service
public class RoulleteServiceImpl implements RoulleteService {
	
	/**
	 * 注入优惠券服务层
	 */
	@Autowired
	private CouponService couponService;
	
	/**
	 * 注入红包服务层
	 */
	@Autowired
	private RedpacketService redpacketService;
	
	@Autowired
	private AwardRelationService awardRelationService;
	
	@Autowired
	private RecordService recordService;
	
	/**
	 * 注入dao
	 */
	@Autowired
	private RoulleteDao roulleteDao;

	@Override
	public List<Roullete> list(Integer sellerId, boolean beingorend,Integer pageSize, Integer pageIndex) {
		if(beingorend){
			return roulleteDao.listBeing(sellerId);
		}else{
			return roulleteDao.listEnd(sellerId,pageSize,pageIndex);
		}
	}

	@Override
	public List<SellerCouponDetail> getSellerCouponDetail(AwardRelation[] awardRelation) {
		return couponService.selectByFreetry(awardRelation);
	}

	@Override
	public List<Object> listAward(Integer sellerId) {
		List<Object> awards=new ArrayList<Object>();
		List<SellerCouponDetail> sellerCouponDetails = couponService.listAllAward(sellerId);
		List<Redpacket> redpackets = redpacketService.listRoulleteAward(sellerId);
		awards.addAll(sellerCouponDetails);
		awards.addAll(redpackets);
		return awards;
	}

	@Override
	public List<SellerCouponDetail> getZhengpinCoupons(Integer sellerId) {
		return couponService.getZhengpinCoupons(sellerId);
	}

	@Override
	public List<SellerCouponDetail> getDiyongCoupons(Integer sellerId) {
		return couponService.getDiyongCoupons(sellerId);
	}

	@Override
	public List<Redpacket> getRedpackets(Integer sellerId) {
		return redpacketService.listRoulleteAward(sellerId);
	}

	/**
	 * 
	 * 方法描述：添加免尝活动
	 * 创建人：jianming  
	 * 创建时间：2016年9月27日 下午6:03:02   
	 * @param freetry
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer save(Roullete roullete) {
		//奖品记录表和免尝表一同添加
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			roullete.setBeginDate(df2.parse(df.format(roullete.getBeginDate())+" 00:00:00"));
			roullete.setEndDate(df2.parse(df.format(roullete.getEndDate())+" 23:59:00"));
			roullete.setCreateTime(new Date());
			roullete.setStatus(0);
			AwardRelation[] awardRelation = roullete.getAwardRelations();
			Integer giveNumber= 0;
			for (AwardRelation awardRelation2 : awardRelation) {
				giveNumber+=awardRelation2.getAmount();
			}
			roullete.setGiveNumber(giveNumber);
			roullete.setJoinNumber(0L);
			roullete.setViews(0);
			roullete.setNewuserNum(0);
			roullete.setConsumeAmount(new BigDecimal(0));
			roulleteDao.insert(roullete);
			//添加奖品记录
			awardRelationService.saveSellerCouponDetails(awardRelation,roullete.getId(),roullete.getActivityType());
			return roullete.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Roullete detail(Integer activityId, Integer sellerId) {
		return roulleteDao.selectByPrimaryKeyAndGiveAwardCount(activityId,sellerId);
	}

	@Override
	public List<AwardRelation> getRoulleteAward(Integer activityId, Integer activityType) {
		return awardRelationService.getActivityAward(activityId,activityType);
	}

	@Override
	public void saveTempRoullete(Roullete roullete, Integer sellerId) {
		recordService.saveTempActivity(roullete,sellerId);
	}

	@Override
	public Roullete giveTempRoullete(Integer sellerId) {
		 return  recordService.giveTempActivity(sellerId,Roullete.class);
	}

	@Override
	public Integer CountBeingActivity(Integer sellerId) {
		return roulleteDao.CountBeingActivity(sellerId);
	}

	@Override
	public void endActivity(Integer activityId, Integer sellerId) {
		roulleteDao.endActivity(activityId,sellerId);
	}

	@Override
	public Integer selectAwardCount(Integer awardId) {
		SellerCouponDetail sellerCouponDetail = couponService.selectByPrimaryKey(awardId);
		return sellerCouponDetail.getMaximum()-sellerCouponDetail.getUseNumber();
	}
}
