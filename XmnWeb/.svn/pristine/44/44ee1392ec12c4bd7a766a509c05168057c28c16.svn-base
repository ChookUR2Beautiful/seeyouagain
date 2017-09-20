package com.xmniao.xmn.core.user_terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.user_terminal.entity.TPostPic;
import com.xmniao.xmn.core.user_terminal.entity.TPostReport;
import com.xmniao.xmn.core.user_terminal.service.TPostPicService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TPostReportController
 * 
 * 类描述：帖子图片表
 * 
 * 创建人： zhou'dekun
 * 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value="user_terminal/tPostPic")
public class TPostPicController extends BaseController {
	
	@Autowired
	private TPostPicService tPostPicService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "user_terminal/editPost";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TPostPic tPostPic) {
		Pageable<TPostPic> pageable = new Pageable<TPostPic>(tPostPic);
		pageable.setContent(tPostPicService.getList(tPostPic));
		pageable.setTotal(tPostPicService.count(tPostPic));
		return pageable;
	}

}
