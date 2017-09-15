/**
 * 2016年10月24日 上午10:09:45
 */
package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.PresellOrderRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PresellOrderDao;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.LiveOrderService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;

/**
 * @项目名称：xmnService
 * @类名称：PresellOrderService
 * @类描述：预售订单Service
 * @创建人： yeyu
 * @创建时间 2016年10月24日 上午10:09:45
 * @version
 */
@Service
public class PresellOrderService {
	private Logger log=LoggerFactory.getLogger(PresellOrderService.class);
	@Autowired
	private PresellOrderDao presellOrderDao;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private PersonalCenterService personalcenterService;
	@Autowired
	private UserPayBirdCoinService userPayBirdCoinService;
	
	@Autowired
	private LiveGiftsInfoService livegiftsinfoService;
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 直播记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	//注入sessionTokenService
	@Autowired
	private SessionTokenService sessionTokenService;
	
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
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private TlsSendImService tlsSendImService;
	
	/**
	 * 
	* @方法名称: payPresellOrder
	* @描述: 支付预售订单
	* @返回类型 Object
	* @创建时间 2016年10月24日
	* @return
	 */
	public Object payPresellOrder(PresellOrderRequest  preRequest){
//		String uid = preRequest.getUid().toString();
		
		//获取uid
		String uid = sessionTokenService.getStringForValue(preRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//查询购买者的用户信息
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		if (liverMap == null || liverMap.size() <= 0) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
		}
		
		//返回订单Map集合
		Map<String, String> resultMap = new HashMap<>();
		
		String presellorderkey="presellorder_key_"+uid;
		String lockKey="lock_"+preRequest.getRecordId()+"_"+preRequest.getCid()+"_"+preRequest.getAnchorId();
		try {
			//当单个用户购买预售粉丝券，操作过于频繁，等待
			long concurrenceNum=stringRedisTemplate.opsForValue().increment(presellorderkey, 1);
			log.info("预售订单并发presellorderkey"+presellorderkey+",uid="+uid);
			if(concurrenceNum>1){
				return new BaseResponse(ResponseCode.FAILURE, "您有订单正在处理,请稍后重新提交!");
			}
			
			LiverInfo LiverInfo=liveUserDao.queryLiverInfoByAnchorId(preRequest.getAnchorId());
			if(LiverInfo==null){
				log.info("未获取到主播基本信息:主播ID="+preRequest.getAnchorId());
				return new BaseResponse(ResponseCode.FAILURE, "未获取到主播基本信息");
			}
			//同时多人购买同一个直播预售粉丝券
			while(!this.setnx(lockKey,"orderLock")){
				log.info("用户UID="+uid+"在主播ID="+preRequest.getAnchorId()+"直播ID="+preRequest.getRecordId()+",等待中.......");
			}
			//开始获取当前粉丝券库存总数量-------------------------------------------------------------------
			
			if(preRequest.getCidNum()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "购买粉丝券数量不能小于或等于0");
			}
			
			Map<Object,Object> paramfansMap=new HashMap<>();
			paramfansMap.put("recordId", preRequest.getRecordId());//直播记录ID
			paramfansMap.put("cid", preRequest.getCid());//粉丝券配置表里主键ID
			paramfansMap.put("uid", LiverInfo.getUid());//主播UID
			Map<Object,Object> conMap=presellOrderDao.queryFansCouponByOther(paramfansMap);
			if(conMap==null || conMap.size()<=0){
				log.info("获取粉丝券数量失败,主播UID="+LiverInfo.getUid());
				return new BaseResponse(ResponseCode.FAILURE, "获取粉丝券库存失败");
			}
			
			Integer conNum=Integer.parseInt(conMap.get("stock").toString());
			//判断购买数量是否大于当前粉丝券含有的库存数量
			if(preRequest.getCidNum()>conNum){
				return new BaseResponse(ResponseCode.FAILURE, "当前购买粉丝券数量不能大于剩余粉丝券库存量");
			}	
			
			//查询该主播直播的预售粉丝券
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("liveRecordId", preRequest.getRecordId());
			paramMap.put("cid", preRequest.getCid());//粉丝券配置表里主键ID
			//获取直播主播的预售粉丝券
			Map<Object, Object> couponDetailMap = anchorLiveRecordDao.queryLiveRecordFansCoupon(paramMap);
			if (couponDetailMap==null || couponDetailMap.size()<=0) {
				log.info("预售粉丝券不存在或已过期");
				return new MapResponse(ResponseCode.FAILURE, "预售粉丝券不存在或已过期");
			}
			
			//查询是否有 选择预售抵用券
			Map<Object, Object> couponMap = new HashMap<Object, Object>();
			couponMap.put("cid", preRequest.getCdid());//粉丝券配置表里主键ID
			CouponInfo couponInfo = anchorLiveRecordDao.queryFansCouponInfoByCouponId(couponMap);
			
			//计算选择好的预售粉丝券 金额  乘以 购买数量    得到订单总额
			BigDecimal couponMoney  = new BigDecimal(couponDetailMap.get("denomination").toString());
			BigDecimal couponNum  = new BigDecimal(preRequest.getCidNum());
			BigDecimal orderAmount = couponMoney.multiply(couponNum);
			
			//生成订单基本主体信息
			Map<Object, Object> orderInfoMap = this.createOrderInfo(preRequest,uid);
			orderInfoMap.put("sellerid", couponDetailMap.get("sellerid"));
			
			//签名验证使用
			BigDecimal amount = couponMoney.multiply(couponNum);	
			//订单总金额
			orderInfoMap.put("total_amount", orderAmount);
			orderInfoMap.put("appSource", preRequest.getAppSource());//app来源
			if (couponDetailMap!=null && couponDetailMap.size()>0) {
				try {
					
					if (preRequest.getCdid() !=null && preRequest.getCdid()>0) {
						
						//如果有使用预售抵用券   计算使用抵用券后的金额  如果够抵扣订单金额  直接支付成功
						if (couponInfo!=null) {
							
							//如果使用了预售抵用券   减去预售抵用券金额
							orderInfoMap.put("cuser", couponInfo.getDenomination());//设置订单预售抵用券的支付金额
							resultMap.put("preferential", couponInfo.getDenomination().toString());//设置订单预售抵用券的支付金额
							orderAmount = orderAmount.subtract(couponInfo.getDenomination());
							if (orderAmount.compareTo(new BigDecimal(0))<=0) {
								return new MapResponse(ResponseCode.FAILURE, "使用抵用券金额不能超过订单总额");
							}
//							amount = orderAmount;//如果有预售抵用券  则 把扣除抵用券的金额负责到总额  签名验证使用
						}else {
							return new MapResponse(ResponseCode.FAILURE, "未找到优惠券或者优惠券已被使用");
						}
					}
				} catch (Exception e) {
					log.info("使用抵用券错误："+preRequest.getCdid());
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "使用抵用券异常");
				}
				
				//判断 使用了抵用券后的 订单金额是否大于0   大于0 则需要选择支付方式 
				if (orderAmount.compareTo(new BigDecimal("0")) >0) {
					// 如果选择使用粉丝卡支付  
					if (preRequest.getIsFansCard() == 1) {
						//获取直播钱包  粉丝卡余额 
						Map<String, Object> liveBlanceMap = livegiftsinfoService.getLiveWalletBlance(uid);
						if (liveBlanceMap!=null) {
							
							BigDecimal zbalance = new BigDecimal(liveBlanceMap.get("zbalance").toString());
							
							if (zbalance.compareTo(new BigDecimal(0))>0) {//标示粉丝卡有余额
								
								//如果订单金额  减去 年粉卡余额 大于 0  标示为支付完成  需要继续支付
								if (orderAmount.subtract(zbalance).compareTo(new BigDecimal(0))>0) {
									//选择粉丝卡支付 后  订单剩余金额  
									return new MapResponse(ResponseCode.FAILURE, "鸟粉卡余额不足!");
									
								}else {
									//粉丝卡 可以完成支付  直接生成订单  完成支付
									try {
										
										orderInfoMap.put("payment_type", preRequest.getPay_type().toString());// 鸟粉卡的支付方式
										orderInfoMap.put("status", 0);// 订单状态 已支付
										orderInfoMap.put("beans", orderAmount);// 订单状态 已支付
											
										try{
											Map<String, String> map = new HashMap<String, String>();
											resultMap.put("paymentType", preRequest.getPay_type().toString());//鸟粉卡的支付方式
											resultMap.put("liveCoin", orderAmount.toString());
											orderAmount = new BigDecimal(0);
											
										} catch (Exception e) {
											log.info("订单异常");
											e.printStackTrace();
											return new MapResponse(ResponseCode.FAILURE, "订单操作失败");
										}
										
									} catch (Exception e) {
										log.info("购买粉丝券异常");
										e.printStackTrace();
										return new MapResponse(ResponseCode.FAILURE,"购买粉丝券异常");
									}
									
								}
							}
						}
						
					}
					
					if (preRequest.getIsBalance() == 1) { //如果选择了   余额支付  
						
						BigDecimal commision=new BigDecimal(0);
						BigDecimal zbalance=new BigDecimal(0);
						
						log.info("获取寻蜜鸟钱包余额，UID="+uid);
						//获取寻蜜客钱包余额
						Map<String, String> walletBalanceMap=personalcenterService.getWalletMoney(uid+"", 1);
						
						if(walletBalanceMap==null || walletBalanceMap.size()<=0){
							log.info("未获取寻蜜鸟钱包余额，UID="+uid);
							return new BaseResponse(ResponseCode.FAILURE, "未获取寻蜜鸟钱包信息");
						}
						commision=new BigDecimal(walletBalanceMap.get("commision").toString());//佣金余额
						zbalance=new BigDecimal(walletBalanceMap.get("zbalance").toString()); //赠送余额
						log.info("获取寻蜜鸟钱包余额成功，UID="+uid+",佣金余额："+commision+",赠送余额："+zbalance);
						
						//判断使用佣金 和 赠送金额支付  值   
//						BigDecimal orderMoney = orderAmount;//使用佣金之前的  订单金额
						
						if (commision.compareTo(new BigDecimal(0)) >0) { //有佣金
							if (commision.compareTo(orderAmount)>=0) {//如果 佣金大于等于 订单金额
								
								resultMap.put("commision", orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
								orderInfoMap.put("commision", orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP));//佣金支付金额
								//如果佣金能够支付  无需传入 赠送金额
								orderAmount = new BigDecimal(0);//够支付修改 0
								orderInfoMap.put("zbalance", 0);//佣金支付金额
								
								orderInfoMap.put("cash", 0);//第三方支付总额
							}else {
								
								orderAmount = orderAmount.subtract(commision);
								resultMap.put("commision", commision.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
								orderInfoMap.put("commision", commision.setScale(2, BigDecimal.ROUND_HALF_UP));//佣金支付金额
							}
							
						}
						if (zbalance.compareTo(new BigDecimal(0))>0 && orderAmount.compareTo(new BigDecimal(0))>0) {
							
							if (zbalance.compareTo(orderAmount)>=0 ) {//如果 佣金大于等于 订单金额
								
								resultMap.put("zbalance", orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
								orderInfoMap.put("zbalance", orderAmount.setScale(2, BigDecimal.ROUND_HALF_UP));//赠送支付金额
								orderAmount = new BigDecimal(0);//够支付修改 0
								orderInfoMap.put("cash", 0);//第三方支付总额
								
							}else {
								orderAmount = orderAmount.subtract(zbalance);
								resultMap.put("zbalance", zbalance.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
								orderInfoMap.put("zbalance", zbalance.setScale(2, BigDecimal.ROUND_HALF_UP));//佣金支付金额
								
							}	
						}
//						orderAmount = orderMoney.subtract(orderAmount);
						resultMap.put("paymentType", "1000000");
						orderInfoMap.put("payment_type", "1000000");
						orderInfoMap.put("cash", orderAmount);//第三方支付总额
						
					}
					
					if (null != preRequest.getPay_type() && preRequest.getPay_type()!=0 ) {
						//支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:微信公众号支付，1000000：钱包支付 1000015 鸟币支付支付方式
						//如果是微信公众号支付  判断 并且传值 wxuid
						if (preRequest.getPay_type() == 1000013 || preRequest.getPay_type() == 1000025) {
							resultMap.put("wxuid", preRequest.getOpenId());
							orderInfoMap.put("wxuid", preRequest.getOpenId());
						}
						if (preRequest.getPay_type() == 1000014 || preRequest.getPay_type() == 1000023) {
							resultMap.put("returnUrl", preRequest.getReturnUrl());
							orderInfoMap.put("returnUrl", preRequest.getReturnUrl());
						}
						orderInfoMap.put("cash", orderAmount);//如果 使用第三方支付  则剩下的余额插入第三方支付总额  
						resultMap.put("paymentType", preRequest.getPay_type()+"");
						orderInfoMap.put("payment_type", preRequest.getPay_type()+"");
						
					}else if (orderAmount.compareTo(new BigDecimal(0))>0) {
						log.info("支付余额不足： "+preRequest.getRecordId());
						return new MapResponse(ResponseCode.FAILURE, "支付余额不足");
					}
				}
				
			}else {
				log.info("获取粉丝券异常:直播记录 "+preRequest.getRecordId());
				return new MapResponse(ResponseCode.FAILURE, "该通告暂无预售或预售已结束");
			}
			
			//------------------------请求支付接口  固定  参数
			log.info("开始组建支付接口参数UID="+uid);
			//订单编号
			resultMap.put("orderSn",orderInfoMap.get("order_sn").toString());
			//用户UID
			resultMap.put("uid", uid);
			//获取每个套餐每个店铺的单价金额
			//订单金额
			resultMap.put("amount", amount.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			//订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单；004:业务系统-物料订单；005:业务系统-直播鸟币购买订单
			resultMap.put("source", "008");
			//订单类型，目前传固定值2
			resultMap.put("orderType", "2");	
			//订单标题
			resultMap.put("subject","预售粉丝券订单");
			//------------------------请求支付接口  固定  参数
			int OrderNum=this.addPresellOrder(orderInfoMap,liverMap);
			if(OrderNum<=0){
				log.info("创建粉丝券订单失败"+orderInfoMap.toString());
				return new BaseResponse(ResponseCode.FAILURE, "创建粉丝券订单失败");
			}
			if(preRequest.getCdid()!=null && !"".equals(preRequest.getCdid())&& preRequest.getCdid()!=-1){
				//锁定抵用券数据状态
				int lockNum=this.lockAndUnlockCouponTicket(preRequest.getCdid(), 1,new Date());
				if(lockNum<=0){
					log.info("锁定抵用券失败，条数="+lockNum);
					return new BaseResponse(ResponseCode.FAILURE, "未获取到优惠券，使用异常");
				}
			}
			log.info("创建粉丝券订单成功，UID="+uid);
			
			//更新粉丝券总数量
			paramfansMap.put("subNum", preRequest.getCidNum());
			paramfansMap.put("cid", conMap.get("cid"));//将查询库存的返回的粉丝券的真实ID赋值到该参数 (原因：粉丝券和通告的配置信息 转移到粉丝券主体表T_COUPON下单后更新主体信息表库存字段)
			int fansNum=presellOrderDao.modifyfansCoupon(paramfansMap);
			if(fansNum<=0){
				log.info("更新粉丝券库存失败,主播UID="+LiverInfo.getUid());
				return new BaseResponse(ResponseCode.FAILURE, "更新粉丝券库存失败");
			}
			
			//以下三个参数可选，值为0时可不传，
			//若paymentType为1000000时 profit + commision + zbalance + integral 必须等于 amount
			//若混合支付，paymentType传第三方支付类型，且 amount 必须大于 profit + commision + zbalance + integral
			//所有传入金额必须格式化为2位小数
			String url = userPayBirdCoinService.transMap(resultMap);
			
			//开始调用支付接口------------------------------------------------------------------
			log.info("开始调用支付接口URL："+url);
			resultMap.put("url", url);
			
			String result = userPayBirdCoinService.payBirdCoin(resultMap).toString();
			JSONObject resultJson = JSONObject.parseObject(result);
			if (Integer.parseInt(resultJson.get("state")==null?"1":resultJson.get("state").toString()) == 201) {
				try {
					//查看正在直播的直播记录信息(包含内部测试通告)
					List<LiveRecordInfo> LiveRecordList = anchorLiveRecordDao.queryCurrentLiveRecord();
					//发送广播的直播间集合
					List<String> groupIdList = new ArrayList<>();
					
					if (LiveRecordList != null && LiveRecordList.size() > 0 ) {
						//批量查询直播间号(群组号)
						groupIdList = anchorLiveRecordDao.queryGroupIds(LiveRecordList);
						//购买粉丝券成功后发送广播信息
						String text = "恭喜" + liverMap.get("nname").toString() + "成功购买了一个预售券";
						//广播
						tlsSendImService.sendGroupMsgLiveRadio(text, 1, groupIdList);
					}
					
				} catch (Exception e) {
					log.info("用户uid:" + liverMap.get("uid") + "成功购买预售券,发送广播信息失败");
				}
			}
			
			return resultJson;
		} catch (Exception e) {
			log.error("支付预售订单异常，UID="+uid);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}finally{
			stringRedisTemplate.delete(presellorderkey);
			stringRedisTemplate.delete(lockKey);
			
		}
		
	}
	
	/**
	 * 
	* @方法名称: addPresellOrder
	* @描述: 增加预售订单接口
	* @返回类型 Object
	* @创建时间 2016年10月24日
	* @return
	 * @throws Exception 
	 */
	public int addPresellOrder(Map<Object,Object> param,Map<Object,Object> liverMap) throws Exception{
			int resultNum=0;
//			Map<Object, Object> liverMap=null;
			LiveRecordInfo recordInfo=null;
			Integer uid=0;
			Integer recordId=0;
			try{
				uid=Integer.parseInt(param.get("uid").toString());//uid
				recordId=Integer.parseInt(param.get("record_id").toString());//直播记录ID
				
				Map<Object, Object> paramMap =  new HashMap<Object, Object>();
				//查询直播记录信息
				paramMap.put("id", recordId);
				 recordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
				if (recordInfo == null) {
					log.error("未获取到直播记录信息,直播记录ID="+recordId);
					throw new Exception("未获取到直播记录信息");
				}
			}catch(Exception e){
				log.error("调用用户基本信息或获取直播记录基本信息异常，用户UID="+uid+",直播记录ID="+recordId);
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		try {
			param.put("return_integral", 0);//赠送积分金额denomination.multiply(new BigDecimal(cid_num))
			param.put("cuser", param.get("cuser"));//预售抵用券抵用金额
			param.put("beans", param.get("beans"));//预售抵用券抵用金额
			param.put("phoneid", liverMap.get("phone"));//用户手机号
			param.put("nname", liverMap.get("nname"));//用户昵称
			param.put("sellerid", param.get("sellerid") );//商户id
			param.put("status", 0);//0 待支付 1 已支付 2 取消支付/支付失败
			param.put("uid_relation_chain", liverMap.get("uid_relation_chain")==null?"":liverMap.get("uid_relation_chain"));//0 待支付 1 已支付 2 取消支付/支付失败
			param.put("appSourceStatus", EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, ObjectUtils.toString(param.get("appSource"))));
			param.put("create_time", DateUtil.format(new Date()));//创建时间
			resultNum=presellOrderDao.addPresellOrder(param);
		} catch (Exception e) {
			log.error("增加预售订单接口异常:param"+param.toString());
			e.printStackTrace();
			throw new Exception("增加预售订单接口异常，请联系管理员");
		}
		return resultNum;
	}
	
	/**
	 * 描述 ： 创建订单基本主体方法
	 * @param map
	 * @return Map<Object, Object>
	 * */
	public Map<Object, Object> createOrderInfo(PresellOrderRequest  preRequest,String uid){
		//开始创建预售订单-----------------------------------------------------------------------
		String order_no = "05"+SaasBidType.getBid();
		Map<Object,Object> param=new HashMap<Object, Object>();
		
		param.put("order_sn", order_no);//生成订单号
		//订单来源 1 ios 2 android 3 微信
		int order_source=3;
		if(preRequest.getSystemversion().toLowerCase().indexOf("ios")>-1){
			order_source=1;
		}else if(preRequest.getSystemversion().toLowerCase().indexOf("android")>-1){
			order_source=2;
		}
		param.put("order_source", order_source);//订单来源
		param.put("anchor_cid", preRequest.getCid());//粉丝券ID
		param.put("cid_num", preRequest.getCidNum());//粉丝券数量
		param.put("uid", uid);//用户id
		param.put("record_id", preRequest.getRecordId());//直播通告ID
		param.put("cash", 0);//第三方支付总额
		param.put("total_amount", 0);
		
		param.put("commision", 0);//佣金支付金额
		param.put("zbalance", 0);//赠送支付金额
		param.put("balance", 0);//赠送支付金额
		param.put("beans", 0);//鸟币支付金额
		
		param.put("integral", 0);//积分支付总额
		param.put("cuser", 0);//预收抵用券支付金额
		param.put("payment_type", preRequest.getPay_type());//赠送支付金额
		
		param.put("cdid", preRequest.getCdid()==-1?null:preRequest.getCdid());//预售抵用券ID
		param.put("buy_source", preRequest.getSource());//赠送支付金额
		
		return param;
	}
	
	
	/**
	 * 检查是否同时购买一批粉丝券redis的key 是否存在
	 * */
	public  boolean setnx(final String redisKey,final String redisKeyValue){
		boolean bool=stringRedisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection con) throws DataAccessException {
				byte[] key=stringRedisTemplate.getStringSerializer().serialize(redisKey);
				byte[] value=stringRedisTemplate.getStringSerializer().serialize(redisKeyValue);
				return con.setNX(key, value);
			}
		});
		return bool;
	}
	/**
	 * 
	* @方法名称: lockAndUnlockFocusTicket
	* @描述:锁定或解除或已使用抵用券
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param ticketId 粉丝券ID
	* @param status   粉丝券状态
	* @return
	* @throws Exception
	 */
	public int lockAndUnlockCouponTicket(Integer cdid,Integer userStatus,Date lockTime) throws Exception{
		int lockNum=0;
		try {
			Map<Object,Object> param=new HashMap<>();
			param.put("cdid", new ArrayList<Integer>().add(cdid));
			param.put("lockTime", lockTime);
			param.put("userStatus", userStatus);
			lockNum=presellOrderDao.lockAndUnlockCouponTicket(param);
		} catch (Exception e) {
			log.error("锁定或解除抵用券异常，ticketId"+cdid+",status="+userStatus);
			e.printStackTrace();
			throw new Exception("锁定或解除抵用券异常异常，请联系管理员");
		}
		return lockNum;
	}
	
	
	/**
	 * 
	* @方法名称: modifyPreSellOrder
	* @描述: 取消预售订单
	* @返回类型 Object
	* @创建时间 2016年10月24日
	* @param orderNo
	* @return
	 */
	public Object modifyPreSellOrder(String orderNo){
		try {
			Integer cdid=1;
			Map<Object,Object> param=new HashMap<>();
			param.put("orderSn", orderNo);//订单号
			param.put("status", "2");
			param.put("modifyTime", DateUtil.format(new Date()));
			int lockNum=this.lockAndUnlockCouponTicket(cdid, 0,new Date());
			if(lockNum>0){
				log.info("解除抵用券条数="+lockNum);
				int modiflyNum=presellOrderDao.modifyPreSellOrder(param);
				log.info("取消预售订单条数="+modiflyNum);
				if(modiflyNum<1){
					log.info("取消预售订单失败，取消条数="+modiflyNum);
					return new BaseResponse(ResponseCode.FAILURE, "取消预售订单失败");
				}
				log.info("取消预售订单成功过，取消条数="+modiflyNum);
				return new BaseResponse(ResponseCode.SUCCESS, "取消预售订单成功");
			}
			log.info("取消预售订单失败,解除抵用券条数="+lockNum);
			return new BaseResponse(ResponseCode.FAILURE, "取消预售订单失败");
		} catch (Exception e) {
			log.error("取消预售订单异常：订单号"+orderNo);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "取消预售订单异常");
		}
	}
	
	
	/**
	 * 
	* @方法名称: modifyFocusTicketNums
	* @描述: 修改粉丝券数量
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @return
	* @throws Exception
	 */
	public int modifyFocusTicketNums() throws Exception{
		int ticketNum=0;
		try {
			Map<Object,Object> param=new HashMap<>();
			ticketNum=presellOrderDao.modifyFocusTicketNums(param);
		} catch (Exception e) {
			log.error("修改粉丝券数量异常");
			e.printStackTrace();
			throw new Exception("修改粉丝券数量异常，请联系管理员");
		}
		return ticketNum;
	}
	
	/**
	 * 
	* @方法名称: giveIntegral
	* @描述: 赠送积分
	* @返回类型 int
	* @创建时间 2016年10月24日
	* @param integral
	* @param uid
	* @return
	 * @throws Exception 
	 */
	public int giveIntegral(BigDecimal integral,int uid) throws Exception{
		try {
			Map<String, String> xmnWalletMap =new HashMap<>();
			xmnWalletMap.put("uId", uid+"");//用户ID
//			xmnWalletMap.put("integral", integral+"");//积分余额
			xmnWalletMap.put("integral", "0");//积分余额
			xmnWalletMap.put("userType", "1");//用户
			xmnWalletMap.put("orderId",System.currentTimeMillis()+"");//订单ID型 
			xmnWalletMap.put("remark", "预售粉丝卷获得"+integral+"积分");//描述
			xmnWalletMap.put("rType", "4");
			xmnWalletMap.put("option", "0");
			Map<String, String>  xmnMapResp= livegiftsinfoService.subtractXmnWalletBlance(xmnWalletMap);
			
			if(xmnMapResp!=null ){
				if(xmnMapResp.get("state").equals("0")){
					log.info("抽取礼品获取积分成功");
					return 1;
				}else{
					log.error("获取积分失败,失败原因："+xmnMapResp.get("msg").toString());
					throw new Exception("获取积分失败,失败原因："+xmnMapResp.get("msg").toString());
				}
			}
		} catch (Exception e) {
			log.error("赠送积分异常，UID="+uid);
			e.printStackTrace();
			throw new Exception("预售粉丝券，赠送积分异常，请联系管理员");
		}
		return 0;
	}
	
	
	
