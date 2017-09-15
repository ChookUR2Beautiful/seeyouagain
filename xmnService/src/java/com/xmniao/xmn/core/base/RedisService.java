package com.xmniao.xmn.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Redis命令封装接口
 * Created by yang.qiang on 2017/3/7.
 */
@Service
public class RedisService {


    /**
     * 初始日志类
     */
    protected final Logger log = LoggerFactory.getLogger(RedisService.class);

    /**
     * 注入redis缓存
     */
    @Autowired
    private StringRedisTemplate redisTemplate;



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
}
