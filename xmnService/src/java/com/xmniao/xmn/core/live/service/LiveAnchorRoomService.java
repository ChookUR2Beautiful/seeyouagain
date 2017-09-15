package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.base.UploadClientFactory;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.AnchorManagerDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveRoomDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.dao.PrivateMessageDao;
import com.xmniao.xmn.core.live.dao.ViewerUserListDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiveSelfGiftInfo;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.DistanceUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.VersionUtil;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.xmer.dao.CouponDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;

/**
 * 描述： 用户或主播进入房间
 * @author yhl
 * 2016年8月15日11:34:14
 * */
@Service
public class LiveAnchorRoomService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveAnchorRoomService.class);
	
	private static final String CHANNEL_STOP_NUMS = "channel_stop_nums";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
	 * 主播|观众通用dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 直播记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 房间观众Dao
	 * */
	@Autowired
	private ViewerUserListDao viewerUserListDao;
	/**
	 * 个人中心dao
	 * */
	@Autowired
	private PersonalCenterDao personalCenterDao;
	@Autowired
	private PersonalCenterService personalCenterService;
	
	/**
	 * 直播redis service
	 * */
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	/**
	 * 房间观众service
	 * */
	@Autowired
	private ViewerUserListService viewerUserListService;
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 用于获取主播鸟蛋
	 * */
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	/**
	 * 礼物dao
	 * */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	/**
	 * sessionTokenService
	 * */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入sellerInfoDao
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	/**
	 * 注入sellerInfoDao
	 */
	@Autowired
	private LiveRobotService liveRobotService;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil  propertiesUtil;
	/**
	 * 注入pushSingleService
	 */
	@Autowired
	private PushSingleService pushSingleService;
	
	@Autowired
	private SelfGiftService selfGiftService;
	
	/**
	 * 注入anchorManagerDao
	 */
	@Autowired
	private AnchorManagerDao anchorManagerDao;
	
	/**
	 * 注入privatemessageDao
	 */
	@Autowired
	private PrivateMessageDao privatemessageDao;
	
	/**
	 * 注入liveRoomDao
	 */
	@Autowired
	private LiveRoomDao liveRoomDao;
	
	/**
	 * 注入anchorLiveRecordService
	 */
	@Autowired 
	private AnchorLiveRecordService anchorLiveRecordService;
	
	/**
	 * 注入couponDao
	 */
	@Autowired 
	private CouponDao couponDao;
	
	@Autowired
	private UrsInfoDao ursInfoDao;

	@Autowired
	private AnchorSignTypeService anchorSignTypeService;
	
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	@Autowired
	private TopicInfo userExitRoomExperienceInfo;
	
	@Autowired
	private TopicInfo vstarRewardIssueInfo;

	@Autowired
	private KSCloudService ksCloudService;
	
	/**
	 * FastDfs客户端工厂
	 */
	@Autowired
	private UploadClientFactory uploadClient;
	
	/**
	 * 描述：用户/主播进入房间 开始直播
	 * @param liverRoomRequest
	 * @return object
	 * 2016-8-16 15:16:54
	 * */
	public Object userEntryRoom(LiverRoomRequest liverRoomRequest){
		try {
			String uid = null;
			//有传token则默认为登录状态
			if (liverRoomRequest.getSessiontoken() != null && StringUtils.isNotEmpty(liverRoomRequest.getSessiontoken())) {
				//验证token
				uid = sessionTokenService.getStringForValue(liverRoomRequest.getSessiontoken()) + "";
				if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
					return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
				}
			}
			
			//组装参数
			Map<Object, Object> paramMap =  new HashMap<Object, Object>();
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//响应
			MapResponse response = null;
			//进入房间当前用户信息
			Map<String, String> liverMap = null;
			
			//判断用户是否为登录状态，登录了，则获取当前用户信息
			if (uid != null) {
				//获取进入房间当前用户的信息
				liverMap = this.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,查无此直播观众用户信息");
				}
				
				if (Integer.parseInt(liverMap.get("utype").toString()) == 1) {
					//id为IOS关键字 将id换成anchorid
					liverMap.put("anchorid", liverMap.get("id").toString());
				}
				
				//如果是主播身份       如果是报名主播  并且  没有groupId  则强制退出 重新登录
				if (liverMap.get("utype")!=null && liverMap.get("utype").toString().equals("1")) {
					if (StringUtils.isEmpty(liverMap.get("groupId")) || liverMap.get("groupId").toString().equals("null")) {
						stringRedisTemplate.delete(liverRoomRequest.getSessiontoken());
						return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
					}
				}
				
				//修改正在直播的通告或者自定义直播的直播记录为结束    避免出现同一主播同事出现多个直播记录
				Map<Object, Object> liveingMap = new HashMap<>();
				liveingMap.put("anchorId", liverMap.get("id"));
				liveingMap.put("zhiboType", 5);
				liveingMap.put("updateTime", DateUtil.format(new Date()));
				//修改直播状态为结束的时候，加上直播结束时间
				liveingMap.put("endDate", DateUtil.format(new Date()));
				anchorLiveRecordDao.modifyCustomLiveRecord(liveingMap);
			}
			
			//开播类型0：通告开播类型  1: 自定义开播类型  2自定义续播
			Integer type = liverRoomRequest.getType();
			
			//主播开播的设备类型  0 ：IOS开播   1： android 开播  -1 未知'
			String systemversion = liverRoomRequest.getSystemversion();
			Integer deviceType = -1;
			if (systemversion.toLowerCase().contains("ios")) {
				deviceType = 0;
			}else if (systemversion.toLowerCase().contains("android")) {
				deviceType = 1;
			}
			
			//主播自定义开播,自定义续播
			if (type != null) {
				if (type == 1 || type == 2) {
					//主播自定义开播,自定义续播,必须登录  此处验证当前用户有无登录
					if (liverMap != null && liverMap.size()>0) {
						if (type == 1) {
							//主播自定义开播,需要拦截上传封面为null的请求
							if (StringUtils.isEmpty(liverRoomRequest.getCover()) || "null".equalsIgnoreCase(liverRoomRequest.getCover())) {
								return new BaseResponse(ResponseCode.FAILURE, "上传封面图失败,请重试");
							}
						}
					}else {
						return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,查无此主播信息");
					}
					//主播自定义开播,自定义续播
					return this.customLiveStart(liverMap,liverRoomRequest,deviceType,type);
				}
			}
			
			//查询直播记录信息
			paramMap.put("id", liverRoomRequest.getZhiboRecordId());
			LiveRecordInfo recordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
			if (recordInfo == null) {
				return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,查无此直播记录信息");
			}
			
			//进入回放房间
			if (recordInfo.getZhibo_type() == 3) {
				return this.entryPlaybackRoom(liverMap,recordInfo);
			}
			
			//如果用户是该记录的主播 则修改直播记录为开播   并且缓存主播钱包的鸟蛋
			if (liverMap != null && recordInfo.getAnchor_id() == Long.parseLong(liverMap.get("id").toString())) {
				
				//检查大赛来源的主播有无限制开播
				boolean flag = this.validateMatchAnchor(liverMap);
				if (!flag) {
					return new BaseResponse(ResponseCode.FAILURE, "开播权限受限制,暂不能开播");
				}
				
				//计划开始时间-当前时间
				Long liveTime = recordInfo.getPlan_start_date().getTime() - new Date().getTime();
				
				//距离开播时间2小时毫秒数
				Long beginTime = Long.parseLong(propertiesUtil.getValue("begin_live_time", "conf_live.properties").toString());
				
				//一天毫秒数
				Long dayTime = Long.parseLong(propertiesUtil.getValue("one_day_time", "conf_live.properties").toString());
				
				log.info("主播进入房间");
				
				//通告已结束,请联系管理员
				if (liveTime + dayTime < 0) {
					return new BaseResponse(ResponseCode.LIVE_OVERTIME, "通告已结束,请联系管理员");
				}
				
				//距离计划开播时间超过2小时,不予开播
				if (liveTime > beginTime) {
					return new BaseResponse(ResponseCode.LIVE_NOT_TIME, "距离计划开播时间超过2小时,不予开播，请联系管理员");
				}
				
				//获取主播直播钱包信息 
				try {
					//如果主播是出现异常掉线导致 暂停直播 从新进入房间的  则返回从新开播  无需再重新缓存鸟蛋信息
					if (recordInfo.getZhibo_type() != 2) {
						Map<String, String> liveWalletMap = personalCenterService.getLiveWalletInfo(uid);
						if (!liveWalletMap.isEmpty() && liveWalletMap!=null) {
							BigDecimal wallet_balance = new BigDecimal(liveWalletMap.get("balance"));
							String liver_wallet_key = "liver_wallet_"+Long.valueOf(uid);
							
							//缓存主播鸟蛋
							Map<Object, Object> walletMap = new HashMap<Object, Object>();
							//表示主播钱包鸟蛋-用于接收鸟蛋累加的 包括发礼物，私信 所得的累计(进房间初始化为0)
							walletMap.put("liverWalletEgg", "0");//累计直播鸟蛋使用
							walletMap.put("liverBirdEgg", wallet_balance+"");//进入房间前的鸟蛋+直播过程中收取的鸟蛋数量
							stringRedisTemplate.opsForHash().putAll(liver_wallet_key, walletMap);
							
							//将当前鸟蛋放入主播基本信息中
//							liverMap.put("birdEgg", Double.valueOf(wallet_balance.toString()).intValue()+"");
							liverMap.put("birdEgg", wallet_balance.intValue()+"");
							log.info("初始化钱包成功："+liver_wallet_key);
						}else {
							log.info("获取主播钱包信息失败,错误信息:用户uid:" + uid);
							return new BaseResponse(ResponseCode.FAILURE, "获取主播钱包信息失败");
						}
					}else {
						return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("获取主播钱包信息失败,错误信息:用户uid:" + uid);
					return new BaseResponse(ResponseCode.FAILURE, "获取主播钱包信息失败");
				}
				
				//主播通告开播
				try {
					resultMap = this.startLive(liverMap, recordInfo,deviceType,0);
					
					resultMap.put("hiddenMark", propertiesUtil.getValue("hiddenMark", "conf_live.properties"));
					resultMap.put("livePointMark", propertiesUtil.getValue("livePointMark", "conf_live.properties"));//首先初始化为不隐藏
					resultMap.put("liveRecordId", 0);
					
				} catch (Exception e) {
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE, "主播开始直播失败");
				}
				
				if (recordInfo.getPlan_end_date() != null && (recordInfo.getPlan_end_date().getTime() - 5*60*1000) > new Date().getTime()) {
					resultMap.put("planEndDate", DateUtil.format(recordInfo.getPlan_end_date()));
				}
				
				//再次开播,清除之前的推流断开次数
				stringRedisTemplate.delete(recordInfo.getAnchor_id() + "_" + CHANNEL_STOP_NUMS);
				
				//响应
				response = new MapResponse(ResponseCode.SUCCESS, "主播进入房间成功,开始直播");
				response.setResponse(resultMap);
				return response;
				
			}else{ //否则就是普通用户
				log.info("观众进入房间");
				//只能进入正在直播的房间
				if(recordInfo.getZhibo_type() != 1){
					log.info("进入房间失败,该直播间未开播");
					return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,该直播间未开播");
				}
				
				//首先验证其是否在直播的观看范围内  如果为传入信息
				String distanceStr = "";
				if (null!=liverRoomRequest.getLongitude() && null!=liverRoomRequest.getLatitude() 
						&& liverRoomRequest.getLongitude()>0 && liverRoomRequest.getLatitude()>0) {
					//获取商家地理位置信息
					String live_seller_address_key = "live_seller_distance_"+recordInfo.getSellerid();
					if (stringRedisTemplate.hasKey(live_seller_address_key)) {
						Map<Object, Object> sellerDistanceMap =stringRedisTemplate.opsForHash().entries(live_seller_address_key);
						double sellerLongitude = Double.parseDouble(sellerDistanceMap.get("longitude")==null?"0":sellerDistanceMap.get("longitude").toString()) ;
						double sellerLatitude = Double.parseDouble(sellerDistanceMap.get("latitude")==null?"0":sellerDistanceMap.get("latitude").toString());
						//得到计算所得的距离
						if (sellerLongitude>0 && sellerLatitude>0) {
							double distance = DistanceUtil.Distance(liverRoomRequest.getLongitude(), liverRoomRequest.getLatitude(), sellerLongitude, sellerLatitude);
//							if (distance > Constant.LIVER_SELLER_DISTANCE) {//30千米以外
//								return new BaseResponse(ResponseCode.FAILURE, "抱歉,您目前的位置距主播超过"+ArithUtil.div(distance, 1000)+"km"+",暂不提供直播观看服务!");
//							}
							if (distance>1000) {
								distanceStr = Math.round(ArithUtil.div(distance, 1000))+"km";
							}else {
								distanceStr = Math.round(distance)+"m";//距离主播距离
							}
						}
					}
				}
				
				//返回主播基本信息 
				Map<Object, Object> anchorInfo = liveUserDao.queryLiverInfoById(new Long(recordInfo.getAnchor_id()).intValue());
				if (anchorInfo == null) {
					return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,未获取到主播信息");
				}
				
				try {
					log.info("进入房间操作");
					//观众用户进房间操作,操作相应的操作,并返回用户基本信息
					resultMap = this.viewerEnterRoom(liverMap, recordInfo,anchorInfo.get("phone").toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE, "观众进入房间失败");
				}
				
				//返回主播信息
				anchorInfo.put("anchorid", anchorInfo.get("id").toString());//主播ID
				anchorInfo.put("avatar", fileUrl+anchorInfo.get("avatar"));//头像
				anchorInfo.put("anchorWechatImg", fileUrl+anchorInfo.get("wechat_pic"));//头像
				
				//返回通告信息 
				anchorInfo.put("anchorWechatTips", propertiesUtil.getValue("live_anchor_wechat_tips", "conf_live.properties"));
				anchorInfo.put("sellername", (recordInfo.getSeller_alias()==null||StringUtils.isEmpty(recordInfo.getSeller_alias()))?recordInfo.getSellername():recordInfo.getSeller_alias());//直播商家名称
				anchorInfo.put("sellerid", recordInfo.getSellerid());//直播商家ID
				anchorInfo.put("view_count", resultMap.get("view_count"));//当前观看人数
				anchorInfo.put("zhiboTitle", recordInfo.getZhibo_title());//直播标题
				anchorInfo.put("ZBPhone", anchorInfo.get("phone"));//直播电话号
				anchorInfo.put("liveTips", propertiesUtil.getValue("live_tips", "conf_live.properties"));//直播提示信息
				anchorInfo.put("isHorizontalScreen", recordInfo.getIsHorizontalScreen());//是否横屏状态：0 不是   1 是
				anchorInfo.put("isQianZhi", recordInfo.getIsQianZhi());//是否是前置摄像头：  0：后置摄像头    1：前置摄像头(默认)
				anchorInfo.put("distanceStr", distanceStr);//距离主播距离
				Integer signType = anchorInfo.get("sign_type") == null ? 0 : Integer.parseInt(anchorInfo.get("sign_type").toString());
				Integer apiVersion = VersionUtil.getVersionCode(liverRoomRequest.getAppversion());
//				if (apiVersion > 360 ) {
					signType = anchorSignTypeService.getSignType(anchorInfo);
//				}
				anchorInfo.put("signType", signType);  //是否签约主播 0否1是2内部测试账号
				log.info("进入房间,返回主播基本信息:" + anchorInfo.toString());
				
				//返回主播鸟蛋   直播历史收益+直播过程中的收益 
				String liver_wallet_key = "liver_wallet_"+anchorInfo.get("uid");
				Map<Object, Object>  anchorBirdEggMap= stringRedisTemplate.opsForHash().entries(liver_wallet_key);
				if (anchorBirdEggMap==null) {
					return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,未获取到主播信息");
				}
				BigDecimal liverBirdEgg = new BigDecimal(anchorBirdEggMap.get("liverBirdEgg").toString());
//				anchorInfo.put("birdEgg", Double.valueOf(liverBirdEgg.toString()).intValue());
				anchorInfo.put("birdEgg", liverBirdEgg.intValue());
				resultMap.put("liveUserInfo", anchorInfo);
				//'设备类型  0 ：IOS开播   1： android 开播  -1 未知'
				resultMap.put("deviceType", recordInfo.getDevice_type());
				
				/**
				 * 如果改主播有粉丝券,并且粉丝券结束时间大于现在，并且粉丝券发放状态在正在发放中，且在下架时间返回,则返回有预售状态,如果平台存在其他推荐的粉丝券，也返回预售状态
				 */
				//粉丝券结束时间大于现在，并且粉丝券发放状态在正在发放中
				if (recordInfo.getFansEndDate() != null && recordInfo.getFansSendStatus() != null && recordInfo.getFansEndDate().getTime() > new Date().getTime() && recordInfo.getFansSendStatus() == 1) {
					//没有粉丝券下架时间或者粉丝券下架时间还没过,则返回有预售状态
					if (recordInfo.getSoldOutTime() == null || recordInfo.getSoldOutTime().getTime() > new Date().getTime()) {
						resultMap.put("isSell", 1);
					}else {
						resultMap.put("isSell", 0);
					}
				}else {
					Map<Object, Object> couponMap = new HashMap<>();
					couponMap.put("uid", Integer.parseInt(anchorInfo.get("uid").toString()));
					if (liverMap != null && liverMap.get("id") != null) {
						//手机号码,排除过滤测试账号
						couponMap.put("phone", liverMap.get("phone"));
					}
					//查询主播的其他粉丝券数量,粉丝券结束时间大于现在，并且粉丝券发放状态在正在发放中，且在下架时间返回
					Integer anchorCouponSum = couponDao.queryAnchorCouponSumByUid(couponMap);
					if (anchorCouponSum > 0) {
						resultMap.put("isSell", 1);
					}else {
						//查询平台推荐的粉丝券数量
						Integer platformRecommendCouponSum = couponDao.queryPlatformRecommendCouponSum(couponMap);
						if (platformRecommendCouponSum > 0) {
							resultMap.put("isSell", 1);
						}else {
							resultMap.put("isSell", 0);
						}
						
					}
					
				}
				
				//查询本场直播通告是否存在有未领取的红包
				Map<Object, Object> map = new HashMap<>();
				map.put("uid", anchorInfo.get("uid"));
				map.put("liveRecordId", recordInfo.getId().intValue());
				Map<Object, Object> livePacketMap = liveRoomDao.queryLiveRedpacketByUid(map);
				
				resultMap.put("existLiveRepacket", 0);
				
				//查询是否存在直播间红包
				if (livePacketMap != null && livePacketMap.size() > 0) {
					//查询是否存在领取红包记录信息
					map.clear();
					map.put("uid", uid);
					map.put("liveRedpacketId", livePacketMap.get("id"));
					Map<Object, Object> liveRedpacketRecordMap = liveRoomDao.queryLiveRedpacketRecord(map);
					if (liveRedpacketRecordMap == null || liveRedpacketRecordMap.size() <= 0) {
						resultMap.put("existLiveRepacket", 1);
						resultMap.put("liveRepacketId", livePacketMap.get("id"));
						if (livePacketMap.get("receiveUid")!=null && !"".equals(livePacketMap.get("receiveUid"))){
							map.put("receiveUid", livePacketMap.get("receiveUid"));
							map.put("receiveNname", getUsernname(livePacketMap.get("receiveUid")+""));
						}
					}
				}
				
				//首先判断 ios审核用 用户    如果是审核用户 则返回标示
				resultMap.put("hiddenMark", propertiesUtil.getValue("hiddenMark", "conf_live.properties"));
				resultMap.put("livePointMark", propertiesUtil.getValue("livePointMark", "conf_live.properties"));//首先初始化为不隐藏
				//响应
				response = new MapResponse(ResponseCode.SUCCESS, "观众进入房间成功,观看直播");
				response.setResponse(resultMap);
				return response;
			}
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "进入房间失败");
		}
	}
	
	
	/**获取用户昵称
	 * @param uid
	 * @return
	 */
	private String getUsernname(String uid){
		Map<Object, Object> receiveMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));		
		if (receiveMap == null || receiveMap.size() <=0 ) {
			return null;
		}
		return receiveMap.get("nname")+"";
	}
	/**
	 * 
	* @Title: customLiveStart
	* @Description:  type = 1 主播自定义开播          type = 2 主播自定义续播
	* @return Object    返回类型
	* @throws
	 */
	public Object customLiveStart(Map<String, String> liverMap,LiverRoomRequest liverRoomRequest,Integer deviceType,Integer type) {
		
		//检查大赛来源的主播有无限制开播
		boolean flag = this.validateMatchAnchor(liverMap);
		if (!flag) {
			return new BaseResponse(ResponseCode.FAILURE, "开播权限受限制,暂不能开播");
		}
		
		//组装参数
		Map<Object, Object> paramMap = new HashMap<>();
		//结果集
		Map<Object, Object> resultMap = new HashMap<>();
		//响应
		MapResponse response = null;
		
		//主播的寻蜜客id
		String uid = liverMap.get("uid");
		
		//获取主播直播钱包信息 
		try {
			//缓存主播鸟蛋
			Map<String, String> liveWalletMap = personalCenterService.getLiveWalletInfo(uid);
			if (!liveWalletMap.isEmpty() && liveWalletMap!=null) {
				BigDecimal wallet_balance = new BigDecimal(liveWalletMap.get("balance"));
				String liver_wallet_key = "liver_wallet_"+Long.valueOf(uid);
				//缓存主播鸟蛋
				Map<Object, Object> walletMap = new HashMap<Object, Object>();
				//表示主播钱包鸟蛋-用于接收鸟蛋累加的 包括发礼物，私信 所得的累计(进房间初始化为0)
				walletMap.put("liverWalletEgg", "0");//累计直播鸟蛋使用
				walletMap.put("liverBirdEgg", wallet_balance+"");//进入房间前的鸟蛋+直播过程中收取的鸟蛋数量
				stringRedisTemplate.opsForHash().putAll(liver_wallet_key, walletMap);
				
				//将当前鸟蛋放入主播基本信息中
//				liverMap.put("birdEgg", Double.valueOf(wallet_balance.toString()).intValue() +"");
				liverMap.put("birdEgg", wallet_balance.intValue() +"");
				log.info("初始化钱包成功："+liver_wallet_key);
			}else {
				log.info("获取主播钱包信息失败,错误信息:用户uid:" + uid);
				return new BaseResponse(ResponseCode.FAILURE, "获取主播钱包信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取主播钱包信息失败,错误信息:用户uid:" + uid);
			return new BaseResponse(ResponseCode.FAILURE, "获取主播钱包信息失败");
		}
		
		Integer customLiveTimeLimit = null;
		try {
			customLiveTimeLimit = Integer.parseInt(propertiesUtil.getValue("customLiveTimeLimit", "conf_live.properties"))*60;
		} catch (IOException e1) {
			customLiveTimeLimit = 90*60;
		}
		
		if (type == 1) {
			//自定义直播封面
			String cover = liverRoomRequest.getCover().replace(fileUrl, "");
			//自定义直播,直播标题
			String title = liverRoomRequest.getTitle();
			//自定义直播,商家名称
			String location = liverRoomRequest.getLocation();
			//自定义直播,商家地址
			String address = liverRoomRequest.getAddress();
			//自定义直播,直播间密码
			String anchorRoomPassword = liverRoomRequest.getRoomLock();
			//通告标签id
			String tagIds = liverRoomRequest.getTagIds();
			
			try {
				//添加主播自定义开播的直播记录
				LiveRecordInfo recordInfo = new LiveRecordInfo();
				recordInfo.setAnchor_id(Long.parseLong(liverMap.get("id")));
				recordInfo.setNname(liverMap.get("nname"));
				recordInfo.setAvatar(liverMap.get("avatar"));
				recordInfo.setAnchor_room_no(Long.parseLong(liverMap.get("anchor_room_no")));
				recordInfo.setAnchor_room_password("".equals(anchorRoomPassword)?null:anchorRoomPassword);//直播间密码
				recordInfo.setZhibo_type(1);
				recordInfo.setZhibo_title(title);
				recordInfo.setZhibo_cover(cover);
				recordInfo.setZhibo_address(address);//直播商家地址
				recordInfo.setStart_date(new Date());
				recordInfo.setLive_start_type(1);
				recordInfo.setCreate_time(new Date());
				recordInfo.setUpdate_time(new Date());
				recordInfo.setIsHorizontalScreen(1);//默认前置摄像头
				recordInfo.setStatus(1);
				recordInfo.setView_count(0);
				recordInfo.setPlan_start_date(new Date());//计划开播时间
				recordInfo.setSellername(location);//在自定义开播的直播记录列表中商家名称显示定位
				
				paramMap.put("anchorId", liverMap.get("id"));
				paramMap.put("nname", liverMap.get("nname"));
				paramMap.put("avatar", liverMap.get("avatar"));
				paramMap.put("anchorRoomNo", liverMap.get("anchor_room_no"));
				paramMap.put("anchorRoomPassword", "".equals(anchorRoomPassword)?null:anchorRoomPassword);//直播间密码
				paramMap.put("zhiboType", 1);//'直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4历史通告 5结束直播',
				paramMap.put("zhiboTitle", title);//直播标题
				paramMap.put("zhiboCover", cover);//直播封面
				paramMap.put("zhiboAddress", address);//直播商家地址
				paramMap.put("videoType", 2);//'视频分类 1 美食  2 休闲娱乐 3 趣玩'
				paramMap.put("startDate", DateUtil.format(new Date()));//开播时间
				paramMap.put("liveStartType", 1);//'开播类型 ： 0 通告开播    1 自定义开播 '
				paramMap.put("deviceType", deviceType);//主播开播的设备类型  0 ：IOS开播   1： android 开播  -1 未知'
				paramMap.put("createTime", DateUtil.format(new Date()));
				paramMap.put("updateTime", DateUtil.format(new Date()));
				paramMap.put("sellername", location);
				paramMap.put("seller_alias", location);
				paramMap.put("planStartDate", DateUtil.format(new Date()));//计划开播时间
				paramMap.put("sign_type", liverMap.get("signType"));//计划开播时间
				
				//添加自定义直播记录
				anchorLiveRecordDao.insertLiveRecord(paramMap);
				Integer id = Integer.parseInt(paramMap.get("id").toString());
				recordInfo.setId(Long.parseLong(id + ""));
				
				//查询通告标签信息
				List<Map<Object, Object>> tagList = new ArrayList<>();
				if (tagIds != null && !"".equals(tagIds)) {
					String[] tagIdArr = tagIds.split(",");
					for (String tagId : tagIdArr) {
						//查询标签信息
						Map<Object, Object> classifyTagMap = liveRoomDao.queryLiveClassifyTagById(Integer.parseInt(tagId.trim()));
						if (classifyTagMap == null || classifyTagMap.size() <= 0) {
							return new BaseResponse(ResponseCode.FAILURE, "查无此标签信息");
						}
						
						classifyTagMap.put("liveRecordId", id);
						tagList.add(classifyTagMap);
					}
				}
				
				if (tagList.size() > 0) {
					//批量插入通告标签
					liveRoomDao.insertLiveRecordTagConf(tagList);
				}
				
				//主播自定义开播
				resultMap = this.startLive(liverMap, recordInfo,deviceType,type);
				//自定义开播生成的直播记录id
				resultMap.put("liveRecordId", id);
				//主播自定义直播结束时间限制
				resultMap.put("planEndDate", DateUtil.format(DateUtil.addSecond(new Date(), customLiveTimeLimit)));
				
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "主播自定义开始直播失败");
			}
			
		}else {
			try {
				//查询自定义直播信息
				LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordById(liverRoomRequest.getZhiboRecordId());
				//主播自定义续播
				resultMap = this.startLive(liverMap,liveRecordInfo,deviceType,type);
				//自定义续播的记录id
				resultMap.put("liveRecordId", liverRoomRequest.getZhiboRecordId());
				//主播自定义续播结束时间限制
				Integer limitTime = customLiveTimeLimit-(liveRecordInfo.getZhibo_duration()==null?0:liveRecordInfo.getZhibo_duration());
				if (limitTime > 0) {
					resultMap.put("planEndDate", DateUtil.format(DateUtil.addSecond(new Date(), limitTime)));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "主播自定义续播失败");
			}
		}
		
		//主播开播的设备类型  0 ：IOS开播   1： android 开播  -1 未知'
		resultMap.put("deviceType", deviceType);
		
		//首先判断 ios审核用 用户    如果是审核用户 则返回标示
		
		try {
			resultMap.put("hiddenMark", propertiesUtil.getValue("hiddenMark", "conf_live.properties"));//首先初始化为不隐藏
			resultMap.put("livePointMark", propertiesUtil.getValue("livePointMark", "conf_live.properties"));//首先初始化为不隐藏
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取审核账号UID出错");
			return new MapResponse(ResponseCode.FAILURE, "获取用户基础信息异常");
		}
		
		//再次开播,清除之前的推流断开次数
		stringRedisTemplate.delete(liverMap.get("id").toString() + "_" + CHANNEL_STOP_NUMS);
		
		//响应
		response = new MapResponse(ResponseCode.SUCCESS, "主播进入房间成功,开始直播");
		response.setResponse(resultMap);
		return response;
	}
	
	/**
	 * 
	* @Title: entryPlaybackRoom
	* @Description: 进入回放房间
	* @return Object    返回类型
	* @throws
	 */
	public Object entryPlaybackRoom(Map<String, String> liverMap,LiveRecordInfo recordInfo) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//返回主播基本信息 
			Map<Object, Object> anchorInfo = liveUserDao.queryLiverInfoById(new Long(recordInfo.getAnchor_id()).intValue());
			if (anchorInfo == null) {
				log.info("进入房间失败,未获取到主播信息");
				return new BaseResponse(ResponseCode.FAILURE, "进入房间失败,未获取到主播信息");
			}
			resultMap.put("anchorid", anchorInfo.get("id").toString());//主播ID
			resultMap.put("uid", anchorInfo.get("uid").toString());//主播会员ID
			resultMap.put("nname", anchorInfo.get("nname").toString());//主播ID
			resultMap.put("avatar", fileUrl+anchorInfo.get("avatar"));//头像
			resultMap.put("sellername", recordInfo.getSellername());//直播商家名称
			resultMap.put("sellerid", recordInfo.getSellerid());//直播商家ID
			resultMap.put("zhiboTitle", recordInfo.getZhibo_title());//直播标题
			resultMap.put("zhiboCover", recordInfo.getZhibo_cover());//直播封面
			resultMap.put("playbackUrl", recordInfo.getZhibo_playback_url());//直播回放地址
			resultMap.put("signType", anchorInfo.get("sign_type") == null ? 0 : anchorInfo.get("sign_type"));  //是否签约主播
			
			//组装请求参数
			Map<Object, Object> paramMap = new HashMap<>();
			
			//判断用户是否登录，没有登录，则直接修改观看人数，登录了，则修改相应的观看记录信息
			if (liverMap != null) {
				//查询是否关注过此主播
				paramMap.put("liverStrId", liverMap.get("id"));
				paramMap.put("liverEndId", new Long(recordInfo.getAnchor_id()).intValue());
				Integer focusAnchorResult = liveUserDao.queryFocusAnchor(paramMap);
				//关注主播状态	0 否 1是
				resultMap.put("attentionStatus", focusAnchorResult);
				
			}else {
				resultMap.put("attentionStatus", 0);
			}
			
			//看过人数
			Integer nums = recordInfo.getView_count();
			
			//修改直播记录观看人数
			paramMap.clear();
			paramMap.put("id", recordInfo.getId());
			paramMap.put("addnumber", "1");
			anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
			
			log.info("同步观看人数成功");
			resultMap.put("view_count", nums + 1);
			
			//响应
			log.info("进入回放房间成功,信息如下:" + resultMap.toString());
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "进入回放房间成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("进入回放房间失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "进入回放房间失败");
		}
	}
	
	/**
	 * 
	* @Title: viewerEnterRoom
	* @Description: 观众进入房间
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object,Object> viewerEnterRoom (Map<String,String>liverMap,LiveRecordInfo recordInfo,String anchorPhone) throws Exception {
		//结果集
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		Integer viewCount = recordInfo.getView_count();//观看人数
		Integer recordId = new Long(recordInfo.getId()).intValue();//直播id
		Integer anchorId = new Long(recordInfo.getAnchor_id()).intValue();//主播id
		String liveId = (liverMap==null?null:liverMap.get("id"));
		Integer page = 1;
		Integer banSpeakStatus = 0; //是否禁言：0-未禁言，1--禁言
		Integer isFree = 0; //是否发送过私信：0-未发过，1--发过
		Integer isFocus = 0; //是否关注过主播：0-未关注，1--已关注
		Integer isManager = 0 ;//是否管理员身份：0-非管理员，1--管理员
		//判断用户身份
		/*1.游客*/
		if (liverMap==null ||liverMap.isEmpty()) {
			viewCount += 1;	
			resultMap.put("userAvatarList", getLiveViewUsers(anchorId, recordId, page));
			resultMap.put("view_count", viewCount);
			return resultMap;
		}
		/*2.登陆用户*/
		//观众进入房间初始化
		String liveRebateKey = "live_rebate_"+liverMap.get("uid"); //累计返利key
		String  liveBeanKey = "live_bean_"+liverMap.get("uid");   //扣减鸟豆key
		stringRedisTemplate.opsForValue().set(liveRebateKey, "0");
		stringRedisTemplate.opsForValue().set(liveBeanKey,"0");
		
		//判断是否为首次进入房间：判断是否观看过该直播 有则修改观看状态
		Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("id", recordInfo.getId());
		paramMap.put("liver_id", liverMap.get("id").toString());
		Map<Object, Object> viewRecordMap= anchorLiveRecordDao.queryLiveViewRecordByliverId(paramMap); //获取观众观看当场直播记录 信息
		
		if (viewRecordMap ==null || viewRecordMap.isEmpty()) {//首次进入
			//未观看过的用户则 新增观看直播记录
			Integer liveStatus =1;//观看状态
			this.installLiverInfoMap(liverMap, recordInfo, liveStatus);
			log.info("欢迎" + liverMap.get("uid").toString() + "观看此直播间");
			paramMap.put("addnumber", "1");
			anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
			
			//Integer liverNums = anchorLiveRecordDao.queryReadViewerCountByLiveRecordId(recordId); //真实观看人数
			//机器人观看人数
			Integer liverNums = anchorLiveRecordDao.queryRobotViewNum(recordId);
			Map<String,Object> addRobotMap = liveRobotService.addRebotLiveRoom(anchorId.toString(),recordId.toString(), recordInfo.getAnchor_room_no().toString(), liverNums, 0); //增加的机器人返回map
			Integer recordNums = addRobotMap.get("recordNums")==null?0:Integer.parseInt(addRobotMap.get("recordNums").toString());
			viewCount =recordNums;
					
		} else {
			Integer liveStatus = Integer.parseInt(viewRecordMap.get("live_status").toString()); //观看状态
			banSpeakStatus = (Integer) viewRecordMap.get("ban_speak_status"); //禁言状态
			if (liveStatus != 1) {
				paramMap.put("live_status", 1);
				paramMap.put("updateTime", DateUtil.format(new Date()));
				//修改观众状态 继续观看
				anchorLiveRecordDao.editLiveViewRecordStatus(paramMap);
				log.info("欢迎用户" + liverMap.get("uid").toString() + "再次观看此直播间");
				//修改直播记录观看人数
				paramMap.put("addnumber", "1");
				viewCount+=1; //页面显示人数加1
				anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
			}	
		}
	
		// 是否发送过私信
		Map<Object,Object> messageParam=new HashMap<Object,Object>();
		messageParam.put("send_liver_uname", liverMap.get("phone").toString());//消息发送者
		messageParam.put("to_liver_uname", anchorPhone);//消息接收者
		Map<Object, Object> secretMarkMap = privatemessageDao.queryPrivateMessage(messageParam);
		if (secretMarkMap !=null && !secretMarkMap.isEmpty()) {
			isFree = 1;
		}
		
		//有无关注过该房间主播 
		Map<String, Object> focusMap = new HashMap<String, Object>();
		focusMap.put("liver_str_id", liverMap.get("id").toString());
		focusMap.put("liver_end_id", recordInfo.getAnchor_id());
		int  focusCount = liveUserDao.queryFocusCount(focusMap);
		if (focusCount > 0) {
			isFocus = 1;
		}
		
		//是否已经是该主播的管理员
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("manager_id", liverMap.get("id").toString());
		map.put("anchor_id", recordInfo.getAnchor_id());		
		Map<Object, Object> managerMap = anchorManagerDao.queryAnchorManager(map);
		if (managerMap !=null && !managerMap.isEmpty()) {
			isManager = 1;
		}
		
		//缓存用户信息
		Map<Object, Object> viewerInfoMap = new HashMap<Object, Object>();
		viewerInfoMap.put("todayFirst", "1");//今日首次观看
		viewerInfoMap.put("banSpeakStatus", banSpeakStatus);
		viewerInfoMap.put("isFree", isFree);
		viewerInfoMap.put("isFocus", isFocus);
		viewerInfoMap.put("isManager", isManager);
		viewerInfoMap.put("avatar", fileUrl+liverMap.get("avatar"));
		viewerInfoMap.put("rankNo", Integer.parseInt(liverMap.get("rank_no").toString()));
		viewerInfoMap.put("uid", Integer.parseInt(liverMap.get("uid").toString()));
		String liver_key = "liver_"+liverMap.get("uid");
		stringRedisTemplate.opsForHash().putAll(liver_key, liverMap);
		
		resultMap.put("viewerInfo", viewerInfoMap);
		resultMap.put("view_count", viewCount);
		////返回观众列表判断用户是否是该直播的主播 ：用户查询数据库
		if (!liveId.equals(anchorId.toString())) {
			resultMap.put("userAvatarList", getLiveViewUsers(anchorId, recordId, page));				
		}
		
		
		return resultMap;
	}
	
	/**获取直播间的观众
	 * @param anchorId
	 * @param recordId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	private  List<Map<Object, Object>> getLiveViewUsers (Integer anchorId,Integer recordId,Integer page) throws Exception{
		List<Map<Object, Object>> viewList = viewerUserListService.queryViewerUserListByAhId(anchorId, recordId, page);
		if (viewList !=null && !viewList.isEmpty()) {
			viewList = viewerUserListService.ViewerUserList(viewList);
		}
		return viewList;
	}
	
	
	/**
	 * 
	* @Title: startLive
	* @Description: type = 0  主播通告开播  type = 1 自定义开播  type = 2 自定义续播
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object, Object> startLive(Map<String, String> liverMap,LiveRecordInfo recordInfo,Integer deviceType,Integer type) throws Exception {
		//开播类型: 0通告开播   1自定义开播
		Integer liveStartType = recordInfo.getLive_start_type();
		//组装参数
		Map<Object, Object> map = new HashMap<>();
		
		//通告开播缓存商家经纬度,自定义开播、自定义续播则不需要
		if (type == 0) {
			//缓存redis 商家地址信息  经纬度  用于观众进入房间计算当前距离
			if (null != recordInfo.getSellerid()) {
				String live_seller_address_key = "live_seller_distance_"+recordInfo.getSellerid();
				Map<String, String> sellerDistanceMap = new HashMap<String, String>();
				
				//先判断直播间是否有商家经纬度信息  有则取 
				if (recordInfo.getLatitude()>0 && recordInfo.getLongitude()>0 ) {
					sellerDistanceMap.put("longitude", String.valueOf(recordInfo.getLongitude()));
					sellerDistanceMap.put("latitude", String.valueOf(recordInfo.getLatitude()));
				}else {
					List<Integer> sellerList = new ArrayList<>();
					sellerList.add(recordInfo.getSellerid().intValue());
					
					List<Map<Object, Object>> sellerMapList = sellerInfoDao.querySellersLandMark(sellerList);
					if (sellerMapList.size()>0) {
						//只查询当前的商家的
						Map<Object, Object> sellerMap = sellerMapList.get(0);
						if (sellerMap.get("longitude")!=null && sellerMap.get("latitude")!=null) {
							Double sellerLongitude = Double.parseDouble(sellerMap.get("longitude").toString());
							Double sellerLatitude = Double.parseDouble(sellerMap.get("latitude").toString());
							sellerDistanceMap.put("longitude", sellerLongitude.toString());
							sellerDistanceMap.put("latitude", sellerLatitude.toString());
						}
					}
				}
				// 商家经纬度存入 redis
				anchorViewerMemberRankService.addOrUpdateLiveRedis(sellerDistanceMap,live_seller_address_key);
				
			}
			
			//修改正在直播的通告或者自定义直播的直播记录为结束
			map.put("anchorId", liverMap.get("id"));
			map.put("zhiboType", 5);
			map.put("updateTime", DateUtil.format(new Date()));
			//修改直播状态为结束的时候，加上直播结束时间
			map.put("endDate", DateUtil.format(new Date()));
			anchorLiveRecordDao.modifyCustomLiveRecord(map);
		}
		
		/**
		 * 查询是否存在有未被领取完的红包,存在,则退还主播未被领取完的红包
		 */
		try {
			//查询本场直播通告是否存在有未领取的红包
			map.clear();
			map.put("uid", liverMap.get("uid").toString());
			map.put("liveRecordId", recordInfo.getId().toString());
			map.put("startDate", recordInfo.getStart_date());
			map.put("planStartDate", recordInfo.getPlan_start_date());
			
			//查询是否存在有未被领取完的红包,如果存在,则退还主播未被领取完的红包
			Map<Object, Object> liveRedpacketMap = this.getLiveRedpacketInfo(map,2);
			
			int refundAmount = Integer.parseInt(liveRedpacketMap.get("refundAmount").toString());
			if ( refundAmount > 0) {
				//如果退还鸟蛋数大于0,则打印日志记录
				log.info("进入房间退还主播红包鸟蛋数refundAmount=" + refundAmount);
			}
			
		} catch (Exception e) {
			log.info("进入房间,查询退还红包鸟蛋数异常");
		}
		
		//自定义开播不需要修改直播记录为开始直播,也不需要修改观看记录表 “等待开播”的用户状态为 “观看直播”,  通告开播,自定义续播则需要
		if (type != 1) {
			Map<Object, Object> paramMap = new HashMap<>();
			//修改直播记录为开始直播
			paramMap.put("id", recordInfo.getId());
			paramMap.put("zhibo_type", 1);
			paramMap.put("device_type", deviceType);
			paramMap.put("utype", "1");
			paramMap.put("roomType", "0");
			paramMap.put("startTime", DateUtil.format(new Date()));
			paramMap.put("updateTime", DateUtil.format(new Date()));
			anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
			
			//修改观看记录表 “等待开播”的用户状态为 “观看直播”
			paramMap.put("start_time", DateUtil.format(new Date()));
			paramMap.put("live_record_id", recordInfo.getId());
			anchorLiveRecordDao.editLiveViewerRecordStart(paramMap);
		}
		//返回主播基本信息 
		Map<Object, Object> liveUserInfoMap = new HashMap<>();
		//观看人数
		Integer viewCount = recordInfo.getView_count();
		//添加机器人,通告第一次开播跟自定义开播增加机器人,通告续播、自定义续播不增加机器人
		if (recordInfo.getZhibo_type() == 0 || type == 1) {
			//消息推送
//			pushSingleService.SendMqStartLive(liverMap, recordInfo);
			//查询真人观看人数
			//Integer liverNums = anchorLiveRecordDao.queryReadViewerCountByLiveRecordId(Integer.parseInt(recordInfo.getId().toString()));
			Integer liverNums = anchorLiveRecordDao.queryRobotViewNum(Integer.parseInt(recordInfo.getId().toString()));
			//观众进入房间,初始化机器人
			Map<String,Object> addRobotMap = liveRobotService.addRebotLiveRoom(recordInfo.getAnchor_id().toString(), recordInfo.getId().toString(), recordInfo.getAnchor_room_no().toString(), liverNums, 1); //增加的机器人返回map
			Integer addNums = addRobotMap.get("recordNums")==null?0:Integer.parseInt(addRobotMap.get("recordNums").toString());
			//返回观看人数
			viewCount += addNums;
			String jsonString = addRobotMap.get("robots")==null?null:(String)addRobotMap.get("robots");
			List<Map<Object,Object>> robotList = JSON.parseObject(jsonString,new TypeReference<List<Map<Object,Object>>>(){});
			if (robotList !=null && !robotList.isEmpty()) {
				int robotSize = robotList.size();
				robotList=robotList.subList(0, robotSize>20?20:robotSize);
				robotList = viewerUserListService.ViewerUserList(robotList);
				liveUserInfoMap.put("userAvatarList", robotList);
			}
			
		}
		
		if (null!=recordInfo.getSeller_alias() && !StringUtils.isEmpty(recordInfo.getSeller_alias())) {
			liverMap.put("sellername", recordInfo.getSeller_alias()); 
		}else {
			liverMap.put("sellername", recordInfo.getSellername() == null?"":recordInfo.getSellername());
		}
		
		if (liveStartType != 1 ) {
			liverMap.put("sellerid", null!=recordInfo.getSellerid()?recordInfo.getSellerid().toString():"0");
		}
		
		liverMap.put("view_count", viewCount + "");//观看人数
		liverMap.put("avatar", fileUrl+liverMap.get("avatar"));
		liverMap.put("anchorWechatImg", fileUrl+liverMap.get("anchorWechatImg"));
		liverMap.put("zhiboTitle", recordInfo.getZhibo_title()==null?"":recordInfo.getZhibo_title());
		liverMap.put("liveTips", propertiesUtil.getValue("live_tips", "conf_live.properties"));
		liverMap.put("anchorWechatTips", propertiesUtil.getValue("live_anchor_wechat_tips", "conf_live.properties"));
		liverMap.put("isHorizontalScreen", recordInfo.getIsHorizontalScreen().toString());//是否横屏状态：0 不是   1 是
		
		
		liveUserInfoMap.put("liveUserInfo", liverMap);
		if (liveUserInfoMap.get("userAvatarList")==null) {
			//返回观众列表
			List<Map<Object, Object>> viewList = viewerUserListService.queryViewerUserListByAhId(Integer.parseInt(liverMap.get("id").toString()), recordInfo.getId().intValue(), 1);
			if (viewList.size()>0) {
				liveUserInfoMap.put("userAvatarList", viewerUserListService.ViewerUserList(viewList));
			}else {
				liveUserInfoMap.put("userAvatarList", viewList);
			}
		}
		
		
		//获取礼物接收数量
		Map<Object, Object> giftParamMap = new HashMap<Object, Object>();
		giftParamMap.put("anchor_id",liverMap.get("id"));
		giftParamMap.put("live_record_id",recordInfo.getId());
		List<Map<Object, Object>> giftList = liveGiftsInfoDao.queryAnchorGiftNums(giftParamMap);
		List<Map<Object, Object>> resultGiftList = new ArrayList<Map<Object, Object>>();
		if (giftList.size()>0) {
			for (int i = 0; i < giftList.size(); i++) {
				Map<Object, Object> giftMap = giftList.get(i);
				giftMap.put("gift_avatar", fileUrl+giftMap.get("gift_avatar"));
				resultGiftList.add(giftMap);
			}
		}
		liveUserInfoMap.put("giftList", resultGiftList);
		
		//如果主播是出现异常掉线导致 暂停直播 从新进入房间的  则返回从新开播   无需再重新缓存信息
		if (recordInfo.getZhibo_type() != 2) {
			//最后将 观众或主播基础信息放入redis  liver_{uid}
			String liver_key = "liver_"+liverMap.get("uid");
			stringRedisTemplate.opsForHash().putAll(liver_key, liverMap);
			
		}

		KSLiveEntity entity = ksCloudService.getAndCreateLiveInfo(liveUserInfoMap, 2);
		if (entity != null && entity.getPlatform() == 2) {
			// 金山云
//			int recordId,String videoUrl,String channelId
			int recordId = Integer.parseInt(recordInfo.getId() + "" );
			anchorLiveRecordService.modiflyLiveRecordVedioUrl(recordId, entity.getUrl(), "0");
		}
		log.info("欢迎进入直播间");
		return liveUserInfoMap;
	}
									
	/**
	 * 描述：主播/用户 离开房间  同步redis数据到数据库
	 * 观众个人离开房间只个人  主播离开房间则修改整个房间观众的观看记录为 已结束 包括主播
	 * @return object
	 * @param liverRoomRequest
	 * @update 2017.07.10
	 * */
	public Object userExitRoom(LiverRoomRequest liverRoomRequest) {

		/* 游客退出 */
		if (StringUtils.isBlank(liverRoomRequest.getSessiontoken())) {
			return new BaseResponse(ResponseCode.SUCCESS, "游客退出房间成功");
		}
		/* 用户退出 */
		String uid = sessionTokenService.getStringForValue(liverRoomRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}

		// 计数器避免重复点击
		Integer recordId = liverRoomRequest.getZhiboRecordId();
		String keyTimes = "user_exit_" + recordId + "_" + uid;
		try {
		Long resultNum = stringRedisTemplate.opsForValue().increment(keyTimes, 1);
		if (resultNum > 1) {
			return new BaseResponse(ResponseCode.FAILURE, "正在退出中，请稍等。。。");
		}
		
			// 直播信息
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", recordId);
			LiveRecordInfo recordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
			if (recordInfo == null) {
				log.info("直播间不存在---" + recordId);
				return new MapResponse(ResponseCode.FAILURE, "获取直播信息失败，退出房间失败");
			}
			// 缓存中获取用户信息
			String liver_rediskey = "liver_" + Long.valueOf(liverRoomRequest.getUid());
			Map<Object, Object> liverMap = stringRedisTemplate.opsForHash().entries(liver_rediskey);
			if (liverMap == null || liverMap.isEmpty()) {
				log.info("用户退出房间,获取信息失败：" + liver_rediskey);
				MapResponse response = this.exitRoomExceptionDeal(recordInfo, Integer.parseInt(uid),liverRoomRequest.getZhiboRecordId());// 主播退出房间异常处理
				return response;
			}
			liverMap.put("live_record_id", liverRoomRequest.getZhiboRecordId());
			liverMap.put("get_experience_type", "5");
			// 区分退出房间的身份信息
			Integer anchorId = new Long(recordInfo.getAnchor_id()).intValue();
			Integer liverId = liverMap.get("id") == null ? null : Integer.parseInt(liverMap.get("id").toString());
			if (anchorId.equals(liverId)) {// 主播退出房间
				// 获取redis缓存数据 同步主播信息 鸟蛋
				String liver_wallet_key = "liver_wallet_" + Long.valueOf(liverRoomRequest.getUid());
				Map<Object, Object> walletMap = stringRedisTemplate.opsForHash().entries(liver_wallet_key);
				if (walletMap == null || walletMap.isEmpty()) {
					log.info("退出房间,获取鸟蛋失败：" + liver_wallet_key);
					return this.exitRoomExceptionDeal(recordInfo, Integer.parseInt(uid),liverRoomRequest.getZhiboRecordId());// 主播退出房间异常处理
				}
				// 累计鸟蛋 同步数据库操作
				ResponseData responseData = anchorViewerMemberRankService.exitRoomAnchorBirdEggTotal(liverMap,recordInfo, walletMap, liver_rediskey, 0);
				if (responseData.getState() != 0) {
					log.info("退出房间失败,钱包信息同步出错：" + liver_wallet_key);
					return new MapResponse(ResponseCode.FAILURE, "同步鸟蛋失败");
				}
				//退出房间修改相关记录  返回相关参数
				return this.exitRoomEditLiveRecord(liverMap, recordInfo, walletMap);
			} else {// 用户退出房间				
				/*异步处理用户退出房间累计经验和修改退出房间人数--start*/
				JSONObject jsonData = new JSONObject();
				jsonData.put("liverRoomRequest", liverRoomRequest);
				jsonData.put("liver_rediskey", liver_rediskey);
				jsonData.put("liverMap",liverMap);
				jsonData.put("liveRecordId", recordId);
				SendResult resultUserExit = producerServiceImpl.send(userExitRoomExperienceInfo, jsonData.toString());
				log.info("异步处理用户退出房间累计经验和修改退出房间人数-推送ID"+resultUserExit.getMsgId()+"推送结果状态："+resultUserExit.getSendStatus());						
					
				//退出时返回 本次观看的返利结果
				String liveRebateKey = "live_rebate_"+liverMap.get("uid");//返利key
				String liveRebate = stringRedisTemplate.opsForValue().get(liveRebateKey);
				String liveBeanKey = "live_bean_"+liverRoomRequest.getUid(); //鸟豆key
				Map<Object, Object> rebateMoneyMap = new HashMap<Object, Object>();
				rebateMoneyMap.put("rebateMoney", new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//初始化返利
				rebateMoneyMap.put("liveExitRebateTips", propertiesUtil.getValue("live_exit_room_tips", "conf_live.properties").toString());//离开直播间tips
				rebateMoneyMap.put("isOtherCoupon", 0); //初始化主播无预售卷
				if (liveRebate !=null ) {
					//指定用户不返鸟币
					String appleUid = propertiesUtil.getValue("appleApprovalUid", "conf_live.properties");
					if (appleUid.indexOf(liverMap.get("uid").toString()) < 0) {
						rebateMoneyMap.put("rebateMoney", new BigDecimal(liveRebate).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}
				
				//查询主播是否有其他预售卷
				Map<Object,Object> params = new HashMap<>();
				params.put("anchorId", recordInfo.getAnchor_id());
				params.put("page", 1);
				params.put("limit", 1);
				List<Map<Object,Object>> list = anchorLiveRecordDao.queryLiveRecordFansCouponList(params);
				if(list!=null && !list.isEmpty()){
					rebateMoneyMap.put("isOtherCoupon", 1);
				}							
				stringRedisTemplate.delete(liveRebateKey);
				stringRedisTemplate.delete(liveBeanKey);
				log.info("用户退出房间成功");
				MapResponse response =  new MapResponse(ResponseCode.SUCCESS,"用户退出房间成功");
				response.setResponse(rebateMoneyMap);
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE,"操作失败");
		}finally{
			stringRedisTemplate.delete(keyTimes);
		}
	}
	
		
	/**
	 * 
	* @Title: exitRoomExceptionDeal
	* @Description: 主播退出房间异常处理(redis没有主播信息,鸟蛋信息，说明已经退出过,则直接重现退出时的数据),用户直接报错
	* @return MapResponse    返回类型
	* @throws
	 */
	public MapResponse exitRoomExceptionDeal(LiveRecordInfo recordInfo,Integer uid,Integer liveRecordId) {
		MapResponse response = null;
		if (recordInfo == null) {
			return new MapResponse(ResponseCode.FAILURE,"查无此直播记录详情");
		}
		
		response =  new MapResponse(ResponseCode.FAILURE,"获取用户信息失败,退出房间失败");
		
		//判断直播记录是否已经结束,如果结束了,并且是主播退出房间,则直接返回结束信息
		if (recordInfo.getZhibo_type() == 5) {
			try {
				Map<Object, Object> anchorMap = liveUserDao.queryLiverInfoByUid(uid);
				//获取主播结束信息
				if (recordInfo.getAnchor_id().intValue() == Integer.parseInt(anchorMap.get("id").toString())) {
					LiveShareRequest liveShareRequest = new LiveShareRequest();
					liveShareRequest.setLiveRecordId(liveRecordId);
					MapResponse mapResponse = (MapResponse) anchorLiveRecordService.queryLiveRecordInfo(liveShareRequest);
					
					//返回主播结束信息
					response =  new MapResponse(ResponseCode.SUCCESS,"退出房间成功");
					response.setResponse(mapResponse.getResponse());
				}
					
			} catch (Exception e) {
				log.info("退出房间失败,获取主播结束信息失败");
				response =  new MapResponse(ResponseCode.FAILURE,"获取用户信息失败,退出房间失败");
			}
		}
		
		return response;
	}
	
	/**
	 * 描述：退出房间 获取返利结果信息
	 * @param LiverRoomRequest
	 * @return object 
	 * */
	public Object queryLiveRebate(LiverRoomRequest liverRoomRequest){
		MapResponse response = null;
		
		//验证token
		String uid = sessionTokenService.getStringForValue(liverRoomRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//退出时返回 本次观看的返利结果
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		try {
			//是否显示鸟粉专享卡按钮
			String showDebitcardBanner = propertiesUtil.getValue("showDebitcardBanner", "conf_common.properties");
			resultMap.put("showDebitcardBanner", showDebitcardBanner);
			
			
			String liveRebateKey = "live_rebate_"+liverRoomRequest.getUid();
			String liveRebate = stringRedisTemplate.opsForValue().get(liveRebateKey);
			
			String liveBeanKey = "live_bean_"+liverRoomRequest.getUid();
			String liveBean = stringRedisTemplate.opsForValue().get(liveBeanKey);
			
			String live_return_birdCoin = propertiesUtil.getValue("live_return_birdCoin", "conf_live.properties");
			resultMap.put("rebateMoney", new BigDecimal(liveRebate).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//			resultMap.put("rebateMoney", liveRebate==null?"0":new BigDecimal(liveRebate).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//			resultMap.put("liveBeanMoney", "本场直播你送出"+new BigDecimal(liveBean==null?"0":liveBean).setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"鸟豆");
			
//			resultMap.put("rebateMoney", new BigDecimal(liveRebate==null?"0":liveRebate).toString()+"");
			resultMap.put("liveBeanMoney", "本场直播你送出"+Double.valueOf(new BigDecimal(liveBean==null?"0":liveBean).toString()).intValue() +"鸟豆");
			
			resultMap.put("liveExitRebateTips", propertiesUtil.getValue("live_exit_room_tips", "conf_live.properties").toString());
			
			response = new MapResponse(ResponseCode.SUCCESS, "获取返利成功");
			response.setResponse(resultMap);
		} catch (Exception e) {
			log.info("获取返利失败："+liverRoomRequest.getUid());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取返利失败");
		}
		return response;
	}
	
	/**
	 * 描述：查询用户基本信息 将key value 转换为String类型 存入redis
	 * @param Integer uid
	 * @return Map<Object, Object>
	 * */
	public Map<String, String> queryLiverInfoByUid(Integer uid){
		Map<Object, Object> map = liveUserDao.queryLiverInfoByUid(uid);
		Map<String, String> liverMap =  new HashMap<String,String>();
		liverMap.put("id", map.get("id")==null?"":map.get("id").toString()); //'直播用户id',
		liverMap.put("avatar", map.get("avatar")==null?"":map.get("avatar").toString()); //'头像',
		liverMap.put("uid", map.get("uid")==null?"":map.get("uid").toString()); //' 寻蜜鸟会员id',
		liverMap.put("utype", map.get("utype")==null?"":map.get("utype").toString()); //'直播用户类型： 1 主播 2 普通用户',
		liverMap.put("anchor_room_no", map.get("anchor_room_no")==null?"":map.get("anchor_room_no").toString()); // '主播房间编号',
		liverMap.put("phone", map.get("phone")==null?"":map.get("phone").toString()); //'手机号码',
		liverMap.put("rank_id", map.get("rank_id")==null?"0":map.get("rank_id").toString()); //'用户当前等级ID',
		liverMap.put("rank_no", map.get("rank_no")==null?"0":map.get("rank_no").toString()); //'用户当前等级数',
		liverMap.put("achievement", map.get("achievement")==null?"":map.get("achievement").toString()); //'用户当前等级头衔名称',
		liverMap.put("current_expe", map.get("current_expe")==null?"0":map.get("current_expe").toString()); //'用户当前持有经验',
		liverMap.put("give_gifts_nums", map.get("give_gifts_nums")==null?"0":map.get("give_gifts_nums").toString()); // '送出礼物总数',
		liverMap.put("gived_gifts_nums", map.get("gived_gifts_nums")==null?"0":map.get("gived_gifts_nums").toString()); //'接收礼物总数',
		liverMap.put("praised_nums", map.get("praised_nums")==null?"0":map.get("praised_nums").toString()); //'被点赞总数',
		liverMap.put("view_duration_day", map.get("view_duration_day")==null?"0":map.get("view_duration_day").toString()); //'当天观看直播总时长',
		liverMap.put("live_duration_day", map.get("live_duration_day")==null?"0":map.get("live_duration_day").toString());  // '当天进行直播总时长',
		liverMap.put("view_duration", map.get("view_duration")==null?"0":map.get("view_duration").toString()); // '观看直播总时长',
		liverMap.put("live_duration", map.get("live_duration")==null?"0":map.get("live_duration").toString()); // '进行直播总时长',
		liverMap.put("status", map.get("status")==null?"1":map.get("status").toString()); // '用户状态 1启用   0停用',
		liverMap.put("msg_status", map.get("msg_status")==null?"1":map.get("msg_status").toString()); // '消息提醒状态  1开启   0未开启',
		liverMap.put("seller_look_status", map.get("seller_look_status")==null?"0":map.get("seller_look_status").toString()); // '是否商家可见： 1是   0否',
		liverMap.put("groupId", map.get("group_id")==null?"":map.get("group_id").toString()); 
		liverMap.put("nname", com.xmniao.xmn.core.util.StringUtils.getUserNameStr(map.get("nname")==null?"":map.get("nname").toString()));
		liverMap.put("anchorWechatImg", map.get("wechat_pic")==null?"":map.get("wechat_pic").toString());
		liverMap.put("ledgerRatio", map.get("ledger_ratio")==null?"0.00":map.get("ledger_ratio").toString());
		liverMap.put("signType", map.get("sign_type") == null ? "0" : map.get("sign_type").toString()); //是否签约主播 0 否 1 是 2 内部测试账号
		liverMap.put("mbEcno", map.get("mb_ecno") == null ? "0" : map.get("sign_type").toString()); //是否是脉宝会员
		liverMap.put("isVstar", map.get("is_vstar") == null ? "0" : map.get("is_vstar").toString()); //是否报名星时尚大赛
		
		//查询路径是否存在文件
		StorageClient1 client =null ;
		try {
			if (liverMap.get("utype")!=null && liverMap.get("utype").toString().equals("1")) {
				if (map.get("avatar")!=null) {
					client = uploadClient.getStorageClients();	
					FileInfo fileInfo = client.query_file_info1(map.get("avatar").toString());
					if (fileInfo == null) {
						JSONArray jsonArray = JSON.parseArray(propertiesUtil.getValue("userRegisterImage", "conf_common.properties"));  ;
						int index = new Random().nextInt(jsonArray.size())%(jsonArray.size()-1+1) + 0;
						UrsInfo info = new UrsInfo();
						info.setUid(Integer.parseInt(liverMap.get("uid").toString()));
						info.setAvatar(jsonArray.get(index).toString());
						int result = ursInfoDao.updateByPrimaryKey(info);
						if (result>0) {
		 					liverMap.put("avatar", jsonArray.get(index).toString());
						}
					}
				}
			}
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}finally{
			if (client!=null) {
				uploadClient.closeConnect();
			}
		}
		
		//查询推荐主播V客信息
		Map<Object, Object> vUserMap = liveUserDao.queryVuidByUid(uid);
		liverMap.put("vkeUid","");
		if (vUserMap!=null && vUserMap.size()>0) {
			liverMap.put("vkeUid", vUserMap.get("uid") == null ? "" : vUserMap.get("uid").toString());
		}
		return liverMap;
	}
	
	/**
	 * 描述：初始化用户观看直播记录
	 * @param Map<Object, Object> liverMap,LiveRecordInfo live_status观看状态
	 * @return Map<Object, Object>
	 * */
	public Map<Object, Object> installLiverInfoMap(Map<String, String> liverMap,LiveRecordInfo recordInfo,int live_status){
		Map<Object, Object> addMap = new HashMap<Object, Object>();
		addMap.put("liver_id", liverMap.get("id").toString());
		addMap.put("uid", liverMap.get("uid").toString());
		addMap.put("rank_no", liverMap.get("rank_no").toString());
		addMap.put("achievement", liverMap.get("achievement")==null?"":liverMap.get("achievement").toString());
		addMap.put("liver_utype", liverMap.get("utype").toString());
		addMap.put("anchor_id", recordInfo.getAnchor_id());
		addMap.put("anchor_room_no", recordInfo.getAnchor_room_no());
		addMap.put("live_record_id", recordInfo.getId());
		if (live_status == 1) {
			addMap.put("start_time", DateUtil.format(new Date()));
		}
		addMap.put("live_type", 1);
		addMap.put("live_status", live_status);
		addMap.put("view_duration", 0);
		anchorLiveRecordDao.addLiveViewRecord(addMap);
		return addMap;
	}
	
	/**
	 * 描述：当日首次观看直播 平台免费送黄瓜
	 * @param Map<Object, Object> liveMap
	 * @return void
	 * */
	public void installTodayFirstSendGift(Map<String, String> liverMap){
		try {
			//查询用户自有礼物表是否有记录
			Map<Object,Object> param=new HashMap<Object,Object>();
			param.put("liver_id", liverMap.get("id"));
			param.put("gift_id", 1);
			LiveSelfGiftInfo selfGiftInfo = liveGiftsInfoDao.queryLiveSelfGifts(param);
			if (selfGiftInfo!=null && null!=selfGiftInfo.getId()) {
				param.put("giftNums", selfGiftInfo.getGiftNums()+3);
				liveGiftsInfoDao.modifyLiveSelfGiftNum(param);
			}else {
				//新增记录
				param.put("gift_nums", 3);
				selfGiftService.insertSelfGift(param);
			}
		} catch (Exception e) {
			log.info("当日首次观看直播 平台免费送黄瓜失败："+liverMap.get("id"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 描述 ： 主播退出房间 修改记录的相关操作
	 * @param  liverMap  , recordInfo,  
	 * @return void
	 * 
	 * */
	public MapResponse exitRoomEditLiveRecord(Map<Object, Object> liverMap,LiveRecordInfo recordInfo,Map<Object, Object> walletMap){
		MapResponse response = null;
		Map<Object, Object> paramMap =  new HashMap<Object, Object>();
		
		String liver_rediskey = "liver_"+liverMap.get("uid");
		String liver_wallet_key = "liver_wallet_"+liverMap.get("uid");
		
		try {
			Map<Object, Object> liveAllViewerMap  = new HashMap<Object, Object>();
			liveAllViewerMap.put("live_record_id", recordInfo.getId());
			liveAllViewerMap.put("anchor_id", Long.valueOf(liverMap.get("id").toString()));
			liveAllViewerMap.put("anchor_room_no", recordInfo.getAnchor_room_no());
			liveAllViewerMap.put("update_time", DateUtil.format(new Date()));
			//批量修改该直播 正在观看人的观看直播记录的 
			anchorLiveRecordDao.editLiveViewerRecordByLiveId(liveAllViewerMap);
			
			List<Map<Object, Object>> editveiwerList = new ArrayList<Map<Object, Object>>();
			//计算主播直播经验值 并返回
			Map<Object, Object> resultMap = anchorViewerMemberRankService.addAnchorLiveExperience(liverMap, recordInfo, liver_rediskey,0);
			
			//统计观看该直播记录的人数
			resultMap.put("view_count", recordInfo.getView_count());
			
			resultMap.put("avatar", resultMap.get("avatar"));
			int liveTime=Integer.valueOf(resultMap.get("liveTime")==null?"0":resultMap.get("liveTime").toString());
			resultMap.put("liveTime", DateUtil.secToTime(liveTime));
			
			//获取本场直播鸟蛋数量  直播结束后返回到主播界面 展示
			resultMap.put("balanceEgg", walletMap.get("liverWalletEgg").toString());
			//本场直播结束日期
			resultMap.put("liveEndDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
			
			//修改直播记录为结束直播
			paramMap.put("id", recordInfo.getId());
			paramMap.put("zhibo_type", 5);//直播状态  结束直播
			paramMap.put("utype", "1");//用户类型
			paramMap.put("roomType", "1");//标示是退出 还是 开始
			//直播时长zhibo_duration  今日直播时长live_duration_day
			paramMap.put("endTime", DateUtil.format(new Date()));
			paramMap.put("income_egg_nums", walletMap.get("liverWalletEgg").toString());
			log.info("修改直播记录结束前，累计鸟蛋"+walletMap.get("liverWalletEgg").toString());
			//修改直播记录为 结束
			anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
			
			//将主播信息同步从redis取出的放入List中
			editveiwerList.add(resultMap);
			//同步 主播redis数据 到数据库 修改
			liveUserDao.editLiveViewerInfo(editveiwerList);
			
			/**      业务描述：新时尚大赛主播直播结束后，给推荐人发放奖励          */
			String vstarRewardIssue = propertiesUtil.getValue("vstarRewardIssue", "conf_live.properties");
			if("1".equals(vstarRewardIssue)){
				JSONObject json = new JSONObject();
				json.put("uid", liverMap.get("uid"));
				json.put("liveTime", (recordInfo.getZhibo_duration()+liveTime)/60);
				json.put("recordId", recordInfo.getId());
				SendResult vstarRewardIssueResult = producerServiceImpl.send(vstarRewardIssueInfo, json.toJSONString());
				log.info("MQ新时尚大赛推荐奖励，推送ID"+vstarRewardIssueResult.getMsgId()+"推送结果状态："+vstarRewardIssueResult.getSendStatus()+",推送信息" + json);
			}
			
			//同步完成后删除用户信息redis
			stringRedisTemplate.delete(liver_rediskey);
			//删除钱包redis
			stringRedisTemplate.delete(liver_wallet_key);
			//删除商家经纬度位置信息
			String live_seller_address_key = "live_seller_distance_"+recordInfo.getSellerid();
			stringRedisTemplate.delete(live_seller_address_key);
			
			/**
			 * 退还主播未被领取完的红包
			 */
			try {
				//查询本场直播通告是否存在有未领取的红包
				Map<Object, Object> map = new HashMap<>();
				map.put("uid", liverMap.get("uid").toString());
				map.put("liveRecordId", recordInfo.getId().toString());
				map.put("startDate", recordInfo.getStart_date());
				map.put("planStartDate", recordInfo.getPlan_start_date());
				
				Map<Object, Object> liveRedpacketInfoMap = this.getLiveRedpacketInfo(map,1);
				
				//退还鸟蛋数
				resultMap.put("refundAmount", liveRedpacketInfoMap.get("refundAmount"));
				//发送的所有鸟蛋数
				resultMap.put("sendAllLiveRedpacketAmount", liveRedpacketInfoMap.get("sendAllLiveRedpacketAmount"));
				
			} catch (Exception e) {
				log.info("退出房间失败,退还红包鸟蛋数异常");
				return new MapResponse(ResponseCode.FAILURE,"退出房间失败,退还红包鸟蛋数异常");
			}
			
			//清除推流断开次数
			stringRedisTemplate.delete(recordInfo.getAnchor_id() + "_" + CHANNEL_STOP_NUMS);
			
			log.info("退出房间成功");
			response =  new MapResponse(ResponseCode.SUCCESS,"主播退出房间");
			response.setResponse(resultMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("主播退出房间出现异常："+liver_rediskey);
			response = new MapResponse(ResponseCode.FAILURE,"主播退出房间出现异常");
		}
		return response;
	}
	
	/**
	 * 
	* @Title: getLiveRedpacketInfo
	* @Description: 获取主播总共发送的红包总鸟蛋数以及退还鸟蛋数   type = 1为退出房间退还红包   type = 2 为进入房间退还红包
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object, Object> getLiveRedpacketInfo(Map<Object, Object> paramMap,Integer type) throws Exception {
		//结果集
		Map<Object, Object> resultMap = new HashMap<>();
		
		String uid = paramMap.get("uid").toString();
		String liveRecordId = paramMap.get("liveRecordId").toString();
		
		//查询本场直播通告是否存在有未领取的红包
		Map<Object, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("liveRecordId", liveRecordId);
		Map<Object, Object> liveRedpacketMap = liveRoomDao.queryLiveRedpacketByUid(map);
		
		//如果是进入房间退还红包,则不需要查询发出总红包鸟蛋数
		Integer sendAllLiveRedpacketAmount = null;
		if (type != 2) {
			//查询该场直播,发送的所有红包总鸟蛋数，由于一条通告存在多次直播,取直播开播时间为点，来筛选红包记录
			map.put("startDate", paramMap.get("startDate")==null?paramMap.get("planStartDate"):paramMap.get("startDate"));
			sendAllLiveRedpacketAmount = liveRoomDao.queryLiveRedpacketByLiveRecordId(map);
			
		}
		
		Integer refundAmount = 0;
		if (liveRedpacketMap  != null && liveRedpacketMap.size() > 0) {
			//如果存在未被领取完的红包,则退还
			refundAmount = this.refundLiveRedpacket(Integer.parseInt(liveRedpacketMap.get("id").toString()));
			
			if (refundAmount > 0) {
				//若退还金额大于0，则退还红包鸟蛋数
				Map<String, String> getWalletMap = new HashMap<String, String>();
				getWalletMap.put("uid",uid);
				getWalletMap.put("liveRecordId", liveRecordId);
				getWalletMap.put("balance", refundAmount + "");
				getWalletMap.put("rtype", "13");
				ResponseData responseData = personalCenterService.updateWalletAmount(getWalletMap);
				if (responseData.getState() != 0) {
					//退还失败
					log.info("退出房间失败,调用支付业务系统,退还红包鸟蛋数失败");
					throw new Exception("退出房间失败,调用支付业务系统,退还红包鸟蛋数失败");
				}
			}
			//清空红包领取用户记录redis add wdh 2017-05-04
			String redPacketId = liveRedpacketMap.get("id").toString();
			String key = "get_live_redpacket_" + liveRecordId +"_"+ redPacketId;
			stringRedisTemplate.delete(key);
		}
		//清空红包列表队列redis
		stringRedisTemplate.delete("send_live_redpacket_list"+liveRecordId);  //add wdh 2017-05-04
		//退还金额
		resultMap.put("refundAmount", refundAmount);
		//本场直播发出的红包总鸟蛋数
		resultMap.put("sendAllLiveRedpacketAmount", sendAllLiveRedpacketAmount==null?0:sendAllLiveRedpacketAmount);
		
		return resultMap;
	}
	
	/**
	 * 
	* @Title: refundLiveRedpacket
	* @Description: 退还主播未被领取完的红包
	* @return boolean    返回类型
	* @throws
	 */
	public Integer refundLiveRedpacket(Integer liveRedpacketId) {
		//查询红包信息
		Map<Object, Object> liveRedpacketMap = liveRoomDao.queryLiveRedpacketById(liveRedpacketId);
		//剩余的红包鸟蛋数
		Integer remainAmount = Integer.parseInt(liveRedpacketMap.get("remain_amount").toString());
		if (remainAmount > 0) {
			//红包类型:固定
			Map<Object, Object> map = new HashMap<>();
			//红包id
			map.put("liveRedpacketId", liveRedpacketId);
			//领取用户uid
			map.put("versionLock", liveRedpacketMap.get("version_lock"));
			//未领取完的红包是否退还  0否 1是
			map.put("remainRefundType", 1);
			//更新时间
			map.put("updateTime", DateUtil.format(new Date()));
			//更新红包信息
			Integer result = liveRoomDao.updateLiveRedpacketById(map);
			if (result == 1) {
				return remainAmount;
			}
			return this.refundLiveRedpacket(liveRedpacketId);
			
		}else {
			//没有退还红包
			return 0;
		}
		
	}
	
	//校验主播当前身份 是否符合开播要求   
	public boolean validateMatchAnchor(Map<String, String> anchorMap){
		//查询主播有无报名
		boolean flag = true;
		
		//检查大赛来源的主播有无限制开播
		if (anchorMap.get("utype")!=null && anchorMap.get("utype").toString().equals("1") 
				&& anchorMap.get("signType")!=null && anchorMap.get("signType").toString().equals("4")) {
			
			String isVstar = anchorMap.get("isVstar");
			if (isVstar.equals("1")) {
				Map<Object, Object> starMap = new HashMap<Object, Object>();
				starMap.put("uid", anchorMap.get("uid"));
				starMap.put("confining", "1");//受限制的，0 否 ，1 是，默认否',
				//查询报名信息 是否受限制
				int resultNum = liveUserDao.queryVstarEnrollInfo(starMap);
				if (resultNum>0) {
					flag = false;
				}
			}
		}
		return flag;
	}
	
	
	
}
