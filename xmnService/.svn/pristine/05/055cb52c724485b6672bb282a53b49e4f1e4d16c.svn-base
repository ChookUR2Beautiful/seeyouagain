package com.xmniao.xmn.core.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 简单实现分布锁
 * 注意，不同代码处，redisKey需要不一样
 * Created by Administrator on 2017/4/11.
 */
@Repository
public class RedisGlobalLockUtils {

    private static ThreadLocal<Boolean> lockList = new ThreadLocal<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean lock(final String redisKey, final String redisValue, final int expire, long timeout) {
        try {
            long nanoTime = System.nanoTime();
            while (System.nanoTime() - nanoTime > timeout) {
                // 非阻塞获取锁
                boolean isLock = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        byte[] key=stringRedisTemplate.getStringSerializer().serialize(redisKey);
                        byte[] value=stringRedisTemplate.getStringSerializer().serialize(redisValue);
                        return redisConnection.setNX(key, value);
                    }
                });
                if (isLock) {  //获取分布锁
                    stringRedisTemplate.execute(new RedisCallback<Boolean>() {
                        @Override
                        public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                            byte[] key=stringRedisTemplate.getStringSerializer().serialize(redisKey);
                            // 设置超时,避免锁忘记释放
                            return redisConnection.expire(key, expire);
                        }
                    });
                    lockList.set(true);
                    return true;
                }
                Thread.sleep(30);
            }
        } catch (Exception e) {
        }
        lockList.set(false);
        return false;
    }

    public void unlock(final String redisKey) {
        if (lockList.get()) {
            try {
                stringRedisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        byte[] key=stringRedisTemplate.getStringSerializer().serialize(redisKey);
                        // 设置超时,避免锁忘记释放
                        return redisConnection.del(key);
                    }
                });
                lockList.set(false);
            } catch (Throwable t) {
            }
        }
    }
}
