package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.Business;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.seller.service.MainSellerService;
import com.xmniao.xmn.core.seller.service.PopularSellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: PopularSellerServiceImpl    
* @Description:常去商圈的人气店铺（更名 “商圈人气店铺”）      
* @author: liuzhihao   
* @date: 2016年12月9日 下午9:39:12
 */
@Service
public class PopularSellerServiceImpl implements PopularSellerService{

	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private MainSellerService mainSellerService;
	
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 
	 * @param kind
	 * @param lat
	 * @param lon
	 * @param zoneid
	 * @param tradeid
	 * @param pageNo
	 * @param pageSize
	 * @param sellers
	 * @return
	 */
	public MongoBaseResponse<MSeller> queryPopularSellers(Integer uid,Integer kind,Double lat,Double lon,Integer cityid,Integer zoneid,Integer tradeid,Integer pageNo,Integer pageSize,List<Integer> sellers,boolean isRandom,Integer status){
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();
		List<MSeller> result = new ArrayList<MSeller>();
		if(kind != null && kind == 7){
			result = mainSellerService.mainSellerList(String.valueOf(uid), zoneid, tradeid, status, lon, lat, pageNo, pageSize);
			mbr.setKind(kind);
			mbr.setResult(result);
			mbr.setPage(pageNo);
			
			return mbr;
		}
		
		
		if(zoneid == null) kind = 4;
		
		if(kind == null){//首次进入
			kind = 1;
			while(true){
				
				if(kind == 3){//当kind等于3的时候,证明已经是最后的一个条件了,如果还是不满足就直接跳出结束程序
					break;
				}
				
				//计算当前位置的店铺数量,满足条件则返回结果集,否则继续轮循知道程序结束
				Integer counts = getPopularCounts(kind,cityid,zoneid,lon,lat);
				//
				if(counts >= 30){
					break;
				}
				
				++kind;
			}
		}
	
		if(pageNo == null){
			pageNo = 1;
		}
		//查询推荐人气店铺
		result = recomPopularSellers(kind, cityid,zoneid, tradeid,lon, lat, pageNo, pageSize, sellers,isRandom);
		mbr.setKind(kind);
		mbr.setPage(pageNo);
		mbr.setResult(result);
		return mbr;
	}
	
	/**
	 * 查询人气商铺信息
	 * @param kind
	 * @return
	 */
	protected List<MSeller> recomPopularSellers(Integer kind,Integer cityid,Integer zoneid,Integer tradeid,Double lon,Double lat,Integer pageNo,Integer pageSize,List<Integer> sellers,boolean isRandom) {
		List<MSeller> result = new ArrayList<MSeller>();//返回结果集
		Double maxDistance = 0D;
		switch(kind){
			case 1://查找当前位置直径1000米以内商户
				maxDistance = 1/111.12;
				result = queryPopularSellersByZoneId(cityid,zoneid, tradeid, lon, lat, maxDistance, sellers, pageNo, pageSize,isRandom);
				break;
			case 2://查找当前位置直径2000米以内商户
				maxDistance = 2/111.12;
				result = queryPopularSellersByZoneId(cityid,zoneid, tradeid, lon, lat, maxDistance, sellers, pageNo, pageSize,isRandom);
				break;
			case 3://查找当前位置直径3000米以内商户
				maxDistance = 3/111.12;
				result = queryPopularSellersByZoneId(cityid,zoneid, tradeid, lon, lat, maxDistance, sellers, pageNo, pageSize,isRandom);
				break;
			case 4://商圈没有数据的时候，仅从城市获取的时候
				maxDistance = null;
				result = queryPopularSellersByZoneId(cityid,zoneid, tradeid, lon, lat, maxDistance, sellers, pageNo, pageSize,isRandom);
				break;
			default :
				break;
		}
		return result;
	}
	
	/**
	 * 查询商圈内商铺个数
	 * @param kind
	 * @return
	 */
	protected Integer getPopularCounts(Integer kind,Integer cityid,Integer zoneid,Double lon,Double lat) {
		Integer counts = 0;//商圈内的店铺个数
		Double maxDistance = 0D;
		switch(kind){
			case 1:
				maxDistance = 1/111.12;//1000米以内
				counts = queryPopularCounts(cityid,zoneid,lon,lat,maxDistance);
				break;
			case 2:
				maxDistance = 2/111.12;//2000米以内
				counts = queryPopularCounts(cityid,zoneid,lon,lat,maxDistance);
				break;
			case 3:
				maxDistance = 3/111.12;//3000米以内
				counts = queryPopularCounts(cityid,zoneid,lon,lat,maxDistance);
				break;
			default :
				break;
		
		}
		return counts;
	}
	
	/**
	 * 查询商圈内店铺的个数
	 * @param zoneid
	 * @param maxDistance
	 * @return
	 */
	public Integer queryPopularCounts(Integer cityid,Integer zoneid,Double lon,Double lat,Double maxDistance) {
		
		Integer counts = 0;
		Double zlon = null;//商圈坐标 --经度
		Double zlat = null;//商圈坐标--纬度
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
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
		
		if(zlon != null && zlat != null){
			criteria.and("coordinate").near(new Point(zlon, zlat)).maxDistance(maxDistance);
		}
		
		Query query = new Query(criteria);
		query.skip(0).limit(30);
		
		List<MSeller> mSellers = new ArrayList<MSeller>();
		
		try{
			//查询商圈内的店铺个数
			mSellers = 
				mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(mSellers != null && !mSellers.isEmpty()){
			counts = mSellers.size();
		}
		
		return counts;
	}

	/**
	 * 通过商圈ID查询范围内的店铺
	 * @return
	 */
	public List<MSeller> queryPopularSellersByZoneId(Integer cityid,Integer zoneid,Integer tradeid, Double lon,Double lat,Double maxDistance,List<Integer> sellers,Integer pageNo,Integer pageSize,boolean isRandom){
		List<MSeller> result = new ArrayList<MSeller>();
		Double zlon = null;//商圈坐标 --经度
		Double zlat = null;//商圈坐标--纬度
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(cityid != null){
			criteria.and("city").is(cityid);
		}
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
			Business business = businessDao.selectByPrimaryKey(zoneid);
			
			zlon = business.getLongitude();
			zlat = business.getLatitude();
			
		}
		if(tradeid != null){
			criteria.and("genre").is(tradeid);
		}
		if(sellers != null && !sellers.isEmpty()){
			criteria.and("sellerid").not().in(sellers);
		}
		
		if(isRandom){//需要随机的时候
			try{
				long counts = mongoTemplate.count(new Query(criteria), propertiesUtil.getValue("seller", "conf_common.properties"));
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
		
		if(zlon != null && zlat != null){
			Circle circle = new Circle(zlon, zlat, maxDistance);
			criteria.and("coordinate").withinSphere(circle);
		}
		
		Query query = new Query(criteria);
		
//		query.sort().on("consumption", Order.DESCENDING);
		query.sort().on("seller_random_num_consumption", Order.DESCENDING);//按假数据来排序
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
	
}
