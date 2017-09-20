package com.xmniao.xmn.core.common.controller;

import java.util.Date;
import java.util.List;

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
import com.xmniao.xmn.core.common.entity.TBusiness;
import com.xmniao.xmn.core.common.service.BusinessService;
import com.xmniao.xmn.core.util.HashUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @author Administrator
 *
 */
@RequestLogging(name="商圈管理")
@Controller
@RequestMapping(value = "common/business")
public class BusinessController extends BaseController {
	
	@Autowired
	private BusinessService businessService;
	
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'dekun
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "common/businessList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'dekun 
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TBusiness business) {
		Pageable<TBusiness> pageable = new Pageable<TBusiness>(business);
		pageable.setContent(businessService.getAreaBusinessList(business));
		pageable.setTotal(businessService.getAreaBusinessListcount(business));
		return pageable;
	}
	
	
	@RequestMapping(value = "BusinessList")
	@ResponseBody
	public Object getBusinessList(@RequestParam("areaId") int areaId){
		this.log.info("LogController-->list="+log);
		List<TBusiness> businessList = businessService.selectBusiness(areaId);
		return businessList;
		
	}
	
	/**
	 * 
	 * add init(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("common/editBusiness");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商圈新增")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TBusiness business) {
		Resultable resultable = null;
		try {
			business.setGeohash(HashUtil.getInstance().getGeoHash(business.getLatitude(), business.getLongitude()));
			business.setSdate(new Date());
			business.setAreaId(business.getTaareaid());
			businessService.add(business);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
			//添加到 日志记录表
			String word = business.getTitle();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			
			String[] s={"商圈",str,"新增"};
			businessService.fireLoginEvent(s);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String word = business.getTitle();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			
			String[] s={"商圈",str,"新增"};
			businessService.fireLoginEvent(s,0);
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("bid") String bid) {
		ModelAndView modelAndView = new ModelAndView("common/editBusiness");
		modelAndView.addObject("isType", "update");
		try {
			TBusiness business = businessService.getObjectBybid(new Long(bid));
			this.log.info(business);
			modelAndView.addObject("business", business);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商圈修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TBusiness business) {
		Resultable resultable = null;
		try {
			business.setGeohash(HashUtil.getInstance().getGeoHash(business.getLatitude(), business.getLongitude()));
			business.setSdate(new Date());
			businessService.update(business);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//添加到日志记录表
			String[] s={"商圈编号",String.valueOf(business.getBid()),"修改","修改"};
			businessService.fireLoginEvent(s,1);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			//添加到日志记录表
			String[] s={"商圈编号",String.valueOf(business.getBid()),"修改","修改"};
			businessService.fireLoginEvent(s,1);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="商圈删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("bid") String bid) {
		Resultable resultable = null;
		try {
			Integer resultNum = businessService.delete(bid.split(","));
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入日志记录表
				String[] s={"商圈编号",bid,"删除","删除"};
				businessService.fireLoginEvent(s,1);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入日志记录表
			String[] s={"商圈编号",bid,"删除","删除"};
			businessService.fireLoginEvent(s,1);
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * 
	 * 全部商圈信息（用于下拉列表）
	 * 
	 * @author：zhou'dekun 
	 */
	@RequestMapping(value = "businessInfo")
	@ResponseBody
	public Object businessInfo(TBusiness business) {
		Pageable<TBusiness> pageable = new Pageable<TBusiness>(business);
		pageable.setContent(businessService.getSelect(business));
		return pageable;
	}
	
		
}
