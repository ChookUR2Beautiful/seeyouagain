package com.xmniao.xmn.core.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGivedGiftInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.DateUtil;


/**
 * 描述 检查主播直播是有无 未接收的礼物记录 
 * @author yhl
 * 2016-8-12 16:55:53
 * */
@Service
public class ModifyAnchorGiftBirdEggQuertz {
	
	private final Logger log = LoggerFactory.getLogger(ModifyAnchorGiftBirdEggQuertz.class);
	/**
	 * 直播礼物Dao /service
	 * */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	/**
	 * 直播记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入redis
	 * */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 直播用户通用dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 描述：对直播未接收成功的礼物 补偿给主播 累计鸟蛋
	 * 		 查询出未接收的礼物记录，根据直播记录ID 和 主播ID anchorId 进行补偿
	 * 
	 * */
	public void modifyGiftsToBirdEgg(){
		
		//保证只有一个定时任务执行
		String live_quzt_redis = "live_quzt_redis";
		//采用redis incr函数初始化值 保证定时任务只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(live_quzt_redis, 1);
		log.info("目前已累计定时任务数量："+resultNum);
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
			log.info("观众赠送主播礼物累计鸟蛋定时任务开启： "+DateUtil.format(new Date()));
			//查询24小时内 已经结束的直播记录列表  做主播鸟蛋 补偿操作
			List<LiveRecordInfo> recordInfoList = anchorLiveRecordDao.queryLiveRecordByEnd();
			log.info("共查询到跨天直播记录总数："+recordInfoList.size());
			//批量执行累计鸟蛋集合
			List<Map<String, String>> batchWalletList = new ArrayList<Map<String, String>>();
			//预接收 鸟蛋状态集合
			List<Map<Object, Object>>  preGivedGiftInfos = new ArrayList<Map<Object, Object>>();
			//未接收 鸟蛋状态集合
			List<Map<Object, Object>>  notGivedGiftInfos = new ArrayList<Map<Object, Object>>();
			
			if (recordInfoList.size()>0) {
				for (int i = 0; i < recordInfoList.size(); i++) {
					LiveRecordInfo info = recordInfoList.get(i);
					//获取主播信息 拿到UID 
					LiverInfo liverInfo = liveUserDao.queryLiverInfoByAnchorId(info.getAnchor_id().intValue());
					
					Map<Object, Object> viewRecordMap = new HashMap<Object, Object>();
					viewRecordMap.put("live_record_id", info.getId());//直播ID
					viewRecordMap.put("anchor_id", info.getAnchor_id());//直播主播ID
					//查询直播送礼物主播接收鸟蛋 “advanced_status”  “未接收” | “已接收”的记录
					//advanced_status  '预处理状态  1 未接受  2 预接收 3 已接收'。
					List<LiveGivedGiftInfo>  givGiftList = liveGiftsInfoDao.queryLiveGivedGiftByAdvancedStatus(viewRecordMap);
					if (givGiftList.size()>0) {
						Long balance = 0L;
						for (int j = 0; j < givGiftList.size(); j++) {
							LiveGivedGiftInfo givedInfo = givGiftList.get(j);
							//接收状态 等于 2 标示预接收  标示主播接收鸟蛋成功 直接修改为 已接收
							if (givedInfo.getAdvancedStatus() == 2) {
								//组装接收成功的 集合
								//预接收  未接收的map 集合
								Map<Object, Object> preMap = new HashMap<Object, Object>();
								preMap.put("id", givedInfo.getId());
								preMap.put("advanced_status", "3");
								preMap.put("update_time", DateUtil.format(new Date()));
								preGivedGiftInfos.add(preMap);
								
							}else if (givedInfo.getAdvancedStatus() == 1 && givedInfo.getIsfailed() == 1) { //等于1 则累计鸟蛋失败了 重新累计
								//组装修改发礼物表记录集合
								Map<Object, Object> notMap = new HashMap<Object, Object>();
								notMap.put("id", givedInfo.getId());
								notMap.put("anchorId", givedInfo.getAnchorId());
								notMap.put("liveRecordId", givedInfo.getLiveRecordId());
								notMap.put("giftPrice", givedInfo.getGiftPrice());
								notMap.put("advanced_status", "3");
								notMap.put("update_time", DateUtil.format(new Date()));
								notGivedGiftInfos.add(notMap);
								
								//循环累计主播鸟蛋 统一执行累计主播鸟蛋
								balance = balance+Long.parseLong(givedInfo.getGiftPrice().toString());
							}
						}
						//balance大于0 标示有鸟蛋需要累计到主播
						if (balance>0) {
							//组装修改更新集合
							Map<String, String> walletMap = new HashMap<String, String>();
							walletMap.put("uid", liverInfo.getUid().toString());
							walletMap.put("balance", balance.toString());
							walletMap.put("rtype", "5");
							batchWalletList.add(walletMap);
						}
					}
				}
				
				//优先执行预接收状态的记录 预接收为成功累计了鸟蛋的记录  无需再次累计鸟蛋
				if (preGivedGiftInfos.size()>0) {
					liveGiftsInfoDao.modifyBatchLiveGivedGiftById(preGivedGiftInfos);
					log.info("累计鸟蛋定时任务此次共执行“预接收”状态记录数："+preGivedGiftInfos.size());
				}
				
				//提供一个批量修改用户直播钱包的记录接口
				try {
					//批量操作完毕  则执行修改批量处理未接收的赠送记录为已接收
					log.info("需要同步鸟蛋："+batchWalletList.size());
					ResponseData result = liveGiftsInfoService.updateLiveWalletsForList(batchWalletList);
					
					if (result.getState() == 0) {
						//批量修改观众赠送礼物记录信息 
						log.info("未接收的记录："+notGivedGiftInfos.size());
						if (notGivedGiftInfos.size()>0) {
							liveGiftsInfoDao.modifyBatchLiveGivedGiftById(notGivedGiftInfos);
							log.info("累计鸟蛋定时任务此次共执行“未接收”状态记录数："+notGivedGiftInfos.size());
						}
						
					}else {
						log.info("定时任务批量累计主播鸟蛋异常");
					}
				} catch (Exception e) {
					log.info("定时任务批量累计主播鸟蛋异常");
					e.printStackTrace();
				}finally{
					//执行删除redis key操作
					stringRedisTemplate.delete(live_quzt_redis);
				}
			}
		} catch (Exception e) {
			log.info("执行定时任务异常!");
			e.printStackTrace();
		}finally{
			//执行删除redis key操作
			stringRedisTemplate.delete(live_quzt_redis);
		}
		
		log.info("观众赠送主播礼物累计鸟蛋定时任务执行完毕： "+DateUtil.format(new Date()));
		
	}

}
