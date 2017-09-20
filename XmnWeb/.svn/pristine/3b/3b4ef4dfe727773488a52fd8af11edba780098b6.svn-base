package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.BRankRedPacketDetail;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BRankRedPacketDetailMapper
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-24 下午3:43:41 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BRankRedPacketDetailDao  extends BaseDao<BRankRedPacketDetail>{
	
	/**
	 * 
	 * 方法描述：根据等级详情id删除模式比例配置 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-24下午4:04:34 <br/>
	 * @param rankDetailId
	 * @return
	 */
	@DataSource("burs")
    int deleteByRankDetailId(Integer rankDetailId);


    /**
     * 
     * 方法描述：新增级别返还模式比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    @DataSource("burs")
    void add(BRankRedPacketDetail record);
    
    /**
     * 
     * 方法描述：批量新增级别返还模式比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    @DataSource("burs")
    Integer addBatch(List<BRankRedPacketDetail> recordList);

    /**
     * 
     * 方法描述：根据主键Id查询级别返还模式比例 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    @DataSource("burs")
    BRankRedPacketDetail selectById(Integer id);
    
    /**
     * 
     * 方法描述：获取级别返还模式比例列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    @DataSource("burs")
    List<BRankRedPacketDetail> getList(BRankRedPacketDetail record);

    /**
     * 
     * 方法描述：更新级别返还模式比例配置 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-2-24下午3:45:11 <br/>
     * @param id
     * @return
     */
    @DataSource("burs")
    Integer update(BRankRedPacketDetail record);
}