package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.TTopicCommentDao;
import com.xmniao.xmn.core.user_terminal.entity.TTopicComment;

@Service
public class TTopicCommentService extends BaseService<TTopicComment> {
	
	@Autowired
	private TTopicCommentDao commentDao;
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return commentDao;
	}
	
	public List<TTopicComment> getTopicCommentList(Long topicId){
		return commentDao.getTopicCommentList(topicId);
	};
	
	
}
