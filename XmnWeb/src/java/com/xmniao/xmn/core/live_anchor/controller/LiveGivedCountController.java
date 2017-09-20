/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGivedGift;
import com.xmniao.xmn.core.live_anchor.service.TLiveGivedGiftService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveGivedCountController
 * 
 * 类描述： 直播打赏礼物统计Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-8 下午8:38:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播打赏礼物统计管理")
@Controller
@RequestMapping(value = "liveGivedCount/manage")
public class LiveGivedCountController extends BaseController {
	
	@Autowired
	private TLiveGivedGiftService liveGivedGiftService;
	
	/**
	 * 跳转到直播打赏礼物列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/liveGivedCountManage";
	}
	
	/**
	 * 
	 * 方法描述：加载直播打赏礼物列表<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param liveGivedGift
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object anchorImageList(TLiveGivedGift liveGivedGift, Model model) {
		Pageable<TLiveGivedGift> pageabel = new Pageable<TLiveGivedGift>(liveGivedGift);
		try {
			List<TLiveGivedGift> list = liveGivedGiftService.getGivedGiftList(liveGivedGift);
			Long count = liveGivedGiftService.count(liveGivedGift);
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
	 * 方法描述：导出直播支付订单
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2下午2:00:47
	 * @param livePayOrder
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="export")
	public void export(TLiveGivedGift liveGivedGift,HttpServletRequest request,HttpServletResponse response){
		liveGivedGift.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("list", liveGivedGiftService.getGeneralCountBean(liveGivedGift));
		doExport(request,response,"live_anchor/liveGivedCount.xls",params);
	}
	
	
	/**
	 * 
	 * 方法描述：加载统计区间数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午5:56:19 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@RequestMapping(value="load/generalCount")
	@ResponseBody
	public Resultable loadGeneralCount(TLiveGivedGift liveGivedGift){
		Resultable result=new Resultable();
		try {
			Map<String, Object> generalCount = liveGivedGiftService.generalCount(liveGivedGift);
			result.setSuccess(true);
			result.setData(generalCount);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载统计区间数据失败!");
			this.log.error("加载统计区间数据失败"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载打赏礼物统计饼图数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午5:56:19 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@RequestMapping(value="load/giftCount")
	@ResponseBody
	public Resultable loadGiftCount(TLiveGivedGift liveGivedGift){
		Resultable result=new Resultable();
		try {
			List<Map<String, Object>> giftMapList = liveGivedGiftService.getGiftCount(liveGivedGift);
			result.setSuccess(true);
			result.setData(giftMapList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载打赏礼物统计数据失败!");
			this.log.error("加载打赏礼物统计数据失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载主播获得打赏数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午5:56:19 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@RequestMapping(value="load/anchorCount")
	@ResponseBody
	public Resultable loadAnchorCount(TLiveGivedGift liveGivedGift){
		Resultable result=new Resultable();
		try {
			List<Map<String, Object>> anchorMapList = liveGivedGiftService.getAnchorCount(liveGivedGift);
			result.setSuccess(true);
			result.setData(anchorMapList);
			this.log.info(JSON.toJSONString(anchorMapList));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载主播获得打赏数据!");
			this.log.error("加载主播获得打赏数据失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载鸟豆打赏区间人数统计数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午5:56:19 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@RequestMapping(value="load/birdBeanZoneCount")
	@ResponseBody
	public Resultable loadBirdBeanZoneCount(TLiveGivedGift liveGivedGift){
		Resultable result=new Resultable();
		try {
			List<Map<String, Object>> birdBeanZoneMapList = liveGivedGiftService.getBirdBeanZoneCount(liveGivedGift);
			result.setSuccess(true);
			result.setData(birdBeanZoneMapList);
			this.log.info(JSON.toJSONString(birdBeanZoneMapList));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载鸟豆打赏区间人数统计!");
			this.log.error("加载鸟豆打赏区间人数统计失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载打赏时间统计数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午5:56:19 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@RequestMapping(value="load/timeZoneCount")
	@ResponseBody
	public Resultable loadTimeZoneCount(TLiveGivedGift liveGivedGift){
		Resultable result=new Resultable();
		try {
			List<Map<String, Object>> birdBeanZoneMapList = liveGivedGiftService.getTimeZoneCount(liveGivedGift);
			result.setSuccess(true);
			result.setData(birdBeanZoneMapList);
			this.log.info(JSON.toJSONString(birdBeanZoneMapList));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载打赏时间统计数据!");
			this.log.error("加载打赏时间统计数据失败:"+e.getMessage(), e);
		}
		return result;
	}
	
}
