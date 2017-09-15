package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.xmniao.xmn.core.common.request.live.*;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.live.AnchorAnnunciateListRequest;
import com.xmniao.xmn.core.common.request.live.AttentionListRequest;
import com.xmniao.xmn.core.common.request.live.LiveRecordRequest;
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.live.dao.LiveRobotDao;
import com.xmniao.xmn.core.live.dao.LiveRoomDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.response.UserLiveOverResponse;
import com.xmniao.xmn.core.recruit.dao.WorksDao;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.DistanceUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;



/**
 * 项目描述：XmnService
 * API描述：直播预告回放列表业务处理类
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Service
public class AnchorLiveRecordService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AnchorLiveRecordService.class);
	
	/**
	 * 直播列表 回放  预告dao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;  
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;  
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入sellerInfoDao
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	/**
	 * 注入文件头
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 加载直播属性文件
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private WorksDao workDao;
	
	/**
	 * 注入商圈
	 */
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 注入liveRobotService
	 */
	@Autowired
	private LiveRobotService liveRobotService;
	
	/**
	 * 注入liveRobotDao
	 */
	@Autowired
	private LiveRobotDao liveRobotDao;
	
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	@Autowired
	private LiveAnchorRoomService liveAnchorRoomService;
	
	/**
	 * 注入liveRoomDao
	 */
	@Autowired
	private LiveRoomDao liveRoomDao;

	
	/**
	 * 描述：获取预告和(直播及回放)列表
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 */
	public Object queryLiveRecordList(LiveRecordRequest liveRecordsRequest){
		try {
			//组装参数
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("page", liveRecordsRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			
			if (StringUtils.isNotEmpty(liveRecordsRequest.getSessiontoken())) {
				//获取Uid  查询直播记录是否指定该用户观看
				String uid = sessionTokenService.getStringForValue(liveRecordsRequest.getSessiontoken()) + "";
				log.info("获取到当前登录用户的UID:============="+uid);
				if (!StringUtils.isEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
					Map<Object, Object> LiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
					log.info("获取到当前登录用户的手机号码:============="+LiverMap.get("phone"));
					paramMap.put("phone", LiverMap.get("phone"));
				}
			}
			
			//结果
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("liveRecord", resultList);
			if (liveRecordsRequest.getType() == 0) {
				//预告列表
				resultList = this.getAnnunciateList(paramMap);
				resultMap.put("liveRecord", resultList);
			}else if (liveRecordsRequest.getType() == 1){
				//直播及回放列表
				List<LiveRecordInfo> currentList = anchorLiveRecordDao.getCurrentLiveRecordInfo(paramMap);
				if (currentList != null && currentList.size() > 0) {
					//更新需要返回直播/回放的列表字段数据
					resultList = updateLiveRecordList(currentList);
					//纬度
					Double latitude = liveRecordsRequest.getLatitude();
					//经度
					Double longitude = liveRecordsRequest.getLongitude();
					if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
						//获取正在直播的距离
						resultList = getDistance(latitude,longitude,resultList);
					}
					resultMap.put("liveRecord", resultList);
				}
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"直播列表获取成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取预告/直播,回放列表失败");
		}
			
	}
	
	/**
	 * 
	* @Title: getDistance
	* @Description: 获取用户跟正在直播的商家距离
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	public List<Map<Object, Object>> getDistance(Double latitude,Double longitude,List<Map<Object, Object>> paramList) {
		//统计正在直播的店铺id
		List<Integer> sellerIds = new ArrayList<>();
		for (Map<Object, Object> paramMap : paramList) {
			if (paramMap.get("zhiboType") != null && Integer.parseInt(paramMap.get("zhiboType").toString()) == 1) {
				//计算直播记录有经纬度的距离
				if (paramMap.get("longitude") != null && paramMap.get("latitude") != null) {
					Double sellerLongitude = Double.parseDouble(paramMap.get("longitude").toString());
					Double sellerLatitude = Double.parseDouble(paramMap.get("latitude").toString());
					//计算距离
					Double distance = DistanceUtil.Distance(longitude, latitude, sellerLongitude, sellerLatitude);
					String distanceStr = "";
					if (distance < 5000) {
						//格式化距离
						if (distance > 1000) {
							distanceStr = ArithUtil.div(distance, 1000) + "km";
						}else {
							DecimalFormat df = new DecimalFormat("0");
							BigDecimal b = new BigDecimal(distance);
							distanceStr = df.format(b) + "m";
						}
						//将距离添加进入结果集
						paramMap.put("distance", distanceStr);
					}
					continue;
				}
				if (paramMap.get("sellerId") != null) {
					sellerIds.add(Integer.parseInt(paramMap.get("sellerId")==null?"0":paramMap.get("sellerId").toString()));
				}
			}
		}
		
		//查询正在直播的店铺的经纬度(直播记录没有经纬度的)
		if (sellerIds.size() > 0) {
			List<Map<Object, Object>> landMarkList = sellerInfoDao.querySellersLandMark(sellerIds);
			if (landMarkList != null && landMarkList.size() > 0) {
				//获取正在直播商家的经纬度
				for (Map<Object, Object> landMarkMap : landMarkList) {
					Double sellerLongitude = Double.parseDouble(landMarkMap.get("longitude").toString());
					Double sellerLatitude = Double.parseDouble(landMarkMap.get("latitude").toString());
					if (sellerLongitude != 0 && sellerLatitude != 0) {
						//计算距离
						Double distance = DistanceUtil.Distance(longitude, latitude, sellerLongitude, sellerLatitude);
						String distanceStr = "";
						if (distance < 5000) {
							//格式化距离
							if (distance > 1000) {
								distanceStr = ArithUtil.div(distance, 1000) + "km";
							}else {
								DecimalFormat df = new DecimalFormat("0");
								BigDecimal b = new BigDecimal(distance);
								distanceStr = df.format(b) + "m";
							}
							//将距离添加进入结果集
							for (Map<Object, Object> resultMap : paramList) {
								if (resultMap.get("sellerId") != null && landMarkMap.get("sellerId").toString().equals(resultMap.get("sellerId").toString())) {
									resultMap.put("distance", distanceStr);
								}
							}
						}
					}
				}
			}
		}
		
		return paramList;
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: updateLiveRecordList
	* @Description: 更新所需要的直播/回放,关注列表字段
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> updateLiveRecordList(List<LiveRecordInfo> paramList) throws IOException {
		//结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		List<Integer> anchorIds = new ArrayList<>();
		for (LiveRecordInfo liveRecord : paramList) {
			anchorIds.add(new Long(liveRecord.getAnchor_id()).intValue());
			Map<Object, Object> resultMap = new HashMap<>();
			//直播/回放记录id
			resultMap.put("id", liveRecord.getId());
			//主播用户id
			resultMap.put("anchorId", liveRecord.getAnchor_id());
			//主播昵称
			resultMap.put("nname", liveRecord.getNname());
			//主播头像
			resultMap.put("avatar", fileUrl + liveRecord.getAvatar());
			//预告标题
			resultMap.put("title", liveRecord.getZhibo_title());
			//预告封面
			resultMap.put("cover", fileUrl + liveRecord.getZhibo_cover());
			//主播聊天室群组id
			resultMap.put("groupId", propertiesUtil.getValue("groupPrefix", "conf_live.properties") + liveRecord.getAnchor_room_no());
			//主播房间编号
			resultMap.put("anchorRoomNo", liveRecord.getAnchor_room_no());
			//直播观看人数
			resultMap.put("viewCount", liveRecord.getView_count());
			//商家id
			resultMap.put("sellerId", liveRecord.getSellerid());
			//商家名称
			resultMap.put("sellerName", liveRecord.getSellername());
			//如果有商家别名，显示商家别名
			if (StringUtils.isNotEmpty(liveRecord.getSeller_alias())) {
				resultMap.put("sellerName", liveRecord.getSeller_alias());
			}
			//商家所在的城市
			resultMap.put("sellerCity", liveRecord.getSeller_city());
			//商家经度
			if (liveRecord.getLongitude() != null && liveRecord.getLongitude() != 0) {
				resultMap.put("longitude", liveRecord.getLongitude());
			}
			//商家纬度
			if (liveRecord.getLatitude() != null && liveRecord.getLatitude() != 0) {
				resultMap.put("latitude", liveRecord.getLatitude());
			}
			//是否横屏状态：0不是   1是
			resultMap.put("isHorizontalScreen", liveRecord.getIsHorizontalScreen());
			//距离直播商家的距离
			resultMap.put("distance", "");
			//开播类型 ： 0 通告开播    1 自定义开播
			resultMap.put("liveStartType", liveRecord.getLive_start_type());
			//直播类型
			resultMap.put("zhiboType", liveRecord.getZhibo_type());
			
			//直播回放日期
			resultMap.put("playbackDay", "");
			//直播回放地址
			resultMap.put("playbackUrl", "");
			//回放列表
			if (liveRecord.getZhibo_type() == 3) {
				
				if (liveRecord.getPlan_end_date() != null) {
					//直播回放日期
					resultMap.put("playbackDay", DateUtil.format(liveRecord.getPlan_end_date(), "yyyy-MM-dd"));
				}else {
					//直播回放日期
					resultMap.put("playbackDay", DateUtil.format(liveRecord.getEnd_date(), "yyyy-MM-dd"));
				}

//				//直播回放日期
//				resultMap.put("playbackDay", DateUtil.format(liveRecord.getPlan_end_date(), "yyyy-MM-dd"));
				if (liveRecord.getPlan_end_date() != null) {
					//直播回放日期
					resultMap.put("playbackDay", DateUtil.format(liveRecord.getPlan_end_date(), "yyyy-MM-dd"));
				}else {
					//直播回放日期
					resultMap.put("playbackDay", DateUtil.format(liveRecord.getEnd_date(), "yyyy-MM-dd"));
				}

//				//直播回放日期
//				resultMap.put("playbackDay", DateUtil.format(liveRecord.getPlan_end_date(), "yyyy-MM-dd"));
				//直播回放地址
				resultMap.put("playbackUrl", liveRecord.getZhibo_playback_url());
			
			}
			resultList.add(resultMap);
		}
		
		//查询所有的uid
		List<Map<Object, Object>> uidsList = liveUserDao.queryLiverUids(anchorIds);
		if (uidsList != null && uidsList.size() > 0 ) {
			for (Map<Object, Object> resultMap : resultList) {
				for (Map<Object, Object> uidMap : uidsList) {
					if (resultMap.get("anchorId").toString().equals(uidMap.get("anchorId").toString())) {
						//星币
						resultMap.put("sex", uidMap.get("sex"));
						//添加uid
						resultMap.put("ZBUid", uidMap.get("uid"));
						//添加登录账号
						resultMap.put("account", uidMap.get("account"));
						break;
						
					}
				}
			}
		}
		return resultList;
	}
	
	/**
	 * 
	* @Title: queryAttentionList
	* @Description: 获取关注列表
	* @return Object    返回类型
	* @throws
	 */
	public Object queryAttentionList(AttentionListRequest attentionListRequest){
		//验证token
		String uid = sessionTokenService.getStringForValue(attentionListRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//组装参数
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("page", attentionListRequest.getPage());
		paramMap.put("limit", Constant.PAGE_LIMIT);
		
		//查询观众信息
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		if (liverMap == null) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此直播观众用户信息");
		}
		paramMap.put("liverId", Integer.parseInt(liverMap.get("id").toString()));
		
		//结果集
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> resultList = new ArrayList<>();
		resultMap.put("attentionList", resultList);

		try {
			// 关注列表
			List<LiveRecordInfo> currentList = anchorLiveRecordDao.getLiveRecordsAttentionList(paramMap);
			
			if (currentList != null && currentList.size() > 0) {
				// 更新需要返回直播/回放的列表字段数据
				resultList = this.updateLiveRecordList(currentList);
				// 纬度
				Double latitude = attentionListRequest.getLatitude();
				// 经度
				Double longitude = attentionListRequest.getLongitude();
				if (latitude != 0 && longitude != 0) {
					// 获取正在直播的距离
					resultList = this.getDistance(latitude, longitude, resultList);
				}
				resultMap.put("attentionList", resultList);
			}

			// 响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取关注列表成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			return new BaseResponse(ResponseCode.FAILURE, "获取关注列表失败");
		}
	}

	/**
	 * 
	* @Title: queryAnchorAnnunciateList
	* @Description: 获取预告/通告/历史通告列表
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object queryAnchorAnnunciateList(AnchorAnnunciateListRequest anchorAnnunciateListRequest) {
		//获取uid
		String uid = sessionTokenService.getStringForValue(anchorAnnunciateListRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//组装参数
			MapResponse response = null;
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("page", anchorAnnunciateListRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			
			//查询直播观众用户信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播观众用户信息");
			}
			
			List<Map<Object, Object>> resultList = new ArrayList<>();
			if (anchorAnnunciateListRequest.getType() == 0) {
				paramMap.put("liveId", liverMap.get("id"));
				//预告列表
				paramMap.put("phone", liverMap.get("phone"));
				resultList = this.getAnnunciateList(paramMap);
				
				response = new MapResponse(ResponseCode.SUCCESS, "查询预告列表成功");
				Map<Object, Object> resultMap = new HashMap<>();
				resultMap.put("liveRecord", resultList);
				response.setResponse(resultMap);
				return response;
				
			}else {
				paramMap.put("anchorId", liverMap.get("id"));
				paramMap.put("type", anchorAnnunciateListRequest.getType());
				paramMap.put("nowDate", new Date());
				//通告列表/历史通告列表
				List<LiveRecordInfo> annunciateList = anchorLiveRecordDao.queryAnchorAnnunciateList(paramMap);
				
				//添加是否可以开播状态
				for (LiveRecordInfo liveRecordInfo : annunciateList) {
					//距离开播时间2小时毫秒数
					Long beginTime = Long.parseLong(propertiesUtil.getValue("begin_live_time", "conf_live.properties").toString());
					
					Long time = liveRecordInfo.getPlan_start_date().getTime() - new Date().getTime();
					if (time < beginTime) {
						//可开播状态
						liveRecordInfo.setIsStart(1);
					}else {
						//不可开播状态
						liveRecordInfo.setIsStart(0);
					}
				}
				
				resultList = updateAnnunciateList(annunciateList);
				
				//日志
				log.info("通告列表数据" + resultList.toString());
				
				//响应
				response = new MapResponse(ResponseCode.SUCCESS, "查询通告列表/历史通告列表成功");
				Map<Object, Object> resultMap = new HashMap<>();
				resultMap.put("annunciateList", resultList);
				response.setResponse(resultMap);
				return response;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
		}
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: getAnnunciateList
	* @Description: 获取预告
	* @return List<Object>    返回类型
	* @author
	* @throws
	 */
	public List<Map<Object, Object>> getAnnunciateList(Map<Object, Object> paramMap) throws IOException {
		//预告列表
		List<LiveRecordInfo> trailerList = anchorLiveRecordDao.getTrailerLiveRecordInfo(paramMap);
		//查看想看的观众信息
		List<Map<Object, Object>> viewerList = anchorLiveRecordDao.queryViewerLiveRecord(paramMap);
		
		//查看想看的观众头像信息
		List<Map<Object, Object>> avatarList = new ArrayList<>();
		//查看想看的机器头像信息
		List<Map<Object, Object>> robotAvatarList = null;
		//查看想看的人数
		List<Map<Object, Object>> viewCountList = null;
		
		if (viewerList != null && viewerList.size() > 0) {
			List<Map<Object, Object>> robotList = new ArrayList<>();
			List<Map<Object, Object>> paramList = new ArrayList<>();
			for (Map<Object, Object> map : viewerList) {
				if (Integer.parseInt(map.get("isRebot").toString()) == 1) {
					//查看机器人的头像信息
					robotList.add(map);
				}else {
					paramList.add(map);
				}
			}
			
			if (paramList != null && paramList.size() > 0) {
				//查看想看的观众头像信息
				avatarList = anchorLiveRecordDao.queryViewerAvatar(paramList);
			}
			
			if (robotList != null && robotList.size() > 0) {
				//查看想看的机器人头像信息
				robotAvatarList = liveRobotDao.queryRobotAvatar(robotList);
				for (Map<Object, Object> robotAvatarMap : robotAvatarList) {
					avatarList.add(robotAvatarMap);
				}
			}
			
			//查看想看的人数
			viewCountList= anchorLiveRecordDao.queryViewerCount();
		}
		
		//调整列表信息
		List<Map<Object, Object>> annunciateList = this.updateAnnunciateList(trailerList);
		
		//结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		if (annunciateList != null && annunciateList.size() > 0) {
			for (Map<Object, Object> map : annunciateList) {
				//用户头像信息
				map.put("viewerAvatar", "");
				//想看人数
				map.put("viewCount", 0);
				
				List<Map<Object, Object>> list = new ArrayList<>();
				//统计每条预告的想看观众头像的个数
				int count = 0;
				if (viewerList != null && viewerList.size() > 0) {
					for (Map<Object, Object> vMap : viewerList) {
						//查询想看预告的用户头像信息
						if (map.get("id").toString().equals(vMap.get("liveRecordId").toString())) {
							for (Map<Object, Object> avatarMap : avatarList) {
								if (avatarMap.get("id").toString().equals(vMap.get("liverId").toString())) {
									if (count < 20) {
										Map<Object, Object> viewerAvatarMap = new HashMap<>();
										viewerAvatarMap.put("avatar", StringUtils.isEmpty(avatarMap.get("avatar").toString())?"":fileUrl + avatarMap.get("avatar").toString());
										list.add(viewerAvatarMap);
										count++;
									}
								}
							}
						}
						
					}
				}
				
				if (list.size() <= 0) {
					try {
						String robotShowViewCount = propertiesUtil.getValue("robot_show_view_count", "conf_live.properties");
						List<Map<Object, Object>> robots = liveRobotService.insertRobotLiveFocusShow(map.get("anchorId").toString(), map.get("id").toString(), Integer.parseInt(robotShowViewCount) + new Random().nextInt(100));
						for (Map<Object, Object> robot : robots) {
							if (count < 20) {
								Map<Object, Object> robotAvatarMap = new HashMap<>();
								robotAvatarMap.put("avatar", StringUtils.isEmpty(robot.get("avatar").toString())?"":fileUrl + robot.get("avatar").toString());
								list.add(robotAvatarMap);
								count++;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				//想看用户头像信息
				map.put("viewerAvatar", list);
				
				if (viewCountList != null && viewCountList.size() > 0) {
					//想看人数
					for (Map<Object, Object> viewCountMap : viewCountList) {
						if (map.get("id").toString().equals(viewCountMap.get("liveRecordId").toString())) {
							map.put("viewCount", viewCountMap.get("viewCount"));
						}
					}
				}
				
				//关注状态:默认 0:没有关注  1:已经关注
				map.put("attentionStatus", 0);
				//观看状态:默认 1 可点击想看  2 不可点击想看
				map.put("viewStatus", 1);
				
				//判断是否登录过
				if (paramMap.get("liveId") != null) {
					//登录后，获取是否关注主播
					Map<Object, Object> focusAnchorMap = new HashMap<>();
					focusAnchorMap.put("liverStrId", paramMap.get("liveId").toString());
					focusAnchorMap.put("liverEndId", map.get("anchorId").toString());
					
					Integer focusAnchorResult = liveUserDao.queryFocusAnchor(focusAnchorMap);
					if (focusAnchorResult == 1) {
						//关注状态
						map.put("attentionStatus", 1);
					}
					
					//登录后，获取是否存在想看预告记录
					Map<Object, Object> focusShowMap = new HashMap<>();
					focusShowMap.put("liverId", paramMap.get("liveId").toString());
					focusShowMap.put("liveRecordId", map.get("id").toString());
					focusShowMap.put("status", 2);
					Integer focusShowResult = liveUserDao.queryFocusShow(focusShowMap);
					if (focusShowResult == 1) {
						//观看状态
						map.put("viewStatus", 2);
					}
				}
				
				resultList.add(map);
			}
		
		}
		
		return resultList;
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: updateAnnunciateList
	* @Description: 调整列表信息
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	public List<Map<Object, Object>> updateAnnunciateList(List<LiveRecordInfo> annunciateList) throws IOException {
		//预告结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		//存放店铺ids
		List<Integer> sellerIds = new ArrayList<>();
		//存放记录ids
		List<Integer> liveRecordIds = new ArrayList<>();
		if (annunciateList != null && annunciateList.size() > 0) {
			for (LiveRecordInfo liveRecordInfo : annunciateList) {
				Map<Object, Object> map = new HashMap<>();
				//直播记录id
				map.put("id", liveRecordInfo.getId());
				//存储直播记录id,用于查询粉丝卷信息
				liveRecordIds.add(liveRecordInfo.getId().intValue());
				//主播用户id
				map.put("anchorId", liveRecordInfo.getAnchor_id());
				//主播昵称
				map.put("nname", liveRecordInfo.getNname());
				//主播头像
				map.put("avatar", fileUrl + liveRecordInfo.getAvatar());
				//预告标题
				map.put("title", liveRecordInfo.getZhibo_title());
				//预告封面
				map.put("cover", fileUrl + liveRecordInfo.getZhibo_cover());
				//主播聊天室群组id
				map.put("groupId", propertiesUtil.getValue("groupPrefix", "conf_live.properties") + liveRecordInfo.getAnchor_room_no());
				//主播房间编号
				map.put("anchorRoomNo", liveRecordInfo.getAnchor_room_no());
				//直播开始时间
				map.put("planStartDate", DateUtil.format(liveRecordInfo.getPlan_start_date(), "yyyy-MM-dd HH:mm"));
				//商家id
				map.put("sellerId", liveRecordInfo.getSellerid());
				//存放店铺ids,用于查询商家logo
				if (null!=liveRecordInfo.getSellerid()) {
					sellerIds.add(liveRecordInfo.getSellerid().intValue());
				}
				//商家名称
				map.put("sellerName", liveRecordInfo.getSellername());
				//如果有商家别名,使用商家别名
				if (StringUtils.isNotEmpty(liveRecordInfo.getSeller_alias())) {
					map.put("sellerName", liveRecordInfo.getSeller_alias());
				}
				//商家所在的城市
				map.put("sellerCity", liveRecordInfo.getSeller_city());
				//直播地址
				map.put("address", liveRecordInfo.getZhibo_address());
				//直播类型
				map.put("zhiboType", liveRecordInfo.getZhibo_type());
				//开播状态
				map.put("isStart", liveRecordInfo.getIsStart());
				//预售状态 0 不显示  1显示
				map.put("isBooking", 0);
				//粉丝卷价格
				map.put("ticketMoney", "");
				
				resultList.add(map);
			}
			
			List<Map<Object, Object>> sellerLogos = new ArrayList<Map<Object, Object>>();
			//批量查询商家的logo图
			if (sellerIds.size()>0) {
				 sellerLogos = anchorLiveRecordDao.querySellerLogos(sellerIds);
			}
			
			//批量查询粉丝卷
			List<Map<Object, Object>> fansCoupons = anchorLiveRecordDao.queryFansCoupons(liveRecordIds);
			
			for (Map<Object, Object> resultMap : resultList) {
				//返回字段添加商家logo
				if (sellerLogos != null && sellerLogos.size() > 0) {
					for (Map<Object, Object> sellerLogoMap : sellerLogos) {
						
						String sellerLogoSellerId = sellerLogoMap.get("sellerId")==null?"0":sellerLogoMap.get("sellerId").toString();
						String resultSellerId = resultMap.get("sellerId")==null?"0":resultMap.get("sellerId").toString();
						if (Integer.parseInt(sellerLogoSellerId) == Integer.parseInt(resultSellerId)) {
							resultMap.put("sellerLogo", sellerLogoMap.get("sellerLogo")==null?"":fileUrl + sellerLogoMap.get("sellerLogo").toString());
						}
					}
				}
				
				//返回粉丝卷的预售状态跟价格
				if (fansCoupons != null && fansCoupons.size() > 0) {
					for (Map<Object, Object> fansCouponMap : fansCoupons) {
						if (Integer.parseInt(fansCouponMap.get("liveRecordId").toString()) == Integer.parseInt(resultMap.get("id").toString())) {
							resultMap.put("isBooking", 1);
							if (Integer.parseInt(fansCouponMap.get("stock").toString()) == 0) {
								resultMap.put("ticketMoney", "已售罄");
							}else {
								resultMap.put("ticketMoney", "¥" + fansCouponMap.get("ticketMoney")==null?0:fansCouponMap.get("ticketMoney").toString() + "/张");
							}
						}
					}
				}
			}
		}
		
		return resultList;
	}

	
	/**
	 * 
	* @方法名称: modiflyLiveRecordVedioUrl
	* @描述: 更新直播记录视频流地址
	* @返回类型 Object
	* @创建时间 2016年10月18日
	* @param recordId
	* @param videoUrl
	* @return
	 */
	 public Object modiflyLiveRecordVedioUrl(int recordId,String videoUrl,String channelId){
		 try {
			 Map<Object,Object> param=new HashMap<>();
			 param.put("recordId", recordId);
			 param.put("videoUrl", videoUrl);
			 param.put("channelId", channelId);
			 param.put("updateTime", DateUtil.format(new Date()));
			int modiyNum=anchorLiveRecordDao.modiflyLiveRecordVedioUrl(param);
			if(modiyNum>0){
				log.info("更新直播记录视频流地址成功：RecordId="+recordId+",视频推流地址：videoUrl="+videoUrl);
				return new BaseResponse(ResponseCode.SUCCESS, "更新直播记录视频流地址成功");
			}
			log.info("更新直播记录视频流地址失败：RecordId="+recordId+",视频推流地址：videoUrl="+videoUrl);
			return new BaseResponse(ResponseCode.FAILURE, "更新直播记录视频流地址失败");
		} catch (Exception e) {
			log.error("更新直播记录视频流地址异常：RecordId="+recordId+",视频推流地址：videoUrl="+videoUrl, e);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "更新直播记录视频流url异常，请联系管理员");
		}
	 }
	 
	 /**
		 * 
		* @Title: queryLiveRecordInfo
		* @Description: 获取直播记录详情
		* @return Object    返回类型
		* @throws
		 */
		public Object queryLiveRecordInfo(LiveShareRequest liveShareRequest) {
			try {
				//直播记录id
				Integer liveRecordId = liveShareRequest.getLiveRecordId();
				//组装参数
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("id", liveRecordId);
				LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
				if (liveRecordInfo == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录详情");
				}
				
				//结果集
				Map<Object, Object> resultMap = new HashMap<>();
				resultMap.put("view_count", liveRecordInfo.getView_count());
				resultMap.put("balanceEgg", liveRecordInfo.getIncome_egg_nums());
				resultMap.put("zhiboType", liveRecordInfo.getZhibo_type());
				
				Integer time = null;
				if (liveRecordInfo.getZhibo_type() == 5) {
					//计算时长
					Long startTime = null;
					Long endTime = null;
					try {
						startTime = liveRecordInfo.getStart_date().getTime();
						endTime = liveRecordInfo.getEnd_date().getTime();
					} catch (Exception e) {
						log.info("查询直播记录时长信息失败,直播记录id:" + liveRecordId +",开播时间:" + liveRecordInfo.getStart_date() + ",结束时间:" + liveRecordInfo.getEnd_date());
						startTime = 0L;
						endTime = 0L;
					}
					
					time = Math.round((endTime-startTime)/1000);
				}else {
					time = liveRecordInfo.getZhibo_duration();
				}
				
				//直播日期
				resultMap.put("liveEndDate", DateUtil.format(liveRecordInfo.getStart_date()==null?liveRecordInfo.getPlan_start_date():liveRecordInfo.getStart_date(), "yyyy-MM-dd"));
				
				//直播时长
				resultMap.put("liveTime", DateUtil.secToTime(time));

				//查询主播的信息
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoById(liveRecordInfo.getAnchor_id().intValue());
				
				//查询本场直播通告是否存在有未领取的红包
				Map<Object, Object> map = new HashMap<>();
				map.put("uid", liverMap.get("uid").toString());
				map.put("liveRecordId", liveRecordInfo.getId().toString());
				//查询该场直播,发送的所有红包总鸟蛋数，由于一条通告存在多次直播,取直播开播时间为点，来筛选红包记录
				map.put("startDate", liveRecordInfo.getStart_date()==null?liveRecordInfo.getPlan_start_date():liveRecordInfo.getStart_date());
				Integer sendAllLiveRedpacketAmount = liveRoomDao.queryLiveRedpacketByLiveRecordId(map);
				
				//查询历史红包退还金额
				Integer refundAmount = liveRoomDao.queryRefundLiveRedpacketAmount(map);
				
				resultMap.put("sendAllLiveRedpacketAmount", sendAllLiveRedpacketAmount==null?0:sendAllLiveRedpacketAmount);
				resultMap.put("refundAmount", refundAmount==null?0:refundAmount);
				
				//响应
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询直播记录详情成功");
				response.setResponse(resultMap);
				return response;
				
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "查询直播记录详情失败");
			}
		}
	 
	 /**
	  * 
	 * @方法名称: getliveRecordList
	 * @描述: 获取主播的直播记录和回放记录
	 * @返回类型 List<LiveRecordInfo>
	 * @创建时间 2016年10月19日
	 * @param page  页码
	 * @param anchorId 主播ID
	 * @param uid   登陆用户UID
	 * @return
	 * @throws Exception 
	  */
	 public List<LiveRecordInfo> getliveRecordList(Integer page,Integer anchorId,Integer uid) throws Exception{
		 	//直播及回放列表
			List<LiveRecordInfo> liveRecordList=null;
			try {
				//组装参数
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("page", page);
				paramMap.put("limit", Constant.PAGE_LIMIT);
				paramMap.put("anchorId", anchorId);
				//获取Uid  查询直播记录是否指定该用户观看
				
				log.info("获取到当前登录用户的UID:============="+uid);
				Map<Object, Object> LiverMap = liveUserDao.queryLiverInfoByUid(uid);
				if(LiverMap!=null && LiverMap.size()>0){
					log.info("获取到当前登录用户的手机号码:============="+LiverMap.get("phone"));
					paramMap.put("phone", LiverMap.get("phone"));
				}
				liveRecordList = anchorLiveRecordDao.queryOneLiveRecordInfo(paramMap);
			} catch (Exception e) {
				log.error("获取主播的直播记录和回放记录异常，请联系管理员,主播ID="+anchorId+",登陆用户UID="+uid);
				e.printStackTrace();
				throw new Exception("获取主播的直播记录和回放记录异常，请联系管理员");
			}
			return liveRecordList;
	 }
	 //直播轨迹
	 /**
	  * 
	 * @方法名称: LiveTrack
	 * @描述: 直播轨迹查询
	 * @返回类型 Object
	 * @创建时间 2016年10月21日
	 * @param page
	 * @param anchorId
	 * @param sessiontoken
	 * @return
	  */
	 public Object LiveTrack(Integer page,Integer anchorId,String sessiontoken){
		 	
		 	try {
		 		//获取uid
				String uid = sessionTokenService.getStringForValue(sessiontoken) + "";
				if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
					return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
				}
		 		Map<Object,Object> resultMap=new HashMap<>();
		 		
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("page", page);
				paramMap.put("limit", Constant.PAGE_LIMIT);
				//查询直播观众用户信息
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此直播观众用户信息");
				}
				int liverId=liverMap.get("id")==null?0:Integer.parseInt(liverMap.get("id").toString());
				paramMap.put("phone", liverMap.get("phone"));
				paramMap.put("anchorId", anchorId);
				//获取主播预告列表
				List<Map<Object, Object>> annunciateList= new ArrayList<>();
				List<LiveRecordInfo> trailerList = anchorLiveRecordDao.getTrailerLiveRecordInfo(paramMap);
				if(trailerList!=null && trailerList.size()>0){
					List<String> wantgos=new ArrayList<>();
					for(LiveRecordInfo liveRecordInfo:trailerList){
						wantgos.add(liveRecordInfo.getId()+"");//获取直播记录id
					}
					paramMap.put("wantgos", wantgos); 
					List<Map<Object,Object>> wantGoList=anchorLiveRecordDao.queryWantGoLiveRecord(paramMap);//查询想去列表
					List<Map<Object,Object>> avatarList=null;//想看用户列表
					//查看想看的人数
					List<Map<Object, Object>> viewCountList = null;
					if(wantGoList!=null && wantGoList.size()>0){
						//查看想看的观众头像信息
						avatarList = anchorLiveRecordDao.queryViewerAvatar(wantGoList);
						log.info("=========================================");
						log.info("未处理的观众头像信息:" + avatarList);	
						//查看想看的人数
						viewCountList= anchorLiveRecordDao.queryViewerCount();
					}
					
					
					
				//调整列表信息
				annunciateList = updateAnnunciateList(trailerList);
				annunciateList=this.getResultList(annunciateList, wantGoList, avatarList,viewCountList,1,liverId);
				}
				//预告信息列表
				resultMap.put("annunciateList", annunciateList);
				
				
				Map<Object, Object> hisparamMap = new HashMap<>();
				hisparamMap.put("page", page);
				hisparamMap.put("limit", Constant.PAGE_LIMIT);
				hisparamMap.put("anchorId", anchorId);
				hisparamMap.put("type", "2");
				//获取主播历史通告列表
				List<LiveRecordInfo> historyList = anchorLiveRecordDao.queryAnchorAnnunciateList(hisparamMap);
				
				
				List<Map<Object,Object>> historyAnnList= new ArrayList<>();
				if(historyList!=null&&historyList.size()>0){
					List<String> wantgos=new ArrayList<>();
					for(LiveRecordInfo liveRecordInfo:historyList){
						wantgos.add(liveRecordInfo.getId()+"");//获取直播记录id
					}
					paramMap.put("wantgos", wantgos); 
					List<Map<Object,Object>> wantGoList=anchorLiveRecordDao.queryWantGoLiveRecord(paramMap);//查询想去列表
					List<Map<Object,Object>> avatarList=null;//想看用户列表
					//查看想看的人数
					List<Map<Object, Object>> viewCountList = null;
					if(wantGoList!=null && wantGoList.size()>0){
						//查看想看的观众头像信息
						avatarList = anchorLiveRecordDao.queryViewerAvatar(wantGoList);
						log.info("=========================================");
						log.info("未处理的观众头像信息:" + avatarList);	
						//查看想看的人数
						viewCountList= anchorLiveRecordDao.queryViewerCount();
					}
					historyAnnList = updateAnnunciateList(historyList);
					historyAnnList=this.getResultList(historyAnnList, wantGoList, avatarList,viewCountList,2,liverId);
				}
				//历史记录信息列表
				resultMap.put("historyAnnList", historyAnnList);
				log.info("获取主播历史轨迹成功,主播ID="+anchorId+",用户uid="+uid);
				MapResponse reponse=new MapResponse(ResponseCode.SUCCESS, "获取主播历史轨迹成功");
				reponse.setResponse(resultMap);
				return reponse;
			} catch (NumberFormatException e) {
				log.error("获取历史轨迹异常，请联系管理员，主播ID="+anchorId);
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "获取历史轨迹异常，请联系管理员");
			} catch (IOException e) {
				log.error("获取历史轨迹异常，请联系管理员，主播ID="+anchorId);
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "获取历史轨迹异常，请联系管理员");
			}
	 }
	 
	/**
	 * 
	* @方法名称: getResultList
	* @描述: 获取预告列表及想看列表
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年10月20日
	* @param recordList直播记录列表
	* @param wantGoList想看列表
	* @param liverList想看客户列表
	* @param viewCountList 想去人数
	* @param type 操作类型，1：预告列表 2：历史记录
	* @param liverId 登陆客户的liverId
	
	* @return
	 */
	 public List<Map<Object,Object>> getResultList(List<Map<Object, Object>> recordList,List<Map<Object, Object>> wantGoList,List<Map<Object, Object>> liverList,List<Map<Object, Object>> viewCountList,int type,int liverId){
		 List<Map<Object,Object>> resultList=new  ArrayList<>();	
		 Integer isWant=1;//是否想看，2想看，1未点
		 if(recordList!=null && recordList.size()>0){
				for(Map<Object,Object> annunciateMap:recordList){
					//声明想看单条记录想看人员的列表
					//直播ID
					int liveRecordId=annunciateMap.get("id")==null?-1:Integer.parseInt(annunciateMap.get("id").toString());
					List<Map<Object,Object>> wantGoPerList=new ArrayList<>();
					if(wantGoList!=null && wantGoList.size()>0){
						//wantGoList所有想看记录集合
						for(Map<Object,Object> wtMap:wantGoList){
							//获取直播记录id
							int w_liveRecordId=wtMap.get("liveRecordId")==null?0:Integer.parseInt(wtMap.get("liveRecordId").toString());
							//获取想看的用户id
							int w_LiverId=wtMap.get("liverId")==null?0:Integer.parseInt(wtMap.get("liverId").toString());
							//判断直播预告中的直播记录id是否等于想看的直播记录id
							if(w_liveRecordId==liveRecordId){
								//liverList所有想看用户的集合
								if(liverList!=null && liverList.size()>0){
									for(Map<Object,Object> liverMap:liverList){
										//获取想看的用户id
										int l_LiverId=liverMap.get("id")==null?-1:Integer.parseInt(liverMap.get("id").toString());
										if(w_LiverId==l_LiverId){
											if(type==1&&liverId==l_LiverId){
												isWant=2;
											}
											Map<Object,Object> wgMap=new HashMap<>();
											wgMap.put("uid", liverMap.get("uid"));
											wgMap.put("avatar", StringUtils.isEmpty(liverMap.get("avatar").toString())?"":fileUrl + liverMap.get("avatar").toString());
											wantGoPerList.add(wgMap);
											break;
										}
									}
								}
							}
						}
						annunciateMap.put("wantCount", 0);
						//获取想去人数
						if(viewCountList!=null && viewCountList.size()>0){
							for(Map<Object,Object> wtCountMap:viewCountList){
								int w_c_recordId=wtCountMap.get("liveRecordId")==null?-1:Integer.parseInt(wtCountMap.get("liveRecordId").toString());
								if(w_c_recordId==liveRecordId){
									annunciateMap.put("wantCount", wtCountMap.get("viewCount"));
									break;
								}
							}
						}
							
						
						
					}
					annunciateMap.put("isWant", isWant);//是否想看
					annunciateMap.put("wantGoList", wantGoPerList);
					//将内容插入list
					resultList.add(annunciateMap);
				}
			}	 
			return resultList;
	 }
	 
	 /**
	  * 
	  * @Description: 根据记录ID查询商家信息
	  * @author xiaoxiong
	  * @date 2016年10月31日
	  */
	public Object showSellerinfo(IDRequest request) {
		try {
			Map<Object,Object> map=new HashMap<>();
			//返回参数
			Map<Object,Object> result=new HashMap<>();
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
			//查询通告记录信息
			//弹出框图片
			map.put("id", request.getId());
			LiveRecordInfo liveRecoreInfo=anchorLiveRecordDao.queryAnchorLiveRecordById(map);
			if(liveRecoreInfo==null){
				return new BaseResponse(ResponseCode.FAILURE,"没有找到直播信息~！"); 
			}
			Long sellerid =liveRecoreInfo.getSellerid();
			result.put("sellerid", ObjectUtils.toString(sellerid, ""));
			result.put("sellerType", 2);
			//如果是自定义通告（sellerid为空）
			if(sellerid==null){
				try {
					result.put("address",liveRecoreInfo.getZhibo_address());
					result.put("sellerName",liveRecoreInfo.getSellername());
					result.put("img",fileUrl+liveRecoreInfo.getZhibo_cover());
					mapResponse.setResponse(result);
					return mapResponse;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new BaseResponse(ResponseCode.FAILURE,"查询商家信息失败");
			}
			//查询商家信息
			Map<Object,Object> sellerMap=sellerInfoDao.querySellerInfoBySellerid(Integer.valueOf(sellerid+""));
			if(sellerMap==null){
				return new BaseResponse(ResponseCode.FAILURE,"没有找到商家信息~！"); 
			}
			//查询商家图片
			List<Map<Object,Object>> sellerPics=sellerInfoDao.querySellerPic(Integer.valueOf(sellerid+""));
			String img="";
			try {
				for(Map<Object,Object> pic : sellerPics){
					if(pic.get("islogo").toString().equals("2")){
						if(pic.get("url")!=null){
							img=fileUrl+pic.get("url");
							continue;
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			Integer sellerStatus = Integer.parseInt(sellerMap.get("status").toString());
			if (sellerStatus==3) {
				result.put("sellerType", 1);
			}
			result.put("typename", sellerMap.get("typename"));
			result.put("tradename", sellerMap.get("tradename"));
			result.put("address",sellerMap.get("address"));
			result.put("sellerName",sellerMap.get("sellername"));
			int zoneid=Integer.parseInt(sellerMap.get("zoneid").toString());
			Map<Object,Object> busMap=businessDao.selectBusinessByid(zoneid);
			String zoneName=busMap==null?"未知":busMap.get("title").toString();
			result.put("zoneName", zoneName);//商圈名称
			result.put("img",img);
			
			//省市区
			Map<Object,Object> areaMap=new HashMap<>();
			areaMap.put("areaid", sellerMap.get("provincenum"));
			areaMap.put("pid", 0);
			String province=workDao.queryAreaName(areaMap);
			areaMap.put("areaid", sellerMap.get("citynum"));
			areaMap.put("pid", sellerMap.get("provincenum"));
			String city=workDao.queryAreaName(areaMap);
			areaMap.put("areaid", sellerMap.get("areanum"));
			areaMap.put("pid", sellerMap.get("citynum"));
			String area=workDao.queryAreaName(areaMap);
			
			result.put("province",province==null?"":province);
			result.put("city",city==null?"":city);
			result.put("area",area==null?"":area);
			result.put("remarks",propertiesUtil.getValue("live_anchor_read_tips", "conf_live.properties"));
			//查询商家信息
			
			mapResponse.setResponse(result);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}
	
	/**
	 * 
	 * @Description: 查询商家回放记录
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public List<LiveRecordInfo> vodListBySellerid(SelleridPageRequest request) {
		try {
			Map<String,Object> params=new HashMap<>();
			params.put("page", request.getPage());
			params.put("pageSize", request.getPageSize());
			params.put("sellerid", request.getSellerid());
			return anchorLiveRecordDao.vodListBySellerid(params);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * @Description: 查询用户基本信息
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public UrsInfo queryUrsByUid(Long uid) {
		
		return ursInfoDao.queryUrsInfoByUid(Integer.parseInt(uid.toString()));
	}
	
	/**
	 * 
	 * @Description: 查询商家正在直播的列表
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public List<LiveRecordInfo> liveListBySellerid(SelleridRequest request) {
		
		return anchorLiveRecordDao.liveListBySellerid(request.getSellerid());
	}
	
	/**
	 * 根据商家ID查询和直播类型查询直播记录
	 * @author xiaoxiong
	 * @date 2016年11月24日
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 */
	public List<LiveRecordInfo> queryLiveRecordInfoBySellerIdAndType(Map<String,Object> params) {
		try {
			return anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询回放列表
	 * @author xiaoxiong
	 * @date 2016年12月1日
	 */
	public List<Map<String, Object>> vodList(Map<String, Object> params) {
		try {
			return anchorLiveRecordDao.vodList(params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用户结束直播观看
	 * @author xiaoxiong
	 * @date 2016年12月19日
	 */
	public Object userLiveOver(IDRequest request) {
		try {
			/*验证用户是否登入*/
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.TOKENERR, "token错误或已过期，请重新登入~！");
			}
			
			/*获取用户观看直播送出鸟豆和返现信息请求参数*/
			LiverRoomRequest liverRoomRequest =new LiverRoomRequest();
			liverRoomRequest.setSessiontoken(request.getSessiontoken());
			liverRoomRequest.setUid(Integer.valueOf(uid));
			MapResponse liveRebate = (MapResponse) liveAnchorRoomService.queryLiveRebate(liverRoomRequest);
			
			/*返回参数*/
			ObjResponse mapResponse = new ObjResponse(ResponseCode.SUCCESS, "成功");
			UserLiveOverResponse response = new UserLiveOverResponse();
			
			/*获取信息成功*/
			if(liveRebate!=null&&liveRebate.getState()==100)
			{
				Map<Object,Object> rebateMap = liveRebate.getResponse();
				if(rebateMap!=null)
				{	/*赠送总额*/
					response.setLiveBeanMoney(rebateMap.get("liveBeanMoney")==null?"":rebateMap.get("liveBeanMoney")+"");
					/*返现总额*/
					response.setRebateMoney(rebateMap.get("rebateMoney ")==null?"":rebateMap.get("rebateMoney ")+"");
				}
			}
			
			/*查询直播记录信息*/
			LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordById(request.getId());
			if(liveRecordInfo!=null)
			{	
				/*主播头像*/
				response.setAvatar(fileUrl+liveRecordInfo.getAvatar());
				/*主播名称*/
				response.setNname(liveRecordInfo.getNname());
				/*观看人数*/
				response.setViewNum(liveRecordInfo.getView_count());
				
				/*查询主播是否有其他粉丝卷*/
				Map<Object,Object> params = new HashMap<>();
				params.put("anchorId", liveRecordInfo.getAnchor_id());
				params.put("page", 1);
				params.put("limit", 1);
				List<Map<Object,Object>> list = anchorLiveRecordDao.queryLiveRecordFansCouponList(params);
				if(list!=null&&list.size()>0){
					response.setIsOtherCoupon(1);
				}
			}
			mapResponse.setResponse(response);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE ,"获取用户观看直播信息失败~！");
	}
	

	
}
