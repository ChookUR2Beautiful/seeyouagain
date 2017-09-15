package com.xmniao.xmn.core.api.controller.live;

import java.util.List;
import java.util.Properties;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.live.service.AnchorLiveRecordService;


/**
 * 
*      
* 类名称：SellerInfoApi   
* 类描述：   查询商家信息
* 创建人：xiaoxiong   
* 创建时间：2016年10月31日 下午6:11:45     
*
 */
@Controller
@RequestMapping("/live")
public class AlertoApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AlertoApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	
	@Autowired
	private Properties properties;
	
	@ResponseBody
	@RequestMapping("/seller/detail")
	public Object SllerInfoApi(IDRequest request){
		// 日志
		log.info("IDRequest data:" + request.toString());
		// 验证参数
		List<ConstraintViolation> result = validator.validate(request);
		if (result != null && result.size() > 0) {
			log.info("数据有问题：" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据不正确!");
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
		IDRequest request=(IDRequest)object;
		return anchorLiveRecordService.showSellerinfo(request);
	}
	
	/**
	 * 
	 * @Description: 鸟人专享福利接口
	 * @author xiaoxiong
	 * @date 2016年11月2日
	 */
	@RequestMapping("/alert/modal")
	public String modal(){
		return "live/modal";
	}
	
	@RequestMapping("/alert/welfare")
	public String welfare(){
		return "live/welfare";
	}
	
	

}
