package com.xmniao.xmn.core.api.controller.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.live.AttentionAnchorMessageListApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.RecomSellerRequest;
import com.xmniao.xmn.core.seller.service.SellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: UntasteListApi    
* @Description:看过不如一尝的商铺列表   
* @author: liuzhihao   
* @date: 2016年12月2日 下午12:26:17
 */
@RequestMapping("/untaste/seller")
@Controller
public class UntasteListApi implements BaseVControlInf{

private final Logger log = Logger.getLogger(AttentionAnchorMessageListApi.class);
	
	@Autowired
	private Validator validator;

	@Autowired
	private SellerService sellerService;
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object queryRecomSellers(RecomSellerRequest recomSellerRequest){
		//验证提交数据
		List<ConstraintViolation> result = validator.validate(recomSellerRequest);
		if(result != null && !result.isEmpty()){
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(recomSellerRequest.getApiversion(), recomSellerRequest);
	}

	public Object versionOne(Object object){
		Map<Object,Object> map = new HashMap<Object,Object>();
		RecomSellerRequest recomSellerRequest = (RecomSellerRequest) object;
		map = sellerService.getUneatSellers(recomSellerRequest);
		MapResponse resposne = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		resposne.setResponse(map);
		return resposne;
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		// TODO Auto-generated method stub
		switch(v){
			case 1:
				return versionOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
}
