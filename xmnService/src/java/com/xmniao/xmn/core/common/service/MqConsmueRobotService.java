/**
 * 
 */
package com.xmniao.xmn.core.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.service.AnchorViewerMemberRankService;
import com.xmniao.xmn.core.live.service.LiveRobotService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 机器人操作 Mq队列消息消费记录操作service
 * 
 * @ClassName:MqConsmueRobotService
 * @Description:Mq队列消息消费记录操作service
 * @Author:xw
 * @Date:2017年6月22日下午6:04:38
 */
@Service
public class MqConsmueRobotService {

	/**
	 * 引入日志
	 */
	private Logger log = LoggerFactory.getLogger(MqConsmueRobotService.class);


	@Autowired
	private LiveRobotService liveRobotService;
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	@Autowired
	private LiveUserDao liveUserDao;
	/**
	 * 机器人开始行动
	 */
	public MapResponse robotAction(String message) {
		log.info("机器人行动 robotAction开始执行：参数为：{}", message);
		JSONObject jsonObject = JSONObject.parseObject(message);

		String uid = jsonObject.getString("uid");
		try {
			String groupId = jsonObject.getString("groupId");
			String dj = jsonObject.getString("dj");
			String iconUrl = jsonObject.getString("iconUrl");
			String uname = jsonObject.getString("uname");
			String liveRecordId = jsonObject.getString("liveRecordId");
			
			boolean flag = false;
			int count = 1;
			do {
				if (count > 5) {
					break;
				}
				// 机器人接下来动作
				int ranNum = (int) (Math.random() * 10);
				Thread.sleep((ranNum + 3) * 10000 );// 不要太快,慢慢来 30-120s
				if (ranNum == 0 || ranNum == 1) {//20%发礼物
					liveRobotService.robotSendGift(dj, iconUrl, uid, uname, groupId, liveRecordId);
					flag = true;
				} else if (1 < ranNum && ranNum < 6) {//40%发留言
					liveRobotService.robotSendMsg(dj, iconUrl, uid, uname, groupId, liveRecordId);
					flag = true;
				} else {
					flag = false;
				}

				// 执行过操作的机器人 有50% 的概率再次执行动作,最多5次
				if (flag) {
					int j = (int) (Math.random() * 2);
					if (j == 1) {
						flag = false;
					}
				}
				count++;

			} while (flag);
			
			return new MapResponse(ResponseCode.SUCCESS, "机器人行动成功");
			
		} catch (Exception e) {
			log.info(uid + "机器人行动失败", e);
			return new MapResponse(ResponseCode.FAILURE, "机器人行动失败");
		}
	}

	public static String generatorUUID() {
		String uuid = UUID.randomUUID().toString();
		String[] uidArray = uuid.split("-");
		return uidArray[uidArray.length - 1].concat(uidArray[uidArray.length - 2]);
	}

	/**
	 * 机器人进入直播间 发送进场提示
	 * @Title:robotEntryRoomMsg
	 * @Description:
	 * @param message
	 * @return BaseResponse
	 * 2017年6月26日下午4:32:11
	 */
	public BaseResponse robotEntryRoomMsg(String message) {
		log.info("机器人入场提示消息开始执行：参数为：{}", message);
		JSONObject jsonObject = JSONObject.parseObject(message);
		
		String uid = jsonObject.getString("uid");
		try {
		
			String groupId = jsonObject.getString("groupId");
			String dj = jsonObject.getString("dj");
			String iconUrl = jsonObject.getString("iconUrl");
			String uname = jsonObject.getString("uname");
			
			int ranNum = (int) (Math.random() * 10);
			Thread.sleep((ranNum + 3) * 10000);// 不要太快,慢慢来 30-120s
			
			liveRobotService.robotEntryRoomMsg(dj, iconUrl, groupId, uid, uname);
			return new MapResponse(ResponseCode.SUCCESS, "机器人行动成功");
		} catch (Exception e) {
			log.info(uid + "机器人行动失败", e);
			return new MapResponse(ResponseCode.FAILURE, "机器人行动失败");
		}
		
	}

