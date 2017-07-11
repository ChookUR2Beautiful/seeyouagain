package com.xmn.saas.service.pay;

import com.xmn.saas.entity.common.SellerAccount;

import java.util.Map;

/**
 * create 2016/10/26
 * 支付服务类
 * @author yangQiang
 */

public interface PayService {

    /**
     * 检查支付密码, 如果24小时内3次, 错误输入3次支付密码, "冻结" 该商户
     * @param payPasswd
     * @param sellerAccount
     * @return
     */
    Map<String, Object> checkPayPasswd(String payPasswd, SellerAccount sellerAccount) throws Exception;
}
