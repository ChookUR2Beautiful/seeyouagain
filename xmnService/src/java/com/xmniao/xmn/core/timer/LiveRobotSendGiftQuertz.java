package com.xmniao.xmn.core.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveRobotDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * 描述 ：定时器检查有无直播正在直播的房间，启动机器人发送弹幕和礼物
 *        发出的弹幕和礼物不做扣钱处理，不做累计鸟蛋处理
 * */
@Service
public class LiveRobotSendGiftQuertz {
	
	private final Logger log = Logger.getLogger(LiveRobotSendGiftQuertz.class);
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired 
	private LiveRobotDao liveRobotDao;
	
	/**
	 * 注入tlsSendImService
	 */
	@Autowired
	private TlsSendImService tlsSendImService;
	
	/**
	 * 直播礼物Dao
	 * */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	/**
	 * 直播用户Dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 配置定义
	 * */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 图片服务器根路径地址
	 * */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/** MQ消息推送服务 */
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	/** MQ队列连接消息  */
	@Autowired
	private TopicInfo robotActionInfo;
	
	/**
	 * 描述：检查当前正在直播的记录  发礼物 弹幕
	 * @param object
	 * @return 
	 * */
	public void sendGiftAndInformation(){
		
		log.info("检查正在直播的房间,拉取机器人随机发送礼物，弹幕  start...");
		//保证只有一个定时任务执行
		String live_quzt_redis = "live_sendGift_key";
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
		log.info("检查正在直播的房间,拉取机器人随机发送礼物，累计定时任务数："+resultNum);
		
		try {
			
			//判断是否开启机器人发礼物  和 聊天信息
			int isliveRobotSendGifts = Integer.parseInt(propertiesUtil.getValue("isliveRobotSendGifts", "conf_live.properties").toString());
			if (isliveRobotSendGifts == 0) {
				stringRedisTemplate.delete(live_quzt_redis);
				return;
			}
			
			if (resultNum>1) {
				log.info("已有定时任务执行操作,其他定时任务强制退出："+live_quzt_redis+":"+resultNum);
				//超过一定次数,删除定时任务redisKey,恢复定时任务
				if (resultNum > 30) {
					//执行删除redis key操作
					stringRedisTemplate.delete(live_quzt_redis);
				}
				return ;
			}
			
			//查看正在直播的直播记录信息(包含内部测试通告)
			List<LiveRecordInfo> liveReordInfoList = anchorLiveRecordDao.queryCurrentLiveRecord(); 
			if(liveReordInfoList.size() <= 0){
				log.info("暂无正在直播的房间");
				return;
			}
			for (LiveRecordInfo liveRecordInfo : liveReordInfoList) {
				String key = Constant.ROBOT_ACTION_KEY + liveRecordInfo.getId();
				
				//没有key,机器人出动
				if(!stringRedisTemplate.hasKey(key)){
					
					//根据直播记录ID去查询机器人
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("live_record_id", liveRecordInfo.getId());
					map.put("anchor_id", liveRecordInfo.getAnchor_id());
					try {
						map.put("limit", Integer.parseInt(propertiesUtil.getValue("liveRobotNum", "conf_live.properties").toString()));
					} catch (Exception e1) {
						log.info("获取机器人数量失败");
						e1.printStackTrace();
					}
					//获取直播间的机器人
					List<Map<Object, Object>> robotListMap = liveRobotDao.queryLiveRebotViewerList(map);
					//获取主播群组id
					String groupId = liveUserDao.queryGroupIdByAnchorId(liveRecordInfo.getAnchor_id()+"");
					
					if(robotListMap.size() > 0){
						for (Map<Object, Object> robotMap : robotListMap) {
							
							//组装MQ消息发送参数
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("dj", robotMap.get("rank_no"));
							jsonObject.put("iconUrl", fileUrl + robotMap.get("avatar"));
							jsonObject.put("uid", robotMap.get("id").toString());
							jsonObject.put("uname", robotMap.get("nname").toString());
							jsonObject.put("groupId", groupId);
							jsonObject.put("liveRecordId", liveRecordInfo.getId());
							
							SendResult sendResult = producerServiceImpl.send(robotActionInfo, jsonObject.toString());
							log.info("推送ID"+sendResult.getMsgId()+"推送结果状态："+sendResult.getSendStatus());
						}
					}
					
				}
			}		
		} catch (Exception e) {
			log.info("");
			e.printStackTrace();
		}finally{
			stringRedisTemplate.delete(live_quzt_redis);
			log.info("检查正在直播的房间,拉取机器人随机发送礼物，弹幕  end...");
		}
	}

}
