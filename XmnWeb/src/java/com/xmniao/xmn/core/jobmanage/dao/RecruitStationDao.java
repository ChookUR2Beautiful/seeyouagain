package com.xmniao.xmn.core.jobmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.jobmanage.entity.RecruitStation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称： XmnWeb
 * 类名称： RecruitStationDao.java
 * 类描述：招聘岗位dao
 * 创建人： lifeng
 * 创建时间： 2016年5月30日下午4:34:18
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Repository
public interface RecruitStationDao extends BaseDao<RecruitStation> {

	/**
	 * @Description: 查询招聘岗位列表
	 * @Param:RecruitStation
	 * @return:List<RecruitStation>
	 * @author:lifeng
	 * @time:2016年5月30日下午6:16:45
	 */
	@DataSource("burs")
	public List<RecruitStation> getRecruitStationList(RecruitStation recruitStation);
	
	/**
	 * @Description: 根据id查询招聘岗位
	 * @Param:RecruitStation
	 * @return:id
	 * @author:lifeng
	 * @time:2016年5月30日下午6:16:45
	 */
	@DataSource("burs")
	public RecruitStation getRecruitStationById(Integer id);
	
	/**
	 * @Description: 查询招聘岗位列表总记录数
	 * @Param:RecruitStation
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年5月30日下午6:16:45
	 */
	@DataSource("burs")
	public Long getCountByParam(RecruitStation recruitStation);

	/**
	 * @Description: 根据id修改招聘岗位信息
	 * @Param:
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月1日下午3:10:58
	 */
	@DataSource("burs")
	public Integer updateByRecruitStationId(RecruitStation recruitStation);
	
	/**
	 * @Description: 根据id删除招聘岗位（把这条记录的状态修改为1）
	 * @Param:recruitStationId
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月1日下午3:26:16
	 */
	public Integer deleteById(Integer recruitStationId);
}
