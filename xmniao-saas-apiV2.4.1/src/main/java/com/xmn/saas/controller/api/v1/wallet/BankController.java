package com.xmn.saas.controller.api.v1.wallet;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.wallet.vo.BankPutRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.wallet.BankList;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.sms.SmsService;
import com.xmn.saas.service.wallet.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller( value = "api-v1-wallet-bank-controller" )
@RequestMapping( "/api/v1/wallet/bank" )
public class BankController extends AbstractController {

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(BankController.class);

    @Autowired
    private BankService bankService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsService smsService;


    /**
     * 绑定/修改银行卡
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/put" )
    public void put(@Valid BankPutRequest request, BindingResult result) throws Exception {
        log.info("【访问绑定/修改银行卡接口】-【 post /api/v1/wallet/bank/put】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());




        // 用户上传的短信验证码
        String verifyCode = request.getVerifyCode();
        // 从Redis中获取缓存的短信验证码
        String smsCode = smsService.getSmsCode(request.getBankPhone(), SmsService.CODE_TYPE_BINDING_BANK_CARD);
        if (verifyCode.equals(smsCode)) {
            // 通过短信验证码校验, 删除验证码
            smsService.deleteSmsCode(request.getBankPhone(), SmsService.CODE_TYPE_BINDING_BANK_CARD);
        }else { // 输入的短信验证码无效
            new Response(ResponseCode.PARAM_ERROR,"短信验证码无效!").write();
            return;
        }

        Map<Object, Object> map =
                bankService.put(request.converToBean(sellerAccount.getSellerid()));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();


//        new Response(ResponseCode.SUCCESS,"操作成功!").write();
        return;

    }

    /**
     * 查询银行卡列表
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/list" )
    public void list() throws Exception {
        log.info("【访问银行卡列表接口】-【 post /api/v1/wallet/bank/list】");
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<String,Object> resultMap = bankService.list(sellerAccount.getSellerid() + "");
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", resultMap);
        response.write();
        return;

    }
    
    
    /**
     * 删除银行卡
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/delete" )
    public void delete(@RequestParam String id) throws Exception {
        log.info("【访问银行卡删除接口】-【 post /api/v1/wallet/bank/delete】");
        Map<Object,Object> resultMap = bankService.delete(id);
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", resultMap);
        response.write();
        return;

    }
    
    /**
     * 查询所有银行列表
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/bankList" )
    public void bankList() throws Exception {
        try {
			List<BankList> bankList=bankService.bankList();
			new Response(ResponseCode.SUCCESS, "成功",bankList).write();
		} catch (Exception e) {
			e.printStackTrace();
			new Response(ResponseCode.FAILURE, "错误").write();
		}
      
        return;
}

}
