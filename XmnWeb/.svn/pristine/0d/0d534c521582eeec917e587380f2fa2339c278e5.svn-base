/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRankDetail;
import com.xmniao.xmn.core.live_anchor.service.BLiveFansRankDetailService;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveRankRestitutionController
 * 
 * 类描述： 返还模式管理Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-25 下午3:19:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value = "liveRankRestitution/manage")
public class LiveRankRestitutionController extends BaseController {
	
	/**
	 * 注入级别返利模式Service
	 */
	@Autowired
	private BLiveFansRankDetailService fansRankDetailService;
	
	
	/**
	 * 跳转到粉丝级别返利模式管理列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(BLiveFansRank fansRank,Model model) {
		model.addAttribute("rankId", fansRank.getId());
		return "live_anchor/rankRestitutionManage";
	}
	
	/**
	 * 
	 * 方法描述：加载粉丝级别返利模式列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param fansRankDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object initList(BLiveFansRankDetail fansRankDetail, Model model) {
		Pageable<BLiveFansRankDetail> pageabel = new Pageable<BLiveFansRankDetail>(fansRankDetail);
		try {
			List<BLiveFansRankDetail> list = fansRankDetailService.getListWithRestitution(fansRankDetail);
			Long count = fansRankDetailService.count(fansRankDetail);
			pageabel.setContent(list);
			pageabel.setTotal(count);
			JSON.toJSON(pageabel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageabel;
	}
	

	/**
	 * 
	 * 方法描述：跳转到添加返还模式页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-27下午1:59:18 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	@RequestToken(createToken=true,tokenName="fansRankDetailEditToken")
	public String addInit(){
		
		return "live_anchor/fansRankDetailEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加返还模式 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-27下午2:48:00 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@RequestToken(removeToken=true,tokenName="fansRankDetailEditToken")
	@ResponseBody
	public Resultable add(BLiveFansRankDetail fansRankDetail){
		Resultable result=new Resultable();
		try {
			fansRankDetailService.saveRankDetailInfo(fansRankDetail);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加返还模式失败："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到粉丝级别返还模式编辑页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-28上午10:21:03 <br/>
	 * @param fansRankDetailReq
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(BLiveFansRankDetail fansRankDetailReq){
		ModelAndView modelAndView=new ModelAndView();
		BLiveFansRankDetail fansRankDetail=fansRankDetailService.setFansRankDetailInfo(fansRankDetailReq);
		modelAndView.addObject("fansRankDetail", fansRankDetail);
		modelAndView.setViewName("live_anchor/fansRankDetailEdit");
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：更新返还模式比例配置 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-28上午11:03:26 <br/>
	 * @param fansRankDetailReq
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(BLiveFansRankDetail fansRankDetailReq){
		Resultable result=new Resultable();
		try {
			fansRankDetailService.update(fansRankDetailReq);
			fansRankDetailService.updateRedPacketDetailInfo(fansRankDetailReq);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("更新返还模式比例配置失败:"+e.getMessage(), e);
		}
		return result;
	}
	
}
