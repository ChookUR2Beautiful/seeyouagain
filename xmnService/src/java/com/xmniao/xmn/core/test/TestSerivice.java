package com.xmniao.xmn.core.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.Hash;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.catehome.entity.mongo.Coordinate;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnZone;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.util.GeoHashUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.entity.MSeller;

/**
 * 
* @projectName: xmnService 
* @ClassName: TestSerivice    
* @Description:测试   
* @author: liuzhihao   
* @date: 2016年12月3日 下午4:37:32
 */
@Service
public class TestSerivice {

	@Resource
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	
	public Object update(BaseRequest baseRequest){
		
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1);
		List<MSeller> mSellers = new ArrayList<MSeller>();
		
		Query query = new Query(criteria);
		try{
			mSellers = mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
//		System.out.println(mSellers.size());
//		Map<Object,Object> map = new HashMap<Object,Object>();
//		map.put("id", 58893);
//		map.put("islogo", 2);
//		map.put("picurl", "img/M00/01/CE/wKgyMld976yAECDLAAJMleOszAo573.jpg");
//		
//		Map<Object,Object> picmap = new HashMap<Object,Object>();
//		picmap.put("pic_cover", map);
//		String cover = JSONObject.toJSONString(map);
		/*if(mSellers != null && !mSellers.isEmpty()){
			for(MSeller mSeller : mSellers){
				
				mSeller.setPic_cover(cover);
				//更新数据
				Update update = new Update();
				update.set("pic_cover", cover);
				try{
					mongoTemplate.updateMulti(new Query(), update,propertiesUtil.getValue("seller", "conf_common.properties"));
				
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}*/
		Update update = new Update();
		Double price =1.0;
		for(int i=0; i<mSellers.size(); i++){
			if(i <= 200){
				++price;
				update.set("consume", price);
				
				try{
					mongoTemplate.updateMulti(new Query(), update,propertiesUtil.getValue("seller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
		
		return new BaseResponse(ResponseCode.SUCCESS,"成功"+mSellers.size());
	}
	
	
	
	public final Object insert(BaseRequest baseRequest){
		try{
			int veiws = 10;
			int saves = 5;
			int cumsums=13;
			List<Map<Object,Object>> sellers = sellerInfoDao.findAll();
			if(sellers != null && !sellers.isEmpty()){
				for(Map<Object,Object> seller : sellers){
					String sellerid = ObjectUtils.toString(seller.get("sellerid"));
					if(StringUtils.isNotEmpty(sellerid)){
						
						MSeller mseller = new MSeller();
						mseller.setSellerid(ObjectUtils.toString(seller.get("sellerid")));
						mseller.setAddress(ObjectUtils.toString(seller.get("address")));
						mseller.setAgio(ObjectUtils.toString(seller.get("agio")));
						mseller.setAgio_agio(ObjectUtils.toString(seller.get("agio_agio")));
						mseller.setAgio_type(ObjectUtils.toString(seller.get("ogio_type")));
						mseller.setAgio_time(ObjectUtils.toString(seller.get("agio_time")));
						mseller.setAgioAgioNum(StringUtils.isNotEmpty(ObjectUtils.toString(seller.get("agio_agio_num")))?Double.parseDouble(ObjectUtils.toString(seller.get("agio_agio_num"))):0.0);
						mseller.setAgreement(ObjectUtils.toString(seller.get("agreement")));
						mseller.setArea(ObjectUtils.toString(seller.get("area")));
						mseller.setAreaname(ObjectUtils.toString(seller.get("areaname")));
						mseller.setBewrite(ObjectUtils.toString(seller.get("bewrite")));
						mseller.setBusiness(ObjectUtils.toString(seller.get("business")));
						mseller.setCategory(ObjectUtils.toString(seller.get("category")));
						mseller.setCity(ObjectUtils.toString(seller.get("city")));
						mseller.setData_source(ObjectUtils.toString(seller.get("data_source")));
						mseller.setDate_operate(ObjectUtils.toString(seller.get("date_operate")));
						mseller.setDebit(ObjectUtils.toString(seller.get("debit")));
						mseller.setEmail(ObjectUtils.toString(seller.get("email")));
						mseller.setEntry(ObjectUtils.toString(seller.get("entry")));
						mseller.setEvalidity(ObjectUtils.toString(seller.get("evalidity")));
						mseller.setExamineinfo(ObjectUtils.toString(seller.get("examineinfo")));
						mseller.setFatherid(ObjectUtils.toString(seller.get("fatherid")));
						mseller.setFlat_agio(ObjectUtils.toString(seller.get("flat_agio")));
						mseller.setFullname(ObjectUtils.toString(seller.get("fullname")));
						mseller.setGenre(ObjectUtils.toString(seller.get("genre")));
						
						mseller.setGive(ObjectUtils.toString(seller.get("give")));
						mseller.setIdentity(ObjectUtils.toString(seller.get("identity")));
						mseller.setIdentityfurl(ObjectUtils.toString(seller.get("identityfurl")));
						mseller.setIdentityzurl(ObjectUtils.toString(seller.get("identityzurl")));
						mseller.setIntroduce(ObjectUtils.toString(seller.get("introduce")));
						mseller.setIs_virtual(ObjectUtils.toString(seller.get("is_virtual")));
						mseller.setIsforce(ObjectUtils.toString(seller.get("isforce")));
						mseller.setIslock(ObjectUtils.toString(seller.get("islock")));
						mseller.setIsfees(ObjectUtils.toString(seller.get("isfees")));
						mseller.setIsmultiple(ObjectUtils.toString(seller.get("ismultiple")));
						mseller.setIsonline(ObjectUtils.toString(seller.get("isonline")));
						mseller.setIsparking(ObjectUtils.toString(seller.get("isparking")));
						mseller.setIsprotocol(ObjectUtils.toString(seller.get("isprotocol")));
						mseller.setIswifi(ObjectUtils.toString(seller.get("iswifi")));
						mseller.setJointid(ObjectUtils.toString(seller.get("jointid")));
						mseller.setLabel(ObjectUtils.toString(seller.get("label")));
						mseller.setLicensefurl(ObjectUtils.toString(seller.get("licensefurl")));
						mseller.setLicenseid(ObjectUtils.toString(seller.get("licenseid")));
						mseller.setLicenseurl(ObjectUtils.toString(seller.get("licenseurl")));
						mseller.setLssellername(ObjectUtils.toString(seller.get("lssellername")));
						mseller.setOrder(StringUtils.isNotEmpty(ObjectUtils.toString(seller.get("order")))?Integer.parseInt(ObjectUtils.toString(seller.get("order"))):0);
						mseller.setOrgid(ObjectUtils.toString(seller.get("orgid")));
						mseller.setPhoneid(ObjectUtils.toString(seller.get("phoneid")));
						mseller.setProvince(ObjectUtils.toString(seller.get("province")));
						mseller.setRatio(ObjectUtils.toString(seller.get("ratio")));
						mseller.setRemarks(ObjectUtils.toString(seller.get("remarks")));
						mseller.setReturnrmb(ObjectUtils.toString(seller.get("returnrmb")));
						mseller.setRule(ObjectUtils.toString(seller.get("rule")));
						mseller.setSdate(ObjectUtils.toString(seller.get("sdate")));
						mseller.setSeller_grade(ObjectUtils.toString(seller.get("seller_grade")));
						mseller.setSellername(ObjectUtils.toString(seller.get("sellername")));
						mseller.setSexplain(ObjectUtils.toString(seller.get("sexplain")));
						mseller.setSigndate(ObjectUtils.toString(seller.get("signdate")));
						mseller.setSsid(ObjectUtils.toString(seller.get("ssid")));
						mseller.setStaffid(ObjectUtils.toString(seller.get("staffid")));
						mseller.setStatus(ObjectUtils.toString(seller.get("status")));
						mseller.setSvalidity(ObjectUtils.toString(seller.get("svalidity")));
						mseller.setTel(ObjectUtils.toString(seller.get("tel")));
						mseller.setTips(ObjectUtils.toString(seller.get("tips")));
						mseller.setTradename(ObjectUtils.toString(seller.get("tradename")));
						mseller.setTypename(ObjectUtils.toString(seller.get("typename")));
						mseller.setUdate(ObjectUtils.toString(seller.get("udate")));
						mseller.setWifi_pwd(ObjectUtils.toString(seller.get("wifi_pwd")));
						mseller.setYledger(ObjectUtils.toString(seller.get("yledger")));
						mseller.setZoneid(ObjectUtils.toString(seller.get("zoneid")));
						
						//查询商铺图片
						List<Map<Object,Object>> images = sellerInfoDao.querySellerPic(Integer.parseInt(seller.get("sellerid").toString()));
						
						List<Map<Object,Object>> image1 = new ArrayList<Map<Object,Object>>();
						if(images != null && !images.isEmpty()){
							for(Map<Object,Object> image : images){
								Integer type = Integer.parseInt(image.get("islogo").toString());
								Map<Object,Object> imagemap = new HashMap<Object,Object>();
								imagemap.put("id", image.get("id"));
								imagemap.put("url", image.get("url"));
								imagemap.put("islogo", type);
								switch(type){
								
									case 0://环境图
										image1.add(imagemap);
										break;
									case 1:
										String piclogo = JSONObject.toJSONString(imagemap);
										mseller.setPic_logo(piclogo);
										break;
									case 2:
										String cover = JSONObject.toJSONString(imagemap);
										mseller.setPic_cover(cover);
										break;
								}
							}
							
							//环境图
							String pic = JSONObject.toJSONString(image1);
							mseller.setPic_pics(pic);
						}
						
						Map<Object,Object> land = sellerInfoDao.querySellerLandMark(Integer.parseInt(sellerid));
						Coordinate cd = new Coordinate();
						cd.setLatitude(StringUtils.isNotEmpty(ObjectUtils.toString(land.get("latitude")))?Double.parseDouble(ObjectUtils.toString(land.get("latitude"))):0.0);
						cd.setLongitude(StringUtils.isNotEmpty(ObjectUtils.toString(land.get("longitude")))?Double.parseDouble(ObjectUtils.toString(land.get("longitude"))):0.0);
						
						mseller.setCoordinate(cd);
						
						Map<Object,Object> livemap = sellerInfoDao.findAllVsLive(sellerid);
					
						if(livemap != null){
							if(Integer.parseInt(livemap.get("zhibo_type").toString())==1){
								mseller.setIs_live(ObjectUtils.toString(livemap.get("zhibo_type")));
								mseller.setIs_advance("0");
								mseller.setWeights(ObjectUtils.toString(livemap.get("liveRank")));
							}else{
								mseller.setIs_live("0");
								mseller.setIs_advance(ObjectUtils.toString(livemap.get("zhibo_type")));
								mseller.setWeights(ObjectUtils.toString(livemap.get("liveRank")));
							}
						}
						
						veiws += 10;
						saves += 5;
						cumsums += 6;
						
						mseller.setViews(String.valueOf(veiws));
						mseller.setSaved(String.valueOf(saves));
						mseller.setConsumption(String.valueOf(cumsums));
						
						
						//插入 mongdb
						
						mongoTemplate.insert(mseller, propertiesUtil.getValue("seller", "conf_common.properties"));
					}
				}
			}
			return new BaseResponse(ResponseCode.SUCCESS,"成功");
		}catch(Exception e){
			
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"成功");
		}
		
	}
	
	
	public List<DBObject> nearSphere(String collection, String locationField, Point center,  
        							long minDistance, long maxDistance, DBObject query, int limit) {  
			if(query==null)  
				query = new BasicDBObject();  
				
			query.put(locationField,
				new BasicDBObject("$nearSphere",  
						new BasicDBObject("$geometry",  
							new BasicDBObject("type","Point").append("coordinates",new double[]{center.getX(),center.getY()})
				        ).append("$minDistance",minDistance).append("$maxDistance",maxDistance)
				)
			);
			
			System.out.println(query);
			
			return mongoTemplate.getCollection(collection).find(query).limit(limit).toArray();
	}  

	public Object list(BaseRequest baseRequest){
		List<Map<String,Object>> output = new ArrayList<Map<String,Object>>();
		
		String collection = "";
		try{
			collection = propertiesUtil.getValue("seller", "conf_common.properties");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String locationField = "coordinate";
		long minDistance =100l;
		
		long maxDistance = 200l;
		
		Criteria criteria = new Criteria();
		criteria.and("status").is("3");
		

		
		Integer pageSize = 8;
		
		Point center = new Point(103.8427203685,30.687386293419);
		
		List<DBObject> result = 
			nearSphere(collection, locationField, center, minDistance, maxDistance, new Query(criteria).getQueryObject(), pageSize);
		
		for(DBObject obj : result){
			DBObject dbo = (DBObject) obj.get("coordinate");
			Double lon = Double.parseDouble(dbo.get("longitude").toString());
			Double lat = Double.parseDouble(dbo.get("latitude").toString());
			Double ranges = GeoHashUtil.getDistance(lon, lat, center.getX(), center.getY());
			
			Map<String,Object> r = new HashMap<String,Object>();
			r.put("lon", lon);
			r.put("lat", lat);
			r.put("ranges", ranges);
			
			output.add(r);
			
		}
		return output;
	}
	
	public Object findAll(BaseRequest baseRequest){
		
		int counts = 0;
		
		Set<Integer> uids = new HashSet<Integer>();
		Criteria criteria = new Criteria();
		criteria.and("zoneid").is(243);
		criteria.and("operate").is(2);
		
		List<XmnZone> xmnZones = new ArrayList<XmnZone>();
		try{
			
			xmnZones = 
				mongoTemplate.find(new Query(criteria), XmnZone.class, propertiesUtil.getValue("xmnZone", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(!xmnZones.isEmpty()){
			for(XmnZone xmnZone : xmnZones){
				LiverInfo liveinfo = liveUserDao.queryLiverByUid(xmnZone.getUid().longValue());
				Criteria criteria2 = new Criteria();
				criteria2.and("uid").is(xmnZone.getUid());
				criteria2.and("operate").is(2);
				if(liveinfo == null){
					try{
						mongoTemplate.remove(new Query(criteria2), propertiesUtil.getValue("xmnZone", "conf_common.properties"));
						++counts;
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}
		}
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("counts", counts);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(map);
		return response;
	}
	
}
