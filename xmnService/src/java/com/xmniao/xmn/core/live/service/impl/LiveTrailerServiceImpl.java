package com.xmniao.xmn.core.live.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.common.Page;
import com.xmniao.xmn.core.live.service.LiveTrailerService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: LiveTrailerServiceImpl    
* @Description:直播预告列表   
* @author: liuzhihao   
* @date: 2016年11月29日 下午2:26:44
 */
@Service
public class LiveTrailerServiceImpl implements LiveTrailerService{
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	/**
	 * 直播预告列表
	 */
	@Override
	public List<Map<Object, Object>> queryLiveTrailer(Page page) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		try{
			
			//设置查询条件
			if(page.getDateType() != null){
				switch(page.getDateType()){
				case 1://今天
//					page.setStartTime(DateUtil.format(DateUtil.now(), "yyyy-MM-dd")+" 00:00:00");//今天 2016-11-29 00:00:00
//					page.setEndTime(DateUtil.format(DateUtil.now(), "yyyy-MM-dd")+" 23:59:59");//今天 2016-11-29 23:59:59
					page.setStartTime("2016-05-05 00:00:00");//测试数据
					page.setEndTime("2016-05-05 23:59:59");//测试数据
					break;
				case 2://明天
					page.setStartTime(DateUtil.format(DateUtil.tomorrow(), "yyyy-MM-dd")+" 00:00:00");//明天 2016-11-30 00:00:00
					page.setEndTime(DateUtil.format(DateUtil.tomorrow(), "yyyy-MM-dd")+" 23:59:59");//明天 2016-11-30 23:59:59
					break;
				case 3://后天
					page.setStartTime(DateUtil.format(DateUtil.addDay(DateUtil.now(), 2), "yyyy-MM-dd")+" 00:00:00");//后天 2016-11-31 00:00:00
					page.setEndTime(DateUtil.format(DateUtil.addDay(DateUtil.now(), 2), "yyyy-MM-dd")+" 23:59:59");//后天 2016-11-31 23:59:59
					break;
				case 4://最近三天
					page.setStartTime(DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));//今天 系统当前时间
					page.setEndTime(DateUtil.format(DateUtil.addDay(DateUtil.now(), 3),"yyyy-MM-dd")+" 23:59:59");//三天后
					break;
				case 5://最近一周
					page.setStartTime(DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));//今天 系统当前时间
					page.setEndTime(DateUtil.format(DateUtil.addDay(DateUtil.now(), 7),"yyyy-MM-dd")+" 23:59:59");//七天后
					break;
				}
			}
			
			Criteria criteria = new Criteria();
			
			Query query = new Query(criteria);
			//根据城市查询所有的上去直播啊
			List<MSeller> mSellers = mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			
			if(mSellers != null && !mSellers.isEmpty()){
				for(MSeller seller : mSellers){
					Map<Object,Object> sellermap = new HashMap<Object,Object>();
					sellermap.put("sellerid", seller.getSellerid());
					sellermap.put("address", seller.getAddress());
					sellermap.put("is_pay", seller.getIs_pay());
					sellermap.put("category", seller.getCategory());
					sellermap.put("city", seller.getCity());
					sellermap.put("signdate", seller.getSigndate());
					result.add(sellermap);
					
				}
				return result;
			}
			
			Map<Object,Object> map = new HashMap<Object,Object>();
			//设置查询条件
			if(page.getDateType() != null){
				switch(page.getDateType()){
				case 1://今天
					map.put("startTime", DateUtil.format(DateUtil.now(), "yyyy-MM-dd")+"00:00:00");//今天 2016-11-29 00:00:00
					map.put("endTime", DateUtil.format(DateUtil.now(), "yyyy-MM-dd")+"23:59:59");//今天 2016-11-29 23:59:59
					break;
				case 2://明天
					map.put("startTime", DateUtil.format(DateUtil.tomorrow(), "yyyy-MM-dd")+"00:00:00");//明天 2016-11-30 00:00:00
					map.put("endTime", DateUtil.format(DateUtil.tomorrow(), "yyyy-MM-dd")+"23:59:59");//明天 2016-11-30 23:59:59
					break;
				case 3://后天
					map.put("startTime", DateUtil.format(DateUtil.addDay(DateUtil.now(), 2), "yyyy-MM-dd")+"00:00:00");//后天 2016-11-31 00:00:00
					map.put("endTime", DateUtil.format(DateUtil.addDay(DateUtil.now(), 2), "yyyy-MM-dd")+"23:59:59");//后天 2016-11-31 23:59:59
					break;
				case 4://最近三天
					map.put("startTime", DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));//今天 系统当前时间
					map.put("endTime", DateUtil.format(DateUtil.addDay(DateUtil.now(), 3),"yyyy-MM-dd")+"23:59:59");//三天后
					break;
				case 5://最近一周
					map.put("startTime", DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));//今天 系统当前时间
					map.put("endTime", DateUtil.format(DateUtil.addDay(DateUtil.now(), 7),"yyyy-MM-dd")+"23:59:59");//三天后
					break;
				}
			}
			
			map.put("lon", page.getLon());
			map.put("lat", page.getLat());
			
			if(StringUtils.isNotEmpty(page.getSessiontoken())){
				String uid = sessionTokenService.getStringForValue(page.getSessiontoken())+"";
				if(StringUtils.isNotEmpty(uid)){
					//登录用户
				}
			}else{
				//非登录用户
				if(page.getRelevantType() != null){
					
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return null;
	}

}
