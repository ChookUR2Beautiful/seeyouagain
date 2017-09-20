package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TTopicComment;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TTopicCommentDao extends BaseDao<TTopicComment> {
	@DataSource("slave")
	public List<TTopicComment> getTopicCommentList(Long topicId);
	public Integer deleteTopicComent (Object[] objects);
	@DataSource("slave")
	public Long countComment (Integer topicId);
	public Integer deleteReply(Object[] objects);
}
