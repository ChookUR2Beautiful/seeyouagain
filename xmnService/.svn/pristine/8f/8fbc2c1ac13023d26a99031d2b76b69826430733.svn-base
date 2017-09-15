package com.xmniao.xmn.core.live.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.AnchorViewerMemberRankService;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EmojiFilter;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TLSCallBackApi   
* 类描述：   互助直播回调
* 创建人：yezhiyong   
* 创建时间：2016年8月3日 下午4:49:12   
* @version    
*
 */

@Controller
@RequestMapping("/live")
public class TLSCallBackApi {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(TLSCallBackApi.class);
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入redis 
	 * */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 直播redis service
	 * */
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	/**
	 * 注入tlsSendImService
	 */
	@Autowired
	private TlsSendImService tlsSendImService;
	
	/**
	 * 
	* @Title: callbackStateChange
	* @Description: 主播上下线回调
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/tls/callback",method=RequestMethod.POST)
	@ResponseBody
	public Object tlsCallback(HttpServletRequest request){
		try {
			//获取请求参数
			Map<String, String[]> parameterMap = request.getParameterMap();
			/**
			 * CallbackCommand=State.StateChange
			 * SdkAppid=1400012767
			 */
			log.info("回调请求参数:" + parameterMap);
			
			//验证是否自己的SdkAppid请求
			String ownSdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String sdkAppid = parameterMap.get("SdkAppid")[0];
			log.info("ownSdkAppid = " + ownSdkAppid + ",sdkAppid = " + sdkAppid);
			
