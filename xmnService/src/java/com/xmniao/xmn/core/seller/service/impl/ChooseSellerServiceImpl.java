package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.Business;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnTrade;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnZone;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.seller.service.ChooseSellerService;
import com.xmniao.xmn.core.seller.service.MainSellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: ChooseSellerServiceImpl    
* @Description:帮你挑选店铺实现类   
* @author: liuzhihao   
* @date: 2016年12月9日 下午7:52:59
 */
@Service
public class ChooseSellerServiceImpl implements ChooseSellerService{
	
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
	 * 商圈
	 */
	@Autowired
	private BusinessDao businessDao;

	/**
	 * 查询帮你挑选的店铺(登录用户)
	 */
	public MongoBaseResponse<MSeller> queryChooseSellers(Integer kind,Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers,Integer status) {
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		List<MSeller> result = new ArrayList<MSeller>();//返回结果集
		//商圈，分类都不为空，自然查
		if(kind != null && kind == 7){//关于我
			result = mainSellerService.mainSellerList(String.valueOf(uid), zoneid, tradeid, status, lon, lat, pageNo, pageSize);
			mbr.setKind(kind);
			mbr.setPage(pageNo);
			mbr.setResult(result);
			return mbr;
		}
		
		if(zoneid != null && tradeid != null){
			kind = 6;
		}else{
			if(zoneid != null){
				//若没消费分类
				if(!isZonesOrTrades(uid, 1)){
					kind = 6;
				}
			}/*else if(tradeid != null){
				//若没消费商圈
				if(!isZonesOrTrades(uid, 2)){
					kind = 6;
				}
			}*/else{
				kind = 6;
			}
		}
		
		if(kind == null){//首次进入
			kind = 1;
		}
		if(pageNo == null){
			pageNo = 1;
		}
	
		//判断结果集是否满足条件,否,则轮循
		while(true){
			List<MSeller> addResult = recomChooseSellers(kind, uid, lat, lon, cityid,zoneid, tradeid, pageNo, pageSize,sellers);//推荐的帮你挑选的店铺
			result.addAll(addResult);
			
			if(kind == 6) break;			
			
			if(result.size() >= pageSize || kind == 5){
				/**
				 * 此处做判断，是否满足条件,则返回结果集
				 * 
				 * 当返回结果集result等于页条数pageSize的时候，则证明满足条件
				 * 当kind等于5的时候,则证明已经走到最后的限制条件了,超出此范围则程序结束
				 */
				break;
			}
			
			pageNo = 1;
			
			++kind;//证明查询结果result不满足条件,则增加kind往下一步走，知道kind等于5
		}
		
		mbr.setKind(kind);
		mbr.setPage(pageNo);
		mbr.setResult(result);
		return mbr;
	}
	
	/**
	 * 查询帮你挑选的店铺(未登录用户)
	 */
	public List<MSeller> queryChooseSellers(Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers,Double minDistance,Double maxDistance){
		Double zlon = null;//商圈坐标 --经度
		Double zlat = null;//商圈坐标--纬度
		List<MSeller> result = new ArrayList<MSeller>();//结果集
		
		Criteria criteria = new Criteria();
		if(cityid != null){
			criteria.and("city").is(cityid);
		}
		
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);//商圈
			
			//获取用户选择的商圈坐标
			
