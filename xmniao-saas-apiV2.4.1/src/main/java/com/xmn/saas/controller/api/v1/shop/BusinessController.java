package com.xmn.saas.controller.api.v1.shop;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.entity.shop.Business;
import com.xmn.saas.service.shop.SellerInfoService;


/**
 * 
*      
* 类名称：BusinessController   
* 类描述：   商圈controller
* 创建人：xiaoxiong   
* 创建时间：2016年10月11日 下午4:44:57     
*
 */

@Controller(value ="api-v1-shop-Business-controller" )
@RequestMapping("/api/v1/shop/business")
public class BusinessController extends AbstractController{
	
	
	@Autowired
	private SellerInfoService  sellerinfoService;
	
	
	/**
	 * 
	 * @Description: 查询离商家最近的商圈
	 * @author xiaoxiong
	 * @throws IOException 
	 * @date 2016年10月11日
	 * longitude 经度
	 * latitude	 纬度
	 */
	@ResponseBody
	@RequestMapping(value = "/detail")
	public void detail(Double longitude,Double latitude) throws IOException{
		if(longitude==null){
			new Response(ResponseCode.DATAERR, "纬度不能为空").write();
			return ;
		}
		if(latitude==null){
			new Response(ResponseCode.DATAERR, "经度不能为空").write();
			return ;
		}
		
		try {
			Business business=sellerinfoService.queryBusinessBylongAndlat(longitude,latitude);
			
			new Response(ResponseCode.SUCCESS, "成功",business).write(new HashMap<Class<?>, String[]>(){{
				put(Business.class, new String[]{"bid","title"});
			}});
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
		
	}

}
