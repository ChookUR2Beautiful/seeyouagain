package com.xmniao.xmn.core.api.controller.seller;

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
import com.xmniao.xmn.core.common.request.RemoveBCRecordRequeset;
import com.xmniao.xmn.core.seller.service.UserSellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: RemoveBrowsedApi    
* @Description:删除浏览接口   
* @author: liuzhihao   
* @date: 2016年11月25日 下午6:46:11
 */
@RequestMapping("browse")
@Controller
public class RemoveBrowsedApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(BrowsedSellersApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	//与用户相关
	@Autowired
	private UserSellerService userSellerService;

	@RequestMapping(value="/delete",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object removeBrowsed(RemoveBCRecordRequeset removeBCRecordRequeset){
		log.info("removeBCRecordRequeset data:"+removeBCRecordRequeset.toString());
		List<ConstraintViolation> result = validator.validate(removeBCRecordRequeset);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(removeBCRecordRequeset.getApiversion(), removeBCRecordRequeset);
	}

	public Object versionOne(Object object){
		RemoveBCRecordRequeset request = (RemoveBCRecordRequeset) object;
		int result = userSellerService.removeBrowse(request);
		if(result > 0){
			return new BaseResponse(ResponseCode.SUCCESS, "删除成功");
		}
		return new BaseResponse(ResponseCode.FAILURE, "删除失败");
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
