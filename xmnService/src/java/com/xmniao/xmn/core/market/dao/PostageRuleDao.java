package com.xmniao.xmn.core.market.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.PostageRule;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: PostageRuleDao    
* @Description:运费模板Dao   
* @author: liuzhihao   
* @date: 2016年12月24日 下午12:26:04
 */
@Repository
public interface PostageRuleDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(PostageRule record);

	@DataSource("joint")
    int insertSelective(PostageRule record);

	@DataSource("joint")
    PostageRule selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(PostageRule record);

	@DataSource("joint")
    int updateByPrimaryKey(PostageRule record);
	
	//获取非指定地址的运费
	@DataSource("joint")
	Double getPostPriceByTid(Map<Object,Object> map);
	
	//获取指定地址的运费
	@DataSource("joint")
	Double querySpecifyTemplate(Map<Object,Object> map);
}