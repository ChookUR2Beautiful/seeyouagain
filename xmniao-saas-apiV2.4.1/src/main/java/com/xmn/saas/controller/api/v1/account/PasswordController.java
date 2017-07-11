package com.xmn.saas.controller.api.v1.account;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.account.vo.PasswordUpdateRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.account.SellerAccountService;
import com.xmn.saas.service.base.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 账户-密码Controller
 */
@Controller("api-v1-account-password-controller")
@RequestMapping("/api/v1/account/password")
public class PasswordController extends AbstractController{

    private final Logger logger = LoggerFactory.getLogger(PasswordController.class);

    // 注入商户账户服务类
    @Autowired
    private SellerAccountService sellerAccountService;

    // 注入Redis服务类
    @Autowired
    private RedisService redisService;

    /**
     * 根据原密码修改账户密码
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void update(PasswordUpdateRequest request, BindingResult result) throws Exception {
        logger.info("[调用修改密码接口]-[post /api/v1/account/password], 参数 : "+ request.toString());
        if (!request.doValidate(result)) {
            return;
        }

        try {
            // 从Redis中获取账号信息
            SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
            // 修改密码
            sellerAccountService.updatePassword(sellerAccount,request.getOldPassword(),request.getNewPassword());
            new Response(ResponseCode.SUCCESS,"密码修改成功!").write();

        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE,e.getMessage()).write();
        } catch (Exception e) {
            logger.error("修改密码发生异常",e);
            new Response(ResponseCode.FAILURE,"失败!").write();
        }
    }

}
