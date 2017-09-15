package com.xmniao.xmn.core.catehome.service;

import java.util.List;

import com.xmniao.xmn.core.catehome.request.SearchKeywordsRequest;
import com.xmniao.xmn.core.catehome.response.SearchKeywordsResponse;

/**
 * 
* @projectName: xmnService 
* @ClassName: SearchService    
* @Description:搜索接口   
* @author: liuzhihao   
* @date: 2017年2月21日 上午9:48:10
 */
public interface SearchKeywordsService {

	/**
	 * 分页查询
	 * 搜索关键词查询
	 * 查询与套餐有关的店铺信息
	 * @param searchKeywordsRequest
	 * @return
	 */
	List<SearchKeywordsResponse> findSellersIsComboByPage(String uid,Double lat,Double lon,Integer cityId,String keyword,Integer page,Integer pageSize) throws Exception;
	
	/**
	 * 关键词搜索 
	 * @param searchKeywordsRequest
	 * @return
	 */
	Object searchSellersByKeywords(SearchKeywordsRequest searchKeywordsRequest);
	
}
