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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.AnchorManagerInfoRequest;
import com.xmniao.xmn.core.live.service.LiveAnchorRoomService;
import com.xmniao.xmn.core.live.service.LiveUserService;



/**
 * 
*      
* 类名称：MyLevelApi   
* 类描述：   我的等级
* 创建人：xiaoxiong   
* 创建时间：2016年8月29日 上午10:35:19   
* 修改人：xiaoxiong   
* 修改时间：2016年8月29日 上午10:35:19   
* 修改备注：   
* @version    
*
 */

@Controller
@RequestMapping("/live")
public class MyLevelApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(MyLevelApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 用户/主播进入房间service
	 * */
	@Autowired
	private LiveUserService liveUserService;
	
	@RequestMapping(value = "/myLevel" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryMyLevel(BaseRequest request){
		List<ConstraintViolation> result = validator.validate(request);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}else {
			return versionControl(request.getApiversion(),request);
		}
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
		BaseRequest request = (BaseRequest)object;
		return liveUserService.queryMyLevel(request);
	}

}
