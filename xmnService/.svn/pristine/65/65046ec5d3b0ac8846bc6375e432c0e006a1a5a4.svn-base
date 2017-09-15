package com.xmniao.xmn.core.sellerPackage.service;

import java.util.List;

import com.xmniao.xmn.core.sellerPackage.request.RecomboRequest;
import com.xmniao.xmn.core.sellerPackage.response.ComboListResponse;

/**
 * 
* @projectName: xmnService 
* @ClassName: ComboService    
* @Description:套餐接口
* @author: liuzhihao   
* @date: 2017年2月20日 上午9:59:54
 */
public interface ComboService {

	/**
	 * 按距离查询套餐列表
	 * 	规则:按照用户当前的坐标距离商铺距离排序
	 * @return
	 * @throws Exception 
	 */
	List<ComboListResponse> findAllByRanges(Double lat,Double lon,Integer cityId,Integer tradeId) throws Exception;
	
	/**
	 * 详情推荐套餐
	 */
	List<ComboListResponse> recommendCombo(Double lat,Double lon,Integer cityId) throws Exception;
	
	/**
	 * 首页推荐套餐
	 * @return
	 */
	List<ComboListResponse> homeRecommendCombo(Double lat,Double lon,Integer cityId)throws Exception;
	
	/**
	 * 获取今日套餐推荐
	 * @param recomboRequest
	 * @return
	 */
	Object getRecommendCombo(RecomboRequest recomboRequest);
	
	/**
	 * 获取套餐列表
	 * @return
	 */
	Object getComboList(RecomboRequest recomboRequest);
	
	
}
