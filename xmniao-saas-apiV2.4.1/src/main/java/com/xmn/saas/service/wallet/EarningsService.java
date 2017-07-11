package com.xmn.saas.service.wallet;

import java.util.Map;

import com.xmn.saas.entity.common.SellerAccount;

public interface EarningsService {
    /**
     * 收益
     * 
     * @param sellerAccount
     * @return
     */
    public Map<Object, Object> getBy(SellerAccount sellerAccount);

}
