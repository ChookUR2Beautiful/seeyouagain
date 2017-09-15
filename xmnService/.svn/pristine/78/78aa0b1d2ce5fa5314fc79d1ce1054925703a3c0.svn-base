/**
 * 
 */
package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.PushSingleRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.MqConsumeRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.entity.TMqConsumeRecord;

/**
 * @ClassName: MqConsmueRecordService
 * @Description: RocketMq队列消息消费记录操作service
 * @author hkun
 * @date 2016年9月5日 下午2:56:00
 *
 */
@Service
public class MqConsmueRecordService {

	/**
	 * 引入日志
	 */
	private Logger log=LoggerFactory.getLogger(MqConsmueRecordService.class);
	
	 /**
	  * 直播大礼包服务
	  */
	@Autowired
	private SelfGiftService selfGiftService;
	/**
	 * 消息推送服务
	 */
	@Autowired
	private PushSingleService pushSingleService;
	
	/**
	 * 消息推送服务
	 */
	@Autowired
	private PushTodayFirstService pushTodayFirstService;
	
	/**
	 * 注入消息消费mapper接口
	 */
	@Autowired
	private MqConsumeRecordDao mqConsumeRecordDao;
	
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	@Autowired
	private TlsSendImService tlsSendImService;
	
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 消息消费成功后记录入库，主要用于处理队列消息幂等性
	 */
	private BaseResponse insertConsumeRecord(TMqConsumeRecord consumeRecord){
		log.info("MqConsmueRecordService方法insertConsumeRecord开始执行：参数为：{}",consumeRecord);
		//设置返回信息
		BaseResponse response = new BaseResponse();
		try{
			mqConsumeRecordDao.insertConsumeRecord(consumeRecord);
			response.setState(ResponseCode.SUCCESS);
			response.setInfo("消息已入库！");
		}catch(Exception e){
			response.setInfo("MqConsmueRecordService方法insertConsumeRecord执行异常：" + e.toString());
			response.setState(ResponseCode.FAILURE);
			log.error("MqConsmueRecordService方法insertConsumeRecord执行异常:{}",e.toString());
			e.printStackTrace();
		}
		log.info("MqConsmueRecordService方法insertConsumeRecord结束：response.state={},response.info={}",
				response.getState(),response.getInfo());
		return response;
	}
	
	/**
	 * 更新消费失败的消息,记录失败的原因以及失败后重试次数
	 * @param message 队列消息
	 */
	public BaseResponse updateConsumeRecord(String message){
		log.info("MqConsmueRecordService方法insertConsumeRecord开始执行：参数为：{}",message);
		TMqConsumeRecord consumeRecord = null;
		//设置返回信息
		BaseResponse response = new BaseResponse();
		String messageKey = "";
		try{
			JSONObject jasonObject = JSONObject.parseObject(message);
			Integer uid = Integer.parseInt(jasonObject.get("uid").toString());//直播用户id
	        Integer giftId = Integer.parseInt(jasonObject.get("gift_id").toString());
	        Integer giftType = Integer.parseInt(jasonObject.get("gift_type").toString());
	        Integer integral = Integer.parseInt(jasonObject.get("gift_price").toString());
	        String giftBagId = jasonObject.get("gift_bag_id").toString();
	        String randomKey = jasonObject.get("randomKey").toString();
	        //生成messageKey
	        messageKey = getGiftBagMsgKey(uid, giftBagId, randomKey);
	       
	        consumeRecord = mqConsumeRecordDao.selectConsumeRecord(messageKey);
	        //第一次消费，未查到消费记录就insert，否则update
	    	if(consumeRecord==null){
	    		//新增消费记录
	    		insertConsumeRecord(getTMqConsumeRecord(messageKey, 1, "", new Date(), null));
	    		//throw new RuntimeException("异常信息");
	    		//更新用户礼物表信息
	    		selfGiftService.ModifySelfGift(uid, giftId, giftType, integral);
	    	}
	    	//消息消费失败后重新消费
	    	if(consumeRecord!=null&&consumeRecord.getStatus()==0){
	    		//更新用户礼物表信息
	    		selfGiftService.ModifySelfGift(uid, giftId, giftType, integral);
	    		mqConsumeRecordDao.updateConsumeRecord(getTMqConsumeRecord(consumeRecord.getMessageKey(), 
	    				1, "", consumeRecord.getCreateTime(), new Date()));
	    	}
			response.setState(ResponseCode.SUCCESS);
			response.setInfo("消息消费成功！");
		}catch(Exception e){
			updateExceptionRecord(messageKey,e.toString());
			response.setInfo("MqConsmueRecordService方法updateConsumeRecord执行异常：" + e.toString());
			response.setState(ResponseCode.FAILURE);
			log.error("MqConsmueRecordService方法updateConsumeRecord执行异常:{}",e.toString());
			e.printStackTrace();
		}
		log.info("MqConsmueRecordService方法insertConsumeRecord执行结束：response.state={},response.info={}",
				response.getState(),response.getInfo());
		return response;
	}
	
