package com.xmniao.service.message.useraction;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.domain.message.MXmnSeller;
import com.xmniao.domain.message.MXmnTrade;
import com.xmniao.domain.message.MXmnZone;
import com.xmniao.service.common.MongoBaseService;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserActionImpl
 * 
 * 类描述： 更新用户浏览、消费的数据统计
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月10日 下午3:27:19 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class UserActionImpl implements UserAction
{
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(UserActionImpl.class);
    
    /**
     * 用户行为redis队列KEY
     */
    @Resource(name="userActionQueue")
    private String userActionQueue;
    
    @Resource(name="sellerAction")
    private String sellerAction;
    
    @Resource(name="zoneAction")
    private String zoneAction;
    
    @Resource(name="tradeAction")
    private String tradeAction;
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
    
    @SuppressWarnings("unchecked")
	@Override
    public void handleMessage(String reqJson)
    {

        if (StringUtils.isNotBlank(reqJson)){
        	log.info("接收到用户行为队列数据："+reqJson);
        	Map<String,String> actionMap = (Map<String,String>)JSONObject.parse(reqJson);
			try{
				if(StringUtils.isBlank(actionMap.get("actiontype"))
					|| StringUtils.isBlank(actionMap.get("uid"))
					|| StringUtils.isBlank(actionMap.get("xmntype"))
					|| StringUtils.isBlank(actionMap.get("operate"))){
					log.error("错误数据，无法处理，直接废弃");
				}else if(actionMap.get("actiontype").equals("1")){
					MXmnSeller mXmnSeller = new MXmnSeller();
					mXmnSeller.setUid(Integer.parseInt(actionMap.get("uid")));
					mXmnSeller.setXmntype(Integer.parseInt(actionMap.get("xmntype")));
					mXmnSeller.setSellerid(Integer.parseInt(actionMap.get("sellerid")));
					mXmnSeller.setOperate(Integer.parseInt(actionMap.get("operate")));
					mXmnSeller.setLast_time(actionMap.get("lastTime"));
					mXmnSeller.setIndex(1);//表示记录+1
					mXmnSeller.setVersion(1);//表示版本号+1
					if(mXmnSeller.getOperate()==3){
						upateSellerSaveActionToMongo(sellerAction,mXmnSeller,Integer.parseInt(actionMap.get("update"))>0?1:0);
					}else{
						upateSellerActionToMongo(sellerAction,mXmnSeller);
					}
					
				}else if(actionMap.get("actiontype").equals("2")){
					MXmnZone mXmnZone = new MXmnZone();
					mXmnZone.setUid(Integer.parseInt(actionMap.get("uid")));
					mXmnZone.setXmntype(Integer.parseInt(actionMap.get("xmntype")));
					mXmnZone.setZoneid(Integer.parseInt(actionMap.get("zoneid")));
					mXmnZone.setOperate(Integer.parseInt(actionMap.get("operate")));
					mXmnZone.setLast_time(actionMap.get("lastTime"));
					mXmnZone.setIndex(1);
					mXmnZone.setVersion(1);
					upateZoneActionToMongo(zoneAction,mXmnZone);
				}else if(actionMap.get("actiontype").equals("3")){
					MXmnTrade mXmnTrade = new MXmnTrade();
					mXmnTrade.setUid(Integer.parseInt(actionMap.get("uid")));
					mXmnTrade.setXmntype(Integer.parseInt(actionMap.get("xmntype")));
					mXmnTrade.setCategory(Integer.parseInt(actionMap.get("category")));
					mXmnTrade.setGenre(Integer.parseInt(actionMap.get("genre")));
					mXmnTrade.setLast_time(actionMap.get("lastTime"));
					mXmnTrade.setOperate(Integer.parseInt(actionMap.get("operate")));
					mXmnTrade.setIndex(1);
					mXmnTrade.setVersion(1);
					upateTradeActionToMongo(tradeAction,mXmnTrade);
				}
			}catch(Exception e){
				log.info("更新用户行为异常。。。",e);
				try{
					int retry = actionMap.get("retry")==null?0:Integer.parseInt(actionMap.get("retry"));
					if(retry>=2){
						log.error(reqJson+",该消息多次重试后，仍不成功，舍弃！");
					}else{
						log.error("用户行为处理异常,插入列队尾部重试",e);
						retry++;
						actionMap.put("retry", retry+"");
						redisTemplate.opsForList().leftPush(userActionQueue, JSONObject.toJSONString(actionMap));
					}
				}catch(Exception e2){
					log.error("用户行为处理失败重插队尾失败,",e2);
				}
			}
        }
        log.info("pushMsg handleMessage end");
    }

	public String getUserActionQueue() {
		return userActionQueue;
	}

	public void setUserActionQueue(String userActionQueue) {
		this.userActionQueue = userActionQueue;
	}

	public Map<String,String> upateSellerActionToMongo(String collectionName,MXmnSeller seller){
		//1.查询当前信息
		//2.更新
		//2.1 若没有,则新建
		//2.2 若有,则+1
		//3.验证结果,失败或冲突则进入redis重试
		
		Criteria criteria = Criteria.where("uid").is(seller.getUid()).and("sellerid").is(seller.getSellerid()).and("operate").is(seller.getOperate());
		MXmnSeller actionObject = mongoService.findOne(collectionName,criteria, seller.getClass());
		if(actionObject==null){
			mongoService.insertOrUpdate(collectionName, seller);
		}else{
			//用户角色有变化
			if(seller.getXmntype()!=null && (actionObject.getXmntype()==null || seller.getXmntype()!=actionObject.getXmntype())){
				Map<String,Object> uMap = new HashMap<String,Object>();
				uMap.put("index", actionObject.getIndex()+1);
				uMap.put("version", actionObject.getIndex()+1);
				uMap.put("xmntype", seller.getXmntype());
				criteria = criteria.and("version").is(actionObject.getVersion());
				int result = mongoService.updateOne(collectionName, criteria, uMap);
				if(result==0){
					new Exception("本次更新发生冲突");
				}
				
				changeUserType(seller.getUid(),seller.getXmntype());
			}else{
				Map<String,Object> uMap = new HashMap<String,Object>();
				uMap.put("index", actionObject.getIndex()+1);
				uMap.put("version", actionObject.getIndex()+1);
				uMap.put("last_time", seller.getLast_time());
				criteria = criteria.and("version").is(actionObject.getVersion());
				int result = mongoService.updateOne(collectionName, criteria, uMap);
				if(result==0){
					new Exception("本次更新发生冲突");
				}
//				Map<String,Number> incMap = new HashMap<String,Number>();
//				incMap.put("index", 1);
//				incMap.put("version", 1);
//				mongoService.updateInc(collectionName, criteria, incMap);
			}
		}
		log.info("更新用户行为统计至MongoDB完成");
		return null;
	}
	
	/*用户关注或取消关注商家*/
	public Map<String,String> upateSellerSaveActionToMongo(String collectionName,MXmnSeller seller,int add){
		//1.查询当前信息
		//2.更新
		//2.1 若没有,则新建
		//2.2 若有,则+1
		//3.验证结果,失败或冲突则进入redis重试
		log.info("更新用户收藏/取消收藏商家信息");

		Criteria criteria = Criteria.where("uid").is(seller.getUid()).and("sellerid").is(seller.getSellerid()).and("operate").is(seller.getOperate());
		MXmnSeller actionObject = mongoService.findOne(collectionName,criteria, seller.getClass());
		if(actionObject==null){
			if(add==1){
				mongoService.insertOrUpdate(collectionName, seller);
			}
		}else{
			if(add==0){
				mongoService.delete(collectionName, criteria);
			}
		}
		return null;
	}
	
	public Map<String,String> upateZoneActionToMongo(String collectionName,MXmnZone zone){
		//1.查询当前信息
		//2.更新
		//2.1 若没有,则新建
		//2.2 若有,则+1
		//3.验证结果,失败或冲突则进入redis重试
		
		Criteria criteria = Criteria.where("uid").is(zone.getUid()).and("zoneid").is(zone.getZoneid()).and("operate").is(zone.getOperate());
		MXmnZone actionObject = mongoService.findOne(collectionName,criteria, zone.getClass());
		if(actionObject==null){
			mongoService.insertOrUpdate(collectionName, zone);
		}else{
			if(zone.getXmntype() != null && (actionObject.getXmntype()==null || zone.getXmntype()!=actionObject.getXmntype())){
				Map<String,Object> uMap = new HashMap<String,Object>();
				uMap.put("index", actionObject.getIndex()+1);
				uMap.put("version", actionObject.getIndex()+1);
				uMap.put("xmntype", zone.getXmntype());
				criteria = criteria.and("version").is(actionObject.getVersion());
				int result = mongoService.updateOne(collectionName, criteria, uMap);
				if(result==0){
					new Exception("本次更新发生冲突");
				}
				changeUserType(zone.getUid(),zone.getXmntype());
			}else{
//				Map<String,Number> incMap = new HashMap<String,Number>();
//				incMap.put("index", 1);
//				incMap.put("version", 1);
//				mongoService.updateInc(collectionName, criteria, incMap);
				Map<String,Object> uMap = new HashMap<String,Object>();
				uMap.put("index", actionObject.getIndex()+1);
				uMap.put("version", actionObject.getIndex()+1);
				uMap.put("last_time", zone.getLast_time());
				criteria = criteria.and("version").is(actionObject.getVersion());
				int result = mongoService.updateOne(collectionName, criteria, uMap);
				if(result==0){
					new Exception("本次更新发生冲突");
				}
			}
		}
		return null;
	}
	
	public Map<String,String> upateTradeActionToMongo(String collectionName,MXmnTrade trade){
		//1.查询当前信息
		//2.更新
		//2.1 若没有,则新建
		//2.2 若有,则+1
		//3.验证结果,失败或冲突则进入redis重试
		
		Criteria criteria = Criteria.where("uid").is(trade.getUid()).and("genre").is(trade.getGenre()).and("operate").is(trade.getOperate());
		MXmnTrade actionObject = mongoService.findOne(collectionName,criteria, trade.getClass());
		if(actionObject==null){
			mongoService.insertOrUpdate(collectionName, trade);
		}else{
			if(trade.getXmntype() != null && (actionObject.getXmntype()==null || trade.getXmntype()!=actionObject.getXmntype())){
				Map<String,Object> uMap = new HashMap<String,Object>();
				uMap.put("index", actionObject.getIndex()+1);
				uMap.put("version", actionObject.getIndex()+1);
				uMap.put("xmntype", trade.getXmntype());
				criteria = criteria.and("version").is(actionObject.getVersion());
				int result = mongoService.updateOne(collectionName, criteria, uMap);
				if(result==0){
					new Exception("本次更新发生冲突");
				}
				changeUserType(trade.getUid(),trade.getXmntype());
			}else{
				Map<String,Object> uMap = new HashMap<String,Object>();
				uMap.put("index", actionObject.getIndex()+1);
				uMap.put("version", actionObject.getIndex()+1);
				uMap.put("last_time", trade.getLast_time());
				criteria = criteria.and("version").is(actionObject.getVersion());
				int result = mongoService.updateOne(collectionName, criteria, uMap);
				if(result==0){
					new Exception("本次更新发生冲突");
				}
				
//				Map<String,Number> incMap = new HashMap<String,Number>();
//				incMap.put("index", 1);
//				incMap.put("version", 1);
//				mongoService.updateInc(collectionName, criteria, incMap);
			}
		}
		return null;
	}
	
	/**
	 * 当用户角色改变时，将Mongo中的所有该用户角色均更新
	 * @Title: changeUserType 
	 * @Description:
	 */
	private void changeUserType(int uid,int xmntype){
		Criteria criteria = Criteria.where("uid").is(uid);
		Map<String,Object> uMap = new HashMap<String,Object>();
		uMap.put("xmntype", xmntype);
		long u1 = mongoService.updateAll(sellerAction, criteria, uMap);
		long u2 = mongoService.updateAll(tradeAction, criteria, uMap);
		long u3 = mongoService.updateAll(zoneAction, criteria, uMap);
		log.info("用户【"+uid+"】类型变更为【"+xmntype+"】时共影响了"+(u1+u2+u3)+"条数据，其中商家记录"+u1+"条，分类记录"+u2+"条，商圈记录"+u3+"条");
	}
}
