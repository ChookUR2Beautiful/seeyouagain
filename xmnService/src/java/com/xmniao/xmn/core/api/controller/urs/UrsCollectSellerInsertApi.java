package com.xmniao.xmn.core.api.controller.urs;
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
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.verification.service.UrsService;




/**
 * 
*      
* 类名称：UrsCollectSellerInsertApi   
* 类描述：   用户收藏店铺
* 创建人：xiaoxiong   
* 创建时间：2016年11月11日 上午10:59:08     
*
 */
@Controller
@RequestMapping("urs/collect/seller")
public class UrsCollectSellerInsertApi implements BaseVControlInf{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(UrsCollectSellerInsertApi.class);
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UrsService ursService;
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(SelleridRequest request){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(request.getApiversion(), request);
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
	
	private Object versionOne(Object object) {
		SelleridRequest request=(SelleridRequest)object;
		return ursService.ursCollectSellerInsert(request);
	}

}
