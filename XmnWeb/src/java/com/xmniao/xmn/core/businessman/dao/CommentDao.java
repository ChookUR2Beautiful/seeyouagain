package com.xmniao.xmn.core.businessman.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TComment;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：CommentDao
 * 
 * @类描述： 商家评论
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日16时00分41秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface CommentDao extends BaseDao<TComment> {

	/**
	 * 逻辑删除（修改记录删除状态）
	 * 
	 * @param objects
	 * @return 影像记录数
	 */
	public Integer deleteByLogic(Object[] objects);
}
