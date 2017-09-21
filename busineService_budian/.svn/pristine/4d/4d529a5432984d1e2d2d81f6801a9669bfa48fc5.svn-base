package com.xmniao.service.message.push;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 推送消息redis队列监听处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PushMsgRedisPool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(PushMsgRedisPool.class);
    
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 推送消息redis队列KEY
     */
    private String pushMsgQueue;
    
    /**
     * 推送消息服务业务处理实现类
     */
    @Autowired
    private PushMessageImpl pushMsgImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public PushMsgRedisPool(int size)
    {
        if (size < 1)
        {
            size = 1;
        }
        this.threadSize = size;
    }
    
    public PushMsgRedisPool()
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
            log.info("pushMsg Redis监听线程: " + i);
            fixedThreadPool.execute(new PushMsgWorkerThreads(pushMsgImpl,redisTemplate, pushMsgQueue));
        }
    }
    
    public int getThreadSize()
    {
        return threadSize;
    }
    
    public void setThreadSize(int threadSize)
    {
        this.threadSize = threadSize;
    }
    
    public String getPushMsgQueue()
    {
        return pushMsgQueue;
    }
    
    public void setPushMsgQueue(String pushMsgQueue)
    {
        this.pushMsgQueue = pushMsgQueue;
    }
}
