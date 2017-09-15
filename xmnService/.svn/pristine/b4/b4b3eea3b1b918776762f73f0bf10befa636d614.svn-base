package com.xmniao.xmn.core.api.controller.live.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.GetBirdBeansListRequest;
import com.xmniao.xmn.core.thrift.ResponseSubList;
import com.xmniao.xmn.core.thrift.ThriftService;


@Controller
@RequestMapping("/live")
public class BirdBeansListApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(BirdBeansListApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private ThriftService thriftService;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private SessionTokenService sessionService;
	
	@RequestMapping("/getBirdBeansList")   
	@ResponseBody
	public Object getBirdBeansList(GetBirdBeansListRequest request){
		log.info("GetBirdBeansListRequest data:" + request.toString());
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题:"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(request.getApiversion(), request);
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

	private Object versionControlOne(Object object) {
		try {
			GetBirdBeansListRequest request = (GetBirdBeansListRequest)object;
			
			String uid = sessionService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("") || uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR,"身份令牌错误或已过期，请重新登入！");
			}
			
			/*调用支付服务获取打赏明细*/
			List<ResponseSubList> list = thriftService.getBirdBeansList(Integer.valueOf(uid), request.getPage(), request.getPageSize(), request.getSdate(), request.getEdate());
			if(list==null){
				list = new ArrayList<>();
			}
			
			MapResponse objResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> resultMap = new HashMap<>();
			resultMap.put("list", list);
			objResponse.setResponse(resultMap);
			return objResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户打赏明细失败");
		}
		return new BaseResponse(ResponseCode.FAILURE, "获取明细记录失败！");
	}

}
