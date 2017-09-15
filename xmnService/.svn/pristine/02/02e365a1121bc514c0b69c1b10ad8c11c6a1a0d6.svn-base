/**
 * 2016年8月18日 下午1:48:53
 */
package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.common.request.live.SendSecretMarkRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.dao.PrivateMessageDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PrivateMessageService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月18日 下午1:48:53
 * @version
 */
@Service
public class PrivateMessageService {

	//日志
	private final Logger log = Logger.getLogger(PrivateMessageService.class);
			
	//注入dao
	@Autowired
	private PrivateMessageDao privatemessageDao;
	
	@Autowired
	private PersonalCenterDao personalcenterDao;
	
	@Autowired
	private PersonalCenterService personalcenterService;
	
	@Autowired
	private SessionTokenService sessiontokenService;
	
	@Autowired
	private AnchorViewerMemberRankService anchorviewermemberrankService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 
	* @Title: sendSecretMark
	* @Description: 发送私信
	* @return Object    返回类型
	* @throws
	 */
	public Object sendSecretMark(SendSecretMarkRequest sendsecretmarkRequest){
		//记录id
		Long record_id = sendsecretmarkRequest.getRecord_id();
		//私信内容
		String messager_txt = sendsecretmarkRequest.getMessager_txt();
		
		Map<Object,Object> messageMap=null; 
		try {
			//获取uid
			String uuid=(String) sessiontokenService.getStringForValue(sendsecretmarkRequest.getSessiontoken());
			if(uuid==null || "".equals(uuid) || "null".equals(uuid)){
				return new BaseResponse(ResponseCode.TOKENERR, "用户过期,请重新登录");
			}
			Integer uid=Integer.parseInt(uuid);
			
			//查询发送者基本信息
			Map<Object,Object> personMap=personalcenterDao.queryLiverPersonByUid(uid);
			if(personMap==null || personMap.size()<=0){
				log.info("发送私信验证失败，未获取到个人信息");
				return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败，未获取到个人信息");
			}
			
			//发送者的手机号码
			String send_liver_id = personMap.get("id").toString();
			String send_liver_uname=personMap.get("phone").toString();
			//发送者 
			Integer rank_no=Integer.parseInt(personMap.get("rank_no").toString());
			
			//发送私信扣鸟币等级限制
			String sendSecretMark_no=propertiesUtil.getValue("sendSecretMark_no", "conf_live.properties");
			
			if (rank_no < Integer.parseInt(sendSecretMark_no)) {
				//查询接收者基本信息
				Map<Object, Object> toLiverMap = liveUserDao.queryAnchorByPhone(sendsecretmarkRequest.getAnchorPhone());
				
				if(toLiverMap==null || toLiverMap.size()<=0){
					log.info("发送私信验证失败，未获取到接收人的信息");
					return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败，未获取到接收人的信息");
				}
				
				String anchorid = toLiverMap.get("id").toString();
				
				//接受者的手机号码
				String to_liver_uname=toLiverMap.get("phone").toString();//私信接收人手机号
				
				//判断是否被对方拉黑
				BaseResponse toresponse=this.ToBlackSendInfo(anchorid+"", send_liver_id);
				if(toresponse.getState()!=100){
					return toresponse;
				}
				//判断是否拉黑了对方
				BaseResponse sendresponse=this.SendBlackToInfo(send_liver_id, anchorid+"",send_liver_uname,to_liver_uname);
				if(sendresponse.getState()!=100){
					return sendresponse;
				}
				
				Map<Object,Object> param=new HashMap<Object,Object>();
				param.put("send_liver_uname", send_liver_uname);//消息发送者用户ID
				param.put("to_liver_uname", to_liver_uname);//消息接收者用户ID
				messageMap=privatemessageDao.queryPrivateMessage(param);
				
				//判断是否第一次发送私信，若是第一次扣除鸟币
				if(messageMap==null || messageMap.size()<=0){
					//调用支付系统扣除200鸟币
					Map<String,String> walletParam=new HashMap<String,String>();
					String commision=propertiesUtil.getValue("privateMessgeBirdCoin", "conf_live.properties");
					walletParam.put("uid", uid+"");//寻蜜客id
					walletParam.put("rtype", "3");//类型：0 充值 1 转出   2 赠送礼物 3 发送私信 4 发送弹幕 ,5 主播礼物收入，6 主播私信收入 7 主播弹幕收入
					walletParam.put("commision", commision);//鸟豆
					
					//添加直播记录id
					if (record_id != null && record_id != 0) {
						walletParam.put("liveRecordId", record_id+"");
					}
					
					walletParam.put("anchorId", anchorid+"");
					ResponseData responsedata=personalcenterService.updateWalletAmount(walletParam);
					if(responsedata.getState()!=0){
						if ("鸟豆余额不足".equals(responsedata.getMsg())) {
							return new BaseResponse(ResponseCode.BIRD_BEAN_NOT_ENOUGH, "鸟豆余额不足,请先充值");
						}
						log.info("私信用户鸟豆支付失败,错误信息：" + responsedata.getMsg());
						return new BaseResponse(ResponseCode.FAILURE, "鸟豆支付失败,错误信息："+responsedata.getMsg());
					}
					
					log.info("客户发送私信给主播扣费成功");
					String liver_key = "liver_"+Long.valueOf(uid);
					//第一次私信扣除鸟币之后加2000经验
					Map<Object, Object>  liverMap = stringRedisTemplate.opsForHash().entries(liver_key);
					Integer current_expe=Integer.parseInt(propertiesUtil.getValue("privateMessaerank_no", "conf_live.properties"));
					
					//查询群组号
					Map<Object, Object> map = liveUserDao.queryLiverInfoById(Integer.parseInt(anchorid + ""));
					liverMap.put("GroupId", map.get("group_id").toString());
					if (record_id != null && record_id != 0) {
						liverMap.put("live_record_id", record_id);
					}
					liverMap.put("get_experience_type", "3");
					
					//获取主播钱包redis 用于鸟蛋累加(如果存在,则累加在redis中，如果不在，直接加入数据库主播鸟蛋收入)
//					String liver_wallet_key = "liver_wallet_" + toLiverMap.get("uid").toString();
//					Map<Object, Object>  anchorBirdEggMap = stringRedisTemplate.opsForHash().entries(liver_wallet_key);
					
					//存在，累加主播redis鸟蛋数
//					if (anchorBirdEggMap != null && anchorBirdEggMap.size() > 0) {
//						LiverInfo liverInfo=new LiverInfo();
//						liverInfo.setUid(Long.valueOf(toLiverMap.get("uid").toString()));//主播uid
//						//添加主播鸟蛋
//						anchorviewermemberrankService.accumulativeBirdEgg(0, liverInfo, new BigDecimal(commision), 1, 0);
//						
//					}else {
						//不存在，直接加入数据库主播鸟蛋收入
//						Map<String, String> walletMap = new HashMap<>();
//						walletMap.put("uid", toLiverMap.get("uid").toString());
//						walletMap.put("rtype", "6");//类型：0 充值 1 转出   2 赠送礼物 3 发送私信 4 发送弹幕 ,5 主播礼物收入，6 主播私信收入 7 主播弹幕收入
//						walletMap.put("anchorId", anchorid+"");//主播id
//						walletMap.put("balance", commision);//鸟蛋
//						ResponseData data = personalcenterService.updateWalletAmount(walletMap);
//						if(data.getState()!=0){
//							log.info("主播私信收入,支付失败,错误信息：" + responsedata.getMsg());
//							return new BaseResponse(ResponseCode.FAILURE, "主播私信收入,支付失败,错误信息："+responsedata.getMsg());
//						}
//					}
					
					//添加观众经验
					if (liverMap != null && liverMap.size() > 0 && liverMap.get("current_expe") != null) {
						anchorviewermemberrankService.addLiverViewerExpe(liverMap, current_expe, liver_key,0);
					}else {
						Map<Object, Object> paramMap = new HashMap<>();
						paramMap.put("uid", personMap.get("uid"));
						paramMap.put("currentExpe", current_expe);
						paramMap.put("updateTime", DateUtil.format(new Date()));
						liveUserDao.updateLiverCurrentExpe(paramMap);
					}
				}
				
			}
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("text", messager_txt);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "发送私信验证成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			log.error("发送私信验证失败,未知错误,请联系管理员");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败,未知错误,请联系管理员");
		}
	}
	
