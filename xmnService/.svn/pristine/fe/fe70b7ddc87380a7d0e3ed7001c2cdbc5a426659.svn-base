/**
 * 2016年8月11日 下午1:35:53
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
import com.xmniao.xmn.core.common.request.AnchorSendRequest;
import com.xmniao.xmn.core.live.service.AnchorPersonService;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：AnchorSendApi
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月11日 下午1:35:53
 * @version
 */
@Controller
public class RecordsListApi implements BaseVControlInf {

	
	//日志
	private final Logger log = Logger.getLogger(RecordsListApi.class);
	//注入service
	@Autowired
	private AnchorPersonService anchorpersonService;
	//注入validator
	@Autowired
	private Validator validator;

	/**
	 * 
	* @Title: getAnchorRecord
	* @Description:根据uid及时间查询直播用户当月直播记录 
	* @return Object
	 */
	//TODO 有同名
	@RequestMapping(value="/live/anchor/records2",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getAnchorRecord(AnchorSendRequest asRequest){
		log.info("AnchorSendRequest Data:"+asRequest.toString());

		List<ConstraintViolation> result = validator.validate(asRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(asRequest.getApiversion(), asRequest);
	}
	
	private Object versionControlOne(Object object) {
		AnchorSendRequest  anchorsendRequest=(AnchorSendRequest) object;
		return anchorpersonService.queryLiveAchorRecord(anchorsendRequest.getUid(), anchorsendRequest.getMonth(), anchorsendRequest.getPage(),anchorsendRequest.getYear());
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	
	

}
