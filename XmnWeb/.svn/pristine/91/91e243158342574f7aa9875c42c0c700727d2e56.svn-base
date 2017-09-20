package com.xmniao.xmn.core.live_anchor.dao;

import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveFocus;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveFocusDao
 * 
 * 类描述： 直播用户关注表DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-9-4 上午11:19:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveFocusDao extends BaseDao<TLiveFocus>{
	
	@DataSource(value="joint")
    int deleteByEndUid(Integer endUid);
	
	/**
	 * 
	 * 方法描述：随机获取一个直播用户信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-9-4下午2:19:32 <br/>
	 * @return
	 */
	@DataSource(value="burs")
	Map<String,Object> getRandomFans();
	
}