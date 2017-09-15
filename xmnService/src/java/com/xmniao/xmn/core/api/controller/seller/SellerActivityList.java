package com.xmniao.xmn.core.api.controller.seller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* 类名称：SellerActivityList   
* 类描述：   查询商家全部活动
* 创建人：xiaoxiong   
* 创建时间：2016年11月21日 下午4:25:36
 */
@Controller
@RequestMapping("seller/activity")
public class SellerActivityList implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerActivityList.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(SelleridRequest request){
		log.info("SelleridRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题:"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		try {
			SelleridRequest request =(SelleridRequest) object;
			/**
			 * 查询商家所有活动
			 */
			List<Map<String,Object>> activityList = sellerService.queryActivityList(request.getSellerid());
			if(activityList==null){
				activityList = new ArrayList<>();
			}
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> result = new HashMap<Object, Object>();
			result.put("activityList", activityList);
			mapResponse.setResponse(result);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}
	
	@RequestMapping("/all")//h5
	public String all(SelleridRequest request,Model model) throws IOException{
		log.info("SelleridRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			model.addAttribute("info", result.get(0).getMessage());
			return "activity/error";
		}
		model.addAttribute("params", request);
		model.addAttribute("url", propertiesUtil.getValue("share.url", "conf_config.properties"));
		model.addAttribute("killUrl", propertiesUtil.getValue("share.kill.url", "conf_common.properties"));
		return "activity/activity_all";
	}
	

}
