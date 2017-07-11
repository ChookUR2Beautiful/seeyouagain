package com.xmn.saas.controller.api.v1.shop;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.shop.vo.DishesCreateRequest;
import com.xmn.saas.controller.api.v1.shop.vo.DishesModifyRequest;
import com.xmn.saas.controller.api.v1.shop.vo.SaveOrUpdateRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.shop.Dishes;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.shop.DishesService;
@Controller(value ="api-v1-shop-dishes-controller" )
@RequestMapping("/api/v1/shop/dishes")
public class DishesController extends AbstractController{
	
	
	/**
	 * 注入店铺service
	 */
	@Autowired
	private DishesService dishesService;
	
	/**
	 * 注入操作rids封装类
	 */
	@Autowired
	private RedisService redisService;
	
	
	/**
	 * 
	 * @Description: 查询推荐菜列表
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public void list() throws Exception{
		
		SellerAccount sellerAccount=redisService.getSessionCacheObject(getToken(), SellerAccount.class);
		Integer sellerid=sellerAccount.getSellerid();
		
		try {
			
			List<Dishes> list=dishesService.queryFoodList(sellerid);
			
			new Response(ResponseCode.SUCCESS, "成功",list).write(new HashMap<Class<?>, String[]>(){{
				put(Dishes.class, new String[]{"id","url"});
				
				
			}});
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
	
	/**
	 * 
	 * @Description: 查询推荐菜详情
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/detail")
	public void detail(Integer id) throws IOException{
		/**
		 * 推荐菜ID
		 */
		if(id==null){
			new Response(ResponseCode.FAILURE,"推荐菜ID不能为空").write();
			return ;
		}
		
		try {
			
			Dishes dishes=dishesService.queryFoodById(id);
			new Response(ResponseCode.SUCCESS,"成功",dishes).write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
		
	}
	
	/***
	 * 
	 * @Description: 新增推荐菜
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 */
	@ResponseBody
	@RequestMapping(value = "/create",method=RequestMethod.POST)
	public void create(@Valid DishesCreateRequest request,BindingResult result) throws Exception{
		/**
		 * 验证参数
		 */
		if(!request.doValidate(result)){
			return; 
		}
		
		SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
		
		Integer sellerid=sellerAccount.getSellerid();
		
		try {
			/**
			 * 添加，返回推荐菜Id
			 */
			Integer id = dishesService.create(request.converToDishes(sellerid));
			
			Map<String,Integer> map=new HashMap<String, Integer>();
			map.put("id", id);
			
			new Response(ResponseCode.SUCCESS,"成功",map).write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE,"错误").write();
		}
		
	}
	
	/**
	 * 
	 * @Description: 修改推荐菜
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/modify",method=RequestMethod.POST)
	public void modify(@Valid DishesModifyRequest request,BindingResult result) throws Exception{
		
		if(!request.doValidate(result)){
			return ;
		}
		
		try {
			
			dishesService.modify(request.converToDishes());
			
			new Response(ResponseCode.SUCCESS,"成功").write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE,"错误").write();
		}
	}
	
	/**
	 * 
	 * @Description: 新增或这修改
	 * @author xiaoxiong
	 * @date 2016年10月18日
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
	public void saveOrUpdate(@Valid SaveOrUpdateRequest request,BindingResult result) throws Exception{
		try {
			SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
			
			Integer sellerid=sellerAccount.getSellerid();
			
			if(request.getId()==null){
				
				dishesService.create(request.converToDishes(sellerid));
				
			}else{
				
				dishesService.modify(request.converToDishes(sellerid));
			}
			
			new Response(ResponseCode.SUCCESS,"成功").write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE,"错误").write();
		}
	}

}
