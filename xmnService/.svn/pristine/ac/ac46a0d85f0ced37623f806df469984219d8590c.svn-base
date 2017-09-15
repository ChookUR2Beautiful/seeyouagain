package com.xmniao.xmn.core.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.common.request.live.LiverRoomRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiveViewRecordInfo;
import com.xmniao.xmn.core.live.service.AnchorViewerMemberRankService;
import com.xmniao.xmn.core.live.service.LiveAnchorRoomService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
*      
* 类名称：ClearViewDurationDay   
* 类描述：   清空当日累计观看时常
* 创建人：xiaoxiong   
* 创建时间：2016年8月25日 下午3:05:19   
* 修改人：xiaoxiong   
* 修改时间：2016年8月25日 下午3:05:19   
* 修改备注：   
* @version    
*
 */
@Service
public class ClearViewDurationDay {
	
		/**
		 * 日志
		 */
		private final Logger log = Logger.getLogger(ClearViewDurationDay.class);
		
		
		@Autowired
		private StringRedisTemplate stringRedisTemplate;
		
		@Autowired
		private LiveUserDao liveUserDao;
		
		@Autowired
		private AnchorViewerMemberRankService anchorViewerMemberRankService;
		
		
		@Autowired
		private AnchorLiveRecordDao anchorLiveRecordDao;
		
		@Autowired
		private LiveAnchorRoomService liveAnchorRoomService;
	
