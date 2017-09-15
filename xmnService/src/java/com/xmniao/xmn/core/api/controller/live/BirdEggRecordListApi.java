/**
 * 2016年8月18日 上午10:52:15
 */
package com.xmniao.xmn.core.api.controller.live;

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
import com.xmniao.xmn.core.common.request.live.BlackAnchorRequest;
import com.xmniao.xmn.core.live.service.AnchorPersonService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：BirdEggRecordListApi
 * @类描述：鸟蛋明细接口
 * @创建人： yeyu
 * @创建时间 2016年8月18日 上午10:52:15
 * @version
 */
@Controller
public class BirdEggRecordListApi implements BaseVControlInf {

	//日志
	private final Logger log = Logger.getLogger(BirdEggRecordListApi.class);
	//注入service
	@Autowired
	private AnchorPersonService anchorpersonService;
	//注入validator
	@Autowired
	private Validator validator;
	
	
	/**
	 * 
	* @Title: queryEggMoneyDetail
	* @Description: 查询鸟蛋消费明细
	* @return Object
	 */
	@RequestMapping(value="/live/anchor/birdEggRecords",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryEggMoneyDetail(BlackAnchorRequest baRequest){
		log.info("BlackAnchorRequest Data:"+baRequest.toString());

		List<ConstraintViolation> result = validator.validate(baRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		 	return versionControl(baRequest.getApiversion(), baRequest);

	}
	
	
	private Object versionControlOne(Object object) {
		BlackAnchorRequest  baRequest=(BlackAnchorRequest) object;
		return anchorpersonService.queryEggMoneyDetail(baRequest.getUid(), baRequest.getPage());
	}
	
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
