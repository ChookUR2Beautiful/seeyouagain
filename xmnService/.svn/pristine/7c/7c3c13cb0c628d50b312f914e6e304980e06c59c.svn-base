package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.seller.service.MainSellerService;
import com.xmniao.xmn.core.seller.service.UneatSellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: UneatSellerServiceImpl    
* @Description:我看过不如一尝的店铺   
* @author: liuzhihao   
* @date: 2016年12月9日 下午11:22:08
 */
@Service
public class UneatSellerServiceImpl implements UneatSellerService{

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private MainSellerService mainSellerService;
	
	
	/**
	 * 查询看过不如一尝的商铺
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryUneatSellers(Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double lon,Double lat,List<Integer> sellers,Integer pageNo,Integer pageSize,boolean isRandom,Integer status){
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		List<MSeller> mSellers = new ArrayList<MSeller>();//未消费过的商铺信息
		if(status != null){//关于我的
			mSellers = mainSellerService.mainSellerList(String.valueOf(uid), zoneid, tradeid, status, lon, lat, pageNo, pageSize);
			mbr.setKind(7);
			mbr.setPage(pageNo);
			mbr.setResult(mSellers);
			return mbr;
		}
		
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//看过 但未消费的商铺
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();//未消费过的商铺记录
		
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> sellerids = new HashSet<Integer>();//用户消费过的商铺ID
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid());
			}
			
			if(sellers != null && !sellers.isEmpty()){
				sellerids.addAll(sellers);//排除首页随机的商铺ID
			}
		}
		
		//查询用户未消费过商铺
		
		
		
		Criteria unCriteria = new Criteria();
		unCriteria.and("uid").is(uid);
		unCriteria.and("operate").ne(2);//为消费
		unCriteria.and("sellerid").not().in(sellerids);
		
		Query unQ = new Query(unCriteria);
		unQ.sort().on("index", Order.DESCENDING).on("last_time", Order.DESCENDING);
		
		try{
			xmnSellers = 
				mongoTemplate.find(unQ, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> unIds = new HashSet<Integer>();
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				unIds.add(xmnSeller.getSellerid());
			}
		}
		
		if(!sellerids.isEmpty()){
			
			Criteria cta = new Criteria();
			cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			cta.and("sellerid").in(unIds);
			if(cityid != null){
				cta.and("city").is(cityid);
			}
			if(zoneid != null){
				cta.and("zoneid").is(zoneid);
			}
			if(tradeid != null){
				cta.and("genre").is(tradeid);
			}
			
			Query q = new Query(cta);
			
			if(isRandom){//需要随机的时候
				try{
					Long counts = mongoTemplate.count(q, propertiesUtil.getValue("seller", "conf_common.properties"));
					if(counts>pageSize){
						pageNo = (int) (counts/pageSize.longValue());
						Random ran = new Random();
						pageNo = ran.nextInt(pageNo);
						if(pageNo<=0){
							pageNo = 1;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			q.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
			if(pageNo != null && pageSize != null){
				
				q.skip((pageNo-1)*pageSize).limit(pageSize);
			}
			
			try{
				mSellers = 
					mongoTemplate.find(q, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		mbr.setResult(mSellers);
		return mbr;
		
	}
}
