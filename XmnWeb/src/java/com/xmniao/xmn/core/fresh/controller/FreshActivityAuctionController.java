/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.TFreshActivityAuction;
import com.xmniao.xmn.core.fresh.entity.TFreshActivityAuctionBidding;
import com.xmniao.xmn.core.fresh.service.FreshActivityAuctionBiddingService;
import com.xmniao.xmn.core.fresh.service.FreshActivityAuctionService;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshActivityAuctionController
 * 
 * 类描述：积分商城竞拍活动管理Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-2 上午10:35:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping("freshAuction/manage")
public class FreshActivityAuctionController extends BaseController{
	
	/**
	 * 注入竞拍活动Service
	 */
	@Autowired
	private FreshActivityAuctionService auctionService;
	
	/**
	 * 注入竞拍活动加价Service
	 */
	@Autowired
	private FreshActivityAuctionBiddingService auctionBiddingService;
	
	/**
	 * 
	 * 方法描述：跳转到竞拍活动管理列表页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2上午10:51:47 <br/>
	 * @return
	 */
	@RequestMapping("init")
	public String init(){
		return "fresh/freshAuctionManage";
	}
	
	/**
	 * 
	 * 方法描述：加载竞拍活动管理列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2上午10:53:33 <br/>
	 * @param freshActivityAuction
	 * @return
	 */
	@RequestMapping("init/list")
	@ResponseBody
	public Object initList(TFreshActivityAuction freshActivityAuction){
		Pageable<TFreshActivityAuction> pageable=new Pageable<TFreshActivityAuction>(freshActivityAuction);
		try {
			List<TFreshActivityAuction> list = auctionService.getListInfo(freshActivityAuction);
			Long total = auctionService.count(freshActivityAuction);
			pageable.setContent(list);
			pageable.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加竞拍活动管理页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2上午10:54:04 <br/>
	 * @return
	 */
	@RequestMapping("add/init")
	public String addInit(){
		return "fresh/auctionEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加竞拍活动 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午2:28:44 <br/>
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Resultable add(TFreshActivityAuction freshActivityAuction){
		Resultable result=new Resultable();
		try {
			auctionService.saveInfo(freshActivityAuction);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到编辑竞拍活动管理页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-2上午10:54:04 <br/>
	 * @return
	 */
	@RequestMapping("update/init")
	public String updateInit(TFreshActivityAuction freshActivityAuction,Model model){
		try {
			TFreshActivityAuction activity=auctionService.getAuctionInfo(freshActivityAuction);
			model.addAttribute("activity", activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fresh/auctionEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新竞拍活动信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午6:26:54 <br/>
	 * @param freshActivityAuction
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Resultable update(TFreshActivityAuction freshActivityAuction){
		Resultable result=new Resultable();
		try {
			auctionService.updateInfo(freshActivityAuction);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新竞拍活动信息失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：立即结束活动 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午5:06:59 <br/>
	 * @return
	 */
	@RequestMapping(value="update/terminate")
	@ResponseBody
	public Resultable teminate(TFreshActivityAuction freshActivityAuction){
		Resultable result =new Resultable();
		try {
			auctionService.terminate(freshActivityAuction);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除活动 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-3下午5:06:59 <br/>
	 * @return
	 */
	@RequestMapping(value="update/delete")
	@ResponseBody
	public Resultable delete(TFreshActivityAuction freshActivityAuction){
		Resultable result =new Resultable();
		try {
			auctionService.deleteInfo(freshActivityAuction);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到竞拍加价列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-4下午4:08:29 <br/>
	 * @return
	 */
	@RequestMapping(value="bidding/list/init")
	public String biddingInit(TFreshActivityAuction freshActivityAuction,Model model){
		model.addAttribute("freshActivityAuction", freshActivityAuction);
		return "fresh/auctionBiddingList";
	}
	
	/**
	 * 
	 * 方法描述：加载竞拍加价列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-4下午4:08:25 <br/>
	 * @param auctionBidding
	 * @return
	 */
	@RequestMapping(value="bidding/list")
	@ResponseBody
	public Object biddingList(TFreshActivityAuctionBidding auctionBidding){
		Pageable<TFreshActivityAuctionBidding> pageable=new Pageable<TFreshActivityAuctionBidding>(auctionBidding);
		try {
			List<TFreshActivityAuctionBidding> list = auctionBiddingService.getList(auctionBidding);
			Long total = auctionBiddingService.count(auctionBidding);
			pageable.setContent(list);
			pageable.setTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
}
