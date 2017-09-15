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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.MqConsumeRecordDao;
import com.xmniao.xmn.core.live.entity.TMqConsumeRecord;
import com.xmniao.xmn.core.live.service.AnchorViewerMemberRankService;
import com.xmniao.xmn.core.live.service.PushSingleService;
import com.xmniao.xmn.core.live.service.PushTodayFirstService;
import com.xmniao.xmn.core.live.service.SelfGiftService;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.market.dao.CouponDetailDao;
import com.xmniao.xmn.core.market.entity.pay.CouponDetail;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.xmer.dao.CouponDao;
import com.xmniao.xmn.core.xmer.entity.Coupon;

/**
 * @ClassName: MqConsmueRecordService
 * @Description: RocketMq队列消息消费记录操作service
 * @author hkun
 * @date 2016年9月5日 下午2:56:00
 *
 */
@Service
public class MqConsmueSaasService {

	/**
	 * 引入日志
	 */
	private Logger log=LoggerFactory.getLogger(MqConsmueSaasService.class);
	
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
	
	@Autowired
	private UrsDao ursDao;
	
	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private CouponDetailDao couponDetailDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
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
	 * 新用户注册赠送各种福利
	 */
	public MapResponse pushNewUserRegisterGift(String message){
		MapResponse mapResponse = null;
		try{
			log.info("pushNewUserRegisterGift执行：新用户注册赠送福利   =====  start");
			JSONObject json=JSONObject.parseObject(message);
			
			String uid = json.get("uid").toString();
			
			//查询新用户注册福利表
			List<Map<Object, Object>> giftList = ursDao.queryUserRegisterGift();
			try {
				if (giftList.size()>0) {
					for (int i = 0; i < giftList.size(); i++) {
						Map<Object, Object> giftMap = giftList.get(i);
						String type = giftMap.get("gift_type").toString();
						// 1 积分
						if (type.equals("1")) {
							sendUserWalletIntegral(uid, giftMap.get("quota").toString());
						}
						//2通用优惠券
						if (type.equals("2")) {
							sendUserCoupon(uid,giftMap.get("coupon_id").toString(),giftMap.get("num").toString());
						}
						//3鸟豆
						if (type.equals("3")) {
							sendUserLiveWalletCommision(uid,giftMap.get("quota").toString());
						}
					}
				}else {
					return new MapResponse(ResponseCode.SUCCESS, "操作成功,没有用户大礼包");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("新用户注册赠送用户大礼包异常");
				return new MapResponse(ResponseCode.SUCCESS, "操作异常");
			}
			
		}catch(Exception e){
			log.error("pushNewUserRegisterGift执行：新用户注册赠送福利  异常");
			e.printStackTrace();
			return new MapResponse(ResponseCode.SUCCESS, "操作异常");
		}
		return mapResponse;
	}
	
	/**
	 * 赠送用户会员钱包积分
	 * */
	public MapResponse sendUserWalletIntegral(String uid,String quota){
		try {
			StringBuffer uri = new StringBuffer();
			uri.append(propertiesUtil.getValue("businessServiceSendIntegral", "conf_common.properties"));
			SubsidyMsg msg = new SubsidyMsg();
			
			msg.setUId(uid);
			msg.setUserType("1");
			msg.setRType("35");
			msg.setIntegral(quota);
			msg.setRemark("新用户注册送积分");
			msg.setSign(MD5.Encode(msg.toString()));
			
			String result = HttpConnectionUtil.doPost(uri.toString(), "");
			if (StringUtils.isNotEmpty(result)) {
				if (result.indexOf("status") != -1) {
					JSONObject json = JSONObject.parseObject(result);
					int state = Integer.parseInt(json.get("status").toString());
					if (state == 0) {
						return new MapResponse(ResponseCode.SUCCESS, "操作成功");
					}else {
						return new MapResponse(ResponseCode.FAILURE, json.get("info")==null?"操作失败":json.get("info").toString());
					}
				}
			}else {
				return new MapResponse(ResponseCode.FAILURE, "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作成功");
		}
		return new MapResponse(ResponseCode.SUCCESS, "操作成功");
	}
	
	/**
	 * 赠送用户直播钱包鸟豆
	 * */
	public MapResponse sendUserLiveWalletCommision(String uid,String quota){
		try {
			StringBuffer uri = new StringBuffer();
			uri.append(propertiesUtil.getValue("businessServiceSendBeans", "conf_common.properties")).append("?uid=").append(uid).append("&birdBean=").append(quota);
			String result = HttpConnectionUtil.doPost(uri.toString(), "");
			if (StringUtils.isNotEmpty(result)) {
				if (result.indexOf("true") != -1) {
					return new MapResponse(ResponseCode.SUCCESS, "操作成功");
				}else {
					return new MapResponse(ResponseCode.FAILURE, "操作失败");
				}
			}else {
				return new MapResponse(ResponseCode.FAILURE, "操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作失败");
		}
	}
	
	/**
	 * 赠送用户平台优惠券
	 * */
	public MapResponse sendUserCoupon(String uid,String couponId,String num){
		if (!StringUtils.isEmpty(couponId)) {
			int nums = Integer.parseInt(num);
			Coupon coupon = couponDao.queryCouponByCid(Integer.parseInt(couponId));
			
			List<CouponDetail> detailList = new ArrayList<CouponDetail>();
			Map<Object, Object> map = new HashMap<Object, Object>();
			
			if (coupon!=null) {
				for (int i = 0; i < nums; i++) {
					CouponDetail detail = new CouponDetail();
					detail.setCid(coupon.getCid());
					detail.setDenomination(coupon.getDenomination());
					detail.setGetWay(4);
					detail.setGetTime(new Date());
					detail.setCtype(coupon.getCtype());
					detail.setSendStatus(1);
					detail.setSerial(generatorUUID());
					detail.setUseNum(coupon.getUseNum());
					
					if (null!=coupon.getStartDate() && null!=coupon.getEndDate()) {
						detail.setStartDate(coupon.getStartDate());
						detail.setEndDate(coupon.getEndDate());
					}else if(null!=coupon.getDayNum() && coupon.getDayNum()>0){
		                detail.setStartDate(new Date());
		                detail.setEndDate(org.apache.commons.lang.time.DateUtils.addDays(new Date(), coupon.getDayNum()));
		            }
					detailList.add(detail);
				}
				map.put("list", detailList);
				
				int  resultNum = couponDetailDao.insertBatch(map);
				if (resultNum>0) {
					return new MapResponse(ResponseCode.SUCCESS, "操作成功");
				}else {
					return new MapResponse(ResponseCode.FAILURE, "操作成功");
				}
			}
		}
		return new MapResponse(ResponseCode.SUCCESS, "操作成功");
	}
	
	public static String  generatorUUID(){
        String uuid = UUID.randomUUID().toString();
        String[] uidArray=uuid.split("-");
        return uidArray[uidArray.length-1].concat(uidArray[uidArray.length-2]);
    }
	
}
