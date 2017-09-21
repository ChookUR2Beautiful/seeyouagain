package com.xmniao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

/**
 * Redis命令封装接口
 * Created by yang.qiang on 2017/3/7.
 */
public class RedisService {


    /**
     * 初始日志类
     */
    protected final Logger logger = LoggerFactory.getLogger(RedisService.class);

    /**
     * 注入redis缓存
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 向Redis中存储字符串
     * @param key
     * @param value
     */
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 根据key获取Redis存储的字符串数据
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除Redis缓存数据
     *
     * @param key
     */
    public void del(final String key) {
        redisTemplate.delete(new ArrayList<String>() {{
            add(key);
        }});
    }

    /**
     * 判断一个key是否存在Redis中
     * @param redisKey
     * @return
     */
    public Boolean exists(String redisKey){
        return redisTemplate.hasKey(redisKey);
    }

    /**
     * 键自增长
     * @param redisKey
     */
    public Long increment(String redisKey){
        return redisTemplate.opsForValue().increment(redisKey,1L);
    }

    public void expireat(String redisKey,Date expire){
        redisTemplate.expireAt(redisKey,expire);
    }


    /**
     * 在Redis中插入 List 数据
     * @param key
     * @param list
     */
    public void lset(String key,List<String> list){
        redisTemplate.opsForList().leftPushAll(key,list.toArray(new String[list.size()]));
    }

    /**
     * 根据角标获取列表数据
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public List<String> lrange(String key,long start,long stop){
        if (!exists(key)) {
            return null;
        }
        return redisTemplate.opsForList().range(key, start, stop);
    }

    /**
     * 获取列表中的所有数据
     * @param key
     * @return
     */
    public List<String> lrange(String key){
        return lrange(key,0,-1);
    }


    public Map<Object, Object> hgetall(String key){
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        return entries.size() == 0 ? null : entries;
    }

    public String hget(String key, String field){
        Object value = redisTemplate.opsForHash().get(key, field);
        return value != null ? value.toString() : null;
    }

    public void hset(String key, String field, String value){
        redisTemplate.opsForHash().put(key,field,value);
    }

    public void hmset(String key,Map<String,String> params){

        // 清空空数据
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String value = next.getValue();
            if (value == null || "null".equals(value)) {
                it.remove();
            }
        }

        redisTemplate.opsForHash().putAll(key,params);
    }


    /**
     * 简单的redis锁
     * @param key  加锁的key
     * @param timeout 毫秒
     * @return
     */
    public String lock(final String key,final long timeout) {
        redisTemplate.opsForValue().set("xx","");
        return (String)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                try {
                    String uuid = UUID.randomUUID().toString();
                    long deadline = System.currentTimeMillis() + timeout;

                    while (System.currentTimeMillis() <= deadline) {


                        if (redisConnection.setNX(key.getBytes(), uuid.getBytes())) {   // 设置分布锁成功
                            redisConnection.expire(key.getBytes(), 30L);   // 30秒生命周期
                            logger.info("添加Redis锁 [" + key + "].");
                            return uuid;
                        } else if (redisConnection.ttl(key.getBytes()) == -1L) {        // 设置分布锁失败, 且redis 的销毁时间未设置
                            redisConnection.expire(key.getBytes(), 30L);    // 30秒生命周期
                        }
                        Thread.sleep((long)Math.random()*100);
                    }
                } catch (Exception e) {
                    logger.error("获取Redis分布式锁 出现异常",e);
                } finally {
                    // 释放Redis连接
                    RedisConnectionUtils.releaseConnection(redisConnection, redisTemplate.getConnectionFactory());
                }
                throw new RuntimeException("获取Redis锁超时 key = " +key);
            }
        });
    }


    /**删除redis锁
     *
     * @param key
     * @param identifier
     */
    public void unlock(String key, String identifier) {
        logger.info("释放Redis锁[" + key + "].");
        if(identifier.equals(redisTemplate.opsForValue().get(key))){
            redisTemplate.delete(key);
        }
    }


    /**
     * 自增 hash 数据内的字段的值
     * @param key   Redis key
     * @param filed Hash 的字段名
     * @param step  步长
     * @return
     */
    public long hincrby(final String key, final String filed, final long step){
        return (long)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                try {
                    return redisConnection.hIncrBy(key.getBytes(),filed.getBytes(),step);
                } finally {
                    RedisConnectionUtils.releaseConnection(redisConnection, redisTemplate.getConnectionFactory());
                }
            }
        });

    }

    /** 自增hash字段值 1 */
    public long hincrby(String key, String filed){
        return hincrby(key,filed,1L);
    }


    /**
     * push数据到Redis列表中
     */
    public void lpush(String key, String value) {
        redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 从列表中弹值
     * @param key
     * @return
     */
    public String lpop(String key) {
       return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 删除Hash的字段
     * @param key
     * @param fields
     */
    public void hdel(String key, String... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }
}


