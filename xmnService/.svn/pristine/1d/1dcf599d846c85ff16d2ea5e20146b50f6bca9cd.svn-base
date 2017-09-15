package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiveViewRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;


/**
 * 描述：主播 或者 观众累加经验值操作
 * @author yhl
 * 2016年8月17日16:18:45
 * */
@Service
public class AnchorViewerMemberRankService {

	private final Logger log = LoggerFactory.getLogger(AnchorViewerMemberRankService.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 直播礼物Dao
	 * */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	/**
	 * 直播礼物service  调用操作钱包的各种方法
	 * */
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 个人中心 service
	 * */
	@Autowired
	private PersonalCenterService personalCenterService;
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 描述：更新redis
	 * @param liver map
	 * @return 
	 * 2016-8-17 16:20:38
	 * */
	public void addOrUpdateLiveRedis(Map<String, String> anchorMap,String redisKey){
		log.info("query redis data:"+redisKey);
		stringRedisTemplate.opsForHash().putAll(redisKey, anchorMap);
	}
	
	/**
	 * 描述：设置今日的日期  组合今日是否有观看过直播 key 与免费赠送礼物次数
	 * @return 
	 * 2016-8-18 10:55:30
	 * */
	public void setTodayLiverViewerRedisKey(String todayRedis,int uid,boolean bool,int num){

		//以今日的日期+UID 作为是否首次观看的redis key
		Map<Object, Object> redisMap = new HashMap<Object, Object>();
		redisMap.put("todayStatus", bool+"");
		stringRedisTemplate.opsForHash().putAll(todayRedis, redisMap);
		stringRedisTemplate.expire(todayRedis, 1, TimeUnit.DAYS);
		
		//缓存初始化免费赠送礼物数量
		String today_gift_nums = "gift_nums_"+uid;
		Map<Object, Object> giveGiftsNumMap = new HashMap<Object, Object>();
		giveGiftsNumMap.put("number", num+"");
		stringRedisTemplate.opsForHash().putAll(today_gift_nums, giveGiftsNumMap);
		stringRedisTemplate.expire(today_gift_nums, 1, TimeUnit.DAYS);
		
	}
	
	/**
	 * 描述：获取今日用户是否有观看 获取redis key
	 * @return String  
	 * 2016-8-18 10:55:30
	 * */
	public String getTodayLiverViewerRedisKey(Integer uid){
		String today = sdf.format(new Date());
		//以今日的日期+UID 作为是否首次观看的redis key
		String redis_key = today+uid;
		return redis_key;
	}
	
	/**
	 * 描述：获取今天首次观看用户的 目前状态 和 免费送礼物个数
	 * @param uid
	 * @return  Map<Object, Object>
	 * 2016-8-18 10:55:30
	 * */
	public Map<Object, Object> getTodayRedisKeyStatus(Integer uid){
		Map<Object, Object> map = new HashMap<Object, Object>();
		String today = sdf.format(new Date());
		String today_redis_key = today+uid;
		String today_gift_nums = "gift_nums_"+uid;
		
		Map<Object, Object> redisMap = stringRedisTemplate.opsForHash().entries(today_redis_key);
		map.put("todayStatus", redisMap.get("todayStatus").toString());
		
		Map<Object, Object> giveGiftsNumMap = stringRedisTemplate.opsForHash().entries(today_gift_nums);
		map.put("number", giveGiftsNumMap.get("number").toString());
		return map;
	}
	
	/**
	 * 描述：退出房间累计主播鸟蛋
	 * @param liverMap主播信息, liveRecord直播基本信息,walletMap钱包 MAp ,
	 * 			liver_rediskey,type：0是退出同步，1红包同步
	 * 
	 * */
	public ResponseData exitRoomAnchorBirdEggTotal(Map<Object, Object> liverMap,LiveRecordInfo liveRecord,Map<Object, Object> walletMap,String liver_rediskey,Integer type){
		
		ResponseData responseData = null;
		
		try {
			//获取redis缓存数据 同步主播信息 鸟蛋
			if (walletMap!=null && !walletMap.isEmpty()) {
				Map<String, String> getWalletMap = new HashMap<String, String>();
				getWalletMap.put("uid", liverMap.get("uid").toString());
				getWalletMap.put("vkeUid", liverMap.get("vkeUid")==null?"":liverMap.get("vkeUid").toString());
//				getWalletMap.put("anchor_id", liverMap.get("id").toString());
				getWalletMap.put("balance", walletMap.get("liverWalletEgg")==null?"0":walletMap.get("liverWalletEgg").toString());//主播的鸟蛋收入 本场的鸟蛋累计
				getWalletMap.put("eggs", walletMap.get("liverWalletEgg")==null?"0":walletMap.get("liverWalletEgg").toString());//主播的鸟蛋收入 本场的鸟蛋累计
				getWalletMap.put("liveRecordId", liveRecord.getId().toString());
				getWalletMap.put("rtype", "5");
				
				//获取redis 累加的鸟蛋数量  修改直播钱包表鸟蛋数
				responseData =	personalCenterService.updateWalletAmount(getWalletMap);
				//如果是红包同步只需累计鸟蛋 同步数据库  直接返回状态
				if (type == 1) {
					
					String uid = liverMap.get("uid").toString();
					//缓存主播鸟蛋
					Map<Object, Object> anginWalletMap = new HashMap<Object, Object>();
					
					Map<String, String> liveWalletMap = personalCenterService.getLiveWalletInfo(uid);
					if (!liveWalletMap.isEmpty() && liveWalletMap!=null) {
						BigDecimal wallet_balance = new BigDecimal(liveWalletMap.get("balance")==null?"0":liveWalletMap.get("balance"));
						String liver_wallet_key = "liver_wallet_"+Long.valueOf(uid);
						
						//表示主播钱包鸟蛋-用于接收鸟蛋累加的 包括发礼物，私信 所得的累计(进房间初始化为0)
						anginWalletMap.put("liverWalletEgg", "0");//累计直播鸟蛋使用
						anginWalletMap.put("liverBirdEgg", wallet_balance+"");//进入房间前的鸟蛋+直播过程中收取的鸟蛋数量
						stringRedisTemplate.opsForHash().putAll(liver_wallet_key, anginWalletMap);
						
						//将当前鸟蛋放入主播基本信息中
//						liverMap.put("birdEgg", Double.valueOf(wallet_balance.toString()).intValue()+"");
						liverMap.put("birdEgg", wallet_balance.toString()+"");
						log.info("初始化钱包成功："+liver_wallet_key);
						
					}else {
						anginWalletMap.put("liverWalletEgg", "0");//累计直播鸟蛋使用
						log.info("获取主播钱包信息失败,错误信息:用户uid:" + uid);
					}
					return responseData;
				}
				
				if (responseData.getState() == 0) {
					//计算主播经验
					Map<Object, Object> resultMap = this.addAnchorLiveExperience(liverMap, liveRecord, liver_rediskey,0);
					List<Map<Object, Object>> editveiwerList = new ArrayList<Map<Object, Object>>();
					editveiwerList.add(resultMap);
					liveUserDao.editLiveViewerInfo(editveiwerList); //同步Redis主播信息到数据库
					log.info("累计鸟蛋执行成功，数据同步数据库成功");
					return responseData;
					
				}else {
					log.info("累计鸟蛋执行失败，数据同步数据库失败,支付服务返回累计鸟蛋状态:"+responseData.getState());
					
					//记录主播异常退出本场次异常退出  鸟蛋累计失败记录 便于查询
					getWalletMap.put("state", responseData.getState()+"");
					getWalletMap.put("create_time", DateUtil.format(new Date(),DateUtil.defaultSimpleFormater));
					anchorLiveRecordDao.inserLiveExitRoomFailedRecord(getWalletMap);
					return responseData;
				}
				
			}else {
				//操作出错
				log.info("主播退出房间获取鸟蛋redis 失败："+liverMap.get("uid"));
				responseData = new ResponseData();
				responseData.setState(1);
				return responseData;
			}
		} catch (Exception e) {
			log.info("主播退出房间执行操作失败："+liverMap.get("uid"));
			e.printStackTrace();
			responseData = new ResponseData();
			responseData.setState(1);
		}
		return responseData;
	}
	
	
	
	/**
	 * 
	* 累计鸟蛋到ridis 
	* @param giftRecordId 发送礼物记录ID,  anchor_id 主播ID , 
	* 		 gift_price 礼物价值, type鸟蛋累计类型： 0 礼物,1私信,2弹幕
	* @return void
	 */
	public Object accumulativeBirdEgg(Integer giftRecordId,LiverInfo liverInfo,BigDecimal gift_price,int type,int giftNum){
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			//获取主播基本信息   累加主播经验  +  鸟蛋  
			String anchorRedisKey = "liver_"+liverInfo.getUid();
			Map<Object, Object>  anchorMap= stringRedisTemplate.opsForHash().entries(anchorRedisKey);
			
			//获取主播钱包redis 用于鸟蛋累加
			String liver_wallet_key = "liver_wallet_"+liverInfo.getUid();
			
			Map<Object, Object>  anchorBirdEggMap= stringRedisTemplate.opsForHash().entries(liver_wallet_key);
			
			//累加主播鸟蛋
			if (!anchorMap.isEmpty() && anchorMap!=null && !anchorBirdEggMap.isEmpty() && anchorBirdEggMap!=null) {
				
				try {
					//累计当前鸟蛋  本场获取的鸟蛋累计
					Map<String, String> eggMap = new HashMap<String, String>();
					
					BigDecimal liverWalletEgg = new BigDecimal(anchorBirdEggMap.get("liverWalletEgg").toString()).add(gift_price);
					BigDecimal liverBirdEgg = new BigDecimal(anchorBirdEggMap.get("liverBirdEgg").toString()).add(gift_price);
					eggMap.put("liverWalletEgg", liverWalletEgg.toString());//主播直播中本场累计直播鸟蛋使用
					eggMap.put("liverBirdEgg", liverBirdEgg.toString());//进入房间前+直播过程中的鸟蛋数量
					
//					log.info("========鸟蛋累计前:"+anchorBirdEggMap.get("liverWalletEgg")  +"  ===================鸟蛋累计后"+liverWalletEgg);
					//如果type 类型等于 0 累计主播获取的礼物个数 每次 加 1    //弹幕私信 不去不参与分账
					if (type == 0) {
						anchorMap.put("gived_gifts_nums", Integer.parseInt(anchorMap.get("gived_gifts_nums").toString())+giftNum);
						
						//将更新后的鸟蛋重新放入redis  累计redis 操作
						this.addOrUpdateLiveRedis(eggMap, liver_wallet_key);
						//再次取出判断是否累加成功
						Map<Object, Object>  anchorMap2= stringRedisTemplate.opsForHash().entries(liver_wallet_key);
						
//						log.info("===============鸟蛋累计后的新数据:"+anchorMap2.get("liverWalletEgg"));
						
						if(null != anchorMap2.get("liverWalletEgg") && !StringUtils.isEmpty(anchorMap2.get("liverWalletEgg")+"") ){
							BigDecimal new_egg = new BigDecimal(anchorMap2.get("liverWalletEgg").toString());
							
							if (new_egg.equals(liverWalletEgg)) {
								//如果type = 0 就去修改礼物的预接收状态
								resultMap.put("totalBirdEgg", liverWalletEgg);
								
								if (type == 0) {
									Map<Object,Object> GigftRecordMap=new HashMap<>();
									GigftRecordMap.put("id", giftRecordId);
									GigftRecordMap.put("advanced_status", 2);
									GigftRecordMap.put("update_time", DateUtil.format(new Date()));
									liveGiftsInfoDao.modifyLiveGivedGiftRecord(GigftRecordMap);
								}
							}else {
								this.modifyGiveGiftsStatus(giftRecordId,0);
							}
						}else {
							this.modifyGiveGiftsStatus(giftRecordId,0);
						}
					}
				} catch (Exception e) {
					//出现异常修改该记录 是否是失败记录
					this.modifyGiveGiftsStatus(giftRecordId,0);
					e.printStackTrace();
					log.info("主播预接收鸟蛋redis同步失败！",liver_wallet_key);
					return new MapResponse(ResponseCode.FAILURE, "鸟蛋预接收失败");
				}
//				stringRedisTemplate.opsForHash().putAll(liver_key, anchorBirdEggMap);
			}else {
				if (type == 0) {
					//赠送礼物异常处理补偿
					this.modifyGiveGiftsStatus(giftRecordId,0);
					
				}else if (type == 1) {
					//私信异常处理补偿
					
				}else if (type == 2) {
					/*
					//弹幕异常退出补偿
					String barrageRedisKey = "liver_barrage_"+liverInfo.getUid();
					String barrage= stringRedisTemplate.opsForValue().get("barrageRedisKey");
					//存在弹幕redis，则累加 
					if (StringUtils.isNotEmpty(barrage)) {
						stringRedisTemplate.opsForValue().set(barrageRedisKey, ArithUtil.add(Double.parseDouble(barrage), gift_price.doubleValue()) + "");
					}else {
						stringRedisTemplate.opsForValue().set(barrageRedisKey, gift_price + "");
					}
					*/
				}
				
			}
		} catch (Exception e) {
			//出现异常修改该记录 是否是失败记录
			if (type == 0) {
				//赠送礼物异常处理补偿
				this.modifyGiveGiftsStatus(giftRecordId,0);
				
			}else if (type == 1) {
				//私信异常处理补偿
				
			}else if (type == 2) {
				/*
				//弹幕异常退出补偿
				String barrageRedisKey = "liver_barrage_"+liverInfo.getUid();
				String barrage= stringRedisTemplate.opsForValue().get("barrageRedisKey");
				//存在弹幕redis，则累加 
				if (StringUtils.isNotEmpty(barrage)) {
					stringRedisTemplate.opsForValue().set(barrageRedisKey, ArithUtil.add(Double.parseDouble(barrage), gift_price.doubleValue()) + "");
				}else {
					stringRedisTemplate.opsForValue().set(barrageRedisKey, gift_price + "");
				}
				*/
			}
			
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "鸟蛋预接收失败");
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"赠送礼物成功");
		response.setResponse(resultMap);
		return response;
	}
	
	
	/**
	 * 描述：修改接收鸟蛋失败记录状态
	 * @param giftRecordId发礼物记录表ID  type累计鸟蛋类型
	 * */
	public void modifyGiveGiftsStatus(Integer giftRecordId,int type){
		//礼物赠送异常处理
		if (type == 0) {
			Map<Object,Object> GigftRecordMap=new HashMap<>();
			GigftRecordMap.put("id", giftRecordId);
			GigftRecordMap.put("isfailed", 1);//标示失败的记录 /用于定时任务执行后核对数据使用
			GigftRecordMap.put("update_time", DateUtil.format(new Date()));
			liveGiftsInfoDao.modifyLiveGivedGiftRecord(GigftRecordMap);
		}
	}
	
	
	
	/**
	 * 描述：累计用户经验值
	 * @param  liverMap  当前用户  
	 * 		   gift_experience   累计经验, 
	 * 		   liver_redis_key   redis_key
	 * @return void
	 * 2016-8-18 14:58:01
	 * @throws Exception 
	 * */
	public Map<Object, Object> addLiverViewerExpe(Map<Object, Object> liverMap,int gift_experience,String liver_redis_key,int type) throws Exception{
		Map<Object, Object> new_Map = new HashMap<Object, Object>();
		try {
			//累计前的观众经验
//			Integer cuurent_expr_old = Integer.parseInt(liverMap.get("current_expe").toString());
			//累计后的观众经验
			Integer current_expe = gift_experience+Integer.parseInt(liverMap.get("current_expe").toString());
			Integer currentRankNo = Integer.parseInt(liverMap.get("rank_no").toString());
			liverMap.put("current_expe", current_expe);
			
			//获取当前经验值对应的等级
			Map<Object, Object> rankMap = liveUserDao.queryMemberRankByExp(current_expe);
			//根据当前用户现有的经验达到更高一级 则升级用户等级 头衔等   
			if (rankMap != null && Integer.parseInt(rankMap.get("rank_no").toString())>=currentRankNo) {
				liverMap.put("rank_id", rankMap.get("id").toString());
				liverMap.put("rank_no", rankMap.get("rank_no").toString());
				liverMap.put("achievement", rankMap.get("member_rank_name")==null?"":rankMap.get("member_rank_name").toString());
				
				//用户升级提醒(即经验累积后等级大于当前等级)
				if(Integer.parseInt(rankMap.get("rank_no").toString())>currentRankNo && Integer.parseInt(rankMap.get("rank_no").toString())>=32){
					log.info("=====================准备推送升级信息======================");
					sendGroupMsg(liverMap);
					log.info("=====================推送升级信息完成======================");
				}
			}
			//redis 存储用户经验升级后基本信息
			if (liverMap!=null) {
				for (Object key : liverMap.keySet()) { 
					new_Map.put(key, liverMap.get(key)+"");
				}  
			}
			//同步到观众redis缓存
			stringRedisTemplate.opsForHash().putAll(liver_redis_key, new_Map);
			//增加经验记录信息
			this.insertExpericeRecord(liverMap,gift_experience);
			
			//日志
			log.info("liverMap data:" + new_Map.toString());
		} catch (Exception e) {
			log.info("累计用户经验错误："+liver_redis_key);
			e.printStackTrace();
			throw new Exception("累计用户经验错误");
		}
		return new_Map;
	}
	
	
	/**
	 * 
	* @方法名称: insertExpericeRecord
	* @描述: 增加经验记录信息
	* @返回类型 int
	* @创建时间 2016年10月13日
	* @param map
	* @return
	* @throws Exception
	 */
	public int insertExpericeRecord(Map<Object, Object> liverMap,int gift_experience) throws Exception{
		try{
			Map<Object,Object> expMap=new HashMap<>();
			expMap.put("liver_id", liverMap.get("id")==null?"":liverMap.get("id"));//用户id
			expMap.put("uid", liverMap.get("uid")==null?"":liverMap.get("uid"));//寻蜜鸟会员id
			expMap.put("liver_utype", liverMap.get("utype")==null?"":liverMap.get("utype"));//直播用户类型： 1 主播 2 普通用户
			expMap.put("anchor_room_no", liverMap.get("anchor_room_no")==null?"":liverMap.get("anchor_room_no"));//主播房间编号
			expMap.put("live_record_id", liverMap.get("live_record_id")==null?0:liverMap.get("live_record_id"));//直播记录id
			expMap.put("get_experience", gift_experience);//获取经验值
			expMap.put("get_experience_type", liverMap.get("get_experience_type"));//经验获取方式 1 赠送礼物，2发送弹幕  3 发送私信 4分享好友  5直播或观看经验
			expMap.put("create_time", DateUtil.format(new Date()));
			expMap.put("update_time", DateUtil.format(new Date()));
			return liveGiftsInfoDao.insertExpericeRecord(expMap);
		}catch(Exception e){
			log.error("增加经验记录异常");
			e.printStackTrace();
			throw new Exception("增加经验记录异常");
		}
	}
	/**
	 * 根据观众观看时长累计经验值
	 * @param livermap  liverRoomRequest  redis_key
	 * @param type：0 正常情况下累计经验 ，1 定时器凌晨执行累计经验 (用于跨天直播处理经验)
	 * @return Map<Object, Object> (livermap)
	 * */
	public Map<Object, Object> addViewerOrAnchorExperience(Map<Object,  Object> liverMap,LiverRoomRequest liverRoomRequest,String liver_rediskey,int type) throws Exception {
		try {
			//查询出用户观看直播的记录 
			liverMap.put("live_record_id", liverRoomRequest.getZhiboRecordId());
			liverMap.put("id", liverMap.get("id"));
			LiveViewRecordInfo viewRecordInfo = anchorLiveRecordDao.queryLiverViewRecord(liverMap);
			
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("id", liverRoomRequest.getZhiboRecordId());
			//查询群组号
			LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
			if (liveRecordInfo==null) {
				liverMap = verifyLiveMap(liverMap,type);
				return liverMap;
			}
			Map<Object, Object> map = liveUserDao.queryLiverInfoById(Integer.parseInt(liveRecordInfo.getAnchor_id().toString()));
			liverMap.put("GroupId", map.get("group_id").toString());
			liverMap.put("live_record_id", liverRoomRequest.getZhiboRecordId());
			liverMap.put("get_experience_type", "5");
			if (!StringUtils.isEmpty(liverMap.get("view_duration_day")+"") ) {
				long today_time = Math.round(Integer.parseInt(liverMap.get("view_duration_day")==null?"0":liverMap.get("view_duration_day").toString())/60);
				long view_duration = liverMap.get("view_duration")==null?0:Long.valueOf(liverMap.get("view_duration").toString());
				
				if (today_time<Constant.LIVE_VIEW_LONG) {
					
					//得到观看时长  便于累加
					if (viewRecordInfo!=null) {
						
						//观看记录结束时间不为空
						if (null!=viewRecordInfo.getEnd_time()) {
							
							//直播完时间 - 已经存在的结束时间 得到秒  + 累加本日已经观看过直播的总时长  单位为：秒
							long overTime = DateUtil.parse(DateUtil.format(new Date())).getTime();
							long endTime = viewRecordInfo.getUpdate_time().getTime();
							long viewTime = Math.round(((overTime-endTime)/60000));//得到分钟数
							liverMap.put("viewTime", viewTime*60);//本次观看时长
							if (today_time<Constant.LIVE_VIEW_LONG) { //如果今天观看未满4小时
								long today_time_total = today_time + viewTime; //今天观看累计本次观看时长  等于今日之和
								if (today_time_total>Constant.LIVE_VIEW_LONG) { //如果今日累加总和大于240分钟
//									long out_time = today_time_total-240; //今日累加和 减去 4小时 获取超出计算经验部分
									long curr_time = Constant.LIVE_VIEW_LONG-today_time ; //得出需要累计经验的时间
									
									//计算观看时间的经验值  必须大于5才计算
									if (curr_time>=Constant.LIVE_EXPERIENCE_LONG) {
										//累加经验值  当前时长 除以 5  乘以 9  得到经验值
										int curr_duration = (int)Math.round(curr_time/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_VIEW_EXPERIENCE;
										liverMap.put("view_duration_day", today_time_total*60);//转换为秒
										liverMap.put("view_duration",  view_duration+ viewTime*60);//转换为秒
										liverMap = verifyLiveMap(liverMap,type);
										return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
									}else {
										return verifyLiveMap(liverMap,type);
									}
								}else {
									//计算观看时间的经验值  必须大于5才计算
									if (viewTime>=Constant.LIVE_EXPERIENCE_LONG) {
										//累加经验值  当前时长 除以 5  乘以 9  得到经验值
										int curr_duration = (int)Math.round(viewTime/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_VIEW_EXPERIENCE;
										liverMap.put("view_duration_day", today_time_total*60);//转换为秒
										liverMap.put("view_duration", view_duration + viewTime*60);//转换为秒
										liverMap = verifyLiveMap(liverMap,type);
										return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
									}else {
										
										return verifyLiveMap(liverMap,type);
									}
								}
							}
						}else {
							//如果其结束时间为空，取系统当前时间 - 开始观看时间
							long overTime = DateUtil.parse(DateUtil.format(new Date())).getTime();
							long startTime = viewRecordInfo.getStart_time().getTime();
							long viewTime = Math.round(((overTime-startTime)/60000));
							liverMap.put("viewTime", viewTime*60);//本次观看时长
							if (today_time<Constant.LIVE_VIEW_LONG) { //如果今天观看未满4小时
								long today_time_total = today_time + viewTime; //今天观看累计本次观看时长  等于今日之和
								if (today_time_total>Constant.LIVE_VIEW_LONG) { //如果今日累加总和大于4小时
//									long out_time = today_time_total-today_time; //今日累加和 减去 4小时 获取超出计算经验部分
									long curr_time = Constant.LIVE_VIEW_LONG-today_time ; //得出需要累计经验的时间
									
									//计算观看时间的经验值  必须大于5才计算
									if (curr_time>=Constant.LIVE_EXPERIENCE_LONG) {
										//累加经验值  当前时长 除以 5  乘以 9  得到经验值
										int curr_duration = (int)Math.round(curr_time/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_VIEW_EXPERIENCE;
										liverMap.put("view_duration_day", today_time_total*60);//转换为秒
										liverMap.put("view_duration", view_duration + viewTime*60);
										liverMap = verifyLiveMap(liverMap,type);
										return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
									}else {
										return verifyLiveMap(liverMap,type);
									}
								}else {
									//计算观看时间的经验值  必须大于5才计算
									if (viewTime>=Constant.LIVE_EXPERIENCE_LONG) {
										//累加经验值  当前时长 除以 5  乘以 9  得到经验值
										int curr_duration = (int)Math.round(viewTime/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_VIEW_EXPERIENCE;
										liverMap.put("view_duration_day", today_time_total*60);//转换为秒
										liverMap.put("view_duration", view_duration + viewTime*60);
										liverMap = verifyLiveMap(liverMap,type);
										return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);										
									}else {
										return verifyLiveMap(liverMap,type);
									}
								}
							}
						}
						 
					}
					
				}
			}
		
		} catch (Exception e) {
			log.info("累计观看时长经验异常");
			e.printStackTrace();
			throw new Exception("累计观看时长经验异常");
		}
		return verifyLiveMap(liverMap,type);
		
	}
	
	
	/**
	 * 根据主播直播时长累计经验值
	 * @param livermap  liverRoomRequest  redis_key  
	 * @param type：0 正常情况下累计经验 ，1 定时器凌晨执行累计经验 (用于跨天直播处理经验)
	 * @return Map<Object, Object> (livermap)
	 * */
	public Map<Object, Object> addAnchorLiveExperience(Map<Object,  Object> liverMap,LiveRecordInfo recordInfo,String liver_rediskey,int type) throws Exception {
		try {
			//查询群组号
			Map<Object, Object> map = liveUserDao.queryLiverInfoById(Integer.parseInt(recordInfo.getAnchor_id() + ""));
			liverMap.put("GroupId", map.get("group_id").toString());
			
			Long startTime = recordInfo.getStart_date().getTime();//开播时间
			Long endTime = DateUtil.parse(DateUtil.format(new Date())).getTime();   //当前时间  算作直播结束时间
			
			long liveTimeSec = Math.round((endTime-startTime)/1000); //返回直播记录当前直播了多少秒  便于转换为00:00:00格式
			long liveTime = Math.round((endTime-startTime)/60000); //获取 结束 减去 开播时间  得到分钟
			liverMap.put("viewTime", liveTime*60);//本次观看时长
			long today_live_time =0L;
			long today_live_all_time = 0L;
			
			//结束时间如果不等于空  标示直播以前开播过 或者异常结束过  直播时长 和 主播直播经验在之前累计过
			if (null!=recordInfo.getEnd_date()) {
				//取出修改的updatetime 时间  在再次开播的时候 会修改相应的时间戳
				//再次退出计算经验会按照 当前日期时间 - 进房间修改的时间  得到每次直播中间的直播时长
				startTime = recordInfo.getUpdate_time().getTime();
				liveTimeSec = Math.round((endTime-startTime)/1000); //返回直播记录当前直播了多少秒  便于转换为00:00:00格式
				liveTime = Math.round((endTime-startTime)/60000); //获取 结束 减去 开播时间  得到分钟
				liverMap.put("viewTime", liveTime*60);//本次观看时长
				today_live_time = Math.round(Long.valueOf(liverMap.get("live_duration_day")==null?"0":liverMap.get("live_duration_day").toString()))/60; //得到直播累计观看 秒转分钟
				today_live_all_time = liveTime+today_live_time;//本次直播时长  + 今日直播时长 =  得到今天累加的直播时长 (单位：分钟)
				
				//直播记录总时长 便于累计 此处(单位：秒)
				long live_duration = liverMap.get("live_duration")==null?0:Long.valueOf(liverMap.get("live_duration").toString());
				
				//存入map集合中  返回
				liverMap.put("live_duration_day", today_live_all_time*60); //今天累计时长  转换为秒
				liverMap.put("live_duration", live_duration + liveTime*60); //累计所以直播时长 转换为秒
				liverMap.put("liveTime", liveTimeSec);//本场直播时长(单位：秒)
				
				if (today_live_time<Constant.LIVE_ANCHOR_LONG) {//如果今天累计直播不大于8小时 480分钟
					
					if (today_live_all_time>Constant.LIVE_ANCHOR_LONG) {//如果今天直播累计大于了480分钟  则取 480 - today_live_time（今天已经累计的时长）
						long curr_time = Constant.LIVE_ANCHOR_LONG-today_live_time;
						if (curr_time>=Constant.LIVE_EXPERIENCE_LONG) {//如果大于等于5分钟 加经验
							//累加经验值  当前时长 除以 5  乘以 18  得到经验值
							int curr_duration = (int)Math.round(curr_time/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_ANCHOR_EXPERIENCE;
							liverMap = verifyLiveMap(liverMap,type);
							return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
						}
					}else {
						if (liveTime>=Constant.LIVE_EXPERIENCE_LONG) {//如果大于等于5分钟 加经验
							//累加经验值  当前时长 除以 5  乘以 18  得到经验值
							int curr_duration = (int)Math.round(liveTime/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_ANCHOR_EXPERIENCE;
							liverMap = verifyLiveMap(liverMap,type);
							return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
						}
					}
					
				}else {
					liverMap = verifyLiveMap(liverMap,type);
					return this.addLiverViewerExpe(liverMap, 0, liver_rediskey,type);
				}
				
			}else {
				today_live_time = Math.round(Long.valueOf(liverMap.get("live_duration_day")==null?liverMap.get("live_duration_day").toString():"0"))/60; //得到直播累计观看 秒转分钟
				today_live_all_time = liveTime+today_live_time;//本次直播时长  + 今日直播时长 =  得到今天累加的直播时长 (单位：分钟)
				
				//直播记录总时长 便于累计 此处(单位：秒)
				long live_duration = liverMap.get("live_duration")==null?0:Long.valueOf(liverMap.get("live_duration").toString());
				
				//存入map集合中  返回
				liverMap.put("live_duration_day", today_live_all_time*60); //今天累计时长  转换为秒
				liverMap.put("live_duration", live_duration + liveTime*60); //累计所以直播时长 转换为秒
				liverMap.put("liveTime", liveTimeSec);//本场直播时长(单位：秒)
				if (today_live_time<Constant.LIVE_ANCHOR_LONG) {//如果今天累计直播不大于8小时 480分钟
					
					if (today_live_all_time>Constant.LIVE_ANCHOR_LONG) {//如果今天直播累计大于了480分钟  则取 480 - today_live_time（今天已经累计的时长）
						long curr_time = Constant.LIVE_ANCHOR_LONG-today_live_time;
						if (curr_time>=Constant.LIVE_EXPERIENCE_LONG) {//如果大于等于5分钟 加经验
							//累加经验值  当前时长 除以 5  乘以 18  得到经验值
							int curr_duration = (int)Math.round(curr_time/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_ANCHOR_EXPERIENCE;
							liverMap = verifyLiveMap(liverMap,type);
							return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
						}
					}else {
						if (liveTime>=Constant.LIVE_EXPERIENCE_LONG) {//如果大于等于5分钟 加经验
							//累加经验值  当前时长 除以 5  乘以 18  得到经验值
							int curr_duration = (int)Math.round(liveTime/Constant.LIVE_EXPERIENCE_LONG)*Constant.LIVE_ANCHOR_EXPERIENCE;
							liverMap = verifyLiveMap(liverMap,type);
							return this.addLiverViewerExpe(liverMap, curr_duration, liver_rediskey,type);
						}
					}
					
				}else {
					liverMap = verifyLiveMap(liverMap,type);
					return this.addLiverViewerExpe(liverMap, 0, liver_rediskey,type);
				}
			}
			
		} catch (Exception e) {
			log.info("累计观看时长经验异常");
			e.printStackTrace();
			throw new Exception("累计观看时长经验异常");
		}
		return verifyLiveMap(liverMap,type);
	}
	
	/**
	 * 重置当天正在观看或直播累计经验为0
	 * @param liveMap   ,  type 1 为定时任务开启执行
	 * @return liveMap
	 * */
	public Map<Object,  Object> verifyLiveMap(Map<Object,  Object> liveMap,int type){
		if (type == 1) {//定时任务执行时重置当天观看时长   表示“直播|观看”已经跨天了 当天经验重新累计
			liveMap.put("live_duration_day", "0");
			liveMap.put("view_duration_day", "0");
			return liveMap;
		}else {
			return liveMap;
		}
	}
	
	
	/** 
	 * 调用腾讯API提示通知用户升级信息
	 * @param toAccout 消息接收方账号(用户手机号)
	 * @return void 返回类型
	 * @throws
	 */
	private void sendGroupMsg(Map<Object, Object> liverMap) {
		log.info("sendMsg()开始执行：liverMap={}",liverMap);
		try{
			String uid = liverMap.get("uid").toString();
			Map<Object,Object> liverInfo = liveUserDao.queryLiverInfoByUid(Integer.valueOf(uid));
			if(liverInfo==null){
				log.info("数据异常，用户uid={}不存在！",uid);
				return;
			}
			
			//从redis中,获取管理员签名
			String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
			
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (adminSig == null) {
				//调用tls,获取管理员tls的sig
				adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
				stringRedisTemplate.opsForValue().set("adminSig", adminSig);
				stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
			}
			
			//生成请求参数paramMap
			Map<Object,Object> paramMap=new HashMap<Object,Object>();
			paramMap.put("tlsSig", adminSig);
			paramMap.put("sdkappid", sdkAppid);
			paramMap.put("identifier", identifier);
			System.out.println("升级用户----------------------------------："+liverInfo.get("phone").toString());
			paramMap.put("GroupId", liverMap.get("GroupId").toString());
			paramMap.put("From_Account", liverInfo.get("phone").toString());
			paramMap.put("MsgType", "TIMCustomElem");
			
			Map<Object,Object> contentMap = new HashMap<Object,Object>();
			
			//自定义data类型参数
			String iconUrl = liverMap.get("avatar") == null?"":fileUrl+liverMap.get("avatar").toString();
			Map<Object,Object> dataMap = new HashMap<Object,Object>();
			dataMap.put("uid", uid);//用户id
			dataMap.put("iconUrl", iconUrl);//头像地址
			dataMap.put("dj", Integer.parseInt(liverMap.get("rank_no").toString()));//当前等级
			dataMap.put("uname", liverInfo.get("uname")!=null?liverInfo.get("uname").toString():"");//用户登录账号
			dataMap.put("type", 7);
			contentMap.put("Data", JSONObject.toJSONString(dataMap));
			paramMap.put("MsgContent", contentMap);
			
			//发送群组聊天信息
			boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
			if(!sendResult){
				log.info("用户升级提醒调用腾讯接口失败!");
				return;
			}
			log.info("已提醒用户升级成功！");
			
		}catch(Exception e){
			log.error("提醒用户升级，接口参数生成异常：{}",e.toString());
			e.printStackTrace();
		}
	}
	
	/** 
	 * 调用腾讯API提示通知用户苗费发送三个礼物信息
	 * @param toAccout 消息接收方账号(用户手机号)
	 * @return void 返回类型
	 */
	public void sendFreeGiftsMsg(Map<Object, Object> liverMap,LiveGiftsInfo giftsInfo,LiverInfo liverInfo) {
		log.info("sendFreeGiftsMsg()开始执行：liverMap={}",liverMap);
		try{
			if(liverMap==null){
				log.info("数据异常，用户uid={}不存在！"+liverMap.get("uid"));
				return;
			}
			
			//从redis中,获取管理员签名
			String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
			
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (adminSig == null) {
				//调用tls,获取管理员tls的sig
				adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
				stringRedisTemplate.opsForValue().set("adminSig", adminSig);
				stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
			}
			
			//生成请求参数paramMap
			Map<Object,Object> paramMap=new HashMap<Object,Object>();
			paramMap.put("tlsSig", adminSig);
			paramMap.put("sdkappid", sdkAppid);
			paramMap.put("identifier", identifier);
			System.out.println("用户发送免费礼物====："+liverMap.get("phone").toString());
			paramMap.put("From_Account", liverMap.get("phone").toString());
			paramMap.put("MsgType", "TIMCustomElem");
			paramMap.put("GroupId", liverInfo.getGroup_id());
			
			Map<Object,Object> contentMap = new HashMap<Object,Object>();
			//自定义data类型参数
			Map<Object,Object> dataMap = new HashMap<Object,Object>();
			dataMap.put("giftId", giftsInfo.getId());//用户id
			dataMap.put("giftCount", 3);//用户名称
			dataMap.put("giftName", giftsInfo.getGift_name());//用户名称
			dataMap.put("type", 4);
			
			contentMap.put("Data", JSONObject.toJSONString(dataMap));
			paramMap.put("MsgContent", contentMap);
			
			//发送群组聊天信息
			boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
			if(!sendResult){
				log.error("用户发送免费礼物调用腾讯接口失败!");
				return;
			}
			log.info("用户发送免费礼物成功！");
		}catch(Exception e){
			log.error("用户发送免费礼物，接口参数生成异常：{}",e.toString());
			e.printStackTrace();
		}
	}
	
}
