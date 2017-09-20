/**
 * 
 */
package com.xmniao.xmn.core.dataDictionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.dataDictionary.entity.TSystemAnnouncement;
import com.xmniao.xmn.core.dataDictionary.service.TSystemAnnouncementService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSystemAnnouncementController
 * 
 * 类描述： 系统公告Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-17 下午4:49:50 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("announcement/manage")
public class TSystemAnnouncementController extends BaseController {
	
	/**
	 * 注入系统公告Service
	 */
	@Autowired
	private TSystemAnnouncementService announmentService;
	
	/**
	 * 跳转到公告列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "dataDictionary/announcementManage";
	}
	
	/**
	 * 
	 * 方法描述：加载公告列表数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param announcement
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object anchorImageList(TSystemAnnouncement announcement, Model model) {
		Pageable<TSystemAnnouncement> pageabel = new Pageable<TSystemAnnouncement>(announcement);
		try {
			List<TSystemAnnouncement> list = announmentService.getList(announcement);
			Long count = announmentService.count(announcement);
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
	 * 方法描述：跳转到添加公告页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:26:08 <br/>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(Model model){
		model.addAttribute("isType", "add");
		return "dataDictionary/announcementEdit";
		
	}
	
	/**
	 * 
	 * 方法描述：保存公告信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:29:51 <br/>
	 * @param announcement
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TSystemAnnouncement announcement){
		Resultable result=new Resultable();
		try {
			announmentService.add(announcement);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加公告失败:"+e.getMessage(), e);
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到编辑公告页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19上午10:38:59 <br/>
	 * @param announcement
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TSystemAnnouncement announcement){
		ModelAndView modelAndView = new ModelAndView();
		Integer id = announcement.getId();
		TSystemAnnouncement announcementInfo = announmentService.getObject(new Long(id));
		modelAndView.addObject("announcement", announcementInfo);
		modelAndView.setViewName("dataDictionary/announcementEdit");
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：修改公告 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-19上午11:27:30 <br/>
	 * @param announcement
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TSystemAnnouncement announcement){
		Resultable result=new Resultable();
		try {
			announmentService.update(announcement);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改公告失败:"+e.getMessage(), e);
		}
		return result;
	}

}
