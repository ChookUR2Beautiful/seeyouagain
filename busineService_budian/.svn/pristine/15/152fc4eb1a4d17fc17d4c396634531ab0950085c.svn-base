package com.xmniao.service.message.seller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserActionImpl
 * 
 * 类描述：  更新商家信息MONGODB内容监听
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月10日 下午3:27:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class SellerInfoRedisPool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerInfoRedisPool.class);
    
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 更新商家信息redis队列KEY
     */
    @Resource(name="sellerInfoQueue")
    private String sellerInfoQueue;
    
    /**
     * 更新商家信息服务业务处理实现类
     */
    @Autowired
    private SellerInfoImpl sellerInfoImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public SellerInfoRedisPool(int size)
    {
        if (size < 1)
        {
            size = 1;
        }
        this.threadSize = size;
    }
    
    public SellerInfoRedisPool()
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
            log.info("userActionMsg Redis监听线程: " + i);
            fixedThreadPool.execute(new SellerInfoWorkerThreads(sellerInfoImpl,redisTemplate, sellerInfoQueue));
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
}
