package com.xmniao.xmn.core.live_anchor.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveLevel;
import com.xmniao.xmn.core.live_anchor.service.TLiveLevelService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 主播等级
 * @author Administrator
 *
 */
@RequestLogging(name="主播等级管理")
@Controller
@RequestMapping(value = "liveLevel/manage")
public class LiveLevelController extends BaseController {
	
	@Autowired
	private TLiveLevelService liveLevelService;
	
	
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/liveLevel/liveLevelList";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TLiveLevel liveLevel){
		Pageable<TLiveLevel> pageable = new Pageable<TLiveLevel>(liveLevel);
		pageable = liveLevelService.getLiveLevelInfoList(liveLevel);
		this.log.info("SpecialTopicController-->list pageable=" + pageable);
		
		return pageable;
	}
	
	

	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true, tokenName="liveLevelToken")
	public ModelAndView addInit(@RequestParam(value = "liveType", required = false) String liveType) {
		//
		ModelAndView modelAndView = new ModelAndView("live_anchor/liveLevel/editLiveLevel");
		modelAndView.addObject("isType", "add");
		modelAndView.addObject("liveType", liveType);
		return modelAndView;
	}
	
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(TLiveLevel liveLevel){
		log.info("liveLevelController-->add-------liveLevel="+liveLevel);
		try {
			Integer id = liveLevel.getId();
			if(id==null){
				log.info("[执行修改套餐方法]id="+id);
				liveLevelService.saveActivity(liveLevel);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("id") String id){
		Resultable result = new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(id)){
				count=liveLevelService.deleteById(Integer.parseInt(id));
			}
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "update/init")
	public String updateInit(TLiveLevel liveLevel, Model model) {
		try {
			if (liveLevel != null) {
				model.addAttribute("isType", "update");
				model.addAttribute("liveLevelInfo", liveLevelService.getLiveLevelData(liveLevel));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "live_anchor/liveLevel/editLiveLevel";
	}

	@RequestMapping(value = { "update" })
	@ResponseBody
	public Resultable update(TLiveLevel liveLevel) {
		Resultable result = new Resultable();
		try {
			int count = 0;
			if (liveLevel != null) {
				count = liveLevelService.saveUpdateActivity(liveLevel);
			}
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				throw new Exception("更新数据出错");
			}
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * 校验连锁店帐号唯一性
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/checkLevelName")
	@ResponseBody
	public boolean checkLevelName(HttpServletRequest request, @RequestParam("levelName") String levelName) {
		long num = liveLevelService.checkLevelName(levelName);
		return  (num == 0);
	}
	
	/**
	 * 
	 * 方法描述：初始化主播等级下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initAnchorLevelId",method=RequestMethod.POST)
	@ResponseBody
	public Object initAnchorLevelId(TLiveLevel liveLevel) {
		Pageable<TLiveLevel> pageable = new Pageable<TLiveLevel>(liveLevel);
		List<TLiveLevel> liveLevelList = liveLevelService.getLiveLevelList(liveLevel);
		pageable.setContent(liveLevelList);
		return pageable;
	}
	
	
}
