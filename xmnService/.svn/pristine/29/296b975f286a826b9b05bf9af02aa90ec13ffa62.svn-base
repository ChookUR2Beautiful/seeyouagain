package com.xmniao.xmn.core.api.controller.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.live.entity.FansCouponAnchorRef;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.service.SellerService;


/**
 * 
* 类名称：SellerCouponListApic   
* 类描述：   查询店铺预售列表
* 创建人：xiaoxiong   
* 创建时间：2016年11月21日 上午10:46:48
 */
@Controller
@RequestMapping("seller/coupon")
public class SellerCouponListApi implements BaseVControlInf{
	
	
	private final Logger log = Logger.getLogger(SellerCouponListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@ResponseBody
	@RequestMapping("/list")
	public Object list(SelleridPageRequest request){
		log.info("SelleridPageRequest data:" + request.toString());
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
			SelleridPageRequest request = (SelleridPageRequest)object;
			
			Map<Object, Object> map=new HashMap<>();
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("page", request.getPage());
			params.put("pageSize", request.getPageSize());
			params.put("sellerid", request.getSellerid());
			
			/**
			 * 批量查询预售卷列表
			 */
			List<Map<String,Object>> couponList = sellerService.queryCouponBySellerid(params);
			map.put("couponList", couponList);
			
			//预售卷详情访问地址
			String annunciate_info_url = propertiesUtil.getValue("annunciate_info_url", "conf_live.properties");
			map.put("url",annunciate_info_url);
			
			mapResponse.setResponse(map);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}
	

}
