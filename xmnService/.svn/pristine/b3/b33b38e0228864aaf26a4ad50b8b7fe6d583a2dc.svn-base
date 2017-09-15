package com.xmniao.xmn.core.api.controller.xmer;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SignPayConfirmRequest;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.xmer.controller.SignPayConfirmController;
import com.xmniao.xmn.core.xmer.service.BuySaasPackageService;


/**
 * 
* 类名称：PlacePayApi   
* 类描述：   代付接口
* 创建人：xiaoxiong   
* 创建时间：2016年8月31日 上午9:50:43   
* 修改人：xiaoxiong   
* 修改时间：2016年8月31日 上午9:50:43   
* 修改备注：   
* @version    
*
 */

@Controller
public class PlacePayOrderApi {
		
	
	//日志报错s
		private static final Logger logger = LoggerFactory.getLogger(SignPayConfirmController.class);
		//注入验证
		@Autowired
		private Validator validator;
		
		//注入购买saas套餐解耦
		@Autowired
		private BuySaasPackageService buySaasPackageService;
		
		//注入缓存
		@Autowired
		private SessionTokenService sessionTokenService;
	
		
		/**
		 * 
		 * @Description: 代付创建订单接口
		 * @author xiaoxiong
		 * @date 2016年8月31日
		 * @version
		 */
		@RequestMapping(value="placePayOrder",method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
		@ResponseBody
		public Object placePay(SignPayConfirmRequest signPayrequest,HttpServletRequest request){ 
			try {
				List<ConstraintViolation> result = validator.validate(signPayrequest);
				if(result.size() >0 && result != null){
					logger.info("提交的数据有问题"+result.get(0).getMessage());
					Map<String,Object> returnResult = new HashMap<String,Object>();
					returnResult.put("result", result.get(0).getMessage());
				}
				String openid=CookieUtils.getVal(AuthzController.WEIXIN_OPENID_KEY, request);
				if(openid == null){
					String callback = ("/placePayOrder?parentid=" + signPayrequest.getParentid() + "&id="
							+ signPayrequest.getId());

					callback = URLEncoder.encode(callback, "utf-8");

					String redirect = "/weixin/authz/authorize?callback=" + callback;
					return "redirect:" + redirect;
					
				}
				logger.info("请求微信openid:"+openid);
				signPayrequest.setOpenid(openid);
				signPayrequest.setOtherPay(1);
				
				return buySaasPackageService.addSoldOrder(signPayrequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new BaseResponse(ResponseCode.FAILURE,"失败");
		}
	
	
		
}
