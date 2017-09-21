package com.xmniao.service.live;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
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
import com.xmniao.common.DateUtil;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.live.CouponOrderDao;
import com.xmniao.dao.live.LiveLedgerRecordDao;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LiveRecordDao;
import com.xmniao.dao.live.LiveSalaryDao;
import com.xmniao.dao.vstar.TVstarRewardRecordDao;
import com.xmniao.domain.common.XmnConstants;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.live.LivePayOrder;
import com.xmniao.domain.live.LiveSalary;
import com.xmniao.domain.live.LiveSalaryData;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.LiverJournalCount;
import com.xmniao.domain.live.SalaryAdmin;
import com.xmniao.domain.vstar.TVstarRewardRecord;
import com.xmniao.service.quartz.LiveSalaryQuertzService;
import com.xmniao.service.quartz.ModifyAnchorGiftBirdEggQuertzService;
import com.xmniao.service.vstar.constant.VstarConstant;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.live.LiveOrderService;
import com.xmniao.thrift.pay.LiveWalletService;
import com.xmniao.urs.dao.LiverDao;
import com.xmniao.urs.dao.UrsEarningsRankDao;

/**
 * 项目名称：busineService
 * 
 * 类名称：LiveOrderServiceImpl
 * 
 * 类描述：更新鸟币充值订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年8月22日下午1:49:29
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service("LiveOrderServiceImpl")
public class LiveOrderServiceImpl implements LiveOrderService.Iface {

	// 初始化日志类
	private final Logger log = Logger.getLogger(LiveOrderServiceImpl.class);

	@Autowired
	private LiveOrderDao liveOrderDao;


	@Autowired
	private LiveRecordDao liveRecordDao;

	@Autowired
	private CouponOrderDao couponOrderDao;

	@Autowired
	private LiveLedgerRecordDao liveLedgerReocrdDao;

	@Autowired
	private LiverDao liverDao;


	/**
	 * 注入消息队列生产者
	 */
	@Autowired
	private DefaultMQProducer producerConnection;

	@Autowired
	private String liveLedgerTopic;


	@Autowired
	private String liveDividendsLedgerTagsV3;
	
	/**
	 * 注入直播礼物打赏鸟蛋分账MQ消息主题
	@Autowired
	private String liveRewardLedgerTopic;
	 */
	
	/**
	 * 注入直播礼物打赏鸟蛋分账MQ消息标签
	 */
	@Autowired
	private String liveRewardLedgerTag;
	
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private String liverLedgerQueue;
    
    @Autowired
    private LiveLedgerServiceImpl liveLedgerService;

    @Autowired
    private GeneralRechargeLiveOrder generalRechargeLiveOrderService;
	
	@Autowired
	private VipRechargeLiveOrder xmnRechargeLiveOrderService;

	@Autowired
	private SellerRechargeLiveOrder sellerRechargeLiveOrderService;

	@Autowired
	private DGRechargeLiveOrder dgRechargeLiveOrderService;

    @Autowired
    private VerRechargeLiveOrder verRechargeLiveOrderService;
    
    @Autowired
    private ManorRechargeLiveOrder manorRechargeLiveOrderService;
	
	@Autowired
	private FansCouponServiceImpl fansCouponService;

	@Autowired
	private LiveSalaryDao liveSalaryDao;
	
	@Autowired
	private LiveSalaryQuertzService liveSalaryQuertzService;
	
	@Autowired
	private ModifyAnchorGiftBirdEggQuertzService anchorGiftBirdEggQuertz;
	
	/**
	 * 注入会员收益等级信息
	 */
	@Autowired
	private UrsEarningsRankDao earningsRankDao;
	
	/**
	 * 注入新时尚大赛推荐奖励发放记录服务
	 */
	@Autowired
	private TVstarRewardRecordDao vstarRewardDao;
	
	 /**
     * 注入支付服务的IP地址
     */
	@Resource(name="transLedgerIP")
    private String transLedgerIP;
    
    /**
     * 注入支付服务的端口号
     */
	@Resource(name="transLedgerPort")
    private int transLedgerPort;

	/**
	 * 更新订单状态
	 */
	@Override
	public ResponseData updateLiveOrder(Map<String, String> paramMap) throws FailureException, TException {
		if (this.updatePayOrderStatusAndRecharge(paramMap)) {
			log.info("订单已更新并完成");
			if (paramMap.get("status").equals("1")) {
				try {
				/**
				 * 充值订单后补说明：
				 * object_oriented = 0，作普通常规充值
				 * object_oriented = 1，2，3 作壕赚充值
				 * object_oriented = 4 作V客充值 
				 * object_oriented = 5 作黄金庄园充值 
				 */
					LivePayOrder liveOrder = liveOrderDao.getLiveOrder(paramMap.get("bid"));
					int chargePlatform = liveOrder.getObjectOriented();
					RechargeLiveOrderService<LivePayOrder> rechargeLiveOrderService = getRechargeLiveOrderService(
							chargePlatform);
					rechargeLiveOrderService.initUidRelation(liveOrder);
					rechargeLiveOrderService.initRechargeOrderLedger(liveOrder);
					rechargeLiveOrderService.recommendOrderLedger(liveOrder);
				} catch (Exception e) {
					log.error("对充值订单进行分账初始化及上级推荐分账异常", e);
				}
			}
			return new ResponseData(0, "成功", null);
		} else {
			return new ResponseData(1, "更新失败", null);
		}

	}

	/**
	 * 更新粉丝券订单 一、成功 1 支付鸟粪卡 1.修改订单状态 t_coupon_order 2.如有使用，消费预售抵用券
	 * t_coupon_detail 3.发放粉丝券 t_coupon_detail 4.赠送预售抵用券t_coupon_detail 5.赠送积分
	 * 
	 * 二、失败/取消 1.修改订单状态 2.恢复占用的预售抵用券 3.更新粉丝券库存数量
	 */
	@Override
	public Map<String, String> updateCouponOrder(Map<String, String> paraMap) throws FailureException, TException {
		return fansCouponService.updateCouponOrder(paraMap);
	}

	/**
	 * 获取商家直播统计信息
	 */
	@Override
	public ResponseData getSellerLiveCountInfo(Map<String, String> paramMap) throws FailureException, TException {
		ResponseData responseData = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (StringUtils.isBlank(paramMap.get("sellerid"))) {
				responseData = new ResponseData(2, "请传入商家编号", null);
			}
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("sellerid", paramMap.get("sellerid"));
			pMap.put("nowDate", new Date());
			if (StringUtils.isBlank(paramMap.get("type")) || paramMap.get("type").equals("0")) {
				List<Map<String, Object>> list = liveRecordDao.getSellerLiveRecordCount(pMap);
				int hasLive = 0;
				int hasAdvance = 0;
				for (Map<String, Object> map : list) {
					if (map.get("zhiboType") == null) {
					} else if ((int) map.get("zhiboType") == 0) {
						hasAdvance = 1;
					} else if ((int) map.get("zhiboType") == 1) {
						hasLive = 1;
					}
					if (hasAdvance == 1 && hasLive == 1) {
						break;
					}
				}
				int hasFansCouponCount = couponOrderDao.getSellerFansCouponCount(pMap) > 0 ? 1 : 0;
				int weights = hasLive * 3 + hasAdvance * 1 + hasFansCouponCount * 2;
				resultMap.put("hasFansCoupon", hasFansCouponCount + "");
				resultMap.put("hasLive", hasLive + "");
				resultMap.put("hasAdvance", hasAdvance + "");
				resultMap.put("weights", weights + "");
				resultMap.put("sellerid", paramMap.get("sellerid"));
				responseData = new ResponseData(0, "查询成功", resultMap);
			} else if (paramMap.get("type").equals("1")) {
				List<Map<String, Object>> list = liveRecordDao.getSellerLiveRecordCount(pMap);
				int hasAdvance = 0;
				for (Map<String, Object> map : list) {
					if (map.get("zhiboType") == null) {
					} else if ((int) map.get("zhiboType") == 0) {
						hasAdvance = 1;
						break;
					}
				}
				resultMap.put("hasAdvance", hasAdvance + "");
				resultMap.put("sellerid", paramMap.get("sellerid"));
				responseData = new ResponseData(0, "查询成功", resultMap);
			} else if (paramMap.get("type").equals("2")) {
				List<Map<String, Object>> list = liveRecordDao.getSellerLiveRecordCount(pMap);
				int hasLive = 0;
				for (Map<String, Object> map : list) {
					if (map.get("zhiboType") == null) {
					} else if ((int) map.get("zhiboType") == 1) {
						hasLive = 1;
						break;
					}
				}
				resultMap.put("hasLive", hasLive + "");
				resultMap.put("sellerid", paramMap.get("sellerid"));
				responseData = new ResponseData(0, "查询成功", resultMap);
			} else if (paramMap.get("type").equals("3")) {
				int hasFansCouponCount = couponOrderDao.getSellerFansCouponCount(pMap) > 0 ? 1 : 0;
				resultMap.put("hasFansCoupon", hasFansCouponCount + "");
				resultMap.put("sellerid", paramMap.get("sellerid"));
				responseData = new ResponseData(0, "查询成功", resultMap);
			} else {
				responseData = new ResponseData(2, "请传入正确的参数", null);
			}
		} catch (Exception e) {
			log.error("查询异常", e);
			responseData = new ResponseData(1, "查询失败", null);
		}
		return responseData;
	}

	/**
	 * 写入分账记录
	 * 
	 * @Title: insertLedgerRecord
	 * @Description:
	 */
	public LiveLedgerRecord insertLedgerRecordByGift(int uid, BigDecimal ledgerAmount, BigDecimal realLedgerAmount,
			String liveRecordId, String remarks, Integer giftId) {
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
	 * 会员消费送礼
	 */

	@Override
	public ResponseData consumeGift(Map<String, String> paramMap) throws FailureException, TException {
		log.info("consumeGift:" + paramMap);
		ResponseData responseData = new ResponseData();
		double returnZbalance = PreciseComputeUtil.mul(Double.valueOf(paramMap.get("consumeAmount")),
				Double.valueOf(paramMap.get("returnRatio")), 2);
		BigDecimal giftLedger = BigDecimal.valueOf(returnZbalance);

		int uid = Integer.parseInt(paramMap.get("uid"));
		Map<String, String> map = new HashMap<>();
		try {
			sellerRechargeLiveOrderService.getLock(uid, 3000);
			map = liveLedgerService.consumeGiftReturnCoin(paramMap, giftLedger);

		} finally {
			sellerRechargeLiveOrderService.releaseLock(uid);
		}
	
		Map<String,String> anchorVkeMap = new HashMap<String, String>();
		if(paramMap.get("anchorUid")!=null){
			anchorVkeMap = liveLedgerService.getGivedGiftVkeInfo(Integer.valueOf(paramMap.get("anchorUid")));
		}
		LiverJournalCount journal = new LiverJournalCount();
		journal.setUid(uid);
		journal.setComsumAmount(new BigDecimal(map.get("consumeAmount")));
		journal.setCurrentConsumeLedger(giftLedger);
		this.updateLiverJournalCountInfo(journal);
		responseData.setState(0);
		responseData.setMsg("成功");
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("uid", map.get("uid"));
//		resultMap.put("ledgerId", record.getId() + "");
		if (map.get("walletRecordId") != null) {
			resultMap.put("walletRecordId", map.get("walletRecordId"));
		}
		resultMap.put("returnCoin", map.get("zbalance") + "");
		resultMap.putAll(anchorVkeMap);
		responseData.setResultMap(resultMap);
		return responseData;
	}

	/**
	 * 日常领取红包
	 */
	@Override
	public ResponseData receiveDailyRedpacket(Map<String, String> paramMap) throws FailureException, TException {
		/*
		 * 1.验证红包记录 2.验证用户红包资格 3.领取红包{ 1.查看用户所有可领红包订单 2. }
		 * 
		 */
		log.info("领取每日分红红包：" + paramMap);
		ResponseData responseData = new ResponseData();
		// try{
		int isVirtual = 0;
		String date = DateUtil.dateFormatY1(new Date());
		LiveLedgerRecord reqRecord = new LiveLedgerRecord();
		reqRecord.setUid(Integer.parseInt(paramMap.get("uid")));
		reqRecord.setId(Integer.parseInt(paramMap.get("recordId")));
		reqRecord.setRedpacketRocordDate(DateUtil.dateFormatY1(new Date()));
		LiveLedgerRecord record = liveLedgerReocrdDao.getLiveLedgerRecord(reqRecord);
		if (record == null || record.getLedgerSource() != 3) {
			return new ResponseData(1, "查询不到对应红包记录", null);
		}
		if (record.getStatus() != 0 && record.getStatus() != 3) {
			return new ResponseData(1, "该红包已领取", null);
		}
		if (!record.getRedpacketRocordDate().startsWith(date)) {
			return new ResponseData(1, "该红包已过期", null);
		}
		if (this.getLiverRedPacketAuthority(record.getUid()) == 2) {
			isVirtual = 1;
		} else if (this.getLiverRedPacketAuthority(record.getUid()) == 1) {
			return new ResponseData(1, "用户没有领取资格", null);
		}
		boolean reReceive = false;
		if (record.getStatus() == 3) {
			// 该红包是领取后到账失败，再次领取，因红包在首次领取时，红包金额已确定，且已将对应金额对应到充值订单的当前已奖励红包金额中，所以再次领取时，只需将红包状态标记为领取中再次发起分账MQ即可
			reReceive = true;
		}
		try {
			// 改红包状态
			liveLedgerService.updateRedpacketStatus(record);
		} catch (FailureException e) {
			log.error("用户已领取红包", e);
			return new ResponseData(1, "用户已领取每日分红红包", null);
		} catch (Exception e) {
			log.error("领取每日分红红包异常", e);
			return new ResponseData(1, "领取每日分红红包异常", null);
		}

		try {
			LiveLedgerRecord record2 = liveLedgerReocrdDao.getLiveLedgerRecord(reqRecord);
			if (reReceive) {
				Map<String, BigDecimal> marginMap = liveLedgerService.reReceiveRedpacket(record2);
					sendDailyRedpacketMsg(record.getId());
				BigDecimal ledgerAmount = marginMap.get("ledgerAmount");
				BigDecimal ledgerCoin = marginMap.get("ledgerCoin");
				responseData.setState(0);
				responseData.setMsg("成功");
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("uid", paramMap.get("uid"));
				resultMap.put("redpacketAmount", ledgerAmount + "");
				resultMap.put("redpacketCoin", ledgerCoin + "");
				resultMap.put("isVirtual", isVirtual + "");
				responseData.setResultMap(resultMap);
			} else {

				Map<String, BigDecimal> marginMap = liveLedgerService.ledgerDailyRedpacket(record2, null, isVirtual);
				BigDecimal ledgerAmount = marginMap.get("ledgerAmount");
				BigDecimal ledgerCoin = marginMap.get("ledgerCoin");
				if ((ledgerAmount.compareTo(BigDecimal.ZERO) > 0 || ledgerCoin.compareTo(BigDecimal.ZERO) > 0)
						&& isVirtual == 0) {
					LiverJournalCount journal = new LiverJournalCount();
					journal.setUid(record.getUid());
					journal.setCurrentDividendLedger(ledgerAmount);
					journal.setCurrentDividendCoinLedger(ledgerCoin);
					journal.setExpectedPriviledgeLedger(liveLedgerService.getExpectedPriviledgeLedger(record.getUid()));
					this.updateLiverJournalCountInfo(journal);

					sendDailyRedpacketMsg(record.getId());

				}

				responseData.setState(0);
				responseData.setMsg("成功");
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("uid", paramMap.get("uid"));
				resultMap.put("redpacketAmount", ledgerAmount + "");
				resultMap.put("redpacketCoin", ledgerCoin + "");
				resultMap.put("isVirtual", isVirtual + "");
				responseData.setResultMap(resultMap);
			}
		} catch (FailureException e) {
			log.error("领取异常", e);
			responseData.setState(2);
			responseData.setMsg(e.getInfo());
		} catch (Exception e) {
			log.error("领取异常", e);
			responseData.setState(2);
			responseData.setMsg("领取每日分红红包异常");
		}
		return responseData;

	}

	/**
	 * 
	 * 方法描述：更新直播分账记录 创建人： ChenBo 创建时间：2016年12月26日 void
	 */
	public void updateLiveLedgerRecord(LiveLedgerRecord record) {
		record.setUpdateDate(new Date());
		LiveLedgerRecord reqRecord = new LiveLedgerRecord();
		reqRecord.setId(record.getId());
		LiveLedgerRecord oRecord = liveLedgerReocrdDao.getLiveLedgerRecord(reqRecord);
		if (oRecord.getStatus() == 2) {
			return;
		}
		if (oRecord.getStatus() == record.getStatus()) {
			return;
		}
		liveLedgerReocrdDao.updateLiveLedgerRecord(record);
	}

	private void updateLiverJournalCountInfo(LiverJournalCount journal) {
		redisTemplate.opsForList().leftPush(liverLedgerQueue, JSONObject.toJSONString(journal));
	}

	public int getLiverRedPacketAuthority(int uid) {
		LiverBean liver = liverDao.getLiverByUid(uid);
		return liver.getRedPacketAuthority();
	}
	
	public RechargeLiveOrderService<LivePayOrder> getRechargeLiveOrderService(int type){
		RechargeLiveOrderService<LivePayOrder> recharageLiveOrderService= null;
		switch(type){
		case 0:
			recharageLiveOrderService=  generalRechargeLiveOrderService;
			break;
		case 1:
			recharageLiveOrderService = xmnRechargeLiveOrderService;
			break;
		case 2:
			recharageLiveOrderService = sellerRechargeLiveOrderService;
			break;
		case 3:
			recharageLiveOrderService = dgRechargeLiveOrderService;
			break;
		case 4:
			recharageLiveOrderService = verRechargeLiveOrderService;
			break;
		case 5:
			recharageLiveOrderService = manorRechargeLiveOrderService;
			break;
		default :
			recharageLiveOrderService=  generalRechargeLiveOrderService;
			break;
		}
		return recharageLiveOrderService;
	}

	private boolean updatePayOrderStatusAndRecharge(Map<String, String> paramMap) throws FailureException {

		log.info("更新充值鸟豆订单：updateLiveOrder " + paramMap);

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("id", paramMap.get("bid"));

		// 验证参数
		if (StringUtils.isNotBlank(paramMap.get("bid"))// 订单id
				&& StringUtils.isNotBlank(paramMap.get("status"))// 支付状态 0 失败 1
																	// 成功
				&& StringUtils.isNotBlank(paramMap.get("payType"))// 支付方式
				&& StringUtils.isNotBlank(paramMap.get("zdate"))) {// 支付时间

			// 支付状态 0 未支付 1 成功
			int status = Integer.valueOf(paramMap.get("status"));
			if (/* status != 0 && */status != 1) {
				log.error("支付状态参数错误" + paramMap);
				return false;
			}

			LivePayOrder liveOrder = liveOrderDao.getLiveOrder(paramMap.get("bid"));
			log.info("liveOrder:" + liveOrder);
			if (liveOrder == null) {
				log.error("未查询到订单：" + paramMap.get("bid"));
				return false;
			}

			if (1 == liveOrder.getPayState()) {
				log.info("该订单已经支付完成");
				return false;
			}

			liveLedgerService.updateLivePayOrder(paramMap, liveOrder);
			return true;
		} else {
			log.error("参数传入错误" + paramMap);
			return false;
		}
	}

	private void sendDailyRedpacketMsg(Integer id){

		List<Map<String,String>> msgLedgerList = liveLedgerService.getDividendsLedgerDetailList(id);
		String json = JSONObject.toJSONString(msgLedgerList);
		Message msg = new Message();
		msg.setTopic(liveLedgerTopic);
		msg.setTags(liveDividendsLedgerTagsV3);
		msg.setKeys(id+"");
		msg.setBody(json.getBytes());
		try {
			producerConnection.send(msg);
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			e.printStackTrace();
			log.error("发送领红包入账消息失败",e);
		}
		log.info("会员领取每日分红分包消息:"+msgLedgerList);
	}

	@Override
	public Map<String, String> uploadLiveSalary(Map<String, String> paramMap)throws FailureException {
		if("manual".equals(paramMap.get("type"))){
			long i=liveSalaryDao.hasThisMonSalary();
			Map<String, String> resultMap = new HashMap<>();
			if(i>0){
				resultMap.put("state", "1");
				resultMap.put("msg", "本月工资已生成");
			}else{
				try {
					liveSalaryQuertzService.exected();
					resultMap.put("state", "0");
					resultMap.put("msg", "生成成功");
				} catch (Exception e) {
					resultMap.put("state", "1");
					resultMap.put("msg", "生成失败");
				}
			}
			return resultMap;
		}
		try {
			anchorGiftBirdEggQuertz.giveBack();
		} catch (TTransportException e) {
			log.error("[执行还原丢失礼物鸟蛋方法出现异常]",e);
		}
		String anchorId = paramMap.get("anchorId");
		String updateTime = paramMap.get("updateTime");
		Map<String, String> resultMap = new HashMap<>();
		if(new Integer(updateTime.substring(0,6))>=new Integer(DateUtil.format(new Date(), "yyyyMM"))){
			//统计月份大于等于本月
			resultMap.put("state", "0");
			resultMap.put("msg", "统计月份未到");
			return resultMap;
		}
		try {
			if (StringUtils.isBlank(anchorId) || StringUtils.isBlank(updateTime)) {
				resultMap.put("state", "2");
				resultMap.put("msg", "参数有误");
				return resultMap;
			}
			Integer leverId = new Integer(anchorId);
			// 判断工资是否发放
			LiveSalary liveSalary = liveSalaryDao.selectAnchorAtTime(new Integer(anchorId), updateTime);
			if (liveSalary != null && liveSalary.getStatus() == 1) {
				resultMap.put("state", "0");
				resultMap.put("msg", "工资已发放");
				return resultMap;
			}
			SalaryAdmin salaryAdmin = new SalaryAdmin(updateTime);

			LiverBean liver = liverDao.getLiverLeverById(leverId);

			LiveSalaryData liveSalaryData = liveSalaryDao.getLastMonGiveByAnchorId(salaryAdmin.getBeginDate(),
					salaryAdmin.getEndDate(), leverId);

			liver.setLiveSalaryData(liveSalaryData);

			LiveSalary liveSalary2 = salaryAdmin.accountOne(liver);

			if (liveSalary != null) {
				//修改工资
				if (liveSalary2.getBaseSalary().compareTo(BigDecimal.valueOf(0)) >= 1) {
					liveSalary2.setId(liveSalary.getId());

					liveSalary2.setUpdateTime(new Date());

					int i = liveSalaryDao.updateByPrimaryKey(liveSalary2);

					if (i > 0) {
						resultMap.put("state", "0");
						resultMap.put("msg", "工资修改成功");
						return resultMap;
					}
				}
				resultMap.put("state", "1");
				resultMap.put("msg", "工资修改失败");
			} else {
				//添加工资条
				List<LiveSalary> liveSalarys = new ArrayList<>();
				liveSalarys.add(liveSalary2);
				List<LiverBean> failSalarys = new ArrayList<>();
				List<LiveSalary> successList = liveSalaryQuertzService.updateZbalanceAfterSalary(liveSalarys, failSalarys);
				if(!successList.isEmpty()){
					int i = liveSalaryDao.addBatch(successList);
					if(i>0){
						resultMap.put("state", "0");
						resultMap.put("msg", "添加工资成功");
						int j=liveSalaryDao.updateFailToSuccess(leverId,updateTime);
						if(j<=0){
							throw new FailureException(1, "修改失败状态不成功");
						}
					}
				}else{
					int i = liveSalaryDao.updateFail(failSalarys.get(0),leverId,updateTime);
					if(i<=0){
						HashMap<String,Object> hashMap = new HashMap<>();
						hashMap.put("fail", failSalarys);
						hashMap.put("countTime", DateUtil.format(salaryAdmin.getBeginDate(), "yyyyMM"));
						liveSalaryDao.addFailBatch(hashMap);
					}
					resultMap.put("state", "0");
					resultMap.put("msg", "添加工资成功");
				}
			}
			return resultMap;
		} catch (Exception e) {
			log.error("调用更新主播工资接口时出现异常", e);
			resultMap.put("state", "1");
			resultMap.put("msg", "工资修改失败");
			return resultMap;
		}
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public ResponseData anchorEggReceipts(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("进入主播鸟蛋入账并分账方法+:paramMap="+paramMap);
		
		ResponseData responseData=new ResponseData();
		
		try {
			//验证主播UID
			if(StringUtils.isBlank(paramMap.get("uid"))){
				log.error("主播UID为空");
				responseData.setState(2);
				responseData.setMsg("主播UID为空");
				return responseData;
			}
			
			//通告ID
			if(StringUtils.isBlank(paramMap.get("liveRecordId"))){
				log.error("通告ID为空");
				responseData.setState(2);
				responseData.setMsg("通告ID为空");
				return responseData;
			}
			
			
			//鸟蛋收入
			String eggs = paramMap.get("eggs");
			if(StringUtils.isBlank(eggs)){
				log.error("鸟蛋收入为空");
				responseData.setState(2);
				responseData.setMsg("鸟蛋收入为空");
				return responseData;
			}
			
			String uid = paramMap.get("uid");
			LiverBean anchor = liverDao.getLiverByUid(new Integer(uid));
			if(anchor==null){
				log.error("未查询到到主播信息:uid="+uid);
				responseData.setState(1);
				responseData.setMsg("未查询到到主播信息:uid="+uid);
				return responseData;
			}
			
			String anchorEgg="";//主播收入鸟蛋
			String vkeAmount="";//V客收入余额
			String vkeRatio="";//V客分账比例
			
			if(false/*StringUtils.isNotBlank(paramMap.get("vkeUid"))*/){//V客参与分账
				String vkeUid = paramMap.get("vkeUid");
				Map<String, Object> vkeRatioMap = earningsRankDao.getVkeRatioByUid(vkeUid);
				if(vkeRatioMap==null || vkeRatioMap.isEmpty() || vkeRatioMap.get("vke_ratio")==null){
					log.error("未查询到V客分账比例信息:vkeUid="+vkeUid);
					responseData.setState(1);
					responseData.setMsg("未查询到V客分账比例信息:vkeUid="+vkeUid);
					return responseData;
				}
				
				BigDecimal vke_ratio = (BigDecimal) vkeRatioMap.get("vke_ratio");
				BigDecimal totalEggs = new BigDecimal(eggs);//本场直播总打赏鸟蛋数
				BigDecimal vkeAmountEggs = totalEggs.multiply(vke_ratio);//V客收入总鸟蛋数
				BigDecimal anchorEggs = totalEggs.subtract(vkeAmountEggs);//主播收入总鸟蛋数
				
				anchorEgg=anchorEggs.toString();
				vkeAmount=vkeAmountEggs.toString();
				vkeRatio=vke_ratio.toString();
				
			}else{//无V客分账信息
				anchorEgg=eggs;
			}
			
			Map<String,String> resultMap=new HashMap<String,String>();
			resultMap.put("anchorEgg", anchorEgg);
			resultMap.put("vkeAmount", vkeAmount);
			resultMap.put("vkeRatio", vkeRatio);
			
			//发送直播直播分账信息
			sendLiveLedgerMsg(paramMap,resultMap);
			
			responseData.setState(0);
			responseData.setMsg("成功");
			responseData.setResultMap(resultMap);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return responseData;
	}

	 /**
	 * 方法描述：发送直播分账MQ消息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-23下午2:04:20 <br/>
	 * @param paramMap
	 * @param resultMap 
	 */
	private void sendLiveLedgerMsg(Map<String, String> paramMap, Map<String, String> resultMap) {
		String liveRecordId = paramMap.get("liveRecordId");
		String uid = paramMap.get("uid");//主播UID
		String balance = resultMap.get("anchorEgg");//主播获得鸟蛋
		String vkid = paramMap.get("vkeUid");//V客UID
		String vkeEgg = resultMap.get("vkeAmount");//V客获得鸟蛋
		String amount=null;//V客分账金额
		if(StringUtils.isNotBlank(vkeEgg)){
			BigDecimal vkeEggs = new BigDecimal(vkeEgg);
			BigDecimal vkeAmount = vkeEggs.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
			amount=vkeAmount.toString();
		}
		
		Map<String,Object> msgMap=new HashMap<String,Object>();
		msgMap.put("uid", uid);
		msgMap.put("balance", balance);
		msgMap.put("vkid", vkid);
		msgMap.put("amount", amount);
		msgMap.put("source", UUID.randomUUID().toString());
		
		String json = JSONObject.toJSONString(msgMap);
		Message msg = new Message();
//		msg.setTopic(liveRewardLedgerTopic);
		msg.setTopic(liveLedgerTopic);
		msg.setTags(liveRewardLedgerTag);
		msg.setKeys(liveRecordId);
		msg.setBody(json.getBytes());
		
		try {
			producerConnection.send(msg);
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 新时尚大赛推荐奖励发放
	 */
	@Override
	@Transactional(rollbackFor={Exception.class})
	public ResponseData vstarRewardIssue(Map<String, String> paramMap)
			throws FailureException, TException {
		// TODO Auto-generated method stub
		log.info("进入新时尚大赛推荐奖励发放+:paramMap="+paramMap);
		
		ResponseData responseData=new ResponseData();
		TTransport transport = null;
		try {
			//验证主播playerId
			if(StringUtils.isBlank(paramMap.get("playerId"))){
				log.error("主播playerId为空");
				responseData.setState(2);
				responseData.setMsg("主播playerId为空");
				return responseData;
			}
			
			//验证主播UID
			if(StringUtils.isBlank(paramMap.get("playerUid"))){
				log.error("主播UID为空");
				responseData.setState(2);
				responseData.setMsg("主播UID为空");
				return responseData;
			}
			
			//验证推荐人referrerUid
			if(StringUtils.isBlank(paramMap.get("referrerUid"))){
				log.error("推荐人referrerUid为空");
				responseData.setState(2);
				responseData.setMsg("推荐人referrerUid为空");
				return responseData;
			}
			
			//通告ID
			if(StringUtils.isBlank(paramMap.get("liveRecordId"))){
				log.error("通告ID为空");
				responseData.setState(2);
				responseData.setMsg("通告ID为空");
				return responseData;
			}
			
			//直播时长
			if(StringUtils.isBlank(paramMap.get("liveTime"))){
				log.error("直播时长liveTime为空");
				responseData.setState(2);
				responseData.setMsg("直播时长liveTime为空");
				return responseData;
			}
			
			String playerId = paramMap.get("playerId");//选手ID
			TVstarRewardRecord recordReq= new TVstarRewardRecord();
			recordReq.setPlayerId(new Integer(playerId));
			recordReq.setStatus(VstarConstant.REWARD_RECEIVE_STATUS.SUCCESS);//领取状态，1领取成功，2领取失败
			TVstarRewardRecord rewardRecord = vstarRewardDao.selectByBean(recordReq);
			
			Map<String, Object> vstarRewardConf = vstarRewardDao.getVstarRewardConf(null);
			if(vstarRewardConf==null){
				log.error("未查询到新时尚大赛推荐奖励配置");
				responseData.setState(2);
				responseData.setMsg("未查询到新时尚大赛推荐奖励配置");
				return responseData;
			}
			
			if(rewardRecord==null){//推荐奖励，只可领取一次
				Integer pilotTime = (Integer)vstarRewardConf.get("pilotTime");//有效试播时长
				BigDecimal rewardCoin = (BigDecimal)vstarRewardConf.get("rewardCoin");//奖励鸟币
				String liveTime = paramMap.get("liveTime");//直播时长
				boolean valid=new Integer(liveTime).compareTo(pilotTime)>=0;
				if(valid){
					String playerUid = paramMap.get("playerUid");
					String referrerUid = paramMap.get("referrerUid");
					String liveRecordId = paramMap.get("liveRecordId");
					TVstarRewardRecord vstarRecordVO=new TVstarRewardRecord();
					vstarRecordVO.setPlayerId(new Integer(playerId));
					vstarRecordVO.setPlayerUid(new Integer(playerUid));
					vstarRecordVO.setReferrerUid(new Integer(referrerUid));
					vstarRecordVO.setReceiveCoin(rewardCoin);
					int add = vstarRewardDao.add(vstarRecordVO);
					
					if(add>0){
						 //调用分账服务的IP和端口号
			            transport = new TSocket(transLedgerIP, transLedgerPort);
			            TFramedTransport frame = new TFramedTransport(transport);
			            // 设置传输协议为 TBinaryProtocol
			            TProtocol protocol = new TBinaryProtocol(frame);
			            //分账服务的综合服务接口模块
			            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
			            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
			            //打开端口,开始调用
			            transport.open();
			            
			            Map<String, String> paraMap=new HashMap<String,String>();
			            paraMap.put("uid", referrerUid);
			            paraMap.put("rtype", XmnConstants.LIVE_RTYPE_38+"");
			            paraMap.put("zbalance", rewardCoin.toString());
			            paraMap.put("liveRecordId", liveRecordId);
			            paraMap.put("option", "0");//0 加，1减
						client.liveWalletOption(paraMap);
					}
					
					responseData.setState(0);
					responseData.setMsg("success");
				}
			}else{
				responseData.setState(1);
				responseData.setMsg("fail,奖励已发放");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行新时尚大赛推荐奖励发放方法vstarRewardIssue()异常", e);
			throw new FailureException(1,"执行新时尚大赛推荐奖励发放方法vstarRewardIssue()异常"+e.getMessage());
		}finally{
			if(transport!=null){
				transport.close();
			}
		}
		
		return responseData;
	}
}
