package com.xmniao.xmn.core.market.dao;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.market.entity.pay.PostageTemplate;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: PostageTemplateDao    
* @Description:快递模板   
* @author: liuzhihao   
* @date: 2016年12月22日 上午10:05:46
 */
@Repository
public interface PostageTemplateDao {
	@DataSource("joint")
    int deleteByPrimaryKey(Integer tid);
	
	@DataSource("joint")
    int insert(PostageTemplate record);
	
	@DataSource("joint")
    int insertSelective(PostageTemplate record);
	
	@DataSource("joint")
    PostageTemplate selectByPrimaryKey(Integer tid);
	
	@DataSource("joint")
    int updateByPrimaryKeySelective(PostageTemplate record);
	
	@DataSource("joint")
    int updateByPrimaryKey(PostageTemplate record);
}