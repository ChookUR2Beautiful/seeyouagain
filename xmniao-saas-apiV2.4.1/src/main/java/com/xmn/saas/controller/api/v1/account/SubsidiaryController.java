package com.xmn.saas.controller.api.v1.account;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.account.vo.SubsidiaryPutRequest;
import com.xmn.saas.controller.api.v1.account.vo.SubsidiaryStatuSetRequest;
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

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子账号Controller
 * create 2016/10/08
 *
 * @author yangQiang
 */
@Controller("api-v1-account-subsidiary-controller")
@RequestMapping("api/v1/account/subsidiary")
public class SubsidiaryController extends AbstractController {

    // 初始化日志对象
    private final Logger logger = LoggerFactory.getLogger(SubsidiaryController.class);

    // 注入商户账户服务类
    @Autowired
    private SellerAccountService sellerAccountService;

    // 注入Redis服务类
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public void list() throws IOException {
        String sessionToken = this.getToken();
        logger.info("[调用查询子账号列表接口] - [post api/v1/account/subsidiary/list] 参数 : sessionToken="+sessionToken);

        List<SellerAccount> subAccountList;
        try {
            SellerAccount sellerAccount = redisService.getSellerAccount(sessionToken);
            subAccountList = sellerAccountService.querySubAccountList(sellerAccount);
        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE,e.getMessage()).write();
            return;
        }  catch (Exception e) {
            logger.error("查询子账号列表出现异常",e);
            new Response(ResponseCode.FAILURE,"操作失败!").write();
            return;
        }

        // 封装响应
        Map<String, Object> result = new HashMap<>();
        result.put("size",subAccountList.size());
        result.put("sub",subAccountList);
        new Response(ResponseCode.SUCCESS,"操作成功!",result).write(new HashMap<Class<?>, String[]>(){{
            put(SellerAccount.class,new String[]{"aid","nname","type","account","userstatus"});
        }});

    }

    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public void detail(Integer subAid) throws IOException {
        logger.info("[调用查询子账号详情] - [post api/v1/account/subsidiary/detail] 参数 : subAid=" + subAid);
        if (subAid == null) {
            new Response(ResponseCode.FAILURE, "未上传子账号id").write();
            return;
        }

        SellerAccount subAccount;
        try {
            SellerAccount parentAccount = redisService.getSellerAccount(this.getToken());
            // 查询子账户
            subAccount = sellerAccountService.querySubAccountDetail(parentAccount,subAid);
        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE,e.getMessage()).write();
            return;
        } catch (Exception e){
            logger.error("查询子账户详情发生异常",e);
            new Response(ResponseCode.FAILURE,"查询失败!").write();
            return;
        }

        // 封装响应
        new Response(ResponseCode.SUCCESS, "操作成功!", subAccount).write(new HashMap<Class<?>, String[]>() {{
            put(SellerAccount.class, new String[]{"aid", "nname","userstatus", "fullname", "account", "type","receiveMessage"});
        }});
    }

    // 添加、修改子账户
    @ResponseBody
    @RequestMapping(value = "put", method = RequestMethod.POST)
    public void put(@Valid SubsidiaryPutRequest request, BindingResult result) throws IOException {
        logger.info("[添加/编辑子账号接口] - [post api/v1/account/subsidiary/put] 参数 : " + request.toString());

        try {
            if (!request.doValidate(result)) {
                return;
            }
            SellerAccount sellerAccount = request.converBean();

            // 判断操作类型
            switch (request.getOperation()) {
                case 0:    // 操作类型为 : 添加子账户
                    sellerAccountService.addSubAccount(this.getToken(), sellerAccount);
                    break;
                case 1:    // 操作类型为 : 修改子账户
                    if (sellerAccount.getAid() == null) {
                        new Response(ResponseCode.FAILURE, "未上传子账号id").write();
                        return;
                    }
                    sellerAccountService.updateSubAccount(this.getToken(), sellerAccount);
                    break;
                default:
                    new Response(ResponseCode.FAILURE, "操作类型只能是 0 或 1").write();
                    return;
            }
        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE, e.getMessage()).write();
            return;
        } catch (Exception e) {
            logger.error("添加|修改子账户实现异常", e);
            new Response(ResponseCode.FAILURE, "添加|修改子账号失败!").write();
            return;
        }

        new Response(ResponseCode.SUCCESS, "操作成功!").write();
    }


    /**
     * 修改子账号状态(冻结/解冻子账号)
     * @param request
     * @param result
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "status_set", method = RequestMethod.POST)
    public void statusSet(@Valid SubsidiaryStatuSetRequest request, BindingResult result) throws Exception {
        logger.info("[调用修改子账号状态(冻结/解冻子账号)] - [post api/v1/account/subsidiary/status_set] 参数 : " + request.toString());

        if (!request.doValidate(result)) {
            return;
        }

        try {
            SellerAccount parentAccount = redisService.getSellerAccount(this.getToken());
            SellerAccount subAccount = request.converBean();
            // 修改子账号状态
            sellerAccountService.setSubAccountStatus(parentAccount,subAccount);
        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE,e.getMessage()).write();
            logger.error("修改子账号状态时出现异常!",e);
            return;
        } catch (Exception e){
            logger.error("修改调用子账号状态接口出现异常",e);
            new Response(ResponseCode.FAILURE,"操作失败!").write();
            return;
        }

        new Response(ResponseCode.SUCCESS,"修改成功").write();
    }

}
