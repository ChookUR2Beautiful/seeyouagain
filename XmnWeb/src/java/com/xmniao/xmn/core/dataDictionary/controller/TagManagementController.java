package com.xmniao.xmn.core.dataDictionary.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.dataDictionary.entity.BRecruitTag;
import com.xmniao.xmn.core.dataDictionary.service.BRecruitTagService;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TagManagementController
 * 
 * 类描述：标签管理Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-30下午3:23:30
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestLogging(name = "基础数据管理 >> 标签管理")
public class TagManagementController extends BaseController {

	private String viewFolder = "dataDictionary/recruitTag/";
	private final String initUrl = "dataDictionary/tag/init";
	private final String initListUrl = "dataDictionary/tag/init/list";
	private final String addInitUrl = "dataDictionary/tag/add/init";
	private final String addUrl = "dataDictionary/tag/add";
	private final String updateInitUrl = "dataDictionary/tag/update/init";
	private final String updateUrl = "dataDictionary/tag/update";

	@Autowired
	private BRecruitTagService recruitTagService;

	/**
	 * 银行查询列表初始页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = initUrl)
	public String init() {
		return viewFolder + "recruitTagInit";
	}

	/**
	 * 银行查询列表
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:34:37
	 * @param recruitTag
	 * @return
	 */
	@RequestMapping(value = initListUrl)
	@ResponseBody
	public Object initList(BRecruitTag recruitTag) {
		Pageable<BRecruitTag> pageable = new Pageable<BRecruitTag>(recruitTag);
		recruitTagService.getListPage(recruitTag, pageable);
		return pageable;
	}

	/**
	 * 
	 * 方法名称：添加标签初始化页面
	 * 创建人： huang'tao
	 * 创建时间：2016-5-30下午6:31:53
	 * @return
	 */
	@RequestMapping(value = addInitUrl)
	public String addInit() {
		return viewFolder + "recruitTagEdit";
	}

	/**
	 * 添加标签
	 */
	@RequestMapping(value = addUrl)
	@RequestLogging(name = "添加标签")
	@ResponseBody
	public Resultable add(BRecruitTag recruitTag,HttpServletRequest request) {
		Resultable resultable;
		HttpSession session = request.getSession(true);
		TUser user = (TUser) session.getAttribute("currentUs");
		String operator=user==null?"":user.getUsername();
		recruitTag.setOperator(operator);
		recruitTag.setUpdateDate(new Date());
		recruitTagService.add(recruitTag);
		resultable = new Resultable(true, "添加成功！");
		String[] couponInfo = { "标签名称为", recruitTag.getTagname(), "添加", "添加" };
		recruitTagService.fireLoginEvent(couponInfo,
				PartnerConstants.FIRELOGIN_NUMA);
		return resultable;
	}

	/**
	 * 修改标签初始化页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = updateInitUrl)
	public String updateInit(Integer id, Model model) {
		model.addAttribute("tag", recruitTagService.getRecruitTag(id.longValue()));
		return viewFolder + "recruitTagEdit";
	}

	@RequestMapping(value = updateUrl)
	@RequestLogging(name = "修改标签")
	@ResponseBody
	public Resultable update(BRecruitTag recruitTag,HttpServletRequest request) {
		Resultable resultable = new Resultable();
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("currentUs");
		String operator=user.getUsername();
		recruitTag.setOperator(operator);
//		recruitTag.setTagstatus(new Byte("0"));
		recruitTag.setUpdateDate(new Date());
		String[] couponInfo = { "标签名称为", recruitTag.getTagname(), "修改", "修改" };
		if (recruitTagService.update(recruitTag) == 1) {
			resultable.setSuccess(true);
			resultable.setMsg("修改标签成功");

			recruitTagService.fireLoginEvent(couponInfo,
					PartnerConstants.FIRELOGIN_NUMA);
		} else {
			resultable.setSuccess(false);
			resultable.setMsg("修改标签失败");
			recruitTagService.fireLoginEvent(couponInfo,
					PartnerConstants.FIRELOGIN_NUMB);
		}
		return resultable;
	}

}
