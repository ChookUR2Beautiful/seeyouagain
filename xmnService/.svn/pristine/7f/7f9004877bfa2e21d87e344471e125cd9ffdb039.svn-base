package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.GiftsInfoRequest;
import com.xmniao.xmn.core.common.request.live.LiveGiftsListRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.live.entity.LiveSelfGiftInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.ExperiencecardService;
import com.xmniao.xmn.core.thrift.LiveOrderService;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.LiveWalletService.Client;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.SynthesizeService;
import com.xmniao.xmn.core.thrift.ValueCardService;
import com.xmniao.xmn.core.thrift.business.java.FailureException;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;



/**
 * 项目描述：XmnService
 * API描述：直播界面礼物业务处理类
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Service
public class LiveGiftsInfoService {
	
	private final Logger log = Logger.getLogger(LiveGiftsInfoService.class);
	
	/**
	 * 直播礼物Dao
	 * */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	/**
	 * 用户基础dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;  
	
	/**
	 * 直播观看记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 直播redis service
	 * */
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	@Autowired 
	private SelfGiftService selfGiftService;
	
	/**
	 * sessionTokenService
	 * */
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
	private ThriftUtil thriftUtil;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入tlsSendImService
	 */
	@Autowired
	private TlsSendImService tlsSendImService;
	
	/**
	 * 注入liveSendProductGiftsService
	 */
	@Autowired
	private LiveSendProductGiftsService liveSendProductGiftsService;
	
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	@Autowired
	private TopicInfo topicSendBirdEgg;
	
	/**
	 * 描述：直播界面获取礼物列表
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	public Object getGiftsInfoList(LiveGiftsListRequest liveGiftsListRequest){
		
//		String uid = liveGiftsListRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(liveGiftsListRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		Map<Object, Object> giftsMap = new HashMap<Object, Object>();
		MapResponse response = null;
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			
			// 获取当前版本号 3.5.8及以前的版本  访问旧的礼物记录
			String appversion = liveGiftsListRequest.getAppversion().replace(".", "");
			if (liveGiftsListRequest.getAppSource().equals("xmn") &&  !StringUtils.isEmpty(appversion) || !"null".equalsIgnoreCase(appversion)) {
				int version = Integer.parseInt(appversion);
				if (version<=358) { //只查询 普通礼物 1
					paramMap.put("giftKind", 1);
				}
			}
			
			//如果IOS审核期间  套餐/实体商品礼物不展示
			try {
				String appleUid = propertiesUtil.getValue("hiddenMark", "conf_live.properties");
				String livePointMark = propertiesUtil.getValue("livePointMark", "conf_live.properties");//首先初始化为不隐藏
				
				//如果直播间需要隐藏套餐理礼物  或者 不开启礼物引导图则  只查普通礼物
				if (appleUid.equals("1") || livePointMark.equals("0")) {
					paramMap.put("giftKind", 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("获取审核账号UID出错");
				return new MapResponse(ResponseCode.FAILURE, "获取用户基础信息异常");
			}
			
			String liver_key = "liver_"+uid;
			
			Map<Object, Object> liveMap = stringRedisTemplate.opsForHash().entries(liver_key);
			if (liveMap!=null) {
				
				paramMap.put("liver_id", liveMap.get("id"));
				List<LiveGiftsInfo> gifts_list = liveGiftsInfoDao.getGiftsInfoList(paramMap);
				for (int i = 0; i < gifts_list.size(); i++) {
					LiveGiftsInfo giftsInfo = gifts_list.get(i);
					giftsInfo.setGift_avatar(fileUrl+giftsInfo.getGift_avatar());
				}
				giftsMap.put("liveGift", gifts_list);
				response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
			}else {
				response = new MapResponse(ResponseCode.FAILURE,"获取礼物列表失败");
			}
			response.setResponse(giftsMap);
			
		} catch (Exception e) {
			response = new MapResponse(ResponseCode.FAILURE,"获取失败");
			e.printStackTrace();
		}
		return response;
		
	}
	
	/**
	 * 描述：直播界面赠送主播礼物  
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	public Object giftsGiveAways(GiftsInfoRequest giftsInfoRequest){
		
//		String uid = giftsInfoRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(giftsInfoRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		} 
		giftsInfoRequest.setUid(uid);//设置当前用户uid
		
		String live_anchor_key ="";
		try {
			live_anchor_key = "live_sync_anchor_"+giftsInfoRequest.getAnchorId()+"_"+giftsInfoRequest.getUid();
			//同步redis key 用来当做锁使用
			if (!this.synchRedisMethod(live_anchor_key)) {
				
				//新增用户发礼物标示key  用于匹配机器人是否该发自动礼物
//				public final static String ROBOT_SEND_GIFT_KEY = "robot_send_gift_key";
				
				//直播间机器人行动
				String key = Constant.ROBOT_ACTION_KEY + giftsInfoRequest.getLiveRecordId();
				if (stringRedisTemplate.hasKey(key)) {
					stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
				}else {
					stringRedisTemplate.opsForValue().set(key, "true");
					stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
				}
//				if (stringRedisTemplate.hasKey(Constant.ROBOT_SEND_GIFT_KEY)) {
//					stringRedisTemplate.expire(Constant.ROBOT_SEND_GIFT_KEY, 30, TimeUnit.SECONDS);
//				}else {
//					stringRedisTemplate.opsForValue().set(Constant.ROBOT_SEND_GIFT_KEY, "true");
//					stringRedisTemplate.expire(Constant.ROBOT_SEND_GIFT_KEY, 30, TimeUnit.SECONDS);
//				}
				
				//获取当前用户的redis 
				String liver_key = "liver_"+Long.valueOf(giftsInfoRequest.getUid());
				Map<Object, Object>  liverMap= stringRedisTemplate.opsForHash().entries(liver_key);
				
				
				//标示需要发送 实体礼物 套餐礼物 商城礼物
				if (giftsInfoRequest.getGiftType()>0) {
					return liveSendProductGiftsService.sendProductGift(giftsInfoRequest,liver_key);
				}
				
				//获取礼物信息
				LiveGiftsInfo giftsInfo = liveGiftsInfoDao.getGiftsInfoByGiftId(Integer.parseInt(giftsInfoRequest.getId()));
				
				if (giftsInfo!=null && null!=giftsInfo.getId()) {
					
					if (liverMap != null) {
						liverMap.put("live_record_id", giftsInfoRequest.getLiveRecordId());
						liverMap.put("get_experience_type", "1");
						//获取主播信息 拿到群组ID
						LiverInfo liverInfo  = liveUserDao.queryLiverInfoByAnchorId(Integer.parseInt(giftsInfoRequest.getAnchorId()));
						if (liverInfo!=null && null!=liverInfo.getUid()) {
							if (!stringRedisTemplate.hasKey("liver_wallet_"+liverInfo.getUid())) {
								//发普通礼物时  提现主播是否还在直播
								return new BaseResponse(ResponseCode.FAILURE, "主播暂时离开,请稍后");
							}
						}
						
						//检查主播有没有同步鸟蛋
						if (stringRedisTemplate.hasKey(Constant.SEND_LIVE_REDPACKET_PROCESS+"_"+liverInfo.getUid()) == true) {
							return new BaseResponse(ResponseCode.LIVE_SEND_GIFTS_FILTER, "系统繁忙");
						}
						
						//发送礼物
						return this.givenGiftsToAnchorId(liverMap,giftsInfo,liver_key,giftsInfoRequest,liverInfo);
					}else {
						log.info("获取用户信息失败："+liver_key);
						return new MapResponse(ResponseCode.FAILURE,"未能获取到用户信息");
					}
				}else {
					log.info("获取礼物信息失败："+giftsInfoRequest.getId());
					return new MapResponse(ResponseCode.FAILURE,"礼物不存在或已下架");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE,"系统异常");
		}finally{
			stringRedisTemplate.delete(live_anchor_key);
		}
		
		return new BaseResponse(ResponseCode.LIVE_SEND_GIFTS_FILTER, "系统繁忙");
	}
	
	/**
	 * 描述：观众发送主播礼物  普通
	 * @param liverMap,LiveGiftsInfo liver_key giftsInfoRequest
	 * @return Object
	 * 2016-8-18 15:20:14
	 * @throws Exception 
	 * */
	public Object givenGiftsToAnchorId(Map<Object, Object> liverMap,LiveGiftsInfo giftsInfo,String liver_key,
				GiftsInfoRequest giftsInfoRequest,LiverInfo liverInfo) throws Exception{
		//如果礼物是积分礼物
		if (giftsInfo.getGift_type() == 1) {
			
			//优先查找自己礼物表里有无该礼物
			Map<Object, Object> selfGiftParamMap = new HashMap<Object, Object>();
			selfGiftParamMap.put("liver_id", liverMap.get("id").toString());
			selfGiftParamMap.put("gift_id", giftsInfoRequest.getId());
			LiveSelfGiftInfo selfGiftInfo = liveGiftsInfoDao.queryLiveSelfGifts(selfGiftParamMap);
			if (selfGiftInfo!=null && selfGiftInfo.getGiftNums()>0) {
				try {
					//削减用户礼物表的所持有礼物数量
					selfGiftParamMap.put("giftNums", selfGiftInfo.getGiftNums()-1);
					int resultNum = liveGiftsInfoDao.modifyLiveSelfGiftNum(selfGiftParamMap);
					if (resultNum>0) {
						return this.sendIntegralGiftsHandle(giftsInfoRequest, liverMap, null, giftsInfo, liverInfo, liver_key);
					}else {
						log.info("赠送礼物出错,扣减礼物数量失败："+liverMap.get("uid").toString());
						return new BaseResponse(ResponseCode.FAILURE, "赠送礼物出错,扣减直播钱包失败");
					}
				} catch (Exception e) {
					log.info("赠送礼物出错，操作自有礼物表出错");
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE, "赠送礼物出错，操作自有礼物表出错");
				}
			}
			
			//自有礼物表为空时  去扣减积分购买礼物
			try {
				//扣减钱包鸟币余额
				Map<String, String> subtractWalletMap = new HashMap<String, String>();
				subtractWalletMap.put("uId", liverMap.get("uid").toString());
				subtractWalletMap.put("integral", giftsInfo.getGift_price().toString());//扣减礼物的积分价格
				subtractWalletMap.put("orderId", new Date().getTime()+"");
				subtractWalletMap.put("userType", "1");//用户
				subtractWalletMap.put("rType", "38");
				subtractWalletMap.put("option", "1");//1 标示扣减积分   0 就是加积分
				Map<String, String>  xmnMapResp= new HashMap<>();
				try {
					xmnMapResp= this.subtractXmnWalletBlance(subtractWalletMap);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("赠送礼物出错,扣减钱包失败："+liverMap.get("uid").toString());
					//返回状态码 “345” 扣减直播钱包失败 客户端不做处理
					return new BaseResponse(ResponseCode.FAILURE, "赠送礼物出错,扣减直播钱包失败");
				}
				if (xmnMapResp!=null && !xmnMapResp.isEmpty() && xmnMapResp.containsKey("state")) {
					if (Integer.parseInt(xmnMapResp.get("state").toString()) == 0) {//判断是否返回正确
						return this.sendIntegralGiftsHandle(giftsInfoRequest, liverMap, xmnMapResp, giftsInfo, liverInfo, liver_key);
						
					}else if(Integer.parseInt(xmnMapResp.get("state").toString()) == 1){
						return new MapResponse(ResponseCode.FAILURE,"积分不足");	
					}else {
						return new MapResponse(ResponseCode.FAILURE,"赠送礼物失败,操作钱包返回错误");	
					}
				}else {
					return new MapResponse(ResponseCode.FAILURE,"赠送礼物失败,扣减钱包积分失败");	
				}
						
			} catch (Exception e) {
				log.info("赠送礼物出错");
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE,"赠送礼物出错");
			}
		}else if (giftsInfo.getGift_type() == 2) {//如果是鸟币购买礼物
			//优先查找自己礼物表里有无该礼物
			Map<Object, Object> selfGiftParamMap = new HashMap<Object, Object>();
			selfGiftParamMap.put("liver_id", liverMap.get("id").toString());
			selfGiftParamMap.put("gift_id", giftsInfoRequest.getId());
			LiveSelfGiftInfo selfGiftInfo = liveGiftsInfoDao.queryLiveSelfGifts(selfGiftParamMap);
			if (selfGiftInfo!=null && selfGiftInfo.getGiftNums()>0) {
				try {
					//削减用户礼物表的所持有礼物数量 减 1 
					selfGiftParamMap.put("giftNums", selfGiftInfo.getGiftNums()-1);
					int resultNum = liveGiftsInfoDao.modifyLiveSelfGiftNum(selfGiftParamMap);
					if (resultNum>0) {
						return this.sendBirdCoinGiftsHandle(giftsInfoRequest, liverMap, null, giftsInfo, liverInfo, liver_key,0,1);
					}else {
						log.info("赠送礼物出错,扣减礼物数量失败："+liverMap.get("uid").toString());
						return new BaseResponse(ResponseCode.FAILURE, "赠送礼物出错,扣减直播钱包失败");
					}
				} catch (Exception e) {
					log.info("赠送礼物出错，操作自有礼物表出错");
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE, "赠送礼物出错，操作自有礼物表出错");
				}
			}
			
			//自有礼物表为空时  去扣减鸟豆购买礼物    //获取直播 钱包  得到鸟币 余额
			try {
					//扣减钱包鸟币余额
					Map<String, String> subtractWalletMap = new HashMap<String, String>();
					subtractWalletMap.put("giftId",giftsInfo.getId().toString());
					subtractWalletMap.put("uid", liverMap.get("uid").toString());
					subtractWalletMap.put("rtype", "2");//这里是支付服务的消费类型
					subtractWalletMap.put("liveRecordId", giftsInfoRequest.getLiveRecordId());
					subtractWalletMap.put("anchorId", giftsInfoRequest.getAnchorId());
					subtractWalletMap.put("commision", giftsInfo.getGift_price().toString());//这里是支付服务的消费鸟豆
					subtractWalletMap.put("consumeAmount", giftsInfo.getGift_price().toString());//这里是业务服务的消费鸟豆
					subtractWalletMap.put("returnRatio", "0.1");
					subtractWalletMap.put("anchorUid", liverInfo.getUid()+"");//这里是业务服务的消费鸟豆
					subtractWalletMap.put("giftRatio", null==liverInfo.getLedger_ratio()?"0":liverInfo.getLedger_ratio().toString());
					try {
						
						//判断苹果审核账号的key 是否存在redis 永久不返鸟币
						if (stringRedisTemplate.hasKey(Constant.APPLE_ACEPT_ACOUNT_UID)) {
							
							String appleUid = stringRedisTemplate.opsForValue().get(Constant.APPLE_ACEPT_ACOUNT_UID);
							if (!StringUtils.isEmpty(appleUid) && !"null".equals(appleUid)) {
								if (appleUid.indexOf(liverMap.get("uid").toString())>=0) {
									subtractWalletMap.put("returnRatio", "0");
								}
							}else {
								appleUid = propertiesUtil.getValue("appleApprovalUid", "conf_live.properties");
								if (appleUid.indexOf(liverMap.get("uid").toString())>=0) {
									subtractWalletMap.put("returnRatio", "0");
								}
								stringRedisTemplate.opsForValue().set(Constant.APPLE_ACEPT_ACOUNT_UID, appleUid);
							}
							
						}else {
							
							String appleUid = propertiesUtil.getValue("appleApprovalUid", "conf_live.properties");
							if (appleUid.indexOf(liverMap.get("uid").toString())>=0) {
								subtractWalletMap.put("returnRatio", "0");
							}
							stringRedisTemplate.opsForValue().set(Constant.APPLE_ACEPT_ACOUNT_UID, appleUid);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						log.info("获取审核账号UID出错");
						return new MapResponse(ResponseCode.FAILURE, "获取用户基础信息异常");
					}
					
					subtractWalletMap.put("description", giftsInfo.getGift_name());
					
					//更新用户钱包余额
					ResponseData responseData = null;
					//计算返利 鸟币
//					Map<Object, Object>  rebateMap = new HashMap<Object, Object>();
					try {
						
						Map<String, Object> liveWalletInfoMap =  this.getLiveWalletBlance(liverMap.get("uid").toString());
						if (liveWalletInfoMap!=null && liveWalletInfoMap.size()>0) {
							//获取返利结果 根据发送礼物总量累计用户返利结果   （暂时注释）
//							rebateMap = this.getRebate(ArithUtil.div(giftsInfo.getGift_price().doubleValue(), 10), Double.parseDouble(liveWalletInfoMap.get("cumulativeZbalance").toString()));
//							if (rebateMap!=null) {
								//返利金额
//								subtractWalletMap.put("zbalance", rebateMap.get("rebate").toString()); ;
//								subtractWalletMap.put("remarks", new BigDecimal(rebateMap.get("rebateRatio").toString()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP)+"%");
//								subtractWalletMap.put("description", giftsInfo.getGift_name());
//							}
							
							//用户发多少礼物(鸟豆) 返多少金额
							subtractWalletMap.put("zbalance", giftsInfo.getGift_price().divide(new BigDecimal(10)).toString()); ;
							subtractWalletMap.put("remarks", "100%");
//							subtractWalletMap.put("description", giftsInfo.getGift_name());
							
						}
						//分账金额
						if (liverInfo.getLedger_ratio()!=null && liverInfo.getLedger_ratio().compareTo(new BigDecimal(0))>0 ) {//&& liverInfo.getLedger_ratio().compareTo(new BigDecimal(1))<0
							
							//分账金额  及 比例  先转换为真实金额 乘以分账比例  得到保留两位数 后的金额
							Integer percentAmount = Double.valueOf(giftsInfo.getGift_price().multiply(liverInfo.getLedger_ratio().multiply(new BigDecimal(100)))
										.setScale(2, BigDecimal.ROUND_HALF_UP).toString()).intValue(); 
							  
							//分账完的金额  乘以100  用于展示 去除小数
							liverMap.put("percentAmount", percentAmount); 
							
						}else {
							liverMap.put("percentAmount", 0);
						}
						//扣减直播用户钱包 
						responseData = this.subtractLiveWalletBlanceBusiness(subtractWalletMap);//subtractLiveWalletBlanceBusiness
						if (responseData.getState() == 0) {
							return this.sendBirdCoinGiftsHandle(giftsInfoRequest,liverMap,responseData,giftsInfo,liverInfo,liver_key,1,1);
						}else if(responseData.getState() == 4){
							return new MapResponse(ResponseCode.FAILURE,"鸟豆余额不足");	
						}else{
							return new MapResponse(ResponseCode.FAILURE,responseData.getMsg());	
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						log.info("赠送礼物出错,扣减钱包失败："+liverMap.get("uid").toString());
						//返回状态码 “345” 扣减直播钱包失败 客户端不做处理
						return new BaseResponse(ResponseCode.FAILURE, "赠送礼物出错,扣减直播钱包失败");
					}
					
			} catch (Exception e) {
				log.info("赠送礼物出错");
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE,"赠送礼物出错");
			}
		}else {
			return new MapResponse(ResponseCode.FAILURE,"赠送礼物获取信息失败");
		}
		
	}
	
	/**
	 * 描述：发送 “鸟币” 礼物后累计鸟蛋，经验操作
	 * @param giftsInfoRequest，liverMap，responseData，giftsInfo，liverInfo，liver_key：缓存redisKey,advancedStatus接收状态
	 * */
	public Object sendBirdCoinGiftsHandle(GiftsInfoRequest giftsInfoRequest,Map<Object, Object> liverMap,
			ResponseData responseData,LiveGiftsInfo giftsInfo,LiverInfo liverInfo,String liver_key,int isAcount,int advancedStatus){
		
		Map<Object, Object> givenGigftRecordMap = new HashMap<Object, Object>();
		givenGigftRecordMap.put("gived_gift_key", System.currentTimeMillis()+giftsInfoRequest.getUid());
//		givenGigftRecordMap.put("gived_gift_key", new Date().getTime()+giftsInfoRequest.getUid());
		givenGigftRecordMap.put("uid", giftsInfoRequest.getUid());
		givenGigftRecordMap.put("live_record_id", giftsInfoRequest.getLiveRecordId());//直播记录id
		givenGigftRecordMap.put("liver_id", liverMap.get("id").toString());//观看直播的用户I
		givenGigftRecordMap.put("liver_utype",  liverMap.get("utype").toString());//直播用户类型： 1 主播 2 普通用户
		givenGigftRecordMap.put("anchor_id", giftsInfoRequest.getAnchorId());//主播用户id
		givenGigftRecordMap.put("anchor_room_no", giftsInfoRequest.getRoomNo());//主播房间编号
		givenGigftRecordMap.put("gift_id", giftsInfo.getId());//礼物id
		givenGigftRecordMap.put("sale_product_id", giftsInfo.getSaleProductId());//礼物id
		givenGigftRecordMap.put("gift_name", giftsInfo.getGift_name());//礼物名称
		givenGigftRecordMap.put("gift_nums", 1);//礼物个数
		givenGigftRecordMap.put("gift_price", giftsInfo.getGift_price());//礼物单价（单位鸟豆）
		if (giftsInfo.getBackCoinStatus() ==0) {
			givenGigftRecordMap.put("return_coin", responseData.getResultMap().get("returnCoin")==null?0:responseData.getResultMap().get("returnCoin"));//返还鸟币（单位鸟币） 除以10
		}else {
			givenGigftRecordMap.put("return_coin", 0);//返还鸟币（单位鸟币） 除以10
		}
		givenGigftRecordMap.put("gift_experience", giftsInfo.getGift_experience());//礼物代表的经验值
		givenGigftRecordMap.put("create_time", DateUtil.format(new Date()));//创建时间
		givenGigftRecordMap.put("advanced_status", "1");//预处理状态  1 预处理  2 处理成功 3 处理失败
		givenGigftRecordMap.put("status", "1");//扣款状态  1 扣款成功  2 扣款失败
		givenGigftRecordMap.put("percentAmount", liverMap.get("percentAmount"));//本次送礼主播分账后鸟蛋
		givenGigftRecordMap.put("uid_relation_chain", liverMap.get("uid_relation_chain")==null?"":liverMap.get("uid_relation_chain"));//本次送礼主播分账后鸟蛋
		givenGigftRecordMap.put("gift_source", giftsInfo.getGiftSource());//本次送礼主播分账后鸟蛋
		givenGigftRecordMap.put("gift_ratio", null==liverInfo.getLedger_ratio()?"0":liverInfo.getLedger_ratio().toString());
		//钱包扣款记录ID  如果是扣款成功的 存入扣款记录ID  否则 补 0 
		if (responseData!=null && null!=responseData.getResultMap().get("walletRecordId")  && !StringUtils.isEmpty(responseData.getResultMap().get("walletRecordId").toString()) ) {
			givenGigftRecordMap.put("wallet_record_id", Long.valueOf(responseData.getResultMap().get("walletRecordId")));
		}else {
			givenGigftRecordMap.put("wallet_record_id", 0);
		}
		
		try {
			//新增赠送礼物记录
			Integer giftRecordNum=liveGiftsInfoDao.addLiveGivedGiftRecord(givenGigftRecordMap);
			Integer giftRecordId=0;
			if(giftRecordNum>0){
				giftRecordId=givenGigftRecordMap.get("id")==null?0:Integer.valueOf(givenGigftRecordMap.get("id").toString());
				log.info("插入礼物记录成功：礼物记录id="+giftRecordId);
				
				//插入V客分账信息 
				if (responseData!=null && responseData.getResultMap()!=null && responseData.getResultMap().get("vkeUid")!=null) {
					Map<String,String> resultMap = responseData.getResultMap();
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("givedId", giftRecordId);  //送礼ID
					map.put("anchorUid", resultMap.get("anchorUid"));  //主播UID
					map.put("anchorPhone", resultMap.get("anchorPhone"));  //主播手机号
					map.put("anchorNname", resultMap.get("anchorNname"));  //主播昵称
					map.put("vkeUid", resultMap.get("vkeUid"));  //V客UID
					map.put("vkePhone", resultMap.get("vkePhone"));  //V客手机号
					map.put("vkeNname", resultMap.get("vkeNname"));  //V客昵称
					map.put("vkeRankid", resultMap.get("vkeRankid"));  //V客等级编号
					map.put("vkeRankname", resultMap.get("vkeRankname"));  //V客等级名称
					map.put("vkeRatio", resultMap.get("vkeRatio"));  //V客分成比
					liveGiftsInfoDao.insertVkeRatioRecord(map);
				}
				
			}else{
				log.error("发送礼物失败，插入礼物记录失败");
				return new BaseResponse(ResponseCode.FAILURE, "发送礼物失败");
			}
			
			//累计用户经验
			liverMap.put("give_gifts_nums", Integer.parseInt(liverMap.get("give_gifts_nums").toString())+1);
			
			//获取查询群组号
			liverMap.put("GroupId", liverInfo.getGroup_id());
			
			//累计用户经验
//			anchorViewerMemberRankService.addLiverViewerExpe(liverMap,giftsInfo.getGift_experience(),liver_key,0);
			
			//累计鸟蛋返回状态
			MapResponse responseBirdEgg = null;
			//累计鸟蛋  isAcount 等于1  计算与主播的分账
//			if (isAcount == 1) {
////				BigDecimal birdEggAmount = liverInfo.getLedger_ratio().multiply(giftsInfo.getGift_price());
//				responseBirdEgg = anchorViewerMemberRankService.accumulativeBirdEgg(giftRecordId,liverInfo, new BigDecimal(liverMap.get("percentAmount").toString()),0,1);
//			}else {
//				responseBirdEgg = anchorViewerMemberRankService.accumulativeBirdEgg(giftRecordId,liverInfo, new BigDecimal(0),0,1);
//			}
			
//			Map<Object, Object> message = new HashMap<Object, Object>();
			JSONObject jsonObject = new JSONObject();
			if (isAcount == 1) {
//				message.put("gift_price", new BigDecimal(liverMap.get("percentAmount").toString()));
				jsonObject.put("gift_price", new BigDecimal(liverMap.get("percentAmount").toString()));
			}else {
				jsonObject.put("gift_price", 0);
//				message.put("gift_price",0);
			}
			
			jsonObject.put("giftRecordId", giftRecordId);
			jsonObject.put("liverInfo", liverInfo);
			jsonObject.put("type", 0);
			jsonObject.put("giftNum", 1);
			//累计用户经验参数
			jsonObject.put("liverMap", liverMap);
			jsonObject.put("experience", giftsInfo.getGift_experience());
			jsonObject.put("price", giftsInfo.getGift_price());//礼物价格
			jsonObject.put("experienceType", 0);
			jsonObject.put("liverKey", liver_key);
			//是否发生广播消息
			jsonObject.put("isRedio", giftsInfo.getIsRadio());
			//直播间送礼返回用户鸟币累计
			jsonObject.put("returnCoin", responseData.getResultMap().get("returnCoin"));
			
			producerServiceImpl.send(topicSendBirdEgg, jsonObject.toString());
			
			//判断这个礼品有无关联大礼包
//			if (giftsInfo.getIs_gift_bag()==1) {
//				List<Map<Object,Object>> resultList=selfGiftService.queryDrawList(giftsInfo.getGift_bag_id());
//				if(resultList==null || resultList.size()<=0){
//					return new BaseResponse(ResponseCode.SUCCESS, "未获取到大礼包中的礼品");
//				}
//				//大礼包生成redis key    当前时间_UID_礼包Id
//				String giftBag_rediskey = new Date().getTime()+"_"+liverMap.get("uid")+"_"+giftsInfo.getGift_bag_id();
//				RandomGift.initRedisDrawMap(giftBag_rediskey, stringRedisTemplate,(long) 120, resultList);
//				//将礼包存放的redis key 返回到客户端 
//				Map<Object, Object> giftMap = new HashMap<Object, Object>();
//				giftMap.put("giftBag", giftBag_rediskey);
//				giftMap.put("onceBirdEgg", liverMap.get("percentAmount"));
//				MapResponse response = null;
//				response =  new MapResponse(ResponseCode.GIFT_SURPRISING,"礼物有关联大礼包");//返回342 标示有关联大礼包
//				response.setResponse(giftMap);
//				return response;
//			}else {
			
			//本次送礼主播获取分账鸟蛋
			Map<Object, Object> resultMap = new HashMap<>();
			if (responseBirdEgg!=null && responseBirdEgg.getState() == 100) {
				resultMap.put("totalBirdEgg", responseBirdEgg.getResponse().get("totalBirdEgg"));
			}else {
				resultMap.put("totalBirdEgg", "");
			}
			resultMap.put("subtractBirdCoin", giftsInfo.getGift_price());
			resultMap.put("addBirdCoin", responseData.getResultMap().get("returnCoin"));
			resultMap.put("onceBirdEgg", liverMap.get("percentAmount"));
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"赠送礼物成功");
			response.setResponse(resultMap);
			return response;
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE,"赠送礼物失败");
		}
	}
	
	
	/**
	 * 描述：发送 “积分” 礼物后累计鸟蛋，经验操作
	 * @param giftsInfoRequest，liverMap，xmnMapResp，giftsInfo，liverInfo，liver_key：缓存rediskey
	 * */
	public Object sendIntegralGiftsHandle(GiftsInfoRequest giftsInfoRequest,Map<Object, Object> liverMap,
			Map<String, String> xmnMapResp,LiveGiftsInfo giftsInfo,LiverInfo liverInfo,String liver_key){
		
		Map<Object, Object> givenGigftRecordMap = new HashMap<Object, Object>();
		givenGigftRecordMap.put("gived_gift_key", new Date().getTime()+giftsInfoRequest.getUid());//赠送记录唯一序列号（redis校验）
		givenGigftRecordMap.put("uid", giftsInfoRequest.getUid());//直播记录id
		givenGigftRecordMap.put("live_record_id", giftsInfoRequest.getLiveRecordId());//直播记录id
		givenGigftRecordMap.put("liver_id", liverMap.get("id").toString());//观看直播的用户I
		givenGigftRecordMap.put("liver_utype",  liverMap.get("utype").toString());//直播用户类型： 1 主播 2 普通用户
		givenGigftRecordMap.put("anchor_id", giftsInfoRequest.getAnchorId());//主播用户id
		givenGigftRecordMap.put("anchor_room_no", giftsInfoRequest.getRoomNo());//主播房间编号
		givenGigftRecordMap.put("gift_id", giftsInfo.getId());//礼物id
		givenGigftRecordMap.put("gift_name", giftsInfo.getGift_name());//礼物名称
		givenGigftRecordMap.put("gift_nums", 1);//礼物个数
		givenGigftRecordMap.put("gift_price", giftsInfo.getGift_price());//礼物单价（单位鸟币）
		givenGigftRecordMap.put("gift_experience", giftsInfo.getGift_experience());//礼物代表的经验值
		givenGigftRecordMap.put("create_time", DateUtil.format(new Date()));//创建时间
		givenGigftRecordMap.put("advanced_status", "1");//预处理状态  1未接收  2 预接收 3 已接收
		givenGigftRecordMap.put("status", "1");//扣款状态  1 扣款成功  2 扣款失败
		
		//钱包扣款记录ID  如果是扣款成功的 存入扣款记录ID  否则 补 0 
		if (xmnMapResp!=null && null != xmnMapResp.get("id") && !StringUtils.isEmpty(xmnMapResp.get("id").toString())) {
			givenGigftRecordMap.put("wallet_record_id", Long.valueOf(xmnMapResp.get("id")));
		}else{
			givenGigftRecordMap.put("wallet_record_id", 0);
		}
		
		try {
			//新增赠送礼物记录
			liveGiftsInfoDao.addLiveGivedGiftRecord(givenGigftRecordMap);
			//首先累计用户经验
			liverMap.put("give_gifts_nums", Integer.parseInt(liverMap.get("give_gifts_nums").toString())+1);
			
			//获取查询群组号
			liverMap.put("GroupId", liverInfo.getGroup_id());
			
			//累计观众的经验值
			anchorViewerMemberRankService.addLiverViewerExpe(liverMap,giftsInfo.getGift_experience(),liver_key,0);
			
			return new MapResponse(ResponseCode.SUCCESS,"赠送礼物成功");	
			
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE,"赠送礼物失败");	
		}
	}
	
	/**
	 * 检查发礼物 同步redis的key 是否存在
	 * */
	public boolean synchRedisMethod(String redisKey){
		boolean bool = false;
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(redisKey, 1);
		if (resultNum > 1) {
			bool = true;
		}
		return bool;
	}
	
	
	/**
	 * 获取直播的钱包信息
	 * @param uid 
	 * @return map
	 * */
	public Map<String, Object> getLiveWalletBlance(String uid) throws Exception{
		Map<String, Object> blanceMap = new HashMap<String, Object>();
		try {
			LiveWalletService.Client  client = null;
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client =new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			Map<String, String> getWalletMap = new HashMap<String, String>();
			getWalletMap.put("uid", uid);
			ResponseData responseData =	client.getLiveWallet(getWalletMap);
			if (responseData.getState() == 0) {
				Map<String, String> liverwalletMap = responseData.getResultMap();
				String commision = liverwalletMap.get("commision").toString();
				String balance = liverwalletMap.get("balance").toString();
				blanceMap.put("commision", commision);//鸟豆
				blanceMap.put("balance", balance);//鸟蛋
				blanceMap.put("turnEggOut", liverwalletMap.get("turnEggOut"));//转出鸟蛋总额
				blanceMap.put("turnCoinOut", liverwalletMap.get("turnCoinOut"));//转出鸟豆总额
				blanceMap.put("zbalance", liverwalletMap.get("zbalance"));//鸟币余额
				blanceMap.put("cumulativeZbalance", liverwalletMap.get("cumulativeZbalance"));//累加鸟币
				blanceMap.put("restrictive", liverwalletMap.get("restrictive"));//鸟币是否限制使用
				blanceMap.put("sellerCoin", liverwalletMap.get("sellerCoin"));//商家储值卡额度
				blanceMap.put("availableExchangeCoin", liverwalletMap.get("availableExchangeCoin"));//可用于兑换储值卡的鸟币额(实际兑换时，需结合zbalance值使用)
				blanceMap.put("usedExchangeCoin", liverwalletMap.get("usedExchangeCoin"));//已用于兑换储值卡的鸟币额
				
				BigDecimal zbalance = new BigDecimal(liverwalletMap.get("zbalance")==null?"0":liverwalletMap.get("zbalance").toString());
				BigDecimal limitBalance = new BigDecimal(liverwalletMap.get("limitBalance")==null?"0":liverwalletMap.get("limitBalance").toString());
				//鸟币减去限额后的鸟币余额
				if (zbalance.subtract(limitBalance).compareTo(new BigDecimal("0"))>0) {
					blanceMap.put("zbalanceCoin", zbalance.subtract(limitBalance));//可用鸟币
				}else {
					blanceMap.put("zbalanceCoin", 0);//可用鸟币
				}
				blanceMap.put("limitBalance", liverwalletMap.get("limitBalance"));//限额鸟币
				
				if (liverwalletMap.get("zbalanceLock")!=null && liverwalletMap.get("zbalanceLock").toString().equals("true")) {
					blanceMap.put("zbalance", 0);//鸟币余额
					blanceMap.put("zbalanceCoin", 0);//可用鸟币余额
					blanceMap.put("zbalanceLock", liverwalletMap.get("zbalanceLock"));//鸟币是否冻结
				}else {
					blanceMap.put("zbalanceLock", "false");//鸟币是否冻结
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户钱包失败"+uid);
			throw new Exception("钱包余额查询失败"+uid);
		}finally{
			thriftUtil.coloseTransport();
		}
		return blanceMap;
	}
	
	/**
	 * 寻蜜鸟会员钱包信息
	 * @param uid  1 用户类型
	 * @return map
	 * */
	public Map<String, String> getXmnWalletBlance(String uid) throws Exception{
		SynthesizeService.Client client = null;
		Map<String, String> xmnWalletMap = new HashMap<String, String>();
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			client = new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			xmnWalletMap =	client.getWalletBalance(uid, 1);
			if (xmnWalletMap!=null && xmnWalletMap.size()>0) {
				
				if (xmnWalletMap.get("zbalanceLock")!=null && xmnWalletMap.get("zbalanceLock").toString().equals("true")) {
					xmnWalletMap.put("commision", "0");
					xmnWalletMap.put("zbalance", "0");
					xmnWalletMap.put("zbalanceLock", xmnWalletMap.get("zbalanceLock"));
				}else {
					xmnWalletMap.put("zbalanceLock", "false");//余额是否冻结
				}
			}
			
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("钱包余额查询失败"+uid);
		}finally{
			thriftUtil.coloseTransport();
		}
		return xmnWalletMap;
	}
	
	/**
	 * 会员储值卡钱包 列表   
	 * @param Map<String, String> map (uid ，sellerid)
	 * @return List<Map<String,String>>
	 * */
	public List<Map<String,String>> getSellerCardBlance(Map<String, String> map) throws Exception{
		ValueCardService.Client client = null;
		List<Map<String, String>> sellerCardMapList = new ArrayList<Map<String,String>>();
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ValueCardService");
			client = new ValueCardService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			sellerCardMapList =	client.getValueCardMsg(map);
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("储值卡查询失败："+map.get("uid")+","+map.get("sellerid"));
		}finally{
			thriftUtil.coloseTransport();
		}
		return sellerCardMapList;
	}
	
	/**
	 * 会员的单家 商家储值卡 钱包 
	 * @param Map<String, String> map (uid ，sellerid)
	 * @return Map<String,String>
	 * */
	public Map<String,String> getSellerCardBlanceInfo(Map<String, String> map) throws Exception{
		ValueCardService.Client client = null;
		List<Map<String, String>> sellerCardMapList = new ArrayList<Map<String,String>>();
		Map<String, String> sellerCardMap = new HashMap<String,String>();
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ValueCardService");
			client = new ValueCardService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			sellerCardMapList =	client.getValueCardMsg(map);
			if (sellerCardMapList.size()>0 && sellerCardMapList.size() ==1) {
				sellerCardMap = sellerCardMapList.get(0);
			}
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("储值卡查询失败："+map.get("uid")+","+map.get("sellerid"));
		}finally{
			thriftUtil.coloseTransport();
		}
		return sellerCardMap;
	}
	
	/**
	 * 获取具体商家储值卡 充值额度
	 * @param Map<String, String> map (sellerid)
	 * @return Map<String,String>
	 * */
	public Map<String,String> getSellerCardTotalBalance(Map<String, String> map) throws Exception{
		ValueCardService.Client client = null;
		Map<String, String> sellerCardBalanceMap = new HashMap<String,String>();
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ValueCardService");
			client = new ValueCardService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			sellerCardBalanceMap =	client.getValueCardBalance(map);
			if (sellerCardBalanceMap == null) {
				log.info("储值卡查询失败："+map.get("uid")+","+map.get("sellerid"));
				throw new Exception("商家储值卡 充值额度查询失败");
			}else {
				return sellerCardBalanceMap;
			}
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("储值卡查询失败："+map.get("uid")+","+map.get("sellerid"));
		}finally{
			thriftUtil.coloseTransport();
		}
	}
	
	/**
	 * 用户 1
	 * 验证用户钱包密码
	 * @param uid
	 * @param pwd
	 * @return
	 * @throws Exception 
	 */
	public Integer checkWalletPwd(String uid,String pwd) throws Exception{
		SynthesizeService.Client client = null;
		Integer result = 1;//
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			client = new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			result =	client.checkWalletPwd(uid, pwd, 1);
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("钱包余额查询失败"+uid);
		}finally{
			thriftUtil.coloseTransport();
		}
		return result;
	}
	
	
	/**
	 * 扣减 直播用户的钱包  
	 * @param uid 
	 * @return map
	 * */
	public ResponseData subtractLiveWalletBlance(Map<String, String> substractMap) throws Exception{
		LiveWalletService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			ResponseData responseData =	client.updateWalletAmount(substractMap);
			log.info("获取直播钱包成功,用户："+substractMap.get("uid"));
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("赠送礼物扣减鸟币失败"+substractMap);
			throw new Exception("赠送礼物扣减鸟币失败"+substractMap.get("uid"));
		}finally{
			thriftUtil.coloseTransport();
		}
	}
	
	/**
	 * 扣减 直播用户的钱包  --用作扣鸟币的操作 2017-4-10 15:36:06 新接口
	 * @param uid 
	 * @return map
	 * */
	public ResponseData subtractLiveWalletCoinBlance(Map<String, String> substractMap) throws Exception{
		LiveWalletService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			ResponseData responseData =	client.liveWalletOption(substractMap);
			log.info("获取直播钱包成功,用户："+substractMap.get("uid"));
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("赠送礼物扣减鸟币失败"+substractMap);
			throw new Exception("赠送礼物扣减鸟币失败"+substractMap.get("uid"));
		}finally{
			thriftUtil.coloseTransport();
		}
	}
	
	/**
	 * 扣减 直播用户的钱包    调用业务服务的扣钱
	 * @param uid 
	 * @return map
	 * */
	public ResponseData subtractLiveWalletBlanceBusiness(Map<String, String> substractMap) throws Exception {
		LiveOrderService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("LiveOrderService");
			client = new LiveOrderService.Client(tMultiplexedProtocol); 
			thriftBusinessUtil.openTransport();
			ResponseData responseData =	client.consumeGift(substractMap);
			
			log.info("获取直播钱包成功,用户："+substractMap.get("uid"));
			return responseData;
		}catch(FailureException e){
			int state= e.getState();
			if(state==2){
				return new ResponseData(4, "", null);
			}else{
				log.info("赠送礼物扣减鸟币失败"+substractMap);
				throw new Exception("赠送礼物扣减鸟币失败"+substractMap.get("uid"));
			}
		}catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			log.info("赠送礼物扣减鸟币失败"+substractMap);
			throw new Exception("赠送礼物扣减鸟币失败"+substractMap.get("uid"));
		}
		finally{
			thriftBusinessUtil.coloseTransport();
		}
	}
	
	
	/**
	 * 扣减寻蜜鸟用户的钱包积分余额
	 * @param uid 
	 * @return map
	 * 2016-8-18 10:52:52
	 * */
	public Map<String, String>  subtractXmnWalletBlance(Map<String, String> substractMap) throws Exception{
		SynthesizeService.Client client = null;
		Map<String, String> xmnWalletMap = new HashMap<String, String>();
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			client = new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			xmnWalletMap =	client.updateWalletAmount(substractMap);
			return xmnWalletMap;
		}catch(Exception e){
			log.error("钱包余额查询失败");
			e.printStackTrace();
			throw new Exception("操作钱包失败："+substractMap.get("uid"));
		}finally{
			thriftUtil.coloseTransport();
		}
