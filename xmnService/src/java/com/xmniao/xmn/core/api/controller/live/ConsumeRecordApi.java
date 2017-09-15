package com.xmniao.xmn.core.api.controller.live;

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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.live.service.UserPayBirdCoinService;


/**
 * 
*      
* 类名称：ConsumeRecordApi   
* 类描述：   查询消费记录
* 创建人：xiaoxiong   
* 创建时间：2016年11月1日 下午5:57:03     
*
 */
@Controller
@RequestMapping("/live")
public class ConsumeRecordApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ConsumeRecordApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UserPayBirdCoinService userPayBirdCoinService;
	
	@ResponseBody
	@RequestMapping("/consumeRecord")
	public Object consumeRecord(PageRequest request){
		// 日志
		log.info("PageRequest data:" + request.toString());
		// 验证参数
		List<ConstraintViolation> result = validator.validate(request);
		if (result != null && result.size() > 0) {
			log.info("数据有问题：" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据不正确!");
		}
		return versionControl(request.getApiversion(), request);
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

	private Object versionOne(Object object) {
		PageRequest request=(PageRequest)object;
		return userPayBirdCoinService.consumeRecord(request);
	}
	
}
