package com.xmniao.service.message.push;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 推送消息redis队列类
 * @author  LiBingBing
 * @version  [版本号, 2015年5月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PushMsgQueueHandle implements InitializingBean, DisposableBean
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