//		return xmnWalletMap;
	}
	
	
	/**
	 * 批量 直播用户的钱包  
	 * @param uid 
	 * @return map
	 * */
	public ResponseData updateLiveWalletsForList(List<Map<String, String>> walletList) throws Exception{
		LiveWalletService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//client =(LiveWalletService.Client)(liveWalletServiceClient.getClient());
			ResponseData responseData =	client.updateLiveWalletsForList(walletList);
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("批量同步主播鸟蛋失败");
			throw new Exception("批量同步主播鸟蛋失败");
		}finally{
			thriftUtil.coloseTransport();
		}
	}
	
	
	/**
	 * 
	* @Description: 更新直播钱包钱包余额  主播退出直播间 累计鸟蛋
	* @return ResponseData
	 */
	public ResponseData updateWalletAmount(Map<String,String> walletMap) throws Exception{
		LiveOrderService.Client client = null;
		ResponseData responseData =null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("LiveOrderService");
			 client = new LiveOrderService.Client(tMultiplexedProtocol);		
			 thriftBusinessUtil.openTransport();
			responseData =	client.anchorEggReceipts(walletMap);
		} catch (Exception e) {
			log.error("更新直播钱包余额异常");
			e.printStackTrace();
			throw new Exception("更新直播钱包余额异常，请联系管理员");
		}finally{
			thriftBusinessUtil.coloseTransport();
		}
		return responseData;
	}
	
	/**
	 * 查询用户开通的美食体验卡  
	 * @return map
	 * */
	public ResponseData queryExperienceCard(Map<String,String> paramMap) throws Exception{
		ExperiencecardService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ExperiencecardService");
			client = new ExperiencecardService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			ResponseData responseData =	client.getExperiencecard(paramMap);
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("批量同步主播鸟蛋失败");
			throw new Exception("批量同步主播鸟蛋失败");
		}finally{
			thriftUtil.coloseTransport();
		}
	}

	/**
	 * 批量获取美食体验卡
	 * @param uids
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getExperiencecardByUids(List<Integer> uids) throws Exception{
		ExperiencecardService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ExperiencecardService");
			client = new ExperiencecardService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			List<Map<String,String>> cardList =	client.getExperiencecardByUids(uids);
			return cardList;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("批量获取美食体验卡失败");
			throw new Exception("批量获取美食体验卡失败");
		}finally{
			thriftUtil.coloseTransport();
		}
	}
	
	
	
	
	/**
	 * 
	* @Title: getRebate
	* @Description:  获取返利鸟币
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object, Object> getRebate(double giftMoney,double cumulativeZbalance) throws Exception {
		//用户累加鸟币数+礼物金额数
		double sumZbalance = cumulativeZbalance + giftMoney;
		
		//获取返利条件
		double oneMin = Double.parseDouble(propertiesUtil.getValue("rebate_condition_one_min", "conf_live.properties"));
		double oneMax = Double.parseDouble(propertiesUtil.getValue("rebate_condition_one_max", "conf_live.properties"));
		double twoMax = Double.parseDouble(propertiesUtil.getValue("rebate_condition_two_max", "conf_live.properties"));
		double threeMax = Double.parseDouble(propertiesUtil.getValue("rebate_condition_three_max", "conf_live.properties"));
		double fourMax = Double.parseDouble(propertiesUtil.getValue("rebate_condition_four_max", "conf_live.properties"));
		double fiveMax = Double.parseDouble(propertiesUtil.getValue("rebate_condition_five_max", "conf_live.properties"));
		
		//返利比例
		double ratioOne = Double.parseDouble(propertiesUtil.getValue("rebate_ratio_one", "conf_live.properties"));
		double ratioTwo = Double.parseDouble(propertiesUtil.getValue("rebate_ratio_two", "conf_live.properties"));
		double ratioThree = Double.parseDouble(propertiesUtil.getValue("rebate_ratio_three", "conf_live.properties"));
		double ratioFour = Double.parseDouble(propertiesUtil.getValue("rebate_ratio_four", "conf_live.properties"));
		double ratioFive = Double.parseDouble(propertiesUtil.getValue("rebate_ratio_five", "conf_live.properties"));
		
		//返利金额
		double rebate = 0;
		
		//结果集
		Map<Object, Object> map = new HashMap<>();
		
		//判断之前累加鸟币数所在的区间  -1 小于 0等于  1大于
		if (cumulativeZbalance < oneMax) {
			//(0,50]
			if (sumZbalance <= oneMax) {
				rebate = ArithUtil.mul(giftMoney, ratioOne);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioOne);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(oneMax, cumulativeZbalance), ratioOne), rebate);
			}
			
			if (sumZbalance > oneMax && sumZbalance <= twoMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, oneMax), ratioTwo), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioTwo);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(twoMax, oneMax), ratioTwo), rebate);
			}
			
			if (sumZbalance > twoMax && sumZbalance <= threeMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, twoMax), ratioThree), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioThree);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(threeMax, twoMax), ratioThree), rebate);
			}
			
			if (sumZbalance > threeMax && sumZbalance <= fourMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, threeMax), ratioFour), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFour);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fourMax, threeMax), ratioFour), rebate);
			}
			
			if (sumZbalance > fourMax && sumZbalance <= fiveMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, fourMax), ratioFive), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}
			
		}else if (cumulativeZbalance >= oneMax && cumulativeZbalance < twoMax) {
			//(50,100]
			if (sumZbalance <= twoMax) {
				rebate = ArithUtil.mul(giftMoney, ratioTwo);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioTwo);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(twoMax, cumulativeZbalance), ratioTwo), rebate);
			}
			
			if (sumZbalance > twoMax && sumZbalance <= threeMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, twoMax), ratioThree), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioThree);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(threeMax, twoMax), ratioThree), rebate);
			}
			
			if (sumZbalance > threeMax && sumZbalance <= fourMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, threeMax), ratioFour), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFour);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fourMax, threeMax), ratioFour), rebate);
			}
			
			if (sumZbalance > fourMax && sumZbalance <= fiveMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, fourMax), ratioFive), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}
			
		}else if (cumulativeZbalance >= twoMax && cumulativeZbalance < threeMax) {
			//(100,200]
			if (sumZbalance <= threeMax) {
				rebate = ArithUtil.mul(giftMoney, ratioThree);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioThree);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(threeMax, cumulativeZbalance), ratioThree), rebate);
			}
			
			if (sumZbalance > threeMax && sumZbalance <= fourMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, threeMax), ratioFour), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFour);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fourMax, threeMax), ratioFour), rebate);
			}
			
			if (sumZbalance > fourMax && sumZbalance <= fiveMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, fourMax), ratioFive), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}
			
		}else if (cumulativeZbalance >= threeMax && cumulativeZbalance < fourMax) {
			//(200,500]
			if (sumZbalance <= fourMax) {
				rebate = ArithUtil.mul(giftMoney, ratioFour);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFour);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fourMax, cumulativeZbalance), ratioFour), rebate);
			}
			
			if (sumZbalance > fourMax && sumZbalance <= fiveMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, fourMax), ratioFive), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fiveMax, fourMax), ratioFive), rebate);
			}
			
			if (sumZbalance > fiveMax && ArithUtil.sub(sumZbalance, 1000) <= oneMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, 1000), ratioOne), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(oneMax, oneMin), ratioOne), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > oneMax && ArithUtil.sub(sumZbalance, 1000) <= twoMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), oneMax), ratioTwo), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(twoMax, oneMax), ratioTwo), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > twoMax && ArithUtil.sub(sumZbalance, 1000) <= threeMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), twoMax), ratioThree), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(threeMax, twoMax), ratioThree), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > threeMax && ArithUtil.sub(sumZbalance, 1000) <= fourMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), threeMax), ratioFour), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fourMax, threeMax), ratioFour), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > fourMax && ArithUtil.sub(sumZbalance, 1000) <= fiveMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), fourMax), ratioFive), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fiveMax, fourMax), ratioFive), rebate);
				
			}
			
		}else if (cumulativeZbalance >= fourMax && cumulativeZbalance < fiveMax) {
			//(500,1000)
			if (sumZbalance <= fiveMax) {
				rebate = ArithUtil.mul(giftMoney, ratioFive);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fiveMax, cumulativeZbalance), ratioFive), rebate);
			}
			
			if (sumZbalance > fiveMax && ArithUtil.sub(sumZbalance, 1000) <= oneMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(sumZbalance, 1000), ratioOne), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(oneMax, oneMin), ratioOne), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > oneMax && ArithUtil.sub(sumZbalance, 1000) <= twoMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), oneMax), ratioTwo), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(twoMax, oneMax), ratioTwo), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > twoMax && ArithUtil.sub(sumZbalance, 1000) <= threeMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), twoMax), ratioThree), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(threeMax, twoMax), ratioThree), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > threeMax && ArithUtil.sub(sumZbalance, 1000) <= fourMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), threeMax), ratioFour), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fourMax, threeMax), ratioFour), rebate);
			}
			
			if (ArithUtil.sub(sumZbalance, 1000) > fourMax && ArithUtil.sub(sumZbalance, 1000) <= fiveMax) {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(ArithUtil.sub(sumZbalance, 1000), fourMax), ratioFive), rebate);
				map.put("rebate", rebate);
				map.put("rebateRatio", ratioFive);
				return map;
			}else {
				rebate = ArithUtil.add(ArithUtil.mul(ArithUtil.sub(fiveMax, fourMax), ratioFive), rebate);
				
			}
			
		}
		return map;
	}
	
	
}
