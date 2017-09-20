package com.xmniao.xmn.core.user_terminal.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.marketingmanagement.entity.HotWords;
import com.xmniao.xmn.core.marketingmanagement.service.HotWordsService;
import com.xmniao.xmn.core.user_terminal.service.SearchTagsService;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
/**
 * 搜索标签管理 Controller
 * @author ch
 *
 */
@RequestLogging(name = "搜索标签管理")
@Controller
@RequestMapping("user_terminal/searchTags")
public class SearchTagsController {
	
	protected final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private HotWordsService hotWordsService;
	
	@Autowired SearchTagsService searchTagsService;
	
	
	@RequestMapping("init")
	public ModelAndView init(ModelAndView view){
		view.setViewName("user_terminal/searchTags/searchTagsCityList");
		view.addObject("areaList", searchTagsService.getOpenBusinessCity());
		return view;
	}
	
	/**
	 * 获取城市搜索标签页面
	 * @param view
	 * @param aid 城市编号
	 * @return
	 */
	@RequestMapping("init/getCitySearchTagsView")
	public ModelAndView  getCitySearchTagsView(ModelAndView view,@RequestParam("aid") String aid){
		view.setViewName("user_terminal/searchTags/citySearchTagsView");
		//判断该城市是否存在商圈
		view.addObject("cityInBusiness", StringUtils.hasLength(aid)?searchTagsService.isCityInBusiness(aid):Boolean.FALSE);
		return view;
	}
	
	@RequestMapping("init/getSearchTagsView")
	public ModelAndView  getSearchTagsView(ModelAndView view,@RequestParam("aid") String aid){
		view.setViewName("user_terminal/searchTags/searchTagsView");
		//判断该城市是否存在商圈
		view.addObject("tags", StringUtils.hasLength(aid)?searchTagsService.getSearchTags(aid):null);
		return view;
	}
	
	
	/**
	 * 查询城市热门关键字
	 * @param hotWords
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/init/hotKey/list")
	@ResponseBody
	public Object hotKeyList(HotWords hotWords) {
		hotWords.setHotStatus("1");
		return searchTagsService.getHotSearchTags(hotWords);
			
	}
	
	/**
	 * 查询历史记录
	 * @param hotWords
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/init/historySet/list")
	@ResponseBody
	public Object historySetList(HotWords hotWords) {
		hotWords.setHotStatus("0");
		Pageable<HotWords> page = searchTagsService.getHotSearchTags(hotWords);
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("page", page.getPage());
		map.put("total", page.getTotal());
		map.put("pageSzie", page.getPageSzie());
		map.put("content", page.getContent());
		map.put("isshow", searchTagsService.checkSearchTagOfArea(hotWords)==6);
		return map;
			
	}
	
	@RequestLogging(name="添加搜索标签")
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Object add(HotWords hotWords,HttpServletRequest request) {
		return searchTagsService.add(hotWords, ResultUtil.getCurrentUser(request).getUserId().toString());
			
	}
	
	
	/**
	 * 检查是否在同一城市有相同搜索标签 
	 * @param hotWords
	 * @return
	 */
	@RequestMapping(value = "/add/checkHotWordsOfArea")
	@ResponseBody
	public boolean checkSearchTagOfArea(HotWords hotWords){
		try{
			//城市编号与关键字名称必须存在
			if(searchTagsService.checkhotWordAndAreaId(hotWords)){
				return searchTagsService.checkSearchTagOfArea(hotWords)==0;
			}
			return Boolean.FALSE;
		}catch(Exception e){
			log.error("检查搜索标签是否存在异常", e);
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 检查搜索标签数量
	 * @param hotWords
	 * @return
	 */
	@RequestMapping(value = "/add/checkHotWordsOfAreaCount")
	@ResponseBody
	public boolean checkSearchTagOfAreaCount(HotWords hotWords){
		try{
			//城市编号必须存在
			if(StringUtils.hasLength(hotWords.getAreaId())){
				//城市搜索标签数量不能超过6个
				return (searchTagsService.checkSearchTagOfArea(hotWords)<6);
			}
			return Boolean.FALSE;
		}catch(Exception e){
			log.error("检查搜索标签数量是否存在异常", e);
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 删除搜索标签
	 * @param hotWords
	 * @param request
	 * @return
	 */
	@RequestLogging(name = "删除搜索标签")
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteSearchTag(HotWords hotWords,HttpServletRequest request){
		return searchTagsService.deleteSearchTag(hotWords, ResultUtil.getCurrentUser(request).getUserId().toString());
	}
	
	
	/**
	 * 设置展示搜索标签
	 * @param hotWords
	 * @param request
	 * @return
	 */
	@RequestLogging(name = "设置展示搜索标签")
	@RequestMapping(value = "setDisplaySearchTag")
	@ResponseBody
	public Object showSearchTag(HotWords hotWords,HttpServletRequest request){
		return searchTagsService.showSearchTag(hotWords, ResultUtil.getCurrentUser(request).getUserId().toString());
	}

}
