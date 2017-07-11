package com.xmn.saas.dao.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.shop.Dishes;


@Repository
public interface DishesDao {
	
	/**
	 * 
	 * @Description: 查询商家推荐菜品列表
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	List<Dishes> queryFoodList(@Param("sellerid")Integer sellerid,@Param("fileUrl")String fileUrl);
	
	/**
	 * 
	 * @Description: 查询推荐菜详情
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	Dishes queryFoodById(@Param("id")Integer id,@Param("fileUrl")String fileUrl);
	
	/**
	 * 
	 * @Description: 添加推荐菜
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 */
	Integer create(Dishes dishes);
	
	/**
	 * 
	 * @Description: 修改推荐菜
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 */
	void modify(Dishes dishes);

}
