/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardSend;
import com.xmniao.xmn.core.vstar.service.TVstarRewardSendService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：vstarRewardController
 * 
 * 类描述： 新时尚大赛推荐奖励Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-1 下午6:25:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value = "vstarRewardSend/manage")
public class VstarRewardSendController extends BaseController {
	
	
	/**
	 * 注入奖励记录服务
	 */
	@Autowired
	private TVstarRewardSendService rewardRecordSendService;
	
	
	
	/**
	 * 
	 * 方法描述：跳转到管理页面 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "vstar/vstarRewardSendManage";
	}
	
	/**
	 * 
	 * 方法描述：加载奖励列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TVstarRewardSend rewardSend) {
		Pageable<TVstarRewardSend> pageable=new Pageable<TVstarRewardSend>(rewardSend);
		Object json =null;
		try {
			List<TVstarRewardSend> list = rewardRecordSendService.getList(rewardSend);
			long count = rewardRecordSendService.count(rewardSend);
			pageable.setContent(list);
			pageable.setTotal(count);
			json = JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
