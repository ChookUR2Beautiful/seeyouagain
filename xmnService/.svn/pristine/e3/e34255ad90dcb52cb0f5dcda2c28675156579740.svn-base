package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;
import com.xmniao.xmn.core.catehome.entity.Business;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnTrade;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.seller.service.CustomisedSellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;

/**
 * 
* @projectName: xmnService 
* @ClassName: CustomisedSellerServiceImpl    
* @Description:专属口味列表   
* @author: liuzhihao   
* @date: 2016年12月10日 上午3:05:09
 */
@Service
public abstract class CustomisedSellerServiceImpl implements CustomisedSellerService{

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private UrsDao ursDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 专属口味(登录用户)
	 * @param uid
	 * @param zoneid
	 * @param tradeid
	 * @param type
	 * @param lat
	 * @param lon
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<MSeller> queryCustomisedSellers(Integer uid,Integer zoneid,Integer tradeid,Integer type,Double lat,Double lon,Double minprice,Double maxprice,Integer pageNo,Integer pageSize){
		List<MSeller> mSellers = new ArrayList<MSeller>();
		switch(type){//吃货类型:0:全部 1:主播去过的店铺 2:我关注的人去过的店铺 3:与我相似的人去过的商铺
			case 0://吃货类型:0 全部，不选
				mSellers = queryCustomisedSellers(zoneid, tradeid, lat, lon, minprice, maxprice, pageNo, pageSize);
				break;
			case 1://吃货类型:1主播去过的店铺 
				mSellers = getCustomisedSellersByLiver(zoneid, tradeid, type, lat, lon, minprice, maxprice, pageNo, pageSize);
				break;
			case 2://吃货类型:2:我关注的人去过的店铺 
				mSellers = getCustomisedSellersByFollow(uid, zoneid, tradeid, type, lat, lon, minprice, maxprice, pageNo, pageSize);
				break;
			case 3://吃货类型:3:与我相似的人去过的商铺
				mSellers = getCustomisedSellersBySameUser(uid, zoneid, tradeid, type, lat, lon, minprice, maxprice, pageNo, pageSize);				
				break;
			default:
				break;
			}
		return mSellers;
	}
	
