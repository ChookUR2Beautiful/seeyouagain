package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnTrade;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnZone;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.seller.service.SameTasteSellerService;
import com.xmniao.xmn.core.seller.service.SellerService;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: SameTasteSellerServiceImpl    
* @Description:与我口味相似的人   
* @author: liuzhihao   
* @date: 2016年12月9日 下午11:42:02
 */
@Service
public class SameTasteSellerServiceImpl implements SameTasteSellerService{

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
	private String fileUrl;
	
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 查询口味相似的人
	 * @return
	 */
	public MongoBaseResponse<Map<Object,Object>> querySameTasteList(Integer uid,Integer kind,Double lat,Double lon,Integer pageNo,Integer pageSize,boolean isRandom){
		MongoBaseResponse<Map<Object,Object>> mbr = new MongoBaseResponse<Map<Object,Object>>();
		
		if(kind == null){//首次访问
			kind = 1;
		}
		if(pageNo == null){
			pageNo = 1;
		}
		
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		while(true){
			List<Map<Object,Object>> addResult = getSameTasteList(kind,uid,lon,lat,pageNo,pageSize,isRandom);
			result.addAll(addResult);
			if(result.size() >= pageSize || kind == 3){
				break;
			}else{
				Integer counts = getSameTasteCounts(kind,uid);
				if(counts >= 30){
					break;
				}
			}
			
			pageNo = 1;
			++kind;
		}
		
		mbr.setKind(kind);
		mbr.setPage(pageNo);
		mbr.setResult(result);
		
		return mbr;
	}
	
