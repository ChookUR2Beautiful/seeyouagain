package com.xmniao.xmn.core.api.controller.live.room;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.live.response.BirdCoinRecordResponse;
import com.xmniao.xmn.core.live.service.LiveRoomService;
import com.xmniao.xmn.core.thrift.ThriftService;


/**
 * 
* 类名称：BirdCoinRecordListApi   
* 类描述：   鸟币消费 奖励 请求Api
* 创建人：xiaoxiong   
* 创建时间：2016年12月22日 下午2:14:39
 */

@Controller
@RequestMapping("/live")
public class BirdCoinRecordListApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(BirdCoinRecordListApi.class);
	@Autowired
	private Validator validator;
	
	
	@Autowired
	private LiveRoomService liveRoomService;
	
	@RequestMapping("/birdConRecordList")
	@ResponseBody
	public Object birdConinRecordList(PageTypeRequest request){
		
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
		PageTypeRequest requet = (PageTypeRequest)object;
		return liveRoomService.birdConinRecordList(requet);
	}

}
