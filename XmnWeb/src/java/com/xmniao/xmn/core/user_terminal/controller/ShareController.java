package com.xmniao.xmn.core.user_terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.coupon.service.CouponService;
import com.xmniao.xmn.core.user_terminal.entity.TShare;
import com.xmniao.xmn.core.user_terminal.service.ShareService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：ShareController
 * 
 * @类描述： 分享信息
 * 
 * @创建人：cao'yingde
 * 
 * @创建时间 ：2015年5月29日 下午3:20:44
 * 
 */
@Controller
@RequestLogging(name = "分享信息")
@RequestMapping(value = "user_terminal/share")
public class ShareController extends BaseController {

	@Autowired
	private ShareService shareService;
	@Autowired
	private CouponService couponService;
	
	/**
	 * 优惠券列表初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "user_terminal/shareList";
	}

	/**
	 * 优惠券列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object initList(TShare share) {
		Pageable<TShare> pageable = new Pageable<TShare>(share);
		pageable.setContent(shareService.getList(share));
		pageable.setTotal(shareService.count(share));
		return pageable;
	}

	/**
	 * 添加优惠券初始页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/init")
	public String add(Model model) {
		model.addAttribute("type", "add");
		return "user_terminal/editShare";
	}

	@RequestMapping(value = "/add")
	@RequestLogging(name = "添加优惠券")
	@ResponseBody
	public Object add(TShare share) {
		this.log.info(share);
		Resultable resultable = new Resultable();
		shareService.addOrUpdateShare(share, resultable);
		return resultable;
	}

	/**
	 * 修改优惠券初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update/init")
	public String update(Long sid, Model model) {
		shareService.getUpdateShareInfo(sid, model);
		model.addAttribute("type", "update");
		return "user_terminal/editShare";
	}

	@RequestMapping(value = "/update/getSellers")
	@ResponseBody
	public Object getSellers(Long sid) {
		return shareService.getSellersBySellerid(sid);
	}

	@RequestMapping(value = "/update")
	@RequestLogging(name = "修改优惠券")
	@ResponseBody
	public Object update(TShare share) {
		this.log.info(share);
		Resultable resultable = new Resultable();
		shareService.addOrUpdateShare(share, resultable);
		return resultable;
	}
}
