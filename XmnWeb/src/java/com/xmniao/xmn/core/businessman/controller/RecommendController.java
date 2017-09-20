/**
 * 
 */
package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.CatehomeActivity;
import com.xmniao.xmn.core.businessman.entity.CatehomeComment;
import com.xmniao.xmn.core.businessman.entity.CatehomeMustBuy;
import com.xmniao.xmn.core.businessman.entity.ExperienceComment;
import com.xmniao.xmn.core.businessman.entity.RecommendSellerPackage;
import com.xmniao.xmn.core.businessman.entity.RecommendSpecial;
import com.xmniao.xmn.core.businessman.entity.SellerTopic;
import com.xmniao.xmn.core.businessman.service.CatehomeActivityService;
import com.xmniao.xmn.core.businessman.service.CatehomeCommentService;
import com.xmniao.xmn.core.businessman.service.CatehomeMustBuyService;
import com.xmniao.xmn.core.businessman.service.RecommendSellerService;
import com.xmniao.xmn.core.businessman.service.RecommendSpecialService;
import com.xmniao.xmn.core.businessman.service.SellerTopicService;
import com.xmniao.xmn.core.fresh.entity.FreshType;
import com.xmniao.xmn.core.fresh.service.FreshTypeService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RecommendController
 * 
 * 类描述： 首页专题推荐
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月22日 上午11:03:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("businessman/recommend")
public class RecommendController extends BaseController{
	
	@Autowired
	private RecommendSpecialService recommendSpecialService;
	
	@Autowired
	private RecommendSellerService recommendSellerService;
	
	@Autowired
	private CatehomeActivityService catehomeActivityService;
	
	@Autowired
	private SellerTopicService sellerTopicService;
	
	@Autowired
	private CatehomeCommentService catehomeCommentService;
	
	@Autowired
	private CatehomeMustBuyService catehomeMustBuyService;
	
	@Autowired
	private FreshTypeService freshTypeService;
	
	@RequestMapping("")
	public Object init(){
		log.info("RecommendController --> ");
		return "businessman/recommend/recommendList";
	}
	
	@RequestMapping("/recommendList")
	@ResponseBody
	public Object recommendList(RecommendSpecial recommendSpecial){
		log.info("RecommendController --> /recommendList");
		List<RecommendSpecial> list = recommendSpecialService.getList(recommendSpecial);
		return new Resultable(true, "操作成功",list);
		
	}
	
	@RequestMapping("/specialChoose")
	@ResponseBody
	public Object specialChoose(RecommendSpecial recommendSpecial){
		log.info("RecommendController --> /specialChoose");
		Pageable<RecommendSpecial> pageable = new Pageable<>();
		pageable.setContent(recommendSpecialService.getSpecialChoose(recommendSpecial));
		return pageable;
	}
	
	
	@RequestMapping("/addSpecial")
	@ResponseBody
	public Object addSpecial(RecommendSpecial recommendSpecial){
		log.info("RecommendController --> /addSpecial");
		if(recommendSpecial.getId()==null){
			return new Resultable(false, "操作失败");
		}
		recommendSpecialService.addSpecial(recommendSpecial);
		return new Resultable(true, "操作成功");
	}
	
