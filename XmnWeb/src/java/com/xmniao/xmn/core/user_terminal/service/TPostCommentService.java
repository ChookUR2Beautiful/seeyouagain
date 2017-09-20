package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.TPostCommentDao;
import com.xmniao.xmn.core.user_terminal.entity.TPostComment;

@Service
public class TPostCommentService extends BaseService<TPostComment> {
	@Autowired
	private TPostCommentDao tPostCommentDao;
	
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return tPostCommentDao;
	}
	
   public List<TPostComment> getStatusOneList(TPostComment tPostComment){
	   List<TPostComment> getStatusOneList=tPostCommentDao.getStatusOneList(tPostComment);
	   return getStatusOneList;
   }
	
	public Long getStatusOneListcount(TPostComment tPostComment){
		Long getStatusOneListcount=tPostCommentDao.getStatusOneListcount(tPostComment);
		return getStatusOneListcount;
	}

}
