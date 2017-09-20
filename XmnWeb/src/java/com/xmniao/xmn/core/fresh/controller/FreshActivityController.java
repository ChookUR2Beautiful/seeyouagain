/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.FreshActivityCommon;
import com.xmniao.xmn.core.fresh.entity.JsonToActivityBean;
import com.xmniao.xmn.core.fresh.entity.JsonToGroupBean;
import com.xmniao.xmn.core.fresh.entity.ProductInfo;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.fresh.service.ActivityGroupService;
import com.xmniao.xmn.core.fresh.service.ActivityProductService;
import com.xmniao.xmn.core.fresh.service.FreshActivityService;
import com.xmniao.xmn.core.fresh.service.FreshManageService;
import com.xmniao.xmn.core.util.PropertiesUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshActivityCommonController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年12月27日 上午10:12:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value = "fresh/activity")
public class FreshActivityController extends BaseController {
	private static final String ACTIVITY_URL = PropertiesUtil.readValue("http.activity.url"); //客户端活动接口地址
	
	@Autowired
	private FreshActivityService freshActivityService;
	
	@Autowired
	private ActivityProductService  activityProductService;
	
	@Autowired
	private ActivityGroupService activityGroupService;
	
	/**
	 * 注入积分超市-产品管理服务
	 */
	@Autowired
	private FreshManageService freshManagermentService;
	
	@RequestMapping(value="list/init")
	@ResponseBody
	private Object listInit(){
		log.info("FreshActivityController-->list/init");
		ModelAndView modelAndView = new ModelAndView("fresh/activityList");
		modelAndView.addObject("activityUrl",ACTIVITY_URL);
		return modelAndView;
	}
	
	@RequestMapping(value="list")
	@ResponseBody
	private Object list(FreshActivityCommon freshActivityCommon){
		log.info("FreshActivityController-->list");
		Pageable<FreshActivityCommon> pageable=new Pageable<FreshActivityCommon>(freshActivityCommon);
		List<FreshActivityCommon> f=freshActivityService.getListPage(freshActivityCommon);
		pageable.setContent(f);
		pageable.setTotal(freshActivityService.countPageable(freshActivityCommon));
		return  pageable;
	} 
	
	@RequestMapping(value="edit/init")
	@ResponseBody
	private Object editInit(Integer id) throws Exception{
		log.info("FreshActivityController-->edit/init");
		ModelAndView modelAndView = new ModelAndView("fresh/activityEdit");
		if(id!=null){
			log.info("[执行修改活动]id="+id);
			FreshActivityCommon freshActivityCommon = freshActivityService.getBykey(id);
			List<Map<String, Object>> activityProductVo = freshActivityService.getEditActivityVo(id);
			modelAndView.addObject("activity",freshActivityCommon);
			modelAndView.addObject("activityProducts",activityProductVo);
		}
		return modelAndView;
	}
	
	@RequestMapping(value="getProduct")
	@ResponseBody
	private Object getProduct(ProductInfo productInfo){
		log.info("FreshActivityController-->getProduct");
		Pageable<ProductInfo> pageable = new Pageable<ProductInfo>();
		pageable.setContent(freshManagermentService.getProductInfoSelect(productInfo));
		return pageable;
	}
	
