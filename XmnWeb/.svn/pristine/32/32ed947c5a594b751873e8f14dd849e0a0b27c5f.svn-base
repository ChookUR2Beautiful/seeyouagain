package com.xmniao.xmn.core.business_cooperation.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_cooperation.entity.MJointLandmark;
import com.xmniao.xmn.core.business_cooperation.entity.TJointLandmark;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称： XmnWeb
 * 类名称： JointLandmarkDao.java
 * 类描述：合作商经纬度信息dao层
 * 创建人： lifeng
 * 创建时间： 2016年6月27日下午2:40:38
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public interface JointLandmarkDao extends BaseDao<TJointLandmark>{
	
	//根据合作商id查询坐标
	@DataSource("slave")
	public List<TJointLandmark> getJointLandmarkByid(Long joinid);
	
	/**
	 * 根据合作商编号查询坐标
	 * @param jointid
	 * @return
	 */
	@DataSource("slave")
	MJointLandmark getMJointLandmarkByid(Long jointid);
}
