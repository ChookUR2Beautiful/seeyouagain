package com.xmniao.xmn.core.api.controller.xmer;

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
import com.xmniao.xmn.core.common.request.TopicalRankRequest;
import com.xmniao.xmn.core.xmer.service.TopicalRankService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TopicalRankApi   
* 类描述：   热点人物排行列表接口
* 创建人：yezhiyong   
* 创建时间：2016年5月24日 上午10:40:07   
* @version    
*
 */
@Controller
public class TopicalRankListApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SaasPackageListApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private TopicalRankService topicalRankService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/topicalRank",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryTopicalRankList(TopicalRankRequest topicalRankRequest){
		log.info("queryTopicalRank data:"+topicalRankRequest.toString());
		List<ConstraintViolation> result = validator.validate(topicalRankRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(topicalRankRequest.getApiversion(), topicalRankRequest);
	}
	
	public Object versionOne(Object obj){
		TopicalRankRequest topicalRankRequest = (TopicalRankRequest) obj;
		return topicalRankService.queryNewTopicalRankList(topicalRankRequest);
//		return topicalRankService.queryTopicalRankList(topicalRankRequest);
		
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
