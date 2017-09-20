/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRank;
import com.xmniao.xmn.core.live_anchor.service.BLiveFansRankService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveFansRankController
 * 
 * 类描述： 直播粉丝级别Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-8 下午8:38:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播粉丝级别管理")
@Controller
@RequestMapping(value = "liveFansRank/manage")
public class LiveFansRankController extends BaseController {
	
	/**
	 * 注入粉丝级别Service
	 */
	@Autowired
	private BLiveFansRankService fansRankService;
	
	/**
	 * 跳转到粉丝级别管理列表页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/fansRankManage";
	}
	
	/**
	 * 
	 * 方法描述：加载粉丝级别列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:50:02 <br/>
	 * @param fansRank
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "init/list" })
	@ResponseBody
	public Object initList(BLiveFansRank fansRank, Model model) {
		Pageable<BLiveFansRank> pageabel = new Pageable<BLiveFansRank>(fansRank);
		try {
			List<BLiveFansRank> list = fansRankService.getListInfo(fansRank);
			Long count = fansRankService.count(fansRank);
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
	 * 方法描述：跳转到添加粉丝级别页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(){
		return "live_anchor/fansRankEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加粉丝级别 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(BLiveFansRank fansRank){
		Resultable result = new Resultable();
		try {
			fansRankService.saveInfo(fansRank);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加粉丝级别失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑粉丝级别页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param fansRank
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(BLiveFansRank fansRank){
		ModelAndView modelAndView = new ModelAndView();
		Long id = fansRank.getId();
		BLiveFansRank fansRankInfo = fansRankService.getFansRank(id);
		modelAndView.addObject("fansRank", fansRankInfo);
		modelAndView.setViewName("live_anchor/fansRankEdit");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改粉丝级别 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param fansRank
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(BLiveFansRank fansRank){
		Resultable result=new Resultable();
		try {
			fansRank.setUpdateTime(new Date());
			fansRankService.update(fansRank);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改精彩视频失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除粉丝级别信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete( @RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			int count = 0;
			if(StringUtils.isNotBlank(ids)){
				count=fansRankService.delete(ids.split(","));
			}
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：批量更新粉丝级别启用状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateBatch")
	@ResponseBody
	public Object updateBatch(@RequestParam("ids") String ids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=fansRankService.updateBatch(ids,status);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取粉丝级别列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-21上午10:30:57 <br/>
	 * @param fansRank
	 * @return
	 */
	@RequestMapping(value="getFansRanks")
	@ResponseBody
	public Object getFansRanks(BLiveFansRank fansRank){
		Pageable<BLiveFansRank> pageable =new Pageable<BLiveFansRank>(fansRank);
		Integer filterVal = fansRank.getFilterVal();
		fansRank.setRankType(filterVal);
		List<BLiveFansRank> content = fansRankService.getList(fansRank);
		pageable.setContent(content);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：获取粉丝级别信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-13上午9:57:22 <br/>
	 * @param celebrity
	 * @return
	 */
	@RequestMapping(value = "getFansRankInfoById")
	@ResponseBody
	public Object getFansRankInfoById(BLiveFansRank fansRank) {
		BLiveFansRank fansRankInfo = fansRankService.getFansRank(fansRank.getId());
		return fansRankInfo;
	}

}
