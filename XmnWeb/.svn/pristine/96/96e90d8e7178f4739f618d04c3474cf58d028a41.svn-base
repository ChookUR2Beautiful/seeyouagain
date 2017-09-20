/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.TLiveBroadcast;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.live_anchor.service.TLiveBroadcastService;
import com.xmniao.xmn.core.live_anchor.service.TLiveRecordService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveGiftController
 *
 * 类描述：直播广播消息Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-17下午2:21:47
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="广播管理")
@Controller
@RequestMapping(value = "liveBroadcast/manage")
public class LiveBroadcastController extends BaseController {
	
	/**
	 * 注入直播广播服务
	 */
	@Autowired
	private TLiveBroadcastService liveBroadcastService;
	
	/**
	 * 注入通告服务
	 */
	@Autowired
	private TLiveRecordService liveRecordService;
	
	
	
	/**
	 * 广播管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/broadcastManage";
	}
	
	/**
	 * 广播管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TLiveBroadcast liveBroadcast) {
		Pageable<TLiveBroadcast> pageable = new Pageable<TLiveBroadcast>(liveBroadcast);
		List<TLiveBroadcast> list = liveBroadcastService.getList(liveBroadcast);
		Long count = liveBroadcastService.count(liveBroadcast);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：添加广播页面初始化
	 * 创建人： huang'tao
	 * 创建时间：2016-8-17下午2:58:39
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String addInit(){
		return "live_anchor/broadcastEdit";
	}
	
	/**
	 * 
	 * 方法描述：保存广播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-17下午3:27:16
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Object add(TLiveBroadcast broadcast){
		Resultable result=new Resultable();
		try {
			broadcast.setCreateTime(new Date());
			broadcast.setUpdateTime(new Date());
			liveBroadcastService.add(broadcast);
			//立即发送 1是 0 否
			Integer immediate = broadcast.getImmediate();
			if(1==immediate){
				liveBroadcastService.sendBroadcast(broadcast);
			}
			result.setMsg("添加成功!");
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("添加失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到广播编辑页面
	 * 创建人： huang'tao
	 * 创建时间：2016-8-17下午5:02:27
	 * @param broadcast
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update/init")
	public String updateInit(TLiveBroadcast broadcastRequest,Model model){
		TLiveBroadcast broadcast = liveBroadcastService.selectById(broadcastRequest.getId());
		model.addAttribute("broadcast", broadcast);
		return "live_anchor/broadcastEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新广播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-17下午4:55:18
	 * @param broadcast
	 * @return
	 */
	@RequestMapping(value={"update"})
	@ResponseBody
	public Object update(TLiveBroadcast broadcast){
		Resultable result=new Resultable();
		try {
			broadcast.setUpdateTime(new Date());
			Integer count = liveBroadcastService.update(broadcast);
			
			Integer immediate = broadcast.getImmediate();
			if(1==immediate){
				liveBroadcastService.sendBroadcast(broadcast);
			}
			if(count>0){
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}else{
				result.setSuccess(false);
				result.setMsg("操作失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error(e.getMessage(), e);
		}finally{
			String[] logInfo={"广播ID:",broadcast.getId().toString(),"更新广播信息","更新"};
			liveBroadcastService.fireLoginEvent(logInfo, result.getSuccess()?1:0);
		}
		return result;
	}
	
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Object delete(TLiveBroadcast broadcast,Model model){
		Resultable result=new Resultable();
		try {
			liveBroadcastService.deleteById(broadcast.getId());
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}finally{
			String[] logInfo={"广播ID:",broadcast.getId().toString(),"删除广播信息","删除"};
			liveBroadcastService.fireLoginEvent(logInfo, result.getSuccess()?1:0);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：初始化主播直播下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value = "initRecordId",method=RequestMethod.POST)
	@ResponseBody
	public Object initAnchorId(TLiveRecord liveRecord) {
		Pageable<TLiveRecord> pageable = new Pageable<TLiveRecord>(liveRecord);
		liveRecord.setZhiboType(LiveConstant.LIVE_UNDERWAY);
		List<TLiveRecord> liveRecordList = liveBroadcastService.getLiveRecordList(liveRecord);
		pageable.setContent(liveRecordList);
		return pageable;
	}
	
	/**
	 * 获取主播直播通告信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "getRecordById")
	@ResponseBody
	public Object getRecordById(TLiveRecord liveRecord) {
		TLiveRecord record =null;
		if(liveRecord.getId()!=null){
			record=liveRecordService.getObject(liveRecord.getId().longValue());
		}
		return record;
	}
	
	/**
	 * 
	 * 方法描述：重新发送广播 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-3下午3:14:42 <br/>
	 * @param broadcast
	 * @return
	 */
	@RequestMapping(value={"resend"})
	@ResponseBody
	public Object resend(TLiveBroadcast broadcast){
		Resultable result=new Resultable();
		try {
			liveBroadcastService.sendBroadcast(broadcast);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error(e.getMessage(), e);
		}finally{
			String[] logInfo={"广播ID:",broadcast.getId().toString(),"发送广播信息","发送"};
			liveBroadcastService.fireLoginEvent(logInfo, result.getSuccess()?1:0);
		}
		return result;
	}

}
