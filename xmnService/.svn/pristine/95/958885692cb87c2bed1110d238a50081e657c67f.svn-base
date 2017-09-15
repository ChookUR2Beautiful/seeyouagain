package com.xmniao.xmn.core.api.controller.catehome;

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
import com.xmniao.xmn.core.catehome.service.CateHomeService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.CateCommentRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateCommentApi   
* 类描述：   发表评论Api
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 上午11:01:58   
* @version    
*
 */
@Controller
public class CateCommentApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(CateCommentApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private CateHomeService cateHomeService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/cateComment",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object insertCateComment(CateCommentRequest cateCommentRequest){
		//验证参数
		log.info("cateCommentRequest data:"+cateCommentRequest.toString());
		List<ConstraintViolation> result = validator.validate(cateCommentRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(cateCommentRequest.getApiversion(), cateCommentRequest);
	}
	
	public Object versionOne(Object obj){
		
		CateCommentRequest cateCommentRequest = (CateCommentRequest) obj;
		return cateHomeService.insertCateComment(cateCommentRequest);
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

}
