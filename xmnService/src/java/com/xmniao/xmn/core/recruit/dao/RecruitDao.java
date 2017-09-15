package com.xmniao.xmn.core.recruit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.RecruitTagRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：saasService   
* 类名称：RecruitDao   
* 类描述：   招聘dao
* 创建人：xiaoxiong   
* 创建时间：2016年5月17日 下午5:12:26   
* @version    
*
 */
@Repository
public interface RecruitDao {
	//查询标签类型信息
	@DataSource("burs")
	List<Map<Object, Object>> queryRecruitTagList(RecruitTagRequest recruitTagRequest);

	//查询自定义标签
	@DataSource("burs")
	List<Map<Object, Object>> queryCustomTag(Map<Object, Object> paramMap);
	//查询简历ID
	@DataSource("burs")
	Integer queryUsedrCVByuid(Integer uid);
	//用户查看招聘岗位
	@DataSource("burs")
	void addRecruitView(Map<Object, Object> param);
	//删除以前记录
	@DataSource("burs")
	void deleteRecuritView(Map<Object, Object> param);
}
