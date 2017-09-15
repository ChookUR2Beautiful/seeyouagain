package com.xmniao.xmn.core.timer;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.entity.TopRankXmer;
import com.xmniao.xmn.core.xmer.service.TopicalRankService;

@Service
public class UpdateXmerTopRankQuartz {
	
	private final Logger log = Logger.getLogger(LiveRobotSendGiftQuertz.class);

	@Autowired
	private  SessionTokenService sessionTokenService;
	
	@Autowired
	private TopicalRankService topicalRankService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	public void updateXmerTopRank(){
		
		
		log.info("更新寻蜜客排行榜信息  start...");
		//保证只有一个定时任务执行
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(Constant.LIVE_XMERTOP_REDIS, 1);
		
		try {
			if (resultNum>1) {
				log.info("已有定时任务执行操作,其他定时任务强制退出："+Constant.LIVE_XMERTOP_REDIS+":"+resultNum);
				//超过一定次数,删除定时任务redisKey,恢复定时任务
				int topXmerUpdateKey = Integer.parseInt(propertiesUtil.getValue("topXmerUpdateKey", "conf_xmer.properties").toString());
				log.info("寻蜜客排行榜定时更新最大 key 个数为：" + topXmerUpdateKey);
				if (resultNum > topXmerUpdateKey) {
					//执行删除redis key操作
					stringRedisTemplate.delete(Constant.LIVE_XMERTOP_REDIS);
				}
				return ;
			}
		
			log.info("更新寻蜜客排行榜数据开始！删除缓存开始。。。");
			//清除redis中的 排行榜数据
			sessionTokenService.deleteSessionToken(Constant.XMER_RANK_KEY);
			
			log.info("缓存删除完毕，开始拉取寻觅客排行信息");
			
			List<TopRankXmer> xmerList = topicalRankService.listTopRankXmer();
			int j = 0;
			for (TopRankXmer xmer : xmerList) {
				xmer.setSort(++j);
				sessionTokenService.setZSetForValue(Constant.XMER_RANK_KEY, JSONObject.toJSONString(xmer), xmer.getSort());
			}
			log.info("更新寻蜜客排行榜信息成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stringRedisTemplate.delete(Constant.LIVE_XMERTOP_REDIS);
		}
		log.info("更新寻蜜客排行榜信息  end...");
		
	}
}