	public List<MSeller> queryCustomisedSellers(Integer zoneid,Integer tradeid,Double lat,Double lon,Double minprice,Double maxprice,Integer pageNo,Integer pageSize){
		
		List<MSeller> mSellers = new ArrayList<MSeller>();
		
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(maxprice != 0.0){
			criteria.and("consume").gte(minprice).lte(maxprice);
		}
		if(tradeid != 0){
			criteria.and("genre").is(tradeid);
		}
		
		Double zlon = null;
		Double zlat = null;
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
			
			Business business = businessDao.selectByPrimaryKey(zoneid);
			
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
			}
		}
		if(zlon != null && zlat != null){
			Circle circle = new Circle(zlon, zlat, 1000);
			criteria.and("coordinate").withinSphere(circle);
		}
		
		Query query = new Query(criteria);
		
		query.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		
		try{
			mSellers = mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return mSellers;
	}
	
	/**
	 * 吃货类型--主播去过的店铺
	 * @return
	 */
	protected List<MSeller> getCustomisedSellersByLiver(Integer zoneid,Integer tradeid,Integer type,Double lat,Double lon,Double minprice,Double maxprice,Integer pageNo,Integer pageSize) {
		List<MSeller> mSellers = new ArrayList<MSeller>();
		Criteria[] liveCriteria = new Criteria[2];
		liveCriteria[0] = new Criteria().and("is_live").is(1);//有直播状态
		liveCriteria[1] = new Criteria().and("is_advance").is(1);//有预告状态
		
		Double zlon = null;
		Double zlat = null;
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(maxprice != 0.0){
			criteria.and("consume").gte(minprice).lte(maxprice);
		}
		if(tradeid != 0){
			criteria.and("genre").is(tradeid);
		}
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
			
			Business business = businessDao.selectByPrimaryKey(zoneid);
			
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
			}
		}
		if(zlon != null && zlat != null){
			Circle circle = new Circle(zlon, zlat, 1000);
			criteria.and("coordinate").withinSphere(circle);
		}
		criteria.orOperator(liveCriteria);
		Query query = new Query(criteria);
		
		query.sort().on("weights", Order.DESCENDING);
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		
		try{
			mSellers = mongoTemplate.find(query,MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return mSellers;
	}

	/**
	 * 吃货类型--我关注的人去过的店铺
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<MSeller> getCustomisedSellersByFollow(Integer uid,Integer zoneid,Integer tradeid,Integer type,Double lat,Double lon,Double minprice,Double maxprice,Integer pageNo,Integer pageSize) {
		List<MSeller> mSellers = new ArrayList<MSeller>();
		
		//查询我关注的用户
		List<Integer> fuidlist = new ArrayList<Integer>();
		List<Map<Object,Object>> fuids = ursDao.findMyFocusByUid(uid);
		if(fuids != null && !fuids.isEmpty()){
			for(Map<Object,Object> fuid : fuids){
				fuidlist.add(Integer.parseInt(fuid.get("uid").toString()));
			}
		}
		Double zlon = null;
		Double zlat = null;
		//查询我关注的人消费过的店铺
		Set<String> sellerids = new HashSet<String>();
		if(!fuidlist.isEmpty()){
			Criteria cta = new Criteria();
			cta.and("operate").is(2);//消费过的商铺
			cta.and("uid").in(fuidlist);
			
			Query q = new Query(cta);
			
			q.sort().on("last_time", Order.DESCENDING);
			q.skip((pageNo-1)*pageSize).limit(pageSize);
			
			DBObject rf = q.getQueryObject();
			
			List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
			try{
				xmnSellers = 
					mongoTemplate.getCollection(propertiesUtil.getValue("xmnSeller", "conf_common.properties")).distinct("sellerid", rf);
			}catch(Exception e){
				e.printStackTrace();
			}
				
			if(xmnSellers != null && !xmnSellers.isEmpty()){
				for(XmnSeller xmnSeller : xmnSellers){
					sellerids.add(String.valueOf(xmnSeller.getSellerid()));//去重
				}
			}
			
			if(!sellerids.isEmpty()){
				
				Criteria c1 = new Criteria();
				c1.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
				c1.and("seller").in(sellerids);
				if(maxprice != 0.0){
					c1.and("consume").gte(minprice).lte(maxprice);
				}
				if(tradeid != 0){
					c1.and("genre").is(tradeid);
				}
				if(zoneid != null){
					c1.and("zoneid").is(zoneid);
					
					Business business = businessDao.selectByPrimaryKey(zoneid);
					
					if(business != null){
						zlon = business.getLongitude();
						zlat = business.getLatitude();
					}
				}
				if(zlon != null && zlat != null){
					Circle circle = new Circle(zlon, zlat, 1000);
					c1.and("coordinate").withinSphere(circle);
				}
				
				Query q1 = new Query(c1);
				q1.sort().on("weights", Order.DESCENDING);
				try{
					mSellers = 
						mongoTemplate.find(q1, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return mSellers;
	}


	protected List<MSeller> getCustomisedSellersBySameUser(Integer uid,Integer zoneid,Integer tradeid,Integer type,Double lat,Double lon,Double minprice,Double maxprice,Integer pageNo,Integer pageSize) {
		List<MSeller> mSellers = new ArrayList<MSeller>();
		Set<String> uids = new HashSet<String>();//去重用户id
		Set<String> selleridlist = new HashSet<String>();//去重商铺id
		//通过用户id查询与用户相似的用户
		Criteria uc = new Criteria();
		uc.and("uid").is(uid);
		Query q = new Query(uc);
		//查询用户的分类
		Double zlon = null;
		Double zlat = null;
		Set<String> trades = new HashSet<String>();//用户分类id
		List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
		try{
			xmnTrades =	mongoTemplate.find(q, XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
			
		if(xmnTrades != null && !xmnTrades.isEmpty()){
			for(XmnTrade xmnTrade : xmnTrades){
				trades.add(ObjectUtils.toString(xmnTrade.getGenre()));
			}
		}
		
		if(!trades.isEmpty()){
			Criteria tradeCriteria = new Criteria();//查询与我分类相同的且消费过的用户
			tradeCriteria.and("operate").is(2);//消费过的
			tradeCriteria.and("genre").in(trades);
			tradeCriteria.and("uid").ne(uid);
			
			Query tradeQuery = new Query(tradeCriteria);
			
			try{
				xmnTrades =	mongoTemplate.find(tradeQuery, XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(xmnTrades != null && !xmnTrades.isEmpty()){
				for(XmnTrade sameTrade: xmnTrades){
					uids.add(ObjectUtils.toString(sameTrade.getUid()));
				}
			}
		
		}
		//我与我浏览过和消费过相同商铺的用户
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(q, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
			
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				selleridlist.add(ObjectUtils.toString(xmnSeller.getSellerid()));
			}
			
			//通过用户浏览或者消费过的商铺id查询相关用户
			Criteria sellerCriteria = new Criteria();
			sellerCriteria.and("operate").is(2);//消费过的
			sellerCriteria.and("uid").ne(uid);
			sellerCriteria.and("sellerid").in(selleridlist);
			
			
			Query sellerQuery = new Query(sellerCriteria);
			
			try{
				xmnSellers = 
					mongoTemplate.find(sellerQuery, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(xmnSellers != null && !xmnSellers.isEmpty()){
				for(XmnSeller sameSeller : xmnSellers){
					uids.add(ObjectUtils.toString(sameSeller.getUid()));
				}
			}
		}
		
		if(!uids.isEmpty()){
			//通过与我相关的用户查询他们消费过的商铺id
			Criteria endCriteria = new Criteria();
			endCriteria.and("operate").is(2);
			endCriteria.and("uid").in(uids);
			
			Query endQuery = new Query(endCriteria);
			
			try{
				xmnSellers = 
					mongoTemplate.find(endQuery, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(xmnSellers != null && !xmnSellers.isEmpty()){
				for(XmnSeller endSeller : xmnSellers){
					selleridlist.clear();//清空商铺id集合重新组装
					selleridlist.add(ObjectUtils.toString(endSeller.getSellerid()));
				}
			}
		}
		
		if(!selleridlist.isEmpty()){
			
			Criteria cta = new Criteria();
			cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			cta.and("sellerid").in(selleridlist);
			if(maxprice != 0.0){
				cta.and("consume").gte(minprice).lte(maxprice);
			}
			if(tradeid != 0){
				cta.and("genre").is(tradeid);
			}
			if(zoneid != null){
				cta.and("zoneid").is(zoneid);
				
				Business business = businessDao.selectByPrimaryKey(zoneid);
				
				if(business != null){
					zlon = business.getLongitude();
					zlat = business.getLatitude();
				}
			}
			if(zlon != null && zlat != null){
				Circle circle = new Circle(zlon, zlat, 1000);
				cta.and("coordinate").withinSphere(circle);
			}
			
			
			Query q1 = new Query(cta);
			q1.sort().on("weights", Order.DESCENDING);
			q1.skip((pageNo-1)*pageSize).limit(pageSize);
			
			try{
				mSellers = 
					mongoTemplate.find(q1, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return mSellers;
	}
}
