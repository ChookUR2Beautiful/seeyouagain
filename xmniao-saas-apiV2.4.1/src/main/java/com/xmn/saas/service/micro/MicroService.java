package com.xmn.saas.service.micro;

import java.util.Map;

import com.xmn.saas.entity.micro.MicroBill;

public interface MicroService {
    /**
     * 扫码支付
     * @return
     */
    public Map<String,Object> pay(MicroBill microBill);
    
    /**
     * 查询扫码支付
     * @return
     */
    public Map<String,Object> query(MicroBill microBill);
    
    /**
     * 撤销扫码支付
     * @return
     */
    public Map<String,Object> cancel(MicroBill microBill);

}
