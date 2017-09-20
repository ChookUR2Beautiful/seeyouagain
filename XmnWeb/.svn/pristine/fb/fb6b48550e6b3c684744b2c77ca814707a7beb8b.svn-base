package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.live_anchor.service.TLiveReferrerService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveReferrerController
 *
 * 类描述：企业级推荐人Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午4:07:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="企业级推荐人管理")
@Controller
@RequestMapping(value = "liveReferrer/manage")
public class LiveReferrerController extends BaseController {
	
	
	
	/**
	 * 注入主播服务
	 */
	@Autowired
	private TLiveAnchorService liveAnchorService;
	
	/**
	 * 注入直播用户服务
	 */
	@Autowired
	private TLiveReferrerService liveReferrerService;
	
	
	/**
	 * 主播管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/liveReferrerManage";
	}
	
	/**
	 * 主播管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		//推荐人类型 ：001 企业推荐人;默认 002 普通推荐人
		try {
			liveAnchor.setReferrerType(LiveConstant.REFERRER_TYPE.ENTERPRISE);
			liveReferrerService.getListPage(liveAnchor, pageable);
			JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	

	/**
	 * 主播修改页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String updateInit(BLiver liveAnchor,Model model) {
		BLiver anchor = liveAnchorService.selectByPrimaryKey(liveAnchor.getId());
		model.addAttribute("anchor",anchor );
		return "live_anchor/liveMemberEdit";
	}
	
	
	/**
	 * 
	 * 方法描述：更新主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8上午11:13:04
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable update(BLiver liveAnchor){
		Resultable result=new Resultable();
		try {
			liveAnchor.setPassword(null);//修改主播信息时，密码不可修改
			liveAnchor.setUpdateTime(new Date());
			int count = liveAnchorService.updateByPrimaryKeySelective(liveAnchor);
			if(count>0){
				result.setMsg("更新成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("更新失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	

	
	/**
	 * 获取主播信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "getAnchorById")
	@ResponseBody
	public Object getAnchorById(BLiver liveAnchor) {
		BLiver anchor = liveAnchorService.selectByPrimaryKey(liveAnchor.getId());
		return anchor;
	}
	
	
	
	/**
	 * 
	 * 方法描述：导出主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午2:57:30
	 */
	@RequestMapping(value="export")
	public void export(BLiver anchor,HttpServletRequest request,HttpServletResponse response){
//		anchor.setOrder(PageConstant.NOT_ORDER);
//		anchor.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params=new HashMap<String,Object>();
		anchor.setReferrerType(LiveConstant.REFERRER_TYPE.ENTERPRISE);
		params.put("list", liveReferrerService.getReferrerList(anchor));
		doExport(request,response,"live_anchor/liveReferrer.xls",params);
		
	}
	
}
