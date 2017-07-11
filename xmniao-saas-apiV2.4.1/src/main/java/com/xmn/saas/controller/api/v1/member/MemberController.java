package com.xmn.saas.controller.api.v1.member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.controller.api.v1.member.vo.MemberListRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.member.MemberBill;
import com.xmn.saas.entity.member.MemberDetail;
import com.xmn.saas.entity.member.MemberList;
import com.xmn.saas.entity.member.MemberStatistics;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.member.MemberService;

@Controller(value ="api-v1-member-controller" )
@RequestMapping("/api/v1/member")
public class MemberController extends AbstractController{
	
	
	
	@Autowired
	private MemberService memberService;
	
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private GlobalConfig config;
	
	
	/**
	 * 
	 * @Description: 查询会员列表
	 * @author xiaoxiong
	 * @date 2016年9月28日
	 * @version
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public void list(@Valid MemberListRequest request,BindingResult result) throws Exception{
		
		/**
		 * 验证参数
		 */
		if(!request.doValidate(result)){
			return ;
		}
		
		SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
		Integer sellerid=sellerAccount.getSellerid();
		
		try {
	
			List<MemberList> memberList=memberService.list(request.toMemberListParams(sellerid));
			
			new Response(ResponseCode.SUCCESS, "成功",memberList).write(new HashMap<Class<?>, String[]>(){{
				put(MemberList.class, new String[]{"sdate","count","weekDay","memberBillList"});
				put(MemberBill.class, new String[]{"name","isBind","uid","count","avatar"});
			}});
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
	
	/**
	 * 
	 * @Description: 查询会员详细信息
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/detail")
	public void detail(Integer uid) throws IOException{
		
		if(uid==null){
			new Response(ResponseCode.DATAERR,"用户ID不能为空").write();
			return ;
		}
		
		SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
		
		Integer sellerid=sellerAccount.getSellerid();
		
		/**
		 * 调用查询会员信息接口
		 */
		Map<String,String> userMap=null;
		try {
			
			ResponseData responseDate=memberService.getThriftUserInfo(uid);
			/**
			 * 如果responseDate返回NULL表示调用接口出错
			 */
			if(responseDate==null){
				new Response(ResponseCode.THRIFT_FAIL,"调用获取用户信息接口出错！").write();
				return ;
			}
			/**
			 * 0表示调用成功，其他调用失败，不继续执行，返回错误信息
			 */
			if(responseDate.getState()==0){
				userMap=responseDate.getResultMap();
				
			}else{
				new Response(ResponseCode.FAILURE, responseDate.getMsg()).write();
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE,"调用接口失败");
			return;
		}
		
		try {
			/**
			 * 统计会员信息，最搞消费，最低消费，消费次数
			 */
			MemberDetail memberDetail=memberService.detail(uid,sellerid);
			
			memberDetail.setAvatar(userMap.get("avatar")==null?"":config.getImageHost()+userMap.get("avatar"));//头像
			
			memberDetail.setName(userMap.get("nname")==null?"":userMap.get("nname"));	//昵称
			String phone=userMap.get("phone")==null?"":userMap.get("phone");
			
			//判断是否是绑定会员
			if(userMap.get("genussellerid")!=null){
				if(userMap.get("genussellerid").equals(sellerid.toString())){
					memberDetail.setIsBind(1);
					memberDetail.setPhone(phone);	//手机号码
				}else{
					memberDetail.setIsBind(0);
					
					if(phone!=null&&phone.length()>3){
						memberDetail.setPhone(phone.substring(0,3)+"****"+phone.substring(7,phone.length()));	//手机号码
					}
					
				}
			}else{
				memberDetail.setIsBind(0);
				if(phone!=null&&phone.length()>3){
					memberDetail.setPhone(phone.substring(0,3)+"****"+phone.substring(7,phone.length()));	//手机号码
				}
			}
			
			new Response(ResponseCode.SUCCESS,"成功",memberDetail).write();;
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.DATAERR,"错误").write();
		}
	}
	
	/**
	 * 
	 * @Description: 会员统计
	 * @author xiaoxiong
	 * @date 2016年9月29日
	 * @version
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/statistics")
	public void statistics(Integer userType,Integer searchType,String sdate,String edate) throws IOException{
		try {
			
			if(searchType==null){
				searchType=0;
			}
			
			SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
			int sellerid=sellerAccount.getSellerid();
			
			MemberStatistics statics=memberService.statistics(userType,searchType,sellerid,sdate,edate);
			if(searchType==0){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
				statics.setSdate(sdf.format(sellerAccount.getSdate()));
				statics.setEdate(sdf.format(new Date()));
			}
			
			new Response(ResponseCode.SUCCESS, "成功",statics).write();
			
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
	}
	
	public static void main(String[] args) {
		String phone="18565030708";
		
	}

}
