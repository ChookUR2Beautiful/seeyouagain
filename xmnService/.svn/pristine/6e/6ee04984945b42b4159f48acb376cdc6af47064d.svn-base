package com.xmniao.xmn.core.catehome.service;

import com.xmniao.xmn.core.common.request.AreaVersionRequest;
import com.xmniao.xmn.core.common.request.XmnHomeRequest;
import com.xmniao.xmn.core.common.request.catehome.CityRequest;
import com.xmniao.xmn.core.common.request.catehome.CustomisedRequest;
import com.xmniao.xmn.core.common.request.catehome.LocationRequest;
import com.xmniao.xmn.core.common.request.catehome.SearchLocationRequest;
import com.xmniao.xmn.core.common.request.catehome.SwitchPositionRequest;
import com.xmniao.xmn.core.common.request.catehome.UneatRequest;
import com.xmniao.xmn.core.common.request.catehome.UserConsumeSellerRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: XmnHomeService    
* @Description:寻蜜鸟首页service   
* @author: liuzhihao   
* @date: 2016年11月9日 下午3:00:36
 */
public interface XmnHomeService {
	
	/**
	 * 查询定位信息
	 * @param locationRequest
	 * @return
	 */
	public Object getLocation(LocationRequest locationRequest);
	
	/**
	 * 获取城市列表
	 * @param baseRequest
	 * @return
	 */
	public Object switchPosition(SwitchPositionRequest switchPositionRequest);
	
	/**
	 * 搜索定位
	 * @param searchLocationRequest
	 * @return
	 */
	public Object searchLocation(SearchLocationRequest searchLocationRequest);

	public Object customisedForm(CustomisedRequest customisedRequest);
	
	/**
	 * 城市切换
	 * @param baseRequest
	 * @return
	 */
	public Object switchCity(AreaVersionRequest areaVersionRequest);
	
	/**
	 * 专属口味--商圈列表接口
	 * @param customisedRequest
	 * @return
	 */
	public Object queryBusinessByArea(CustomisedRequest customisedRequest);
	
	/**
	 * 专属口味列表
	 * @param customisedListRequest
	 * @return
	 */
//	public Object queryCustomisedList(RecomSellerRequest recomSellerRequest);
	
	/**
	 * 专属口味--我消费过的商家记录
	 * @param  userConsumeSellerRequest
	 * @return Object
	 */
	public Object queryUserConsumeSellerList(UserConsumeSellerRequest userConsumeSellerRequest);
	
	/**
	 * 首页banner图接口实现类
	 * @param bannerListRequest
	 * @return
	 */
	public Object getBannerList(CityRequest bannerListRequest);
	
	/**
	 * 首页主播去哪儿了接口实现类
	 * @param cityRequest
	 * @return
	 */
	public Object getLiveList(CityRequest cityRequest);
	
	/**
	 * 首页商铺展示接口实现类
	 * @param xmnHomeRequest
	 * @return
	 */
	public Object getSellerList(XmnHomeRequest xmnHomeRequest);
	
	/**
	 * 查询首页口味相似的人实现类
	 * @param xmnHomeRequest
	 * @return
	 */
	public Object getSameTasteList(XmnHomeRequest xmnHomeRequest);
	
	
	//看过不如一尝
	public Object getUneatList(UneatRequest uneatRequest);
}
