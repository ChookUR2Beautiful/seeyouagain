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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
*      
* 类名称：UrsCollectSellerCancelApi   
* 类描述：   取消收藏
* 创建人：xiaoxiong   
* 创建时间：2016年11月17日 下午6:32:24     
*
 */
@Controller
@RequestMapping("urs/collect/seller")
public class UrsCollectSellerCancelApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(UrsCollectSellerCancelApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private UrsService ursService;
	

	
	@ResponseBody
	@RequestMapping("/cancel")
	public Object cancel(SelleridRequest request){
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
	
	/**
	 * 
	 * @Description: 取消收藏
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	private Object versionControlOne(Object object) {
		
		SelleridRequest request =(SelleridRequest)object;
		try {
			String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.FAILURE,"错误");
			}
			int count = ursService.deleteUrsCollectByUidAndSellerid(request.getSellerid(),Integer.parseInt(uid));
			if(count>0){
				try {
					ursService.userActionService(3,-1,Integer.parseInt(uid),request.getSellerid(),1);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("取消收藏店铺，调用业务服务接口失败");
				}
				
				return new BaseResponse(ResponseCode.SUCCESS, "成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "错误");
		}
		return new BaseResponse(ResponseCode.FAILURE, "失败");
	}

}
