package com.xmniao.xmn.core.live.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SensitiveWordUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TlsService   
* 类描述：   发送IM消息Service
* 创建人：yezhiyong   
* 创建时间：2016年10月26日 下午2:31:43   
* @version    
*
 */
@Service
public class TlsSendImService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(TlsSendImService.class);
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入anchorViewerMemberRankService
	 */
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	/**
	 * 注入liveAnchorRoomService
	 */
	@Autowired
	private LiveAnchorRoomService liveAnchorRoomService;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入sensitiveWordUtil
	 */
	@Autowired
	private SensitiveWordUtil sensitiveWordUtil;
	
	/**
	 * @throws Exception 
	 * 
	* @Title: sendGroupMsgByWeb
	* @Description: 业务后台发送群组消息
	* @return void    返回类型
	* @throws
	 */
	public void sendGroupMsgByWeb(String text,Map<Object, Object> liverMap,LiveRecordInfo liveRecord) throws Exception {
		//查询主播信息
		LiverInfo liver = liveUserDao.queryLiverInfoByAnchorId(Integer.parseInt(liveRecord.getAnchor_id().toString()));
		//群组号
		String groupId = liver.getGroup_id();
		
		//弹幕内容过滤
		String newText = text;
		try {
			newText = sensitiveWordUtil.sensitiveWordDeal(text);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("处理敏感词汇出错,处理文本内容text=" + newText + "信息如下" + e.getMessage());
		}
		
		//生成发送IM群组自定义信息请求参数
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("GroupId", groupId);
		map.put("type", 1);
		map.put("uid", liverMap.get("uid").toString());
		map.put("text", newText);
		map.put("uname", liverMap.get("nname").toString());
		
		//发送者的等级数
		int rankNo = Integer.parseInt(liverMap.get("rank_no").toString());
		map.put("dj", rankNo);
		
		//颜色
		if (rankNo >= 25) {
			map.put("color", 0xff47da);
		}else {
			map.put("color", 0xffffff);
		}
		
		Map<Object, Object> paramMap = this.createSendGroupMsgParam(map);
		
		//发送群组聊天信息
		boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
		
		//日志信息
		String logInfo = "groupId=" + groupId + ",nname=" + liverMap.get("nname").toString() + ",text=" + text;
		
		if(!sendResult){
			log.info("发送IM消息失败,发送群组消息失败," + logInfo);
			return;
			
		}
		
		log.info("发送IM消息成功,发送群组消息成功," + logInfo);
		return;
		
	}
	
	public Object sendLiveRadio(Map<Object, Object> radioMap) throws Exception {
		//指定房间 默认0 否，全部房间  1 是，指定房间
		Integer assignRoom = Integer.parseInt(radioMap.get("assign_room").toString());
		//广播内容
		String text = radioMap.get("content").toString();
		//广播次数
		Integer playCount = Integer.parseInt(radioMap.get("play_amount").toString());
		//发送广播的直播间集合
		List<String> groupIdList = new ArrayList<>();
		
		//指定广播间发送广播消息
		if (assignRoom == 1) {
			//直播记录id
			Integer liveRecordId = Integer.parseInt(radioMap.get("record_id").toString());
			Map<Object, Object> map = new HashMap<>();
			map.put("id", liveRecordId);
			
			//查询直播记录信息
			LiveRecordInfo liveRecord = anchorLiveRecordDao.queryAnchorLiveRecordById(map);
			if (liveRecord == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录信息,发送直播间系统广播信息失败");
			}
			
			//查询主播信息
			LiverInfo liver = liveUserDao.queryLiverInfoByAnchorId(Integer.parseInt(liveRecord.getAnchor_id().toString()));
			//群组号
			groupIdList.add(liver.getGroup_id().toString());
			
		}else if (assignRoom == 0){
			//查看正在直播的直播记录信息(包含内部测试通告)
			List<LiveRecordInfo> LiveRecordList = anchorLiveRecordDao.queryCurrentLiveRecord();
			if (LiveRecordList == null || LiveRecordList.size() <= 0 ) {
				return new BaseResponse(ResponseCode.SUCCESS, "发送直播间广播消息成功,但暂无正在直播的直播间");
			}
			
			//批量查询直播间号(群组号)
			groupIdList = anchorLiveRecordDao.queryGroupIds(LiveRecordList);
		}
		
		//发送广播内容
		log.info("发送广播信息start:radioId=" + radioMap.get("id").toString() + ",text=" + text + ",playCount=" + playCount + ",groupIdList=" + groupIdList.toString());
		this.sendGroupMsgLiveRadio(text,playCount,groupIdList);
		log.info("发送广播信息成功:radioId=" + radioMap.get("id").toString() + ",text=" + text + ",playCount=" + playCount + ",groupIdList=" + groupIdList.toString());
		
		//响应
		return new BaseResponse(ResponseCode.SUCCESS, "发送直播间广播信息成功");
	}
	
	/**
	 * 
	* @Title: sendGroupMsgLiveRadio
	* @Description: 发送直播间系统广播IM消息
	* @return void    返回类型
	* @throws
	 */
	public void sendGroupMsgLiveRadio(String text,Integer playCount,List<String> groupIdList) throws Exception {
		for (String groupId : groupIdList) {
			//生成发送IM群组自定义信息请求参数
			Map<Object,Object> map=new HashMap<Object,Object>();
			map.put("GroupId", groupId);
			map.put("type", 14);
			map.put("text", text);
			map.put("uname", "");
			map.put("playCount", playCount);
			Map<Object, Object> paramMap = this.createSendGroupMsgParam(map);
			
			//发送群组聊天信息
			boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
			
			//日志信息
			String logInfo = "groupId=" + groupId + ",text=" + text + ",playCount=" + playCount;
			
			if(!sendResult){
				log.info("发送IM消息失败,发送广播信息失败," + logInfo);
				continue;
				
			}
			
			log.info("发送IM消息成功,发送广播信息成功," + logInfo);
			continue;
		}
	}
	
	/**
	 * 
	* @Title: sendGroupMsg
	* @Description: 发送关闭直播间IM消息
	* @return void    返回类型
	* @throws
	 */
	public void sendGroupMsgCloseLive(LiveRecordInfo liveRecord) throws Exception {
		/**
		 * 消息类型 1、普通消息 2、弹幕消息 3、点赞消息 4、送礼物消息  5 禁言/取消禁言消息  6音效消息 7升级消息 8.主播退出房间消息 9 横竖屏消息
    			10 土豪进房间消息 11 主播切换摄像头  12 设为管理员消息 13用户进入房间的人数 14 系统广播消息 15 预售卷消息、
		 */
		//直播记录id
		Long liveRecordId = liveRecord.getId();
		//观看人数
		Integer viewCount = liveRecord.getView_count();
		//主播收入鸟蛋数
		Integer balanceEgg = liveRecord.getIncome_egg_nums();
		
		//查询主播信息
		LiverInfo liver = liveUserDao.queryLiverInfoByAnchorId(Integer.parseInt(liveRecord.getAnchor_id().toString()));
		//群组id
		String groupId = liver.getGroup_id();
		
		//计算观看时长
		Long duration = 0L;
		
		//计算本次观看时长
		if (liveRecord.getStart_date() != null) {
			duration = new Date().getTime() - (liveRecord.getStart_date()==null?liveRecord.getPlan_start_date().getTime():liveRecord.getStart_date().getTime());
		}
		
		//格式化后的本次观看时长
		String liveTime = DateUtil.secToTime(Integer.parseInt(Math.round(ArithUtil.div(Double.parseDouble(duration + ""), 1000)) + ""));
		
		//生成发送IM群组自定义信息请求参数
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("GroupId", groupId);
		map.put("type", 8);
		map.put("view_count", viewCount);
		map.put("liveTime", liveTime);
		map.put("liveRecordId", liveRecordId);
		map.put("balanceEgg", balanceEgg);
		map.put("liveEndDate", DateUtil.format(liveRecord.getStart_date()==null?liveRecord.getPlan_start_date():liveRecord.getStart_date(), "yyyy-MM-dd"));
		Map<Object, Object> paramMap = this.createSendGroupMsgParam(map);
		
		//发送群组聊天信息
		boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
		
		//日志信息
		String logInfo = "liveRecordId=" + liveRecordId + ",GroupId=" + groupId + ",view_count=" + viewCount + ",liveTime=" + liveTime + ",balanceEgg=" +balanceEgg;
		
		if(!sendResult){
			log.info("发送IM消息失败,关闭直播间失败," + logInfo);
			return;
			
		}
		
		log.info("发送IM消息成功,关闭直播间成功," + logInfo);
		return;
		
	}
	
	/**
	 * 
	* @Title: createTlsParamMap
	* @Description: 生成发送IM群组自定义信息请求参数
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object, Object> createSendGroupMsgParam(Map<Object, Object> map) throws Exception {
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
		paramMap.put("GroupId", map.get("GroupId"));
		map.remove("GroupId");
		paramMap.put("MsgType", "TIMCustomElem");
		Map<Object,Object> contentMap = new HashMap<Object,Object>();
		
		//自定义data类型参数
		Map<Object,Object> dataMap = new HashMap<Object,Object>();
		for (Entry<Object, Object> entry : map.entrySet()) {
			dataMap.put(entry.getKey(), entry.getValue());
			
		}
		contentMap.put("Data", JSONObject.toJSONString(dataMap));
		paramMap.put("MsgContent", contentMap);
		
		return paramMap;
	}
	
	/**
	 * 
	* @Title: synAnchorInfo
	* @Description: 同步主播鸟蛋,清理直播以及观众观看记录,发送IM消息关闭直播间
	* @return void    返回类型
	* @throws
	 */
	public void synAnchorInfo(LiveRecordInfo liveRecord) {
		//获取主播基本信息  拉取redis记录
		LiverInfo liverInfo = liveUserDao.queryLiverInfoByAnchorId(liveRecord.getAnchor_id().intValue());
		String liver_key = "liver_"+liverInfo.getUid();
		Map<Object, Object> liverInfoMap = stringRedisTemplate.opsForHash().entries(liver_key);
		
		//获取redis缓存数据 同步主播信息 鸟蛋
		String liver_wallet_key = "liver_wallet_"+liverInfo.getUid();
		Map<Object, Object> walletMap = stringRedisTemplate.opsForHash().entries(liver_wallet_key);
		
		//如果存在主播基本信息redis 的key，则累计经验跟鸟蛋
		if (stringRedisTemplate.hasKey(liver_key) && stringRedisTemplate.hasKey(liver_wallet_key)) {
			liveRecord.setIncome_egg_nums(Integer.parseInt(walletMap.get("liverWalletEgg").toString()));
			log.info("异常退出直播，累计鸟蛋"+liveRecord.getIncome_egg_nums());
			//判断是否已经给主播增加经验跟鸟蛋，没有则给主播统计，统计完，则发送IM消息退出直播房间
			ResponseData responseData = anchorViewerMemberRankService.exitRoomAnchorBirdEggTotal(liverInfoMap, liveRecord,walletMap,liver_key,0);
			if (responseData.getState() == 0) {
				try {
					//退出房间修改相关记录  返回相关参数
					MapResponse response = liveAnchorRoomService.exitRoomEditLiveRecord(liverInfoMap, liveRecord, walletMap);
					if (response.getState() == 100) {
						//发送IM消息 退出房间操作
						this.sendGroupMsgCloseLive(liveRecord);
						return;
					}
				} catch (Exception e) {
					log.info("异常退出直播,发送IM消息失败,关闭直播间失败");
					e.printStackTrace();
				}
			}
		}
		
		try {
			//主播收入鸟蛋数为0
			liveRecord.setIncome_egg_nums(0);
			log.info("执行此处时,主播的退出房间累计鸟蛋失败了,将鸟蛋累计置为0");
			log.info("异常退出直播,累计鸟蛋"+liveRecord.getIncome_egg_nums());
			//修改观众观看记录状态为结束,并且修改直播记录为结束直播
			this.closeLiveViewerRecordAndLiveRecord(liveRecord);
			//发送IM消息 退出房间操作
			this.sendGroupMsgCloseLive(liveRecord);
		} catch (Exception e) {
			log.info("异常退出直播,发送IM消息失败,关闭直播间失败");
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 
	* @Title: closeLiveViewerRecordAndLiveRecord
	* @Description: 修改观众观看记录跟直播记录为结束
	* @return void    返回类型
	* @throws
	 */
	public void closeLiveViewerRecordAndLiveRecord(LiveRecordInfo liveRecord) {
		//组装参数
		Map<Object, Object> liveAllViewerMap  = new HashMap<Object, Object>();
		liveAllViewerMap.put("live_record_id", liveRecord.getId());
		liveAllViewerMap.put("anchor_id", Long.valueOf(liveRecord.getAnchor_id()));
		liveAllViewerMap.put("anchor_room_no", liveRecord.getAnchor_room_no());
		liveAllViewerMap.put("update_time", DateUtil.format(new Date()));
		//批量修改该直播 正在观看人的观看直播记录的 
		anchorLiveRecordDao.editLiveViewerRecordByLiveId(liveAllViewerMap);
		
		Map<Object, Object> paramMap =  new HashMap<Object, Object>();
		//组装参数
		paramMap.put("id", liveRecord.getId());
		paramMap.put("zhibo_type", 5);//直播状态  结束直播
		paramMap.put("utype", "1");//用户类型
		paramMap.put("roomType", "1");//标示是退出 还是 开始
		//直播时长zhibo_duration  今日直播时长live_duration_day
		paramMap.put("endTime", DateUtil.format(new Date()));
		paramMap.put("income_egg_nums", liveRecord.getIncome_egg_nums());
		log.info("修改直播记录结束前，累计鸟蛋" + liveRecord.getIncome_egg_nums());
		//修改直播记录为结束
		anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
	}

	/**
	 * @throws Exception 
	 * 
	* @Title: sendGroupMsgAfterSendLiveRedpacket
	* @Description: 发送完红包,发送IM消息,通知用户端
	* @return void    返回类型
	* @throws
	 */
	public void sendGroupMsgAfterSendLiveRedpacket(Integer receiveUid,String uname,Integer liveRecordId,Integer liveRedpacketId,String groupId) throws Exception {
		//生成发送IM群组自定义信息请求参数
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("liveRecordId", liveRecordId);
		map.put("GroupId", groupId);
		map.put("type", 15);
		map.put("liveRedpacketId", liveRedpacketId);
		if (receiveUid!=null && !"".equals(receiveUid)){
			map.put("receiveUid", receiveUid);
			map.put("receiveNname", uname);
		}
		
		Map<Object, Object> paramMap = this.createSendGroupMsgParam(map);
		
		//发送群组聊天信息
		boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
		
		//日志信息
		String logInfo = "liveRecordId=" + liveRecordId + ",GroupId=" + groupId + ",redPacketId=" + liveRedpacketId;
		if (receiveUid!=null && !"".equals(receiveUid)){
			logInfo+=",receiveUid="+receiveUid+",receiveNname="+uname;
		}
		if(!sendResult){
			log.info("发送红包IM消息通知失败" + logInfo);
			return;
			
		}
		
		log.info("发送红包IM消息通知成功" + logInfo);
		return;
	}
	
}
