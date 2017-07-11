package com.xmn.saas.service.wallet;

import java.util.Map;

import com.xmn.saas.controller.api.v1.wallet.vo.PasswordResetRequest;
import com.xmn.saas.controller.api.v1.wallet.vo.PasswordSetRequest;
import com.xmn.saas.entity.common.SellerAccount;


public interface PasswordService {
    /**
     * 设置钱包支付密码
     * 
     * @param passwordSetRequest
     * @return
     */
    public Map<Object, Object> setPassword(SellerAccount account);

    /**
     * 重置钱包支付密码
     * 
     * @param passwordResetRequest
     * @return
     */
    public Map<Object, Object> resetPassword(SellerAccount account);

    /**
     * 验证钱包支付密码
     * 
     * @param passwordResetRequest
     * @return
     */
    public Map<Object, Object> verifyPassword(SellerAccount account);


}
