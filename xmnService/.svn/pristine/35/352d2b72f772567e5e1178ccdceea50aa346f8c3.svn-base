package com.xmniao.xmn.core.api.controller.live.room;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.integral.IntegralBillListApi;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.integral.service.BillFreshService;
import com.xmniao.xmn.core.live.response.BirdCurrencyStatisResponse;
import com.xmniao.xmn.core.live.service.LiveAnchorRoomService;
import com.xmniao.xmn.core.live.service.LiveRoomService;


@Controller
@RequestMapping("/live")
public class BirdCurrencyStatisApi implements BaseVControlInf{
	

	private final Logger log = Logger.getLogger(BirdCurrencyStatisApi.class);
	
	//注入service
	@Autowired
	private BillFreshService billFreshService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@Autowired
	private LiveRoomService liveRoomService;
	
	@ResponseBody
	@RequestMapping("/birdCurrencyStatis")
	public Object birdCurrencyStatis(BaseRequest request){
		log.info("PageTypeRequest data:" + request.toString());
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
		BaseRequest request =(BaseRequest)object;
		return liveRoomService.birdCurrencyStatis(request);
		
	}
	

}
