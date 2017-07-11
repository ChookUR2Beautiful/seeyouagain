package com.xmn.saas.service.sellercard;

import java.util.Map;


/**
 * 储值卡服务类
 * 2017/03/02
 * @author zhouxiaojian
 *
 */
public interface SellerCardService {
    
    /**
     * 储值卡扫码充值
     * @return
     */
    public Map<String,Object> code(Integer sellerid);

}
