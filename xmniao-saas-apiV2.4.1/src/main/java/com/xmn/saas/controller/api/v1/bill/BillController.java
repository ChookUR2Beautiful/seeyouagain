package com.xmn.saas.controller.api.v1.bill;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.bill.vo.BillDetailRequest;
import com.xmn.saas.controller.api.v1.bill.vo.BillIsNormalRequest;
import com.xmn.saas.controller.api.v1.bill.vo.BillListRequest;
import com.xmn.saas.controller.api.v1.bill.vo.BillValueCardBalanceRequest;
import com.xmn.saas.controller.api.v1.bill.vo.BillValueCardListRequest;
import com.xmn.saas.controller.api.v1.bill.vo.BillValueCardUserDetailRequest;
import com.xmn.saas.controller.api.v1.bill.vo.BillValueCardUserListRequest;
import com.xmn.saas.entity.bill.Bill;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.bill.BillService;
@Controller( value = "api-v1-bill-controller" )
@RequestMapping( "/api/v1/bill" )
public class BillController extends AbstractController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private BillService billService;
    
    @Autowired
    private GlobalConfig config; 

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(BillController.class);

    @ResponseBody
    @RequestMapping( value = "/list" )
    public void list(@Valid BillListRequest request, BindingResult result) throws Exception {
        log.info("【访问账单列表，根据条件（时间、营收、收益、支出）筛选接口】-【 post /api/v1/bill/list】");
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<Object, Object> map = billService.list(request.converToBean(sellerAccount, request));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }
    
    
    
    
    
    @ResponseBody
    @RequestMapping( value = "/detail" )
    public void detail(@Valid BillDetailRequest request, BindingResult result) throws Exception {
        log.info("【访问账单详情接口】-【 post /api/v1/bill/detail】");
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<Object, Object> map = billService.detail(request.converToBean(sellerAccount, request));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;
    }
    
    /**
     * 
     * @Description: 验证消费分账
     * @author xiaoxiong
     * @date 2016年10月14日
     */
    @ResponseBody
    @RequestMapping( value = "/vertify",method=RequestMethod.POST)
    public void vertify(String codeid) throws IOException{
    	
    	if(codeid==null){
    		new Response(ResponseCode.DATAERR, "消费验证码不能为空").write();
    		return;
    	}
    	if(codeid.length()!=4 && codeid.length()!=6){
    		new Response(ResponseCode.DATAERR, "请输入4位数或6位数的验证码！").write();
    		return;
    	}
    	
    	SellerAccount sellerAccount=redisService.getSellerAccount(getToken());
    	
    	int sellerid=sellerAccount.getSellerid();
    	
    	try {
    		if(codeid.length()==4)
    		{
    			Bill bill=billService.selectBillByCodeidAndSellerid(codeid,sellerid);
    			if(bill==null){
    				new Response(ResponseCode.FAILURE,"验证码已验证或输入验证码错误！").write();
    				return ;
    			}
    			billService.vertify(bill.getBid().toString(),bill.getLiveLedger());
    			Map<String,Object> result=billService.billConverMap(bill);
    			
    			new Response(ResponseCode.SUCCESS, "成功",result).write();
    			return;
    		}else
    		{	
    					//新的消费码新的验证方式
        				switch (Integer.parseInt(codeid.substring(0,1))) {
        				
						case 1://美食
							if(billService.virtifyBill(codeid,sellerid))return;
							break;
							
						case 3://商家赠送券
							if(billService.virtifySellercoupon(codeid,sellerid))return;
							break;
						case 4://粉丝券
							if(billService.virtifycoupon(sellerAccount,codeid))return;
							break;
						case 5://储值套餐券
                            if(billService.virtifySellerCard(sellerAccount,codeid))return;
                            break;
							
//						default://爆品
//							if(virtifybargin(codeid,sellerid))return;
//							break;
						}
    		}
    		new Response(ResponseCode.FAILURE, "已验证或输入验证码错误！").write();
    		
		} catch (Exception e) {
			log.error("验证订单或订单分账异常", e);
			new Response(ResponseCode.FAILURE, "错误").write();
		}
    	
    }
	 /**
	 * 访问商户储值卡充值消费详细记录
	 * @param request
	 * @param result
	 * @throws Exception
	 */
	 @ResponseBody
     @RequestMapping( value = "/value_card_list" )
     public void getValueCardList(@Valid BillValueCardListRequest request, BindingResult result) throws Exception {
        log.info("【访问商户储值卡充值消费详细记录接口】-【 post /api/v1/bill/value_card_list】");
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String, Object> map = billService.getValueCardList(request.converToBean(sellerAccount, request));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

     }
	 

     /**
     * 获取商户充值会员列表
     * @param request
     * @param result
     * @throws Exception
     */
     @ResponseBody
     @RequestMapping( value = "/value_card_member_list" )
     public void getUserList(@Valid BillValueCardUserListRequest request, BindingResult result) throws Exception {
        log.info("【访问获取商户充值会员列表接口】-【 post /api/v1/bill/value_card_member_list】");
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String, Object> map = billService.getUserList(request.converToBean(sellerAccount, request));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

     }
     
     /**
      * 获取用户储值卡详细信息
      * @param request
      * @param result
      * @throws Exception
      */
     @ResponseBody
     @RequestMapping( value = "/value_card_member_detail" )
     public void getUserDetail(@Valid BillValueCardUserDetailRequest request, BindingResult result) throws Exception {
        log.info("【访问获取用户储值卡详细信息接口】-【 post /api/v1/bill/value_card_member_detail】");
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String, Object> map = billService.getUserDetail(request.converToBean(sellerAccount));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

     }
     
     
     /**
      * 获取商户累计充值，累计剩余，充值会员
      * @param request
      * @param result
      * @throws Exception
      */
     @ResponseBody
     @RequestMapping( value = "/value_card_balance" )
     public void getValueCardBalance(@Valid BillValueCardBalanceRequest request, BindingResult result) throws Exception {
         log.info("【访问获取商户累计充值，累计剩余，充值会员接口】-【 post /api/v1/bill/value_card_balance】");
         if (!request.doValidate(result)) {
             return;
         }
         SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
         Map<String, Object> map = billService.getValueCardBalance(sellerAccount);
         Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
         response.write();
         return;
         
     }
     
     /**
      * 是否普通商家
      * @param request
      * @param result
      * @throws Exception
      */
     @ResponseBody
     @RequestMapping( value = "/is_normal" )
     public void isNormal(@Valid BillIsNormalRequest request, BindingResult result) throws Exception {
         log.info("【访问是否普通商家接口】-【 post /api/v1/bill/is_normal】");
         if (!request.doValidate(result)) {
             return;
         }
         SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
         Map<Object, Object> map = billService.isNormal(sellerAccount);
         Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
         response.write();
         return;
         
     }
	
}
