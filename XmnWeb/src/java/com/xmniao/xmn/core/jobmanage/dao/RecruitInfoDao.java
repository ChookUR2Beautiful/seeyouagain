package com.xmniao.xmn.core.jobmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.jobmanage.entity.RecruitInfo;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称： XmnWeb
 * 类名称： RecruitInfoDao.java
 * 类描述：招聘信息dao
 * 创建人： lifeng
 * 创建时间： 2016年5月30日下午4:35:56
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Repository
public interface RecruitInfoDao extends BaseDao<RecruitInfo> {

	/**
	 * @Description: 查询招聘信息列表
	 * @Param:RecruitInfo
	 * @return:List<RecruitInfo>
	 * @author:lifeng
	 * @time:2016年5月30日下午7:59:11
	 */
	@DataSource("burs")
	public List<RecruitInfo> getRecruitInfoList(RecruitInfo recruitInfo);
	
	/**
	 * @Description: 根据id查询招聘信息
	 * @Param:recruitInfo
	 * @return:RecruitInfo
	 * @author:lifeng
	 * @time:2016年5月30日下午8:51:38
	 */
	@DataSource("burs")
	public RecruitInfo getRecruitInfoById(Integer recruitId);
	
	/**
	 * @Description: 查询总记录条数
	 * @Param:recruitInfo
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年5月30日下午8:54:56
	 */
	@DataSource("burs")
	public Long getCountByParam(RecruitInfo recruitInfo);
}
