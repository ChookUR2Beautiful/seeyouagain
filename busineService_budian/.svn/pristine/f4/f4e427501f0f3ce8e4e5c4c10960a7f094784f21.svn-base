package com.xmniao.service.message.xmike;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis工作线程类
 * @author  LiBingBing
 * @version  [版本号, 2015年2月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QueueWorkerThreads implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(QueueWorkerThreads.class);
    
    /**
     * 队列处理Service
     */
    private QueueDelegateImpl delegateImpl;
    
    /**
     * 队列KEY
     */
    private String mikeQueue;
    
    /**
     * StringRedisTemplate
     */
    private StringRedisTemplate redisTemplate;
    
    public QueueWorkerThreads()
    {
        
    }
    
    /**
     * 传递redis队列的key的构造函数
     */
    public QueueWorkerThreads(QueueDelegateImpl delegateImpl,StringRedisTemplate redisTemplate, String mikeQueue)
    {
        this.delegateImpl = delegateImpl;
        this.redisTemplate = redisTemplate;
        this.mikeQueue = mikeQueue;
    }
    
    /**
     * 执行线程
     */
    @Override
    public void run()
    {
        log.info("mikeRedis工作线程"+Thread.currentThread().getName()+"已启动...");
        while(true)
        {
            try
            {
                //取出redis队列中的结果
                String result= redisTemplate.opsForList().rightPop(this.mikeQueue, 0, TimeUnit.SECONDS);
                if(null!=result)
                {
                     //调用队列处理实现类的方法
                     delegateImpl.handleMessage(result);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
