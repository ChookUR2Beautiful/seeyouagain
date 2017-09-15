package com.xmniao.xmn.core.market.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.util.dataSource.DataSource;

import java.util.List;

/**
 * 
* @projectName: xmnService 
* @ClassName: FreshActivityCommonDao    
* @Description:通过活动dao   
* @author: liuzhihao   
* @date: 2016年12月22日 下午5:39:08
 */
@Repository
public interface FreshActivityCommonDao {
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(FreshActivityCommon record);

	@DataSource("joint")
    int insertSelective(FreshActivityCommon record);

	@DataSource("joint")
	FreshActivityCommon selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(FreshActivityCommon record);

	@DataSource("joint")
    int updateByPrimaryKey(FreshActivityCommon record);

	@DataSource("joint")
    List<FreshActivityCommon> selectHostSaleActivity();

	@DataSource("joint")
    List<FreshActivityCommon> selectBySpikeId(Long spikeId);

	@DataSource("joint")
    FreshActivityCommon selectCurrentSpikeActivityBySpikeId(@Param("spikeId") Long spikeId);
}