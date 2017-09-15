package com.xmniao.xmn.core.recruit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SearchShopRequest;
import com.xmniao.xmn.core.xmer.entity.MSeller;

/**   
 * 项目名称：xmnService   
 * 类名称：SearchShopService   
 * 类描述：根据关键词查询商铺列表   
 * 创建人：zhengyaowen
 * 创建时间：2016年5月24日 下午6:25:02   
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version     
 */
@Service
public class SearchShopService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SearchShopService.class);
	
	/**
	 * 注入SessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

//	@Autowired
//	private SellerDao sellerDao;
	@Autowired
	private MongoBaseService mongoBaseService;
	
	/**
	* @Title: searchShopByKeywork
	* @Description: 根据关键词查询商铺列表
	* @param SearchShopRequest
	* @return Object 返回类型
	* @author zhengyaowen
	* @update 
	* @throws
	*/
	public Object searchShopByKeywork(SearchShopRequest searchShopRequest){
		
		try{
			String keywork = searchShopRequest.getKeyword();
			keywork = new String(keywork.getBytes("ISO-8859-1"),"UTF-8");
			
//			List<Map<Object,Object>> shopList = sellerDao.querySellerByKeywork(keywork);
//			if(shopList == null || shopList.size() ==0){
//				return new BaseResponse(ResponseCode.DATA_NULL,"没有数据");
//			}
			
			List<MSeller> sellerList =	mongoBaseService.findLikeKeyword("sellername", keywork, MSeller.class);
			if(sellerList == null || sellerList.size() ==0){
				return new BaseResponse(ResponseCode.DATA_NULL,"没有数据");
			}
				
			List<Map<Object,Object>> shops = new ArrayList<Map<Object,Object>>();
			for(MSeller mSeller : sellerList){
				Map<Object,Object> map = new HashMap<Object,Object>();
				map.put("sellerid", mSeller.getSellerid());
				map.put("sellername", mSeller.getSellername());
				shops.add(map);
			}
			
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			Map<Object,Object> result = new HashMap<Object,Object>();
			result.put("shops", shops);
			response.setResponse(result);
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据关键词查询商铺列表失败！", e);
			return new BaseResponse(ResponseCode.FAILURE,"根据关键词查询商铺列表失败！");
		}
		
	}
}
