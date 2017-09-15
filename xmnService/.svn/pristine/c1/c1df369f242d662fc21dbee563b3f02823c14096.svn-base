package com.xmniao.xmn.core.catehome.service;

import java.util.List;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.common.request.catehome.BestForYouRequest;
import com.xmniao.xmn.core.common.request.catehome.BestForYouTitleRequest;
import com.xmniao.xmn.core.common.request.catehome.CityRequest;
import com.xmniao.xmn.core.common.request.catehome.HomeCateCommetRequest;
import com.xmniao.xmn.core.common.request.catehome.HomeMustBuyRequest;
import com.xmniao.xmn.core.sellerPackage.response.ComboListResponse;

/**
 * 
* @projectName: xmnService 
* @ClassName: NewXmnHomeService    
* @Description:改版后首页接口   
* @author: liuzhihao   
* @date: 2017年2月22日 下午3:33:18
 */
public interface NewXmnHomeService{
	
	/**
	 * 首页banner图
	 */
	public Object getBannerList(CityRequest request); 

	/**
	 * 获取随机套餐
	 * @return
	 */
	public List<ComboListResponse> getRandomRecommendCombos(Double lat,Double lon,Integer cityId);
	
	/**
	 * 获取推荐专题
	 * @param cityID
	 * @return
	 */
	public List<HomeImageResponse> getRecommenSpecials(Integer cityId);
	
	/**
	 * 获取推荐轮播图
	 * @param cityId
	 * @return
	 */
	public List<HomeImageResponse> getRecomenBanners(Integer cityId);
	
	/**
	 * 获取推荐活动
	 * @param cityId
	 * @return
	 */
	public List<HomeImageResponse> getRecomenActivitys(Integer cityId);

	/**大版本首页Banner图
	 * @param object
	 * @return
	 */
	public Object getRecomenBanners(CityRequest object);

	/**首页为你优选
	 * @param cityRequest
	 * @return
	 */
	public Object getBestForYou(BestForYouRequest cityRequest);

	/**查询网红点评和 必买清单
	 * @param cityRequest
	 * @return
	 */
	public Object getCommentSellers(HomeCateCommetRequest request);

	/**首页必买清单
	 * @param request
	 * @return
	 */
	public Object getMustBuy(HomeMustBuyRequest request);

	/**为你优选title分类
	 * @param request
	 * @return
	 */
	public Object getBestForYouTitle(BestForYouTitleRequest request);

	
	
}
