package com.xmniao.xmn.core.system_settings.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.system_settings.entity.TAuthorityArea;
import com.xmniao.xmn.core.system_settings.entity.TRoleArea;

public interface RoleAreaDao extends BaseDao<TRoleArea>{
	
	/**
	 * 获取区域资源权限列表
	 */
	List<TAuthorityArea> getAuthorityAreaList();
	
	
	TRoleArea getRoleArea(TRoleArea roleArea);
	
	List<String> getAidByFid(Map<String, Object> map);
}
