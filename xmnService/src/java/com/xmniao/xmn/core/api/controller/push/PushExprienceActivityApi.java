package com.xmniao.xmn.core.api.controller.push;

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
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.experience.PushExperienceRequest;
import com.xmniao.xmn.core.common.request.live.PushUrsRequest;
import com.xmniao.xmn.core.live.service.PushSingleService;
import com.xmniao.xmn.core.push.service.PushService;


/**
 * 
*      
* 类名称：PushUrsApi   
* 类描述：   用户关注主播开播提醒
* 创建人：xiaoxiong   
* 创建时间：2016年8月29日 下午3:49:51   
* 修改人：xiaoxiong   
* 修改时间：2016年8月29日 下午3:49:51   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping("/experience")
public class PushExprienceActivityApi{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(PushExprienceActivityApi.class);

	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入点播服务
	 */
	@Autowired
	private PushSingleService pushSingleService;
	
		@RequestMapping(value="/pushActivity",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object pushUrs(PushExperienceRequest request){
			//参数
			log.info("PushUrsRequest data:"+request.toString());
			//验证
			List<ConstraintViolation> result = validator.validate(request);
			if(result != null && result.size()>0){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
			}
			return pushSingleService.pushActivityEntroll(request);
		}



}
