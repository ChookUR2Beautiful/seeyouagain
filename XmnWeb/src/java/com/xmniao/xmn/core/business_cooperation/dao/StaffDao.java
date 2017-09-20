package com.xmniao.xmn.core.business_cooperation.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：StaffDao
 * 
 * @类描述： 合作商员工
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月19日11时26分27秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface StaffDao extends BaseDao<TStaff>{
	/**
	 * 根据合作商id查询合作商信息
	 * @return
	 */
	@DataSource("slave")
	public TStaff getStaffByJointid(Long jointid);

	/**
	 * 获取合作商的员工
	 * @param jointid
	 * @return
	 */
	@DataSource("slave")
	public List<TStaff> getStaffsByJointid(Long jointid);

	/**
	 * 根据jointid数组获取该合作商的管理员账户
	 * @param jointids
	 * @return
	 */
	@DataSource("slave")
	public List<TStaff> getAdminsByJoint(String[] jointids);
}
