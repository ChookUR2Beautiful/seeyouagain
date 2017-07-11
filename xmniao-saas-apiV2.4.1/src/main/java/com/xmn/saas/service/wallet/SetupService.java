package com.xmn.saas.service.wallet;

import java.util.Map;



public interface SetupService {
    /**
     * 设置自动分账
     * 
     * @param ledger 是否设置自动分账 0不自动分账，1自动分账
     * @return
     */
    public Map<String,Object> autoLedger(Integer ledger,Integer sellerId);

    /**
     * 设置自动提现
     * @param ledger 是否设置自动提现 0不自动分账，1自动分账
     * @param sellerId
     * @param money  店内收入
     * @param cmAmount 店外收入
     * @return
     */
    public Map<String, Object> autoWithdrawal(Integer mention, Integer sellerId,double money,double cmAmount);



}