public Object queryPrivateMessageBysendId(String sessiontoken,Long anchorid,Long record_id,String messager_txt){
		
		Map<Object,Object> messageMap=null; 
		try {
			String uuid=(String) sessiontokenService.getStringForValue(sessiontoken);
			if(uuid==null || "".equals(uuid) || "null".equals(uuid)){
				return new BaseResponse(ResponseCode.TOKENERR, "用户过期,请重新登录");
			}
			Integer uid=Integer.parseInt(uuid);
			//查询发送者基本信息
			Map<Object,Object> personMap=personalcenterDao.queryLiverPersonByUid(uid);
			
			if(personMap==null || personMap.size()<=0){
				log.info("发送私信验证失败，未获取到个人信息");
				return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败，未获取到个人信息");
			}
			String send_liver_utype=personMap.get("utype").toString();//私信发送人类型
			String send_liver_uname=personMap.get("phone").toString();//私信发送者手机号
//			Integer rank_no=Integer.parseInt(personMap.get("rank_no").toString());
//			Integer current_expe=Integer.parseInt(personMap.get("current_expe").toString());
			String send_liver_id=personMap.get("anchorid").toString();//发送者ID
			
			//查询接收者基本信息
			Map<Object,Object> toLiverMap=personalcenterDao.queryLiverPersonById(Integer.parseInt(anchorid+""));
			
			if(toLiverMap==null || toLiverMap.size()<=0){
				log.info("发送私信验证失败，未获取到接收人的信息");
				return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败，未获取到接收人的信息");
			}
			String to_liver_utype=toLiverMap.get("utype").toString();//私信接收人类型
			String to_liver_uname=toLiverMap.get("phone").toString();//私信接收人手机号
			
			//判断是否被对方拉黑
			BaseResponse toresponse=this.ToBlackSendInfo(anchorid+"", send_liver_id);
			if(toresponse.getState()!=100){
				return toresponse;
			}
			
			//判断是否拉黑了对方
			BaseResponse sendresponse=this.SendBlackToInfo(send_liver_id, anchorid+"",send_liver_uname,to_liver_uname);
			if(sendresponse.getState()!=100){
				return sendresponse;
			}
			
			Map<Object,Object> param=new HashMap<Object,Object>();
			param.put("send_liver_id", send_liver_id);//消息发送者用户ID
			param.put("to_liver_id", anchorid);//消息接收者用户ID
			param.put("send_liver_utype", send_liver_utype);//发送用户类型： 1 主播 2 普通用户
			messageMap=privatemessageDao.queryPrivateMessageBysendId(param);
			
			//判断是否第一次发送私信，若是第一次扣除鸟币
			if(messageMap==null || messageMap.size()<=0){
				if("2".equals(send_liver_utype)||("1".equals(send_liver_utype)&&"1".equals(to_liver_utype))){
					//扣除200鸟币
					Map<String,String> walletParam=new HashMap<String,String>();
					String commision=propertiesUtil.getValue("privateMessgeBirdCoin", "conf_live.properties");
					walletParam.put("uid", uid+"");//寻蜜客id
					walletParam.put("rtype", "3");//类型：0 充值 1 转出   2 赠送礼物 3 发送私信 4 发送弹幕 ,5 主播礼物收入，6 主播私信收入 7 主播弹幕收入
					walletParam.put("commision", commision);//鸟币
					
					//添加直播记录id
					if (record_id != null && record_id != 0) {
						walletParam.put("liveRecordId", record_id+"");
					}
					
					walletParam.put("anchorId", anchorid+"");
					ResponseData responsedata=personalcenterService.updateWalletAmount(walletParam);
					if(responsedata.getState()!=0){
						if ("鸟币余额不足".equals(responsedata.getMsg())) {
							return new BaseResponse(ResponseCode.BIRD_EGG_NOT_ENOUGH, "鸟币不足,请先充值");
						}
						return new BaseResponse(ResponseCode.FAILURE, "鸟币支付失败,错误信息："+responsedata.getMsg());
					}
					log.info("客户发送私信给主播扣费成功");
					String liver_key = "liver_"+Long.valueOf(uid);
					//第一次私信扣除鸟币之后加2000经验
					Map<Object, Object>  liverMap= stringRedisTemplate.opsForHash().entries(liver_key);
					Integer current_expe=Integer.parseInt(propertiesUtil.getValue("privateMessaerank_no", "conf_live.properties"));
					
					//查询群组号
					Map<Object, Object> map = liveUserDao.queryLiverInfoById(Integer.parseInt(anchorid + ""));
					liverMap.put("GroupId", map.get("group_id").toString());
					if (record_id != null && record_id != 0) {
						liverMap.put("live_record_id", record_id);
					}
					liverMap.put("get_experience_type", "3");
					LiverInfo liverInfo=new LiverInfo();
					liverInfo.setUid(Long.valueOf(toLiverMap.get("uid").toString()));//主播uid
					anchorviewermemberrankService.accumulativeBirdEgg(0, liverInfo, new BigDecimal(commision), 1, 0);
					anchorviewermemberrankService.addLiverViewerExpe(liverMap, current_expe, liver_key,0);
			
				}
			}
			
			Map<Object,Object> paramMap=new HashMap<Object,Object>();
			paramMap.put("send_liver_id", send_liver_id);//消息发送者用户ID
			paramMap.put("send_liver_uname", send_liver_uname);//发送者登录名称
			paramMap.put("send_liver_utype", send_liver_utype);//发送用户类型： 1 主播 2 普通用户
			paramMap.put("to_liver_id", anchorid);//消息接收者用户ID
			paramMap.put("to_liver_uname", to_liver_uname);//接收者登录名称
			paramMap.put("to_liver_utype", to_liver_utype);//发送用户类型： 1 主播 2 普通用户
			paramMap.put("live_record_id", record_id);//直播记录id
			paramMap.put("messager_txt", messager_txt);//消息内容
			paramMap.put("mess_status", 0);//消息发送状态  0 正在发送  1 发送成功
			paramMap.put("mess_send_time", DateUtil.format(new Date()));//消息发送时间
			paramMap.put("create_time", DateUtil.format(new Date()));//创建时间
			paramMap.put("update_time", DateUtil.format(new Date()));//更新时间
			
			Integer mssagenum=privatemessageDao.addPrivateMessage(paramMap);
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("text", messager_txt);
			if(mssagenum>0){
				//响应
				log.info("客户发送私信验证成功");
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "发送私信验证成功");
				response.setResponse(resultMap);
				return response;
			}
			log.info("客户发送私信验证失败");
			return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败");
		} catch (Exception e) {
			log.error("发送私信验证失败,未知错误,请联系管理员");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败,未知错误,请联系管理员");
		}
	}
	
	/**
	 * 
	* @Title: isBlackInfo
	* @Description: 判断是是否被对方拉黑
	* @return Object
	 */
	public BaseResponse ToBlackSendInfo(String send_liver_id,String to_liver_id){
		//查询接收者是否为黑名单用户
		Map<Object,Object> paramblack=new HashMap<>();
		paramblack.put("liver_str_id", send_liver_id);
		paramblack.put("liver_end_id", to_liver_id);
		Map<Object,Object> blackMap=personalcenterDao.queryBlackInfoBasic(paramblack);
		if(blackMap!=null && blackMap.size()>0){
			log.info("您已被对方拉黑，不能发私信");
			return new BaseResponse(ResponseCode.FAILURE, "您已被对方拉黑，不能发私信");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "未被拉黑");
	}
	
	
	/**
	 * 
	* @Title: isBlackInfo
	* @Description: 判断是是否拉黑了对方
	* @return Object
	 */
	public BaseResponse SendBlackToInfo(String send_liver_id,String to_liver_id,String send_liver_name,String to_liver_name){
		//查询接收者是否为黑名单用户
		Map<Object,Object> paramblack=new HashMap<>();
		paramblack.put("liver_str_id", send_liver_id);
		paramblack.put("liver_end_id", to_liver_id);
		Map<Object,Object> blackMap=personalcenterDao.queryBlackInfoBasic(paramblack);
		if(blackMap!=null && blackMap.size()>0){
			//如果主播发送私信给客户，解除黑名单
//			if("1".equals(send_liver_utype)){
				//若是黑名单人员，则删除黑名单关系
			Map<Object,Object> paramMap=personalcenterService.getparamMap(send_liver_name, to_liver_name);
			if(paramMap==null || paramMap.size()<=0){
				return new BaseResponse(ResponseCode.FAILURE, "黑名单操作失败,未获取到调用腾讯云参数");
			}
			//解除黑名单
			if(TLSUtil.BlackListOperation(paramMap, 2)){
				Integer deletenums=personalcenterDao.deleteBlackInfo(paramblack);
				if(deletenums<=0){
					log.info("客户发送私信并解除黑名单失败");
					return new BaseResponse(ResponseCode.FAILURE, "发送私信验证失败");
				}
			}
			/*}else{
				log.info("您已拉黑该主播，不能发私信");
				return new BaseResponse(ResponseCode.FAILURE, "您已拉黑该主播，不能发私信");
			}*/
		}
		return new BaseResponse(ResponseCode.SUCCESS, "未有拉黑");
	}

	/**
	 * 
	* @Title: getBlackMark
	* @Description: 验证是否给拉黑
	* @return Object    返回类型
	* @throws
	 */
	public Object getBlackMark(PhoneRequest phoneRequest) {
		try {
			//验证token
			String uid=sessiontokenService.getStringForValue(phoneRequest.getSessiontoken()) + "";
			if(StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)){
				return new BaseResponse(ResponseCode.TOKENERR, "用户过期,请重新登录");
			}
			
			//获取查询者的用户信息
			Map<Object, Object> endLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (endLiverMap == null || endLiverMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无个人用户信息");
			}
			
			//获取对方的用户信息
			Map<Object, Object> strLiverMap = liveUserDao.queryLiveByPhone(phoneRequest.getPhone());
			if (strLiverMap == null || strLiverMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无个人用户信息");
			}
			
			//查询是否存在拉黑记录
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liver_str_id", Integer.parseInt(strLiverMap.get("id").toString()));
			paramMap.put("liver_end_id", Integer.parseInt(endLiverMap.get("id").toString()));
			Integer result = personalcenterDao.queryLiveBlack(paramMap);
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			if (result > 0) {
				//是否给拉黑 0否 1是
				resultMap.put("isBlack", 1);
			}else {
				resultMap.put("isBlack", 0);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "验证是否给拉黑成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "验证是否给拉黑失败");
		}
	}
}
