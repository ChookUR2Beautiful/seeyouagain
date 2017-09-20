/**    
 * 文件名：RedisLockUtil.java    
 *    
 * 版本信息：    
 * 日期：2017年4月26日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：RedisLockUtil
 * 
 * 类描述： 基于Redis的同步锁
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年4月26日 下午6:12:08 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class RedisLockService {
	
	// 初始化日志类
	private final Logger log = Logger.getLogger(getClass());
	
    @Autowired
    private StringRedisTemplate redisTemplate;
    	
    /*
     * 获取锁
     */
	public boolean getLock(String key){
		return this.getLock(key, 60000);
	}   
	
	/*
	 * 获取锁
	 */
	public boolean getLock(String key,long timeout){
		boolean getLock= false;
		long t=0;
		long s = System.currentTimeMillis();
		try {
			while(true){
				getLock = redisTemplate.boundValueOps(key).increment(1) == 1;
				if(getLock){
					redisTemplate.expire(key,30 , TimeUnit.SECONDS);
					break;
				}else{
					if(t>=timeout){
						break;
					}
					Thread.sleep(10);
					t=t+10;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("key:"+key+",是否获得锁:"+getLock+",等待时长："+(System.currentTimeMillis()-s));
		return getLock;
	}
	
	/*
	 * 释放锁
	 */
	public void releaseLock(String key){
		log.info("key:"+key+"释放锁");
		redisTemplate.delete(key);
	}
}
