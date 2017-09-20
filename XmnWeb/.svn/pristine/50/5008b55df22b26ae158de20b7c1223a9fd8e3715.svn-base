package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshTypeDao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-6-17下午6:15:42
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface FreshTypeDao extends BaseDao<FreshType>{

	@DataSource("slave")
	List<FreshType> getAllFreshType();
	
	
	/**
	 * 获取一级生鲜类别
	 * 创建人： huang'tao
	 * 创建时间：2016-6-17下午6:15:04
	 * @return
	 */
	@DataSource("slave")
	List<FreshType> getParentList(FreshType freshType);
	
	/**
	 * 获取二级生鲜类别
	 * 创建人： huang'tao
	 * 创建时间：2016-6-18上午11:33:15
	 * @param parentFreshType
	 * @return
	 */
	@DataSource("slave")
	List<FreshType> getSubList(List<FreshType> parentFreshType);
	
	/**
	 * 获取所有一级分类
	 * 创建人： huang'tao
	 * 创建时间：2016-6-18下午3:11:32
	 * @return
	 */
	List<FreshType> getParent();


	/**
	 * 方法描述：根据排序获取所有分类
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月20日上午11:37:01 <br/>
	 * @param freshType 
	 * @param freshType
	 * @return
	 */
	@DataSource("slave")
	List<FreshType> getALLFather(FreshType freshType);


	/**
	 * 方法描述：寻找字分类
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月20日下午2:41:28 <br/>
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	List<FreshType> findChild(Integer id);


	/**
	 * 方法描述：统计父级总数
	 * 创建人： jianming 
	 * 创建时间：2016年12月20日下午2:49:44 <br/>
	 * @return
	 */
	@DataSource("slave")
	Long selectFatherTotal();


	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月21日下午6:22:09 <br/>
	 * @param id
	 */
	void delete(Integer id);


	/**
	 * 方法描述：批量删除
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月22日上午9:53:14 <br/>
	 * @param ids
	 * @param childId 
	 * @return
	 */
	int deleteMine(@Param("parentId")String parentId, @Param("childId")String childId);


	/**
	 * 方法描述：查询所有
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月24日下午5:51:15 <br/>
	 * @return
	 */
	List<FreshType> findAll();


	/**
	 * 方法描述：判断分类是否包含品牌
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月11日下午3:33:53 <br/>
	 * @param parentId
	 * @return
	 */
	List<FreshType> hasParentBrand(@Param("parentId")String parentId);


	/**
	 * 方法描述：判断分类是否包含商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月11日下午3:37:41 <br/>
	 * @param parentId
	 * @return
	 */
	List<FreshType> hasParentProduct(@Param("parentId")String parentId);


	/**
	 * 方法描述：判断子分类是否包含商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月11日下午3:42:11 <br/>
	 * @param childId
	 * @return
	 */
	List<FreshType> hasChildBrand(@Param("childId")String childId);


	/**
	 * 方法描述：判断子分类是否包含商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月11日下午3:45:34 <br/>
	 * @param childId
	 * @return
	 */
	List<FreshType> hasChildProduct(@Param("childId")String childId);
}