	/**把机器人插入到观看表中
	 * @param message
	 * @return
	 */
	public BaseResponse insertRobotLiveView(String message) {
		log.info("机器人插入到观看表中参数为：{}",message);
		JSONObject  jsonObject = JSONObject.parseObject(message);
		String robotKey = jsonObject.getString("robotKey");
		String anchorId = jsonObject.getString("anchorId");
		String anchorRoomNo = jsonObject.getString("liveRoomNo");
		String liveRecordId = jsonObject.getString("liveRecordId");
		Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(robotKey);
		
		//累计robotKey消费次数，大于10次删除缓存取消消费
		String keyTimes = robotKey +"_"+liveRecordId;
		Integer maxConsumeTime = 10;
		Long resultNum = stringRedisTemplate.opsForValue().increment(keyTimes, 1);
		
		if (map !=null && !map.isEmpty()) {
			String jsonStrng= (String) map.get("robots"); 
			try {
			List<Map<Object,Object>> robotList = JSON.parseObject(jsonStrng,new TypeReference<List<Map<Object,Object>>>(){});
			List<Map<Object,Object>> paramlist=new ArrayList<>();
			if(robotList!=null && robotList.size()>0){
				for(int i=0;i<robotList.size();i++){
					Map<Object,Object> rebotMap = (Map<Object, Object>) robotList.get(i);
					Map<Object,Object> paramMap=new HashMap<>();
					paramMap.put("robot_id", rebotMap.get("viewer_id"));//机器人ID
					paramMap.put("robot_avatar", rebotMap.get("avatar"));//头像
					paramMap.put("rank_no", rebotMap.get("rank_no"));//用户当前等级数
					paramMap.put("anchor_id", anchorId);//主播用户id
					paramMap.put("anchor_room_no", anchorRoomNo);//主播房间编号
					paramMap.put("live_record_id", liveRecordId);//直播记录id
					paramMap.put("ban_speak_status", "0");//禁言状态  0 未禁言 1 禁言中
					paramMap.put("create_time", DateUtil.format(new Date()));//创建时间
					paramMap.put("update_time", null);//更新时间
					paramlist.add(paramMap);
				}
			}				
				// 插入机器人观看表
				Integer recordNums = liveRobotService.addRebotRecord(paramlist);
				log.info(liveRecordId+"直播插入机器人观看表数量为----"+recordNums);
				if(recordNums>0){
					stringRedisTemplate.delete(robotKey);//删除缓存
					stringRedisTemplate.delete(keyTimes);//删除计数器
				}
			} catch (Exception e) {
				log.info("异步插入失败" +e);
				e.printStackTrace();
				if (resultNum > maxConsumeTime) {
					//执行删除redis key操作
					stringRedisTemplate.delete(robotKey);//删除缓存
					stringRedisTemplate.delete(keyTimes);//删除计数器
				}
				return new MapResponse(ResponseCode.FAILURE, "机器人写入数据库失败");
			}		
		}else {
			stringRedisTemplate.delete(keyTimes);//删除计数器
			stringRedisTemplate.delete(robotKey);//删除缓存
			log.info("机器人写到数据库失败---redis数据为空");
		}
		return new MapResponse(ResponseCode.SUCCESS, "机器人写入数据库成功");
	}

	/**退出直播间：累计用户经验修改观看人数
	 * @param message
	 * @return
	 */
	public BaseResponse addUpUserExperience(String message) {
		log.info("累计用户经验修改观看人数：{}"+message);
		JSONObject jsonObject = JSONObject.parseObject(message);
		String liver_rediskey = jsonObject.getString("liver_rediskey");
		Map<Object, Object> liverMap = jsonObject.getObject("liverMap", Map.class);
		LiverRoomRequest liverRoomRequest = jsonObject.getObject("liverRoomRequest", LiverRoomRequest.class);
		String liveRecordId = jsonObject.getString("liveRecordId");
		// 累计消费次数，大于10次取消消费直接通过
		String keyTimes = liver_rediskey + "_"+liveRecordId;
		Integer maxConsumeTime = 10;
		Long resultNum = stringRedisTemplate.opsForValue().increment(keyTimes, 1);
		if (new Long(resultNum).intValue() > maxConsumeTime) {
			stringRedisTemplate.delete(liver_rediskey);//删除用户观看redis
			stringRedisTemplate.delete(keyTimes);//删除计数器
			return new MapResponse(ResponseCode.SUCCESS, "用户退出房间异步成功");
		}
		try {
			// 计算观众观看时长所得经验
			List<Map<Object, Object>> editveiwerList = new ArrayList<Map<Object, Object>>();
			Map<Object, Object> resultMap = anchorViewerMemberRankService.addViewerOrAnchorExperience(liverMap,liverRoomRequest, liver_rediskey, 0);
			resultMap.put("update_time", DateUtil.format(new Date()));
			editveiwerList.add(resultMap);
			liveUserDao.editLiveViewerInfo(editveiwerList);// 批量修改用户基本信息此逻辑中仅修改一个同步redis到数据库

			// 修改直播记录观看人数
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", liverRoomRequest.getZhiboRecordId());
			paramMap.put("removenumber", 1);
			anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);

			// 修改观众观看记录为“已退出”
			paramMap.put("endTime", DateUtil.format(new Date()));
			paramMap.put("updateTime", DateUtil.format(new Date()));
			paramMap.put("live_status", 0);// “已退出”
			paramMap.put("liver_id", liverMap.get("id").toString());
			paramMap.put("view_duration",resultMap.get("viewTime") == null ? "0" : resultMap.get("viewTime").toString());
			anchorLiveRecordDao.editLiveViewRecordStatus(paramMap);
			
			//用户退出房间就修改  redis 为 false
			String todayRedis = anchorViewerMemberRankService.getTodayLiverViewerRedisKey(liverRoomRequest.getUid());
			if (stringRedisTemplate.hasKey(todayRedis)) {
				anchorViewerMemberRankService.setTodayLiverViewerRedisKey(todayRedis,liverRoomRequest.getUid(),false,0);
			}
			stringRedisTemplate.delete(liver_rediskey);//删除用户观看redis
			stringRedisTemplate.delete(keyTimes);//删除计数器
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "用户退出房间异步消费失败");
		}
		return new MapResponse(ResponseCode.SUCCESS, "用户退出房间异步消费成功");
	}

}
