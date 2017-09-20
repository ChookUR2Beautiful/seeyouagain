package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CommentReplyDao;
import com.xmniao.xmn.core.businessman.entity.TCommentReply;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommentReplyService
 * 
 * 类描述： 评论回复
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日16时02分54秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class CommentReplyService extends BaseService<TCommentReply> {

	@Autowired
	private CommentReplyDao commentReplyDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return commentReplyDao;
	}
	
}
