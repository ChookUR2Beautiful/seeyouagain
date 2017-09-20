package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRankDetail;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLiveFansRankDetailDao
 * 
 * 类描述： 直播粉丝级别返还模式Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-25 下午2:24:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BLiveFansRankDetailDao {
	
	/**
	 * 
	 * 方法描述：根据主键Id删除返还模式 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-24下午6:13:28 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="burs")
    int deleteById(Integer id);
    
    /**
     * 
     * 方法描述：根据主键Id查询返还模式 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:13:32 <br/>
     * @param id
     * @return
     */
	@DataSource(value="burs")
    BLiveFansRankDetail selectById(Integer id);
    
    
    /**
     * 
     * 方法描述：获取返还模式列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:32:02 <br/>
     * @param record
     * @return
     */
	@DataSource(value="burs")
    List<BLiveFansRankDetail> getList(BLiveFansRankDetail record);
    
    /**
     * 
     * 方法描述：获取返还模式数量<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:35:26 <br/>
     * @param record
     * @return
     */
	@DataSource(value="burs")
    long count(BLiveFansRankDetail record);

    /**
     * 
     * 方法描述：添加粉丝级别返还模式<br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:13:37 <br/>
     * @param record
     * @return
     */
	@DataSource(value="burs")
    int add(BLiveFansRankDetail record);

    /**
     * 
     * 方法描述：更新粉丝级别返还模式 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午6:13:41 <br/>
     * @param record
     * @return
     */
	@DataSource(value="burs")
    int update(BLiveFansRankDetail record);

}