package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLivePurse;
import com.xmniao.xmn.core.live_anchor.service.BLiveMemberService;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import com.xmniao.xmn.core.xmnpay.entity.LiveWallet;
import com.xmniao.xmn.core.xmnpay.service.LiveWalletService;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveMemberController
 *
 * 类描述：直播会员Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午4:07:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="直播会员管理")
@Controller
@RequestMapping(value = "liveMember/manage")
public class LiveMemberController extends BaseController {
	
	
	
	/**
	 * 注入主播服务
	 */
	@Autowired
	private TLiveAnchorService liveAnchorService;
	
	
	/**
	 * 注入直播钱包服务
	 */
	@Autowired
	private LiveWalletService liveWalletService;
	
	/**
	 * 注入直播会员服务
	 */
	@Autowired
	private BLiveMemberService liveMemberService;
	
	
	/**
	 * 主播管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/liveMemberManage";
	}
	
	/**
	 * 主播管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		
		//用户类型 1 主播 2 普通用户
//		liveAnchor.setUtype(LiveConstant.UTYPE_COMMON);
//		liveAnchor.setOrder(PageConstant.NOT_ORDER);
		try {
			liveAnchorService.getMemberListPage(liveAnchor, pageable);
			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	/**
	 * 获取当前查询记录数，限制单次导出
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/getCurrentDataSize")
	@ResponseBody
	public Object getCurrentDataSize(BLiver liveAnchor) {
		long count=0l;
		try {
			count = liveAnchorService.count(liveAnchor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 
	 * 方法描述：跳转到查看下级页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9上午11:04:19 <br/>
	 * @return
	 */
	@RequestMapping(value="viewJuniorInfo")
	public String viewJuniorInfo(@RequestParam("uid") String uid,Model model){
		model.addAttribute("uidViewJunior", uid);
		return "live_anchor/liveMemberManage";
	}
	
	
	/**
	 * 加载直播会员/主播信息
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "viewJuniorInfo/list")
	@ResponseBody
	public Object viewJuniorInfoList(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		try {
			liveAnchorService.getMemberListPage(liveAnchor, pageable);
			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	
	/**
	 * 直播会员添加页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add/init")
	@RequestToken(createToken=true,tokenName="liverToken")
	public String addInit(BLiver liveAnchor,Model model) {
		return "live_anchor/liverEdit";
	}
	
	
	/**
	 * 添加直播会员信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add")
	@RequestToken(removeToken=true,tokenName="liverToken")
	@ResponseBody
	public Resultable add(BLiver liveAnchor) {
		Resultable result=new Resultable();
		try {
			//判断是否存在b_urs用户,isBinding 为Y 则直接保存主播信息，否则先创建寻蜜鸟会员
			int count=liveAnchorService.saveLiverInfo(liveAnchor);
			if(count>0){
				result.setMsg("添加成功!");
				result.setSuccess(true);
			}else{
				result.setMsg("添加失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 主播修改页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String updateInit(BLiver liveAnchor,Model model) {
		BLiver anchor = liveAnchorService.selectByPrimaryKey(liveAnchor.getId());
		Integer uid = anchor.getUid();
		LiveWallet walletInfo = liveWalletService.selectByUid(uid);
		anchor.setRestrictive(walletInfo.getRestrictive());
		anchor.setLimitBalance(walletInfo.getLimitBalance());
		//下线人数
		long juniors = liveAnchorService.countJuniorsByUid(uid);
		anchor.setJuniors(juniors);
		model.addAttribute("anchor",anchor );
		return "live_anchor/liveMemberEdit";
	}
	
	
	/**
	 * 
	 * 方法描述：更新主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8上午11:13:04
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable update(BLiver liveAnchor){
		Resultable result=new Resultable();
		try {
			liveAnchor.setPassword(null);//修改主播信息时，密码不可修改
			liveAnchor.setUpdateTime(new Date());
			
			//拆分绑定上级代码
			/*BLiver liverBean = liveAnchorService.selectBLiver(liveAnchor);
			String superiorUid = liverBean.getSuperiorUid();
			long juniors = liveAnchor.getJuniors();
			//不存在上级，且下级人数为0
			if(StringUtils.isBlank(superiorUid)&&juniors==0l){
				liveAnchorService.addSuperiorInfo(liveAnchor);
			}*/
			
			
			liveAnchorService.updateByPrimaryKeySelective(liveAnchor);
			liveWalletService.updateLiveWalletInfo(liveAnchor);
//			liveAnchorService.bindSuperiorInfo(liveAnchor);
			
