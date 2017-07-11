package com.xmn.saas.controller.api.v1.wallet;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.wallet.vo.PasswordResetRequest;
import com.xmn.saas.controller.api.v1.wallet.vo.PasswordSetRequest;
import com.xmn.saas.controller.api.v1.wallet.vo.PasswordVerifyRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.wallet.BankApply;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.sms.SmsService;
import com.xmn.saas.service.wallet.BankService;
import com.xmn.saas.service.wallet.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller(value="api-v1-wallet-password-controller" )
@RequestMapping( "/api/v1/wallet/password" )
public class PasswordController extends AbstractController {
    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(PasswordController.class);

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisService redisService;
    
    @Autowired
    private BankService bankService;

    /**
     * 设置支付密码
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/set" )
    public void set(@Valid PasswordSetRequest request, BindingResult result) throws Exception {
        log.info("【访问设置钱包支付密码接口】-【 post /api/v1/wallet/password/set】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());

        switch (request.getType()){
            case 1 :    // 第一次修改密码
                break;
            case 0:     // 不是第一次修改密码
                String oldPassword = request.getOldPassword();
                sellerAccount.setPassword(oldPassword);

                // 验证旧密码是否正确
                Map<Object, Object> map = passwordService.verifyPassword(sellerAccount);
                if (map != null &&
                        !("0".equals(map.get("code")+""))) {    // 如果返回的code不是0,认定就支付密码输入错误
                    new Response(ResponseCode.PARAM_ERROR,"旧密码输入错误!").write();
                    return;
                }
                break;
            default:
                new Response(ResponseCode.PARAM_ERROR,"是否第一修改支付密码(type),只能为 0 / 1").write();
                return;
        }

        Map<Object,Object> map = passwordService.setPassword(request.converToBean(sellerAccount.getSellerid()));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;
    }

    /**
     * 重置支付密码
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/reset" )
    public void reset(@Valid PasswordResetRequest request, BindingResult result) throws Exception {
        log.info("【访问重置钱包支付密码接口】-【 post /api/v1/wallet/password/reset】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        //获取银行卡列表信息
        Map<String,Object> bankList = bankService.list(sellerAccount.getSellerid()+"");
        if(bankList!=null && bankList.get("dataList")!=null){
            @SuppressWarnings( "unchecked" )
            List<BankApply> list  = (List<BankApply>) bankList.get("dataList");
            String account = list.get(0).getBankid();
            String name = list.get(0).getFullname();
            if(!account.equals(request.getBank())){
                Response response = new Response(ResponseCode.FAILURE, "银行卡账号错误！");
                response.write();
                return;
            }
            if(!name.equals(request.getName())){
                Response response = new Response(ResponseCode.FAILURE, "姓名错误！");
                response.write();
                return;
            }
        }else if(bankList==null || bankList.get("dataList")==null){
            Response response = new Response(ResponseCode.FAILURE, "查无此银行卡信息！");
            response.write();
            return;
        }
        
        String smsCode = request.getVerifyCode();
        Map<Object,Object> map;
        String redisCode = smsService.getSmsCode(sellerAccount.getAccount(),SmsService.CODE_TYPE_PAY_PASSWORD);
        if (!smsCode.equals(redisCode)) {
            Response response = new Response(ResponseCode.FAILURE, "验证码错误！");
            response.write();
            return;
        }
        else{
            map = passwordService.resetPassword(request.converToBean(sellerAccount.getSellerid()));

            smsService.deleteSmsCode(sellerAccount.getAccount(),SmsService.CODE_TYPE_PAY_PASSWORD);
            Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
            response.write();
        }
        return;

    }

    /**
     * 验证支付密码
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/verify" )
    public void verify(@Valid PasswordVerifyRequest request, BindingResult result) throws Exception {
        log.info("【访问验证钱包支付密码接口】-【 post /api/v1/wallet/password/verify】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<Object,Object> map =
                passwordService.verifyPassword(request.converToBean(sellerAccount.getSellerid()));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }

}
