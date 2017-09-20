package com.xmniao.xmn.core.businessman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopic;
import com.xmniao.xmn.core.businessman.entity.TSpecialTopicRelation;
import com.xmniao.xmn.core.businessman.service.SpecialTopicService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 连锁店
 * @author Administrator
 *
 */
@RequestLogging(name="连锁店管理")
@Controller
@RequestMapping(value = "businessman/specialTopic")
public class SpecialTopicController extends BaseController {
	
	private static final String SPECIALTOPICVIEW_URL = PropertiesUtil.readValue("http.specialtopicView.url"); //客户端活动接口地址

	
	@Autowired
	private SpecialTopicService specialTopicService;
	
	
	/**
	 * 初始化专题信息
	 */
	@RequestMapping(value = "init")
	public Object init() {
		log.info("SpecialTopicController--> init");
		ModelAndView modelAndView = new ModelAndView("businessman/specialTopic/specialTopicList");
		modelAndView.addObject("specialTopicLink", SPECIALTOPICVIEW_URL);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSpecialTopic specialTopic){
		Pageable<TSpecialTopic> pageable = new Pageable<TSpecialTopic>(specialTopic);
		pageable = specialTopicService.getSpecialTopicInfoList(specialTopic);
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
	@RequestToken(createToken=true, tokenName="specialTopic")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("businessman/specialTopic/editSpecialTopic");
		modelAndView.addObject("isType", "add");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(TSpecialTopic specialTopic){
		log.info("SpecialTopicController-->add-------specialTopic="+specialTopic);
		try {
			Integer id = specialTopic.getId();
			if(id!=null){
				log.info("[执行修改活动方法]id="+id);
			}else{
				log.info("[执行添加专题方法]");
				specialTopicService.saveActivity(specialTopic);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}

	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(ModelAndView model, @RequestParam("id")Integer topicid){
		try {
			specialTopicService.initSpecialTopicInfo(topicid, model);
			model.addObject("isType", "update");
		}catch(Exception e){
			this.log.error("修改初始异常", e);
		}
		model.setViewName("businessman/specialTopic/editSpecialTopic");
		
		return model;
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(TSpecialTopic specialTopic) {
		log.info("SpecialTopicController-->update-------specialTopic=" + specialTopic);
		try {
			specialTopicService.saveUpdatActivity(specialTopic);
			
		} catch (Exception e) {
			this.log.error("修改数据操作异常", e);
			return new Resultable(false, "操作失败");
		}
		
		return new Resultable(true, "操作成功");

	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id){
		try {
			specialTopicService.deleteActivity(id);
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e);
			return new Resultable(false, "操作失败");
		}
	}
	
	@RequestMapping(value = "viewRelation")
	public ModelAndView viewRelation(ModelAndView model, @RequestParam("id")Integer topicid, @RequestParam("topicType")Integer topicType){
		model.addObject("isType", "view");
		model.addObject("fid", topicid);
		model.addObject("topicType", topicType);
		model.setViewName("businessman/specialTopic/specialTopicRelation");
		
		return model;
	}
	
	@RequestMapping(value = "viewRelation/init")
	@ResponseBody
	public Object viewRelationInit(TSpecialTopicRelation specialTopicRelation){
		Pageable<TSpecialTopicRelation> pageable = new Pageable<TSpecialTopicRelation>(specialTopicRelation);
		try {
			specialTopicService.initSpecialTopicRelationInfo(specialTopicRelation, pageable);
		}catch(Exception e){
			this.log.error("修改初始异常", e);
		}
		return pageable;
	}
}
