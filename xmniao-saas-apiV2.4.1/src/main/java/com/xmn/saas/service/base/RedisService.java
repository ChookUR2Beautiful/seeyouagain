package com.xmn.saas.service.base;

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
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.entity.common.SellerAccount;

/**
 * @项目名称：saasService
 * @类名称：SessionTokenService
 * @类描述：会话令牌 token
 * @创建人： zhangchangyuan
 * @创建时间 2016年3月29日 下午5:16:10
 */
@Service
public class RedisService {

    public static final String PREFIX_ACCOUNT = "saas:api:account:";
    public static final String PREFIX_ACCOUNT_AID = "saas:api:account:aid:";
    public static final String PREFIX_ACCOUNT_AGREEMENT = "saas:api:account:agreement:";
    public static final String PREFIX_PAY_COUNT = "saas:api:pay:count:";
    public static final String PREFIX_PAY_FREEZE = "saas:api:pay:freeze:";

    public static final String ROW_KEY_SELLER_ACCOUNT = "sellerAccount";
    
    /**
     * 寻蜜鸟平台买单订单生成时的顺序码初始值
     * */
    public final static String XMNIAO_ORDERNO_SEQ = "000000";

    /**
     * 初始日志类
     */
    protected final Logger log = LoggerFactory.getLogger(RedisService.class);

    /**
     * 注入redis缓存
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * @return void    返回类型
     * @throws
     * @Title: addSessionTokenToRedis
     * @Description: 将登陆用户数据存入Redis中
     */
    @Deprecated
    public void addSessionTokenToRedis(String redisKey, Map<String, Object> map) {
        redisKey = PREFIX_ACCOUNT + redisKey;
        if (!redisTemplate.hasKey(redisKey)) {
            log.info("query redis data:" + redisKey);
            redisTemplate.opsForHash().putAll(redisKey, map);

            Calendar calendar = Calendar.getInstance();
            // 获取配置文件配置的时间  conf-redis.properties
            calendar.add(Calendar.MINUTE, globalConfig.getRedisTimeSessionToken());

            redisTemplate.expireAt(redisKey, calendar.getTime());
        }
    }

    /**
     * @return Map<Object,Object>    返回类型
     * @throws
     * @Title: getsessionToken
     * @Description: 查询缓存数据
     */
    @Deprecated
    public Map<Object, Object> getSessionToken(String redisKey) {
        redisKey = PREFIX_ACCOUNT + redisKey;
        if (StringUtils.isNotBlank(redisKey) && redisTemplate.hasKey(redisKey)) {
            return redisTemplate.opsForHash().entries(redisKey);
        }
        return null;
    }

    /**
     * 验证会话令牌是否存在缓存中
     */
    @Deprecated
    public Map<Object, Object> isVerifyToken(String sessionToken) {
        sessionToken = PREFIX_ACCOUNT + sessionToken;
        Map<Object, Object> tokenMap = getSessionToken(sessionToken);
        //会话令牌是否存在
        if (null != tokenMap) {
            return tokenMap;
        }
        return null;
    }

    /**
     * @return boolean    返回类型
     * @throws
     * @Title: deleteSessionToken
     * @Description: 退出 清空sessionToken
     */
    @Deprecated
    public boolean deleteSessionToken(String sessionToken) {
        sessionToken = PREFIX_ACCOUNT + sessionToken;
        try {
            if (redisTemplate.hasKey(sessionToken)) {
                redisTemplate.delete(sessionToken);
            }
        } catch (Exception e) {
            log.error("quit clear session fail!", e);
        }
        return true;
    }


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
     * 根据sessionToken返回缓存在Redis中的对象
     *
     * @param sessionToken 会话令牌
     * @param clazz        缓存实体的class
     * @param <T>
     * @return 返回缓存的实体
     */
    public <T> T getSessionCacheObject(String sessionToken, Class<T> clazz) {
        String className = clazz.getSimpleName(); 
        // 根据ressionToken取出Redis中缓存的Hash数据
        Map<Object, Object> cacheObjectMap = this.getHash(PREFIX_ACCOUNT + sessionToken);

        if (cacheObjectMap == null) {
            return null;
        }

        String json = (String) cacheObjectMap.get(className);
        if (json == null) {
            throw new RuntimeException(className + "对象未被缓存,请确认" + className + "是否被缓存到Redis中");
        }
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将登陆需要缓存的对象保存在Redis中,默认缓存一个月 并返回sessionToken
     * @param redisKey
     * @param sellerAccount
     */
    public void setSessionCache(String redisKey, SellerAccount sellerAccount) {
        // 封装Redis存储数据
        HashMap<String, String> cacheObjectMap = new HashMap<>();
        String className = sellerAccount.getClass().getSimpleName();
        String json = JSON.toJSONString(sellerAccount);
        cacheObjectMap.put(className,json);
        cacheObjectMap.put("sellerid",sellerAccount.getSellerid().toString());
        cacheObjectMap.put("aid",sellerAccount.getAid().toString());

        Calendar calendar = Calendar.getInstance();
        // 在配置文件中获取Redis存放时间
        calendar.add(Calendar.MINUTE,globalConfig.getRedisTimeSessionToken());

        // 将登录缓存对象以Hash数据格式,存入Redis中,
        // 存入Redis, redis key 需要加上前缀
        this.setHash(redisKey, cacheObjectMap, calendar.getTime());
    }

    /**
     * 删除登陆信息缓存
     *
     * @param sessionToken
     */
    public void deleteSessionCacheObject(String sessionToken) {
        sessionToken = PREFIX_ACCOUNT + sessionToken;
        this.deleteKey(sessionToken);
    }

    /**
     * 返回登录时缓存到Redis中的SellerAccount
     *
     * @param sesionToken
     * @return
     */
    public SellerAccount getSellerAccount(String sesionToken) {
        return getSessionCacheObject(sesionToken, SellerAccount.class);

    }

    /**
     * 检查Redis中时候是否存在sessionToken
     *
     * @param sessionToken
     * @return
     */
    public boolean checkSessionCacheObject(String sessionToken) {
        sessionToken = PREFIX_ACCOUNT + sessionToken;
        return redisTemplate.hasKey(sessionToken);
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
    
    /**
     * 描述：生成平台下单的订单编号
     * 由日期+六位顺序码组合而成   如 2016-11-21 + 000001 实际单号为  161121000001
     * @return String
     * */
    public String createOrderNumber(){
        StringBuffer orderNo = new StringBuffer();
        SimpleDateFormat sFormat = new SimpleDateFormat("yyMMdd");
        orderNo = orderNo.append(sFormat.format(new Date()));
        String orderSeqNo = XMNIAO_ORDERNO_SEQ;//订单六位顺序码初始值
        
        //根据当前时间作为key 取出当天订单顺序累计数字
        String resultNum = redisTemplate.opsForValue().increment(orderNo.toString(), 1).toString();
        orderNo = orderNo.append(orderSeqNo.substring(0, orderSeqNo.length()-resultNum.length())).append(resultNum);
        return orderNo.toString();
    }

}
