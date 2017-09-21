/**    
 * 文件名：SellerRechargeLiveOrder.java    
 *    
 * 版本信息：    
 * 日期：2017年2月23日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.dao.live.DebitcardOrderDao;
import com.xmniao.dao.live.DebitcardSellerDao;
import com.xmniao.dao.live.LiveLedgerRecordDao;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.domain.live.DebitcardOrder;
import com.xmniao.domain.live.DebitcardSeller;
import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.live.LivePayOrder;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.LiverJournalCount;
import com.xmniao.domain.live.RecommendLedger;
import com.xmniao.domain.order.XmnWalletBean;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.service.order.OrderServiceImpl;
import com.xmniao.thrift.pay.ResponseData;
import com.xmniao.urs.dao.UrsEarningsRelationDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerRechargeLiveOrder
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年2月23日 下午5:39:29 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service("sellerRechargeLiveOrder")
public class SellerRechargeLiveOrder implements RechargeLiveOrderService<LivePayOrder>{

	// 初始化日志类
	private final Logger log = Logger.getLogger(SellerRechargeLiveOrder.class);
	
	@Autowired
	private LiveLedgerServiceImpl liveLedgerService;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	private LiveOrderDao liveOrderDao;
	
	@Autowired
	private CommonServiceImpl commonServiceDao;
	
	@Autowired
	private LiveLedgerRecordDao liveLedgerRecordDao;
	
	@Autowired
	private DebitcardSellerDao debitcardSellerDao;
	
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
    
    @Autowired
    private DebitcardOrderDao debitcardOrderDao;
    
    @Autowired
    private UrsEarningsRelationDao ursRelationDao;
    
    private String uidReturnSellerCoinKey="key:coin:sellerCoin:";
	@Override
	public LivePayOrder initRechargeOrderLedger(LivePayOrder order) {
		this.initRechargeOrderLedgerInfo(order.getUid(),order.getOrderNo(),order.getPayment(),null,false);;
		return order;

	}

	
	@Override
	public void recommendOrderLedger(LivePayOrder order) {
		return;
	}


	/**
	 * 
	 * 方法描述：当商家作为充值会员的上(上)级时，给商家进行现金分账
	 * 创建人： ChenBo
	 * 创建时间：2017年2月27日
	 * @param sellerid
	 * @param recomendRtaio
	 * @param payment void
	 */
	public BigDecimal recomendOrderLedgerForSeller(int sellerid,BigDecimal recomendRtaio,BigDecimal payment){
		return payment.multiply(recomendRtaio);
	}
		
	
	@Override
	public BigDecimal getMinRecommendLedgerPayment() {
		return new BigDecimal(500);
	}
	
	/**
	 * 
	 * 方法描述：给用户充值商家额度
	 * 创建人： ChenBo
	 * 创建时间：2017年3月1日
	 * @param debitcardQuota void
	 * @throws Exception 
	 */
	public void rechargeSellerPreQuota(LivePayOrder order,String phone) throws Exception{
		//充值额度
		Map<String,String> quotaMap = new HashMap<String,String>();
		quotaMap.put("uid", order.getUid()+"");
		quotaMap.put("phone", phone==null?"":phone);
		quotaMap.put("sellerid", order.getSellerid()+"");
		quotaMap.put("rtype", "1");
		quotaMap.put("remarks", order.getOrderNo()+"_1");
		quotaMap.put("quota", order.getPayment()+"");
		quotaMap.put("sellername", order.getSellername()+"");
		quotaMap.put("sellertype", order.getSellertype()+"");
		quotaMap.put("option", "1");
		log.info("对本次充值给会员"+order.getUid()+"充值"+order.getPayment()+"额度");
		boolean code = commonServiceDao.updateDebitcardQuota(quotaMap);
		if(!code){
			log.error("给用户充值商家额度失败");
		}
	}
	
	/**
	 * 
	 * 方法描述：更新订单给商家分账信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param uid
	 * @param orderNo
	 * @param payment
	 * @param liveFansRankDetail
	 * @param priviledgeLedger void
	 */
	public void initDebitcardOrderInfo(Integer id,BigDecimal sellerIncome,LiveFansRankDetail liveFansRankDetail){
		DebitcardOrder order = new DebitcardOrder();
		order.setId(id);
		order.setSellerIncome(sellerIncome);
		order.setOffsetSellerIncome(BigDecimal.ZERO);
		long level = liveFansRankDetail.getRankId();
		order.setLedgerLevel((int)level);
		debitcardOrderDao.initPreLedgerInfo(order);
	}
	/**
	 * 填充会员充值的该订单可返金额数据
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2016年12月21日
	 * @param payment
	 * @param liveFansRank void
	 */
	public void initDebitcardOrderLedgerInfo(DebitcardOrder order,LiveFansRankDetail liveFansRankDetail,boolean priviledgeLedger){
		LivePrivilege privilege = new LivePrivilege();
		
		privilege.setObjectOriented(2);
		privilege.setUid(order.getUid());
		privilege.setPayment(order.getPayCoin());
		privilege.setCreateTime(order.getPayTime());
		privilege.setUpdateTime(order.getPayTime());
		privilege.setDebitcardId(order.getDebitcardId());
		privilege.setQuota(order.getDenomination());
		
		privilege.setOrderNo(order.getOrderNo());
		privilege.setConsumeLedger(BigDecimal.ZERO);
		privilege.setPrivilegeLedger(priviledgeLedger?liveLedgerService.mulNumberTwoPoint(order.getPayCoin(), liveFansRankDetail.getReferrerReward()):BigDecimal.ZERO);
		privilege.setTotalLedgerAmount(priviledgeLedger?LiveLedgerServiceImpl.keepTwoPoint(privilege.getConsumeLedger().add(privilege.getPrivilegeLedger())):privilege.getConsumeLedger());
		privilege.setCurrentConsumeLedger(BigDecimal.ZERO);
		privilege.setCurrentPrivilegeLedger(order.getDenomination().compareTo(order.getPayCoin())>0?
				order.getDenomination().subtract(order.getPayCoin()):BigDecimal.ZERO);
		privilege.setCurrentDividendLedger(BigDecimal.ZERO);
		privilege.setCurrentDividendCoinLedger(privilege.getCurrentPrivilegeLedger());

		int hasRecomend = 0;
		int hasDividend = 0;
		privilege.setHasRecomend(hasRecomend);	//该订单后以后是否可获得推荐奖励
		privilege.setHasDividend(hasDividend);	//该订单以后是否可获取每日分红奖励
		long level = liveFansRankDetail.getRankId();
		privilege.setLedgerLevel((int)level);
		livePrivilegeDao.initPrivilegeInfo(privilege);

		
		LiverJournalCount journal = new LiverJournalCount();
		journal.setUid(order.getUid());
		journal.setComsumLedger(privilege.getConsumeLedger());
		journal.setPrivilegeLedger(privilege.getPrivilegeLedger());
		journal.setOrderAmount(order.getPayCoin());
		journal.setExpectedPriviledgeLedger(liveLedgerService.getExpectedPriviledgeLedger(order.getUid()));
		liveLedgerService.updateLiverJournalCountInfo(journal);
	}
	
	/**
	 * 因会员在商家平台充值储值卡，需立即给商家进行订单额*70%的分账收入。
	 * 那么所有会员在此后在该店进行消费时(得鸟豆，最终是得鸟币，鸟币可代替钱进行消费)，
	 * 原有逻辑也需给商家进行分账，对此，如不做处理，将造成订单的二次分账。
	 * 为解决以上情况现给出解决方案：
	 * 1.会员充值订单新增字段'offset_seller_income'，记录后续所有会员在该商家消费对商家产生的分账金额
	 * 2.在商家进行消费后(含所有需分账的订单类型)，将给商家分账的金额扣除，并累计到所有商家的offset_seller_income字段
	 * 3.当该商家的所有充值订单offset_seller_income都达到上限(即订单金额的70%)时，才允许后续的订单给商家进行真实的分账
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年3月7日
	 * @param sellerid
	 * @param uid
	 * @param ledgerAmount
	 * @return BigDecimal
	 */
	public BigDecimal getSellerRechargeOrderSurplus(Integer debitcardId,Integer uid,BigDecimal ledgerAmount){
		DebitcardOrder order = new DebitcardOrder();
		order.setDebitcardId(debitcardId);
		order.setUid(uid);
		BigDecimal allSellerRechargeOrderMoney =  debitcardOrderDao.countSellerOrder(order);
		return allSellerRechargeOrderMoney==null?new BigDecimal(0):allSellerRechargeOrderMoney;
	}
	
	public List<DebitcardOrder> getSellerRechargeOrderSurplusList(Integer debitcardId,Integer uid){
		DebitcardOrder order = new DebitcardOrder();
		order.setDebitcardId(debitcardId);
		order.setUid(uid);
		return  debitcardOrderDao.getSellerRechargeList(order);
	}
	
	public BigDecimal updateSellerOrderSurplus(Integer sellerid,Integer uid,BigDecimal ledgerAmount){

		log.info("用户【"+uid+"】在商家消费，商家【"+sellerid+"】理论可获取分账收入【"+ledgerAmount+"】现计算其实际可获取分账收入");
		
		List<DebitcardSeller> debitcardSellerList = debitcardSellerDao.getDebitcardForSellerid(sellerid);
		if(debitcardSellerList ==null || debitcardSellerList.size()==0){
			log.info("这个商家不参与储值卡业务哦");
			return ledgerAmount;
		}
		for(DebitcardSeller debitcardSeller:debitcardSellerList){
			ledgerAmount = updateSellerOrderSurplus2(sellerid,uid,ledgerAmount,debitcardSeller);
		}
		return ledgerAmount;
	}
	/**
	 * 
	 * 方法描述：检测商家预付分账金额的抵消买情况
	 * 创建人： ChenBo
	 * 创建时间：2017年3月7日
	 * @param sellerid
	 * @param ledgerAmount
	 * @return BigDecimal
	 */
	public BigDecimal updateSellerOrderSurplus2(Integer sellerid,Integer uid,BigDecimal ledgerAmount,DebitcardSeller debitcardSeller){
		log.info("商家【"+sellerid+"】理论可获取分账收入【"+ledgerAmount+"】，现计算其实际可获取分账收入");
		
		if(debitcardSeller ==null){
			log.info("这个商家不参与储值卡业务哦");
			return ledgerAmount;
		}
		if(debitcardSeller.getSellertype()==3/* && debitcardSeller.getSellerid()!=sellerid*/){
			log.info("区域代理总店开的储值卡，本商家概不负责哦，不要影响我分账");
			return ledgerAmount;
		}
		if(debitcardSeller.getSellertype()!=1){
			String[] subSellers = debitcardSeller.getSubSellerid().split(",");
			List<String> list = Arrays.asList(subSellers); 
			if(list.indexOf(sellerid+"") == -1){
				log.info("该商家不参与储值卡业务");
				return ledgerAmount;
			}
		}
		BigDecimal ledgerAmountMax = ledgerAmount;
		BigDecimal surplus = this.getSellerRechargeOrderSurplus(debitcardSeller.getId(),uid, ledgerAmount);
		if(surplus.compareTo(BigDecimal.ZERO)>0){
			List<DebitcardOrder> list = this.getSellerRechargeOrderSurplusList(debitcardSeller.getId(), uid);
			for(DebitcardOrder order:list){
				BigDecimal temp = order.getSellerIncome().subtract(order.getOffsetSellerIncome());
				BigDecimal orderOffset = BigDecimal.ZERO;
				if(ledgerAmount.compareTo(temp)>=0){
					orderOffset = temp;
				}else{
					orderOffset = ledgerAmount;
				}
				ledgerAmount = ledgerAmount.subtract(orderOffset);
				if(orderOffset.compareTo(BigDecimal.ZERO)==0){
					break;
				}else{
					Map<String,Object> uMap = new HashMap<String,Object>();
					uMap.put("orderNo", order.getOrderNo());
					uMap.put("oldOffsetSellerIncome", order.getOffsetSellerIncome());
					uMap.put("offsetSellerIncome", orderOffset);
					int result = debitcardOrderDao.updateSellerOrderOffset(uMap);
					if(result==0){
						log.error("更新失败");
					}
				}
			}
		}
		log.info("商家【"+sellerid+"】理论可获取分账收入【"+ledgerAmountMax+"】，经抵消商家储值卡预分账金额，实际可获取分账收入【"+ledgerAmount+"】");
		return ledgerAmount;
	}
	
	/**
	 * 
	 * 方法描述：本次奖励的鸟币，需奖励到商家专享鸟币的金额
	 * 		观众打赏，得到鸟币优先退回商家专享鸟币，即查询该用户所有商家储值卡订单的打赏鸟币返还情况
	 * 创建人： ChenBo
	 * 创建时间：2017年3月8日
	 * @return BigDecimal
	 */
	public BigDecimal returnToSellerCoinPrivate(Integer uid,BigDecimal coin){
		if(coin==null || coin.compareTo(BigDecimal.ZERO)==0){
			return BigDecimal.ZERO;
		}
		List<LivePrivilege> list = livePrivilegeDao.getSellerCoinOrderList(uid);
		if(list==null || list.size()==0){
			return BigDecimal.ZERO;
		}

		BigDecimal sellerCoin = BigDecimal.ZERO;
		BigDecimal tempCoin = BigDecimal.ZERO;
		BigDecimal orderCoin = BigDecimal.ZERO;
		for(LivePrivilege order:list){
			if(order.getDebitcardId()==null){
				log.info("充值订单【"+order.getOrderNo()+"】时的储值卡ID为空，其打赏奖励鸟币部分进入公共鸟币部分");
				continue;
			}
			DebitcardSeller reqSeller = new DebitcardSeller();
			reqSeller.setId(order.getDebitcardId());
			reqSeller.setStatus(0);
			DebitcardSeller debitcardSeller = debitcardSellerDao.getDebitcardSeller(reqSeller);
			if(debitcardSeller==null){
				log.info("充值订单【"+order.getOrderNo()+"】时的所属商家已取消储值卡业务，其打赏奖励鸟币部分进入公共鸟币部分");
				continue;
			}
			tempCoin = order.getPayment().subtract(order.getCurrentConsumeLedger());
			if(tempCoin.compareTo(coin)<0){
				orderCoin = tempCoin;
			}else{
				orderCoin = coin;
			}
			coin= coin.subtract(orderCoin);
			sellerCoin = sellerCoin.add(orderCoin);
			if(orderCoin.compareTo(BigDecimal.ZERO)>0){
				LivePrivilege uOrder = new LivePrivilege();
				uOrder.setId(order.getId());
				uOrder.setCurrentConsumeLedger(orderCoin.add(order.getCurrentConsumeLedger()));
				livePrivilegeDao.updateLiveOrderLedger(uOrder);
			}else{
				break;
			}
		}
		return sellerCoin;
	}
	
	public BigDecimal returnToSellerCoin(Integer uid,BigDecimal coin){
//		BigDecimal rtsc = BigDecimal.ZERO;
//		List<LivePayOrder> list = liveOrderDao.getSellerCoinOrderList(uid);
//		if(list==null || list.size()==0){
//			return BigDecimal.ZERO;
//		}
//		try{
//			getLock(uid,2000);
			return returnToSellerCoinPrivate(uid,coin);
//		}catch(Exception e){
//			log.error("给用户增加商家专享鸟币失败",e);
//		}finally{
//			releaseLock(uid);
//		}
//		return rtsc;
	} 
	
	/**
	 * 
	 * 方法描述：
	 * 	     打赏返还鸟币时，在商家途径充值的订单，返的鸟币可用于购买储值卡，在VIP或直销途径充值的订单，返的鸟币不可用于购买储值卡
	 * 创建人： ChenBo
	 * 创建时间：2017年3月8日
	 * @return BigDecimal
	 */
	public BigDecimal returnToAvailableExchangeCoin(Integer uid,BigDecimal coin){
		if(coin==null || coin.compareTo(BigDecimal.ZERO)==0){
			return BigDecimal.ZERO;
		}
		List<LivePrivilege> list = livePrivilegeDao.getSellerCoinOrderList(uid);
		if(list==null || list.size()==0){
			return BigDecimal.ZERO;
		}

		BigDecimal availableExchangeCoin = BigDecimal.ZERO;
		BigDecimal tempCoin = BigDecimal.ZERO;
		BigDecimal orderCoin = BigDecimal.ZERO;
		for(LivePrivilege order:list){
//			if(order.getDebitcardId()==null){
//				log.info("充值订单【"+order.getOrderNo()+"】时的储值卡ID为空，其打赏奖励鸟币部分进入公共鸟币部分");
//				continue;
//			}
//			DebitcardSeller reqSeller = new DebitcardSeller();
//			reqSeller.setId(order.getDebitcardId());
//			reqSeller.setStatus(0);
//			DebitcardSeller debitcardSeller = debitcardSellerDao.getDebitcardSeller(reqSeller);
//			if(debitcardSeller==null){
//				log.info("充值订单【"+order.getOrderNo()+"】时的所属商家已取消储值卡业务，其打赏奖励鸟币部分进入公共鸟币部分");
//				continue;
//			}
			tempCoin = order.getConsumeLedger().subtract(order.getCurrentConsumeLedger());
			if(tempCoin.compareTo(coin)<0){
				orderCoin = tempCoin;
			}else{
				orderCoin = coin;
			}
			coin= coin.subtract(orderCoin);
			availableExchangeCoin = availableExchangeCoin.add(orderCoin);
			if(orderCoin.compareTo(BigDecimal.ZERO)>0){
				LivePrivilege uOrder = new LivePrivilege();
				uOrder.setId(order.getId());
				uOrder.setCurrentConsumeLedger(orderCoin.add(order.getCurrentConsumeLedger()));
				livePrivilegeDao.updateLiveOrderLedger(uOrder);
			}else{
				break;
			}
		}
		return availableExchangeCoin;
	}
	 
	public boolean getLock(int uid,long timeout){
		boolean getLock= false;
		long t=0;
		long s = System.currentTimeMillis();
		try {
			while(true){
				getLock = redisTemplate.boundValueOps(uidReturnSellerCoinKey + uid).increment(1) == 1;
				if(getLock){
					break;
				}else{
					if(t>timeout){
						break;
					}
					Thread.sleep(10);
					t=t+10;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info(Thread.currentThread().getName()+",uid:"+uid+",是否获得锁:"+getLock+",等待时长："+(System.currentTimeMillis()-s));
		return getLock;
	}
	public void releaseLock(int uid){
		log.info(Thread.currentThread().getName()+",uid:"+uid+"释放锁");
		redisTemplate.delete(uidReturnSellerCoinKey + uid);
	}
	
	/**
	 * 
	 * 方法描述：兑换储值初始化兑换会员信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param debitcardOrder
	 * @return LivePayOrder
	 */
	public DebitcardOrder initExchangeDebitcardLedger(DebitcardOrder debitcardOrder) {
		/*
		 * 会员在"商家充值平台(即买鸟粉专享卡)"上充值，可享受规则：
		 * 1.每一个订单达到可享奖励的最低标准后，即可享受对应奖励
		 *     
		 * 2.每一个订单都可享受1倍的打赏奖励
		 * 
		 */

		try{
			Integer uid=debitcardOrder.getUid();
//			LiverBean curLiver = liveLedgerService.getLiverInfo(uid);
			
			UrsEarningsRelation curRelation = liveLedgerService.getUrsRelation(new UrsEarningsRelation(uid, 2));
			//1.用户加商家鸟币(已移到扣公共鸟币时同步增加)
			//this.rechargeSellerCoin(debitcardOrder);
			
			//用户加额度
			this.rechargeSellerQuota(debitcardOrder,debitcardOrder.getPhone());
			
			
			//2.商家订单分账
			this.rechargeOrderLedgerForSeller(debitcardOrder);
					
			/* 获取该订单所达到的等级信息 */
			LiveFansRank liveFansRank = null;
			try{
				liveFansRank = liveLedgerService.getOrderLiveFansRank(debitcardOrder.getPayCoin(),1,debitcardOrder.getPayTime(),2);
			}catch(Exception e){
				//先升等级。。。
				liveFansRank=liveLedgerService.getLiveFansRankBase(debitcardOrder.getPayCoin(),1);
			}
			LiveFansRankDetail liveFansRankDetail = liveFansRank.getLiveFansRankDetail();
			
			log.info("该订单可达到的粉丝等级信息："+liveFansRank);
			
			Map<String,Object> reqMap = new HashMap<String,Object>();
			reqMap.put("uid", uid);
			LivePrivilege highestOrder = livePrivilegeDao.getHighestDebitcardLedgerOrder(reqMap);
			if(highestOrder==null || highestOrder.getPayment().compareTo(debitcardOrder.getPayCoin())<0){
				liveLedgerService.updateUrsRankInfo((long)uid, liveFansRank.getId(), 2);
			}
			

			if(curRelation==null){
				//木有关系链，新增
				UrsEarningsRelation relation = new UrsEarningsRelation();
				relation.setUid(uid);
				relation.setObjectOriented(2);
				relation.setUidRelationChain(String.format("%011d", uid));
				relation.setUidRelationChainLevel(1);
				relation.setUidRelationChainNname(debitcardOrder.getUname());
				relation.setReferrerSellerid(debitcardOrder.getSellerid());
				relation.setReferrerSellertype(debitcardOrder.getSellertype());
				relation.setCreateTime(new Date());
				liveLedgerService.addUrsRelation(relation);
			}else if(curRelation!=null && curRelation.getUidRelationChain().length()<12 && curRelation.getReferrerSellerid()==null){
				UrsEarningsRelation relation = new UrsEarningsRelation();
				//当该用户没有上级，且没有绑定商家时，则将其绑定到当前充值商家
				relation.setReferrerSellerid(debitcardOrder.getSellerid());
				relation.setReferrerSellertype(debitcardOrder.getSellertype());
				relation.setId(curRelation.getId());
				liveLedgerService.updateUrsRelation(relation);
			}

			if(liveFansRankDetail==null){
				log.error("没有订单"+debitcardOrder.getOrderNo()+"合适的等级明细信息");
				throw new Exception("没有合适的等级明细信息");
			}
			
			/*更新订单的其他信息*/
			initDebitcardOrderInfo(debitcardOrder.getId(), debitcardOrder.getSellerIncome(), liveFansRankDetail);
			/*更新初始化该订单可得到的分账数据*/
			initDebitcardOrderLedgerInfo(debitcardOrder,liveFansRankDetail,true);

		}catch(Exception e){
			log.error("充值订单更新用户信息失败",e);
		}

		return null;
	}

	/**
	 * 
	 * 方法描述：兑换储值卡，给上及产品推荐奖励
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param debitcardOrder void
	 */
	public void recommendExchangeDebitcardLedger(DebitcardOrder debitcardOrder) {
		/*
		 * 会员在"商家充值平台(即买鸟粉专享卡)"上充值，作为该会员的上级和上上级，可享受的推荐奖励规则（以上级角色说明）：
		 * 1.享受推荐奖励时，上级角色分“商家”与“会员”两种角色
		 * 2.上级角色是“会员”时：
 		 * 2.1.充值会员本次充值的订单达到一定额度(1000)，才可给上级分推荐奖励
		 * 2.2.上级自身具有可获奖励的订单，且该订单的奖励金额(推荐+红包)尚没有拿满
		 * 2.3.上级享受推荐奖励时，收入将进入鸟币(进公共鸟币OR进商家专享鸟币)
		 * 3.上级角色是“商家”时：
 		 * 3.1.充值会员本次充值的订单达到一定额度(1000)，才可给上级分推荐奖励
		 * 3.2.商家自身无需任何条件即可享受推荐奖励
		 * 3.3.商家享受推荐奖励时，收入将进入店外收益余额
		 */

		LiveLedgerRecord reqRecord = new LiveLedgerRecord();
		reqRecord.setOrderNo(debitcardOrder.getOrderNo());
		reqRecord.setLedgerSource(2);
		long count = liveLedgerRecordDao.countLedgerRecord(reqRecord);
		if(count>0){
			log.info("该订单已进行分账");
		}
		
		log.info("开始给上线计算分账操作。。。");
		BigDecimal minLedgerOrder = this.getMinRecommendLedgerPayment();
		if(debitcardOrder.getPayCoin().compareTo(minLedgerOrder)>=0){
//			LiverBean liverBean = liveLedgerService.getLiverInfo(debitcardOrder.getUid());
			List<Map<String,Object>> list = liveLedgerService.getLiverLevel(debitcardOrder.getUid(),2);
			RecommendLedger recommendLedger = new RecommendLedger(debitcardOrder.getOrderNo(),2, debitcardOrder.getUid(), debitcardOrder.getPayCoin());
			for(Map<String,Object> map:list){
				if((int)map.get("usertype")==1){
					LiverBean liver = liveLedgerService.getLiverInfo((int)map.get("id"));
					liveLedgerService.recommendLedger(liver, (int) map.get("recommendLevel"), recommendLedger);
					liveLedgerService.updateLiverFansRank(liver.getUid(),2);				
				}else if((int)map.get("usertype")==2){
					if(debitcardOrder.getSellerid()!=(int)map.get("id") || debitcardOrder.getSellertype()!=(int)map.get("sellertype")){
						log.info("商家只能获取商家会员在其所属商家充值的推荐奖励");
						continue;
					}
//					if(!((Integer)map.get("id")).equals(liverBean.getReferrerSellerid())){
//						log.info("商家只能获取商家会员在其所属商家充值的推荐奖励");
//						continue;
//					}
					DebitcardSeller req = new DebitcardSeller();
					req.setSellerid((int)map.get("id"));
					req.setSellertype((int)map.get("sellertype"));
					req.setStatus(0);
					DebitcardSeller debitcardSeller = debitcardSellerDao.getDebitcardSeller(req);
					liveLedgerService.recommendToSeller(debitcardSeller, (int) map.get("recommendLevel"), recommendLedger);
				}
			}

		}else{
			log.info("该订单金额不具备上级推荐返利资格");
		}
	}
	
	/**
	 * 
	 * 方法描述：给用户充值商家额度
	 * 创建人： ChenBo
	 * 创建时间：2017年3月1日
	 * @param debitcardQuota void
	 * @throws Exception 
	 */
	public void rechargeSellerQuota(DebitcardOrder debitcardOrder,String phone) throws Exception{
		//充值额度
		Map<String,String> quotaMap = new HashMap<String,String>();
		quotaMap.put("uid", debitcardOrder.getUid()+"");
		quotaMap.put("phone", phone==null?"":phone);
		quotaMap.put("sellerid", debitcardOrder.getSellerid()+"");
		quotaMap.put("rtype", "1");
		quotaMap.put("remarks", debitcardOrder.getOrderNo()+"_1");
		quotaMap.put("quota", debitcardOrder.getQuota()+"");
		quotaMap.put("sellername", debitcardOrder.getSellername()+"");
		quotaMap.put("sellertype", debitcardOrder.getSellertype()+"");
		quotaMap.put("option", "1");
		log.info("对本次充值给会员"+debitcardOrder.getUid()+"充值"+debitcardOrder.getQuota()+"额度");
		boolean code = commonServiceDao.updateDebitcardQuota(quotaMap);
		if(!code){
			log.error("给用户充值商家额度失败");
		}
	}
	
	/**
	 * 
	 * 方法描述：给用户充值商家鸟币
	 * 创建人： ChenBo
	 * 创建时间：2017年4月14日
	 * @param rechargeSellerCoin void
	 * @throws Exception 
	 */
	public void rechargeSellerCoin(DebitcardOrder debitcardOrder) throws Exception{
		//充值额度
		Map<String,String> coinMap = new HashMap<String,String>();
		coinMap.put("uid", debitcardOrder.getUid()+"");
		coinMap.put("rtype", "28");
		coinMap.put("sellerCoin", debitcardOrder.getDenomination()+"");
		coinMap.put("remarks", debitcardOrder.getOrderNo());
		coinMap.put("description", "兑换商家储值卡");
		coinMap.put("option", "0");
		log.info("对本次兑换储值卡给会员"+debitcardOrder.getUid()+"兑换"+debitcardOrder.getDenomination()+"商家专享鸟币");
		ResponseData responseData = commonServiceDao.liveWalletOption(coinMap);
		if(responseData==null || responseData.getState()!=0){
			log.error("给用户充值商家额度失败");
		}
	}
	
	public void rechargeOrderLedgerForSeller(DebitcardOrder debitcardOrder){
		if(debitcardOrder.getSellertype()==3){
			log.info("在区域代理的总商家下充值，不进行分账");
			return ;
		}
		BigDecimal sellerAgio = new BigDecimal("0.7");
		BigDecimal sellerAmount = debitcardOrder.getPayCoin().multiply(sellerAgio).setScale(2, BigDecimal.ROUND_HALF_UP);
		debitcardOrder.setSellerIncome(sellerAmount);
		XmnWalletBean xmnWallet = new XmnWalletBean();
		xmnWallet.setuId(debitcardOrder.getSellerid()+"");
		xmnWallet.setUserType(2+"");
		xmnWallet.setSellerAmount(sellerAmount+"");
		xmnWallet.setOption("0");
		xmnWallet.setOrderId(debitcardOrder.getOrderNo());
		xmnWallet.setRemark(debitcardOrder.getUid()+"充值分账");
		xmnWallet.setrType("42");
		xmnWallet.setReturnMode("0");
		orderService.insertXmnWalletRedis(xmnWallet);
		log.info("对本次订单给商户进行分账:"+sellerAmount+"元现金");
	}
	
	/**
	 * 填充会员充值的该订单可返金额数据
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年4月11日
	 * @param payment
	 * @param liveFansRank void
	 */
	public void initRechargeOrderLedgerInfo(int uid,String orderNo,BigDecimal payment,LiveFansRankDetail liveFansRankDetail,boolean priviledgeLedger){
		LivePrivilege livePrivilege = new LivePrivilege();
		
		livePrivilege.setObjectOriented(102);
		livePrivilege.setUid(uid);
		livePrivilege.setPayment(payment);
		livePrivilege.setCreateTime(new Date());
		livePrivilege.setUpdateTime(new Date());
		livePrivilege.setDebitcardId(null);
		livePrivilege.setQuota(null);
//		long level = liveFansRankDetail.getRankId();
		livePrivilege.setLedgerLevel(null);
		
		livePrivilege.setOrderNo(orderNo);
		livePrivilege.setConsumeLedger(payment);
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
		journal.setUid(uid);
		journal.setComsumLedger(payment);
		journal.setOrderAmount(payment);
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
