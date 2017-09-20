package com.xmniao.xmn.core.businessman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CommentPicDao;
import com.xmniao.xmn.core.businessman.entity.TCommentPic;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CommentPicService
 * 
 * 类描述： 评论图片
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日16时05分06秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
 @Service
public class CommentPicService extends BaseService<TCommentPic> {

	@Autowired
	private CommentPicDao commentPicDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return commentPicDao;
	}
	
}
