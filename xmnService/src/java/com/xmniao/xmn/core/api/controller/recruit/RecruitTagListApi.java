package com.xmniao.xmn.core.api.controller.recruit;

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

import com.alibaba.druid.util.StringUtils;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RecruitTagRequest;
import com.xmniao.xmn.core.recruit.service.RecruitService;

/**
 * 
*    
* 项目名称：saasService   
* 类名称：RecruitTagListApi   
* 类描述：查询所有标签API   
* 创建人：xiaoxiong   
* 创建时间：2016年5月18日 下午2:41:35   
* @version    
*
 */
@Controller
public class RecruitTagListApi implements BaseVControlInf{
	
	/**
	 * 初始日志类
	 */	
	private final Logger log = Logger.getLogger(RecruitTagListApi.class);
	
	/**
	 * 注入service
	 */
	@Autowired
	private RecruitService recruitService;
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="recruitTagList",method = RequestMethod.GET)
	@ResponseBody
	public Object recruitTagList(RecruitTagRequest recruitTagRequest){
		//验证参数
				List<ConstraintViolation> result=validator.validate(recruitTagRequest);
				if(result!=null && result.size()>0){
					log.info("数据提交有问题"+result.get(0).getMessage());
					return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
				}
				//会话令牌
			
					return versionControl(recruitTagRequest.getApiversion(), recruitTagRequest);
					
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionRecruitTagList(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionRecruitTagList(Object object) {
		RecruitTagRequest sellerIdRequest=(RecruitTagRequest) object;		
		return recruitService.queryRecruitTagList(sellerIdRequest);
	}

}
