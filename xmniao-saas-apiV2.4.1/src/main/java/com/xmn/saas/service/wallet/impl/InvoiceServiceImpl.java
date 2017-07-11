package com.xmn.saas.service.wallet.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.dao.wallet.OrderinvoiceDao;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.wallet.Orderinvoice;
import com.xmn.saas.service.wallet.InvoiceService;


@Service
public class InvoiceServiceImpl implements InvoiceService {



    @Autowired
    private GlobalConfig globalConfig;
    
    @Autowired
    private OrderinvoiceDao orderinvoiceDao;
    
    /**
     * 申请发票
     */
    @Transactional
    @Override
    public boolean apply(Orderinvoice orderinvoice,SellerAccount sellerAccount) {
        orderinvoice.setSellerName(sellerAccount.getFullname()==null?"":sellerAccount.getFullname());
        orderinvoice.setSellerTelephone(sellerAccount.getPhone());
        orderinvoice.setApplyDate(new Date());
        boolean flag = orderinvoiceDao.insertSelective(orderinvoice);
        return flag;
    }



}
