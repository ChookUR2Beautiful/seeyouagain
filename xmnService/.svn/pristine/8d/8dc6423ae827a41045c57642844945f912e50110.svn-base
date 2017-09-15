package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.urs.UrsCollectSellerCancelApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.SelleridRequest;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
* 类名称：SellerBrowsedInsertApi   
* 类描述：店铺浏览记录   
* 创建人：xiaoxiong   
* 创建时间：2016年11月18日 上午9:37:31     
*
 */
@Controller
@RequestMapping("seller/browsed")
public class SellerBrowsedInsertApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(SellerBrowsedInsertApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private UrsService ursService;
	
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object intsert(SelleridRequest request){
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
			SelleridRequest request = (SelleridRequest) object;
			
			String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR, "token错误或已过期，请重新登入");
			}
			/**
			 * 查询浏览表中是否有浏览的记录
			 */
			int viewCount = sellerService.querySellerBrowsedCount(uid,request.getSellerid());
			if(viewCount>0){
				/**
				 * 用户浏览店铺的数量+1
				 */
				sellerService.updateSellerBrowSed(uid,request.getSellerid());
			}else{
				/**
				 * 添加用户浏览店铺记录
				 */
				sellerService.insertSellerBrowsed(uid,request.getSellerid());
			}
			return new BaseResponse(ResponseCode.SUCCESS, "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}

}
