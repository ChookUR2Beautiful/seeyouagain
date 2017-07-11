package com.xmn.saas.service.sellercard.impl;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.dao.sellercard.DebitcardSellerDao;
import com.xmn.saas.entity.sellercard.DebitcardSeller;
import com.xmn.saas.service.sellercard.SellerCardService;

@Service
public class SellerCardServiceImpl implements SellerCardService {
    
    @Autowired
    private DebitcardSellerDao debitcardSellerDao;
    
    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public Map<String, Object> code(Integer sellerid) {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(sellerid.toString());
        resultMap.put("code", 0);//此商户已经开通储值卡充值
        if(debitcardSeller!=null){
        	resultMap.put("code", 1);//此商户没开通储值卡充值
        	Integer sellerType = debitcardSeller.getSellertype()==null?0:debitcardSeller.getSellertype();
        	Integer cardId = debitcardSeller.getId();
        	String url = globalConfig.getSellerCardCodeUrl()+"?id="+cardId+"&sellerType="+sellerType+"&sellerid="+sellerid;
        	resultMap.put("url", url);
        }
        
        return resultMap;
    }

}
