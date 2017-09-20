package com.xmniao.xmn.core.user_terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.user_terminal.entity.TPost;
import com.xmniao.xmn.core.user_terminal.entity.TPostReport;
import com.xmniao.xmn.core.user_terminal.service.TPostReportService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostReportController
 * 
 * 类描述：帖子举报表
 * 
 * 创建人： zhou'dekun
 * 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value="user_terminal/tPostReport")
public class TPostReportController extends BaseController {
	
	@Autowired
	private TPostReportService PostReportService;
	

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "user_terminal/postList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Object list(TPostReport tPostReport) {
		Pageable<TPostReport> pageable = new Pageable<TPostReport>(tPostReport);
		pageable.setContent(PostReportService.getList(tPostReport));
		pageable.setTotal(PostReportService.count(tPostReport));
		return pageable;
	}

}
