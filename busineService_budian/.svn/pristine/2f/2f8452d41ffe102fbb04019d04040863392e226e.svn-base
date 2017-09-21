package com.xmniao.service.message.sms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 发送短信工作线程类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SendSmsWorkerThreads implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SendSmsWorkerThreads.class);
    
    /**
     * 发送短信redis队列KEY
     */
    private String smsQueue;
    
    /**
     * 推送消息服务业务处理实现类
     */
    @Autowired
    private SendSmsImpl sendSmsImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public SendSmsWorkerThreads(SendSmsImpl sendSmsImpl,StringRedisTemplate redisTemplate,String smsQueue)
    {
        this.sendSmsImpl=sendSmsImpl;
        this.smsQueue=smsQueue;
        this.redisTemplate=redisTemplate;
    }
    
    public SendSmsWorkerThreads()
    {
        
    }
    
    
    @Override
    public void run()
    {
        log.info("sendSms Redis工作线程"+Thread.currentThread().getName()+"已启动......");
        while (true) {
            try {
                //取出redis队列中的结果
                String result = redisTemplate.opsForList().rightPop(this.smsQueue, 0, TimeUnit.SECONDS);
                log.info("从Redis短信队列中取出:"+result);
                if (null != result) {
                    //调用发送短信业务处理实现类的方法
                    sendSmsImpl.handleMessage(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
