package com.xmniao.xmn.core.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.common.service.BusinessService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：AreaController
 * 
 * 类描述： 区域3级联动查询
 * 
 * 创建人： yang'xu
 * 
 * 创建时间：2014年11月13日 下午14:56:43
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 * 
 */
@RequestLogging(name="区域管理")
@Controller
@RequestMapping(value = "common/area")
public class AreaController extends BaseController {
	@Autowired
	private AreaService areaService;// 区域service
	
	@Autowired
	private AreaDao areaDao;// 区域areaDao
	
	@Autowired
	private BusinessService businessService;//商圈service
	

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "common/areaList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable.setContent(areaDao.getAreaList(area));
		pageable.setTotal(areaDao.getAreaListCount(area));
		return pageable;
	}
	
		/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "open/init/list")
	@ResponseBody
	public Object listopen(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable.setContent(areaDao.getAreaList(area));
		pageable.setTotal(areaDao.getAreaListCount(area));
		return pageable;
	}
	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "lock/init/list")
	@ResponseBody
	public Object listLockArea(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable.setContent(areaDao.getAreaList(area));
		pageable.setTotal(areaDao.getAreaListCount(area));
		return pageable;
	}

	/**
	 * 开通或者关闭区域
	 * @param tArea
	 * @return
	 */
	@RequestLogging(name = "更新区域状态")
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public Object updateStatus(TArea tArea){
		log.info("更新区域状态: " + tArea);
		boolean flag = true;
		try{
			areaService.updateStatus(tArea);
			log.info("区域状态更新成功");
		}catch(Exception e){
			flag = false;
			log.info("区域状态更新失败: " + e);
			areaService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("区域状态更新异常", e, new Object[]{tArea}).getMessage()), 0);
		}
		return recordLogAndReturn(tArea, flag);
	}
	
	/**
	 * 记录日志
	 * @param tArea
	 * @param flag
	 * @return
	 */
	private Resultable recordLogAndReturn(TArea tArea, boolean flag) {
		if(flag){
			String[] str = new String[]{"区域编号为", tArea.getAreaId().toString(), "更新状态", "更新"};
			areaService.fireLoginEvent(str, 1);
		}
		return new Resultable(flag, flag ? "操作成功" : "操作失败");
	}

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="区域删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("areaId") String areaId) {
		Resultable resultable = null;
		try {
			Integer resultNum = areaService.delete(areaId.split(","));
			if (resultNum > 0) {
				//删除区域对应的商圈
				businessService.deletebid(areaId.split(","));
				String[] b={"区域编号",areaId,"删除区域对应的商圈","删除区域对应的商圈"};
				businessService.fireLoginEvent(b);
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
			}else{
				resultable = new Resultable(false, "操作失败");
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			//写入 日志记录表
			String[] s={"区域编号",areaId,"删除","删除"};
			areaService.fireLoginEvent(s,resultable.getSuccess()?1:0);
		}
		return resultable;
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("common/editArea");
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="区域添加")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TArea area) {
		Resultable resultable = null;
		try {
			area.setSdate(new Date());
			areaService.add(area);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			//写入 日志记录表
			String word = area.getTitle();
			String str = "";
			if (word.length() <= 12){
				str = word;
			}else{
				str = word.substring(0, 12)+"...";
			}
			String[] s={"区域",str,"新增"};
			areaService.fireLoginEvent(s,resultable.getSuccess()?1:0);	
		}
		return resultable;
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("areaId") String areaId) {
		log.info(areaId);
		ModelAndView modelAndView = new ModelAndView("common/editArea");
		modelAndView.addObject("isType", "update");
		try {
			TArea area = areaService.getObjectById(new Long(areaId));
			this.log.info(area);
			modelAndView.addObject("area", area);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="区域修改")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TArea area) {
		Resultable resultable = null;
		try {
			area.setSdate(new Date());
			areaService.update(area);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			//添加到日志记录表
			String[] s={"区域编号",String.valueOf(area.getAreaId()),"修改","修改"};
			areaService.fireLoginEvent(s,resultable.getSuccess()?1:0);
		}
		return resultable;
	}

	@RequestMapping(value = "getAll",method = RequestMethod.POST)
	public void getAll(@RequestParam("src")String src,TArea area, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		String areas = areaService.getAll(request,area,src);
		response.getWriter().print(areas);
		
	}
	
	/**
	 * provinceList() 一级菜单（省、直辖市）查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "provinceList", method = { RequestMethod.GET })
	@ResponseBody
	public Object provinceList() {
		this.log.info("LogController-->list=" + log);
		List<TArea> selectProvincelist = areaService.selectProvince();
		return selectProvincelist;
	}

	/**
	 * 
	 * cityList() 二级（市、自治州）区域查询
	 * 
	 * @param areaId
	 * @return
	 */
	@RequestMapping(value = "cityList", method = { RequestMethod.GET })
	@ResponseBody
	public Object cityList(@RequestParam("areaId") int areaId) {
		this.log.info("LogController-->list=" + log);
		List<TArea> cityServiceList = areaService.selectCity(areaId);
		return cityServiceList;
	}

	/**
	 * 
	 * selectAreaList() 查询3级（区、县）
	 * 
	 * @param areaId
	 * @return
	 */
	@RequestMapping(value = "selectAreaList", method = { RequestMethod.GET })
	@ResponseBody
	public Object selectAreaList(@RequestParam("areaId") int areaId) {
		this.log.info("LogController-->list=" + log);
		List<TArea> areaServiceList = areaService.selectArea(areaId);
		return areaServiceList;
	}

	/**
	 * 初始化修改页面时，查询区域信息 通过区县Id，查询
	 * 
	 * @param areaId
	 *            区县ID
	 * @return
	 */
	@RequestMapping(value = "findAreaInfo")
	@ResponseBody
	public Object findArea(@RequestParam("areaId") int areaId) {
		this.log.info("LogController-->findAreaTitle=" + log);
		// 区域全部信息list，将省、市、区县 3个area对象放入areaInfoList中
		List<TArea> areaInfoList = new ArrayList<TArea>();
		List<TArea> areaBrotherlist = new ArrayList<TArea>();// 获取Area对象的brother同级对象，放入areaBrotherlist
		List<TArea> cityBrotherlist = new ArrayList<TArea>();// 获取city对象的brother同级对象，放入cityBrotherlist
		List<TArea> provinceBrotherlist = new ArrayList<TArea>();// 找到province对象的同级对象，放入provinceBrotherlist
		Map<String, List<TArea>> resultMap = new HashMap<>();
		TArea area = areaService.findAreaInfo(areaId);// 找到区县对象Area
		
		if(area.getPid() != 0 ){
			TArea city = areaService.findAreaInfo(area.getPid());// 找到区县对象上级，即市级 id
			TArea province = areaService.findAreaInfo(city.getPid());// 找到省Area对象
			areaInfoList.add(area);// 添加区县对象
			
			areaBrotherlist = areaService.findBrother(areaId);
			
			areaInfoList.add(city);// 添加市area对象
			cityBrotherlist = areaService.findBrother(city.getAreaId());
		
			areaInfoList.add(province);// 添加省对象
			provinceBrotherlist = areaService.findBrother(province.getAreaId());
			
			resultMap.put("areainfo", areaInfoList);
			resultMap.put("areaBrother", areaBrotherlist);
			resultMap.put("cityBrother", cityBrotherlist);
			resultMap.put("provinceBrother", provinceBrotherlist);
		}else{
			return resultMap;
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "init/areaInBusiness/init")
	public ModelAndView initAreaInBusiness(@RequestParam("areaId")String areaId) {
		ModelAndView modelAndView = new ModelAndView("common/areaInbusinessList");
		modelAndView.addObject("requestInit", "common/area/init/areaInBusiness");
		modelAndView.addObject("areaId", areaId);
		
		return modelAndView;
		
	}
	@RequestMapping(value = "init/areaInBusiness")
	public ModelAndView areaInBusiness(TArea area) {
		ModelAndView modelAndView = new ModelAndView("common/areaInbusinessTable");
		modelAndView.addObject("page", area.getPage());
		try {
			Long id = new Long(area.getAreaId());
			modelAndView.addObject("area", areaService.getAreaInBusinessList(area));
			modelAndView.addObject("total", areaService.getAreaInBusinessListCount(id));
		} catch (Exception e) {
			this.log.error("获取区域商圈列表异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/check")
	@ResponseBody
	public Long initCheck( @RequestParam("area") String area) {
		return areaDao.checkArea(area);
	}
	
	
	
	/**
	 * 显示商圈详情
	 * @return
	 */
	@RequestMapping(value="/showBussinessInArea")
	public ModelAndView showBussinessInArea(@RequestParam("areaId")String areaId){
		ModelAndView modelAndView = new ModelAndView("common/bussinessInAreaList");
		modelAndView.addObject("areaId", areaId);
		return modelAndView;
	}
	
	/**
	 * 
	 * list(区域管理中已开通商圈列表数据初始化)
	 * 
	 * @author wangzhimin
	 */
	@RequestMapping(value = "bussiness/open/list")
	@ResponseBody
	public Object openBussinessInAreaList(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable = areaService.getBussinessInAreaListPageable(area);
		return pageable;
	}
	
	/**
	 * 
	 * list(区域管理中未开通商圈列表数据初始化)
	 * 
	 * @author wangzhimin
	 */
	@RequestMapping(value = "bussiness/lock/list")
	@ResponseBody
	public Object loclBussinessInArea(TArea area) {
		log.info(area);
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable = areaService.getBussinessInAreaListPageable(area);
		return pageable;
	}
	
	/**
	 * 区域管理中开通或者关闭商圈
	 * @param tArea
	 * @return
	 */
	@RequestLogging(name = "更新商圈状态")
	@RequestMapping(value = "/bussiness/updateBussinessStatus")
	@ResponseBody
	public Object updateBussinessStatus(TArea tArea){
		log.info("更新商圈状态: " + tArea);
		boolean flag = true;
		try{
			businessService.updateBussinessStatus(tArea);
			log.info("商圈状态更新成功");
		}catch(Exception e){
			flag = false;
			log.info("商圈状态更新失败: " + e);
			businessService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("商圈状态更新异常", e, new Object[]{tArea}).getMessage()), 0);
		}
		return recordBussinessLogAndReturn(tArea, flag);
	}
	
	/**
	 * 记录日志
	 * @param tArea
	 * @param flag
	 * @return
	 */
	private Resultable recordBussinessLogAndReturn(TArea tArea, boolean flag) {
		if(flag){
			String[] str = new String[]{"商圈编号为", tArea.getBid().toString(), "更新状态", "更新"};
			areaService.fireLoginEvent(str, 1);
		}
		return new Resultable(flag, flag ? "操作成功" : "操作失败");
	}
}
