package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*      
* 类名称：LiveGetClassIdApi   
* 类描述：   查询视频分类
* 创建人：xiaoxiong   
* 创建时间：2016年11月11日 下午4:24:50     
*
 */
@Controller
@RequestMapping("live")
public class LiveGetClassIdApi {
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/getClassID")
	@ResponseBody
	public Object getClassid(BaseRequest request){
		try {
			
			String classID = propertiesUtil.getValue("live.classID", "conf_config.properties");
			Map<Object,Object> map=new HashMap<Object, Object>();
			map.put("classID", classID);
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(map);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
	}

}
