package com.xmniao.xmn.core.timer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：liveRadioQuertz   
* 类描述：   直播间广播消息定时任务
* 创建人：yezhiyong   
* 创建时间：2016年11月2日 下午4:06:18   
* @version    
*
 */
@Service
public class LiveRadioQuertz {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveRadioQuertz.class);
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入tlsSendImService
	 */
	@Autowired
	private TlsSendImService tlsSendImService;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	* @Title: liveRadio
	* @Description: 直播间广播消息定时任务
	* @return void    返回类型
	* @throws
	 */
	public void liveRadio() {
		
		//保证只有一个定时任务执行
		String live_quzt_redis = "live_radio_key";
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
		log.info("直播间广播消息定时任务，累计定时任务数："+resultNum);
		if (resultNum>1) {
			log.info("已有定时任务执行操作,其他定时任务强制退出："+live_quzt_redis+":"+resultNum);
			//超过一定次数,删除定时任务redisKey,恢复定时任务
			if (resultNum > 30) {
				//执行删除redis key操作
				stringRedisTemplate.delete(live_quzt_redis);
			}
			return ;
		}
		
		try {
			
			String redisRadioExpire = propertiesUtil.getValue("redis_radio_expire", "conf_live.properties");
			stringRedisTemplate.expire(live_quzt_redis, Integer.parseInt(redisRadioExpire), TimeUnit.SECONDS);
			
			//查询是否有指定时间点播放的广播消息
			List<Map<Object, Object>> radioList = anchorLiveRecordDao.queryLiveRadioBySendTime(DateUtil.format(new Date()));
			if (radioList != null && radioList.size() > 0) {
				for (Map<Object, Object> radioMap : radioList) {
					tlsSendImService.sendLiveRadio(radioMap);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("直播间广播消息定时任务失败,错误信息如下:" + e.getMessage());
		}finally{
			//执行删除redis key操作
			stringRedisTemplate.delete(live_quzt_redis);
		}
	}
	
}
