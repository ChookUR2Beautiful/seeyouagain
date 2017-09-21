package com.xmniao.service.message.fault;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.domain.message.SendHttpRedisRequest;
import com.xmniao.service.common.CommonServiceImpl;

/**
 * 容错机制实现处理类
 * @author  LiBingBing
 * @version  [版本号, 2015年8月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FaultTolerantImpl implements FaultTolerant
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(FaultTolerantImpl.class);
    
    /**
     * 注入公共实现处理类
     */
    private CommonServiceImpl commonServiceImpl;
    
    /**
     * 注入向蜜客redis的key
     */
    private String mikeQueue;
    
    /**
     * 注入短信redis的key
     */
    private String smsQueue;
    
    /**
     * 注入推送消息redis的key
     */
    private String pushMsgQueue;
    
    /**
     * 注入自动分账redis的key
     */
    private String zdQueueKey;
    
    /**
     * 注入HTTP发送redis的URL
     */
    private String redisHttpURL;
    
    /**
     * 注入本地缓存
     */
    @Autowired
    private Cache dataCache;
    
    @Override
	public synchronized void faultTolerantProcess(String cacheKey) {
    	//容错处理方法
    	try {
    		// 获取Ecache缓存
    		Element cacheCode = dataCache.get(cacheKey);
    		/**
    		 * 迭代list集合中的数据，迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常
    		 * 这里使用CopyOnWriteArrayList来进行处理此情况出错
    		 */
    		CopyOnWriteArrayList<Map<String,String>> cl = null;
    		if(null!=cacheCode){
    			// 取出Ecache缓存中的内容
    			cl = (CopyOnWriteArrayList<Map<String,String>>) cacheCode.getObjectValue();
    		}		
    		log.info("faultTolerantProcess cache data size:"+cl.size());
    		
    		if (null != cl && cl.size() > 0) {
    			
    			for (Iterator it = cl.iterator(); it.hasNext();) {
    				// 转map数据
    				Map<String, String> content = (Map<String, String>) it.next();
    				//处理缓存中的数据
    				int tagNum = FaultTolerantSmsHandle(content);
    				// 处理成功移除缓存中的数据，或者把缓存中处理次数为5慈的删除
    				if (tagNum > 0 || Integer.valueOf(content.get("addNumber"))>4) {
    					log.info("success faultTolerantProcess cache content:"+content);
    					cl.remove(content);
    				}
    				if (cl.size() == 0) {
    					break;
    				}
    			}
    		}
    		if (cl.size() == 0) {
    			// 全部处理成功后,移除ehcache中数据
    			dataCache.remove(cacheKey);
    		}
    	} catch (IllegalStateException e) {
    		log.error("faultTolerantProcess IllegalStateException", e);
    	} catch (CacheException e) {
    		log.error("faultTolerantProcess CacheException", e);
    	}
	}
    
    /**
     * 
    * @Title: FaultTolerantSmsHandle
    * @Description: 处理短信发送redis失败,加入缓存中处理
    * @return void    返回类型 1发送成功
    * redisType:rpush,zadd
    * @throws
     */
    
	public int FaultTolerantSmsHandle(Map<String, String> content) {
		int tagNum = 0;
		if (null != content) {
			SendHttpRedisRequest reqBean = new SendHttpRedisRequest();			
			if (content.get("addNumber").equals("0")
					|| System.currentTimeMillis() >= Long.valueOf(content
							.get("dateTime"))) {
				//redis内容
				String smsObject = content.get("markContent");
				//redis数据类型
				String redisType = content.get("markKey").equals(zdQueueKey)?"zadd":"rpush";
				//redis地址
				reqBean.setRedisUrl(redisHttpURL);
				//redis 数据类型
				reqBean.setRedisType(redisType);
				//redis的存储key
				reqBean.setRedisKey(content.get("markKey"));
				//redis的value
				reqBean.setRedisContent(smsObject);
				try {
					// 组装HTTP方式发送redis的URL地址 
					String connUrl = reqBean.getRedisUrl() + "?fun="
							+ reqBean.getRedisType() + "&key="
							+ reqBean.getRedisKey() + "&value="
							+ reqBean.getRedisContent();
					if(redisType.equals("zadd")){
						JSONObject zdLedgerJson = JSONObject
								.parseObject(smsObject);
						// 组装HTTP方式发送redis的URL地址
						connUrl = reqBean.getRedisUrl() + "?fun="
								+ reqBean.getRedisType() + "&key="
								+ reqBean.getRedisKey() + "&score="
								+ zdLedgerJson.getDouble("order_number")
								+ "&value=" + reqBean.getRedisContent();
					}		
					log.info("faultTolerantProcess cache data sendHttpRedis::" + content);
					// 调用HTTP方式发送redis缓存处理方法
					String result = commonServiceImpl.sendHttpRedis(connUrl);
					if (StringUtils.isNotBlank(result)) {
						tagNum = 1;
					}else{
						ModifyTimeAndNum(content);
					}
				} catch (Exception e) {
					ModifyTimeAndNum(content);
					return 0;
				}
			}
		}
		return tagNum;
	}
	
	/**
	 * 
	* @Title: ModifyTimeAndNum
	* @Description: 修改处理失败数据的时间和次数，以便下一次执行
	* @return void    返回类型
	* @throws
	 */
	public void ModifyTimeAndNum(Map<String, String> content){
		if (null != content) {
			content.put("dateTime", String.valueOf(Long.valueOf(content
					.get("dateTime")) + 5000));
			content.put("addNumber", String.valueOf(Integer
					.valueOf(content.get("addNumber")) + 1));
		}
	}
	
    public String getMikeQueue()
    {
        return mikeQueue;
    }
    
    public void setMikeQueue(String mikeQueue)
    {
        this.mikeQueue = mikeQueue;
    }
    
    public String getSmsQueue()
    {
        return smsQueue;
    }
    
    public void setSmsQueue(String smsQueue)
    {
        this.smsQueue = smsQueue;
    }
    
    public String getPushMsgQueue()
    {
        return pushMsgQueue;
    }
    
    public void setPushMsgQueue(String pushMsgQueue)
    {
        this.pushMsgQueue = pushMsgQueue;
    }
    
    public String getZdQueueKey()
    {
        return zdQueueKey;
    }
    
    public void setZdQueueKey(String zdQueueKey)
    {
        this.zdQueueKey = zdQueueKey;
    }
    
    public CommonServiceImpl getCommonServiceImpl()
    {
        return commonServiceImpl;
    }
    
    public void setCommonServiceImpl(CommonServiceImpl commonServiceImpl)
    {
        this.commonServiceImpl = commonServiceImpl;
    }
    
    public String getRedisHttpURL()
    {
        return redisHttpURL;
    }
    
    public void setRedisHttpURL(String redisHttpURL)
    {
        this.redisHttpURL = redisHttpURL;
    }
}
