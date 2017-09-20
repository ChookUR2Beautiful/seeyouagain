package com.xmniao.xmn.core.jobmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.jobmanage.entity.RecruitTag;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称： XmnWeb
 * 类名称： RecruitTagDao.java
 * 类描述：招聘标签dao
 * 创建人：lifeng
 * 创建时间： 2016年5月30日下午4:36:25
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Repository
public interface RecruitTagDao extends BaseDao<RecruitTag> {

	/**
	 * @Description: 查询招聘标签 
	 * @Param:RecruitTag
	 * @return:List<RecruitTag>
	 * @author:lifeng
	 * @time:2016年5月30日下午8:00:42
	 */
	@DataSource("burs")
	public List<RecruitTag> getRecruitTagList(RecruitTag recruitTag);
	
	/**
	 * @Description: 根据id查询招聘标签
	 * @Param:recruitTag
	 * @return:RecruitTag
	 * @author:lifeng
	 * @time:2016年5月30日下午8:52:54
	 */
	@DataSource("burs")
	public RecruitTag getRecruitTagByParam(RecruitTag recruitTag);
	
	/**
	 * @Description:查询总记录数
	 * @Param:recruitTag
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年5月30日下午8:54:30
	 */
	@DataSource("burs")
	public Long getCountByParam(RecruitTag recruitTag);

}
