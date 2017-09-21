package com.xmniao.service.message.useraction;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;


public class UserActionWorkerThreads implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(UserActionWorkerThreads.class);
    
    /**
     * 推送消息redis队列KEY
     */
    private String userActionQueue;
    
    /**
     * 推送消息服务业务处理实现类
     */
    @Autowired
    private UserActionImpl userActionImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public UserActionWorkerThreads(UserActionImpl userActionImpl,StringRedisTemplate redisTemplate, String userActionQueue)
    {
        this.userActionImpl = userActionImpl;
        this.redisTemplate = redisTemplate;
        this.userActionQueue = userActionQueue;
    }
    
    public UserActionWorkerThreads()
    {
        
    }
    
    @Override
    public void run()
    {
        log.info("pushMsg Redis工作线程"+Thread.currentThread().getName()+"已启动......");
        while(true)
        {
            try
            {
                //取出redis队列中的结果
                String result= redisTemplate.opsForList().rightPop(this.userActionQueue, 0, TimeUnit.SECONDS);
                if(null!=result)
                {
                    //调用推送消息业务处理实现类的方法
                	userActionImpl.handleMessage(result);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            } 
        }
    }
}
