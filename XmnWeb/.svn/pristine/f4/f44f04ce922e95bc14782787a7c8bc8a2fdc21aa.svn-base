package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.user_terminal.dao.TpostDao;
import com.xmniao.xmn.core.user_terminal.entity.TPost;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostService
 * 
 * 类描述： 香蜜客圈子发帖表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年12月08日20时07分10秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TPostService extends BaseService<TPost> {

	@Autowired
	private TpostDao tpostDao;
	
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return tpostDao;
	}
	
	/**
	 * 查询帖子列表（状态为0）
	 * @param tPost
	 * @return
	 */
	public List<TPost> getZeroList(TPost tPost){
		List<TPost> getZeroList =tpostDao.getZeroList(tPost);
		return getZeroList;
		
	}
	
	/**
	 * 查询帖子列表count
	 * @param tPost
	 * @return
	 */
	public Long getZeroListCount(TPost tPost){
		Long getZeroListCount =tpostDao.getZeroListCount(tPost);
		return getZeroListCount;		
	}
	
	/**
	 * 查询举报列表（状态2）
	 * @param tPost
	 * @return
	 */
	public List<TPost> getTwoList(TPost tPost){
		List<TPost> getTwoList=tpostDao.getTwoList(tPost);
		return getTwoList;
		
	}
	/**
	 * 查询举报列表count
	 * @param tPost
	 * @return
	 */
	public Long getTwoListCount(TPost tPost){
		Long getTwoListCount=tpostDao.getTwoListCount(tPost);
		return getTwoListCount;
	}
	
	/**
	 * 回收站（状态1）
	 * @param tPost
	 * @return
	 */
	public List<TPost> getOneList(TPost tPost){
		List<TPost> getOneList=tpostDao.getOneList(tPost);
		return getOneList;
	}
	
	/**
	 * 回收站count
	 * @param tPost
	 * @return
	 */
	public Long getOneListCount(TPost tPost){
		Long getOneListCount=tpostDao.getOneListCount(tPost);
		return getOneListCount;		
	}
	
	/**
	 * 删除（更改状态）
	 * @param objects
	 * @param tPost
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updatePostStatus(Object[] objects, TPost tPost){
		TPost tPost1 = new TPost();
		tPost1.setStatus(tPost.getStatus());
		tPost1.setArray(objects);
		return tpostDao.updatePostStatus(tPost1);
	}
	
	/**
	 * 根据tid查询帖子评论
	 * @param tid
	 * @return
	 */
	public List<TPost> getPostPostComment(Long tid){
		List<TPost> getPostPostCommentList=tpostDao.getPostPostComment(tid);
		return getPostPostCommentList;
	}

}
