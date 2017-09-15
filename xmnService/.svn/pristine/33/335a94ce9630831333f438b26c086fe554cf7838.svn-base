/**
 * 2016年9月13日 上午11:30:16
 */
package com.xmniao.xmn.core.live.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.PushTodayFirstRequest;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiveSelfGiftInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PushSingleService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年9月13日 上午11:30:16
 * @version
 */
@Service
public class PushTodayFirstService {

	private Logger log=Logger.getLogger(PushTodayFirstService.class);
	
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
//	@Autowired
//	private TopicInfo topicTodayFirstInfo;
	
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	@Autowired
	private SelfGiftService selfGiftService;
	
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	/**
	 * 
	* 描述: 推送消息到IM  发送赠送礼物消息
	* @返回类型 void
	* @创建时间 2016年9月20日
	* @param liverMap
	* @param recordInfo
	 */
	public void SendMqTodayFirst(PushTodayFirstRequest request){
		try{
			log.info("=========================================================大爷的");
			//查询用户自有礼物表是否有记录
			Map<Object,Object> param=new HashMap<Object,Object>();
			param.put("liver_id", request.getLiverId());
			param.put("gift_id", request.getGiftId());
			LiveSelfGiftInfo selfGiftInfo = liveGiftsInfoDao.queryLiveSelfGifts(param);
			if (selfGiftInfo!=null && null!=selfGiftInfo.getId()) {
				param.put("giftNums", selfGiftInfo.getGiftNums()+3);
				liveGiftsInfoDao.modifyLiveSelfGiftNum(param);
				System.out.println("有===========================");
			}else {
				param.put("giftNums", 3);
				selfGiftService.insertSelfGift(param);
				System.out.println("无======================");
			}
			//发送IM消息  组装数据格式  观众
			Map<Object, Object> liverMap = new HashMap<Object, Object>();
			liverMap.put("phone", request.getPhone());
			liverMap.put("avatar", request.getAvatar());
			//主播
			LiverInfo anchorInfo = new LiverInfo();
			anchorInfo.setGroup_id(request.getGroupId());
			//礼物
			LiveGiftsInfo giftsInfo = new LiveGiftsInfo();
			giftsInfo.setId(request.getGiftId());
			//发送消息
			anchorViewerMemberRankService.sendFreeGiftsMsg(liverMap, giftsInfo, anchorInfo);
			
		}catch(Exception e){
			log.error("当天首次观看免得赠送观众礼物失败："+request.getLiverId());
			e.printStackTrace();
		}
	}

	/**
	 * 
	* 描述: 推送直播消息到mq
	* @返回类型 void
	* @param liverMap
	* @param anchorMap
	 */
//	public void SendMqTodayFirstLive(Map<String, String> liverMap, Map<Object, Object> anchorMap){
//		try{
//			Map<Object,Object> param=new HashMap<Object,Object>();
//			param.put("giftId", 1);
//			param.put("liverName", liverMap.get("nname"));
//			param.put("groupId", anchorMap.get("group_id"));
//			param.put("avatar", liverMap.get("avatar"));
//			param.put("phone", liverMap.get("phone"));
//			param.put("id", liverMap.get("id"));
//			SendResult result=producerServiceImpl.send(topicTodayFirstInfo, JSONObject.valueToString(param));
//			log.info("推送ID"+result.getMsgId()+"推送结果状态："+result.getSendStatus());
//		}catch(Exception e){
//			log.error("直播消息推送失败");
//			e.printStackTrace();
//		}
//	}
	
	
	
	
}
