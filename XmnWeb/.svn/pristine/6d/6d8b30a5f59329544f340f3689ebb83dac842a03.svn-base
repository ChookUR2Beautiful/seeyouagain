package com.xmniao.xmn.core.manor.dao;

import java.util.List;

import com.xmniao.xmn.core.manor.entity.TManorFlower;



public interface ManorFlowerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TManorFlower record);

    int insertSelective(TManorFlower record);

    TManorFlower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TManorFlower record);

    int updateByPrimaryKey(TManorFlower record);
    
    /**
     * 方法描述：查询花朵总数 <br/>
     * 创建人： Administrator <br/>
     * 创建时间：2017年7月14日下午5:01:01 <br/>
     * @param objects
     * @return
     */
    List<TManorFlower> getCurrentFlowerCount(Object[] objects);

    /**
     * 方法描述：查询累计花朵总数  <br/>
     * 创建人： Administrator <br/>
     * 创建时间：2017年7月14日下午5:01:04 <br/>
     * @param objects
     * @return
     */
    List<TManorFlower> getTotalFlowerCount(Object[] objects);
    
}