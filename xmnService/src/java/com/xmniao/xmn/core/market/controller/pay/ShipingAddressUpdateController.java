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
import com.xmniao.xmn.core.common.request.market.pay.EditIsDefaultRequest;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.service.pay.ShipingAddressService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: UpdateShipingAddressController    
* @Description:修改收货地址默认状态   
* @author: liuzhihao   
* @date: 2016年12月21日 下午5:56:10
 */
@Controller
@RequestMapping("/api/v1/market/pay")
public class ShipingAddressUpdateController implements BaseVControlInf{

	@Autowired
	private ShipingAddressService shipingAddressService;
	
	//验证数据
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/address/isdefault",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object editShipingAddress(EditIsDefaultRequest editIsDefaultRequest){
		//验证数据
		List<ConstraintViolation> result = validator.validate(editIsDefaultRequest);
		if(result != null && !result.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : result){
                message+=vo.getMessage()+",";
            }
			
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return versionControl(editIsDefaultRequest.getApiversion(),editIsDefaultRequest);
	}

	public Object versionOne(Object object){
		EditIsDefaultRequest editIsDefaultRequest = (EditIsDefaultRequest) object;
		
		int result = shipingAddressService.updateIsDefault(editIsDefaultRequest);
		
		if(result != 0){
			return new BaseResponse(ResponseCode.SUCCESS,"修改成功");
		}
		return new BaseResponse(ResponseCode.FAILURE,"修改失败");
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
