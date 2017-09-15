package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.Page;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.service.LiveTrailerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: SellerLiveListApi    
* @Description:直播列表接口   
* @author: liuzhihao   
* @date: 2016年11月29日 下午12:02:57
 */
@RequestMapping("/live")
@Controller
public class SellerLiveListApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(AttentionAnchorMessageListApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private LiveTrailerService liveTrailerService;
	
	/**
	 * 预告直播列表
	 * @param liveTrailerRequest
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object list(Page page){
		//验证提交数据
		List<ConstraintViolation> result = validator.validate(page);
		if(result != null && !result.isEmpty()){
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(page.getApiversion(), page);
	}

	public Object versionOne(Object obj){
		Page page = (Page) obj;
		return liveTrailerService.queryLiveTrailer(page);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default:
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
