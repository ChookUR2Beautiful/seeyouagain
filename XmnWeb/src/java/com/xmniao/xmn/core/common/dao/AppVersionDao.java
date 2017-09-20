package com.xmniao.xmn.core.common.dao;


import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.common.entity.TAppVersion;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：AppVersionDao
 * 
 * @类描述： 版本
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月19日11时51分56秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface AppVersionDao extends BaseDao<TAppVersion>{
	
	void updateNewVersion(TAppVersion app);

}
