package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.Business;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnTrade;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnZone;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.seller.service.CustomisedSellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: CustomizedServiceImpl    
* @Description:口味定制接口实现类   
* @author: liuzhihao   
* @date: 2016年12月11日 上午10:49:33
 */
@Service
public class CustomizedServiceImpl implements CustomisedSellerService{

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	
	
	/**
	 * 专属口味列表
	 * @return
	 */
	public MongoBaseResponse<MSeller>  queryCustomizedSellers(
		Integer kind,Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Integer type,Double lat,Double lon,Integer pageNo,Integer pageSize){
		
		MongoBaseResponse<MSeller>  mbr = new MongoBaseResponse<MSeller>();
		switch(type){
			//吃货类型 1:主播去过的  2:与我相似的人去过的  3:我关注的人去过的
			case 0:
				mbr = queryCustomizedSellers(zoneid, cityid,tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
				break;//不限
			case 1:
				mbr = querySellersByLivers(zoneid,cityid, tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);//1:主播去过的
				break;
			case 2:
				mbr = querySellersByFollow(uid, cityid,zoneid, tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);//3:我关注的人去过的
				break;
			case 3:
				mbr = querySellersByLikeMe(kind,cityid, uid, zoneid, tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);//2:与我相似的人去过的
				break;
			default:
				break;
		}
		
		return mbr;
	}
	
	/**
	 * 主播去过的
	 * @return
	 */
	public MongoBaseResponse<MSeller> querySellersByLivers(Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize){
		
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		//查询存在主播的商铺
		List<MSeller> mSellers = new ArrayList<MSeller>();
		Criteria[] criteriaslist = new Criteria[2];
		criteriaslist[0] = new Criteria().and("is_live").is(1);//正在直播的商铺
		criteriaslist[1] = new Criteria().and("is_advance").is(1);//有直播预告的店铺
		
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		
		Double zlon = null;
		Double zlat = null;
		if(cityid != null){
			criteria.and("city").is(cityid);
		}
		
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
			
			Business business = businessDao.selectByPrimaryKey(zoneid);
			
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
			}
		}
		if(tradeid != 0){
			criteria.and("genre").is(tradeid);
		}
		if(maxPrice != 0.0){
			criteria.and("consume").gte(minPrice).lte(maxPrice);//人均消费
		}
		if(zlon != null && zlat != null){
			Circle circle = new Circle(zlon, zlat, 1000);
			criteria.and("coordinate").withinSphere(circle);
		}
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 20;
		}
		criteria.orOperator(criteriaslist);
		
		Query query = new Query(criteria);
		
		query.sort().on("weights", Order.DESCENDING).on("label", Order.DESCENDING);
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		try{
			mSellers = mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		mbr.setResult(mSellers);
		return mbr;
	}
	
	/**
	 * 与我相似的人去过的
	 * @return
	 */
	public MongoBaseResponse<MSeller> querySellersByLikeMe(Integer kind,Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize){
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		
		if(kind == null){
			kind = 1;
		}
		
		if(pageNo == null){
			pageNo = 1;
		}
		
		List<MSeller> result = new ArrayList<MSeller>();
		while(true){
			
			List<MSeller> addResult = recomSellersByLikeMe(kind, uid, zoneid,cityid, tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
			result.addAll(addResult);
			if(result.size() >= pageSize || kind == 3){
				break;
			}else{
				Integer counts = sumSellerCounts(kind,uid);
				if(counts >= 30){
					break;
				}
			}
			pageNo = 1;
			
			++kind;
			
		}
		
		mbr.setKind(kind);
		mbr.setPage(pageNo+1);
		mbr.setResult(result);
		return mbr;
	}
	
	
	protected List<MSeller> recomSellersByLikeMe(Integer kind,Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize) {
		List<MSeller> result = new ArrayList<MSeller>();
		Set<Integer> sellers = new HashSet<Integer>();
			switch(kind){
				case 1://与我消费商铺相似的
					sellers = getSellersBySameSeller(uid);
					result = queryrecomSellersByLikeMe(sellers, zoneid,cityid, tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
					break;
				case 2://与我消费分类相似的
					sellers = getSellersBySameTrade(uid);
					result = queryrecomSellersByLikeMe(sellers, zoneid, cityid ,tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
					break;
				case 3://与我消费商圈相似的
					sellers = getSellersBySameZone(uid);
					result = queryrecomSellersByLikeMe(sellers, zoneid,cityid, tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
					break;
				default:
					break;
				}
	
			return result;
	}
	
	protected Integer sumSellerCounts(Integer kind,Integer uid) {
		Integer counts = 0;
			switch(kind){
				case 1:
					counts = getSameSellersCounts(uid);//相同消费商铺数量
					break;
				case 2:
					counts = getSameTradesCounts(uid);//相同消费分类数量
					break;
				case 3:
					counts = getSameZonesCounts(uid);//相同消费商圈数量
					break;
				default:
					break;
			}
		return counts;
	}
	
	/**
	 * 获取跟我消费相同商铺的用户消费过的商铺ID
	 * @param uid
	 * @return
	 */
	public Set<Integer> getSellersBySameSeller(Integer uid){
		Set<Integer> sellers = new HashSet<Integer>();
		//查询与我消费相同商铺的用户消费的商铺
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(!xmnSellers.isEmpty()){
			Set<Integer> sellerids = new HashSet<Integer>();
			
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid());
			}
			
			if (!sellerids.isEmpty()) {
				Criteria c1 = new Criteria();
				c1.and("uid").is(uid);
				c1.and("operate").is(2);
				c1.and("sellerid").in(sellerids);
				
				try{
					xmnSellers = 
						mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				Set<Integer> uids = new HashSet<Integer>();
				if(!xmnSellers.isEmpty()){
					for(XmnSeller xmnSeller : xmnSellers){
						uids.add(xmnSeller.getUid());
					}
				}
				
				if(!uids.isEmpty()){
					Criteria c2 = new Criteria();
					c2.and("operate").all(2);//消费过的商铺
					c2.and("uid").in(uids);
					
					try{
						xmnSellers = 
							mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));	
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(!xmnSellers.isEmpty()){
						
						for(XmnSeller xmnSeller : xmnSellers){
							sellers.add(xmnSeller.getSellerid());
						}
					}
				}
			}
		}
		
		return sellers;
	}
	
	/**
	 * 获取跟我消费相同分类的用户消费过的商铺ID
	 * @param uid
	 * @return
	 */
	public Set<Integer> getSellersBySameTrade(Integer uid){
		Set<Integer> sellers = getSellersBySameSeller(uid);
		
		//查询与我消费分类相同的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);
		
		List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
		
		try{
			xmnTrades = 
				mongoTemplate.find(new Query(criteria), XmnTrade.class,propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//获取用户分类ID
		Set<Integer> trades = new HashSet<Integer>();
		if (!xmnTrades.isEmpty()) {
			for(XmnTrade xmnTrade : xmnTrades){
				trades.add(xmnTrade.getGenre());//分类
			}
			
			if(!trades.isEmpty()){
				//查询分类用户
				Criteria c1 = new Criteria();
				c1.and("uid").ne(uid);
				c1.and("operate").is(2);
				c1.and("genre").in(trades);
				try{
					xmnTrades = 
						mongoTemplate.find(new Query(criteria), XmnTrade.class,propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(!xmnTrades.isEmpty()){
					Set<Integer> uids = new HashSet<Integer>();
					for(XmnTrade xmnTrade : xmnTrades){
						uids.add(xmnTrade.getUid());
					}
					
					if(!uids.isEmpty()){
						Criteria c2 = new Criteria();
						c2.and("operate").is(2);
						c2.and("uid").in(uids);
						
						List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
						
						try{
							xmnSellers =
								mongoTemplate.find(new Query(c2), XmnSeller.class,propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
						}catch(Exception e){
							e.printStackTrace();
						}
						
						if(!xmnSellers.isEmpty()){
							for(XmnSeller xmnSeller : xmnSellers){
								sellers.add(xmnSeller.getSellerid());
							}
						}
					}
				}
			}
		}
		return sellers;
	}
	
	/**
	 * 获取跟我消费相同商圈的用户消费过的商铺ID
	 * @param uid
	 * @return
	 */
	public Set<Integer> getSellersBySameZone(Integer uid){
		Set<Integer> sellers = getSellersBySameSeller(uid);
		
		//查询与我消费商圈相同的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);
		
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		
		try{
			xmnZones = 
				mongoTemplate.find(new Query(criteria), XmnZone.class,propertiesUtil.getValue("xmnZone", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//获取用户分类ID
		Set<Integer> zones = new HashSet<Integer>();
		if (!xmnZones.isEmpty()) {
			for(XmnZone xmnZone : xmnZones){
				zones.add(xmnZone.getZoneid());//分类
			}
			
			if(!zones.isEmpty()){
				//查询分类用户
				Criteria c1 = new Criteria();
				c1.and("uid").ne(uid);
				c1.and("operate").is(2);
				c1.and("zoneid").in(zones);
				try{
					xmnZones = 
						mongoTemplate.find(new Query(criteria), XmnZone.class,propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(!xmnZones.isEmpty()){
					Set<Integer> uids = new HashSet<Integer>();
					for(XmnZone xmnZone : xmnZones){
						uids.add(xmnZone.getUid());
					}
					
					if(!uids.isEmpty()){
						Criteria c2 = new Criteria();
						c2.and("operate").is(2);
						c2.and("uid").in(uids);
						
						List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
						
						try{
							xmnSellers =
								mongoTemplate.find(new Query(c2), XmnSeller.class,propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
						}catch(Exception e){
							e.printStackTrace();
						}
						
						if(!xmnSellers.isEmpty()){
							for(XmnSeller xmnSeller : xmnSellers){
								sellers.add(xmnSeller.getSellerid());
							}
						}
					}
				}
			}
		}
		return sellers;
	}
	
	/**
	 * 计算与我消费相同商铺的用户数量
	 * @param uid
	 * @return
	 */
	protected Integer getSameSellersCounts(Integer uid) {
		Long counts = 0l;
		//查询与我消费过的商铺相同的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费过的商铺
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//开始查询跟我消费商铺相同的用户
		if(!xmnSellers.isEmpty()){
			Set<Integer> sellerids = new HashSet<Integer>();
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid());
			}
			
			if(!sellerids.isEmpty()){
				Criteria c1 = new Criteria();
				c1.and("uid").ne(uid);
				c1.and("operate").is(2);
				c1.and("sellerids").in(sellerids);
				try{
					counts = 
						mongoTemplate.count(new Query(c1), propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
						
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return counts.intValue();
	}
	
	/**
	 * 计算与我消费相同分类的用户数量
	 * @param uid
	 * @return
	 */
	protected Integer getSameTradesCounts(Integer uid) {
		Long counts = 0l;
		//与我消费分类相同的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费过的分类
		List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
		try{
			xmnTrades = 
				mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(!xmnTrades.isEmpty()){
			Set<Integer> trades = new HashSet<Integer>();
			for(XmnTrade xmnTrade : xmnTrades){
				trades.add(xmnTrade.getGenre());//分类ID
			}
			
			if(!trades.isEmpty()){
				//查询数量
				Criteria c1 = new Criteria();
				c1.and("uid").ne(uid);
				c1.and("operate").is(2);
				c1.and("genre").in(trades);
				
				try{
					//查询数量
					counts = mongoTemplate.count(new Query(c1), propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return counts.intValue();
	}
	
	/**
	 * 计算与我消费相同商圈的用户数量
	 * @param uid
	 * @return
	 */
	protected Integer getSameZonesCounts(Integer uid) {
		Long counts = 0l;
		//查询相同消费商圈的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);
		
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		try{
			xmnZones = 
				mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(!xmnZones.isEmpty()){
			Set<Integer> zones = new HashSet<Integer>();
			
			for(XmnZone xmnZone : xmnZones){
				zones.add(xmnZone.getZoneid());
			}
			
			if(!zones.isEmpty()){
				Criteria c1 = new Criteria();
				c1.and("uid").ne(uid);
				c1.and("operate").is(2);
				c1.and("zoneid").in(zones);
				
				try{
					counts = mongoTemplate.count(new Query(c1), propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return counts.intValue();
	}
	
	/**
	 * 查询结果
	 * @param sellers
	 * @param zoneid
	 * @param tradeid
	 * @param minPrice
	 * @param maxPrice
	 * @param lon
	 * @param lat
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	protected List<MSeller> queryrecomSellersByLikeMe(Set<Integer> sellers,Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize) {
		
		List<MSeller> result = new ArrayList<MSeller>();
		//最终查询结果
		
		Double zlon = null;
		Double zlat = null;
		if(!sellers.isEmpty()){
			
			Criteria cta = new Criteria();
			cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			cta.and("sellerid").in(sellers);
			
			if(cityid!=null){
				cta.and("city").is(cityid);
			}
			if(zoneid != null){
				cta.and("zoneid").is(zoneid);
				
				Business business = businessDao.selectByPrimaryKey(zoneid);
				
				if(business != null){
					zlon = business.getLongitude();
					zlat = business.getLatitude();
				}
			}
			if(tradeid != 0){
				cta.and("genre").is(tradeid);
			}
			if(maxPrice != 0.0){
				cta.and("consume").gte(minPrice).lte(maxPrice);//人均消费
			}
			if(zlon != null && zlat != null){
				Circle circle = new Circle(zlon, zlat, 1000);
				cta.and("coordinate").withinSphere(circle);
			}
			if(pageNo == null && pageSize == null){
				pageNo = 1;
				pageSize = 20;
			}
			
			Query cq = new Query(cta);
			cq.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
			cq.skip((pageNo-1)*pageSize).limit(pageSize);	
			
			try{
				result = 
					mongoTemplate.find(cq, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/**
	 * 我关注的人去过的
	 * @return
	 */
	public MongoBaseResponse<MSeller> querySellersByFollow(Integer uid,Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize){
		
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		//根据UID查询关注用户消费过的商铺信息
		List<Integer> fsellers = liveUserDao.findFollowUidCustomSellerIdByUid(uid);
		//通过商铺ID查询商铺信息
		
		List<MSeller> mSellers = new ArrayList<MSeller>();
		
		Double zlon = null;
		Double zlat = null;
		
		if(fsellers != null && !fsellers.isEmpty()){
			Criteria cta = new Criteria();
			cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			cta.and("sellerid").in(fsellers);
			
			if(cityid != null){
				cta.and("city").is(cityid);
			}
			if(zoneid != null){
				cta.and("zoneid").is(zoneid);
				
				Business business = businessDao.selectByPrimaryKey(zoneid);
				
				if(business != null){
					zlon = business.getLongitude();
					zlat = business.getLatitude();
				}
			}
			if(tradeid != 0){
				cta.and("genre").is(tradeid);
			}
			if(maxPrice != 0.0){
				cta.and("consume").gte(minPrice).lte(maxPrice);//人均消费
			}
			if(zlon != null && zlat != null){
				Circle circle = new Circle(zlon, zlat, 1000);
				cta.and("coordinate").withinSphere(circle);
			}
			if(pageNo == null && pageSize == null){
				pageNo = 1;
				pageSize = 20;
			}
			
			Query cq = new Query(cta);
			cq.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
			cq.skip((pageNo-1)*pageSize).limit(pageSize);	
			
			try{
				mSellers = 
					mongoTemplate.find(cq, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(!mSellers.isEmpty()){
				mbr.setResult(mSellers);
			}
		}
		
		return mbr;
	}
	
	/**
	 * 专属口味列表(未登录用户)
	 * @param zoneid
	 * @param tradeid
	 * @param minPrice
	 * @param maxPrice
	 * @param lon
	 * @param lat
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryCustomizedSellers(Integer zoneid,Integer cityid,Integer tradeid,Double minPrice,Double maxPrice,Double lon,Double lat,Integer pageNo,Integer pageSize){
		MongoBaseResponse<MSeller>  mbr = new MongoBaseResponse<MSeller>();
		List<MSeller> mSellers = new ArrayList<MSeller>();
		Criteria cta = new Criteria();
		cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
//		cta.and("label").is(1);//推荐的
		
		Double zlon = null;
		Double zlat = null;
		
		if(cityid != null){
			cta.and("city").is(cityid);
		}
		if(zoneid != null){
			cta.and("zoneid").is(zoneid);
			
			Business business = businessDao.selectByPrimaryKey(zoneid);
			
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
			}
		}
		if(tradeid != 0){
			cta.and("genre").is(tradeid);
		}
		if(maxPrice != 0.0){
			cta.and("consume").gte(minPrice).lte(maxPrice);//人均消费
		}
		if(zlon != null && zlat != null){
			Circle circle = new Circle(zlon, zlat, 1000);
			cta.and("coordinate").withinSphere(circle);
		}
		if(pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 20;
		}
		
		Query cq = new Query(cta);
		cq.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
		cq.skip((pageNo-1)*pageSize).limit(pageSize);	
		
		try{
			mSellers = 
				mongoTemplate.find(cq, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		mbr.setResult(mSellers);
		return mbr;
	}
	
}
