package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.seller.service.MainSellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;

@Service
public class MainSellerServiceImpl implements MainSellerService{

	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/***
	 * 店铺推荐列表
	 */
	public List<MSeller> mainSellerList(String uid,Integer zoneid,Integer tradeid,Integer type,Double lon,Double lat,Integer pageNo,Integer pageSize){
		List<MSeller> mSellers = new ArrayList<MSeller>();
		//查询结果集组装查询条件
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
		}
		if(tradeid != null){
			criteria.and("genre").is(tradeid);
		}
		//关于我的过滤条件
		//登录方可有此条件
		if(StringUtils.isNotEmpty(uid)){
			if(type != null){
				List<Integer> sellers = getCriteriaByType(type, uid);
				if(sellers != null){
					criteria.and("sellerid").in(sellers);
				}
			}
		}
		//组装排序条件
		Query query = new Query(criteria);
		
		if(type != null){
			switch(type){
				case 1://我消费过的 按消费量降序排序
					query.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
					break;
				case 2://我收藏过的 按收藏量降序排序
					query.sort().on("weights", Order.DESCENDING).on("saved", Order.DESCENDING);
					break;
				case 3://我浏览过的 按浏览量降序排序
					query.sort().on("weights", Order.DESCENDING).on("views", Order.DESCENDING);
					break;
				case 4://与我口味相似的人也喜欢的 按平台活跃降序排序
					query.sort().on("weights", Order.DESCENDING).on("label", Order.DESCENDING).on("consumption", Order.DESCENDING);
					break;
					default:
						break;
			}
		}else{
			query.sort().on("weights", Order.DESCENDING).on("label", Order.DESCENDING).on("consumption", Order.DESCENDING).on("views", Order.DESCENDING);
		}
		
		query.skip((pageNo - 1)*pageSize).limit(pageSize);
		
		try{
			
			mSellers = 
				mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mSellers;
	}
	
	
	public List<Integer> getCriteriaByType(Integer type,String uid){
		List<Integer> sellers = new ArrayList<Integer>();
		switch(type){
			case 1:
				sellers = getConsumIds(uid);//我消费过
				break;
			case 2:
				sellers = getCollectIds(uid);//我收藏过
				break;
			case 3:
				sellers = getBrowseIds(uid);//我浏览过
				break;
			case 4:
				sellers = getSameLikeSellerIds(uid);
				break;
			default:
				break;
		}
		return sellers;
	}
	
	/**
	 * 获取我消费过的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getConsumIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(2);//我消费过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellers.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
		}
		
		return sellers;
	}
	
	/**
	 * 获取我浏览过的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getBrowseIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(1);//我浏览过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellers.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
		}
		
		return sellers;
	}
	
	/**
	 * 获取我收藏过的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getCollectIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(3);//我浏览过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellers.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
		}
		
		return sellers;
	}
	
	/**
	 * 获取与我口味相似的人也喜欢的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getSameLikeSellerIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(2);//我消费过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			
			List<Integer> consumids = new ArrayList<Integer>();
			
			for(XmnSeller xmnSeller : xmnSellers){
				consumids.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
			
			//与我有相同消费的用户
			if(!consumids.isEmpty()){
				Criteria consumCriteria = new Criteria();
				consumCriteria.and("uid").ne(Integer.parseInt(uid));//排除自己
				consumCriteria.and("operate").is(2);
				consumCriteria.and("sellerid").in(consumids);
				
				Query consumQuery = new Query(consumCriteria);
				consumQuery.skip(0).limit(200);//限制200个与我相似的用户
				try{
					xmnSellers = 
						mongoTemplate.find(new Query(consumCriteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				//通过我消费的商铺ID查询与我同样消费过的用户ID
				if(xmnSellers != null && !xmnSellers.isEmpty()){
					Set<Integer> set = new HashSet<Integer>();//因为通过ID查询用户，所以需要去重用户
					for(XmnSeller xmnSeller : xmnSellers){
						set.add(xmnSeller.getUid());
					}
					
					//通过组装好的用户ID查询这些用户消费过的商铺ID
					if(!set.isEmpty()){
						Criteria likemeCriteria = new Criteria();
						likemeCriteria.and("operate").is(2);//消费过
						likemeCriteria.and("uid").in(set);
						
						Query likemeQuery = new Query(likemeCriteria);
						likemeQuery.skip(0).limit(400);//限制四百个
						try{
							xmnSellers = 
								mongoTemplate.find(new Query(consumCriteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
						}catch(Exception e){
							e.printStackTrace();
						}
						
						//组装与我口味相似的人也喜欢的商铺ID
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
}