	@RequestMapping("/updateSort")
	@ResponseBody
	public Object updateSort(Integer id,Integer homeSort){
		log.info("RecommendController --> /updateSort");
		if(id==null){
			return new Resultable(false, "操作失败");
		}
		recommendSpecialService.updateSort(id,homeSort);
		return new Resultable(true, "操作成功");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object updateSort(@RequestParam(value="id",required=true)Integer id){
		log.info("RecommendController --> /delete id="+id);
		try {
			recommendSpecialService.deleteSpecial(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.info("执行删除首页推荐时出现错误",e);
			return new Resultable(false, "操作失败");
		}
	}
	
	
	@RequestMapping("/packageList")
	@ResponseBody
	public Object packageList(RecommendSellerPackage sellerPackage){
		log.info("RecommendController --> /packageList");
		List<RecommendSellerPackage> list = recommendSellerService.getList(sellerPackage);
		return new Resultable(true, "操作成功",list);
	}
	
	@RequestMapping("/packageChoose")
	@ResponseBody
	public Object packageChoose(RecommendSellerPackage sellerPackage){
		log.info("RecommendController --> /packageChoose  sellerPackage="+sellerPackage);
		Pageable<RecommendSellerPackage> pageable = new Pageable<>();
		pageable.setContent(recommendSellerService.getPackageChoose(sellerPackage));
		return pageable;
	}
	
	@RequestMapping("/addPackage")
	@ResponseBody
	public Object addPackage(@RequestParam(required=true)Integer id,@RequestParam(value="homeSort",defaultValue="0")Integer homeSort){
		log.info("RecommendController --> /addPackage id="+id);
		try {
			recommendSellerService.addPackage(id,homeSort);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.info("添加套餐推荐时出现异常",e);
			return new Resultable(false, "操作成功");
		}
		
	}
	
	@RequestMapping("/updatePackageSort")
	@ResponseBody
	public Object updatePackageSort(Integer id,Integer homeSort){
		log.info("RecommendController --> /updatePackageSort id="+id+" -homeSort:"+homeSort);
		if(id==null){
			return new Resultable(false, "操作失败");
		}
		recommendSellerService.updatePackageSort(id,homeSort);
		return new Resultable(true, "操作成功");
	}
	
	@RequestMapping("/deletePackage")
	@ResponseBody
	public Object deletePackage(@RequestParam(value="id",required=true)Integer id){
		log.info("RecommendController --> /deletePackage  id="+id);
		try {
			recommendSellerService.deletePackage(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.info("执行删除套餐方法时出现异常",e);
			return new Resultable(false, "操作失败");
		}
	}
	
	
	@RequestMapping("/activityList")
	@ResponseBody
	public Object activityList(CatehomeActivity catehomeActivity){
		return Resultable.success("成功", catehomeActivityService.getList(catehomeActivity));
	}
	
	@RequestMapping("/activityEdit")
	public Object activityEdit(){
		return "businessman/cateActivityEdit";
	}
	
	
	@RequestMapping("/addActivity")
	@ResponseBody
	public Object addActivity(CatehomeActivity catehomeActivity){
		try {
			catehomeActivity.setCreateTime(new Date());
			catehomeActivity.setUpdateTime(new Date());
			catehomeActivity.setState(1);
			if(catehomeActivity.getProvinceId()==null||catehomeActivity.getCityId()==null){
				catehomeActivity.setIsAll(1);
				catehomeActivity.setCityId(null);
				catehomeActivity.setProvinceId(null);
			}else{
				catehomeActivity.setIsAll(0);
			}
			catehomeActivityService.add(catehomeActivity);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	
	@RequestMapping("/updateActivitySort")
	@ResponseBody
	public Object updateActivitySort(@RequestParam(value="id",required=true)Long id,@RequestParam(value="homeSort",required=true)Integer sort){
		try {
			catehomeActivityService.updateActivitySort(id,sort);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.success("操作失败");
		}
	}
	
	
	@RequestMapping("/deleteActivity")
	@ResponseBody
	public Object deleteActivity(@RequestParam(required=true)Long id){
		try {
			catehomeActivityService.deleteActivity(id);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.success("操作失败");
		}
	}
	
	
	@RequestMapping("/topicList")
	@ResponseBody
	public Object topicList(SellerTopic sellerTopic){
		return Resultable.success("成功", sellerTopicService.getList(sellerTopic));
	}
	
	
	@RequestMapping("/topicEdit")
	public Object topicEdit(){
		return "businessman/cateTopicEdit";
	}
	
	@RequestMapping("/addTopic")
	@ResponseBody
	public Object addTopic(SellerTopic sellerTopic){
		try {
			sellerTopic.setCreateTime(new Date());
			sellerTopic.setUpdateTime(new Date());
			if(sellerTopic.getProvinceId()==null||sellerTopic.getCityId()==null){
				sellerTopic.setIsAll(1);	
			}else{
				sellerTopic.setIsAll(0);
			}
			sellerTopicService.add(sellerTopic);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping("/updateTopicSort")
	@ResponseBody
	public Object updateTopicSort(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="homeSort",required=true)Integer sort){
		try {
			sellerTopicService.updateTopicSort(id,sort);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	@RequestMapping("/deleteTopic")
	@ResponseBody
	public Object deleteTopic(@RequestParam(value="id",required=true)Integer id){
		try {
			sellerTopicService.deleteTopic(id);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("操作失败");
		}
	}
	
	
	@RequestMapping("/commentList")
	@ResponseBody
	public Object commentList(CatehomeComment catehomeComment){
		return Resultable.success("成功",catehomeCommentService.getList(catehomeComment)); 
	}
	
	@RequestMapping("/commentChoose")
	@ResponseBody
	public Object commentChoose(ExperienceComment experienceComment){
		Pageable<ExperienceComment> pageable = new Pageable<>(experienceComment);
		pageable.setContent(catehomeCommentService.getCommentChoose(experienceComment));
		return pageable;
	}
	
	@RequestMapping("/commentEdit")
	public Object commentEdit(){
		return "businessman/cateCommentEdit";
	}
	
	
	@RequestMapping("/addComment")
	@ResponseBody
	public Object addComment(CatehomeComment catehomeComment){
		try {
			catehomeCommentService.add(catehomeComment);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.success("操作失败");
		}
	}
	
	
	@RequestMapping("/updateCommentSort")
	@ResponseBody
	public Object updateCommentSort(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="homeSort",required=true)Integer sort){
		try {
			catehomeCommentService.updateCommentSort(id,sort);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.success("操作失败");
		}
	}
	
	@RequestMapping("/deleteComment")
	@ResponseBody
	public Object deleteComment(@RequestParam(value="id",required=true)Integer id){
		try {
			catehomeCommentService.deleteComment(id);
			return Resultable.success("操作成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.success("操作失败");
		}
	}
	
	
	@RequestMapping("/mustbuyEdit")
	public Object mustbuyEdit(CatehomeMustBuy catehomeMustBuy){
		ModelAndView modelAndView = new ModelAndView("businessman/cateMustbuyEdit");
		FreshType freshType = new FreshType();
		freshType.setLimit(-1);
		List<FreshType> freshTypes = freshTypeService.getFatherAndChilds(freshType);
		modelAndView.addObject("freshTypes",freshTypes);
		return modelAndView;
	}
	
	@RequestMapping("/addMustbuy")
	@ResponseBody
	public Object addMustbuy(CatehomeMustBuy catehomeMustBuy){
		try {
			Integer type = catehomeMustBuy.getType();
			Long count=catehomeMustBuyService.countByType(type);
			if(type==null||count>0){
				return Resultable.defeat("该分类已存在商品,不能继续添加");
			}
			catehomeMustBuy.setCreateTime(new Date());
			catehomeMustBuy.setUpdateTime(new Date());
			catehomeMustBuyService.add(catehomeMustBuy);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("失败");
		}
	}
	
	@RequestMapping("/mustbuyList")
	@ResponseBody
	public Object getMustbuyList(CatehomeMustBuy catehomeMustBuy){
		return Resultable.success("成功", catehomeMustBuyService.getList(catehomeMustBuy));
	}
	
	@RequestMapping("/updateMustbuySort")
	@ResponseBody
	public Object updateMustbuySort(@RequestParam(value="id",required=true)Integer id,@RequestParam(value="homeSort",required=true)Integer sort){
		try {
			catehomeMustBuyService.updateMustbuySort(id,sort);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("失败");
		}
	}
	
	@RequestMapping("/deleteMustbuy")
	@ResponseBody
	public Object deleteMustbuy(@RequestParam(value="id",required=true)Integer id){
		try {
			catehomeMustBuyService.deleteMustbuy(id);
			return Resultable.success("成功");
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat("失败");
		}
	}
	
}
