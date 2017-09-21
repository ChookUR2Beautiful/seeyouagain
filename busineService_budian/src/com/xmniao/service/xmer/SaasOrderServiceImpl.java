package com.xmniao.service.xmer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.DateUtil;
import com.xmniao.common.ResponseState;
import com.xmniao.dao.coupon.CouponDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.dao.xmer.SaasOrderDao;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.thrift.busine.xmer.SaasOrderService;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.pay.LiveWalletService;
import com.xmniao.thrift.pay.ResponseData;
import com.xmniao.urs.dao.UrsDao;
import com.xmniao.urs.dao.XmerDao;
import com.xmniao.util.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：SaasOrderServiceImpl
 * 
 * 类描述： SaaS订单服务接口实现类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年5月27日 下午4:10:54
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service("saasOrderServiceImpl")
public class SaasOrderServiceImpl implements SaasOrderService.Iface {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(SaasOrderServiceImpl.class);

	@Autowired
	private SaasOrderDao saasOrderDao;

	@Autowired
	private DefaultMQProducer producerConnection;

	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private XmerDao xmerDao;

	@Autowired
	private UrsDao ursDao;

	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private XmerService xmerService;
	/**
	 * 注入发送消息主题
	 */
	@Autowired
	private String saasOrderTopic;

	/**
	 * 注入发送消息标签
	 */
	@Autowired
	private String saasOrderTags;

	@Autowired
	private CommonServiceImpl commonServiceImpl;

	/**
	 * 注入SAAS分账主题
	 */
	@Autowired
	private String saasLedgerTopic;

	/**
	 * 注入SAAS分账标签
	 */
	@Autowired
	private String saasLedgerTags;
	
	@Resource(name="smsqueue")
	private String smsQueue;
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
	/**
	 * 立即到账部分金额
	 */
	private BigDecimal preLedgerMoney= new BigDecimal("36.00");
	
	@Resource(name="waterCoupon")
	private Map<String,String> waterCoupon;
	
	private String PAY_HANDSEL="10000000";
	
	 /**
     * 注入分账系统支付服务的IP地址
     */
	@Resource(name="transLedgerIP")
    private String transLedgerIP;
    
