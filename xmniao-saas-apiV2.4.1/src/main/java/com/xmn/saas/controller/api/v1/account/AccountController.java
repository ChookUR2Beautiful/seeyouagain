package com.xmn.saas.controller.api.v1.account;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Request;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.account.vo.AccountForgetGestureRequest;
import com.xmn.saas.controller.api.v1.account.vo.AccountQuickLoginRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.account.SellerAccountService;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.utils.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@Controller("api-v1-account-controller")
@RequestMapping("/api/v1/account")
public class AccountController extends AbstractController {
    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private SellerAccountService sellerAccountService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public void logout(@Valid Request request, BindingResult result) throws Exception {
        logger.info("【调用登出接口】-【post /api/v1/account/logout】, 参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        try {
            sellerAccountService.logout(getToken());
            new Response(ResponseCode.SUCCESS, "登出成功").write();
        } catch (Exception e) {
            logger.error("用户登出失败!",e);
            new Response(ResponseCode.FAILURE, "登出失败!").write();
            return;
        }


    }

    /**
     * 账户详情接口
     */
    @ResponseBody
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public void detail() throws IOException {
        logger.info("[调用查询账户详情接口] - [post /api/v1/account/detail] 参数:sessionToken="+this.getToken());
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());

        Map<String,Object> result;
        try {
            result = sellerAccountService.getAccountDetail(sellerAccount);
            // 格式化日期
            result.put("sdate", CalendarUtil.getDateString((Date) result.get("sdate"), "yyyy.MM.dd"));
            result.put("edate", CalendarUtil.getDateString((Date) result.get("edate"), "yyyy.MM.dd hh:mm:ss"));
        } catch (Exception e) {
            logger.error("查询账户详情实现异常",e);
            new Response(ResponseCode.FAILURE,"操作失败!").write();
            return;
        }

        new Response(ResponseCode.SUCCESS,"操作成功",result).write();

    }

    /**
     * 快速登录
     * APP在扫描页面的二维码,调用该接口, 用于页面快速登录
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "quick_login",method = RequestMethod.POST)
    public void quickLogin(@Valid AccountQuickLoginRequest request, BindingResult result) throws Exception {
        logger.info("[调用快速登录接口] - [post /api/v1/account/quick_login] 参数:"+request.toString());
        if (!request.doValidate(result)) {
            return;
        }

        try {
            // 快速登录
            sellerAccountService.quickLogin(request.getToken(),request.getType(),this.getToken());
        } catch (SaasException e) {
            new Response(e.getCode(),e.getMessage()).write();
            return;
        } catch (Exception e){
            logger.error("调用快速登录接口发生异常",e);
            new Response(ResponseCode.FAILURE,"操作失败!").write();
            return;
        }

        new Response(ResponseCode.SUCCESS,"登录成功").write();
    }

    /**
     * 忘记手势密码接口, 验证短信验证码
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "forget_gesture",method = RequestMethod.POST)
    public void forgetGesture(@Valid AccountForgetGestureRequest request, BindingResult result) throws Exception {
        logger.info("[调用忘记手势密码接口] - [post /api/v1/account/forget_gesture] 参数:"+request.toString());
        if (!request.doValidate(result)) {
            return;
        }

        String sessionToken = this.getToken();
        String verifyCode = request.getVerifyCode();

        SellerAccount loggedAccount = redisService.getSellerAccount(sessionToken);
        try {
            // 检查短信验证码
            if (sellerAccountService.checkGestureVerifyCode(loggedAccount,verifyCode)) {
                new Response(ResponseCode.SUCCESS,"短信验证码有效!").write();
                return;
            }else {
                new Response(ResponseCode.ACCESSCODE,"短信验证码无效").write();
                return;
            }
        } catch (Exception e) {
            logger.info("调用忘记手势密码接口实现异常",e);
            new Response(ResponseCode.FAILURE,"内部异常!").write();
            return;
        }

    }

    /**
     * 用户同意协议
     */
    @ResponseBody
    @RequestMapping(value = "agreement",method = RequestMethod.POST)
    public void agreement() throws IOException {
        logger.info("[调用同意协议接口] - [post /api/v1/account/agreement] 参数:");
        // 获取登陆的账户
        String sessionToken = this.getToken();
        SellerAccount sellerAccount = redisService.getSellerAccount(sessionToken);

        try {
            sellerAccountService.agreeAgreement(sellerAccount);
        } catch (Exception e) {
            logger.error("调用[用户同意协议接口]出现异常",e);
            new Response(ResponseCode.FAILURE,"系统异常").write();
            return;
        }

        new Response(ResponseCode.SUCCESS,"操作成功!").write();
        return;
    }

}