/*	public int giveUseTicket(Map<Object,Object> param){
		
		//赠送抵用券
			Map<Object,Object> giveMap=new HashMap<>();
			giveMap.put("aid", aid);//活动ID
			giveMap.put("cid", value);//优惠劵ID
			giveMap.put("serial", value);//优惠劵序列码
			giveMap.put("denomination", value);//优惠劵面额
			giveMap.put("get_way", 4);//获取方式，1摇一摇，2满返，3短信获取 4直接发放,5：订单后刮优惠劵；6:分享后刮优惠劵
			giveMap.put("start_date", value);//起始有效期
			giveMap.put("end_date", value);//截止有效期
			giveMap.put("get_status", 1);//获取状态，0未获取，1已获取，2获取锁定
			giveMap.put("get_time", value);//获取时间
			giveMap.put("uid", value);//获取用户ID
			giveMap.put("phone", value);//获取用户手机号码
			giveMap.put("third_uid", value);//第三方支付ID
			giveMap.put("user_status", value);//使用状态，0未使用，1锁定，2已使用
			giveMap.put("user_money", value);//使用金额
			giveMap.put("lock_time", value);//锁定时间
			giveMap.put("user_time", value);//使用时间
			giveMap.put("issue_id", value);//发放表Id
			giveMap.put("date_issue", value);//发行时间
			giveMap.put("order_no", value);//满就送的订单编号
			giveMap.put("0", value);//发送状态(短信发送专用)，0 待发送 1 已发送
			giveMap.put("sellerid", value);//商家id
			giveMap.put("bid", value);//订单id

			int giceTicketNum=presellOrderDao.giveUseTicket(giveMap);
	}*/
}
