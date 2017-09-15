/**
 * 2016年8月19日 上午10:49:02
 */
package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SendBarrageRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.dao.SendBarrageDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SensitiveWordUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SendBarrageService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月19日 上午10:49:02
 * @version
 */
@Service
public class SendBarrageService {
	//日志
	private Logger log=Logger.getLogger(SendBarrageService.class);
	
	//注入service
	@Autowired
	private SendBarrageDao sendbarrageDao;
	
	@Autowired
	private PersonalCenterService personalcenterService;
	
	@Autowired
	private SessionTokenService sessiontokenService;
	
	@Autowired
	private PersonalCenterDao personalcenterDao;
	@Autowired
	private AnchorViewerMemberRankService anchorviewermemberrankService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private LiveUserDao liveUserDao;

	/**
	 * 注入sensitiveWordUtils
	 */
	@Autowired
	private SensitiveWordUtil sensitiveWordUtil;

	/**
	 * 
	* @Title: addSendBarrage
	* @Description: 发送弹幕
	* @return Object
	 */
	public Object addSendBarrage(SendBarrageRequest sbRequest)
	{
		
		try {
			LiveRecordInfo liveRecordInfo=null;
			String uuid=(String) sessiontokenService.getStringForValue(sbRequest.getSessiontoken());
			if(uuid==null || "".equals(uuid) || "null".equals(uuid)){
				return new BaseResponse(ResponseCode.TOKENERR, "用户过期,请重新登录");
			}
			
			//获取管理员签名
			/*String tlsSig = TLSUtil.getTLSSig(sbRequest.getSdkAppid(), sbRequest.getIdentifier());
			Map<Object,Object> paramMap=new HashMap<Object,Object>();
			paramMap.put("tlsSig", tlsSig);
			paramMap.put("sdkAppid", sbRequest.getSdkAppid());
			paramMap.put("identifier", sbRequest.getIdentifier());
			paramMap.put("account", sbRequest.getAccount());
			paramMap.put("groupId", sbRequest.getMessager_group_no());
			paramMap.put("MsgType", "TIMTextElem");
			paramMap.put("Text", sbRequest.getMessager_txt());
			paramMap.put("Index", "");
			paramMap.put("Data", "");
			//腾讯云发送弹幕成功
			if(TLSUtil.sendBarrage(paramMap)){*/
			
				Integer uid=Integer.parseInt(uuid);
				//查询发送者基本信息
				Map<Object,Object> personMap=personalcenterDao.queryLiverPersonByUid(uid);
				if(personMap==null || personMap.size()<=0){
					log.info("发送弹幕：获取个人信息失败,UID="+uid);
					return new BaseResponse(ResponseCode.FAILURE, "获取个人信息失败");
				}
				
				String send_liver_uname=personMap.get("phone").toString();
				String send_liver_id=personMap.get("anchorid").toString();//发送者ID
//				Integer rank_no=Integer.parseInt(personMap.get("rank_no").toString());//当前等级数
				Map<Object,Object> param=new HashMap<Object,Object>();
				param.put("send_liver_id", send_liver_id);//发送者登录名称
				param.put("send_liver_uname", send_liver_uname);//发送者登录名称
				param.put("live_record_id", sbRequest.getLiveRecordId());//直播记录id
				param.put("messager_group_no", sbRequest.getMessagerGroupNo());//消息群组号
				param.put("messager_txt", removeNonBmpUnicode(sbRequest.getMessagerTxt()));//消息内容
				param.put("mess_send_time", DateUtil.format(new Date()));//消息发送时间
				param.put("message_type", "2");//消息类型  1 普通消息 2 弹幕消息
				param.put("create_time", DateUtil.format(new Date()));//创建时间
				param.put("update_time", DateUtil.format(new Date()));//更新时间
				
				Integer sendNums=sendbarrageDao.addSendBarrage(param);
				if(sendNums==1)
				{
					
					Map<Object,Object> paramMap=new HashMap<>();
					paramMap.put("id", sbRequest.getLiveRecordId());
					liveRecordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
					if(liveRecordInfo==null){
						log.info("发送弹幕：未获取到直播记录信息，发送弹幕失败,UID="+uid);
						return new BaseResponse(ResponseCode.FAILURE, "未获取到直播记录信息，发送弹幕失败");
					}
//					if(sbRequest.getMesssage_type()==2){
					String commision=propertiesUtil.getValue("sendbarrgeBirdCoin", "conf_live.properties");
					//扣除2鸟币
					Map<String,String> walletParam=new HashMap<String,String>();
//					walletParam.put("id", null);//钱包id
					walletParam.put("uid", uid+"");//寻蜜客id
					walletParam.put("rtype", "4");//类型：0 充值 1 转出   2 赠送礼物 3 发送私信 4 发送弹幕 ,5 主播礼物收入，6 主播私信收入 7 主播弹幕收入
//					walletParam.put("balance", null);//鸟蛋
					walletParam.put("commision", commision);//鸟币
					walletParam.put("liveRecordId", sbRequest.getLiveRecordId()+"");//直播记录id
//					walletParam.put("remarks", null);//交易订单
					walletParam.put("anchorId", liveRecordInfo.getAnchor_id()+"");//主播id
		
					ResponseData responsedata=personalcenterService.updateWalletAmount(walletParam);
					if(responsedata.getState()!=0){
						log.info("发送弹幕：鸟币支付失败,错误信息："+responsedata.getMsg());
						return new BaseResponse(ResponseCode.FAILURE, "鸟币支付失败,错误信息："+responsedata.getMsg());
					}
					
					//获取主播的基本信息
					Map<Object,Object> toLiverMap=personalcenterDao.queryLiverPersonById(Integer.valueOf(liveRecordInfo.getAnchor_id()+""));
					
					if(toLiverMap==null || toLiverMap.size()<=0){
						log.info("发送弹幕失败，未获取到主播的信息");
						return new BaseResponse(ResponseCode.FAILURE, "发送弹幕失败，未获取到主播的信息");
					}
					
					
					
					String liver_key = "liver_"+Long.valueOf(uid);
					//加经验
					Map<Object, Object>  liverMap= stringRedisTemplate.opsForHash().entries(liver_key);
					Integer current_expe=Integer.parseInt(propertiesUtil.getValue("sendBarrgerank_no", "conf_live.properties"));
					
					//群组号
					liverMap.put("GroupId", sbRequest.getMessagerGroupNo());
					liverMap.put("live_record_id", sbRequest.getLiveRecordId());
					liverMap.put("get_experience_type", "2");
					liverMap.put("anchor_room_no", sbRequest.getMessagerGroupNo());
					LiverInfo liverInfo=new LiverInfo();
					liverInfo.setUid(Long.valueOf(toLiverMap.get("uid").toString()));//获取主播uid
//					anchorviewermemberrankService.accumulativeBirdEgg(0, liverInfo, new BigDecimal(commision), 2, 0);
					anchorviewermemberrankService.addLiverViewerExpe(liverMap, current_expe, liver_key,0);
//					}
					
					log.info("发送弹幕成功");
					
					//弹幕内容过滤
					String newText = sbRequest.getMessagerTxt();
					try {
						newText = sensitiveWordUtil.sensitiveWordDeal(newText);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("处理敏感词汇出错,处理文本内容text=" + newText);
						log.error("处理敏感词汇出错,处理文本内容text=" + newText + "信息如下" + e.getMessage());
					}
					
					//用户发送弹幕,保存redisKey ,用于匹配机器人是否 行动
					String key = Constant.ROBOT_ACTION_KEY + sbRequest.getLiveRecordId();
					if (stringRedisTemplate.hasKey(key)) {
						stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
					}else {
						stringRedisTemplate.opsForValue().set(key, "true");
						stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
					}
					//响应
					Map<Object, Object> resultMap = new HashMap<>();
					resultMap.put("text", newText);
					MapResponse response = new MapResponse(ResponseCode.SUCCESS, "发送弹幕成功");
					response.setResponse(resultMap);
					return response;
			}
//			}
			return new BaseResponse(ResponseCode.FAILURE, "发送弹幕失败");
	} catch (Exception e) {
		log.error("发送弹幕失败");
		e.printStackTrace();
		return new BaseResponse(ResponseCode.FAILURE, "发送弹幕失败异常,请联系管理员");
	}
	}
	
	/**
	 * 
	* @方法名称: removeNonBmpUnicode
	* @描述: 过滤掉含有表情的弹幕
	* @返回类型 String
	* @创建时间 2016年10月21日
	* @param str
	* @return
	 */
	public  String removeNonBmpUnicode(String str) {  
		   if (str == null) {  
		       return null;  
		   }  
		   str = str.replaceAll("[^\\u0000-\\uFFFF]", "");  
		  return str;  
		}  	
}
