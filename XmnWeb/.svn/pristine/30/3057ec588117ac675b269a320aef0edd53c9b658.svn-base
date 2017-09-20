package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.live_anchor.entity.TLiveRecordTagConf;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRecordTagConfDao
 * 
 * 类描述： 直播通告标签配置Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-7 上午11:30:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveRecordTagConfDao {
    
	
	/**
	 * 
	 * 方法描述：获取直播通告标签配置列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-7下午2:03:29 <br/>
	 * @param record
	 * @return
	 */
	@DataSource("slave")
	List<TLiveRecordTagConf> getList(TLiveRecordTagConf record);

    
    /**
     * 
     * 方法描述:删除指定通告ID下的所有标签 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-3-7上午11:55:11 <br/>
     * @param recordId
     * @return
     */
	@DataSource("slave")
    int deleteByRecordId(Integer recordId);

    /**
     * 
     * 方法描述：添加通告标签配置 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-3-7上午11:42:38 <br/>
     * @param record
     * @return
     */
    @DataSource("slave")
    int add(TLiveRecordTagConf record);
    
    /**
     * 
     * 方法描述：批量插入通告标签配置信息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-3-7下午2:09:21 <br/>
     * @param recordTagList
     * @return
     */
    @DataSource("slave")
    Integer addBatch(List<TLiveRecordTagConf> recordTagList);

    /**
     * 
     * 方法描述：更新通告标签配置 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-3-7上午11:51:58 <br/>
     * @param record
     * @return
     */
    @DataSource("slave")
    int update(TLiveRecordTagConf record);
}