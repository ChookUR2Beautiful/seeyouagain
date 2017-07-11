package com.xmn.saas.service.wallet;

import java.util.Map;

import com.xmn.saas.entity.common.SellerAccount;


public interface WalletService {
    /**
     * 钱包余额
     * 
     * @param sellerAccount
     * @return
     */
    public Map<Object, Object> balance(SellerAccount sellerAccount);
    
    public Map<String, String> getWalletBalance(SellerAccount sellerAccount);

    /**
     * 更新钱包余额
     * @Title: updateWalletAmount 
     * @Description: TODO 
     * @param @param paramMap
     * @param @return    设定文件 
     * @return Map<String,String>    返回类型 
     * @throws
     */
    public Map<String, String> updateWalletAmount(Map<String, String> paramMap);
    

}