			if ( sdkAppid != null && ownSdkAppid.equalsIgnoreCase(sdkAppid)) {
				//获取腾讯请求包数据
				InputStream is = request.getInputStream();
				String content = IOUtils.toString(is, "UTF-8");
				log.info("回调成功,回调请求实体:content = " + content);
				
				//解析数据
				JSONObject jsonObject = JSONObject.parseObject(content);
				
				//回调命令
				String callbackCommand = jsonObject.getString("CallbackCommand");
				
				if ( callbackCommand != null) {
					if (callbackCommand.contains("State.StateChange")) {
						/**
						 * 登录请求参数{"CallbackCommand":"State.StateChange","Info":{"To_Account":"kuangfeng","Action":"Login","Reason":"Register"}}
						 * 退出请求参数{"CallbackCommand":"State.StateChange","Info":{"To_Account":"kuangfeng","Action":"Logout","Reason":"Unregister"}}
						 * 下线的原因有:
						 * 		Unregister:App用户注销账号导致TCP断开; 
						 * 		LinkClose:云通信检测到App TCP连接断开;
						 * 		Timeout：云通信检测到App心跳包超时,认为TCP已断开（客户端杀后台或Crash）。 
						 */
						//主播上下线回调
						this.callbackStateChange(jsonObject);
						
					}else if (callbackCommand.contains("Group.CallbackAfterSendMsg")) {
						/**
						 * {
							    "CallbackCommand": "Group.CallbackAfterSendMsg",
							    "From_Account": "12100008888",
							    "GroupId": "@xmniao#999991",
							    "MsgBody": [
							        {
							            "MsgContent": {
							                "Text": "系统消息 : 萌萌 加入了直播间"
							            },
							            "MsgType": "TIMTextElem"
							        }
							    ],
							    "Type": "AVChatRoom"
							}
						 */
						//群组聊天信息回调
						this.callbackAfterSendMsg(jsonObject);
						
					}else if (callbackCommand.contains("C2C.CallbackAfterSendMsg")) {
						/**
						 * {
								    "CallbackCommand": "C2C.CallbackAfterSendMsg",  // 回调命令
								    "From_Account": "jared",  // 发送者
								    "To_Account": "Jonh",  // 接收者
								    "MsgBody": [  // 消息体，参见TIMMessage消息对象
								        {
								            "MsgType": "TIMTextElem", // 文本
								            "MsgContent": {
								                "Text": "red packet"
								            }
								        }
								    ]
								}
						 */
						//私信回调
						this.callbackAfterSendSecret(jsonObject);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: callbackStateChange
	* @Description: 上下线回调处理
	* @return void    返回类型
	* @throws
	 */
	public void callbackStateChange(JSONObject jsonObject){
		try {
			/**
			 * 登录请求参数{"CallbackCommand":"State.StateChange","Info":{"To_Account":"kuangfeng","Action":"Login","Reason":"Register"}}
			 * 退出请求参数{"CallbackCommand":"State.StateChange","Info":{"To_Account":"kuangfeng","Action":"Logout","Reason":"Unregister"}}
			 * 下线的原因有:
			 * 		Unregister:App用户注销账号导致TCP断开; 
			 * 		LinkClose:云通信检测到App TCP连接断开;
			 * 		Timeout：云通信检测到App心跳包超时,认为TCP已断开（客户端杀后台或Crash）。 
			 */
			
			JSONObject infoJson = JSONObject.parseObject(jsonObject.getString("Info"));
			//云账号用户
			String account = infoJson.getString("To_Account");
			//回调操作
			String action = infoJson.getString("Action");
			//回调原因
			String reason = infoJson.getString("Reason");
			
			//根据云账号直播用户信息(默认在腾讯云,账号用户为手机号码)
			Map<Object, Object> liverMap = liveUserDao.queryAnchorByPhone(account);
			
			if (liverMap != null ) {
				if ("Login".equalsIgnoreCase(action)) {
					//直播用户重新异常退出，重新连接(上线回调)
					//this.login(liverMap, reason);
					
				}else if ("Logout".equalsIgnoreCase(action)) {
					if ("Timeout".equalsIgnoreCase(reason) || "LinkClose".equalsIgnoreCase(reason)) {
						//直播用户TCP连接断开、若干心跳包无法收到触发(下线回调) 包含杀掉进程
						this.logout(liverMap, reason);
					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("错误信息如下:" + e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: Login
	* @Description: 主播重新异常退出，重新连接(上线回调)
	* @return void    返回类型
	* @throws
	 */
	public void login(Map<Object, Object> liverMap,String reason) {
		Map<Object, Object> paramMap = new HashMap<>();
		//主播id
		paramMap.put("anchorId", Integer.parseInt(liverMap.get("id").toString()));
		//直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4 无回放
		paramMap.put("zhiboType", 2);
		
		//查询该主播暂停直播的记录
		LiveRecordInfo liveRecord= anchorLiveRecordDao.queryLiveRecordIdByAnchorId(paramMap);
		
		//主播异常退出后,再次登录处理
		if (liveRecord != null) {
			//主播异常退出后,再次登录处理
			this.anchorExceptionLogin(liverMap,liveRecord);
		}
		
		paramMap.clear();
		//观众直播用户id
		paramMap.put("liverId", liverMap.get("id"));
		//观看状态  0 已退出 1 正在观看   2  直播结束  3 观看异常 
		paramMap.put("liveStatus", 3);
		
		//查询观众直播正在观看记录
		Map<Object, Object> viewRecordMap = anchorLiveRecordDao.queryCurrentViewRecord(paramMap);
		
		//观众异常退出后,再次登录处理
		if (viewRecordMap != null) {
			//观众异常退出后,再次登录处理
			this.viewerExceptionLogin(liverMap, viewRecordMap);
			
		}
		
	}
	
	/**
	 * 
	* @Title: anchorExceptionLogin
	* @Description: //观众异常退出后,再次登录处理
	* @return void    返回类型
	* @throws
	 */
	public void viewerExceptionLogin(Map<Object, Object> liverMap,Map<Object, Object> viewRecordMap) {
		try {
			//将观众的观看状态修改为:正在观看                              观看状态  0 已退出 1 正在观看   2  直播结束  3 观看异常 
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("id", Integer.parseInt(viewRecordMap.get("id").toString()));//观众观看直播记录ID
			paramMap.put("live_status", "1");
			paramMap.put("update_time", DateUtil.format(new Date()));
			
			Integer result = anchorLiveRecordDao.editLiveViewRecordStatus(paramMap);
			//日志
			if (result != 1) {
				//成功日志
				log.info("观众uid=" + liverMap.get("uid") + "异常掉线后,修改观看状态为观看异常成功");
				
			}else {
				//失败日志
				log.info("观众uid=" + liverMap.get("uid") + "异常掉线后,修改观看状态为观看异常失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("观众异常退出后,再次登录处理失败,错误信息如下:" + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @Title: anchorExceptionLogin
	 * @Description: //主播异常退出后,再次登录处理
	 * @return void    返回类型
	 * @throws
	 */
	public void anchorExceptionLogin(Map<Object, Object> liverMap,LiveRecordInfo liveRecord) {
		try {
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liveRecordId", liveRecord.getId());
			paramMap.put("liveId", Integer.parseInt(liverMap.get("id").toString()));
			
			//查询异常退出记录
			Map<Object, Object> liveExitRecord = anchorLiveRecordDao.queryLiveExitRecord(paramMap);
			if (liveExitRecord != null) {
				if (liveExitRecord.get("live_exit_end") == null) {
					//计算异常退出直播时长
					Long startTime = DateUtil.parse(liveExitRecord.get("live_exit_start").toString()).getTime();
					Long endTime = new Date().getTime();
					Long duraTime = (endTime - startTime)/1000;
					Double time = Math.floor(Double.parseDouble(duraTime.toString()));
					
					paramMap.clear();
					paramMap.put("id", liveExitRecord.get("id"));
					paramMap.put("duraTime", time);
					paramMap.put("liveExitEnd", DateUtil.format(new Date()));
					paramMap.put("updateTime", DateUtil.format(new Date()));
					
					//修改直播异常退出记录
					anchorLiveRecordDao.modifyLiveExitRecord(paramMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("主播异常退出后,再次登录处理失败,错误信息如下:" + e.getMessage());
		}
	}
	
	/**
	 * 
	* @Title: logout
	* @Description: 主播心跳超时等下线回调
	* @return void    返回类型
	* @throws
	 */
	public void logout(Map<Object, Object> liverMap,String reason) {
		//查询该主播正在直播的记录
		Map<Object, Object> paramMap = new HashMap<>();
		//主播id
		paramMap.put("anchorId", Integer.parseInt(liverMap.get("id").toString()));
		//直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4 无回放 5结束直播
		paramMap.put("zhiboType", 1);
		
		//查询主播是否有正在直播的直播记录
		LiveRecordInfo liveRecord = anchorLiveRecordDao.queryLiveRecordIdByAnchorId(paramMap);
		
		//主播异常退出处理
		if (liveRecord != null && null != liveRecord.getId()) {
			//主播异常退出处理
			this.anchorExceptionLogout(liverMap,liveRecord,reason);
			
		}
			
		paramMap.clear();
		//观众直播用户id
		paramMap.put("liverId", liverMap.get("id"));
		//观看状态  0 已退出 1 正在观看   2  直播结束  3 等待开播
		List<Integer> liveStatus = new ArrayList<>();
		liveStatus.add(1);
		liveStatus.add(2);
		paramMap.put("liveStatus",liveStatus);
		
		//查询观众正在观看记录
		Map<Object, Object> viewCurrentRecordMap = anchorLiveRecordDao.queryCurrentViewRecord(paramMap);
		
		//观众异常退出处理
		if (viewCurrentRecordMap != null) {
			//观众异常退出处理
			this.viewerExceptionLogout(liverMap, viewCurrentRecordMap,reason);
			
		}
		
	}
	
	/**
	 * 
	* @Title: viewerExceptionLogout
	* @Description: 观众异常退出处理
	* @return void    返回类型
	* @throws
	 */
	public void viewerExceptionLogout(Map<Object, Object> liverMap, Map<Object, Object> viewRecordMap,String reason) {
		log.info("用户异常退出执行开始========================");
		//首先同步redis 到数据库  包括基本信息   鸟蛋
		String liver_rediskey = "liver_"+Long.valueOf(liverMap.get("uid").toString());
		Map<Object, Object>  liverInfoMap= stringRedisTemplate.opsForHash().entries(liver_rediskey);
		
		List<Map<Object, Object>> editveiwerList = new ArrayList<Map<Object, Object>>();
		try {
			//计算观众观看时长所得经验
			LiverRoomRequest liverRoomRequest = new LiverRoomRequest();
			liverRoomRequest.setZhiboRecordId(Integer.parseInt(viewRecordMap.get("live_record_id").toString()));
			Map<Object, Object> resultMap= anchorViewerMemberRankService.addViewerOrAnchorExperience(liverInfoMap, liverRoomRequest, liver_rediskey,0);
			resultMap.put("update_time", DateUtil.format(new Date()));
			
			editveiwerList.add(resultMap);
			//批量修改用户基本信息   此逻辑中仅修改一个  同步redis到数据库
			liveUserDao.editLiveViewerInfo(editveiwerList);
			log.info("用户异常退出同步数据库========================");
			//将观众的观看状态修改为:已退出    观看状态  0 已退出 1 正在观看   2  直播结束  3 观看异常 
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("id", liverRoomRequest.getZhiboRecordId());//观众观看直播记录ID
			paramMap.put("live_status", 0);
			paramMap.put("updateTime", DateUtil.format(new Date()));
			paramMap.put("endTime", DateUtil.format(new Date()));
			paramMap.put("liver_id", liverMap.get("id"));
			paramMap.put("view_duration", resultMap.get("viewTime"));
			Integer result = anchorLiveRecordDao.editLiveViewRecordStatus(paramMap);
			//日志
			if (result > 0) {
				//成功日志
				log.info("观众uid=" + liverMap.get("uid") + "异常掉线后,修改观看状态为已退出成功");
				
			}else {
				//失败日志
				log.info("观众uid=" + liverMap.get("uid") + "异常掉线后,修改观看状态为已退出失败");
			}
			
		} catch (Exception e) {
			log.info("观众异常掉线,同步观众经验错误,错误信息如下:" + e.getMessage());
			e.printStackTrace();
		}
		log.info("用户异常退出执行结束========================");
	}
	
	/**
	 * 主播异常掉线相关处理
	 * liveExceptionLogout
	 * @param Map<Object, Object> liveMap 主播信息    
	 * 
	 * */
	public void anchorExceptionLogout(Map<Object, Object> liverMap, LiveRecordInfo liveRecord,String reason){
		log.info("主播异常退出开始执行=================");
		try {
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("liveRecordId", liveRecord.getId());
			paramMap.put("liveId", liverMap.get("id").toString());
			paramMap.put("liveExitStart", DateUtil.format(new Date()));
			paramMap.put("createTime", DateUtil.format(new Date()));
			paramMap.put("updateTime", DateUtil.format(new Date()));
			
			//异常退出原因
			Integer exitReason = null;
			switch(reason){
			case "Unregister":
				exitReason = 1;
				break;
			case "LinkClose":
				exitReason = 2;
				break;
			case "Timeout":
				exitReason = 3;
				break;
			default :
				exitReason = null;
			}
			paramMap.put("exitReason", exitReason);
			//记录主播正在直播异常退出信息
			anchorLiveRecordDao.insertLiveExitRecord(paramMap);
			
			//同步主播鸟蛋,清理直播以及观众观看记录,发送IM消息关闭直播间
			tlsSendImService.synAnchorInfo(liveRecord);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("主播异常掉线，同步信息失败,错误信息如下:" + e.getMessage());
		}
		
		log.info("主播异常退出执行结束=================");
		
	}
	
	/**
	 * 
	* @Title: callbackAfterSendMsg
	* @Description: 群组聊天信息回调处理
	* @return void    返回类型
	* @throws
	 */
	public void callbackAfterSendMsg(JSONObject jsonObject){
		/**
		 * 格式一:文本消息 :普通用户进入房间提示(不解析)
		 * {
			    "CallbackCommand": "Group.CallbackAfterSendMsg",
			    "From_Account": "12100008888",
			    "GroupId": "@xmniao#999991",
			    "MsgBody": [
			        {
			            "MsgContent": {
			                "Text": "系统消息 : 萌萌 加入了直播间"
			            },
			            "MsgType": "TIMTextElem"
			        }
			    ],
			    "Type": "AVChatRoom"
			}
			
			格式二:自定义消息:互动聊天室聊天内容(解析,获取聊天信息)
			{
			    "CallbackCommand": "Group.CallbackAfterSendMsg",
			    "From_Account": "15088138451",
			    "GroupId": "@xmniao#999991",
			    "MsgBody": [
			        {
			            "MsgContent": {
			                "Data": "{
			  "uid" : "340709",
			  "iconUrl" : "http:\/\/gzdev.xmniao.com:88\/img\/M00\/01\/FF\/wKgyMle2xH-EcZL7AAAAAFTi2lc496.png",
			  "dj" : 21,
			  "soundType" : 0,
			  "uname" : "桃小妖",
			  "type" : 10,
			  "isNoSpeak" : false,
			  "text" : "测试123"
			}",
			                "Desc": "",
			                "Ext": ""
			            },
			            "MsgType": "TIMCustomElem"
			        }
			    ],
			    "Type": "AVChatRoom"
			}
		 */
		
		//直播间类型:互动直播间
		String roomType = jsonObject.getString("Type");
		if ("AVChatRoom".equalsIgnoreCase(roomType)) {
			//发送者
			String sendUname = jsonObject.getString("From_Account");
			//群组id
			String groupId = jsonObject.getString("GroupId");
			
			//消息实体
			JSONArray msgBody = JSONObject.parseArray(jsonObject.getString("MsgBody"));
			log.info("msgBody : " + msgBody.toString());
			
			//消息类型:消息文本
			JSONObject msg = JSONObject.parseObject(msgBody.getString(0));
			String msgType = msg.getString("MsgType");
			//MsgType的值: TIMTextElem  文本消息        TIMCustomElem	自定义消息
			if ("TIMCustomElem".equalsIgnoreCase(msgType)) {
				//消息内容
				String data = JSONObject.parseObject(msg.getString("MsgContent")).getString("Data");
				log.info("msgContent : " + data.toString());
				//解析数据
				JSONObject dataJsonObj = JSONObject.parseObject(data);
				
				//文本内容
				String msgContent = dataJsonObj.getString("text");
				//发送群组消息的uid
				String uid = dataJsonObj.getString("uid");
				//自定义消息类型
				String type = dataJsonObj.getString("type");
				//是否是前置摄像头
				String isQianZhi = dataJsonObj.getString("isQianZhi");
				//是否是横屏
				String isHorizontalScreen = dataJsonObj.getString("isHorizontalScreen");
				//是否是机器人标识   0是机器人
				String utype = dataJsonObj.getString("utype");
				
				log.info("============================发送IM消息=====================================");
				log.info("sendUname=" + sendUname + ", groupId=" + groupId + ",uid=" + uid   + ", type=" + type  + ",isHorizontalScreen=" + isHorizontalScreen 
						+ ",isQianZhi=" + isQianZhi);
				
				try {
					if (Integer.parseInt(type) == 9) {
						//切屏回调
						this.changeHorizontalScreen(uid, isHorizontalScreen);
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("切屏回调失败,错误信息如下:" + e.getMessage());
				}
				
				try {
					if (Integer.parseInt(type) == 11) {
						//摄像头切换
						this.changeCamera(uid, isQianZhi);
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info("摄像头切换回调失败,错误信息如下:" + e.getMessage());
				}
				
				Map<Object, Object> liverMap = null;
				try {
					if ((Integer.parseInt(type) == 1 || Integer.parseInt(type) == 4) && (utype == null || Integer.parseInt(utype) != 0)) {
						if (!"admin".equalsIgnoreCase(sendUname)) {
							//记录观众普通消息
							liverMap =liveUserDao.queryLiveByPhone(sendUname);
						}else {
							//记录业务后台发送的普通消息
							liverMap =liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
						}
						
						this.recordPublicMsg(groupId,liverMap,msgContent,type);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					log.info("插入聊天室普通信息失败,信息如下:msgContent=" + msgContent + ",sendUname=" + sendUname + ",uid=" + uid + ",groupNo=" + groupId + ",发生错误时间:" + DateUtil.format(new Date()));
				}
				
			}
		}
	}
	
	/**
	 * 
	* @Title: recordPublicMsg
	* @Description: 记录聊天室普通消息
	* @return void    返回类型
	* @throws
	 */
	public void recordPublicMsg(String groupId,Map<Object, Object> liverMap,String msgContent,String type) {
		
		
		//根据群组号查询主播id,再根据主播的id查询正在直播的直播记录或者是最近结束的直播记录 
		Map<Object, Object> anchorMap = liveUserDao.queryAnchorByGroupId(groupId);
		Map<Object, Object> resultMap = anchorLiveRecordDao.queryCurrentLiveRecordByAnchorId(Integer.parseInt(anchorMap.get("id").toString()));
		
		//回调操作:记录聊天室记录信息
		if (EmojiFilter.containsEmoji(msgContent)) {
			msgContent = EmojiFilter.filterEmoji(msgContent);
		}
		Map<Object, Object> paramMap = new HashMap<>();
//		paramMap.put("sendUname", liverMap.get("phone")==null?liverMap.get("openid"):liverMap.get("phone"));
		paramMap.put("sendUname", liverMap.get("phone")==null?liverMap.get("uid"):liverMap.get("phone"));
		paramMap.put("groupNo", groupId);
		paramMap.put("msgContent", msgContent);
		paramMap.put("liveRecordId", resultMap.get("id").toString());
		paramMap.put("sendLiverId", liverMap.get("id").toString());
		paramMap.put("sendTime", DateUtil.format(new Date()));
		paramMap.put("msgtype", type);
		paramMap.put("createTime", DateUtil.format(new Date()));
		paramMap.put("updateTime", DateUtil.format(new Date()));
		
		//插入信息记录
		Integer result = liveUserDao.insertPublicMsg(paramMap);
		if (result != 1) {
			log.info("插入聊天室普通信息失败,信息如下:msgContent=" + msgContent + ",sendUname=" + liverMap.get("phone") + ",groupNo=" + groupId + ",发生错误时间:" + DateUtil.format(new Date()));
		}else {
			log.info("插入聊天室普通信息成功,信息如下:msgContent=" + msgContent + ",sendUname=" + liverMap.get("phone") + ",groupNo=" + groupId);
		}
	}
	
	/**
	 * 
	* @Title: changeCamera
	* @Description: 摄像头切换
	* @return void    返回类型
	* @throws
	 */
	public void changeCamera(String uid,String isQianZhi) {

		//查询主播用户信息
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		
		if (liverMap != null) {
			//查询某一个主播的直播记录信息
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("zhiboType", 1);
			paramMap.put("anchorId", liverMap.get("id"));
			LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordIdByAnchorId(paramMap);
			
			//摄像头摆放
			Integer camera = liveRecordInfo.getIsQianZhi();
			if (camera != Integer.parseInt(isQianZhi)) {
				paramMap.clear();
				paramMap.put("id", liveRecordInfo.getId());
				paramMap.put("isQianZhi", Integer.parseInt(isQianZhi));
				paramMap.put("updateTime", DateUtil.format(new Date()));
				
				//修改直播记录摄像头放置
				Integer result = anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
				
				//日志
				if (result != 1) {
					//成功日志
					log.info("主播uid=" + uid + "，当前摄像头摆放为:" + camera + ",切换摄像头摆放为" + Integer.parseInt(isQianZhi) + "失败");
					
				}else {
					//失败日志
					log.info("主播uid=" + uid + "，当前摄像头摆放为:" + camera + ",切换摄像头摆放为" + Integer.parseInt(isQianZhi) + "成功");
				}
			}
			
		}
	
	}
	
	/**
	 * 
	* @Title: changeHorizontalScreen
	* @Description: 切换横竖屏
	* @return void    返回类型
	* @throws
	 */
	public void changeHorizontalScreen(String uid,String isHorizontalScreen) {

		//查询主播用户信息
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		
		if (liverMap != null) {
			//查询某一个主播的直播记录信息
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("zhiboType", 1);
			paramMap.put("anchorId", liverMap.get("id"));
			LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordIdByAnchorId(paramMap);
			
			//横竖屏状态
			Integer liveRecordScreen = liveRecordInfo.getIsHorizontalScreen();
			if (liveRecordScreen != Integer.parseInt(isHorizontalScreen)) {
				paramMap.clear();
				paramMap.put("id", liveRecordInfo.getId());
				paramMap.put("isHorizontalScreen", Integer.parseInt(isHorizontalScreen));
				paramMap.put("updateTime", DateUtil.format(new Date()));
				
				//修改直播记录横竖屏状态
				Integer result = anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
				
				//日志
				if (result != 1) {
					//成功日志
					log.info("主播uid=" + uid + "，当前屏幕摆放为:" + liveRecordScreen + ",切换屏幕摆放为" + Integer.parseInt(isHorizontalScreen) + "失败");
					
				}else {
					//失败日志
					log.info("主播uid=" + uid + "，当前屏幕摆放为:" + liveRecordScreen + ",切换屏幕摆放为" + Integer.parseInt(isHorizontalScreen) + "成功");
				}
			}
			
		}
	
	}
	
	/**
	 * 
	* @Title: callbackAfterSendSecret
	* @Description: 私信回调处理
	* @return void    返回类型
	* @throws
	 */
	public void callbackAfterSendSecret(JSONObject jsonObject){
		/**
		 * {
				    "CallbackCommand": "C2C.CallbackAfterSendMsg",  // 回调命令
				    "From_Account": "jared",  // 发送者
				    "To_Account": "Jonh",  // 接收者
				    "MsgBody": [  // 消息体，参见TIMMessage消息对象
				        {
				            "MsgType": "TIMTextElem", // 文本
				            "MsgContent": {
				                "Text": "red packet"
				            }
				        }
				    ]
				}
		 */
		
		//消息内容
		String msgContent = "";
		//发送者
		String sendUname = jsonObject.getString("From_Account");
		//接受者
		String toUname = jsonObject.getString("To_Account");
		
		//消息实体
		JSONArray msgBody = JSONObject.parseArray(jsonObject.getString("MsgBody"));
		log.info("msgBody:" + msgBody);
		
		//消息类型
		JSONObject msg = JSONObject.parseObject(msgBody.getString(0));
		String msgType = msg.getString("MsgType");
		//MsgType的值: TIMTextElem  文本消息        TIMCustomElem	自定义消息
		if ("TIMTextElem".equalsIgnoreCase(msgType)) {
			//消息内容
			msgContent = JSONObject.parseObject(msg.getString("MsgContent")).getString("Text");
			log.info("msgContent : " + msgContent.toString());
			
			try {
				//组装参数
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("sendUname", sendUname);
				paramMap.put("toUname", toUname);
				//消息内容
				paramMap.put("msgContent", msgContent);
				//发送时间
				paramMap.put("msgSendTime", DateUtil.format(new Date()));
				//创建时间
				paramMap.put("createTime", DateUtil.format(new Date()));
				//更新时间
				paramMap.put("updateTime", DateUtil.format(new Date()));
				
				//记录私信信息
				Integer result = liveUserDao.insertSecretMsg(paramMap);
				if (result != 1) {
					log.info("记录私信信息失败,信息如下:msgContent=" + msgContent + ",sendUname=" + sendUname + ",toUname=" + toUname + ",发生错误时间:" + DateUtil.format(new Date()));
				}else {
					log.info("记录私信信息成功,信息如下:msgContent=" + msgContent + ",sendUname=" + sendUname + ",toUname=" + toUname + ",记录时间:" + DateUtil.format(new Date()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("记录私信信息失败,信息如下:msgContent=" + msgContent + ",sendUname=" + sendUname + ",toUname=" + toUname + ",发生错误时间:" + DateUtil.format(new Date()));
			}
		}
		
	}

}
