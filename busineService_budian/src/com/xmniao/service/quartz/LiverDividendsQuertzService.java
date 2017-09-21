/**    
 * 文件名：LiverDividendsQuertzService.java    
 *    
 * 版本信息：    
 * 日期：2016年12月22日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2016     
 * 版权所有    
 *    
 */
package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.DateUtil;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.dao.live.LiveDividendsLedgerDetailRecordDao;
import com.xmniao.dao.live.LiveGivedGiftDao;
import com.xmniao.dao.live.LiveLedgerRecordDao;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.dao.live.LivePrivilegeDao;
import com.xmniao.domain.live.LiveDividendsLedgerDetailRecord;
import com.xmniao.domain.live.LiveFansRank;
import com.xmniao.domain.live.LiveFansRankDetail;
import com.xmniao.domain.live.LiveLedgerRecord;
import com.xmniao.domain.live.LivePrivilege;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.RankRedPacketDetail;
import com.xmniao.service.common.RedisLockUtil;
import com.xmniao.service.live.LiveLedgerServiceImpl;
import com.xmniao.urs.dao.LiveFansRankDao;
import com.xmniao.urs.dao.LiverDao;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：LiverDividendsQuertzService
 * 
 * 类描述： 直播平台每日给会员派发红包分红定时任务
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月22日 上午9:51:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service("liverDividendsQuertz")
public class LiverDividendsQuertzService {
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(LiverDividendsQuertzService.class);
    
    
    @Autowired
    private LiverDao liverDao;
    
    @Autowired
    private LiveFansRankDao liveFansRankDao;
    
    @Autowired
    private LiveLedgerRecordDao liveLedgerRecordDao;
    
    @Autowired
    private LiveDividendsLedgerDetailRecordDao liveDividendsLedgerDetailRecordDao;
    
    @Autowired
    private LiveOrderDao liveOrderDao;
    
    @Autowired
    private LiveGivedGiftDao liveGivedGiftDao;
    
    @Autowired
    private String liveDividendsTest;
    
    @Autowired
    private String disableDividendsDate;
        
    @Autowired
    private LiveLedgerServiceImpl liveLedgerService;
    
    @Autowired
    private LivePrivilegeDao livePrivilegeDao;
    
    @Resource(name="testRedpackagePhone")
    private List<String> testRedpackagePhone;
    
