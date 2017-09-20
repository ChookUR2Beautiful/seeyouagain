/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.dao;


import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerConfig;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TexperienceofficerConfigDao
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年5月17日 下午3:23:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface TExperienceofficerConfigDao {
	TExperienceofficerConfig getConfig(TExperienceofficerConfig config);
	int add(TExperienceofficerConfig config);
	int update(TExperienceofficerConfig config);
	
}