	@RequestMapping(value="group/init")
	@ResponseBody
	private Object groupInit(@RequestParam(value="pid",required=true) Integer pid,@RequestParam(value="json",required=false)String json){
		log.info("FreshActivityController-->group/init");
		ModelAndView modelAndView = new ModelAndView("fresh/groupEdit");
			Map<String, Object> freshInfo = freshManagermentService
					.getFreshAllInfo(pid);
		if(StringUtils.isNotBlank(json)){
			//把Map看成一个对象  
		     Map classMap=new HashMap();  
		     classMap.put("saleGroupList",ActivityGroup.class);  
		     //使用暗示，直接将json解析为指定自定义对象，其中List完全解析,Map没有完全解析  
		     JsonToGroupBean<ActivityGroup> diyBean=(JsonToGroupBean) JSONObject.toBean(JSONObject.fromObject(json),JsonToGroupBean.class,classMap);  
		     List list=diyBean.getSaleGroupList();
		     freshInfo.put("saleGroupList", list);
		     freshInfo.put("cash", diyBean.getCash());
		     freshInfo.put("choiceSort", diyBean.getSort());
		     freshInfo.put("integral", diyBean.getIntegral());
		     freshInfo.put("type", diyBean.getType());
		     freshInfo.put("store", diyBean.getStore());
		     freshInfo.put("maxStore", diyBean.getMaxStore());
		}
		modelAndView.addObject("freshInfo", freshInfo);
		return modelAndView;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="add")
	@ResponseBody
	private Object add(FreshActivityCommon activity){
		log.info("FreshActivityController-->add-------activity="+activity);
		try {
			String productJson = activity.getProductJson();
			JSONObject fromObject = JSONObject.fromObject(productJson);
			JSONArray jsonArray = fromObject.getJSONArray("json");
			Integer id = activity.getId();
			if(id!=null){
				log.info("[执行修改活动方法]id="+id);
				FreshActivityCommon f=freshActivityService.hasBeingActivity(activity.getId());
				if(f!=null){
					return new Resultable(false, "修改失败,活动已开始不能修改");
				}
				freshActivityService.updateActivity(activity,jsonArray);
			}else{
				log.info("[执行添加活动方法]");
				FreshActivityCommon freshActivityCommon = new FreshActivityCommon();
				freshActivityCommon.setBeginDate(activity.getBeginDate());;
				freshActivityCommon.setEndDate(activity.getEndDate());
				freshActivityCommon.setImg(activity.getImg());
				freshActivityCommon.setRemark(activity.getRemark());
				freshActivityCommon.setTitle(activity.getTitle());
				freshActivityCommon.setLabelId(activity.getLabelId());
				freshActivityCommon.setCreateTime(new Date());
				freshActivityCommon.setUpdateTime(new Date());
				List<ActivityProduct> activityProducts = new ArrayList<ActivityProduct>();
				for (Object object : jsonArray) {
					ActivityProduct activityProduct = new ActivityProduct();
					Map classMap=new HashMap();  
					classMap.put("saleGroupList",ActivityGroup.class);  
					//使用暗示，直接将json解析为指定自定义对象，其中List完全解析,Map没有完全解析  
					JsonToGroupBean<ActivityGroup> diyBean=(JsonToGroupBean) JSONObject.toBean(JSONObject.fromObject(object.toString()),JsonToGroupBean.class,classMap);  
					activityProduct.setCodeId(new Long(diyBean.getCodeId()));
					activityProduct.setSellIntegral(diyBean.getIntegral());
					activityProduct.setSalePrice(diyBean.getCash());
					activityProduct.setSort(diyBean.getSort());
					activityProduct.setCreateTime(new Date());
					activityProduct.setUpdateTime(new Date());
					List<ActivityGroup> saleGroupList = diyBean.getSaleGroupList();
					Integer store=saleGroupList.size()>0?0:diyBean.getStore();
					List<ActivityGroup> activityGroups=new ArrayList<ActivityGroup>();
					for (ActivityGroup saleGroup : saleGroupList) {
						ActivityGroup activityGroup = new ActivityGroup();
						activityGroup.setPvValue(com.xmniao.xmn.core.util.StringUtils.listToString(saleGroup.getPvValues(), ','));
						activityGroup.setCodeId(saleGroup.getCodeId());
						activityGroup.setPvIds(saleGroup.getPvIds());
						activityGroup.setAmount(saleGroup.getAmount());
						activityGroup.setStock(saleGroup.getStock());
						activityGroup.setSort(saleGroup.getSort());
						activityGroups.add(activityGroup);
						store+=activityGroup.getStock();
					}
					activityProduct.setSellStore(store);
					activityProduct.setBeforeStore(store);
					activityProduct.setActivityGroups(activityGroups);
					activityProducts.add(activityProduct);
				}
				freshActivityCommon.setActivityProducts(activityProducts);
				freshActivityService.saveActivity(freshActivityCommon);
			}
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value="end",method=RequestMethod.POST)
	@ResponseBody
	private Object end(@RequestParam("id")Integer id){
		log.info("FreshActivityController-->end");
		try {
			Resultable resultable=freshActivityService.checkEndCondition(id);
			if(!resultable.getSuccess()){
				return resultable;
			}
			freshActivityService.endActivity(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	private Object delete(@RequestParam("id")Integer id){
		log.info("FreshActivityController-->delete");
		try {
			FreshActivityCommon freshActivityCommon = freshActivityService.getBykey(id);
			if(freshActivityCommon.getProceedStatus()==2){
				return new Resultable(false, "活动进行中,无法删除");
			}
			freshActivityService.deleteActivity(id);
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Resultable(false, "操作失败");
	}
	
	@RequestMapping(value="preview",method=RequestMethod.GET)
	@ResponseBody
	private Object preview(@RequestParam("id")Integer id){
		log.info("FreshActivityController-->preview------id="+id);
		ModelAndView modelAndView = new ModelAndView("fresh/acitivityPreview");
		FreshActivityCommon freshActivityCommon = freshActivityService.getBykey(id);
		List<ActivityProduct> products=freshActivityService.getPreview(id);
		modelAndView.addObject("activity",freshActivityCommon);
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping(value="getActivityChoose",method=RequestMethod.POST)
	@ResponseBody
	private Object getActivityChoose(FreshActivityCommon freshActivityCommon){
		log.info("FreshActivityController-->getActivityChoose------freshActivityCommon="+freshActivityCommon);
		Pageable<FreshActivityCommon> pageable=new Pageable<FreshActivityCommon>(freshActivityCommon);
		List<FreshActivityCommon> activitys=freshActivityService.getActivityChoose(freshActivityCommon);
		pageable.setContent(activitys);
		return pageable;
	}
}
