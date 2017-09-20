package com.xmniao.xmn.core.jobmanage.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.jobmanage.entity.RecruitStation;
import com.xmniao.xmn.core.jobmanage.entity.RecruitTag;
import com.xmniao.xmn.core.jobmanage.service.RecruitStationService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称： XmnWeb
 * 类名称： RecruitStationController
 * 类描述：岗位列表Controller
 * 创建人： lifeng
 * 创建时间： 2016年5月30日下午4:29:35
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@RequestLogging(name="招聘频道")
@Controller
@RequestMapping(value = "jobmanage/recruitStation")
public class RecruitStationController extends BaseController{
	
	@Autowired
	private RecruitStationService recruitStationService;
	
	/**
	 * @Description: init(初始化列表页面)
	 * @return:String
	 * @author:lifeng
	 * @time:2016年5月30日下午4:30:01
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "jobmanage/recruitStationList";
	}

	/**
	 * @Description: list(列表数据初始化)
	 * @Param:recruitStation
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年5月30日下午4:33:21
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(RecruitStation recruitStation) {
		this.log.info("StationController-->list recruitStation=" + recruitStation);
		Pageable<RecruitStation> pageable = new Pageable<RecruitStation>(recruitStation);
		List<RecruitStation> recruitStationList = recruitStationService.getRecruitStationList(recruitStation);
		pageable.setContent(recruitStationList);
		pageable.setTotal(recruitStationService.getCountByParam(recruitStation));
		return pageable;
	}
	
	/**
	 * @Description: 导出岗位列表
	 * @param recruitStation
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(RecruitStation recruitStation, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		recruitStation.setOrder(PageConstant.NOT_ORDER);
		recruitStation.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		List<RecruitStation> recruitStationList = recruitStationService.getRecruitStationList(recruitStation);
		params.put("list", recruitStationList);
		doExport(request, response, "jobmanage/recruitStation.xls", params);
	}
	
	/**
	 * @Description: 初始化修改页面
	 * @Param:id
	 * @return:ModelAndView
	 * @author:lifeng
	 * @time:2016年5月31日上午11:06:52
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("recruitStationId") String id) {
		ModelAndView modelAndView = new ModelAndView("jobmanage/editRecruitStation");
		RecruitTag recruitTag1 = new RecruitTag();
		recruitTag1.setTagType(RecruitTag.STATION_NAME);//岗位名称，标签类型为0
		RecruitTag recruitTag2 = new RecruitTag();
		recruitTag2.setTagType(RecruitTag.STATION_REQUIRE);//岗位要求，标签类型为4
		try {
			RecruitStation recruitStation = recruitStationService.getRecruitStationById(Integer.parseInt(id));
			List<RecruitTag> stationTagNameList = recruitStationService.getRecruitTagList(recruitTag1);//岗位名称（标签类型为0）
			List<RecruitTag> stationTagRequireList = recruitStationService.getRecruitTagList(recruitTag2);//岗位要求（标签类型为4）
			this.log.info(recruitStation);
			modelAndView.addObject("recruitStation", recruitStation);
			modelAndView.addObject("stationTagNameList", stationTagNameList);
			modelAndView.addObject("stationTagRequireList",stationTagRequireList);
		} catch (Exception e) {
			this.log.error("修改初始化异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	/**
	 * @Description: 
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年5月31日下午9:11:59
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改招聘岗位")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(RecruitStation  recruitStation,HttpServletRequest request) {
		Resultable resultable = null;
		try {
			recruitStationService.updateByRecruitStationId(recruitStation);
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			//写入 日志记录表
			String[] s={"招聘岗位编号",String.valueOf(recruitStation.getRecruitStationId()),"修改","修改"};
			recruitStationService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"招聘岗位编号",String.valueOf(recruitStation.getRecruitStationId()),"修改","修改"};
			recruitStationService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
	/**
	 * @Description: 
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年5月31日下午9:11:44
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="删除招聘岗位")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("recruitStationId") String id) {
		Resultable resultable = null;
		try {
			Integer resultNum = recruitStationService.deleteById(id);
			if (resultNum > 0) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				//写入 日志记录表
				String[] s={"招聘岗位编号",id,"删除","删除"};
				recruitStationService.fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			//写入 日志记录表
			String[] s={"招聘岗位编号",id,"删除","删除"};
			recruitStationService.fireLoginEvent(s,UserConstants.FIRELOGIN_ERROR);
		} finally {
			return resultable;
		}
	}
	
}
