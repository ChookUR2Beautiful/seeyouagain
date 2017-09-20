package com.xmniao.xmn.core.business_cooperation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.SubjectReplyDao;
import com.xmniao.xmn.core.business_cooperation.entity.TSubjectReply;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SubjectReplyService
 * 
 * 类描述： 话题回复
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月20日14时58分36秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class SubjectReplyService extends BaseService<TSubjectReply> {

	@Autowired
	private SubjectReplyDao subjectReplyDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return subjectReplyDao;
	}
	
}
