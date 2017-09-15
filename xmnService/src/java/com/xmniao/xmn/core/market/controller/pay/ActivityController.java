package com.xmniao.xmn.core.market.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.market.pay.ActivityRequest;
import com.xmniao.xmn.core.market.service.pay.ActivityService;

/**
 * 
* @projectName: xmnService 
* @ClassName: ActivityController    
* @Description:通用活动   
* @author: liuzhihao   
* @date: 2016年12月22日 上午11:47:42
 */
@RequestMapping("/api/v1/market/pay")
@Controller
public class ActivityController{
		
	@Autowired
	private ActivityService activityService;

	/**
	 * 查看活动
	 * @param idRequest
	 * @return
	 */
	@RequestMapping(value="/activity")
	public String activityInfo(Model model,ActivityRequest activityRequest){
		
		if(activityRequest.getId() == null){
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题"));
			return "error";
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
		response.setResponse(activityService.queryActivityInfo(activityRequest));
		model.addAttribute("data", response);
		return "/market/acitivity";
	}
	
	
	@RequestMapping(value="other/acivity/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getProductInfo(ActivityRequest activityRequest){
		if(activityRequest.getId() == null){
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		
		return activityService.queryActivityProductList(activityRequest);
	}
}
