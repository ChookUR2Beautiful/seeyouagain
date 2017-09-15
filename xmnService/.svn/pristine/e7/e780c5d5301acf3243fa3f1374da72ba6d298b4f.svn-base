/**
 * 2016年9月20日 下午8:37:15
 */
package com.xmniao.xmn.core.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * @项目名称：xmnService
 * @类名称：WatchLivePersonQuertz
 * @类描述：获取当前观看直播记录人数
 * @创建人： yeyu
 * @创建时间 2016年9月20日 下午8:37:15
 * @version
 */
@Service
public class CountViewerCountQuertz {
	
	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(CountViewerCountQuertz.class);
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private PersonalCenterService personalCenterService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 
	* @方法名称: countViewerCount
	* @描述: 查询当前直播用户中的直播人数
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年9月20日
	* @return
	 */
	public void countViewerCount(){
		
		//保证只有一个定时任务执行
		String live_quzt_redis = "live_viewCount_key";
		//采用redis incr函数初始化值 保证定时任务只有一个执行	
		Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
		log.info("统计正在直播的直播间的观看人数，累计定时任务数："+resultNum);
		if (resultNum>1) {
			log.info("已有定时任务执行操作,其他定时任务强制退出："+live_quzt_redis+":"+resultNum);
			//超过一定次数,删除定时任务redisKey,恢复定时任务
			if (resultNum > 30) {
				//执行删除redis key操作
				stringRedisTemplate.delete(live_quzt_redis);
			}
			return;
		}
		
		try{
			String redisViewCountExpire = propertiesUtil.getValue("redis_viewCount_expire", "conf_live.properties");
			stringRedisTemplate.expire(live_quzt_redis, Integer.parseInt(redisViewCountExpire), TimeUnit.SECONDS);
			
			//统计观看人数
			List<Map<Object,Object>> watchLiveList=anchorLiveRecordDao.queryLivePersionSum();
			
			//同步正在直播的观看人数
			//anchorLiveRecordDao.modifyViewerCount();
			
			if(watchLiveList != null && watchLiveList.size() > 0){
				List<String> ids=new ArrayList<>();
				for(Map<Object,Object> watchmap:watchLiveList){
					ids.add(watchmap.get("anchor_id").toString());
				}
				
				log.info("统计正在直播的直播间的观看人数,信息:" + watchLiveList.toString());
				
				List<Map<Object,Object>> personList=personalCenterService.queryLiverPersonByListId(ids);
				if(personList != null && personList.size() > 0){
					//发送IM消息
					for(Map<Object,Object> watchmap:watchLiveList){
						//主播id
						int anchor_id=watchmap.get("anchor_id")==null?0:Integer.parseInt(watchmap.get("anchor_id").toString());
						for(Map<Object,Object> personMap:personList){
							//主播id
							int id=personMap.get("anchorid")==null?0:Integer.parseInt(personMap.get("anchorid").toString());
							if(id==anchor_id){
								
								watchmap.put("group_id", personMap.get("group_id").toString());
								
								//从redis中,获取管理员签名
								String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
								
								String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
								String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
								
								if (adminSig == null) {
									//调用tls,获取管理员tls的sig
									adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
									stringRedisTemplate.opsForValue().set("adminSig", adminSig);
									stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
								}
								
								//生成请求参数paramMap
								Map<Object,Object> paramMap=new HashMap<Object,Object>();
								paramMap.put("tlsSig", adminSig);
								paramMap.put("sdkappid", sdkAppid);
								paramMap.put("identifier", identifier);
								paramMap.put("GroupId", personMap.get("group_id").toString());
								paramMap.put("MsgType", "TIMCustomElem");
								
								Map<Object,Object> contentMap = new HashMap<Object,Object>();
								
								//自定义data类型参数
								Map<Object,Object> dataMap = new HashMap<Object,Object>();
								dataMap.put("type", 13);
								//dataMap.put("viewCount", watchmap.get("nums").toString());
								dataMap.put("viewCount", watchmap.get("view_count").toString());
								dataMap.put("liveRecordId", watchmap.get("id").toString());
								contentMap.put("Data", JSONObject.toJSONString(dataMap));
								paramMap.put("MsgContent", contentMap);
								
								//发送群组聊天信息
								boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
								if(!sendResult){
									log.info("定时统计正在直播的直播间的观看人数,发送IM消息失败,liveRecordId=" + watchmap.get("id").toString() + ",GroupId=" + personMap.get("group_id").toString() + 
											",viewCount=" + watchmap.get("view_count").toString());
									return;
								}
								log.info("定时统计正在直播的直播间的观看人数,发送IM消息成功,liveRecordId=" + watchmap.get("id").toString() + ",GroupId=" + personMap.get("group_id").toString() + 
										",viewCount=" + watchmap.get("view_count").toString());
								break;
							}
						}
					}
				}else {	
					log.info("定时统计正在直播的直播间的观看人数失败,未获取到直播间主播信息");
				}
				
			}else {
				log.info("定时统计正在直播的直播间的观看人数,没有正在直播的直播间");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("定时统计正在直播的直播间的观看人数失败,错误信息如下:" + e.getMessage());
		}finally{
			//执行删除redis key操作
			stringRedisTemplate.delete(live_quzt_redis);
		}
	}
}
