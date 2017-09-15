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
import com.xmniao.xmn.core.common.request.live.LiveLedgerRecordRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.service.LiveLedgerRecordService;
import com.xmniao.xmn.core.util.ThriftUtil;

/**
 * 
*    
* 类名称：LiveAnchorRankingListApi   
* 类描述：   查询每天的平台发送红包  
* 创建人：yezhiyong   
* 创建时间：2016年8月16日 下午3:07:00   
* @version    
*
 */
@Controller
public class LiveLedgerRecordApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveLedgerRecordApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 主播|观众通用dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入anchorPersonService
	 */
	@Autowired 
	private LiveLedgerRecordService liveRedpacketBranchService;
	
	@Autowired
	private ThriftUtil thriftUtil;

	/**
	 * 
	* @Title: queryAnchorAnnunciateList
	* @Description: 查询每天的平台发送红包  
	* @return Object    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value = "/live/redpacket/branch" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryRedpacketBranch(LiveLedgerRecordRequest request){
		log.info("提交的数据："+request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(request.getApiversion(),request);
	}

	
	public Object versionOne(Object obj){
		LiveLedgerRecordRequest request = (LiveLedgerRecordRequest)obj;
		return liveRedpacketBranchService.queryLiveRedPacketBranch(request);
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
