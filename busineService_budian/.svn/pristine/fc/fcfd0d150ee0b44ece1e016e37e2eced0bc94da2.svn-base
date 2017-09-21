package com.xmniao.service.message.sms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 发送短信线程池处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SendSmsPool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SendSmsPool.class);
    
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 发送短信redis队列KEY
     */
    private String smsQueue;
    
    /**
     * 发送短信业务处理实现类
     */
    @Autowired
    private SendSmsImpl sendSmsImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    public SendSmsPool(int size)
    {
     if(size<1)
     {
         size=1;
     }
     this.threadSize=size;
    }
    
    public SendSmsPool()
    {
        
    }
    
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
            log.info("sendSms Redis监听线程: " + i);
            fixedThreadPool.execute(new SendSmsWorkerThreads(sendSmsImpl,redisTemplate, smsQueue));
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

    public String getSmsQueue()
    {
        return smsQueue;
    }

    public void setSmsQueue(String smsQueue)
    {
        this.smsQueue = smsQueue;
    }
}
