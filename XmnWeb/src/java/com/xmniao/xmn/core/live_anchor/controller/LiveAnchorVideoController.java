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
import com.xmniao.xmn.core.live_anchor.entity.TLiveAnchorVideo;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorVideoService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveAnchorVideoController
 * 
 * 类描述： 直播精彩视频Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-8 下午8:38:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播精彩视频管理")
@Controller
@RequestMapping(value = "liveAnchorVideo/manage")
public class LiveAnchorVideoController extends BaseController {
	
	@Autowired
	private TLiveAnchorVideoService anchorVideoService;
	
	/**
	 * 跳转到直播精彩视频管理列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/anchorVideoManage";
	}
	
	/**
	 * 
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param anchorVideo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object anchorImageList(TLiveAnchorVideo anchorVideo, Model model) {
		Pageable<TLiveAnchorVideo> pageabel = new Pageable<TLiveAnchorVideo>(anchorVideo);
		try {
			List<TLiveAnchorVideo> list = anchorVideoService.getList(anchorVideo);
			Long count = anchorVideoService.count(anchorVideo);
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
	 * 方法描述：跳转到添加精彩视频页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "live_anchor/anchorVideoEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加精彩视频 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TLiveAnchorVideo anchorVideo){
		Resultable result = new Resultable();
		try {
			anchorVideoService.add(anchorVideo);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加精彩视频失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑精彩视频页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param anchorVideo
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TLiveAnchorVideo anchorVideo){
		ModelAndView modelAndView = new ModelAndView();
		Integer id = anchorVideo.getId();
		TLiveAnchorVideo anchorVideoInfo = anchorVideoService.getObject(new Long(id));
		modelAndView.addObject("anchorVideo", anchorVideoInfo);
		modelAndView.setViewName("live_anchor/anchorVideoEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改精彩视频 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param anchorVideo
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TLiveAnchorVideo anchorVideo){
		Resultable result=new Resultable();
		try {
			anchorVideo.setUpdateTime(new Date());
			anchorVideoService.update(anchorVideo);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改精彩视频失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除精彩视频信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
				count=anchorVideoService.delete(ids.split(","));
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
	 * 方法描述：批量更新视频上线状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateBatch")
	@ResponseBody
	public Object updateBatch(TLiveAnchorVideo anchorVideo){
		if(anchorVideo.getRecommended()!=null&&anchorVideo.getRecommended()==1){
			List<TLiveAnchorVideo> list = anchorVideoService.getList(anchorVideo);
			String ids = anchorVideo.getIds();
			String[] arr=ids.split(",");
			if(list!=null&&list.size()+arr.length>3){
				return new Resultable(false,"首页推荐最多可以设置三条");
			}
		}
		Resultable result=new Resultable();
		result=anchorVideoService.updateBatch(anchorVideo);
		return result;
	}

}
