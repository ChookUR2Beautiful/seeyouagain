package com.xmniao.xmn.core.system_settings.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.system_settings.entity.Category;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


@Repository
public interface CategoryDao extends BaseDao<Category>{
	/**
	 * 获取一级分类
	 * @return
	 */
	@DataSource("slave")
	List<Category> getParentList(Category category);
	
	/**
	 * 获取所有一级分类
	 * @return
	 */
	@DataSource("slave")
	List<Category> getParent();
	
	/**
	 * 获取二级分类
	 * @param parentCategory
	 * @return
	 */
	@DataSource("slave")
	List<Category> getSubList(List<Category> parentCategory);
	
	/**
	 * 根据id删除分类
	 * @param id
	 */
	 void deleteById(Long id);
	 
	 @DataSource("slave")
	 List<Category> getLdAll();
	 
	 /**
	  * 获取一级分类
	  * @return List<Category>
	  */
	 @DataSource("slave")
	 List<Category> getFirstTraderList(Category category);
	 
	 /**
	  * 获取一级分类数量
	  * @return
	  */
	 @DataSource("slave")
	 Long getFirstTraderListCount(Category category);
	 
	 /**
	  * 获取二级分类
	  * @param pid 父编号
	  * @return List<Category>
	  */
	 @DataSource("slave")
	 List<Category> getTwoTraderListByPid(Category category);
	 
	 /**
	  * 获取二级分类数量
	  * @param pid 父编号
	  * @return
	  */
	 @DataSource("slave")
	 Long getTwoTraderListByPidCount(Category category);
}
