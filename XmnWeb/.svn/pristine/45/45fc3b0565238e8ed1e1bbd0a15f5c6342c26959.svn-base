package com.xmniao.xmn.core.manor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.manor.entity.TManorFlower;
import com.xmniao.xmn.core.manor.entity.TManorFlowerRelation;
import com.xmniao.xmn.core.manor.entity.TManorInfo;
import com.xmniao.xmn.core.manor.service.ManorMemberService;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.reward_dividends.service.BursEarningsRelationService;
import com.xmniao.xmn.core.thrift.service.manorService.FailureException;
import com.xmniao.xmn.core.thrift.service.manorService.ResponseData;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

@RequestLogging(name="庄园会员管理")
@Controller
@RequestMapping(value = "manorMember/manage")
public class ManorMemberController extends BaseController {
	
	
	/**
	 * 庄园会员管理服务
	 */
	@Autowired
	private ManorMemberService manorMemberService;
	
	@Autowired
	private BursService bursService;
	
	@Autowired
	private BursEarningsRelationService bursEarningsRelationService;
	
	@RequestMapping(value = "init")
	public String init() {
		return "golden_manor/manorMemberManage";
	}
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TManorInfo manorInfo) {
		Pageable<TManorInfo> pageable = new Pageable<TManorInfo>(manorInfo);
		
		//用户类型 1 主播 2 普通用户
		try {
			pageable = manorMemberService.getManorInfoInfoList(manorInfo);
//			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	@RequestMapping(value = "update/init")
	public String updateInit(TManorInfo manorInfo,Model model) {
		TManorInfo anchor = manorMemberService.getManorInfoData(manorInfo);
		model.addAttribute("anchor",anchor );
		return "live_anchor/liveMemberEdit";
	}
	
	
	@RequestMapping(value = {"update"})
	@ResponseBody
	public Resultable update(TManorInfo manorInfo){
		Resultable result=new Resultable();
		try {
			manorInfo.setUpdateTime(new Date());
			
			manorMemberService.saveUpdateActivity(manorInfo);
			
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
	
	@RequestMapping(value = "list/viewLowerLevel")
	@ResponseBody
	public Object getManorMemberLowerLevelList(TManorInfo manorInfo) {
		Resultable resultable = null;
		try {
			List<TManorFlower> verExcitationReceiveList = manorMemberService.getManorMemberLowerLevelList( manorInfo);
			resultable = new Resultable(true, "查询成功", verExcitationReceiveList);
			return resultable;
			
		} catch (Exception e) {
			log.error("查询下级列表信息失败", e);
			resultable = new Resultable(false, "查询下级列表信息失败");
			return resultable;
		}

	}
	
	@RequestMapping(value = "init/getCurrentDataSize")
	@ResponseBody
	public Object getCurrentDataSize(TManorInfo manorInfo) {
		long count=0l;
		try {
			count = manorMemberService.count(manorInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 方法描述：导出庄园会员 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月7日下午2:39:46 <br/>
	 * @param anchor
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="export")
	public void export(TManorInfo anchor,HttpServletRequest request,HttpServletResponse response){
		anchor.setOrder(PageConstant.NOT_ORDER);
		anchor.setLimit(PageConstant.PAGE_LIMIT_NO);
		
		Map<String,Object> params=new HashMap<String,Object>();
//		anchor.setUtype(LiveConstant.UTYPE_COMMON);
		params.put("list", manorMemberService.getManorInfoList(anchor));
		doExport(request, response, "golden_manor/manorMember.xls", params);
		
	}
	
	@RequestMapping("usrChain/init")
	public Object usrChainInit(){
		return "golden_manor/usrChainInit";
	}
	
	@RequestMapping("usrChain/choose")
	@ResponseBody
	public Object usrChainUrsChoose(Burs burs){
		Pageable<Burs> pageable = new Pageable<>(burs);
		List<Burs> ursList = bursService.getUrsList(burs);
		pageable.setContent(ursList);
		return pageable;
	}
	
	@RequestMapping("usrChain/parentChoose")
	@ResponseBody
	public Object usrChainParentChoose(BursEarningsRelation burs){
		Pageable<BursEarningsRelation> pageable = new Pageable<>(burs);
		List<BursEarningsRelation> ursList = manorMemberService.getUsrChainParentChoose(burs);
		pageable.setContent(ursList);
		return pageable;
	}
	
	@RequestMapping("usrChain/getManorParent")
	@ResponseBody
	public Object usrChainGetManorParent(@RequestParam(required=true)Integer uid){
		BursEarningsRelation r= bursEarningsRelationService.getManorByUid(uid);
		return r;
	}
	
	@RequestMapping(value={"usrChain/BindingParent"},method=RequestMethod.POST)
	@ResponseBody
	public Object usrChainBindingParent(@RequestParam(required=true) Integer childId,@RequestParam(required=true)Integer parentId){
		BursEarningsRelation child= bursEarningsRelationService.getManorByUid(childId);
		BursEarningsRelation parent= bursEarningsRelationService.getManorByUid(parentId);
		if(child!=null){
			return Resultable.defeat("会员已有上级,不能绑定");
		}
		if(parent==null){
			return Resultable.defeat("父级没有庄园信息,不能绑定");
		}
		Resultable result;
		try {
			result = bursEarningsRelationService.usrChainBindingParent(childId,parentId);
		} catch (Exception e) {
			return Resultable.defeat();
		}
		return result;
	}
	
	
	@RequestMapping("usrChain/activate/init")
	public Object usrChainAactivateInit(){
		return "golden_manor/usrChainActivateInit";
	}
	
	@RequestMapping("usrChain/activate/getManorMsg")
	public Object usrChainActivateGetManorMsg(@RequestParam(required=true)Integer uid){
		TManorInfo info = manorMemberService.getObject(uid.longValue());
		/*manorMemberService.getUser*/
		return "golden_manor/usrChainActivateInit";
	}
	
	@RequestMapping("usrChain/edit/choose")
	@ResponseBody
	public Object usrChainEditChoose(TManorInfo manorInfo){
		Pageable<TManorInfo> pageable = new Pageable<>(manorInfo);
		List<TManorInfo> manorInfoList = manorMemberService.getManorInfoList(manorInfo);
		pageable.setContent(manorInfoList);
		return pageable;
	}
	
	@RequestMapping("usrChain/edit/hasChlid")
	@ResponseBody
	public Object countChlid(@RequestParam(required=true)Integer uid){
		boolean hasChild = manorMemberService.hasChlid(uid);
		return hasChild;
	}
	
/*	@RequestMapping("usrChain/edit/parentChoose")
	@ResponseBody
	public Object usrChainEditParentChoose(ManorFlowerBranch flowerBranch){
		//获取所有可以绑定的坑(坑没有下级)
		Pageable<ManorFlowerBranch> pageable = new Pageable<>(flowerBranch);
		List<ManorFlowerBranch> list= manorMemberService.getUsrChainEditParentChoose(flowerBranch);
		pageable.setContent(list);
		return pageable;
	}*/
	
	@RequestMapping("usrChain/edit/init")
	public Object usrChainEditInit(){
		return "golden_manor/usrChainEditInit";
	}
	
	@RequestMapping("usrChain/edit/getFlowerBranch")
	@ResponseBody
	public Object getFlowerBranch(@RequestParam(required=true)Integer uid){
		List<ManorFlowerBranch> list= manorMemberService.getFlowerBranch(uid);	
		return list;
	}
	
	@RequestMapping("usrChain/Edit/BindingManorParent")
	@ResponseBody
	public Object bindingManorParent(@RequestParam(required=true)Integer childId,@RequestParam(required=true)Integer parentId,@RequestParam(required=true)Integer location){
		if(manorMemberService.hasChlid(childId)){
			return Resultable.defeat("该用户花朵链不可迁移");
		}
		Resultable r= manorMemberService.bindingManorParent(childId,parentId,location);
		return r;
	}
	
	
	@RequestMapping("usrChain/activate/getManorState")
	@ResponseBody
	public Object getManorState(@RequestParam(required=true)Integer uid){
		TManorInfo manorInfo = manorMemberService.getObject(uid.longValue());
		List<Map<String,String>> statisticsUserProps = manorMemberService.statisticsUserProps(uid);
		Map<String,Object> map =new HashMap<>();
		if(manorInfo!=null&&manorInfo.getManorDeadline().getTime()>new Date().getTime()){
			map.put("state", "已激活");
		}else{
			map.put("state", "未激活");
		}
		map.put("activate", "0");
		for (Map<String, String> prop : statisticsUserProps) {
			if("3".equals(prop.get("type"))){
				map.put("activate", prop.get("number"));
			}
		}
		return map;
	}
	
	@RequestMapping("usrChain/activate/activateManor")
	@ResponseBody
	public Object activateManor(@RequestParam(required=true)Integer uid){
		try {
			ResponseData activateManorThrift = manorMemberService.activateManorThrift(uid);
			if(activateManorThrift.getState()==0){
				return Resultable.success();
			}
			else{
				return Resultable.defeat(activateManorThrift.getMsg());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return Resultable.defeat();
	}
	
	
}