		/**
		 * 清空当天的 直播用户 和观众的 时长记录
		 * 每日 0 点 执行操作
		 * */
		public void clearViewDurationDay(){
			
			//保证只有一个定时任务执行
			//采用redis incr函数初始化值 保证定时任务只有一个执行
			String live_quzt_redis = "live_clear_expr_key";
			
			Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
			log.info("跨天直播定时任务，已累计定时任务数量："+resultNum);
			if (resultNum>1) {
				log.info("已有定时任务执行操作,其他定时任务强制退出："+live_quzt_redis+":"+resultNum);
				//超过一定次数,删除定时任务redisKey,恢复定时任务
				if (resultNum > 30) {
					//执行删除redis key操作
					stringRedisTemplate.delete(live_quzt_redis);
				}
				return ;
			}
			
	        long endtime=System.currentTimeMillis();
			try {
				//查询未结束直播记录
				List<LiveRecordInfo> recordList= liveUserDao.queryLiveRecord();
				if(recordList!=null&&recordList.size()>0){
					for(LiveRecordInfo recordInfo :recordList){
						try {
							addAnchorLiveExperience(recordInfo,endtime);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("对未结束观看记录用户操作失败："+recordInfo.toString());
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.info("清空当天观看总时长和直播时长失败~!");
			}finally{
				//删除redis  key 
				stringRedisTemplate.delete(live_quzt_redis);
			}
		}
		
		
		/**
		 * 定时统计任务对主播观看时长和经验进行修改
		 * @author xiaoxiong
		 * @date 2016年8月25日
		 * @version
		 */
		public void addAnchorLiveExperience(LiveRecordInfo liveRecordInfo,long endtime) throws Exception{

			String aid=liveRecordInfo.getAnchor_id().toString();//用户ID
			Map<Object,Object> liveMap=null;
			try {
				 liveMap=liveUserDao.queryLiverInfoById(Integer.valueOf(aid));
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询主播信息失败！");
			}
			if(liveMap==null||liveMap.get("uid")==null){
				return ;
			}
			//查询reids缓存用户信息
		    String liver_rediskey = "liver_"+Long.valueOf(liveMap.get("uid")+"");
			Map<Object, Object>  liverMap_redis= stringRedisTemplate.opsForHash().entries(liver_rediskey);
			if(liverMap_redis.isEmpty()){
				return;
			}
			try {
				//累计主播的经验值 
				liverMap_redis.put("live_record_id", liveRecordInfo.getId());
				liverMap_redis.put("get_experience_type", 5);
				anchorViewerMemberRankService.addAnchorLiveExperience(liverMap_redis, liveRecordInfo, liver_rediskey,1);
				
				//修改直播记录为结束直播
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("id", liveRecordInfo.getId());
				paramMap.put("utype", "1");//用户类型
				paramMap.put("roomType", "1");//标示是退出 还是 开始
				paramMap.put("endTime", DateUtil.format(new Date()));
				//修改直播记录为 结束
				anchorLiveRecordDao.editAnchorLiveRecordStatus(paramMap);
				
			} catch (Exception e) {
				log.info("累计主播直播经验失败：主播ID = "+liveRecordInfo.getAnchor_id());
				e.printStackTrace();
			}
			
			//根据相应的直播记录查询出 正在观看的用户
			Map<Object, Object> viewMap = new HashMap<Object, Object>();
			viewMap.put("live_record_id", liveRecordInfo.getId());
			viewMap.put("anchor_id", liveRecordInfo.getAnchor_id());
			List<LiveViewRecordInfo> viewRecordInfos = anchorLiveRecordDao.queryLiverViewRecordByLiveRecordId(viewMap);
			if (viewRecordInfos.size()>0) {
				for (int i = 0; i < viewRecordInfos.size(); i++) {
					LiveViewRecordInfo viewRecordInfo = viewRecordInfos.get(i);
					String liver_id = viewRecordInfo.getLiver_id().toString();// liver表ID
					Map<Object, Object> viewerInfoMap = new HashMap<Object, Object>();
					try {
						//获取基本信息  获取UID
						viewerInfoMap = liveUserDao.queryLiverInfoById(Integer.valueOf(liver_id));
					} catch (Exception e) {
						e.printStackTrace();
						log.info("查询主播信息失败！");
					}
					
					if(viewerInfoMap==null||viewerInfoMap.get("uid")==null){
						return ;
					}
					//查询reids缓存用户信息
				    String viewer_rediskey = "liver_"+Long.valueOf(viewerInfoMap.get("uid")+"");
					Map<Object, Object>  viewerMap_redis= stringRedisTemplate.opsForHash().entries(viewer_rediskey);
					if(viewerMap_redis.isEmpty()){
						return;
					}
					try {
						LiverRoomRequest request = new LiverRoomRequest();
						request.setZhiboRecordId(viewRecordInfo.getLive_record_id().intValue());
						
						viewerMap_redis.put("live_record_id", liveRecordInfo.getId());
						viewerMap_redis.put("get_experience_type", 5);
						Map<Object, Object> resultMap = anchorViewerMemberRankService.addViewerOrAnchorExperience(viewerMap_redis, request, viewer_rediskey,1);
						
						//修改观众观看记录 
						Map<Object, Object> paramMap = new HashMap<Object, Object>();
						paramMap.put("endTime", DateUtil.format(new Date()));
						paramMap.put("updateTime", DateUtil.format(new Date()));
						paramMap.put("liver_id", resultMap.get("id"));
						paramMap.put("id", viewRecordInfo.getLive_record_id());
						paramMap.put("view_duration", resultMap.get("viewTime")==null?"0":resultMap.get("viewTime").toString());
						anchorLiveRecordDao.editLiveViewRecordStatus(paramMap);
						
					} catch (Exception e) {
						log.info("执行定时任务累计观众经验异常，观众 = "+liveMap.get("id"));
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		/**
		 * 添加用户经验和观看时长
		 * @Description: 
		 * @author xiaoxiong
		 * @date 2016年8月25日
		 * @version
		 */
		public void addUserLiveExperience(LiveViewRecordInfo viewRecordInfo,long endtime) throws Exception{
			
			String aid=viewRecordInfo.getLiver_id().toString();// liver表ID
			Map<Object,Object> liveMap=null;
			try {
				 liveMap=liveUserDao.queryLiverInfoById(Integer.valueOf(aid));
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询主播信息失败！");
			}
			if(liveMap==null||liveMap.get("uid")==null){
				return ;
			}
			//查询reids缓存用户信息
		    String liver_rediskey = "liver_"+Long.valueOf(liveMap.get("uid")+"");
			Map<Object, Object>  liverMap= stringRedisTemplate.opsForHash().entries(liver_rediskey);
			if(liverMap.isEmpty()){
				return;
			}
			try {
				LiverRoomRequest request = new LiverRoomRequest();
				request.setZhiboRecordId(Integer.valueOf(liveMap.get("live_record_id").toString()));
				Map<Object, Object> resultMap = anchorViewerMemberRankService.addViewerOrAnchorExperience(liverMap, request, liver_rediskey,1);
				//如果
				//修改观众观看记录为“已退出”
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("endTime", DateUtil.format(new Date()));
				paramMap.put("updateTime", DateUtil.format(new Date()));
				paramMap.put("liver_id", liverMap.get("id").toString());
				paramMap.put("id", viewRecordInfo.getLive_record_id());
				paramMap.put("view_duration", resultMap.get("viewTime")==null?"0":resultMap.get("viewTime").toString());
				anchorLiveRecordDao.editLiveViewRecordStatus(paramMap);
				
			} catch (Exception e) {
				log.info("执行定时任务累计主播经验异常，观众 = "+liveMap.get("id"));
				e.printStackTrace();
			}
			
		}
		
		public void updateLiver(Map<String,Object> map){
			//修改观看记录
			try {
				map.put("live_duration", 0);
				liveUserDao.clearViewDurationDay(map);
				
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
}
