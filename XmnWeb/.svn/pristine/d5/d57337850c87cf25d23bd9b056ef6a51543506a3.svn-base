package com.xmniao.xmn.core.business_cooperation.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.entity.TVideo;
import com.xmniao.xmn.core.business_cooperation.service.VideoService;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：VideoController
 * 
 * 类描述： 视频讲解
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日10时36分55秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/video")
public class VideoController extends BaseController {

	@Autowired
	private VideoService videoService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/productIntroduce";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TVideo video) {
		Pageable<TVideo> pageable = new Pageable<TVideo>(video);
		pageable.setContent(videoService.getList(video));
		pageable.setTotal(videoService.count(video));
		return pageable;
	}

	/**
	 * 导出列表
	 * @param video
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TVideo video, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		video.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", videoService.getList(video));
		doExport(request, response, "business_cooperation/video.xls", params);
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="产品视频介绍删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("vid") String vid) {
		Resultable resultable = null;
		try {
			Integer resultNum = videoService.delete(vid.split(","));
			if (resultNum > PartnerConstants.RESULTNUM) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"产品介绍编号",vid,"删除","删除"};
				videoService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"产品介绍编号",vid,"删除","删除"};
			videoService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editVideo");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	/**
	 * 
	 * playVideo(添加初始化)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "/playVideo/init")
	public ModelAndView playVideo(HttpServletRequest request, @RequestParam("vurl") String vurl) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/playVideo");
		modelAndView.addObject("vurl", vurl);
		return modelAndView;
	}
	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="产品视频介绍添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TVideo video) {
		Resultable resultable = null;
		try {
			video.setFormat(video.getVideoname().substring(video.getVideoname().lastIndexOf(".")+1));
			videoService.add(video);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String word = video.getVideoname();
			String str = "";
			if (word.length() <= PartnerConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(PartnerConstants.RESULTNUM, PartnerConstants.WORD_LENGTH)+"...";
			}
			
			String[] s={"产品视频介绍",str,"新增"};
			videoService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String word = video.getVideoname();
			String str = "";
			if (word.length() <= PartnerConstants.WORD_LENGTH){
				str = word;
			}else{
				str = word.substring(PartnerConstants.RESULTNUM, PartnerConstants.WORD_LENGTH)+"...";
			}
			String[] s={"产品视频介绍",str,"新增"};
			videoService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("vid") String vid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/editVideo");
		modelAndView.addObject("isType", "update");
		try {
			TVideo video = videoService.getObject(new Long(vid));
			this.log.info(video);
			modelAndView.addObject("video", video);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="产品视频介绍修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TVideo  video) {
		Resultable resultable = null;
		try {
			video.setFormat(video.getVideoname().substring(video.getVideoname().lastIndexOf(".")+1));
			videoService.update(video);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			String[] s={"产品介绍编号",String.valueOf(video.getVid()),"修改","修改"};
			videoService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//添加到日志记录表
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"产品介绍编号",String.valueOf(video.getVid()),"修改","修改"};
			videoService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);//添加到日志记录表
		} finally {
			return resultable;
		}
	}

}