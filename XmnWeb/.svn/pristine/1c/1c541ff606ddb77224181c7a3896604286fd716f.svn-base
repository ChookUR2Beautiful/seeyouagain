/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveShare;
import com.xmniao.xmn.core.live_anchor.service.TLiveShareService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveAnchorVideoController
 * 
 * 类描述： 直播邀请分享Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-8 下午8:38:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播邀请分享管理")
@Controller
@RequestMapping(value = "liveShare/manage")
public class LiveShareController extends BaseController {
	
	
	/**
	 * 注入直播邀请分享Service
	 */
	@Autowired
	private TLiveShareService liveShareService;
	
	/**
	 * 跳转到直播邀请分享管理列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/liveShareManage";
	}
	
	/**
	 * 
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param liveShare
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object anchorImageList(TLiveShare liveShare, Model model) {
		Pageable<TLiveShare> pageabel = new Pageable<TLiveShare>(liveShare);
		try {
			List<TLiveShare> list = liveShareService.getList(liveShare);
			Long count = liveShareService.count(liveShare);
			pageabel.setContent(list);
			pageabel.setTotal(count);
			JSON.toJSON(pageabel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageabel;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加邀请分享页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(Model model){
		model.addAttribute("isType", "add");
		return "live_anchor/liveShareEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加邀请分享 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TLiveShare liveShare){
		Resultable result = new Resultable();
		try {
			liveShareService.add(liveShare);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加邀请分享失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑邀请分享页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param liveShare
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TLiveShare liveShare){
		ModelAndView modelAndView = new ModelAndView();
		Long id = liveShare.getId();
		TLiveShare liveShareInfo = liveShareService.getObject(id);
		modelAndView.addObject("isType", "update");
		modelAndView.addObject("liveShareInfo", liveShareInfo);
		modelAndView.setViewName("live_anchor/liveShareEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改邀请分享 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param liveShare
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TLiveShare liveShare){
		Resultable result=new Resultable();
		try {
			liveShare.setUpdateTime(new Date());
			liveShareService.update(liveShare);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改邀请分享失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除邀请分享信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
				count=liveShareService.delete(ids.split(","));
			}
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：批量更新邀请分享状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateBatch")
	@ResponseBody
	public Object updateBatch(@RequestParam("ids") String ids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=liveShareService.updateBatch(ids,status);
		return result;
	}

}
