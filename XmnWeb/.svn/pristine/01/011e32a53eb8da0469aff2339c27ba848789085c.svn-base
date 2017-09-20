package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CommentDao;
import com.xmniao.xmn.core.businessman.entity.TComment;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommentService
 * 
 * 类描述： 商家评论
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日16时00分41秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class CommentService extends BaseService<TComment> {

	@Autowired
	private CommentDao commentDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return commentDao;
	}
	
	public Integer deleteByLogic(Object[] objects) {
		return commentDao.deleteByLogic(objects);
	}
}