	/**
	 * 查询与用户消费过同样的商铺的用户信息
	 * @param uid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Map<Object,Object>> querySameTaseteListBySellerId(Integer uid, Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//查询与我有相同消费商家的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费商家
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> sellerids = new HashSet<Integer>();
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid());
			}
		}
		
		if(sellerids != null && !sellerids.isEmpty()){
			//查询跟我消费过的商铺有联系的用户ID
			Criteria uc = new Criteria();
			uc.and("uid").ne(uid);
			uc.and("operate").is(2);//消费过的用户
			uc.and("sellerid").in(sellerids);
			
			Query q = new Query(uc);
			
			q.sort().on("last_time", Order.DESCENDING);//已时间的倒序排序
			
			if(isRandom){//需要随机的时候
				try{
					Long counts = mongoTemplate.count(q, propertiesUtil.getValue("seller", "conf_common.properties"));
					if(counts>pageSize){
						pageNo = (int) (counts/pageSize.longValue());
						Random ran = new Random();
						pageNo = ran.nextInt(pageNo);
						
						if(pageNo <= 0){
							 pageNo = 1;
						}
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pageNo != null && pageSize != null){
				q.skip((pageNo-1)*pageSize).limit(pageSize);
			}
			
			try{
				xmnSellers = 
					mongoTemplate.find(q, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Set<Integer> uids = new HashSet<Integer>();
		if(!xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				uids.add(xmnSeller.getUid());
			}
		}
		
		//查询用户信息
		result = queryUserInfoByUidList(uid, uids, lon, lat);
		
		return result;
		
	}
	
	public List<Map<Object,Object>> querySameTaseteListByTradeId(Integer uid, Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Set<Integer> uids = getSameTasteCountsBySellerId(uid);
		//查询我的消费分类记录
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费分类
		
		List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
		try{
			xmnTrades = 
				mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> trades = new HashSet<Integer>();
		if(!xmnTrades.isEmpty()){
			for(XmnTrade xmnTrade : xmnTrades){
				trades.add(xmnTrade.getGenre());//分类集合
			}
		}
		//查询与我分类相同的用户
		
		if(!trades.isEmpty()){
			
			uids.add(uid);
			
			Criteria tc = new Criteria();
			tc.and("uid").not().in(uids);
			tc.and("operate").is(2);//消费过
			tc.and("genre").in(trades);
			
			Query query = new Query(tc);
			
			query.sort().on("last_time", Order.DESCENDING);//已时间的倒序排序
			if(isRandom){//需要随机的时候
				try{
					Long counts = mongoTemplate.count(query, propertiesUtil.getValue("seller", "conf_common.properties"));
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
			
			if(pageNo != null && pageSize != null){
				query.skip((pageNo-1)*pageSize).limit(pageSize);
			}
			
			try{
				xmnTrades = 
					mongoTemplate.find(query, XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		Set<Integer> tradeUids = new HashSet<>();
		if(!xmnTrades.isEmpty()){
			for(XmnTrade xmnTrade : xmnTrades){
				tradeUids.add(xmnTrade.getUid());
			}
		}
		
		//查询用户信息
		result = queryUserInfoByUidList(uid, tradeUids, lon, lat);
		
		return result;
	}
	
	
	public List<Map<Object,Object>> querySameTaseteListByZoneId(Integer uid, Double lon,Double lat,Integer pageNo,Integer pageSize,boolean isRandom){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Set<Integer> uids = getSameTasteCountsByTradeId(uid);
		
		//查询相同消费商圈用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费过的商圈
		
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		try{
			xmnZones = 
				mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Set<Integer> zoneids = new HashSet<Integer>();
		if(!xmnZones.isEmpty()){
			for(XmnZone xmnZone : xmnZones){
				zoneids.add(xmnZone.getZoneid());
			}
		}
		//查找与我消费过相同商圈的用户
		if(!zoneids.isEmpty()){
			
			uids.add(uid);
			
			Criteria zc = new Criteria();
			zc.and("uid").not().in(uids);
			zc.and("operate").is(2);//消费过的
			zc.and("zoneid").in(zoneids);
			
			Query q = new Query(zc);

			q.sort().on("last_time", Order.DESCENDING);//已时间的倒序排序
			
			if(isRandom){//需要随机的时候
				try{
					Long counts = mongoTemplate.count(q, propertiesUtil.getValue("seller", "conf_common.properties"));
					pageNo = (int) (counts/pageSize.longValue());
					Random ran = new Random();
					pageNo = ran.nextInt(pageNo);
					if(pageNo <= 0){
						pageNo = 1;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(pageNo != null && pageSize != null){
				q.skip((pageNo-1)*pageSize).limit(pageSize);
			}
			
			try{
				xmnZones = 
					mongoTemplate.find(q, XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		Set<Integer> zoneUids = new HashSet<>();
		if(!xmnZones.isEmpty()){
			for(XmnZone xmnZone : xmnZones){
				zoneUids.add(xmnZone.getUid());
			}
		}
		
		//查询用户信息
		result = queryUserInfoByUidList(uid,zoneUids,lon,lat);
		
		return result;
	}
	
	protected List<Map<Object,Object>> getSameTasteList(Integer kind,Integer uid,Double lat,Double lon,Integer pageNo,Integer pageSize,boolean isRandom) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
			switch(kind){
				case 1:
					result = querySameTaseteListBySellerId(uid, lon, lat, pageNo, pageSize, isRandom);
					break;
				case 2:
					result = querySameTaseteListByTradeId(uid, lon, lat, pageNo, pageSize, isRandom);
					break;
				case 3:
					result = querySameTaseteListByZoneId(uid, lon, lat, pageNo, pageSize, isRandom);
					break;
				default:
					break;
			}
		return result;
		
	}
	
	protected Integer getSameTasteCounts(Integer kind,Integer uid) {
		Integer counts = 0;
		switch(kind){
		case 1://与我有相同消费商家的用户
			counts = getSameTasteCountsBySellerId(uid).size();
			break;
		case 2://若相同消费商家的用户小于30个，接着找有相同消费分类的用户 (去第1类别重复)
			counts = getSameTasteCountsByTradeId(uid).size();
			break;
			
		case 3://若相同消费分类的用户小于30个，接着找有相同消费商圈的用户 (去第1、2类别重复)
			counts = getSameTasteCountsByZoneId(uid).size();
			break;
		default:
			break;
		}
		
		return counts;
	}
	/**
	 * 与我有相同消费商家的用户
	 * @param uid
	 * @return
	 */
	protected Set<Integer> getSameTasteCountsBySellerId(Integer uid) {
		//查询与我有相同消费商家的用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费商家
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> sellerids = new HashSet<Integer>();
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid());
			}
		}
		
		if(sellerids != null && !sellerids.isEmpty()){
			//查询跟我消费过的商铺有联系的用户ID
			Criteria uc = new Criteria();
			uc.and("uid").ne(uid);
			uc.and("operate").is(2);//消费过的用户
			uc.and("sellerid").in(sellerids);
			
			
			Query q = new Query(uc);
			q.sort().on("last_time", Order.DESCENDING);//已时间的倒序排序
			q.skip(0).limit(30);
			try{
				xmnSellers = 
					mongoTemplate.find(q, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Set<Integer> uids = new HashSet<Integer>();
		if(!xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				uids.add(xmnSeller.getUid());
			}
		}
		return uids;
	}
	
	/**
	 * 若相同消费商家的用户小于30个，接着找有相同消费分类的用户
	 * @param uid
	 * @return
	 */
	protected Set<Integer> getSameTasteCountsByTradeId(Integer uid) {
		Set<Integer> uids = getSameTasteCountsBySellerId(uid);
		//查询我的消费分类记录
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费分类
		
		List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
		try{
			xmnTrades = 
				mongoTemplate.find(new Query(criteria), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> trades = new HashSet<Integer>();
		if(!xmnTrades.isEmpty()){
			for(XmnTrade xmnTrade : xmnTrades){
				trades.add(xmnTrade.getGenre());//分类集合
			}
		}
		//查询与我分类相同的用户
		
		if(!trades.isEmpty()){
			uids.add(uid);
			Criteria tc = new Criteria();
			tc.and("uid").not().in(uids);
			tc.and("operate").is(2);//消费过
			tc.and("genre").in(trades);
			
			Query query = new Query(tc);
			
			query.sort().on("last_time", Order.DESCENDING);//已时间的倒序排序
			
			query.skip(0).limit(30);
			
			try{
				xmnTrades = 
					mongoTemplate.find(query, XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(!xmnTrades.isEmpty()){
			for(XmnTrade xmnTrade : xmnTrades){
				uids.add(xmnTrade.getUid());
			}
		}
		
		uids.remove(uid);
		
		return uids;
	}

	/**
	 * 若相同消费分类的用户小于30个，接着找有相同消费商圈的用户 (去第1、2类别重复)
	 * @param uid
	 * @return
	 */
	protected Set<Integer> getSameTasteCountsByZoneId(Integer uid) {
		
		Set<Integer> uids = getSameTasteCountsByTradeId(uid);
		
		//查询相同消费商圈用户
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(2);//消费过的商圈
		
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		try{
			xmnZones = 
				mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Set<Integer> zoneids = new HashSet<Integer>();
		if(!xmnZones.isEmpty()){
			for(XmnZone xmnZone : xmnZones){
				zoneids.add(xmnZone.getZoneid());
			}
		}
		//查找与我消费过相同商圈的用户
		if(!zoneids.isEmpty()){
			uids.add(uid);
			Criteria zc = new Criteria();
			zc.and("uid").not().in(uids);;//去重
			zc.and("operate").is(2);//消费过的
			zc.and("zoneid").in(zoneids);
			
			Query q = new Query(zc);
			
			q.sort().on("last_time", Order.DESCENDING);//已时间的倒序排序
			
			q.skip(0).limit(30);
			
			try{
				xmnZones = 
					mongoTemplate.find(q, XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(!xmnZones.isEmpty()){
			for(XmnZone xmnZone : xmnZones){
				uids.add(xmnZone.getUid());
			}
		}
		
		uids.remove(uid);
		return uids;
		
	}


	/**
	 * 通过用户id查询用户的信息
	 * @param uid
	 * @param uids
	 * @return
	 */
	public List<Map<Object,Object>> queryUserInfoByUidList(Integer uid,Set<Integer> uids,Double lon,Double lat){
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();//结果集
		
		if(uids != null && !uids.isEmpty()){
			Map<Object,Object> checkmap = new HashMap<Object,Object>();
			for(Integer uuid : uids){
				Map<Object,Object> usermap = new HashMap<Object,Object>();
				Map<Object,Object> fusermap = liveUserDao.findUserInfoByUid(uuid);
				if(fusermap != null && !fusermap.isEmpty()){
					
					String avatar = ObjectUtils.toString(fusermap.get("avatar"));
					
					if(StringUtils.isEmpty(avatar)){
						try{
							avatar = propertiesUtil.getValue("defaultImage", "conf_common.properties");
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
					usermap.put("avatar", fileUrl+avatar);//用户图像
					usermap.put("sex", ObjectUtils.toString(fusermap.get("sex")));//用户性别
					usermap.put("uid", uuid);
					String name = ObjectUtils.toString(fusermap.get("nname"));
					if(StringUtils.isNotEmpty(name)){
						usermap.put("nname",name);
					}else{
						name = ObjectUtils.toString(fusermap.get("uname"));
						if(StringUtils.isNotEmpty(name)){
							int len = name.length();
							if(len >= 11){
								name = name.substring(0, 3) + "****" + name.substring(name.length() - 4);
							}
							usermap.put("nname",name);
						}else{
//							name = ObjectUtils.toString(fusermap.get("openid"));
//							if(StringUtils.isNotEmpty(name)){
//								usermap.put("nname","微信用户"+name.substring(3, 7));
								usermap.put("nname","匿名用户");
//							}
						}
					}
					
					usermap.put("utype", 2);//默认用户类型为普通用户
					Map<Object,Object> endmap = liveUserDao.queryLiverInfoByUid(uuid);
					if(endmap != null && !endmap.isEmpty()){
						usermap.put("utype", endmap.get("utype"));
					}
					
					//用户类型
					usermap.put("isfollow", 0);//未关注
					usermap.put("tagname", "");//非登录用户毫无关系
					
					if(uid != null){
						checkmap.put("uid", uid);//登录用户ID
						checkmap.put("fuid", uuid);//被关注用户ID
						int follows = liveUserDao.checkTwoUidIsFollow(checkmap);
						if(follows != 0){
							usermap.put("isfollow", 1);//关注
						}
						//查询两人之间的相似度
						String tagname = getAlikeName(String.valueOf(uid),String.valueOf(uuid));
						usermap.put("tagname", tagname);
					}
					
					//查询用户去 最近去过的商铺
					Criteria criteria = new Criteria();
					criteria.and("uid").is(uuid);
					criteria.and("operate").is(2);
					
					Query query = new Query(criteria);
					query.sort().on("last_time", Order.DESCENDING);
					
					List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
					
					try{
						xmnSellers = 
							mongoTemplate.find(query, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
					}catch(Exception e){
						e.printStackTrace();
					}
					
					Set<Integer> sellerids = new HashSet<Integer>();
					if(!xmnSellers.isEmpty()){
						for(XmnSeller xmnSeller : xmnSellers){
							sellerids.add(xmnSeller.getSellerid());
						}
					}
					
					//查询去过的商铺信息
					if(!sellerids.isEmpty()){
						Criteria cta = new Criteria();
						cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
						cta.and("sellerid").in(sellerids);
						
						Query qcta = new Query(cta);
						
						qcta.sort().on("udate", Order.DESCENDING);
						qcta.skip(0).limit(4);
						
						List<MSeller> mSellers = new ArrayList<MSeller>(); 
						
						try{
							mSellers = 
								mongoTemplate.find(qcta, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
						}catch(Exception e){
							e.printStackTrace();
						}
						
						if(!mSellers.isEmpty()){
							
							List<Map<Object,Object>> sellerlist = sellerService.getSellers(mSellers, lon, lat, uuid);
							if(sellerlist == null || sellerlist.isEmpty()){
								sellerlist = new ArrayList<Map<Object,Object>>();
							}
							usermap.put("sellers", sellerlist);
							result.add(usermap);
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 判断用户与相似用户的相似度
	 * @param uid
	 * @param fuid
	 * @return
	 */
	protected String getAlikeName(String uid,String fuid) {
		//非常相似标签名称
		String withOthersMatchTag ="";
		//一般相似标签名称
		String withOthersCommonTag="";
		try{
			withOthersMatchTag = propertiesUtil.getValue("withOthersMatchTag", "conf_common.properties");
			withOthersCommonTag = propertiesUtil.getValue("withOthersCommonTag", "conf_common.properties");
		}catch(Exception e){
			e.printStackTrace();
			withOthersMatchTag = "与我非常相似";
			withOthersCommonTag = "与我一般相似";
		}
		
		//如果去过相同店铺消费过的则非常相似
		
		Criteria criteria = new Criteria();
		
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(2);
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<String> sellerids = new HashSet<String>();
		if(!xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid().toString());
			}
		}
		
		if(!sellerids.isEmpty()){
			Criteria cta = new Criteria();
			cta.and("uid").is(Integer.parseInt(fuid));
			cta.and("sellerid").in(sellerids);
			try{
				xmnSellers = 
					mongoTemplate.find(new Query(cta), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(!xmnSellers.isEmpty()){
				return withOthersMatchTag;//非常相似
			}else{
				return withOthersCommonTag;//一般相似
			}
		}
		return withOthersCommonTag;
	}


	/**
	 * 未登录
	 */
	@Override
	public List<Map<Object, Object>> querySameTasteList(Integer zoneid, Double lon,Double lat,Integer pageNo, Integer pageSize,boolean isRandom) {
		
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Criteria criteria = new Criteria();
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
		}
		criteria.and("operate").is(2);//消费过
		
		Query query = new Query(criteria);
		
		if(isRandom){//需要随机的时候
			try{
				
				Long counts = mongoTemplate.count(query, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
			
				if(counts>pageSize){
					pageNo = (int) (counts/pageSize.longValue());
					Random ran = new Random();
					pageNo = ran.nextInt(pageNo);
					if(pageNo <=0){
						pageNo = 1;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		query.sort().on("last_time", Order.DESCENDING).on("xmntype", Order.DESCENDING);
		
		if(pageNo != null && pageSize != null){
			query.skip((pageNo-1)*pageSize).limit(pageSize);
		}
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		
		try{
			xmnZones = 
				mongoTemplate.find(query, XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<Integer> uids = new HashSet<Integer>();
		if(!xmnZones.isEmpty()){
			for(XmnZone xmnZone : xmnZones){
				uids.add(xmnZone.getUid());
			}
		}
		
		if(!uids.isEmpty()){
			//查询用户信息
			result = queryUserInfoByUidList(null, uids, lon, lat);
		}
		return result;
	}
}
