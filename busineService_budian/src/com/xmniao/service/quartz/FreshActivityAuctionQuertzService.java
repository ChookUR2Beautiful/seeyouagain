package com.xmniao.service.quartz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.HttpUtil;
import com.xmniao.common.PropertiesUtil;
import com.xmniao.common.Signature;
import com.xmniao.dao.order.BillActivityDao;
import com.xmniao.dao.order.FreshActivityAuctionDao;
import com.xmniao.dao.order.FreshActivityAuctionRecordDao;
import com.xmniao.domain.order.BillActivity;
import com.xmniao.domain.order.FreshActivityAuction;
import com.xmniao.domain.order.FreshActivityAuctionRecord;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.util.Constant;
import com.xmniao.util.PayIDGenerate;

public class FreshActivityAuctionQuertzService {
	
	@Autowired
	FreshActivityAuctionDao freshActivityAuctionDao;
	
	
	@Autowired
	FreshActivityAuctionRecordDao freshActivityAuctionRecordDao;
	
	
	@Autowired
	private BillActivityDao billActivityDao;
	
	@Autowired
	private FreshActivityQuertzService activityQuertzService;
	
	@Autowired
	private UrsDao ursDao;
	
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
	@Resource(name="smsqueue")
	private String smsQueue;
	
	@Resource(name="returnDepositServerUrl")
	private String returnDepositServerUrl;
	
	@Resource(name="freshKey")
	private String freshKey;
	
	//拍卖成功处理逻辑
	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(FreshActivityAuctionQuertzService.class);
	
	
	public void executeFreshActivityAuction() {
		log.info("开始执行竞拍的定时任务...");
		try {
			//把未开始的竞拍活动 根据开始时间改为竞拍进行中的状态
			doStartFreshActivityAuction(); 
			
			//处理进行中的竞拍活动记录
			doNormalFreshActivityAuction();
			
			//处理异常的竞拍数据
			doFaultFreshActivityAuction();

		} catch (Exception e) {
			log.error("执行积分超市竞拍成功任务定时任务出现异常",e);
//			throw new RuntimeException("执行竞拍成功任务定时任务出现异常");
		}
		
	}
	
	/**
	 * 把竞拍活动的状态改为开始
	 */
	public void doStartFreshActivityAuction() {
		List<FreshActivityAuction> freshActivityAuctionList = new ArrayList<FreshActivityAuction>();
		freshActivityAuctionList = freshActivityAuctionDao.selectStartActvityGroup();
		if (freshActivityAuctionList.size() > 0){
			for (FreshActivityAuction freshActivityAuction: freshActivityAuctionList){  //循环处理竞拍成功的订单记录
				//进行状态 ( 1:未开始 2:进行中 3:已结束 4:流拍)
			  freshActivityAuction.setProceedStatus(2);
			  freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
			}
		}
		
	}
	
	/**
	 * 处理在进行中的竞拍活动数据
	 * @throws Exception
	 */

	public void doNormalFreshActivityAuction() throws Exception{
		//处理竞拍活动竞拍进行中的状态的数据
		List<FreshActivityAuction> freshActivityAuctionList = new ArrayList<FreshActivityAuction>();
		freshActivityAuctionList = freshActivityAuctionDao.selectEndActvityGroup();
		if (freshActivityAuctionList.size() > 0){
			//数据状态state (状态 0:正常 1:终止 2:删除)
			//进行状态proceed_status ( 1:未开始 2:进行中 3:已结束 4:流拍)
			//产品状态product_status (0:竞拍中 1:竞拍成功(待支付尾款) 2:已退还库存(流拍) 3:已售出(已支付尾款) 4:已退还库存(超时未支付尾款))
			for (FreshActivityAuction freshActivityAuction: freshActivityAuctionList){  //循环处理竞拍成功的订单记录
				doFreshActivityAuctionState(freshActivityAuction);
			}
		}
		log.info("定时执行竞拍成功任务结束,共竞拍成功"+(freshActivityAuctionList==null ? 0 : freshActivityAuctionList.size())+"个活动");	
	}
	
