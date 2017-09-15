package com.xmniao.xmn.core.sellerPackage.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveFansOrderUseCouponRequest;
import com.xmniao.xmn.core.common.request.live.LiveQueryCouponRequest;
import com.xmniao.xmn.core.common.request.live.LiveRecordFansCouponRequest;
import com.xmniao.xmn.core.common.request.sellerPackage.SellerPackageBuyRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PresellOrderDao;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.live.service.PresellOrderService;
import com.xmniao.xmn.core.live.service.UserPayBirdCoinService;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageOrderDao;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackage;
import com.xmniao.xmn.core.thrift.business.java.SellerPackageService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.service.SellerService;
import com.xmniao.xmn.core.thrift.ResponseData;

/**
 * 描述： 用户或主播进入房间
 * @author yhl
 * 2016年8月15日11:34:14
 * */
@Service
public class SellerPackageBuyService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerPackageBuyService.class);
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil  propertiesUtil;
	
	@Autowired
	private UserPayBirdCoinService userPayBirdCoinService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired 
	private PresellOrderDao presellOrderDao;
	
	@Autowired
	private BillDao billDao;
	
	/**
	 * 直播钱包 ：支付服务接口
	 */
	@Resource(name = "liveWalletServiceClient")
	private ThriftClientProxy liveWalletServiceClient;
	
	/**
	 * 寻蜜鸟钱包： 支付服务
	 * */
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	@Autowired
	private ThriftUtil thriftUtil;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private SellerPackageDao sellerPackageDao;
	
	@Autowired
	private SellerPackageOrderDao sellerPackageOrderDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private PersonalCenterService personalCenterService;
	
	@Autowired
	private PresellOrderService presellOrderService;
	
	/**
	 * 描述：购买套餐 - 下单操作
	 * @param SellerPackageBuyRequest
	 * @return Object
	 * */
	public Object buySellerPackage(SellerPackageBuyRequest sellerPackageBuyRequest){
//		String uid = sellerPackageBuyRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(sellerPackageBuyRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
//		检查预售粉丝券是否已经过期  根据通告ID检查
		MapResponse response = null;
		try {
			//查询该套餐的基本信息
			SellerPackage sellerPackage = sellerPackageDao.selectByPrimaryKey(sellerPackageBuyRequest.getPackageId());
			if (sellerPackage==null) {
				log.info("商家套餐不存在或已下架:"+sellerPackageBuyRequest.getPackageId());
				return new MapResponse(ResponseCode.FAILURE, "商家套餐不存在或已下架");
			}
			
			Seller seller = sellerService.querySellerBySellerid(Long.valueOf(sellerPackage.getSellerid()));
			if (seller== null ) {
				log.info("商家不存在或已下架:"+sellerPackageBuyRequest.getPackageId());
				return new MapResponse(ResponseCode.FAILURE, "商家不存在或已下架");
			}
			List<Integer> couponIdList = new ArrayList<Integer>();
			if (sellerPackage!=null && sellerPackage.getSellerid()!=null) {
				
				//最终返回结果集的总集合  
				Map<Object, Object> map = new HashMap<Object, Object>();
				//检查库存 
				boolean stock = this.validateFansCouponStaock(sellerPackageBuyRequest, sellerPackage);
				try {
					
					if (!StringUtils.isEmpty(sellerPackageBuyRequest.getCouponId())) {
						String [] couponId = sellerPackageBuyRequest.getCouponId().split(",");
						if (couponId.length>0) {
							for (int i = 0; i < couponId.length; i++) {
								couponIdList.add(Integer.parseInt(couponId[i]));
							}
						}
					}
					
					if (stock == false) {//库存不足的时候
						sellerPackageBuyRequest.setQuantity(sellerPackage.getStock());
						map = this.calculatedFanCouponPrice(sellerPackageBuyRequest,sellerPackage,uid,seller,couponIdList);
						//库存不足的时候，把库存返回为0
						map.put("stock", 0);
					}else {//库存富余的时候
						map = this.calculatedFanCouponPrice(sellerPackageBuyRequest,sellerPackage,uid,seller,couponIdList);
					}
					
					//初始化使用的 优惠券 list 
					List<CouponInfo> couponInfoList = (List<CouponInfo>) map.get("couponInfoList");
					
					map.put("uid", uid);
					map.put("sellername", seller.getSellername());
					
					//是否创建订单 大于0  创建订单
					if (sellerPackageBuyRequest.getIsCreate()>0) {
						
						//发起支付的时候 组装参数
						Map<String, String> paymentMap = new HashMap<String, String>();
						//组装时优先初始化订单发起验单时公共信息
						Map<Object, Object> typeMap = new HashMap<Object, Object>();
						typeMap.put("isQuota", "false");
						typeMap.put("isLiveCoin", "false");
						typeMap.put("isBalance", "false");
						
						paymentMap.put("orderName", sellerPackage.getTitle());
						
						//返回订单结果
						Map<Object, Object> resultOrderMap = new HashMap<Object, Object>();
						
						//判断是否有被多次提交
						String sellerPackageOrderkey="seller_package_key_"+uid;
						//当单个用户购买预售粉丝券，操作过于频繁，等待
						long concurrenceNum=stringRedisTemplate.opsForValue().increment(sellerPackageOrderkey, 1);
						if (concurrenceNum==1) {
							stringRedisTemplate.expire(sellerPackageOrderkey,2, TimeUnit.MINUTES);
						}
						try {
							log.info("购买商户套餐并发"+sellerPackageOrderkey+",uid="+uid);
							if(concurrenceNum>1){
								return new BaseResponse(ResponseCode.FAILURE, "您有订单正在处理,请稍后重新提交!");
							}
							
							Map<Object, Object> userMap = liveUserDao.queryLiverInfoByUid(Integer.valueOf(uid));
							if (userMap==null && userMap.size()<=0) {
								return new BaseResponse(ResponseCode.FAILURE, "当前用户不存在!");
							}
							
							//获取鸟币要支付的总额
							BigDecimal orderCoinAmount = new BigDecimal(map.get("orderCoinAmount").toString());
							//判断使用佣金 和 赠送金额支付  值   
							BigDecimal orderBalanceAmount = new BigDecimal(map.get("orderBalanceAmount").toString());
							
							//优惠券可以完全支付的情况下  不需要走支付接口 业务服务发起
							if (orderCoinAmount.compareTo(new BigDecimal(0))<=0 || orderBalanceAmount.compareTo(new BigDecimal(0))<=0 ) {
								
								BigDecimal orderCoinOrderAmount = new BigDecimal(map.get("orderCoinOrderAmount").toString());//订单鸟币原始金额
								BigDecimal orderBalanceOrderAmount = new BigDecimal(map.get("orderBalanceOrderAmount").toString());;//订单现金原始金额
								
								//创建订单
								try {
									resultOrderMap = this.insertSellerPackageOrder(sellerPackageBuyRequest,map,seller,sellerPackage,userMap,0);
								} catch (Exception e) {
									log.info("创建订单异常");
									e.printStackTrace();
									return new BaseResponse(ResponseCode.FAILURE, "生成订单异常,请重试!");
								}
								//锁定优惠券
								int state = 0;
								if (resultOrderMap!=null && resultOrderMap.size()>0) {
									try {
										state = this.updateCouponState(sellerPackageBuyRequest, paymentMap, uid, resultOrderMap, typeMap,couponInfoList,couponIdList);
									} catch (Exception e) {
										log.info("");
										return new MapResponse(ResponseCode.FAILURE, e.getMessage());
									}
								}
								//更新订单
								Map<String, String> editOrderMap = new HashMap<String, String>();
								editOrderMap.put("orderNo", resultOrderMap.get("order_no").toString());
								editOrderMap.put("uid", uid);
								editOrderMap.put("status", "1");
								editOrderMap.put("payType", "1000011");
								editOrderMap.put("cash", "0.00");
								editOrderMap.put("balance", "0.00");
								editOrderMap.put("commision", "0.00");
								editOrderMap.put("zbalance", "0.00");
								editOrderMap.put("beans", "0.00");
								editOrderMap.put("sellerCoin", "0.00");
								editOrderMap.put("base", map.get("platformPaybirdCoin").toString());
								editOrderMap.put("discounts", seller.getAgio().toString());
								if (orderCoinAmount.compareTo(new BigDecimal(0))<=0 && orderBalanceAmount.compareTo(new BigDecimal(0))<=0) {
									//取价格高的订单总额
									if (orderCoinOrderAmount.compareTo(orderBalanceOrderAmount)<0) {
										editOrderMap.put("totalAmount",orderBalanceOrderAmount.toString());
										editOrderMap.put("deduction", "1");
										
									}else {
										editOrderMap.put("totalAmount",orderCoinOrderAmount.toString());
										editOrderMap.put("deduction", "0");
									}
								}else {
									if (orderCoinAmount.compareTo(new BigDecimal(0))<=0 && orderBalanceAmount.compareTo(new BigDecimal(0))>0) {
										editOrderMap.put("totalAmount",orderCoinOrderAmount.toString() );
										editOrderMap.put("deduction", "0");
									}else if(orderCoinAmount.compareTo(new BigDecimal(0))>0 && orderBalanceAmount.compareTo(new BigDecimal(0))<=0){
										editOrderMap.put("totalAmount",orderBalanceOrderAmount.toString());
										editOrderMap.put("deduction", "1");
									}
								}
								
								Map<Object, Object> orderResultMap = new HashMap<Object, Object>();
								int updateState = this.updateSellerPackageOrder(editOrderMap);
								if (updateState== 0){
									orderResultMap.put("orderNo", editOrderMap.get("orderNo"));
									orderResultMap.put("state", 200);
									orderResultMap.put("info", "购买成功");
								}else {
									orderResultMap.put("state", 500);
									orderResultMap.put("info", "系统异常");
								}
								return orderResultMap;
							}
							
							//鸟币支付金额与现金支付金额
							BigDecimal paymentAmount = new BigDecimal(0);
							
							//如果选择了储值卡
							if (sellerPackageBuyRequest.getIsSellerCard() > 0) {
								//获取当前用户的储值卡 额度
								BigDecimal sellerCardQuota = new BigDecimal(map.get("sellerCardMoney").toString()) ;
								//如果
								if (sellerCardQuota.compareTo(new BigDecimal(0)) >0) {
									
									if (sellerPackageBuyRequest.getIsSellerCard() > 0 && sellerPackageBuyRequest.getIsFansCard() == 0
											&& sellerPackageBuyRequest.getIsBalance() == 0 && (null==sellerPackageBuyRequest.getPayType() || sellerPackageBuyRequest.getPayType().equals(""))) {
										if (sellerCardQuota.compareTo(orderCoinAmount)<0) {
											return new MapResponse(ResponseCode.FAILURE, "鸟粉专享卡余额不足!");
										}
									}
									//如果 订单金额减去专享卡 有剩余 则累计专享卡金额
									BigDecimal orderCoin_ = orderCoinAmount.subtract(sellerCardQuota);
									if(orderCoin_.compareTo(new BigDecimal(0))>0){
										paymentAmount = paymentAmount.add(sellerCardQuota);
										orderCoinAmount = orderCoinAmount.subtract(sellerCardQuota);
									}else {
										paymentAmount = paymentAmount.add(orderCoinAmount);
										orderCoinAmount = new BigDecimal(0);
									}
									paymentMap.put("amount", paymentAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									
									//验证支付参数组装
									typeMap.put("isQuota", "true");//是否使用储值卡 必填
									paymentMap.put("consumeSellerId", seller.getSellerid().toString());//消费商家ID
									paymentMap.put("consumeSellerName", seller.getSellername().toString());//名称
									paymentMap.put("sellerId", map.get("sellerCardId").toString()); //储值卡所属商家
									paymentMap.put("sellerName", map.get("sellerCardName").toString()); //储值卡所属商家
									paymentMap.put("sellerType", map.get("sellerCardType").toString()); //储值卡所属商家类型
									paymentMap.put("base", map.get("platformPaybirdCoin").toString());
									paymentMap.put("discount", new BigDecimal(map.get("sellerAgio").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString() );
									
								}else {
									log.info("选择支付方式错误,当前储值卡不存在或者余额不足");
									return new BaseResponse(ResponseCode.FAILURE, "当前专享卡余额不足,请重新现在支付方式!");
								}
							}
							
							//如果是鸟币支付
							if (sellerPackageBuyRequest.getIsFansCard() > 0) {
								
								//是否开启鸟币支付
								if (map.get("restrictive").toString().equals("1")) {
									
									if (orderCoinAmount.compareTo(new BigDecimal(0))>0) {
										BigDecimal balanceCoinMoney = new BigDecimal(map.get("zbalance").toString());
										if (sellerPackageBuyRequest.getIsFansCard() > 0 && sellerPackageBuyRequest.getIsBalance() == 0 
												&& (null==sellerPackageBuyRequest.getPayType() || sellerPackageBuyRequest.getPayType().equals(""))) {
											if (balanceCoinMoney.compareTo(orderCoinAmount)<0) {
												return new MapResponse(ResponseCode.FAILURE, "鸟粉卡余额不足!");
											}
										}
										
										//如果 订单金额减去专享卡 有剩余 则累计专享卡金额
										BigDecimal orderCoin_ = orderCoinAmount.subtract(balanceCoinMoney);
										if(orderCoin_.compareTo(new BigDecimal(0))>0){
											paymentAmount = paymentAmount.add(balanceCoinMoney);
											orderCoinAmount = orderCoinAmount.subtract(balanceCoinMoney);
										}else {
											paymentAmount = paymentAmount.add(orderCoinAmount);
											orderCoinAmount = new BigDecimal(0);
										}
										paymentMap.put("amount", paymentAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										typeMap.put("isLiveCoin", "true");//是否使用余额 必须
										paymentMap.put("base", map.get("platformPaybirdCoin").toString());
										paymentMap.put("discount", new BigDecimal(map.get("sellerAgio").toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString() );
										
									}else {
										log.info("选择支付方式错误,请重新选择支付方式!："+seller.getSellerid());
										return new BaseResponse(ResponseCode.FAILURE, "选择支付方式错误,请重新现在支付方式!");
									}
									
								}else {
									log.info("当前商家不支持鸟粉卡支付："+seller.getSellerid());
									return new BaseResponse(ResponseCode.FAILURE, "商家不支持鸟粉卡支付,请重新现在支付方式!");
								}
							}
							
							//如果选择了鸟币支付的任何的一种支付方式  则必须将折扣基数 和 商家折扣传到支付接口
							if ((sellerPackageBuyRequest.getIsSellerCard()>0 || sellerPackageBuyRequest.getIsFansCard()>0) 
									&& (sellerPackageBuyRequest.getIsBalance() > 0 || (null!=sellerPackageBuyRequest.getPayType() && !sellerPackageBuyRequest.getPayType().equals("")))  ) {
								//计算完鸟币后  如果还没有支付完成的 就返回换算 余额要支付多少钱
								if (orderCoinAmount.compareTo(new BigDecimal(0))>=0) {
									orderBalanceAmount = orderCoinAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
									paymentMap.put("amount", paymentAmount.add(orderBalanceAmount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}
							}
							
							
							if (sellerPackageBuyRequest.getIsSellerCard()>0 || sellerPackageBuyRequest.getIsFansCard()>0) {
								paymentMap.put("amount", map.get("orderCoinOrderAmount").toString());
							}else {
								paymentMap.put("amount", map.get("orderBalanceOrderAmount").toString());
							}
							
							//如果选择了   余额支付  
							if (sellerPackageBuyRequest.getIsBalance() > 0) {
								log.info("纯余额的时候的orderBalanceAmount "+orderBalanceAmount);
								if (orderBalanceAmount.compareTo(new BigDecimal(0))>0) {
									BigDecimal balanceMoney = new BigDecimal(map.get("amountMoney").toString());
									
									if (balanceMoney.compareTo(new BigDecimal(0)) > 0) {
										if (sellerPackageBuyRequest.getIsBalance() > 0 && (null==sellerPackageBuyRequest.getPayType() || sellerPackageBuyRequest.getPayType().equals(""))) {
											if (balanceMoney.compareTo(orderBalanceAmount)<0) {
												return new MapResponse(ResponseCode.FAILURE, "钱包余额不足!");
											}
										}
										typeMap.put("isBalance", "true");
									}else {
										log.info("当前钱包余额不足，UID="+uid);
										return new BaseResponse(ResponseCode.FAILURE, "钱包余额不足,请选择其他支付方式");
									}
								}else {
									log.info("选择支付方式错误，请重新选择UID="+uid);
									return new BaseResponse(ResponseCode.FAILURE, "选择支付方式错误，请重新选择");
								}
							}
							
							//新增订单
							try {
								resultOrderMap = this.insertSellerPackageOrder(sellerPackageBuyRequest,map,seller,sellerPackage,userMap,0);
							} catch (Exception e) {
								log.info("创建订单异常");
								e.printStackTrace();
								return new BaseResponse(ResponseCode.FAILURE, "生成订单异常,请重试!");
							}
							
							if (null != sellerPackageBuyRequest.getPayType() && sellerPackageBuyRequest.getPayType()!=0) {
								
								//支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:微信公众号支付，1000000：钱包支付 1000015 鸟币支付支付方式
								//如果是微信公众号支付  判断 并且传值 wxuid
								if (sellerPackageBuyRequest.getPayType() == 1000013 || sellerPackageBuyRequest.getPayType() == 1000025) {
									paymentMap.put("wxuid", sellerPackageBuyRequest.getOpenId());
								}
								if (sellerPackageBuyRequest.getPayType() == 1000014 || sellerPackageBuyRequest.getPayType() == 1000023) {
									paymentMap.put("returnUrl", sellerPackageBuyRequest.getReturnUrl()+resultOrderMap.get("order_no"));
								}
							}
							
							//发起支付
							return this.paymentUrl(sellerPackageBuyRequest, paymentMap, uid, resultOrderMap, typeMap,couponInfoList,couponIdList);
							
						} catch (Exception e) {
							log.info("创建订单异常");
							e.printStackTrace();
							return new BaseResponse(ResponseCode.FAILURE, "生成订单异常,请重试!");
						}finally{
							stringRedisTemplate.delete(sellerPackageOrderkey);
						}
					}
				} catch (Exception e) {
					log.info("计算价格出现异常");
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "操作异常");
					
				}
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				response.setResponse(map);
				return response;
				
			}else {
				log.info("获取套餐异常: "+sellerPackageBuyRequest.getPackageId());
				return new MapResponse(ResponseCode.FAILURE, "该套餐不存在或已下架");
			}
			
		} catch (Exception e) {
			log.info("系统异常："+sellerPackageBuyRequest.getPackageId());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "系统失败");
		}
	}
	
	/**
	 * 锁定优惠券信息  / 插入优惠券使用记录
	 * @param 
	 * @return int
	 * */
	public int updateCouponState(SellerPackageBuyRequest sellerPackageBuyRequest,Map<String, String> paymentMap ,
			String uid ,Map<Object, Object> map ,Map<Object, Object> typeMap, List<CouponInfo> couponInfoList, List<Integer> couponIdList )throws Exception{
		int state = 0;
		if (map!=null && map.size()>0) {
			if(couponIdList.size()>0 ){
				paymentMap.put("preferential", map.get("cuser").toString());//设置订单预售抵用券的支付金额 发起支付时使用
				
				//锁定抵用券数据状态
				int lockNum=0;
				try {
					Map<Object,Object> param=new HashMap<>();
					param.put("list", couponIdList);
					param.put("lockTime", DateUtil.format(new Date(), DateUtil.defaultSimpleFormater));
					param.put("userStatus", 1);
					param.put("bid", map.get("order_no"));
					lockNum=presellOrderDao.lockAndUnlockCouponTicket(param);
				} catch (Exception e) {
					log.error("锁定或解除抵用券异常");
					e.printStackTrace();
					throw new Exception("锁定或解除抵用券异常异常，请联系管理员");
				}
				
				if(lockNum<=0){
					log.info("锁定抵用券失败，条数="+lockNum);
					throw new Exception("使用抵用券异常");
				}
				
				//新增优惠券对应的使用记录
				if (lockNum>0) {
					
					List<Map<Object, Object>> recordlist = new ArrayList<Map<Object, Object>>();
					if (couponIdList.size()>0) {
						
						for (int i = 0; i < couponIdList.size(); i++) {
							CouponInfo info = couponInfoList.get(i);
							Map<Object, Object> useCouponRecordMap2 = new HashMap<Object, Object>();
							useCouponRecordMap2.put("ctype", sellerPackageBuyRequest.getCouponType());
							useCouponRecordMap2.put("bid", map.get("order_no"));
							useCouponRecordMap2.put("cuser", info.getDenomination());//优惠券支付金额
							useCouponRecordMap2.put("serial", info.getSerial());//优惠券序列码
							useCouponRecordMap2.put("cdenom", info.getDenomination());//优惠券面额
							useCouponRecordMap2.put("cdid", info.getCdid());//优惠券ID
							recordlist.add(useCouponRecordMap2);
						}
						
					}
					Map<Object, Object> recMap = new HashMap<Object, Object>();
					recMap.put("list", recordlist);
					
					billDao.insertBillCouponRecord(recMap);
				}
				
				state = 1;
			}
		}
		return state;
	}
	
	/**
	 * 描述：组合套餐购买 支付请求参数
	 * @param Map<Object Object> map
	 * @return
	 * */
	public Object paymentUrl(SellerPackageBuyRequest sellerPackageBuyRequest,Map<String, String> paymentMap ,
			String uid ,Map<Object, Object> map ,Map<Object, Object> typeMap, List<CouponInfo> couponInfoList, List<Integer> couponIdList ) throws Exception {
		
		//更新套餐总数量
		Map<Object, Object> paramPackageMap = new HashMap<Object, Object>();
		paramPackageMap.put("nums", sellerPackageBuyRequest.getQuantity());
		paramPackageMap.put("id", sellerPackageBuyRequest.getPackageId());
		paramPackageMap.put("buys", 1);//标示买套餐 库存要扣减，销量要加
		
		int fansNum=sellerPackageDao.modifySellerPackageInfo(paramPackageMap);
		if(fansNum<=0){
			log.info("更新套餐库存失败："+sellerPackageBuyRequest.getPackageId());
			return new BaseResponse(ResponseCode.FAILURE, "更新套餐库存失败");
		}
		
		//订单编号
		paymentMap.put("orderNumber",map.get("order_no").toString());
		//用户UID
		paymentMap.put("userId", uid);
		//获取每个套餐每个店铺的单价金额
		
		// 订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;
		// 003:业务系统-积分商品订单；004:业务系统-物料订单；005:业务系统-直播鸟币购买订单
		paymentMap.put("source", "011");
		//订单类型，目前传固定值2
		paymentMap.put("orderType", "2");	
		//订单标题
		paymentMap.put("orderName",paymentMap.get("orderName"));
		//查看是否有优惠金额
		if (map.get("cuser")!=null && new BigDecimal(map.get("cuser").toString()).compareTo(new BigDecimal(0))>0) {
			paymentMap.put("preferential", map.get("cuser").toString());//是否使用储值卡 必填
		}
		
		//------------------------请求支付接口  固定  参数
		//以下三个参数可选，值为0时可不传，
		//若paymentType为1000000时 profit + commision + zbalance + integral 必须等于 amount
		//若混合支付，paymentType传第三方支付类型，且 amount 必须大于 profit + commision + zbalance + integral
		//所有传入金额必须格式化为2位小数
		String paySwitch = propertiesUtil.getValue("payBirdurl_weixinWap_switch", "conf_live.properties");
		String signUrl = "";
		if (paySwitch.equals("open")) {
			if (null != sellerPackageBuyRequest.getPayType() &&sellerPackageBuyRequest.getPayType().toString().equals("1000013")) {
				signUrl =propertiesUtil.getValue("payBirdurl_sq2", "conf_live.properties");
			}else {
				signUrl =propertiesUtil.getValue("payBirdurl2", "conf_live.properties");
			}
		}else {
			signUrl =propertiesUtil.getValue("payBirdurl2", "conf_live.properties");
		}
		String url = userPayBirdCoinService.transMap2(paymentMap,signUrl);
		
		if (typeMap!=null) {
			StringBuffer sb = new StringBuffer();
			sb = sb.append("&").append("isQuota=").append(typeMap.get("isQuota")).append("&").append("isLiveCoin=")
					.append(typeMap.get("isLiveCoin")).append("&").append("isBalance=").append(typeMap.get("isBalance"));
			if (null != sellerPackageBuyRequest.getPayType() && !sellerPackageBuyRequest.getPayType().equals("")) {
				sb = sb.append("&").append("paymentType=").append(sellerPackageBuyRequest.getPayType());//支付类型  必须
			}
			url = url+sb.toString();
		}
		if (url.indexOf("sellerName")>0) {//如果包含该字符   取出该字符的参数 做编码
			url=url.replace(paymentMap.get("sellerName").toString(), URLEncoder.encode(paymentMap.get("sellerName").toString(),"UTF-8")).replaceAll(" ", "%20");
		}
		if (url.indexOf("consumeSellerName")>0) {//如果包含该字符   取出该字符的参数 做编码
			url=url.replace(paymentMap.get("consumeSellerName"), URLEncoder.encode(paymentMap.get("consumeSellerName").toString(),"UTF-8")).replaceAll(" ", "%20");
		}
		if (url.indexOf("orderName")>0) {//如果包含该字符   取出该字符的参数 做编码
			url=url.replace(paymentMap.get("orderName"), URLEncoder.encode(paymentMap.get("orderName").toString(),"UTF-8")).replaceAll(" ", "%20");
		}
		
		//开始调用支付接口------------------------------------------------------------------
		log.info("开始调用支付接口URL："+url);
		paymentMap.put("url", url);
		
		String result = userPayBirdCoinService.payBirdCoin(paymentMap).toString();
		return JSONObject.parseObject(result);
		
	}
	
	/**
	 * 更新套餐订单状态
	 * */
	public int updateSellerPackageOrder(Map<String, String> orderParam) throws Exception{
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("SellerPackageService");
			SellerPackageService.Client  orderClient= new SellerPackageService.Client(tMultiplexedProtocol); 
			thriftBusinessUtil.openTransport();
			ResponseData resdaData = orderClient.updateSellerPackageOrder(orderParam);
			if (resdaData.getState()==0) {
				return resdaData.getState();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("更新订单异常");
		}
		return 1;
	}
	
	/**
	 * 描述：校验套餐售卖库存
	 * @param SellerPackageBuyRequest   , SellerPackage
	 * @return boolean 
	 * */
	public boolean validateFansCouponStaock(SellerPackageBuyRequest sellerPackageBuyRequest,SellerPackage sellerPackage){
		boolean flag = false;
		//购买量
		int quantity = sellerPackageBuyRequest.getQuantity();
		//库存量
		int stock = sellerPackage.getStock();
		//库存量必须大于0  并且必须大于等于 购买量
		if (stock>0 && stock>=quantity) {
			flag = true;
		}else {
			return flag;
		}
		return flag;
	}
	
	/**
	 * 描述：计算购买套餐的基本金额 
	 * @param SellerPackageBuyRequest ,SellerPackage , uid
	 * @return Map<Object, Object>  
	 * @throws Exception 
	 * */
	public Map<Object, Object> calculatedFanCouponPrice(SellerPackageBuyRequest sellerPackageBuyRequest ,SellerPackage sellerPackage,
			String uid,Seller seller,List<Integer> couponIdList) throws Exception{
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			resultMap.put("uid", uid);
			// 传过来的 购买数量    初始值为1
			resultMap.put("quantity", sellerPackageBuyRequest.getQuantity());//购买量
			resultMap.put("title", sellerPackage.getTitle());//名称
			
			resultMap.put("sellingPrice", sellerPackage.getSellingPrice());//现金价格
			resultMap.put("sellingCoinPrice", sellerPackage.getSellingCoinPrice());//鸟币价格
			resultMap.put("originalPrice", sellerPackage.getOriginalPrice());//原始价格
			resultMap.put("useStartTime", sellerPackage.getUseStartTime());//使用开始时间
			resultMap.put("useEndTime", sellerPackage.getUseEndTime());//结束时间
			
			resultMap.put("stock", sellerPackage.getStock());//剩余数量
			resultMap.put("sales", sellerPackage.getSales());//销售总量
			resultMap.put("useCouponAmount", 0);//初始化  返回使用抵用券金额
			resultMap.put("useCouponId", "-1");//初始化  返回使用抵用券ID
			
			resultMap.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));
			
//			resultMap.put("birdCoinExpression", propertiesUtil.getValue("platformPaybirdCoinExpression", "conf_common.properties").replace("SellerAgio", seller.getAgio().toString()));//鸟币计算公式
			resultMap.put("birdCoinExpression", propertiesUtil.getValue("sellerPackagePayMoneyExpression", "conf_common.properties"));//鸟币计算公式
			resultMap.put("moneyExpression", propertiesUtil.getValue("sellerPackagePayMoneyExpression", "conf_common.properties"));//现金计算公式
			
			BigDecimal platformPaybirdCoin = new BigDecimal(propertiesUtil.getValue("platformSellerPackagePaybirdCoin", "conf_common.properties"));// 鸟币/现金 换算系数
			resultMap.put("platformPaybirdCoin", platformPaybirdCoin);
			resultMap.put("sellerAgio", seller.getAgio());
			
			//查询商铺是否开启储值卡
			resultMap.put("sellerCardMoney", 0);//设置会员在上家储值卡基础余额
			resultMap.put("isSellerCardPay", 0);//设置商家是否开启储值卡支付 0 否
			resultMap.put("birdCoinDesc", "");//设置商家是否开启储值卡支付 0 否
			
			resultMap.put("couponInfoList", new ArrayList<>());
			
			//获取商家logo
			List<SellerPic> sellerPics = sellerService.querySellerPicBySelleridAndType(sellerPackage.getSellerid(),1);
			if (sellerPics.size()>0) {
				resultMap.put("logo", sellerPics.get(0).getUrl());//商家logo
			}else {
				resultMap.put("logo", "");//商家logo
			}
			
			//赠送积分 乘以购买数量 此处为初始化
			resultMap.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));
			resultMap.put("integral", 0);
			
			//鸟币价格 
			BigDecimal packageCoinMoney = sellerPackage.getSellingCoinPrice();//.multiply(seller.getAgio());
			//现金价格
			BigDecimal packageBalanceMoney = sellerPackage.getSellingPrice();
			
			BigDecimal orderCoinOrderAmount = packageCoinMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
			BigDecimal orderBalanceOrderAmount = packageBalanceMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
			resultMap.put("orderCoinOrderAmount", orderCoinOrderAmount);//订单鸟币原始金额
			resultMap.put("orderBalanceOrderAmount", orderBalanceOrderAmount);//订单现金原始金额
			
			resultMap.put("orderCoinAmount", packageCoinMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity())));//用于返回鸟币计算后的金额
			resultMap.put("orderBalanceAmount", packageBalanceMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity())));//用于返回现金计算后的金额
			
			//计算金额  结果到最后  鸟币 和 余额需要支付多少
			BigDecimal orderCoinAmount = packageCoinMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
			BigDecimal orderBalanceAmount = packageBalanceMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
			BigDecimal useCouponAmount = new BigDecimal(0);
			StringBuffer  useCouponId = new StringBuffer();
			
			//获取默认抵用券     取ID抵用金额     根据 抵用券判断  具体返回 需要支付的总金额  是多少  (已现金价格计算拉取抵用券)
