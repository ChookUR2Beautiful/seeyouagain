package com.xmniao.xmn.core.live_anchor.controller;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TFloatAdvert;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackage;
import com.xmniao.xmn.core.live_anchor.service.TFloatAdvertService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 悬浮广告
 * @author Administrator
 *
 */
@RequestLogging(name="悬浮广告管理")
@Controller
@RequestMapping(value = "floatAdvert/manage")
public class FloatAdvertController extends BaseController {

	
	@Autowired
	private TFloatAdvertService floatAdvertService;
	
	
	/**
	 * 初始化悬浮广告信息
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/floatAdvert/floatAdvertList";
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TFloatAdvert floatAdvert){
		Pageable<TFloatAdvert> pageable = new Pageable<TFloatAdvert>(floatAdvert);
		
		pageable = floatAdvertService.getFloatAdvertInfoList(floatAdvert);
		this.log.info("SpecialTopicController-->list pageable=" + pageable);
		
		return pageable;
		
	}
	
	
	/**
	 * 
	 * addInit(专题添加初始化)
	 * 
	 * @author：caiyl
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true, tokenName="floatAdvertToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("live_anchor/floatAdvert/editFloatAdvert");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(TFloatAdvert floatAdvert){
		log.info("floatAdvertController-->add-------floatAdvert="+floatAdvert);
		try {
			Integer id = floatAdvert.getId();
			if(id!=null){
				log.info("[执行修改套餐方法]id="+id);
//				SpecialTopicService.updateActivity(specialTopic,jsonArray);
			}else{
				log.info("[执行修改套餐方法]id="+id);
				floatAdvertService.saveActivity(floatAdvert);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	
	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月3日下午2:03:06 <br/>
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
				count=floatAdvertService.delete(ids.split(","));
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
	
	@RequestMapping(value = "update/init")
	public String updateInit(TFloatAdvert floatAdvert, Model model) {
		try {
			// 1.套餐信息
			if (floatAdvert != null) {
				Integer id = floatAdvert.getId();
				model.addAttribute("isType", "update");
				model.addAttribute("floatAdvertInfo", floatAdvertService.getFloatAdvertInfoById(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "live_anchor/floatAdvert/editFloatAdvert";
	}

	@RequestMapping(value = { "update" })
	@ResponseBody
	public Resultable update(TFloatAdvert floatAdvert) {
		Resultable result = new Resultable();
		try {
			floatAdvert.setUpdateTime(new Date());
			floatAdvertService.update(floatAdvert);
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}

	
	@RequestMapping("/beachOnLine/updateStatusOption")
	@ResponseBody
	public Object updateStatusOption(TFloatAdvert floatAdvert, @RequestParam("ids") String ids, @RequestParam(value="state")Integer state){
		log.info("批量修改商家套餐状态statusOption："+ids);
		Resultable resultable=null;
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
//				Object[] objects = StringUtils.paresToArray(floatAdvert.getIds(), ",");
				floatAdvert.setArray(ids.split(","));
				floatAdvert.setStatus(state);
				count=floatAdvertService.updateStatusOption(floatAdvert);
			}

			log.info("更新成功 共计["+ count + "]条记录");
			resultable = new Resultable(true,"更新数据成功");
		} catch (Exception e) {
			log.error("修改失败",e);
			resultable = new Resultable(false,"更新数据成失败");
		}
		return resultable;
	}
	
}
