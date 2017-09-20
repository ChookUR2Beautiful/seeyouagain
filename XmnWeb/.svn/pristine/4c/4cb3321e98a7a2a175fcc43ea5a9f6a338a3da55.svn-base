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

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.AndroidAvatarForm;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRobot;
import com.xmniao.xmn.core.live_anchor.service.BLiveAnchorImageService;
import com.xmniao.xmn.core.live_anchor.service.TLiveRobotService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveAnchorController
 *
 * 类描述：直播机器人Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午4:07:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="机器人管理")
@Controller
@RequestMapping(value = "liveAndroid/manage")
public class LiveAndroidController extends BaseController {
	
	
	
	/**
	 * 注入直播机器人服务
	 */
	@Autowired
	private TLiveRobotService liveRobotService;
	
	/**
	 * 注入直播相册服务
	 */
	@Autowired
	private BLiveAnchorImageService liveImageService;
	
	/**
	 * 机器人管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/androidManage";
	}
	
	/**
	 * 机器人管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TLiveRobot robot) {
		Pageable<TLiveRobot> pageable = new Pageable<TLiveRobot>(robot);
		List<TLiveRobot> list = liveRobotService.getList(robot);
		Long count = liveRobotService.count(robot);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	
	/**
	 * 机器人添加页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String addInit(BLiver liveAnchor,Model model) {
		return "live_anchor/androidAddInit";
	}
	
	/**
	 * 添加机器人信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Resultable addBatch(BLiver liveAnchor) {
		Resultable result=new Resultable();
		try {
			BLiveAnchorImage imageParam=new BLiveAnchorImage();
			imageParam.setImageType(LiveConstant.IMAGETYPE_ROBOT);//相册类型  1 主播  2 机器人
			Long imageCount = liveImageService.count(imageParam);
			if(imageCount==0){
				result.setSuccess(false);
				result.setMsg("生成机器人失败，请先添加头像");
				return result;
			}
			int count=liveRobotService.saveAndroidInfo(liveAnchor);
			if(count>0){
				result.setMsg("添加成功!");
				result.setSuccess(true);
			}else{
				result.setMsg("添加失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 机器人添加头像页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "addAvatar/init")
	public String addAvatarInit(TLiveRobot liveRobot,Model model) {
		BLiveAnchorImage imageParam = new BLiveAnchorImage();
		imageParam.setImageType(2);//相册类型  1 主播  2 机器人
		Long count = liveImageService.count(imageParam);
		model.addAttribute("avatarCount", count);
//		return "live_anchor/androidAvatarAddInit";
		return "live_anchor/androidAvatarAddBatchInit";
	}

	
	/**
	 * 添加机器人头像
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "addAvatar")
	@ResponseBody
	public Object addAvatar(AndroidAvatarForm androidAvatarForm) {
		Resultable result=new Resultable();
//		result=liveRobotService.addAvatar(androidAvatarForm);
		result=liveRobotService.addBatchAvatar(androidAvatarForm);
		return result;
	}
	/**
	 * 机器人修改页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String updateInit(TLiveRobot liveAndroid,Model model) {
		model.addAttribute("android", liveRobotService.selectById(liveAndroid.getId()));
		return "live_anchor/androidEdit";
	}
	
	
	/**
	 * 
	 * 方法描述：更新机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8上午11:13:04
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable update(TLiveRobot liveRobot){
		Resultable result=new Resultable();
		try {
			liveRobot.setUpdateTime(new Date());
			int count = liveRobotService.update(liveRobot);
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
	 * 
	 * 方法描述：删除机器人信息
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
				count=liveRobotService.delete(ids.split(","));
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
	 * 方法描述：删除所有机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "deleteAll")
	@ResponseBody
	public Resultable deleteAll( ){
		Resultable result=new Resultable();
		try {
			int count = 0;
			count=liveRobotService.deleteAll();
			if(count>0){
				result.setMsg("清除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("清除失败!");
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
	 * 方法描述：删除机器人相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "deleteImages")
	@ResponseBody
	public Resultable deleteImages( ){
		Resultable result=new Resultable();
		try {
			int count = 0;
			count=liveImageService.deleteImages(LiveConstant.IMAGETYPE_ROBOT);
			if(count>0){
				result.setMsg("清除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("清除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	
}
