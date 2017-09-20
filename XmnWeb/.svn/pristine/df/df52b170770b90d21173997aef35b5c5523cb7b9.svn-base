package com.xmniao.xmn.core.businessman.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.businessman.entity.TFood;
import com.xmniao.xmn.core.businessman.service.FoodService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：FoodController
 * 
 * @类描述： 商家菜品
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年7月6日 下午2:26:38
 * 
 */
@Controller
@RequestMapping(value = "businessman/seller/food")
@RequestLogging(name = "商家菜品管理")
public class FoodController extends BaseController {

	@Autowired
	private FoodService foodService;



	/**
	 * 商家菜品列表初始化页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月6日 下午2:39:19
	 * @return
	 */
	@RequestMapping(value = "init")
	public String foodListInit(@RequestParam(required = true) String sellerid,
			Model model) {
		model.addAttribute("sellerId", sellerid);
		return "/businessman/food/foodInit";
	}

	/**
	 * 商家菜品列表
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月6日 下午2:40:40
	 * @TODO
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object foodList(TFood food) {
		Pageable<TFood> pageable = new Pageable<TFood>(food);
		pageable.setContent(foodService.getList(food));
		pageable.setTotal(foodService.count(food));
		return pageable;
	}

	/**
	 * 添加商家菜品初始化页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月6日 下午3:05:14
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String foodAddInit(Integer sellerId, Integer sellerid,
			HttpServletResponse response, Model model) {
		if (sellerId == null && sellerid != null) {
			sellerId = sellerid;
		}

		model.addAttribute("sellerId", sellerId);
		return "/businessman/food/foodAdd";
	
	}


	/**
	 * 添加商家菜品
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月6日 下午3:06:12
	 * @TODO
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	@RequestLogging(name = "添加商家菜品")
	public Resultable foodAdd(TFood food) {
		Resultable resultable = new Resultable();
		try {
			food.setSdate(new Date());
			food.setSource(3);//数据来源:1商户APP，2商户PC端，3业务系统，4其他
			foodService.addOrUpdateFood(food);
			resultable.setSuccess(true);
			resultable.setMsg("添加商家菜品成功!");
			String[] s = { "商家菜品", food.getFoodName(), "添加" };
			foodService.fireLoginEvent(s, PartnerConstants.FIRELOGIN_NUMA);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e);
			resultable.setSuccess(false);
			resultable.setMsg("添加商家菜品失败!");
			String[] s = { "商家菜品", food.getFoodName(), "添加" };
			foodService.fireLoginEvent(s, PartnerConstants.FIRELOGIN_NUMB);
		}
		return resultable;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	@SuppressWarnings("finally")
	public Object foodDelete(String id,HttpServletRequest request){

		Resultable resultable = null;
		try {
			Integer resultNum = foodService.delete(id.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}

	/**
	 * 修改商家菜品初始化页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月7日 上午11:13:56
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String foodUpdateInit(Integer id, Model model) {
		model.addAttribute("food", foodService.getObject(id.longValue()));
		return "/businessman/food/foodUpdate";
	}

	/**
	 * 修改商家菜品
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年7月7日 上午11:14:35
	 * @param food
	 * @return
	 */
	@RequestMapping(value = "update")
	@RequestLogging(name = "修改商家菜品")
	@ResponseBody
	public Resultable foodUpdate(TFood food) {
		Resultable resultable = new Resultable();
		try {
			food.setPdate(new Date());
			//food.setSource(3);//业务系统修改时不能修改数据来源
			foodService.addOrUpdateFood(food);
			resultable.setSuccess(true);
			resultable.setMsg("修改商家菜品成功!");
			String[] s = { "商家菜品", food.getId().toString(), "修改", "修改" };
			foodService.fireLoginEvent(s, PartnerConstants.FIRELOGIN_NUMA);
		} catch (Exception e) {
			this.log.error(e);
			resultable.setSuccess(false);
			resultable.setMsg("修改商家菜品失败!");
			String[] s = { "商家菜品", food.getId().toString(), "修改", "修改" };
			foodService.fireLoginEvent(s, PartnerConstants.FIRELOGIN_NUMB);
		}
		return resultable;
	}
}
