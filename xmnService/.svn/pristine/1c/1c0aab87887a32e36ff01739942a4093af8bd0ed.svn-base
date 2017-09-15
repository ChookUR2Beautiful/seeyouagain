package com.xmniao.xmn.core.market.controller.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.ShipingAddressService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: RemoveShipingAddressController    
* @Description:删除收货地址   
* @author: liuzhihao   
* @date: 2016年12月21日 下午5:36:09
 */
@Controller
@RequestMapping("/api/v1/market/pay")
public class ShipingAddressRemoveController implements BaseVControlInf{

	@Autowired
	private ShipingAddressService shipingAddressService;
	
	//验证数据
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/address/delete",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object editShipingAddress(IDRequest idRequest){
		//验证数据
		List<ConstraintViolation> result = validator.validate(idRequest);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return versionControl(idRequest.getApiversion(),idRequest);
	}

	public Object versionOne(Object object){
		IDRequest idRequest = (IDRequest) object;
		int result = shipingAddressService.remove(idRequest);
		if(result != 0){
			return new BaseResponse(ResponseCode.SUCCESS,"删除成功");
		}
		return new BaseResponse(ResponseCode.FAILURE,"删除失败");
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
