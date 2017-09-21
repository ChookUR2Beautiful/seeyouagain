package com.xmniao.service.message.xmike;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis队列线程
 * @author  LiBingBing
 * @version  [版本号, 2015年2月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RedisQueuePool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(RedisQueuePool.class);
    
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 队列KEY
     */
    private String mikeQueue;
    
    /**
     * 队列处理实现类
     */
    @Autowired
    private QueueDelegateImpl delegateImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 构造方法
     */
    public RedisQueuePool(int size)
    {
        if (size < 1)
        {
            size = 1;
        }
        this.threadSize = size;
    }
    
    public RedisQueuePool()
    {
        
    }
    
    /**
     * 线程执行
     */
    @Override
    public void run()
    {
        if (this.threadSize < 1)
        {
            this.threadSize = 1;
        }
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(this.threadSize);
        for (int i = 1; i <= threadSize; i++)
        {
            log.info("mikeRedis监听线程: " + i);
            fixedThreadPool.execute(new QueueWorkerThreads(delegateImpl,
                    redisTemplate, mikeQueue));
        }
    }
    
    public void setThreadSize(int threadSize)
    {
        this.threadSize = threadSize;
    }
    
    public String getMikeQueue()
    {
        return mikeQueue;
    }
    
    public void setMikeQueue(String mikeQueue)
    {
        this.mikeQueue = mikeQueue;
    }
}