	/**
	 * 更新异常消费记录信息
	 * @param TMqConsumeRecord 消费失败的消息记录
	 * @param exception 异常信息
	 */
	private void updateExceptionRecord(String messageKey,String exception){
		TMqConsumeRecord consumeRecord = new TMqConsumeRecord();
		consumeRecord = mqConsumeRecordDao.selectConsumeRecord(messageKey);
		consumeRecord.setConsumeException(exception);
		consumeRecord.setUpdateTime(new Date());
		consumeRecord.setStatus(0);
		//修改消费记录
		mqConsumeRecordDao.updateConsumeRecord(consumeRecord);
	}
	
	   /**
	    * 生成消息记录实体
	    */
	private TMqConsumeRecord getTMqConsumeRecord(String messageKey,int status,String consumeException,Date createTime,Date updateTime){
		   TMqConsumeRecord consumeRecord =new TMqConsumeRecord();
		   consumeRecord.setMessageKey(messageKey);
		   consumeRecord.setStatus(status);
		   consumeRecord.setConsumeException(consumeException);
		   consumeRecord.setCreateTime(createTime);
		   consumeRecord.setUpdateTime(updateTime);
		   return consumeRecord;
	}
	
	/**
	 * 生成领取大礼包唯一标示，规则：uid + "|" + giftBagId + "|" + randomKey
	 */
	private String getGiftBagMsgKey(int uid,String giftBagId,String randomKey){
		 StringBuffer msgKeybf = new StringBuffer("");
	     msgKeybf.append(uid).append("|").append(giftBagId).append("|").append(randomKey);
		return msgKeybf.toString();
	}
	
