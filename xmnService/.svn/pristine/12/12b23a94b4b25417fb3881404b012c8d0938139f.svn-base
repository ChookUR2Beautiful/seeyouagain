package com.xmniao.xmn.core.live.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：RandomDraw
 * @类描述：根据概率获取奖品的测试类
 * @创建人： yeyu
 * @创建时间 2016年8月22日 下午2:53:50
 * @version
 */
public class RandomGift {
   
	private static Logger log=Logger.getLogger(RandomGift.class);
	/**
	 * 
	* @Title: getGumDrawMap
	* @Description: 抽奖规则,暂不使用
	* @return Map<Object,Object>
	 */
	public static Map<Object,Object> getGumDrawMap(String randomkey,StringRedisTemplate stringRedisTemplate,String uid) throws Exception {
		Map<Object,Object> resultMap=new HashMap<>();
		try{
			//从redis中读取缓存的大礼包中的奖品
			Map<Object,Object> paramMap=(Map<Object, Object>) JSONArray.parse(stringRedisTemplate.opsForValue().get(randomkey));
			//从redis中读取缓存的已抽奖的人
			Map<Object,Object> persongMap=(Map<Object, Object>) JSONArray.parse(stringRedisTemplate.opsForValue().get(randomkey+"persongMap"));
			if(paramMap==null || paramMap.size()<=0){
				return paramMap;
			}
			
			//判断抽奖人是否已经抽过奖
			if(persongMap!=null && persongMap.get(randomkey+uid)!=null){
				resultMap.put("state", "0");
				resultMap.put("msg", "已领取过");
				return resultMap;
			}
			//获取缓存中所有的key
			Set set=paramMap.keySet();
			Iterator it=set.iterator();
			//按照礼物数量抽取随机数
			int random = new Random().nextInt(set.size());
			int i=0;
			int sum=0;
			int prize_nums=0;
			//获得大礼包中所有的礼物总数
			while(it.hasNext()){
				
				
				String key=(String) it.next();
				Map<Object,Object> param=(Map<Object, Object>) paramMap.get(key);
				Integer gift_nums= param.get("gift_nums")==null?0:Integer.parseInt(param.get("gift_nums").toString());
				prize_nums+=gift_nums;
			}
			Iterator itt=set.iterator();
			//开始抽奖
			while(itt.hasNext()){
				String key=(String) itt.next();
				//根据保存的key取出map中对象
				Map<Object,Object> param=(Map<Object, Object>) paramMap.get(key);
				
				//获取当前大礼包的奖品已被领取的总数
				Integer current= param.get("current")==null?0:Integer.parseInt(param.get("current").toString());
				sum+=current;
				
				//如果i等于随机数，就开始抽奖
				if(i==random){
					//判断当前礼物的总数
			    	if(param.get("gift_nums")!=null){
			    		//判断当前礼物的总数和当前礼物被领取数
						if(param.get("current")!=null && param.get("gift_nums")!=null){
							//如果当前被领取的数量大于等于礼物总数,就不能再抽取该礼物，开始递归重新抽取
						if(Integer.parseInt(param.get("current").toString())>=Integer.parseInt(param.get("gift_nums").toString())){
							//判断大礼包中礼物被领取的总数如果等于礼物的总数，则返回提示“抱歉,奖品已被领光了”
							if(sum==prize_nums){
								resultMap.put("state", "0");
								resultMap.put("msg", "抱歉,礼品已被领光了");
								return resultMap;
							}
							//开始递归抽奖
							return RandomGift.getGumDrawMap(randomkey, stringRedisTemplate,uid);
						}
					}
						//存储领奖人的uid
					if(persongMap==null){
						persongMap=new HashMap<>();
					}
					//将抽奖人的信息存入map中
					persongMap.put(randomkey+uid, uid);
					
					//当前礼物被抽取的到后加1，并存储到当前map对象中
					param.put("current", Integer.parseInt(param.get("current").toString())+1);
					paramMap.put(key, param);
					//获取剩余时间
					Long remaining=stringRedisTemplate.getExpire(randomkey);
					//将奖品当前的信息重新填充redis
					stringRedisTemplate.opsForValue().set(randomkey, JSONArray.toJSONString(paramMap));
					//将已经领取过奖品的用户的对象缓存到redis中
					stringRedisTemplate.opsForValue().set(randomkey+"persongMap", JSONArray.toJSONString(persongMap));
					//更新redis定时时间
					stringRedisTemplate.expire(randomkey, remaining, TimeUnit.SECONDS);
					stringRedisTemplate.expire(randomkey+"persongMap", remaining, TimeUnit.SECONDS);
					
					//抽奖成功后将信息存储到map对象中，并返回
					resultMap.put("state", "1");//状态
					resultMap.put("gift_type", param.get("gift_type").toString());//奖品类型，是礼物（1），还是积分（2）
					resultMap.put("gift_id", param.get("gift_id").toString());//礼物id
					resultMap.put("gift_name", param.get("gift_name").toString());//礼品名称
					resultMap.put("gift_price", param.get("gift_price"));//积分数量
					
					
				
					
					
					
					return resultMap;
				}
			    }
				i++;
			}
		}catch(Exception e){
			log.error("抽取礼品异常,联系管理员");
			e.printStackTrace();
			throw new Exception("抽取礼品异常,联系管理员");
		}
		//未抽中
		resultMap.put("state", "0");
		resultMap.put("msg", "抽取礼品已结束");
		return resultMap;
    }

	
	
	
	/**
	 * 
	* @Title: getGumDrawMap2
	* @Description: 抽取礼品2
	* @return Map<Object,Object>
	 */
	public static Map<Object,Object> getGumDrawMap2(String randomkey,StringRedisTemplate stringRedisTemplate,String uid) throws Exception {
		Map<Object,Object> resultMap=new HashMap<>();
		try{
			//领取过的用户key
			String userkey=randomkey+"_persongMap_"+uid;
			//获取领取过礼品的人员次数//抽奖的uid
			long userNums=stringRedisTemplate.opsForValue().increment(userkey,1);
			//判断抽奖人是否已经抽过奖
			if(userNums>1){
				resultMap.put("state", "0");
				resultMap.put("msg", "已领取过");
				return resultMap;
			}
			//从redis中读取缓存的大礼包中的奖品
			Map<Object,Object> paramMap=stringRedisTemplate.opsForHash().entries(randomkey);
			//按照礼物数量抽取随机数
			int random = new Random().nextInt(paramMap.entrySet().size());
			int i=0;
			
			//开始抽奖
			for(Map.Entry<Object, Object> entry:paramMap.entrySet()){
				//如果i等于随机数，就开始抽奖
				if(i==random){
					String key=	(String) entry.getKey();
					//根据保存的key取出map中对象
					Map<Object,Object> param=(Map<Object, Object>) JSONArray.parse(paramMap.get(key).toString());
					//判断当前礼物的总数是否为空
			    	if(param.get("gift_nums")!=null){
					//获取礼物ID
					String gift_id=param.get("gift_id").toString();
					String incrkey="incr_"+randomkey+"_"+gift_id;
					//获取某个礼品领取多少次
					Long current=stringRedisTemplate.opsForValue().increment(incrkey, 1);
					
					//如果该礼物数量已经被领光，这是删除该礼物缓存
					if(current>Integer.parseInt(param.get("gift_nums").toString())){
					//paramMap.remove(key);
					//未抽中
					resultMap.put("state", "0");
					resultMap.put("msg", "未抽中礼品");
					}else{
						//抽奖成功后将信息存储到map对象中，并返回
					resultMap.put("state", "1");//状态
					resultMap.put("gift_type", param.get("gift_type").toString());//奖品类型，是礼物（1），还是积分（2）
					resultMap.put("gift_id", param.get("gift_id").toString());//礼物id
					resultMap.put("gift_name", param.get("gift_name").toString());//礼品名称
					resultMap.put("gift_price", param.get("gift_price"));//积分数量
					resultMap.put("gift_bag_id", param.get("gift_bag_id"));//礼包id
					resultMap.put("uid",uid);
					resultMap.put("randomKey", randomkey);
					resultMap.put("uuid",UUID.randomUUID().toString());
					paramMap.put(key, JSONArray.toJSONString(param));
					}
					
					//获取剩余时间
					Long remaining=stringRedisTemplate.getExpire(randomkey);
					//将奖品当前的信息重新填充redis
					stringRedisTemplate.opsForHash().putAll(randomkey, paramMap);

					//更新redis定时时间
					stringRedisTemplate.expire(incrkey, remaining, TimeUnit.SECONDS);
					stringRedisTemplate.expire(randomkey, remaining, TimeUnit.SECONDS);
					stringRedisTemplate.expire(userkey, remaining, TimeUnit.SECONDS);
					
					
					return resultMap;
				}
			    }
				i++;
			}
		}catch(Exception e){
			log.error("抽取礼品异常,联系管理员"+e.getMessage());
			e.printStackTrace();
			throw new Exception("抽取礼品异常,联系管理员");
		}
		//未抽中
		resultMap.put("state", "0");
		resultMap.put("msg", "抽取礼品已结束");
		return resultMap;
    }
	
    /**
	 * 
	* @Title: initRedisDrawMap
	* @Description: 初始化抽奖缓存内容到redis
	* @return void
	 * @throws Exception 
	 */
    public static void initRedisDrawMap(String randomkey,StringRedisTemplate stringRedisTemplate,Long timeout,List<Map<Object,Object>> resultList) throws Exception{
    	try{
	    	Map<Object,Object> param=new HashMap<Object, Object>();
	    	
			for(int i=0;i<resultList.size();i++){
				Map<Object,Object> resultMap=resultList.get(i);
				param.put(resultMap.get("prize_key"), JSONArray.toJSONString(resultMap));
			}
		
			//将大礼包中的礼品缓存至redis
			stringRedisTemplate.opsForHash().putAll(randomkey, param);
			//大礼包缓存10秒
			stringRedisTemplate.expire(randomkey, timeout, TimeUnit.SECONDS);
    	}catch(Exception e){
    		log.error("初始化礼品失败");
    		e.printStackTrace();
    		throw new Exception("初始化礼品失败");
    	}
    }
    
}
