package com.xmniao.xmn.core.seller.service;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.common.request.seller.RecomSellerRequest;
import com.xmniao.xmn.core.seller.entity.SearchCommentSellerRequest;
import com.xmniao.xmn.core.seller.entity.SellerPhotoRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerService    
* @Description:商铺service   
* @author: liuzhihao   
* @date: 2016年11月30日 上午10:13:32
 */
public interface SellerService {

	/**
	 * 推荐商铺
	 * @param page
	 * @return
	 */
	public Object recommendSellers(RecomSellerRequest recomSellerRequest);
	
	
	
	public List<Map<Object,Object>> getSellers(List<MSeller> list,Double lon,Double lat,Integer uid);
	

	public Map<Object,Object> getSameTasteSellers(RecomSellerRequest recomSellerRequest);
	
	public Map<Object,Object> getUneatSellers(RecomSellerRequest recomSellerRequest);
	
	public Object queryCustomisedSellers(RecomSellerRequest recomSellerRequest);


	/**
	 * 查询店铺相册(点评图片)
	 * @Title:querySellerPhotos
	 * @Description: 查询店铺相册(点评图片)
	 * @param request 店铺id请求类
	 * @return Object 
	 * 2017年5月15日上午11:47:21
	 */
	public Object querySellerPhotos(SellerPhotoRequest request);



	/**
	 * 通过关键字查询商圈下的匹配店铺，关键字为空时查询商圈下所有店铺
	 * @Title:chooseCommentSeller
	 * @Description:选择点评店铺(列表显示选择商圈范围内的店铺,如果有创建新位置并且后台审核通过，列表同步记录)
	 * @param searchCommentSellerRequest zoneid 和 keyword
	 * @return Object 店铺列表
	 * 2017年5月17日上午11:59:02
	 */
	public Object searchCommentSellerByKeyword(SearchCommentSellerRequest searchCommentSellerRequest);
}
