package com.xmniao.xmn.core.user_terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.user_terminal.dao.TPostReportDao;
import com.xmniao.xmn.core.user_terminal.entity.TPostReport;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostReportService
 * 
 * 类描述： 帖子举报表
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年12月08日20时07分10秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TPostReportService extends BaseService<TPostReport> {

	@Autowired
	private TPostReportDao tPostReportDao;
	
	@Override
	protected BaseDao getBaseDao() {
		// TODO Auto-generated method stub
		return tPostReportDao;
	}

}
