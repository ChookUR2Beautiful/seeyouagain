package com.xmniao.xmn.core.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：ClearAnnunciate   
* 类描述：   定时清理直播通告
* 创建人：yezhiyong   
* 创建时间：2016年9月17日 上午9:36:24   
* @version    
*
 */
@Service
public class ClearAnnunciateQuertz {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ClearAnnunciateQuertz.class);
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired 
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入缓存stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	* @Title: clearAnnunciate
	* @Description: 定时清理直播通告
	* @return void    返回类型
	* @throws
	 */
	public void clearAnnunciate() {
		//保证只有一个定时任务执行
		String live_quzt_redis = "live_annunciate_key";
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
		log.info("定时清理超过计划时间24小时的未在直播的通告为历史通告，累计定时任务数："+resultNum);
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
			String redisAnnunciateExpire = propertiesUtil.getValue("redis_annunciate_expire", "conf_live.properties");
			stringRedisTemplate.expire(live_quzt_redis, Integer.parseInt(redisAnnunciateExpire), TimeUnit.SECONDS);
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("zhiboType", 4);
			paramMap.put("updateTime", DateUtil.format(new Date()));
			paramMap.put("endDate", DateUtil.format(new Date()));
			//定时清理直播通告
			anchorLiveRecordDao.modifyCurrentLiveRecord(paramMap);
			
			Map<Object, Object> paramEndMap = new HashMap<>();
			paramEndMap.put("zhiboType", 5);
			paramEndMap.put("updateTime", DateUtil.format(new Date()));
			paramEndMap.put("endDate", DateUtil.format(new Date()));
			anchorLiveRecordDao.modifyCurrentLiveEndRecord(paramEndMap);
			
			log.info("定时清理超过计划时间24小时的未在直播的通告为历史通告成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("修改超过计划时间24小时的未在直播的通告为历史通告失败,错误信息如下:" + e.getMessage());
		}finally{
			//执行删除redis key操作
			stringRedisTemplate.delete(live_quzt_redis);
		}
		
	}

}
