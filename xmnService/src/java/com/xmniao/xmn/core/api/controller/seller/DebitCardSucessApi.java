package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.xmer.SellerInfoApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.common.request.seller.DebitCardSucessRquest;
import com.xmniao.xmn.core.common.request.seller.UserDebitCardSellerRquest;
import com.xmniao.xmn.core.seller.service.UserDebitCardService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
*      
* 类名称：专享表领取列表  
* 类描述：专享卡商户列表
* 创建人：wdh   
* 创建时间：2016年11月14日 上午10:40:34     
*
 */
@Controller
@RequestMapping("sellerCard")
public class DebitCardSucessApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoApi.class);
		
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UserDebitCardService userDebitCardService;
	

	
	@ResponseBody
	@RequestMapping("/debitGetSuccess")
	public Object list(DebitCardSucessRquest request){
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
			return versionControl(request.getApiversion(),request);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	/**
	 * 
	 * @Description: 
	 * @author wdh
	 * @date 2016年11月14日
	 */
	private Object versionOne(Object object) {
		DebitCardSucessRquest rq = (DebitCardSucessRquest) object ;			
		return userDebitCardService.showSuccessDebitCard(rq);
	}
	

}