	/**
	 * @param freshActivityAuction
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void doFreshActivityAuctionState(FreshActivityAuction freshActivityAuction) throws Exception{
		//判断是否竞拍成功
		Integer cout = freshActivityAuctionDao.getCount(freshActivityAuction.getId());  
		if ( cout == 1 && freshActivityAuction.getState() == 0){ //只有这种前况才算竞拍成功， 并生成订单信息 0:正常  1:终止  2:删除'
			freshActivityAuction.setProductStatus(1);
			freshActivityAuction.setProceedStatus(3);
			//处理竞拍参与人的状态和保证金信息
			doFreshActivityAuctionRecord(freshActivityAuction);
		}else{
			freshActivityAuction.setProceedStatus(4);
			freshActivityAuction.setProductStatus(2);
			//处理竞拍参与人的状态和保证金信息
			doFreshActivityAuctionRecordFault(freshActivityAuction);
		}
		//当前的竞拍活动改为终止
		freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
	}
	
	
	public void doFaultFreshActivityAuction() throws Exception{
		this.doHandEndFreshActivityAuction();
		
		//处理竞拍成功未支付尾款的竞拍活动记录(暂时定为72小时)
		this.doPayFaultFreshActivityAuction();
	}
	
	
	private void doHandEndFreshActivityAuction() throws Exception{
		//处理竞拍活动竞拍进行中的状态的数据
		List<FreshActivityAuction> freshActivityAuctionList = new ArrayList<FreshActivityAuction>();
		freshActivityAuctionList = freshActivityAuctionDao.selectHandEndActvityGroup();
		if (freshActivityAuctionList.size() > 0){
			//数据状态state (状态 0:正常 1:终止 2:删除)
			//进行状态proceed_status ( 1:未开始 2:进行中 3:已结束 4:流拍)
			//产品状态product_status (0:竞拍中 1:竞拍成功(待支付尾款) 2:已退还库存(流拍) 3:已售出(已支付尾款) 4:已退还库存(超时未支付尾款))
			for (FreshActivityAuction freshActivityAuction: freshActivityAuctionList){  //循环处理竞拍成功的订单记录
				//判断是否竞拍成功
			    freshActivityAuction.setProceedStatus(3);
				freshActivityAuction.setProductStatus(2);
				//当前的竞拍活动改为终止
				freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
				//处理竞拍参与人的状态和保证金信息
				doFreshActivityAuctionRecordFault(freshActivityAuction);
			}
		}
		log.info("定时执行人工结束竞拍任务,共结束"+(freshActivityAuctionList==null ? 0 : freshActivityAuctionList.size())+"个活动");	
	}
	
	
	/**
	 * 处理竞拍成功，未支付尾款的情况
	 */
	private void doPayFaultFreshActivityAuction() {
		List<FreshActivityAuction> freshActivityAuctionList = new ArrayList<FreshActivityAuction>();
		freshActivityAuctionList = freshActivityAuctionDao.selectPayFaultActvityGroup();
		if (freshActivityAuctionList.size() > 0) {
			for (FreshActivityAuction freshActivityAuction : freshActivityAuctionList) { // 循环处理竞拍成功的订单记录
				// 进行状态 ( 1:未开始 2:进行中 3:已结束 4:流拍)
				Date cdate = DateUtil.calendarHour(freshActivityAuction.getEndTime(), 72); // 竞拍结束时间24小时以上  313  314
				if (DateUtil.compare_date(new Date(), cdate) >= 0) { 
					// String tempdate1=DateUtil.dateFormatY1(cdate);
					//活动的状态更改为结束
					freshActivityAuction.setProductStatus(4);
					freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
					
					//更改竞拍成功人状态
					int fid = freshActivityAuction.getId();
					FreshActivityAuctionRecord freshActivityAuctionRecord = freshActivityAuctionRecordDao.getMaxPriceRecordByActivityId(fid);
					freshActivityAuctionRecord.setState(6);
					freshActivityAuctionRecordDao.updateByPrimaryKeySelective(freshActivityAuctionRecord);
					
				    //更改订单状态
					String finishOrder = freshActivityAuctionRecord.getFinishOrder();
					BillActivity billActivity = billActivityDao.selectByPrimaryKey(finishOrder);
					billActivity.setState(5);
					billActivity.setUpdateTime(new Date());
					billActivityDao.updateByPrimaryKey(billActivity);
					
					// 1.还原库存数据
					activityQuertzService.updateActivityProductAndGroup(1, freshActivityAuction.getCodeid(), freshActivityAuction.getPvIds());
				}
			}
		}

	}
	
	
	
