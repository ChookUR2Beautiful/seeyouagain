package com.xmn.saas.service.activity.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.dao.activity.AwardRelationDao;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.service.activity.AwardRelationService;
import com.xmn.saas.service.coupon.CouponService;
import com.xmn.saas.service.redpacket.RedpacketService;
@Service
public class AwardRelationServiceImpl implements AwardRelationService {
	@Autowired
	private AwardRelationDao awardRelationDao;
	
	/**
	 * 注入红包服务
	 */
	@Autowired
	private RedpacketService redpacketService;
	
	@Autowired
	private CouponService couponService;
	
	@Override
	public void save(AwardRelation awardRelation) {
		awardRelationDao.insert(awardRelation);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void saveSellerCouponDetails(AwardRelation[] awardRelations, Integer activityId, Integer activityType) {
		for (int i = 0; i < awardRelations.length; i++) {
			Integer awardId = awardRelations[i].getId();
			AwardRelation awardRelation=new AwardRelation();
			awardRelation.setStatus(0);
			awardRelation.setActivityId(activityId);
			awardRelation.setAwardId(awardId);
			awardRelation.setAwardType(awardRelations[i].getAwardType());
			awardRelation.setActivityType(activityType);
			awardRelation.setAmount(awardRelations[i].getAmount());
			String couponName = null;
			if(awardRelations[i].getAwardType()==5){
				Redpacket redpacket;
				try {
					redpacket = redpacketService.findRedpacketByPrimaryKey(new Long(awardRelation.getAwardId()));
					couponName=redpacket.getRedpacketName();
					redpacketService.setAward(awardRelation.getAwardId());
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}else if(awardRelations[i].getAwardType()==3||awardRelations[i].getAwardType()==4){
				SellerCouponDetail seller=couponService.selectByPrimaryKey(awardRelation.getAwardId());
				couponName=seller.getCname();
				if(awardRelation.getAmount()>(seller.getMaximum()-seller.getAwardNumber())){
					throw new RuntimeException("设置奖品数量大于奖品实际数量");
				}
				//couponName = couponService.getNameBy(awardRelation.getAwardId(),awardRelation.getAwardType());
				//锁定奖品
				couponService.setAward(awardRelation.getAwardId());
			}
			awardRelation.setAwardName(couponName);
			save(awardRelation);
		}
	}

	@Override
	public List<Object> getRoulleteAward(Integer id, Integer activityType) {
		List<Object> awards=new ArrayList<Object>();
		List<SellerCouponDetail> sellerCouponDetails = couponService.getActivityAward(id,activityType);
		awards.addAll(sellerCouponDetails);
		List<Redpacket> redpackets=redpacketService.getActivityAward(id,activityType);
		awards.addAll(redpackets);
		return awards;
	}

	@Override
	public List<AwardRelation> getActivityAward(Integer activityId, Integer activityType) {
		return awardRelationDao.getActivityAward(activityId,activityType);
	}

	@Override
	public void updateStatus(Integer id, int status) {
		awardRelationDao.updateStatus(id,status);
	}

	@Override
	public Integer sumAward(Integer id, Integer activityType) {
		return awardRelationDao.sumAward(id,activityType);
	}


	
}
