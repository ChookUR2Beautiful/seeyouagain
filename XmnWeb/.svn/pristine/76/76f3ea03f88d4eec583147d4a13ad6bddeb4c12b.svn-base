package com.xmniao.xmn.core.nagivate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.nagivate.util.NavigateConstant;
import com.xmniao.xmn.core.navigate.dao.CategoryNavigateDao;
import com.xmniao.xmn.core.navigate.entity.CategoryNavigate;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CategoryNavigateService
 * 
 * 类描述： 用户端管理 ==> 分类导航
 * 
 * 创建人： wangzhimin
 * 
 * 创建时间：2015年9月22日14时04分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Service
public class CategoryNavigateService extends BaseService<CategoryNavigate> {

	@Autowired
	private CategoryNavigateDao categoryNavigateDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return categoryNavigateDao;
	}

	/**
	 * 获取导航列表信息
	 * @param categoryNavigate
	 * @return
	 */
	public List<CategoryNavigate> getNavigateList(CategoryNavigate categoryNavigate) {
		return categoryNavigateDao.getNavigateList(categoryNavigate);
	}

	/**
	 * 获取导航列表记录数
	 * @param categoryNavigate
	 * @return
	 */
	public Long getNavigateListcount(CategoryNavigate categoryNavigate) {
		return categoryNavigateDao.getNavigateListcount(categoryNavigate);
	}
	
	/**
	 * 查看分类导航详细页面初始化
	 * @param id
	 * @return
	 */
	public CategoryNavigate getNavigateObject(Integer nId) {
		return categoryNavigateDao.getNavigateObject(nId);
	}

	/**
	 * 根据 类别 获取最大的排序序号
	 * @param oneLevelNaviogate
	 * @return
	 */
	public Integer getMaxOrderByType(CategoryNavigate categoryNavigate) {
		return categoryNavigateDao.getMaxOrderByType(categoryNavigate);
	}

	/**
	 * 修改初始化
	 * @param valueOf
	 * @return
	 */
	public Object getNavigateObject(Long nId) {
		return categoryNavigateDao.getNavigateObject(nId);
	}

	/**
	 * 修改分类导航位置
	 * @param categoryNavigate
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOrder(CategoryNavigate categoryNavigate) {
		try{
			update(setParameter(categoryNavigate));
			if(NavigateConstant.UP_MOVE == categoryNavigate.getFlag()){ //上移
				categoryNavigateDao.updateBeforeOrder(categoryNavigate);
			}else if(NavigateConstant.DOWN_MOVE == categoryNavigate.getFlag()){ //下移
				categoryNavigateDao.updateAfterOrder(categoryNavigate);
			}
		}catch(Exception e){
			throw new ApplicationException("分类导航修改位置异常", e, new Object[] { categoryNavigate }, new String[] { "分类导航编号", categoryNavigate.getNId(), "位置更新", "位置更新" });
		}
	}
	
	private CategoryNavigate setParameter(CategoryNavigate categoryNavigate){
		Integer order = categoryNavigate.getOrder();
		if(NavigateConstant.UP_MOVE == categoryNavigate.getFlag()){ //上移
			categoryNavigate.setOrder(order - 1);
		}else if(NavigateConstant.DOWN_MOVE == categoryNavigate.getFlag()){ //下移
			categoryNavigate.setOrder(order + 1);
		}
		return categoryNavigate;
	}
}
