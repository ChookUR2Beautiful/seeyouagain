package com.xmniao.service.message.seller;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 推送消息工作线程处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SellerInfoWorkerThreads implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerInfoWorkerThreads.class);
    
    /**
     * 推送消息redis队列KEY
     */
    private String sellerInfoQueue;
    
    /**
     * 推送消息服务业务处理实现类
     */
    @Autowired
    private SellerInfoImpl sellerInfoImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public SellerInfoWorkerThreads(SellerInfoImpl sellerInfoImpl,StringRedisTemplate redisTemplate, String sellerInfoQueue)
    {
        this.sellerInfoImpl = sellerInfoImpl;
        this.redisTemplate = redisTemplate;
        this.sellerInfoQueue = sellerInfoQueue;
    }
    
    public SellerInfoWorkerThreads()
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
                String result= redisTemplate.opsForList().rightPop(this.sellerInfoQueue, 0, TimeUnit.SECONDS);
                if(null!=result)
                {
                    //调用推送消息业务处理实现类的方法
                	sellerInfoImpl.handleMessage(result);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            } 
        }
    }
}
