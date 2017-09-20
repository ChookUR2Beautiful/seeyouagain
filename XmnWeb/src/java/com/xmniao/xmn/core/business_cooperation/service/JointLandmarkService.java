package com.xmniao.xmn.core.business_cooperation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.JointLandmarkDao;
import com.xmniao.xmn.core.business_cooperation.entity.MJointLandmark;
import com.xmniao.xmn.core.business_cooperation.entity.TJointLandmark;

/**
 * 项目名称：XmnWeb
 * 类名称：JointLandmarkService
 * 类描述： 合作商家经纬度信息
 * 创建人： lifeng
 * 创建时间：2016年06月27日14时38分10秒
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class JointLandmarkService extends BaseService<TJointLandmark> {

	@Autowired
	private JointLandmarkDao jointLandmarkDao;

	@Override
	protected BaseDao<TJointLandmark> getBaseDao() {
		return jointLandmarkDao;
	}

	public TJointLandmark getJointLandmarkByid(Long sellerid){
		List<TJointLandmark> tJointLandmarkList = jointLandmarkDao.getJointLandmarkByid(sellerid);
		return tJointLandmarkList.isEmpty()?null:tJointLandmarkList.get(0);
	}
	
	/**
	 * 根据商家编号查询坐标
	 * @param sellerid
	 * @return
	 */
	public MJointLandmark getMJointLandmarkByid(Long jointid){
		return jointLandmarkDao.getMJointLandmarkByid(jointid);
	}
}
