package com.xmniao.xmn.core.api.controller.live.room;
import java.util.List;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.MakeHaoService;



/**
 * 
* 类名称：MyMakeHaoApi   
* 类描述：   我的壕赚接口
* 创建人：xiaoxiong   
* 创建时间：2016年12月22日 下午2:53:52
 */
@Controller
@RequestMapping("/live")
public class MyMakeHaoApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(BirdBeansListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private MakeHaoService makeHaoServcie;
	
	@RequestMapping("/myMakeHao")
	@ResponseBody
	public Object myMakeHao(BaseRequest request){
		log.info("GetBirdBeansListRequest data:" + request.toString());
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
		BaseRequest request = (BaseRequest)object;
		
		return makeHaoServcie.myMakeHao(request);
	}
	
	
	
}
