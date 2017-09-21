/**    
 * 文件名：SellerMongnoQuertzService.java    
 *    
 * 版本信息：    
 * 日期：2016年12月1日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2016     
 * 版权所有    
 *    
 */
package com.xmniao.service.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.dao.live.LiveRecordDao;
import com.xmniao.dao.seller.SellerDao;
import com.xmniao.service.common.MongoBaseService;
import com.xmniao.service.live.LiveOrderServiceImpl;
import com.xmniao.service.user.UserActionServiceImpl;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerMongnoQuertzService
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月1日 下午5:44:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service("sellerLiveWeightsQuertz")
public class SellerLiveWeightsQuertzService {
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerLiveWeightsQuertzService.class);
    
    @Resource(name="sellerInfoQueue")
    private String sellerInfoQueue;
    
    @Resource(name="sellerMongo")
    private String sellerCollection;
    
    @Autowired
    private SellerDao sellerDao;
    
    @Autowired
    private LiveRecordDao liveRecordDao;
    
    @Autowired
    private LiveOrderServiceImpl liveOrderService;
    
    @Autowired
    private UserActionServiceImpl userActionService;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 注入Mongo操作
     */
    @Autowired
    private MongoBaseService mongoService;
    
    
	public void sellerLiveModify(){
		//1.更新商户 is_live is_advance is_fans_coupon 	views consumption saved weights 等信息
		try{
			/*仅查询更新近2天有直播的商家*/
			Map<String,Object> reqMap = new HashMap<String,Object>();
			reqMap.put("nowDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			Criteria criteria1 = Criteria.where("is_live").is(1);
			Criteria criteria2 = Criteria.where("is_advance").is(1);
			Criteria criteria3 = Criteria.where("is_fans_coupon").is(1);
			Criteria criteria4 = new Criteria().orOperator(criteria1,criteria2,criteria3);
			List<HashMap> sellerList = mongoService.findAll(sellerCollection, criteria4, HashMap.class);
			Set<String> sellerSet = new HashSet<>();
			for(Map sellerMap:sellerList){
				if(sellerMap != null && sellerMap.get("sellerid")!=null){
					sellerSet.add(sellerMap.get("sellerid").toString());
				}
			}
			sellerList=null;
			List<Map<String,Object>> list = liveRecordDao.getHasLiveRecordSellerList(reqMap);
			for(Map<String,Object> map:list){
				if(map != null && map.get("sellerid")!=null){
					sellerSet.add(map.get("sellerid").toString());
				}
			}
			list=null;
			log.info("本次共查询到"+sellerSet.size()+"个商家的直播信息要更新");
			for(String sellerid:sellerSet){
				if(sellerid==null || sellerid.equals("0")){
					continue;
				}
				Map<String,String> infoMap=new HashMap<String,String>();
				infoMap.put("sellerid", sellerid);
				infoMap.put("infoType", "4");
				userActionService.insertSellerInfoRedis(sellerInfoQueue,infoMap);
			}
		}catch(Exception e){
			log.error("查询更新异常",e);
		}
	}

	public static void main(String[] args) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			for(Map<String,Object> map:list){
				System.out.println("35263253");
				if(map.get("sellerid")!=null){
					System.out.println("4574745");
				}
			}
			System.out.println("ewtetw");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
