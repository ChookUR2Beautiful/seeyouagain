package com.xmniao.xmn.core.http.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.service.JointService;
import com.xmniao.xmn.core.http.entity.Member;
import com.xmniao.xmn.core.http.entity.PUserRequestSelect;
import com.xmniao.xmn.core.http.entity.PUserResponseSelect;
import com.xmniao.xmn.core.http.entity.PhpHttpPageable;
import com.xmniao.xmn.core.http.service.PuserService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="会员黑名单管理")
@Controller
@RequestMapping(value = "memberblactlist/memberList")
public class MemberBlackController extends BaseController {
	protected final Logger log = Logger.getLogger(PuserController.class);
	
	@Autowired 
	PuserService puserService;
	@Autowired
	private JointService jointService;
	
/*	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("blackordinarylist", "memberblactlist/memberList/init/ordinarylist");
		mv.addObject("blackxunmikelist", "memberblactlist/memberList/init/xunmikelist");
		mv.setViewName("member/memberList/memberBlackList");
		return mv;
	}*/
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年8月19日 下午6:32:05
	 * 描述：会员黑名单管理列表初始化
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberList/memberBlackList");
		return mv;
	}
	
	
/*	@RequestMapping(value = "init/ordinarylist")
	public ModelAndView Ordinarylist(PUserRequestSelect puser) {
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,true);
		mv.setViewName("member/memberList/memberOrdinaryBlackListTable");
		return mv;
	}*/
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年8月19日 下午6:32:39
	 * 描述：会员黑名单列表List获取
	 * @param puser
	 * @return
	 */
	@RequestMapping(value = "init/ordinarylist")
	@ResponseBody
	public Object Ordinarylist(PUserRequestSelect puser) {
		puser.setPageSize("15");
		return puserService.getPuserInfos(puser);
	}
	
/*	@RequestMapping(value = "init/xunmikelist")
	public ModelAndView list(PUserRequestSelect puser) {
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,true);
		mv.setViewName("member/memberList/memberxunmikeBlackListTable");
		return mv;
	}*/
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年8月19日 下午6:33:39
	 * 描述：寻蜜客黑名单列表List获取
	 * @param puser
	 * @return
	 */
	@RequestMapping(value = "init/xunmikelist")
	@ResponseBody
	public Object list(PUserRequestSelect puser) {
		puser.setPageSize("15");
		return puserService.getPuserInfos(puser);
	}
	
	/**
	 * 修改（调会员接口） 
	 * @param request
	 * @return 	
	 */
	@RequestLogging(name="普通会员黑名单恢复到列表")
	@RequestMapping("ordinary/update")
	@ResponseBody
	public Object ordinaryupdate(Member request){
		String uid=request.getUid();
		boolean statusflag=false;
		try {
			PhpHttpPageable<PUserResponseSelect> p= puserService.updateStatus(request);
			 statusflag=p.getStatus();
			return  new Resultable(p.getStatus(), p.getStatus()?"操作成功":"操作失败");
		}  catch (Exception e) {
			log.error("更新-- "+request+" --会员恢复到列表失败", e);
		}finally{
			String[] s={"会员编号",String.valueOf(uid),"恢复到列表","恢复到列表"};
			puserService.fireLoginEvent(s, statusflag?1:0);
		}
		return  new Resultable(false, "操作失败");
	}
	/**
	 * 修改（调会员接口） 
	 * @param request
	 * @return 	
	 */
	@RequestLogging(name="寻蜜客会员黑名单恢复到列表")
	@RequestMapping("xunmike/update")
	@ResponseBody
	public Object xunmikeupdate(Member request){
		String uid=request.getUid();
		boolean statusflag=false;
		try {
			PhpHttpPageable<PUserResponseSelect> p= puserService.updateStatus(request);
			 statusflag=p.getStatus();
			return  new Resultable(p.getStatus(), p.getStatus()?"操作成功":"操作失败");
		}  catch (Exception e) {
			log.error("更新-- "+request+" --会员恢复到列表失败", e);
		}finally{
			String[] s={"会员编号",String.valueOf(uid),"恢复到列表","恢复到列表"};
			puserService.fireLoginEvent(s, statusflag?1:0);
		}
		return  new Resultable(false, "操作失败");
	}
	
	/**
	 * 查看初始化
	 * @param puser
	 * @return
	 */
	@RequestMapping("ordinary/details")
	public ModelAndView ordinarydetails(PUserRequestSelect puser){
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,false);
		mv.setViewName("member/memberList/memberDetails");
		return mv;
	}
	
	/**
	 * 查看初始化
	 * @param puser
	 * @return
	 */
	@RequestMapping("xunmike/details")
	public ModelAndView details(PUserRequestSelect puser){
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,false);
		mv.setViewName("member/memberList/memberDetails");
		return mv;
	}
}
