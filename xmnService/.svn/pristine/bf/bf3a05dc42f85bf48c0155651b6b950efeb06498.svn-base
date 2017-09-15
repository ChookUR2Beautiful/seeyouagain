package com.xmniao.xmn.core.live.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.MessageActivityRequest;
import com.xmniao.xmn.core.common.request.live.MessageManagerInfoRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.MessageManageDao;
import com.xmniao.xmn.core.live.entity.LiveFocusInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;


/**
 * 项目描述：XmnService
 * API描述：用户消息 及 系统消息
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */
@Service
public class MessageManageService {
	/**
	 * 消息管理DAO
	 * */
	@Autowired
	private MessageManageDao messageManageDao;
	
	/**
	 * 主播 观众通用DAO
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private StringRedisTemplate stringredisTemplate;  
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;  
	
	/**
	 * 描述：获取关注主播列表 显示消息设置
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	public Object getAttentionAnchorMsgList(MessageManagerInfoRequest messageInfoRequest){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		MapResponse response = null;
		try {
			
			Map<Object, Object> viewerMap = new HashMap<>();
			paramMap.put("id", messageInfoRequest.getId());
			paramMap.put("page", messageInfoRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			
			//根据观众ID 获取关注的主播ID列表
			List<LiveFocusInfo> focusList = liveUserDao.queryAttentionAnchorByViewerId(paramMap);
			if (focusList.size()>0) {
				List<Long> list = new ArrayList<Long>();
				for (int i = 0; i < focusList.size(); i++) {
					Long anchor_id = focusList.get(i).getLiver_end_id();
					list.add(anchor_id);
				}
				//根据观众ID 获取观众信息
				Map<Object, Object> liveviewerMap = liveUserDao.queryLiverInfoById(messageInfoRequest.getId());
				if (liveviewerMap!=null) {
					//获取关注主播列表 
					List<LiverInfo>  AnchorInfos = liveUserDao.queryAttentionAnchorByAnchorIds(list);
					liveviewerMap.put("msg_status", liveviewerMap.get("msg_status"));
					liveviewerMap.put("attentionShow", AnchorInfos);
					response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
					response.setResponse(viewerMap);
				}
			}else {
				response = new MapResponse(ResponseCode.FAILURE,"获取失败");
				response.setResponse(viewerMap);
			}
		} catch (Exception e) {
			response = new MapResponse(ResponseCode.FAILURE,"获取失败");
		}
		return response;
	}
	
	
	/**
	 * 描述：修改接收消息提醒状态
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@Transactional
	public Object liverEditMsgStatus(HttpServletRequest request){
		String id = request.getParameter("id");
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("id", id);
		MapResponse response = null;
		try {
			Map<Object, Object> liveviewerMap = liveUserDao.queryLiverInfoById(Integer.parseInt(id));
			if (liveviewerMap!=null && null != liveviewerMap.get("id")) {
				if (1 == Integer.parseInt(liveviewerMap.get("msg_status").toString())) {
					paramMap.put("msg_status", 0);
					liveUserDao.editLiveViewerMsgStatus(paramMap);
				}else {
					paramMap.put("msg_status", 1);
					liveUserDao.editLiveViewerMsgStatus(paramMap);
				}
				response = new MapResponse(ResponseCode.SUCCESS,"操作成功");
			}else {
				response = new MapResponse(ResponseCode.SUCCESS,"操作失败");
			}
		} catch (Exception e) {
			response = new MapResponse(ResponseCode.FAILURE,"操作失败");
		}
		return response;
	}
	
	/**
	 * 描述：获取用户的消息提醒   系统消息
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	public Object getSystemMessageList(MessageManagerInfoRequest messageInfoRequest){
		//获取uid
		String uid = sessionTokenService.getStringForValue(messageInfoRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
		try {
			
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("uid", uid);
			paramMap.put("page", messageInfoRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			
			List<Object> systemMsgs = messageManageDao.getMessageSystemList(paramMap);
			Map<Object, Object> msgMap = new HashMap<>();
			msgMap.put("systemMessage", systemMsgs);
			response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
			response.setResponse(msgMap);
			
		} catch (Exception e) {
			response = new MapResponse(ResponseCode.FAILURE,"操作失败");
		}
		return response;
	}
	
	
	/**
	 * 描述：批量修改系统消息 为已读
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@Transactional
	public Object editBatchMessageStauts(HttpServletRequest request){
		MapResponse response = null;
		try {
			
			String uid = request.getParameter("uid");
//			String type = request.getParameter("type");
			if (null!=uid && ""!=uid ) {
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("uid", uid);
				int resultSum = messageManageDao.editBatchMessageStatus(paramMap);
				if (resultSum>0) {
					response = new MapResponse(ResponseCode.SUCCESS,"操作成功");
				}
			}else {
				response = new MapResponse(ResponseCode.SUCCESS,"请检查参数是否完整");
			}
		} catch (Exception e) {
			response = new MapResponse(ResponseCode.FAILURE,"操作失败");
		}
		return response;
	}
	
	/**
	 * 描述：修改系统消息为已读
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@Transactional
	public Object editMessageStauts(HttpServletRequest request){
		MapResponse response = null;
		try {
			String uid = request.getParameter("uid");
			String tid = request.getParameter("tid");
//			String type = request.getParameter("type");
			if (null!=uid && ""!=uid && null!=tid && ""!=tid) {
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("uid",	uid);
				paramMap.put("tid", tid);
				int resultSum = messageManageDao.editMessageStatus(paramMap);
				if (resultSum>0) {
					response = new MapResponse(ResponseCode.SUCCESS,"操作成功");
				}
			}else {
				response = new MapResponse(ResponseCode.FAILURE,"请检查参数是否完整");
			}
		} catch (Exception e) {
			response = new MapResponse(ResponseCode.FAILURE,"操作失败");
		}
		return response;
	}

	
	
}
