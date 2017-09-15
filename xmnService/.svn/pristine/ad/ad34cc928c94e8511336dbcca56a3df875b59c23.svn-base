package com.xmniao.xmn.core.live.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.UidRequest;
import com.xmniao.xmn.core.common.request.live.BanSpeakAndSpeakRequest;
import com.xmniao.xmn.core.common.request.live.FocusLiveRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：ViewerLiveService   
* 类描述：   直播观众service
* 创建人：yezhiyong   
* 创建时间：2016年8月24日 下午4:55:58   
* @version    
*
 */
@Service
public class ViewerLiveService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ViewerLiveService.class);
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private PushSingleService pushSingleService;
	
	/**
	 * 
	* @Title: insertFocusLive
	* @Description: 关注主播/想看预告
	* @return Object    返回类型
	* @throws
	 */
	public Object insertFocusLive(FocusLiveRequest focusLiveRequest) {
		//验证uid
		String uid = sessionTokenService.getStringForValue(focusLiveRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//关注主播/想看预告
			Integer type = focusLiveRequest.getType();
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("uid", uid);
			//查询直播观众信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此观众用户信息");
			}
			
			paramMap.clear();
			paramMap.put("createTime", DateUtil.format(new Date()));
			paramMap.put("updateTime", DateUtil.format(new Date()));
			
			if(type == 0){
				//查询直播用户信息
				Integer liverId = focusLiveRequest.getId();
				Map<Object, Object> toLiverMap = liveUserDao.queryLiverInfoById(liverId);
				if (toLiverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
				}
				
				//取消关注
				paramMap.clear();
				paramMap.put("liverStrId", liverMap.get("id"));
				paramMap.put("liverEndId", liverId);
				
				//查询是否关注过此用户
				Integer focusAnchorResult = liveUserDao.queryFocusAnchor(paramMap);
				if (focusAnchorResult == 0) {
					return new BaseResponse(ResponseCode.FAILURE, "你未关注此用户");
				}
				
				//删除关注用户信息记录
				Integer result = liveUserDao.deleteFocusAnchor(paramMap);
				if (result != 1) {
					return new BaseResponse(ResponseCode.FAILURE, "取消关注失败");
				}
				
				//更新用户关注数
				Map<Object, Object> map = new HashMap<>();
				map.put("uid", uid);
				map.put("concernNums", -1);
				Integer viewResult = liveUserDao.modifyLiverByUid(map);
				
				//更新用户的被关注数
				map.clear();
				map.put("uid", toLiverMap.get("uid"));
				map.put("concernedNums", -1);
				Integer anchorResult = liveUserDao.modifyLiverByUid(map);
				
				if (viewResult != 1 || anchorResult != 1) {
					return new BaseResponse(ResponseCode.FAILURE, "更新关注信息失败");
				}
				
				return new BaseResponse(ResponseCode.SUCCESS, "取消关注成功");
				
			}else if (type == 1) {
				//查询直播用户信息
				Integer liverId = focusLiveRequest.getId();
				Map<Object, Object> toLiverMap = liveUserDao.queryLiverInfoById(liverId);
				if (toLiverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
				}
				
				//关注用户
				paramMap.put("liverStrId", liverMap.get("id"));
				paramMap.put("liverEndId", liverId);
				
				if (liverMap.get("id").toString().equals(liverId.toString())) {
					return new BaseResponse(ResponseCode.FOCUS_MYSELFT_ERROR, "用户自己不能关注自己");
				}
				
				//查询是否关注过此用户
				Integer focusAnchorResult = liveUserDao.queryFocusAnchor(paramMap);
				if (focusAnchorResult != 0) {
					return new BaseResponse(ResponseCode.FAILURE, "你已关注此用户");
				}
				
				//关注主播
				paramMap.put("strUid", liverMap.get("uid"));
				paramMap.put("endUid", toLiverMap.get("uid"));
				Integer result = liveUserDao.insertFocusAnchor(paramMap);
				if (result != 1) {
					return new BaseResponse(ResponseCode.FAILURE, "插入关注用户信息失败");
				}
				
				//更新用户关注数
				Map<Object, Object> map = new HashMap<>();
				map.put("uid", uid);
				map.put("concernNums", 1);
				Integer viewResult = liveUserDao.modifyLiverByUid(map);
				
				//更新被关注用户的被关注数
				map.clear();
				map.put("uid", toLiverMap.get("uid"));
				map.put("concernedNums", 1);
				Integer anchorResult = liveUserDao.modifyLiverByUid(map);
				
				if (viewResult != 1 || anchorResult != 1) {
					return new BaseResponse(ResponseCode.FAILURE, "更新关注用户信息失败");
				}
				
				return new BaseResponse(ResponseCode.SUCCESS, "插入关注用户信息成功");
				
			}else if (type == 2) {
				//想看预告
				Integer liveRecordId = focusLiveRequest.getId();
				paramMap.put("liverId", liverMap.get("id"));
				paramMap.put("liveRecordId", liveRecordId);
				paramMap.put("status", 2);
				
				Map<Object, Object> liveRecordMap = liveUserDao.queryAnchorByLiveRecordId(liveRecordId);
				
				if (liveRecordMap == null ) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录");
				}else if (liveRecordMap != null && liveRecordMap.get("anchor_id").toString().equals(liverMap.get("id").toString())) {
					return new BaseResponse(ResponseCode.SUCCESS, "主播不能点击想看自己预告");
				}
				
				//查询是否有想看预告记录
				Integer focusShowResult = liveUserDao.queryFocusShow(paramMap);
				if (focusShowResult != 0) {
					return new BaseResponse(ResponseCode.SUCCESS, "你已登记想看预告信息");
				}
				
				//想看预告
				Integer result = liveUserDao.insertFocusShow(paramMap);
				if (result != 1) {
					return new BaseResponse(ResponseCode.FAILURE, "插入想看预告信息失败");
				}
				//推送消息
//				pushSingleService.wantSeeLive(uid, liveRecordId+"");
				return new BaseResponse(ResponseCode.SUCCESS, "插入想看预告信息成功");
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "请求参数有误");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
		}
	}
	
	
	public Object banSpeak(UidRequest uidRequest) {
		return null;
	}

	/**
	 * 
	* @Title: banSpeak
	* @Description: 主播禁言观众/主播解除观众禁言接口
	* @return Object    返回类型
	* @throws
	 */
	public Object banSpeakAndSpeak(BanSpeakAndSpeakRequest banSpeakAndSpeakRequest) {
		try {
			//验证token
			String uid = sessionTokenService.getStringForValue(banSpeakAndSpeakRequest.getSessiontoken()) + "";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
			
			//直播记录id
			Integer liveRecordId = banSpeakAndSpeakRequest.getLiveRecordId();
			Integer type = banSpeakAndSpeakRequest.getType();
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liveRecordId", liveRecordId);
			paramMap.put("uid", banSpeakAndSpeakRequest.getUid());
			
			//查询某一个观众观看某一场直播的观看信息
			Map<Object, Object> viewRecordMap = liveUserDao.queryLiveViewRecord(paramMap);
			
			if (viewRecordMap == null) {
				//查询是否是机器人,不是机器人，又没有观看记录，则是业务后台发送群组消息(此功能有待完善)
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(banSpeakAndSpeakRequest.getUid());
				if (liverMap != null && liverMap.size() > 0) {
					log.info("=============禁言了业务后台发送群组消息的用户============");
					return new BaseResponse(ResponseCode.SUCCESS,"禁言成功/解禁成功");
				}
				log.info("==========禁言/解禁机器人成功==========");
				return new BaseResponse(ResponseCode.SUCCESS,"禁言成功/解禁成功");
			}
			
			//禁言状态
			Integer banSpeakStatus = Integer.parseInt(viewRecordMap.get("ban_speak_status").toString());
			
			if (type == 1) {
				//禁言
				if (banSpeakStatus == 1) {
					return new BaseResponse(ResponseCode.FAILURE, "您已经禁言过此观众了");
				}else {
					//禁言状态:禁言
					paramMap.put("banSpeakStatus", 1);
					paramMap.put("updateTime", DateUtil.format(new Date()));
					
					//修改
					Integer result = liveUserDao.modifyLiveViewRecord(paramMap);
					if (result != 1) {
						return new BaseResponse(ResponseCode.FAILURE, "禁言观众失败");
					}
				}
				
				return new BaseResponse(ResponseCode.SUCCESS, "禁言观众成功");
				
			}else if (type == 2) {
				//解除禁言
				if (banSpeakStatus == 0) {
					return new BaseResponse(ResponseCode.FAILURE, "您没有禁言过此观众");
				}else {
					//禁言状态:未禁言
					paramMap.put("banSpeakStatus", 0);
					paramMap.put("updateTime", DateUtil.format(new Date()));
					
					//修改
					Integer result = liveUserDao.modifyLiveViewRecord(paramMap);
					if (result != 1) {
						return new BaseResponse(ResponseCode.FAILURE, "解除观众禁言失败");
					}
				}
				
				return new BaseResponse(ResponseCode.SUCCESS, "解除观众禁言成功");
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "类型参数错误,请重试");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "禁言/解除观众禁言失败");
		}
	}

}
