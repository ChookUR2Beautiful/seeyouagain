package com.xmniao.xmn.core.market.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.pay.FreshActivityProduct;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface FreshActivityProductDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(FreshActivityProduct record);

    @DataSource( "joint" )
    int insertSelective(FreshActivityProduct record);

    @DataSource( "joint" )
    FreshActivityProduct selectByPrimaryKey(Integer id);
    
    @DataSource( "joint" )
    FreshActivityProduct findByActivityIdAndCodeId(@Param("id") Integer id ,@Param("codeid")Long codeid);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(FreshActivityProduct record);

    @DataSource( "joint" )
    int updateByPrimaryKey(FreshActivityProduct record);

    @DataSource( "joint" )
    List<FreshActivityProduct> findAllByActivityIdAndType(Map<Object, Object> map);
    
    int sumActivityProducts(Integer id);
    
    /**
     * 更新活动商品总库存
     * @param codeid
     * @param num
     * @return
     */
    @DataSource("joint")
    int updateFreshActivityProductStore(@Param("codeid")String codeid,@Param("activityid")Integer activityid,@Param("num")Integer num);
}