//			if (sellerPackageBuyRequest.getCouponType()>-1 && !StringUtils.isEmpty(sellerPackageBuyRequest.getCouponId())) {
//				 
//				//标示为选择抵用券  默认设置抵用券 根据当前订单金额查询有无抵用券  获取到最早发放 并且是大面额的   根据订单总金额查询抵用券列表
//				BigDecimal buyAmount = packageBalanceMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
//				resultMap.put("orderAmount", buyAmount);
//				Map<Object, Object> fansListMap = new HashMap<Object, Object>();
//				fansListMap.put("amount", buyAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
//				fansListMap.put("uid", uid);
//				fansListMap.put("source", 4);//标示是购买套餐的抵用券
//				
//				//获取粉丝抵用券列表
//				List<CouponInfo> couponInfoList = anchorLiveRecordDao.queryFansCouponListByAmount(fansListMap);
//				if (couponInfoList.size()>0) {
//					CouponInfo info = new CouponInfo();
//					for (int i = 0; i < couponInfoList.size(); i++) {
//						info  = couponInfoList.get(i);
//						if (info.getIsAvailable() == 0) {
//							//返回需要支付的总额  默认计算 鸟币支付 现金支付价格
//							BigDecimal orderCoinAmount = packageCoinMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
//							BigDecimal orderBalanceAmount = packageBalanceMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity()));
//							//鸟币返回价格
//							if (orderCoinAmount.subtract(info.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
//								resultMap.put("orderCoinAmount", orderCoinAmount.subtract(info.getDenomination()));
//							}else {
//								resultMap.put("denomination", info.getDenomination());
//								resultMap.put("orderCoinAmount", 0);
//							}
//							//现金返回价格
//							if (orderBalanceAmount.subtract(info.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
//								resultMap.put("orderBalanceAmount", orderBalanceAmount.subtract(info.getDenomination()));
//							}else {
//								resultMap.put("orderBalanceAmount",0);
//							}
//							//返回使用的抵用券ID
//							resultMap.put("useCouponId", info.getCdid());
//							resultMap.put("useCouponAmount", info.getDenomination());
//							
//							//计算购买套餐 赠送的抵用券返回金额  如果使用了抵用券 不赠送 设置赠送金额为0
//							resultMap.put("voucherAmount", new BigDecimal(0));
//							break;
//						}
//					}
//				}
//				
//			}
			if(!StringUtils.isEmpty(sellerPackageBuyRequest.getCouponId())){
				//标示有选择预售抵用券
				Map<Object, Object> couponMap = new HashMap<Object, Object>();
//				couponMap.put("cid", sellerPackageBuyRequest.getCouponId());
				couponMap.put("list", couponIdList);
				List<CouponInfo> couponInfoList = anchorLiveRecordDao.queryFansCouponListByCouponId(couponMap);
				resultMap.put("couponInfoList", couponInfoList);
				
				if (couponInfoList.size()>0) {
					
					for (int i = 0; i < couponInfoList.size(); i++) {
						CouponInfo couponInfo = couponInfoList.get(i);
						if (couponInfo!=null && null != couponInfo.getCdid()) {
							
							if (orderCoinAmount.subtract(couponInfo.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
								orderCoinAmount = orderCoinAmount.subtract(couponInfo.getDenomination());
								resultMap.put("orderCoinAmount", orderCoinAmount);
							}else {
								resultMap.put("orderCoinAmount", 0);
								orderCoinAmount = new BigDecimal(0);	
							}
							//
							if (orderBalanceAmount.subtract(couponInfo.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
								orderBalanceAmount = orderBalanceAmount.subtract(couponInfo.getDenomination());
								resultMap.put("orderBalanceAmount", orderBalanceAmount);
							}else {
								resultMap.put("orderBalanceAmount",0);
								orderBalanceAmount = new BigDecimal(0);	
							}
							
							//判断 订单价格 是否都大于0  如果大于0 优惠金额就取优惠券面额 累加
							if (orderCoinAmount.compareTo(new BigDecimal(0))>0 && orderBalanceAmount.compareTo(new BigDecimal(0))>0) {
								useCouponAmount = useCouponAmount.add(couponInfo.getDenomination());
								
								//如果订单价格都小于或等于0  优惠金额取 价格高的金额
							}else if (orderCoinAmount.compareTo(new BigDecimal(0))<=0 && orderBalanceAmount.compareTo(new BigDecimal(0))<=0) {
								//取价格高的订单总额
								if (orderCoinOrderAmount.compareTo(orderBalanceOrderAmount)<0) {
									useCouponAmount = orderBalanceOrderAmount;
								}else {
									useCouponAmount = orderCoinOrderAmount;
								}
							}else {
								//如果鸟币总额小于0 并且 现金总额大于0  则取鸟币原始价格   
								if (orderCoinAmount.compareTo(new BigDecimal(0))<=0 && orderBalanceAmount.compareTo(new BigDecimal(0))>0) {
									useCouponAmount = orderCoinOrderAmount;
								}else if(orderCoinAmount.compareTo(new BigDecimal(0))>0 && orderBalanceAmount.compareTo(new BigDecimal(0))<=0){
									useCouponAmount = orderBalanceOrderAmount;
								}
							}
							//组装返回使用的优惠券ID
							if (i == 0) {
								useCouponId = useCouponId.append(couponInfo.getCdid());
							}else {
								useCouponId.append(",").append(couponInfo.getCdid());
							}
							//计算购买粉丝券 赠送的抵用券返回金额  如果使用了抵用券 不赠送 设置赠送金额为0
							resultMap.put("voucherAmount", new BigDecimal(0));
						}
					}
					resultMap.put("useCouponId", useCouponId.toString());//返回使用的抵用券ID
					resultMap.put("useCouponAmount", useCouponAmount);//使用优惠券信息
					resultMap.put("orderCoinAmount", orderCoinAmount.compareTo(new BigDecimal(0))<0?0:orderCoinAmount);//鸟币剩余金额
					resultMap.put("orderBalanceAmount", orderBalanceAmount.compareTo(new BigDecimal(0))<0?0:orderBalanceAmount);//现金剩余金额
				}
				
			}else if(sellerPackageBuyRequest.getCouponType() == -1){
				//标示放弃使用了抵用券
				resultMap.put("orderCoinAmount", packageCoinMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity())));
				resultMap.put("orderBalanceAmount", packageBalanceMoney.multiply(new BigDecimal(sellerPackageBuyRequest.getQuantity())));
			}
		
		} catch (Exception e) {
			log.info("订单操作异常");
			e.printStackTrace();
			throw new Exception("订单操作异常");
		}
		
		//返回钱包余额信息
		Map<String, String> xmnWalletMap = null;
		try {
			xmnWalletMap = liveGiftsInfoService.getXmnWalletBlance(uid);
			//钱包余额总金额
			BigDecimal amountMoney = new BigDecimal("0");
			if (xmnWalletMap!=null) {
				amountMoney = new BigDecimal(xmnWalletMap.get("zbalance"))
							.add(new BigDecimal(xmnWalletMap.get("commision")));
//							.add(new BigDecimal(xmnWalletMap.get("balance")));
				
				resultMap.put("amountMoney", amountMoney.toString());
				resultMap.put("userWalletZbalanceLock", xmnWalletMap.get("zbalanceLock"));
				
			}else {
				log.info("获取会员钱包余额失败");
				throw new Exception("获取会员钱包余额失败");
			}
		} catch (Exception e) {
			log.info("获取钱包余额失败");
			e.printStackTrace();
			throw new Exception("获取钱包余额失败");
		}
		
		//返回直播钱包余额信息
		Map<String, Object> liveWalletMap = null;
		try {
			liveWalletMap = liveGiftsInfoService.getLiveWalletBlance(uid.toString());
			//直播钱包余额总金额
			if (liveWalletMap!=null) {
				
				BigDecimal balanceCoin = liveWalletMap.get("zbalance")==null?new BigDecimal("0"):new BigDecimal(liveWalletMap.get("zbalance").toString());
				BigDecimal originalBbalanceCoin = liveWalletMap.get("zbalanceCoin")==null?new BigDecimal("0"):new BigDecimal(liveWalletMap.get("zbalanceCoin").toString());
				resultMap.put("zbalance", originalBbalanceCoin);
				resultMap.put("liveWalletZbalanceLock", liveWalletMap.get("zbalanceLock"));
				
				//判断鸟币余额 是否小于限制金额   
				resultMap.put("restrictive",0);
				if (null!=seller.getLiveCoinPay()) {//首先判断商家是否开启了鸟币支付
					if (seller.getLiveCoinPay()==1) {//开启鸟币支付：如果开启了鸟币支付则 还需判断 用户是否被限制使用鸟币
						resultMap.put("restrictive",1);//设置为1开启
						
						if (liveWalletMap.get("limitBalance")!=null) {
							//如果限制金额大于0 标示开启了限额
							BigDecimal limitBalance = new BigDecimal(liveWalletMap.get("limitBalance").toString());
							if (limitBalance.compareTo(new BigDecimal("0"))>0) {
								//如果当前余额大于限额  并且  当前鸟币余额 减去订单金额 也大于限额 标示可以使用鸟币支付方式
								if (balanceCoin.compareTo(limitBalance)>0) {
									resultMap.put("restrictive",1);
								}else {
									resultMap.put("restrictive",0);
								}
							}
						}
					}
				}
				
			}else {
				log.info("获取直播钱包余额失败");
				throw new Exception("获取直播钱包余额失败");
			}
		} catch (Exception e) {
			log.info("获取钱包余额失败");
			e.printStackTrace();
			throw new Exception("获取钱包余额失败");
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
						
						//如果有储值卡返利的鸟币  判断储值卡与 返回鸟币值  分别返回储值卡|商户鸟币
						if (liveWalletMap!=null && new BigDecimal(liveWalletMap.get("sellerCoin").toString()).compareTo(new BigDecimal(0))>0 ) {
							sellerCoin = new BigDecimal(liveWalletMap.get("sellerCoin").toString());
							resultMap.put("isSellerCardPay", 1);//设置商家是否开启储值卡支付  1是
							if (sellerQouta.compareTo(sellerCoin)>0) {
								//查询商铺是否开启储值卡
								resultMap.put("sellerCardMoney", sellerCoin);//设置会员在上家储值卡基础余额
							}else {
								resultMap.put("sellerCardMoney", sellerQouta);//设置会员在上家储值卡基础余额
							}
						}else {
							resultMap.put("sellerCardMoney", 0);//设置会员在上家储值卡基础余额
							resultMap.put("isSellerCardPay", 2);//设置商家是否开启储值卡支付  2置灰
							resultMap.put("birdCoinDesc", "鸟币不足，看直播打赏主播可获鸟币奖励");//有额度没鸟币
						}
					}
					resultMap.put("sellerCardId", userSellerCardMap.get("sellerid")); //储值卡所属商家
					resultMap.put("sellerCardName", userSellerCardMap.get("sellername")); //储值卡所属商家
					resultMap.put("sellerCardType", userSellerCardMap.get("sellertype")); //储值卡所属商家
				}
			} catch (Exception e) {
				log.info("获取鸟粉专享卡失败");
				e.printStackTrace();
				throw new Exception("获取鸟粉专享卡失败");
			}
		}
		return resultMap;
	}
	
	/**
	 * 描述：创建订单 组装参数
	 * @param SellerPackageBuyRequest 请求基础参数
	 * @param map  计算价格map
	 * @param Seller 商家基础信息
	 * @param SellerPackage  套餐基础信息
	 * @param type 0正常流程订单,1直播间礼物订单
	 * @return map 订单创建的参数
	 * */
	public Map<Object, Object> insertSellerPackageOrder(SellerPackageBuyRequest sellerPackageBuyRequest,Map<Object, Object> resMap,
			Seller seller, SellerPackage sellerPackage ,Map<Object, Object> userMap,int type) throws Exception{
				
		Map<Object, Object> orderMap = new HashMap<Object, Object>();
		try {
			
			BigDecimal nums = new BigDecimal(resMap.get("quantity").toString());
			
			orderMap.put("order_no", "011"+SaasBidType.getBid());//'订单号',
			orderMap.put("status", 0);//'创建订单待支付',
			orderMap.put("appSourceStatus", EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, sellerPackageBuyRequest.getAppSource()));
			orderMap.put("pid", sellerPackage.getId());//'套餐编号',
			orderMap.put("title", sellerPackage.getTitle());//'套餐名',
			orderMap.put("sellerid", sellerPackage.getSellerid());//'商家编号',
			orderMap.put("uid", userMap.get("uid"));//'会员编号',
			orderMap.put("phone", userMap.get("phone")); //'会员手机号',
			orderMap.put("uname", userMap.get("uname")); //'会员昵称',
			orderMap.put("create_time", DateUtil.format(new Date(), DateUtil.defaultSimpleFormater)); //'创建时间',
			orderMap.put("last_time", DateUtil.format(new Date(), DateUtil.defaultSimpleFormater));//'最后操作时间',
			orderMap.put("nums", resMap.get("quantity"));//'购买数量',
			orderMap.put("original_price", sellerPackage.getOriginalPrice()) ;//'原价/份',
			orderMap.put("selling_price", sellerPackage.getSellingPrice());//'售价/份',
			orderMap.put("selling_coin_price",sellerPackage.getSellingCoinPrice());//'鸟币价/份',
			
			orderMap.put("seller_agio",seller.getAgio());//商家折扣
			orderMap.put("base_agio",resMap.get("platformPaybirdCoin"));//'鸟币与人民币兑换比率基数
			
			//如果是鸟币支付
			orderMap.put("total_coin_amount", nums.multiply(sellerPackage.getSellingCoinPrice()));//'订单总价',
			orderMap.put("total_amount", nums.multiply(sellerPackage.getSellingPrice()));//'订单总价',
			
			orderMap.put("retrun_coupon_amount", 0);
			orderMap.put("cuser", resMap.get("useCouponAmount")) ;//'预售抵用券抵用金额',
			
//			if (resMap.get("useCouponId")!=null) {
//				orderMap.put("cdid", resMap.get("useCouponId")) ;// '预售抵用券ID',
//			}
			orderMap.put("ledger_type", sellerPackage.getLedgerType())  ;//'分账方式 1.固定金额分账 2.销售比例分账',
			orderMap.put("ledger_ratio", sellerPackage.getLedgerRatio()) ;// '分账金额/分账比例',
			orderMap.put("uid_relation_chain", userMap.get("uid_relation_chain")) ;// '会员粉丝等级关系链',
			orderMap.put("buy_source", sellerPackageBuyRequest.getSource()) ;//'购买来源
			
			if (type==1) {//'如果是发礼物的订单',
				orderMap.put("send_uid", resMap.get("uid"));
			}
			
			if (sellerPackageBuyRequest.getSystemversion().indexOf("ios") >=0) {
				orderMap.put("order_source", 1) ;//'订单来源 1 ios 2 android 3 微信',
			}else if (sellerPackageBuyRequest.getSystemversion().indexOf("android") >=0){
				orderMap.put("order_source", 2) ;//'订单来源 1 ios 2 android 3 微信',
			}else {
				orderMap.put("order_source", 3) ;//'订单来源 1 ios 2 android 3 微信',
			}
			orderMap.put("version", 0) ;// '会员粉丝等级关系链',
			
			int OrderNum= sellerPackageOrderDao.insertSellerPackageOrder(orderMap);
			if (OrderNum<=0) {
				log.info("创建订单失败");
				throw new Exception("创建订单失败");
			}else {
				orderMap.put("result", OrderNum);
			}
		} catch (Exception e) {
			log.info("创建订单失败");
			e.printStackTrace();
			throw new Exception("创建订单失败");
		}
		return orderMap;
		
	}
	
	/**
	 * 描述：查询我的粉丝抵用券  金额为大于0时 标示查询所有抵用券
	 * @param liveQueryCouponRequest
	 * @return Object
	 * */
	public Object queryUseCoupon(LiveQueryCouponRequest liveQueryCouponRequest){
		
//		String uid = liveQueryCouponRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(liveQueryCouponRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
		try {
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			List<CouponInfo> resultCouponList = new ArrayList<CouponInfo>();
			Map<Object, Object> fansListMap = new HashMap<Object, Object>();
			fansListMap.put("page", liveQueryCouponRequest.getPage());
			fansListMap.put("limit", Constant.PAGE_LIMIT);
			fansListMap.put("source", 4);
//			if (liveQueryCouponRequest.getOrderAmount() > 0) {
				fansListMap.put("amount", liveQueryCouponRequest.getOrderAmount());
//			}
			fansListMap.put("uid", uid);
			
			String appversion = liveQueryCouponRequest.getAppversion();
			appversion = appversion.replace(".", "");
			int appv = Integer.parseInt(appversion);
			if (appv<=360) {
				fansListMap.put("version", 0);
			}else {
				fansListMap.put("version", 1);
			}
			List<CouponInfo> couponInfoList = anchorLiveRecordDao.queryFansCouponListByAmount(fansListMap);
			if (couponInfoList.size()>0) {
				for (int i = 0; i < couponInfoList.size(); i++) {
					CouponInfo couponInfo = couponInfoList.get(i);
					couponInfo.setPic(fileUrl+couponInfo.getPic());
					couponInfo.setBreviary(fileUrl+couponInfo.getBreviary());
					
					if (couponInfo.getCondition().compareTo(new BigDecimal(0))>0) {
						couponInfo.setConditionStr("满"+couponInfo.getCondition()+"可用");
					}else {
						couponInfo.setConditionStr("不限使用");
					}
					couponInfo.setUseTimeDesc("有效期:"+DateUtil.format(couponInfo.getStartDate(),DateUtil.minuteSimpleFormater)+"至:"+DateUtil.format(couponInfo.getEndDate(),DateUtil.minuteSimpleFormater));
//					couponInfo.setEndDateStr("有效期至:"+DateUtil.format(couponInfo.getEndDate(),DateUtil.daySimpleFormater));
					resultCouponList.add(couponInfo);
				}
			}
			resultMap.put("couponList", resultCouponList);
			response = new MapResponse(ResponseCode.SUCCESS, "获取粉丝券成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			log.info("获取用户抵用券异常!");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "获取粉丝券失败");
		}
		return response;
	}
	
	/**
	 * 描述：获取主播的 有预售信息的直播记录    已经直播完,已经过了试用期的不显示 
	 * @param LiveRecordFansCouponRequest
	 * @return Object 
	 * */
	public Object queryLiveRecordFansCoupon(LiveRecordFansCouponRequest liveRecordFansCouponRequest){
//		String uid = liveRecordFansCouponRequest.getUid().toString();
		//验证token
		MapResponse response = null;
		String uid = sessionTokenService.getStringForValue(liveRecordFansCouponRequest.getSessiontoken()) + "";
//		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
//			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
//		}
		try {
			//根据 主播会员ID 查询直通通告的预售列表
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("uid", liveRecordFansCouponRequest.getAnchorUid());
			paramMap.put("page", liveRecordFansCouponRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			Map<Object, Object> liverInfoMap = liveUserDao.queryLiverInfoByUid(liveRecordFansCouponRequest.getAnchorUid());
			paramMap.put("anchorId", liverInfoMap.get("id"));
			paramMap.put("phone", liverInfoMap.get("phone"));
			//查询该主播的直播及预售 记录  
			
			List<Map<Object, Object>> recordList = new ArrayList<Map<Object, Object>>();
			if (liveRecordFansCouponRequest.getPosition() == 0) {//标示只需要展示悬浮
				paramMap.put("position",1);//只查询推荐的
				recordList = anchorLiveRecordDao.queryLiveRecordFansCouponList(paramMap);
			}else {
				paramMap.put("position",0);//查本场主播和其他主播推荐  包含本场主播其他的粉丝券
				recordList = anchorLiveRecordDao.queryLiveRecordFansCouponList(paramMap);
			}
			
			if (recordList.size()>0) {
				Map<Object, Object> resultMap = new HashMap<Object, Object>();
				List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
				//根据预售显示位置分别的返回 不同结果行数   0 直播间悬浮窗
				if (liveRecordFansCouponRequest.getPosition() == 0) {
					Map<Object, Object> recordMap = new HashMap<Object, Object>();
					for (int i = 0; i < recordList.size(); i++) {
						recordMap = recordList.get(i);
						int isPreSell = Integer.parseInt(recordMap.get("isPreSell").toString());
						//获取有预售的 并且是最新的记录 
						if (isPreSell == 1) {
							recordMap.put("zhiboCover", fileUrl+(recordMap.get("zhiboThumbnail")==null?recordMap.get("zhiboCover"):recordMap.get("zhiboThumbnail")));
//							recordMap.put("zhiboCover", fileUrl+recordMap.get("zhiboCover"));
							recordMap.put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(recordMap.get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
							list.add(recordMap);
							break;
						}
					}
					resultMap.put("recordFansCoupons", list);
					response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
					response.setResponse(resultMap);
					
				}else {
					
					//根据直播用户ID查询 是否有想看的记录
					
					if (liverInfoMap!=null) {
						
						if (!StringUtils.isEmpty(uid) && !"null".equalsIgnoreCase(uid)) {

							//查询当前用户的直播用户Id
							Map<Object, Object> cerrLiver = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
							
							//查询是否有改用户想去列表
							List<Object> liveRecordIdList = new ArrayList<Object>();
//							Integer liverId =  Integer.parseInt(liverInfoMap.get("id").toString());
							Map<Object, Object> focusMap = new HashMap<Object, Object>();
							focusMap.put("liverStrId", cerrLiver.get("id"));
							//将直播列表组装到List  便于查询 是否 点击了想去按钮
							for (int i = 0; i <  recordList.size(); i++) {
								liveRecordIdList.add(recordList.get(i).get("liveRecordId"));
							}
							
							List<Map<Object, Object>> recordResultList = new ArrayList<Map<Object, Object>>();
							focusMap.put("liveRecordIdList", liveRecordIdList);
							//查询 该主播下直播记录  是否点击了想去  并且统计人数
							List<Map<Object, Object>> focusList = liveUserDao.queryFocusShowList(focusMap);
							if (focusList.size()>0) {
								for (int i = 0; i < recordList.size(); i++) {
									for (int j = 0; j < focusList.size(); j++) {
										if (null != focusList.get(j).get("live_record_id") && null != recordList.get(i).get("liveRecordId") 
												&&  focusList.get(j).get("live_record_id").toString().equals(recordList.get(i).get("liveRecordId").toString())) {
											recordList.get(i).put("isWant", "2");//已经想去
											recordList.get(i).put("zhiboCover", fileUrl+(recordList.get(i).get("zhiboThumbnail")==null?recordList.get(i).get("zhiboCover"):recordList.get(i).get("zhiboThumbnail")));
											
											Map<Object, Object> wantGoMap =liveUserDao.queryFocusShowListCount(recordList.get(i));
											if (wantGoMap!=null) {
												recordList.get(i).put("counts",wantGoMap.get("counts"));
											}else {
												recordList.get(i).put("counts",0);
											}
											
											recordResultList.add(recordList.get(i));
										}else {
											recordList.get(i).put("isWant", "1");//未想去
											recordList.get(i).put("breviary", fileUrl+recordList.get(i).get("breviary"));
											recordList.get(i).put("pic", fileUrl+recordList.get(i).get("pic"));
											recordList.get(i).put("zhiboCover", fileUrl+(recordList.get(i).get("zhiboThumbnail")==null?recordList.get(i).get("zhiboCover"):recordList.get(i).get("zhiboThumbnail")));
											recordResultList.add(recordList.get(i));
										}
									}
									recordList.get(i).put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(recordList.get(i).get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
								}
								resultMap.put("recordFansCoupons", recordResultList);
								response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
								response.setResponse(resultMap);
								
							}else {
								for (int i = 0; i < recordList.size(); i++) {
									Map<Object, Object> map = recordList.get(i);
									map.put("breviary", fileUrl+map.get("breviary"));
									map.put("pic", fileUrl+map.get("pic"));
									map.put("zhiboCover", fileUrl+(map.get("zhiboThumbnail")==null?map.get("zhiboCover"):map.get("zhiboThumbnail")));
									map.put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(map.get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
									map.put("isWant", "1");//没想去
									list.add(map);
								}
								
								resultMap.put("recordFansCoupons", list);
								response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
								response.setResponse(resultMap);
							}
						}else {
						
							for (int i = 0; i < recordList.size(); i++) {
								Map<Object, Object> map = recordList.get(i);
								map.put("breviary", fileUrl+map.get("breviary"));
								map.put("pic", fileUrl+map.get("pic"));
								map.put("zhiboCover", fileUrl+(map.get("zhiboThumbnail")==null?map.get("zhiboCover"):map.get("zhiboThumbnail")));
								map.put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(map.get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
								map.put("isWant", "1");//没想去
								list.add(map);
							}
							resultMap.put("recordFansCoupons", list);
							response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
							response.setResponse(resultMap);
							
						}
							
					}
				}
				
			} else {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("recordFansCoupons", recordList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取直播通告预售成功");
				response.setResponse(map);
			}
			
		} catch (Exception e) {
			log.info("获取直播通告预售失败："+uid);
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取直播通告预售失败");
		}
		return response;
	}
	
	/**
	 * 描述：粉丝订单取消接口
	 * @param LiveFansOrderCancleRequest
	 * @return Object
	 * */
	public Object fansOrderUseCoupon(LiveFansOrderUseCouponRequest liveFansOrderUseCouponRequest){
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
		String uid = sessionTokenService.getStringForValue(liveFansOrderUseCouponRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
			
		try{
			//获取粉丝券购买赠送的抵用券基本信息
			String useCouponDesc = propertiesUtil.getValue("fansBuySucessDesc", "conf_common.properties");
			List<Map<Object, Object>> orderListMap = anchorLiveRecordDao.queryFansOrderUseCouponByNo(liveFansOrderUseCouponRequest.getOrderNo());
			if (orderListMap.size()>0) {
				resultMap.put("isFansUseCoupon", 1);
				for (int i = 0; i < orderListMap.size(); i++) {
					Map<Object, Object> fansMap = orderListMap.get(i);
					fansMap.put("cdid", fansMap.get("cdid"));
					fansMap.put("denomination", fansMap.get("denomination"));
					fansMap.put("cname", fansMap.get("cname"));
					if (new BigDecimal(fansMap.get("condition").toString()).compareTo(new BigDecimal(0))>0 ) {
						fansMap.put("condition", "消费满¥"+fansMap.get("condition")+"使用");
					}else {
						fansMap.put("condition", "不限制使用");
					}
					resultList.add(fansMap);
				}
			}else {
				resultMap.put("isFansUseCoupon", 0);
			}
			resultMap.put("useCouponList", resultList);
			resultMap.put("useCouponDesc", useCouponDesc);
			response = new MapResponse(ResponseCode.SUCCESS,"操作成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			log.info("取消订单异常");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "操作成功");
		}
		return response;
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
	public String transMap(Map<String,String> transmap) throws IOException{
		String key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
		//签名
		transmap.put("sign", Signature.sign(transmap,key));	
		
		String url = propertiesUtil.getValue("cancleFansOrderUrl", "conf_live.properties");
		String request = "";
		  for(Entry<String, String> entry : transmap.entrySet()){
		        	request += "&" + entry.getKey() + "=" + entry.getValue();
		   }
		        
		request = request.substring(1, request.length());
		        
		System.out.println(url + "?" + request);
		String payurl=url + "?" + request;
			    
		return payurl;
	}
	
	public static void main(String[] args) {
//		System.out.println(MD5.Encode("123456"));

//		Integer a = 1;
//		System.out.println(Integer.highestOneBit(1) + " " + Integer.lowestOneBit(1) + " " + Integer.toBinaryString(1));
//		System.out.println(Integer.highestOneBit(2) + " " + Integer.lowestOneBit(2) + " " + Integer.toBinaryString(2));
//		System.out.println(Integer.highestOneBit(-1) + " " + Integer.lowestOneBit(-1) + " " + Integer.toBinaryString(-1));

		String maxAmpStr = Integer.toBinaryString(8);
		char[] arr = maxAmpStr.toCharArray();
		boolean[] binaryarray = new boolean[7];
		for (int i=0; i<maxAmpStr.length(); i++){
			if (arr[i] == '1'){
				binaryarray[i] = true;
			}
			else if (arr[i] == '0'){
				binaryarray[i] = false;
			}
		}

		System.out.println(maxAmpStr);
		System.out.println(binaryarray[0]);
		System.out.println(binaryarray[1]);
		System.out.println(binaryarray[2]);
		System.out.println(binaryarray[3]);
		System.out.println(binaryarray[4]);
		System.out.println(binaryarray[5]);
		System.out.println(binaryarray[6]);

	}
}
