package com.xmn.saas.service.wallet.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.shop.SellerInfo;
import com.xmn.saas.service.bill.BillService;
import com.xmn.saas.service.bill.StatisticalService;
import com.xmn.saas.service.shop.SellerInfoService;
import com.xmn.saas.service.wallet.EarningsService;
import com.xmn.saas.service.wallet.WalletService;


@Service
public class EarningsServiceImpl implements EarningsService{
    
    @Autowired
    private WalletService walletService;
    
    
    @Autowired
    private BillService billService;
    
    
    @Autowired
    private StatisticalService statisticalService;
    
    @Autowired
    private SellerInfoService sellerinfoService;
    
    /**
     * 收益
     */
    @Override
    public Map<Object, Object> getBy(SellerAccount sellerAccount) {
        Map<Object,Object> resultMap  = walletService.balance(sellerAccount);
        Map<String,String> paramMap =new HashMap<>();
        paramMap.put("uid", sellerAccount.getSellerid()+"");
        //商家昵称
        SellerInfo sellerInfo=sellerinfoService.querySellerBySellerid(sellerAccount.getSellerid());
        resultMap.put("name", sellerInfo.getSellerName()==null?sellerInfo.getPhone():sellerInfo.getSellerName());
        Map<String, Object> amount = statisticalService.amount(sellerAccount);
        if(amount !=null ){
            Double todayAmount = Double.valueOf(amount.get("todayAmount").toString()) ;
            Double todayEarnings = Double.valueOf(amount.get("todayEarnings").toString());
            if(sellerAccount.getType()==1){
                resultMap.put("todayIncome", todayAmount+todayEarnings);
            }else if(sellerAccount.getType()==2 || sellerAccount.getType()==3){
                resultMap.put("todayIncome", todayAmount);
            }
        }
        return resultMap;
    }

}
