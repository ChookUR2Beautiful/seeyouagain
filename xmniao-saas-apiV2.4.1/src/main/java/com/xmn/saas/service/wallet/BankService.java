package com.xmn.saas.service.wallet;

import java.util.List;
import java.util.Map;

import com.xmn.saas.entity.wallet.BankApply;
import com.xmn.saas.entity.wallet.BankList;


public interface BankService {
    /**
     * 绑定/修改银行卡
     * 
     * @param passwordSetRequest
     * @return
     */
    public Map<Object,Object> put(BankApply bankApply);

    /**
     * 查看银行卡列表
     * 
     * @param type
     * @return
     */
    public Map<String,Object> list(String sellerId);
    
    /**
     * 删除银行卡
     * 
     * @param id
     * @return
     */
    public Map<Object, Object> delete(String id);
    
    /**
     * 
     * @Description: 查询所有银行卡列表
     * @author xiaoxiong
     * @date 2016年10月13日
     */
	public List<BankList> bankList();



}