	/**
	 * 直播消息mq推送
	 */
	public BaseResponse PushLiveMessage(String message){
		try{
			JSONObject json=JSONObject.parseObject(message);
			PushSingleRequest psRequest=new PushSingleRequest();
			psRequest.setAnchorId(json.getInteger("anchorId"));
			psRequest.setAnchorName(json.getString("anchorName"));
			psRequest.setGroupId(json.getString("groupId"));
			psRequest.setRecordId(json.getInteger("recordId"));
			psRequest.setRoomNo(json.getString("roomNo"));
			psRequest.setSellerName(json.getString("sellerName"));
			psRequest.setStartTime(json.getString("startTime"));
			psRequest.setType(1);
			BaseResponse response= (BaseResponse) pushSingleService.pushLiveMesgge(psRequest, 2);
			log.info("开始直播推送成功");
			return response;
		}catch(Exception e){
			log.error("MqConsmueRecordService方法PushLiveMessage执行：开始直播推送失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "推送失败");
		}
	}
	
	/**
	 * 当天首次观看用户免费赠送三个黄瓜
	 */
	public MapResponse pushLiveSendBirdEgg(String message){
		MapResponse mapResponse = null;
		try{
			log.info("pushLiveSendBirdEgg执行：发礼物累计主播鸟蛋MQ执行   =====  start");
			JSONObject json=JSONObject.parseObject(message);
			
			String giftRecordId = json.get("giftRecordId").toString();
			String liverInfosString = json.get("liverInfo").toString();
			JSONObject  jasonObject = JSONObject.parseObject(liverInfosString);
			LiverInfo liverInfo = JSONObject.toJavaObject(jasonObject, LiverInfo.class);
			
			//累计主播鸟蛋
			BigDecimal giftPrice = new BigDecimal(json.get("gift_price").toString());
			int type = Integer.parseInt(json.get("type").toString());
			int giftNum = Integer.parseInt(json.get("giftNum").toString());
			
			//累计用户经验参数
			Map<Object, Object> liverMap = (Map<Object, Object>)json.get("liverMap");
			int experience = Integer.parseInt(json.get("experience").toString()) ;
			int experienceType = Integer.parseInt(json.get("experienceType").toString()) ;
			BigDecimal price = new BigDecimal(json.get("price").toString());
			String liverKey = json.get("liverKey").toString() ;
			
			//增加用户经验
			try{
				anchorViewerMemberRankService.addLiverViewerExpe(liverMap,experience,liverKey,experienceType);
			}catch(Exception e){
				log.info("累计用户经验异常:"+liverKey +"  ,  "+experience);
				e.printStackTrace();
			}
			
			try{
				//增加主播鸟蛋
				mapResponse = (MapResponse) anchorViewerMemberRankService.accumulativeBirdEgg(Integer.parseInt(giftRecordId),liverInfo, giftPrice,type,giftNum);
			}catch(Exception e){
				log.info("累计用户经验异常:"+liverInfo.getUid() +" , "+giftPrice);
				e.printStackTrace();
			}
			
			//购买指定发送礼物成功后发送广播信息
			if (null != json.get("isRadio") && json.get("isRadio").toString().equals("1")) {
				//查看正在直播的直播记录信息(包含内部测试通告)
				List<LiveRecordInfo> LiveRecordList = anchorLiveRecordDao.queryCurrentLiveRecord();
				//发送广播的直播间集合
				List<String> groupIdList = new ArrayList<>();
				
				if (LiveRecordList != null && LiveRecordList.size() > 0 ) {
					//批量查询直播间号(群组号)
					groupIdList = anchorLiveRecordDao.queryGroupIds(LiveRecordList);
					//广播内容
					String text = json.get("uname") + "在" + liverInfo.getNname() + "的直播间送出了一个" + json.get("giftName");
					//广播
					tlsSendImService.sendGroupMsgLiveRadio(text, 1, groupIdList);
				}
			}
			
			//扣钱成功  累计鸟币
			String liveRebateKey = "live_rebate_"+liverMap.get("uid");
			String liveRebate = stringRedisTemplate.opsForValue().get(liveRebateKey);
			//扣钱成功  累计鸟豆
			String  liveBeanKey = "live_bean_"+liverMap.get("uid");
			String liveBean = stringRedisTemplate.opsForValue().get(liveBeanKey);
			
			if(liveBean!=null) {
				//累计用户发礼物的鸟豆总额
				stringRedisTemplate.opsForValue().set(liveBeanKey,price.add(new BigDecimal(liveBean)).toString());
			}
			if (liveRebate!=null) {
				//获取返利比例
//				String live_return_birdCoin = propertiesUtil.getValue("live_return_birdCoin", "conf_live.properties");
				// 1鸟豆等于0.1鸟币  礼物价格除以返利参数
				BigDecimal rebateMoneyBig = new BigDecimal(json.get("returnCoin").toString());
				stringRedisTemplate.opsForValue().set(liveRebateKey,rebateMoneyBig.add(new BigDecimal(liveRebate)).toString());
			}
			
		}catch(Exception e){
			log.error("pushLiveSendBirdEgg执行：发礼物累计主播鸟蛋MQ执行");
			e.printStackTrace();
			return new MapResponse(ResponseCode.SUCCESS, "累计鸟蛋失败");
			
		}
		return mapResponse;
	}
}
