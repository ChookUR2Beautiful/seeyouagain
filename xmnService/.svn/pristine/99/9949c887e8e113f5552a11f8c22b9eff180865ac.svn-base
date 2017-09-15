package com.xmniao.xmn.core.xmer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmniao.xmn.core.recruit.dao.UserDao;

/**
 * 
* 项目名称：xmnService   
* 类名称：PerfectInformationController   
* 类描述：寻蜜客分享注册   
* 创建人：liuzhihao   
* 创建时间：2016年6月6日 下午9:13:41   
* @version    
*
 */
	@Controller
	public class PerfectInformationController{
		//注入用户service
		@Autowired
		private UserDao userDao;;
		
		@RequestMapping(value="perfectInformation")
		public String perfectInformation(Integer id){
			return "signprogress/perfectInformation";
		}
	
	@RequestMapping(value="validephone")
	public String getPhoneCode(Model model,String uid){
		//通过uid查询用户信息
		if(!StringUtils.isEmpty(uid)){
			try{
				Map<Object,Object> map = userDao.selectUsrName(Integer.valueOf(uid));
				if(map==null){
					model.addAttribute("info", "没有找到推荐用户信息！");
					return "pay/error";
				}
				//转换格式
				String name = map.get("name").toString();//推荐人姓名
				String phone = map.get("phone").toString();//推荐人电话
				StringBuffer sb1 = new StringBuffer(name);
				name = sb1.replace(0, 1, "*").toString();
				StringBuffer sb2 = new StringBuffer(phone);
				phone = sb2.replace(3, phone.length()-3, "****").toString();
				
				//清空map
				map.clear();
				map.put("name", name);
				map.put("phone", phone);
				model.addAttribute("data", map);
			}catch(Exception e){
				e.printStackTrace();
			}
//			return "share/validephone";
			return "share/validephone361";
		}else{
			model.addAttribute("info", "推荐人uid不能为空！");
			return "pay/error";
		}
		
	}
	
	@RequestMapping(value="invitefriend")
	public String inviteFriend(){
		return "share/invitefriend";
	}
	
	@RequestMapping(value="paysuccess")
	public String paySuccess(){
		return "pay/paysuccess";
	}
	
	@RequestMapping(value="signpayfail")
	public String payFail(){
		return "signprogress/signpayfail";
	}
}
