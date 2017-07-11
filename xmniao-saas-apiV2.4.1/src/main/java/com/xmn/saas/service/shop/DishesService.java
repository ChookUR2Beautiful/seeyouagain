package com.xmn.saas.service.shop;

import java.util.List;

import com.xmn.saas.entity.shop.Dishes;

/**
 * 
*      
* 类名称：DishesService   
* 类描述：   推荐菜
* 创建人：xiaoxiong   
* 创建时间：2016年9月28日 下午5:22:06   
* 修改人：xiaoxiong   
* 修改时间：2016年9月28日 下午5:22:06   
* 修改备注：   
* @version    
*
 */
public interface DishesService {
	
	/**
	 * 
	 * @Description: 查询推荐菜列表
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	List<Dishes> queryFoodList(Integer sellerid);

	/**
	 * 
	 * @Description: 根据推荐菜ID查询推荐菜详情
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	Dishes queryFoodById(Integer id);
	
	/**
	 * 
	 * @Description: 添加推荐菜
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 */
	Integer create(Dishes converToDishes);
	
	/**
	 * 
	 * @Description: 修改推荐菜
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 */
	void modify(Dishes dishes);

}
