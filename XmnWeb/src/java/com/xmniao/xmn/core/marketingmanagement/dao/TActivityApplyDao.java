package com.xmniao.xmn.core.marketingmanagement.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityApply;

public interface TActivityApplyDao extends BaseDao<TActivityApply>{
	/*
	 * 获取指定 活动申请记录
	 */
    TActivityApply getActivityApply(Integer id);

    /*
     * 更新活动申请状态
     */
    int updateAcivityApply(TActivityApply tActivityApply);
    
    /*
     * 通过ids更新
     */
    int updateBatchByIds(TActivityApply tActivityApply);
    
    /*
     * 通过ids获取
     */
    List<TActivityApply> getListByIds(TActivityApply tActivityApply);
}