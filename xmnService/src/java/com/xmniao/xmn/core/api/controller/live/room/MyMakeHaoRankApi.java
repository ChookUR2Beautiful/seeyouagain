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
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.live.service.MakeHaoService;


/**
 * 
* 类名称：MyMakeHaoRankApi   
* 类描述：   我的壕赚排名
* 创建人：xiaoxiong   
* 创建时间：2016年12月22日 下午4:07:49
 */
@RequestMapping("/live")
@Controller
public class MyMakeHaoRankApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(MyMakeHaoFriendApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private MakeHaoService makeHaoService;
	
	@RequestMapping("/myMakeHaoRank")
	@ResponseBody
	public Object myMakeHaoRank(PageRequest request){
		log.info("PageRequest data:" + request.toString());
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
		PageRequest request = (PageRequest)object;
		return makeHaoService.makeHaoRank(request);
	}

}
