package com.xmniao.xmn.core.api.controller.vod;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.vod.AnchorVideoRequest;
import com.xmniao.xmn.core.common.request.vod.CreateRecordRequest;
import com.xmniao.xmn.core.vod.service.LiveVodService;

/**
 * 
*      
* 类名称：AnchorVideoApi   
* 类描述：   查询主播视频列表
* 创建人：xiaoxiong   
* 创建时间：2016年8月30日 下午3:32:25   
* 修改人：xiaoxiong   
* 修改时间：2016年8月30日 下午3:32:25   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping("/vod")
public class AnchorVideoApi implements BaseVControlInf{
	

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
		
		@RequestMapping(value="/queryAnchorVedio",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object queryAnchorVideo(AnchorVideoRequest request){
			//参数
			log.info("AnchorVideoRequest data:"+request.toString());
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
			AnchorVideoRequest request = (AnchorVideoRequest) obj;
			return liveVodService.queryAnchorVedio(request);
		}

}
