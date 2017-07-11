package com.xmn.saas.service.bill;

import java.text.ParseException;
import java.util.Map;

import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.base.thrift.common.ResponsePageList;
import com.xmn.saas.entity.common.SellerAccount;


public interface StatisticalService {
    /**
     * 获取商户的历史营收列表
     */
    public ResponsePageList getBusinessList(Map<String, String> map);

    /**
     * 根据条件查询账单金额统计信息
     */
    public Map<String, Object> get_by(Map<String, String> map, Integer type) throws ParseException;


    /**
     * 寻蜜鸟钱包可提现余额及提现统计
     * 
     * @param walletMap
     * @return
     */
    public ResponseData getXmnWithdrawAmount(Map<String, String> walletMap);

    /**
     * 获取商户当天经营收支信息
     * 
     * @param walletMap
     * @return
     */
    public ResponseData getBusinessInfo(Map<String, String> walletMap);
    
    
    /**
     * 账单金额统计
     */
    public Map<String, Object> amount(SellerAccount sellerAccount);



}
