/**    
 * 文件名：VerRechargeLiveOrder.java    
 *    
 * 版本信息：    
 * 日期：2017年2月23日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.DateUtil;
import com.xmniao.dao.live.LiveLedgerRecordDao;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.dao.live.TVerExcitationProjectDao;
import com.xmniao.dao.xmer.SaasOrderDao;
import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.live.LivePayOrder;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.LiverJournalCount;
import com.xmniao.domain.live.TVerExcitationProject;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.service.xmer.SaasOrderServiceImpl;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.urs.dao.UrsEarningsRelationDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：VerRechargeLiveOrder
 * 
 * 类描述： 食尚V客打赏分红服务
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月23日 下午5:40:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("verRechargeLiveOrder")
public class VerRechargeLiveOrder implements RechargeLiveOrderService<LivePayOrder> {

	// 初始化日志类
	private final Logger log = Logger.getLogger(VerRechargeLiveOrder.class);
	
	@Autowired
	private LiveLedgerServiceImpl liveLedgerService;
	
	@Autowired
	private LiveOrderDao liveOrderDao;
	
	@Autowired
	private LiveLedgerRecordDao liveLedgerRecordDao;
	
    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
    
    @Autowired
    private UrsEarningsRelationDao ursRelationDao;
	
    @Autowired
    private TVerExcitationProjectDao verExcitationProjectDao;
    
    @Autowired
    private SaasOrderDao saasOrderDao;
    
    @Autowired
    private SaasOrderServiceImpl  saasOrderService;
    
	@Override
	public LivePayOrder initRechargeOrderLedger(LivePayOrder order) {
		/*
		 * 在"V客平台"上充值，奖励规则：
		 * 1.同时最多只有一个订单可享受奖励
		 *     
		 * 2.每一个订单都可享受1倍的打赏奖励
		 * 
		 * 3.每一个订单可享的推荐奖励不限倍数
		 * 
		 */
		
		try{
			
			/* 查看当前该用户的在该平台奖励订单信息 */
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("uid", order.getUid());
			map.put("objectOriented", order.getObjectOriented());
			LivePrivilege ledgeringOrder = livePrivilegeDao.getLiveOrderByLedger(map);
			
			/* 获取该订单所达到的等级信息 */
			LiveFansRank liveFansRank = liveLedgerService.getOrderLiveFansRank(order.getPayment(),2,order.getPayTime(),order.getObjectOriented());
			LiveFansRankDetail liveFansRankDetail = liveFansRank.getLiveFansRankDetail();
			/* 获取当前该用户的直播会员信息*/
			LiverBean curLiver = liveLedgerService.getLiverInfo(order.getUid());
			log.info("当前会员信息："+curLiver+",当前会员的V客平台分账订单信息："+ledgeringOrder+",该订单可达到的粉丝等级信息："+liveFansRank);
		
			if(ledgeringOrder==null || ledgeringOrder.getPayment().compareTo(order.getPayment())<0){
				liveLedgerService.updateUrsRankInfo((long)order.getUid(), liveFansRank.getId(), 4);
			}
			
			/*补相应的SAAS名额*/
			int replenishNumber = replenishSaasNumber(order.getUid(), 3, liveFansRank.getSaasNumber());
			log.info("本次V客充值订单可获取得SAAS数量:"+replenishNumber+"个");
			int period =  this.getPeriodExcitation(order.getExcitationProject(),(int)((long)liveFansRank.getId()));
			
			if(ledgeringOrder==null 
				|| ledgeringOrder.getPayment().compareTo(order.getPayment())<=0){
				//若该用户当前没有正在进行奖励分账的订单，或新充值的订单可获取的奖励分账面额>正在进行奖励分账订单的面额
				//则将新充值的订单设置为正在进行奖励分账的订单，并据此新充值订单的面额调整用户的分账级别
				
				/*更新初始化该订单可得到的分账数据*/

				initRechargeOrderLedgerInfo(order,liveFansRankDetail,true,period);

			}else{
				/*更新初始化该订单可得到的分账数据*/
				initRechargeOrderLedgerInfo(order,liveFansRankDetail,false,period);
			}

		}catch(Exception e){
			log.error("充值订单更新用户信息失败",e);
		}

		return null;
	}

	
	@Override
	public void recommendOrderLedger(LivePayOrder order){
		/*
		 * 1.V客享受推荐奖励无限额
		 * 2.只有自身有V客充值订单，只可享下级的推荐奖励
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
			for(Map<String,Object> map:list){
				if((int)map.get("usertype")==1){
					LiverBean liver = liveLedgerService.getLiverInfo((int)map.get("id"));
					this.recommendLedgerByVer(liver, (int) map.get("recommendLevel"), order);
					liveLedgerService.updateLiverFansRank(liver.getUid(),order.getObjectOriented());
				}else if((int)map.get("usertype")==2){
					log.info("商户下的会员充值成为V客，商户将不能获取奖励");
				}
			}
		}else{
			log.info("该订单金额不具备上级推荐返利资格");
		}

	}
	/**
	 * 填充会员充值的该订单可返金额数据
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年4月6日
	 * @param payment
	 * @param liveFansRank void
	 */
	public void initRechargeOrderLedgerInfo(LivePayOrder payOrder,LiveFansRankDetail liveFansRankDetail,boolean priviledgeLedger,int period){
		LivePrivilege order = new LivePrivilege();
		order.setObjectOriented(4);
		order.setUid(payOrder.getUid());
		order.setPayment(payOrder.getPayment());
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setDebitcardId(null);
		order.setQuota(null);
		order.setLedgerLevel(null);
		
		order.setOrderNo(payOrder.getOrderNo());
		order.setConsumeLedger(payOrder.getRealCoin().multiply(new BigDecimal("0.1")));
		order.setPrivilegeLedger(priviledgeLedger?payOrder.getPayment():BigDecimal.ZERO);
		order.setTotalLedgerAmount(order.getConsumeLedger().add(order.getPrivilegeLedger()));
		order.setCurrentConsumeLedger(BigDecimal.ZERO);
		order.setCurrentPrivilegeLedger(BigDecimal.ZERO);
		order.setCurrentDividendLedger(BigDecimal.ZERO);
		order.setCurrentDividendCoinLedger(BigDecimal.ZERO);
		int hasRecomend = 0;
		int hasDividend = 0;
		if(liveFansRankDetail.getReferrerReward()>0){
			hasRecomend = 1;
		}
		order.setHasRecomend(hasRecomend);	//该订单后以后是否可获得推荐奖励
		order.setHasDividend(hasDividend);	//该订单以后是否可获取每日分红奖励
		long level = liveFansRankDetail.getRankId();
		order.setLedgerLevel((int)level);
		order.setPeriodExcitation(period);
		order.setCurPeriodExcitation(0);
		livePrivilegeDao.initPrivilegeInfo(order);
		
		LiverJournalCount journal = new LiverJournalCount();
		journal.setUid(payOrder.getUid());
		journal.setComsumLedger(payOrder.getRealCoin().multiply(new BigDecimal("0.1")));
		journal.setOrderAmount(payOrder.getPayment());
		liveLedgerService.updateLiverJournalCountInfo(journal);
	}
	
	public BigDecimal getMinRecommendLedgerPayment(){
		return new BigDecimal("0");
	}
	
    /**
     * 
     * 方法描述：给上级进行推荐奖励分账 
     * 创建人： ChenBo
     * 创建时间：2017年1月2日
     * @param liver
     * @param level
     * @param liveOrder
     * @return LiveLedgerRecord
     */
    public LiveLedgerRecord recommendLedgerByVer(LiverBean liver,int level,LivePayOrder liveOrder){
    	/*
    	 * 上级可能同时存多个充值订单，且都具备享受推荐资格，需拿到其中在返且达到等级最高的那笔订单，
    	 * 此时该订单就按这个最高等级的推荐比享受推荐金额，在享受推荐金额时，对多个订单，依次按充值订单金额大->小进行返，
    	 * 当大金额订单返满时，继续返小订单金额，同时，上级自身的粉丝等级也需调整为小订单金额对应的等级，共涉及
    	 * 1.查询上级所有正在享受奖励分账的订单；
    	 * 2.查询上级当前最大金额订单等级的推荐比例
    	 * 3.更新上级自身已享受的订单奖励变化
    	 * 4.调整上级自己已享受推荐奖励后的等级变化
    	 * 
    	 */
    	
    	Map<String,Object> map = new HashMap<>();
    	map.put("uid", liver.getUid());
    	map.put("objectOriented", 4);
    	LivePrivilege  order = livePrivilegeDao.getLiveOrderByLedger(map);
    	if(order==null){  
    		log.info("该用户没有在奖励分账订单");
    		return null;
    	}
    	
    	
    	LiveFansRank rank = liveLedgerService.getOrderApplyLiveFansRank(order.getLedgerLevel(), order.getCreateTime(),order.getObjectOriented());
    	BigDecimal orderMaxLedger = liveLedgerService.getOrderMaxLedger(liveOrder.getPayment(), rank.getLiveFansRankDetail(), level);
    	boolean isCash = rank.getLiveFansRankDetail().getReferrerLedgerType()==1;	//true.推荐得现金   false.推荐得鸟币
		//本次分账全部分给该订单
    	Map<String,BigDecimal> resultMap = this.updateHighLevelLedgerOrder(order, orderMaxLedger);
		BigDecimal ledgerAmount = resultMap.get("ledgerAmount");
		if(ledgerAmount.compareTo(BigDecimal.ZERO)>0){
			LiveLedgerRecord liveLedgerRecord=null;
			if(isCash){
				liveLedgerRecord = liveLedgerService.insertRecommendLedgerRecord(liver.getUid(),level+1,orderMaxLedger,ledgerAmount,BigDecimal.ZERO,BigDecimal.ZERO,liveOrder.getOrderNo(),liveOrder.getObjectOriented(),"V客充值推荐奖励",liveOrder.getUid());
			}else{
				liveLedgerRecord = liveLedgerService.insertRecommendLedgerRecord(liver.getUid(),level+1,BigDecimal.ZERO,BigDecimal.ZERO,orderMaxLedger,ledgerAmount,liveOrder.getOrderNo(),liveOrder.getObjectOriented(),"V客充值推荐奖励",liveOrder.getUid());
			}
				
			List<Map<String,String>> ledgerList = this.getVerLedgerMQMap(liveLedgerRecord,isCash);
			try {
				liveLedgerService.sendLedgerMsg(ledgerList);
			} catch (Exception e) {
				log.error("发送分账消息失败",e);
			}
			return liveLedgerRecord;
		}
		return null;
    }
    
	/**
	 * 
	 * 方法描述：计算上级可获取的推荐金额，并更新上级的奖励订单分账信息
	 * 创建人： ChenBo
	 * 创建时间：2017年2月27日
	 * @param uid -uid
	 * @param orderAmount 参与分账的订单金额
	 * @return Map<String,Object>
	 */
	public Map<String,BigDecimal> updateHighLevelLedgerOrder(LivePrivilege order,BigDecimal orderMaxLedger){
		Map<String,BigDecimal> resultMap = new HashMap<String,BigDecimal>();
		BigDecimal ledgerAmount = orderMaxLedger;//本次可得到的分账值

		//本次分账全部分给该订单
		LivePrivilege uOrder = new LivePrivilege();
		uOrder.setId(order.getId());
		uOrder.setCurrentPrivilegeLedger(order.getCurrentPrivilegeLedger().add(ledgerAmount));
		livePrivilegeDao.updateLiveOrderLedger(uOrder);

		resultMap.put("ledgerAmount", ledgerAmount);
		return resultMap;
	}
	
	private List<Map<String,String>> getVerLedgerMQMap(LiveLedgerRecord record,boolean isCash){
		List<Map<String,String>> ledgerList = new ArrayList<Map<String,String>>();
		Map<String,String> ledgerMap = new HashMap<String,String>();
		if(isCash){
			ledgerMap.put("uId", record.getUid()+"");
			ledgerMap.put("userType", "1");
			ledgerMap.put("option", "0");
			ledgerMap.put("rType", "1");
			ledgerMap.put("commision", record.getLedgerAmount()+"");
			ledgerMap.put("orderId", record.getOrderNo()+"_"+(record.getUidRole()-1));
			ledgerMap.put("remark", record.getLedgerUid()+"");
			ledgerMap.put("recordid", record.getId()+"");
			ledgerMap.put("ledgerType", "1");//普通钱包
			
		}else{
			ledgerMap.put("uid", record.getUid()+"");
			ledgerMap.put("option", "0");
			ledgerMap.put("rtype", "14");
			ledgerMap.put("zbalance", record.getLedgerCoin()+"");
			ledgerMap.put("orderId", record.getId()+"");
			ledgerMap.put("remarks", record.getOrderNo()+"_"+(record.getUidRole()-1));
			ledgerMap.put("description", record.getLedgerUid()+"");
			ledgerMap.put("recordid", record.getId()+"");
			ledgerMap.put("ledgerType", "2");//直播钱包
			
		}
						
		ledgerList.add(ledgerMap);
		return ledgerList;
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
	
	/**
	 * 
	 * 方法描述：初始化V客奖励期数<br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月10日下午4:18:34 <br/>
	 */
	public int getPeriodExcitation(String excitationProject,int rankId){
		if(StringUtils.isBlank(excitationProject)){
			return 0;
		}
		try{
		TVerExcitationProject project = new TVerExcitationProject();
		project.setProjectName(excitationProject);
		project.setRankId(rankId);
		project.setStatus((byte)0);
		return verExcitationProjectDao.getObject(project).getPeriod();
		}catch(Exception e){
			log.error("获取会员奖励方案异常",e);
			return 0;
		}
	} 
	
	/**
	 * 
	 * 方法描述：给V客补SAAS套餐订单(num) <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年8月9日下午9:00:42 <br/>
	 * @param uid
	 * @param saasChannel 补充的SAAS的类型
	 * @param num 最多本次可获得的SAAS套餐
	 * @return 实际补的SAAS套餐数
	 */
	public int replenishSaasNumber(int uid,int saasChannel,int num){
		int hasNum = saasOrderDao.sumSaasNumber(uid, saasChannel);
		if(hasNum>=num){
			return 0;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		StringBuffer sb = new StringBuffer("01").append(sdf.format(new Date())).append((int)(Math.random()*9000)+1000); 
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ordersn", sb.toString());
		map.put("uid", uid);
		map.put("nums", num-hasNum);
		map.put("status", 0);
		map.put("sdate", new Date());
		map.put("soldnums", 0);
		map.put("stock", num-hasNum);
		map.put("returnnums", 0);
		map.put("saasChannel", saasChannel);
		
		saasOrderDao.insertVkeSaasOrder(map);
		
		
		Map<String,String> paraMap = new HashMap<String, String>();
		paraMap.put("bid", sb.toString());
		paraMap.put("status", "1");
		paraMap.put("rtype", "2");
//		paraMap.put("", "");
//		paraMap.put("", "");
//		paraMap.put("", "");
//		paraMap.put("", "");
//		paraMap.put("", "");
//		paraMap.put("", "");
		try {
			saasOrderService.modifyXmerOrderInfo(paraMap);
		} catch (FailureException e) {
			log.error("给V客SAAS资格异常");
		} catch (TException e) {
			e.printStackTrace();
			log.error("给V客SAAS资格异常");
		}
		return num-hasNum;
	}

}
