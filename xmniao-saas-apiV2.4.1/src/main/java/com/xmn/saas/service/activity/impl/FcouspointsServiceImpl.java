package com.xmn.saas.service.activity.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.constants.ActivityConsts;
import com.xmn.saas.dao.activity.AwardRelationDao;
import com.xmn.saas.dao.activity.FcouspointsConverDao;
import com.xmn.saas.dao.activity.FcouspointsDao;
import com.xmn.saas.dao.activity.FcouspointsRecordDao;
import com.xmn.saas.dao.bill.BillDao;
import com.xmn.saas.dao.coupon.UserCouponDao;
import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.activity.Fcouspoints;
import com.xmn.saas.entity.activity.FcouspointsConver;
import com.xmn.saas.entity.activity.FcouspointsRecord;
import com.xmn.saas.entity.coupon.SellerCouponDetail;
import com.xmn.saas.service.activity.AwardRelationService;
import com.xmn.saas.service.activity.FcouspointsService;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.coupon.CouponService;
@Service
public class FcouspointsServiceImpl implements FcouspointsService {
	 
	@Autowired
	private FcouspointsDao fcouspointsDao;
	
	@Autowired
	private  UserCouponDao couponDao;
	
	@Autowired
	private FcouspointsRecordDao fcouspointsRecordDao;
	
	@Autowired
	private AwardRelationDao awardRelationDao;
	
	@Autowired
	private AwardRelationService awardRelationService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private FcouspointsConverDao fcouspointsConverDao;
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private BillDao billDao;
	
	@Override
	public Fcouspoints detail(Integer id, Integer sellerId) {
		Fcouspoints fcouspoints=fcouspointsDao.selectBySellerKey(id,sellerId);
		Integer userCount=couponDao.countActivityUse(id,ActivityConsts.ACTIVITY_TYPE_FCOUSPONTS);
		//Integer joinCount=fcouspointsRecordDao.countByActivityId(id);
		BigDecimal divide =new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
		if(fcouspoints.getJoinNumber()!=0){
			divide= new BigDecimal(userCount).divide(new BigDecimal(fcouspoints.getJoinNumber())).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		fcouspoints.setConverProportion(divide);
		return fcouspoints;
	}

	@Override
	public AwardRelation getFcouspointsAward(Integer id, Integer activityType) {
		 List<AwardRelation> activityAward = awardRelationDao.getActivityAward(id, activityType);
		 if(activityAward==null){
			 throw new RuntimeException("没有奖品");
		 }
		 if(activityAward.size()>1){
			 throw new RuntimeException("奖品数量大于1");
		 }
		 return activityAward.get(0);
	}

	@Override
	public List<Fcouspoints> listBeing(Integer sellerId) {
		return fcouspointsDao.listBeing(sellerId);
	}

	@Override
	public List<Fcouspoints> listEnd(Integer sellerId, Integer pageSize, Integer pageIndex) {
		return fcouspointsDao.listEnd(sellerId,pageSize,pageIndex);
	}

	@Override
	public Integer CountBeingActivity(Integer sellerId) {
		return fcouspointsDao.CountBeingActivity(sellerId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void save(Fcouspoints fcouspoints) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fcouspoints.setBeginDate(df2.parse(df.format(fcouspoints.getBeginDate())+" 00:00:00"));
			fcouspoints.setEndDate(df2.parse(df.format(fcouspoints.getEndDate())+" 23:59:00"));
			fcouspoints.setCreateTime(new Date());
			fcouspoints.setStatus(0);
			AwardRelation awardRelation = fcouspoints.getAwardRelation();
			fcouspoints.setGiveNumber(awardRelation.getAmount());
			fcouspoints.setJoinNumber(0);
			fcouspoints.setViews(0);
			fcouspoints.setConversionNumber(0);
			fcouspoints.setViews(0);
			fcouspointsDao.insert(fcouspoints);
			AwardRelation[] awardRelations=new AwardRelation[1];
			awardRelations[0]=awardRelation;
			awardRelationService.saveSellerCouponDetails(awardRelations,fcouspoints.getId(),fcouspoints.getActivityType());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("创建集点活动出错");
		}
	}

	@Override
	public List<SellerCouponDetail> listAward(Integer sellerId) {
		return couponService.listAllAward(sellerId);
	}

	@Override
	public List<Fcouspoints> list(Integer sellerId, boolean beingorend, Integer pageSize, Integer pageIndex) {
		if(beingorend){
			return fcouspointsDao.listBeing(sellerId);
		}else{
			return fcouspointsDao.listEnd(sellerId,pageSize,pageIndex);
		}
	}

	@Override
	public List<ActivityRecord> listRecord(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex) {
		List<FcouspointsRecord> listRecord = fcouspointsRecordDao.listRecord(activityId,sellerId,pageSize,pageIndex);
		return recordService.getUserMsg(listRecord, sellerId);
	}

	@Override
	public List<ActivityRecord> listConver(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex) {
		List<FcouspointsConver> listConver = fcouspointsConverDao.listConver(activityId,sellerId,pageSize,pageIndex);
		return recordService.getUserMsg(listConver, sellerId);
	}

	@Override
	public List<Map<String, Object>> countRecordByDate(Integer activityId) {
		return fcouspointsRecordDao.countRecordByDate(activityId);
	}

	@Override
	public List<Map<String,Object>> countConverByDate(Integer activityId) {
		return fcouspointsConverDao.countConverByDate(activityId);
	}

	@Override
	public void endActivity(Integer activityId, Integer sellerId) {
		fcouspointsDao.endActivity(activityId,sellerId);
	}

	@Override
	public FcouspointsConver detailConver(Integer id,Integer sellerId) {
		 FcouspointsConver conver = fcouspointsConverDao.selectByPrimaryKey(id);
		 return (FcouspointsConver) recordService.getUserMsg(conver,sellerId);
	}

	@Override
	public Map<String, Object> detailUser(Integer uid, Integer sellerId) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		//首次消费时间
		Date firstDate=billDao.selectFirstConsume(uid,sellerId);
		map.put("firstDate", firstDate);
		//最近消费时间
		Date lastDate=billDao.selectLastConsume(uid,sellerId);
		map.put("lastDate", lastDate);
		//消费次数
		Integer count=billDao.CountConsume(uid,sellerId);
		map.put("count", count);
		//累计消费
		BigDecimal sum= billDao.sumConsume(uid,sellerId);
		map.put("sum",sum);
		//最高消费
		BigDecimal max=billDao.maxConsume(uid,sellerId);
		map.put("max",max);
		//最底消费
		BigDecimal min=billDao.minConsume(uid,sellerId);
		map.put("min",min);
		//平均消费
		BigDecimal avg=billDao.avgConsume(uid,sellerId);
		map.put("avg",avg);
		return map;
	}

	@Override
	public FcouspointsRecord detailRecord(Integer id, Integer sellerId) {
		return fcouspointsRecordDao.selectByPrimaryKey(id,sellerId);
	}

}
