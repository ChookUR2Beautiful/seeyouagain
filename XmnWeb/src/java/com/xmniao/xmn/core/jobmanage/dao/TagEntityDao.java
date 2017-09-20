package com.xmniao.xmn.core.jobmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.jobmanage.entity.TagEntity;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称： XmnWeb
 * 类名称： TagEntityDao.java
 * 类描述：标签实体dao
 * 创建人： lifeng
 * 创建时间： 2016年5月30日下午9:22:31
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Repository
public interface TagEntityDao extends BaseDao<TagEntity> {

	/**
	 * @Description:条件查询
	 * @Param:tagEntity
	 * @return:TagEntity
	 * @author:lifeng
	 * @time:2016年5月31日上午8:41:57
	 */
	@DataSource("burs")
	public TagEntity getTagEntityByParam(TagEntity tagEntity);
	
	@DataSource("burs")
	public List<TagEntity> getListByParam(TagEntity tagEntity);

	/**
	 * @Description: 保存多条标签实体关系数据
	 * @Param:
	 * @return:void
	 * @author:lifeng
	 * @time:2016年6月6日下午8:39:13
	 */
	@DataSource("burs")
	public Integer saveTagEntities(List<TagEntity> tagEntities);
	
	/**
	 * @Description: 根据标签实体id删除标签实体
	 * @Param:
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月8日下午2:54:49
	 */
	@DataSource("burs")
	public Integer deleteTagEntityByPojoId(Integer pojoId);
}
