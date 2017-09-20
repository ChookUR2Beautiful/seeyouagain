/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import com.xmniao.xmn.core.vstar.entity.TVstarPlayerInfo;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardConf;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardRecord;
import com.xmniao.xmn.core.vstar.service.TVstarPlayerInfoService;
import com.xmniao.xmn.core.vstar.service.TVstarRewardConfService;
import com.xmniao.xmn.core.vstar.service.TVstarRewardRecordService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：vstarRewardController
 * 
 * 类描述： 新时尚大赛推荐奖励Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-1 下午6:25:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping(value = "VstarReward/manage")
public class vstarRewardController extends BaseController {
	
	
	/**
	 * 注入新时尚大赛选手服务
	 */
	@Autowired
	private TVstarPlayerInfoService vstarPlayerService;
	
	/**
	 * 注入奖励记录服务
	 */
	@Autowired
	private TVstarRewardRecordService rewardRecordService;
	
	/**
	 * 注入奖励配置服务
	 */
	@Autowired
	private TVstarRewardConfService rewardConfService;
	
	
	/**
	 * 
	 * 方法描述：跳转到管理页面 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "vstar/vstarRewardManage";
	}
	
	/**
	 * 
	 * 方法描述：加载奖励列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TVstarRewardRecord rewardRecord) {
		Pageable<TVstarRewardRecord> pageable=new Pageable<TVstarRewardRecord>(rewardRecord);
		Object json =null;
		try {
			List<TVstarRewardRecord> list = rewardRecordService.getListInfo(rewardRecord);
			long count = rewardRecordService.count(rewardRecord);
			pageable.setContent(list);
			pageable.setTotal(count);
			json = JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * 方法描述：跳转到奖励设置页面  <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-20下午2:18:50 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	@RequestToken(createToken=true,tokenName="rewardConfToken")
	public String addInit(){
		return "vstar/rewardConfEdit";
	}
	
	/**
	 * 
	 * 方法描述：新增奖励设置  <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-20下午2:18:50 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@RequestToken(removeToken=true,tokenName="rewardConfToken")
	@ResponseBody
	public Resultable add(TVstarRewardConf rewardConf){
		Resultable result = new Resultable();
		try {
			rewardConfService.add(rewardConf);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("vstarRewardController——>add(),新增奖励设置失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到奖励设置编辑页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-20下午3:24:47 <br/>
	 * @param rewardConfReq
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TVstarRewardConf rewardConfReq){
		ModelAndView mv=new ModelAndView();
		TVstarRewardConf rewardConf = rewardConfService.getFirstObject();
		mv.addObject("rewardConf", rewardConf);
		mv.setViewName("vstar/rewardConfEdit");
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：保存推荐奖励配置信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-20下午3:42:56 <br/>
	 * @param rewardConfReq
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TVstarRewardConf rewardConfReq){
		Resultable result = new Resultable();
		try {
			Integer update = rewardConfService.update(rewardConfReq);
			if(update>0){
				result.setSuccess(true);
				result.setMsg("保存成功!");
			}else{
				result.setSuccess(false);
				result.setMsg("保存失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("执行vstarRewardController——>update()方法,保存推荐奖励配置信息失败："+e.getMessage(), e);
		}
		
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
	public Object getCurrentSize(TVstarRewardRecord rewardRecord){
		long count=0l;
		try {
			count = rewardRecordService.count(rewardRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 
	 * 方法描述：导出<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-12下午6:08:14 <br/>
	 * @param playerInfo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "export")
	public void export(TVstarPlayerInfo playerInfo, HttpServletRequest request,
			HttpServletResponse response) {
		playerInfo.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", vstarPlayerService.getRankListInfo(playerInfo));
		doExport(request, response, "vstar/vstarPlayerRankInfo.xls", params);

	}

}
