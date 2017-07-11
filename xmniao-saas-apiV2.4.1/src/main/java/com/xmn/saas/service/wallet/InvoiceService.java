package com.xmn.saas.service.wallet;

import java.util.Map;

import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.wallet.Orderinvoice;



public interface InvoiceService {
    /**
     * 申请发票
     * 
     * @param Integer ledger,
     * @param Integer sellerId
     * @return
     */
    public boolean apply(Orderinvoice orderinvoice,SellerAccount sellerAccount);




}
