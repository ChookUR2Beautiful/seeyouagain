/**
 * 2016年10月11日 上午10:13:23
 */
package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xmniao.xmn.core.common.request.live.LiveShareVideoRequest;
import com.xmniao.xmn.core.live.dao.LiveAnchorVideoDao;
import com.xmniao.xmn.core.util.RedisGlobalLockUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareInitRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.SendBarrageDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * @项目名称：xmnService
 * @类名称：LiveShareInitService
 * @类描述：直播分享内容service
 * @创建人： yeyu
 * @创建时间 2016年10月11日 上午10:13:23
 * @version
 */
@Service
public class LiveShareInitService {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveShareInitService.class);
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 主播|观众通用dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 直播记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil  propertiesUtil;
	
	/**
	 * 注入弹幕Dao
	 */
	@Autowired
	private SendBarrageDao sendBarrageDao;
	
	
	@Autowired
	private PersonalCenterService personalcenterService;
	
	/**
	 * 注入本地服务地址
	 */
	@Autowired
	private String localDomain;
	@Autowired
	private LiveAnchorVideoDao liveAnchorVideoDao;
	@Autowired
	private LiveWonderfulVideoService liveWonderfulVideoService;

	/**
	 * 
	* @方法名称: queryLiveShareInit
	* @描述: 直播分享初始化
	* @返回类型 Object
	* @创建时间 2016年10月11日
	* @param liveShareInitRequest
	* @return
	 */
	public MapResponse queryLiveShareInit(LiveShareInitRequest liveShareInitRequest){
		try{
			//组装参数
			Map<Object, Object> paramMap =  new HashMap<Object, Object>();
			
			//查询直播记录信息
			paramMap.put("id", liveShareInitRequest.getZhiboRecordId());
			LiveRecordInfo recordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
			if (recordInfo == null) {
				return new MapResponse(ResponseCode.FAILURE, "进入房间失败,查无此直播记录信息");
			}
			
			//首先验证其是否在直播的观看范围内  如果为传入信息
			double sellerLongitude=0D;
			double sellerLatitude=0D;
				String live_seller_address_key = "live_seller_distance_"+recordInfo.getSellerid();
				if (stringRedisTemplate.hasKey(live_seller_address_key)) {
					Map<Object, Object> sellerDistanceMap =stringRedisTemplate.opsForHash().entries(live_seller_address_key);
					 sellerLongitude = Double.parseDouble(sellerDistanceMap.get("longitude")==null?"0":sellerDistanceMap.get("longitude").toString()) ;
					 sellerLatitude = Double.parseDouble(sellerDistanceMap.get("latitude")==null?"0":sellerDistanceMap.get("latitude").toString());
				}
			
			//返回主播基本信息 
			Map<Object, Object> anchorInfo = liveUserDao.queryLiverInfoById(recordInfo.getAnchor_id().intValue());
			if (anchorInfo == null) {
				log.info("观看分享房间失败,未获取到主播信息:直播ID="+liveShareInitRequest.getZhiboRecordId());
				return new MapResponse(ResponseCode.FAILURE, "观看分享房间失败,未获取到主播信息");
			}
			anchorInfo.put("nname", anchorInfo.get("nname"));//直播记录ID
			anchorInfo.put("zhiboRecordId", liveShareInitRequest.getZhiboRecordId());//直播记录ID
			anchorInfo.put("anchorid", anchorInfo.get("id").toString());//主播ID
			anchorInfo.put("avatar", fileUrl+anchorInfo.get("avatar"));//头像
			String sellername = recordInfo.getSellername();//直播商家名称
			if (sellername.length() > 6) {
				sellername = sellername.substring(0, 5) + "...";
			}
			anchorInfo.put("sellername", sellername);
			anchorInfo.put("sellerid", recordInfo.getSellerid());//直播商家ID
			anchorInfo.put("view_count", recordInfo.getView_count());//当前观看人数
			anchorInfo.put("zhiboTitle", recordInfo.getZhibo_title());//直播标题
			anchorInfo.put("ZBPhone", anchorInfo.get("phone"));//直播电话号
			anchorInfo.put("liveTips", propertiesUtil.getValue("live_tips", "conf_live.properties"));//直播提示信息
			anchorInfo.put("anchor_room_no", anchorInfo.get("anchor_room_no"));//房间编号
			anchorInfo.put("barrageUrl", localDomain +"/live/getBarrage?zhiboRecordId="+liveShareInitRequest.getZhiboRecordId() );//获取弹幕消息
			Integer zhiboType=recordInfo.getZhibo_type();
			if( null!=zhiboType&& 3==zhiboType ){
				anchorInfo.put("vedioUrl", recordInfo.getZhibo_playback_url());//视频流地址
			}else{
				anchorInfo.put("vedioUrl", recordInfo.getVedio_url());//视频流地址
			}
			
			anchorInfo.put("liveCoverUrl", fileUrl+recordInfo.getZhibo_cover());
			anchorInfo.put("long1", sellerLongitude);
			anchorInfo.put("lat1", sellerLatitude);
//			anchorInfo.put("locationUrl", localDomain+"/live/shareInit?zhiboRecordId="+liveShareInitRequest.getZhiboRecordId());//获取本页地址
			anchorInfo.put("barrageUrl", "http://localhost:8080/xmnService/live/getBarrage?zhiboRecordId="+liveShareInitRequest.getZhiboRecordId() );//获取弹幕消息
			anchorInfo.put("zhiboType", recordInfo.getZhibo_type());
			
			//预售信息
			if (recordInfo.getZhibo_type() == 1 && recordInfo.getTicketMoney() != null && recordInfo.getTicketMoney() != 0) {
				//是否存在直播预售 0 无  1有
				anchorInfo.put("isSell", 1);
				//预售标题
				anchorInfo.put("title", recordInfo.getPreSellTitle());
				//预售库存
				anchorInfo.put("preSellStock", recordInfo.getPreSellStock());
				//预售价格
				anchorInfo.put("ticketMoney", "¥" + recordInfo.getTicketMoney());
				
			}else {
				anchorInfo.put("isSell", 0);
			}
			
			//获取分享的推荐直播列表
/*			List<Map<Object, Object>> referList = this.getReferList();
			anchorInfo.put("referList", referList);*/
			
			log.info("==============================================");
			log.info("进入直播分享初始化成功,返回主播基本信息:" + anchorInfo.toString());
			MapResponse response=new MapResponse(ResponseCode.SUCCESS, "进入直播分享初始化成功");
			response.setResponse(anchorInfo);
			return response;
		}catch(Exception e){
			log.error("直播分享查看直播失败");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "查看直播分享初始化异常");
			
		}
	}


	/**
	 * 精彩时刻视频分享
	 * @param liveShareVideoRequest
	 * @return
	 */
	public MapResponse queryLiveShareVideoInit(LiveShareVideoRequest liveShareVideoRequest) {
		try{
			Integer id = liveShareVideoRequest.getId();
			// 查询精彩视频记录
			Map<Object, Object> videoMap = liveAnchorVideoDao.getAnchorVideoById(id);
			if (videoMap == null) {
				return new MapResponse(ResponseCode.FAILURE, "观看分享房间失败,未获取到分享记录");
			}
			//返回主播基本信息
			Map<Object, Object> anchorInfo = liveUserDao.queryLiverInfoById((Integer) videoMap.get("anchor_id"));
			if (anchorInfo == null) {
				log.info("观看分享房间失败,未获取到主播信息:精彩时刻视频ID=" + String.valueOf(id));
				return new MapResponse(ResponseCode.FAILURE, "观看分享房间失败,未获取到主播信息");
			}
			//组装参数

			anchorInfo.put("nname", anchorInfo.get("nname"));//直播记录ID
			anchorInfo.put("anchorid", anchorInfo.get("id").toString());//主播ID
			anchorInfo.put("avatar", fileUrl+anchorInfo.get("avatar"));//头像
			String sellername = "";  //直播商家名称
			if (sellername.length() > 6) {
				sellername = sellername.substring(0, 5) + "...";
			}
			anchorInfo.put("sellername", sellername);
			anchorInfo.put("view_count", videoMap.get("view_count"));//当前观看人数
			anchorInfo.put("zhiboTitle", videoMap.get("title"));//直播标题
			anchorInfo.put("ZBPhone", anchorInfo.get("phone"));//直播电话号
			anchorInfo.put("liveTips", propertiesUtil.getValue("live_tips", "conf_live.properties"));//直播提示信息
			anchorInfo.put("anchor_room_no", anchorInfo.get("anchor_room_no"));//房间编号
//			anchorInfo.put("barrageUrl", localDomain +"/live/getBarrage?zhiboRecordId="+liveShareInitRequest.getZhiboRecordId() );//获取弹幕消息
			anchorInfo.put("vedioUrl", videoMap.get("video_url"));//视频流地址
			anchorInfo.put("liveCoverUrl", fileUrl + videoMap.get("cover_url"));
			anchorInfo.put("long1", liveShareVideoRequest.getLongitude());
			anchorInfo.put("lat1", liveShareVideoRequest.getLatitude());
			anchorInfo.put("locationUrl", localDomain+"/live/shareVideoInit?id=" + id);//获取本页地址
//			anchorInfo.put("barrageUrl", "http://localhost:8080/xmnService/live/getBarrage?zhiboRecordId="+liveShareInitRequest.getZhiboRecordId() );//获取弹幕消息
			anchorInfo.put("zhiboType", 3);
			//预售信息不显示
			anchorInfo.put("isSell", 0);

//			更新观看次数
			liveWonderfulVideoService.updateViewCount(id);

			log.info("==============================================");
			log.info("进入直播分享初始化成功,返回主播基本信息:" + anchorInfo.toString());
			MapResponse response=new MapResponse(ResponseCode.SUCCESS, "进入直播分享初始化成功");
			response.setResponse(anchorInfo);
			return response;
		}catch(Exception e){
			log.error("直播分享查看直播失败");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "查看直播分享初始化异常");

		}
	}
	/**
	 * 
	* @Title: getReferList
	* @Description: 获取推荐列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public Object getReferList() {
		try {
			//推荐列表
			List<Map<Object, Object>> referList = new ArrayList<>();
			
			//获取预告列表
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("page", 1);
			paramMap.put("limit", 4);
			//查询最多4条预告列表
			List<LiveRecordInfo> trailerList = anchorLiveRecordDao.getTrailerLiveRecordInfo(paramMap);
			
			//判空
			if (trailerList != null && trailerList.size() > 0) {
				//存放记录ids
				List<Integer> liveRecordIds = new ArrayList<>();
				for (LiveRecordInfo liveRecord : trailerList) {
					liveRecordIds.add(liveRecord.getId().intValue());
				}
				
/*			//批量查询粉丝卷
				List<Map<Object, Object>> fansCoupons = anchorLiveRecordDao.queryFansCoupons(liveRecordIds);*/
				
				//组装返回结果集
				for (LiveRecordInfo liveRecord : trailerList) {
					Map<Object, Object> liveRecordMap = new HashMap<>();
					//主播id
					liveRecordMap.put("anchorId", liveRecord.getAnchor_id().intValue());
					//直播记录id
					liveRecordMap.put("zhiboRecordId", liveRecord.getId());
					//直播类型标示
					liveRecordMap.put("liveTypeMark", DateUtil.format(liveRecord.getPlan_start_date(), "yyyy.MM.dd HH:mm "));
					//主播昵称
					liveRecordMap.put("anchorNname", liveRecord.getNname());
					//预告封面
					liveRecordMap.put("cover", fileUrl + liveRecord.getZhibo_cover());
					//直播记录类型
					liveRecordMap.put("zhiboType", liveRecord.getZhibo_type());
					//预售详情H5的URl
					String annunciateInfoUrl = propertiesUtil.getValue("annunciate_info_url", "conf_live.properties");
					//直播记录类型
					liveRecordMap.put("annunciateInfoUrl", annunciateInfoUrl);
					/*
					//是否预售中 0否 1是
					liveRecordMap.put("isBooking", 0);
					//预售价格
					liveRecordMap.put("ticketMoney", 0);
					
					//返回粉丝卷的预售状态跟价格
					if (fansCoupons != null && fansCoupons.size() > 0) {
						for (Map<Object, Object> fansCouponMap : fansCoupons) {
							if (Integer.parseInt(fansCouponMap.get("liveRecordId").toString()) == liveRecord.getId().intValue()) {
								liveRecordMap.put("isBooking", 1);
								liveRecordMap.put("ticketMoney", "¥" + fansCouponMap.get("ticketMoney").toString() + "/张");
							}
							
						}
					}
					*/
					
					//存入返回结果集中
					referList.add(liveRecordMap);
				}
				
			}
			
			//获取直播列表
			List<LiveRecordInfo> liveRecordList = anchorLiveRecordDao.queryOnlineCurrentLiveRecord();
			if (liveRecordList != null && liveRecordList.size() > 0) {
				for (LiveRecordInfo liveRecord : liveRecordList) {
					Map<Object, Object> liveRecordMap = new HashMap<>();
					//主播id
					liveRecordMap.put("anchorId", liveRecord.getAnchor_id().intValue());
					//直播记录id
					liveRecordMap.put("zhiboRecordId", liveRecord.getId());
					//直播类型标示
					liveRecordMap.put("liveTypeMark", "正在直播");
					//主播昵称
					liveRecordMap.put("anchorNname", liveRecord.getNname());
					//预告封面
					liveRecordMap.put("cover", fileUrl + liveRecord.getZhibo_cover());
					//直播记录类型
					liveRecordMap.put("zhiboType", liveRecord.getZhibo_type());
					//存入返回结果集中
					referList.add(liveRecordMap);
					if (referList.size() >= 8 ) {
						break;
					}
				}
			}
			
			//预告跟正在直播的记录数
			int size = referList.size();
			if (size < 8) {
				paramMap.clear();
				paramMap.put("page", 1);
				paramMap.put("limit", 8-size);
				List<LiveRecordInfo> playbackLiveRecord = anchorLiveRecordDao.queryPlaybackLiveRecord(paramMap);
				if (playbackLiveRecord != null && playbackLiveRecord.size() > 0) {
					for (LiveRecordInfo liveRecord : playbackLiveRecord) {
						Map<Object, Object> liveRecordMap = new HashMap<>();
						//主播id
						liveRecordMap.put("anchorId", liveRecord.getAnchor_id().intValue());
						//直播记录id
						liveRecordMap.put("zhiboRecordId", liveRecord.getId());
						//直播类型标示
						liveRecordMap.put("liveTypeMark", "回放");
						//主播昵称
						liveRecordMap.put("anchorNname", liveRecord.getNname());
						//预告封面
						liveRecordMap.put("cover", fileUrl + liveRecord.getZhibo_cover());
						//直播记录类型
						liveRecordMap.put("zhiboType", liveRecord.getZhibo_type());
						//存入返回结果集中
						referList.add(liveRecordMap);
					}
				}
			}
			
			//查询主播的性别
			for (Map<Object, Object> referMap : referList) {
				//查询主播信息
				Map<Object, Object> anchorMap = liveUserDao.queryLiverInfoById(Integer.parseInt(referMap.get("anchorId").toString()));
				referMap.put("sex", anchorMap.get("sex")==null?0:anchorMap.get("sex"));
			}
			
			//响应
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("referList", referList);
			MapResponse response=new MapResponse(ResponseCode.SUCCESS, "获取直播分享推荐列表成功");
			response.setResponse(resultMap);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取直播分享推荐列表失败");
		}
	}
	
	/**
	 * 
	* @方法名称: queryCommonMessage
	* @描述: 获取普通消息内容
	* @返回类型 Object
	* @创建时间 2016年10月11日
	* @param live_record_id
	* @return
	 */
	public Object queryCommonMessage(int live_record_id){
		Map<Object,Object> resultMap=new HashMap<Object, Object>();
		List<Map<Object, Object>> resultList=null;
		try {
			resultList = this.queryCommonMessageList(live_record_id);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return resultList;
//			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}
		resultMap.put("barrageList", resultList);
//		MapResponse response=new MapResponse(ResponseCode.SUCCESS, "获取弹幕信息成功");
//		response.setResponse(resultMap);
		return resultList;
	}
	
	
	/**
	 * 
	* @方法名称: queryBarrageContext
	* @描述: 获取群聊消息的基本信息及内容
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年10月11日
	* @param live_record_id
	* @return
	* @throws Exception
	 */
	public List<Map<Object,Object>> queryCommonMessageList(int live_record_id) throws Exception{
		List<Map<Object,Object>> resultList=new ArrayList<>();
		List<Map<Object,Object>> barrageList=null;
		try{
		//获取群聊消息内容
		Map<Object,Object> param=new HashMap<>();
		param.put("live_record_id", live_record_id);
	
		/*int minute=100000;
		for(int i=1;i<=2;i++){
			param.put("minute", minute*i);
			barrageList=this.queryBarrageByTime(param);
			if(barrageList!=null && barrageList.size()>0){
				break;
			}
		}*/
		barrageList=this.queryCommonMsgByRecordId(param);
		if(barrageList==null || barrageList.size()<=0){
				return null;
		}
		List<String> ids=new ArrayList<>();
		//获取发送消息的直播客户ID集合
		for(Map<Object,Object> barrgeMap:barrageList){
			String send_liver_id=barrgeMap.get("send_liver_id")+"";
			ids.add(send_liver_id);
		}
		
		//根据主播id查询用户信息
		List<Map<Object,Object>> personList=personalcenterService.queryLiverPersonByListId(ids);
		if(personList==null || personList.size()<=0){
			return null;
		}
		
		//获取直播记录中弹幕消息及发送者基本信息
		for(int i=barrageList.size();i>0;i--){
			Map<Object,Object> barrgeMap=barrageList.get(i-1);
//			Map<Object,Object> resultMap=new HashMap<>();
			int send_liver_id=barrgeMap.get("send_liver_id")==null?0:Integer.parseInt(barrgeMap.get("send_liver_id").toString());
//			resultMap.put("messager_txt", barrgeMap.get("messager_txt"));
			for(Map<Object,Object> personMap:personList){
				int liver_id=personMap.get("anchorid")==null?-1:Integer.parseInt(personMap.get("anchorid").toString());
				if(send_liver_id==liver_id){
					int rank_no=personMap.get("rank_no")==null?1:Integer.parseInt(personMap.get("rank_no").toString());
					barrgeMap.put("rank_no", rank_no);
					barrgeMap.put("nname", personMap.get("nname"));
					resultList.add(barrgeMap);
					break;
				}
			}
			
		}

		}catch(Exception e){
			log.error("获取群聊信息内容失败");
			e.printStackTrace();
			throw new Exception("获取群聊信息内容异常");
		
		}
		return resultList;
	}
	
	
	/**
	 * 
	* @方法名称: queryBarrageByTime
	* @描述: 获取最近5分钟内的到普通消息
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年10月11日
	* @param live_record_id
	* @return
	* @throws Exception
	 */
	public List<Map<Object,Object>> queryCommonMsgByRecordId(Map<Object,Object> param) throws Exception{
		List<Map<Object,Object>> resultList=null;
		try{
			resultList=sendBarrageDao.queryCommonMsgByRecordId(param);
		}catch(Exception e){
			log.error("获取普通消息异常，直播ID:"+param.toString());
			e.printStackTrace();
			throw new Exception("获取普通消息异常，直播ID:"+param.toString());
		}
		return resultList;
	}
}
