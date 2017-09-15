package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.seller.service.InterestSellerService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: InterestSellerServiceImpl    
* @Description:感兴趣的新店（更名“猜你喜欢”）   
* @author: liuzhihao   
* @date: 2016年12月9日 下午11:05:21
 */
@Service
public class InterestSellerServiceImpl implements InterestSellerService{

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 查询感兴趣的新店
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryInterestSellers(Integer cityid,Integer zoneid,Integer tradeid,Double lon,Double lat,List<Integer> sellers,Integer pageNo,Integer pageSize,boolean isRandom){
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		
		Long counts = 0l;
		
		// 一个月内签约的商铺
		String mouthTime = DateUtil.format(DateUtil.addMonth(-3), "yyyy-MM-dd") + " 00:00:00";// 30天前的日期
		
		Criteria[] criterialist = new Criteria[2];
		criterialist[0] = new Criteria().and("signdate").gte(mouthTime).lte(DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
		criterialist[1] = new Criteria().and("label").is(1);// 平台推荐 0 否 1 是
		
		//查询商铺条件
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(cityid != null){
			criteria.and("city").is(cityid);
		}
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
		}
		if(tradeid != null){
			criteria.and("genre").is(tradeid);
		}
		if(sellers != null && !sellers.isEmpty()){
			criteria.and("sellerid").not().in(sellers);
		}
		
		criteria.orOperator(criterialist);
		Query query = new Query(criteria);
	
		query.sort().on("weights",Order.DESCENDING);
		
		if(isRandom){//需要随机的时候
			try{
				counts = mongoTemplate.count(query, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(counts>pageSize){
				pageNo = (int) (counts/pageSize.longValue());
				Random ran = new Random();
				pageNo = ran.nextInt(pageNo);
				if(pageNo<=0){
					pageNo = 1;
				}
			}
		}
		
		if(pageNo != null && pageSize != null){
			query.skip((pageNo-1)*pageSize).limit(pageSize);
		}
		
		List<MSeller> mSellers = new ArrayList<MSeller>();
		
		try{
			mSellers = 
				mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		mbr.setResult(mSellers);;
	
		return mbr;
	}
	
}