			//修改真实姓名
			liveAnchorService.updateBursInfoByUid(liveAnchor);
			
			result.setMsg("更新成功!");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到修改绑定上级信息页面<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7上午11:10:16 <br/>
	 * @param liver
	 * @return
	 */
	@RequestMapping(value="bindSuperiorInfo/init")
	@RequestToken(createToken=true,tokenName="bindSuperiorInfoToken")
	public String bindSuperiorInfoInit(BLiver liver,Model model){
		liveMemberService.setSuperiorInfo(liver,model);
		return "live_anchor/bindSuperiorInfo";
	}
	
	/**
	 * 
	 * 方法描述：修改绑定上级信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7上午11:10:16 <br/>
	 * @param liver
	 * @return
	 */
	@RequestLogging(name="绑定上级信息")
	@RequestMapping(value="bindSuperiorInfo")
	@RequestToken(removeToken=true,tokenName="bindSuperiorInfoToken")
	@ResponseBody
	public Resultable bindSuperiorInfo(BLiver liver){
		Resultable result=new Resultable();
		try {
			liveMemberService.bindSuperiorInfo(liver);
			liveMemberService.bindInderectSuperiorInfo(liver);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
		}finally{
			String[] data={"直播会员ID",liver.getId().toString(),"绑定上级信息","修改"};
			liveMemberService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		return result;
	}
	

	
	/**
	 * 获取主播信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "getAnchorById")
	@ResponseBody
	public Object getAnchorById(BLiver liveAnchor) {
		BLiver anchor = liveAnchorService.selectByPrimaryKey(liveAnchor.getId());
		return anchor;
	}
	
	
	/**
	 * 
	 * 方法描述：验证手机是否已注册直播用户
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午7:58:55
	 * @return
	 */
	@RequestMapping(value = "checkAccount")
	@ResponseBody
	public Object checkAccount(BLiver anchor){
		String result="";
		//用户类型 1 主播 2 普通用户
//		anchor.setUtype(LiveConstant.UTYPE_ANCHOR);
		Long count = liveAnchorService.count(anchor);
		if(count==0){
			result="true";
		}else {
			result= "【"+anchor.getPhone()+"】已被注册,不可重复添加";
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：绑定间接上级，关系校验 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-27下午2:35:26 <br/>
	 * @param anchor
	 * @return
	 */
	@RequestMapping(value = "bindSuperiorInfo/indirectValidate")
	@ResponseBody
	public Object indirectValidate(BLiver anchor){
		Resultable result=new Resultable();
		result=liveMemberService.indirectValidate(anchor);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取当前会员是否有V客充值订单 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-27下午2:35:26 <br/>
	 * @param anchor
	 * @return
	 */
	@RequestMapping(value = "bindSuperiorInfo/getRechargeInfo")
	@ResponseBody
	public Object getRechargeInfo(BLiver anchor){
		long count = liveMemberService.getRechargeInfo(anchor);
		return count;
	}
	
	
	/**
	 * 
	 * 方法描述：导出主播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午2:57:30
	 */
	@RequestMapping(value="export")
	public void export(BLiver anchor,HttpServletRequest request,HttpServletResponse response){
		anchor.setOrder(PageConstant.NOT_ORDER);
		anchor.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params=new HashMap<String,Object>();
//		anchor.setUtype(LiveConstant.UTYPE_COMMON);
		params.put("list", liveAnchorService.getLiveMemberList(anchor));
		doExport(request,response,"live_anchor/liveMember.xls",params);
		
	}
	
	
	@RequestMapping(value="viewLivePurseInfo")
	public String viewLiveMemberPurseInfo(@RequestParam("uid") String uid, Model model){
		model.addAttribute("uid", uid);
		return "live_anchor/livePurseList";
	}
	
	@RequestMapping(value = "viewLivePurseInfo/list")
	@ResponseBody
	public Object viewLiveMemberPurseInfoList(TLivePurse livePurse) {
		Pageable<TLivePurse> pageable = new Pageable<TLivePurse>(livePurse);
		try {
			liveMemberService.getPurseListPage(livePurse, pageable);
			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	

	/**
	 * 方法描述：导出用户钱包记录信息 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年3月15日下午3:40:30 <br/>
	 * @param livePurse
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="exportPurseInfoList")
	public void exportPurseInfoList(TLivePurse livePurse,HttpServletRequest request,HttpServletResponse response){
		livePurse.setOrder(PageConstant.NOT_ORDER);
		livePurse.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("list", liveMemberService.searchPurseDataList(livePurse));
		doExport(request,response,"live_anchor/liveMemberPurseList.xls",params);
		
	}
}
