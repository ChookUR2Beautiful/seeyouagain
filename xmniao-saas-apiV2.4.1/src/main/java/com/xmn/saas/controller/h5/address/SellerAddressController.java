package com.xmn.saas.controller.h5.address;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.h5.activity.AbstractActiviryController;
import com.xmn.saas.controller.h5.address.vo.SellerAddressSaveRequest;
import com.xmn.saas.entity.address.SellerAddress;
import com.xmn.saas.entity.common.Area;
import com.xmn.saas.service.address.SellerAddressService;
import com.xmn.saas.utils.WebUtils;

@Controller(value = "h5-address-controller")
@RequestMapping("/h5/address")
public class SellerAddressController extends AbstractActiviryController{
	@Autowired
	private SellerAddressService sellerAddressService;
	
	private final ObjectMapper MAPPER=new ObjectMapper();
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listInit(@RequestParam(value="sessionToken",required=true)String sessionToken){
		return "address/list-address";
	}
	
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String  inputInit(@RequestParam(value="id",required=false)Integer id,Model model){
		try {
			if(id!=null){
				SellerAddress sellerAddress=sellerAddressService.selectByKey(id,this.getSellerId());
				model.addAttribute("sellerAddress", MAPPER.writeValueAsString(sellerAddress));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "address/input-address";
	}
	
	/**
	 * 
	 * 方法描述：保存商家地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月22日 上午9:46:02   
	 * @param request
	 * @param httpRequest
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public void save(SellerAddressSaveRequest request,HttpServletRequest httpRequest){
		try {
			SellerAddress sellerAddress=request.convertRequestToBean();
			sellerAddress.setSellerid(this.getSellerId());
			sellerAddress.setProvince(sellerAddressService.selectAreaName(sellerAddress.getProvinceId()));
			sellerAddress.setCity(sellerAddressService.selectAreaName(sellerAddress.getCityId()));
			if(sellerAddress.getAreaId()==null){
				//判断有没有区级
				Integer countLastArea = sellerAddressService.countLastArea(sellerAddress.getCityId());
				if(countLastArea>0){
					new Response(500, "地址信息不全").write();
					return;
				}
			}else{
				sellerAddress.setAreaName(sellerAddressService.selectAreaName(sellerAddress.getAreaId()));
			}
			if(sellerAddress.getId()!=null){
				sellerAddressService.update(sellerAddress,this.getSellerId());
			}else{
				sellerAddressService.save(sellerAddress,this.getSellerId());
			}
			Cookie cookie = WebUtils.getCookie(httpRequest, "sessionToken");
			String sessionToken="";
			if(cookie!=null){
				cookie.getValue();
			}
			new Response(ResponseCode.SUCCESS, "添加成功",sessionToken).write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述：获取商家所有地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月22日 上午9:45:43
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public void list(){
		try {
			List<SellerAddress> list = sellerAddressService.list(this.getSellerId());
			new Response(ResponseCode.SUCCESS, "加载成功",list).write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述：查询下级区域信息
	 * 创建人：jianming  
	 * 创建时间：2016年11月22日 上午9:43:17   
	 * @param pid
	 */
	@RequestMapping(value="list_area",method=RequestMethod.GET)
	@ResponseBody
	public void listArea(@RequestParam(value="pid",defaultValue="0")Integer pid){
	    try {
			List<Area> areas = sellerAddressService.listArea(pid);
			new Response(ResponseCode.SUCCESS, "加载成功",areas).write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述：删除地址
	 * 创建人：jianming  
	 * 创建时间：2016年10月18日 下午8:03:40   
	 * @param id
	 */
	@RequestMapping(value="remove",method=RequestMethod.POST)
	@ResponseBody
	public void remove(@RequestParam(value="id",required=true)Integer id){
		try {
			sellerAddressService.remove(id,this.getSellerId());
			new Response(ResponseCode.SUCCESS, "添加成功",this.getCookieToken()).write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * 方法描述：获取商家默认地址
	 * 创建人：jianming  
	 * 创建时间：2016年11月22日 上午9:45:18
	 */
	@RequestMapping(value="get_default",method=RequestMethod.GET)
	@ResponseBody
	public void getDefualt(){
		try {
			SellerAddress sellerAddress=sellerAddressService.getDesfault(this.getSellerId());
			new Response(ResponseCode.SUCCESS, "成功",sellerAddress).write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
