package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.user_terminal.entity.TPost;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TpostDao
 * 
 * @类描述：香蜜客圈子发帖表
 * 
 * @创建人：zhou'dekun
 * 
 * @创建时间：2014年12月08日20时07分10秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface TpostDao extends BaseDao<TPost> {
	/**
	 * 帖子列表（状态0）
	 * @param tPost
	 * @return
	 */
	@DataSource("slave")
	public List<TPost> getZeroList(TPost tPost);
	/**
	 * 帖子列表count
	 * @param tPost
	 * @return
	 */
	@DataSource("slave")
	public Long getZeroListCount(TPost tPost);
	
	/**
	 * 举报列表（状态2）
	 * @param tPost
	 * @return
	 */
	@DataSource("slave")
	public List<TPost> getTwoList(TPost tPost);
	/**
	 * 举报列表count
	 * @param tPost
	 * @return
	 */
	@DataSource("slave")
	public Long getTwoListCount(TPost tPost);
	
	/**
	 * 回收站（状态1）
	 * @param tPost
	 * @return
	 */
	@DataSource("slave")
	public List<TPost> getOneList(TPost tPost);
	
	/**
	 * 回收站count
	 * @param tPost
	 * @return
	 */
	@DataSource("slave")
	public Long getOneListCount(TPost tPost);
	
	/**
	 * 删除（更改状态）
	 * @param tPost
	 * @return
	 */
	public Integer updatePostStatus(TPost tPost);
	
	
	/**
	 * 根据tid查询帖子评论
	 * @param tid
	 * @return
	 */
	@DataSource("slave")
	public List<TPost> getPostPostComment(Long tid);
	

}
