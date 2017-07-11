package com.xmn.saas.service.wallet.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.SynthesizeService;
import com.xmn.saas.service.wallet.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
    private final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);


    @Autowired
    private GlobalConfig globalConfig;

    // 调用支付服务获取钱包余额
    @Override
    public Map<String, String> getWalletBalance(SellerAccount sellerAccount) {
        Map<String, String> map = new HashMap<>();
        Integer type = sellerAccount.getType();// 1 老板帐号 2 店长帐号 3 店员帐号
        Integer sellerId = 0;
        sellerId = sellerAccount.getSellerid();
        try {
            SynthesizeService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftPayHost(),
                            Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
                            SynthesizeService.Client.class);

            ThriftBuilder.open();
            map = client.getWalletBalance(sellerId + "", 2);


        } catch (Exception e) {
            log.error("调用支付系统接口查询钱包余额异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return map;
    }

    /**
     * 钱包余额
     * 
     * @param sellerAccount
     * @return
     */
    @Override
    public Map<Object, Object> balance(SellerAccount sellerAccount) {
        Map<Object, Object> resultMap = new HashMap<>();
        log.info("访问getWalletBalance返回数据,【请求参数】：" +sellerAccount);
        Map<String, String> thriftMap = getWalletBalance(sellerAccount);
        String commision = thriftMap.get("commision") == null ? "0" : thriftMap.get("commision");
        String sellerAmount =
                thriftMap.get("seller_amount") == null ? "0" : thriftMap.get("seller_amount");
        resultMap.put("availableRebate", commision);// 可提现店外收入
        resultMap.put("availableTurnover", sellerAmount);// 可提现店内收入
        resultMap.put("allAvail", Double.valueOf(commision) + Double.valueOf(sellerAmount));// 可提现总额
        // 店长
        if (sellerAccount.getType() == 2) {
            resultMap.put("availableRebate", "");// 可提现店外收入
            resultMap.put("allAvail", Double.valueOf(sellerAmount));// 可提现总额
        }
        // 店员
        if (sellerAccount.getType() == 3) {
            resultMap.put("availableRebate", "");// 可提现店外收入
            resultMap.put("availableTurnover", "");// 可提现店内收入
            resultMap.put("allAvail", "");// 可提现总额
        }

        return resultMap;
    }
    
    /**
     * 更新钱包余额
     * @Title: updateWalletAmount 
     * @Description: TODO 	
     * @param @param paramMap
     * @param @return    设定文件 
     * @return Map<String,String>    返回类型 
     * @throws
     */
    public Map<String, String> updateWalletAmount(Map<String, String> paramMap){
    	Map<String, String> resultMap=new HashMap<String, String>();
		try {
			SynthesizeService.Client client =ThriftBuilder.build(globalConfig.getThriftPayHost(),
			                 Integer.parseInt(globalConfig.getThriftPayPort()), "SynthesizeService",
			                 SynthesizeService.Client.class);
			 ThriftBuilder.open();
			 resultMap= client.updateWalletAmount(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("调用支付服务更新钱包余额异常！", e);
		} finally {
            ThriftBuilder.close();
        }
    	return resultMap;
    }

}