	/**
	 * 处理竞拍成功的竞拍活动信息
	 * @param freshActivityAuction
	 * @throws Exception
	 */
	private void doFreshActivityAuctionRecord(FreshActivityAuction freshActivityAuction)  throws Exception{
		//1.更改拍卖表状态为竞拍成功
		if (freshActivityAuction != null) {
			//取出竞拍成功的人并更改状态
			Integer activityId = freshActivityAuction.getId();
			FreshActivityAuctionRecord maxPriceRecordInfo = freshActivityAuctionRecordDao.getMaxPriceRecordByActivityId(activityId);  //compareTo
			if (maxPriceRecordInfo != null){
				//增加订单信息, 并更改竞拍成功状态
				createBillDataInfo(freshActivityAuction, maxPriceRecordInfo);
				
				//更改其他竞拍用户的状态为竞拍失败
				Integer count = freshActivityAuctionRecordDao.updateAuctionRecordState(maxPriceRecordInfo.getId(), maxPriceRecordInfo.getActivityId(), 3);
				//出价最高的人改为竞拍成功
				maxPriceRecordInfo.setState(1);  //1:竞拍成功
				freshActivityAuctionRecordDao.updateByPrimaryKeySelective(maxPriceRecordInfo);
				//退还保证金
				List<FreshActivityAuctionRecord> dataRecordList = freshActivityAuctionRecordDao.getFaultRecordByActivityId(maxPriceRecordInfo.getId(), maxPriceRecordInfo.getActivityId());
				doReturnDeposit(dataRecordList);

				log.info("定时执行竞拍成功任务结束, 共竞拍成功"+ count +"个活动");
			}else {
				freshActivityAuction.setState(3);
				freshActivityAuctionDao.updateByPrimaryKeySelective(freshActivityAuction);
				
				log.error("执行积分超市竞拍成功任务定时任务出现异常");
				throw new Exception("活动编号["+activityId+"]:处理失败, 未找到竞拍出价人信息");
			}
	
		}
	}
	
	
	/**
	 * 创建订单信息
	 * @param activityAuction
	 * @param activityAuctionRecord
	 * @throws Exception
	 */
	private void createBillDataInfo(FreshActivityAuction activityAuction, FreshActivityAuctionRecord activityAuctionRecord)  throws Exception{
		Integer userId = activityAuctionRecord.getUid();
		Map<String, Object> usrMap = ursDao.getUrsByUid( String.valueOf(userId));
		String userName = "";
		if (usrMap != null) {
			userName = usrMap.get("nname").toString();
		}
		
		//根据uid获取用户信息
		BillActivity billActivity = new BillActivity();
		// 生成发货订单
		billActivity.setId(PayIDGenerate.createPayId());
		billActivity.setActivityType(Constant.INDIANA_AUCTION_BILL_TYPE);
		
		billActivity.setActivityId(activityAuction.getId().longValue());
		billActivity.setCreateTime(new Date());
		
		billActivity.setProductCodeId(activityAuction.getCodeid().toString());
		billActivity.setProductNum(1);
		billActivity.setState(Constant.BILL_ACTIVITY_STATE_TYPE);
		billActivity.setUpdateTime(new Date());
		
//		billActivity.setReceivingName(indianaBout.getName());
//		billActivity.setReceivingPhone(indianaBout.getPhone());
		billActivity.setUserId(activityAuctionRecord.getUid()!=null?activityAuctionRecord.getUid().toString():"");
		billActivity.setUserPhone(activityAuctionRecord.getPhone());
		billActivity.setUserName(userName);
		
		billActivity.setAmountReceived(new BigDecimal(0));
		billActivity.setProductBreviary(activityAuction.getBreviary());
		billActivity.setProductPrice(activityAuction.getPrice());
		
		String productName = activityAuction.getPname() == null ? "" :activityAuction.getPname();
		billActivity.setProductName(productName);
		
		billActivity.setProductPvIds(activityAuction.getPvIds());
		billActivity.setProductPvValue(activityAuction.getPvValue());
		
		BigDecimal risedPrice  = activityAuctionRecord.getRisedPrice();
		BigDecimal auctionDeposit = new BigDecimal(activityAuctionRecord.getDepositPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
		billActivity.setAmountReceived(auctionDeposit);  //已支付的金额
		billActivity.setAuctionDeposit(auctionDeposit);  //保证金
		billActivity.setAuctionBalance(risedPrice.subtract(auctionDeposit));  //尾款
		
		billActivity.setUserType(1);
		billActivityDao.insertSelective(billActivity);
		
		//生成的订单编号回写
		String finishOrder = billActivity.getId();
		activityAuctionRecord.setFinishOrder(finishOrder); //订单编号
		freshActivityAuctionRecordDao.updateByPrimaryKeySelective(activityAuctionRecord);
		
		StringBuffer msgContent = new StringBuffer();
		msgContent.append("恭喜您竞拍成功，获得["+productName+"]购买权。请尽快上线支付尾款哦！购买订单只保留72小时。");
		//发送短信
		Map<String, Object> smsMap = new HashMap<String, Object>();
		smsMap.put("phoneid", activityAuctionRecord.getPhone());
		smsMap.put("ordersn", "ordersn");
		smsMap.put("message", msgContent.toString());
		sendMessage(smsMap);
		
	}
	
	
	/**
     * 发送短信处理
     * @param resOrderMap [订单信息MAP]
     * @param phoneid [手机号码]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
	private void sendMessage(Map<String, Object> smsMap) throws Exception{
		try {
			// 组装短信发送
			Map<String, String> sendSmsMap = new HashMap<String, String>();
			// 手机号码
			sendSmsMap.put("phoneid", String.valueOf(smsMap.get("phoneid")));
			// 订单编号
			sendSmsMap.put("bid", String.valueOf(smsMap.get("ordersn")));

			sendSmsMap.put("smscontent", String.valueOf(smsMap.get("message")));
			String smsJson = JSONObject.toJSONString(sendSmsMap);
			smsJson = smsJson.replaceAll("&", "");
			smsJson = smsJson.replaceAll("%", "");
			log.info("SendSms Redis Key:" + smsQueue + "==Send Sms JSON:"
					+ smsJson);
			try {
				// 将短信发送放到redis队列中去
				redisTemplate.opsForList().rightPush(smsQueue, smsJson);
			} catch (RedisConnectionFailureException e) {
				log.error("sendSms RedisConnection Failure:");
			}
		} catch (Exception e) {
			log.error("发送短信出现异常", e);
		}
	}
	
	
	/**
	 * 处理竞拍不成功的数据
	 * @param freshActivityAuction
	 * @throws Exception
	 */
	private void doFreshActivityAuctionRecordFault(FreshActivityAuction freshActivityAuction) throws Exception {
		//1.还原库存数据
		activityQuertzService.updateActivityProductAndGroup(1, freshActivityAuction.getCodeid(), freshActivityAuction.getPvIds());
		// 退还保证金
		List<FreshActivityAuctionRecord> dataRecordList = freshActivityAuctionRecordDao.getFaultRecordByActivityId(null, freshActivityAuction.getId());
		if (dataRecordList.size() > 0) {
			doReturnDeposit(dataRecordList);
		}
		
		int recordStat = 0;  
		//2.退还拍卖活动的保证金,更改竞拍状态  2:竞拍失败,出价人次低于最低人次, 5活动终止
		if (freshActivityAuction.getState() == 1) 
			recordStat = 5;
		else 
			recordStat = 2;
		Integer count = freshActivityAuctionRecordDao.updateAuctionRecordState(null, freshActivityAuction.getId(), recordStat);
		log.info("竞拍失败，更新竞拍人状态成功 !共计" + count + "个活动");
	}
	
	
	/**
	 * 退还保证金
	 * @param dataList
	 * @throws Exception
	 */
	private void doReturnDeposit(List<FreshActivityAuctionRecord> dataList) throws Exception {
		if (dataList.size() > 0) {
			for (FreshActivityAuctionRecord bean : dataList) {
				Integer randomNumber = 100;
				randomNumber = (int)(Math.random()*400)+101;//200-500随机数
				Map<String, String> param = new HashMap<String, String>();
				param.put("orderNumber", (String) bean.getDepositOrder());
				param.put("amount", String.valueOf(new BigDecimal(bean.getDepositPrice()).setScale(2, BigDecimal.ROUND_HALF_UP)));
				param.put("randomNumber", String.valueOf(randomNumber));
				//调用支付服务接口
				String sign = Signature.sign(param, freshKey);
				param.put("sign", sign);
//				PropertiesUtil.readValue("http.user.url");
				HttpResponse post = HttpUtil.getInstance().post(returnDepositServerUrl, param);
				HttpEntity entity = post.getEntity();
				String returnInfo = EntityUtils.toString(entity);

				Map<String,Object> returnInfoObject = JSON.parseObject(returnInfo, HashMap.class);
//				HttpParams params = post.getParams();
				if("200".equals(returnInfoObject.get("state"))){
					//1：已交；2：已退还
					bean.setDepositState(2);
					freshActivityAuctionRecordDao.updateByPrimaryKeySelective(bean);
				}else{
					log.info("退还鸟币失败:调用退还鸟币接口状态为"+returnInfoObject.get("state")+"   信息:"+returnInfoObject.get("msg")+"    订单号:"+param.get("orderNumber"));
				}
			}
		}
	}
	
}
