/**    
 * 文件名：LiverLedgerThread.java    
 *    
 * 版本信息：    
 * 日期：2016年12月28日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2016     
 * 版权所有    
 *    
 */
package com.xmniao.service.message.liverLedger;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.domain.live.LiverJournalCount;
import com.xmniao.urs.dao.LiverDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：liverLedgerImpl
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月28日 下午4:05:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class LiverLedgerImpl /*implements Runnable */{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(LiverLedgerImpl.class);
	
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private String liverLedgerQueue;
    
    @Autowired
    private LiverDao liverDao;
    
//	@Override
//	public void run() {
//
//        log.info("更新直播会员分账统计 Redis工作线程"+Thread.currentThread().getName()+"已启动......");
//        while(true)
//        {
//            try
//            {
//                //取出redis队列中的结果
//                String result= redisTemplate.opsForList().rightPop(liverLedgerQueue, 0, TimeUnit.SECONDS);
//                if(null!=result){
//                	handleMessage(result);
//                }
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            } 
//        }
//    
//	}
	
	/**
	 * 
	 * 方法描述：业务处理
	 * 创建人： ChenBo
	 * 创建时间：2016年12月28日
	 * @param jsonStr
	 */
	public void handleMessage(String jsonStr){
        if (StringUtils.isNotBlank(jsonStr)){
        	log.info("接收到直播会员分账更新队列数据："+jsonStr);
        	try{
        		LiverJournalCount journal = JSONObject.parseObject(jsonStr, LiverJournalCount.class);
			try{
				if(journal.getUid()==null || journal.getUid()==0){
					log.error("错误数据，无法处理，直接废弃");
					return;
				}
				journal.setUpdateDate(new Date());
				LiverJournalCount reqJournal = liverDao.getLiverJournal(journal.getUid());
				if(reqJournal==null){
					liverDao.insertLiverJournal(journal);
				}else{
					liverDao.updateLiverJournal(journal);
				}
				
			}catch(Exception e){
				try{
					int retry = journal.getVersion();
					if(retry>=2){
						log.error(jsonStr+",该消息多次重试后，仍不成功，舍弃！");
					}else{
						log.error("更新直播会员分账信息处理异常,插入列队尾部重试",e);
						retry++;
						journal.setVersion(retry);
						redisTemplate.opsForList().leftPush(liverLedgerQueue, JSONObject.toJSONString(journal));
					}
				}catch(Exception e2){
					log.error("更新更新直播会员分账信息处理失败重插队尾失败,",e2);
				}
			}
        	}catch(Exception e3){
        		log.error("更新直播会员分账信息数据转换异常",e3);
        	}
        }
		
	}
}
