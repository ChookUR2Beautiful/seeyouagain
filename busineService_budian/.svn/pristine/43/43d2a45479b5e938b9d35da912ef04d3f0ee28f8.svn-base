package com.xmniao.service.live;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.BusineConstants;
import com.xmniao.common.MapUtil;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.live.DebitcardSellerDao;
import com.xmniao.dao.live.LiveDividendsLedgerDetailRecordDao;
import com.xmniao.dao.live.LiveLedgerRecordDao;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.domain.live.DebitcardSeller;
import com.xmniao.domain.live.LiveDividendsLedgerDetailRecord;
import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.live.LivePayOrder;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.LiverJournalCount;
import com.xmniao.domain.live.RankRedPacketDetail;
import com.xmniao.domain.live.RecommendLedger;
import com.xmniao.domain.live.ResponseData;
import com.xmniao.domain.live.UrsEarningsRank;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.proxy.ThriftClientProxy;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.pay.ManorPropsThriftService;
import com.xmniao.thrift.pay.ManorPropsThriftService.Client;
import com.xmniao.thrift.pay.Result;
import com.xmniao.urs.dao.LiveFansRankDao;
import com.xmniao.urs.dao.LiverDao;
import com.xmniao.urs.dao.UrsEarningsRankDao;
import com.xmniao.urs.dao.UrsEarningsRelationDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiveLedgerServiceImpl
 * 
 * 类描述： 直播分账服务类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月29日 下午5:01:42 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class LiveLedgerServiceImpl {
	// 初始化日志类
	private final Logger log = Logger.getLogger(LiveOrderServiceImpl.class);

	@Autowired
	private LiveOrderDao liveOrderDao;

	@Autowired
	private LiveLedgerRecordDao liveLedgerReocrdDao;
	
	@Autowired
	private LiveDividendsLedgerDetailRecordDao liveDividendsLedgerDetailRecordDao;
	
	@Autowired
	private LiverDao liverDao;
	
	@Autowired
	private CommonServiceImpl commonServiceImpl;
    /**
     * 注入消息队列生产者
     */
    @Autowired
    private DefaultMQProducer producerConnection;
	
	@Autowired
	private String liveLedgerTopic;
	
	@Autowired
	private String liveRecommendLedgerTags;
	
	@Autowired
	private String liveDividendsLedgerTags;
	
	@Autowired
	private String manorBusineTopic;
	
	@Autowired
	private String manorEnergyTag;
	
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private String liverLedgerQueue;
    
    @Autowired
    private String recomendLedgerOrderMinAmount;
    
    @Autowired
    private LiveFansRankDao liveFansRankDao;
    
    @Autowired
    private DebitcardSellerDao debitcardSellerDao;

	@Autowired
	private String liveDividendsLedgerTagsV2;
	
	@Autowired
	private String liveDividendsLedgerTagsV3;
	
	@Autowired
	private SellerRechargeLiveOrder sellerRechargeLiveOrderService;
	
	@Autowired
	private RechargeDividendsService verEexcitationProject;
	
    @Resource(name="notHalvedRedpackagePhone")
    private List<String> notHalvedRedpackagePhone;
    
    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
	@Autowired
	private UrsEarningsRankDao ursRankDao;
	
	@Autowired
	private UrsEarningsRelationDao ursRelationDao;
	
	@Autowired
	private ThriftClientProxy clientProxy;
	/**
	 * 
	 * 方法描述：发送直播分账MQ
	 * 创建人： ChenBo
	 * 创建时间：2017年3月14日
	 * @param ledgerList
	 * @throws MQClientException
	 * @throws RemotingException
	 * @throws MQBrokerException
	 * @throws InterruptedException void
	 */
	public void sendLedgerMsg(List<Map<String,String>> ledgerList) throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
		String json = JSONObject.toJSONString(ledgerList);
		Message msg = new Message();
		msg.setTopic(liveLedgerTopic);
		msg.setTags(liveDividendsLedgerTagsV3);
		msg.setKeys(ledgerList.get(0).get("remarks"));
		msg.setBody(json.getBytes());
		log.info("topic:"+msg.getTopic()+",tags:"+msg.getTags()+",body:"+json);
		producerConnection.send(msg);
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
    public LiveLedgerRecord recommendLedger(LiverBean liver,int level,RecommendLedger recommendLedger){
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
    	
    	List<LivePrivilege>  orderList = this.getLedgeringLivePayOrderByAll(liver.getUid());
    	if(orderList.size()==0){
    		log.info("该用户没有在奖励分账订单");
    		return null;
    	}
    	
    	/* 获取上级充值/兑换最大金额订单时的等级明细  */
    	LiveFansRank rank = this.getOrderApplyLiveFansRank(orderList.get(0).getLedgerLevel(), orderList.get(0).getCreateTime(),orderList.get(0).getObjectOriented());
    	
    	/*排序，将那一个在返的VIP或直销订单放在推荐奖励订单列表的最后奖励*/
    	LivePrivilege lastOrder = null;
    	Iterator<LivePrivilege> it = orderList.iterator();
		while (it.hasNext()) {
			LivePrivilege orderTemp =  (LivePrivilege) it.next();
			if(orderTemp.getObjectOriented()!=BusineConstants.LIVE_ORDER_2 && it.hasNext()){
				lastOrder=orderTemp;
				it.remove();
				break;
			}
		}
    	if(lastOrder!=null){
    		orderList.add(lastOrder);
    	}
    	
    	BigDecimal orderMaxLedger = this.getOrderMaxLedger(recommendLedger.getPayment(), rank.getLiveFansRankDetail(), level);
    	
    	Map<String,BigDecimal> resultMap = this.updateHighLevelLedgerOrder(orderList, orderMaxLedger);
		BigDecimal ledgerAmount = resultMap.get("ledgerAmount");
		if(ledgerAmount.compareTo(BigDecimal.ZERO)>0){
//			LiveLedgerRecord liveLedgerRecord=insertLedgerRecord(liver.getUid(),level+1,orderMaxLedger,ledgerAmount,recommendLedger.getOrderNo(),null,recommendLedger.getLedgerUid());
			LiveLedgerRecord liveLedgerRecord=insertRecommendLedgerRecord(liver.getUid(),level+1,orderMaxLedger,ledgerAmount,BigDecimal.ZERO,BigDecimal.ZERO,recommendLedger.getOrderNo(),recommendLedger.getObjectOriented(),null,recommendLedger.getLedgerUid());
			LiverJournalCount journal = new LiverJournalCount();
			journal.setUid(liver.getUid());
			journal.setCurrentRecommendLedger(ledgerAmount);
			journal.setExpectedPriviledgeLedger(this.getExpectedPriviledgeLedger(liver.getUid()));
			this.updateLiverJournalCountInfo(journal);
			
			List<Map<String,String>> ledgerList = this.ledgerMQMap(liveLedgerRecord,resultMap);
			try {
				this.sendLedgerMsg(ledgerList);
			} catch (Exception e) {
				log.error("发送分账消息失败",e);
			}
			return liveLedgerRecord;
		}
		return null;
    }

    @Transactional(rollbackFor={FailureException.class,Exception.class})
    public Map<String,String> consumeGiftReturnCoin(Map<String,String> paramMap,BigDecimal giftLedger)
			throws FailureException, TException {
//		BigDecimal returnToSellerCoin = sellerRechargeLiveOrderService.returnToSellerCoin(new Integer(paramMap.get("uid")), giftLedger);
    	//2017年3月31日 新业务修改，在打赏返鸟币不需将返的鸟币区分为通用鸟币和商家鸟币，统一归到通用鸟币中，故该代码废弃 
//   	BigDecimal returnToAvailableExchangeCoin = sellerRechargeLiveOrderService.returnToAvailableExchangeCoin(new Integer(paramMap.get("uid")), giftLedger);
//    	log.info("本次打赏，累计需给用户返"+returnToAvailableExchangeCoin+"可用于购买储值卡鸟币");
    	BigDecimal returnCoin = this.getGiftReturnCoin(giftLedger,new Integer(paramMap.get("uid")));
		paramMap.put("rtype", "2");
		paramMap.put("uid", paramMap.get("uid"));
		paramMap.put("commision", paramMap.get("consumeAmount"));
		paramMap.put("zbalance", returnCoin+"");
		paramMap.put("remarks", paramMap.get("returnRatio"));
		log.info("paramMap"+paramMap);
		com.xmniao.thrift.pay.ResponseData payResponseData = commonServiceImpl.updateLiveWalletReturnResponse(paramMap);
		if(payResponseData==null){
			throw new FailureException(1,"送礼钱包操作异常");
		}else if(payResponseData.getState()!=0){
			if(payResponseData.getState()==4){//余额不足
				throw new FailureException(2,"鸟豆余额不足");
			}else {
				throw new FailureException(1,"送礼钱包操作异常");
			}
		}else{
			if(payResponseData.getResultMap()!=null){
				paramMap.put("walletRecordId", payResponseData.getResultMap().get("id"));	
			}
		}
		log.info("会员打赏，预计奖励"+giftLedger+"鸟币,实际奖励"+returnCoin+"鸟币");
		return paramMap;
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
	public Map<String,BigDecimal> updateHighLevelLedgerOrder(List<LivePrivilege> orderList,BigDecimal orderMaxLedger){
		Map<String,BigDecimal> resultMap = new HashMap<String,BigDecimal>();
		BigDecimal ledgerAmount = new BigDecimal(0);//本次可得到的分账值
		BigDecimal ledgerAmountForCommon = new BigDecimal(0);//本次通用鸟币可得到的分账值
		BigDecimal ledgerAmountForSeller = new BigDecimal(0);//本次商家专享鸟币可得到的分账值
		BigDecimal unledgerAmount = new BigDecimal(0);	//该用户累计待分账的推荐-红包鸟币总额
		BigDecimal availableExchangeCoin = new BigDecimal(0);//可用于兑换储值卡的公共鸟币
		for(LivePrivilege order:orderList){
			BigDecimal orderLedgerAmount = BigDecimal.ZERO;	//本订单可获取推荐奖励鸟币金额
			BigDecimal orderUnledgerAmount = BigDecimal.ZERO;
			if(order!=null){
				orderUnledgerAmount = order.getPrivilegeLedger().subtract(order.getCurrentPrivilegeLedger());
				unledgerAmount=unledgerAmount.add(orderUnledgerAmount);
				unledgerAmount = keepTwoPoint(unledgerAmount);
			}
			
			if(orderUnledgerAmount.compareTo(BigDecimal.ZERO)>0){//订单累计的待返面额>0，则本单允许与该上线分账
				if(orderMaxLedger.compareTo(BigDecimal.ZERO)<=0){
				}else{
					boolean addDebitcardQuota = false;
					if(orderUnledgerAmount.compareTo(orderMaxLedger)<=0){//本次计算返利金额>可返金额，则本次最多可返sumLedger的额度
						orderLedgerAmount=orderUnledgerAmount;
					}else{
						orderLedgerAmount=orderMaxLedger;
					}
					ledgerAmount = ledgerAmount.add(orderLedgerAmount);
					orderMaxLedger = orderMaxLedger.subtract(orderLedgerAmount);	
					unledgerAmount = unledgerAmount.subtract(orderLedgerAmount);
		
					if(order.getObjectOriented()==BusineConstants.LIVE_ORDER_2 && order.getDebitcardId()!=null){
						DebitcardSeller reqSeller = new DebitcardSeller();
						reqSeller.setId(order.getDebitcardId());
						reqSeller.setStatus(0);
						DebitcardSeller debitcardSeller = debitcardSellerDao.getDebitcardSeller(reqSeller);
						if(debitcardSeller==null){
							log.info("充值订单时的所属商家已取消储值卡业务，其打赏奖励鸟币部分进入公共鸟币部分");
							ledgerAmountForCommon = ledgerAmountForCommon.add(orderLedgerAmount);
						}else{
							//这部分鸟币到商家专享鸟币钱包
							Map<String,String> quotaMap = new HashMap<String,String>();
							quotaMap.put("uid", order.getUid()+"");
							quotaMap.put("sellerid", debitcardSeller.getSellerid()+"");
							quotaMap.put("rtype", "4");
							quotaMap.put("remarks", order.getOrderNo()+"");
							quotaMap.put("quota", orderLedgerAmount+"");
							quotaMap.put("sellername", debitcardSeller.getSellername());
							quotaMap.put("sellertype", debitcardSeller.getSellertype()+"");
							quotaMap.put("option", "1");
							
							boolean code = commonServiceImpl.updateDebitcardQuota(quotaMap);
							if(!code){
								//throw new Exception("给用户充值商家额度失败");
								log.error("给用户充值商家额度失败");
							}
							ledgerAmountForSeller = ledgerAmountForSeller.add(orderLedgerAmount);
							addDebitcardQuota = true;
						}
					}else{
						//这部分鸟币到原有公共鸟币钱包
						ledgerAmountForCommon = ledgerAmountForCommon.add(orderLedgerAmount);
					}
					if(order.getObjectOriented()!=BusineConstants.LIVE_ORDER_2){
						availableExchangeCoin = availableExchangeCoin.add(orderLedgerAmount);
					}
					//本次分账全部分给该订单
					LivePrivilege uOrder = new LivePrivilege();
					uOrder.setId(order.getId());
					uOrder.setObjectOriented(order.getObjectOriented());
					uOrder.setCurrentPrivilegeLedger(order.getCurrentPrivilegeLedger().add(orderLedgerAmount));
					if(addDebitcardQuota){
						uOrder.setQuota(order.getCurrentPrivilegeLedger().add(orderLedgerAmount));
					}
					livePrivilegeDao.updateLiveOrderLedger(uOrder);
				}
			}	
		}
		resultMap.put("ledgerAmount", ledgerAmount);
		resultMap.put("unledgerAmount", unledgerAmount);
		resultMap.put("ledgerAmountForSeller", ledgerAmountForSeller);
		resultMap.put("ledgerAmountForCommon", ledgerAmountForCommon);
		resultMap.put("ledgerAmountForAvailableExchangeCoin", availableExchangeCoin);
		return resultMap;
	}
	
    
	/**
	 * 写入分账记录
	 * @Title: insertLedgerRecord 
	 * @Description:
	 */
	public LiveLedgerRecord insertRecommendLedgerRecord(int uid,int role,BigDecimal ledgerAmount,BigDecimal realLedgerAmount,BigDecimal ledgerCoin,BigDecimal realLedgerCoin,String orderNo,Integer objectOriented,String remarks,Integer ledgerUid){
		LiveLedgerRecord record = new LiveLedgerRecord();
		record.setUid(uid);
		record.setUidRole(role);
		record.setLedgerAmount(ledgerAmount);
		record.setRealLedgerAmount(realLedgerAmount);
		record.setLedgerCoin(ledgerCoin);
		record.setRealLedgerCoin(realLedgerCoin);
		record.setStatus(1);
		record.setCreateDate(new Date());
		record.setUpdateDate(record.getCreateDate());
		record.setLedgerSource(2);
		record.setLedgerSourceInfo(remarks==null?"壕赚充值订单分账":remarks);
		record.setOrderNo(orderNo);
		record.setObjectOriented(objectOriented);
		record.setLedgerUid(ledgerUid);
		liveLedgerReocrdDao.insertLiveLedgerRecord(record);
		return record;
	}
	
	/**
	 * 写入商家获得推荐分账记录
	 * @Title: insertLedgerRecord 
	 * @Description:
	 */
	public LiveLedgerRecord insertLedgerRecordToSeller(int sellerid,int sellertype,int role,BigDecimal realLedgerAmount,String orderNo,String remarks,Integer ledgerUid){
		LiveLedgerRecord record = new LiveLedgerRecord();
		record.setSellerid(sellerid);
		record.setSellertype(sellertype);
		record.setUidRole(role+1);
		record.setLedgerAmount(realLedgerAmount);
		record.setRealLedgerAmount(realLedgerAmount);
		record.setStatus(1);
		record.setCreateDate(new Date());
		record.setUpdateDate(record.getCreateDate());
		record.setLedgerSource(2);
		record.setLedgerSourceInfo(remarks==null?"直播充值订单分账":remarks);
		record.setOrderNo(orderNo);
		record.setLedgerUid(ledgerUid);
		record.setObjectOriented(2);
		liveLedgerReocrdDao.insertLiveLedgerRecord(record);
		return record;
	}
	
	/**
	 * 写入打赏送礼分账记录
	 * @Title: insertLedgerRecord 
	 * @Description:
	 */
	public LiveLedgerRecord insertLedgerRecordByGift(int uid,BigDecimal ledgerAmount,BigDecimal realLedgerAmount,String liveRecordId,String remarks,Integer giftId){
		LiveLedgerRecord record = new LiveLedgerRecord();
		record.setUid(uid);
		record.setUidRole(1);
		record.setLedgerAmount(ledgerAmount);
		record.setRealLedgerAmount(realLedgerAmount);
		record.setStatus(2);
		record.setCreateDate(new Date());
		record.setUpdateDate(record.getCreateDate());
		record.setLedgerSource(1);
		record.setLedgerSourceInfo(remarks);
		record.setGivedGiftId(giftId);
		record.setOrderNo(liveRecordId);
		liveLedgerReocrdDao.insertLiveLedgerRecord(record);
		return record;
	}
	

	/**
	 * 
	 * 方法描述：写入更新会员统计信息Redis
	 * 创建人： ChenBo
	 * 创建时间：2017年1月2日
	 * @param journal void
	 */
	public void updateLiverJournalCountInfo(LiverJournalCount journal){
		redisTemplate.opsForList().leftPush(liverLedgerQueue, JSONObject.toJSONString(journal));
	}
	
	/**
	 * 计算其分账比例，拿到其上线的是上线还是上上级，以及其对应的分账比例
	 * 1 上级
	 * 2 上上级
	 */
	public BigDecimal getLedgerRatio(LiveFansRankDetail liveFansRankDetail,int level){
		if(level==1){
			return percentage2BigDecimal(liveFansRankDetail.getReferrerRatio());
		}else if(level==2){
			return  percentage2BigDecimal(liveFansRankDetail.getParentReferrerRatio());
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * 
	 * 方法描述：保持2位小数
	 * 创建人： ChenBo
	 * 创建时间：2017年1月2日
	 * @param money
	 * @return BigDecimal
	 */
	public static BigDecimal keepTwoPoint(BigDecimal money) {
		String str = "#####0.";
		for (int i = 0; i < 2; i++) {
			str = str + "0";
		}
		DecimalFormat format = new DecimalFormat(str);
		format.setRoundingMode(RoundingMode.FLOOR);
		return new BigDecimal(format.format(money.doubleValue()));
	}
	

	
	/**
	 * 
	 * 方法描述：获取当前正在进行奖励分账的VIP或直销充值订单
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @return LivePayOrder
	 */
	public LivePrivilege getObjectOrientedLivePayOrder(int uid,int objectOriented){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("objectOriented", objectOriented);
		LivePrivilege order = livePrivilegeDao.getLiveOrderByLedger(map);
		if(order==null){
			return null;
		}else{
			if(order.getPrivilegeLedger().compareTo(order.getCurrentPrivilegeLedger())>0){
				return order;
			}else{
				return null;
			}
		}
	}
	
	
	/**
	 * 
	 * 方法描述：获取当前正在进行奖励分账的所有充值订单
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @return LivePayOrder
	 */
	public List<LivePrivilege> getLedgeringLivePayOrderByAll(int uid){
		List<LivePrivilege> orderList = new ArrayList<>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		orderList = livePrivilegeDao.getLiveOrderListByLedgering(map);
		Iterator<LivePrivilege> it = orderList.iterator();
		while (it.hasNext()) {
			LivePrivilege orderTemp =  (LivePrivilege) it.next();
			if(orderTemp ==null){
				it.remove();
			}else{
				if(orderTemp.getPrivilegeLedger().compareTo(orderTemp.getCurrentPrivilegeLedger())<=0){
					it.remove();
				}
			}
		}
		int sorttype=1;//排序方式：0 默认 1.订单金额大->小，2.支付时间 远->近，3充值类型 VIP->商家->直销
		if(sorttype==1){
			
			/*
			 * 订单金额从大到小排序
			 */
			Collections.sort(orderList, new Comparator<LivePrivilege>(){  
	            public int compare(LivePrivilege o1, LivePrivilege o2) {  

	                if(o1.getPayment().compareTo(o2.getPayment())>0){  
	                    return -1;  
	                }  
	                if(o1.getPayment().compareTo(o2.getPayment())==0){  
	                    return 0;  
	                }  
	                return 1;  
	            }  
	        });   
		}else if(sorttype==2){
			/*
			 * 订单时间从远到近排序
			 */
			Collections.sort(orderList, new Comparator<LivePrivilege>(){  
	            public int compare(LivePrivilege o1, LivePrivilege o2) {  
	                if(o1.getCreateTime().compareTo(o2.getCreateTime())>0){  
	                    return 1;  
	                }  
	                if(o1.getCreateTime().compareTo(o2.getCreateTime())==0){  
	                    return 0;  
	                }  
	                return -1;  
	            }  
	        }); 
		}else if(sorttype==3){
			/*
			 * 订单类型从VIP->商家->直销排序
			 */
			Collections.sort(orderList, new Comparator<LivePrivilege>(){  
	            public int compare(LivePrivilege o1, LivePrivilege o2) {  
	                if(o1.getObjectOriented()>o2.getObjectOriented()){  
	                    return 1;  
	                }  
	                if(o1.getObjectOriented()==o2.getObjectOriented()){  
	                    return 0;  
	                }  
	                return -1;  
	            }  
	        }); 
		}
		return orderList;
	}
	
	
	/**
	 * 
	 * 方法描述：获取当前正在进行推荐红包奖励分账的所有充值订单
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @return LivePayOrder
	 */
	public List<LivePrivilege> getDividendsLivePayOrderByAll(int uid){
		List<LivePrivilege> orderList =  this.getLedgeringLivePayOrderByAll(uid);
		Iterator<LivePrivilege> it = orderList.iterator();
		while (it.hasNext()) {
			LivePrivilege orderTemp =  (LivePrivilege) it.next();
			if(orderTemp ==null){
				it.remove();
			}else{
				if(orderTemp.getObjectOriented()==4){
					continue;
				}
				if(orderTemp.getPrivilegeLedger().compareTo(orderTemp.getCurrentPrivilegeLedger())<=0){
					it.remove();
					continue;
				}
				if(orderTemp.getPayment().compareTo(orderTemp.getCurrentDividendLedger().add(orderTemp.getCurrentDividendCoinLedger()))<=0){
					it.remove();
					continue;
				}
			}
		}
		return orderList;
	}
	
	/**
	 * 
	 * 方法描述：转换分账MQ消息实体
	 * 创建人： ChenBo
	 * 创建时间：2017年1月2日
	 * @param record
	 * @return Map<String,String>
	 */
	public List<Map<String,String>> ledgerMQMap(LiveLedgerRecord record,Map<String,BigDecimal> detailMap){
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> ledgerMap = new HashMap<String,String>();
		ledgerMap.put("uid", record.getUid()+"");
		ledgerMap.put("rtype", "14");
		ledgerMap.put("option", "0");
		ledgerMap.put("zbalance", detailMap.get("ledgerAmountForCommon").toString());
		ledgerMap.put("sellerCoin", detailMap.get("ledgerAmountForSeller").toString());
		ledgerMap.put("availableCoin", detailMap.get("ledgerAmountForAvailableExchangeCoin").toString());
		ledgerMap.put("remarks", record.getOrderNo()+"_"+(record.getUidRole()-1));
		ledgerMap.put("description", record.getLedgerUid()+"");
		ledgerMap.put("recordid", record.getId()+"");
		ledgerMap.put("ledgerType", "2");//直播钱包
		list.add(ledgerMap);
		return list;
	}
	
	public void ledgerMsgToSeller(LiveLedgerRecord record){
		List<Map<String,String>> ledgerList = new ArrayList<Map<String,String>>();

		Map<String,String> ledgerMap = new HashMap<String,String>();
		ledgerMap.put("uId", record.getSellerid()+"");
		ledgerMap.put("userType", "2");
		ledgerMap.put("option", "0");
		ledgerMap.put("rType", "41");
		ledgerMap.put("commision", record.getRealLedgerAmount()+"");
		ledgerMap.put("orderId", record.getOrderNo()+"_"+(record.getUidRole()-1));
		ledgerMap.put("remark", record.getLedgerUid()+"");
		ledgerMap.put("recordid", record.getId()+"");
		ledgerMap.put("ledgerType", "1");//普通钱包
		ledgerList.add(ledgerMap);
		
		String json = JSONObject.toJSONString(ledgerList);
		Message msg = new Message();
		msg.setTopic(liveLedgerTopic);
		msg.setTags(liveDividendsLedgerTagsV2);
		msg.setKeys(record.getId()+"");
		msg.setBody(json.getBytes());
		
		try {
			producerConnection.send(msg);
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			e.printStackTrace();
			log.error("给商家发送推荐分账信息失败",e);
		}
	}
	
	/**
	 * 
	 * 方法描述：返回充值订单理论可返奖励金额
	 * 创建人： ChenBo
	 * 创建时间：2017年1月2日
	 * @return BigDecimal
	 */
	public BigDecimal getOrderMaxLedger(BigDecimal payment,LiveFansRankDetail liveFansRankDetail,int level){
		return  keepTwoPoint(payment.multiply(getLedgerRatio(liveFansRankDetail,level)));//本单本用户按分账比，最多可返的鸟币值
	}
	
	@Transactional(rollbackFor = { FailureException.class, Exception.class,
			RuntimeException.class })
	public  void updateLivePayOrder(Map<String, String> paramMap,LivePayOrder liveOrder)
			throws FailureException {
			LivePayOrder uOrder = new LivePayOrder();
			uOrder.setOrderNo(paramMap.get("bid"));
			uOrder.setVersion(liveOrder.getVersion());
			uOrder.setPayState(Integer.parseInt(paramMap.get("status")));
			uOrder.setPayType(paramMap.get("payType"));
			uOrder.setPayNo(paramMap.get("payId"));
			uOrder.setPayCode(paramMap.get("payCode"));
			uOrder.setPayTime(new Date());
			uOrder.setUpdateTime(new Date());
			if(liveOrder.getObjectOriented()!=0 && StringUtils.isBlank(liveOrder.getUidRelationChain())){
				uOrder.setUidRelationChain(String.format("%011d", liveOrder.getUid()));
			}
			int result1 = liveOrderDao.updateLiveOrder(uOrder);
			
			if (result1 != 1) {
				log.info("更新订单失败" + paramMap);
				throw new FailureException(1,"更新失败。。。");
			}else{
			/* 给用户充值鸟豆 */
				if(paramMap.get("status").equals("1")){
					rechargeLiveCoin(liveOrder);
				}
			}
	}
	
	/**
	 * 
	 * 方法描述：鸟币充值
	 * 创建人： ChenBo
	 * 创建时间：2016年12月22日 void
	 * @throws FailureException 
	 */
	public void rechargeLiveCoin(LivePayOrder liveOrder) throws FailureException{
		// 调用支付服务查询该用户是否存在直播钱包，若不存在则添加
		Map<String, String> map = new HashMap<>();
		map.put("uid", liveOrder.getUid() + "");
		boolean result = commonServiceImpl.checkLiveWallet(map);
		if (!result) {
			log.info("更新订单失败" + map.get("uid") + "没有直播钱包，并添加失败");
			throw new FailureException(1, "给用户添加直播钱包失败");
		}
		
		// 调用支付服务更新钱包接口更新钱包鸟币数量
		Map<String, String> walletMap = new HashMap<>();
		walletMap.put("uid", liveOrder.getUid() + "");
		walletMap.put("rtype", "0");
		walletMap.put("commision", liveOrder.getRealCoin() + "");
		walletMap.put("remarks", liveOrder.getOrderNo());
		boolean result2 = commonServiceImpl.updateLiveWallet(walletMap);
		if (result2) {
			log.info("更新订单状态成功");
		} else {
			log.info("更新订单状态失败");
			throw new FailureException(1, "更新订单状态失败");
		}
	}
	
	/**
	 * 会员领取红包
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年1月13日
	 * @param record
	 * @param isVirtual
	 * @return Map<String,BigDecimal>
	 * @throws FailureException 
	 * @throws Exception 
	 */
	public Map<String,BigDecimal> ledgerDailyRedpacket(LiveLedgerRecord record,LiveFansRank rank,int isVirtual) throws FailureException {
		Map<String,BigDecimal> marginMap = new HashMap<>();
//		if(record.getId()<602230){//上线当天，兼容旧版本发放的红包领取过渡。
//			marginMap = this.getLedgerMargin(record.getUid(), record.getLedgerAmount(), record.getLedgerCoin(), rank, isVirtual);
//		}else{
			marginMap = this.getLedgerMargin(record);
//		}
		
		BigDecimal ledgerAmount = marginMap.get("ledgerAmount");
		BigDecimal ledgerCoin = marginMap.get("ledgerCoin");
		record.setStatus(isVirtual==1?4:(ledgerAmount.add(ledgerCoin)).compareTo(BigDecimal.ZERO)>0?1:2);//红包现金+鸟币=0时，直接写为已到账
		record.setRealLedgerAmount(ledgerAmount);//真实的分账金额
		record.setRealLedgerCoin(ledgerCoin);//真实的分账金额
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		int result = liveLedgerReocrdDao.updateLiveLedgerRecord(record);
		if(result==0){
			throw new FailureException(1,"红包已领取，请勿重复领取");
		}
		return marginMap;
	}
	/**
	 * 
	 * 方法描述：更新订单领取分账信息
	 * 创建人： ChenBo
	 * 创建时间：2016年12月28日
	 * @param uid
	 * @param dividendsAmount
	 * @return BigDecimal
	 */
	private Map<String,BigDecimal> getLedgerMargin(LiveLedgerRecord record){
		Map<String,BigDecimal> resultMap = new HashMap<String,BigDecimal>();
		if(record.getLedgerCoin()==null)record.setLedgerCoin(BigDecimal.ZERO);
		List<LiveDividendsLedgerDetailRecord> detailList = liveDividendsLedgerDetailRecordDao.getDiviDendsLedgerDetailRecordList(record.getId());
		LiverBean liverBean = liverDao.getLiverByUid(record.getUid());
		
		BigDecimal ledgerAmountTotal = BigDecimal.ZERO;		//本次实际可获取红包现金
		BigDecimal ledgerCoinTotal = BigDecimal.ZERO;		//本次实际可获取红包鸟币
		BigDecimal ledgerCoinForSeller = BigDecimal.ZERO;		//本次进商家专享鸟币账号
		BigDecimal ledgerCoinForCommon = BigDecimal.ZERO;		//本次进公众鸟币账号
		
		//内购还是外购
		int dividendsRole = liverBean.getDividendsRole()==null?1:liverBean.getDividendsRole();	
		int isVirtual = liverBean.getRedPacketAuthority()==2?1:0;//是否虚拟入账
		for(LiveDividendsLedgerDetailRecord detail:detailList){
		
			//1.打赏已完成，而有奖励金额且金额没满订单
			BigDecimal unledgerAmount = BigDecimal.ZERO;	//该用户累计待奖励的总额=推荐+红包
			BigDecimal unledgerDividendAmount = BigDecimal.ZERO;	//该用户累计待奖励红包现金的总额
			BigDecimal unledgerDividendCoin = BigDecimal.ZERO;	//该用户累计待奖励红包鸟币的总额
			BigDecimal ledgerAmount = BigDecimal.ZERO;		//本次实际可获取红包现金
			BigDecimal ledgerCoin = BigDecimal.ZERO;		//本次实际可获取红包鸟币
			BigDecimal dividendTemp = BigDecimal.ZERO;		//因现金鸟币比调整，可能产生的已领现金超过实际可领现金的部分
			LivePrivilege reqLivePrivilege = new LivePrivilege();
//			reqLivePrivilege.setId(detail.get);
			reqLivePrivilege.setOrderNo(detail.getOrderNo());
//			reqLivePrivilege.setObjectOriented(detail.getO);
			LivePrivilege order = livePrivilegeDao.getLivePrivilege(reqLivePrivilege);
			if(order!=null){
				LiveFansRank rank = this.getOrderApplyLiveFansRank(order.getLedgerLevel(),order.getCreateTime(),order.getObjectOriented());
				//订单红包现金百分比
				int cashRatio=0;
				//订单红包鸟币百分比
				int coinRatio=0;
				if(dividendsRole==1){
					cashRatio = rank.getLiveFansRankDetail().getPrivateRedPacketCashRatio();
					coinRatio = rank.getLiveFansRankDetail().getPrivateRedPacketCoinRatio();
				}else{
					cashRatio = rank.getLiveFansRankDetail().getPublicRedPacketCashRatio();
					coinRatio = rank.getLiveFansRankDetail().getPublicRedPacketCoinRatio();
				}
				
				BigDecimal halfLedgerOrder = this.getHalfRedPacketRatio(order,liverBean.getJuniorLimitRatio());
				
				//实际计入分红订单的金额
				BigDecimal orderAmount = this.mulNumberTwoPoint(order.getPayment(),halfLedgerOrder);
				
				/* 会员实际可领现金红包金额， */
				BigDecimal realOrderCash = order.getBaseAmount()==null?orderAmount.multiply(this.percentage2BigDecimal(cashRatio)):order.getBaseAmount();
				
				unledgerDividendAmount=unledgerDividendAmount.add(realOrderCash);
				unledgerDividendAmount=unledgerDividendAmount.subtract(order.getCurrentDividendLedger()); 
				unledgerDividendAmount = keepTwoPoint(unledgerDividendAmount);
				if(unledgerDividendAmount.compareTo(BigDecimal.ZERO)<0){
					dividendTemp = unledgerAmount.abs();
					unledgerDividendAmount = BigDecimal.ZERO;
				}
				
				unledgerDividendCoin=unledgerDividendCoin.add(orderAmount.multiply(this.percentage2BigDecimal(coinRatio)));
				unledgerDividendCoin=unledgerDividendCoin.subtract(order.getCurrentDividendCoinLedger()).subtract(dividendTemp);
				unledgerDividendCoin = keepTwoPoint(unledgerDividendCoin);
				
				unledgerAmount=unledgerAmount.add(order.getPrivilegeLedger().subtract(order.getCurrentPrivilegeLedger()));
				unledgerAmount = keepTwoPoint(unledgerAmount);
				
				LivePrivilege uOrder = new LivePrivilege();
				uOrder.setId(order.getId());
				uOrder.setCurrentPrivilegeLedger(order.getCurrentPrivilegeLedger());
				uOrder.setCurrentDividendLedger(order.getCurrentDividendLedger());
				uOrder.setCurrentDividendCoinLedger(order.getCurrentDividendCoinLedger());
				uOrder.setVersion(order.getVersion()+1);
				/* 领现金 */
				if(unledgerDividendAmount.compareTo(BigDecimal.ZERO)>0){//有待返还奖励的订单
					if(unledgerDividendAmount.compareTo(detail.getLedgerAmount())<=0){//本次计算返利金额>可返金额，则本次最多返可返金额的额度
						ledgerAmount=unledgerDividendAmount;
					}else{
						ledgerAmount=detail.getLedgerAmount();
					}

					uOrder.setCurrentPrivilegeLedger(uOrder.getCurrentPrivilegeLedger().add(ledgerAmount));
					uOrder.setCurrentDividendLedger(uOrder.getCurrentDividendLedger().add(ledgerAmount));
					
					unledgerDividendAmount = unledgerDividendAmount.subtract(ledgerAmount);
					unledgerAmount = unledgerAmount.subtract(ledgerAmount);
					ledgerAmountTotal = ledgerAmountTotal.add(ledgerAmount);
				}
				
				/* 领鸟币 */
				if(unledgerDividendCoin.compareTo(BigDecimal.ZERO)>0){//有待返还奖励的订单
					if(unledgerDividendCoin.compareTo(detail.getLedgerCoin())<=0){//本次计算返利鸟币>可返鸟币，则本次最多返可返鸟币的额度
						ledgerCoin=unledgerDividendCoin;
					}else{
						ledgerCoin=detail.getLedgerCoin();
					}
					
					if(order.getObjectOriented()==2 && order.getDebitcardId()!=null){
						uOrder.setQuota(order.getQuota().add(ledgerCoin));//更新已返额度
					}
					
					uOrder.setCurrentPrivilegeLedger(uOrder.getCurrentPrivilegeLedger().add(ledgerCoin));
					uOrder.setCurrentDividendCoinLedger(uOrder.getCurrentDividendCoinLedger().add(ledgerCoin));

					unledgerDividendCoin = unledgerDividendCoin.subtract(ledgerCoin);
					unledgerAmount = unledgerAmount.subtract(ledgerCoin);
					ledgerCoinTotal=ledgerCoinTotal.add(ledgerCoin);
				}
				
				livePrivilegeDao.updateLiveOrderLedger(uOrder);
							
				boolean addForSeller=false;
				if(order.getObjectOriented()==2 && order.getDebitcardId()!=null){
					DebitcardSeller reqSeller = new DebitcardSeller();
					reqSeller.setId(order.getDebitcardId());
					reqSeller.setStatus(0);
					DebitcardSeller debitcardSeller = debitcardSellerDao.getDebitcardSeller(reqSeller);
					if(debitcardSeller!=null){
						addForSeller = true;
						ledgerCoinForSeller = ledgerCoinForSeller.add(ledgerCoin);
						//要给自己充额度啊和充商家专享鸟币啊
						Map<String,String> quotaMap = new HashMap<String,String>();
						quotaMap.put("uid", order.getUid()+"");
						quotaMap.put("sellerid", debitcardSeller.getSellerid()+"");
						quotaMap.put("rtype", "5");
						quotaMap.put("remarks", order.getOrderNo()+"");
						quotaMap.put("quota", ledgerCoin+"");
						quotaMap.put("sellername", debitcardSeller.getSellername()+"");
						quotaMap.put("sellertype", debitcardSeller.getSellertype()+"");
						quotaMap.put("option", "1");
						
						boolean code = commonServiceImpl.updateDebitcardQuota(quotaMap);
						if (!code) {
							log.error("给用户充值商家专享鸟币失败");
						}
					}else{
						log.info("充值订单时的所属商家已取消储值卡业务，其打赏奖励鸟币部分进入公共鸟币部分");
						ledgerCoinForCommon = ledgerCoinForCommon.add(ledgerCoin);
					}
				}else{
					ledgerCoinForCommon = ledgerCoinForCommon.add(ledgerCoin);
				}
				
				LiveDividendsLedgerDetailRecord uDetail = new LiveDividendsLedgerDetailRecord();
				uDetail.setId(detail.getId());
				uDetail.setRealLedgerAmount(ledgerAmount);
				uDetail.setRealLedgerCoin(ledgerCoin);
				uDetail.setRealLedgerCommonCoin(addForSeller?BigDecimal.ZERO:ledgerCoin);
				uDetail.setRealLedgerSellerCoin(addForSeller?ledgerCoin:BigDecimal.ZERO);
				liveDividendsLedgerDetailRecordDao.updateDividendsLedgerDetailRecord(uDetail);
				this.updateLiverFansRank(liverBean.getUid(),order.getObjectOriented());
			}
		}


		resultMap.put("ledgerAmount", ledgerAmountTotal);
		resultMap.put("ledgerCoin", ledgerCoinTotal);
		resultMap.put("ledgerCoinForSeller", ledgerCoinForSeller);
		resultMap.put("ledgerCoinForCommon", ledgerCoinForCommon);
		resultMap.put("ledgerCoinForAvailableExchangeCoin", ledgerCoinForCommon);
//		resultMap.put("unledgerDividendAmount", unledgerDividendAmount);
//		resultMap.put("unledgerDividendCoin", unledgerDividendCoin);
//		resultMap.put("unledgerAmount", unledgerAmount);
		return resultMap;
	}
	

	
	/**
	 * 
	 * 方法描述：获取指定等级在指定日期下的分账规则
	 * 创建人： ChenBo
	 * 创建时间：2017年2月22日
	 * @param rankId 等级ID
	 * @param applyDate 应用日期
	 * @return LiveFansRank
	 */
	public LiveFansRank getOrderApplyLiveFansRank(int rankId,Date applyDate,int objectOriented){
		LiveFansRank rank= liveFansRankDao.getLiveFansRankBase(new LiveFansRank((long)rankId)); 
		if(rank!=null){
			LiveFansRankDetail detail = new LiveFansRankDetail();
			detail.setRankId((long)rankId);
			detail.setObjectOriented(objectOriented);
			detail.setEffectiveDate(applyDate);
			LiveFansRankDetail liveFansRankDetail = liveFansRankDao.getLiveFansRankDetail(detail);
			rank.setLiveFansRankDetail(liveFansRankDetail);
		}
		return rank;
	}
	
	/**
	 * 
	 * 方法描述：获取会员在当前生效等级下的红包奖励配置
	 * 创建人： ChenBo
	 * 创建时间：2017年2月27日
	 * @param liveFansRankDetailId
	 * @param dividendsRole
	 * @param consumAmount
	 * @return RankRedPacketDetail
	 */
	public RankRedPacketDetail getApplyRankRedPacketDetail(int liveFansRankDetailId,int dividendsRole,BigDecimal consumAmount){
		RankRedPacketDetail rankRedPacketDetail = new RankRedPacketDetail();
		rankRedPacketDetail.setRankDetailId(liveFansRankDetailId);
		rankRedPacketDetail.setDividendsRole(dividendsRole);
		rankRedPacketDetail.setConsume(consumAmount);
		return liveFansRankDao.getRankRedPakcetDetail(rankRedPacketDetail);
	}
	
	/**
	 * 
	 * 方法描述：百分比转小数
	 * 创建人： ChenBo
	 * 创建时间：2017年2月24日
	 * @param ratio
	 * @return BigDecimal
	 */
	public BigDecimal percentage2BigDecimal(Number ratio){
		return mulNumberTwoPoint(ratio==null?0:ratio,0.01);
	}
	
	public BigDecimal mulNumberTwoPoint(Number number1,Number number2){
		BigDecimal decimal1 = new BigDecimal(number1+"");
		BigDecimal decimal2 = new BigDecimal(number2+"");
		return keepTwoPoint(decimal1.multiply(decimal2));
	}
	
	/**
	 * 
	 * 方法描述：获取订单所处的会员分账等级信息
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @param payAmount
	 * @return LiveFansRank
	 * @throws Exception 
	 */
	public LiveFansRank getOrderLiveFansRank(BigDecimal payAmount,int rankType,Date payDate,int objectOriented) throws Exception{
		LiveFansRank rank = new LiveFansRank(payAmount,rankType);
		LiveFansRank liveFansRank = liveFansRankDao.getLiveFansRankBase(rank);
		if(liveFansRank != null){
			LiveFansRankDetail detail = new LiveFansRankDetail();
			detail.setRankId(liveFansRank.getId());
			detail.setEffectiveDate(payDate);
			detail.setObjectOriented(objectOriented);
			LiveFansRankDetail liveFansRankDetail = liveFansRankDao.getLiveFansRankDetail(detail);
			liveFansRank.setLiveFansRankDetail(liveFansRankDetail);
		}
		if(liveFansRank==null || liveFansRank.getLiveFansRankDetail()==null){
			throw new Exception("找不到对应的分账等级区间信息");
		}
		return liveFansRank;
	}
	
	public LiveFansRank getLiveFansRankBase(BigDecimal payAmount,int rankType){
 		LiveFansRank rank = new LiveFansRank(payAmount,rankType);
 		return liveFansRankDao.getLiveFansRankBase(rank);
	}
	
	public LiverBean getLiverInfo(int uid){
		LiverBean reqLiver = new LiverBean();
		reqLiver.setUid(uid);
		return liverDao.getLiverInfo(reqLiver);
	}
	
	/**
	 * 
	 * 方法描述：当前会员等级调整
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @param uid
	 * @param payAmount
	 * @return boolean 是否有更新
	 */
	public boolean ledgerLevelAdjustment(LiverBean liver,LiveFansRank liveFansRank){
//		if(liver.getFansRankId()==null
//			||liver.getFansRankNo()==null
//			||liver.getFansRankNo()<liveFansRank.getRankNo()){
//			liver.setFansRankId(liveFansRank.getId());
//			liver.setFansRankName(liveFansRank.getRankName());
//			liver.setFansRankNo(liveFansRank.getRankNo());
//			liverDao.updateLiverLedgerInfo(liver);
//			return true;
//		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：修改会员信息
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @param uid
	 * @param payAmount
	 * @return boolean 是否有更新
	 */
	public boolean updateLiverInfo(LiverBean liver){
//		return liverDao.updateLiverLedgerInfo(liver)==1?true:false;
		return false;

	}
	
	
	public Integer parseUid(String uidStr){
		return uidStr==null?null:Integer.parseInt(uidStr);
	} 

	/**
	 * 
	 * 方法描述：
	 * 创建人： ChenBo
	 * 创建时间：2017年3月1日
	 * @param uid
	 * @return List<Map<String,Object>>
				id:								//UID OR sellerid
				usertype:						//1 用户(直销上级也算用户) 2商家
				recommendLevel:					//1 上级 2 上上级
	 */
	public List<Map<String,Object>> getLiverLevel(Integer uid,final Integer objectOriented){
		List<Map<String,Object>> list = new ArrayList<>();
		UrsEarningsRelation ursRrelation = this.getUrsRelation(new UrsEarningsRelation(uid, objectOriented));
		if(ursRrelation==null){
			return list;
		}
		String uidRelationChain = ursRrelation.getUidRelationChain()==null?"":ursRrelation.getUidRelationChain();
		String[] uidArray = uidRelationChain.split(",");
		int length = uidArray.length;
		if(length==1){
			if(ursRrelation.getReferrerSellerid()!=null){
				Map<String,Object> recommendMap = new HashMap<>();
				recommendMap.put("id", ursRrelation.getReferrerSellerid());	//UID OR sellerid
				recommendMap.put("usertype", 2);						//1 用户(直销上级也算用户) 2商家
				recommendMap.put("sellertype", ursRrelation.getReferrerSellertype());	//商家时，商家类型 1常规商家 2连锁商家 3区域代理
				recommendMap.put("recommendLevel", 1);					//1 上级 2 上上级
				list.add(recommendMap);
			}else{
				log.info("无上级");
			}
		}else if(length>=1){
			int highLevelUid = this.parseUid(uidArray[length-2]);
			//LiverBean liverTemp = this.getLiverInfo(highLevelUid);//上级
			UrsEarningsRelation ursRrelationTemp = this.getUrsRelation(new UrsEarningsRelation(highLevelUid, objectOriented));//上级
			
			Map<String,Object> recommendMap = new HashMap<>();
			recommendMap.put("id", ursRrelationTemp.getUid());	//UID OR sellerid
			recommendMap.put("usertype", 1);						//1 用户(直销上级也算用户) 2商家
			recommendMap.put("recommendLevel", 1);					//1 上级 2 上上级
			list.add(recommendMap);
			if(length==2){
				if(ursRrelationTemp.getReferrerSellerid()!=null){
					//上上级是商家
					Map<String,Object> recommend2Map = new HashMap<>();
					recommend2Map.put("id", ursRrelationTemp.getReferrerSellerid());	//UID OR sellerid
					recommend2Map.put("usertype", 2);						//1 用户(直销上级也算用户) 2商家
					recommend2Map.put("sellertype", ursRrelationTemp.getReferrerSellertype());	//商家时，商家类型 1常规商家 2连锁商家 3区域代理
					recommend2Map.put("recommendLevel", 2);					//1 上级 2 上上级
					list.add(recommend2Map);
				}else{
					log.info("无上上级");
				}
			}else{
				int highestLevelUid = this.parseUid(uidArray[length-3]);
//				LiverBean liver2Temp = this.getLiverInfo(highestLevelUid);
				
				Map<String,Object> recommend2Map = new HashMap<>();
				recommend2Map.put("id", highestLevelUid);	//UID OR sellerid
				recommend2Map.put("usertype", 1);						//1 用户(直销上级也算用户) 2商家
				recommend2Map.put("recommendLevel", 2);					//1 上级 2 上上级
				list.add(recommend2Map);
			}
		}
		
		return list;
	}
	/**
	 * 写入分账记录
	 * @Title: insertLedgerRecord 
	 * @Description:
	 */
	public LiveLedgerRecord insertLedgerRecordByDailyDividends(int uid,BigDecimal ledgerAmount,BigDecimal ledgerCoin,String date,String remarks){
		LiveLedgerRecord record = new LiveLedgerRecord();
		record.setUid(uid);
		record.setUidRole(1);
		record.setLedgerAmount(ledgerAmount);
		record.setRealLedgerAmount(null);
		record.setLedgerCoin(ledgerCoin);
		record.setRealLedgerCoin(null);
		record.setStatus(0);
		record.setCreateDate(null);
		record.setUpdateDate(record.getCreateDate());
		record.setLedgerSource(3);
		record.setLedgerSourceInfo(remarks);
		record.setRedpacketRocordDate(date);
		liveLedgerReocrdDao.insertLiveLedgerRecord(record);
		return record;
	}
	
	/**
	 * 
	 * 方法描述：查询用户当前总预计可获的收益
	 * 创建人： ChenBo
	 * 创建时间：2017年3月6日 void
	 */
	public BigDecimal getExpectedPriviledgeLedger(Integer uid){
		BigDecimal allAmount = BigDecimal.ZERO;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("uid", uid);
		List<LivePrivilege> list = livePrivilegeDao.getLiveOrderListByLedgering(paraMap);
		for(LivePrivilege order:list){
			BigDecimal orderAmount = order.getPrivilegeLedger().subtract(order.getCurrentPrivilegeLedger());
			allAmount=allAmount.add(orderAmount);
		}
		return allAmount;
		
	}
	
	public void recommendToSeller(DebitcardSeller debitcardSeller,int level,RecommendLedger recommendLedger){

		if(debitcardSeller.getStatus()==1){
			log.info("该商家已取消储值卡业务，无法享受推荐奖励");
			return ;
		}
		BigDecimal recommend = BigDecimal.ZERO;
		BigDecimal ratio = BigDecimal.ZERO;
		if(level==1){
			ratio = this.percentage2BigDecimal(debitcardSeller.getReferrerRatio());
		}else if(level==2){
			ratio = this.percentage2BigDecimal(debitcardSeller.getParentReferrerRatio());
		}
		recommend = this.mulNumberTwoPoint(recommendLedger.getPayment(), ratio);
		
		//拿到商家的推荐比，
		LiveLedgerRecord liveLedgerRecord = this.insertLedgerRecordToSeller(debitcardSeller.getSellerid(),debitcardSeller.getSellertype(), level,  recommend, recommendLedger.getOrderNo(), null, recommendLedger.getLedgerUid());
//		Map<String,String> ledgerMap = 
		this.ledgerMsgToSeller(liveLedgerRecord);
//		try {
//			this.sendLedgerMsg(ledgerMap);
//		} catch (Exception e) {
//			log.error("发送分账消息失败",e);
//		}
	
	}
	
	/**
	 * 
	 * 方法描述：当会员存在多个在奖励的订单时，若在返的最高等级订单返完，则将会员等级调到下一个最高等级的订单等级
	 * 创建人： ChenBo
	 * 创建时间：2017年3月10日
	 * @return LiveFansRank
	 */
	public LiveFansRank updateLiverFansRank(Integer uid,Integer objectOriented){
//		Map<String,Object> reqMap = new HashMap<>();
//		reqMap.put("uid",uid);
//		reqMap.put("objectOriented",objectOriented);
//		
//		LivePayOrder order = liveOrderDao.getHighestLedgerOrder(reqMap);
//		LiverBean liver = this.getLiverInfo(uid);
//
//		if(!order.getLedgerLevel().equals(liver.getFansRankId())){
//			LiveFansRank reqRank = new LiveFansRank(order.getPayment());
//			LiveFansRank rank = liveFansRankDao.getLiveFansRankBase(reqRank);
//			if(!rank.getId().equals(liver.getFansRankId())
//				||!rank.getRankNo().equals(liver.getFansRankNo())){
//				log.info("用户等级信息需更新");
//				LiverBean uLiver = new LiverBean();
//				uLiver.setUid(uid);
//				uLiver.setFansRankId(rank.getId());
//				uLiver.setFansRankName(rank.getRankName());
//				uLiver.setFansRankNo(rank.getRankNo());
//				liverDao.updateLiverLedgerInfo(uLiver);
//				return rank;
//			}
//		}
		return null;
	}
	
	@Transactional(rollbackFor = { FailureException.class, Exception.class,
					RuntimeException.class })
	public void updateRedpacketStatus(LiveLedgerRecord record) throws Exception{
		LiveLedgerRecord uRecord = new LiveLedgerRecord();
		uRecord.setId(record.getId());
		uRecord.setStatus(1);
		uRecord.setCreateDate(new Date());
		uRecord.setVersion(record.getVersion());
		int result = liveLedgerReocrdDao.updateLiveLedgerRecord(uRecord);
		if(result ==0){
			throw new FailureException(1,"红包已领取，请勿重复领取");
		}
	}
	
	/**
	 * 
	 * 方法描述：查询该订单的用户所能得到的红包总额比例(VIP充值订单，若直接下级充值金额少了该订单额，该订单的红包收益减半)
	 * 创建人： ChenBo
	 * 创建时间：2017年3月14日
	 * @param order
	 * @return BigDecimal
	 */
	public BigDecimal getHalfRedPacketRatio(LivePrivilege order,BigDecimal vipRedpacketRatio){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse("2017-03-15 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("格式化日期异常");
		}
		//若奖励订单是2017-03-10之前的充值订单，则不受该条件影响影响
		if(order.getCreateTime().before(date)){
			return BigDecimal.ONE; 
		}

		log.info(order.getUid()+"会员的VIP红包限制比例为："+vipRedpacketRatio);
		if(vipRedpacketRatio==null){
			vipRedpacketRatio=new BigDecimal("50");
		}
		if(vipRedpacketRatio.compareTo(new BigDecimal("100"))==0){
			log.info(order.getUid()+"会员的VIP红包不限制，实际比例为："+1);
			return BigDecimal.ONE; 
		}

		if(order.getObjectOriented()==1){
			BigDecimal allRechargeAmount = liveOrderDao.countSubordinateOrderAmount(order.getUid());
			if(allRechargeAmount==null || allRechargeAmount.compareTo(order.getPayment())<0){
				log.info(order.getUid()+"会员的VIP红包实际比例为："+this.percentage2BigDecimal(vipRedpacketRatio));
				return this.percentage2BigDecimal(vipRedpacketRatio);
			}
		}
		return BigDecimal.ONE;
	}
	
	/**
	 * 
	 * 方法描述：红包到账失败重新领取
	 * 创建人： ChenBo
	 * 创建时间：2017年3月14日 void
	 */
	public Map<String,BigDecimal> reReceiveRedpacket(LiveLedgerRecord record){
		Map<String,BigDecimal> resultMap = new HashMap<String,BigDecimal>();
		LiveDividendsLedgerDetailRecord sumRecord= liveDividendsLedgerDetailRecordDao.getDiviDendsLedgerDetailRecordSum(record.getId());
		resultMap.put("ledgerAmount", sumRecord.getRealLedgerAmount());
		resultMap.put("ledgerCoin", sumRecord.getRealLedgerCoin());
		resultMap.put("ledgerCoinForSeller", sumRecord.getRealLedgerSellerCoin());
		resultMap.put("ledgerCoinForCommon", sumRecord.getRealLedgerCommonCoin());
		resultMap.put("ledgerCoinForAvailableExchangeCoin", sumRecord.getRealLedgerCommonCoin());
		return resultMap;
	
	}
	
	/**
	 * 
	 * 方法描述：查询领取红包的明细
	 * 创建人： ChenBo
	 * 创建时间：2017年4月8日 void
	 */
	public List<Map<String,String>> getDividendsLedgerDetailList(Integer id){
		List<Map<String,String>> resultList= new ArrayList<>();
		String redpacketDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<LiveDividendsLedgerDetailRecord> list= liveDividendsLedgerDetailRecordDao.getDiviDendsLedgerDetailRecordList(id);
		for(LiveDividendsLedgerDetailRecord detail:list){
			if(detail.getRealLedgerAmount() !=null && detail.getRealLedgerAmount().compareTo(BigDecimal.ZERO)>0){
				Map<String,String> ledgerCashMap = new HashMap<>();
				ledgerCashMap.put("uId", detail.getUid()+"");
				ledgerCashMap.put("commision", detail.getRealLedgerAmount()+"");
				ledgerCashMap.put("ledgerType", "1");
				
				ledgerCashMap.put("userType", "1");
				ledgerCashMap.put("option", "0");
				ledgerCashMap.put("orderId", detail.getId()+"");
				ledgerCashMap.put("remark", detail.getOrderNo());
				ledgerCashMap.put("recordid", id+"");
				
				if(detail.getObjectOriented()==4){
					ledgerCashMap.put("rType", "2");
				}if(detail.getObjectOriented()==1){
					ledgerCashMap.put("rType", "3");
				}else{
					ledgerCashMap.put("rType", "4");
				}
				resultList.add(ledgerCashMap);
			}
			if(detail.getRealLedgerCoin() !=null && detail.getRealLedgerCoin().compareTo(BigDecimal.ZERO)>0){
				Map<String,String> ledgerCoinMap = new HashMap<>();
				ledgerCoinMap.put("uid", detail.getUid()+"");
				ledgerCoinMap.put("ledgerType", "2");
				if(detail.getObjectOriented()==4){
					ledgerCoinMap.put("rtype", "21");
				}else{
					ledgerCoinMap.put("rtype", "19");
				}
				ledgerCoinMap.put("rtype", "19");
				ledgerCoinMap.put("option", "0");
				ledgerCoinMap.put("zbalance", detail.getRealLedgerCommonCoin()+"");
				ledgerCoinMap.put("sellerCoin", detail.getRealLedgerSellerCoin()+"");
				ledgerCoinMap.put("remarks", detail.getId()+"");
				ledgerCoinMap.put("description", detail.getOrderNo());
				ledgerCoinMap.put("recordid", id+"");
				resultList.add(ledgerCoinMap);
			}

		}
		return resultList;
	
	}
	/**
	 * 
	 * 方法描述：获取当前正在进行奖励分账的充值订单
	 * 创建人： ChenBo
	 * 创建时间：2016年12月30日
	 * @return LivePayOrder
	 */
	public LivePrivilege getLedgeringLivePayOrder(int uid){
    	List<LivePrivilege>  orderList = this.getLedgeringLivePayOrderByAll(uid);
    	if(orderList==null || orderList.size()==0){
    		return null;
    	}
    	for(LivePrivilege order:orderList){
    		if(order.getObjectOriented()==null || order.getObjectOriented()==1){
    			if(order.getPrivilegeLedger().compareTo(order.getCurrentPrivilegeLedger())>0){
    				return order;
    			}else{
    				return null;
    			}
    		}
    	}
    	return null;
	}
	
	/**
	 * 
	 * 方法描述：初始化会员可用于购买储值卡的面额
	 * 创建人： ChenBo
	 * 创建时间：2017年4月6日 void
	 */
	public void initWalletCoin(){
		//初始化会员可用于购买储值卡的信息
		int pageSize=1000;
		int pageNo=0;
		Map<String,Object> reqMap = new HashMap<String,Object>();
		List<Map<String,String>> list = null;
		reqMap.put("pageSize", pageSize);
		do{
			int index=0;
			reqMap.put("pageNo", pageNo++);
			list = livePrivilegeDao.getAvailableExchangeCoinList(reqMap);
//			for(Map<String,Object> map:list){
//				Map<String,String> uMap = new HashMap<>();
				com.xmniao.thrift.pay.ResponseData rdata = commonServiceImpl.updateExchangeCoin(list);
				log.info("【"+index+"】update result:"+rdata);
//			}
			
		}while(list.size()==pageSize);
	}
	
//	private List<LiverBean> getNotHalvedUidList(){
//		if(notHalvedLiverList!=null){
//			return notHalvedLiverList;
//		}
//		notHalvedLiverList = getNotHalvedUidListFactory();
//		return notHalvedLiverList;
//	}
//	List<LiverBean> getNotHalvedUidListFactory(){
//		synchronized (this) {
//			if(notHalvedRedpackagePhone!=null){
//				Iterator<String> testIt = notHalvedRedpackagePhone.iterator();
//				while(testIt.hasNext()){
//					String phone=testIt.next();
//					if(StringUtils.isBlank(phone)){
//						log.info("为空，删除");
//						testIt.remove();
//					}
//				}
//				List<String> phones = new ArrayList<>();
//				for(int i=0;i<notHalvedRedpackagePhone.size();i++){
//					phones.add(notHalvedRedpackagePhone.get(i).trim());
//				}
//				return liverDao.getLiverListByPhone(phones);
//				
//			}else{
//				return new ArrayList<>();
//			}
//		}
//	}
	
	/**
	 * 
	 * 方法描述：更新会员等级
	 * 创建人： ChenBo
	 * 创建时间：2017年4月6日 void
	 */
	public void updateUrsRankInfo(Long uid,Long rankId,Integer rankSource){
		UrsEarningsRank rank = new UrsEarningsRank(uid,rankId,rankSource);
		int result = ursRankDao.updateSelective(rank);
		if(result==0){
			ursRankDao.insertSelective(rank);
		}
	}
	
	public UrsEarningsRelation getUrsRelation(UrsEarningsRelation ursRelation){
		return ursRelationDao.getUrsEarningsRelation(ursRelation);
	}
	
	public int addUrsRelation(UrsEarningsRelation ursRelation){
		return ursRelationDao.insertUrsEarningsRelation(ursRelation);
	}
	
	public int updateUrsRelation(UrsEarningsRelation ursRelation){
		return ursRelationDao.updateUrsEarningsRelation(ursRelation);
	}
	
	/* 会员打赏鸟豆，是否给会员打奖励鸟币，及奖励多少，
	 * V客充值订单，不给鸟币奖励
	 * */
	public BigDecimal getGiftReturnCoin(BigDecimal proCoin,Integer uid){
		BigDecimal coin = proCoin;		//刚进来时的预计奖励鸟币
		BigDecimal verCoin = BigDecimal.ZERO;	//理应归属到V客充值订单的奖励鸟币
//		BigDecimal energyNum = BigDecimal.ZERO;	//用户打赏收获能量，新业务改到一充值，即累计能量
		if(coin==null || coin.compareTo(BigDecimal.ZERO)==0){
			return BigDecimal.ZERO;
		}
		//提取所有未打赏完的充值订单
		Map<String,Object> map = new HashMap<>();
		map.put("uid", uid);
		List<LivePrivilege> list = livePrivilegeDao.getConsumeOrderList(map);
		if(list==null || list.size()==0){
			//充值订单获得的鸟豆均已打赏光光,其他途径获得的鸟豆放过
			return coin;
		}
		
		list = sortPrivilegeOrder(list);
		
		BigDecimal tempCoin = BigDecimal.ZERO;
		BigDecimal orderCoin = BigDecimal.ZERO;
		
		for(LivePrivilege privilege:list){
			tempCoin = privilege.getPayment().subtract(privilege.getCurrentConsumeLedger());
			if(tempCoin.compareTo(coin)<0){
				orderCoin = tempCoin;
			}else{
				orderCoin = coin;
			}
			coin= coin.subtract(orderCoin);
//			if(order.getObjectOriented()==0){//常规充值鸟豆打赏，额外送等额鸟币的庄园能量
//				energyNum=energyNum.add(orderCoin);
//			}
			if(privilege.getObjectOriented()==4){
				verCoin =verCoin.add(orderCoin);
				if(!this.halfOfPayOrder(privilege.getConsumeLedger(), privilege.getCurrentConsumeLedger())	//打赏前没有满一半
					&& this.halfOfPayOrder(privilege.getConsumeLedger(), orderCoin.add(privilege.getCurrentConsumeLedger()))){//打赏后才满一半
					LivePayOrder order = liveOrderDao.getLiveOrder(privilege.getOrderNo());
					if(order.getExcitationProject()!=null && order.getExcitationProject().equalsIgnoreCase("B")){
						returnVerExcitationProject(privilege);
					}
				}
			}
			if(orderCoin.compareTo(BigDecimal.ZERO)>0){
				LivePrivilege uOrder = new LivePrivilege();
				uOrder.setId(privilege.getId());
				uOrder.setCurrentConsumeLedger(orderCoin.add(privilege.getCurrentConsumeLedger()));
				livePrivilegeDao.updateLiveOrderLedger(uOrder);
			}else{
				break;
			}
		}
		
//		this.countUserEnergyNum(uid, energyNum);
		return proCoin.subtract(verCoin);
	}
	
	/* 对剩下未打赏完的充值订单，按即定规则依次排序，完成打赏的鸟豆归属 
	 * 优先顺序：正常充值(0)->VIP充值(1)->商家充值(2)->大观充值(3)->V客充值(4)
	 * */
	private List<LivePrivilege> sortPrivilegeOrder(List<LivePrivilege> list){
		List<LivePrivilege> newList = new ArrayList<>(list.size());
		int[] sort={0,5,1,102,3,4};
		for(int idx:sort){
			for(LivePrivilege privilege:list){
				if(privilege.getObjectOriented()==idx){
					newList.add(privilege);
				}
			}
		}
		list=null;
		return newList;
	}
	
	/**
	 * 
	 * 方法描述：对方案B的V客充值订单进行奖励 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月26日下午2:40:34 <br/>
	 * @param order
	 * @param privilege
	 */
	private void returnVerExcitationProject(LivePrivilege privilege){
		Map<String,String> map = new HashMap<>();
		map.put("orderNo", privilege.getOrderNo());
		map.put("uid", privilege.getUid().toString());
		map.put("projectName", "B");
		map.put("fansRankId", privilege.getLedgerLevel().toString());
		
		try {
			ResponseData responseData= verEexcitationProject.saveReceiveB(map);
			if(responseData.getStatus()!= ResponseState.SUCCESS){
				log.error("发放B奖励失败,"+responseData.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发放B奖励方案出错！",e);
		}
	}
	
	/* 订单是否打赏过半 */
	private  boolean halfOfPayOrder(BigDecimal all,BigDecimal now){
		//return now.multiply(BigDecimal.valueOf(2.0)).compareTo(all)>=0;
		return all.multiply(new BigDecimal("0.80")).compareTo(now)<=0;
	}
	
	//异步统计会员的能量数据
	public void countUserEnergyNum(Integer uid,BigDecimal energyNum,String transNo,int objectOriented){
		if(energyNum.compareTo(BigDecimal.ZERO)<=0){
			return ;
		}
		boolean retry=false;
		try{
			ManorPropsThriftService.Client client = (Client) clientProxy.getPayServiceClient(ManorPropsThriftService.class);
			Result result = client.addUserEnergy(transNo, uid, energyNum.doubleValue(), objectOriented==5?4:3);
			if(result.getCode()==1 && result.getStatusCode().equals("10000")){
				log.info("添加能量成功");
			}else if(result.getCode()==0 && result.getStatusCode().equals("20003")){
				log.info("重复添加能量");
			}else{
				log.error("添加能量失败");
				retry=true;
			}
		}catch(Exception e){
			log.error("添加能量异常",e);
			retry=true;
		}finally{
			clientProxy.returnPayServiceClient();
		}
		
		/*异步重试机制*/
		if(retry){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("uid", uid);
			map.put("energy", energyNum.doubleValue());
			map.put("transNo", transNo);
			map.put("type", objectOriented==5?4:3);
			
			String json = JSONObject.toJSONString(map);
			Message msg = new Message();
			msg.setTopic(manorBusineTopic);
			msg.setTags(manorEnergyTag);
			msg.setKeys("");
			msg.setBody(json.getBytes());
			try {
				producerConnection.send(msg);
				log.info("充值送能量:"+msg+",json"+json);
			} catch (MQClientException | RemotingException | MQBrokerException
					| InterruptedException e) {
				log.error("合计能量出错",e);
			}
		}
	}

	/**
	 * 
	 * 方法描述：写入主播所属V客信息<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月9日下午1:57:22 <br/>
	 * @param givedId
	 * @param anchorUid
	 */
	public Map<String,String> getGivedGiftVkeInfo(Integer anchorUid){
		Map<String,String> anchorVkeMap = new HashMap<String, String>();
		if(anchorUid==null){
			return anchorVkeMap;
		}
		Map<String,Object> anchorMap = liverDao.getAnchorBaseInfo(anchorUid);
		if(anchorMap!=null && anchorMap.get("vkeUid")!=null){
			Map<String,Object> vkeMap = liverDao.getVkeAnchorInfo((Integer)anchorMap.get("vkeUid"));
			if(vkeMap !=null){
				anchorMap.putAll(vkeMap);
			}
			return MapUtil.formatMapStr(anchorMap);
		}
		return anchorVkeMap;
	}
}

