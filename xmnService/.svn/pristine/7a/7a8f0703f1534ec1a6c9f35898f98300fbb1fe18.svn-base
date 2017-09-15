package com.xmniao.xmn.core.market.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.PostageFreeRule;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/***
 * 
* @projectName: xmnService 
* @ClassName: PostageFreeRuleDao    
* @Description:包邮Dao   
* @author: liuzhihao   
* @date: 2016年12月23日 上午11:41:38
 */
@Repository
public interface PostageFreeRuleDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(PostageFreeRule record);

	@DataSource("joint")
    int insertSelective(PostageFreeRule record);

	@DataSource("joint")
    PostageFreeRule selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(PostageFreeRule record);

	@DataSource("joint")
    int updateByPrimaryKey(PostageFreeRule record);
	
	@DataSource("joint")
	List<PostageFreeRule> findAllByTid(List<Integer> list);
	
	/**
	 * 查询包邮
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	Map<Object,Object> findOneIsFree(Map<Object,Object> map);
	
	/**
	 * 查询不包邮且超重最高的
	 * @param map
	 * @return
	 */
	Map<Object,Object> findOneIsNotFree(Map<Object,Object> map);
}