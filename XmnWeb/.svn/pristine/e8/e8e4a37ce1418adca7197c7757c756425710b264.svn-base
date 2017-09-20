package com.xmniao.xmn.core.reward_dividends.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.reward_dividends.service.BursEarningsRelationService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BursRelationChainController
 * 
 * 类描述： 会员收益关系链Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-16 上午11:02:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="会员收益关系链管理")
@Controller
@RequestMapping(value = "bursRelationChain/manage")
public class BursRelationChainController extends BaseController {
	
	
	
	/**
	 * 会员收益关系链服务
	 */
	@Autowired
	private BursEarningsRelationService bursRelationChainService;
	
	
	/**
	 * 关系链管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "reward_dividends/bursRelationChainManage";
	}
	
	/**
	 * 关系链管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(BursEarningsRelation relationChain) {
		Pageable<BursEarningsRelation> pageable = new Pageable<BursEarningsRelation>(relationChain);
		
		try {
			List<BursEarningsRelation> list = bursRelationChainService.getDetailList(relationChain);
			Long count = bursRelationChainService.count(relationChain);
			pageable.setContent(list);
			pageable.setTotal(count);
			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到添加关系链页面<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7上午11:10:16 <br/>
	 * @param bursRelation
	 * @return
	 */
	@RequestMapping(value="add/init")
	@RequestToken(createToken=true,tokenName="addToken")
	public String addInit(BursEarningsRelation bursRelation,Model model){
		return "reward_dividends/addRelation";
	}
	
	/**
	 * 
	 * 方法描述：绑定当前会员是否已有此渠道会员身份 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-27下午2:35:26 <br/>
	 * @param anchor
	 * @return
	 */
	@RequestMapping(value = "add")
	@RequestToken(removeToken=true,tokenName="addToken")
	@ResponseBody
	public Object add(BLiver anchor){
		Resultable result=new Resultable();
		result=bursRelationChainService.addRelation(anchor);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到修改绑定上级信息页面<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-7上午11:10:16 <br/>
	 * @param bursRelation
	 * @return
	 */
	@RequestMapping(value="bindSuperiorInfo/init")
	@RequestToken(createToken=true,tokenName="bindSuperiorInfoToken")
	public String bindSuperiorInfoInit(BursEarningsRelation bursRelation,Model model){
		bursRelationChainService.setSuperiorInfo(bursRelation,model);
		return "reward_dividends/bindRelationSuperiorInfo";
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
	public Resultable bindSuperiorInfo(BLiver liver,BursEarningsRelation relationReq){
		Resultable result=new Resultable();
		try {
			bursRelationChainService.bindSuperiorInfo(liver);
			bursRelationChainService.bindInderectSuperiorInfo(liver,relationReq);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
		}finally{
			String[] data={"直播会员UID",liver.getUid().toString(),"绑定上级信息","修改"};
			bursRelationChainService.fireLoginEvent(data, result.getSuccess()==true?1:0);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：初始化直播用户下拉框,排除等下级
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initLiverExclude",method=RequestMethod.POST)
	@ResponseBody
	public Object initLiverExclude(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		liveAnchor.setOrder(PageConstant.NOT_ORDER);
		List<BLiver> liveAnchorList = bursRelationChainService.getLiverInfoExcludeList(liveAnchor);
		pageable.setContent(liveAnchorList);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：初始化直播用户下拉框(指定渠道来源)
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "initLiverByObjectOriented",method=RequestMethod.POST)
	@ResponseBody
	public Object initLiverByObjectOriented(BLiver liveAnchor) {
		Pageable<BLiver> pageable = new Pageable<BLiver>(liveAnchor);
		liveAnchor.setOrder(PageConstant.NOT_ORDER);
		List<BLiver> liveAnchorList = bursRelationChainService.getLiverInfoByObjectOriented(liveAnchor);
		pageable.setContent(liveAnchorList);
		return pageable;
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
		result=bursRelationChainService.indirectValidate(anchor);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：绑定当前会员是否已有此渠道会员身份 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-27下午2:35:26 <br/>
	 * @param anchor
	 * @return
	 */
	@RequestMapping(value = "bindSuperiorInfo/haveObjectOriented")
	@ResponseBody
	public Object haveObjectOriented(BLiver anchor){
		Resultable result=new Resultable();
		result=bursRelationChainService.haveObjectOriented(anchor);
		return result;
	}
	
	
	/**
	 * 获取查询记录数
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/getCurrentSize")
	@ResponseBody
	public Object getCurrentSize(BursEarningsRelation relationChain){
		long count=0l;
		try {
			count = bursRelationChainService.count(relationChain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 
	 * 方法描述：导出关系链信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午2:57:30
	 */
	@RequestMapping(value="export")
	public void export(BursEarningsRelation relationChain,HttpServletRequest request,HttpServletResponse response){
		relationChain.setOrder(PageConstant.NOT_ORDER);
		relationChain.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("list", bursRelationChainService.getDetailList(relationChain));
		doExport(request,response,"reward_dividends/BursRelation.xls",params);
		
	}
	
	
}
