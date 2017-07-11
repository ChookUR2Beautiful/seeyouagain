package com.xmn.saas.controller.api.v1.shop;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.shop.Account;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.entity.shop.SellerPic;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.shop.SellerInfoService;


/**
 * 
*      
* 类名称：AccountController   
* 类描述：   商家商户详情
* 创建人：xiaoxiong   
* 创建时间：2016年10月12日 上午9:49:31     
*
 */
@Controller(value ="api-v1-shop-account-controller" )
@RequestMapping("/api/v1/shop/account")
public class AccountController extends AbstractController{
	
	
	@Autowired
	private SellerInfoService  sellerinfoService;
	
	@Autowired
	private RedisService redisService;
	
		
	@ResponseBody
	@RequestMapping(value = "/detail")
	public void detail() throws IOException{
			
		SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
		/**
		 * 商家编号
		 */
		int sellerid=sellerAccount.getSellerid();
		/**
		 * 商家帐号ID
		 */
		int aid=sellerAccount.getAid();
		
		try {
			
			Account account=sellerinfoService.queryAccount(sellerid,aid);
			
			if(account==null){
				new Response(ResponseCode.FAILURE, "没有找到商家信息").write();
				return;
			}
			
			new Response(ResponseCode.SUCCESS, "成功",account).write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/home")
	public void home() throws IOException{
			
		SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
		/**
		 * 商家编号
		 */
		int sellerid=sellerAccount.getSellerid();
		
		
		String account=sellerAccount.getAccount();
		try {
			
			SellerInfo sellerInfo=sellerinfoService.querySellerBySellerid(sellerid);
			if(sellerInfo==null){
				new Response(ResponseCode.FAILURE, "没有找到商家信息").write();
				return;
			}
			//商户名称
			String name=sellerInfo.getSellerName()==null?"":sellerInfo.getSellerName();
			
			Map<String,String> reMap=new HashMap<String, String>();
				
			String	phone="";
			try {
				phone=account.substring(0,3)+"****"+account.substring(7,11);
			} catch (Exception e) {
				e.printStackTrace();
			}
			reMap.put("phone",phone);
			reMap.put("name",name);
			String logo="";
			List<SellerPic> picList = sellerinfoService.querySellerPicBySellerId(sellerid,1);
			if(picList!=null && picList.size()>0){
				logo=picList.get(0).getUrl();
			}
			reMap.put("logo",logo);
			new Response(ResponseCode.SUCCESS, "成功",reMap).write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
}
