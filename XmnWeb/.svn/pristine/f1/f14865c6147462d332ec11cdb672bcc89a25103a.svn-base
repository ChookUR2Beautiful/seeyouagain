package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TTopicImg;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TTopicImgDao extends BaseDao<TTopicImg> {
	@DataSource("slave")
	public List<TTopicImg> getTopicImgList (Long topicId);
	public Integer deleteTopicImg(Object[] objects);
}
