package com.xmniao.xmn.core.system_settings.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.system_settings.entity.TRoleAuthority;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：RoleAuthorityDao
 * 
 * @类描述： 角色资源
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月19日10时11分27秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface RoleAuthorityDao extends BaseDao<TRoleAuthority>{
	@DataSource("slave")
	public List<Long> getAuthority(Long id);
	
	public void deleteByRole(Long id);
	
}
