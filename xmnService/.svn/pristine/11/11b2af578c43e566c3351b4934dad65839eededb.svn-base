package com.xmniao.xmn.core.api.controller.live.room;
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
import com.xmniao.xmn.core.common.request.live.MyMakeHaoFriendRequest;
import com.xmniao.xmn.core.live.service.MakeHaoService;


/**
 * 我的壕友
* 类名称：MyMakeHaoFriendApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年12月24日 下午1:18:27
 */
@Controller
@RequestMapping("/live")
public class MyMakeHaoFriendApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(MyMakeHaoFriendApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private MakeHaoService makeHaoService;
	
	@RequestMapping("/myMakeHaoFriend")
	@ResponseBody
	public Object myMakeHaoFriend(MyMakeHaoFriendRequest request){                               
		log.info("MyMakeHaoFriendRequest data:" + request.toString());
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
		MyMakeHaoFriendRequest request = (MyMakeHaoFriendRequest)object;
		return makeHaoService.myMakeHaoFriend(request);
	}

}
