/**
 * 
 */
package com.xmniao.xmn.core.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.thrift.LiveOrderService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.vstar.dao.VStarPlayerInfoDao;

/**
 * 机器人操作 Mq队列消息消费记录操作service
 * 
 * @ClassName:MqLiveRoomService
 * @Description:Mq队列消息消费记录操作service
 * @Author:xw
 * @Date:2017年6月22日下午6:04:38
 */
@Service
public class MqLiveRoomService {

	/**
	 * 引入日志
	 */
	private Logger log = LoggerFactory.getLogger(MqLiveRoomService.class);

	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private VStarPlayerInfoDao vStarPlayerInfoDao;
	
	/**
	 * 修改观看直播用户的记录为 已结束 
	 */
	public MapResponse editLiveViewerRecord(String message) {
		log.info("MQ开始修改观看直播用户的记录为 已结束 ：参数为：{}", message);
		JSONObject jsonObject = JSONObject.parseObject(message);
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("live_record_id", jsonObject.getString("live_record_id"));
			map.put("anchor_id", jsonObject.getString("anchor_id"));
			map.put("anchor_room_no", jsonObject.getString("anchor_room_no"));
			map.put("update_time", jsonObject.getString("update_time"));
			anchorLiveRecordDao.editLiveViewerRecordByLiveId(map);
			
			map.clear();
			map.put("id", jsonObject.getString("id"));
			map.put("zhibo_type", jsonObject.getString("zhibo_type"));//直播状态  结束直播
			map.put("utype", jsonObject.getString("utype"));//用户类型
			map.put("roomType", jsonObject.getString("roomType"));//标示是退出 还是 开始
			//直播时长zhibo_duration  今日直播时长live_duration_day
			map.put("endTime", jsonObject.getString("endTime"));
			map.put("income_egg_nums", jsonObject.getString("income_egg_nums"));
			log.info("修改直播记录结束前，累计鸟蛋"+ jsonObject.getString("income_egg_nums"));
			//修改直播记录为 结束
			anchorLiveRecordDao.editAnchorLiveRecordStatus(map);
			
			return new MapResponse(ResponseCode.SUCCESS, "修改观看直播用户的状态成功");
			
		} catch (Exception e) {
			log.info("修改观看直播用户的状态失败，live_record_id:{}",jsonObject.getString("live_record_id"));
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "修改观看直播用户的状态失败");
		}
	}

	/**
	 * 插入经验记录信息
	 * @Title:insertExpericeRecord
	 * @param message
	 * @return MapResponse
	 * 2017年8月2日下午2:25:54
	 */
	public MapResponse insertExpericeRecord(String message) {
		log.info("MQ开始添加经验记录信息 ：参数为：{}", message);
		JSONObject jsonObject = JSONObject.parseObject(message);
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("liver_id", jsonObject.getString("liver_id"));//用户id
			map.put("uid", jsonObject.getString("uid"));//寻蜜鸟会员id
			map.put("liver_utype", jsonObject.getString("liver_utype"));//直播用户类型： 1 主播 2 普通用户
			map.put("anchor_room_no", jsonObject.getString("anchor_room_no"));//主播房间编号
			map.put("live_record_id", jsonObject.getString("live_record_id"));//直播记录id
			map.put("get_experience", jsonObject.getString("get_experience"));//获取经验值
			map.put("get_experience_type", jsonObject.getString("get_experience_type"));//经验获取方式 1 赠送礼物，2发送弹幕  3 发送私信 4分享好友  5直播或观看经验
			map.put("create_time", jsonObject.getString("create_time"));
			map.put("update_time", jsonObject.getString("update_time"));
			liveGiftsInfoDao.insertExpericeRecord(map);
			return new MapResponse(ResponseCode.SUCCESS, "MQ插入经验记录信息成功");
			
		} catch (Exception e) {
			log.info("MQ插入经验记录信息失败，live_record_id:{}",jsonObject.getString("live_record_id"));
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "MQ插入经验记录信息失败");
		}
	}

	public MapResponse editLiveViewerInfo(String message) {
		log.info("MQ开始同步直播结束用户信息，参数为:{}", message);
		try {
			
			Map<Object, Object> map = JSON.parseObject(message, Map.class);
			List<Map<Object, Object>> list = new ArrayList<>();
			list.add(map);
			
			liveUserDao.editLiveViewerInfo(list);
			return new MapResponse(ResponseCode.SUCCESS, "MQ同步直播结束用户信息成功");
			
		} catch (Exception e) {
			log.info("MQ同步直播结束用户信息失败，参数为:{}", message);
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "MQ同步直播结束用户信息失败");
		}
	}
	
	/**
	 * 修改直播的观看人数
	 * @Title:editLiveRecordViewCount
	 * @param message
	 * @return MapResponse
	 * 2017年8月7日上午11:02:43
	 */
	public MapResponse editLiveRecordViewCount(String message) {
		log.info("MQ开始修改直播的观看人数，参数为:{}", message);
		try {
			
			Map<Object, Object> map = JSON.parseObject(message, Map.class);
			anchorLiveRecordDao.editAnchorLiveRecordStatus(map);
			//修改成功，同步缓存
			Integer addnumber = Integer.parseInt(map.get("addnumber")+"");
			String key = Constant.LIVE_RECORD_INFO_KEY + map.get("id");
			if(stringRedisTemplate.hasKey(key)){
				String json = stringRedisTemplate.opsForValue().get(key);
				LiveRecordInfo liveRecordInfo = JSON.parseObject(json, LiveRecordInfo.class);
				liveRecordInfo.setView_count(liveRecordInfo.getView_count() + addnumber);
				stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(liveRecordInfo));
			}
			return new MapResponse(ResponseCode.SUCCESS, "MQ修改直播的观看人数成功");
			
		} catch (Exception e) {
			log.info("MQ修改直播的观看人数失败，参数为:{}", message);
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "MQ修改直播的观看人数失败");
		}
	}
	
	/**
	 * 直播结束，判断是否是新时尚大赛主播，给推荐人发放奖励
	 * @Title:vstarRewardIssue
	 * @param message
	 * @return MapResponse
	 * 2017年8月7日下午5:10:27
	 */
	public MapResponse vstarRewardIssue(String message) {
		log.info("MQ新时尚大赛推荐人奖励调用开始，参数为:{}", message);
		JSONObject jsonObject = JSONObject.parseObject(message);
		
		LiveOrderService.Client client = null;
		ResponseData responseData =null;
		try {
			String uid = jsonObject.get("uid").toString();
			String liveTime = jsonObject.get("liveTime").toString();
			if(StringUtils.isEmpty(jsonObject.get("recordId"))){
				return new MapResponse(ResponseCode.FAILURE,"直播记录为空");
			}
			String recordId = jsonObject.get("recordId").toString();
			
			Map<Object, Object> paramMap = vStarPlayerInfoDao.queryPlayerAndReferrerByUid(uid);
			if(paramMap == null || StringUtils.isEmpty(paramMap.get("referrerUid"))){
				return new MapResponse(ResponseCode.SUCCESS,"该主播没有大赛推荐人，不需要给推荐人奖励");
			}
			Map<String, String> map = new HashMap<>(5);
			map.put("playerId", paramMap.get("playerId")+"");
			map.put("playerUid", paramMap.get("playerUid")+"");
			map.put("referrerUid", paramMap.get("referrerUid")+"");
			map.put("liveTime", liveTime);
			map.put("liveRecordId", recordId);
			
			log.info("大赛主播开始调用支付服务，给推荐人分账，参数为：{}",map);
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("LiveOrderService");
			client = new LiveOrderService.Client(tMultiplexedProtocol);		
			thriftBusinessUtil.openTransport();
			responseData =	client.vstarRewardIssue(map);
			if(responseData==null || responseData.getState()!=0){
				return new MapResponse(ResponseCode.FAILURE, responseData.getMsg());
			}
			log.info("推荐人分账调用成功，返回参数为：{}",responseData.getMsg());
			return new MapResponse(ResponseCode.SUCCESS, "MQ新时尚大赛推荐人奖励调用成功");
		} catch (Exception e) {
			log.error("新时尚大赛推荐人奖励调用异常",e);
			e.printStackTrace();
 			return new MapResponse(ResponseCode.FAILURE, "MQ新时尚大赛推荐人奖励调用失败");
		}finally{
			thriftBusinessUtil.coloseTransport();
		}
	}

}
