package com.xmniao.xmn.core.user_terminal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.dao.BannerDao;
import com.xmniao.xmn.core.user_terminal.entity.TBanner;
import com.xmniao.xmn.core.user_terminal.service.BannerService;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.DateHelper;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.JsonUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
/**
 *@ClassName:BannerController
 *@Description:TODO
 *@author hls
 *@date:2016年5月11日上午10:09:58
 */
@RequestLogging(name="导航图管理")
@Controller
@RequestMapping(value = "/user_terminal/banner")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerDao bannerDao;
	@Autowired
	private BannerService bannerService;
	
	/*   
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "user_terminal/banner/bannerlist";
	}
	
	/**
	 * @Title:selectBannerInfoList
	 * @Description:查询导航图列表
	 * @param banner
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getList(TBanner banner){
		this.log.info("BannerController-->list banner=" + banner);
		Pageable<TBanner> pageable = new Pageable<TBanner>(banner);
		pageable.setContent(bannerService.selectBannerInfoList(banner));
		pageable.setTotal(bannerService.tbillBannerInfoCount(banner));
		return pageable;
	}

    /**
     * @Title:view
     * @Description:查看页面初始化
     * @param request
     * @param id
     * @return ModelAndView
     * @throw
     */
	@SuppressWarnings("finally")
	@RequestMapping(value = "check")
	public ModelAndView view(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/banner/checkbanner");
		try {
			TBanner banner = bannerService.getBanner(Integer.parseInt(id));
			modelAndView.addObject("banner", banner);
		} catch (NumberFormatException e) {
			this.log.error("查看页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}
	/**
	 * @Title:updateInit
	 * @Description:导航图修改初始化
	 * @param request
	 * @param id
	 * @return ModelAndView
	 * @throw
	 */
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/banner/addAndUpdateBanner");
		try {
			TBanner banner = bannerService.getBanner(Integer.parseInt(id));
			modelAndView.addObject("isType", "update");
			modelAndView.addObject("banner", banner);
		} catch (NumberFormatException e) {
			this.log.error("修改初始化异常", e);
		} 
		return modelAndView;
	}
	/**
	 * @Title:addInit
	 * @Description:导航图添加初始化
	 * @return ModelAndView
	 * @throw
	 */
	@RequestMapping(value = "add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("user_terminal/banner/addAndUpdateBanner");
		try {
			modelAndView.addObject("isType", "add");
		} catch (NumberFormatException e) {
			this.log.error("添加导航图初始化异常", e);
		} 
		return modelAndView;
	}
	
	/**
	 * @Title:add
	 * @Description:添加导航图
	 * @param banner
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TBanner banner) {
		Resultable resultable = null;
		try {
			List<Map<String, Object>> obj_json = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapToJson1 = new HashMap<String, Object>();
			mapToJson1.put("pic_url", banner.getPicurl1());
			mapToJson1.put("type", banner.getType1());
			mapToJson1.put("content", banner.getContent1());
			mapToJson1.put("logRequired", banner.getLogRequired1());
			if(banner.getBannerStyle()!=1){//展示风格。0图片横排一格，2轮播图
				mapToJson1.put("sort", 0);
				obj_json.add(mapToJson1);
			}else{//展示风格。1图片横排两格
				mapToJson1.put("sort", banner.getSort1());
				obj_json.add(mapToJson1);
				Map<String, Object> mapToJson2 = new HashMap<String, Object>();
				mapToJson2.put("pic_url", banner.getPicurl2());
				mapToJson2.put("type", banner.getType2());
				mapToJson2.put("content", banner.getContent2());
				mapToJson2.put("logRequired", banner.getLogRequired2());
				mapToJson2.put("sort", banner.getSort2());
				obj_json.add(mapToJson2);
			}
			String objJson = JsonUtil.toJSONString(obj_json);
			banner.setObjJson(Base64.getBase64(objJson));
			//获取当前时间作为创建时间
			String createTimeStr = DateHelper.getDateFormatter();
			Date createTime = DateUtil.smartFormat(createTimeStr);
			banner.setCreateTime(createTime);
			banner.setUpdateTime(createTime);
			bannerService.checkFresh(banner);
			bannerDao.addReturnId(banner);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
		
	}
	
	/**
	 * @Title:update
	 * @Description:修改导航图
	 * @param banner
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TBanner banner) {
		Resultable resultable = null;
		try {
			List<Map<String, Object>> obj_json = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapToJson1 = new HashMap<String, Object>();
			mapToJson1.put("pic_url", banner.getPicurl1());
			mapToJson1.put("type", banner.getType1());
			mapToJson1.put("content", banner.getContent1());
			mapToJson1.put("logRequired", banner.getLogRequired1());
			if(banner.getBannerStyle()!=1){//展示风格。0图片横排一格
				mapToJson1.put("sort", 0);
				obj_json.add(mapToJson1);
			}else{//展示风格。1图片横排两格
				mapToJson1.put("sort", banner.getSort1());
				obj_json.add(mapToJson1);
				Map<String, Object> mapToJson2 = new HashMap<String, Object>();
				mapToJson2.put("pic_url", banner.getPicurl2());
				mapToJson2.put("type", banner.getType2());
				mapToJson2.put("content", banner.getContent2());
				mapToJson2.put("logRequired", banner.getLogRequired2());
				mapToJson2.put("sort", banner.getSort2());
				obj_json.add(mapToJson2);
			}
			String objJson = JsonUtil.toJSONString(obj_json);
			banner.setObjJson(Base64.getBase64(objJson));
			//获取当前时间作为更新时间
			String updateTimeStr = DateHelper.getDateFormatter();
			Date updateTime = DateUtil.smartFormat(updateTimeStr);
			banner.setUpdateTime(updateTime);
			bannerService.updateBanner(banner);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		}  
		return resultable;
	}
	
	/**
	 * @Title:delete
	 * @Description:删除导航图
	 * @param banner
	 * @param request
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(TBanner banner,HttpServletRequest request) {
		Resultable resultable = null;
		try {
			bannerService.deleteBannerById(banner);
			this.log.info("删除成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		}  
		return resultable;
	}
	
}
