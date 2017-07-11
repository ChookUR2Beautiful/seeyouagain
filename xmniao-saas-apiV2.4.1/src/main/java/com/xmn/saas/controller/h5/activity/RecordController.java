package com.xmn.saas.controller.h5.activity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.constants.ActivityConsts;
import com.xmn.saas.controller.h5.activity.vo.RecordRequest;
import com.xmn.saas.entity.activity.Activity;
import com.xmn.saas.entity.activity.ActivityRecord;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.activity.RecordService;
import com.xmn.saas.service.common.CommonService;

@Controller(value="h5-activity-record-controller")
@RequestMapping(value = "h5/activity/record")
public class RecordController extends AbstractActiviryController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecordController.class);
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private CommonService commonService;

	/**
	 * 
	 * 方法描述：获得营销活动列表 创建人：jianming 创建时间：2016年9月26日 下午8:42:59
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@RequestParam(value="sessionToken",required=false)String sessionToken,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex,HttpServletRequest request) {
		// 查询营销活动数量,如果没有跳转首次页面,否则获得获得列表
		ModelAndView modelAndView = new ModelAndView("activity/introduce");
		try {
			SellerAccount sellerAccount = this.getSellerAccount();
			if(pageIndex==1){
				List<Object> beingActivitys = recordService.getList(sellerAccount, true,pageSize,pageIndex);
				List<Object> endActivitys = recordService.getList(sellerAccount, false,pageSize,pageIndex);
				if (beingActivitys.size() > 0 || endActivitys.size() > 0) {
					modelAndView.addObject("beingActivitys", beingActivitys);
					modelAndView.addObject("endActivitys", endActivitys);
					modelAndView.addObject("requestURL", request.getRequestURL());
					modelAndView.setViewName("activity/list-activity");
					return modelAndView;
				}
				modelAndView.setViewName("activity/introduce");
				return modelAndView;
			}else{
				List<Object> endActivitys = recordService.getList(sellerAccount, false,pageSize,pageIndex);
				return new Response(ResponseCode.SUCCESS, "成功",endActivitys);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	@RequestMapping(value = "select_type", method = RequestMethod.GET)
	public String selectType() {
		return "activity/select-type";
	}

	/**
	 * 
	 * 方法描述：调到活动详情页1 创建人：jianming 创建时间：2016年10月11日 下午5:26:36
	 * 
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "detail_init", method = RequestMethod.POST)
	public ModelAndView detailInit(RecordRequest recordRequest) {
		ModelAndView modelAndView = new ModelAndView("activity/detail-activity");
		if(recordRequest.getActivityType()==ActivityConsts.ACTIVITY_TYPE_FCOUSPONTS){
			String title="会员集点活动";
			modelAndView.addObject("title",title);
		}
		modelAndView.addObject("activityId", recordRequest.getActivityId());
		modelAndView.addObject("activityType", recordRequest.getActivityType());
		return modelAndView;
	}

	/**
	 * 
	 * 方法描述：调到活动详情页1 创建人：jianming 创建时间：2016年10月11日 下午5:26:36
	 * 
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	@ResponseBody
	public Object detail(RecordRequest recordRequest) {
		try {
			Activity activity = recordService.detailActivity(recordRequest.getActivityId(),
					recordRequest.getActivityType(), this.getSellerId());
			BigDecimal activeAmount =new BigDecimal(0);
			try{
				activeAmount = commonService.getActiveAmount(this.getSellerId(),activity.getBeginDate(), activity.getEndDate());
			}catch(Exception e){
			    
			}
			activity.setConsumeAmount(activeAmount.doubleValue()<0?BigDecimal.ZERO:activeAmount);//刺激消费负数设置为0
			return new Response(ResponseCode.SUCCESS, "成功", activity);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	 * 方法描述：调到领取列表页 创建人：jianming 创建时间：2016年10月11日 下午5:57:12
	 * 
	 * @param activityId
	 * @param activityType
	 * @return
	 */
	@RequestMapping(value = "list_record_init", method = RequestMethod.GET)
	public ModelAndView listRecordInit(RecordRequest recordRequest) {
		ModelAndView modelAndView = new ModelAndView("activity/list-activity-record");
		try {
			Activity activity = recordService.detailActivity(recordRequest.getActivityId(),
					recordRequest.getActivityType(), this.getSellerId());
			//统计活动剩余奖品数
			Integer residue=recordService.countResidue(activity);
			activity.setResidue(residue);
			modelAndView.addObject("activity", activity);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 
	 * 方法描述：获取领取列表接口 创建人：jianming 创建时间：2016年10月11日 下午5:57:12
	 * 
	 * @param activityId
	 * @param activityType
	 * @return
	 */
	@RequestMapping(value = "list_record", method = RequestMethod.POST)
	@ResponseBody
	public Object listRecord(RecordRequest recordRequest,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,@RequestParam(value="pageIndex",defaultValue="1")Integer pageIndex) {
		try {
			List<ActivityRecord> activityRecords = recordService.listActivityRecord(recordRequest.getActivityId(),
					recordRequest.getActivityType(), this.getSellerId(),pageSize,pageIndex);
			return new Response(ResponseCode.SUCCESS, "成功", activityRecords);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return new Response(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	 * 方法描述：领取明细页 创建人：jianming 创建时间：2016年10月11日 下午5:57:12
	 * 
	 * @param activityId
	 * @param activityType
	 * @return
	 */
	@RequestMapping(value = "detail_record", method = RequestMethod.GET)
	public ModelAndView detailRecord(RecordRequest recordRequest) {
		ModelAndView modelAndView;
		try {
			modelAndView = new ModelAndView();
			ActivityRecord activityRecord = recordService.detailRecord(recordRequest.getActivityId(),
					recordRequest.getActivityType(), recordRequest.getRecordId(),this.getSellerId());
			if (activityRecord != null) {
				if (activityRecord.getAwardType() == 3) { // 抵用券
					modelAndView.setViewName("activity/detail-diyongAward");
				} else if (activityRecord.getAwardType() == 4) { // 赠品券
					modelAndView.setViewName("activity/detail-zengpinAward");
				} else if (activityRecord.getAwardType() == 5) { // 普通红包
					modelAndView.setViewName("activity/detail-redpacketAward");
				}
				modelAndView.addObject("record", activityRecord);
			}
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@RequestMapping(value = "has_activity", method = RequestMethod.POST)
	@ResponseBody
	private void hasActivity(@RequestParam(value="activityType",required=true)Integer activityType){
		try {
			Integer beingCount=recordService.hasActivity(activityType,this.getSellerId());
			if(beingCount==0){
				new Response(ResponseCode.SUCCESS, "活动可创建").write();
			}else{
				new Response(ResponseCode.ACTIVITY_HAD_APPLY, "活动已存在").write();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping(value = "end_activity", method = RequestMethod.POST)
	@ResponseBody
	public void endActivity(RecordRequest recordRequest){
		try {
			recordService.endActivity(recordRequest.getActivityId(),recordRequest.getActivityType(),this.getSellerId());
			new Response(ResponseCode.SUCCESS, "删除成功",this.getCookieToken()).write();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			try {
				new Response(ResponseCode.FAILURE, "删除失败").write();
			} catch (IOException e1) {
				logger.error(e.getMessage(),e1);
				e1.printStackTrace();
			}
		}
	}

}
