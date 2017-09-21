package com.xmniao.service.message.useraction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
 * 类描述： 更新用户浏览、消费的数据统计监听
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月10日 下午3:27:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class UserActionRedisPool implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(UserActionRedisPool.class);
    
    /**
     * 启动线程数
     */
    private int threadSize = 1;
    
    /**
     * 推送消息redis队列KEY
     */
    private String userActionQueue;
    
    /**
     * 推送消息服务业务处理实现类
     */
    @Autowired
    private UserActionImpl userActionMsgImpl;
    
    /**
     * StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public UserActionRedisPool(int size)
    {
        if (size < 1)
        {
            size = 1;
        }
        this.threadSize = size;
    }
    
    public UserActionRedisPool()
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
            fixedThreadPool.execute(new UserActionWorkerThreads(userActionMsgImpl,redisTemplate, userActionQueue));
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

	public String getUserActionQueue() {
		return userActionQueue;
	}

	public void setUserActionQueue(String userActionQueue) {
		this.userActionQueue = userActionQueue;
	}
    

}
