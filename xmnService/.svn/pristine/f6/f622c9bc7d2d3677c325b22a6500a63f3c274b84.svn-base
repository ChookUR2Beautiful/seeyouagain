package com.xmniao.xmn.core.api.controller.seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.UserSellerRequest;
import com.xmniao.xmn.core.seller.service.UserSellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: BrowsedSellersApi    
* @Description:浏览记录接口   
* @author: liuzhihao   
* @date: 2016年11月23日 下午4:58:45
 */
@RequestMapping("/browse")
@Controller
public class BrowsedSellersApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(BrowsedSellersApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	//与用户相关
	@Autowired
	private UserSellerService userSellerService;
	
	private MapResponse response = null;
	
	/**
	 * 定位
	 * @param locationRequest
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object browseSellers(UserSellerRequest userSellerRequest){
		log.info("userSellerRequest data:"+userSellerRequest.toString());
		List<ConstraintViolation> result = validator.validate(userSellerRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(userSellerRequest.getApiversion(), userSellerRequest);
	}

	public Object versionOne(Object object){
		Map<Object,Object> map = new HashMap<Object,Object>();
		UserSellerRequest request = (UserSellerRequest) object;
		if(request.getStatus() == 1){
			response = new MapResponse(ResponseCode.SUCCESS,"此功能正在建设中,敬请期待......");
			map.put("sellers", new ArrayList<Map<Object,Object>>());
			response.setResponse(map);
		}else{
			List<Map<Object,Object>> browses = userSellerService.querySellerByBrowse(request);
			if(browses != null && !browses.isEmpty()){
				map.put("sellers", browses);
			}else {
				map.put("sellers", new ArrayList<>());
			}
			response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
		}
		return response;
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
