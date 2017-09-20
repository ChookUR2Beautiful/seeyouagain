package com.xmniao.xmn.core.common.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.common.entity.TBusiness;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：BusinessDao
 * 
 * @类描述： 商圈
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月12日17时41分48秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface BusinessDao extends BaseDao<TBusiness>{
	@DataSource("slave")
	public List<TBusiness>  businessList(int areaId);
	@DataSource("slave")
	public List<TBusiness> getAreaBusinessList(TBusiness business);
	@DataSource("slave")
	public Long getAreaBusinessListcount(TBusiness business);
	@DataSource("slave")
	public TBusiness getObjectBybid(Long bid);
	
	/**
	 * 根据区域id删除商圈
	 * @return
	 */
	public Integer deletebid(Object[] objects);

	/**
	 * 获取下拉列表数据
	 * @param business
	 * @return
	 */
	@DataSource("slave")
	public List<TBusiness> getSelect(TBusiness business);
	
	/**
	 * 根据区域ID更新改区域下所有商圈的状态：开通/未开通
	 * @param business
	 */
	public void updateIsOpen(TBusiness business);
	
	/**
	 * 区域管理中开通或者关闭商圈
	 * @param tArea
	 * @return
	 */
	public void updateBussinessStatus(TBusiness business);
}

