package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.FansCoupon;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: FansCouponDao    
* @Description:粉丝卷dao   
* @author: liuzhihao   
* @date: 2017年2月17日 下午6:31:16
 */
@Repository
public interface FansCouponDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer anchorCid);

	@DataSource("joint")
    int insert(FansCoupon record);

	@DataSource("joint")
    int insertSelective(FansCoupon record);

	@DataSource("joint")
    FansCoupon selectByPrimaryKey(Integer anchorCid);

	@DataSource("joint")
    int updateByPrimaryKeySelective(FansCoupon record);

	@DataSource("joint")
    int updateByPrimaryKey(FansCoupon record);
	
	@DataSource("joint")
	List<Map<Object,Object>> findAll(@Param("coupons")List<Integer> coupons);
}