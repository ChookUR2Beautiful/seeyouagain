package com.xmniao.service.message.xmike;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis队列类
 * @author  LiBingBing
 * @version  [版本号, 2015年2月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RedisQueueHandle implements InitializingBean, DisposableBean
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
    public void afterPropertiesSet() throws Exception
    {
        factory = stringRedisTemplate.getConnectionFactory();
    }
    
    @Override
    public void destroy() throws Exception
    {
        
    }
}
