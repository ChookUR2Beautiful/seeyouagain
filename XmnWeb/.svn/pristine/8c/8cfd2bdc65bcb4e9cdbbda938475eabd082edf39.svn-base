package com.xmniao.xmn.core.system_settings.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：RoleDao
 * 
 * @类描述： 角色
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月19日10时08分19秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface RoleDao extends BaseDao<TRole>{
	/**
	 * 查询当前用户绑定角色之外其他角色
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	List<TRole> getotherRoleList(@Param("roleid")Long id);
}
