/**    
 * 文件名：UserActionServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2016年11月29日    
 * Copyright ChenBo 
 * Corporation 2016     
 * 版权所有    
 *    
 */
package com.xmniao.service.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.domain.message.MXmnSeller;
import com.xmniao.domain.message.MXmnTrade;
import com.xmniao.domain.message.MXmnZone;
import com.xmniao.domain.seller.SellerBean;
import com.xmniao.service.common.MongoBaseService;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.userAction.UserActionService;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：UserActionServiceImpl
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年11月29日 下午6:46:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class UserActionServiceImpl implements UserActionService.Iface {

    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(UserActionServiceImpl.class);
    
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private OrderServiceDao orderDao;
    
    /**
     * 注入redis处理
     */
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private MongoBaseService mongoService;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Resource(name="userActionQueue")
    private String userActionQueue;
    
    @Resource(name="sellerInfoQueue")
    private String sellerInfoQueue;
    
    @Resource(name="sellerAction")
    private String sellerAction;
    
    @Resource(name="tradeAction")
    private String tradeAction;
    
    @Resource(name="zoneAction")
    private String zoneAction;
    
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 更新用户统计信息
	 */
	@Override
	public Map<String, String> userActionService(
			List<Map<String, String>> actionList) throws FailureException,
			TException {
		log.info("更新用户统计信息："+actionList);
		String nowDate = sdf.format(new Date());
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("state", "0");
		resultMap.put("msg", "处理成功");
		for(Map<String,String> actionMap:actionList){
			try{
				actionMap.put("lastTime", nowDate);
				actionMap.put("retry", "0");
				insertXmnActionRedis(userActionQueue,actionMap);
				if(actionMap.get("actiontype") !=null && actionMap.get("actiontype").equals("1")){
					Map<String,String> infoMap=new HashMap<String,String>();
					infoMap.put("sellerid", actionMap.get("sellerid"));
					infoMap.put("infoType", actionMap.get("operate"));
					infoMap.put("operation", actionMap.get("update"));
					if(actionMap.get("operate").equals("3")){
						Criteria criteria = Criteria.where("uid").is(Integer.parseInt(actionMap.get("uid"))).and("sellerid").is(Integer.parseInt(actionMap.get("sellerid"))).and("operate").is(Integer.parseInt(actionMap.get("operate")));
						MXmnSeller actionObject = mongoService.findOne(sellerAction,criteria, MXmnSeller.class);
						if(actionObject==null &&  Integer.parseInt(actionMap.get("update"))>0){//过滤同一用户对同一商户进行多次收藏
							insertSellerInfoRedis(sellerInfoQueue, infoMap);
						}else if(actionObject!=null &&  Integer.parseInt(actionMap.get("update"))<0){//过滤同一用户对同一商户进行多次取消
							insertSellerInfoRedis(sellerInfoQueue, infoMap);
						}
					}else{
						insertSellerInfoRedis(sellerInfoQueue, infoMap);
					}
					
				}
			}catch(Exception e){
				log.error("处理异常",e);
			}
		}
		
		return resultMap;
	}
		
    /**
     * 更新用户行为队列
     */
    public void insertXmnActionRedis(String key,Map<String,String> actionMap){
    	redisTemplate.opsForList().leftPush(key, JSONObject.toJSONString(actionMap));
    }
    
    /**
     * 更新商家信息队列
     */
    public void insertSellerInfoRedis(String key,Map<String,String> infoMap){
    	redisTemplate.opsForList().leftPush(key, JSONObject.toJSONString(infoMap));
    }
    
	/**
	 * 更新用户统计信息
	 */
	public Map<String, String> initUserSellerActionService() throws FailureException,
			TException {
		log.info("更新用户-商家统计信息：");
		String now = sdf.format(new Date());
		int pageNo=0;
		int pageSize=1000;
		Map<String,Object> selMap = new HashMap<String,Object>();
		selMap.put("isonline", 1);
		selMap.put("pageSize", pageSize);
		List<SellerBean> sellerList = null;
		
		/*先删除旧内容*/
		mongoService.delete(sellerAction, new Criteria());
		do{ 
			selMap.put("pageNo", pageNo);
			sellerList = sellerDao.getSellerList(selMap);
			pageNo++;

			for(SellerBean seller:sellerList){
				int subPageNo=0;
				int subPageSize=1000;
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sellerid", seller.getSellerid());
				map.put("pageSize", subPageSize);
				List<Map<String,Object>> userList = null;
				do{
					userList=null;
					map.put("pageNo", subPageNo);
					userList = orderDao.getUserCountBySeller(map);
					subPageNo++;
					List<MXmnSeller> uList = new ArrayList<MXmnSeller>();
					for(Map<String,Object> userOrder:userList){
						if(userOrder.get("uid")==null){
							continue;
						}
						if(((Long)userOrder.get("views")).intValue()>0){
						MXmnSeller xmnSeller = new MXmnSeller();
						xmnSeller.setIndex(((Long)userOrder.get("views")).intValue());
						xmnSeller.setLast_time(now);
						xmnSeller.setOperate(1);
						xmnSeller.setSellerid((int)seller.getSellerid());
						xmnSeller.setUid((int)userOrder.get("uid"));
						xmnSeller.setVersion(1);
						xmnSeller.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
						uList.add(xmnSeller);
						}
						
						if(((Long)userOrder.get("consumption")).intValue()>0){
						MXmnSeller xmnSeller2 = new MXmnSeller();
						xmnSeller2.setIndex(((Long)userOrder.get("consumption")).intValue());
						xmnSeller2.setLast_time(now);
						xmnSeller2.setOperate(2);
						xmnSeller2.setSellerid((int)seller.getSellerid());
						xmnSeller2.setUid((int)userOrder.get("uid"));
						xmnSeller2.setVersion(1);
						xmnSeller2.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
						uList.add(xmnSeller2);
						}
						
						
					}
					
					//1.更新用户-商家数据
					mongoService.insertAll(sellerAction, uList);
					log.info("商家"+seller.getSellerid()+"拿到"+userList.size()+"条数据");
				}while(userList.size()>=subPageSize);
			}
			log.info("上线商家拿到"+sellerList.size()+"条数据");
		}while(sellerList.size()>=pageSize);
		
		//初始化收藏信息
		int subPageNo=0;
		int subPageSize=1000;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageSize", subPageSize);
		List<Map<String,Object>> savedList = null;
		do{
			savedList=null;
			map.put("pageNo", subPageNo);
			savedList = sellerDao.getSellerBySavedList(map);
			subPageNo++;
			List<MXmnSeller> uList = new ArrayList<MXmnSeller>();
			for(Map<String,Object> savedMap:savedList){
				MXmnSeller xmnSeller = new MXmnSeller();
				xmnSeller.setIndex(1);
				xmnSeller.setLast_time(now);
				xmnSeller.setOperate(3);
				xmnSeller.setSellerid((int)savedMap.get("sellerid"));
				xmnSeller.setUid((int)savedMap.get("uid"));
				xmnSeller.setVersion(1);
				xmnSeller.setXmntype(getInitUserXmntype((int)savedMap.get("uid")));
				uList.add(xmnSeller);
			}
			
			//1.更新用户-商家数据
			mongoService.insertAll(sellerAction, uList);
			log.info("收藏商家本次拿到"+savedList.size()+"条数据");
		}while(savedList.size()>=subPageSize);
		return null;
	}
	/**
	 * 更新用户-商圈统计信息
	 */
	public Map<String, String> initUserZoneActionService() throws FailureException,
			TException {
		log.info("更新用户-商圈统计信息：");
		String now = sdf.format(new Date());
		int pageNo=0;
		int pageSize=1000;
		Map<String,Object> selMap = new HashMap<String,Object>();
		selMap.put("pageSize", pageSize);
		List<Map<String,Object>> busineList = null;
		/*先删除旧内容*/
		mongoService.delete(zoneAction, new Criteria());
		do{ 
			selMap.put("pageNo", pageNo);
			busineList = sellerDao.getBusinessList(selMap);
			pageNo++;
			for(Map<String,Object> busine:busineList){
				int subPageNo=0;
				int subPageSize=1000;
				int zoneid= (int)busine.get("bid");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("zoneid", zoneid);
				map.put("pageSize", subPageSize);
				List<Map<String,Object>> userList = null;
				do{
					userList=null;
					map.put("pageNo", subPageNo);
					userList = orderDao.getUserCountByZone(map);
					subPageNo++;
					List<MXmnZone> uList = new ArrayList<MXmnZone>();
					for(Map<String,Object> userOrder:userList){
						if(userOrder.get("uid")==null){
							continue;
						}
						if(((Long)userOrder.get("views")).intValue()>0){
						MXmnZone xmnZone = new MXmnZone();
						xmnZone.setIndex(((Long)userOrder.get("views")).intValue());
						xmnZone.setLast_time(now);
						xmnZone.setOperate(1);
						xmnZone.setUid((int)userOrder.get("uid"));
						xmnZone.setVersion(1);
						xmnZone.setZoneid(zoneid);
						xmnZone.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
						uList.add(xmnZone);
						}
						if(((Long)userOrder.get("consumption")).intValue()>0){
						MXmnZone xmnZone2 = new MXmnZone();
						xmnZone2.setIndex(((Long)userOrder.get("consumption")).intValue());
						xmnZone2.setLast_time(now);
						xmnZone2.setOperate(2);
						xmnZone2.setUid((int)userOrder.get("uid"));
						xmnZone2.setVersion(1);
						xmnZone2.setZoneid(zoneid);
						xmnZone2.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
						uList.add(xmnZone2);
						}
					}
					
					//1.更新用户-商家数据
					mongoService.insertAll(zoneAction, uList);
				}while(userList.size()>=subPageSize);
			}
		}while(busineList.size()>=pageSize);
		return null;
	}
	/**
	 * 更新用户-分类统计信息
	 */
	public Map<String, String> initUserTradeActionService() throws FailureException,
			TException {
		log.info("更新用户-分类统计信息：");
		String now = sdf.format(new Date());
		int pageNo=0;
		int pageSize=1000;
		Map<String,Object> selMap = new HashMap<String,Object>();
		selMap.put("pageSize", pageSize);
		List<Map<String,Object>> tradeList = null;
		/*先删除旧内容*/
		mongoService.delete(tradeAction, new Criteria());
		do{ 
			selMap.put("pageNo", pageNo);
			tradeList = sellerDao.getTradeList(selMap);
			pageNo++;
			for(Map<String,Object> trade:tradeList){
				int subPageNo=0;
				int subPageSize=1000;
				int category = (int)trade.get("category");
				int genre = (int)trade.get("genre");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("genre", genre);
				map.put("pageSize", subPageSize);
				List<Map<String,Object>> userList = null;
				do{
					userList=null;
					map.put("pageNo", subPageNo);
					userList = orderDao.getUserCountByTrade(map);
					subPageNo++;
					List<MXmnTrade> uList = new ArrayList<MXmnTrade>();
					for(Map<String,Object> userOrder:userList){
						if(userOrder.get("uid")==null){
							continue;
						}
						if(((Long)userOrder.get("views")).intValue()>0){
						MXmnTrade xmnTrade = new MXmnTrade();
						xmnTrade.setUid((int)userOrder.get("uid"));
						xmnTrade.setCategory(category);
						xmnTrade.setGenre(genre);
						xmnTrade.setIndex(((Long)userOrder.get("views")).intValue());
						xmnTrade.setOperate(1);
						xmnTrade.setLast_time(now);
						xmnTrade.setVersion(1);
						xmnTrade.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
						uList.add(xmnTrade);
						}
						if(((Long)userOrder.get("consumption")).intValue()>0){
						MXmnTrade xmnTrade2 = new MXmnTrade();
						xmnTrade2.setUid((int)userOrder.get("uid"));
						xmnTrade2.setCategory(category);
						xmnTrade2.setGenre(genre);
						xmnTrade2.setIndex(((Long)userOrder.get("consumption")).intValue());
						xmnTrade2.setOperate(2);
						xmnTrade2.setLast_time(now);
						xmnTrade2.setVersion(1);
						xmnTrade2.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
						uList.add(xmnTrade2);
						}
						
						
					}
					
					//1.更新用户-商家数据
					mongoService.insertAll(tradeAction, uList);
					log.info("分类"+genre+"拿到"+userList.size()+"条数据");
				}while(userList.size()>=subPageSize);
			}
			log.info("分类拿到"+tradeList.size()+"条数据");
		}while(tradeList.size()>=pageSize);
		return null;
	}

	
	@Override
	public Map<String, String> initActionService(Map<String, String> actionMap)
			throws FailureException, TException {
		Map<String,String> resultMap = new HashMap<String,String>();
		log.info("初始化所有用户的浏览消费基本数据");
		try{
			resultMap.put("state", "2");
			resultMap.put("msg", "参数错误");
			
			String actiontype= actionMap.get("actiontype")==null?"":actionMap.get("actiontype") ;
			String operate=actionMap.get("operate")==null?"":actionMap.get("operate");
			if(StringUtils.isNotBlank(operate) && StringUtils.isNotBlank(actiontype)){
				if(actiontype.equals("0")){
					initUserSellerActionService();
					initUserZoneActionService();
					initUserTradeActionService();
				}else if(actiontype.equals("1")){
					initUserSellerActionService();
				}else if(actiontype.equals("2")){
					initUserZoneActionService();
				}else if(actiontype.equals("3")){
					initUserTradeActionService();
				}
			}
			resultMap.put("state", "0");
			resultMap.put("msg", "操作成功");
		}catch(Exception e){
			log.error("初始化操作异常",e);
			resultMap.put("state", "1");
			resultMap.put("msg", "初始化操作异常");
		}
		return resultMap;
	}

	/**
	 * 移除用户足迹
	 */
	@Override
	public int removeViewActionService(int uid,
			int removeAll, List<Integer> sellerList) throws FailureException,
			TException {
		try{
			Criteria criteria = Criteria.where("uid").is(uid).and("operate").is(1);
			if(removeAll==1){
			}else{
				criteria.and("sellerid").in(sellerList);
			}
			return mongoService.delete(sellerAction, criteria);
		}catch(Exception e){
			log.error("删除用户足迹失败",e);
			throw new FailureException(1, "删除用户足迹失败");
		}
	}
	
	/**
	 * 已下线的历史商家，重新上线时，写入MongoDB历史数据
	 * 返回值 已产生订单的商家为true
	 * 	         未产生订单的商家为false	
	 * @Title: initOldSellerOnlineData 
	 * @Description:
	 */
	public boolean initOldSellerOnlineData(int sellerid){
		boolean oldSeller = false;
		String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		int subPageNo=0;
		int subPageSize=1000;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sellerid", sellerid);
		map.put("pageSize", subPageSize);
		List<Map<String,Object>> userList = null;
		do{
			userList=null;
			map.put("pageNo", subPageNo);
			userList = orderDao.getUserCountBySeller(map);
			if(userList.size()>0){oldSeller=true;}
			subPageNo++;
			List<MXmnSeller> uList = new ArrayList<MXmnSeller>();
			for(Map<String,Object> userOrder:userList){
				if(userOrder.get("uid")==null){
					continue;
				}
				MXmnSeller xmnSeller = new MXmnSeller();
				xmnSeller.setIndex(((Long)userOrder.get("views")).intValue());
				xmnSeller.setLast_time(nowDate);
				xmnSeller.setOperate(1);
				xmnSeller.setSellerid(sellerid);
				xmnSeller.setUid((int)userOrder.get("uid"));
				xmnSeller.setVersion(1);
				xmnSeller.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
				
				MXmnSeller xmnSeller2 = new MXmnSeller();
				xmnSeller2.setIndex(((Long)userOrder.get("consumption")).intValue());
				xmnSeller2.setLast_time(nowDate);
				xmnSeller2.setOperate(2);
				xmnSeller2.setSellerid(sellerid);
				xmnSeller2.setUid((int)userOrder.get("uid"));
				xmnSeller2.setVersion(1);
				xmnSeller2.setXmntype(getInitUserXmntype((int)userOrder.get("uid")));
				
				uList.add(xmnSeller);
				uList.add(xmnSeller2);
			}
			
			//1.更新用户-商家数据
			mongoService.insertAll(sellerAction, uList);
			log.info("商家"+sellerid+"拿到"+userList.size()+"条数据");
		}while(userList.size()>=subPageSize);
		return oldSeller;
	}
	/*
	 * 查询该用户是不是主播
	 * 1 不是
	 * 2 是
	 */
	private int getInitUserXmntype(int uid){
		return userService.getAnchorUidSet().contains(uid)?2:1;
	}
}
