package com.xmniao.service.message.seller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.service.common.MongoBaseService;
import com.xmniao.service.live.LiveOrderServiceImpl;
import com.xmniao.thrift.busine.common.ResponseData;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserActionImpl
 * 
 * 类描述： 更新商家信息MONGODB内容处理
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月10日 下午3:27:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SellerInfoImpl
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerInfoImpl.class);
    
    /**
     * 商家信息更新redis队列KEY
     */
    @Resource(name="sellerInfoQueue")
    private String sellerInfoQueue;
    
    @Resource(name="sellerMongo")
    private String sellerCollection;
    
    @Resource(name="sellerRandomNum")
    private String sellerRandomNum;
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 注入Mongo操作
     */
    @Autowired
    private MongoBaseService mongoService;
    
    @Autowired
    private LiveOrderServiceImpl liveService;
    
    @SuppressWarnings("unchecked")
    public void handleMessage(String reqJson)
    {
    	
        if (StringUtils.isNotBlank(reqJson)){
        	log.info("接收到商户信息更新队列数据："+reqJson);
        	Map<String,String> actionMap = (Map<String,String>)JSONObject.parse(reqJson);
			try{
				if(actionMap.get("sellerid")==null
				|| actionMap.get("infoType")==null){
					log.error("错误数据，无法处理，直接废弃");
					return;
				}
				
				Criteria criteria = Criteria.where("sellerid").is(Integer.parseInt(actionMap.get("sellerid")));
				Map<String,Object> uMap = new HashMap<String,Object>();
				Map<String,Object> sellerMap = mongoService.findOne(sellerCollection, criteria, HashMap.class);
				if(sellerMap==null){
					log.info("在MongoDB中找不到该商家信息");
					return ;
				}
				if(actionMap.get("infoType").equals("1")){	//浏览
					int view = 1+ (int) sellerMap.get("views");
					uMap.put("views", view);
				}else if(actionMap.get("infoType").equals("2")){	//消费
					int consumption = 1+ (int) sellerMap.get("consumption");
					uMap.put("consumption", consumption);
					
					//更新虚拟消费数
					if(sellerMap.get("seller_random_num_consumption")!=null){
						uMap.put("seller_random_num_consumption",(Integer)sellerMap.get("seller_random_num_consumption")+1);
					}else{
						Integer sellerRandom = 100;
						Map<Object, Object> redisMap = redisTemplate.opsForHash().entries(actionMap.get("sellerid"));
						if(redisMap == null){
							redisMap = new HashMap<>();
						}
						if(redisMap.containsKey(actionMap.get("sellerid"))){//判断redis是否存在商家随机数，不存在创建
							sellerRandom = (Integer) redisMap.get(actionMap.get("sellerid"));
						}else{
							sellerRandom = (int)(Math.random()*400)+101;//200-500随机数
							redisTemplate.opsForHash().put(sellerRandomNum,actionMap.get("sellerid")+"",sellerRandom+"");
						}
						uMap.put("seller_random_num",sellerRandom+consumption);
						uMap.put("seller_random_num_consumption",(Integer)sellerMap.get("seller_random_num_consumption")+1);
					}
				}else if(actionMap.get("infoType").equals("3")){	//收藏
					int uSaved = Integer.parseInt(actionMap.get("operation"));
					int saved = (int) sellerMap.get("saved")+uSaved;
					uMap.put("saved", saved);
				}else if(actionMap.get("infoType").equals("4")	//直播-粉丝券-预告
					|| actionMap.get("infoType").equals("5")
					|| actionMap.get("infoType").equals("6")){
					Map<String,String> paramMap = new HashMap<String,String>();
					paramMap.put("sellerid", actionMap.get("sellerid"));
					paramMap.put("type", "0");
					ResponseData responseData = liveService.getSellerLiveCountInfo(paramMap);
					if(responseData!=null && responseData.getState()==0){
						if(responseData.getResultMap().get("hasLive").equals(sellerMap.get("is_live"))
						&& responseData.getResultMap().get("hasAdvance").equals(sellerMap.get("is_advance"))
						&& responseData.getResultMap().get("hasFansCoupon").equals(sellerMap.get("is_fans_coupon"))
						&& responseData.getResultMap().get("weights").equals(sellerMap.get("weights"))){//直播信息有变才更新
						}else{
							uMap.put("is_live", Integer.parseInt(responseData.getResultMap().get("hasLive")));
							uMap.put("is_advance", Integer.parseInt(responseData.getResultMap().get("hasAdvance")));
							uMap.put("is_fans_coupon", Integer.parseInt(responseData.getResultMap().get("hasFansCoupon")));
							uMap.put("weights", Integer.parseInt(responseData.getResultMap().get("weights")));
						}
					}
				}
				if(uMap.size()!=0){
					int version = sellerMap.get("version")==null?0:(int)sellerMap.get("version");
					uMap.put("version", (version+1));
					if(sellerMap.get("version")!=null){
						criteria = criteria.and("version").is(version);
					}
					int n = mongoService.updateOne(sellerCollection, criteria, uMap);
					if(n==0){
						log.info("写入失败");
						new Exception("本次更新影响了0行数据，重试");
					}
				}
			}catch(Exception e){
				try{
					int retry = actionMap.get("retry")==null?0:Integer.parseInt(actionMap.get("retry"));
					if(retry>=2){
						log.error(reqJson+",该消息多次重试后，仍不成功，舍弃！");
					}else{
						log.error("更新商家处理异常,插入列队尾部重试",e);
						retry++;
						actionMap.put("retry", retry+"");
						redisTemplate.opsForList().leftPush(sellerInfoQueue, JSONObject.toJSONString(actionMap));
					}
				}catch(Exception e2){
					log.error("更新商家信息处理失败重插队尾失败,",e2);
				}
			}
        }
    }


	public Map<String,String> upateSellerInfoToMongo(String collectionName,Query query,Map<String,Object> uMap){
		
		
		return null;
	}

	
}