    /**
     * 注入分账系统支付服务的端口号
     */
	@Resource(name="transLedgerPort")
    private int transLedgerPort;
	
	
	/**
	 * 更新寻蜜客Saas套餐订单接口
	 */
	@Override
	@Transactional
	public Map<String, String> modifyXmerOrderInfo(Map<String, String> paraMap)
			throws FailureException, TException {
		/*
		 * 1.更新业务库订单状态 2.通知用户端APP服务
		 */
		log.info("modifyXmerOrderInfo start::" + paraMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("bid", paraMap.get("bid"));
		if (!validateData(paraMap)) {
			log.error("传入参数不完整");
			throw new FailureException(ResponseState.PARAM_ERROR, "传入参数不完整");
		}
		
		// 更新该订单之前,先获取该条订单是否存在
		Map<String, Object> resOrderMap = saasOrderDao.getSaasOrderById(paraMap.get("bid"));
		// 若有该订单信息,则再进行下步操作
		if (null == resOrderMap) {
			log.error("没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
			throw new FailureException(ResponseState.ORDERNULL, "没有获取到订单号为:"
					+ paraMap.get("bid") + "订单信息");
		}

		// 订单状态
		int status = Integer.valueOf(resOrderMap.get("status").toString());
		if (status >= 1) {
			log.error("该订单【" + paraMap.get("bid") + "】已重复支付");
			return resultMap;
		}
		
		//验单
		if(!verifyOrder(paraMap, resOrderMap)){
			log.error("订单金额验证不通过, 订单编号:【" + paraMap.get("bid") + "】");
			throw new FailureException(ResponseState.ORDERNULL, "订单金额验证不通过:"
					+ paraMap.get("bid") + "订单信息");
		}
		
		String rtype = paraMap.get("rtype").toString();
		resOrderMap.put("rtype", rtype);  //设置寻密客类型
		

		//验单
//		if(!verifyOrder(paraMap, resOrderMap)){
//			log.error("订单金额验证不通过, 订单编号:【" + paraMap.get("bid") + "】");
//			throw new FailureException(ResponseState.ORDERNULL, "订单金额验证不通过:"
//					+ paraMap.get("bid") + "订单信息");
//		}

		try {
			//1-个人寻密客 2-V客寻蜜客 3-中脉寻密客 4-V客赠送SAAS给主播 
			
			if ("3".equals(rtype)) {  //脉客购买SAAS套餐
				this.saveMaibaoLedgerNotify(resOrderMap);  //须创建云脉网络分账通知信息
			}else if ("4".equals(rtype)) {  //V客赠送SAAS给主播情况(扣减库存信息)
				Integer giveNumber = Integer.parseInt(paraMap.get("giveNumber")) ; //赠送SAAS数量
				updateSassStock(resOrderMap, giveNumber);
			}
			
			Map<String, String> uMap = new HashMap<String, String>();
			uMap.put("ordersn", paraMap.get("bid"));// 订单编号
			uMap.put("status", paraMap.get("status"));// 订单状态
			uMap.put("payType", paraMap.get("payType"));// 支付方式
			uMap.put("payId", paraMap.get("payId"));// 支付流水
			uMap.put("payCode", paraMap.get("payCode"));// 第三方交易号
			uMap.put("zdate", DateUtil.getCurrentTimeStr());// 支付时间
			saasOrderDao.updateSaasOrder(uMap);

			// 支付成功，则创建寻蜜客
			if ("1".equals(paraMap.get("status"))) {
				//创建寻蜜客信息
				createXmerInfo(resOrderMap);
				
				if ("1".equals(rtype)) {  //1-个人寻密客 2-V客寻蜜客 3-中脉寻密客
					ledgerPrenterXmer(resOrderMap);
				}
				/* 
				 * 取消优惠券赠送 0605版本(废弃)
				 * try{
					//派送饮用水优惠券
					if(isNeedSendWaterCoupon(resOrderMap)){
						Map<String,Object> couponMap = new HashMap<String,Object>();
						couponMap.put("denomination", resOrderMap.get("amount"));
						couponMap.put("ordersn", resOrderMap.get("ordersn"));
						couponMap.put("ctype", 1);
						couponMap.put("uid", resOrderMap.get("uid"));
						couponMap.put("nums", resOrderMap.get("nums"));
						sendWalletCoupon(couponMap);
					}
				}catch (Exception e){
					log.error("派送饮用水优惠券异常",e);
				}*/
			}
		} catch (Exception ex) {
			log.error("更新订单接口服务异常", ex);
			throw new FailureException(ResponseState.ORDERFAIL, "更新订单接口服务异常");
		}
		try {
			// 押金订单，通知用户APP端服务器
			sendLedgerMQ(paraMap.get("bid"), 1);
		} catch (Exception ex) {
			log.error("发送MQ信息异常", ex);
			throw new FailureException(ResponseState.ORDERFAIL, "发送MQ信息异常");
		}

		log.info("modifyXmerOrderInfo end......");
		return resultMap;
	}
	
	
	 /**
	 * 方法描述：验证订单金额<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月9日下午7:00:22 <br/>
	 * @param paraMap
	 * @param orderMap
	 * @return
	 */
	private boolean verifyOrder(Map<String,String> paraMap, Map<String,Object> orderMap){
		Boolean bool = true;
		
		if ("1".equals(paraMap.get("rtype"))/* || "3".equals(paraMap.get("rtype"))*/) {  //个人寻密客购买 3-中脉寻密客
			BigDecimal totalAmout = new BigDecimal(paraMap.get("cash"));//订单金额
			
			BigDecimal amount = new BigDecimal(orderMap.get("amount").toString()) ;//鸟币总额
			BigDecimal agio = new BigDecimal(orderMap.get("agio") == null ? "1" : orderMap.get("agio").toString());//套餐折扣
			BigDecimal baseAmount = amount.multiply(agio).setScale(2,BigDecimal.ROUND_HALF_UP);//数据库订单金额
			
			if(totalAmout.compareTo(baseAmount)!=0){
				bool = false;
			}
		}
		
		return bool;
	}
	

	/**
	 * 更新商家Saas订单接口
	 */
	@Transactional(rollbackFor={FailureException.class,Exception.class})
	@Override
	public Map<String, String> modifySellerOrderInfo(Map<String, String> paraMap)
			throws FailureException, TException {
		
		/*
		 * 1.更新业务库订单状态 2.更新商家签约状态
		 */

		log.info("modifySellerOrderInfo start::" + paraMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("bid", paraMap.get("bid"));
		if (paraMap.get("bid") == null || paraMap.get("status") == null
				|| paraMap.get("payType") == null
				|| paraMap.get("payId") == null
				|| paraMap.get("payCode") == null) {
			log.error("传入参数不完整");
			throw new FailureException(ResponseState.PARAM_ERROR, "传入参数不完整");
		}

		// 更新该订单之前,先获取该条订单是否存在
		Map<String, Object> resOrderMap = saasOrderDao
				.getSaasSoldOrderById(paraMap.get("bid"));

		// 若有该订单信息,则再进行下步操作
		if (null == resOrderMap) {
			log.error("没有获取到订单号为:【" + paraMap.get("bid") + "】订单信息");
			throw new FailureException(ResponseState.ORDERNULL, "没有获取到订单号为:"
					+ paraMap.get("bid") + "订单信息");
		}

		// 订单状态
		int status = Integer.valueOf(resOrderMap.get("status").toString());
		if (status >= 1) {
			log.error("该订单【" + paraMap.get("bid") + "】已重复支付");
			resultMap.put("state", "2");
			resultMap.put("info", "重复支付");
			return resultMap;
		}

		try {
			//赠送
			if(paraMap.get("payType").equals(PAY_HANDSEL)){
				subXerSaasStockNum();
			}
			Map<String, String> uMap = new HashMap<>();
			uMap.put("ordersn", paraMap.get("bid"));// 订单编号
			uMap.put("status", paraMap.get("status"));// 订单状态
			uMap.put("payType", paraMap.get("payType"));// 支付方式
			uMap.put("payId", paraMap.get("payId"));// 支付流水
			uMap.put("payCode", paraMap.get("payCode"));// 第三方交易号
			uMap.put("zdate", DateUtil.getCurrentTimeStr());// 支付时间
			uMap.put("udate", DateUtil.getCurrentTimeStr());// 支付时间
			uMap.put("hstatus", "0");// 支付时间
			saasOrderDao.updateSaasSoldOrder(uMap);
		
			// 签约订单支付成功，更新商家状态为审核中 2016-6-20 由用户端更新
			// 签约订单支付成功，当商家有上传商家图片，则更新商家状态为审核中 2016-7-5
			log.info("判定该商家【" + resOrderMap.get("sellerid") + "】是否需要提交为审核中");
			int picCount = sellerDao.getSellerPicCount((Integer) resOrderMap
					.get("sellerid"));
			SellerBean seller = sellerDao
					.getSellerInfo((Integer) resOrderMap.get("sellerid"));
			if (picCount > 0) {
				log.info("该商家【" + resOrderMap.get("sellerid") + "】已上传了环境图");
				if (paraMap.get("status").equals("1")
						&& seller.getStatus() == Constant.SELLER_NOT_SIGN_STATUS) {
					log.info("该商家【" + resOrderMap.get("sellerid")
							+ "】已上传环境图，且签约状态为未签约");
					seller.setStatus(Constant.SELLER_AUDIT_STATUS);
					seller.setSigndate(new Date());
					seller.setUdate(new Date());
					sellerDao.modifySellerInfo(seller);

				}
			}
			
			Integer saasSource = (Integer) resOrderMap.get("saas_source");	//SAAS来源 0 正常库存 1 销售退回库存
			Integer saasChannel = (Integer) resOrderMap.get("saas_channel");	//签约的SAAS获取渠道 1常规 2脉客 3 V客
			Integer i=updateXmrStock((Integer)resOrderMap.get("uid"), saasChannel,saasSource,(String)resOrderMap.get("saas_ordersn"));
			if(i<=0){
				throw new RuntimeException("扣除寻蜜客saas库存失败    order:"+resultMap);
			}
			if(saasChannel==1){
				//寻蜜客等级升级
				xmerService.updateLevelByUid((Integer)resOrderMap.get("uid"));
			}
			sellerDao.updateStatusBySellerId(seller.getSellerid(),1);
		} catch (Exception ex) {
			log.error("更新订单接口服务异常", ex);
			throw new FailureException(ResponseState.ORDERFAIL, "更新订单接口服务异常");
		}

		log.info("modifySellerOrderInfo end......");
		resultMap.put("state", "1");
		resultMap.put("info", "支付成功");
		return resultMap;

	}

	/**
	 * 
	 * 方法描述：扣除库存
	 * 创建人：jianming  
	 * 创建时间：2017年5月10日 上午11:01:58   
	 * @param uid
	 * @param saasChannel
	 * @param saasSource 
	 * @param saasOrdersn 
	 * @return SAAS来源 0 正常库存 1 销售退回库存
	 */
	public Integer updateXmrStock(Integer uid, Integer saasChannel, Integer saasSource, String saasOrdersn) {
		Map<String, Object> saasOrder = saasOrderDao.getSaasOrderByUidType(uid,saasChannel,saasOrdersn);	//根据寻蜜客类型获取寻蜜客订单
		if(saasOrder==null){
			throw new RuntimeException("寻蜜客订单订单不存在");
		}
		switch (saasSource) {
		case 0:
			Integer stock=(Integer)saasOrder.get("stock");	//剩余套餐库存数量
			if(stock<=0){
				throw new RuntimeException("寻蜜客saas库存不足    uid:"+uid +"");
			}
			saasOrder.put("stock",--stock);
			break;
		case 1:
			Integer returnnums=(Integer)saasOrder.get("returnnums");  //退还数量
			if(returnnums<=0){
				throw new RuntimeException("寻蜜客saas库存不足    uid:"+uid +"");
			}
			saasOrder.put("returnnums",--returnnums);
			break;
		default:
			throw new RuntimeException("saas订单库存类型有误");
		}
		saasOrder.put("soldnums", (Integer)saasOrder.get("soldnums")+1);
		int i = saasOrderDao.updateStock(saasOrder);	//修改库存
		Long stock=saasOrderDao.sumStock(uid,saasChannel);
		int j=xmerDao.updateXmerSoldNums(uid,stock.intValue());
		return i>0&&j>0?1:0;
	}

	/**
	 * 
	 * @param bid
	 *            订单编号
	 * @param type
	 *            1.寻蜜客订单,2.商家订单
	 * @throws InterruptedException
	 * @throws MQBrokerException
	 * @throws RemotingException
	 * @throws MQClientException
	 */
	private void sendLedgerMQ(String bid, int type) throws MQClientException,
			RemotingException, MQBrokerException, InterruptedException {
		Map<String, Object> orderMap = getSaasSoldOrder(bid, type);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("ordersn", orderMap.get("ordersn"));
		message.put("amount", orderMap.get("amount"));
		message.put("uid", orderMap.get("uid"));
		message.put("description", "");
		message.put("zdate", orderMap.get("zdate"));
		String jsonMsg = JSON.toJSONString(message);

		Message msg = new Message();
		// msg.setTopic("topic_payorder");
		// msg.setTags("deposit_pay");
		msg.setTopic(saasOrderTopic);
		msg.setTags(saasOrderTags);
		msg.setBody(jsonMsg.getBytes());
		msg.setKeys(bid);
		log.info("发送MQ:\r\nTopic:" + msg.getTopic() + ",Tags:" + msg.getTags()
				+ ",keys:" + msg.getKeys() + ",body:" + jsonMsg);
		SendResult sendResult = producerConnection.send(msg);
		log.info("发送结果:" + sendResult.getSendStatus());
		if (log.isDebugEnabled()) {
			log.debug("msgId:" + sendResult.getMsgId() + ",status:"
					+ sendResult.getSendStatus());
		}

	}

	private Map<String, Object> getSaasSoldOrder(String bid, int type) {
		if (type == 1) {
			return saasOrderDao.getSaasOrderById(bid);
		} else {
			return saasOrderDao.getSaasSoldOrderById(bid);
		}
	}

	private boolean validateData(Map<String, String> paraMap) {
		String rtype = paraMap.get("rtype");
		if ("1".equals(rtype) || "3".equals(rtype)) { //个人寻密客、 3-中脉寻密客
			if (paraMap.get("bid") == null || paraMap.get("status") == null
					|| paraMap.get("payType") == null
					|| paraMap.get("payId") == null
					|| paraMap.get("payCode") == null) {
				return false;
			}
		}else if ("2".equals(rtype)) { //V客兑换寻密客
			if (paraMap.get("bid") == null) {
				return false;
			}
		}else if ("4".equals(rtype)) { //V客赠送SAAS主播
			if (paraMap.get("bid") == null 
					|| paraMap.get("giveNumber") == null) {
				return false;
			}			
		}else{
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 * @Title: ledgerPrenterXmer 
	 * @throws InterruptedException
	 * @throws MQBrokerException 
	 * @throws RemotingException 
	 * @throws MQClientException 
	 * @Description:
	 */
	private void ledgerPrenterXmer(Map<String,Object> orderMap) throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
		Integer uid = (Integer)orderMap.get("uid");
		if(uid == null || uid==0){
			return ;
		}
		BigDecimal nums = new BigDecimal((Integer)orderMap.get("nums"));
		Map<String, Object> message = new HashMap<String, Object>();
		/*Map<String,Object> xmerMap =xmerDao.getXmerIssues(uid);
		if(xmerMap!=null && xmerMap.get("oneLevelXmerId")!=null){
			message.put("oneLevelXmerId", xmerMap.get("oneLevelXmerId"));
			message.put("oneLevelXmerMoney", preLedgerMoney.multiply(nums));
		}
		if(xmerMap!=null && xmerMap.get("twoLevelXmerId")!=null){
			message.put("twoLevelXmerId", xmerMap.get("twoLevelXmerId"));
			message.put("twoLevelXmerMoney", preLedgerMoney.multiply(nums));
		}*/
		
		//0605已迁移关系链
		String uidRelationChain  = (String) orderMap.get("uidRelationChain");
		if (uidRelationChain != null && !"".equals(uidRelationChain) ) {
			String strArr[] = uidRelationChain.split("\\,");  //拆分成数组
			for (int i = 0; i < strArr.length; i++) {
				if (strArr.length >= 2 && i == strArr.length - 2) {
					message.put("oneLevelXmerId", strArr[i]);
					message.put("oneLevelXmerMoney", preLedgerMoney.multiply(nums));
				}
				if (i == strArr.length - 1) {
					message.put("twoLevelXmerId", strArr[i]);
					message.put("twoLevelXmerMoney", preLedgerMoney.multiply(nums));
				}
			}
		}
		
		message.put("orderId", orderMap.get("ordersn"));
		message.put("zdate", orderMap.get("zdate"));
		message.put("xmerId", orderMap.get("uid"));
		String jsonMsg = JSON.toJSONString(message);

		Message msg = new Message();
		msg.setTopic(saasLedgerTopic);
		msg.setTags(saasLedgerTags);
		msg.setBody(jsonMsg.getBytes());
		msg.setKeys(String.valueOf(orderMap.get("ordersn")));
		log.info("发送SAAS签约订单立即分账MQ:\r\nTopic:" + msg.getTopic() + ",Tags:" + msg.getTags()
				+ ",keys:" + msg.getKeys() + ",body:" + jsonMsg);
		SendResult sendResult = producerConnection.send(msg);
		log.info("发送结果:" + sendResult.getSendStatus());
		if (log.isDebugEnabled()) {
			log.debug("msgId:" + sendResult.getMsgId() + ",status:"
					+ sendResult.getSendStatus());
		}

	}
	private void createXmerInfo(Map<String,Object> resOrderMap) throws FailureException{
		String uid = resOrderMap.get("uid") + "";
		// 判断该用户是否已经是寻蜜客
		Map<String, Object> map1 = xmerDao.getXmerByUid(uid);
		if (map1 != null) {// 改用户已经是寻蜜客，返回
//			return ;
		} else {
			// 根据saas订单信息创建寻蜜客,并给他的上级和上上级伙伴数量加一
			boolean bool = xmerService.createXmerBySaasorder(resOrderMap);
			if (bool) {
				log.info("寻蜜客创建成功，且上级和上上级伙伴数量加一");
			} else {
				log.error("创建寻蜜客失败");
				throw new FailureException(ResponseState.ELSEEROR,
						"创建寻蜜客失败");
			}
		}

		// 生成寻密客关系链信息
		String rtype =  (String) resOrderMap.get("rtype");  //接口寻密客类型
		Integer objectOriented = 0;  //表中的寻密客类型
		switch (Integer.parseInt(rtype)) {
		case 1:
			objectOriented = 5;
			break;
		case 2:
			objectOriented = 7;
			break;
		case 3:
			objectOriented = 8;
			break;
		case 4:
			objectOriented = 6;
			break;
		}
		// 判断类型的寻密客关系链信息是否存在
		Map<String, Object> relationMap = xmerDao.getXmerUrsEarningsRelationByUid(Integer.parseInt(uid), objectOriented);
		if (relationMap == null) {// 改用户已经存在关系链，返回
			// 同一个uid有多种身份
			int icout = xmerService.createUrsEarningsRelation(resOrderMap);
			if (1 != icout) {
				log.error("创建寻蜜客会员收益关系链信息失败");
				throw new FailureException(ResponseState.ELSEEROR,
						"创建寻蜜客关系链信息失败");
			}
		}
		
		return ;
	}
	
	/**
	 * 成为寻蜜客，赠送等额指定优惠券
	 * @Title: sendOnLineMaillCoupon 
	 * @Description:
	 */
	private void sendWalletCoupon(Map<String,Object> orderMap){
		Date now = new Date();
		//获取用户手机号
		Map<String, Object> ursMap = ursDao.getUrsByUid(String.valueOf(orderMap.get("uid")));
		//根据当前金额，获取指定优惠券信息
		List<Map<String,Object>> couponList = null;
		Integer cid =getCouponId(String.valueOf(orderMap.get("nums")));
		if(cid == null){
			couponList = couponDao.getCouponList(orderMap);
		}else{
			Map<String,Object> cmap = new HashMap<String,Object>();
			cmap.put("cid", cid);
			couponList = couponDao.getCouponList(cmap);
		}
		if(couponList==null || couponList.size()>1){
			log.error("获取优惠券失败，赠送优惠券失败");
			return ;
		}
		Map<String,Object> couponMap = couponList.get(0);
		
		//拒绝重复赠送
		Map<String,Object> sel = new HashMap<String,Object>();
		sel.put("uid", orderMap.get("uid"));
		sel.put("ctype", "1");
		sel.put("cid", couponMap.get("cid"));
		List<Map<String,Object>> list = couponDao.getCouponDetailList(sel);
		if(list ==null || list.size()==0){
			//允许添加
		}else{
			log.info("该寻蜜客已获取过优惠券，将不再次获取");
			return;
		}
		
		Map<String,Object> issueMap = null;
		List<Map<String,Object>> issueList = couponDao.getCouponIssue((Integer)couponMap.get("cid"));
		if(issueList == null ||issueList.size()==0){
			//不下发短信了
			issueMap = new HashMap<String,Object>();
		}else{
			issueMap=issueList.get(0);
		}
		
		//2.给当前寻蜜客赠送等额积分超市的优惠券
		if(couponMap.get("dayNum")!=null){
			couponMap.put("startDate", DateUtil.format(now, "yyyy-MM-dd"));
			couponMap.put("endDate", DateUtil.format(DateUtil.calendarDay(now,(Integer)couponMap.get("dayNum")), "yyyy-MM-dd"));
		}
		Map<String,Object> detailMap = new HashMap<String,Object>();
		detailMap.put("cid", couponMap.get("cid"));
		detailMap.put("serial", generatorUUID());
		detailMap.put("denomination", couponMap.get("denomination"));
		detailMap.put("getWay", "3");
		detailMap.put("startDate", couponMap.get("startDate"));
		detailMap.put("endDate", couponMap.get("endDate"));
		detailMap.put("getTime", now);
		detailMap.put("uid", orderMap.get("uid"));
		detailMap.put("phone", ursMap.get("phone"));
		detailMap.put("getStatus", "1");
		detailMap.put("userStatus", "0");
		detailMap.put("issueId", issueMap.get("issueId"));
		detailMap.put("dateIssue", issueMap.get("dateCreated"));
		detailMap.put("sendStatus", "1");
		detailMap.put("ctype", "1");
		detailMap.put("orderNo", orderMap.get("ordersn"));
		couponDao.insertCouponDetail(detailMap);
		
		//3.给当前寻蜜客下发短信
		if( detailMap.get("phone") != null){
			Map<String,Object> smsMap = new HashMap<String,Object>();
			smsMap.put("phoneid", detailMap.get("phone"));
			smsMap.put("ordersn", "ordersn");
			smsMap.put("message", issueMap.get("message"));
			sendCouponSms(smsMap);
		}
	}
	
    /**
     * 发送短信处理
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private void sendCouponSms(Map<String,Object> smsMap)
    {
        try
        {
            //组装短信发送
            Map<String,String> sendSmsMap = new HashMap<String,String>();
            //手机号码
            sendSmsMap.put("phoneid", String.valueOf(smsMap.get("phoneid")));
            //订单编号
            sendSmsMap.put("bid", String.valueOf(smsMap.get("ordersn")));
                    
            sendSmsMap.put("smscontent", String.valueOf(smsMap.get("message")));
            
            
            String smsJson = JSONObject.toJSONString(sendSmsMap);
            smsJson=smsJson.replaceAll("&", "");
            smsJson=smsJson.replaceAll("%","");
            log.info("SendSms Redis Key:" + smsQueue + "==Send Sms JSON:" + smsJson);
            try
            {
                //将短信发送放到redis队列中去
                redisTemplate.opsForList().rightPush(smsQueue, smsJson);
            }
            catch (RedisConnectionFailureException e)
            {
                log.error("sendSms RedisConnection Failure:");
//                //若放到redis队列失败，则放入本地缓存Ecache中
//                if(StringUtils.isNotBlank(smsJson))
//                {
//                    Map<String,String> contentMap=new HashMap<String,String>();
//                    contentMap.put("markKey", smsQueue);
//                    contentMap.put("markContent", smsJson);
//                    contentMap.put("dateTime",String.valueOf(System.currentTimeMillis()));
//                    contentMap.put("addNumber","0");
//                    //失败数据放入Ecache缓存中
//                    exceptionHandle(contentMap);
//                }
            }
        }
        catch (Exception e)
        {
            log.error("发送短信出现异常",e);
        }
    }
    
    /*
     * 是否需要派送饮用水优惠券
     */
    private boolean isNeedSendWaterCoupon(Map<String,Object> resOrderMap){
		Map<String,Object> saasMap = new HashMap<String,Object>();
		saasMap.put("uid", resOrderMap.get("uid"));
		saasMap.put("ordersn", resOrderMap.get("ordersn"));
		long count = saasOrderDao.getCountSaasPackageOrder(saasMap);
    	return count==0;
    }
    
    private Integer getCouponId(String num){
    	 Set<String> couponSet = waterCoupon.keySet();
    	 Iterator iterator = couponSet.iterator();
    	 while (iterator.hasNext()) {  
    	   //遍历Set集合
    	   String setKey = (String) iterator.next();
    	   if(setKey.endsWith("_"+num)){
    		   return StringUtils.isNotBlank(waterCoupon.get(setKey))?Integer.valueOf(waterCoupon.get(setKey)):null;
    	   }
    	 }
    	 return null;
    }
    
	public static String  generatorUUID(){
		String uuid = UUID.randomUUID().toString(); 
		String[] uidArray=uuid.split("-");
		return uidArray[uidArray.length-1].concat(uidArray[uidArray.length-2]);
	}
	
	private void subXerSaasStockNum(){
	}
	
	
	/**
	 * 调用支付服务V客兑换SAAS套餐扣除鸟币
	 * @param resOrderMap
	 * @throws FailureException
	 */
	@SuppressWarnings("unused")
	private boolean deductBirdCoin(Map<String, Object> resOrderMap) throws FailureException {
		TTransport transport = null;
		Map<String, String> params = new HashMap<String, String>();
		String uid = resOrderMap.get("uid") + "";
		String bid = (String) resOrderMap.get("ordersn");
		
		try {
			// 调用分账服务的IP和端口号
			transport = new TSocket(transLedgerIP, transLedgerPort);
			TFramedTransport frame = new TFramedTransport(transport);
			// 设置传输协议为 TBinaryProtocol
			TProtocol protocol = new TBinaryProtocol(frame);
			// 分账服务的综合服务接口模块
			TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(
					protocol, "LiveWalletService");
            //打开端口,开始调用
            transport.open();
            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
    		
            params.put("uid", uid);  //用户uid
            params.put("deductZbalance", (String) resOrderMap.get("birdCoin"));  //例如:100.00 需要扣除的鸟币
            params.put("remarks", bid);  //订单编号
            log.info("需要同步鸟蛋："+"");
			ResponseData result = client.exchangeSaas(params);
			if (result.getState() == 0) {
				//V客兑换SAAS套餐扣除鸟币 成功
				log.info("V客兑换SAAS套餐扣除鸟币成功：订单编号["+bid+"]");
				return true;
			}else {
				log.error("调用支付服务V客兑换SAAS套餐扣除鸟币接口异常");
				return false;
			}

		} catch (TException e) {
			// 若调用抛出异常,则返回标识为-1
			log.error("调用支付服务V客兑换SAAS套餐扣除鸟币接口异常", e);
			return false;
		} catch (Exception e) {
			log.error("调用支付服务V客兑换SAAS套餐扣除鸟币接口异常", e);
			return false;
			/*throw new FailureException(ResponseState.ELSEEROR,
					"调用支付服务V客兑换SAAS套餐扣除鸟币接口异常, 异常信息:" + e.getMessage());*/
		} finally {
			// 关闭连接
			transport.close();
		}

	}
	
	
	 /**
	 * 方法描述：扣除V客Sass库存, V客赠送给主播的情况 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月11日下午5:23:25 <br/>
	 * @param resOrderMap
	 * @return
	 * @throws FailureException
	 */
	private boolean updateSassStock(Map<String, Object> resOrderMap, Integer giveNumber) throws Exception {
		try {
			int num = 0;
			Integer fromUid = (Integer) resOrderMap.get("fromUid");  //V客uid
			Integer stock = (Integer) resOrderMap.get("stock");  //V客赠送数量
			if (stock != giveNumber){
                throw new Exception("赠送的Saas套餐与订单表中的数量不一致");
			}
			if (fromUid == null){
                throw new Exception("赠送的Saas套餐中的uid信息不存在");
			}
					
			List<Map<String, Object>> saasOrderListMap = saasOrderDao.getSaasOrderByUid(fromUid);  //查询订单信息
			if (saasOrderListMap != null && stock > 0){
				for (int i = 0; i < saasOrderListMap.size(); i++) { //多笔v客兑换的订单 for(Map<String,Object> map:list){
					Map<String, Object> object = saasOrderListMap.get(i);
					Integer normalStock = (Integer) object.get("stock"); // 获取正常库存
					Integer returnnums = (Integer) object.get("returnnums"); // 获取退还库存
					
					String ordersn = (String) object.get("ordersn"); // 订单编号
					Integer version = (int) object.get("version"); // 版本信息

					Object soldnums = object.get("soldnums");
					Integer numbers =  soldnums == null ? 0 : (Integer)soldnums;

					Map<String, Object> params = new HashMap<String, Object>();
					if (normalStock > 0){
						if (stock >= normalStock){  //V客赠送数量大于当前订单的正常存库
	//						stock = stock - giveNumber; // 扣减后库存
							params.put("stock", 0);
							stock -= normalStock;  //扣减库存
							numbers += normalStock;  //卖出数量
						}else {//V客赠送数量小于当前订单的正常存库
							normalStock -= stock;
							params.put("stock", normalStock);
							numbers += stock; //卖出数量
							stock = 0;  //全部扣减
						}
					}
					
					if (returnnums > 0 && stock >0){
						if (stock >= returnnums){  //退还库存
							params.put("returnnums", 0);
							stock -= returnnums;  //扣减库存
							numbers += returnnums;	 //卖出数量
						}else{//V客赠送数量小于当前订单的退还的存库
							returnnums -= stock;  //减去扣减库存
							params.put("returnnums", returnnums);	
							numbers += stock;	//卖出数量
							stock = 0;  //扣减库存
						}
					}
					
					params.put("ordersn", ordersn); // 赠送订单编号
					params.put("soldnums", numbers); // 卖出数量
					params.put("version", version);	
					num += saasOrderDao.updateStock(params);
					
					if (stock == 0) {
						break; // 如果被扣完库存
					}
				}
			}
			
			if (num > 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error("V客赠送SAAS给主播库存更新异常", e);
			return false;
		}

	}
	
	 /**
	 * 方法描述：脉客购买SAAS时, 保存云脉网络分账通知 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年5月12日下午2:19:11 <br/>
	 * @param resOrderMap
	 * @return
	 * @throws Exception
	 */
	private boolean saveMaibaoLedgerNotify(Map<String, Object> resOrderMap) throws Exception {
		String ordersn = (String) resOrderMap.get("ordersn");  //购买订单编号
		if(true){
			return true;
		}
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orderNo", ordersn);  //本次支付订单编号
			params.put("orderType", 1); //订单类型 1 购买saas订单 2 美食订单
			params.put("ecno", resOrderMap.get("mbEcno"));  //脉客编号
			params.put("uid", resOrderMap.get("uid"));  //脉客对应uid
			params.put("payAmount", resOrderMap.get("amount"));	//订单金额
			params.put("payTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));	//支付时间	

			BigDecimal amout = (BigDecimal) resOrderMap.get("amount"); //订单金额
			BigDecimal ledgerAmount = amout.multiply(new BigDecimal(0.85)) ;
			params.put("ledgerAmount", ledgerAmount);	 //订单金额*(乘以)85%
			params.put("notifyState", 0);	 //通知状态 0 未通知 1已通知 2通知失败
			
			params.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));	
			params.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			int num = saasOrderDao.insertMaibaoLedgerNotify(params);
			if (num > 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error("保存云脉网络分账通知表异常", e);
			return false;
		}

	}
	
		   
	
}
