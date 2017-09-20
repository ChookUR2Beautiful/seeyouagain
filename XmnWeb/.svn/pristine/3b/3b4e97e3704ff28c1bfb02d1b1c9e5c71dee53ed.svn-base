package com.xmniao.xmn.core.xmnburs.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmnburs.entity.BursInfo;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BursInfoDao
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-15上午11:14:21
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BursInfoDao extends BaseDao<BursInfo>{
	/**
	 * 
	 * 方法描述：新增用户详细信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15上午11:18:46
	 * @param record
	 * @return
	 */
	@DataSource("burs")
    int insertBursInfo(BursInfo record);
    
	/**
	 * 
	 * 方法描述：删除用户详细信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-15上午11:22:27
	 * @param uid
	 * @return
	 */
    @DataSource("burs")
    int deleteBursInfo(Integer uid);
    
    /**
     * 
     * 方法描述：更新用户详细信息
     * 创建人： huang'tao
     * 创建时间：2016-8-15上午11:41:32
     * @param record
     * @return
     */
    @DataSource("burs")
    int updateBursInfo(BursInfo record);
}