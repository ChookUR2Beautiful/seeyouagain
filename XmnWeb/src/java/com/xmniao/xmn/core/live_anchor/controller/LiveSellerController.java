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
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.service.TSequenceService;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.live_anchor.service.TLiveRecordService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：LiveRecordController
 *
 * 类描述：直播管理Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午4:07:43
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="直播管理")
@Controller
@RequestMapping(value = "liveSeller/manage")
public class LiveSellerController extends BaseController {
	
	private final Integer RECORD_NUMID=100006;//直播管理排序号
	
	/**
	 * 注入通告服务
	 */
	@Autowired
	private TLiveRecordService liveRecordService;
	
	/**
	 * 注入序列号服务
	 */
	@Autowired
	private TSequenceService sequenceService;
	
	/**
	 * 直播管理列表初始页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(BLiver liver,Model model) {
		model.addAttribute("liver",liver);
		return "live_anchor/liveSellerManage";
	}
	
	/**
	 * 直播管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TLiveRecord liveRecord) {
		Pageable<TLiveRecord> pageable = new Pageable<TLiveRecord>(liveRecord);
		liveRecordService.getListPageWithRowNum(liveRecord, pageable);
		return pageable;
	}
	
	/**
	 * 主播时长列表页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "liveTimeInit")
	public String liveTimeInit(BLiver liver,Model model) {
		model.addAttribute("liver",liver);
		return "live_anchor/liveTimeInit";
	}
	
	/**
	 * 添加通告页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add/init")
	public String addInit(TLiveRecord liveRecord,Model model) {
		return "live_anchor/recordEdit";
	}
	
	/**
	 * 添加通告信息
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Resultable add(TLiveRecord liveRecord) {
		Resultable result=new Resultable();
		try {
			Long sequenceNo = sequenceService.getAndUpdateSid(RECORD_NUMID);
			liveRecord.setSequenceNo(Integer.valueOf(sequenceNo.toString()));
			liveRecord.setZhiboType(-1);//直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4 无回放 5结束直播
			liveRecord.setCreateTime(new Date());
			liveRecord.setUpdateTime(new Date());
			liveRecordService.add(liveRecord);
			result.setSuccess(true);
			result.setMsg("添加成功!");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("添加失败!");
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：删除通告信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午6:26:35
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Resultable delete(TLiveRecord liveRecord){
		Resultable result=new Resultable();
		try {
			int count = liveRecordService.deleteById(liveRecord.getId());
			//删除后,对大于当前序号的记录重新排序(排序号-1),并更新t_sequence表中对应的sid
			liveRecordService.updateSequenceNo(liveRecord);
			if(count>0){
				result.setMsg("删除成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 通告修改页面初始化
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String updateInit(TLiveRecord liveRecord,Model model) {
		try {
			model.addAttribute("liveRecord", liveRecordService.getObject(liveRecord.getId().longValue()));
			model.addAttribute("setSellerSequNo", "Y");//推荐商家排序设置权限,Y 设置 , N 不可设置
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "live_anchor/recordEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新直播信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8上午11:13:04
	 * @param liveAnchor
	 * @return
	 */
	@RequestMapping(value = {"update","setAdvance"})
	@ResponseBody
	public Resultable update(TLiveRecord liveRecord){
		Resultable result=new Resultable();
		try {
			int count = liveRecordService.update(liveRecord);
			if(count>0){
				result.setMsg("更新成功!");
				result.setSuccess(true);
			}else {
				result.setMsg("更新失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping(value = {"upOrDown"})
	@ResponseBody
	public Resultable upOrDown(TLiveRecord liveRecordRequest){
		Resultable result=new Resultable();
		TLiveRecord targetRecord=new TLiveRecord();//目标记录,将A移动到B,则B为目标记录
		TLiveRecord liveRecord=liveRecordService.getObject(liveRecordRequest.getId().longValue());//操作记录
		int offset=0;//偏移量,1目标记录下移,-1目标记录上移
		int count=0;//受影响的记录数
		try {
			String operationType = liveRecordRequest.getOperationType();
			Integer sequenceNo = liveRecord.getSequenceNo();//发生上移(下移)操作记录的序号
			Integer maxSequnceNo = liveRecordService.getMaxSequnceNo();//当前通告最大序号
			if("up".equals(operationType)){
				if(sequenceNo==1){
					result.setSuccess(false);
					result.setMsg("当前序号最小,不可上移");
				}else{
					liveRecord.setSequenceNo(sequenceNo-1);
					targetRecord.setSequenceNo(sequenceNo-1);
					offset=1;
					targetRecord.setOffset(offset);
					liveRecordService.updateTargetSequenceNo(targetRecord);//目标记录下移
					count = liveRecordService.update(liveRecord);//操作记录上移
				}
			}else if("down".equals(operationType)){
				if(sequenceNo==maxSequnceNo){
					result.setSuccess(false);
					result.setMsg("当前序号最大,不可下移");
				}else{
					liveRecord.setSequenceNo(sequenceNo+1);
					targetRecord.setSequenceNo(sequenceNo+1);
					offset=-1;
					targetRecord.setOffset(offset);
					liveRecordService.updateTargetSequenceNo(targetRecord);//目标记录上移
					count = liveRecordService.update(liveRecord);//操作记录下移
				}
				
			}
			if(count>0){
				result.setMsg("操作成功!");
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setMsg("操作失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：导出通告信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-8下午2:57:30
	 */
	@RequestMapping(value="export")
	public void export(TLiveRecord record,HttpServletRequest request,HttpServletResponse response){
		record.setOrder(PageConstant.NOT_ORDER);
		record.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("list", liveRecordService.getList(record));
		doExport(request,response,"live_anchor/liveRecord.xls",params);
		
	}
	
}
