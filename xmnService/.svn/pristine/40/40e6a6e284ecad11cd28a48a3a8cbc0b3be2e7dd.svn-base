package com.xmniao.xmn.core.seller.service;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.common.request.RemoveBCRecordRequeset;
import com.xmniao.xmn.core.common.request.seller.UserSellerRequest;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserSellerService    
* @Description:跟用户有关的商铺接口
* @author: liuzhihao   
* @date: 2016年11月23日 下午4:54:36
 */
public interface UserSellerService {

	/**
	 * 浏览记录
	 * @return
	 */
	public List<Map<Object,Object>> querySellerByBrowse(UserSellerRequest userSellerRequest);
	
	/**
	 * 收藏记录
	 * @return
	 */
	public List<Map<Object,Object>> querySellerByCollect(UserSellerRequest userSellerRequest);
	
	/**
	 * 删除浏览记录
	 * @param sellerInfoRequest
	 * @return
	 */
	public int removeBrowse(RemoveBCRecordRequeset removeBCRecordRequeset);
	
	/**
	 * 删除收藏记录
	 * @param sellerInfoRequest
	 * @return
	 */
	public int removeCollect(RemoveBCRecordRequeset removeBCRecordRequeset);
	
	/**
	 * 与我相关的菜单列表
	 * @param baseRequest
	 * @return
	 */
	public List<Map<Object,Object>> queryAboutUs(BaseRequest baseRequest);
	
	/**
	 * 与我相关的商铺列表
	 * @param userSellerRequest
	 * @return
	 */
	public Object mainSellerList(UserSellerRequest userSellerRequest);
	
	
	/**
	 * 消费
	 * @param userSellerRequest
	 * @return
	 */
	public List<Map<Object,Object>> queryConsumList(UserSellerRequest userSellerRequest);
	
	/**
	 * 浏览
	 * @param userSellerRequest
	 * @return
	 */
	public List<Map<Object,Object>> queryBrowseList(UserSellerRequest userSellerRequest);
	
	/**
	 * 收藏
	 * @param userSellerRequest
	 * @return
	 */
	public List<Map<Object,Object>> queryCollectList(UserSellerRequest userSellerRequest);
	
	
	/**
	 * 和我相似的人也喜欢的商铺列表
	 * @param userSellerRequest
	 * @return
	 */
	public List<Map<Object,Object>> samePersonLikeList(UserSellerRequest userSellerRequest);
	
}
