package com.xmniao.service.message.useraction;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserActionImpl
 * 
 * 类描述： 更新用户浏览、消费的数据统计处理
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月10日 下午3:27:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class UserActionQueueHandle implements InitializingBean, DisposableBean
{
    /**
     * StringRedisTemplate
     */
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * Redis连接工厂
     */
    private RedisConnectionFactory factory;
    
    @Override
    public void destroy() throws Exception
    {
        
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        factory = stringRedisTemplate.getConnectionFactory();
    }
}