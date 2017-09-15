/**
 * 2016年8月15日 下午4:46:43
 */
package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.EggtobirdmoneyRequest;
import com.xmniao.xmn.core.live.service.EggToBirdMoneyService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：EggToBirdMoneyApi
 * @类描述：鸟蛋转换鸟币Api
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午4:46:43
 * @version
 */
@Controller
public class EggToBirdMoneyApi implements BaseVControlInf {

	private final Logger log = Logger.getLogger(EggToBirdMoneyApi.class);

	@Autowired
	EggToBirdMoneyService eggtobirdmoneyService;
	//注入validator
	@Autowired
	private Validator validator;
	/**
	 * 
	* @Title: updateEggToBirdMoney
	* @Description: 鸟蛋转出鸟币余额
	* @return Object
	 */
	@RequestMapping(value="/live/anchor/eggToBirdMoney",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object updateEggToBirdMoney(EggtobirdmoneyRequest eggRequest){
		List<ConstraintViolation> result = validator.validate(eggRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(eggRequest.getApiversion(), eggRequest);
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
		EggtobirdmoneyRequest  eggtobirdmoneyRequest=(EggtobirdmoneyRequest) object;
		return eggtobirdmoneyService.updateEggToBirdMoney(eggtobirdmoneyRequest.getUid(), eggtobirdmoneyRequest.getEggNum());
	}


}
