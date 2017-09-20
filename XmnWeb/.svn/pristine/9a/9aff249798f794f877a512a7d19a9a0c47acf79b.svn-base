package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TPostComment;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TPostCommentDao
 * 
 * @类描述：评论列表
 * 
 * @创建人：zhou'dekun
 * 
 * @创建时间：2014年12月10日14时07分10秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface TPostCommentDao extends BaseDao<TPostComment> {
	@DataSource("slave")
	public List<TPostComment> getStatusOneList(TPostComment tPostComment);
	@DataSource("slave")
	public Long getStatusOneListcount(TPostComment tPostComment);

}
