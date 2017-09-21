package com.xmniao.dao.seller;

import com.xmniao.domain.seller.JointBean;
import com.xmniao.domain.seller.JointSaasLedgerBean;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：JointDao
 * 
 * 类描述：经销商DAO层
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月13日 下午3:02:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface JointDao {
	
	/*
	 * 获取经销商信息
	 */
	JointBean getJointInfo(Integer jointid);
	
	/*
	 * 更新经销商SAAS信息
	 */
	int modifyJonitSaasInfo(JointBean jointBean);
	
	/*
	 * 添加经销商分账记录
	 */
	int addJointSaasLedger(JointSaasLedgerBean jointSaasLedger);
}
