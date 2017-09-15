package com.xmniao.xmn.core.api.controller.live;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveBuyBirdCoinIOSRequest;
import com.xmniao.xmn.core.live.service.UserPayBirdCoinService;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveBuyBirdCoinComboIOSApi   
* 类描述： 直播购买鸟币套餐 IOS 内购版
* 创建人：yhl   
* 创建时间：2016年8月12日 下午4:08:40   
* @version    
*
 */
@Controller
public class LiveBuyBirdCoinComboIOSApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveBuyBirdCoinComboIOSApi.class);
	
	/**
	 * 验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入购买鸟币service
	 */
	@Autowired 
	private UserPayBirdCoinService userPayBirdCoinService;
	

	/**
	 * @Description 验证票据
	 * @author yhl
	 * @param giftsInfoRequest
	 * @return josn
	 * 2016-8-10 16:59:03
	 * */
	@RequestMapping(value = "/live/buyBirdCoin/IOScombo" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLiveRecordList(LiveBuyBirdCoinIOSRequest iosRequest,HttpServletRequest request){
			try {
				//苹果内购以二进制流接收
				InputStream is = request.getInputStream();
				String jsonData = IOUtils.toString(is, "utf-8");
				if (jsonData!=null && !"".equals(jsonData)) {
					JSONObject jsonObject = JSONObject.parseObject(jsonData);
					iosRequest.setReceiptStr(jsonObject.getString("receipt-data"));
					iosRequest.setOrderNo(jsonObject.getString("orderNo"));
					iosRequest.setApiversion(Integer.parseInt(jsonObject.getString("apiversion")));
					iosRequest.setAppversion(jsonObject.getString("appversion"));
					iosRequest.setSystemversion(jsonObject.getString("systemversion"));
					iosRequest.setSessiontoken(jsonObject.getString("sessiontoken"));
					log.info("请求流接收:" + iosRequest.getReceiptStr());
				}else {
					return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
			}
			
			List<ConstraintViolation> result = validator.validate(iosRequest);
			if(result != null && result.size()>0){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!"+result.get(0).getMessage());
			}
			return versionControl(iosRequest.getApiversion(), iosRequest);
		}
		
		public Object versionOne(Object obj){
			LiveBuyBirdCoinIOSRequest iosRequest = (LiveBuyBirdCoinIOSRequest)obj;
			return userPayBirdCoinService.validateIOSReceiptData(iosRequest);
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
	
	
	
	
	
}
