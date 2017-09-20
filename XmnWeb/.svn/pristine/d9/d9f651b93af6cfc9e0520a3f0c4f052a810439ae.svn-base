package com.xmniao.xmn.core.user_terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.TPostPicDao;
import com.xmniao.xmn.core.user_terminal.entity.TPost;
import com.xmniao.xmn.core.user_terminal.entity.TPostPic;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostReportService
 * 
 * 类描述： 帖子图片表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年12月10日20时07分10秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TPostPicService extends BaseService<TPostPic> {

	@Autowired
	private TPostPicDao tPostPicDao;
	
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return tPostPicDao;
	}
	
     public List<TPostPic> getListpic(Long tid){
		List<TPostPic> getListpic=tPostPicDao.getListpic(tid);
		return getListpic;
	}

}
