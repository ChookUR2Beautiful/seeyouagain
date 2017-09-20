package com.xmniao.xmn.core.reward_dividends.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationReceive;

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
	
	List<TVerExcitationReceive> getList(TVerExcitationReceive record);

    int add(TVerExcitationReceive record);

    int update(TVerExcitationReceive record);
    
    int addBatch(List<TVerExcitationReceive> receiveList);

	List<TVerExcitationReceive> selectUnclaimedByType(@Param("type") Integer type, @Param("uid") Integer uid);
}