	public void liverDividends(){
		try{
			/*
			 * 1.检测当天是否不分红
			 * 2.检测当天是否已分红
			 * 3.for{
			 *   1.获取当前有可奖励订单的用户
			 *   	for{
			 *   	1.查看用户是否限领分红红包
			 *   	2.获取用户前一天的打赏信息
			 *   	3.根据前一天的打赏信息，与当前用户粉丝级别对比，检测其是否可领分红红包
			 *   	4.查询用户当前所有在享受红包订单
			 *   		for{
			 *   		1.检测订单的奖励及红包及现金/鸟币情况
			 *   		2.获取订单本次可享受的红包比例区间
			 *   		3.根据订单与红包比例，随机出本单可获取的现金+鸟币
			 *   		}
			 *   	5.合并用户本次能获取的所有红包，并写入红包记录
			 *   	}
			 *   2.获取下一批有可奖励订单的用户
			 *   }
			 */
			log.info("开始执行会员每日分红任务");
			String[] disableDate = disableDividendsDate.split(",");
			Date nowDate = new Date();
			String date = DateUtil.dateFormatY1(nowDate);
			for(String disDate:disableDate){
				if(date.equals(disDate)){
					log.info(date+"当日不进行分红红包分账");
					return;
				}
			}
			

			LiveLedgerRecord reqRecord = new LiveLedgerRecord();
			reqRecord.setRedpacketRocordDate(date);
			reqRecord.setLedgerSource(3);
			long count =liveLedgerRecordDao.countLedgerRecord(reqRecord);
			if(count>0 && !isTest()){
				log.info(date+"已进行每日分红红包分账");
				return ;
			}
			Map<String,Object> uMap = new HashMap<String,Object>();
			uMap.put("redPacketAuthority", 1);
			List<Integer> uidlist1 = liverDao.getLiverUidList(uMap);	/* 没有获取红包资格的会员列表  */
						
			int pageSize = 500;
			int pageNo = 0;
			
			TestUser testUser = this.getTestPhone();

			
			List<Integer> ursList=null;
			List<Map<String,Object>> consumeList = null;
			List<LiverBean> liverList = null;
			Map<String,Object> reqMap = new HashMap<String,Object>();
			reqMap.put("pageSize", pageSize);
			do{
				reqMap.put("pageNo", pageNo);
				if(!testUser.getIsAllUser()){
					if(testUser.getTestPhoneList().size()>0){
						List<LiverBean> testLiver = liverDao.getLiverListByPhone(testUser.getTestPhoneList());
						StringBuilder sb = new StringBuilder();
						for(int i=0;i<testLiver.size();i++){
							LiverBean bean = testLiver.get(i);
							sb.append(bean.getUid());
							if(i!=(testLiver.size()-1)){
								sb.append(",");
							}
						}
						reqMap.put("uids", sb);
					}else{
						log.info("没有人需要进行测试红包");
						break;
					}
				}else{
				}
				ursList = livePrivilegeDao.getLiveOrderHasDividendsUserList(reqMap);	//查询具有可分账订单的用户
				pageNo++;
				
				Map<String,Object> reqConsumeMap = new HashMap<String,Object>();
				reqConsumeMap.put("nowDate", DateUtil.calendarDay(nowDate, isTest()?0:-1));
				reqConsumeMap.put("ursList", ursList);
				consumeList = liveGivedGiftDao.dailyConsumeCountList(reqConsumeMap);	//查询用户的打赏信息
				
				liverList = liverDao.getLiverListByUids(ursList);	//查询用户的详细信息
				
				
				Map<String,Object> consumeMap = new HashMap<>();
				for(Map<String,Object> map:consumeList){
					consumeMap.put(map.get("uid")+"", map.get("consumeAmount"));
				}
				consumeList.clear();
				
				Map<String,LiverBean> liverMap = new HashMap<>();
				for(LiverBean liverBean:liverList){
					liverMap.put(liverBean.getUid()+"", liverBean);
				}
				liverList.clear();
				
				for(Integer uid:ursList){
					try{
						
						if(isTest() && hasUnreceiveDailyRedpacket(uid)){
							log.info("测试环境下，该用户"+uid+"当前还有未领取红包，不再发放新红包");
							continue;
						}
						if(uidlist1.contains(uid)){
							log.info("该用户"+uid+"没有获取每日分红资格——没有获取红包的权限");
							continue;
						}
						
						List<LivePrivilege> orderList = liveLedgerService.getDividendsLivePayOrderByAll(uid);
						if(orderList == null||orderList.size()==0){
							log.info("该用户"+uid+"没有获取每日分红资格——没有可返的充值订单");
							continue;
						}
						
						BigDecimal consume = (BigDecimal) consumeMap.get(uid+"");
						if(consume==null){
							consume =  BigDecimal.ZERO;
						}
						BigDecimal LedgerCashAmount = BigDecimal.ZERO;
						BigDecimal LedgerCoinAmount = BigDecimal.ZERO;
						List<LiveDividendsLedgerDetailRecord> recordList = new ArrayList<>();
						for(LivePrivilege order: orderList){
							/*
							 * 只限VIP会员，VIP会员的充值金额<直属下线会员累计充值金额时，获得红包金额减半，VIP会员的充值金额>直属下线会员累计充值金额时，获得全额红包金额.
							 * 例如充值10000，原规则可以获得红包返还10000， 新规则 VIP会员的充值金额<直属下线会员累计充值金额时 获得红包总额=5000 ，
							 * VIP会员的充值金额>直属下线会员累计充值金额时，获得红包总额=10000
							 */
							BigDecimal halfLedgerOrder = liveLedgerService.getHalfRedPacketRatio(order,liverMap.get(uid+"").getJuniorLimitRatio());
							
							//内购还是外购
							int dividendsRole = liverMap.get(uid+"").getDividendsRole()==null?1:liverMap.get(uid+"").getDividendsRole();	
							//订单红包现金百分比
							int cashRatio=0;
							//订单红包鸟币百分比
							int coinRatio=0;
							
							//获取各订单所对应的等级信息
							LiveFansRank rank = liveLedgerService.getOrderApplyLiveFansRank(order.getLedgerLevel(), order.getCreateTime(),order.getObjectOriented());
							if(rank==null){
								log.info("该用户"+uid+"没有对应的等级,-将不产生红包奖励");
								continue;
							}
							LiveFansRankDetail rankDetail = rank.getLiveFansRankDetail();
							if(rankDetail==null){
								log.info("该用户"+uid+"没有对应的等级详情，其当前等级:"+rank.getId()+"-"+rank.getRankName()+"-将不产生红包奖励");
								continue;
							}
							//获取订单适用的红包奖励配置
							RankRedPacketDetail rankRedPacketDetail =liveLedgerService.getApplyRankRedPacketDetail(rankDetail.getId(), dividendsRole, consume);
							if(rankRedPacketDetail==null){
								log.info("该用户"+uid+"打赏鸟豆数"+consume+"没有对应的红包奖励区间——没有配置该区间的红包奖励比例");
								continue;
							}
							rankDetail.setRankRedPacketDetail(rankRedPacketDetail);
							
							if(dividendsRole==1){
								cashRatio = rankDetail.getPrivateRedPacketCashRatio();
								coinRatio = rankDetail.getPrivateRedPacketCoinRatio();
							}else{
								cashRatio = rankDetail.getPublicRedPacketCashRatio();
								coinRatio = rankDetail.getPublicRedPacketCoinRatio();
							}
							//该订单此刻可参加分红金额
							BigDecimal orderAmount = order.getPayment().multiply(halfLedgerOrder);
							//订单总可返现金金额
							BigDecimal orderCash = liveLedgerService.mulNumberTwoPoint(orderAmount, liveLedgerService.percentage2BigDecimal(cashRatio));
							
							//订单总可返鸟币金额
							BigDecimal orderCoin = liveLedgerService.mulNumberTwoPoint(orderAmount, liveLedgerService.percentage2BigDecimal(coinRatio));
							
							
							//随机红包
							BigDecimal cash = new BigDecimal(new Double(this.getRandomLedgerAmount(orderCash.doubleValue(), rankRedPacketDetail.getCashLowest().doubleValue(), rankRedPacketDetail.getCashHighest().doubleValue())).toString());
							BigDecimal coin =new BigDecimal(new Double(this.getRandomLedgerAmount(orderCoin.doubleValue(), rankRedPacketDetail.getCoinLowest().doubleValue(), rankRedPacketDetail.getCoinHighest().doubleValue())).toString());
							
							
							BigDecimal unLedgerTemp = BigDecimal.ZERO;	//解决当调整红包现金鸟币分配比例时，用户已领红包超出调整后可领现金上额时，导致实际用户可领红包现金+鸟币>payment的情况
							BigDecimal unLedgerCash = orderCash.subtract(order.getCurrentDividendLedger());
							if(unLedgerCash.compareTo(BigDecimal.ZERO)<0){
								unLedgerTemp = unLedgerCash.abs();
								unLedgerCash=BigDecimal.ZERO;
							}
							if(cash.compareTo(unLedgerCash)>0){
								cash = unLedgerCash;
							}
							
							BigDecimal unLedgerCoin = orderCoin.subtract(order.getCurrentDividendCoinLedger().add(unLedgerTemp));
							if(unLedgerCoin.compareTo(BigDecimal.ZERO)<0){unLedgerCoin=BigDecimal.ZERO;}
							if(coin.compareTo(unLedgerCoin)>0){
								coin = unLedgerCoin;
							}
							
							LedgerCashAmount = LedgerCashAmount.add(cash);
							LedgerCoinAmount = LedgerCoinAmount.add(coin);
							if(cash.add(coin).compareTo(BigDecimal.ZERO)>0){
								int objectOriented = order.getObjectOriented();
								BigDecimal common = BigDecimal.ZERO;
								BigDecimal seller = BigDecimal.ZERO;
								if(objectOriented==2){
									seller=coin;
								}else{
									common=coin;
								}
								recordList.add(new LiveDividendsLedgerDetailRecord(null, cash, coin,common,seller, order.getOrderNo(),uid,order.getObjectOriented()));
							}
							log.info("会员"+uid+"天降壕礼构成,订单【"+order.getOrderNo()
									+"】,现金部分："+cash+"【随机范围："+rankRedPacketDetail.getCashLowest()+"%~"+rankRedPacketDetail.getCashHighest()
									+"】,鸟币部分："+coin+"【随机范围："+rankRedPacketDetail.getCoinLowest()+"%~"+ rankRedPacketDetail.getCoinHighest()
									+"】,红包基数："+order.getPayment() +"*"+halfLedgerOrder+",各自占比："+cashRatio+"+"+coinRatio);
						}
						
						log.info("本次会员"+uid+"天降壕礼,现金部分："+LedgerCashAmount+",鸟币部分："+LedgerCoinAmount+",共消费:"+consume);
						if((LedgerCashAmount.add(LedgerCoinAmount)).compareTo(BigDecimal.ZERO)>0){
							LiveLedgerRecord liveLedgerRecord = liveLedgerService.insertLedgerRecordByDailyDividends(uid,LedgerCashAmount,LedgerCoinAmount,date, consume+"");
							liveDividendsLedgerDetailRecordDao.insertList(recordList,liveLedgerRecord.getId(),new Date());
						}
					}catch(Exception e){
						log.error("给会员"+uid+"派发每日红包异常",e);
					}
				}
			}while(ursList.size()==pageSize);
			consumeList = null;
			log.info("本次平台每日分红执行完毕");
		}catch(Exception e){
			log.error("平台每日红包分红异常",e);
		}finally{
		}
	}

	
	/**
	 * 
	 * 方法描述：随机返利金额
	 * 创建人： ChenBo
	 * 创建时间：2016年12月22日
	 * @param baseAmount
	 * @param minRatio
	 * @param maxRatio
	 * @return double
	 */
	private double getRandomLedgerAmount(double baseAmount,double minRatio,double maxRatio){
		double ratio = PreciseComputeUtil.div(PreciseComputeUtil.add(PreciseComputeUtil.mul(Math.random(), PreciseComputeUtil.sub(maxRatio,minRatio)),minRatio),100);
		return PreciseComputeUtil.mul(baseAmount,ratio,2);
	}
		
