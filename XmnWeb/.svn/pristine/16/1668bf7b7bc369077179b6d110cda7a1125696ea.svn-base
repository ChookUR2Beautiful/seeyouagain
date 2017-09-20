package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveAnchor;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveAnchorDao
 *
 * 类描述：主播DAO
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午3:00:20
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveAnchorDao extends BaseDao<TLiveAnchor>{
	
	/**
	 * 
	 * 方法描述：删除主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-6下午3:06:13
	 * @param anchorId
	 * @return
	 */
	@DataSource("burs")
    int deleteByPrimaryKey(Integer anchorId);

    /**
     * 
     * 方法描述：添加主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:06:29
     * @param record
     * @return
     */
    @DataSource("burs")
    int insertSelective(TLiveAnchor record);

    /**
     * 
     * 方法描述：查询主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:06:59
     * @param anchorId
     * @return
     */
    @DataSource("burs")
    TLiveAnchor selectByPrimaryKey(Integer anchorId);

    /**
     * 
     * 方法描述：更新主播信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    int updateByPrimaryKeySelective(TLiveAnchor record);
    
    /**
     * 
     * 方法描述：获取主播列表信息
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    List<TLiveAnchor> getList(TLiveAnchor record);
    
    /**
     * 
     * 方法描述：获取主播信息表纪录数
     * 创建人： huang'tao
     * 创建时间：2016-8-6下午3:07:43
     * @param record
     * @return
     */
    @DataSource("burs")
    Long count(TLiveAnchor record);
    

}