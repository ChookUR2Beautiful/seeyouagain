package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SellerNearListRequest;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
* 类名称：SellerNearListApi   
* 类描述：   查询附近店铺
* 创建人：xiaoxiong   
* 创建时间：2016年11月30日 下午4:29:27
 */
@Controller
@RequestMapping("seller")
public class SellerNearListApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerNearListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	
	@ResponseBody
	@RequestMapping("/near/list")
	public Object list(SellerNearListRequest request){
		log.info("SellerNearListRequest data:" + request.toString());
		
		//参数验证
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
		SellerNearListRequest request =(SellerNearListRequest) object;
		
		return sellerService.sellerNearList(request);
	}
}