	private boolean isTest(){
		if(liveDividendsTest==null){
			return false;
		}
		return liveDividendsTest.equalsIgnoreCase("TRUE");
	}
	private TestUser getTestPhone(){
		if(isTest() && testRedpackagePhone!=null){
			Iterator<String> testIt = testRedpackagePhone.iterator();
			while(testIt.hasNext()){
				String phone=testIt.next();
				if(StringUtils.isBlank(phone)){
					log.info("为空，删除");
					testIt.remove();
				}else if(phone.trim().equalsIgnoreCase("ALLUSER")){
					return new TestUser(true, null);
				}
			}
			List<String> phones = new ArrayList<>();
			for(int i=0;i<testRedpackagePhone.size();i++){
				phones.add(testRedpackagePhone.get(i).trim());
			}
			return new TestUser(false,phones);
		}else{
			return new TestUser(true, null);
		}
	}
	private boolean hasUnreceiveDailyRedpacket(Integer uid){

		LiveLedgerRecord record = new LiveLedgerRecord();
		record.setUid(uid);
		record.setRedpacketRocordDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		record.setStatus(0);
		List<LiveLedgerRecord> list = liveLedgerRecordDao.getLiveLedgerRecordList(record);
		return list.size()==0?false:true;
	}
}

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TestUser
 * 
 * 类描述： 领取测试红包用户
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月31日 下午2:25:21 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
class TestUser{
	private boolean isAllUser;
	private List<String> testPhoneList;

	public boolean getIsAllUser() {
		return isAllUser;
	}
	public void setAllUser(boolean isAllUser) {
		this.isAllUser = isAllUser;
	}
	public List<String> getTestPhoneList() {
		return testPhoneList;
	}
	public void setTestPhoneList(List<String> testPhoneList) {
		this.testPhoneList = testPhoneList;
	}
	public TestUser() {
		super();
	}
	public TestUser(boolean isAllUser, List<String> testPhoneList) {
		super();
		this.isAllUser = isAllUser;
		this.testPhoneList = testPhoneList;
	}
	
	
}