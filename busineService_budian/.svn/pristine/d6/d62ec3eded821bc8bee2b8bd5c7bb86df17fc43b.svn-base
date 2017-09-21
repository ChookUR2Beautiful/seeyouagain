/**    
 * 文件名：DGRechargeLiveOrder.java    
 *    
 * 版本信息：    
 * 日期：2017年2月23日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.live.DebitcardSellerDao;
import com.xmniao.dao.live.LiveLedgerRecordDao;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.domain.live.DebitcardSeller;
import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.live.LivePayOrder;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.LiverJournalCount;
import com.xmniao.domain.live.RecommendLedger;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.urs.dao.UrsEarningsRelationDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：DGRechargeLiveOrder
 * 
 * 类描述： 中脉大观直销平台充值订单
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月23日 下午5:42:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("dgRechargeLiveOrder")
public class DGRechargeLiveOrder implements RechargeLiveOrderService<LivePayOrder>{

	// 初始化日志类
	private final Logger log = Logger.getLogger(DGRechargeLiveOrder.class);
	
	@Autowired
	private LiveLedgerServiceImpl liveLedgerService;
	
	@Autowired
	private LiveOrderDao liveOrderDao;
	
	@Autowired
	private LiveLedgerRecordDao liveLedgerRecordDao;
	
	@Autowired
	private DebitcardSellerDao debitcardSellerDao;
	
    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
    
    @Autowired
    private UrsEarningsRelationDao ursRelationDao;
    
	@Override
	public LivePayOrder initRechargeOrderLedger(LivePayOrder order) {
		/*
		 * VIP会员在"寻蜜鸟充值平台"上充值，可享受的奖励规则与此前规则一致：
		 * 1.同时最多只有一个订单可享受奖励
		 *     
		 * 2.每一个订单都可享受1倍的打赏奖励
		 * 
		 */
		
		try{
			/* 查看当前该用户在直销平台的奖励订单信息 */
			LivePrivilege ledgeringOrder = liveLedgerService.getObjectOrientedLivePayOrder(order.getUid(),order.getObjectOriented());
			
			/* 获取该订单所达到的等级信息 */
			LiveFansRank liveFansRank = liveLedgerService.getOrderLiveFansRank(order.getPayment(),1,order.getPayTime(),order.getObjectOriented());
			LiveFansRankDetail liveFansRankDetail = liveFansRank.getLiveFansRankDetail();
			/* 获取当前该用户的直播会员信息*/
			LiverBean curLiver = liveLedgerService.getLiverInfo(order.getUid());
			log.info("当前会员信息："+curLiver+",\r\n当前会员的直销分账订单信息："+ledgeringOrder+",\r\n该订单可达到的粉丝等级信息："+liveFansRank);
//			Integer objectOriented = curLiver.getObjectOriented();
//			if(objectOriented!= null && objectOriented==1){
//				log.info("这个会员怎么回事嘛，跑偏了，自己啥子奖励都没啦。。。");
//				initRechargeOrderLedgerInfo(order,liveFansRankDetail,false);
//				return null;
//			}
			
			if(ledgeringOrder==null || ledgeringOrder.getPayment().compareTo(order.getPayment())<0){
				liveLedgerService.updateUrsRankInfo((long)order.getUid(), liveFansRank.getId(), 3);
			}
			
			boolean isUpdateLiver = false;
			LiverBean uLiver  = new LiverBean();
			uLiver.setUid(curLiver.getUid());
//			if(curLiver.getObjectOriented()==null){
//				isUpdateLiver = true;
//				uLiver.setObjectOriented(3);
//			}
			
			Map<String,Object> reqMap = new HashMap<String,Object>();
			reqMap.put("uid", order.getUid());
			LivePrivilege livePrivilege = livePrivilegeDao.getHighestLedgerOrder(reqMap);
			if(livePrivilege==null || livePrivilege.getPayment().compareTo(order.getPayment())<0){
				isUpdateLiver = true;
				uLiver.setFansRankId(liveFansRank.getId());
				uLiver.setFansRankName(liveFansRank.getRankName());
				uLiver.setFansRankNo(liveFansRank.getRankNo());
			}

			if(isUpdateLiver){
				liveLedgerService.updateLiverInfo(uLiver);
			}
			
			if(ledgeringOrder==null 
				|| ledgeringOrder.getPrivilegeLedger().compareTo(liveLedgerService.mulNumberTwoPoint(order.getPayment(),liveFansRankDetail.getReferrerReward()))<=0){
				//若该用户当前没有正在进行奖励分账的订单，或新充值的订单可获取的奖励分账面额>正在进行奖励分账订单的面额
				//则将新充值的订单设置为正在进行奖励分账的订单，并据此新充值订单的面额调整用户的分账级别
				
				/*更新初始化该订单可得到的分账数据*/
				initRechargeOrderLedgerInfo(order,liveFansRankDetail,true);

			}else{
				/*更新初始化该订单可得到的分账数据*/
				initRechargeOrderLedgerInfo(order,liveFansRankDetail,false);
			}

		}catch(Exception e){
			log.error("充值订单更新用户信息失败",e);
		}

		return null;
	}

	
	@Override
	public void recommendOrderLedger(LivePayOrder order) {
		/*
		 * 会员在"寻蜜鸟充值平台"上充值，作为该会员的上级和上上级，可享受的推荐奖励规则与此前规则一致（以上级角色说明）：
		 * 1.会员本次充值的订单达到一定额度(1000)，才可给上级分推荐奖励
		 * 2.上级自身具有可获奖励的订单，且该订单的奖励金额(推荐+红包)尚没有拿满
		 * 3.上级享受推荐奖励时，收入将进入鸟币(进公共鸟币OR进商家专享鸟币)
		 */
		
		
		LiveLedgerRecord reqRecord = new LiveLedgerRecord();
		reqRecord.setOrderNo(order.getOrderNo());
		reqRecord.setLedgerSource(2);
		long count = liveLedgerRecordDao.countLedgerRecord(reqRecord);
		if(count>0){
			log.info("该订单已进行分账");
		}

		log.info("开始给上线计算分账操作。。。");
		BigDecimal minLedgerOrder = this.getMinRecommendLedgerPayment();
		if(order.getPayment().compareTo(minLedgerOrder)>=0){
			List<Map<String,Object>> list = liveLedgerService.getLiverLevel(order.getUid(),order.getObjectOriented());
			RecommendLedger recommendLedger = new RecommendLedger(order.getOrderNo(), order.getObjectOriented(), order.getUid(), order.getPayment());
			for(Map<String,Object> map:list){
				if((int)map.get("usertype")==1){
					LiverBean liver = liveLedgerService.getLiverInfo((int)map.get("id"));
					liveLedgerService.recommendLedger(liver, (int) map.get("recommendLevel"), recommendLedger);
					
					liveLedgerService.updateLiverFansRank(liver.getUid(),order.getObjectOriented());
				}else if((int)map.get("usertype")==2){
					if(true){
						log.info("商家只能获取商家会员在其所属商家充值的推荐奖励");
						continue;
					}
				}
			}
		}else{
			log.info("该订单金额不具备上级推荐返利资格");
		}

	}


	@Override
	public BigDecimal getMinRecommendLedgerPayment() {
		return new BigDecimal(10000);
	}
	
	/**
	 * 填充会员充值的该订单可返金额数据
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2016年12月21日
	 * @param payment
	 * @param liveFansRank void
	 */
	public void initRechargeOrderLedgerInfo(LivePayOrder order,LiveFansRankDetail liveFansRankDetail,boolean priviledgeLedger){
		LivePrivilege privilege = new LivePrivilege();
		privilege.setObjectOriented(order.getObjectOriented());
		privilege.setUid(order.getUid());
		privilege.setPayment(order.getPayment());
		privilege.setCreateTime(order.getPayTime());
		privilege.setUpdateTime(order.getPayTime());
		privilege.setDebitcardId(null);
		privilege.setQuota(null);
		
		privilege.setOrderNo(order.getOrderNo());
		privilege.setConsumeLedger(order.getPayment());
		privilege.setPrivilegeLedger(priviledgeLedger?
				liveLedgerService.mulNumberTwoPoint(order.getPayment(), liveFansRankDetail.getReferrerReward()):BigDecimal.ZERO);
		privilege.setTotalLedgerAmount(priviledgeLedger?
				LiveLedgerServiceImpl.keepTwoPoint(privilege.getConsumeLedger().add(privilege.getPrivilegeLedger())):privilege.getConsumeLedger());
		privilege.setCurrentConsumeLedger(BigDecimal.ZERO);
		privilege.setCurrentPrivilegeLedger(BigDecimal.ZERO);
		privilege.setCurrentDividendLedger(BigDecimal.ZERO);
		privilege.setCurrentDividendCoinLedger(BigDecimal.ZERO);
		int hasRecomend = 0;
		int hasDividend = 0;
		if(liveFansRankDetail.getReferrerReward()>0){
			hasRecomend = 1;
		}
		privilege.setHasRecomend(hasRecomend);	//该订单后以后是否可获得推荐奖励
		privilege.setHasDividend(hasDividend);	//该订单以后是否可获取每日分红奖励
		long level = liveFansRankDetail.getRankId();
		privilege.setLedgerLevel((int)level);
		livePrivilegeDao.initPrivilegeInfo(privilege);
		
		LiverJournalCount journal = new LiverJournalCount();
		journal.setUid(order.getUid());
		journal.setComsumLedger(privilege.getConsumeLedger());
		journal.setPrivilegeLedger(privilege.getPrivilegeLedger());
		journal.setOrderAmount(order.getPayment());
		journal.setExpectedPriviledgeLedger(liveLedgerService.getExpectedPriviledgeLedger(order.getUid()));
		liveLedgerService.updateLiverJournalCountInfo(journal);
	}


	
	@Override
	public void initUidRelation(LivePayOrder t) {
		UrsEarningsRelation relation = new UrsEarningsRelation(t.getUid(), t.getObjectOriented()); 
		if(null == ursRelationDao.getUrsEarningsRelation(relation)){
			relation.setCreateTime(new Date());
			relation.setUidRelationChain(String.format("%011d", t.getUid()));
			relation.setUidRelationChainLevel(1);
			relation.setUidRelationChainNname("");
			ursRelationDao.insertUrsEarningsRelation(relation);
		}		
	}
}
