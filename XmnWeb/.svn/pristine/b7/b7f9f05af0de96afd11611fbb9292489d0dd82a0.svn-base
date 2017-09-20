package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TFloatAdvert;

public interface TFloatAdvertDao  extends BaseDao<TFloatAdvert>{
    int deleteByPrimaryKey(Integer id);

    int insert(TFloatAdvert record);

    int insertSelective(TFloatAdvert record);

    TFloatAdvert selectByPrimaryKey(Integer id);

    
    //*************************自定义内容*******************************
    Long floatAdvertCount(TFloatAdvert record);
    
    public List<TFloatAdvert> getFoatAdvertList(TFloatAdvert record);
    
    int updateStatusOption(TFloatAdvert record);
    
}