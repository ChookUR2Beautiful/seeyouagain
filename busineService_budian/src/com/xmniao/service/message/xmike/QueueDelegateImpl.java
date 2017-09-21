package com.xmniao.service.message.xmike;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.service.common.CommonServiceImpl;

/**
 * 消息处理实现类
 * @author  LiBingBing
 * @version  [版本号, 2015年2月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QueueDelegateImpl implements QueueDelegate
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(QueueDelegateImpl.class);
    
    /**
     * 注入通用服务接口DAO层
     */
    private CommonServiceImpl commonServiceImpl;
    
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 向蜜客处理队列
     */
    private String mikeQueue;
    
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    public QueueDelegateImpl()
    {
        log.info("Redis监听已启动...");
    }
    
    /**
     * 消息队列业务逻辑处理
     * @param reqJson
     * @throws Exception
     */
    @Override
    public void handleMessage(String reqJson) throws Exception
    {
        log.info("handleMessage start:" + reqJson);
        if(StringUtils.isNotBlank(reqJson))
        {
            JSONObject redisJson = JSONObject.parseObject(reqJson);
            //将JSON数据转换为MAP
            Map<String,Object> reqMikeMap = redisJson;
            //此处调用用户中心服务接口
            String resFlag = commonServiceImpl.modifyUserCenter(reqMikeMap);
            //先非空判断,再判断status返回是否为true
            if (StringUtils.isNotEmpty(resFlag))
            {
                JSONObject resObject = JSONObject.parseObject(resFlag);
                if (resObject.getBoolean("status")==false)
                {
                    reqMikeMap.put("addNum", redisJson.getInteger("addNum")+1);
                    if(Integer.valueOf(String.valueOf(reqMikeMap.get("addNum")))<=4)
                    {
                        reqJson = JSONObject.toJSONString(reqMikeMap);
                        //将调用用户中心服务的JSON数据放到redis队列中
                        redisTemplate.opsForList().rightPush(mikeQueue, reqJson);
                    }
                }
            }else
            {
                reqMikeMap.put("addNum", redisJson.getInteger("addNum")+1);
                if(Integer.valueOf(String.valueOf(reqMikeMap.get("addNum")))<=4)
                {
                    reqJson = JSONObject.toJSONString(reqMikeMap);
                    //将调用用户中心服务的JSON数据放到redis队列中
                    redisTemplate.opsForList().rightPush(mikeQueue, reqJson);
                }
            }
        }
        log.info("handleMessage end......");
    }
    public CommonServiceImpl getCommonServiceImpl()
    {
        return commonServiceImpl;
    }
    public void setCommonServiceImpl(CommonServiceImpl commonServiceImpl)
    {
        this.commonServiceImpl = commonServiceImpl;
    }

    public String getMikeQueue()
    {
        return mikeQueue;
    }

    public void setMikeQueue(String mikeQueue)
    {
        this.mikeQueue = mikeQueue;
    }
}
