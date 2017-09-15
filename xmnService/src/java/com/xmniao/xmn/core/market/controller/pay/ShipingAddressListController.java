package com.xmniao.xmn.core.market.controller.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.ShipingAddressService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: ShipingAddressListController    
* @Description:收货地址列表   
* @author: liuzhihao   
* @date: 2016年12月21日 下午5:21:23
 */
@Controller
@RequestMapping("/api/v1/market/pay")
public class ShipingAddressListController implements BaseVControlInf{

	@Autowired
	private ShipingAddressService shipingAddressService;
	
	//验证数据
	@Autowired
	private Validator validator;
	
	private MapResponse response = null;
	
	@RequestMapping(value="/address/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object editShipingAddress(BaseRequest baseRequest){
		//验证数据
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return versionControl(baseRequest.getApiversion(),baseRequest);
	}

	public Object versionOne(Object object){
		Map<Object,Object> map = new HashMap<Object,Object>();
		BaseRequest baseRequest = (BaseRequest) object;
		List<Map<Object,Object>> result = shipingAddressService.findAll(baseRequest);
		if(result != null && !result.isEmpty()){
			map.put("address", result);
			response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		}else{
			response = new MapResponse(ResponseCode.FAILURE,"查询失败");
		}
		response.setResponse(map);
		return response;
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1:
				return versionOne(object);
			default:
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
}
