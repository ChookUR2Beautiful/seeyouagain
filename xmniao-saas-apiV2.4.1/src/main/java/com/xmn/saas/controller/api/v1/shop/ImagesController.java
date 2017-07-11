package com.xmn.saas.controller.api.v1.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.shop.vo.ImagesPutRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.shop.SellerApply;
import com.xmn.saas.entity.shop.SellerDetailed;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.entity.shop.SellerLandMark;
import com.xmn.saas.entity.shop.SellerPic;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.shop.SellerInfoService;

/**
 * 
*      
* 类名称：ImageController   
* 类描述：   商家图片
* 创建人：xiaoxiong   
* 创建时间：2016年9月28日 上午10:32:34   
* 修改人：xiaoxiong   
* 修改时间：2016年9月28日 上午10:32:34   
* 修改备注：   
* @version    
*
 */
@Controller(value ="api-v1-shop-images-controller" )
@RequestMapping("/api/v1/shop/images")
public class ImagesController extends AbstractController{
	
	/**
	 * 日志
	 */
	private Logger log = LoggerFactory.getLogger(ImagesController.class);
	
	
	/**
	 * 店铺信息service
	 */
	@Autowired
	private SellerInfoService sellerInfoService;
	
	
	/**
	 * redis
	 */
	@Autowired
	private RedisService redisService;
	
	/**
	 * 
	 * @Description: 查询商家图片，（如果商家图片为待审核则显示审核中的图片）
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list() throws IOException{
		
	SellerAccount sellerAccount=redisService.getSessionCacheObject(getToken(), SellerAccount.class);
		
	Integer sellerid=sellerAccount.getSellerid();
	
	try {
		/**
		 * 查询是否有修改图片的申请
		 */
		int count = sellerInfoService.querySellerApplyCount(2, sellerid);
		if(count>0){
			SellerApply sellerApply=sellerInfoService.querySellerApplyBySellerid(2,sellerid);
			
			Integer id=sellerApply.getId();	//申请记录ID
			/**
			 * 查询申请审核中的图片（seller_pic_apply）
			 */
			List<SellerPic> picListtemp=sellerInfoService.querySellerPicApplyByid(id);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("status",1);//有待审核的图片信息
			//map.put("picList", picList);
			List<String> picList=new ArrayList<>();
			for(SellerPic pic:picListtemp){
				if(pic.getType()==0){
					map.put("logo",pic.getUrl());
				}
				if(pic.getType()==2){
					map.put("cover",pic.getUrl());
				}
				if(pic.getType()==1){
					
					picList.add(pic.getUrl());
				}
			}
			map.put("picList", picList);
			new Response(ResponseCode.SUCCESS, "成功",map).write(new HashMap<Class<?>, String[]>(){{
				put(Map.class, new String[]{"logo","cover","status","picList"});
			}});
			
			return;
		}else{
			
			/**
			 * 查询商家图片(seller_pic)
			 */
			List<SellerPic> picListTemp=sellerInfoService.querySellerPicBySellerId(sellerid,null);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("status", 0);//没有待审核的图片记录
			List<String> picList=new ArrayList<>();
			for(SellerPic pic:picListTemp){
				if(pic.getUrl()!=null&&!pic.getUrl().equals("null")){
					if(pic.getType()==1){
						map.put("logo",pic.getUrl());
					}
					if(pic.getType()==2){
						map.put("cover",pic.getUrl());
					}
					if(pic.getType()==0){
						
						picList.add(pic.getUrl());
					}
				}
					
			}
			map.put("picList", picList);
			new Response(ResponseCode.SUCCESS, "成功",map).write(new HashMap<Class<?>, String[]>(){{
				put(Map.class, new String[]{"logo","cover","status","picList","id"});
				
			}});
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		new Response(ResponseCode.FAILURE, "失败").write();
	}
	
  }
	/**
	 * 
	 * @Description: 提交图片审核信息
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 * @throws Exception 
	 */
	@RequestMapping("/put")
	@ResponseBody
	public void put(ImagesPutRequest request,BindingResult result) throws Exception{
		/**
		 * 验证参数
		 */
		if(!request.doValidate(result)){
			return ;
		}
		
		try {
			SellerAccount sellerAccount=redisService.getSessionCacheObject(getToken(), SellerAccount.class);
			
			Integer sellerid=sellerAccount.getSellerid();
			
			/**
			 * 查询是否有修改图片的申请
			 */
			int count = sellerInfoService.querySellerApplyCount(2, sellerid);
			if(count>0){
				
				new Response(ResponseCode.HAS_BEEN_SUBMIT, "您已提交图片审核，请您耐心等待").write();
				
			}else{
				
				List<SellerPic> list=request.toSellerPic();
				
				if(list==null||list.size()==0){
					new Response(ResponseCode.DATAERR, "参数提交有误！").write();
				}
				
				sellerInfoService.insertSellerApply(list,sellerid);
				
				new Response(ResponseCode.SUCCESS, "成功").write();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
	
	/**
	 * 
	 * @Description: 提交图片审核信息
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String url ,Integer status) throws IOException{
		if(url==null||status==null){
			new Response(ResponseCode.DATAERR, "图片url和状态不能为空").write();
			return ;
		}
		try {
			
			
			SellerAccount sellerAccount=redisService.getSessionCacheObject(getToken(), SellerAccount.class);
			
			Integer sellerid=sellerAccount.getSellerid();
			if(status==0)
			{	
				int falg=sellerInfoService.deleteSellerPic(sellerid,url);
				if(falg==1){
					new Response(ResponseCode.SUCCESS, "成功").write();
				}else{
					new Response(ResponseCode.FAILURE, "失败").write();
				}	
				return;
			}else
			{
				int falg=sellerInfoService.deleteSellerPicApply(sellerid,url);
				if(falg>0){
					new Response(ResponseCode.SUCCESS, "成功").write();
				}else{
					new Response(ResponseCode.FAILURE, "失败").write();
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.DATAERR, "删除图片失败！").write();
		}
	}
	
}
