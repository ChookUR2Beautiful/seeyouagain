package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveBroadcast;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveBroadcastDao
 * 
 * 类描述： 直播广播DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午2:48:30 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveBroadcastDao extends BaseDao<TLiveBroadcast>{
	/**
	 * 
	 * 方法描述：根据主键删除广播 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-24下午2:59:16 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="master")
    int deleteById(Integer id);

    /**
     * 
     * 方法描述：添加广播消息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午2:58:53 <br/>
     * @param record
     * @return
     */
	@DataSource(value="master")
    void add(TLiveBroadcast record);


    /**
     * 
     * 方法描述：根据主键查询广播消息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午2:57:22 <br/>
     * @param id
     * @return
     */
	@DataSource(value="slave")
    TLiveBroadcast selectById(Integer id);

    /**
     * 
     * 方法描述：更新广播消息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午2:55:57 <br/>
     * @param record
     * @return
     */
	@DataSource(value="master")
    Integer update(TLiveBroadcast record);
    
    /**
     * 
     * 方法描述：获取广播列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-10-24下午4:56:51 <br/>
     * @param record
     * @return
     */
	@DataSource(value="slave")
    List<TLiveBroadcast> getList(TLiveBroadcast broadcast);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-24下午5:09:40 <br/>
	 * @param broadcast
	 * @return
	 */
	@DataSource(value="slave")
	Long count(TLiveBroadcast broadcast);
}