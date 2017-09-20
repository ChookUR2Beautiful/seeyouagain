package com.xmniao.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * @项目名称：saasService
 * @类名称：SessionTokenService
 * @类描述：会话令牌 token
 * @创建人： zhangchangyuan
 * @创建时间 2016年3月29日 下午5:16:10
 */
@Service
public class RedisService {

	public static final String LIVE_WALLET_REDIS_KEY = "wallet:live:message:";

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
     * @return Object  返回类型
     * @throws
     * @Title: getStringForValue
     * @Description: 查询缓存数据 String
     */
    public Object getStringForValue(String key) {
        try {
            if (redisTemplate.hasKey(key)) {
                return redisTemplate.opsForValue().get(key);
            }
        } catch (Exception e) {
            log.error("getStringForValue fail!", e);
        }
        return null;
    }

    /**
     * @return void  返回类型
     * @throws
     * @Title: setStringForValue
     * @Description: 设置缓存数据 String
     */
    public void setStringForValue(String key, String value, int entyrnyTime, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value);
            if (entyrnyTime != 0) {
                boolean success = redisTemplate.expire(key, entyrnyTime, unit);
                log.info(key + " set expire:--" + success + "-");
            }
        } catch (Exception e) {
            log.error("setStringForValue fail!", e);
            redisTemplate.delete(key);
        }
    }

    /**
     * @return Object  返回类型
     * @throws
     * @Title: getMapForValue
     * @Description: 查询缓存数据 Map
     */
    public Object getMapForValue(String key) {
        try {
            if (redisTemplate.hasKey(key)) {
                return redisTemplate.opsForHash().entries(key);
            }
        } catch (Exception e) {
            log.error("getMapForValue fail!", e);
        }
        return null;
    }

    /**
     * @return void  返回类型
     * @throws
     * @Title: setMapForValue
     * @Description: 设置缓存数据 Map
     */
    public void setMapForValue(String key, Map<String, Object> value, int entyrnyTime, TimeUnit unit) {
        try {
            redisTemplate.opsForHash().putAll(key, value);
            if (entyrnyTime != 0) {
                boolean success = redisTemplate.expire(key, entyrnyTime, unit);
                log.info(key + " set expire:--" + success + "-");
            }
        } catch (Exception e) {
            log.error("setMapForValue fail!", e);
            redisTemplate.delete(key);
        }
    }

    /**
     * @return void  返回类型
     * @throws
     * @Title: setMapForValue
     * @Description: 设置缓存数据List
     */
    public void setListForExpire(String key, int entyrnyTime, TimeUnit unit) {
        try {
            if (entyrnyTime != 0) {
                boolean success = redisTemplate.expire(key, entyrnyTime, unit);
                log.info(key + " set expire:--" + success + "-");
            }
        } catch (Exception e) {
            log.error("setMapForValue fail!", e);
            redisTemplate.delete(key);
        }
    }

    /**
     * @return void  返回类型
     * @throws
     * @Title: getListForExpire
     * @Description: 查询缓存数据 List
     */
    public Object getListForValue(String key) {
        try {
            return redisTemplate.opsForList().range(key, 0, -1);
        } catch (Exception e) {
            log.error("getListForExpire fail!", e);
            return null;
        }
    }

    /**
     * list逐个存入redis
     */
    public void setListForValue(String key, String json) {
        try {
            redisTemplate.opsForList().leftPush(key, json);
        } catch (Exception e) {
            log.error("setList fail!", e);
        }
    }

    /**
     * set逐个存入redis，加时间戳date
     */
    public void setZSetForValue(String key, String json, double date) {
        try {
            redisTemplate.opsForZSet().add(key, json, date);
        } catch (Exception e) {
            log.error("setSet fail!", e);
            redisTemplate.delete(key);
        }
    }

    /**
     * ZSet失效时间设置
     */
    public void setZSetForExpire(String key, int entyrnyTime, TimeUnit unit) {
        try {
            if (entyrnyTime != 0) {
                boolean success = redisTemplate.expire(key, entyrnyTime, unit);
                log.info(key + " set expire:--" + success + "-");
            }
        } catch (Exception e) {
            log.error("setZset fail!", e);
            redisTemplate.delete(key);
        }
    }

    /**
     * @return void    返回类型
     * @throws
     * @Title: getExpire
     * @Description: 处理redis缓存相对一些key未设置失效时间，进行删除处理
     */
    public void getExpire(String key) {
        /**
         * redisTemplate.getExpire(key) 底层实现ttl,以秒为单位，返回给定 key 的剩余生存时间
         当 key 不存在时，返回 -2 。
         当 key 存在但没有设置剩余生存时间时，返回 -1 。
         否则，以秒为单位，返回 key 的剩余生存时间。
         */
        if (redisTemplate.getExpire(key) == -1) {
            redisTemplate.delete(key);
            log.info("删除key:" + key);
        }
    }



    /**
     * 向Redis中存入Hash类型值,并设置有效时间
     *
     * @param key        redis key
     * @param hash       redis value 类型为Map<String,Object>
     * @param exprieDate 数据的有效时间
     */
    public void setHash(String key, Map<String, String> hash, Date exprieDate) {
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForHash().putAll(key, hash);
            redisTemplate.expireAt(key, exprieDate);
        }
    }

    /**
     * 根据key获取Redis中存储的Hash数据
     *
     * @param key
     * @return
     */
    public Map<Object, Object> getHash(String key) {
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.opsForHash().entries(key);
        }
        return null;
    }

    /**
     * 向Redis中存储字符串数据类型
     *
     * @param key        redis key
     * @param value      redis value 类型为字符串
     * @param expireDate 数据的有效时间
     */
    public void setString(String key, String value, Date expireDate) {
        redisTemplate.opsForValue().set(key, value);
        Boolean result = redisTemplate.expireAt(key, expireDate);
    }

    /**
     * 向Redis中存储字符串
     * @param key
     * @param value
     */
    public void setString(String key, String value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 根据key获取Redis存储的字符串数据
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 根据key删除Redis缓存数据
     *
     * @param key
     */
    public void deleteKey(final String key) {
        redisTemplate.delete(new ArrayList<String>() {{
            add(key);
        }});
    }

    /**
     * 判断一个key是否存在Redis中
     * @param redisKey
     * @return
     */
    public Boolean exsitKey(String redisKey){
        return redisTemplate.hasKey(redisKey);
    }

    /**
     * 键自增长
     * @param redisKey
     */
    public void increment(String redisKey){
        redisTemplate.opsForValue().increment(redisKey,1L);
    }

    public void setExpire(String redisKey,Date expire){
        redisTemplate.expireAt(redisKey,expire);
    }
    
    public StringRedisTemplate getStringRedisTemplate(){
    	return redisTemplate;
    }
    
}
