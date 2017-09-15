package com.xmniao.xmn.core.payment.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.BillpaymentRequest;
import com.xmniao.xmn.core.common.request.order.PaymentOrderRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.order.dao.FreshOrderProcessDao;
import com.xmniao.xmn.core.seller.entity.ActivityFullreductionInfo;
import com.xmniao.xmn.core.seller.entity.LiveSellerLedgerInfo;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.thrift.XmnOrderParamV2;
import com.xmniao.xmn.core.thrift.business.java.OrderService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.service.SellerService;

@Service
public class BillPaymentService {

	//日志
	private final Logger log = Logger.getLogger(BillPaymentService.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	//注入sessionTokenService
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入mongoDB
	@Autowired
	private MongoBaseService mongoBaseService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private PersonalCenterDao personalCenterDao;
	
	@Autowired
	private PersonalCenterService personalCenterService;
	
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private FreshOrderProcessDao freshOrderProcessDao;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private XmerDao xmerDao;
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil; 
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	/**
	 * 描述：下单页计算订单各项金额
	 * @param billpaymentRequest
	 * @return Object
	 * */
	public Object goBillPayment(BillpaymentRequest billpaymentRequest){
		MapResponse response = null;
		//需要返回到前端的记录
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		//组装下订单时需要用的其他数据
		Map<Object, Object> otherMap = new HashMap<Object, Object>();
		
//		String uid = billpaymentRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(billpaymentRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		billpaymentRequest.setUid(Integer.parseInt(uid));
		
		try {
			//判断下单金额的最大值和最小值
			BigDecimal minAmount = new BigDecimal(propertiesUtil.getValue("minAmount", "conf_common.properties")).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal maxAmount = new BigDecimal(propertiesUtil.getValue("maxAmount", "conf_common.properties")).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//获取商家分账基本信息
			LiveSellerLedgerInfo  sellerLedger = anchorLiveRecordDao.queryLiveSellerLedgerBySellerId(billpaymentRequest.getSellerId().intValue());
			//获取商家基本信息
			Seller seller = sellerService.querySellerBySellerid(billpaymentRequest.getSellerId());
			if (seller!=null && null!=seller.getSellerid()) {
				//查询商家订单单日下单总额  限制消费
				Map<Object, Object> dayMap = billDao.queryDayOrderAmountBySellerId(billpaymentRequest.getSellerId());
				if (dayMap!=null) {
					if (dayMap.get("dayMoney")!=null && new BigDecimal(dayMap.get("dayMoney").toString()).compareTo(seller.getDailyLimitTurnover())>=0 ) {
						return new MapResponse(ResponseCode.FAILURE, "店铺休息中");
					}
				}
				
				//查询商家订单单日下单总额    限制消费
				Map<Object, Object> totalMap = billDao.queryTotalOrderAmountBySellerId(billpaymentRequest.getSellerId());
				if (dayMap!=null) {
					if (totalMap.get("totalMoney")!=null && new BigDecimal(totalMap.get("totalMoney").toString()).compareTo(seller.getTotalLimitTurnover())>=0 ) {
						return new MapResponse(ResponseCode.FAILURE, "店铺休息中");
					}
				}
				
				resultMap.put("sellername", seller.getSellername());
				resultMap.put("sellerid", seller.getSellerid());
				resultMap.put("zoneid", seller.getZoneid());
				resultMap.put("sellerAgio", seller.getAgio());
				resultMap.put("agio", 0);//没有下单立减了  字段要保留 兼容老版本
				otherMap.put("phoneid", seller.getPhoneid());
				otherMap.put("aid", seller.getAid());
				otherMap.put("fullname", seller.getSellername());
				otherMap.put("jointid", seller.getJointid());
				otherMap.put("xmerUid", seller.getXmerUid());
				otherMap.put("area", seller.getArea());
				
				//获取商家logo
				List<SellerPic> sellerPicList = sellerService.querySellerPicBySelleridAndType(seller.getSellerid(),1);
				if (sellerPicList.size()>0) {
					resultMap.put("logoPic", sellerPicList.get(0).getUrl());
				}else {
					resultMap.put("logoPic", "");
				}
			}else {
				log.info("获取商家信息失败:"+billpaymentRequest.getSellerId());
				return new MapResponse(ResponseCode.FAILURE, "商家不存在或已下线");
			}
			
			if (billpaymentRequest.getAmount().compareTo(new BigDecimal(0))>=0) {
				try {
					
					billpaymentRequest.setUid(Integer.parseInt(uid));
					//计算价格 
					Map<Object, Object> map = this.calculationPayAmount(billpaymentRequest,resultMap);
					map.put("sellerAgio", seller.getAgio());//返回商家折扣
					
					resultMap.put("money", billpaymentRequest.getAmount());
					resultMap.put("fullId", map.get("fullId"));
					resultMap.put("fullMinusAmount", map.get("fullMinusAmount"));
					resultMap.put("paymentSaleAmount", map.get("paymentSaleAmount"));
					resultMap.put("denomination", map.get("denomination"));
					resultMap.put("integral", map.get("integral"));
					resultMap.put("payAmount", map.get("payAmount"));
					resultMap.put("serial", map.get("serial"));
					resultMap.put("cuser", map.get("cuser"));
					resultMap.put("area", seller.getArea());
					resultMap.put("liveCoinPay", seller.getLiveCoinPay());
					
					//是否有粉丝券
					Map<Object, Object> fansMap = new HashMap<Object, Object>();
					fansMap.put("uid", uid);
					fansMap.put("useStatus", 0);
					fansMap.put("sellerId", billpaymentRequest.getSellerId());
//					fansMap.put("payAmount",  map.get("payAmount"));
					fansMap.put("payAmount",  billpaymentRequest.getAmount());//没有下单立减 改为使用原始金额去查询优惠券记录
					
					fansMap.put("page", 1);
					fansMap.put("limit", Constant.PAGE_LIMIT);
					List<CouponInfo> fansList = personalCenterDao.queryUserFansCouponList(fansMap);
					if (fansList.size()>0) {
						map.put("isCouponFans", 1);
						map.put("isLiveActivity", 1);
					}else {
						map.put("isCouponFans", 0);
						map.put("isLiveActivity", 0);
					}
					
					//是否有平台/商户优惠券
					
					fansMap.put("version", 0);
					//版本控制 3.5.9  使用优惠券的情况 
					String appversion = billpaymentRequest.getAppversion().replace(".", "");
					if (!StringUtils.isEmpty(appversion) || !"null".equalsIgnoreCase(appversion)) {
						int version = Integer.parseInt(appversion);
						if (version<=359) { //标示没有使用优惠券
							fansMap.put("version", 1);
						}
					}
					List<CouponInfo> couponList = personalCenterDao.queryUserCouponList(fansMap);
					if (couponList.size()>0) {
						map.put("isCoupon", 1);
//						map.put("isLiveActivity", 1);
					}else {
						map.put("isCoupon", 0);
//						map.put("isLiveActivity", 0);
					}
					map.put("minAmount", minAmount);//最小消费金额
					map.put("maxAmount", maxAmount);//最大消费金额
					map.put("originalAmount", billpaymentRequest.getAmount());//订单原始金额
					
					map.put("isAgio", propertiesUtil.getValue("isAgio", "conf_common.properties")) ;//订单是否有下单立减
					map.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));//订单是否有赠送积分
					map.put("ratioNumber", propertiesUtil.getValue("platformPaybirdCoin", "conf_common.properties"));//鸟币支付与金额转换系数
					map.put("birdCoinExpression", propertiesUtil.getValue("platformPaybirdCoinExpression", "conf_common.properties")
							.replace("SellerAgio", seller.getAgio().toString()).trim());//Android 计算鸟币支付金额公式
					map.put("moneyExpression", propertiesUtil.getValue("platformPayMoneyExpression", "conf_common.properties")
							.replace("SellerAgio", seller.getAgio().toString()).trim() );//Android计算金额需要支付的公式
					
//					//查询商家是否有直播活动可以使用优惠券 
//					int isLiveActivity = sellerService.nowLiveCount(seller.getSellerid(), 1);
//					if (isLiveActivity>0) {
//						map.put("isLiveActivity", 1);
//					}else {
//						map.put("isLiveActivity", 0);
//					}
					
					//发起支付后返回订单基础信息
					Map<Object, Object> payOrderMap = new HashMap<>();
					//计算订单返回订单计算基础信息
					Map<Object, Object> payInfoMap  = new HashMap<Object, Object>();
					payInfoMap.put("payAmount", resultMap.get("payAmount"));
					payInfoMap.put("sellerId", seller.getSellerid());
					payInfoMap.put("sellerName", seller.getSellername());
					payInfoMap.put("couponId", billpaymentRequest.getCouponId());
					payInfoMap.put("couponType", billpaymentRequest.getCouponType());
					payInfoMap.put("isChoosePay", 1);//是否去到选择支付方式页面  是
					payInfoMap.put("birdCoinPayMoney", map.get("birdCoinPayMoney"));//
					payInfoMap.put("liveCoinPay", seller.getLiveCoinPay());//商家是否开启鸟币支付
					
					//查询商铺是否开启储值卡
					payInfoMap.put("sellerCardMoney", 0);//设置会员在上家储值卡基础余额
					payInfoMap.put("isSellerCardPay", 0);//设置商家是否开启储值卡支付 0 否
					payInfoMap.put("birdCoinDesc", "");//有额度没鸟币
					//返回改用户的钱包 直播钱包基本信息
					Map<String, String> wallMap = new HashMap<>();
					Map<String, Object> liveWallMap = new HashMap<>();
					try {
						//获取寻蜜鸟会员钱包余额
						wallMap = liveGiftsInfoService.getXmnWalletBlance(uid);
						//钱包余额总金额
						BigDecimal amountMoney = new BigDecimal("0");
						if (wallMap!=null && wallMap.size()>0) {
							amountMoney = new BigDecimal(wallMap.get("zbalance")).add(new BigDecimal(wallMap.get("commision")));
							payInfoMap.put("balanceMoney", amountMoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							payOrderMap.put("balanceMoney", amountMoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							
							payInfoMap.put("userWalletZbalanceLock", wallMap.get("zbalanceLock"));
						}else {
							payInfoMap.put("balanceMoney", amountMoney.toString());
							payOrderMap.put("balanceMoney", amountMoney.toString());
						}
						
						//获取寻蜜鸟直播用户钱包
						liveWallMap = liveGiftsInfoService.getLiveWalletBlance(uid);
						if (liveWallMap!=null && liveWallMap.size()>0) {
							//鸟币余额  买单返回 余额 减去 限额后的金额
							BigDecimal originalBbalanceCoin = liveWallMap.get("zbalanceCoin")==null?new BigDecimal("0"):new BigDecimal(liveWallMap.get("zbalanceCoin").toString());
							payInfoMap.put("balanceCoin",originalBbalanceCoin.setScale(2, BigDecimal.ROUND_HALF_UP).toString() );
							payOrderMap.put("balanceCoin",originalBbalanceCoin.setScale(2, BigDecimal.ROUND_HALF_UP).toString() );
							
							payInfoMap.put("liveWalletZbalanceLock", liveWallMap.get("zbalanceLock"));
							
							BigDecimal balanceCoin = liveWallMap.get("zbalance")==null?new BigDecimal("0"):new BigDecimal(liveWallMap.get("zbalance").toString());
							//判断鸟币余额 是否小于限制金额  
							payInfoMap.put("restrictive",0);
							if (null!=seller.getLiveCoinPay()) {//首先判断商家是否开启了鸟币支付
								
								if (seller.getLiveCoinPay()==1) {//开启鸟币支付：如果开启了鸟币支付则 还需判断 用户是否被限制使用鸟币
									payInfoMap.put("restrictive",1);//设置为1开启
									payOrderMap.put("restrictive",1);//设置为1开启
									
									if (liveWallMap.get("limitBalance")!=null) {
										//如果限制金额大于0 标示开启了限额
										if ((new BigDecimal(liveWallMap.get("limitBalance").toString()).compareTo(new BigDecimal("0"))>0)) {
											BigDecimal limitBalance = new BigDecimal(liveWallMap.get("limitBalance").toString());
											//如果当前余额大于限额  并且  当前鸟币余额 减去订单金额 也大于限额 标示可以使用鸟币支付方式
											if (balanceCoin.compareTo(limitBalance)>0) {
												payInfoMap.put("restrictive",1);
												payOrderMap.put("restrictive",1);
											}else {
												payInfoMap.put("restrictive",0);
												payOrderMap.put("restrictive",0);
											}
										}
									}
								}
							}
							
						}else {
							payInfoMap.put("balanceCoin", "0");
							payOrderMap.put("balanceCoin",0);
						}
						map.put("subjectInfo", payInfoMap);
					} catch (Exception e) {
						log.info("获取相关钱包失败："+uid);
						e.printStackTrace();
					}
						
					//开通储值卡后查询用户是否有充值记录
					Map<Object, Object> sellerCardMap = sellerDao.queryCardSellerBySellerId(seller.getSellerid());
					if (sellerCardMap!=null && sellerCardMap.size()>0) {
						try {
							BigDecimal sellerCoin = new BigDecimal(0);
							BigDecimal sellerQouta = new BigDecimal(0);
							
							Map<String, String> userCardParam = new HashMap<String, String>();
							userCardParam.put("sellerid", sellerCardMap.get("sellerid").toString());
							userCardParam.put("uid", uid);
							//查询储值卡余额
							Map<String, String> userSellerCardMap = liveGiftsInfoService.getSellerCardBlanceInfo(userCardParam);
							if (userSellerCardMap!=null && userSellerCardMap.size()>0) {//如果有商家储值卡余额记录
								//判断商家储值卡余额的额度是否为0  
								if (userSellerCardMap.get("quota")!=null && new BigDecimal(userSellerCardMap.get("quota").toString()).compareTo(new BigDecimal(0))>0) {
									sellerQouta = new BigDecimal(userSellerCardMap.get("quota").toString());
									
									//如果有
									if (liveWallMap.get("sellerCoin")!=null && new BigDecimal(liveWallMap.get("sellerCoin").toString()).compareTo(new BigDecimal(0))>0 ) {
										sellerCoin = new BigDecimal(liveWallMap.get("sellerCoin").toString());
										
										if (sellerQouta.compareTo(sellerCoin)>0) {
											//查询商铺是否开启储值卡
											payInfoMap.put("sellerCardMoney", sellerCoin);//设置会员在上家储值卡基础余额
										}else {
											payInfoMap.put("sellerCardMoney", sellerQouta);//设置会员在上家储值卡基础余额
										}
										payInfoMap.put("isSellerCardPay", 1);//设置商家是否开启储值卡支付  1是
										
									}else {
										//查询商铺是否开启储值卡
										payInfoMap.put("sellerCardMoney", 0);//设置会员在上家储值卡基础余额
										payInfoMap.put("isSellerCardPay", 2);//设置商家是否开启储值卡支付  2置灰
										payInfoMap.put("birdCoinDesc", "鸟币不足，看直播打赏主播可获鸟币奖励");//有额度没鸟币
									}
								}
							}
						} catch (Exception e) {
							log.info("获取鸟粉专享卡失败");
							e.printStackTrace();
							return new MapResponse(ResponseCode.FAILURE, "获取鸟粉专享卡失败");
						}
					}
					
					//表示需要创建订单 
					if (billpaymentRequest.getIsCreate() == 1) {
						
						//确认下单的时候去计算是否符合当前设置的消费要求
						if (billpaymentRequest.getAmount().compareTo(minAmount)<0 || billpaymentRequest.getAmount().compareTo(maxAmount)>0) {
							return new MapResponse(ResponseCode.FAILURE, "消费金额须介于"+minAmount+"-"+maxAmount+"元之间");
						}
						
						//创建订单 主体  生成订单
						Map<Object, Object> orderResultMap = this.createOrderResultMap(billpaymentRequest,resultMap,otherMap,uid,seller);
						if (seller!=null) {
							orderResultMap.put("ledger_mode", seller.getLedgerMode()); // 是否开启直播分账 0 不开启 1开启
							orderResultMap.put("baseagio", seller.getAgio());
							orderResultMap.put("saas_channel", seller.getSaasType());
						}
						
						//订单插入直播分账基本信息
						if (sellerLedger!=null) {
							orderResultMap.put("live_ledger", 1); // 是否开启直播分账 0 不开启 1开启
							if (null !=sellerLedger.getLedgerStyle()) {
								orderResultMap.put("live_ledger_style", sellerLedger.getLedgerStyle()); //分账方式 0 自动 1 手动,
							}
							orderResultMap.put("live_ledger_mode", sellerLedger.getLedgerMode()); //分账模式 1 全部金额分账 2 粉丝券金额分账,
							orderResultMap.put("live_ledger_ratio", sellerLedger.getLedgerRatio()); //分账比例 如:0.6000
						}else {
							orderResultMap.put("live_ledger", 0);//
						}
						
						//插入会员等级关系链
						Map<Object, Object> liveMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
						orderResultMap.put("uid_relation_chain",liveMap.get("uid_relation_chain")==null?"":liveMap.get("uid_relation_chain") );///会员等级关系链
						//加入appSource来源					
							orderResultMap.put("appSourceStatus",EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, billpaymentRequest.getAppSource()));
						if (orderResultMap!=null && orderResultMap.size()>0) {
							//如果优惠券金额完全可以支付订单的时候 立即支付 无需调起支付接口
							BigDecimal payAmount = new BigDecimal(orderResultMap.get("payment").toString());
							if (payAmount.compareTo(new BigDecimal(0))==0 && billpaymentRequest.getCouponType()>-1 && !StringUtils.isEmpty(billpaymentRequest.getCouponId()) ) {
								
								//生成订单 
								orderResultMap.put("paytype", "1000011");//优惠券支付
								int resultNum = billDao.insertBillOrderInfo(orderResultMap);
								if (resultNum>0) {
									try {
										//锁定优惠券，生成优惠券使用记录
										List<Integer> couponIdList = (List<Integer>)map.get("couponIdList");
										if (billpaymentRequest.getCouponType()>-1 && null != billpaymentRequest.getCouponId() && !StringUtils.isEmpty(billpaymentRequest.getCouponId())) {
											this.lockAndReleaseCouponStatus(orderResultMap, billpaymentRequest,billpaymentRequest.getCouponType(),couponIdList);
											
										}
										//调用业务服务 更新美食订单状态
										int state = this.updateBillOrderStatus(orderResultMap,billpaymentRequest);
										if (state>0) {
											Map<Object, Object> sucMap = new HashMap<Object, Object>();
											sucMap.put("orderNo", orderResultMap.get("bid"));
											sucMap.put("isChoosePay", 0);//是否去到选择支付方式页面 否
											response = new MapResponse(ResponseCode.BILL_ORDER_PAY_SUCCESS, "操作成功");//美食订单下单支付成功
											response.setResponse(sucMap);
											return response;
										}else {
											return new MapResponse(ResponseCode.FAILURE, "更新订单失败");//美食订单下单支付成功
										}
									} catch (Exception e) {
										log.info("调用业务服务更新订单异常："+orderResultMap.get("bid"));
										e.printStackTrace();
										return new MapResponse(ResponseCode.FAILURE, e.getMessage());
									}
								}
								
							}else {
								//生成订单 
								int resultNum = billDao.insertBillOrderInfo(orderResultMap);
								
								if (resultNum>0) {
									payOrderMap.put("orderNo", orderResultMap.get("bid"));
									payOrderMap.put("payAmount", orderResultMap.get("payment"));
									payOrderMap.put("sellerId", orderResultMap.get("sellerid"));
									payOrderMap.put("sellerName", orderResultMap.get("sellername"));
									payOrderMap.put("couponId", billpaymentRequest.getCouponId());
									
									payOrderMap.put("couponType", billpaymentRequest.getCouponType());
									payOrderMap.put("isChoosePay", 1);//是否去到选择支付方式页面  是
									payOrderMap.put("birdCoinPayMoney", map.get("birdCoinPayMoney"));//
									payOrderMap.put("liveCoinPay", seller.getLiveCoinPay());//商家是否开启鸟币支付
								}
								response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
								response.setResponse(payOrderMap);
								return response;
							}
							
						}else {
							return new MapResponse(ResponseCode.FAILURE, "生成订单失败");
						}
						
					}else {
						response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
						response.setResponse(map);
						return response;
					}
					
				} catch (Exception e) {
					log.info("下单计算金额异常");
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, e.getMessage());
				}
				
			}else {
				return new MapResponse(ResponseCode.FAILURE, "结算金额不能小于0");
			}
			
		} catch (Exception e) {
			log.info("平台下单操作异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "系统异常");
		}
		return response;
	}
	
	
	/**
	 * 描述：计算用户的金额，折扣，使用优惠券，返积分
	 * @param BillpaymentRequest billpaymentRequest
	 * @return Map<object,object>计算结果
	 * 
	 * */
	public Map<Object, Object> calculationPayAmount(BillpaymentRequest billpaymentRequest,Map<Object, Object> sellerMap) throws Exception{
		
		//首选初始化返回参数
		Map<Object, Object> map = this.resultOrderMap(billpaymentRequest,sellerMap);
		BigDecimal payAmount = billpaymentRequest.getAmount();
		String switchState = propertiesUtil.getValue("platformPaybirdCoinSwitch", "conf_common.properties").toString() ;//鸟币支付与金额转换系数
		BigDecimal sellerAgio = new BigDecimal(1);
		 if (!StringUtils.isEmpty(switchState) && !"null".equals(switchState)) {
			if (switchState.equals("1")) {
				sellerAgio = new BigDecimal(sellerMap.get("sellerAgio")==null?"0":sellerMap.get("sellerAgio").toString());
			}
		}
		
		//优惠金额的面额
		BigDecimal cuser = new BigDecimal(0);
		
		try {
			//计算鸟币需要支付的金额          
			
			BigDecimal birdAddAgio = new BigDecimal(propertiesUtil.getValue("platformPaybirdCoin", "conf_common.properties"));//获取鸟币提价比例0.7
			
//			BigDecimal birdAgio = sellerAgio.add(birdAddAgio).setScale(2, BigDecimal.ROUND_HALF_UP);//计算鸟币支付比例
			
			BigDecimal birdCoinPayMoney = new BigDecimal(0);
			BigDecimal birdCoin = new BigDecimal(0);
			
			//查看商家有无设置推荐红包  
			int sellerRedPacket = sellerService.querySellerRedPacketBySellerId(billpaymentRequest.getSellerId().intValue());
			if (sellerRedPacket>0) {
				map.put("isRedPacket", 1);
			}
			
			map.put("paymentSaleAmount", 0);//初始化下单立减金额  已无下单立减功能
			//如果有使用优惠券的情况
			if (billpaymentRequest.getCouponType()>-1) {
				//优惠券等于平台的   计算金额：减去活动 ->商家立减->优惠券
				if (billpaymentRequest.getCouponType() == 0 || billpaymentRequest.getCouponType() == 5 || billpaymentRequest.getCouponType() == 1) {
					
					//使用优惠券金额
					BigDecimal denominationAmount =  new BigDecimal(0);
						
					//3. 使用优惠券
					Map<Object, Object> couponMap = new HashMap<Object, Object>();
					String [] couponId = billpaymentRequest.getCouponId().split(",");
					List<Integer> couponIdList = new ArrayList<Integer>();
					for (int i = 0; i < couponId.length; i++) {
						couponIdList.add(Integer.parseInt(couponId[i]) );
					}
					couponMap.put("list", couponIdList);
					map.put("couponIdList", couponIdList);
					
					
					List<CouponInfo> couponInfoList = anchorLiveRecordDao.queryFansCouponInfoTwoByCouponId(couponMap);
					if (couponInfoList.size()>0) {
						for (int i = 0; i < couponInfoList.size(); i++) {
							CouponInfo couponInfo = couponInfoList.get(i);
							if (couponInfo!=null) {
								if (couponInfo.getCondition().compareTo(new BigDecimal(0))>0) {
									if (billpaymentRequest.getAmount().compareTo(couponInfo.getCondition())<0) {
										log.info("消费金额未满足优惠券使用条件,请重新选择："+billpaymentRequest.getCouponId());
//										map.put("isClearCoupon", "1");
										throw new Exception("消费金额未满足优惠券使用条件,请重新选择");
									}
								}
								BigDecimal couponMoney = couponInfo.getDenomination().setScale(2, BigDecimal.ROUND_HALF_UP);
								if (payAmount.compareTo(couponMoney)>=0) {//如果买单 金额大于优惠券面额 
									payAmount = payAmount.subtract(couponMoney);    //剩余金额   等于  金额 - 优惠券
									cuser = cuser.add(couponMoney);        //使用优惠券
									
								}else {
									cuser = cuser.add(payAmount);   //使用优惠券 金额 等于  优惠券面额
									payAmount = payAmount.subtract(couponMoney);    //剩余金额 等于  金额 - 优惠券
								}
								
//								payAmount = payAmount.subtract(cuser);
								denominationAmount = denominationAmount.add(couponMoney);
//								map.put("denomination", couponInfo.getDenomination().setScale(2, BigDecimal.ROUND_HALF_UP));//使用优惠券面额
//								map.put("serial", couponInfo.getSerial());//优惠券序列码
								
							}else {
								log.info("未找到优惠券或者优惠券已被使用"+billpaymentRequest.getCouponId());
								throw new Exception("未找到优惠券或者优惠券已被使用");
							}
						}
						
					}
					
					map.put("cuser",cuser);//优惠券支付金额
					map.put("denomination", denominationAmount);//使用优惠券面额
					//使用了平台优惠券  计算下单立减 和 优惠券金额全部使用 下单金额   然后减去平台券 下单立减
//					payAmount = payAmount.subtract(denominationAmount);//.subtract(paymentSaleAmount);
					birdCoin = payAmount.divide(birdAddAgio,4,BigDecimal.ROUND_HALF_UP);
					map.put("birdCoinPayMoney",  birdCoin.multiply(sellerAgio).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				
				//优惠券等于商家的   结算顺序：扣减商家券->下单立减
				if (billpaymentRequest.getCouponType() == 2) {
					
					//1. 减去商家券的面额
					if (null != billpaymentRequest.getCouponId()  && !billpaymentRequest.getCouponId().isEmpty()) {
						//查询该用户获得的平台优惠券 面额
						CouponInfo couponInfo = personalCenterDao.queryUserCoponByCuid(Integer.parseInt(billpaymentRequest.getCouponId()));
						if (couponInfo!=null) {
							
							if (couponInfo.getConditions().compareTo(new BigDecimal(0))>0) {
								if (payAmount.compareTo(couponInfo.getConditions())<0) {
									log.info("消费金额未满足优惠券使用条件,请重新选择："+billpaymentRequest.getCouponId());
//									map.put("isClearCoupon", "1");
									throw new Exception("消费金额未满足优惠券使用条件,请重新选择");
								}
							}
							
							//判断结账金额是否大于优惠券面额  
							if (payAmount.compareTo(couponInfo.getDenomination())>=0) {
								//减去优惠券面额
								birdCoinPayMoney = birdCoinPayMoney.subtract(couponInfo.getDenomination());
								payAmount = payAmount.subtract(couponInfo.getDenomination());
								map.put("cuser",couponInfo.getDenomination().setScale(2, BigDecimal.ROUND_HALF_UP));//优惠券支付金额
							}else {
								map.put("cuser",payAmount.setScale(2, BigDecimal.ROUND_HALF_UP));//优惠券支付金额
							}
							map.put("denomination", couponInfo.getDenomination().setScale(2, BigDecimal.ROUND_HALF_UP));//使用优惠券面额
							map.put("serial", couponInfo.getSerial());//优惠券序列码
							birdCoin = payAmount.divide(birdAddAgio,4,BigDecimal.ROUND_HALF_UP);
							map.put("birdCoinPayMoney",  birdCoin.multiply(sellerAgio).setScale(2, BigDecimal.ROUND_HALF_UP));
						}else {
//							map.put("isClearCoupon", "1");
							log.info("未找到优惠券或者优惠券已被使用"+billpaymentRequest.getCouponId());
							throw new Exception("未找到优惠券或者优惠券已被使用");
						}
					}
					
				}
				
				//优惠券等于粉丝券的
				if (billpaymentRequest.getCouponType() == 3) {
					Map<Object, Object> couponMap = new HashMap<Object, Object>();
					couponMap.put("cid", billpaymentRequest.getCouponId());
					CouponInfo couponInfo = anchorLiveRecordDao.queryFansCouponInfoByCouponId(couponMap);
					if (couponInfo!=null) {
						if (payAmount.compareTo(couponInfo.getDenomination())>=0) {
							map.put("cuser",couponInfo.getDenomination().setScale(2, BigDecimal.ROUND_HALF_UP));//优惠券支付金额
						}else {
							map.put("cuser",payAmount.setScale(2, BigDecimal.ROUND_HALF_UP));//优惠券支付金额
						}
						birdCoinPayMoney = birdCoinPayMoney.subtract(couponInfo.getDenomination());
						payAmount = payAmount.subtract(couponInfo.getDenomination());
						map.put("denomination", couponInfo.getDenomination().setScale(2, BigDecimal.ROUND_HALF_UP));//使用优惠券面额
						map.put("serial", couponInfo.getSerial());//优惠券序列码
						birdCoin = payAmount.divide(birdAddAgio,4,BigDecimal.ROUND_HALF_UP);
						map.put("birdCoinPayMoney",  birdCoin.multiply(sellerAgio).setScale(2, BigDecimal.ROUND_HALF_UP));
					}else {
//						map.put("isClearCoupon", "1");
						log.info("未找到优惠券或者优惠券已被使用"+billpaymentRequest.getCouponId());
						throw new Exception("未找到优惠券或者优惠券已被使用");
					}
				}
				
			}else {
				
				//获取商家的满减活动
				Map<Object, Object> fullMap = new HashMap<>();
				fullMap.put("sellerId", billpaymentRequest.getSellerId());
				fullMap.put("payAmount", payAmount);
				List<ActivityFullreductionInfo> fullreductionInfoList = sellerService.querySellerSaleBySellerId(fullMap);
				ActivityFullreductionInfo fullreductionInfo = new ActivityFullreductionInfo();
				if (fullreductionInfoList.size()>0) {
					fullreductionInfo = fullreductionInfoList.get(0);
					map.put("fullId", fullreductionInfo.getId());
					map.put("fullMinusName", fullreductionInfo.getName());
				}
				
				//1. 扣减商家活动减免
				if (fullreductionInfo!=null && fullreductionInfo.getId()!=null) {
					//如果满减活动有三级减免等级  并且三级减免是正常开启  
					if (fullreductionInfo.getIsDerate() == 1) {
						//订单金额是否符合一级满减活动  
						if (payAmount.compareTo(fullreductionInfo.getConsumeAndPay1())>=0 && payAmount.compareTo(fullreductionInfo.getConsumeAndPay2())<0) {
							payAmount = payAmount.subtract(fullreductionInfo.getDerateLevel1Amount());
							map.put("fullMinusAmount", fullreductionInfo.getDerateLevel1Amount().setScale(2, BigDecimal.ROUND_HALF_UP));//计算满减的金额
							map.put("fullMinusStatus", 1);//是否满足了商家满减 0否1是
						}
						//订单金额是否符合二级满减活动  
						else if (payAmount.compareTo(fullreductionInfo.getConsumeAndPay2())>=0 && payAmount.compareTo(fullreductionInfo.getConsumeAndPay3())<0) {
							payAmount = payAmount.subtract(fullreductionInfo.getDerateLevel2Amount());
							map.put("fullMinusAmount", fullreductionInfo.getDerateLevel2Amount().setScale(2, BigDecimal.ROUND_HALF_UP));//计算满减的金额
							map.put("fullMinusStatus", 1);//是否满足了商家满减 0否1是
						}
						//订单金额是否符合三级满减活动  
						else if (payAmount.compareTo(fullreductionInfo.getConsumeAndPay3())>=0) {
							payAmount = payAmount.subtract(fullreductionInfo.getDerateLevel3Amount());
							map.put("fullMinusAmount", fullreductionInfo.getDerateLevel3Amount().setScale(2, BigDecimal.ROUND_HALF_UP));//计算满减的金额
							map.put("fullMinusStatus", 1);//是否满足了商家满减 0否1是
						}
					}else {
						//未开启三级减免  按照普通减免方式计算
						if (payAmount.compareTo(fullreductionInfo.getConsumeAndPay())>=0) {
							payAmount = payAmount.subtract(fullreductionInfo.getOffsetAmount());
							map.put("fullMinusAmount", fullreductionInfo.getOffsetAmount().setScale(2, BigDecimal.ROUND_HALF_UP));//计算满减的金额
							map.put("fullMinusStatus", 1);//是否满足了商家满减
						}
					}
				}else {
					map.put("fullMinusStatus", 0);//是否满足了商家满减 0否1是
					map.put("fullMinusAmount", new BigDecimal(0));
				}
				birdCoin = payAmount.divide(birdAddAgio,4,BigDecimal.ROUND_HALF_UP);
				map.put("birdCoinPayMoney",  birdCoin.multiply(sellerAgio).setScale(2, BigDecimal.ROUND_HALF_UP));
				
			}
			//返回参数的时候 查看金额是否为  负数
			if (payAmount.compareTo(new BigDecimal(0))<0) {
				map.put("payAmount", 0);
				map.put("birdCoinPayMoney", 0);
				map.put("integral", 0);
			}else {
//				map.put("birdCoinPayMoney", birdCoinPayMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
				map.put("payAmount", payAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
				map.put("integral", 0);
			}
			
		} catch (Exception e) {
			log.info("平台下单结算异常");
			e.printStackTrace();
			throw new Exception("操作异常");
		}
		return map;
	}
	
	/**
	 * 描述：买单调起支付
	 * @param 
	 * */
	public Object paymentOrder(PaymentOrderRequest paymentOrderRequest){
		
		MapResponse response = null;
//		String uid = paymentOrderRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(paymentOrderRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		if (StringUtils.isEmpty(paymentOrderRequest.getOrderNo())) {
			return new MapResponse(ResponseCode.FAILURE, "未获取到订单编号");
		}
		
		//处理用户订单重复提交支付 使用redis做判断  
		String consumeOrderkey = " consume_order_"+uid;
		
		Long resultNum = stringRedisTemplate.opsForValue().increment(consumeOrderkey, 1);
		if (resultNum == 1) {//设置key首次存活时长为3分钟   避免程序异常导致死锁
			stringRedisTemplate.expire(consumeOrderkey, 2, TimeUnit.MINUTES);
		}
		log.info("该用户提交支付请求个数累计："+resultNum);
		if (resultNum>1) {
			log.info("用户:"+uid+",目前有订单正在进行支付操作,将强退其他操作");
			return new MapResponse(ResponseCode.FAILURE, "有正在处理的订单,请稍后!");
		}
		
		try {
			//组装签名参数
			Map<String, String> signMap = new HashMap<String, String>();
			
			String clientType = "";
			String systemVerson = paymentOrderRequest.getSystemversion();
			if (systemVerson.indexOf("ios")>=0) {
				clientType = "ios";
			}else if (systemVerson.indexOf("android")>=0){
				clientType = "android";
			}else if (systemVerson.indexOf("wx")>=0){
				clientType = "wx";
			}
//			组装时优先初始化订单发起验单时公共信息
			boolean isQuota = false;
			boolean isLiveCoin = false;
			boolean isBalance = false;
			
			BigDecimal amountMoney = new BigDecimal(0);//会员钱包 余额
			BigDecimal balanceCoinMoney = new BigDecimal(0); //直播钱包鸟币余额
			BigDecimal balanceSellerCoinMoney = new BigDecimal(0); //直播钱包 商户鸟币余额
			try {
				
				//获取钱包余额
				Map<String, String> wallMap = liveGiftsInfoService.getXmnWalletBlance(uid);
				//钱包余额总金额
				if (wallMap!=null) {
					amountMoney = new BigDecimal(wallMap.get("zbalance"))
								.add(new BigDecimal(wallMap.get("commision")));
				}else {
					log.info("获取会员钱包余额失败");
					return new MapResponse(ResponseCode.FAILURE, "获取钱包失败");
				}

				//获取寻蜜鸟直播用户钱包
				Map<String, Object> liveWallMap = liveGiftsInfoService.getLiveWalletBlance(uid);
				
				if (liveWallMap!=null && liveWallMap.size()>0) {//
					balanceCoinMoney = liveWallMap.get("zbalanceCoin")==null?new BigDecimal(0):new BigDecimal(liveWallMap.get("zbalanceCoin").toString()) ;
					balanceSellerCoinMoney = liveWallMap.get("sellerCoin")==null?new BigDecimal(0):new BigDecimal(liveWallMap.get("sellerCoin").toString()) ;
				}
				
			} catch (Exception e) {
				log.info("获取用户钱包失败");
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, "获取用户钱包失败");
			}
			
			//当支付类型不为空的时候 判断是否是 寻蜜鸟的支付方式   是则赋值空串
			if (null!=paymentOrderRequest.getPayType()) {
				if (paymentOrderRequest.getPayType().equals("1000015") || paymentOrderRequest.getPayType().equals("1000020") 
						|| paymentOrderRequest.getPayType().equals("1000000") ||  paymentOrderRequest.getPayType().equals("1000027")) {
					paymentOrderRequest.setPayType("");
				}
			}
			
			//根据订单编号查询订单
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("bid", paymentOrderRequest.getOrderNo());
			paramMap.put("uid", uid);
			//查询订单详情
			Map<Object, Object> orderInfoMap = billDao.queryBillInfoByOrderNo(paramMap);
			if (orderInfoMap!=null && orderInfoMap.size()>0) {
				
				//计算各单位需要支付的金额
				BigDecimal money = new BigDecimal(orderInfoMap.get("money")==null?"0":orderInfoMap.get("money").toString());
				BigDecimal cuser = new BigDecimal(orderInfoMap.get("cuser")==null?"0":orderInfoMap.get("cuser").toString());
				BigDecimal reduction = new BigDecimal(orderInfoMap.get("reduction")==null?"0":orderInfoMap.get("reduction").toString());
				BigDecimal full_reduction = new BigDecimal(orderInfoMap.get("full_reduction")==null?"0":orderInfoMap.get("full_reduction").toString());
				BigDecimal payment = new BigDecimal(orderInfoMap.get("payment")==null?"0":orderInfoMap.get("payment").toString());
				//需要支付的金额
				BigDecimal paymentAmount = money.subtract(cuser).subtract(reduction).subtract(full_reduction);
				
				//如果是优惠券完全抵扣 (支持PHP下单 使用优惠券完全支付) 校验订单金额
				if (paymentAmount.compareTo(new BigDecimal(0))<=0 && null != paymentOrderRequest.getCouponId() && !StringUtils.isEmpty(paymentOrderRequest.getCouponId()) ) {
					
//					 paymentOrderRequest.getPayType().equals("1000011");
					
					//订单消费总额 减去 优惠券，立减  满减等金额必须小于或等于0  并且 订单表payment需支付金额必须为0
					if (paymentAmount.compareTo(new BigDecimal("0"))<=0 && payment.compareTo(new BigDecimal("0")) == 0 ) {
						
						//更新订单
						BillpaymentRequest billpaymentRequest = new BillpaymentRequest();
						billpaymentRequest.setCouponId(paymentOrderRequest.getCouponId());
						billpaymentRequest.setCouponType(paymentOrderRequest.getCouponType());
						billpaymentRequest.setUid(Integer.parseInt(uid));
						billpaymentRequest.setAppversion(paymentOrderRequest.getAppversion());
						
						String [] couponId = paymentOrderRequest.getCouponId().split(",");
						List<Integer> couponIdList = new ArrayList<Integer>();
						for (int i = 0; i < couponId.length; i++) {
							couponIdList.add(Integer.parseInt(couponId[i]));
						}
						
						//锁定优惠券，生成优惠券使用记录
						if (paymentOrderRequest.getCouponType()>-1  && !StringUtils.isEmpty(paymentOrderRequest.getCouponId())) {
							try {
								this.lockAndReleaseCouponStatus(orderInfoMap, billpaymentRequest,billpaymentRequest.getCouponType(),couponIdList);
							} catch (Exception e) {
								return new MapResponse(ResponseCode.FAILURE, e.getMessage()) ;
							}
							
						}
						
						//调用业务服务 更新美食订单状态
						int state = this.updateBillOrderStatus(orderInfoMap,billpaymentRequest);
						if (state>0) {
							Map<Object, Object> sucMap = new HashMap<Object, Object>();
							sucMap.put("orderNo", orderInfoMap.get("bid"));
							sucMap.put("isChoosePay", 0);//是否去到选择支付方式页面 否
							response = new MapResponse(ResponseCode.BILL_ORDER_PAY_SUCCESS, "操作成功");//美食订单下单支付成功
							response.setResponse(sucMap);
							return response;
						}else {
							return new MapResponse(ResponseCode.FAILURE, "更新订单失败");//美食订单下单支付成功
						}
						
					}else {
						return new MapResponse(ResponseCode.FAILURE, "优惠券不能完全抵扣您的订单总额,请选择其他支付方式");
					}
					
				}
				BigDecimal  ratioNumber = new BigDecimal(propertiesUtil.getValue("platformPaybirdCoin", "conf_common.properties").toString()) ;//鸟币支付与金额转换系数
				String switchState = propertiesUtil.getValue("platformPaybirdCoinSwitch", "conf_common.properties").toString() ;//鸟币支付与金额转换系数
				String discount = "1";
				
				//如果选择了鸟币支付的任何的一种支付方式  则必须将折扣基数 和 商家折扣传到支付接口
				if (paymentOrderRequest.getIsSellerCard()>0 || paymentOrderRequest.getIsBirdCoin()>0) {
					signMap.put("base", ratioNumber.toString());//换算基数
					if (!StringUtils.isEmpty(switchState) && !"null".equals(switchState)) {
						if (switchState.equals("1")) {
							discount = orderInfoMap.get("baseagio").toString();
						}else {
							signMap.put("discount", "1");//换算基数
						}
					}
					signMap.put("discount", discount);//换算基数
					//计算目前支付订单需要支付多少鸟币  适用鸟粉专享卡  和  鸟粉卡
					//鸟币需支付 = 金额 / 0.7 * 折扣
					paymentAmount = paymentAmount.divide(ratioNumber,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(discount)).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				log.info("未进入计算逻辑钱的余额:"+paymentAmount);
				
				//储值卡支付
				if (paymentOrderRequest.getIsSellerCard() > 0) {
					
					try {
						//开通储值卡后查询用户是否有充值记录
						Map<Object, Object> sellerCardMap = sellerDao.queryCardSellerBySellerId(Integer.parseInt(orderInfoMap.get("sellerid").toString()) );
						if (sellerCardMap==null && sellerCardMap.size()<=0) {
							return new MapResponse(ResponseCode.FAILURE, "该商家未开通储值卡,或已关闭储值卡功能!");
						}
						//获取储值卡余额
						//开通储值卡后查询用户是否有充值记录 调支付服务
						Map<String, String> userCardParam = new HashMap<String, String>();
						userCardParam.put("sellerid", sellerCardMap.get("sellerid").toString());
						userCardParam.put("uid", uid.toString());
						Map<String, String> userSellerCardMap = liveGiftsInfoService.getSellerCardBlanceInfo(userCardParam);
						
						if (userSellerCardMap.size()>0) {
							//如果有充值  计算当前额度是否能开启储值卡支付
							BigDecimal quota = new BigDecimal(userSellerCardMap.get("quota")==null?"0":userSellerCardMap.get("quota").toString());//当前商铺当前用户的储值卡额度
							//计算储值卡金额是否能完全支付订单
							if (quota.compareTo(new BigDecimal(0))>0) {
								//如果有 商户鸟币
								if (balanceSellerCoinMoney.compareTo(new BigDecimal(0))>0 ) {
									//如果当前额度大于 商户返现鸟币
									if (quota.compareTo(balanceSellerCoinMoney)>0) {
										//查询商铺是否开启储值卡
										paymentAmount = paymentAmount.subtract(balanceSellerCoinMoney).setScale(2, BigDecimal.ROUND_HALF_UP);//设置专享卡支付剩余金额
									}else {
										paymentAmount = paymentAmount.subtract(quota).setScale(2, BigDecimal.ROUND_HALF_UP);//设置专享卡支付剩余金额
									}
									
									log.info("进入专享卡逻辑后的余额:"+paymentAmount);
									
								}else {
									log.info("当前用户所属的商户专享卡没有返现鸟币"+uid+",sellerid "+orderInfoMap.get("sellerid"));
									return new MapResponse(ResponseCode.FAILURE, "当前用户商户专享卡没有返现鸟币");
								}
								//判断舒服还有选择其他支付方式
								if (paymentOrderRequest.getIsSellerCard() > 0 && paymentOrderRequest.getIsBirdCoin() == 0
										&& paymentOrderRequest.getIsBalance() == 0 && (null==paymentOrderRequest.getPayType() || paymentOrderRequest.getPayType().equals(""))) {
									if (quota.compareTo(paymentAmount)<0) {
										return new MapResponse(ResponseCode.FAILURE, "储值卡余额不足!");
									}
								}
								
								//验证支付参数组装
								isQuota=true;//是否使用储值卡 必填
								signMap.put("sellerId", userSellerCardMap.get("sellerid").toString()); //储值卡所属商家
								signMap.put("sellerName", userSellerCardMap.get("sellername").toString()); //储值卡所属商家
								signMap.put("sellerType", userSellerCardMap.get("sellertype")); //储值卡所属商家
								signMap.put("consumeSellerId", orderInfoMap.get("sellerid").toString());//消费商家ID
								signMap.put("consumeSellerName", orderInfoMap.get("sellername").toString());//消费商家名称
//								paymentAmount = paymentAmount.subtract(quota);//设置专享卡支付剩余金额
								
							}else {
								return new MapResponse(ResponseCode.FAILURE, "储值卡额度不足!");
							}
							
						}else {
							log.info("鸟粉专享卡支付错误：没有找到储值卡UID "+uid+",sellerid "+orderInfoMap.get("sellerid"));
							return new MapResponse(ResponseCode.FAILURE, "未获取到鸟粉专享卡");
						}
					} catch (Exception e) {
						log.info("鸟粉专享卡支付错误");
						e.printStackTrace();
						return new MapResponse(ResponseCode.FAILURE, "鸟粉专享卡支付失败");
					}
				}
				
				//判断鸟币余额是否能够支付当前订单消费金额
				if (paymentOrderRequest.getIsBirdCoin()>0) {
					try {
						//余额是否大于当前剩余支付的订单总额
						if (paymentAmount.compareTo(new BigDecimal(0))>0) {//如果前面支付方式在够支付的情况下
							if (paymentOrderRequest.getIsBirdCoin() > 0 && paymentOrderRequest.getIsBalance() == 0 && (null==paymentOrderRequest.getPayType() || paymentOrderRequest.getPayType().equals(""))) {
								if (balanceCoinMoney.compareTo(paymentAmount)<0) {
									return new MapResponse(ResponseCode.FAILURE, "鸟币余额不足!");
								}
							}
							paymentAmount = paymentAmount.subtract(balanceCoinMoney).setScale(2, BigDecimal.ROUND_HALF_UP);//设置专享卡支付剩余金额
							
							log.info("进入鸟粉卡逻辑后的余额:"+paymentAmount);
							
							isLiveCoin=true;//是否使用余额 必须
						}else {
							log.info("查看鸟币逻辑 进入false 情况下的金额:"+paymentAmount);
							
							return new MapResponse(ResponseCode.FAILURE, "选择支付方式有误,请重新选择!");//美食订单下单支付成功
						}
						
					} catch (Exception e) {
						log.info("调用业务服务更新订单异常："+orderInfoMap.get("bid"));
						e.printStackTrace();
						return new MapResponse(ResponseCode.FAILURE, "生成订单异常");
					}
				}
				
				//如果选择了鸟币支付的任何的一种支付方式  则必须将折扣基数 和 商家折扣传到支付接口
				if (paymentOrderRequest.getIsSellerCard()>0 || paymentOrderRequest.getIsBirdCoin()>0) {
					//计算完鸟币后  如果还没有支付完成的 就返回换算 余额要支付多少钱
					paymentAmount = paymentAmount.multiply(ratioNumber).divide(new BigDecimal(discount), 4, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				
				//判断钱包余额不足 无需调用支付服务
				if (paymentOrderRequest.getIsBalance() >0) {
					if (paymentAmount.compareTo(new BigDecimal(0))>0) {//如果前面支付方式在不够支付的情况下
						if (paymentOrderRequest.getIsBalance() > 0 && (null==paymentOrderRequest.getPayType() || paymentOrderRequest.getPayType().equals(""))) {
							if (amountMoney.compareTo(paymentAmount)<0) {
								return new MapResponse(ResponseCode.FAILURE, "钱包余额不足!");
							}
						}
						paymentAmount = paymentAmount.subtract(amountMoney).setScale(2, BigDecimal.ROUND_HALF_UP);//设置钱包支付剩余金额
						log.info("进入现金逻辑后的余额:"+paymentAmount);
						
						isBalance=true;//是否使用余额 必须
					}else {
						log.info("查看剩余现金逻辑 进入false 情况下的金额:"+paymentAmount);
						
						return new MapResponse(ResponseCode.FAILURE, "选择支付方式有误,请重新选择!");//美食订单下单支付成功
					}
				}
				//锁定优惠券   - 发起支付才锁定优惠券
				try {
					//锁定优惠券，生成优惠券使用记录
					if ( paymentOrderRequest.getCouponType()>-1  && null != paymentOrderRequest.getCouponId()  && !StringUtils.isEmpty(paymentOrderRequest.getCouponId())) {
						BillpaymentRequest billpaymentRequest = new BillpaymentRequest();
						billpaymentRequest.setCouponId(paymentOrderRequest.getCouponId());
						billpaymentRequest.setCouponType(paymentOrderRequest.getCouponType());
						billpaymentRequest.setAppversion(paymentOrderRequest.getAppversion());
						
						String [] couponId = paymentOrderRequest.getCouponId().split(",");
						List<Integer> couponIdList = new ArrayList<Integer>();
						for (int i = 0; i < couponId.length; i++) {
							couponIdList.add(Integer.parseInt(couponId[i]));
						}
						
						int result = this.lockAndReleaseCouponStatus(orderInfoMap, billpaymentRequest,paymentOrderRequest.getCouponType(),couponIdList);
						if (result<=0) {
							return new MapResponse(ResponseCode.FAILURE, "使用优惠券异常");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new  MapResponse(ResponseCode.FAILURE, e.getMessage());
				}
				
				if (null != paymentOrderRequest.getPayType() && !paymentOrderRequest.getPayType().equals("")) {
					
					//如果是微信公众号支付  判断 并且传值 wxuid
					if (paymentOrderRequest.getPayType().equals("1000013") || paymentOrderRequest.getPayType().equals("1000025")) {
						signMap.put("wxuid", paymentOrderRequest.getOpenId());
					}
					if (paymentOrderRequest.getPayType().equals("1000014") || paymentOrderRequest.getPayType().equals("1000023")) {
						signMap.put("callBackUrl", paymentOrderRequest.getReturnUrl());
					}
				}
				
				signMap.put("amount", orderInfoMap.get("money").toString());//订单总额 必须
				signMap.put("clientType", clientType);//（客户端类型）、--必须
				signMap.put("orderName", orderInfoMap.get("sellername").toString()+"@买单");//（订单名称,取商户名称）、--必须
				signMap.put("orderNumber", orderInfoMap.get("bid").toString());//订单编号 必须
				signMap.put("appVersion", paymentOrderRequest.getAppversion());//app版本号 必须
				signMap.put("appId", "10010");//appId 固定值10010  必须
				signMap.put("userId", uid);//appId 固定值10010  必须
//				signMap.put("source", "001");//订单来源
//				signMap.put("orderType", "2");//订单类型
				
				BigDecimal preferential = new BigDecimal(0).add(new BigDecimal(orderInfoMap.get("full_reduction").toString())
				.add(new BigDecimal(orderInfoMap.get("reduction").toString()))
				.add(new BigDecimal(orderInfoMap.get("cuser").toString())));
				if (preferential.compareTo(new BigDecimal(0))>0) {
					signMap.put("preferential", preferential.toString());//优惠金额
				}
				
				//为商家名称做编码 并且去掉空格 否则会出签名问题
				String url = this.transMap(signMap,"payFoodUrl");
				url=url.replace(orderInfoMap.get("sellername").toString(), URLEncoder.encode(orderInfoMap.get("sellername").toString(),"UTF-8")).replaceAll(" ", "%20");
				StringBuffer sb = new StringBuffer(); //下面三个参数不参与前面 
				sb = sb.append("&").append("isQuota=").append(isQuota).append("&").append("isLiveCoin=").append(isLiveCoin).append("&").append("isBalance=").append(isBalance);
				if (null != paymentOrderRequest.getPayType() && !paymentOrderRequest.getPayType().equals("")) {
					sb = sb.append("&").append("paymentType=").append(paymentOrderRequest.getPayType());//支付类型  必须
				}
				url = url +sb.toString();
				try {
					//请求支付接口;
					String result = HttpConnectionUtil.doPost(url, "");
					System.out.println("===================:"+result);
					if (StringUtils.isNotEmpty(result)) {
						JSONObject json = JSONObject.parseObject(result);
						json.put("orderNo", paymentOrderRequest.getOrderNo());
						return json;
					}else {
						return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
					}
				} catch (Exception e) {
					log.info("调用支付接口失败"+paymentOrderRequest.getOrderNo());
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
				}
			}else {
				log.info("查询订单失败:"+paymentOrderRequest.getOrderNo());
				return new MapResponse(ResponseCode.FAILURE, "获取订单失败");
			}
		} catch (Exception e) {
			log.info("订单支付异常："+paymentOrderRequest.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "订单支付异常,请重试");
		}finally{
			//删除处理并发操作的rediskey
			stringRedisTemplate.delete(consumeOrderkey);
		}
		
	}
	
	/**
	 * 描述：更新业务服务订单状态 
	 * @param map , billpaymentRequest
	 * @return object 
	 * */
	public int updateBillOrderStatus(Map<Object, Object> map,BillpaymentRequest billpaymentRequest) throws Exception{
		int state = 0;
		try {
			//更新订单组合
			XmnOrderParamV2 orderParam = new XmnOrderParamV2();
			orderParam.setBid(map.get("bid").toString());
			orderParam.setStatus("1");
			orderParam.setZdate(DateUtil.format(new Date()));
			orderParam.setUid(billpaymentRequest.getUid().toString());
			orderParam.setPhoneid(map.get("phoneid")==null?"":map.get("phoneid").toString());
			//如果是鸟币支付
			if (null!=billpaymentRequest.getIsBirdCoin() && billpaymentRequest.getIsBirdCoin()==1) {
				orderParam.setPaytype("1000015");
				orderParam.setLiveCoin(billpaymentRequest.getAmount().toString());//鸟币支付总额
				orderParam.setPayamount(map.get("payment").toString());//订单实付金额(支付成功必填)
				orderParam.setLiveCoinArrivedMoney(map.get("payment").toString());
				orderParam.setLiveCoinRatio(propertiesUtil.getValue("platformPaybirdCoin", "conf_common.properties"));
				orderParam.setIsbalance("1");
				
			}else {//否则是优惠券完全抵消订单金额
				orderParam.setPaytype("1000000");
				orderParam.setLiveCoin("0");//鸟币支付总额
				orderParam.setPayamount("0");//订单实付金额(支付成功必填)
				orderParam.setLiveCoinArrivedMoney("0");
				orderParam.setLiveCoinRatio("0");
			}
			if (Integer.parseInt(map.get("coupon_type")==null?"0":map.get("coupon_type").toString())>0) {
				orderParam.setPreferential(map.get("cuser")==null?"0":map.get("cuser").toString());
			}
			
			orderParam.setOrdertype("1");
//			orderParam.setLedgertype(map.get("live_ledger").toString());//订单分账模式(支付成功必填)
			orderParam.setIsbalance("0");//是否使用寻蜜鸟钱包支付(支付成功必填)
			orderParam.setMoney(map.get("money").toString());//订单总金额(金额字段全部保留2位小数)， (支付成功必填)
			
			orderParam.setSamount("0");//第三方支付金额(支付成功必填)
			orderParam.setCommision("0");//佣金支付金额(支付成功必填)
			orderParam.setProfit("0");//返利支付金额(支付成功必填)
			orderParam.setGiveMoney("0");//赠送支付金额(支付成功必填)
			
			//订单优惠金额(支付成功必填)
			BigDecimal preferential = new BigDecimal(0).add(new BigDecimal(map.get("full_reduction").toString())
														.add(new BigDecimal(map.get("reduction").toString()))
														.add(new BigDecimal(map.get("cuser").toString())));
			orderParam.setPreferential(preferential.toString());
			
			try {
				TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("OrderService");
				OrderService.Client  liveClient= new OrderService.Client(tMultiplexedProtocol); 
				thriftBusinessUtil.openTransport();
				String editResult = liveClient.updateXmnOrderInfoV2(orderParam);
				System.out.println("========================="+editResult);
				if (editResult.indexOf("state")>=0) {
					state = 0;
				}else {
					state = 1;
				}
			} catch (Exception e) {
				log.info("更新订单失败"+e);
				e.printStackTrace();
				throw new Exception("支付成功,修改订单状态异常");
			}finally{
				thriftBusinessUtil.coloseTransport();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("更新订单状态失败："+map.get("bid"));
		}
		return state;
	}
	
	
	/**
	 * 描述：计算满立减应该立减多少钱
	 * @param payAmount(金额)    sellerMap(包含立减比例)
	 * */
	public String paymentOrderSaleAmount(BigDecimal payAmount ,Map<Object, Object> sellerMap ){
		BigDecimal saleAgio = new BigDecimal(0);
		if (sellerMap!=null) {
			saleAgio = new BigDecimal(sellerMap.get("agio").toString());
//			if (saleAgio.compareTo(new BigDecimal(0)) == 0) {
//				saleAgio = new BigDecimal(1);  
//			}
		}
		BigDecimal paymentSaleAmount = payAmount.multiply(saleAgio).setScale(2, BigDecimal.ROUND_HALF_UP);
		return paymentSaleAmount.toString();
	}
	
	
	/**
	 * 描述 ：计算商家折扣多少
	 * 计算方式 ：商户折扣大于等于 0.95  (1 - 商户折扣) * 0.5    / 小于的 0.95 - 商户折扣
	 * @param  Map： sellerId ，  agio 
	 * @return String  折扣信息
	 * */
	public String calculationSellerDiscount(Map<Object, Object> map){
		String rellayAgio = "0";
		//---------获取直播商家的分账信息   全部金额分账 不显示下单立减折扣
		LiveSellerLedgerInfo  sellerLedger = anchorLiveRecordDao.queryLiveSellerLedgerBySellerId(Integer.parseInt(map.get("sellerid").toString()) );
		if (sellerLedger!=null && sellerLedger.getLedgerMode() == 1) {
			return rellayAgio;
		}
		if (map!=null ) {
			BigDecimal agio = new BigDecimal(map.get("agio").toString());
			if (agio.compareTo(new BigDecimal("0.95"))>=0) {
				//商户折扣大于等于 0.95  (1 - 商户折扣) * 0.5 
				rellayAgio = (new BigDecimal(1).subtract(agio)).multiply(new BigDecimal("0.5")).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
			}else {
				//小于的 0.95 - 商户折扣
				rellayAgio = new BigDecimal(0.95).subtract(agio).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
			}
		}
		return rellayAgio;
	}
	
	/**
	 * 描述：初始化下单结算需要返回的参数
	 * @param billpaymentRequest  sellerMap
	 * @return Map<Object, Object> 
	 * */
	public Map<Object, Object> resultOrderMap(BillpaymentRequest billpaymentRequest,Map<Object, Object> sellerMap) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		//商户信息
		try {
//			map.put("isClearCoupon", "0");
			
			map.put("sellername", sellerMap.get("sellername"));
			map.put("sellerid", sellerMap.get("sellerid"));
			map.put("zoneid", sellerMap.get("zoneid"));
			map.put("agio", sellerMap.get("agio"));
			map.put("logoPic", sellerMap.get("logoPic"));
			
			//下单信息
			map.put("uid", billpaymentRequest.getUid());
//			map.put("couponId", billpaymentRequest.getCouponId());
			map.put("amount", billpaymentRequest.getAmount());
			map.put("couponType", billpaymentRequest.getCouponType());
			map.put("fullMinusAmount", 0);//满减金额
			map.put("fullId", "0");//满减ID
			map.put("fullMinusName", "");//满减名称
			map.put("paymentSaleAmount", 0);//下单立减
			map.put("denomination", 0);//使用优惠券
			map.put("integral", 0);
			map.put("isRedPacket", 0);//是否有设置商家推荐红包
			map.put("rPhone", billpaymentRequest.getrPhone()==null?"":billpaymentRequest.getrPhone());
			map.put("serial", "");//优惠券序列码
			map.put("cuser",0);
			map.put("recommendFriendDesc",propertiesUtil.getValue("platformRecommendFriendDesc", "conf_common.properties") );
		} catch (Exception e) {
			log.info("组装订单返回参数失败");
			throw new Exception("系统异常");
		}
		return map;
	}
	
	/**
	 * 描述：创建订单后锁定/使用 优惠券  ，并且生成优惠券使用记录
	 * @param orderMap ,billpaymentRequest
	 * @return null
	 * */
	public int lockAndReleaseCouponStatus(Map<Object, Object> orderMap,BillpaymentRequest billpaymentRequest,
				Integer couponState,List<Integer> couponIdList) throws Exception{
		
		//版本控制 3.5.9 没有使用优惠券的情况  返回成功
		String appversion = billpaymentRequest.getAppversion().replace(".", "");
		if (!StringUtils.isEmpty(appversion) || !"null".equalsIgnoreCase(appversion)) {
			int version = Integer.parseInt(appversion);
			if (version<=359 && billpaymentRequest.getCouponType() == 0) { //标示没有使用优惠券
				return 1;
			}
		}
		
		//锁定使用的优惠券 并且添加使用记录
		int state = 0;
		try {
			
			List<CouponInfo> couponInfoList = new ArrayList<CouponInfo>();
			CouponInfo couponInfo =  null;
			
			if (billpaymentRequest.getCouponType() == 2) {
				//查询该用户获得的平台优惠券 面额
				couponInfo = personalCenterDao.queryUserCoponByCuid(couponIdList.get(0));
			}else {
				Map<Object, Object> couponMap = new HashMap<Object, Object>();
				couponMap.put("list", couponIdList);
				
				couponInfoList = anchorLiveRecordDao.queryFansCouponInfoTwoByCouponId(couponMap);
				if (couponIdList.size()> 0 && couponInfoList.size()<=0) {
					throw new Exception("使用优惠券异常,未找到优惠券信息");
				}
			}
			
			Map<Object, Object> editCouponMap = new HashMap<Object, Object>();
			editCouponMap.put("user_status", couponState);
			editCouponMap.put("bid", orderMap.get("bid"));
			editCouponMap.put("lock_time", new Date());
			editCouponMap.put("order_type", "01");
			if (billpaymentRequest.getCouponType() == 2) {
				editCouponMap.put("cdid", couponInfo.getCdid());
			}else {
				editCouponMap.put("list", couponIdList);
			}
			log.info(billpaymentRequest.getCouponType());
			editCouponMap.put("couponType", billpaymentRequest.getCouponType());
			//标记优惠券的相关使用状态
			int lockCouponResult = billDao.updateOrderCouponStatus(editCouponMap);
			
			//新增优惠券对应的使用记录
			if (lockCouponResult>0) {
				Map<Object, Object> useCouponRecordMap = new HashMap<Object, Object>();
				
				List<Map<Object, Object>> recordlist = new ArrayList<Map<Object, Object>>();
				if (couponInfoList.size()>0 || couponInfo !=null) {
					
					if (billpaymentRequest.getCouponType() == 2) {
						useCouponRecordMap.put("ctype", "2");
						useCouponRecordMap.put("bid", orderMap.get("bid"));
						useCouponRecordMap.put("cuser", couponInfo.getDenomination());//优惠券支付金额
						useCouponRecordMap.put("serial", couponInfo.getSerial());//优惠券序列码
						useCouponRecordMap.put("cdenom", couponInfo.getDenomination());//优惠券面额
						useCouponRecordMap.put("cdid", couponInfo.getCdid());//优惠券ID
						useCouponRecordMap.put("order_type", "01");
						recordlist.add(useCouponRecordMap);
					}else {
						
						for (int i = 0; i < couponInfoList.size(); i++) {
							CouponInfo info = couponInfoList.get(i);
							Map<Object, Object> useCouponRecordMap2 = new HashMap<Object, Object>();
							
							if (billpaymentRequest.getCouponType() == 0 || billpaymentRequest.getCouponType() == 3 || billpaymentRequest.getCouponType() == 1) {//如果优惠券类型 是1，3 标示是平台券 否则是商户券
								useCouponRecordMap2.put("ctype", "1");
							}else if (billpaymentRequest.getCouponType() == 5 ) {
								useCouponRecordMap2.put("ctype", billpaymentRequest.getCouponType());
							}
							
							useCouponRecordMap2.put("bid", orderMap.get("bid"));
							useCouponRecordMap2.put("cuser", info.getDenomination());//优惠券支付金额
							useCouponRecordMap2.put("serial", info.getSerial());//优惠券序列码
							useCouponRecordMap2.put("cdenom", info.getDenomination());//优惠券面额
							useCouponRecordMap2.put("cdid", info.getCdid());//优惠券ID
							useCouponRecordMap2.put("order_type", "01");
							recordlist.add(useCouponRecordMap2);
						}
					}
					
				}
				Map<Object, Object> recMap = new HashMap<Object, Object>();
				recMap.put("list", recordlist);
				
				int insertCoupon = billDao.insertBillCouponRecord(recMap);
				if (insertCoupon>0) {
					state = 1;
				}
			}
		} catch (Exception e) {
			log.info("锁定优惠券过程异常："+billpaymentRequest.getCouponId());
			e.printStackTrace();
			throw new Exception("使用优惠券异常,未查找到优惠券信息");
		}
		return state;
		
	}
	
	/**
	 * 描述：初始化下单结算需要返回的参数
	 * @param billpaymentRequest  sellerMap
	 * @return Map<Object, Object> 
	 * */
	public Map<Object, Object> createOrderResultMap(BillpaymentRequest billpaymentRequest,Map<Object, Object> orderMap,
			Map<Object, Object> orderOtherMap,String uid,Seller seller) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			//商户信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverMap!=null && liverMap.size()>0) {
				map.put("genussellerid", liverMap.get("genussellerid")==null?seller.getSellerid():liverMap.get("genussellerid"));
				map.put("jointid", liverMap.get("jointid")==null?seller.getJointid():liverMap.get("jointid"));
				map.put("genusname", liverMap.get("genusname")==null?seller.getSellername():liverMap.get("genusname"));
				map.put("corporate", liverMap.get("corporate")==null?seller.getCorporate():liverMap.get("corporate"));
				map.put("consume_corporate", liverMap.get("corporate")==null?seller.getCorporate():liverMap.get("corporate"));
				map.put("consume_jointid", liverMap.get("jointid")==null?seller.getJointid():liverMap.get("jointid"));
				map.put("phoneid", liverMap.get("phone"));
			}
			map.put("bid", this.createOrderNumber());
			//美食订单验单码 取出一个五位验单码  在前面加1 标示是美食订单
			map.put("codeid", "1"+this.getRedisSerial(billpaymentRequest.getSellerId().intValue()));
			map.put("sellername", orderMap.get("sellername"));
			map.put("sellerid", orderMap.get("sellerid"));
			map.put("aid", orderOtherMap.get("aid"));	
			map.put("fullname", orderOtherMap.get("fullname"));
			map.put("area", orderMap.get("area"));
			
			//查询用户信息
			Map<Object, Object> liverInfoMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverInfoMap!=null) {
				map.put("nname", liverInfoMap.get("nname")==null?"":liverInfoMap.get("nname"));
				map.put("uid_mb_ecno", liverInfoMap.get("regtype")==null?"":(liverInfoMap.get("regtype").toString().equals("22")?liverInfoMap.get("mb_ecno"):""));
			}else {
				log.info("获取用户基本信息失败"+uid);
				throw new Exception("获取用户基本信息失败"+uid);
			}
			map.put("money", billpaymentRequest.getAmount());
			map.put("payment", orderMap.get("payAmount"));
			map.put("sdate", DateUtil.format(new Date()));
			map.put("status", 0);
			//-----商家关联
			map.put("zoneid", orderMap.get("zoneid"));
			map.put("baseagio", orderMap.get("agio"));
			map.put("jointid", orderOtherMap.get("jointid"));
			map.put("uid", billpaymentRequest.getUid());
			if (orderOtherMap.get("xmerUid")!=null) {
				map.put("xmer_uid", Integer.parseInt(orderOtherMap.get("xmerUid").toString()));
			}
			
			//-----优惠券   积分 满减信息
			if (billpaymentRequest.getCouponType()>-1) {
				map.put("coupon_type", billpaymentRequest.getCouponType()==0?1:billpaymentRequest.getCouponType());
			}
			map.put("cuser", orderMap.get("cuser"));//使用优惠券 金额
			map.put("reduction", orderMap.get("paymentSaleAmount"));//下单立减
			map.put("cdenom", orderMap.get("denomination"));//优惠券面额
			map.put("serial", orderMap.get("serial"));//优惠券面额
			map.put("integral", orderMap.get("integral"));
			map.put("full_reduction", orderMap.get("fullMinusAmount"));//满减
			map.put("full_reduction_id", orderMap.get("fullId"));//满减Id
			
			//---------手机类型  app版本
			String systemAppversion = billpaymentRequest.getSystemversion();
			if (!StringUtils.isEmpty(systemAppversion) && systemAppversion.indexOf("android")>0) {
				map.put("phone_type", 1);//'客户端类型\r\n1：安卓\r\n2：Ios\r\n3：Wp\r\n4：其他\r\n',
			}else if(!StringUtils.isEmpty(systemAppversion) && systemAppversion.indexOf("ios")>0){
				map.put("phone_type", 2);
			}else {
				map.put("phone_type", 4);
			}
			map.put("version", billpaymentRequest.getAppversion());//'用户端版本',
			map.put("version_id", 1);//'用户端版本ID',
			map.put("app_serial", 1);//'App序列号',
			
			//---------推荐人信息
			if (billpaymentRequest.getrPhone()!=null) {
				Map<Object, Object> rliverMap = liveUserDao.queryAnchorByPhone(billpaymentRequest.getrPhone());
				if (rliverMap!=null && rliverMap.size()>0) {
					map.put("r_user_id", rliverMap.get("uid"));
					map.put("r_phone", billpaymentRequest.getrPhone());
				}
//				else {
//					map.put("rPhone", billpaymentRequest.getrPhone());
//				}
			}
			
			
		} catch (Exception e) {
			log.info("预生成订单失败,生成订单主体信息失败");
			e.printStackTrace();
			throw new Exception("预生成订单失败");
		}
		
		return map;
	}
	
	/**
	 * 描述：生成平台下单的订单编号
	 * 由日期+六位顺序码组合而成   如 2016-11-21 + 000001 实际单号为  161121000001
	 * @return String
	 * */
	public String createOrderNumber(){
		StringBuffer orderNo = new StringBuffer();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyMMdd");
		orderNo = orderNo.append(sFormat.format(new Date()));
		String orderSeqNo = Constant.XMNIAO_ORDERNO_SEQ;//订单六位顺序码初始值
		
		//根据当前时间作为key 取出当天订单顺序累计数字
		String resultNum = stringRedisTemplate.opsForValue().increment(orderNo.toString(), 1).toString();
		
		orderNo = orderNo.append(orderSeqNo.substring(0, orderSeqNo.length()-resultNum.length())).append(resultNum);
		
		//查询生成的订单编号有无重复的 有则继续生成
//		int result = freshOrderProcessDao.queryBillOrderCountByOrderNo(orderNo.toString());
//		if (result>0) {
//			//根据当前时间作为key 取出当天订单顺序累计数字
//			resultNum = stringRedisTemplate.opsForValue().increment(orderNo.toString(), 1).toString();
//			orderNo = orderNo.append(orderSeqNo.substring(0, orderSeqNo.length()-resultNum.length())).append(resultNum);
//		}
		
		return orderNo.toString();
	}
	
	/**
     * 获取商家下单 的验单序列码  
     * @Title: getRedisSerial 
     * @Description:
     */
    public String getRedisSerial(int sellerid){
    	String existKey=Constant.XMNIAO_SERIAL_SELLER+sellerid;  //serial_seller_+sellerid;
    	String usedKey=Constant.XMNIAO_SERIAL_SELLER_USED+sellerid;    //serial_seller_used_+sellerid;
    	
    	String result = null;
    	if(stringRedisTemplate.opsForList().size(existKey)<10){
    		log.info("redis队列中库存不足...");
    		
            Random random = new Random();
            List<String> usedList = stringRedisTemplate.opsForList().range(usedKey, 0, -1);
            List<String> existList = stringRedisTemplate.opsForList().range(existKey, 0, -1);
            
    		Set<String> numSet = new HashSet<String>();
    		while(true){
    			String rdm= (random.nextInt(99999)%(99999-10000+1) + 10000)+"";
    			//已使用队列中没有 && 剩余队列中没有 才允许添加
    			if(usedList.contains(rdm) || existList.contains(rdm)){
    				continue;
    			}
    			
    			numSet.add(rdm);
    			if(numSet.size()==500){	//一次添500个
    				break;
    			}
    		}
    		String[] s =  numSet.toArray(new String[numSet.size()]);
    		stringRedisTemplate.opsForList().leftPushAll(existKey, s);
    	}
    	result = stringRedisTemplate.opsForList().rightPop(existKey);
    	boolean isExists = stringRedisTemplate.hasKey(usedKey);
    	stringRedisTemplate.opsForList().leftPush(usedKey, result); 
    	if(!isExists){
    		//设置过期时间
    		try {
    			
    			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(date);
            	cal.add(Calendar.DATE, 1);
            	String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
//        		Date now = new Date();
//        		Date tomorrow = DateUtil.calendarDay(now, 1);
//        		Date end = DateUtil.parseDate(DateUtil.getDateTime("yyyy-MM-dd",tomorrow), "yyyy-MM-dd HH:mm:ss");
        		Date endDate = DateUtil.parse(endTime);
        		long expireSeconds = ((endDate.getTime()-new Date().getTime())/1000)+1;
        		stringRedisTemplate.expire(usedKey, expireSeconds, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return result;
    }

    /**
	 * @throws IOException 
	 * 
	* @Title: transMap
	* @Description: 换map类型
	* @return String    返回类型
	* @author
	* @throws
	 */
	public String transMap(Map<String,String> transmap,String urlKey ) throws IOException{
		String key = propertiesUtil.getValue("payFoodKey", "conf_common.properties");
		//签名
		transmap.put("sign", Signature.sign(transmap,key));	
		
		String url = "";
		
		//用于区分测试环境与生存环境的访问路径   测试环境为兼容微信WAP调用测试  如果是测试环境 就进入open逻辑  生存环境则直接进入无需判断
		String paySwitch = propertiesUtil.getValue("payBirdurl_weixinWap_switch", "conf_common.properties");
		if (paySwitch.equals("open")) {
			if (null!=transmap.get("paymentType") && transmap.get("paymentType").toString().equals("1000013")) {
				url =propertiesUtil.getValue("payBirdurl_weixinWap_url", "conf_common.properties");
			}else {
				url = propertiesUtil.getValue(urlKey, "conf_common.properties");
			}
		}else {
			url = propertiesUtil.getValue(urlKey, "conf_common.properties");
		}
		
		String request = "";
		for(Entry<String, String> entry : transmap.entrySet()){
		    request += "&" + entry.getKey() + "=" + entry.getValue();
		}
		request = request.substring(1, request.length());
		System.out.println(url + "?" + request);
		String payurl=url + "?" + request;
		
		return payurl;
	}
	


}