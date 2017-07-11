package com.xmn.saas.service.wallet;

import com.xmn.saas.base.thrift.common.ResponseList;
import com.xmn.saas.exception.SaasException;

import java.util.Map;


public interface WithdrawalService {
    /**
     * 总店提现授权
     * 
     * @param sellerAccount
     * @return
     */
    public Map<Object, Object> accredit(Integer operatingOut, Integer sellerid);


    public Map<Object, Object> apply(double amount, Integer type, Integer accountId, String name) throws SaasException;

    /**
     * 获取钱包的提现列表
     * 
     * @param walletMap
     * @return
     */
    public ResponseList getXmnWithdrawList(Map<String, String> walletMap);

    /**
     * 提现申请列表
     * 
     * @param walletMap
     * @return
     */
    public Map<Object, Object> list(Map<String, String> walletMap);

    /**
     * 查询 总店营业资金提现:0不允许，1允许'
     * @param sellerid
     * @return
     */
    Integer queryOperatingOut(Integer sellerid);
    
    public  Map<String,Object>  isCanDraw(int sellerid , int type);
    
    /**
     * 判断商户是否在线
     * @param sellerid
     * @param type
     * @return
     */
    public  boolean  isonline(int sellerid , int type);
}