			Business business = businessDao.selectByPrimaryKey(zoneid);
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
			}
		}
		if(tradeid != null){
			criteria.and("genre").is(tradeid);//分类
		}
		if(sellers != null && !sellers.isEmpty()){
			criteria.and("sellerid").not().in(sellers);
		}
		
		if(minDistance != null && maxDistance != null){
			if(zlon != null && zlat != null){
				//位置范围查询，不支持表分片
				DBObject dbaddon = new BasicDBObject("$nearSphere",  
					new BasicDBObject("$geometry",  
						new BasicDBObject("type","Point").append("coordinates",new double[]{zlon,zlat})
						).append("$minDistance",minDistance).append("$maxDistance",maxDistance)
					);
				
				criteria.and("coordinate").is(dbaddon);
			}
		}
		
		Query query = new Query(criteria);
		
		query.sort().on("weights", Order.DESCENDING).on("consumption",Order.DESCENDING);/*.on("views", Order.DESCENDING).on("saved", Order.DESCENDING);*/
		
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		
		try{
			result = mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 查询帮你挑选店铺
	 * @param kind
	 * @param uid
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	protected List<MSeller> recomChooseSellers(Integer kind,Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers){
		List<MSeller> result = new ArrayList<MSeller>();//返回结果集
		Double maxDistance = 0D;
		Double minDistance = 0D;
		switch(kind){
			case 1://先查找直径500米内用户相关的
				maxDistance = 500D;
				result = getChooseSellersByUid(uid, lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, minDistance, maxDistance,sellers);
				break;
			case 2://查找用户无关的0-1000米的
				maxDistance = 1000D;
				result = getChooseSellersByZoneId(uid,lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, minDistance, maxDistance,sellers);
				break;
			case 3://查找500-1000米用户相关的
				minDistance = 500D;
				maxDistance = 1000D;
				result = getChooseSellersByUid(uid, lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, minDistance, maxDistance,sellers);
				break;
			case 4://自然查找1000-2000米
				minDistance = 1000D;
				maxDistance = 2000D;
				result = queryChooseSellers(lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, sellers, minDistance, maxDistance);
				break;
			case 5://自然查找2000-3000米
				minDistance = 2000D;
				maxDistance = 6000D;
				result = queryChooseSellers(lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, sellers, minDistance, maxDistance);
				break;
			case 6://自然查找0-6000米
				minDistance = 0D;
				maxDistance = 6000D;
				result = queryChooseSellers(lat, lon,cityid, zoneid, tradeid, pageNo, pageSize, sellers, minDistance, maxDistance);
				break;
			default:
				break;
		}
		return result;
	}

	/**
	 * 查询与用户相关的店铺
	 * @param uid 用户ID
	 * @param lat 纬度
	 * @param lon 经度
	 * @param zoneid 商圈ID
	 * @param tradeid 分类ID
	 * @param pageNo 分页
	 * @param pageSize 条数
	 * @param minDistance 最小范围
	 * @param maxDistance 最大范围
	 * @return
	 */
	public List<MSeller> getChooseSellersByUid(Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,Double minDistance,Double maxDistance,List<Integer> sellers) {
		Double zlon = null;//商圈坐标 --经度
		Double zlat = null;//商圈坐标--纬度
		List<MSeller> result = new ArrayList<MSeller>();//结果集
		
		Criteria criteria = getChooseCriteria(uid, cityid,zoneid, tradeid, sellers, true);
		if(zoneid != null){
			Business business = businessDao.selectByPrimaryKey(zoneid);
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
			
				//位置范围查询，不支持表分片
				DBObject dbaddon = new BasicDBObject("$nearSphere",  
					new BasicDBObject("$geometry",  
						new BasicDBObject("type","Point").append("coordinates",new double[]{zlon,zlat})
						).append("$minDistance",minDistance).append("$maxDistance",maxDistance)
					);
				criteria.and("coordinate").is(dbaddon);
			}
		}
		
		
		
		Query q = new Query(criteria);
		
		q.sort().on("weights", Order.DESCENDING).on("label",Order.DESCENDING).on("views", Order.DESCENDING).on("saved", Order.DESCENDING);
		if(pageNo != null && pageSize != null){
			q.skip((pageNo-1)*pageSize).limit(pageSize);
		}
		
		try{
			result = 
				mongoTemplate.find(q, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	/**
	 * 查询与用户无关的店铺
	 * @param lat 纬度
	 * @param lon 经度
	 * @param zoneid 商圈ID
	 * @param tradeid 分类ID
	 * @param pageNo 分页
	 * @param pageSize 条数
	 * @param minDistance 最小范围
	 * @param maxDistance 最大范围
	 * @return
	 */
	public List<MSeller> getChooseSellersByZoneId(Integer uid,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,Double minDistance,Double maxDistance,List<Integer> sellers) {
		List<MSeller> result = new ArrayList<MSeller>();//结果集
		//通过商圈ID 查询商圈内的店铺信息
		Criteria criteria = getChooseCriteria(uid, cityid,zoneid, tradeid, sellers, false);
		Double zlon = null;
		Double zlat = null;
		if(zoneid != null){
			Business business = businessDao.selectByPrimaryKey(zoneid);
			if(business != null){
				zlon = business.getLongitude();
				zlat = business.getLatitude();
				//位置范围查询，不支持表分片
				DBObject dbaddon = new BasicDBObject("$nearSphere",  
					new BasicDBObject("$geometry",  
						new BasicDBObject("type","Point").append("coordinates",new double[]{zlon,zlat})
						).append("$minDistance",minDistance).append("$maxDistance",maxDistance)
					);
				
				criteria.and("coordinate").is(dbaddon);
			}
		}
		
		Query query = new Query(criteria);
		
		query.sort().on("weights", Order.DESCENDING).on("label",Order.DESCENDING).on("views", Order.DESCENDING).on("saved", Order.DESCENDING);
		
		if(pageNo != null && pageSize != null){
			query.skip((pageNo-1)*pageSize).limit(pageSize);
		}
		
		try{
			result = 
					mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Long sumChooseCounts(Integer uid,Integer zoneid,Integer tradeid){
		long counts = 0;
		if(uid != null){
			Criteria criteria = new Criteria();
			criteria.and("uid").is(uid);
			//商圈
			List<XmnZone> xmnZones = new ArrayList<XmnZone>();
			//分类
			List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
			try{
				xmnZones = mongoTemplate.find(new Query(criteria), XmnZone.class,propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				xmnTrades = mongoTemplate.find(new Query(criteria), XmnTrade.class,propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			Set<Integer> zones = new HashSet<Integer>();
			Set<Integer> trades = new HashSet<Integer>();
			if(xmnZones != null && !xmnZones.isEmpty()){
				for(XmnZone xmnZone : xmnZones){
					zones.add(xmnZone.getZoneid());
				}
			}
			if(xmnTrades != null && !xmnTrades.isEmpty()){
				for(XmnTrade xmnTrade : xmnTrades){
					zones.add(xmnTrade.getGenre());
				}
			}
			
			Criteria cta = new Criteria();
			cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			if(zoneid != null){
				cta.and("zoneid").is(zoneid);
			}else{
				if(!zones.isEmpty()){
					cta.and("zoneid").in(zones);
				}
			}
			if(tradeid != null){
				cta.and("genre").is(tradeid);
			}else{
				if(!trades.isEmpty()){
					cta.and("genre").in(trades);
				}
			}
			try{
				counts = mongoTemplate.count(new Query(cta), propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			Criteria c1 = new Criteria();
			c1.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			c1.and("zoneid").is(zoneid);
			
			try{
				counts = mongoTemplate.count(new Query(c1), propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return counts;
	}


	protected Criteria getChooseCriteria(Integer uid,Integer cityid,Integer zoneid,Integer tradeid,List<Integer> sellers,boolean isRelev) {
		
		//查询结果
		Criteria cta = new Criteria();
		cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(sellers != null && !sellers.isEmpty()){
			cta.and("sellerid").not().in(sellers);
		}
		if(cityid != null){
			cta.and("city").is(cityid);
		}
		//通过用户查询与用户相关的 消费分类、消费商圈
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费过的
		//判断商圈ID不为空
		Set<Integer> trades = new HashSet<Integer>();//分类ID集合
		//判断分类ID不为空
		Set<Integer>	zones = new HashSet<Integer>();//商铺ID集合
		//查询我de浏览分类记录和消费分类记录
		List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
		//查询我的浏览商圈记录和消费商圈记录
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		if(isRelev){//相关的
			if(zoneid != null){
				//查询商圈内的消费分类
				try{
					xmnTrades = 
						mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(xmnTrades != null &&!xmnTrades.isEmpty()){
					for(XmnTrade xmnTrade : xmnTrades){
						trades.add(xmnTrade.getGenre());
					}
				}
				cta.and("zoneid").is(String.valueOf(zoneid));
				if(!trades.isEmpty()){
					cta.and("genre").in(trades);
				}
				
			}
			
			if(tradeid != null){
				try{
					xmnZones = 
						mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(xmnZones != null && !xmnZones.isEmpty()){
					for(XmnZone xmnZone : xmnZones){
						zones.add(xmnZone.getZoneid());
					}
				}
				
				cta.and("genre").is(tradeid);
				if(!zones.isEmpty()){
					cta.and("zoneid").in(zones);
				}
			}
			//如果 商圈ID 和 分类ID 都为空的时候
			if(zoneid == null && tradeid == null){
				
				//查询消费分类
				try{
					xmnTrades = 
						mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(xmnTrades != null &&!xmnTrades.isEmpty()){
					for(XmnTrade xmnTrade : xmnTrades){
						trades.add(xmnTrade.getGenre());
					}
				}
				
				//查询消费商圈
				try{
					xmnZones = 
						mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(xmnZones != null && !xmnZones.isEmpty()){
					for(XmnZone xmnZone : xmnZones){
						zones.add(xmnZone.getZoneid());
					}
				}
				
				if(!trades.isEmpty()){
					cta.and("genre").in(trades);
				}
				
				if(!zones.isEmpty()){
					cta.and("zoneid").in(zones);
				}
			}
		}else{//无关的
			
			if(zoneid != null){
				
				//查询消费分类
				try{
					xmnTrades = 
						mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(xmnTrades != null &&!xmnTrades.isEmpty()){
					for(XmnTrade xmnTrade : xmnTrades){
						trades.add(xmnTrade.getGenre());
					}
				}
				
				cta.and("zoneid").is(String.valueOf(zoneid));
				if(!trades.isEmpty()){
					cta.and("genre").not().in(trades);
				}
				
			}
			
			if(tradeid != null){
				//查询消费商圈
				
				try{
					xmnZones = 
						mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(xmnZones != null && !xmnZones.isEmpty()){
					for(XmnZone xmnZone : xmnZones){
						zones.add(xmnZone.getZoneid());
					}
				}
				
				cta.and("genre").is(tradeid);
				if(!zones.isEmpty()){
					cta.and("zoneid").not().in(zones);
				}
			}
			
			//如果 商圈ID 和 分类ID 都为空的时候
			if(zoneid == null && tradeid == null){
				
				//查询商圈内的消费分类
				try{
					xmnTrades = 
						mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(xmnTrades != null &&!xmnTrades.isEmpty()){
					for(XmnTrade xmnTrade : xmnTrades){
						trades.add(xmnTrade.getGenre());
					}
				}
				
				try{
					xmnZones = 
						mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(xmnZones != null && !xmnZones.isEmpty()){
					for(XmnZone xmnZone : xmnZones){
						zones.add(xmnZone.getZoneid());
					}
				}
				
				if(!trades.isEmpty()){
					cta.and("genre").not().in(trades);
				}
				
				if(!zones.isEmpty()){
					cta.and("zoneid").not().in(zones);
				}
			}
		}
		return cta;
	}
	
	protected boolean isZonesOrTrades(Integer uid,Integer type) {
		
		Long  counts = 0l;
		//查询条件
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费
		switch(type){
			case 1://分类
				try{
					counts = 
						mongoTemplate.count(new Query(criteria), propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			case 2://商圈
				try{
					counts = 
						mongoTemplate.count(new Query(criteria), propertiesUtil.getValue("xmnZone", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		
		return counts.longValue()>0;

	}

}
