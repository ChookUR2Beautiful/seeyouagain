package com.xmn.saas.service.activity.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.dao.activity.FreetryDao;
import com.xmn.saas.dao.activity.FreetryRecordDao;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Freetry;
import com.xmn.saas.entity.activity.FreetryRecord;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.exception.activity.ActivityBlockException;
import com.xmn.saas.exception.activity.ConditionAtTimeQuantumException;
import com.xmn.saas.exception.activity.MaxLimitNumberException;
import com.xmn.saas.exception.activity.MaxPrizeDrawNumberException;
import com.xmn.saas.service.activity.AwardRelationService;
import com.xmn.saas.service.activity.FreetryService;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.utils.ActivityUtil;

@Service
public class FreetryServiceImpl implements FreetryService {
	@Autowired
	private FreetryDao freetryDao;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private AwardRelationService awardRelationService;
	
	@Autowired
	private RecordService recordService;
	
	
	@Autowired
	private FreetryRecordDao freetryRecordDao;
	
	@Override
	public Freetry detail(Integer id,Integer sellerId) {
		return freetryDao.selectByPrimaryKeyAndGiveAwardCount(id,sellerId);
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
	public Integer save(Freetry freetry) {
		//奖品记录表和免尝表一同添加
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			freetry.setBeginDate(df2.parse(df.format(freetry.getBeginDate())+" 00:00:00"));
			freetry.setEndDate(df2.parse(df.format(freetry.getEndDate())+" 23:59:00"));
			freetry.setCreateTime(new Date());
			freetry.setStatus(0);
			AwardRelation[] awardRelation = freetry.getAwardRelations();
			Integer giveNumber= 0;
			for (AwardRelation awardRelation2 : awardRelation) {
				giveNumber+=awardRelation2.getAmount();
			}
			freetry.setGiveNumber(giveNumber);
			freetry.setJoinNumber(0);
			freetry.setViews(0);
			freetry.setNewuserNum(0);
			freetry.setTakeNum(0);
			freetryDao.insert(freetry);
			awardRelationService.saveSellerCouponDetails(awardRelation,freetry.getId(),freetry.getActivityType());
			return freetry.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Override
	public List<Freetry> list(Integer sellerId,boolean beingorend,Integer pageSize, Integer pageIndex) {
		if(beingorend){
			return freetryDao.listBeing(sellerId);
		}else{
			return freetryDao.listEnd(sellerId,pageSize,pageIndex);
		}
	}

	@Override
	public List<SellerCouponDetail> listAward(Integer sellerId) {
		return couponService.listFreetryAward(sellerId);
	}

	@Override
	public List<Freetry> listBeing(Integer sellerId) {
		return freetryDao.listBeing(sellerId);
	}

	@Override
	public List<Freetry> listEnd(Integer sellerId,Integer pageSize, Integer pageIndex) {
		return freetryDao.listEnd(sellerId,pageSize,pageIndex);
	}

	@Override
	public List<SellerCouponDetail> getSellerCouponDetail(AwardRelation[] awardRelation) {
		return couponService.selectByFreetry(awardRelation);
	}

	@Override
	public List<AwardRelation> getFreetryAway(Integer id, Integer activityType) {
		return awardRelationService.getActivityAward(id, activityType);
	}

	@Override
	public void saveTempFreetry(Freetry freetry, Integer sellerId) {
		recordService.saveTempActivity(freetry,sellerId);
	}

	@Override
	public Freetry giveTempFreetry(Integer sellerId) {
		  return  recordService.giveTempActivity(sellerId,Freetry.class);
	}


	@Override
	public Integer CountBeingActivity(Integer sellerId) {
		return freetryDao.CountBeingActivity(sellerId);
	}

	@Override
	public void endActivity(Integer id,Integer sellerId) {
		freetryDao.endActivity(id,sellerId);
	}

	@Override
	public Integer selectAwardCount(Integer awardId) {
		return couponService.selectAwardCount(awardId);
	}
}
