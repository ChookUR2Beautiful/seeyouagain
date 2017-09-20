package com.xmniao.xmn.core.business_cooperation.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 *@ClassName:SalesmanManagementDao
 *@Description:合作商业务员管理Dao层
 *@author hls
 *@date:2016年3月15日上午9:26:50
 */
public interface SalesmanManagementDao extends BaseDao<TStaff>{
	/**
	 * @Title:getSalesman
	 * @Description:根据合作商编号或者合作商名称查询业务员
	 * @param joint
	 * @return List<TStaff>
	 * @throw
	 */
	public List<TStaff> getSalesman(TJoint joint);
	/**
	 * @Title:getSalesmanCount
	 * @Description:根据合作商编号或者合作商名称查询业务员记录条数
	 * @param joint
	 * @return Long
	 * @throw
	 */
	public Long getSalesmanCount(TJoint joint);
	/**
	 * @Title:getSalesmanById
	 * @Description:根据合作商业务员Id查询业务员信息用于初始化业务员修改页面
	 * @param staffid
	 * @return TStaff
	 * @throw
	 */
	public TStaff getSalesmanById(Long staffid);
	/**
	 * 获取合作商初始化下拉列表（合作商业务员添加）
	 * @return
	 */
	@DataSource("slave")
	public List<TJoint> getJointList();
	/**
	 * @Title:vailStaff
	 * @Description:验证业务员账号是否存在
	 * @return Integer
	 * @throw
	 */
	@DataSource("slave")
	public Integer vailStaff(Long phoneid);
	
}
