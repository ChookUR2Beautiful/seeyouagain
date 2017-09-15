package com.xmniao.xmn.core.api.controller.catehome;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.catehome.service.SpecialTopicService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.catehome.SpecilTopicRequest;
import com.xmniao.xmn.core.market.common.Response;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: SpecilTopicController    
* @Description:专题详情   
* @author: liuzhihao   
* @date: 2017年2月17日 下午7:22:01
 */
@Controller
@RequestMapping("specil/topic")
public class SpecilTopicController {

	
	//注入专题接口
	@Autowired
	private SpecialTopicService specialTopicService;
	
	//注入请求数据验证接口
	@Autowired
	private Validator validator;
	
	/**
	 * 查看专题详情
	 * @return
	 */
	@RequestMapping(value="/info",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object viewSpecilTopicInfo(SpecilTopicRequest specilTopicRequest){
	
		List<ConstraintViolation> request = validator.validate(specilTopicRequest);
		
		if(request != null && !request.isEmpty()){
			String message ="";
			for(ConstraintViolation vo : request){
                message+=vo.getMessage()+",";
            }
			return new Response(ResponseCode.DATAERR, message.substring(0, message.length()-1));
		}
		return specialTopicService.viewSpecialTopicInfo(specilTopicRequest);
	}
}
