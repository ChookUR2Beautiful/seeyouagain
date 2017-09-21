/**    
 * 文件名：ManorRechargeLiveOrder.java    
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.LivePayOrder;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.LiverJournalCount;


/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：ManorRechargeLiveOrder
 * 
 * 类描述：黄金庄园充值打赏分红版块
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月23日 下午5:40:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("manorRechargeLiveOrder")
public class ManorRechargeLiveOrder implements RechargeLiveOrderService<LivePayOrder> {

	// 初始化日志类
	private final Logger log = Logger.getLogger(ManorRechargeLiveOrder.class);
	
	@Autowired
	private LiveLedgerServiceImpl liveLedgerService;
	
	@Autowired
	private LiveOrderDao liveOrderDao;
	
    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
    
	@Override
	public LivePayOrder initRechargeOrderLedger(LivePayOrder order) {
		initRechargeOrderLedgerInfo(order,null);
		liveLedgerService.countUserEnergyNum(order.getUid(), order.getPayment(),order.getOrderNo(),5);
		return null;
	}

	
	@Override
	public void recommendOrderLedger(LivePayOrder order){
		//log.info("常规充值，没有推荐奖励");
	}

	public BigDecimal getMinRecommendLedgerPayment(){
		return BigDecimal.ZERO;
	}
	
	/**
	 * 填充会员充值的该订单可返金额数据
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年4月11日
	 * @param payment
	 * @param liveFansRank void
	 */
	public void initRechargeOrderLedgerInfo(LivePayOrder payOrder,LiveFansRankDetail liveFansRankDetail){
		LivePrivilege livePrivilege = new LivePrivilege();
		
		livePrivilege.setObjectOriented(5);
		livePrivilege.setUid(payOrder.getUid());
		livePrivilege.setPayment(payOrder.getPayment());
		livePrivilege.setCreateTime(new Date());
		livePrivilege.setUpdateTime(new Date());
		livePrivilege.setDebitcardId(null);
		livePrivilege.setQuota(null);
		livePrivilege.setLedgerLevel(null);
		
		livePrivilege.setOrderNo(payOrder.getOrderNo());
		livePrivilege.setConsumeLedger(payOrder.getRealCoin().multiply(new BigDecimal("0.1")));
		livePrivilege.setPrivilegeLedger(BigDecimal.ZERO);
		livePrivilege.setTotalLedgerAmount(livePrivilege.getConsumeLedger());
		livePrivilege.setCurrentConsumeLedger(BigDecimal.ZERO);
		livePrivilege.setCurrentPrivilegeLedger(BigDecimal.ZERO);
		livePrivilege.setCurrentDividendLedger(BigDecimal.ZERO);
		livePrivilege.setCurrentDividendCoinLedger(BigDecimal.ZERO);
		livePrivilege.setHasRecomend(0);	
		livePrivilege.setHasDividend(0);	

		livePrivilegeDao.initPrivilegeInfo(livePrivilege);
				
		LiverJournalCount journal = new LiverJournalCount();
		journal.setUid(payOrder.getUid());
		journal.setComsumLedger(payOrder.getRealCoin().multiply(new BigDecimal("0.1")));
		journal.setOrderAmount(payOrder.getPayment());
		liveLedgerService.updateLiverJournalCountInfo(journal);
	}

	@Override
	public void initUidRelation(LivePayOrder t) {
		
	}
}
