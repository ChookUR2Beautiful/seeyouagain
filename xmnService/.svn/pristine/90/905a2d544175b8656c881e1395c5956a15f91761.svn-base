package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
* 类名称：SellerDetailFoodListApi   
* 类描述：   商家详情查询推荐菜列表
* 创建人：xiaoxiong   
* 创建时间：2017年1月12日 下午2:32:13
 */
@RequestMapping("seller")
@Controller
public class SellerDetailFoodListApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerDetailApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@RequestMapping("/food/list")
	@ResponseBody
	public Object sellerDetailFoodList(SelleridPageRequest request){
		log.info("IDRequest data:" + request.toString());
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
		SelleridPageRequest request =(SelleridPageRequest)object;
		return sellerService.sellerDetailFoodList(request);
	}

}
