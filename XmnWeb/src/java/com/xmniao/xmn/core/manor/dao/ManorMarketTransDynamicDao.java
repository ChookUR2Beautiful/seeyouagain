package com.xmniao.xmn.core.manor.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.manor.entity.TManorMarketTransDynamic;


public interface ManorMarketTransDynamicDao extends BaseDao<TManorMarketTransDynamic>{
    int deleteByPrimaryKey(Long id);

    Integer insert(TManorMarketTransDynamic record);

    int insertSelective(TManorMarketTransDynamic record);

    TManorMarketTransDynamic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TManorMarketTransDynamic record);

    int updateByPrimaryKey(TManorMarketTransDynamic record);
    
}