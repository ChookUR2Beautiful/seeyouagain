package com.xmniao.xmn.core.base;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.util.FrameUtil;

/**
 *
 * @项目名称：saasService
 * @类名称：SessionTokenService
 * @类描述：会话令牌 token
 * @创建人： zhangchangyuan
 * @创建时间 2016年3月29日 下午5:16:10
 * @version
 */
@Service
public class SessionTokenService {
	
	/**
	 * 初始日志类
	 */
	private final Logger log = Logger.getLogger(SessionTokenService.class);
	
	private static final String LOCKED = "TRUE";
	
	private static final String LOCKED_FLAG = "_lock_flag";
	
	private volatile boolean locked = false;
	 
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;  

	/**
	 * 
	* @Title: addSessionTokenToRedis
	* @Description: 添加 用户登录数据到缓存
	* @return void    返回类型
	* @throws
	 */
	public void addSessionTokenToRedis(String redisKey,Map<String, Object> map){
		if(!stringredisTemplate.hasKey(redisKey)){
			log.info("query redis data:"+redisKey);
			stringredisTemplate.opsForHash().putAll(redisKey, map);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, +1);    //得到下个月
			//缓存一个月 后失效
			stringredisTemplate.expireAt(redisKey, calendar.getTime());
		}		
	}
	
	/**
	 * 
	* @Title: addSessionTokenToRedis
	* @Description: 添加 用户登录数据到缓存
	* @return void    返回类型
	* @throws
	 */
	public void addXmerInfoRedis(String redisKey,Map<Object, Object> map){
			log.info("query redis data:"+redisKey);
			stringredisTemplate.opsForHash(). putAll(redisKey, map);
			
			
	}
	
	/**
	 * 
	* @Title: getsessionToken
	* @Description: 查询缓存数据
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	public Map<Object, Object> getsessionToken(String redisKey){
		if(stringredisTemplate.hasKey(redisKey)){
			return stringredisTemplate.opsForHash().entries(redisKey);
		}
		return null;
	}
	
	/**
	 * 验证会话令牌是否存在缓存中
	 */
	public Map<Object,Object> isVerifyToken(String sessionToken) {
		Map<Object,Object> tokenMap = getsessionToken(sessionToken);
		//会话令牌是否存在
		if(null!=tokenMap){
			return tokenMap;
		}
		return null;
	}

	/**
	 * 
	* @Title: deleteSessionToken
	* @Description: 退出 清空sessionToken
	* @return boolean    返回类型
	* @throws
	 */
	public boolean deleteSessionToken(String key){	
		try {
			if (stringredisTemplate.hasKey(key)) {
				stringredisTemplate.delete(key);
			}
		} catch (Exception e) {
			log.error("quit clear session fail!", e);
		}
		return true;
	}
	
	
	/**
	 * 
	* @Title: getStringForValue
	* @Description: 查询缓存数据 String
	* @return Object  返回类型
	* @throws
	 */
	public Object getStringForValue(String key){
		if(StringUtils.isNotEmpty(key)){
			try {
				if(stringredisTemplate.hasKey(key)){
					return stringredisTemplate.opsForValue().get(key);
				}
			} catch (Exception e) {
				log.error("getStringForValue fail!", e);
			}
		}
		return null;
	}
	
	/**
	 * 
	* @Title: setStringForValue
	* @Description: 设置缓存数据 String
	* @return void  返回类型
	* @throws
	 */
	public void setStringForValue(String key,String value,int entyrnyTime,TimeUnit unit){
		try {
			stringredisTemplate.opsForValue().set(key, value);
			if(entyrnyTime!=0){
			boolean success=stringredisTemplate.expire(key, entyrnyTime, unit);
			log.info(key +" set expire:--"+success+"-");
			}
		} catch (Exception e) {
			log.error("setStringForValue fail!", e);
			stringredisTemplate.delete(key);
		}		
	}
	
	/**
	 * 
	* @Title: getMapForValue
	* @Description: 查询缓存数据 Map
	* @return Object  返回类型
	* @throws
	 */
	public Object getMapForValue(String key){
		try {
			if(stringredisTemplate.hasKey(key)){
				return stringredisTemplate.opsForHash().entries(key);
			}
		} catch (Exception e) {
			log.error("getMapForValue fail!", e);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: setMapForValue
	* @Description: 设置缓存数据 Map
	* @return void  返回类型
	* @throws
	 */
	public void setMapForValue(String key,Map<String, Object> value,int entyrnyTime,TimeUnit unit){
		try {
			stringredisTemplate.opsForHash(). putAll(key, value);
			if(entyrnyTime!=0){
			boolean success=stringredisTemplate.expire(key, entyrnyTime, unit);
			log.info(key +" set expire:--"+success+"-");
			}
		} catch (Exception e) {
			log.error("setMapForValue fail!", e);
			stringredisTemplate.delete(key);
		}		
	}
	
	public void setMapForObject(String key,Map<Object, Object> value,int entyrnyTime,TimeUnit unit){
		try {
			stringredisTemplate.opsForHash(). putAll(key, value);
			if(entyrnyTime!=0){
			boolean success=stringredisTemplate.expire(key, entyrnyTime, unit);
			log.info(key +" set expire:--"+success+"-");
			}
		} catch (Exception e) {
			log.error("setMapForValue fail!", e);
			stringredisTemplate.delete(key);
		}		
	}
	
	/**
	 * 
	* @Title: setMapForValue
	* @Description: 设置缓存数据List
	* @return void  返回类型
	* @throws
	 */
	public void setListForExpire(String key,int entyrnyTime,TimeUnit unit){
		try {
			if(entyrnyTime!=0){
			boolean success=stringredisTemplate.expire(key, entyrnyTime, unit);
			log.info(key +" set expire:--"+success+"-");
			}
		} catch (Exception e) {
			log.error("setMapForValue fail!", e);
			stringredisTemplate.delete(key);
		}		
	}
	
	/**
	 * 
	* @Title: getListForExpire
	* @Description:  查询缓存数据 List
	* @return void  返回类型
	* @throws
	 */
	public Object getListForValue(String key){
		try {
			return stringredisTemplate.opsForList().range(key, 0, -1);
		} catch (Exception e) {
			log.error("getListForExpire fail!", e);
			return null;
		}		
	}
	
	/**
	 *list逐个存入redis
	 */
	public void setListForValue(String key,String json){
		try {
			stringredisTemplate.opsForList().leftPush(key,json);
		} catch (Exception e) {
			log.error("setList fail!", e);
		}		
	}
	
	/**
	 * set逐个存入redis，加时间戳date
	 */
	public void setZSetForValue(String key,String json,double date){
		try {
			stringredisTemplate.opsForZSet().add(key, json, date);
		} catch (Exception e) {
			log.error("setSet fail!", e);
			stringredisTemplate.delete(key);
		}		
	}
	/**
	 * 
	* @Title: getZSetForValue
	* @Description: 查询缓存数据ZSet
	* @return Set<String>    返回类型
	* @throws
	 */
	public Set<String> getZSetForValue(String key,long start , long end){
		try {
			return stringredisTemplate.opsForZSet().range(key, start, end);
		} catch (Exception e) {
			log.error("setSet fail!", e);
			
		}
		return null;
	}
	public void deleteZSet(String key,long start ,long end){
		try {
			stringredisTemplate.opsForZSet().removeRangeByScore(key, start, end);
		} catch (Exception e) {
			log.error("deleteZSet fail!", e);
			
		}
	}
	
	
	
	/**
	 * ZSet失效时间设置
	 */
	public void setZSetForExpire(String key,int entyrnyTime,TimeUnit unit){
		try {
			if(entyrnyTime!=0){
			boolean success=stringredisTemplate.expire(key, entyrnyTime, unit);
			log.info(key +" set expire:--"+success+"-");
			}
		} catch (Exception e) {
			log.error("setZset fail!", e);
			stringredisTemplate.delete(key);
		}	
	}
	
	
	
	/**
	 * 
	* @Title: getExpire
	* @Description: 处理redis缓存相对一些key未设置失效时间，进行删除处理
	* @return void    返回类型
	* @throws
	 */
	public void getExpire(String key){
		/**
		 * stringredisTemplate.getExpire(key) 底层实现ttl,以秒为单位，返回给定 key 的剩余生存时间
		    当 key 不存在时，返回 -2 。
		    当 key 存在但没有设置剩余生存时间时，返回 -1 。
		    否则，以秒为单位，返回 key 的剩余生存时间。
		 */
		if(stringredisTemplate.getExpire(key)==-1){
			stringredisTemplate.delete(key);
			log.info("删除key:"+key);
		}
	}
	
	
	/**
	 * 简单的redis锁
	 * @param key  加锁的key
	 * @param timeout 毫秒
	 * @return
	 */
	public String lock(String key,long timeout) {
        long nano = System.nanoTime();
        //输入的timeout转化为纳秒
        timeout *= 1000000;
        final Random r = new Random();
        String uuid =FrameUtil.getUUID();
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (stringredisTemplate.getConnectionFactory().getConnection().setNX(key.getBytes(), uuid.getBytes())) {
                    stringredisTemplate.expire(key, 1, TimeUnit.MINUTES);
                    log.info("add RedisLock[" + key + "].");
                    return uuid;
                }
                if(stringredisTemplate.getConnectionFactory().getConnection().ttl(key.getBytes())==-1L){
                    stringredisTemplate.expire(key, 1, TimeUnit.MINUTES);
                }
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
            log.error("add RedisLock[" + key + "] fail!!");
        }
        return null;
    }
    /**删除redis锁
     * 
     * @param key
     * @param identifier
     */
    public void unlock(String key,String identifier) {
        byte[] value = stringredisTemplate.getConnectionFactory().getConnection().get(key.getBytes());
        if(value.length==0){
            return;
        }
        String str = new String(value);
        if (identifier.equals(str)) {
            log.info("release RedisLock[" + key + "].");
            stringredisTemplate.delete(key);
        }
    }
	
	
	
}
