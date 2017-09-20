package com.xmniao.service.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.xmniao.thrift.busine.OrderService;
import com.xmniao.thrift.busine.OrderService.Client;

/**
 * 
 * 
 * 项目名称：payService
 * 
 * 类名称：PushMsgWorkerThreads
 * 
 * 类描述： 更新钱包余额消息
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年10月19日 下午4:06:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class XmnWalletWorkerThreads implements Runnable
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(XmnWalletWorkerThreads.class);
    
    /**
     * 推送消息redis队列KEY
     */
    private String pushMsgQueue;
    
    /**
     * 推送消息服务业务处理实现类
     */
//    @Autowired
    private XmnWalletMessage pushMsgImpl;
    
    /**
     * StringRedisTemplate
     */
//   @Autowired
    private StringRedisTemplate redisTemplate;
    
    public XmnWalletWorkerThreads(XmnWalletMessage pushMsgImpl,StringRedisTemplate redisTemplate, String pushMsgQueue)
    {
        this.pushMsgImpl = pushMsgImpl;
        this.redisTemplate = redisTemplate;
        this.pushMsgQueue = pushMsgQueue;
    }
    
    public XmnWalletWorkerThreads()
    {
        
    }
    
    @Override
    public void run()
    {
        log.info("pushMsg Redis工作线程"+Thread.currentThread().getName()+"已启动......");
        Map<String,String> map = new HashMap<String,String>();
        map.put("threadId",Thread.currentThread().getName());
        map.put("pushMsgImpl",pushMsgImpl.toString());
        while(true)
        {
            try
            {
            	  log.info("reids结果："+System.currentTimeMillis());
                //取出redis队列中的结果
                String result= redisTemplate.opsForList().rightPop(this.pushMsgQueue, 0, TimeUnit.SECONDS);
                if(null!=result)
                {
                    //调用推送消息业务处理实现类的方法
                	log.info("pushMsg Redis工作线程"+map+"运行中......");
                	pushMsgImpl.handleMessage(result,map);
                	
                }
            }
            catch (Exception e)
            {
            	log.error("处理Redis异常，",e);
                e.printStackTrace();
            } 
        }
    }
}
