package com.xmniao.xmn.core.match.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.match.request.StarMatchPageRequest;
import com.xmniao.xmn.core.match.service.StarMatchService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**报名页面
 * @author wdh
 * @date 2017年7月28日
 * @decription StarMatchPage
 */
@Controller
public class StarMatchPage implements BaseVControlInf{

	private final Logger log = Logger.getLogger(BaseVControlInf.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private StarMatchService starMatchService;
	
	@RequestMapping(value="/starMatchPage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object StartMatchPage(StarMatchPageRequest request) {
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}	
		return versionControl(request.getApiversion(), request);
	}	
	@Override
	public Object versionControl(int v, Object object) {
		StarMatchPageRequest request = (StarMatchPageRequest) object;
		switch(v){
		case 1:
			return starMatchService.startMatchPage(request);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}



}
