package com.xmniao.xmn.core.live.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.xmniao.xmn.core.common.request.BannerRequest;
import com.xmniao.xmn.core.common.service.CommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.common.request.live.GetLiveRedpacketRequest;
import com.xmniao.xmn.core.common.request.live.SendLiveRedpacketRequest;
import com.xmniao.xmn.core.integral.dao.IntegralMallDao;
import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.live.dao.LiveRoomDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.response.BirdCoinRecordResponse;
import com.xmniao.xmn.core.live.response.BirdCurrencyStatisResponse;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.ThriftService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveRoomService   
* 类描述：   直播间处理service
* 创建人：yezhiyong   
* 创建时间：2016年12月15日 上午11:00:20   
* @version    
*
 */
@Service
public class LiveRoomService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRoomService.class);
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入liveRoomDao
	 */
	@Autowired
	private LiveRoomDao liveRoomDao;
	
	/**
	 * 注入fileUrl
	 */
	@Autowired
	private String fileUrl;

	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private ThriftService thriftService;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入personalCenterService
	 */
	@Autowired
	private PersonalCenterService personalCenterService;
	
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
	 * 注入anchorViewerMemberRankService
	 */
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	/**
	 * 注入liveHomeService
	 */
	@Autowired
	private LiveHomeService liveHomeService;
	
	/**
	 * 注入integralMallDao
	 */
	@Autowired
	private IntegralMallDao integralMallDao;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 
	* @Title: queryEnterRoomLoading
	* @Description:  进入房间初始加载页
	* @return Object    返回类型
	* @throws
	 */
	public Object queryEnterRoomLoading(IDRequest idRequest) {
		//直播记录id
		Integer id = idRequest.getId();
		//纬度
		Double latitude = idRequest.getLatitude();
		//经度
		Double longitude = idRequest.getLongitude();
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liveRecordId", id);
					
			if (latitude != null && latitude != 0 && longitude != null && longitude != 0) {
				paramMap.put("latitude", latitude);
				paramMap.put("longitude", longitude);
				
			}
			
			//通过记录id查询直播记录信息
			Map<Object, Object> liveRecordMap = liveRoomDao.queryLiveRecordById(paramMap);
			if (liveRecordMap == null || liveRecordMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录");
			}
			
			//距离
			if (liveRecordMap.get("distance") != null &&StringUtils.isNotEmpty(liveRecordMap.get("distance").toString()) && Double.parseDouble(liveRecordMap.get("distance").toString()) > 0) {
				String distanceStr = "";
				if (Double.parseDouble(liveRecordMap.get("distance").toString()) < 1000) {
					distanceStr = liveRecordMap.get("distance").toString() + "m";
				}else {
					distanceStr = ArithUtil.div(Double.parseDouble(liveRecordMap.get("distance").toString()), 1000, 2) + "km";
				}
				
				resultMap.put("distance", distanceStr);
				
			}
			
			//直播标题
			resultMap.put("zhiboTitle", liveRecordMap.get("zhibo_title")==null?"":liveRecordMap.get("zhibo_title").toString());
			//直播封面
			resultMap.put("zhiboCover", liveRecordMap.get("zhibo_cover")==null?"": fileUrl + liveRecordMap.get("zhibo_cover").toString());
			//主播昵称
			resultMap.put("nname", liveRecordMap.get("nname")==null?"":liveRecordMap.get("nname").toString());
			//店铺名称
			resultMap.put("sellerName", liveRecordMap.get("sellername")==null?"":liveRecordMap.get("sellername").toString());
			//如果存在商家别名,使用商家别名
			if (liveRecordMap.get("seller_alias") != null && StringUtils.isNotEmpty(liveRecordMap.get("seller_alias").toString())) {
				resultMap.put("sellerName", liveRecordMap.get("seller_alias").toString());
				
			}
			//直播地址
			resultMap.put("zhiboAddress", liveRecordMap.get("zhibo_address")==null?"":liveRecordMap.get("zhibo_address").toString());
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "进入房间初始加载页成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("进入房间初始加载页失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "进入房间初始加载页失败");
		}
	}

	/**
	 * 
	* @Title: queryLiveAnchorImage
	* @Description: 获取主播的相册信息
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveAnchorImage(PageRequest pageRequest) {
		//获取uid
		String uid = sessionTokenService.getStringForValue(pageRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			
			//根据uid查询主播的相册信息
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("uid", Integer.parseInt(uid));
			paramMap.put("page", pageRequest.getPage());
			paramMap.put("pageSize", pageRequest.getPageSize());
			List<Map<Object, Object>> anchorImageList = liveUserDao.queryLiveAnchorImageByUid(paramMap);
			
			if (anchorImageList == null) {
				return new BaseResponse(ResponseCode.FAILURE, "获取主播的相册信息失败");
			}
			
			if (anchorImageList.size() > 0) {
				for (Map<Object, Object> anchorImageMap : anchorImageList) {
					Map<Object, Object> map = new HashMap<>();
					map.put("cover", anchorImageMap.get("anchor_image")==null?"":fileUrl + anchorImageMap.get("anchor_image").toString());
					resultList.add(map);
				}
				
			}
			
			//存放入结果集
			resultMap.put("coverList", resultList);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取主播的相册信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取主播的相册信息失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取主播的相册信息失败");
		}
	}

	public Object birdConinRecordList(PageTypeRequest request) {
		try {
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null"))
			{
				return new BaseResponse(ResponseCode.DATAERR,"身份令牌错误或已过期，请重新登入!");
			}
			List<BirdCoinRecordResponse> resultList = new ArrayList<>();
			/*调用支付服务获取收支明细*/
			List<Map<String,String>> tempList = thriftService.birdCoinDetail(Integer.parseInt(uid), request.getPage(), request.getPageSize(), request.getType());
			if(tempList!=null && tempList.size()>0)
			{
				for(int i=0;i<tempList.size();i++)
				{
					try {
						Map<String,String> map = tempList.get(i);
						BirdCoinRecordResponse br = new  BirdCoinRecordResponse();
						br.setBirdCoin(map.get("birdCoin"));
						br.setSdate(map.get("date"));
						br.setType(Integer.parseInt(map.get("rtype")+""));
						br.setTitle(getTitle(Integer.parseInt(map.get("rtype")+""),map.get("birdBeans")));
						resultList.add(br);
					} catch (Exception e) {
						log.info("转换收支明细处理异常");
						e.printStackTrace();
						return new BaseResponse(ResponseCode.FAILURE,"操作异常");
					}
				}
			}
			
			MapResponse objResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> resultMap = new HashMap<>();
			resultMap.put("list", resultList);
			objResponse.setResponse(resultMap);
			return objResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"操作成功");
	}
	

	/**
	 * 
	* @Title: sendLiveRedpacket
	* @Description: 发送直播间红包
	* @return Object    返回类型
	* @throws
	 */
	public Object sendLiveRedpacket(SendLiveRedpacketRequest sendLiveRedpacketRequest) {
		// 获取uid验证用户
		String uid = sessionTokenService.getStringForValue(sendLiveRedpacketRequest.getSessiontoken()) + "";
		//String uid ="604863";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		// 采用redis incr函数初始化值 保证发送红包只有一个执行
		String send_live_redpacket_key = "send_live_redpacket_" + uid+"_"+sendLiveRedpacketRequest.getLiveRecordId();
		Long resultNum = stringRedisTemplate.opsForValue().increment(send_live_redpacket_key, 1);
		log.info("主播发送直播间红包,任务执行次数" + resultNum);
		if (resultNum>1) {
			return new BaseResponse(ResponseCode.FAILURE, "红包正在发送中。。。");
		}
		//设置缓存时间
		stringRedisTemplate.expire(send_live_redpacket_key, 10, TimeUnit.SECONDS);
			
		Integer liveRedpacketId = null; //红包记录id
		Integer liveRecordId = sendLiveRedpacketRequest.getLiveRecordId(); //直播记录id
		Integer amountType = sendLiveRedpacketRequest.getAmountType(); //红包类型 0随机 1固定
		Integer totalAmount = sendLiveRedpacketRequest.getTotalAmount(); //红包总鸟蛋数
		Integer number = sendLiveRedpacketRequest.getNumber(); //红包个数
		Integer privateRedUid = sendLiveRedpacketRequest.getPrivateRedUid(); //指定红包领取者的uid
		String nname="";//指定红包用户昵称
		// 判断红包参数是否符合（1鸟蛋 = 100鸟豆）		
		if (!checkRedPacketArgs(sendLiveRedpacketRequest)) {
			return new BaseResponse(ResponseCode.FAILURE, "红包参数有误");
		}
		
		// 查询红包发送者的用户信息,判断是否存在红包发送者的用户信息,是否是主播发送
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));	
		if (liverMap == null || liverMap.isEmpty() || Integer.parseInt(liverMap.get("utype").toString()) != 1) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此主播用户信息");
		}
	
		// 结果集
		Map<Object, Object> resultMap = new HashMap<>();
		
		// 查询本场直播通告是否存在有未领取的红包
		Map<Object, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("liveRecordId", liveRecordId);
		Map<Object, Object> liveRedpacketMap = liveRoomDao.queryLiveRedpacketByUid(map);
		if (liveRedpacketMap != null && liveRedpacketMap.size() > 0) {
			// 响应
			resultMap.put("remainNumber", Integer.parseInt(liveRedpacketMap.get("redpacket_number").toString()) - Integer.parseInt(liveRedpacketMap.get("get_redpacket_number").toString()));
			MapResponse response = new MapResponse(ResponseCode.EXIST_LIVE_REDPACKET, "您的红包还未被领完,不能再次发");
			response.setResponse(resultMap);
			return response;
		}
		
		// 拆分红包到redis队列中
		List<String> sendPacketList = this.spitList(totalAmount/100,number,amountType);	
		if (sendPacketList==null || sendPacketList.size()==0) {
			log.info("发红包：红包拆分异常。。。");
			return new BaseResponse(ResponseCode.FAILURE, "红包发送异常");
		}
		
		try {		
			// 调用支付接口查询直播用户钱包信息
			Map<String, String> liveWalletMap = personalCenterService.getLiveWalletInfo(uid);
			log.info("调用支付接口查询直播用户钱包信息" + uid);
			if(liveWalletMap == null || liveWalletMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播钱包信息");
			}
			
			// 统计主播鸟蛋余额总量
			int balance = (int)Math.floor((Double.parseDouble(liveWalletMap.get("balance").toString()))); //直播钱包中鸟蛋余额		
			int allBalance = balance;
			
			//获取redis缓存数据 同步主播信息 鸟蛋
			String liver_wallet_key = "liver_wallet_" + Long.valueOf(uid);
			Map<Object, Object> walletMap = stringRedisTemplate.opsForHash().entries(liver_wallet_key);
			if (walletMap != null && walletMap.size() > 0) {
				//直播过程中获取的鸟蛋数
				allBalance = balance + Integer.parseInt(walletMap.get("liverWalletEgg").toString());
				
			}
			
			//判断总鸟蛋是否满足发红包
			if (allBalance < totalAmount) {
				return new BaseResponse(ResponseCode.BIRD_EGG_NOT_ENOUGH, "鸟蛋余额不足");
			}
			
			//优选扣取鸟蛋余额，更新直播钱包记录
			if (balance - totalAmount >= 0) {
				Map<String, String> walletParamMap = new HashMap<>();
				walletParamMap.put("uid", uid);
				//11发红包 12收红包 13红包退还
				walletParamMap.put("rtype", "11");
				walletParamMap.put("balance", totalAmount.toString());
				walletParamMap.put("liveRecordId", liveRecordId.toString());
				walletParamMap.put("anchorId", liverMap.get("id").toString());
				ResponseData responseData = personalCenterService.updateWalletAmount(walletParamMap);
				if (responseData.getState() != 0) {
					log.info(uid + "发送红包,调用支付业务系统,更新直播钱包失败,扣除鸟蛋失败");
					//扣除鸟蛋失败
					return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
				}
			}else {
				
				String sendLiveRedpacketProcessKey = Constant.SEND_LIVE_REDPACKET_PROCESS + "_" + uid;
				try {
					//设置一个redis key 防止送礼更新redis操作
					stringRedisTemplate.opsForValue().set(sendLiveRedpacketProcessKey, sendLiveRedpacketProcessKey);
					stringRedisTemplate.expire(sendLiveRedpacketProcessKey, 60, TimeUnit.SECONDS);
					
					//当鸟蛋余额不够扣取的时候,同步redis中的鸟蛋数到数据库中
					LiveRecordInfo liveRecord = new LiveRecordInfo();
					liveRecord.setId(liveRecordId.longValue());
					
					//重新获取主播redis获取的鸟蛋数
					walletMap = stringRedisTemplate.opsForHash().entries(liver_wallet_key);
					anchorViewerMemberRankService.exitRoomAnchorBirdEggTotal(liverMap, liveRecord, walletMap, "", 1);
				} catch (Exception e) {
					log.info("同步redis鸟蛋数到数据库中失败,错误信息如下:" + e.getMessage());
					
				}finally{
					//执行删除redis key操作
					stringRedisTemplate.delete(sendLiveRedpacketProcessKey);
				}
				
				if (sendLiveRedpacketRequest.getType() == 0) {
					return new BaseResponse(ResponseCode.BIRD_EGG_NOT_ENOUGH, "你账户中的鸟蛋和本场次收入鸟蛋余额不足");
				}
				
				return new BaseResponse(ResponseCode.BIRD_EGG_PAY_AGAIN, "你账户中的鸟蛋余额不足，即将使用本场收到的鸟蛋数量");
				
			}
			
			//扣取主播鸟蛋完成后,添加发红包记录，发送IM消息
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("amountType", amountType); //'红包类型 0：随机 1：固定'
			paramMap.put("uid", uid); //红包发送者uid
			paramMap.put("liveRecordId", liveRecordId); //直播记录id
			paramMap.put("totalAmount", totalAmount); //红包总鸟蛋数
			paramMap.put("redpacketNumber", number); //红包个数
			paramMap.put("versionLock", 1); //乐观锁版本控制
			paramMap.put("remainAmount", totalAmount); 	//乐观锁版本控制
			paramMap.put("createTime", DateUtil.format(new Date())); //创建红包时间
			if (privateRedUid!=null) {
				Map<Object, Object> receiveMap = liveUserDao.queryLiverInfoByUid(privateRedUid);		
				if (receiveMap == null || receiveMap.size() <=0 ) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
				}
				nname =receiveMap.get("nname")+"";
				paramMap.put("receiveUid", privateRedUid); 	//指定红包接收人		
			}else {
				paramMap.put("receiveUid", null); 	//指定红包接收人
			}
			if (amountType == 1) {				
				//固定红包鸟蛋数
				Integer singleAmount = totalAmount/number;
				paramMap.put("singleAmount", singleAmount);
				
			}	
			
			//插入红包记录
			liveRoomDao.insertLiveRedpacket(paramMap);
			liveRedpacketId =Integer.parseInt(paramMap.get("id").toString()); //insert返回获取红包记录id
			resultMap.put("liveRedpacketId", liveRedpacketId); //response结果集添加:红包id
			if (sendLiveRedpacketRequest.getPrivateRedUid()!=null) {
				/*新加字段 红包指定用户*/
				resultMap.put("receiverUid", sendLiveRedpacketRequest.getPrivateRedUid());
			}
			// 把拆分的红包放入缓存中
			String redis_redPacket_list = "send_live_redpacket_list"+sendLiveRedpacketRequest.getLiveRecordId();
			 ListOperations<String, String> list =stringRedisTemplate.opsForList();	
			 list.leftPushAll(redis_redPacket_list, sendPacketList.toArray(new String[sendPacketList.size()]));
			 
			 
			 Long redPacketListSize = list.size(redis_redPacket_list);
			 if (redPacketListSize==0) {
					return new BaseResponse(ResponseCode.FAILURE, "红包未保存到redis中");
			 }
				
			try {							
				//发送完红包,发送IM消息,通知用户端
				tlsSendImService.sendGroupMsgAfterSendLiveRedpacket(privateRedUid,nname,liveRecordId,liveRedpacketId,liverMap.get("group_id").toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.info("发送红包IM消息通知失败,错误信息如下:" + e.getMessage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("发送直播间红包失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "发送直播间红包失败");
		}finally{
			//执行删除redis key操作
			stringRedisTemplate.delete(send_live_redpacket_key);
		}	
		//发送直播间成功
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "发送直播间红包成功");
		response.setResponse(resultMap);
		return response;
		
	}
	
	/**检查红包参数合法性
	 * @Title checkRedPacketArgs
	 * @param sendLiveRedpacketRequest 发送红包请求
	 * @return true or false
	 */
	private  boolean checkRedPacketArgs(SendLiveRedpacketRequest sendLiveRedpacketRequest) {
		Integer amountType = sendLiveRedpacketRequest.getAmountType(); //红包类型 0随机 1固定
		Integer totalAmount = sendLiveRedpacketRequest.getTotalAmount(); //红包总鸟蛋数
		Integer number = sendLiveRedpacketRequest.getNumber(); //红包个数
		Integer privateUid = sendLiveRedpacketRequest.getPrivateRedUid();
		//1鸟豆 = 100 鸟蛋 红包金额换算成是鸟豆数量
		if (number < 1  || (amountType!=0 && amountType!=1) || totalAmount%100 != 0 || (totalAmount/number) < 100) {
			return false; 
		}	
		if (amountType == 1) {
			if(totalAmount%(number*100) != 0){
				return false;
			}
		}
		if (privateUid!=null && number!=1) {//指定用户红包个数固定为1
			return false;
		}
		return true;
	}
	
	/**递归设置红包金额
	 * @param remainMoney
	 * @param remainSize
	 * @return
	 */
	private  Integer setRamdomAmount(Integer remainMoney,Integer remainSize) {
		Integer minMoney =1;
		Random random = new Random();		
		Integer money = random.nextInt(remainMoney);
		//数值范围在最小值与余额平均数3倍之间
		Integer avegeMoney = remainMoney/remainSize;
		if (minMoney<= money && money <= avegeMoney*3 && remainMoney-money >= minMoney*(remainSize-1)) {
			if (money < remainMoney) {
				return money;
			} else {
				setRamdomAmount(remainMoney,remainSize);
			}

		}
		return setRamdomAmount(remainMoney,remainSize);
	}	
	
	/**分拆红包
	 * @param totalAmount
	 * @param number
	 * @param type
	 * @return
	 */
	private  List<String> spitList(Integer totalAmount, Integer number,Integer type) {
		if (totalAmount > 0 && number > 0) {
			List<String> list = new ArrayList<String>();
			Integer retainMoney = totalAmount;
			for (int i = 1; i <= number; i++) {
				if (type==0) {
					if (i != number) {
						Integer money = setRamdomAmount(retainMoney, number-i);
						retainMoney = retainMoney - money;
						
						list.add(String.valueOf(money*100));
					} else {
						Integer money = retainMoney * 100;
						list.add(money.toString());
					}
				}else {
					//固定单个红包金额
					Integer singleAmount = totalAmount/number;
					singleAmount*=100;
					list.add(singleAmount.toString());
				}
				
			}
			return list;
			
			
		}
		return null;
	}

	/**
	 * 
	* @Title: queryLiveRedpacketBirdEggInfo
	* @Description: 直播间发送红包前,获取主播鸟蛋信息
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveRedpacketBirdEggInfo(BaseRequest baseRequest) {
		//获取uid
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//查询红包发送者的用户信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			
			//判断是否存在红包发送者的用户信息,是否是主播发送
			if (liverMap == null || liverMap.size() <=0 || Integer.parseInt(liverMap.get("utype").toString()) != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播用户信息");
			}
			
			//调用支付接口查询直播用户钱包信息
			Map<String, String> liveWalletMap = personalCenterService.getLiveWalletInfo(uid);
			log.info("调用支付接口查询直播用户钱包信息" + uid);
			if(liveWalletMap == null || liveWalletMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播钱包信息");
			}
			
			//鸟蛋余额
			int balance = (int)Math.floor((Double.parseDouble(liveWalletMap.get("balance").toString())));
			
			int allBalance = balance;
			
			//获取redis缓存数据 同步主播信息 鸟蛋
			String liver_wallet_key = "liver_wallet_" + Long.valueOf(uid);
			Map<Object, Object> walletMap = stringRedisTemplate.opsForHash().entries(liver_wallet_key);
			if (walletMap != null && walletMap.size() > 0) {
				//直播过程中获取的鸟蛋数
				allBalance = balance + Integer.parseInt(walletMap.get("liverWalletEgg").toString());
				
			}
			
			//返回主播所拥有的鸟蛋总数
			resultMap.put("allBirdEgg", allBalance);
			
			//固定红包鸟蛋数
			String singleAmount = propertiesUtil.getValue("live_redpacket_fixed", "conf_live.properties");
			resultMap.put("singleAmount", singleAmount);
			
			//最多红包个数
			Integer maxLiveRedpacketNumber = Integer.parseInt(propertiesUtil.getValue("max_live_redpacket_number", "conf_live.properties"));
			
			Integer maxNumber = (int) Math.floor(allBalance/Integer.parseInt(singleAmount));
			
			if (maxNumber > maxLiveRedpacketNumber) {	
				//若超过最大红包数,则取最大红包数
				resultMap.put("maxLiveRedpacketNumber", maxLiveRedpacketNumber);
			}else {
				resultMap.put("maxLiveRedpacketNumber", maxNumber);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "直播间发送红包前,获取主播鸟蛋信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("直播间发送红包前,获取主播鸟蛋信息,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "直播间发送红包前,获取主播鸟蛋信息失败");
		}
		
	}

	
	/**
	 * 
	* @Title: getLiveRedpacket
	* @Description: 领取直播间红包
	* @return Object    返回类型
	* @throws
	 */
	public Object getLiveRedpacket(GetLiveRedpacketRequest getLiveRedpacketRequest) {
		
		// 获取uid,验证红包领取者的用户信息
		String uid = sessionTokenService.getStringForValue(getLiveRedpacketRequest.getSessiontoken()).toString();
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		
		// 采用redis的incr函数初始化值 保证领取红包进程只允许单一
		Integer liveRedpacketId = getLiveRedpacketRequest.getLiveRedpacketId(); //红包记录id 
		String get_live_redpacket_key = "get_live_redpacket_"+ liveRedpacketId;	
		Long resultNum = stringRedisTemplate.opsForValue().increment(get_live_redpacket_key, 1);
		log.info("抢直播间红包,任务执行次数" + resultNum);
		if (resultNum>1) {
			return new BaseResponse(ResponseCode.FAILURE, "红包没挤进去。。。");
		}
		stringRedisTemplate.expire(get_live_redpacket_key, 10, TimeUnit.SECONDS);
		
		//用户是否存在
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));		
		if (liverMap == null || liverMap.size() <=0 ) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
		}
						
		// 查询红包是否存在,剩余鸟蛋数量大于0
		Map<Object, Object> liveRedpacketMap = liveRoomDao.queryLiveRedpacketById(liveRedpacketId);	
		if (liveRedpacketMap == null || liveRedpacketMap.size() <= 0) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此红包信息");
		}
		String receiveUid =liveRedpacketMap.get("receiveUid")+"";
		if (StringUtils.isNotBlank((receiveUid) ) &&!"null".equals(receiveUid)) {
			if (!uid.equals(receiveUid)) {
				return new BaseResponse(ResponseCode.FAILURE, "你无法领取用户xxx的红包");
			}
		}
		Integer remainAmount = Integer.parseInt(liveRedpacketMap.get("remain_amount").toString());
		if (remainAmount <= 0) {
			log.info(uid +"直播间领取红包：红包剩余鸟蛋数量不足......");
			return new BaseResponse(ResponseCode.FAILURE, "红包数量异常");
		}
				
		// 返回结果集
		Map<Object, Object> resultMap = new HashMap<>();
		try {		
			// 判断用户是否领取过该红包，查询缓存中 该key值中 set集合中用户是否存在
			Boolean isHasGivt = false;
			String get_live_redpacket_user = "get_live_redpacket_" + getLiveRedpacketRequest.getLiveRecordId() +"_"+ getLiveRedpacketRequest.getLiveRedpacketId();
			if (!stringRedisTemplate.hasKey(get_live_redpacket_user)) {
				// 缓存中不存在领取记录key查询数据库信息
				Map<Object, Object> redPacketRecordParams = new HashMap<>();
				redPacketRecordParams.put("uid", uid);
				redPacketRecordParams.put("liveRedpacketId", liveRedpacketId);
				Map<Object, Object> liveRedpacketRecordMap = liveRoomDao.queryLiveRedpacketRecord(redPacketRecordParams);
				if (liveRedpacketRecordMap != null && liveRedpacketRecordMap.size() > 0) {
					isHasGivt = true;
				}
			} else {
				isHasGivt = stringRedisTemplate.opsForSet().isMember(get_live_redpacket_user, uid);
			}
			if (isHasGivt) {
				return new BaseResponse(ResponseCode.GET_LIVE_REDPACKET_AGAIN, "您已经领取过此红包了");
			}				
			
			// 判断拆分红包是否已经被领取完
			String redis_redPacket_list = "send_live_redpacket_list"+getLiveRedpacketRequest.getLiveRecordId();
			ListOperations<String, String> list =stringRedisTemplate.opsForList();	
			Long redPacketListSize = list.size(redis_redPacket_list);		
			if (!stringRedisTemplate.hasKey(redis_redPacket_list)) {
				log.info(uid +"直播间领取红包：缓存中拆分子红包不存在。。。");
				return new BaseResponse(ResponseCode.FAILURE, "红包不存在");
			}
			if (redPacketListSize==0) {
				return new BaseResponse(ResponseCode.NO_GET_LIVE_REDPACKET, "红包已抢完");
			}
			
			// 取出缓存中拆分子红包第一个位置的金额
			String value = list.leftPop(redis_redPacket_list);  
			if (StringUtils.isBlank(value)) {
				log.info(uid +"直播间领取红包：缓存中拆分子红包信息异常。。。");
				return new BaseResponse(ResponseCode.FAILURE, "红包金额为空");
			}
			
			// 插入数据到红包记录表格中 
			Map<Object, Object> map = new HashMap<>();
			map.put("getRedpacketAmount", value); // 获得的红包鸟蛋数
			map.put("liveRedpacketId", liveRedpacketId); // 红包id
			map.put("uid", uid); // 领取用户uid
			map.put("liveRecordId", getLiveRedpacketRequest.getLiveRecordId()); // 直播记录id		
			map.put("createTime", DateUtil.format(new Date())); // 创建时间			
			map.put("updateTime", DateUtil.format(new Date())); // 更新时间												
			liveRoomDao.insertLiveRedpacketRecord(map);
			resultMap.put("getRedpacketAmount", Integer.parseInt(map.get("getRedpacketAmount").toString()) / 100); // 鸟蛋换成鸟豆的数				
			
			// 缓存中remove领取的子红包队列，加入本红包该用户领取记录,如果该红包分配完成移除掉 用户领取此红包记录
			if (list.size(redis_redPacket_list)==0) {
				stringRedisTemplate.delete(redis_redPacket_list);
			} else {				
				stringRedisTemplate.opsForSet().add(get_live_redpacket_user,uid);
			}		
			// 更新红包表：计算剩余金额  和  剩余领取数量
			Map<Object, Object> redPacketParams = new HashMap<>();
			Integer amountType = Integer.parseInt(liveRedpacketMap.get("amount_type").toString());
			redPacketParams.put("liveRedpacketId", liveRedpacketId); //红包id	
			if (amountType==1){
				redPacketParams.put("singleAmount", liveRedpacketMap.get("single_amount").toString()); //固定红包：单个红包鸟蛋数
			}else if (amountType==0) {
				redPacketParams.put("randomAmount", value); //随机红包:鸟蛋数
			}
			redPacketParams.put("versionLock", liveRedpacketMap.get("version_lock")); //乐观锁		
			redPacketParams.put("updateTime", DateUtil.format(new Date())); //更新时间
			Integer result = liveRoomDao.updateLiveRedpacketById(redPacketParams); 
			if (result != 1) {
				log.info(uid + "抢直播间红包成功后,更新红包表失败");
				// 红包表更新失败
				return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
			}
							
			// 更新直播钱包的信息
			Map<String, String> walletParamMap = new HashMap<>();
			walletParamMap.put("uid", uid);
			walletParamMap.put("rtype", "12"); // 11发红包 12收红包 13红包退还
			walletParamMap.put("commision",Integer.parseInt(map.get("getRedpacketAmount").toString()) / 100 + ""); // 鸟豆
			walletParamMap.put("liveRecordId", getLiveRedpacketRequest.getLiveRecordId() + ""); // 直播记录id
			walletParamMap.put("anchorId", liverMap.get("id").toString()); // 主播id
			ResponseData responseData = personalCenterService.updateWalletAmount(walletParamMap); 
			if (responseData.getState() != 0) {
				log.info(uid + "抢直播间红包,调用支付业务系统,更新直播钱包失败,获得鸟豆失败");
				// 获取鸟豆失败
				return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("领取直播间红包,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "领取直播间红包失败");
		} finally {
			// 执行删除redis key操作
			stringRedisTemplate.delete(get_live_redpacket_key);
		}
		
		// 响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "领取直播间红包成功");
		response.setResponse(resultMap);
		return response;
	} 	
	//////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	* @Title: receiveLiveRedpacket
	* @Description: 抢红包
	* @return boolean    返回类型
	* @throws
	 */
	public boolean receiveLiveRedpacket(Integer liveRedpacketId,Integer count) {
		//递归3次，如果没有抢到则退出去
		if (count > 3) {
			return false;
		}
		count++;
		
		//查询红包信息
		Map<Object, Object> liveRedpacketMap = liveRoomDao.queryLiveRedpacketById(liveRedpacketId);
		//剩余的红包鸟蛋数
		String remainAmount = liveRedpacketMap.get("remain_amount").toString();
		if (Integer.parseInt(remainAmount) > 0) {
			//红包类型:固定
			Map<Object, Object> map = new HashMap<>();
			//红包id
			map.put("liveRedpacketId", liveRedpacketId);
			//单个红包鸟蛋数
			map.put("singleAmount", liveRedpacketMap.get("single_amount").toString());
			//乐观锁
			map.put("versionLock", liveRedpacketMap.get("version_lock"));
			//更新时间
			map.put("updateTime", DateUtil.format(new Date()));
			//更新红包信息
			Integer result = liveRoomDao.updateLiveRedpacketById(map);
			if (result == 1) {
				return true;
			}
			return this.receiveLiveRedpacket(liveRedpacketId,count);
			
		}else {
			//没有抢到红包
			return false;
		}
		
	}
	
	public String getTitle(int type , String birdBeans){
		String title="";
		try {
			switch (type) {
			case 0:title="充值鸟豆奖励";break;
			case 1:title="鸟蛋转出";break;
			case 2:title="赠送礼物奖励";break;
			case 3:title="发送私信奖励";break;
			case 4:title="发送弹幕奖励";break;
			case 5:title="主播礼物收入奖励";break;
			case 6:title="主播私信收入奖励";break;
			case 7:title="主播弹幕收入奖励";break;
			case 8:title="购买粉丝劵";break;
			case 9:title="主播卖出粉丝劵分账收入";break;
			case 10:title="用户使用粉丝劵主播分账收入";break;
			case 11:title="发红包奖励";break;
			case 12:title="收红包奖励";break;
			case 13:title="红包余额退回";break;
			case 14:title="推荐壕友奖励";break;
			case 15:title="到店消费买单";break;
			case 19:title="天降壕礼";break;
			
			case 22:title="购买商城商品";break;
			case 23:title="赠送主播商品礼物";break;
			case 24:title="赠送主播套餐礼物";break;
			case 25:title="购买商家套餐";break;
			case 26:title="购买鸟粉转专享卡";break;
			
			default:
				title="其它奖励";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return title;
	}

		
	/**
	 * 获取鸟币收入统计
	 * @author xiaoxiong
	 * @date 2016年12月23日
	 */
	public Object birdCurrencyStatis(BaseRequest request) {
		try {
			/*验证用户信息*/
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR,"身份令牌错误或已过期，请重新登入！");
			}
			ObjResponse objResponse = new ObjResponse(ResponseCode.SUCCESS,"成功");
			
			/*调用支付接口回去鸟币收入统计*/
			Map<String,String> result = thriftService.countBirdCoin(Integer.parseInt(uid));
			if(result!=null && !result.isEmpty()){
				BirdCurrencyStatisResponse response = new BirdCurrencyStatisResponse();
				response.setRedPacketCoin(result.get("birdCoin3"));
				response.setRecomAwardCoin(result.get("birdCoin2"));
				response.setRewardRewardCoin(result.get("birdCoin1"));
				response.setSumBirdCoin(result.get("total"));
				objResponse.setResponse(response);
			}
			return objResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "获取鸟币收入统计失败");
	}

	/**
	 * 
	* @Title: queryLiveRedpacketRemainNum
	* @Description: 获取直播间红包的剩余个数
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveRedpacketRemainNum(GetLiveRedpacketRequest getLiveRedpacketRequest) {
		//获取uid
		String uid = sessionTokenService.getStringForValue(getLiveRedpacketRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//查询红包信息
			Map<Object, Object> liveRedpacketMap = liveRoomDao.queryLiveRedpacketById(getLiveRedpacketRequest.getLiveRedpacketId());
			
			if (liveRedpacketMap == null || liveRedpacketMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此红包信息");
			}
			
			//剩余红包个数 = 红包个数 - 已领取的红包个数
			resultMap.put("liveRedpacketRemainNum", Integer.parseInt(liveRedpacketMap.get("redpacket_number").toString())-Integer.parseInt(liveRedpacketMap.get("get_redpacket_number").toString()));
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取红包剩余个数成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.info("获取红包剩余个数失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取红包剩余个数失败");
		}
	}

	/**
	 * 
	* @Title: queryLiveRoomBanner
	* @Description: 查询直播间banner广告:查询美食首页或者直播首页的重点推荐banner
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveRoomBanner(BaseRequest baseRequest) {
		try {
			//结果集
			List<Map<Object, Object>> resultList = new ArrayList<>();

			BannerRequest request = new BannerRequest();
			request.setPosition(11);
			request.setAppversion(baseRequest.getAppversion());
			request.setSystemversion(baseRequest.getSystemversion());
			request.setAppSource(baseRequest.getAppSource());
			request.setApiversion(baseRequest.getApiversion());
			request.setSessiontoken(baseRequest.getSessiontoken());
			Object tmp = commonService.getBanner(request);
			if (tmp instanceof MapResponse) {
				MapResponse mapResponse = (MapResponse) tmp;
				List<Map<Object,Object>> result = (List<Map<Object, Object>>) mapResponse.getResponse().get("banners");
				if (result != null && result.size() > 0) {
					commonService.checkIOSManorVersion(baseRequest, 9, result);
					try {
						if (result.size() > 0) {
							int index = new Random().nextInt(result.size());
							Map<Object,Object> tmpBanner = result.get(index);
							List<Map<Object,Object>> picList = (List<Map<Object, Object>>) tmpBanner.get("bannerlist");
							tmpBanner.remove("bannerlist");
							tmpBanner.put("bannerList", picList);
							resultList.add(tmpBanner);
						}
					} catch (Exception e) {
						log.warn("解析错误", e);
					}

//					for (int i = 0; i < result.size(); i++) {
//						Map<Object,Object> tmpBanner = result.get(i);
//						List<Map<Object,Object>> picList = (List<Map<Object, Object>>) tmpBanner.get("bannerlist");
//						tmpBanner.remove("bannerlist");
//						tmpBanner.put("bannerList", picList);
//						resultList.add(tmpBanner);
//					}
				}
			}
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询直播间banner广告成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("bannerList", resultList);
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询直播间banner广告失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.SUCCESS, "查询直播间banner广告成功");
		}
	}

	/**
	 * 
	* @Title: queryLiveClassifyTag
	* @Description: 获取自定义开播标签列表
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveClassifyTag(BaseRequest baseRequest) {
		try {
			//'分类类别 1.商家 2.主播 3.直播'
			int classifyType = 3;
			//查询直播标签的分类标签
			List<Map<Object, Object>> liveClassifyList = liveRoomDao.queryLiveClassify(classifyType);
			
			if (liveClassifyList != null && liveClassifyList.size() > 0) {
				for (Map<Object, Object> liveClassifyMap : liveClassifyList) {
					//查询直播标签分类下的所有标签
					List<Map<Object, Object>> liveClassifyTagList = liveRoomDao.queryLiveClassifyTagByClassifyId(Integer.parseInt(liveClassifyMap.get("classifyId").toString()));
					liveClassifyMap.put("liveClassifyTagList", liveClassifyTagList);
				}
			}
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("liveClassifyList", liveClassifyList);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询自定义开播标签列表成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.info("查询自定义开播标签列表失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "查询自定义开播标签列表失败");
		}
	}
	
}
