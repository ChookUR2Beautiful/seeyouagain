package com.xmniao.xmn.core.api.controller.vod;

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
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.vod.CreateClassRequest;
import com.xmniao.xmn.core.vod.service.LiveVodService;


/**
 * 
*      
* 类名称：CreateClassApi   
* 类描述： 创建视频分类  
* 创建人：Administrator   
* 创建时间：2016年8月25日 上午9:40:18   
* 修改人：Administrator   
* 修改时间：2016年8月25日 上午9:40:18   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping("/live/vod")
public class CreateClassApi implements BaseVControlInf{
	
		/**
		 * 日志
		 */
		private final Logger log = Logger.getLogger(CreateClassApi.class);
	
		/**
		 * 注入验证
		 */
		@Autowired
		private Validator validator;
		
		/**
		 * 注入点播服务
		 */
		@Autowired
		private LiveVodService liveVodService;
		
		@RequestMapping(value="/CreateClass",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object createClass(CreateClassRequest request){
			//参数
			log.info("request data:"+request.toString());
			//验证
			List<ConstraintViolation> result = validator.validate(request);
			if(result != null && result.size()>0){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
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
		
		public Object versionOne(Object obj){
			CreateClassRequest request = (CreateClassRequest) obj;
			return liveVodService.createClass(request);
		}

}
