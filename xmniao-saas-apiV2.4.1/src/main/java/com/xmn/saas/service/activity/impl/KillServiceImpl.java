package com.xmn.saas.service.activity.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.dao.activity.KillDao;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.activity.Kill;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.service.activity.AwardRelationService;
import com.xmn.saas.service.activity.KillService;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.coupon.CouponService;

@Service
public class KillServiceImpl implements KillService {
	@Autowired
	private KillDao killDao;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private AwardRelationService awardRelationService;
	
	@Autowired
	private RecordService recordService;

	@Override
	public List<Kill> list(Integer sellerId,boolean beingorend, Integer pageSize, Integer pageIndex) {
		if(beingorend){
			return killDao.listBeing(sellerId);
		}else{
			
			return killDao.listEnd(sellerId,pageSize,pageIndex);
		}
	}

	@Override
	public List<SellerCouponDetail> getSellerCouponDetail(AwardRelation[] awardRelation) {
		return couponService.selectByFreetry(awardRelation);
	}

	@Override
	public List<SellerCouponDetail> listAward(Integer sellerId) {
		return couponService.listFreetryAward(sellerId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer save(Kill kill) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			kill.setBeginDate(df2.parse(df.format(kill.getBeginDate())+" 00:00:00"));
			kill.setEndDate(df2.parse(df.format(kill.getEndDate())+" 23:59:00"));
			kill.setCreateTime(new Date());
			kill.setStatus(0);
			AwardRelation[] awardRelation = kill.getAwardRelations();
			Integer giveNumber= 0;
			for (AwardRelation awardRelation2 : awardRelation) {
				giveNumber+=awardRelation2.getAmount();
			}
			kill.setGiveNumber(giveNumber);
			kill.setJoinNumber(0);
			kill.setViews(0);
			kill.setNewuserNum(0);
			kill.setSecKillNumber(0);
			kill.setConsumeAmount(new BigDecimal(0));
			killDao.insert(kill);
			awardRelationService.saveSellerCouponDetails(awardRelation,kill.getId(),kill.getActivityType());
			return kill.getId();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Kill detail(Integer activityId, Integer sellerId) {
		return killDao.selectByPrimaryKeyAndGiveAwardCount(activityId,sellerId);
	}

	@Override
	public List<AwardRelation> getKillAward(Integer id, Integer activityType) {
		return awardRelationService.getActivityAward(id,activityType);
	}

	@Override
	public void saveTempKill(Kill kill, Integer sellerId) {
		recordService.saveTempActivity(kill,sellerId);
		
	}

	@Override
	public Kill giveTempKill(Integer sellerId) {
		return  recordService.giveTempActivity(sellerId,Kill.class);
	}

	@Override
	public Integer CountBeingActivity(Integer sellerId) {
		return killDao.CountBeingActivity(sellerId);
	}

	@Override
	public void endActivity(Integer activityId, Integer sellerId) {
		killDao.endActivity(activityId,sellerId);
	}

	@Override
	public Integer selectAwardCount(Integer awardId) {
		SellerCouponDetail sellerCouponDetail = couponService.selectByPrimaryKey(awardId);
		return sellerCouponDetail.getMaximum()-sellerCouponDetail.getUseNumber();
	}
}
