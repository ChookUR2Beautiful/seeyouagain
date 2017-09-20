package com.xmniao.xmn.core.jobmanage.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.jobmanage.entity.UserCV;
import com.xmniao.xmn.core.jobmanage.service.UserCVService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmermanagerment.dao.BXmerDao;
/**
 *@ClassName:UserCVController
 *@Description:用户简历管理
 *@author hls
 *@date:2016年5月31日下午2:11:34
 */
@RequestLogging(name="用户简历管理")
@Controller
@RequestMapping(value = "usercv/manage")
public class UserCVController extends BaseController {
	
	@Autowired
	private BXmerDao bxmerDao;
	@Autowired
	private UserCVService userCVService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "jobmanage/userCvList";
	}
	/**
	 * @Title:getList
	 * @Description:查询用户简历信息列表
	 * @param bxmer
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getList(UserCV userCV){
		this.log.info("UserCVController-->list bxmer=" + userCV);
		Pageable<UserCV> pageable = new Pageable<UserCV>(userCV);
		pageable.setContent(userCVService.selectUserCVInfoList(userCV));
		pageable.setTotal(userCVService.userCVInfoCount(userCV));
		return pageable;
	}
	/**
	 * @Title:export
	 * @Description:导出用户简历信息列表
	 * @param userCV
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException void
	 * @throw
	 */
	@RequestMapping(value = "export")
	public void export(UserCV userCV, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", userCVService.exportUserCVList(userCV));
		doExport(request, response, "jobmanage/userCVList.xls", params);
	}
	
	/**
	 * @Title:delete
	 * @Description:删除用户简历信息(逻辑删除)
	 * @param banner
	 * @param request
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(UserCV userCV,HttpServletRequest request) {
		Resultable resultable = null;
		try {
			userCVService.deleteUserCVById(userCV);
			this.log.info("删除成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
		}  
		return resultable;
	}
}
