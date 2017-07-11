package com.xmn.saas.controller.api.v1.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.service.common.AreaService;


/**
 *    
* 类名称：AreaController   
* 类描述：   地区
* 创建人：xiaoxiong   
* 创建时间：2016年10月17日 下午5:44:27     
*
 */
@Controller("api-v1-common-area")
@RequestMapping("/api/v1/common/area")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	
	@ResponseBody
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public void list() throws IOException{
		
		try {
			
			Object areaList=areaService.list();
			
			new Response(ResponseCode.SUCCESS, "成功",areaList).write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
}
