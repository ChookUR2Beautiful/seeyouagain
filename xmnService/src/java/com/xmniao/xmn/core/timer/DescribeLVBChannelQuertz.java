package com.xmniao.xmn.core.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.TlsSendImService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.vod.QcloudApiModuleCenter;
import com.xmniao.xmn.core.vod.Module.Live;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：DescribeLVBChannelQuertz   
* 类描述：   清理僵尸直播频道定时任务
* 创建人：yezhiyong   
* 创建时间：2016年10月18日 下午2:45:21   
* @version    
*
 */
@Service
public class DescribeLVBChannelQuertz {
	
	private static final String CHANNEL_STOP_NUMS = "channel_stop_nums";
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(DescribeLVBChannelQuertz.class);
	
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

	@Autowired
	private KSCloudService ksCloudService;
	
	/**
	 * 
	* @Title: describeLVBChannel
	* @Description: 清理僵尸直播频道定时任务
	* @return void    返回类型
	* @throws
	 */
	public void describeLVBChannel() {
		
		//保证只有一个定时任务执行
		String live_quzt_redis = "live_channel_key";
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
		log.info("清理残留直播频道定时任务，累计定时任务数："+resultNum);
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
			/**
			 * {
				    "code": 0,
				    "message": "",
				    "channelInfo": [
				        {
				            "channel_id": "XXX",
				            "channel_name": "XXX",
				            "channel_describe": "XXX",
				            "channel_status": "1",
				            "upstream_list": [
				                {
				                    "sourceName": "RTMP",
				                    "sourceID": "YYYYYYYYYYY",
				                    "sourceType": "1",
				                    "sourceAddress": "rtmp://2000.livepush.myqcloud.com/live/YYYYYYYYYYYYYYYYYY?bizid2000"
				                }
				            ],
				            "hls_downstream_address": "",
				            "rtmp_downstream_address": "rtmp://2000.liveplay.myqcloud.com/live/XXX",
				            "player_id": "226",
				            "resolution": null,
				            "password": null
				        }
				    ]
				}
			 */
			String redisChannelExpire = propertiesUtil.getValue("redis_channel_expire", "conf_live.properties");
			stringRedisTemplate.expire(live_quzt_redis, Integer.parseInt(redisChannelExpire), TimeUnit.SECONDS);
			
			//查看正在直播的直播记录信息(包含内部测试通告)
			List<LiveRecordInfo> liveReordInfo = anchorLiveRecordDao.queryCurrentLiveRecord();
			if (liveReordInfo != null) {
				for (LiveRecordInfo liveRecord : liveReordInfo) {
					boolean isKSL = ksCloudService.isKSL(liveRecord);
					if (isKSL) {
						log.info("金山直播，不用清理 " + liveRecord.toString());
						continue;
					}

					//直播频道id
					String channelId = liveRecord.getChannel_id();
					//主播id
					Long anchorId = liveRecord.getAnchor_id();
					//redis缓存频道异常错误次数
					String channelStopNumKey = anchorId + "_" + CHANNEL_STOP_NUMS;
					if (StringUtils.isNotEmpty(channelId)) {

						//查询腾讯云直播频道状态
						QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Live(),"GET");
						TreeMap<String, Object> params = new TreeMap<String, Object>();
						//请求参数,频道号
						params.put("channelId",channelId);
						
						String result = null;
						try {
							/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
							result = module.call("DescribeLVBChannel", params);
							log.info("查询直播频道详细信息" + result);
							//解析数据
							JSONObject jsonObj = JSONObject.parseObject(result);
							//获取直播频道列表信息
							String channelInfoList = jsonObj.getString("channelInfo");


							//获取不到直播频道信息
							if (StringUtils.isEmpty(channelInfoList) && "4000".equals(jsonObj.get("code").toString())) {
								//频道异常
								if ("20301".equals(jsonObj.get("codeDesc").toString())) {
									//清理直播频道,检测3次
									this.clearLiveChannel(channelStopNumKey, 3, liveRecord);
									return;
								}
								
								//频道长期不存在
								if (jsonObj.get("message").toString().contains("频道不存在")) {
									//清理直播频道,检测3次
									this.clearLiveChannel(channelStopNumKey, 3, liveRecord);
									return;
								}
							}
							
							//获取直播频道第一个信息
							JSONObject channelInfo = JSONObject.parseObject(JSONObject.parseArray(channelInfoList).getString(0));
							//获取直播频道状态0 无输入流 ，1   直播中， 2  异常，3  关闭',
							String channelStatus = channelInfo.getString("channel_status");
							
							//如果直播频道状态不是直播中，则进行记录推流断开次数，进行相应的逻辑处理
							if (!"1".equals(channelStatus)) {
								//清理直播频道,检测3次
								this.clearLiveChannel(channelStopNumKey, 4, liveRecord);
								
							}else {
								//如果检测到直播频道直播中，则清除推流断开次数
								stringRedisTemplate.delete(channelStopNumKey);
							}
							
						} catch (Exception e) {
							log.info("查询直播频道状态信息失败，信息如下:" + e.getMessage());
						}
						
					}else {
						this.clearLiveChannel(channelStopNumKey, 4, liveRecord);
						log.info("直播记录id=" + liveRecord.getId() + "的直播频道channel_id=" + channelId);
					}
						
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("清理残留直播频道未关闭失败,错误信息如下:" + e.getMessage());
		}finally{
			//执行删除redis key操作
			stringRedisTemplate.delete(live_quzt_redis);
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: clearChannel
	* @Description: 清理直播频道
	* @return void    返回类型
	* @throws
	 */
	public void clearLiveChannel(String liveChannelKey,int checkCount,LiveRecordInfo liveRecordInfo) throws Exception {
		try {
			//查询直播频道缓存的推流断开的次数
			String nums = stringRedisTemplate.opsForValue().get(liveChannelKey);
			if (nums != null && StringUtils.isNotEmpty(nums)) {
				//已经记录了推流断开次数记录的，则直接清除正在直播记录
				if (Integer.parseInt(nums) > checkCount) {
					try {
						//记录定时器退出直播间信息
						this.recordAnchorExceptionQuartzLogout(liveRecordInfo);
					} catch (Exception e) {
						log.info("记录定时器退出直播间信息失败,错误信息如下:" + e.getMessage());
					}

					//同步主播信息
					tlsSendImService.synAnchorInfo(liveRecordInfo);
					//清除推流断开次数
					stringRedisTemplate.delete(liveChannelKey);
				}else {
					stringRedisTemplate.opsForValue().set(liveChannelKey,Integer.parseInt(nums) + 1 + "");
				}
			}else {
				//检测直播频道推流第一次断开，则缓存1次断开次数
				stringRedisTemplate.opsForValue().set(liveChannelKey,"1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("清理直播频道失败,错误信息如下:" + e.getMessage());
		}
	}

	public void clearLiveChannelByKSL(LiveRecordInfo liveRecordInfo) {
		try {
			try {
				//记录定时器退出直播间信息
				this.recordAnchorExceptionQuartzLogout(liveRecordInfo);
			} catch (Exception e) {
				log.info("金山云 记录定时器退出直播间信息失败,错误信息如下:" , e);
			}
			//同步主播信息
			tlsSendImService.synAnchorInfo(liveRecordInfo);
		} catch (Exception e) {
			log.info("金山云 清理直播频道失败,错误信息如下:", e);
		}
	}

	
	/**
	 * 
	* @Title: recordAnchorExceptionQuartzLogout
	* @Description: 记录定时器退出直播间信息
	* @return void    返回类型
	* @throws
	 */
	public void recordAnchorExceptionQuartzLogout(LiveRecordInfo liveRecordInfo) {
		//组装参数
		Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("liveRecordId", liveRecordInfo.getId());
		paramMap.put("liveId", liveRecordInfo.getAnchor_id().toString());
		paramMap.put("liveExitStart", DateUtil.format(new Date()));
		paramMap.put("createTime", DateUtil.format(new Date()));
		paramMap.put("updateTime", DateUtil.format(new Date()));
		
		//异常退出原因  4定时任务退出
		paramMap.put("exitReason", "4");
		//记录主播正在直播异常退出信息
		anchorLiveRecordDao.insertLiveExitRecord(paramMap);
	}
}
