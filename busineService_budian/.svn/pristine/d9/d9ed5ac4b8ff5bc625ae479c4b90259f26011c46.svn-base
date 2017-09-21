package com.xmniao.dao.live;

import com.xmniao.domain.live.TVerExcitationReceive;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TVerExcitationReceiveDao
 * 
 * 类描述： 会员发放奖励记录DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-26 下午3:27:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVerExcitationReceiveDao {
	
	/**
	 * 
	 * 方法描述：获取发放记录列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-26下午3:47:46 <br/>
	 * @param record
	 * @return
	 */
	List<TVerExcitationReceive> getList(TVerExcitationReceive record);

	/**
	 * 
	 * 方法描述：添加奖励发放记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-26下午3:48:07 <br/>
	 * @param record
	 * @return
	 */
    int add(TVerExcitationReceive record);

    /**
     * 
     * 方法描述：更新奖励发放记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-26下午3:49:02 <br/>
     * @param record
     * @return
     */
    int update(TVerExcitationReceive record);
    
    /**
     * 
     * 方法描述：批量添加V客奖励发放记录 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-5-27上午10:48:25 <br/>
     * @param receiveList
     * @return
     */
    int addBatch(List<TVerExcitationReceive> receiveList);

	/**
	 * 查询用户待领取的奖励
	 * @param type
	 * @param uid
	 * @return
	 */
	List<TVerExcitationReceive> selectUnclaimedByType(@Param("type") Integer type, @Param("uid") Integer uid);

	/**
	 * 批量更新领取状态
     * @param integer
     * @param date
     * @param receives
     */
    int updateStatusBatch(@Param("receiveDate") Date receiveDate, @Param("status") Integer status, @Param("receives") List<TVerExcitationReceive> receives);

    /**
     * 
     * 方法描述：统计订单奖励记录总条数 <br/>
     * 创建人： ChenBo <br/>
     * 创建时间：2017年8月9日下午8:13:50 <br/>
     * @param orderNo
     * @return
     */
    int countExcitationRecord(String orderNo);
}