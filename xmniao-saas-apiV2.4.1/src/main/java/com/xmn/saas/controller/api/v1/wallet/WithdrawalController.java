package com.xmn.saas.controller.api.v1.wallet;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.wallet.vo.WithdrawalApplyRequest;
import com.xmn.saas.controller.api.v1.wallet.vo.WithdrawalListRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.wallet.WithdrawalService;

@Controller( value = "api-v1-wallet-withdrawal-controller" )
@RequestMapping( "/api/v1/wallet/withdrawal" )
public class WithdrawalController extends AbstractController {

    /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(WithdrawalController.class);


    @Autowired
    private RedisService redisService;

    @Autowired
    private WithdrawalService withdrawalService;


    /**
     * 总店提现授权
     * 
     * @param operatingOut
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/accredit" )
    public void accredit(@RequestParam Integer operatingOut) throws Exception {
        log.info("【访问总店提现授权接口】-【 post /api/v1/wallet/withdrawal/accredit】");
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<Object, Object> map =
                withdrawalService.accredit(operatingOut, sellerAccount.getSellerid());
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }



    /**
     * 提现申请
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/apply" )
    public void apply(@Valid WithdrawalApplyRequest request, BindingResult result) throws Exception {
        log.info("【访问提现申请接口】-【 post /api/v1/wallet/withdrawal/apply】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        if(!withdrawalService.isonline(sellerAccount.getSellerid(), request.getType())){
            new Response(ResponseCode.FAILURE, "该商户已下线，您无权限操作").write();
            return; 
        }
        
        Map<String, Object> rmap =withdrawalService.isCanDraw(sellerAccount.getSellerid(), request.getType());
        boolean ischain = rmap.get("isChain") == null ? false : true;
        double amountlimit = Double.parseDouble(String.valueOf(rmap.get("amountlimit")));

        // 判断提现商户是否是连锁店 (连锁店提现有限制)
        if (ischain) {
            // 金额限制
            if (request.getAmount() <= amountlimit) {
                    try {
                        Map<Object, Object> map =withdrawalService.apply(request.getAmount(), request.getType(),sellerAccount.getSellerid(), sellerAccount.getFullname());
                        new Response(ResponseCode.SUCCESS, "请求成功", map).write();
                    } catch (SaasException e) {
                        new Response(ResponseCode.FAILURE, e.getMessage()).write();
                    }
            }else {
                new Response(ResponseCode.FAILURE, "您本次提现金额不能超银行限额" + amountlimit
                        + "元，请调整金额后重试或致电寻蜜鸟客服！").write();;
            }

        } else {
            new Response(ResponseCode.FAILURE, "该功能已授权总店操作，您无权限操作").write();
            return;
        }
    }

    /**
     * 提现申请列表
     * 
     * @param request
     * @param result
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping( value = "/list" )
    public void list(@Valid WithdrawalListRequest request, BindingResult result) throws Exception {
        log.info("【访问提现申请列表接口】-【 post /api/v1/wallet/withdrawal/list】，参数 : " + request.toString());
        if (!request.doValidate(result)) {
            return;
        }
        SellerAccount sellerAccount = redisService.getSellerAccount(this.getToken());
        Map<Object, Object> map = withdrawalService.list(request.converToBean(sellerAccount));
        Response response = new Response(ResponseCode.SUCCESS, "请求成功", map);
        response.write();
        return;

    }


}
