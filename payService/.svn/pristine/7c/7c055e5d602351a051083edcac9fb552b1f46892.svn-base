package com.xmniao.service.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;



/**
 * 
 * 
 * 项目名称：payService
 * 
 * 类名称：XmnWalletRedisPool
 * 
 * 类描述： 钱包消息队列线程池
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年10月19日 下午5:44:17 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service("xmnWalletRedisPool")
public class XmnWalletRedisPool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(XmnWalletRedisPool.class);
    
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 推送消息redis队列KEY
     */
    @Resource(name="xmnWalletModifyRedisName")
    private String pushMsgQueue;
    
    /**
     * 处理类
     */
    @Autowired
    private XmnWalletMessage xmnWalletMsgImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public XmnWalletRedisPool(int size)
    {
        if (size < 1)
        {
            size = 1;
        }
        this.threadSize = size;
    }
    
    public XmnWalletRedisPool()
    {
        log.info("=======");
    }
    
    /**
     * 线程执行
     */
    @Override
    public void run()
    {
    	log.info("Redis监听线程运行开始。。。");
        if (this.threadSize < 1)
        {
            this.threadSize = 1;
        }
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(this.threadSize);
        for (int i = 1; i <= threadSize; i++)
        {
            log.info("更新钱包 Redis监听线程: " + i);
            fixedThreadPool.execute(new XmnWalletWorkerThreads(xmnWalletMsgImpl,redisTemplate, pushMsgQueue));
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
