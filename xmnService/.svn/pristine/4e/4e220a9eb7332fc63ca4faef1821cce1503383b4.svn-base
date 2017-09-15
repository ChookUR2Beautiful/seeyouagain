package com.xmniao.xmn.core.api.controller.live.room;

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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.PropertiesUtil;


@RequestMapping("/live")
@Controller
public class MakeHaoExplainApi implements BaseVControlInf{
	
	private final Logger log = Logger.getLogger(MakeHaoExplainApi.class);
	@Autowired
	private Validator validator;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/makeHaoExplain")
	@ResponseBody
	public Object makeHaoExplain(BaseRequest request){
		log.info("BaseRequest data:" + request.toString());
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
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS,"成功");
			
			String title = propertiesUtil.getValue("makeHao.explanin.title","conf_common.properties");
			String content = propertiesUtil.getValue("makeHao.explanin.content","conf_common.properties");
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("title", title);
			map.put("content", content);
			mapResponse.setResponse(map);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"获取预计奖励鸟币规则失败！");
	}

}
