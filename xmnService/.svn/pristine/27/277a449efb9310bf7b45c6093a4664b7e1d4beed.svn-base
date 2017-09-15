package com.xmniao.xmn.core.api.controller.common;
import java.util.List;
import java.util.Map;

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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.BannerRequest;
import com.xmniao.xmn.core.common.service.CommonService;


/**
 * 
*      
* 类名称：GetBannerApi   
* 类描述：   获取banner
* 创建人：xiaoxiong   
* 创建时间：2016年9月5日 下午2:00:35   
* 修改人：xiaoxiong   
* 修改时间：2016年9月5日 下午2:00:35   
* 修改备注：   
* @version    
*
 */
@Controller
public class GetBannerApi implements BaseVControlInf{
	
		//接口日志
		private final Logger log = Logger.getLogger(GetBannerApi.class);
		
		@Autowired
		private Validator validator;
		
		/**
	     * 注入redis缓存
	     */
	    @Autowired
	    private SessionTokenService sessionTokenService;
	    
	    /**
	     * 通用Service
	     */
	    @Autowired
	    private CommonService commonService;
	
	    @RequestMapping(value="/getBanner",method={RequestMethod.GET, RequestMethod.POST},produces={"application/json;charset=UTF-8"})
		@ResponseBody
		public Object getBanner(BannerRequest request){
	    	List<ConstraintViolation> result = validator.validate(request);
			if(result.size() >0 && result != null){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new MapResponse(ResponseCode.DATAERR,result.get(0).getMessage());
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

		private Object versionOne(Object object) {
			BannerRequest request = (BannerRequest) object;
			return commonService.getBanner(request);
		}

}
