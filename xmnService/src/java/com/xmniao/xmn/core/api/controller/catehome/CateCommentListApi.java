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
import com.xmniao.xmn.core.common.request.PageRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateCommentListApi   
* 类描述：   查询评价列表
* 创建人：yezhiyong   
* 创建时间：2016年7月4日 上午10:09:01   
* @version    
*
 */
@Controller
public class CateCommentListApi implements BaseVControlInf {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(CateCommentListApi.class);
		
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
	
	@RequestMapping(value="/cateCommentList",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryCateCommentList(PageRequest pageRequest){
		log.info("queryCateCommentList data:"+pageRequest.toString());
		List<ConstraintViolation> result = validator.validate(pageRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(pageRequest.getApiversion(), pageRequest);
	}
	
	public Object versionOne(Object obj){
		PageRequest pageRequest = (PageRequest) obj;
		return cateHomeService.queryCateCommentList(pageRequest);
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
