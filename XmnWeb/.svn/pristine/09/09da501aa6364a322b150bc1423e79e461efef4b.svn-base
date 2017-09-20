package com.xmniao.xmn.core.system_settings.dao;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.system_settings.entity.TUser;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：UserDao
 * 
 * @类描述： 用户
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月12日15时07分03秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface UserDao extends BaseDao<TUser>{

	/**
	 * 检查用户唯一性
	 * @param username
	 * @return
	 */
	public long getUsernameCount(String username);
	
	/**
	 * 用户登录
	 * @param u
	 * @return
	 */
	TUser loginCheck(TUser u);
	
	/**
	 * 删除角色
	 * @param objects
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRole(Object[] objects);
	
}
